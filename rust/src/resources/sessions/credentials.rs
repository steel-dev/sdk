use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct SessionsCredentials<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> SessionsCredentials<'a> {
    /// The session's credential lookup and the live credentials bound to it (no values). Subject to session organization and project access.
    pub fn list(&self, id: &str) -> crate::Call<'a, crate::SessionCredentials> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/credentials", crate::encoding::segment(id));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, path);
            client.json(request).await
        })
    }

    /// Attach credentialIds and/or Vault or item references in bindings. Each array accepts at most 100 entries; at most 100 distinct direct items and whole Vaults may be bound. Only credentials can be autofilled. Lookup becomes explicit and remains so. Errors: 400 `invalid_reference`, `reference_not_found`, `reference_ambiguous`, `reference_not_supported`, `credential_not_bindable`, or `too_many_credentials`; 409 `credentials_not_enabled` or `session_not_running`; 503 `credential_bindings_busy` (retryable).
    pub fn attach(
        &self,
        id: &str,
        params: SessionCredentialAttachParams,
    ) -> crate::Call<'a, crate::SessionCredentials> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/credentials", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::POST, path);
            let mut body = serde_json::Map::new();
            if let Some(value) = &params.bindings {
                body.insert("bindings".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.credential_ids {
                body.insert("credentialIds".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// Unbind a credential from a running session. The lookup stays `explicit`: with nothing bound, the session fills nothing. Errors: 404 `credential_not_attached`, 409 `session_not_running`, 503 `credential_bindings_busy` (retryable).
    pub fn detach(
        &self,
        id: &str,
        credential_id: &str,
    ) -> crate::Call<'a, crate::SessionCredentials> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/credentials/{}",
            crate::encoding::segment(id),
            crate::encoding::segment(credential_id)
        );
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::DELETE, path);
            client.json(request).await
        })
    }

    /// Pass reference as a query parameter to detach one Vault or item binding, including steel://connections/<connection>/vaults/<vault>[/<item>]. Does not detach items covered by a whole Vault binding. Errors: 400 `invalid_reference` or `reference_ambiguous`; 404 `credential_not_attached`; 409 `session_not_running`; 503 `credential_bindings_busy` (retryable).
    pub fn detach_binding(
        &self,
        id: &str,
        params: SessionCredentialDetachBindingParams,
    ) -> crate::Call<'a, crate::SessionCredentials> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/credentials/bindings",
            crate::encoding::segment(id)
        );
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::DELETE, path);
            request.query.extend(crate::encoding::query(
                "reference",
                &serde_json::to_value(&params.reference)?,
            ));
            client.json(request).await
        })
    }

    /// Replace all direct item and Vault bindings, including provider references steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds and/or bindings; empty arrays detach all. At most 100 distinct bindings may be stored. Only credentials can be autofilled. Lookup becomes explicit and remains so.
    pub fn replace(
        &self,
        id: &str,
        params: SessionCredentialReplaceParams,
    ) -> crate::Call<'a, crate::SessionCredentials> {
        let client = self.client;
        let path = format!("/v1/sessions/{}/credentials", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::PUT, path);
            let mut body = serde_json::Map::new();
            if let Some(value) = &params.bindings {
                body.insert("bindings".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.credential_ids {
                body.insert("credentialIds".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionCredentialAttachParams {
    /// At most 100 bindings; KV bindings may specify allowedOrigins.
    pub bindings: Option<Vec<SessionCredentialAttachParamsBinding>>,
    /// Ids of credentials (Secrets of kind `credential`) in the session's project. Binding makes the session's lookup `explicit` for good.
    pub credential_ids: Option<Vec<String>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCredentialAttachParamsBinding {
    String(String),
    UnionMember1(SessionCredentialAttachParamsBindingUnionMember1),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCredentialAttachParamsBindingUnionMember1 {
    #[serde(rename = "allowedOrigins")]
    pub allowed_origins: Vec<String>,
    pub reference: String,
}

impl SessionCredentialAttachParamsBindingUnionMember1 {
    pub fn new(reference: String, allowed_origins: Vec<String>) -> Self {
        SessionCredentialAttachParamsBindingUnionMember1 {
            reference,
            allowed_origins,
        }
    }
}

#[derive(Debug, Clone, PartialEq)]
pub struct SessionCredentialDetachBindingParams {
    /// Vault or item reference to detach
    pub reference: String,
}

impl SessionCredentialDetachBindingParams {
    pub fn new(reference: String) -> Self {
        SessionCredentialDetachBindingParams { reference }
    }
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionCredentialReplaceParams {
    /// At most 100 bindings; KV bindings may specify allowedOrigins.
    pub bindings: Option<Vec<SessionCredentialReplaceParamsBinding>>,
    /// Ids of credentials (Secrets of kind `credential`) in the session's project. Binding makes the session's lookup `explicit` for good.
    pub credential_ids: Option<Vec<String>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
#[serde(untagged)]
pub enum SessionCredentialReplaceParamsBinding {
    String(String),
    UnionMember1(SessionCredentialReplaceParamsBindingUnionMember1),
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCredentialReplaceParamsBindingUnionMember1 {
    #[serde(rename = "allowedOrigins")]
    pub allowed_origins: Vec<String>,
    pub reference: String,
}

impl SessionCredentialReplaceParamsBindingUnionMember1 {
    pub fn new(reference: String, allowed_origins: Vec<String>) -> Self {
        SessionCredentialReplaceParamsBindingUnionMember1 {
            reference,
            allowed_origins,
        }
    }
}
