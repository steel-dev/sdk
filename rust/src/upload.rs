use bytes::Bytes;

#[derive(Debug, Clone, PartialEq, Eq)]
pub struct Upload {
    name: String,
    content: Bytes,
    content_type: Option<String>,
}

impl Upload {
    pub fn from_bytes(name: impl Into<String>, content: impl Into<Bytes>) -> Self {
        Upload {
            name: name.into(),
            content: content.into(),
            content_type: None,
        }
    }

    pub async fn open(path: impl AsRef<std::path::Path>) -> std::io::Result<Self> {
        let path = path.as_ref();
        let content = tokio::fs::read(path).await?;
        let name = path
            .file_name()
            .map(|name| name.to_string_lossy().into_owned())
            .unwrap_or_else(|| "file".to_owned());
        Ok(Upload::from_bytes(name, content))
    }

    pub fn with_content_type(mut self, content_type: impl Into<String>) -> Self {
        self.content_type = Some(content_type.into());
        self
    }

    pub fn name(&self) -> &str {
        &self.name
    }

    pub fn content(&self) -> &Bytes {
        &self.content
    }

    pub(crate) fn part(&self) -> Result<reqwest::multipart::Part, reqwest::Error> {
        let part =
            reqwest::multipart::Part::bytes(self.content.to_vec()).file_name(self.name.clone());
        match &self.content_type {
            Some(content_type) => part.mime_str(content_type),
            None => Ok(part),
        }
    }
}
