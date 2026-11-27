pub mod captchas;
pub use captchas::*;
pub mod command_executions;
pub use command_executions::*;
pub mod credentials;
pub use credentials::*;
pub mod files;
pub use files::*;

use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct Sessions<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> Sessions<'a> {
    pub fn captchas(&self) -> crate::SessionsCaptchas<'a> {
        crate::SessionsCaptchas {
            client: self.client,
        }
    }

    pub fn command_executions(&self) -> crate::SessionsCommandExecutions<'a> {
        crate::SessionsCommandExecutions {
            client: self.client,
        }
    }

    pub fn credentials(&self) -> crate::SessionsCredentials<'a> {
        crate::SessionsCredentials {
            client: self.client,
        }
    }

    pub fn files(&self) -> crate::SessionsFiles<'a> {
        crate::SessionsFiles {
            client: self.client,
        }
    }

    /// Creates a new session with the provided configuration.
    pub fn create(&self, params: SessionCreateParams) -> crate::Call<'a, crate::Session> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request =
                crate::client::Request::new(reqwest::Method::POST, "/v1/sessions".to_owned());
            let mut body = serde_json::Map::new();
            if let Some(value) = &params.block_ads {
                body.insert("blockAds".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.ca_certificates {
                body.insert("caCertificates".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.concurrency {
                body.insert("concurrency".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.credentials {
                body.insert("credentials".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.debug_config {
                body.insert("debugConfig".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.device_config {
                body.insert("deviceConfig".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.dimensions {
                body.insert("dimensions".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.experimental_features {
                body.insert(
                    "experimentalFeatures".to_owned(),
                    serde_json::to_value(value)?,
                );
            }
            if let Some(value) = &params.extension_ids {
                body.insert("extensionIds".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.fullscreen {
                body.insert("fullscreen".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.headless {
                body.insert("headless".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.inactivity_timeout {
                body.insert("inactivityTimeout".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.is_selenium {
                body.insert("isSelenium".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.metadata {
                body.insert("metadata".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.namespace {
                body.insert("namespace".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.optimize_bandwidth {
                body.insert("optimizeBandwidth".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.persist_profile {
                body.insert("persistProfile".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.profile_id {
                body.insert("profileId".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.project_id {
                body.insert("projectId".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.proxy_url {
                body.insert("proxyUrl".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.recording {
                body.insert("recording".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.region {
                body.insert("region".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.session_context {
                body.insert("sessionContext".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.session_id {
                body.insert("sessionId".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.solve_captcha {
                body.insert("solveCaptcha".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.stealth_config {
                body.insert("stealthConfig".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.timeout {
                body.insert("timeout".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.use_proxy {
                body.insert("useProxy".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.user_agent {
                body.insert("userAgent".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// Retrieves details of a specific session by ID.
    pub fn retrieve(&self, id: &str) -> crate::Call<'a, crate::Session> {
        let client = self.client;
        let path = format!("/v1/sessions/{}", crate::encoding::segment(id));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, path);
            client.json(request).await
        })
    }

    /// Fetches all active sessions for the current organization.
    pub fn list(
        &self,
        params: SessionListParams,
    ) -> crate::Call<'a, crate::Page<SessionslistSession>> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request =
                crate::client::Request::new(reqwest::Method::GET, "/v1/sessions".to_owned());
            if let Some(value) = &params.created_after {
                request.query.extend(crate::encoding::query(
                    "createdAfter",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.created_before {
                request.query.extend(crate::encoding::query(
                    "createdBefore",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.cursor_id {
                request.query.extend(crate::encoding::query(
                    "cursorId",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.limit {
                request.query.extend(crate::encoding::query(
                    "limit",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.metadata {
                request.query.extend(crate::encoding::query(
                    "metadata",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.release_reason {
                request.query.extend(crate::encoding::query(
                    "releaseReason",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.signal {
                request.query.extend(crate::encoding::query(
                    "signal",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.status {
                request.query.extend(crate::encoding::query(
                    "status",
                    &serde_json::to_value(value)?,
                ));
            }
            client
                .page(
                    request,
                    crate::client::Cursor {
                        items: "sessions",
                        param: "cursorId",
                        from: "id",
                    },
                )
                .await
        })
    }

    /// Fetches all active sessions for the current organization.
    pub fn list_auto_paging(
        &self,
        params: SessionListParams,
    ) -> impl futures_util::Stream<Item = Result<SessionslistSession, crate::Error>> + 'a {
        let this = *self;
        crate::client::auto_paging(async move { this.list(params).await })
    }

    /// Query the semantic agent trace timeline for a session: a coalesced, replay-oriented record of user/agent interactions (clicks, typing, scrolls, drags, navigations) with target metadata (role, accessible name, selectors). Returns an empty list for sessions that predate browser-interaction capture.
    pub fn agent_traces(
        &self,
        id: &str,
        params: SessionAgentTracesParams,
    ) -> crate::Call<'a, SessionAgentTracesResponse> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/agent-traces", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            if let Some(value) = &params.end_time {
                request.query.extend(crate::encoding::query(
                    "endTime",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.event_types {
                request.query.extend(crate::encoding::query(
                    "eventTypes",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.limit {
                request.query.extend(crate::encoding::query(
                    "limit",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.namespace {
                request.query.extend(crate::encoding::query(
                    "namespace",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.offset {
                request.query.extend(crate::encoding::query(
                    "offset",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.start_time {
                request.query.extend(crate::encoding::query(
                    "startTime",
                    &serde_json::to_value(value)?,
                ));
            }
            client.json(request).await
        })
    }

    /// Drives the session's browser with high-level commands such as snapshot, click, and fill. Commands run in order and stop at the first failure. Send a random Idempotency-Key header to make retries safe.
    pub fn commands(
        &self,
        session_id: &str,
        params: SessionCommandsParams,
    ) -> crate::Call<'a, SessionCommandsResponse> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/commands",
            crate::encoding::segment(session_id)
        );
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::POST, path);
            if let Some(value) = &params.idempotency_key {
                request.headers.extend(crate::encoding::query(
                    "idempotency-key",
                    &serde_json::to_value(value)?,
                ));
            }
            let mut body = serde_json::Map::new();
            body.insert(
                "commands".to_owned(),
                serde_json::to_value(&params.commands)?,
            );
            if let Some(value) = &params.after {
                body.insert("after".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.observe {
                body.insert("observe".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// Execute computer actions like mouse movements, clicks, keyboard input, and more
    pub fn computer(
        &self,
        session_id: &str,
        params: SessionComputerParams,
    ) -> crate::Call<'a, SessionComputerResponse> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/computer",
            crate::encoding::segment(session_id)
        );
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::POST, path);
            request.body = crate::client::Body::Json(serde_json::to_value(&params)?);
            client.json(request).await
        })
    }

    /// Fetches the context data of a specific session.
    pub fn context(&self, id: &str) -> crate::Call<'a, crate::SessionContext> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/context", crate::encoding::segment(id));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, path);
            client.json(request).await
        })
    }

    /// Returns the finalized cost of a released session as an exact amount in US dollars (e.g. 0.031905), suitable for precise billing passthrough.
    pub fn cost(&self, id: &str) -> crate::Call<'a, SessionCostResponse> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/cost", crate::encoding::segment(id));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, path);
            client.json(request).await
        })
    }

    /// This endpoint allows you to get the recorded session events in the RRWeb format
    pub fn events(
        &self,
        id: &str,
        params: SessionEventsParams,
    ) -> crate::Call<'a, Vec<serde_json::Value>> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/events", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            if let Some(value) = &params.compressed {
                request.query.extend(crate::encoding::query(
                    "compressed",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.limit {
                request.query.extend(crate::encoding::query(
                    "limit",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.pointer {
                request.query.extend(crate::encoding::query(
                    "pointer",
                    &serde_json::to_value(value)?,
                ));
            }
            client.json(request).await
        })
    }

    /// Get HLS playlist for a session recording using app auth or an RTC token
    pub fn hls(&self, id: &str) -> crate::Call<'a, bytes::Bytes> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/hls", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            request.accept = Some("application/vnd.apple.mpegurl");
            client.bytes(request).await
        })
    }

    /// Returns the live state of the session, including pages, tabs, and browser state
    pub fn live_details(&self, id: &str) -> crate::Call<'a, SessionLiveDetailsResponse> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/live-details", crate::encoding::segment(id));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, path);
            client.json(request).await
        })
    }

    /// Query browser event logs for a session
    pub fn logs(
        &self,
        id: &str,
        params: SessionLogsParams,
    ) -> crate::Call<'a, SessionLogsResponse> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/logs", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            if let Some(value) = &params.end_time {
                request.query.extend(crate::encoding::query(
                    "endTime",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.event_types {
                request.query.extend(crate::encoding::query(
                    "eventTypes",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.limit {
                request.query.extend(crate::encoding::query(
                    "limit",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.namespace {
                request.query.extend(crate::encoding::query(
                    "namespace",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.offset {
                request.query.extend(crate::encoding::query(
                    "offset",
                    &serde_json::to_value(value)?,
                ));
            }
            if let Some(value) = &params.start_time {
                request.query.extend(crate::encoding::query(
                    "startTime",
                    &serde_json::to_value(value)?,
                ));
            }
            client.json(request).await
        })
    }

    /// Releases a specific session by ID.
    pub fn release(&self, id: &str) -> crate::Call<'a, SessionReleaseResponse> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/release", crate::encoding::segment(id));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::POST, path);
            client.json(request).await
        })
    }

    /// Releases all active sessions for the current organization.
    pub fn release_all(
        &self,
        params: SessionReleaseAllParams,
    ) -> crate::Call<'a, SessionReleaseAllResponse> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(
                reqwest::Method::POST,
                "/v1/sessions/release".to_owned(),
            );
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            client.json(request).await
        })
    }

    /// Atomically replace customer metadata. Send an empty object to clear it. Last accepted write wins; use PATCH to change individual keys. Subject to session organization and project access.
    pub fn replace_metadata(
        &self,
        id: &str,
        params: SessionReplaceMetadataParams,
    ) -> crate::Call<'a, SessionReplaceMetadataResponse> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/metadata", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::PUT, path);
            let mut body = serde_json::Map::new();
            body.insert(
                "metadata".to_owned(),
                serde_json::to_value(&params.metadata)?,
            );
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// Returns current signal labels and meanings for customer integrations. Uses normal Steel API-key authentication. Includes no session data or administrative signals. Definitions do not establish that detection ran for a particular session.
    pub fn signal_catalog(&self) -> crate::Call<'a, SessionSignalCatalogResponse> {
        let client = self.client;
        crate::Call::new(async move {
            let request = crate::client::Request::new(
                reqwest::Method::GET,
                "/v1/sessions/signals/catalog".to_owned(),
            );
            client.json(request).await
        })
    }

    /// Atomically merge keys into customer metadata. Set a key to null to remove it; keys you leave out are kept, so concurrent updates to different keys are all kept. Returns the merged metadata, which must stay within the metadata limits. Subject to session organization and project access.
    pub fn update_metadata(
        &self,
        id: &str,
        params: SessionUpdateMetadataParams,
    ) -> crate::Call<'a, SessionUpdateMetadataResponse> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/metadata", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::PATCH, path);
            let mut body = serde_json::Map::new();
            body.insert(
                "metadata".to_owned(),
                serde_json::to_value(&params.metadata)?,
            );
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionCreateParams {
    /// Block ads in the browser session. Default is true.
    pub block_ads: Option<bool>,
    /// PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN EXPERIMENTAL FEATURE.
    pub ca_certificates: Option<Vec<String>>,
    /// Number of sessions to create concurrently (check your plan limit)
    pub concurrency: Option<i64>,
    /// Configuration for session credentials. Set enabled to false to disable credentials, including those inherited from a profile. Credentials are opt-in at creation; only credentials-enabled sessions can have credentials attached later (otherwise 409 `credentials_not_enabled`).
    pub credentials: Option<SessionCreateParamsCredentials>,
    /// Configuration for the debug URL and session viewer. Controls interaction capabilities, cursor visibility, and other debug-related settings.
    pub debug_config: Option<SessionCreateParamsDebugConfig>,
    /// Device configuration for the session. Specify 'mobile' for mobile device fingerprints and configurations.
    pub device_config: Option<SessionCreateParamsDeviceConfig>,
    /// Viewport and browser window dimensions for the session. Mobile sessions require dimensions of at least 508x1074; smaller mobile dimensions are rejected with a 400 response.
    pub dimensions: Option<SessionCreateParamsDimensions>,
    /// Enable experimental features for the session.
    pub experimental_features: Option<Vec<String>>,
    /// Array of extension IDs to install in the session. Use ['all_ext'] to install all uploaded extensions.
    pub extension_ids: Option<Vec<String>>,
    /// Launch the browser in fullscreen mode, covering the full screen with no Chrome UI. Default is false.
    pub fullscreen: Option<bool>,
    /// Enable headless browser mode (disable Headful mode)
    pub headless: Option<bool>,
    /// Inactivity timeout in milliseconds. When set, the session is released if no CDP command or remote input is received for this duration, even if `timeout` has not yet elapsed. Note that `timeout` remains the hard cap on session lifetime: if `inactivityTimeout` is greater than or equal to the effective `timeout`, it has no effect since `timeout` always elapses first. Omit to disable.
    pub inactivity_timeout: Option<i64>,
    /// Deprecated. Selenium sessions are no longer supported, and a request that sets this to true is rejected with 400. Connect over CDP with Playwright or Puppeteer instead.
    pub is_selenium: Option<bool>,
    /// Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must start with a letter, use only letters, digits, underscores, dots or hyphens, and be at most 64 characters; steel_ is reserved. Values are strings (up to 1024 characters, valid Unicode without NUL), finite numbers or booleans. Numbers and booleans are stored as strings, so 3 and "3" are the same value.
    pub metadata: Option<std::collections::HashMap<String, SessionCreateParamsMetadata>>,
    /// The namespace the session should be created against. Defaults to "default".
    pub namespace: Option<String>,
    /// Enable bandwidth optimizations. Passing true enables all flags (except hosts/patterns). Object allows granular control.
    pub optimize_bandwidth: Option<SessionCreateParamsOptimizeBandwidth>,
    /// This flag will persist the user profile for the session.
    pub persist_profile: Option<bool>,
    /// This flag will set the profile for the session.
    pub profile_id: Option<String>,
    /// The project to create the session in. When provided, the session namespace is resolved from the project.
    pub project_id: Option<String>,
    /// Custom proxy URL for the browser session. Overrides useProxy, disabling Steel-provided proxies in favor of your specified proxy. Format: http(s)://username:password@hostname:port or socks5://username:password@hostname:port
    pub proxy_url: Option<String>,
    /// Record the headful session for playback after it ends. Default is true. Disabling recording does not affect live WHEP viewing.
    pub recording: Option<bool>,
    /// The desired region for the session. Available: us-east, us-west, us-central, eu-west, eu-central, ap-northeast, ap-southeast, sa-east. Legacy codes (iad, lax, ord) are also accepted.
    pub region: Option<serde_json::Value>,
    /// Session context data to be used in the created session. Sessions will start with an empty context by default.
    pub session_context: Option<SessionCreateParamsSessionContext>,
    /// Deprecated. A session ID of your choice. Steel will reject it in a future release, because the ID that Steel generates records where the session runs. Omit it, use the `id` in the response, and put your own ID in `metadata`.
    pub session_id: Option<String>,
    /// Enable automatic captcha solving. Default is false.
    pub solve_captcha: Option<bool>,
    /// Stealth configuration for the session
    pub stealth_config: Option<SessionCreateParamsStealthConfig>,
    /// Session timeout duration in milliseconds. Default is 300000 (5 minutes). On Launch and Scale, a value over the plan's maximum runs at the maximum; the response's `timeout` shows the value used.
    pub timeout: Option<i64>,
    /// Simple boolean to enable/disable Steel proxies
    pub use_proxy: Option<serde_json::Value>,
    /// Custom user agent string for the browser session
    pub user_agent: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsCredentials {
    #[serde(
        rename = "autoSubmit",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub auto_submit: Option<bool>,
    /// At most 100 bindings. KV references may use { reference, allowedOrigins }, with at most 20 canonical HTTP(S) origins; omitted or empty origins match nothing.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub bindings: Option<Vec<SessionCreateParamsCredentialsBinding>>,
    #[serde(
        rename = "blurFields",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub blur_fields: Option<bool>,
    /// Bind the session to these credentials (ids of Secrets of kind `credential` in the session's project). A bound session only ever fills these, never others by namespace and origin. Implies `lookup: "explicit"`. Change them later with /v1/sessions/{id}/credentials. Errors: 400 `credential_not_bindable` (param `credentials.credentialIds`), 400 `too_many_credentials`, 409 `session_id_taken` (a custom sessionId that already has a session or credential bindings).
    #[serde(
        rename = "credentialIds",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub credential_ids: Option<Vec<String>>,
    /// Credential provider to use for runtime credential resolution. Defaults to "steel".
    #[serde(
        rename = "credentialProvider",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub credential_provider: Option<String>,
    /// Allow any script on the page, including third-party scripts, to request credential form operations through DOM events. Disabled by default.
    #[serde(
        rename = "domTrigger",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub dom_trigger: Option<bool>,
    /// Set to false to disable credential injection for this session.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub enabled: Option<bool>,
    /// Deprecated and ignored: credentials only ever match their own origin or their additionalOrigins.
    #[serde(
        rename = "exactOrigin",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub exact_origin: Option<bool>,
    /// `implicit` (default without credentialIds or bindings): fill credentials stored for the session's namespace and the page origin. `explicit` (default when credentialIds or bindings is supplied): fill only direct credentials and credentials in bound Vaults, including future members; with none bound, nothing is filled until some are attached.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub lookup: Option<SessionCreateParamsCredentialsLookup>,
    /// Optional allowlist of credential origins to inject into this session.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub origins: Option<Vec<String>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCreateParamsCredentialsBinding {
    String(String),
    UnionMember1(SessionCreateParamsCredentialsBindingUnionMember1),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsCredentialsBindingUnionMember1 {
    #[serde(rename = "allowedOrigins")]
    pub allowed_origins: Vec<String>,
    pub reference: String,
}

impl SessionCreateParamsCredentialsBindingUnionMember1 {
    pub fn new(reference: String, allowed_origins: Vec<String>) -> Self {
        SessionCreateParamsCredentialsBindingUnionMember1 {
            reference,
            allowed_origins,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCreateParamsCredentialsLookup {
    #[serde(rename = "implicit")]
    Implicit,
    #[serde(rename = "explicit")]
    Explicit,
    #[serde(other)]
    Unknown,
}

impl SessionCreateParamsCredentialsLookup {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Implicit => "implicit",
            Self::Explicit => "explicit",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCreateParamsCredentialsLookup {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsDebugConfig {
    /// Allow interaction with the browser session via the debug URL viewer. When false, the session viewer will be view-only. Default is true.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub interactive: Option<bool>,
    /// Show the OS-level mouse cursor in the WebRTC stream (headful mode only). When false, the system cursor will not be rendered in the stream. Default is true.
    #[serde(
        rename = "systemCursor",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub system_cursor: Option<bool>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsDeviceConfig {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub device: Option<SessionCreateParamsDeviceConfigDevice>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCreateParamsDeviceConfigDevice {
    #[serde(rename = "desktop")]
    Desktop,
    #[serde(rename = "mobile")]
    Mobile,
    #[serde(other)]
    Unknown,
}

impl SessionCreateParamsDeviceConfigDevice {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Desktop => "desktop",
            Self::Mobile => "mobile",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCreateParamsDeviceConfigDevice {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsDimensions {
    /// Height of the session
    pub height: i64,
    /// Width of the session
    pub width: i64,
}

impl SessionCreateParamsDimensions {
    pub fn new(width: i64, height: i64) -> Self {
        SessionCreateParamsDimensions { width, height }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCreateParamsMetadata {
    String(String),
    Number(f64),
    Bool(bool),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCreateParamsOptimizeBandwidth {
    Bool(bool),
    UnionMember1(SessionCreateParamsOptimizeBandwidthUnionMember1),
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsOptimizeBandwidthUnionMember1 {
    #[serde(
        rename = "blockHosts",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_hosts: Option<Vec<String>>,
    #[serde(
        rename = "blockImages",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_images: Option<bool>,
    #[serde(
        rename = "blockMedia",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_media: Option<bool>,
    #[serde(
        rename = "blockStylesheets",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_stylesheets: Option<bool>,
    #[serde(
        rename = "blockUrlPatterns",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_url_patterns: Option<Vec<String>>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsSessionContext {
    /// Cookies to initialize in the session
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub cookies: Option<Vec<SessionCreateParamsSessionContextCookie>>,
    /// Domain-specific indexedDB items to initialize in the session
    #[serde(rename = "indexedDB", default, skip_serializing_if = "Option::is_none")]
    pub indexed_db:
        Option<std::collections::HashMap<String, Vec<SessionCreateParamsSessionContextIndexedDb>>>,
    /// Domain-specific localStorage items to initialize in the session
    #[serde(
        rename = "localStorage",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub local_storage:
        Option<std::collections::HashMap<String, std::collections::HashMap<String, String>>>,
    /// Domain-specific sessionStorage items to initialize in the session
    #[serde(
        rename = "sessionStorage",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub session_storage:
        Option<std::collections::HashMap<String, std::collections::HashMap<String, String>>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsSessionContextCookie {
    /// The name of the cookie
    pub name: String,
    /// The value of the cookie
    pub value: String,
    /// The domain of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub domain: Option<String>,
    /// The expiration date of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expires: Option<f64>,
    /// Whether the cookie is HTTP only
    #[serde(rename = "httpOnly", default, skip_serializing_if = "Option::is_none")]
    pub http_only: Option<bool>,
    /// The partition key of the cookie
    #[serde(
        rename = "partitionKey",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub partition_key: Option<SessionCreateParamsSessionContextCookiePartitionKey>,
    /// The path of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub path: Option<String>,
    /// The priority of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub priority: Option<SessionCreateParamsSessionContextCookiePriority>,
    /// Whether the cookie is a same party cookie
    #[serde(rename = "sameParty", default, skip_serializing_if = "Option::is_none")]
    pub same_party: Option<bool>,
    /// The same site attribute of the cookie
    #[serde(rename = "sameSite", default, skip_serializing_if = "Option::is_none")]
    pub same_site: Option<SessionCreateParamsSessionContextCookieSameSite>,
    /// Whether the cookie is secure
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub secure: Option<bool>,
    /// Whether the cookie is a session cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub session: Option<bool>,
    /// The size of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub size: Option<f64>,
    /// The source port of the cookie
    #[serde(
        rename = "sourcePort",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub source_port: Option<f64>,
    /// The source scheme of the cookie
    #[serde(
        rename = "sourceScheme",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub source_scheme: Option<SessionCreateParamsSessionContextCookieSourceScheme>,
    /// The URL of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

impl SessionCreateParamsSessionContextCookie {
    pub fn new(name: String, value: String) -> Self {
        SessionCreateParamsSessionContextCookie {
            name,
            value,
            domain: None,
            expires: None,
            http_only: None,
            partition_key: None,
            path: None,
            priority: None,
            same_party: None,
            same_site: None,
            secure: None,
            session: None,
            size: None,
            source_port: None,
            source_scheme: None,
            url: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsSessionContextCookiePartitionKey {
    /// Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
    #[serde(rename = "hasCrossSiteAncestor")]
    pub has_cross_site_ancestor: bool,
    /// The site of the top-level URL the browser was visiting at the start of the request to the endpoint that set the cookie.
    #[serde(rename = "topLevelSite")]
    pub top_level_site: String,
}

impl SessionCreateParamsSessionContextCookiePartitionKey {
    pub fn new(top_level_site: String, has_cross_site_ancestor: bool) -> Self {
        SessionCreateParamsSessionContextCookiePartitionKey {
            top_level_site,
            has_cross_site_ancestor,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCreateParamsSessionContextCookiePriority {
    #[serde(rename = "Low")]
    Low,
    #[serde(rename = "Medium")]
    Medium,
    #[serde(rename = "High")]
    High,
    #[serde(other)]
    Unknown,
}

impl SessionCreateParamsSessionContextCookiePriority {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Low => "Low",
            Self::Medium => "Medium",
            Self::High => "High",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCreateParamsSessionContextCookiePriority {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCreateParamsSessionContextCookieSameSite {
    #[serde(rename = "Strict")]
    Strict,
    #[serde(rename = "Lax")]
    Lax,
    #[serde(rename = "None")]
    None,
    #[serde(other)]
    Unknown,
}

impl SessionCreateParamsSessionContextCookieSameSite {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Strict => "Strict",
            Self::Lax => "Lax",
            Self::None => "None",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCreateParamsSessionContextCookieSameSite {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCreateParamsSessionContextCookieSourceScheme {
    #[serde(rename = "Unset")]
    Unset,
    #[serde(rename = "NonSecure")]
    NonSecure,
    #[serde(rename = "Secure")]
    Secure,
    #[serde(other)]
    Unknown,
}

impl SessionCreateParamsSessionContextCookieSourceScheme {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Unset => "Unset",
            Self::NonSecure => "NonSecure",
            Self::Secure => "Secure",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCreateParamsSessionContextCookieSourceScheme {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsSessionContextIndexedDb {
    pub id: f64,
    pub data: Vec<SessionCreateParamsSessionContextIndexedDbData>,
    pub name: String,
}

impl SessionCreateParamsSessionContextIndexedDb {
    pub fn new(
        id: f64,
        name: String,
        data: Vec<SessionCreateParamsSessionContextIndexedDbData>,
    ) -> Self {
        SessionCreateParamsSessionContextIndexedDb { id, name, data }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsSessionContextIndexedDbData {
    pub id: f64,
    pub name: String,
    pub records: Vec<SessionCreateParamsSessionContextIndexedDbDataRecord>,
}

impl SessionCreateParamsSessionContextIndexedDbData {
    pub fn new(
        id: f64,
        name: String,
        records: Vec<SessionCreateParamsSessionContextIndexedDbDataRecord>,
    ) -> Self {
        SessionCreateParamsSessionContextIndexedDbData { id, name, records }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsSessionContextIndexedDbDataRecord {
    pub key: serde_json::Value,
    pub value: serde_json::Value,
    #[serde(rename = "blobFiles", default, skip_serializing_if = "Option::is_none")]
    pub blob_files: Option<Vec<SessionCreateParamsSessionContextIndexedDbDataRecordBlobFile>>,
}

impl SessionCreateParamsSessionContextIndexedDbDataRecord {
    pub fn new(key: serde_json::Value, value: serde_json::Value) -> Self {
        SessionCreateParamsSessionContextIndexedDbDataRecord {
            key,
            value,
            blob_files: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsSessionContextIndexedDbDataRecordBlobFile {
    #[serde(rename = "blobNumber")]
    pub blob_number: f64,
    #[serde(rename = "mimeType")]
    pub mime_type: String,
    pub size: f64,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub filename: Option<String>,
    #[serde(
        rename = "lastModified",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub last_modified: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub path: Option<String>,
}

impl SessionCreateParamsSessionContextIndexedDbDataRecordBlobFile {
    pub fn new(blob_number: f64, mime_type: String, size: f64) -> Self {
        SessionCreateParamsSessionContextIndexedDbDataRecordBlobFile {
            blob_number,
            mime_type,
            size,
            filename: None,
            last_modified: None,
            path: None,
        }
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCreateParamsStealthConfig {
    /// When true, captchas will be automatically solved when detected. When false, use the solve endpoints to manually initiate solving.
    #[serde(
        rename = "autoCaptchaSolving",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub auto_captcha_solving: Option<bool>,
    /// This flag will make the browser act more human-like by moving the mouse in a more natural way.
    #[serde(
        rename = "humanizeInteractions",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub humanize_interactions: Option<bool>,
    /// This flag will skip the fingerprint generation for the session.
    #[serde(
        rename = "skipFingerprintInjection",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub skip_fingerprint_injection: Option<bool>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionListParams {
    /// Only sessions created at or after this ISO 8601 timestamp, e.g. 2026-10-01T00:00:00Z.
    pub created_after: Option<String>,
    /// Only sessions created before this ISO 8601 timestamp.
    pub created_before: Option<String>,
    /// Cursor ID for pagination
    pub cursor_id: Option<String>,
    /// Number of sessions to return. Default is 50, max is 100.
    pub limit: Option<i64>,
    /// Exact-match metadata filters, e.g. metadata[environment]=production. All entries must match. Values match the stored string exactly; numbers and booleans are stored as strings, so metadata[attempt]=3 matches attempt sent as 3 or "3".
    pub metadata: Option<std::collections::HashMap<String, String>>,
    /// Filter sessions by project
    pub project_id: Option<String>,
    /// Filter released sessions by why they ended. Live sessions have no release reason and are excluded whenever this is set. Without an explicit status, matching failed sessions are included.
    pub release_reason: Option<SessionListParamsReleaseReason>,
    /// Signals the session must carry; repeat the parameter to require several. All must match. Derived signals are finalized at release.
    pub signal: Option<Vec<SessionListParamsSignal>>,
    /// Filter sessions by current status
    pub status: Option<SessionListParamsStatus>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionListParamsReleaseReason {
    #[serde(rename = "user_requested")]
    UserRequested,
    #[serde(rename = "timeout")]
    Timeout,
    #[serde(rename = "inactivity_timeout")]
    InactivityTimeout,
    #[serde(rename = "creation_timeout")]
    CreationTimeout,
    #[serde(rename = "startup_failed")]
    StartupFailed,
    #[serde(rename = "browser_closed")]
    BrowserClosed,
    #[serde(rename = "browser_crashed")]
    BrowserCrashed,
    #[serde(other)]
    Unknown,
}

impl SessionListParamsReleaseReason {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::UserRequested => "user_requested",
            Self::Timeout => "timeout",
            Self::InactivityTimeout => "inactivity_timeout",
            Self::CreationTimeout => "creation_timeout",
            Self::StartupFailed => "startup_failed",
            Self::BrowserClosed => "browser_closed",
            Self::BrowserCrashed => "browser_crashed",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionListParamsReleaseReason {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionListParamsSignal {
    #[serde(rename = "captcha_encountered")]
    CaptchaEncountered,
    #[serde(rename = "proxy_connection_failed")]
    ProxyConnectionFailed,
    #[serde(rename = "session_timeout_exceeded")]
    SessionTimeoutExceeded,
    #[serde(rename = "bot_detection_triggered")]
    BotDetectionTriggered,
    #[serde(rename = "context_restored")]
    ContextRestored,
    #[serde(other)]
    Unknown,
}

impl SessionListParamsSignal {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::CaptchaEncountered => "captcha_encountered",
            Self::ProxyConnectionFailed => "proxy_connection_failed",
            Self::SessionTimeoutExceeded => "session_timeout_exceeded",
            Self::BotDetectionTriggered => "bot_detection_triggered",
            Self::ContextRestored => "context_restored",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionListParamsSignal {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionListParamsStatus {
    #[serde(rename = "live")]
    Live,
    #[serde(rename = "released")]
    Released,
    #[serde(rename = "failed")]
    Failed,
    #[serde(other)]
    Unknown,
}

impl SessionListParamsStatus {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Live => "live",
            Self::Released => "released",
            Self::Failed => "failed",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionListParamsStatus {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionslistSession {
    /// Unique identifier for the session
    pub id: String,
    /// Browser mode used for this session.
    #[serde(rename = "browserMode")]
    pub browser_mode: SessionslistSessionBrowserMode,
    /// Timestamp when the session started
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Amount of credits consumed by the session
    #[serde(rename = "creditsUsed")]
    pub credits_used: i64,
    /// URL for debugging the session
    #[serde(rename = "debugUrl")]
    pub debug_url: String,
    /// Viewport and browser window dimensions for the session
    pub dimensions: SessionslistSessionDimensions,
    /// Duration of the session in milliseconds
    pub duration: i64,
    /// Number of events processed in the session
    #[serde(rename = "eventCount")]
    pub event_count: i64,
    /// Bandwidth optimizations that were applied to the session.
    #[serde(rename = "optimizeBandwidth")]
    pub optimize_bandwidth: SessionslistSessionOptimizeBandwidth,
    /// Amount of data transmitted through the proxy
    #[serde(rename = "proxyBytesUsed")]
    pub proxy_bytes_used: i64,
    /// Source of the proxy used for the session
    #[serde(rename = "proxySource", default)]
    pub proxy_source: Option<SessionslistSessionProxySource>,
    /// URL to view session details
    #[serde(rename = "sessionViewerUrl")]
    pub session_viewer_url: String,
    /// Signals observed during the session. Filled in within a few seconds after the session ends, and empty until then. An empty list does not mean nothing happened: detection is heuristic and coverage is incomplete.
    pub signals: Vec<SessionslistSessionSignal>,
    /// Status of the session
    pub status: SessionslistSessionStatus,
    /// Session timeout duration in milliseconds
    pub timeout: i64,
    /// URL for the session's WebSocket connection
    #[serde(rename = "websocketUrl")]
    pub websocket_url: String,
    /// Configuration for the debug URL and session viewer. Controls interaction capabilities and cursor visibility.
    #[serde(
        rename = "debugConfig",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub debug_config: Option<SessionslistSessionDebugConfig>,
    /// Device configuration for the session
    #[serde(
        rename = "deviceConfig",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub device_config: Option<SessionslistSessionDeviceConfig>,
    /// Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub fullscreen: Option<bool>,
    /// Indicates if the session is headless or headful
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub headless: Option<bool>,
    /// Inactivity timeout in milliseconds, if one was set when the session was created
    #[serde(
        rename = "inactivityTimeout",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub inactivity_timeout: Option<i64>,
    /// Indicates if Selenium is used in the session
    #[serde(
        rename = "isSelenium",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub is_selenium: Option<bool>,
    /// Customer-defined flat metadata. Values are always strings: numbers and booleans sent on write come back in their string form.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub metadata: Option<std::collections::HashMap<String, String>>,
    /// This flag will persist the profile for the session.
    #[serde(
        rename = "persistProfile",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub persist_profile: Option<bool>,
    /// The ID of the profile associated with the session
    #[serde(rename = "profileId", default, skip_serializing_if = "Option::is_none")]
    pub profile_id: Option<String>,
    /// The project associated with the session
    #[serde(rename = "projectId", default, skip_serializing_if = "Option::is_none")]
    pub project_id: Option<String>,
    /// The region where the session was created.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub region: Option<String>,
    /// Why the session reached a terminal state. Null while the session is live, or when the reason is unknown (e.g. sessions created before this was tracked). One of: user_requested (released via the API/SDK), timeout (hard `timeout` elapsed), inactivity_timeout (no activity for the configured window), creation_timeout (never started in time), startup_failed (could not be dispatched), browser_closed (the browser or agent closed itself — not a crash), browser_crashed (the browser crashed or its machine became unresponsive).
    #[serde(
        rename = "releaseReason",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub release_reason: Option<SessionslistSessionReleaseReason>,
    /// Indicates if captcha solving is enabled
    #[serde(
        rename = "solveCaptcha",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub solve_captcha: Option<bool>,
    /// Stealth configuration for the session
    #[serde(
        rename = "stealthConfig",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub stealth_config: Option<SessionslistSessionStealthConfig>,
    /// User agent string used in the session
    #[serde(rename = "userAgent", default, skip_serializing_if = "Option::is_none")]
    pub user_agent: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionslistSessionBrowserMode {
    #[serde(rename = "stealth")]
    Stealth,
    #[serde(rename = "standard")]
    Standard,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl SessionslistSessionBrowserMode {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Stealth => "stealth",
            Self::Standard => "standard",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for SessionslistSessionBrowserMode {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionslistSessionDimensions {
    /// Height of the browser window
    pub height: i64,
    /// Width of the browser window
    pub width: i64,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionslistSessionOptimizeBandwidth {
    #[serde(
        rename = "blockHosts",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_hosts: Option<Vec<String>>,
    #[serde(
        rename = "blockImages",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_images: Option<bool>,
    #[serde(
        rename = "blockMedia",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_media: Option<bool>,
    #[serde(
        rename = "blockStylesheets",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_stylesheets: Option<bool>,
    #[serde(
        rename = "blockUrlPatterns",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_url_patterns: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionslistSessionProxySource {
    #[serde(rename = "steel")]
    Steel,
    #[serde(rename = "external")]
    External,
    #[serde(other)]
    Unknown,
}

impl SessionslistSessionProxySource {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Steel => "steel",
            Self::External => "external",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionslistSessionProxySource {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionslistSessionSignal {
    #[serde(rename = "captcha_encountered")]
    CaptchaEncountered,
    #[serde(rename = "proxy_connection_failed")]
    ProxyConnectionFailed,
    #[serde(rename = "session_timeout_exceeded")]
    SessionTimeoutExceeded,
    #[serde(rename = "bot_detection_triggered")]
    BotDetectionTriggered,
    #[serde(rename = "context_restored")]
    ContextRestored,
    #[serde(other)]
    Unknown,
}

impl SessionslistSessionSignal {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::CaptchaEncountered => "captcha_encountered",
            Self::ProxyConnectionFailed => "proxy_connection_failed",
            Self::SessionTimeoutExceeded => "session_timeout_exceeded",
            Self::BotDetectionTriggered => "bot_detection_triggered",
            Self::ContextRestored => "context_restored",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionslistSessionSignal {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionslistSessionStatus {
    #[serde(rename = "live")]
    Live,
    #[serde(rename = "released")]
    Released,
    #[serde(rename = "failed")]
    Failed,
    #[serde(other)]
    Unknown,
}

impl SessionslistSessionStatus {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Live => "live",
            Self::Released => "released",
            Self::Failed => "failed",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionslistSessionStatus {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionslistSessionDebugConfig {
    /// Whether interaction is allowed via the debug URL viewer. When false, the session viewer is view-only.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub interactive: Option<bool>,
    /// Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode only).
    #[serde(
        rename = "systemCursor",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub system_cursor: Option<bool>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionslistSessionDeviceConfig {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub device: Option<SessionslistSessionDeviceConfigDevice>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionslistSessionDeviceConfigDevice {
    #[serde(rename = "desktop")]
    Desktop,
    #[serde(rename = "mobile")]
    Mobile,
    #[serde(other)]
    Unknown,
}

impl SessionslistSessionDeviceConfigDevice {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Desktop => "desktop",
            Self::Mobile => "mobile",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionslistSessionDeviceConfigDevice {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionslistSessionReleaseReason {
    #[serde(rename = "user_requested")]
    UserRequested,
    #[serde(rename = "timeout")]
    Timeout,
    #[serde(rename = "inactivity_timeout")]
    InactivityTimeout,
    #[serde(rename = "creation_timeout")]
    CreationTimeout,
    #[serde(rename = "startup_failed")]
    StartupFailed,
    #[serde(rename = "browser_closed")]
    BrowserClosed,
    #[serde(rename = "browser_crashed")]
    BrowserCrashed,
    #[serde(other)]
    Unknown,
}

impl SessionslistSessionReleaseReason {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::UserRequested => "user_requested",
            Self::Timeout => "timeout",
            Self::InactivityTimeout => "inactivity_timeout",
            Self::CreationTimeout => "creation_timeout",
            Self::StartupFailed => "startup_failed",
            Self::BrowserClosed => "browser_closed",
            Self::BrowserCrashed => "browser_crashed",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionslistSessionReleaseReason {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionslistSessionStealthConfig {
    /// When true, captchas will be automatically solved when detected. When false, use the solve endpoints to manually initiate solving.
    #[serde(
        rename = "autoCaptchaSolving",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub auto_captcha_solving: Option<bool>,
    /// This flag will make the browser act more human-like by moving the mouse in a more natural way
    #[serde(
        rename = "humanizeInteractions",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub humanize_interactions: Option<bool>,
    /// This flag will skip the fingerprint generation for the session.
    #[serde(
        rename = "skipFingerprintInjection",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub skip_fingerprint_injection: Option<bool>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionAgentTracesParams {
    pub end_time: Option<String>,
    pub event_types: Option<Vec<String>>,
    pub limit: Option<i64>,
    pub namespace: Option<String>,
    pub offset: Option<i64>,
    pub start_time: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionAgentTracesResponse {
    pub events: Vec<std::collections::HashMap<String, serde_json::Value>>,
    #[serde(rename = "hasMore")]
    pub has_more: bool,
    pub total: f64,
}

#[derive(Debug, Clone, PartialEq)]
pub struct SessionCommandsParams {
    /// One command, or an array of up to 50. They run in order and stop at the first failure. Commands that already ran are not undone.
    pub commands: SessionCommandsParamsCommands,
    /// The next value from your last response. A retry with the same after and commands runs only once.
    pub after: Option<String>,
    /// Returns a snapshot, or a diff against the last one, after the commands.
    pub observe: Option<SessionCommandsParamsObserve>,
    /// A random value. A retry with the same key returns the stored result.
    pub idempotency_key: Option<String>,
}

impl SessionCommandsParams {
    pub fn new(commands: SessionCommandsParamsCommands) -> Self {
        SessionCommandsParams {
            commands,
            after: None,
            observe: None,
            idempotency_key: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCommandsParamsCommands {
    NavigateCommand(SessionCommandsParamsCommandsNavigateCommand),
    BackCommand(SessionCommandsParamsCommandsBackCommand),
    ForwardCommand(SessionCommandsParamsCommandsForwardCommand),
    ReloadCommand(SessionCommandsParamsCommandsReloadCommand),
    SnapshotCommand(SessionCommandsParamsCommandsSnapshotCommand),
    ClickCommand(SessionCommandsParamsCommandsClickCommand),
    HoverCommand(SessionCommandsParamsCommandsHoverCommand),
    FocusCommand(SessionCommandsParamsCommandsFocusCommand),
    TypeCommand(SessionCommandsParamsCommandsTypeCommand),
    FillCommand(SessionCommandsParamsCommandsFillCommand),
    PressCommand(SessionCommandsParamsCommandsPressCommand),
    KeyboardCommand(SessionCommandsParamsCommandsKeyboardCommand),
    ReleaseCommand(SessionCommandsParamsCommandsReleaseCommand),
    MouseCommand(SessionCommandsParamsCommandsMouseCommand),
    SelectCommand(SessionCommandsParamsCommandsSelectCommand),
    CheckCommand(SessionCommandsParamsCommandsCheckCommand),
    UploadCommand(SessionCommandsParamsCommandsUploadCommand),
    DragCommand(SessionCommandsParamsCommandsDragCommand),
    UncheckCommand(SessionCommandsParamsCommandsUncheckCommand),
    GetCommand(SessionCommandsParamsCommandsGetCommand),
    IsCommand(SessionCommandsParamsCommandsIsCommand),
    WaitCommand(SessionCommandsParamsCommandsWaitCommand),
    ScreenshotCommand(SessionCommandsParamsCommandsScreenshotCommand),
    EvalCommand(SessionCommandsParamsCommandsEvalCommand),
    PdfCommand(SessionCommandsParamsCommandsPdfCommand),
    ScrollCommand(SessionCommandsParamsCommandsScrollCommand),
    FindCommand(SessionCommandsParamsCommandsFindCommand),
    TabCommand(SessionCommandsParamsCommandsTabCommand),
    DialogCommand(SessionCommandsParamsCommandsDialogCommand),
    Array(Vec<crate::Command>),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsNavigateCommand {
    pub command: SessionCommandsParamsCommandsNavigateCommandCommand,
    pub url: String,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsNavigateCommand {
    pub fn new(url: String) -> Self {
        SessionCommandsParamsCommandsNavigateCommand {
            url,
            command: Default::default(),
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsNavigateCommandCommand {
    #[default]
    #[serde(rename = "navigate")]
    Navigate,
}

impl SessionCommandsParamsCommandsNavigateCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Navigate => "navigate",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsNavigateCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsBackCommand {
    pub command: SessionCommandsParamsCommandsBackCommandCommand,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsBackCommandCommand {
    #[default]
    #[serde(rename = "back")]
    Back,
}

impl SessionCommandsParamsCommandsBackCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Back => "back",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsBackCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsForwardCommand {
    pub command: SessionCommandsParamsCommandsForwardCommandCommand,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsForwardCommandCommand {
    #[default]
    #[serde(rename = "forward")]
    Forward,
}

impl SessionCommandsParamsCommandsForwardCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Forward => "forward",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsForwardCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsReloadCommand {
    pub command: SessionCommandsParamsCommandsReloadCommandCommand,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsReloadCommandCommand {
    #[default]
    #[serde(rename = "reload")]
    Reload,
}

impl SessionCommandsParamsCommandsReloadCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Reload => "reload",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsReloadCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsSnapshotCommand {
    pub command: SessionCommandsParamsCommandsSnapshotCommandCommand,
    /// Only lines with refs or values, and their ancestors.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub compact: Option<bool>,
    /// Only lines up to this depth.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub depth: Option<i64>,
    /// Only lines with refs.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub interactive: Option<bool>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsSnapshotCommandCommand {
    #[default]
    #[serde(rename = "snapshot")]
    Snapshot,
}

impl SessionCommandsParamsCommandsSnapshotCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Snapshot => "snapshot",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsSnapshotCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsClickCommand {
    pub command: SessionCommandsParamsCommandsClickCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// left by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub button: Option<SessionCommandsParamsCommandsClickCommandButton>,
    /// 2 makes a double click and 3 a triple click. 1 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub clicks: Option<i64>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsClickCommand {
    pub fn new(target: crate::CommandTarget) -> Self {
        SessionCommandsParamsCommandsClickCommand {
            target,
            command: Default::default(),
            button: None,
            clicks: None,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsClickCommandCommand {
    #[default]
    #[serde(rename = "click")]
    Click,
}

impl SessionCommandsParamsCommandsClickCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Click => "click",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsClickCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsClickCommandButton {
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(rename = "middle")]
    Middle,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsClickCommandButton {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Left => "left",
            Self::Right => "right",
            Self::Middle => "middle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsClickCommandButton {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsHoverCommand {
    pub command: SessionCommandsParamsCommandsHoverCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsHoverCommand {
    pub fn new(target: crate::CommandTarget) -> Self {
        SessionCommandsParamsCommandsHoverCommand {
            target,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsHoverCommandCommand {
    #[default]
    #[serde(rename = "hover")]
    Hover,
}

impl SessionCommandsParamsCommandsHoverCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Hover => "hover",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsHoverCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsFocusCommand {
    pub command: SessionCommandsParamsCommandsFocusCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsFocusCommand {
    pub fn new(target: crate::CommandTarget) -> Self {
        SessionCommandsParamsCommandsFocusCommand {
            target,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsFocusCommandCommand {
    #[default]
    #[serde(rename = "focus")]
    Focus,
}

impl SessionCommandsParamsCommandsFocusCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Focus => "focus",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsFocusCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsTypeCommand {
    pub command: SessionCommandsParamsCommandsTypeCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub text: String,
    /// Replace the current content.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub clear: Option<bool>,
    /// Milliseconds to wait between key presses.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub delay: Option<i64>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsTypeCommand {
    pub fn new(target: crate::CommandTarget, text: String) -> Self {
        SessionCommandsParamsCommandsTypeCommand {
            target,
            text,
            command: Default::default(),
            clear: None,
            delay: None,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsTypeCommandCommand {
    #[default]
    #[serde(rename = "type")]
    Type,
}

impl SessionCommandsParamsCommandsTypeCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Type => "type",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsTypeCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsFillCommand {
    pub command: SessionCommandsParamsCommandsFillCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub value: String,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsFillCommand {
    pub fn new(target: crate::CommandTarget, value: String) -> Self {
        SessionCommandsParamsCommandsFillCommand {
            target,
            value,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsFillCommandCommand {
    #[default]
    #[serde(rename = "fill")]
    Fill,
}

impl SessionCommandsParamsCommandsFillCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Fill => "fill",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsFillCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsPressCommand {
    pub command: SessionCommandsParamsCommandsPressCommandCommand,
    pub key: String,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

impl SessionCommandsParamsCommandsPressCommand {
    pub fn new(key: String) -> Self {
        SessionCommandsParamsCommandsPressCommand {
            key,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
            target: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsPressCommandCommand {
    #[default]
    #[serde(rename = "press")]
    Press,
}

impl SessionCommandsParamsCommandsPressCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Press => "press",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsPressCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsKeyboardCommand {
    pub action: SessionCommandsParamsCommandsKeyboardCommandAction,
    pub command: SessionCommandsParamsCommandsKeyboardCommandCommand,
    /// One key, not a combination such as Control+a.
    pub key: String,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsKeyboardCommand {
    pub fn new(action: SessionCommandsParamsCommandsKeyboardCommandAction, key: String) -> Self {
        SessionCommandsParamsCommandsKeyboardCommand {
            action,
            key,
            command: Default::default(),
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsKeyboardCommandAction {
    #[serde(rename = "down")]
    Down,
    #[serde(rename = "up")]
    Up,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsKeyboardCommandAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Down => "down",
            Self::Up => "up",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsKeyboardCommandAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsKeyboardCommandCommand {
    #[default]
    #[serde(rename = "keyboard")]
    Keyboard,
}

impl SessionCommandsParamsCommandsKeyboardCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Keyboard => "keyboard",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsKeyboardCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsReleaseCommand {
    pub command: SessionCommandsParamsCommandsReleaseCommandCommand,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsReleaseCommandCommand {
    #[default]
    #[serde(rename = "release")]
    Release,
}

impl SessionCommandsParamsCommandsReleaseCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Release => "release",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsReleaseCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsMouseCommand {
    pub action: SessionCommandsParamsCommandsMouseCommandAction,
    pub command: SessionCommandsParamsCommandsMouseCommandCommand,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub button: Option<SessionCommandsParamsCommandsMouseCommandButton>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub clicks: Option<i64>,
    #[serde(rename = "deltaX", default, skip_serializing_if = "Option::is_none")]
    pub delta_x: Option<f64>,
    #[serde(rename = "deltaY", default, skip_serializing_if = "Option::is_none")]
    pub delta_y: Option<f64>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub x: Option<f64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub y: Option<f64>,
}

impl SessionCommandsParamsCommandsMouseCommand {
    pub fn new(action: SessionCommandsParamsCommandsMouseCommandAction) -> Self {
        SessionCommandsParamsCommandsMouseCommand {
            action,
            command: Default::default(),
            button: None,
            clicks: None,
            delta_x: None,
            delta_y: None,
            tab: None,
            x: None,
            y: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsMouseCommandAction {
    #[serde(rename = "move")]
    Move,
    #[serde(rename = "down")]
    Down,
    #[serde(rename = "up")]
    Up,
    #[serde(rename = "click")]
    Click,
    #[serde(rename = "wheel")]
    Wheel,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsMouseCommandAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Move => "move",
            Self::Down => "down",
            Self::Up => "up",
            Self::Click => "click",
            Self::Wheel => "wheel",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsMouseCommandAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsMouseCommandCommand {
    #[default]
    #[serde(rename = "mouse")]
    Mouse,
}

impl SessionCommandsParamsCommandsMouseCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Mouse => "mouse",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsMouseCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsMouseCommandButton {
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(rename = "middle")]
    Middle,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsMouseCommandButton {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Left => "left",
            Self::Right => "right",
            Self::Middle => "middle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsMouseCommandButton {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsSelectCommand {
    pub command: SessionCommandsParamsCommandsSelectCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub values: SessionCommandsParamsCommandsSelectCommandValues,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsSelectCommand {
    pub fn new(
        target: crate::CommandTarget,
        values: SessionCommandsParamsCommandsSelectCommandValues,
    ) -> Self {
        SessionCommandsParamsCommandsSelectCommand {
            target,
            values,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsSelectCommandCommand {
    #[default]
    #[serde(rename = "select")]
    Select,
}

impl SessionCommandsParamsCommandsSelectCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Select => "select",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsSelectCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCommandsParamsCommandsSelectCommandValues {
    String(String),
    Array(Vec<String>),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsCheckCommand {
    pub command: SessionCommandsParamsCommandsCheckCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsCheckCommand {
    pub fn new(target: crate::CommandTarget) -> Self {
        SessionCommandsParamsCommandsCheckCommand {
            target,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsCheckCommandCommand {
    #[default]
    #[serde(rename = "check")]
    Check,
}

impl SessionCommandsParamsCommandsCheckCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Check => "check",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsCheckCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsUploadCommand {
    pub command: SessionCommandsParamsCommandsUploadCommandCommand,
    pub files: SessionCommandsParamsCommandsUploadCommandFiles,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsUploadCommand {
    pub fn new(
        target: crate::CommandTarget,
        files: SessionCommandsParamsCommandsUploadCommandFiles,
    ) -> Self {
        SessionCommandsParamsCommandsUploadCommand {
            target,
            files,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsUploadCommandCommand {
    #[default]
    #[serde(rename = "upload")]
    Upload,
}

impl SessionCommandsParamsCommandsUploadCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Upload => "upload",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsUploadCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCommandsParamsCommandsUploadCommandFiles {
    String(String),
    Array(Vec<String>),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsDragCommand {
    pub command: SessionCommandsParamsCommandsDragCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub source: crate::CommandTarget,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsDragCommand {
    pub fn new(source: crate::CommandTarget, target: crate::CommandTarget) -> Self {
        SessionCommandsParamsCommandsDragCommand {
            source,
            target,
            command: Default::default(),
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsDragCommandCommand {
    #[default]
    #[serde(rename = "drag")]
    Drag,
}

impl SessionCommandsParamsCommandsDragCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Drag => "drag",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsDragCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsUncheckCommand {
    pub command: SessionCommandsParamsCommandsUncheckCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsUncheckCommand {
    pub fn new(target: crate::CommandTarget) -> Self {
        SessionCommandsParamsCommandsUncheckCommand {
            target,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsUncheckCommandCommand {
    #[default]
    #[serde(rename = "uncheck")]
    Uncheck,
}

impl SessionCommandsParamsCommandsUncheckCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Uncheck => "uncheck",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsUncheckCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsGetCommand {
    pub command: SessionCommandsParamsCommandsGetCommandCommand,
    pub what: SessionCommandsParamsCommandsGetCommandWhat,
    /// The attribute to read with attr.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub attribute: Option<String>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

impl SessionCommandsParamsCommandsGetCommand {
    pub fn new(what: SessionCommandsParamsCommandsGetCommandWhat) -> Self {
        SessionCommandsParamsCommandsGetCommand {
            what,
            command: Default::default(),
            attribute: None,
            expect: None,
            frame: None,
            tab: None,
            target: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsGetCommandCommand {
    #[default]
    #[serde(rename = "get")]
    Get,
}

impl SessionCommandsParamsCommandsGetCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Get => "get",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsGetCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsGetCommandWhat {
    #[serde(rename = "text")]
    Text,
    #[serde(rename = "html")]
    Html,
    #[serde(rename = "markdown")]
    Markdown,
    #[serde(rename = "value")]
    Value,
    #[serde(rename = "attr")]
    Attr,
    #[serde(rename = "title")]
    Title,
    #[serde(rename = "url")]
    Url,
    #[serde(rename = "count")]
    Count,
    #[serde(rename = "box")]
    Box,
    #[serde(rename = "locator")]
    Locator,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsGetCommandWhat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Text => "text",
            Self::Html => "html",
            Self::Markdown => "markdown",
            Self::Value => "value",
            Self::Attr => "attr",
            Self::Title => "title",
            Self::Url => "url",
            Self::Count => "count",
            Self::Box => "box",
            Self::Locator => "locator",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsGetCommandWhat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsIsCommand {
    pub command: SessionCommandsParamsCommandsIsCommandCommand,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub what: SessionCommandsParamsCommandsIsCommandWhat,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsIsCommand {
    pub fn new(
        what: SessionCommandsParamsCommandsIsCommandWhat,
        target: crate::CommandTarget,
    ) -> Self {
        SessionCommandsParamsCommandsIsCommand {
            what,
            target,
            command: Default::default(),
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsIsCommandCommand {
    #[default]
    #[serde(rename = "is")]
    Is,
}

impl SessionCommandsParamsCommandsIsCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Is => "is",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsIsCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsIsCommandWhat {
    #[serde(rename = "visible")]
    Visible,
    #[serde(rename = "enabled")]
    Enabled,
    #[serde(rename = "checked")]
    Checked,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsIsCommandWhat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Visible => "visible",
            Self::Enabled => "enabled",
            Self::Checked => "checked",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsIsCommandWhat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsWaitCommand {
    pub command: SessionCommandsParamsCommandsWaitCommandCommand,
    /// Waits for the next download to finish.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub download: Option<bool>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub load: Option<SessionCommandsParamsCommandsWaitCommandLoad>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub ms: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub state: Option<SessionCommandsParamsCommandsWaitCommandState>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub text: Option<String>,
    /// Milliseconds. 10000 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub timeout: Option<i64>,
    /// A URL pattern. * matches any characters.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsWaitCommandCommand {
    #[default]
    #[serde(rename = "wait")]
    Wait,
}

impl SessionCommandsParamsCommandsWaitCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Wait => "wait",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsWaitCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsWaitCommandLoad {
    #[serde(rename = "load")]
    Load,
    #[serde(rename = "domcontentloaded")]
    Domcontentloaded,
    #[serde(rename = "networkidle")]
    Networkidle,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsWaitCommandLoad {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Load => "load",
            Self::Domcontentloaded => "domcontentloaded",
            Self::Networkidle => "networkidle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsWaitCommandLoad {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsWaitCommandState {
    #[serde(rename = "visible")]
    Visible,
    #[serde(rename = "hidden")]
    Hidden,
    #[serde(rename = "attached")]
    Attached,
    #[serde(rename = "detached")]
    Detached,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsWaitCommandState {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Visible => "visible",
            Self::Hidden => "hidden",
            Self::Attached => "attached",
            Self::Detached => "detached",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsWaitCommandState {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsScreenshotCommand {
    pub command: SessionCommandsParamsCommandsScreenshotCommandCommand,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub format: Option<SessionCommandsParamsCommandsScreenshotCommandFormat>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    #[serde(rename = "fullPage", default, skip_serializing_if = "Option::is_none")]
    pub full_page: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub quality: Option<i64>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsScreenshotCommandCommand {
    #[default]
    #[serde(rename = "screenshot")]
    Screenshot,
}

impl SessionCommandsParamsCommandsScreenshotCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Screenshot => "screenshot",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsScreenshotCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsScreenshotCommandFormat {
    #[serde(rename = "png")]
    Png,
    #[serde(rename = "jpeg")]
    Jpeg,
    #[serde(rename = "webp")]
    Webp,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsScreenshotCommandFormat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Png => "png",
            Self::Jpeg => "jpeg",
            Self::Webp => "webp",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsScreenshotCommandFormat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsEvalCommand {
    pub command: SessionCommandsParamsCommandsEvalCommandCommand,
    /// An expression, or an async function body that gives its value with return. A promise result is awaited.
    pub script: String,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// Milliseconds. 10000 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub timeout: Option<i64>,
}

impl SessionCommandsParamsCommandsEvalCommand {
    pub fn new(script: String) -> Self {
        SessionCommandsParamsCommandsEvalCommand {
            script,
            command: Default::default(),
            tab: None,
            timeout: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsEvalCommandCommand {
    #[default]
    #[serde(rename = "eval")]
    Eval,
}

impl SessionCommandsParamsCommandsEvalCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Eval => "eval",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsEvalCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsPdfCommand {
    pub command: SessionCommandsParamsCommandsPdfCommandCommand,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub landscape: Option<bool>,
    #[serde(
        rename = "printBackground",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub print_background: Option<bool>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsPdfCommandCommand {
    #[default]
    #[serde(rename = "pdf")]
    Pdf,
}

impl SessionCommandsParamsCommandsPdfCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Pdf => "pdf",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsPdfCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsScrollCommand {
    pub command: SessionCommandsParamsCommandsScrollCommandCommand,
    /// Pixels. 300 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub amount: Option<f64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub direction: Option<SessionCommandsParamsCommandsScrollCommandDirection>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsScrollCommandCommand {
    #[default]
    #[serde(rename = "scroll")]
    Scroll,
}

impl SessionCommandsParamsCommandsScrollCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Scroll => "scroll",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsScrollCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsScrollCommandDirection {
    #[serde(rename = "up")]
    Up,
    #[serde(rename = "down")]
    Down,
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsScrollCommandDirection {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Up => "up",
            Self::Down => "down",
            Self::Left => "left",
            Self::Right => "right",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsScrollCommandDirection {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsFindCommand {
    pub command: SessionCommandsParamsCommandsFindCommandCommand,
    /// A CSS selector or a locator, not a ref.
    pub target: crate::CommandTarget,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsCommandsFindCommand {
    pub fn new(target: crate::CommandTarget) -> Self {
        SessionCommandsParamsCommandsFindCommand {
            target,
            command: Default::default(),
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsFindCommandCommand {
    #[default]
    #[serde(rename = "find")]
    Find,
}

impl SessionCommandsParamsCommandsFindCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Find => "find",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsFindCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsTabCommand {
    pub action: SessionCommandsParamsCommandsTabCommandAction,
    pub command: SessionCommandsParamsCommandsTabCommandCommand,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

impl SessionCommandsParamsCommandsTabCommand {
    pub fn new(action: SessionCommandsParamsCommandsTabCommandAction) -> Self {
        SessionCommandsParamsCommandsTabCommand {
            action,
            command: Default::default(),
            tab: None,
            url: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsTabCommandAction {
    #[serde(rename = "list")]
    List,
    #[serde(rename = "new")]
    New,
    #[serde(rename = "close")]
    Close,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsTabCommandAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::List => "list",
            Self::New => "new",
            Self::Close => "close",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsTabCommandAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsTabCommandCommand {
    #[default]
    #[serde(rename = "tab")]
    Tab,
}

impl SessionCommandsParamsCommandsTabCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Tab => "tab",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsTabCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsCommandsDialogCommand {
    pub action: SessionCommandsParamsCommandsDialogCommandAction,
    pub command: SessionCommandsParamsCommandsDialogCommandCommand,
    /// The id of the dialog, such as d-3, from dialog in a response.
    pub dialog: String,
    /// The answer to a prompt. Works only with accept. A prompt gets its default text without it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub text: Option<String>,
}

impl SessionCommandsParamsCommandsDialogCommand {
    pub fn new(dialog: String, action: SessionCommandsParamsCommandsDialogCommandAction) -> Self {
        SessionCommandsParamsCommandsDialogCommand {
            dialog,
            action,
            command: Default::default(),
            text: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsDialogCommandAction {
    #[serde(rename = "accept")]
    Accept,
    #[serde(rename = "dismiss")]
    Dismiss,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsCommandsDialogCommandAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Accept => "accept",
            Self::Dismiss => "dismiss",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsDialogCommandAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsCommandsDialogCommandCommand {
    #[default]
    #[serde(rename = "dialog")]
    Dialog,
}

impl SessionCommandsParamsCommandsDialogCommandCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Dialog => "dialog",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsCommandsDialogCommandCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsParamsObserve {
    pub mode: SessionCommandsParamsObserveMode,
    /// A snapshot id to diff against.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub base: Option<String>,
    /// Only lines with refs or values, and their ancestors.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub compact: Option<bool>,
    /// Only lines up to this depth.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub depth: Option<i64>,
    /// Only lines with refs.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub interactive: Option<bool>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl SessionCommandsParamsObserve {
    pub fn new(mode: SessionCommandsParamsObserveMode) -> Self {
        SessionCommandsParamsObserve {
            mode,
            base: None,
            compact: None,
            depth: None,
            interactive: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsParamsObserveMode {
    #[serde(rename = "snapshot")]
    Snapshot,
    #[serde(rename = "diff")]
    Diff,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsParamsObserveMode {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Snapshot => "snapshot",
            Self::Diff => "diff",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsParamsObserveMode {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsResponse {
    /// The id of this execution. Use it to get or cancel the execution.
    pub id: String,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: SessionCommandsResponseEffect,
    /// The tab the execution used.
    #[serde(default)]
    pub page: Option<SessionCommandsResponsePage>,
    /// Commands started.
    pub ran: i64,
    /// This is the stored result of an earlier request. Nothing ran again.
    pub replayed: bool,
    pub results: Vec<crate::CommandResult>,
    pub state: SessionCommandsResponseState,
    #[serde(
        rename = "bodyExpired",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub body_expired: Option<bool>,
    /// A dialog that is open on the tab and blocks it until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    /// Set when the execution ran with after. Whether another execution, a quick action, or a browser restart changed the session since then. Input from CDP clients or the live view does not count.
    #[serde(
        rename = "epochChanged",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub epoch_changed: Option<bool>,
    /// Tabs with keys or a mouse button still held down. Left out when nothing is held.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub held: Option<Vec<crate::CommandHeld>>,
    /// Send it as after in the next request.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub next: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub observe: Option<SessionCommandsResponseObserve>,
    /// Why the execution stopped early, such as a cancel, a timeout, or a browser restart. A failed command has its error in results instead.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub stopped: Option<crate::CommandErrorInfo>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsResponseEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl SessionCommandsResponseEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for SessionCommandsResponseEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsResponsePage {
    pub tab: String,
    pub title: String,
    pub url: String,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandsResponseState {
    #[serde(rename = "running")]
    Running,
    #[serde(rename = "completed")]
    Completed,
    #[serde(rename = "failed")]
    Failed,
    #[serde(rename = "cancelled")]
    Cancelled,
    #[serde(rename = "timeout")]
    Timeout,
    #[serde(other)]
    Unknown,
}

impl SessionCommandsResponseState {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Running => "running",
            Self::Completed => "completed",
            Self::Failed => "failed",
            Self::Cancelled => "cancelled",
            Self::Timeout => "timeout",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandsResponseState {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandsResponseObserve {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub additions: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub base: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub diff: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub full: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub removals: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub snapshot: Option<String>,
    #[serde(
        rename = "snapshotId",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub snapshot_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(tag = "action")]
pub enum SessionComputerParams {
    #[serde(rename = "move_mouse")]
    MoveMouse(SessionComputerParamsMoveMouse),
    #[serde(rename = "click_mouse")]
    ClickMouse(SessionComputerParamsClickMouse),
    #[serde(rename = "drag_mouse")]
    DragMouse(SessionComputerParamsDragMouse),
    #[serde(rename = "scroll")]
    Scroll(SessionComputerParamsScroll),
    #[serde(rename = "press_key")]
    PressKey(SessionComputerParamsPressKey),
    #[serde(rename = "type_text")]
    TypeText(SessionComputerParamsTypeText),
    #[serde(rename = "wait")]
    Wait(SessionComputerParamsWait),
    #[serde(rename = "take_screenshot")]
    TakeScreenshot(SessionComputerParamsTakeScreenshot),
    #[serde(rename = "get_cursor_position")]
    GetCursorPosition(SessionComputerParamsGetCursorPosition),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsMoveMouse {
    /// X and Y coordinates [x, y]
    pub coordinates: Vec<f64>,
    /// Keys to hold while moving
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub hold_keys: Option<Vec<String>>,
    /// Whether to take a screenshot after the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<bool>,
}

impl SessionComputerParamsMoveMouse {
    pub fn new(coordinates: Vec<f64>) -> Self {
        SessionComputerParamsMoveMouse {
            coordinates,
            hold_keys: None,
            screenshot: None,
        }
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsClickMouse {
    /// Mouse button to click. Defaults to 'left'
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub button: Option<SessionComputerParamsClickMouseButton>,
    /// Type of click (down, up, or click). Defaults to 'click'
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub click_type: Option<SessionComputerParamsClickMouseClickType>,
    /// X and Y coordinates [x, y]
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub coordinates: Option<Vec<f64>>,
    /// Keys to hold while clicking
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub hold_keys: Option<Vec<String>>,
    /// Number of clicks. Defaults to 1
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub num_clicks: Option<f64>,
    /// Whether to take a screenshot after the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<bool>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionComputerParamsClickMouseButton {
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(rename = "middle")]
    Middle,
    #[serde(rename = "back")]
    Back,
    #[serde(rename = "forward")]
    Forward,
    #[serde(other)]
    Unknown,
}

impl SessionComputerParamsClickMouseButton {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Left => "left",
            Self::Right => "right",
            Self::Middle => "middle",
            Self::Back => "back",
            Self::Forward => "forward",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionComputerParamsClickMouseButton {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionComputerParamsClickMouseClickType {
    #[serde(rename = "down")]
    Down,
    #[serde(rename = "up")]
    Up,
    #[serde(rename = "click")]
    Click,
    #[serde(other)]
    Unknown,
}

impl SessionComputerParamsClickMouseClickType {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Down => "down",
            Self::Up => "up",
            Self::Click => "click",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionComputerParamsClickMouseClickType {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsDragMouse {
    /// Array of [x, y] coordinate pairs
    pub path: Vec<Vec<f64>>,
    /// Keys to hold while dragging
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub hold_keys: Option<Vec<String>>,
    /// Whether to take a screenshot after the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<bool>,
}

impl SessionComputerParamsDragMouse {
    pub fn new(path: Vec<Vec<f64>>) -> Self {
        SessionComputerParamsDragMouse {
            path,
            hold_keys: None,
            screenshot: None,
        }
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsScroll {
    /// X and Y coordinates [x, y]
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub coordinates: Option<Vec<f64>>,
    /// Horizontal scroll amount. Defaults to 0
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub delta_x: Option<f64>,
    /// Vertical scroll amount. Defaults to 0
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub delta_y: Option<f64>,
    /// Keys to hold while scrolling
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub hold_keys: Option<Vec<String>>,
    /// Whether to take a screenshot after the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<bool>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsPressKey {
    /// Keys to press
    pub keys: Vec<String>,
    /// Duration to hold keys in seconds
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub duration: Option<f64>,
    /// Whether to take a screenshot after the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<bool>,
}

impl SessionComputerParamsPressKey {
    pub fn new(keys: Vec<String>) -> Self {
        SessionComputerParamsPressKey {
            keys,
            duration: None,
            screenshot: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsTypeText {
    /// Text to type
    pub text: String,
    /// Keys to hold while typing
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub hold_keys: Option<Vec<String>>,
    /// Whether to take a screenshot after the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<bool>,
}

impl SessionComputerParamsTypeText {
    pub fn new(text: String) -> Self {
        SessionComputerParamsTypeText {
            text,
            hold_keys: None,
            screenshot: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsWait {
    /// Duration to wait in seconds
    pub duration: f64,
    /// Whether to take a screenshot after the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<bool>,
}

impl SessionComputerParamsWait {
    pub fn new(duration: f64) -> Self {
        SessionComputerParamsWait {
            duration,
            screenshot: None,
        }
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsTakeScreenshot {}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerParamsGetCursorPosition {}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionComputerResponse {
    /// Base64 encoded screenshot if requested
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub base64_image: Option<String>,
    /// Error message if action failed
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<String>,
    /// Output message from the action
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub output: Option<String>,
    /// System information
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub system: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCostResponse {
    /// Session ID
    pub id: String,
    /// Currency used for the cost values
    pub currency: SessionCostResponseCurrency,
    /// Exact total session cost in US dollars (e.g. 0.031905), with no rounding to whole cents. Rounded only to micro-dollar precision (6 decimal places) for precise billing passthrough.
    #[serde(rename = "totalCost")]
    pub total_cost: f64,
    /// Cost unit. Values are expressed in US dollars.
    pub unit: SessionCostResponseUnit,
    /// Billable usage inputs used to calculate the costs
    pub usage: SessionCostResponseUsage,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCostResponseCurrency {
    #[serde(rename = "usd")]
    Usd,
    #[serde(other)]
    Unknown,
}

impl SessionCostResponseCurrency {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Usd => "usd",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCostResponseCurrency {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCostResponseUnit {
    #[serde(rename = "dollar")]
    Dollar,
    #[serde(other)]
    Unknown,
}

impl SessionCostResponseUnit {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Dollar => "dollar",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCostResponseUnit {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCostResponseUsage {
    pub browser: SessionCostResponseUsageBrowser,
    pub captcha: SessionCostResponseUsageCaptcha,
    pub proxy: SessionCostResponseUsageProxy,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCostResponseUsageBrowser {
    /// Billing unit for browser usage
    pub unit: SessionCostResponseUsageBrowserUnit,
    /// Billable browser usage value
    pub value: f64,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCostResponseUsageBrowserUnit {
    #[serde(rename = "minute")]
    Minute,
    #[serde(rename = "second")]
    Second,
    #[serde(other)]
    Unknown,
}

impl SessionCostResponseUsageBrowserUnit {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Minute => "minute",
            Self::Second => "second",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCostResponseUsageBrowserUnit {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCostResponseUsageCaptcha {
    /// Billable captcha solves
    pub solves: i64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCostResponseUsageProxy {
    /// Billable Steel proxy bytes for the session
    pub bytes: i64,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionEventsParams {
    /// Compress the events
    pub compressed: Option<bool>,
    /// Optional pagination limit
    pub limit: Option<i64>,
    /// Opaque pagination token. Pass the Next-Cursor header value to get the next page.
    pub pointer: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionLiveDetailsResponse {
    pub pages: Vec<SessionLiveDetailsResponsePage>,
    #[serde(rename = "sessionViewerFullscreenUrl")]
    pub session_viewer_fullscreen_url: String,
    #[serde(rename = "sessionViewerUrl")]
    pub session_viewer_url: String,
    #[serde(rename = "wsUrl")]
    pub ws_url: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionLiveDetailsResponsePage {
    pub id: String,
    #[serde(default)]
    pub favicon: Option<String>,
    #[serde(rename = "sessionViewerFullscreenUrl")]
    pub session_viewer_fullscreen_url: String,
    #[serde(rename = "sessionViewerUrl")]
    pub session_viewer_url: String,
    pub title: String,
    pub url: String,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionLogsParams {
    pub end_time: Option<String>,
    pub event_types: Option<Vec<String>>,
    pub limit: Option<i64>,
    pub namespace: Option<String>,
    pub offset: Option<i64>,
    pub start_time: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionLogsResponse {
    pub events: Vec<SessionLogsResponseEvent>,
    #[serde(rename = "hasMore")]
    pub has_more: bool,
    pub total: f64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionLogsResponseEvent {
    pub id: String,
    pub log: String,
    pub timestamp: String,
    pub r#type: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionReleaseResponse {
    /// Details about the outcome of the release operation
    pub message: String,
    /// Indicates if the session was successfully released
    pub success: bool,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionReleaseAllParams {
    /// Release sessions only within this project
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionReleaseAllResponse {
    /// Details about the outcome of the release operation
    pub message: String,
    /// Indicates if the sessions were successfully released
    pub success: bool,
}

#[derive(Debug, Clone, PartialEq)]
pub struct SessionReplaceMetadataParams {
    /// Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must start with a letter, use only letters, digits, underscores, dots or hyphens, and be at most 64 characters; steel_ is reserved. Values are strings (up to 1024 characters, valid Unicode without NUL), finite numbers or booleans. Numbers and booleans are stored as strings, so 3 and "3" are the same value.
    pub metadata: std::collections::HashMap<String, SessionReplaceMetadataParamsMetadata>,
}

impl SessionReplaceMetadataParams {
    pub fn new(
        metadata: std::collections::HashMap<String, SessionReplaceMetadataParamsMetadata>,
    ) -> Self {
        SessionReplaceMetadataParams { metadata }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionReplaceMetadataParamsMetadata {
    String(String),
    Number(f64),
    Bool(bool),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionReplaceMetadataResponse {
    /// Customer-defined flat metadata. Values are always strings: numbers and booleans sent on write come back in their string form.
    pub metadata: std::collections::HashMap<String, String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionSignalCatalogResponse {
    /// Interpretation and coverage limitations of session signals
    pub semantics: String,
    pub signals: Vec<SessionSignalCatalogResponseSignal>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionSignalCatalogResponseSignal {
    pub description: String,
    pub label: String,
    pub severity: SessionSignalCatalogResponseSignalSeverity,
    pub source: SessionSignalCatalogResponseSignalSource,
    pub value: SessionSignalCatalogResponseSignalValue,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionSignalCatalogResponseSignalSeverity {
    #[serde(rename = "info")]
    Info,
    #[serde(rename = "warning")]
    Warning,
    #[serde(rename = "danger")]
    Danger,
    #[serde(rename = "success")]
    Success,
    #[serde(other)]
    Unknown,
}

impl SessionSignalCatalogResponseSignalSeverity {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Info => "info",
            Self::Warning => "warning",
            Self::Danger => "danger",
            Self::Success => "success",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionSignalCatalogResponseSignalSeverity {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionSignalCatalogResponseSignalSource {
    #[serde(rename = "manual")]
    Manual,
    #[serde(rename = "derived")]
    Derived,
    #[serde(other)]
    Unknown,
}

impl SessionSignalCatalogResponseSignalSource {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Manual => "manual",
            Self::Derived => "derived",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionSignalCatalogResponseSignalSource {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionSignalCatalogResponseSignalValue {
    #[serde(rename = "captcha_encountered")]
    CaptchaEncountered,
    #[serde(rename = "proxy_connection_failed")]
    ProxyConnectionFailed,
    #[serde(rename = "session_timeout_exceeded")]
    SessionTimeoutExceeded,
    #[serde(rename = "bot_detection_triggered")]
    BotDetectionTriggered,
    #[serde(rename = "context_restored")]
    ContextRestored,
    #[serde(other)]
    Unknown,
}

impl SessionSignalCatalogResponseSignalValue {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::CaptchaEncountered => "captcha_encountered",
            Self::ProxyConnectionFailed => "proxy_connection_failed",
            Self::SessionTimeoutExceeded => "session_timeout_exceeded",
            Self::BotDetectionTriggered => "bot_detection_triggered",
            Self::ContextRestored => "context_restored",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionSignalCatalogResponseSignalValue {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq)]
pub struct SessionUpdateMetadataParams {
    /// Metadata keys to change, up to 100 per update. Keys follow the same rules as metadata keys. Send a string, finite number or boolean to set a key, or null to remove it; keys you leave out are kept. The merged metadata must stay within 50 keys and 8192 UTF-8 JSON bytes.
    pub metadata: std::collections::HashMap<String, Option<SessionUpdateMetadataParamsMetadata>>,
}

impl SessionUpdateMetadataParams {
    pub fn new(
        metadata: std::collections::HashMap<String, Option<SessionUpdateMetadataParamsMetadata>>,
    ) -> Self {
        SessionUpdateMetadataParams { metadata }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionUpdateMetadataParamsMetadata {
    String(String),
    Number(f64),
    Bool(bool),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionUpdateMetadataResponse {
    /// Customer-defined flat metadata. Values are always strings: numbers and booleans sent on write come back in their string form.
    pub metadata: std::collections::HashMap<String, String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(tag = "command")]
pub enum Command {
    #[serde(rename = "navigate")]
    Navigate(CommandNavigate),
    #[serde(rename = "back")]
    Back(CommandBack),
    #[serde(rename = "forward")]
    Forward(CommandForward),
    #[serde(rename = "reload")]
    Reload(CommandReload),
    #[serde(rename = "snapshot")]
    Snapshot(CommandSnapshot),
    #[serde(rename = "click")]
    Click(CommandClick),
    #[serde(rename = "hover")]
    Hover(CommandHover),
    #[serde(rename = "focus")]
    Focus(CommandFocus),
    #[serde(rename = "type")]
    Type(CommandType),
    #[serde(rename = "fill")]
    Fill(CommandFill),
    #[serde(rename = "press")]
    Press(CommandPress),
    #[serde(rename = "keyboard")]
    Keyboard(CommandKeyboard),
    #[serde(rename = "release")]
    Release(CommandRelease),
    #[serde(rename = "mouse")]
    Mouse(CommandMouse),
    #[serde(rename = "select")]
    Select(CommandSelect),
    #[serde(rename = "check")]
    Check(CommandCheck),
    #[serde(rename = "upload")]
    Upload(CommandUpload),
    #[serde(rename = "drag")]
    Drag(CommandDrag),
    #[serde(rename = "uncheck")]
    Uncheck(CommandUncheck),
    #[serde(rename = "get")]
    Get(CommandGet),
    #[serde(rename = "is")]
    Is(CommandIs),
    #[serde(rename = "wait")]
    Wait(CommandWait),
    #[serde(rename = "screenshot")]
    Screenshot(CommandScreenshot),
    #[serde(rename = "eval")]
    Eval(CommandEval),
    #[serde(rename = "pdf")]
    Pdf(CommandPdf),
    #[serde(rename = "scroll")]
    Scroll(CommandScroll),
    #[serde(rename = "find")]
    Find(CommandFind),
    #[serde(rename = "tab")]
    Tab(CommandTab),
    #[serde(rename = "dialog")]
    Dialog(CommandDialogInline),
}

/// Opens a URL in the tab. Only http, https, and about:blank. A URL without a scheme gets https://.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandNavigate {
    pub url: String,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandNavigate {
    pub fn new(url: String) -> Self {
        CommandNavigate { url, tab: None }
    }
}

/// Goes back one history entry.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandBack {
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

/// Goes forward one history entry.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandForward {
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

/// Reloads the tab.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandReload {
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

/// Returns the accessibility tree of the page, with a ref for each element you can act on.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandSnapshot {
    /// Only lines with refs or values, and their ancestors.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub compact: Option<bool>,
    /// Only lines up to this depth.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub depth: Option<i64>,
    /// Only lines with refs.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub interactive: Option<bool>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

/// Clicks the element with the mouse.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandClick {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// left by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub button: Option<CommandClickButton>,
    /// 2 makes a double click and 3 a triple click. 1 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub clicks: Option<i64>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandClick {
    pub fn new(target: crate::CommandTarget) -> Self {
        CommandClick {
            target,
            button: None,
            clicks: None,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandClickButton {
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(rename = "middle")]
    Middle,
    #[serde(other)]
    Unknown,
}

impl CommandClickButton {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Left => "left",
            Self::Right => "right",
            Self::Middle => "middle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandClickButton {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Moves the mouse onto the element.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandHover {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandHover {
    pub fn new(target: crate::CommandTarget) -> Self {
        CommandHover {
            target,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

/// Focuses the element.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandFocus {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandFocus {
    pub fn new(target: crate::CommandTarget) -> Self {
        CommandFocus {
            target,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

/// Types text one key press at a time, after the current content.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandType {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub text: String,
    /// Replace the current content.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub clear: Option<bool>,
    /// Milliseconds to wait between key presses.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub delay: Option<i64>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandType {
    pub fn new(target: crate::CommandTarget, text: String) -> Self {
        CommandType {
            target,
            text,
            clear: None,
            delay: None,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

/// Replaces the content of a field in one step, without key presses.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandFill {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub value: String,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandFill {
    pub fn new(target: crate::CommandTarget, value: String) -> Self {
        CommandFill {
            target,
            value,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

/// Presses a key, such as Enter, or a combination such as Control+a. On a tab with an open dialog, Enter without a target accepts the dialog and Escape dismisses it.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandPress {
    pub key: String,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

impl CommandPress {
    pub fn new(key: String) -> Self {
        CommandPress {
            key,
            expect: None,
            frame: None,
            tab: None,
            target: None,
        }
    }
}

/// Holds a key down, or lets go of a key held down.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandKeyboard {
    pub action: CommandKeyboardAction,
    /// One key, not a combination such as Control+a.
    pub key: String,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandKeyboard {
    pub fn new(action: CommandKeyboardAction, key: String) -> Self {
        CommandKeyboard {
            action,
            key,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandKeyboardAction {
    #[serde(rename = "down")]
    Down,
    #[serde(rename = "up")]
    Up,
    #[serde(other)]
    Unknown,
}

impl CommandKeyboardAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Down => "down",
            Self::Up => "up",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandKeyboardAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Lets go of every key and mouse button held on the tab.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandRelease {
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

/// Mouse input at a point in CSS pixels of the visible part of the page, for pages without useful elements such as a canvas.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandMouse {
    pub action: CommandMouseAction,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub button: Option<CommandMouseButton>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub clicks: Option<i64>,
    #[serde(rename = "deltaX", default, skip_serializing_if = "Option::is_none")]
    pub delta_x: Option<f64>,
    #[serde(rename = "deltaY", default, skip_serializing_if = "Option::is_none")]
    pub delta_y: Option<f64>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub x: Option<f64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub y: Option<f64>,
}

impl CommandMouse {
    pub fn new(action: CommandMouseAction) -> Self {
        CommandMouse {
            action,
            button: None,
            clicks: None,
            delta_x: None,
            delta_y: None,
            tab: None,
            x: None,
            y: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandMouseAction {
    #[serde(rename = "move")]
    Move,
    #[serde(rename = "down")]
    Down,
    #[serde(rename = "up")]
    Up,
    #[serde(rename = "click")]
    Click,
    #[serde(rename = "wheel")]
    Wheel,
    #[serde(other)]
    Unknown,
}

impl CommandMouseAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Move => "move",
            Self::Down => "down",
            Self::Up => "up",
            Self::Click => "click",
            Self::Wheel => "wheel",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandMouseAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandMouseButton {
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(rename = "middle")]
    Middle,
    #[serde(other)]
    Unknown,
}

impl CommandMouseButton {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Left => "left",
            Self::Right => "right",
            Self::Middle => "middle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandMouseButton {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Selects options. Matches by value, then by label.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandSelect {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub values: CommandSelectValues,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandSelect {
    pub fn new(target: crate::CommandTarget, values: CommandSelectValues) -> Self {
        CommandSelect {
            target,
            values,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum CommandSelectValues {
    String(String),
    Array(Vec<String>),
}

/// Checks a checkbox, radio button, switch, or toggle button.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandCheck {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandCheck {
    pub fn new(target: crate::CommandTarget) -> Self {
        CommandCheck {
            target,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

/// Sets the files of a file input. Upload the files to the session first.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandUpload {
    pub files: CommandUploadFiles,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandUpload {
    pub fn new(target: crate::CommandTarget, files: CommandUploadFiles) -> Self {
        CommandUpload {
            target,
            files,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum CommandUploadFiles {
    String(String),
    Array(Vec<String>),
}

/// Drags one element onto another with the mouse.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandDrag {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub source: crate::CommandTarget,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandDrag {
    pub fn new(source: crate::CommandTarget, target: crate::CommandTarget) -> Self {
        CommandDrag {
            source,
            target,
            frame: None,
            tab: None,
        }
    }
}

/// Unchecks a checkbox, switch, or toggle button.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandUncheck {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandUncheck {
    pub fn new(target: crate::CommandTarget) -> Self {
        CommandUncheck {
            target,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

/// Reads something from the page or an element.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandGet {
    pub what: CommandGetWhat,
    /// The attribute to read with attr.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub attribute: Option<String>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

impl CommandGet {
    pub fn new(what: CommandGetWhat) -> Self {
        CommandGet {
            what,
            attribute: None,
            expect: None,
            frame: None,
            tab: None,
            target: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandGetWhat {
    #[serde(rename = "text")]
    Text,
    #[serde(rename = "html")]
    Html,
    #[serde(rename = "markdown")]
    Markdown,
    #[serde(rename = "value")]
    Value,
    #[serde(rename = "attr")]
    Attr,
    #[serde(rename = "title")]
    Title,
    #[serde(rename = "url")]
    Url,
    #[serde(rename = "count")]
    Count,
    #[serde(rename = "box")]
    Box,
    #[serde(rename = "locator")]
    Locator,
    #[serde(other)]
    Unknown,
}

impl CommandGetWhat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Text => "text",
            Self::Html => "html",
            Self::Markdown => "markdown",
            Self::Value => "value",
            Self::Attr => "attr",
            Self::Title => "title",
            Self::Url => "url",
            Self::Count => "count",
            Self::Box => "box",
            Self::Locator => "locator",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandGetWhat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Checks the state of an element.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandIs {
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    pub target: crate::CommandTarget,
    pub what: CommandIsWhat,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandIs {
    pub fn new(what: CommandIsWhat, target: crate::CommandTarget) -> Self {
        CommandIs {
            what,
            target,
            expect: None,
            frame: None,
            tab: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandIsWhat {
    #[serde(rename = "visible")]
    Visible,
    #[serde(rename = "enabled")]
    Enabled,
    #[serde(rename = "checked")]
    Checked,
    #[serde(other)]
    Unknown,
}

impl CommandIsWhat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Visible => "visible",
            Self::Enabled => "enabled",
            Self::Checked => "checked",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandIsWhat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Waits for one of an element, text, a URL, a load state, a download, or a fixed time.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandWait {
    /// Waits for the next download to finish.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub download: Option<bool>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub load: Option<CommandWaitLoad>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub ms: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub state: Option<CommandWaitState>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub text: Option<String>,
    /// Milliseconds. 10000 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub timeout: Option<i64>,
    /// A URL pattern. * matches any characters.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandWaitLoad {
    #[serde(rename = "load")]
    Load,
    #[serde(rename = "domcontentloaded")]
    Domcontentloaded,
    #[serde(rename = "networkidle")]
    Networkidle,
    #[serde(other)]
    Unknown,
}

impl CommandWaitLoad {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Load => "load",
            Self::Domcontentloaded => "domcontentloaded",
            Self::Networkidle => "networkidle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandWaitLoad {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandWaitState {
    #[serde(rename = "visible")]
    Visible,
    #[serde(rename = "hidden")]
    Hidden,
    #[serde(rename = "attached")]
    Attached,
    #[serde(rename = "detached")]
    Detached,
    #[serde(other)]
    Unknown,
}

impl CommandWaitState {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Visible => "visible",
            Self::Hidden => "hidden",
            Self::Attached => "attached",
            Self::Detached => "detached",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandWaitState {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Captures the page or an element as base64 data.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandScreenshot {
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub format: Option<CommandScreenshotFormat>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    #[serde(rename = "fullPage", default, skip_serializing_if = "Option::is_none")]
    pub full_page: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub quality: Option<i64>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandScreenshotFormat {
    #[serde(rename = "png")]
    Png,
    #[serde(rename = "jpeg")]
    Jpeg,
    #[serde(rename = "webp")]
    Webp,
    #[serde(other)]
    Unknown,
}

impl CommandScreenshotFormat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Png => "png",
            Self::Jpeg => "jpeg",
            Self::Webp => "webp",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandScreenshotFormat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Runs JavaScript in the page and returns the result as JSON.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandEval {
    /// An expression, or an async function body that gives its value with return. A promise result is awaited.
    pub script: String,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// Milliseconds. 10000 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub timeout: Option<i64>,
}

impl CommandEval {
    pub fn new(script: String) -> Self {
        CommandEval {
            script,
            tab: None,
            timeout: None,
        }
    }
}

/// Prints the page to a PDF as base64 data.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandPdf {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub landscape: Option<bool>,
    #[serde(
        rename = "printBackground",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub print_background: Option<bool>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

/// Scrolls the page, or an element when target is set.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandScroll {
    /// Pixels. 300 by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub amount: Option<f64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub direction: Option<CommandScrollDirection>,
    /// Fails the command when the element does not have this role or name.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expect: Option<crate::CommandExpectation>,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    /// A ref from a snapshot such as e12, a CSS selector, or a locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub target: Option<crate::CommandTarget>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandScrollDirection {
    #[serde(rename = "up")]
    Up,
    #[serde(rename = "down")]
    Down,
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(other)]
    Unknown,
}

impl CommandScrollDirection {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Up => "up",
            Self::Down => "down",
            Self::Left => "left",
            Self::Right => "right",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandScrollDirection {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Finds elements and returns refs for up to 50 matches. With nth, it returns that one match.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandFind {
    /// A CSS selector or a locator, not a ref.
    pub target: crate::CommandTarget,
    /// The iframe to look in. List nested iframes from the outside in.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub frame: Option<crate::CommandFrame>,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
}

impl CommandFind {
    pub fn new(target: crate::CommandTarget) -> Self {
        CommandFind {
            target,
            frame: None,
            tab: None,
        }
    }
}

/// Lists, opens, or closes tabs. The last tab cannot be closed.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandTab {
    pub action: CommandTabAction,
    /// A tab id such as t2. A command on a ref does not need it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

impl CommandTab {
    pub fn new(action: CommandTabAction) -> Self {
        CommandTab {
            action,
            tab: None,
            url: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandTabAction {
    #[serde(rename = "list")]
    List,
    #[serde(rename = "new")]
    New,
    #[serde(rename = "close")]
    Close,
    #[serde(other)]
    Unknown,
}

impl CommandTabAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::List => "list",
            Self::New => "new",
            Self::Close => "close",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandTabAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Answers an open dialog. It never sends a key to the page, and fails if the dialog is not open.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandDialogInline {
    pub action: CommandDialogInlineAction,
    /// The id of the dialog, such as d-3, from dialog in a response.
    pub dialog: String,
    /// The answer to a prompt. Works only with accept. A prompt gets its default text without it.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub text: Option<String>,
}

impl CommandDialogInline {
    pub fn new(dialog: String, action: CommandDialogInlineAction) -> Self {
        CommandDialogInline {
            dialog,
            action,
            text: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandDialogInlineAction {
    #[serde(rename = "accept")]
    Accept,
    #[serde(rename = "dismiss")]
    Dismiss,
    #[serde(other)]
    Unknown,
}

impl CommandDialogInlineAction {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Accept => "accept",
            Self::Dismiss => "dismiss",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandDialogInlineAction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// A JavaScript dialog: alert, confirm, prompt, or beforeunload.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandDialog {
    pub id: String,
    pub message: String,
    pub tab: String,
    pub r#type: CommandDialogType,
    #[serde(
        rename = "defaultPrompt",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub default_prompt: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandDialogType {
    #[serde(rename = "alert")]
    Alert,
    #[serde(rename = "confirm")]
    Confirm,
    #[serde(rename = "prompt")]
    Prompt,
    #[serde(rename = "beforeunload")]
    Beforeunload,
    #[serde(other)]
    Unknown,
}

impl CommandDialogType {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Alert => "alert",
            Self::Confirm => "confirm",
            Self::Prompt => "prompt",
            Self::Beforeunload => "beforeunload",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandDialogType {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandErrorInfo {
    /// Error code, such as stale_ref or not_actionable.
    pub code: String,
    pub message: String,
    /// More detail, such as covered or detached.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub reason: Option<String>,
}

/// Fails the command when the element does not have this role or name.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandExpectation {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub name: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub role: Option<String>,
}

/// The iframe to look in. List nested iframes from the outside in.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum CommandFrame {
    String(String),
    CommandLocator(crate::CommandLocator),
    Array(Vec<crate::CommandTarget>),
}

/// Input still held down on a tab. It applies to later input until released.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandHeld {
    /// Keys still held down, for example with keyboard down.
    pub keys: Vec<String>,
    pub tab: String,
    /// A mouse button held down with mouse down.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub button: Option<CommandHeldButton>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandHeldButton {
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(rename = "middle")]
    Middle,
    #[serde(other)]
    Unknown,
}

impl CommandHeldButton {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Left => "left",
            Self::Right => "right",
            Self::Middle => "middle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandHeldButton {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Finds an element. Set exactly one of role, text, label, placeholder, alt, title, testid, css.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct CommandLocator {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub alt: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub css: Option<String>,
    /// Match the whole text. Text matches a case-insensitive substring by default.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub exact: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub label: Option<String>,
    /// The accessible name. Works only with role.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub name: Option<String>,
    /// Picks one of several matches. Negative values count from the end.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub nth: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub placeholder: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub role: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub testid: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub text: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub title: Option<String>,
}

/// Result of one command, with the fields of that command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum CommandResult {
    Navigate(CommandResultNavigate),
    Back(CommandResultBack),
    Forward(CommandResultForward),
    Reload(CommandResultReload),
    Snapshot(CommandResultSnapshot),
    Click(CommandResultClick),
    Hover(CommandResultHover),
    Focus(CommandResultFocus),
    Type(CommandResultType),
    Fill(CommandResultFill),
    Press(CommandResultPress),
    Keyboard(CommandResultKeyboard),
    Mouse(CommandResultMouse),
    Release(CommandResultRelease),
    Select(CommandResultSelect),
    Check(CommandResultCheck),
    Uncheck(CommandResultUncheck),
    Upload(CommandResultUpload),
    Drag(CommandResultDrag),
    Get(CommandResultGet),
    Is(CommandResultIs),
    Wait(CommandResultWait),
    Screenshot(CommandResultScreenshot),
    Eval(CommandResultEval),
    Pdf(CommandResultPdf),
    Scroll(CommandResultScroll),
    Find(CommandResultFind),
    Tab(CommandResultTab),
    Dialog(CommandResultDialog),
    Unknown(serde_json::Value),
}

/// Result of a navigate command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultNavigate {
    pub command: CommandResultNavigateCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultNavigateEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultNavigateCommand {
    #[default]
    #[serde(rename = "navigate")]
    Navigate,
}

impl CommandResultNavigateCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Navigate => "navigate",
        }
    }
}

impl std::fmt::Display for CommandResultNavigateCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultNavigateEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultNavigateEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultNavigateEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a back command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultBack {
    pub command: CommandResultBackCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultBackEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// False when there was no history entry.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub navigated: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultBackCommand {
    #[default]
    #[serde(rename = "back")]
    Back,
}

impl CommandResultBackCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Back => "back",
        }
    }
}

impl std::fmt::Display for CommandResultBackCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultBackEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultBackEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultBackEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a forward command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultForward {
    pub command: CommandResultForwardCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultForwardEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// False when there was no history entry.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub navigated: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultForwardCommand {
    #[default]
    #[serde(rename = "forward")]
    Forward,
}

impl CommandResultForwardCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Forward => "forward",
        }
    }
}

impl std::fmt::Display for CommandResultForwardCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultForwardEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultForwardEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultForwardEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a reload command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultReload {
    pub command: CommandResultReloadCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultReloadEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultReloadCommand {
    #[default]
    #[serde(rename = "reload")]
    Reload,
}

impl CommandResultReloadCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Reload => "reload",
        }
    }
}

impl std::fmt::Display for CommandResultReloadCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultReloadEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultReloadEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultReloadEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a snapshot command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultSnapshot {
    pub command: CommandResultSnapshotCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultSnapshotEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub snapshot: Option<String>,
    #[serde(
        rename = "snapshotId",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub snapshot_id: Option<String>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultSnapshotCommand {
    #[default]
    #[serde(rename = "snapshot")]
    Snapshot,
}

impl CommandResultSnapshotCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Snapshot => "snapshot",
        }
    }
}

impl std::fmt::Display for CommandResultSnapshotCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultSnapshotEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultSnapshotEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultSnapshotEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a click command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultClick {
    pub command: CommandResultClickCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultClickEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultClickCommand {
    #[default]
    #[serde(rename = "click")]
    Click,
}

impl CommandResultClickCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Click => "click",
        }
    }
}

impl std::fmt::Display for CommandResultClickCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultClickEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultClickEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultClickEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a hover command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultHover {
    pub command: CommandResultHoverCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultHoverEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultHoverCommand {
    #[default]
    #[serde(rename = "hover")]
    Hover,
}

impl CommandResultHoverCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Hover => "hover",
        }
    }
}

impl std::fmt::Display for CommandResultHoverCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultHoverEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultHoverEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultHoverEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a focus command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultFocus {
    pub command: CommandResultFocusCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultFocusEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultFocusCommand {
    #[default]
    #[serde(rename = "focus")]
    Focus,
}

impl CommandResultFocusCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Focus => "focus",
        }
    }
}

impl std::fmt::Display for CommandResultFocusCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultFocusEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultFocusEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultFocusEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a type command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultType {
    pub command: CommandResultTypeCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultTypeEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultTypeCommand {
    #[default]
    #[serde(rename = "type")]
    Type,
}

impl CommandResultTypeCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Type => "type",
        }
    }
}

impl std::fmt::Display for CommandResultTypeCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultTypeEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultTypeEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultTypeEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a fill command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultFill {
    pub command: CommandResultFillCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultFillEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultFillCommand {
    #[default]
    #[serde(rename = "fill")]
    Fill,
}

impl CommandResultFillCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Fill => "fill",
        }
    }
}

impl std::fmt::Display for CommandResultFillCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultFillEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultFillEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultFillEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a press command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultPress {
    pub command: CommandResultPressCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultPressEffect,
    pub ok: bool,
    /// The id of the dialog that the key answered. Absent when the key went to the page.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub answered: Option<String>,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultPressCommand {
    #[default]
    #[serde(rename = "press")]
    Press,
}

impl CommandResultPressCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Press => "press",
        }
    }
}

impl std::fmt::Display for CommandResultPressCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultPressEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultPressEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultPressEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a keyboard command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultKeyboard {
    pub command: CommandResultKeyboardCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultKeyboardEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultKeyboardCommand {
    #[default]
    #[serde(rename = "keyboard")]
    Keyboard,
}

impl CommandResultKeyboardCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Keyboard => "keyboard",
        }
    }
}

impl std::fmt::Display for CommandResultKeyboardCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultKeyboardEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultKeyboardEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultKeyboardEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a mouse command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultMouse {
    pub command: CommandResultMouseCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultMouseEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultMouseCommand {
    #[default]
    #[serde(rename = "mouse")]
    Mouse,
}

impl CommandResultMouseCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Mouse => "mouse",
        }
    }
}

impl std::fmt::Display for CommandResultMouseCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultMouseEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultMouseEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultMouseEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a release command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultRelease {
    pub command: CommandResultReleaseCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultReleaseEffect,
    pub ok: bool,
    /// The mouse button that was let go.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub button: Option<CommandResultReleaseButton>,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The keys that were let go.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub keys: Option<Vec<String>>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultReleaseCommand {
    #[default]
    #[serde(rename = "release")]
    Release,
}

impl CommandResultReleaseCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Release => "release",
        }
    }
}

impl std::fmt::Display for CommandResultReleaseCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultReleaseEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultReleaseEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultReleaseEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultReleaseButton {
    #[serde(rename = "left")]
    Left,
    #[serde(rename = "right")]
    Right,
    #[serde(rename = "middle")]
    Middle,
    #[serde(other)]
    Unknown,
}

impl CommandResultReleaseButton {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Left => "left",
            Self::Right => "right",
            Self::Middle => "middle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandResultReleaseButton {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a select command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultSelect {
    pub command: CommandResultSelectCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultSelectEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
    /// The values of the selected options.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub selected: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultSelectCommand {
    #[default]
    #[serde(rename = "select")]
    Select,
}

impl CommandResultSelectCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Select => "select",
        }
    }
}

impl std::fmt::Display for CommandResultSelectCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultSelectEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultSelectEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultSelectEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a check command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultCheck {
    pub command: CommandResultCheckCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultCheckEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub checked: Option<bool>,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultCheckCommand {
    #[default]
    #[serde(rename = "check")]
    Check,
}

impl CommandResultCheckCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Check => "check",
        }
    }
}

impl std::fmt::Display for CommandResultCheckCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultCheckEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultCheckEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultCheckEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a uncheck command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultUncheck {
    pub command: CommandResultUncheckCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultUncheckEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub checked: Option<bool>,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultUncheckCommand {
    #[default]
    #[serde(rename = "uncheck")]
    Uncheck,
}

impl CommandResultUncheckCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Uncheck => "uncheck",
        }
    }
}

impl std::fmt::Display for CommandResultUncheckCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultUncheckEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultUncheckEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultUncheckEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a upload command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultUpload {
    pub command: CommandResultUploadCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultUploadEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub files: Option<Vec<String>>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultUploadCommand {
    #[default]
    #[serde(rename = "upload")]
    Upload,
}

impl CommandResultUploadCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Upload => "upload",
        }
    }
}

impl std::fmt::Display for CommandResultUploadCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultUploadEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultUploadEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultUploadEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a drag command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultDrag {
    pub command: CommandResultDragCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultDragEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultDragCommand {
    #[default]
    #[serde(rename = "drag")]
    Drag,
}

impl CommandResultDragCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Drag => "drag",
        }
    }
}

impl std::fmt::Display for CommandResultDragCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultDragEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultDragEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultDragEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a get command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultGet {
    pub command: CommandResultGetCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultGetEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The value is a secret, so it is left out.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub masked: Option<bool>,
    /// A string for text, html, markdown, value, attr, title, and url. A number for count. { x, y, width, height } or null for box. A list of locators for locator.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub value: Option<serde_json::Value>,
    /// What was read. It tells the type of value.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub what: Option<CommandResultGetWhat>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultGetCommand {
    #[default]
    #[serde(rename = "get")]
    Get,
}

impl CommandResultGetCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Get => "get",
        }
    }
}

impl std::fmt::Display for CommandResultGetCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultGetEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultGetEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultGetEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultGetWhat {
    #[serde(rename = "text")]
    Text,
    #[serde(rename = "html")]
    Html,
    #[serde(rename = "markdown")]
    Markdown,
    #[serde(rename = "value")]
    Value,
    #[serde(rename = "attr")]
    Attr,
    #[serde(rename = "title")]
    Title,
    #[serde(rename = "url")]
    Url,
    #[serde(rename = "count")]
    Count,
    #[serde(rename = "box")]
    Box,
    #[serde(rename = "locator")]
    Locator,
    #[serde(other)]
    Unknown,
}

impl CommandResultGetWhat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Text => "text",
            Self::Html => "html",
            Self::Markdown => "markdown",
            Self::Value => "value",
            Self::Attr => "attr",
            Self::Title => "title",
            Self::Url => "url",
            Self::Count => "count",
            Self::Box => "box",
            Self::Locator => "locator",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandResultGetWhat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a is command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultIs {
    pub command: CommandResultIsCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultIsEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub value: Option<bool>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultIsCommand {
    #[default]
    #[serde(rename = "is")]
    Is,
}

impl CommandResultIsCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Is => "is",
        }
    }
}

impl std::fmt::Display for CommandResultIsCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultIsEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultIsEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultIsEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a wait command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultWait {
    pub command: CommandResultWaitCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultWaitEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub bytes: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The downloaded file, for download.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub file: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub waited: Option<CommandResultWaitWaited>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultWaitCommand {
    #[default]
    #[serde(rename = "wait")]
    Wait,
}

impl CommandResultWaitCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Wait => "wait",
        }
    }
}

impl std::fmt::Display for CommandResultWaitCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultWaitEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultWaitEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultWaitEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultWaitWaited {
    #[serde(rename = "ms")]
    Ms,
    #[serde(rename = "target")]
    Target,
    #[serde(rename = "text")]
    Text,
    #[serde(rename = "url")]
    Url,
    #[serde(rename = "load")]
    Load,
    #[serde(rename = "download")]
    Download,
    #[serde(other)]
    Unknown,
}

impl CommandResultWaitWaited {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Ms => "ms",
            Self::Target => "target",
            Self::Text => "text",
            Self::Url => "url",
            Self::Load => "load",
            Self::Download => "download",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandResultWaitWaited {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a screenshot command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultScreenshot {
    pub command: CommandResultScreenshotCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultScreenshotEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub data: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub format: Option<CommandResultScreenshotFormat>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultScreenshotCommand {
    #[default]
    #[serde(rename = "screenshot")]
    Screenshot,
}

impl CommandResultScreenshotCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Screenshot => "screenshot",
        }
    }
}

impl std::fmt::Display for CommandResultScreenshotCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultScreenshotEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultScreenshotEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultScreenshotEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultScreenshotFormat {
    #[serde(rename = "png")]
    Png,
    #[serde(rename = "jpeg")]
    Jpeg,
    #[serde(rename = "webp")]
    Webp,
    #[serde(other)]
    Unknown,
}

impl CommandResultScreenshotFormat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Png => "png",
            Self::Jpeg => "jpeg",
            Self::Webp => "webp",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandResultScreenshotFormat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a eval command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultEval {
    pub command: CommandResultEvalCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultEvalEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
    /// The result of the script as JSON.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub value: Option<serde_json::Value>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultEvalCommand {
    #[default]
    #[serde(rename = "eval")]
    Eval,
}

impl CommandResultEvalCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Eval => "eval",
        }
    }
}

impl std::fmt::Display for CommandResultEvalCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultEvalEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultEvalEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultEvalEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a pdf command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultPdf {
    pub command: CommandResultPdfCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultPdfEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub data: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub format: Option<CommandResultPdfFormat>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultPdfCommand {
    #[default]
    #[serde(rename = "pdf")]
    Pdf,
}

impl CommandResultPdfCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Pdf => "pdf",
        }
    }
}

impl std::fmt::Display for CommandResultPdfCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultPdfEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultPdfEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultPdfEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultPdfFormat {
    #[serde(rename = "pdf")]
    Pdf,
    #[serde(other)]
    Unknown,
}

impl CommandResultPdfFormat {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Pdf => "pdf",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for CommandResultPdfFormat {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a scroll command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultScroll {
    pub command: CommandResultScrollCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultScrollEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub moved: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub x: Option<f64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub y: Option<f64>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultScrollCommand {
    #[default]
    #[serde(rename = "scroll")]
    Scroll,
}

impl CommandResultScrollCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Scroll => "scroll",
        }
    }
}

impl std::fmt::Display for CommandResultScrollCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultScrollEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultScrollEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultScrollEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Result of a find command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultFind {
    pub command: CommandResultFindCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultFindEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub count: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub matches: Option<Vec<CommandResultFindMatch>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultFindCommand {
    #[default]
    #[serde(rename = "find")]
    Find,
}

impl CommandResultFindCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Find => "find",
        }
    }
}

impl std::fmt::Display for CommandResultFindCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultFindEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultFindEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultFindEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultFindMatch {
    pub name: String,
    pub r#ref: String,
    pub role: String,
}

/// Result of a tab command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultTab {
    pub command: CommandResultTabCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultTabEffect,
    pub ok: bool,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tab: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub tabs: Option<Vec<CommandResultTabTab>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultTabCommand {
    #[default]
    #[serde(rename = "tab")]
    Tab,
}

impl CommandResultTabCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Tab => "tab",
        }
    }
}

impl std::fmt::Display for CommandResultTabCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultTabEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultTabEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultTabEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultTabTab {
    pub tab: String,
    pub title: String,
    pub url: String,
}

/// Result of a dialog command.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CommandResultDialog {
    pub command: CommandResultDialogCommand,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: CommandResultDialogEffect,
    pub ok: bool,
    /// A dialog that the command opened. It blocks the tab until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    /// The page was still loading when the command returned.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub loading: Option<bool>,
    /// The ids of tabs that opened while the command ran.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub opened: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultDialogCommand {
    #[default]
    #[serde(rename = "dialog")]
    Dialog,
}

impl CommandResultDialogCommand {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Dialog => "dialog",
        }
    }
}

impl std::fmt::Display for CommandResultDialogCommand {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum CommandResultDialogEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl CommandResultDialogEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for CommandResultDialogEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// A ref from a snapshot such as e12, a CSS selector, or a locator.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum CommandTarget {
    String(String),
    CommandLocator(crate::CommandLocator),
}

/// Represents the data structure for a browser session, including its configuration and status.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct Session {
    /// Unique identifier for the session
    pub id: String,
    /// Browser mode used for this session.
    #[serde(rename = "browserMode")]
    pub browser_mode: SessionBrowserMode,
    /// Timestamp when the session started
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Amount of credits consumed by the session
    #[serde(rename = "creditsUsed")]
    pub credits_used: i64,
    /// URL for debugging the session
    #[serde(rename = "debugUrl")]
    pub debug_url: String,
    /// Viewport and browser window dimensions for the session
    pub dimensions: SessionDimensions,
    /// Duration of the session in milliseconds
    pub duration: i64,
    /// Number of events processed in the session
    #[serde(rename = "eventCount")]
    pub event_count: i64,
    /// Bandwidth optimizations that were applied to the session.
    #[serde(rename = "optimizeBandwidth")]
    pub optimize_bandwidth: SessionOptimizeBandwidth,
    /// Amount of data transmitted through the proxy
    #[serde(rename = "proxyBytesUsed")]
    pub proxy_bytes_used: i64,
    /// Source of the proxy used for the session
    #[serde(rename = "proxySource", default)]
    pub proxy_source: Option<SessionProxySource>,
    /// URL to view session details
    #[serde(rename = "sessionViewerUrl")]
    pub session_viewer_url: String,
    /// Signals observed during the session. Filled in within a few seconds after the session ends, and empty until then. An empty list does not mean nothing happened: detection is heuristic and coverage is incomplete.
    pub signals: Vec<SessionSignal>,
    /// Status of the session
    pub status: SessionStatus,
    /// Session timeout duration in milliseconds
    pub timeout: i64,
    /// URL for the session's WebSocket connection
    #[serde(rename = "websocketUrl")]
    pub websocket_url: String,
    /// Configuration for the debug URL and session viewer. Controls interaction capabilities and cursor visibility.
    #[serde(
        rename = "debugConfig",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub debug_config: Option<SessionDebugConfig>,
    /// Device configuration for the session
    #[serde(
        rename = "deviceConfig",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub device_config: Option<SessionDeviceConfig>,
    /// Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub fullscreen: Option<bool>,
    /// Indicates if the session is headless or headful
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub headless: Option<bool>,
    /// Inactivity timeout in milliseconds, if one was set when the session was created
    #[serde(
        rename = "inactivityTimeout",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub inactivity_timeout: Option<i64>,
    /// Indicates if Selenium is used in the session
    #[serde(
        rename = "isSelenium",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub is_selenium: Option<bool>,
    /// Customer-defined flat metadata. Values are always strings: numbers and booleans sent on write come back in their string form.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub metadata: Option<std::collections::HashMap<String, String>>,
    /// This flag will persist the profile for the session.
    #[serde(
        rename = "persistProfile",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub persist_profile: Option<bool>,
    /// The ID of the profile associated with the session
    #[serde(rename = "profileId", default, skip_serializing_if = "Option::is_none")]
    pub profile_id: Option<String>,
    /// The project associated with the session
    #[serde(rename = "projectId", default, skip_serializing_if = "Option::is_none")]
    pub project_id: Option<String>,
    /// The region where the session was created.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub region: Option<String>,
    /// Why the session reached a terminal state. Null while the session is live, or when the reason is unknown (e.g. sessions created before this was tracked). One of: user_requested (released via the API/SDK), timeout (hard `timeout` elapsed), inactivity_timeout (no activity for the configured window), creation_timeout (never started in time), startup_failed (could not be dispatched), browser_closed (the browser or agent closed itself — not a crash), browser_crashed (the browser crashed or its machine became unresponsive).
    #[serde(
        rename = "releaseReason",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub release_reason: Option<SessionReleaseReason>,
    /// Indicates if captcha solving is enabled
    #[serde(
        rename = "solveCaptcha",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub solve_captcha: Option<bool>,
    /// Stealth configuration for the session
    #[serde(
        rename = "stealthConfig",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub stealth_config: Option<SessionStealthConfig>,
    /// User agent string used in the session
    #[serde(rename = "userAgent", default, skip_serializing_if = "Option::is_none")]
    pub user_agent: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionBrowserMode {
    #[serde(rename = "stealth")]
    Stealth,
    #[serde(rename = "standard")]
    Standard,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl SessionBrowserMode {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Stealth => "stealth",
            Self::Standard => "standard",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for SessionBrowserMode {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionDimensions {
    /// Height of the browser window
    pub height: i64,
    /// Width of the browser window
    pub width: i64,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionOptimizeBandwidth {
    #[serde(
        rename = "blockHosts",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_hosts: Option<Vec<String>>,
    #[serde(
        rename = "blockImages",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_images: Option<bool>,
    #[serde(
        rename = "blockMedia",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_media: Option<bool>,
    #[serde(
        rename = "blockStylesheets",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_stylesheets: Option<bool>,
    #[serde(
        rename = "blockUrlPatterns",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub block_url_patterns: Option<Vec<String>>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionProxySource {
    #[serde(rename = "steel")]
    Steel,
    #[serde(rename = "external")]
    External,
    #[serde(other)]
    Unknown,
}

impl SessionProxySource {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Steel => "steel",
            Self::External => "external",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionProxySource {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionSignal {
    #[serde(rename = "captcha_encountered")]
    CaptchaEncountered,
    #[serde(rename = "proxy_connection_failed")]
    ProxyConnectionFailed,
    #[serde(rename = "session_timeout_exceeded")]
    SessionTimeoutExceeded,
    #[serde(rename = "bot_detection_triggered")]
    BotDetectionTriggered,
    #[serde(rename = "context_restored")]
    ContextRestored,
    #[serde(other)]
    Unknown,
}

impl SessionSignal {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::CaptchaEncountered => "captcha_encountered",
            Self::ProxyConnectionFailed => "proxy_connection_failed",
            Self::SessionTimeoutExceeded => "session_timeout_exceeded",
            Self::BotDetectionTriggered => "bot_detection_triggered",
            Self::ContextRestored => "context_restored",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionSignal {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionStatus {
    #[serde(rename = "live")]
    Live,
    #[serde(rename = "released")]
    Released,
    #[serde(rename = "failed")]
    Failed,
    #[serde(other)]
    Unknown,
}

impl SessionStatus {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Live => "live",
            Self::Released => "released",
            Self::Failed => "failed",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionStatus {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionDebugConfig {
    /// Whether interaction is allowed via the debug URL viewer. When false, the session viewer is view-only.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub interactive: Option<bool>,
    /// Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode only).
    #[serde(
        rename = "systemCursor",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub system_cursor: Option<bool>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionDeviceConfig {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub device: Option<SessionDeviceConfigDevice>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionDeviceConfigDevice {
    #[serde(rename = "desktop")]
    Desktop,
    #[serde(rename = "mobile")]
    Mobile,
    #[serde(other)]
    Unknown,
}

impl SessionDeviceConfigDevice {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Desktop => "desktop",
            Self::Mobile => "mobile",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionDeviceConfigDevice {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionReleaseReason {
    #[serde(rename = "user_requested")]
    UserRequested,
    #[serde(rename = "timeout")]
    Timeout,
    #[serde(rename = "inactivity_timeout")]
    InactivityTimeout,
    #[serde(rename = "creation_timeout")]
    CreationTimeout,
    #[serde(rename = "startup_failed")]
    StartupFailed,
    #[serde(rename = "browser_closed")]
    BrowserClosed,
    #[serde(rename = "browser_crashed")]
    BrowserCrashed,
    #[serde(other)]
    Unknown,
}

impl SessionReleaseReason {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::UserRequested => "user_requested",
            Self::Timeout => "timeout",
            Self::InactivityTimeout => "inactivity_timeout",
            Self::CreationTimeout => "creation_timeout",
            Self::StartupFailed => "startup_failed",
            Self::BrowserClosed => "browser_closed",
            Self::BrowserCrashed => "browser_crashed",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionReleaseReason {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionStealthConfig {
    /// When true, captchas will be automatically solved when detected. When false, use the solve endpoints to manually initiate solving.
    #[serde(
        rename = "autoCaptchaSolving",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub auto_captcha_solving: Option<bool>,
    /// This flag will make the browser act more human-like by moving the mouse in a more natural way
    #[serde(
        rename = "humanizeInteractions",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub humanize_interactions: Option<bool>,
    /// This flag will skip the fingerprint generation for the session.
    #[serde(
        rename = "skipFingerprintInjection",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub skip_fingerprint_injection: Option<bool>,
}

/// Session context data returned from a browser session.
#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionContext {
    /// Cookies to initialize in the session
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub cookies: Option<Vec<SessionContextCookie>>,
    /// Domain-specific indexedDB items to initialize in the session
    #[serde(rename = "indexedDB", default, skip_serializing_if = "Option::is_none")]
    pub indexed_db: Option<std::collections::HashMap<String, Vec<SessionContextIndexedDb>>>,
    /// Domain-specific localStorage items to initialize in the session
    #[serde(
        rename = "localStorage",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub local_storage:
        Option<std::collections::HashMap<String, std::collections::HashMap<String, String>>>,
    /// Domain-specific sessionStorage items to initialize in the session
    #[serde(
        rename = "sessionStorage",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub session_storage:
        Option<std::collections::HashMap<String, std::collections::HashMap<String, String>>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionContextCookie {
    /// The name of the cookie
    pub name: String,
    /// The value of the cookie
    pub value: String,
    /// The domain of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub domain: Option<String>,
    /// The expiration date of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub expires: Option<f64>,
    /// Whether the cookie is HTTP only
    #[serde(rename = "httpOnly", default, skip_serializing_if = "Option::is_none")]
    pub http_only: Option<bool>,
    /// The partition key of the cookie
    #[serde(
        rename = "partitionKey",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub partition_key: Option<SessionContextCookiePartitionKey>,
    /// The path of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub path: Option<String>,
    /// The priority of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub priority: Option<SessionContextCookiePriority>,
    /// Whether the cookie is a same party cookie
    #[serde(rename = "sameParty", default, skip_serializing_if = "Option::is_none")]
    pub same_party: Option<bool>,
    /// The same site attribute of the cookie
    #[serde(rename = "sameSite", default, skip_serializing_if = "Option::is_none")]
    pub same_site: Option<SessionContextCookieSameSite>,
    /// Whether the cookie is secure
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub secure: Option<bool>,
    /// Whether the cookie is a session cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub session: Option<bool>,
    /// The size of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub size: Option<f64>,
    /// The source port of the cookie
    #[serde(
        rename = "sourcePort",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub source_port: Option<f64>,
    /// The source scheme of the cookie
    #[serde(
        rename = "sourceScheme",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub source_scheme: Option<SessionContextCookieSourceScheme>,
    /// The URL of the cookie
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub url: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionContextCookiePartitionKey {
    /// Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
    #[serde(rename = "hasCrossSiteAncestor")]
    pub has_cross_site_ancestor: bool,
    /// The site of the top-level URL the browser was visiting at the start of the request to the endpoint that set the cookie.
    #[serde(rename = "topLevelSite")]
    pub top_level_site: String,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionContextCookiePriority {
    #[serde(rename = "Low")]
    Low,
    #[serde(rename = "Medium")]
    Medium,
    #[serde(rename = "High")]
    High,
    #[serde(other)]
    Unknown,
}

impl SessionContextCookiePriority {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Low => "Low",
            Self::Medium => "Medium",
            Self::High => "High",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionContextCookiePriority {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionContextCookieSameSite {
    #[serde(rename = "Strict")]
    Strict,
    #[serde(rename = "Lax")]
    Lax,
    #[serde(rename = "None")]
    None,
    #[serde(other)]
    Unknown,
}

impl SessionContextCookieSameSite {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Strict => "Strict",
            Self::Lax => "Lax",
            Self::None => "None",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionContextCookieSameSite {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionContextCookieSourceScheme {
    #[serde(rename = "Unset")]
    Unset,
    #[serde(rename = "NonSecure")]
    NonSecure,
    #[serde(rename = "Secure")]
    Secure,
    #[serde(other)]
    Unknown,
}

impl SessionContextCookieSourceScheme {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Unset => "Unset",
            Self::NonSecure => "NonSecure",
            Self::Secure => "Secure",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionContextCookieSourceScheme {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionContextIndexedDb {
    pub id: f64,
    pub data: Vec<SessionContextIndexedDbData>,
    pub name: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionContextIndexedDbData {
    pub id: f64,
    pub name: String,
    pub records: Vec<SessionContextIndexedDbDataRecord>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionContextIndexedDbDataRecord {
    pub key: serde_json::Value,
    pub value: serde_json::Value,
    #[serde(rename = "blobFiles", default, skip_serializing_if = "Option::is_none")]
    pub blob_files: Option<Vec<SessionContextIndexedDbDataRecordBlobFile>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionContextIndexedDbDataRecordBlobFile {
    #[serde(rename = "blobNumber")]
    pub blob_number: f64,
    #[serde(rename = "mimeType")]
    pub mime_type: String,
    pub size: f64,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub filename: Option<String>,
    #[serde(
        rename = "lastModified",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub last_modified: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub path: Option<String>,
}

/// The credentials a session is bound to
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCredentials {
    /// Bindings, including KV references with their allowed origins and soft-deleted targets.
    pub bindings: Vec<SessionCredentialsBinding>,
    /// Live credentials bound to the session, oldest binding first. No values.
    pub credentials: Vec<SessionCredentialsCredential>,
    /// Whether the session was created with a `credentials` configuration. Only then can credentials be attached; otherwise attach and replace answer 409 `credentials_not_enabled`.
    #[serde(rename = "credentialsEnabled")]
    pub credentials_enabled: bool,
    /// `explicit`: the session fills only the credentials listed here. `implicit`: it fills credentials stored for its namespace and the page origin.
    pub lookup: SessionCredentialsLookup,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCredentialsBinding {
    String(String),
    UnionMember1(SessionCredentialsBindingUnionMember1),
    Unknown(serde_json::Value),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCredentialsBindingUnionMember1 {
    #[serde(rename = "allowedOrigins")]
    pub allowed_origins: Vec<String>,
    pub reference: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCredentialsCredential {
    /// The credential's id (a Secret of kind `credential`)
    pub id: String,
    /// Other origins the credential may be filled on
    #[serde(rename = "additionalOrigins")]
    pub additional_origins: Vec<String>,
    /// The credential's name
    pub name: String,
    /// The credential's namespace
    pub namespace: String,
    /// The website origin the credential is for
    pub origin: String,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCredentialsLookup {
    #[serde(rename = "implicit")]
    Implicit,
    #[serde(rename = "explicit")]
    Explicit,
    #[serde(other)]
    Unknown,
}

impl SessionCredentialsLookup {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Implicit => "implicit",
            Self::Explicit => "explicit",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCredentialsLookup {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

/// Response containing a list of browser sessions with pagination details.
#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct Sessionslist {
    /// Cursor for the next page of results. Null if no more pages.
    #[serde(rename = "nextCursor", default)]
    pub next_cursor: Option<String>,
    /// List of browser sessions
    pub sessions: Vec<SessionslistSession>,
    /// Exact number of live sessions in the organization/project scope. Only included for status=live without metadata, signal, releaseReason, createdAfter or createdBefore filters. Other queries omit this field; use nextCursor to paginate until it is null.
    #[serde(
        rename = "totalCount",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub total_count: Option<i64>,
}
