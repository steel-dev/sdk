use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct Extensions<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> Extensions<'a> {
    /// Update a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization
    pub fn update(
        &self,
        extension_id: &str,
        params: ExtensionUpdateParams,
    ) -> crate::Call<'a, ExtensionUpdateResponse> {
        let client = self.client;
        let path = format!("/v1/extensions/{}", crate::encoding::segment(extension_id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::PUT, path);
            let mut fields = Vec::new();
            if let Some(value) = &params.file {
                fields.push(crate::encoding::FormField::File(
                    "file".to_owned(),
                    value.clone(),
                ));
            }
            if let Some(value) = &params.url {
                fields.extend(crate::encoding::form("url", &serde_json::to_value(value)?));
            }
            request.body = crate::client::Body::Form(fields);
            client.json(request).await
        })
    }

    /// List all extensions for the organization
    pub fn list(&self) -> crate::Call<'a, ExtensionListResponse> {
        let client = self.client;
        crate::Call::new(async move {
            let request =
                crate::client::Request::new(reqwest::Method::GET, "/v1/extensions".to_owned());
            client.json(request).await
        })
    }

    /// Delete an extension by ID
    pub fn delete(&self, extension_id: &str) -> crate::Call<'a, ExtensionDeleteResponse> {
        let client = self.client;
        let path = format!("/v1/extensions/{}", crate::encoding::segment(extension_id));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::DELETE, path);
            client.json(request).await
        })
    }

    /// Delete all extensions for the organization
    pub fn delete_all(&self) -> crate::Call<'a, ExtensionDeleteAllResponse> {
        let client = self.client;
        crate::Call::new(async move {
            let request =
                crate::client::Request::new(reqwest::Method::DELETE, "/v1/extensions".to_owned());
            client.json(request).await
        })
    }

    /// Download an extension file by extension ID
    pub fn download(&self, extension_id: &str) -> crate::Call<'a, bytes::Bytes> {
        let client = self.client;
        let path = format!("/v1/extensions/{}", crate::encoding::segment(extension_id));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            request.accept = Some("application/zip");
            client.bytes(request).await
        })
    }

    /// Upload a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization
    pub fn upload(
        &self,
        params: ExtensionUploadParams,
    ) -> crate::Call<'a, ExtensionUploadResponse> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request =
                crate::client::Request::new(reqwest::Method::POST, "/v1/extensions".to_owned());
            let mut fields = Vec::new();
            if let Some(value) = &params.file {
                fields.push(crate::encoding::FormField::File(
                    "file".to_owned(),
                    value.clone(),
                ));
            }
            if let Some(value) = &params.url {
                fields.extend(crate::encoding::form("url", &serde_json::to_value(value)?));
            }
            request.body = crate::client::Body::Form(fields);
            client.json(request).await
        })
    }
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct ExtensionUpdateParams {
    /// Extension .zip/.crx file
    pub file: Option<crate::Upload>,
    /// Extension URL
    pub url: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ExtensionUpdateResponse {
    /// Unique extension identifier (e.g., ext_12345)
    pub id: String,
    /// Creation timestamp
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Extension name
    pub name: String,
    /// Last update timestamp
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ExtensionListResponse {
    /// Total number of extensions
    pub count: f64,
    /// List of extensions for the organization
    pub extensions: Vec<ExtensionListResponseExtension>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ExtensionListResponseExtension {
    /// Unique extension identifier (e.g., ext_12345)
    pub id: String,
    /// Creation timestamp
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Extension name
    pub name: String,
    /// Last update timestamp
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ExtensionDeleteResponse {
    pub message: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ExtensionDeleteAllResponse {
    pub message: String,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct ExtensionUploadParams {
    /// Extension .zip/.crx file
    pub file: Option<crate::Upload>,
    /// Extension URL
    pub url: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ExtensionUploadResponse {
    /// Unique extension identifier (e.g., ext_12345)
    pub id: String,
    /// Creation timestamp
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// Extension name
    pub name: String,
    /// Last update timestamp
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
}
