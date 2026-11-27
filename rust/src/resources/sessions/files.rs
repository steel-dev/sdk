#[derive(Debug, Clone, Copy)]
pub struct SessionsFiles<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> SessionsFiles<'a> {
    /// List all files from the session in descending order.
    pub fn list(&self, session_id: &str) -> crate::Call<'a, crate::Fileslist> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/files",
            crate::encoding::segment(session_id)
        );
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, path);
            client.json(request).await
        })
    }

    /// Delete a file from a session
    pub fn delete(&self, session_id: &str, path: &str) -> crate::Call<'a, ()> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/files/{}",
            crate::encoding::segment(session_id),
            crate::encoding::segment(path)
        );
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::DELETE, path);
            client.empty(request).await
        })
    }

    /// Delete all files from a session
    pub fn delete_all(&self, session_id: &str) -> crate::Call<'a, ()> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/files",
            crate::encoding::segment(session_id)
        );
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::DELETE, path);
            client.empty(request).await
        })
    }

    /// Download a file from a session
    pub fn download(&self, session_id: &str, path: &str) -> crate::Call<'a, bytes::Bytes> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/files/{}",
            crate::encoding::segment(session_id),
            crate::encoding::segment(path)
        );
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            request.accept = Some("application/octet-stream");
            client.bytes(request).await
        })
    }

    /// Download all files from the session as a zip archive.
    pub fn download_archive(&self, session_id: &str) -> crate::Call<'a, bytes::Bytes> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/files.zip",
            crate::encoding::segment(session_id)
        );
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::GET, path);
            request.accept = Some("application/zip");
            client.bytes(request).await
        })
    }

    /// Uploads a file to a session via `multipart/form-data` with a `file` field that accepts either binary data or a URL string to download from, and an optional `path` field for the file storage path.
    pub fn upload(
        &self,
        session_id: &str,
        params: SessionFileUploadParams,
    ) -> crate::Call<'a, crate::File> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/files",
            crate::encoding::segment(session_id)
        );
        crate::Call::new(async move {
            let mut request = crate::client::Request::new(reqwest::Method::POST, path);
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
pub struct SessionFileUploadParams {
    /// The file to upload (binary) or URL string to download from
    pub file: crate::Upload,
    /// Path to the file in the storage system
    pub path: Option<String>,
}

impl SessionFileUploadParams {
    pub fn new(file: crate::Upload) -> Self {
        SessionFileUploadParams { file, path: None }
    }
}
