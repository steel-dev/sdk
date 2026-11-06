use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct Credentials<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> Credentials<'a> {
    /// Encrypts and stores credentials for an origin
    pub async fn create(
        &self,
        params: CredentialCreateParams,
    ) -> Result<CredentialCreateResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::POST, "/v1/credentials".to_owned());
        let mut body = serde_json::Map::new();
        body.insert("value".to_owned(), serde_json::to_value(&params.value)?);
        if let Some(value) = &params.label {
            body.insert("label".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.namespace {
            body.insert("namespace".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.origin {
            body.insert("origin".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.project_id {
            body.insert("projectId".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.client.json(request).await
    }

    /// Encrypts and updates credentials for an origin
    pub async fn update(
        &self,
        params: CredentialUpdateParams,
    ) -> Result<CredentialUpdateResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::PUT, "/v1/credentials".to_owned());
        let mut body = serde_json::Map::new();
        if let Some(value) = &params.label {
            body.insert("label".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.namespace {
            body.insert("namespace".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.origin {
            body.insert("origin".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.project_id {
            body.insert("projectId".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.value {
            body.insert("value".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.client.json(request).await
    }

    /// Fetches all credential metadata for the current organization.
    pub async fn list(
        &self,
        params: CredentialListParams,
    ) -> Result<CredentialListResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::GET, "/v1/credentials".to_owned());
        if let Some(value) = &params.namespace {
            request.query.extend(crate::encoding::query(
                "namespace",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.origin {
            request.query.extend(crate::encoding::query(
                "origin",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.project_id {
            request.query.extend(crate::encoding::query(
                "projectId",
                &serde_json::to_value(value)?,
            ));
        }
        self.client.json(request).await
    }

    /// Deletes encrypted credentials from the database
    pub async fn delete(
        &self,
        params: CredentialDeleteParams,
    ) -> Result<CredentialDeleteResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::DELETE, "/v1/credentials".to_owned());
        let mut body = serde_json::Map::new();
        body.insert("origin".to_owned(), serde_json::to_value(&params.origin)?);
        if let Some(value) = &params.namespace {
            body.insert("namespace".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.project_id {
            body.insert("projectId".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.client.json(request).await
    }
}

#[derive(Debug, Clone, PartialEq)]
pub struct CredentialCreateParams {
    /// Value for the credential
    pub value: std::collections::HashMap<String, String>,
    /// Label for the credential
    pub label: Option<String>,
    /// The namespace the credential is stored against. Defaults to "default".
    pub namespace: Option<String>,
    /// Website origin the credential is for
    pub origin: Option<String>,
    /// Project to store the credential in.
    pub project_id: Option<String>,
}

impl CredentialCreateParams {
    pub fn new(value: std::collections::HashMap<String, String>) -> Self {
        CredentialCreateParams {
            value,
            label: None,
            namespace: None,
            origin: None,
            project_id: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CredentialCreateResponse {
    /// Date and time the credential was created
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Date and time the credential was last updated
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// Label for the credential
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub label: Option<String>,
    /// The namespace the credential is stored against. Defaults to "default".
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub namespace: Option<String>,
    /// Website origin the credential is for
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub origin: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct CredentialUpdateParams {
    /// Label for the credential
    pub label: Option<String>,
    /// The namespace the credential is stored against. Defaults to "default".
    pub namespace: Option<String>,
    /// Website origin the credential is for
    pub origin: Option<String>,
    /// Project to update the credential in.
    pub project_id: Option<String>,
    /// Value for the credential
    pub value: Option<std::collections::HashMap<String, String>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CredentialUpdateResponse {
    /// Date and time the credential was created
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Date and time the credential was last updated
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// Label for the credential
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub label: Option<String>,
    /// The namespace the credential is stored against. Defaults to "default".
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub namespace: Option<String>,
    /// Website origin the credential is for
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub origin: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct CredentialListParams {
    /// namespace credential is stored against
    pub namespace: Option<String>,
    /// website origin the credential is for
    pub origin: Option<String>,
    /// Project to query credentials from.
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CredentialListResponse {
    pub credentials: Vec<CredentialListResponseCredential>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CredentialListResponseCredential {
    /// Date and time the credential was created
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Date and time the credential was last updated
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// Label for the credential
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub label: Option<String>,
    /// The namespace the credential is stored against. Defaults to "default".
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub namespace: Option<String>,
    /// Website origin the credential is for
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub origin: Option<String>,
}

#[derive(Debug, Clone, PartialEq)]
pub struct CredentialDeleteParams {
    /// Website origin the credential is for
    pub origin: String,
    /// The namespace the credential is stored against. Defaults to "default".
    pub namespace: Option<String>,
    /// Project to delete the credential from.
    pub project_id: Option<String>,
}

impl CredentialDeleteParams {
    pub fn new(origin: String) -> Self {
        CredentialDeleteParams {
            origin,
            namespace: None,
            project_id: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct CredentialDeleteResponse {
    pub success: bool,
}
