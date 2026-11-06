use serde::{Deserialize, Serialize};

/// Create project-scoped API keys using an organization-scoped API key.
#[derive(Debug, Clone, Copy)]
pub struct ApiKeys<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> ApiKeys<'a> {
    /// Create a project-scoped API key using a global (organization-scoped) API key. Supply projectId for an active project in the authenticated organization; scopeType must be project or omitted. Project-scoped API keys cannot create keys, and global keys cannot create other global keys. Dashboard users may also create project keys, or global keys with organization admin access. Returns the full secret once with Cache-Control: no-store; save it securely. The new key is independent: revoking the creating global key does not revoke keys it created. Missing projectId returns 400; insufficient scope returns 403; an unavailable or foreign project returns 404.
    pub async fn create(&self, params: ApiKeyCreateParams) -> Result<crate::ApiKey, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::POST, "/v1/api-keys".to_owned());
        let mut body = serde_json::Map::new();
        if let Some(value) = &params.name {
            body.insert("name".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.project_id {
            body.insert("projectId".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.scope_type {
            body.insert("scopeType".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.client.json(request).await
    }
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct ApiKeyCreateParams {
    /// A human-readable label for the new key. Defaults to Default.
    pub name: Option<String>,
    /// The active project to bind the new key to, belonging to the authenticated organization. Required when authenticating with a global API key. Dashboard users may omit it to use their default project.
    pub project_id: Option<String>,
    /// Defaults to `project`. Global API keys may only create project-scoped keys. Only organization admins using dashboard authentication may create a `global` key; projectId is ignored for those keys.
    pub scope_type: Option<ApiKeyCreateParamsScopeType>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum ApiKeyCreateParamsScopeType {
    #[serde(rename = "project")]
    Project,
    #[serde(rename = "global")]
    Global,
    #[serde(other)]
    Unknown,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ApiKey {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// The full secret on creation or rotation only. Metadata reads return a masked preview. Store a newly created secret securely; it cannot be retrieved later.
    pub key: String,
    /// The pinned project, or null for a global key, which binds to no project.
    #[serde(rename = "projectId", default)]
    pub project_id: Option<String>,
    /// `project` pins the key to one project. `global` authorizes access across the organization and has no project binding.
    #[serde(rename = "scopeType")]
    pub scope_type: ApiKeyScopeType,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub name: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum ApiKeyScopeType {
    #[serde(rename = "project")]
    Project,
    #[serde(rename = "global")]
    Global,
    #[serde(other)]
    Unknown,
}
