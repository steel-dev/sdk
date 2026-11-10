use std::collections::hash_map::RandomState;
use std::collections::VecDeque;
use std::future::Future;
use std::hash::{BuildHasher, Hasher};
use std::sync::atomic::{AtomicU64, Ordering};
use std::sync::Arc;
use std::time::{Duration, SystemTime, UNIX_EPOCH};

use bytes::Bytes;
use futures_util::stream::{self, Stream, StreamExt};
use reqwest::header::HeaderMap;
use reqwest::{Method, StatusCode};
use serde::de::DeserializeOwned;
use serde_json::Value;

use crate::encoding::FormField;
use crate::error::Error;

const DEFAULT_TIMEOUT: Duration = Duration::from_secs(60);
const DEFAULT_MAX_RETRIES: u32 = 2;

#[derive(Debug, Clone)]
pub struct Client {
    inner: Arc<Inner>,
}

#[derive(Debug)]
struct Inner {
    http: reqwest::Client,
    base_url: String,
    api_key: Option<String>,
    headers: Vec<(String, String)>,
    max_retries: u32,
    timeout: Duration,
}

#[derive(Debug, Clone)]
pub struct ClientBuilder {
    base_url: String,
    api_key: Option<String>,
    headers: Vec<(String, String)>,
    max_retries: u32,
    timeout: Duration,
    http: Option<reqwest::Client>,
}

impl Default for ClientBuilder {
    fn default() -> Self {
        let headers = std::env::var(crate::CUSTOM_HEADERS_ENV)
            .map(|text| {
                text.lines()
                    .filter_map(|line| line.split_once(':'))
                    .map(|(name, value)| (name.trim().to_owned(), value.trim().to_owned()))
                    .collect()
            })
            .unwrap_or_default();
        ClientBuilder {
            base_url: std::env::var(crate::BASE_URL_ENV)
                .unwrap_or_else(|_| crate::DEFAULT_BASE_URL.to_owned()),
            api_key: std::env::var(crate::API_KEY_ENV).ok(),
            headers,
            max_retries: DEFAULT_MAX_RETRIES,
            timeout: DEFAULT_TIMEOUT,
            http: None,
        }
    }
}

impl ClientBuilder {
    pub fn base_url(mut self, base_url: impl Into<String>) -> Self {
        self.base_url = base_url.into();
        self
    }

    pub fn api_key(mut self, api_key: impl Into<String>) -> Self {
        self.api_key = Some(api_key.into());
        self
    }

    pub fn header(mut self, name: impl Into<String>, value: impl Into<String>) -> Self {
        self.headers.push((name.into(), value.into()));
        self
    }

    pub fn max_retries(mut self, max_retries: u32) -> Self {
        self.max_retries = max_retries;
        self
    }

    pub fn timeout(mut self, timeout: Duration) -> Self {
        self.timeout = timeout;
        self
    }

    pub fn http_client(mut self, http: reqwest::Client) -> Self {
        self.http = Some(http);
        self
    }

    pub fn build(self) -> Client {
        Client {
            inner: Arc::new(Inner {
                http: self.http.unwrap_or_default(),
                base_url: self.base_url,
                api_key: self.api_key,
                headers: self.headers,
                max_retries: self.max_retries,
                timeout: self.timeout,
            }),
        }
    }
}

impl Default for Client {
    fn default() -> Self {
        Client::new()
    }
}

#[derive(Debug, Clone)]
pub(crate) enum Body {
    None,
    Json(Value),
    Form(Vec<FormField>),
}

#[derive(Debug, Clone)]
pub(crate) struct Request {
    pub(crate) method: Method,
    pub(crate) path: String,
    pub(crate) query: Vec<(String, String)>,
    pub(crate) headers: Vec<(String, String)>,
    pub(crate) body: Body,
    pub(crate) accept: Option<&'static str>,
}

impl Request {
    pub(crate) fn new(method: Method, path: String) -> Self {
        Request {
            method,
            path,
            query: Vec::new(),
            headers: Vec::new(),
            body: Body::None,
            accept: None,
        }
    }
}

#[derive(Debug, Clone, Copy)]
pub(crate) struct Cursor {
    pub(crate) items: &'static str,
    pub(crate) param: &'static str,
    pub(crate) from: &'static str,
}

fn retryable(status: StatusCode, headers: &HeaderMap) -> bool {
    match headers
        .get("x-should-retry")
        .and_then(|value| value.to_str().ok())
    {
        Some("true") => true,
        Some("false") => false,
        Some(_) | None => matches!(status.as_u16(), 408 | 409 | 429) || status.is_server_error(),
    }
}

fn backoff(attempt: u32, headers: &HeaderMap) -> Duration {
    let header = |name: &str| {
        headers
            .get(name)
            .and_then(|value| value.to_str().ok())
            .and_then(|value| value.parse::<f64>().ok())
    };
    let hinted = header("retry-after-ms")
        .map(|millis| millis / 1000.0)
        .or_else(|| header("retry-after"));
    if let Some(seconds) = hinted.filter(|seconds| (0.0..=60.0).contains(seconds)) {
        return Duration::from_secs_f64(seconds);
    }
    let base = (0.5 * 2f64.powi(attempt as i32)).min(8.0);
    let nanos = SystemTime::now()
        .duration_since(UNIX_EPOCH)
        .map(|elapsed| elapsed.subsec_nanos())
        .unwrap_or_default();
    let jitter = 1.0 - f64::from(nanos % 1000) / 4000.0;
    Duration::from_secs_f64(base * jitter)
}

impl Client {
    pub fn new() -> Self {
        ClientBuilder::default().build()
    }

    pub fn builder() -> ClientBuilder {
        ClientBuilder::default()
    }

    pub fn base_url(&self) -> &str {
        &self.inner.base_url
    }

    fn idempotency_key(&self, request: &Request) -> Option<String> {
        if request.method == Method::GET {
            return None;
        }
        let given = self
            .inner
            .headers
            .iter()
            .chain(&request.headers)
            .any(|(name, _)| name.eq_ignore_ascii_case(crate::IDEMPOTENCY_HEADER));
        if given {
            return None;
        }
        Some(new_idempotency_key())
    }

    async fn attempt(
        &self,
        request: &Request,
        idempotency_key: Option<&str>,
    ) -> Result<reqwest::Response, reqwest::Error> {
        let url = format!(
            "{}{}",
            self.inner.base_url.trim_end_matches('/'),
            request.path
        );
        let mut builder = self
            .inner
            .http
            .request(request.method.clone(), &url)
            .timeout(self.inner.timeout)
            .header(
                "user-agent",
                format!("{}/Rust {}", crate::CLIENT, crate::VERSION),
            )
            .header("x-steel-lang", "rust")
            .header("x-steel-package-version", crate::VERSION)
            .header("x-steel-os", os())
            .header("x-steel-arch", arch());
        if !request.query.is_empty() {
            builder = builder.query(&request.query);
        }
        if let Some(api_key) = &self.inner.api_key {
            builder = builder.header(crate::API_KEY_HEADER, api_key);
        }
        for (name, value) in self.inner.headers.iter().chain(&request.headers) {
            builder = builder.header(name, value);
        }
        if let Some(key) = idempotency_key {
            builder = builder.header(crate::IDEMPOTENCY_HEADER, key);
        }
        if let Some(accept) = request.accept {
            builder = builder.header("accept", accept);
        }
        builder = match &request.body {
            Body::None => builder,
            Body::Json(value) => builder.json(value),
            Body::Form(fields) => {
                let mut form = reqwest::multipart::Form::new();
                for field in fields {
                    form = match field {
                        FormField::Text(name, text) => form.text(name.clone(), text.clone()),
                        FormField::File(name, file) => form.part(name.clone(), file.part()?),
                    };
                }
                builder.multipart(form)
            }
        };
        builder.send().await
    }

    pub(crate) async fn send(&self, request: &Request) -> Result<reqwest::Response, Error> {
        let idempotency_key = self.idempotency_key(request);
        let mut attempt = 0;
        loop {
            match self.attempt(request, idempotency_key.as_deref()).await {
                Ok(response) => {
                    let status = response.status();
                    if status.is_success() {
                        return Ok(response);
                    }
                    if attempt < self.inner.max_retries && retryable(status, response.headers()) {
                        tokio::time::sleep(backoff(attempt, response.headers())).await;
                        attempt += 1;
                        continue;
                    }
                    let headers = response.headers().clone();
                    let body = response.text().await.unwrap_or_default();
                    return Err(Error::Api {
                        status: status.as_u16(),
                        body,
                        headers,
                    });
                }
                Err(error) => {
                    if attempt < self.inner.max_retries
                        && (error.is_connect() || error.is_timeout())
                    {
                        tokio::time::sleep(backoff(attempt, &HeaderMap::new())).await;
                        attempt += 1;
                        continue;
                    }
                    return Err(Error::Http(error));
                }
            }
        }
    }

    pub(crate) async fn json<T: DeserializeOwned>(&self, request: Request) -> Result<T, Error> {
        let response = self.send(&request).await?;
        let bytes = response.bytes().await?;
        Ok(serde_json::from_slice(&bytes)?)
    }

    pub(crate) async fn empty(&self, request: Request) -> Result<(), Error> {
        self.send(&request).await?;
        Ok(())
    }

    pub(crate) async fn bytes(&self, request: Request) -> Result<Bytes, Error> {
        Ok(self.send(&request).await?.bytes().await?)
    }

    pub(crate) async fn page<T: DeserializeOwned>(
        &self,
        request: Request,
        cursor: Cursor,
    ) -> Result<Page<T>, Error> {
        let response = self.send(&request).await?;
        let bytes = response.bytes().await?;
        let mut value: Value = serde_json::from_slice(&bytes)?;
        let raw = match value.get_mut(cursor.items).map(Value::take) {
            Some(Value::Array(items)) => items,
            Some(_) | None => Vec::new(),
        };
        let last = raw
            .last()
            .and_then(|item| item.get(cursor.from))
            .and_then(Value::as_str)
            .map(str::to_owned);
        let items = raw
            .into_iter()
            .map(serde_json::from_value)
            .collect::<Result<Vec<T>, _>>()?;
        let next = last.map(|last| {
            let mut next = request.clone();
            next.query.retain(|(name, _)| name != cursor.param);
            next.query.push((cursor.param.to_owned(), last));
            next
        });
        Ok(Page {
            items,
            next,
            client: self.clone(),
            cursor,
        })
    }
}

#[derive(Debug, Clone)]
pub struct Page<T> {
    items: Vec<T>,
    next: Option<Request>,
    client: Client,
    cursor: Cursor,
}

impl<T: DeserializeOwned> Page<T> {
    pub fn items(&self) -> &[T] {
        &self.items
    }

    pub fn into_items(self) -> Vec<T> {
        self.items
    }

    pub fn has_next_page(&self) -> bool {
        self.next.is_some()
    }

    pub async fn next_page(&self) -> Result<Option<Page<T>>, Error> {
        match &self.next {
            Some(request) => self
                .client
                .page(request.clone(), self.cursor)
                .await
                .map(Some),
            None => Ok(None),
        }
    }

    pub fn into_stream(self) -> impl Stream<Item = Result<T, Error>> {
        let state = (
            VecDeque::from(self.items),
            self.next,
            self.client,
            self.cursor,
            false,
        );
        stream::unfold(state, |mut state| async move {
            loop {
                if let Some(item) = state.0.pop_front() {
                    return Some((Ok(item), state));
                }
                if state.4 {
                    return None;
                }
                let request = state.1.take()?;
                match state.2.page::<T>(request, state.3).await {
                    Ok(page) => {
                        state.0 = VecDeque::from(page.items);
                        state.1 = page.next;
                    }
                    Err(error) => {
                        state.4 = true;
                        return Some((Err(error), state));
                    }
                }
            }
        })
    }
}

pub(crate) fn auto_paging<T, F>(first: F) -> impl Stream<Item = Result<T, Error>>
where
    T: DeserializeOwned,
    F: Future<Output = Result<Page<T>, Error>>,
{
    stream::once(first).flat_map(|result| match result {
        Ok(page) => page.into_stream().left_stream(),
        Err(error) => stream::once(async move { Err(error) }).right_stream(),
    })
}

fn new_idempotency_key() -> String {
    static COUNTER: AtomicU64 = AtomicU64::new(0);
    let count = COUNTER.fetch_add(1, Ordering::Relaxed);
    let nanos = SystemTime::now()
        .duration_since(UNIX_EPOCH)
        .map(|elapsed| elapsed.as_nanos())
        .unwrap_or_default();
    let mut bits = 0u128;
    for round in 0..2u8 {
        let mut hasher = RandomState::new().build_hasher();
        hasher.write_u64(count);
        hasher.write_u128(nanos);
        hasher.write_u8(round);
        bits = (bits << 64) | u128::from(hasher.finish());
    }
    bits = (bits & !(0xf << 76)) | (0x4 << 76);
    bits = (bits & !(0x3 << 62)) | (0x2 << 62);
    let hex = format!("{bits:032x}");
    format!(
        "steel-rust-retry-{}-{}-{}-{}-{}",
        &hex[0..8],
        &hex[8..12],
        &hex[12..16],
        &hex[16..20],
        &hex[20..32]
    )
}

fn os() -> String {
    match std::env::consts::OS {
        "ios" => "iOS".to_owned(),
        "android" => "Android".to_owned(),
        "macos" => "MacOS".to_owned(),
        "windows" => "Windows".to_owned(),
        "freebsd" => "FreeBSD".to_owned(),
        "openbsd" => "OpenBSD".to_owned(),
        "linux" => "Linux".to_owned(),
        other => format!("Other:{other}"),
    }
}

fn arch() -> String {
    match std::env::consts::ARCH {
        "x86" => "x32".to_owned(),
        "x86_64" => "x64".to_owned(),
        "arm" => "arm".to_owned(),
        "aarch64" => "arm64".to_owned(),
        other => format!("other:{other}"),
    }
}

#[cfg(test)]
mod tests {
    use wiremock::matchers::method;
    use wiremock::{Mock, MockServer, ResponseTemplate};

    use super::{Client, Method, Request};

    async fn server(failures: u64) -> MockServer {
        let server = MockServer::start().await;
        if failures > 0 {
            Mock::given(method("POST"))
                .respond_with(ResponseTemplate::new(503).insert_header("retry-after-ms", "10"))
                .up_to_n_times(failures)
                .with_priority(1)
                .mount(&server)
                .await;
        }
        Mock::given(wiremock::matchers::any())
            .respond_with(ResponseTemplate::new(200))
            .mount(&server)
            .await;
        server
    }

    fn client(server: &MockServer) -> Client {
        Client::builder()
            .base_url(server.uri())
            .api_key("My API Key")
            .build()
    }

    async fn keys(server: &MockServer) -> Vec<Vec<String>> {
        server
            .received_requests()
            .await
            .unwrap_or_default()
            .iter()
            .map(|request| {
                request
                    .headers
                    .get_all(crate::IDEMPOTENCY_HEADER)
                    .iter()
                    .filter_map(|value| value.to_str().ok())
                    .map(str::to_owned)
                    .collect()
            })
            .collect()
    }

    #[tokio::test]
    async fn a_post_sends_one_key_and_reuses_it_on_retry() {
        let server = server(1).await;
        let client = client(&server);
        let request = Request::new(Method::POST, "/foo".to_owned());

        client.send(&request).await.expect("the first call");
        client.send(&request).await.expect("the second call");

        let keys = keys(&server).await;
        assert_eq!(keys.len(), 3);
        assert_eq!(keys[0].len(), 1);
        assert!(keys[0][0].starts_with("steel-rust-retry-"));
        assert_eq!(keys[1], keys[0]);
        assert_eq!(keys[2].len(), 1);
        assert_ne!(keys[2], keys[0]);
    }

    #[tokio::test]
    async fn a_get_sends_no_key() {
        let server = server(0).await;
        let client = client(&server);

        client
            .send(&Request::new(Method::GET, "/foo".to_owned()))
            .await
            .expect("the call");

        assert_eq!(keys(&server).await, vec![Vec::<String>::new()]);
    }

    #[tokio::test]
    async fn a_should_retry_false_answer_is_not_retried() {
        let server = MockServer::start().await;
        Mock::given(method("POST"))
            .respond_with(ResponseTemplate::new(503).insert_header("x-should-retry", "false"))
            .mount(&server)
            .await;
        let client = client(&server);

        let result = client
            .send(&Request::new(Method::POST, "/foo".to_owned()))
            .await;

        assert!(result.is_err());
        assert_eq!(keys(&server).await.len(), 1);
    }

    #[tokio::test]
    async fn a_should_retry_true_answer_is_retried() {
        let server = MockServer::start().await;
        Mock::given(method("POST"))
            .respond_with(
                ResponseTemplate::new(400)
                    .insert_header("x-should-retry", "true")
                    .insert_header("retry-after-ms", "10"),
            )
            .up_to_n_times(1)
            .with_priority(1)
            .mount(&server)
            .await;
        Mock::given(wiremock::matchers::any())
            .respond_with(ResponseTemplate::new(200))
            .mount(&server)
            .await;
        let client = client(&server);

        client
            .send(&Request::new(Method::POST, "/foo".to_owned()))
            .await
            .expect("the retried call");

        assert_eq!(keys(&server).await.len(), 2);
    }

    #[tokio::test]
    async fn a_key_in_the_headers_wins() {
        let server = server(1).await;
        let client = client(&server);
        let mut request = Request::new(Method::POST, "/foo".to_owned());
        request
            .headers
            .push(("idempotency-key".to_owned(), "mine".to_owned()));

        client.send(&request).await.expect("the call");

        assert_eq!(
            keys(&server).await,
            vec![vec!["mine".to_owned()], vec!["mine".to_owned()]]
        );
    }

    #[test]
    fn keys_are_unique_version_four_uuids() {
        let first = super::new_idempotency_key();
        let second = super::new_idempotency_key();
        assert_ne!(first, second);
        let uuid = first.trim_start_matches("steel-rust-retry-");
        let groups: Vec<&str> = uuid.split('-').collect();
        assert_eq!(
            groups.iter().map(|group| group.len()).collect::<Vec<_>>(),
            vec![8, 4, 4, 4, 12]
        );
        assert!(groups[2].starts_with('4'));
        assert!(matches!(
            groups[3].chars().next(),
            Some('8' | '9' | 'a' | 'b')
        ));
    }
}
