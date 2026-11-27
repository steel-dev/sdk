use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct Files<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> Files<'a> {
    /// List all global files for the organization in descending order.
    pub fn list(&self) -> crate::Call<'a, crate::Fileslist> {
        let client = self.client;
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, "/v1/files".to_owned());
            client.json(request).await
        })
    }

    /// Delete a file from global storage
    pub fn delete(&self, path: &str) -> crate::Call<'a, ()> {
        let client = self.client;
        let path = format!("/v1/files/{}", crate::encoding::segment(path));
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::DELETE, path);
            client.empty(request).await
        })
    }

    /// Download a file from global storage
    pub fn download(&self, path: &str) -> crate::Call<'a, bytes::Bytes> {
        let client = self.client;
        let path = format!("/v1/files/{}", crate::encoding::segment(path));
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            request.accept = Some("application/octet-stream");
            client.bytes(request).await
        })
    }

    /// Uploads a file to global storage via `multipart/form-data` with a `file` field that accepts either binary data or a URL string to download from, and an optional `path` field for the file storage path.
    pub fn upload(&self, params: FileUploadParams) -> crate::Call<'a, crate::File> {
        let client = self.client;
        crate::Call::new(async move {
            let mut request =
                crate::client::Request::new(reqwest::Method::POST, "/v1/files".to_owned());
            let mut fields = Vec::new();
            fields.push(crate::encoding::FormField::File(
                "file".to_owned(),
                params.file.clone(),
            ));
            if let Some(value) = &params.path {
                fields.extend(crate::encoding::form("path", &serde_json::to_value(value)?));
            }
            request.body = crate::client::Body::Form(fields);
            client.json(request).await
        })
    }
}

#[derive(Debug, Clone, PartialEq)]
pub struct FileUploadParams {
    /// The file to upload (binary) or URL string to download from
    pub file: crate::Upload,
    /// Path to the file in the storage system
    pub path: Option<String>,
}

impl FileUploadParams {
    pub fn new(file: crate::Upload) -> Self {
        FileUploadParams { file, path: None }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct File {
    /// Timestamp when the file was created
    #[serde(rename = "lastModified")]
    pub last_modified: String,
    /// Path to the file in the storage system
    pub path: String,
    /// Size of the file in bytes
    pub size: i64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct Fileslist {
    /// Array of files for the current page
    pub data: Vec<FileslistData>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct FileslistData {
    /// Timestamp when the file was created
    #[serde(rename = "lastModified")]
    pub last_modified: String,
    /// Path to the file in the storage system
    pub path: String,
    /// Size of the file in bytes
    pub size: i64,
}
