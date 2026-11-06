use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct SessionsCaptchas<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> SessionsCaptchas<'a> {
    /// Solves captcha(s) for the session. If pageId, url, or taskId is provided, solves that specific captcha. If no parameters are provided, solves all detected captchas. Use this when autoCaptchaSolving is disabled in stealthConfig.
    pub async fn solve(
        &self,
        session_id: &str,
        params: SessionCaptchaSolveParams,
    ) -> Result<SessionCaptchaSolveResponse, crate::Error> {
        let mut request = crate::client::Request::new(
            reqwest::Method::POST,
            format!(
                "/v1/sessions/{}/captchas/solve",
                crate::encoding::segment(session_id)
            ),
        );
        let mut body = serde_json::Map::new();
        if let Some(value) = &params.page_id {
            body.insert("pageId".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.task_id {
            body.insert("taskId".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.url {
            body.insert("url".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.client.json(request).await
    }

    /// Solves an image captcha using XPath selectors
    pub async fn solve_image(
        &self,
        session_id: &str,
        params: SessionCaptchaSolveImageParams,
    ) -> Result<SessionCaptchaSolveImageResponse, crate::Error> {
        let mut request = crate::client::Request::new(
            reqwest::Method::POST,
            format!(
                "/v1/sessions/{}/captchas/solve-image",
                crate::encoding::segment(session_id)
            ),
        );
        let mut body = serde_json::Map::new();
        body.insert(
            "imageXPath".to_owned(),
            serde_json::to_value(&params.image_x_path)?,
        );
        body.insert(
            "inputXPath".to_owned(),
            serde_json::to_value(&params.input_x_path)?,
        );
        if let Some(value) = &params.url {
            body.insert("url".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.client.json(request).await
    }

    /// Gets the current captcha status for a session
    pub async fn status(
        &self,
        session_id: &str,
    ) -> Result<Vec<SessionCaptchaStatusResponse>, crate::Error> {
        let request = crate::client::Request::new(
            reqwest::Method::GET,
            format!(
                "/v1/sessions/{}/captchas/status",
                crate::encoding::segment(session_id)
            ),
        );
        self.client.json(request).await
    }
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct SessionCaptchaSolveParams {
    /// The page ID where the captcha is located
    pub page_id: Option<String>,
    /// The task ID of the specific captcha to solve
    pub task_id: Option<String>,
    /// The URL where the captcha is located
    pub url: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCaptchaSolveResponse {
    /// Whether the action was successful
    pub success: bool,
    /// Response message
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub message: Option<String>,
}

#[derive(Debug, Clone, PartialEq)]
pub struct SessionCaptchaSolveImageParams {
    /// XPath to the captcha image element
    pub image_x_path: String,
    /// XPath to the captcha input element
    pub input_x_path: String,
    /// URL where the captcha is located. Defaults to the current page URL
    pub url: Option<String>,
}

impl SessionCaptchaSolveImageParams {
    pub fn new(image_x_path: String, input_x_path: String) -> Self {
        SessionCaptchaSolveImageParams {
            image_x_path,
            input_x_path,
            url: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCaptchaSolveImageResponse {
    /// Whether the action was successful
    pub success: bool,
    /// Response message
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub message: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCaptchaStatusResponse {
    /// Whether a captcha is currently being solved
    #[serde(rename = "isSolvingCaptcha")]
    pub is_solving_captcha: bool,
    /// The page ID where the captcha is located
    #[serde(rename = "pageId")]
    pub page_id: String,
    /// Array of captcha tasks
    pub tasks: Vec<serde_json::Value>,
    /// The URL where the captcha is located
    pub url: String,
    /// Timestamp when the state was created
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub created: Option<f64>,
    /// Timestamp when the state was last updated
    #[serde(
        rename = "lastUpdated",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub last_updated: Option<f64>,
}
