use reqwest::header::HeaderMap;

#[derive(Debug, thiserror::Error)]
pub enum Error {
    #[error("{status}: {body}")]
    Api {
        status: u16,
        body: String,
        headers: HeaderMap,
    },
    #[error(transparent)]
    Http(#[from] reqwest::Error),
    #[error("could not decode the response: {0}")]
    Decode(#[from] serde_json::Error),
    #[error("{0}")]
    Invalid(String),
}

impl Error {
    pub fn status(&self) -> Option<u16> {
        match self {
            Error::Api { status, .. } => Some(*status),
            Error::Http(error) => error.status().map(|status| status.as_u16()),
            Error::Decode(_) | Error::Invalid(_) => None,
        }
    }

    pub fn body(&self) -> Option<&str> {
        match self {
            Error::Api { body, .. } => Some(body),
            Error::Http(_) | Error::Decode(_) | Error::Invalid(_) => None,
        }
    }
}
