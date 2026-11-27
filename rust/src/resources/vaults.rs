use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct Vaults<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> Vaults<'a> {
    /// Create a Vault
    pub fn create(&self, params: VaultCreateParams) -> crate::Call<'a, crate::Vault> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request =
                crate::client::Request::new(reqwest::Method::POST, "/v1/vaults".to_owned());
            let mut body = serde_json::Map::new();
            body.insert("name".to_owned(), serde_json::to_value(&params.name)?);
            if let Some(value) = &params.description {
                body.insert("description".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.project_id {
                body.insert("projectId".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// Get a Vault
    pub fn retrieve(&self, id: &str, params: VaultRetrieveParams) -> crate::Call<'a, crate::Vault> {
        let client = self.client;
        let path = format!("/v1/vaults/{}", crate::encoding::segment(id));
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

    /// Update a Vault
    pub fn update(&self, id: &str, params: VaultUpdateParams) -> crate::Call<'a, crate::Vault> {
        let client = self.client;
        let path = format!("/v1/vaults/{}", crate::encoding::segment(id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::PATCH, path);
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            let mut body = serde_json::Map::new();
            if let Some(value) = &params.description {
                body.insert("description".to_owned(), serde_json::to_value(value)?);
            }
            if let Some(value) = &params.name {
                body.insert("name".to_owned(), serde_json::to_value(value)?);
            }
            request.body = crate::client::Body::Json(serde_json::Value::Object(body));
            client.json(request).await
        })
    }

    /// List Vaults
    pub fn list(&self, params: VaultListParams) -> crate::Call<'a, crate::VaultList> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request =
                crate::client::Request::new(reqwest::Method::GET, "/v1/vaults".to_owned());
            if let Some(value) = &params.project_id {
                request.query.extend(crate::encoding::query(
                    "projectId",
                    &serde_json::to_value(value)?,
                ));
            }
            client.json(request).await
        })
    }

    /// Soft-delete a Vault. Returns 409 while it contains any live Secrets or credentials; move or delete them first.
    pub fn delete(&self, id: &str, params: VaultDeleteParams) -> crate::Call<'a, ()> {
        let client = self.client;
        let path = format!("/v1/vaults/{}", crate::encoding::segment(id));
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
}

#[derive(Debug, Clone, PartialEq)]
pub struct VaultCreateParams {
    /// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start with a letter or digit.
    pub name: String,
    pub description: Option<String>,
    pub project_id: Option<String>,
}

impl VaultCreateParams {
    pub fn new(name: String) -> Self {
        VaultCreateParams {
            name,
            description: None,
            project_id: None,
        }
    }
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct VaultRetrieveParams {
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct VaultUpdateParams {
    pub project_id: Option<String>,
    pub description: Option<String>,
    /// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start with a letter or digit.
    pub name: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct VaultListParams {
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct VaultDeleteParams {
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct Vault {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    #[serde(default)]
    pub description: Option<String>,
    /// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start with a letter or digit.
    pub name: String,
    #[serde(rename = "projectId")]
    pub project_id: String,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct VaultList {
    pub vaults: Vec<VaultListVault>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct VaultListVault {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    #[serde(default)]
    pub description: Option<String>,
    /// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start with a letter or digit.
    pub name: String,
    #[serde(rename = "projectId")]
    pub project_id: String,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
}
