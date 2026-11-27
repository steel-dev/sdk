use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct SecretConnections<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> SecretConnections<'a> {
    /// Create a secret connection
    pub fn create(
        &self,
        params: SecretConnectionCreateParams,
    ) -> crate::Call<'a, SecretConnectionCreateResponse> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(
                reqwest::Method::POST,
                "/v1/secret-connections".to_owned(),
            );
            let mut body = serde_json::Map::new();
            body.insert("auth".to_owned(), serde_json::to_value(&params.auth)?);
            body.insert("name".to_owned(), serde_json::to_value(&params.name)?);
            body.insert(
                "provider".to_owned(),
                serde_json::to_value(params.provider)?,
            );
            if let Some(value) = &params.config {
                body.insert("config".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.project_id {
                body.insert("projectId".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.token_budget {
                body.insert("tokenBudget".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// Get a secret connection
    pub fn retrieve(
        &self,
        id: &str,
        params: SecretConnectionRetrieveParams,
    ) -> crate::Call<'a, SecretConnectionRetrieveResponse> {
        let client = self.client;
        let path = format!("/v1/secret-connections/{}", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            client.json(request).await
        })
    }

    /// Update a secret connection
    pub fn update(
        &self,
        id: &str,
        params: SecretConnectionUpdateParams,
    ) -> crate::Call<'a, SecretConnectionUpdateResponse> {
        let client = self.client;
        let path = format!("/v1/secret-connections/{}", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::PATCH, path);
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            let mut body = serde_json::Map::new();
            if let Some(value) = &params.auth {
                body.insert("auth".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.config {
                body.insert("config".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.enabled {
                body.insert("enabled".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.name {
                body.insert("name".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.token_budget {
                body.insert("tokenBudget".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// List secret connections
    pub fn list(
        &self,
        params: SecretConnectionListParams,
    ) -> crate::Call<'a, SecretConnectionListResponse> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(
                reqwest::Method::GET,
                "/v1/secret-connections".to_owned(),
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

    /// Delete a secret connection
    pub fn delete(&self, id: &str, params: SecretConnectionDeleteParams) -> crate::Call<'a, ()> {
        let client = self.client;
        let path = format!("/v1/secret-connections/{}", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::DELETE, path);
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            client.empty(request).await
        })
    }

    /// Test a secret connection
    pub fn test(&self, id: &str, params: SecretConnectionTestParams) -> crate::Call<'a, ()> {
        let client = self.client;
        let path = format!(
            "/v1/secret-connections/{}/test",
            crate::encoding::segment(id)
        );
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::POST, path);
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            client.empty(request).await
        })
    }
}

#[derive(Debug, Clone, PartialEq)]
pub struct SecretConnectionCreateParams {
    /// Write-only provider authentication. Supply a service account token for 1Password, a token for HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
    pub auth: SecretConnectionCreateParamsAuth,
    pub name: String,
    pub provider: SecretConnectionCreateParamsProvider,
    pub config: Option<SecretConnectionCreateParamsConfig>,
    pub project_id: Option<String>,
    pub token_budget: Option<SecretConnectionCreateParamsTokenBudget>,
}

impl SecretConnectionCreateParams {
    pub fn new(
        name: String,
        provider: SecretConnectionCreateParamsProvider,
        auth: SecretConnectionCreateParamsAuth,
    ) -> Self {
        SecretConnectionCreateParams {
            name,
            provider,
            auth,
            config: None,
            project_id: None,
            token_budget: None,
        }
    }
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionCreateParamsAuth {
    /// Write-only; never returned by the API.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub token: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub mount: Option<String>,
    /// Write-only; never returned by the API.
    #[serde(rename = "roleId", default, skip_serializing_if = "Option::is_none")]
    pub role_id: Option<String>,
    /// Write-only; never returned by the API.
    #[serde(rename = "secretId", default, skip_serializing_if = "Option::is_none")]
    pub secret_id: Option<String>,
    /// Write-only; never returned by the API.
    #[serde(
        rename = "serviceAccountToken",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub service_account_token: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub r#type: Option<SecretConnectionCreateParamsAuthType>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SecretConnectionCreateParamsAuthType {
    #[serde(rename = "token")]
    Token,
    #[serde(rename = "approle")]
    Approle,
    #[serde(other)]
    Unknown,
}

impl SecretConnectionCreateParamsAuthType {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Token => "token",
            Self::Approle => "approle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SecretConnectionCreateParamsAuthType {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SecretConnectionCreateParamsProvider {
    #[serde(rename = "1password")]
    V1password,
    #[serde(rename = "hashicorp_vault")]
    HashicorpVault,
    #[serde(other)]
    Unknown,
}

impl SecretConnectionCreateParamsProvider {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::V1password => "1password",
            Self::HashicorpVault => "hashicorp_vault",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SecretConnectionCreateParamsProvider {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionCreateParamsConfig {
    pub address: String,
    #[serde(rename = "caPem", default, skip_serializing_if = "Option::is_none")]
    pub ca_pem: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub namespace: Option<String>,
}

impl SecretConnectionCreateParamsConfig {
    pub fn new(address: String) -> Self {
        SecretConnectionCreateParamsConfig {
            address,
            ca_pem: None,
            namespace: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionCreateParamsTokenBudget {
    pub burst: i64,
    /// Tokens per second, with at most three decimal places.
    #[serde(rename = "refillPerSecond")]
    pub refill_per_second: f64,
}

impl SecretConnectionCreateParamsTokenBudget {
    pub fn new(burst: i64, refill_per_second: f64) -> Self {
        SecretConnectionCreateParamsTokenBudget {
            burst,
            refill_per_second,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionCreateResponse {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    pub enabled: bool,
    pub name: String,
    pub provider: SecretConnectionCreateResponseProvider,
    #[serde(rename = "tokenBudget", default)]
    pub token_budget: Option<SecretConnectionCreateResponseTokenBudget>,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and namespace. Neither proves immutable account or server identity.
    #[serde(rename = "vaultFingerprint")]
    pub vault_fingerprint: String,
    pub version: i64,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SecretConnectionCreateResponseProvider {
    #[serde(rename = "1password")]
    V1password,
    #[serde(rename = "hashicorp_vault")]
    HashicorpVault,
    #[serde(other)]
    Unknown,
}

impl SecretConnectionCreateResponseProvider {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::V1password => "1password",
            Self::HashicorpVault => "hashicorp_vault",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SecretConnectionCreateResponseProvider {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionCreateResponseTokenBudget {
    pub burst: i64,
    /// Tokens per second, with at most three decimal places.
    #[serde(rename = "refillPerSecond")]
    pub refill_per_second: f64,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SecretConnectionRetrieveParams {
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionRetrieveResponse {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    pub enabled: bool,
    pub name: String,
    pub provider: SecretConnectionRetrieveResponseProvider,
    #[serde(rename = "tokenBudget", default)]
    pub token_budget: Option<SecretConnectionRetrieveResponseTokenBudget>,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and namespace. Neither proves immutable account or server identity.
    #[serde(rename = "vaultFingerprint")]
    pub vault_fingerprint: String,
    pub version: i64,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SecretConnectionRetrieveResponseProvider {
    #[serde(rename = "1password")]
    V1password,
    #[serde(rename = "hashicorp_vault")]
    HashicorpVault,
    #[serde(other)]
    Unknown,
}

impl SecretConnectionRetrieveResponseProvider {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::V1password => "1password",
            Self::HashicorpVault => "hashicorp_vault",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SecretConnectionRetrieveResponseProvider {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionRetrieveResponseTokenBudget {
    pub burst: i64,
    /// Tokens per second, with at most three decimal places.
    #[serde(rename = "refillPerSecond")]
    pub refill_per_second: f64,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SecretConnectionUpdateParams {
    pub project_id: Option<String>,
    /// Write-only provider authentication. Supply a service account token for 1Password, a token for HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
    pub auth: Option<SecretConnectionUpdateParamsAuth>,
    pub config: Option<SecretConnectionUpdateParamsConfig>,
    pub enabled: Option<bool>,
    pub name: Option<String>,
    pub token_budget: Option<SecretConnectionUpdateParamsTokenBudget>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionUpdateParamsAuth {
    /// Write-only; never returned by the API.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub token: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub mount: Option<String>,
    /// Write-only; never returned by the API.
    #[serde(rename = "roleId", default, skip_serializing_if = "Option::is_none")]
    pub role_id: Option<String>,
    /// Write-only; never returned by the API.
    #[serde(rename = "secretId", default, skip_serializing_if = "Option::is_none")]
    pub secret_id: Option<String>,
    /// Write-only; never returned by the API.
    #[serde(
        rename = "serviceAccountToken",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub service_account_token: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub r#type: Option<SecretConnectionUpdateParamsAuthType>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SecretConnectionUpdateParamsAuthType {
    #[serde(rename = "token")]
    Token,
    #[serde(rename = "approle")]
    Approle,
    #[serde(other)]
    Unknown,
}

impl SecretConnectionUpdateParamsAuthType {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Token => "token",
            Self::Approle => "approle",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SecretConnectionUpdateParamsAuthType {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionUpdateParamsConfig {
    pub address: String,
    #[serde(rename = "caPem", default, skip_serializing_if = "Option::is_none")]
    pub ca_pem: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub namespace: Option<String>,
}

impl SecretConnectionUpdateParamsConfig {
    pub fn new(address: String) -> Self {
        SecretConnectionUpdateParamsConfig {
            address,
            ca_pem: None,
            namespace: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionUpdateParamsTokenBudget {
    pub burst: i64,
    /// Tokens per second, with at most three decimal places.
    #[serde(rename = "refillPerSecond")]
    pub refill_per_second: f64,
}

impl SecretConnectionUpdateParamsTokenBudget {
    pub fn new(burst: i64, refill_per_second: f64) -> Self {
        SecretConnectionUpdateParamsTokenBudget {
            burst,
            refill_per_second,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionUpdateResponse {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    pub enabled: bool,
    pub name: String,
    pub provider: SecretConnectionUpdateResponseProvider,
    #[serde(rename = "tokenBudget", default)]
    pub token_budget: Option<SecretConnectionUpdateResponseTokenBudget>,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and namespace. Neither proves immutable account or server identity.
    #[serde(rename = "vaultFingerprint")]
    pub vault_fingerprint: String,
    pub version: i64,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SecretConnectionUpdateResponseProvider {
    #[serde(rename = "1password")]
    V1password,
    #[serde(rename = "hashicorp_vault")]
    HashicorpVault,
    #[serde(other)]
    Unknown,
}

impl SecretConnectionUpdateResponseProvider {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::V1password => "1password",
            Self::HashicorpVault => "hashicorp_vault",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SecretConnectionUpdateResponseProvider {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionUpdateResponseTokenBudget {
    pub burst: i64,
    /// Tokens per second, with at most three decimal places.
    #[serde(rename = "refillPerSecond")]
    pub refill_per_second: f64,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SecretConnectionListParams {
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionListResponse {
    pub connections: Vec<SecretConnectionListResponseConnection>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionListResponseConnection {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    pub enabled: bool,
    pub name: String,
    pub provider: SecretConnectionListResponseConnectionProvider,
    #[serde(rename = "tokenBudget", default)]
    pub token_budget: Option<SecretConnectionListResponseConnectionTokenBudget>,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and namespace. Neither proves immutable account or server identity.
    #[serde(rename = "vaultFingerprint")]
    pub vault_fingerprint: String,
    pub version: i64,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SecretConnectionListResponseConnectionProvider {
    #[serde(rename = "1password")]
    V1password,
    #[serde(rename = "hashicorp_vault")]
    HashicorpVault,
    #[serde(other)]
    Unknown,
}

impl SecretConnectionListResponseConnectionProvider {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::V1password => "1password",
            Self::HashicorpVault => "hashicorp_vault",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SecretConnectionListResponseConnectionProvider {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SecretConnectionListResponseConnectionTokenBudget {
    pub burst: i64,
    /// Tokens per second, with at most three decimal places.
    #[serde(rename = "refillPerSecond")]
    pub refill_per_second: f64,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SecretConnectionDeleteParams {
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SecretConnectionTestParams {
    pub project_id: Option<String>,
}
