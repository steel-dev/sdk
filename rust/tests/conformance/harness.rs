#![allow(dead_code)]

use std::future::Future;
use std::sync::atomic::{AtomicUsize, Ordering};
use std::sync::OnceLock;

use serde_json::Value;
use steel_rs::Client;
use wiremock::{Mock, MockServer, Request, Respond, ResponseTemplate};

pub enum Outcome {
    Json(Value),
    Items(Value),
    Ids(Vec<String>),
    Text(String),
    Empty,
}

fn fixture() -> &'static Value {
    static FIXTURE: OnceLock<Value> = OnceLock::new();
    FIXTURE.get_or_init(|| {
        serde_json::from_str(include_str!("cases.json")).expect("the conformance cases parse")
    })
}

struct Replies {
    responses: Vec<Value>,
    next: AtomicUsize,
}

impl Respond for Replies {
    fn respond(&self, _: &Request) -> ResponseTemplate {
        let index = self.next.fetch_add(1, Ordering::SeqCst);
        let Some(reply) = self.responses.get(index) else {
            return ResponseTemplate::new(500);
        };
        let status = u16::try_from(reply["status"].as_u64().unwrap_or(200)).unwrap_or(200);
        let template = ResponseTemplate::new(status);
        let content_type = reply["contentType"]
            .as_str()
            .unwrap_or("application/octet-stream");
        match (reply["body"].get("json"), reply["body"].get("text")) {
            (Some(json), _) => template.set_body_raw(
                serde_json::to_vec(json).expect("the response serializes"),
                content_type,
            ),
            (None, Some(text)) => {
                template.set_body_raw(text.as_str().unwrap_or_default().as_bytes(), content_type)
            }
            (None, None) => template,
        }
    }
}

fn digits(text: &str, start: usize, end: usize) -> Option<i64> {
    let slice = text.get(start..end)?;
    if slice.bytes().all(|byte| byte.is_ascii_digit()) {
        slice.parse().ok()
    } else {
        None
    }
}

fn instant(text: &str) -> Option<String> {
    let bytes = text.as_bytes();
    if bytes.len() < 17 || bytes[4] != b'-' || bytes[7] != b'-' || bytes[10] != b'T' {
        return None;
    }
    if bytes[13] != b':' {
        return None;
    }
    let year = digits(text, 0, 4)?;
    let month = digits(text, 5, 7)?;
    let day = digits(text, 8, 10)?;
    let hour = digits(text, 11, 13)?;
    let minute = digits(text, 14, 16)?;
    let mut rest = text.get(16..)?;
    let mut second = 0;
    let mut nanos = 0;
    if let Some(after) = rest.strip_prefix(':') {
        second = digits(after, 0, 2)?;
        rest = after.get(2..)?;
        if let Some(fraction) = rest.strip_prefix('.') {
            let count = fraction.bytes().take_while(u8::is_ascii_digit).count();
            let kept = &fraction[..count.min(9)];
            nanos = format!("{kept:0<9}").parse().ok()?;
            rest = &fraction[count..];
        }
    }
    let offset = match rest {
        "Z" | "z" => 0,
        _ => {
            let sign = match rest.as_bytes().first()? {
                b'+' => 1,
                b'-' => -1,
                _ => return None,
            };
            if rest.len() != 6 || rest.as_bytes()[3] != b':' {
                return None;
            }
            sign * (digits(rest, 1, 3)? * 60 + digits(rest, 4, 6)?)
        }
    };
    let shifted = if month <= 2 { year - 1 } else { year };
    let era = (if shifted >= 0 { shifted } else { shifted - 399 }) / 400;
    let year_of_era = shifted - era * 400;
    let day_of_year = (153 * ((month + 9) % 12) + 2) / 5 + day - 1;
    let day_of_era = year_of_era * 365 + year_of_era / 4 - year_of_era / 100 + day_of_year;
    let days = era * 146_097 + day_of_era - 719_468;
    let seconds = days * 86_400 + hour * 3_600 + minute * 60 + second - offset * 60;
    Some(format!("time:{seconds}.{nanos:09}"))
}

fn is_number(text: &str) -> bool {
    text.starts_with(|c: char| c.is_ascii_digit() || c == '-')
        && text
            .chars()
            .all(|c| c.is_ascii_digit() || matches!(c, '-' | '+' | '.' | 'e' | 'E'))
        && text.parse::<f64>().is_ok()
}

fn scalar(text: &str) -> String {
    if let Some(time) = instant(text) {
        return time;
    }
    if is_number(text) {
        return format!("number:{}", text.parse::<f64>().unwrap_or_default());
    }
    text.to_owned()
}

fn normalize(value: &Value) -> Value {
    match value {
        Value::Number(number) => {
            Value::String(format!("number:{}", number.as_f64().unwrap_or_default()))
        }
        Value::String(text) => Value::String(instant(text).unwrap_or_else(|| text.clone())),
        Value::Array(items) => Value::Array(items.iter().map(normalize).collect()),
        Value::Object(entries) => Value::Object(
            entries
                .iter()
                .map(|(key, item)| (key.clone(), normalize(item)))
                .collect(),
        ),
        Value::Null | Value::Bool(_) => value.clone(),
    }
}

fn decode(text: &str, plus: bool) -> String {
    let bytes = text.as_bytes();
    let mut out = Vec::with_capacity(bytes.len());
    let mut index = 0;
    while index < bytes.len() {
        match bytes[index] {
            b'%' => {
                match text
                    .get(index + 1..index + 3)
                    .and_then(|hex| u8::from_str_radix(hex, 16).ok())
                {
                    Some(byte) => {
                        out.push(byte);
                        index += 3;
                    }
                    None => {
                        out.push(b'%');
                        index += 1;
                    }
                }
            }
            b'+' if plus => {
                out.push(b' ');
                index += 1;
            }
            byte => {
                out.push(byte);
                index += 1;
            }
        }
    }
    String::from_utf8_lossy(&out).into_owned()
}

fn strings(value: &Value) -> Vec<String> {
    value
        .as_array()
        .map(|items| {
            items
                .iter()
                .map(|item| item.as_str().unwrap_or_default().to_owned())
                .collect()
        })
        .unwrap_or_default()
}

fn latin1(bytes: &[u8]) -> String {
    bytes.iter().map(|byte| char::from(*byte)).collect()
}

fn utf8(text: &str) -> String {
    let bytes: Vec<u8> = text
        .chars()
        .map(|c| u8::try_from(u32::from(c)).unwrap_or(b'?'))
        .collect();
    String::from_utf8_lossy(&bytes).into_owned()
}

fn attribute(disposition: &str, name: &str) -> Option<String> {
    disposition.split(';').find_map(|part| {
        let (key, value) = part.trim().split_once('=')?;
        (key.trim().eq_ignore_ascii_case(name)).then(|| value.trim().trim_matches('"').to_owned())
    })
}

fn parts(body: &[u8], content_type: &str) -> Vec<String> {
    let boundary = attribute(content_type, "boundary").expect("a multipart boundary");
    let text = latin1(body);
    let chunks: Vec<&str> = text.split(&format!("--{boundary}")).collect();
    let mut out: Vec<String> = chunks
        .iter()
        .skip(1)
        .take(chunks.len().saturating_sub(2))
        .map(|chunk| {
            let trimmed = chunk.strip_prefix("\r\n").unwrap_or(chunk);
            let trimmed = trimmed.strip_suffix("\r\n").unwrap_or(trimmed);
            let (head, content) = trimmed.split_once("\r\n\r\n").unwrap_or((trimmed, ""));
            let disposition = head
                .lines()
                .find(|line| {
                    line.to_ascii_lowercase()
                        .starts_with("content-disposition:")
                })
                .unwrap_or_default();
            let name = attribute(disposition, "name").unwrap_or_default();
            let content = utf8(content);
            if attribute(disposition, "filename").is_some() {
                format!("{name}=file:{content}")
            } else {
                format!("{name}={}", scalar(&content))
            }
        })
        .collect();
    out.sort();
    out
}

fn check(id: &str, request: &Request, expected: &Value) {
    assert_eq!(
        request.method.as_str(),
        expected["method"].as_str().unwrap_or_default(),
        "{id} method"
    );
    let path: Vec<String> = request
        .url
        .path()
        .split('/')
        .filter(|segment| !segment.is_empty())
        .map(|segment| decode(segment, false))
        .collect();
    assert_eq!(path, strings(&expected["path"]), "{id} path");
    let mut query: Vec<String> = request
        .url
        .query()
        .unwrap_or_default()
        .split('&')
        .filter(|pair| !pair.is_empty())
        .map(|pair| {
            let (key, value) = pair.split_once('=').unwrap_or((pair, ""));
            format!("{}={}", decode(key, true), scalar(&decode(value, true)))
        })
        .collect();
    query.sort();
    let mut expected_query: Vec<String> = expected["query"]
        .as_array()
        .map(|pairs| {
            pairs
                .iter()
                .map(|pair| {
                    format!(
                        "{}={}",
                        pair[0].as_str().unwrap_or_default(),
                        scalar(pair[1].as_str().unwrap_or_default())
                    )
                })
                .collect()
        })
        .unwrap_or_default();
    expected_query.sort();
    assert_eq!(query, expected_query, "{id} query");
    if let Some(headers) = expected["headers"].as_object() {
        for (name, value) in headers {
            let actual = request
                .headers
                .get(name.as_str())
                .and_then(|header| header.to_str().ok())
                .unwrap_or_default();
            assert_eq!(
                actual,
                value.as_str().unwrap_or_default(),
                "{id} header {name}"
            );
        }
    }
    for (name, value) in [
        ("x-steel-lang", "rust"),
        ("x-steel-package-version", steel_rs::VERSION),
    ] {
        let actual = request
            .headers
            .get(name)
            .and_then(|header| header.to_str().ok())
            .unwrap_or_default();
        assert_eq!(actual, value, "{id} header {name}");
    }
    let content_type = request
        .headers
        .get("content-type")
        .and_then(|header| header.to_str().ok())
        .unwrap_or_default()
        .to_owned();
    let body = &expected["body"];
    if body.is_null() {
        assert!(request.body.is_empty(), "{id} body should be empty");
    } else if let Some(json) = body.get("json") {
        assert!(
            content_type.starts_with("application/json"),
            "{id} content type {content_type}"
        );
        let actual: Value = serde_json::from_slice(&request.body).expect("a JSON body");
        assert_eq!(normalize(&actual), normalize(json), "{id} body");
    } else {
        assert!(
            content_type.starts_with("multipart/form-data"),
            "{id} content type {content_type}"
        );
        let mut expected_parts: Vec<String> = body["multipart"]
            .as_array()
            .map(|parts| {
                parts
                    .iter()
                    .map(|part| {
                        let name = part["name"].as_str().unwrap_or_default();
                        match part.get("file") {
                            Some(file) => {
                                format!("{name}=file:{}", file.as_str().unwrap_or_default())
                            }
                            None => format!(
                                "{name}={}",
                                scalar(part["value"].as_str().unwrap_or_default())
                            ),
                        }
                    })
                    .collect()
            })
            .unwrap_or_default();
        expected_parts.sort();
        assert_eq!(
            parts(&request.body, &content_type),
            expected_parts,
            "{id} parts"
        );
    }
}

pub async fn conform<F, Fut>(id: &str, call: F)
where
    F: FnOnce(Client) -> Fut,
    Fut: Future<Output = Outcome>,
{
    let fixture = fixture();
    let case = &fixture["cases"][id];
    assert!(case.is_object(), "unknown conformance case {id}");
    let exchanges = case["exchanges"].as_array().cloned().unwrap_or_default();
    let server = MockServer::start().await;
    Mock::given(wiremock::matchers::any())
        .respond_with(Replies {
            responses: exchanges
                .iter()
                .map(|exchange| exchange["response"].clone())
                .collect(),
            next: AtomicUsize::new(0),
        })
        .mount(&server)
        .await;
    let client = Client::builder()
        .base_url(server.uri())
        .api_key(fixture["apiKey"].as_str().unwrap_or_default())
        .max_retries(0)
        .build();
    let outcome = call(client).await;
    let requests = server.received_requests().await.unwrap_or_default();
    assert_eq!(requests.len(), exchanges.len(), "{id} requests");
    for (request, exchange) in requests.iter().zip(&exchanges) {
        check(id, request, &exchange["request"]);
    }
    let expected = &case["result"];
    let lenient = case["lenient"].as_bool().unwrap_or(false);
    match outcome {
        Outcome::Json(value) if !lenient => {
            assert_eq!(
                normalize(&value),
                normalize(&expected["rust"]),
                "{id} result"
            );
        }
        Outcome::Items(value) if !lenient => {
            assert_eq!(
                normalize(&value),
                normalize(&expected["rustItems"]),
                "{id} items"
            );
        }
        Outcome::Ids(ids) => assert_eq!(ids, strings(&expected["ids"]), "{id} ids"),
        Outcome::Text(text) => {
            assert_eq!(
                text,
                expected["text"].as_str().unwrap_or_default(),
                "{id} text"
            );
        }
        Outcome::Json(_) | Outcome::Items(_) | Outcome::Empty => {}
    }
}
