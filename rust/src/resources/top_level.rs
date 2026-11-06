use serde::{Deserialize, Serialize};

impl crate::Client {
    pub fn api_keys(&self) -> crate::ApiKeys<'_> {
        crate::ApiKeys { client: self }
    }

    pub fn credentials(&self) -> crate::Credentials<'_> {
        crate::Credentials { client: self }
    }

    pub fn extensions(&self) -> crate::Extensions<'_> {
        crate::Extensions { client: self }
    }

    pub fn files(&self) -> crate::Files<'_> {
        crate::Files { client: self }
    }

    pub fn profiles(&self) -> crate::Profiles<'_> {
        crate::Profiles { client: self }
    }

    pub fn projects(&self) -> crate::Projects<'_> {
        crate::Projects { client: self }
    }

    pub fn sessions(&self) -> crate::Sessions<'_> {
        crate::Sessions { client: self }
    }

    /// Generates a PDF from a specified webpage.
    pub async fn pdf(&self, params: PdfParams) -> Result<PdfResponse, crate::Error> {
        let mut request = crate::client::Request::new(reqwest::Method::POST, "/v1/pdf".to_owned());
        let mut body = serde_json::Map::new();
        body.insert("url".to_owned(), serde_json::to_value(&params.url)?);
        if let Some(value) = &params.delay {
            body.insert("delay".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.project_id {
            body.insert("projectId".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.region {
            body.insert("region".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.use_proxy {
            body.insert("useProxy".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.json(request).await
    }

    /// Extracts content from a specified URL.
    pub async fn scrape(&self, params: ScrapeParams) -> Result<ScrapeResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::POST, "/v1/scrape".to_owned());
        let mut body = serde_json::Map::new();
        body.insert("url".to_owned(), serde_json::to_value(&params.url)?);
        if let Some(value) = &params.delay {
            body.insert("delay".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.format {
            body.insert("format".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.pdf {
            body.insert("pdf".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.project_id {
            body.insert("projectId".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.region {
            body.insert("region".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.screenshot {
            body.insert("screenshot".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.use_proxy {
            body.insert("useProxy".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.json(request).await
    }

    /// Captures a screenshot of a specified webpage.
    pub async fn screenshot(
        &self,
        params: ScreenshotParams,
    ) -> Result<ScreenshotResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::POST, "/v1/screenshot".to_owned());
        let mut body = serde_json::Map::new();
        body.insert("url".to_owned(), serde_json::to_value(&params.url)?);
        if let Some(value) = &params.delay {
            body.insert("delay".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.full_page {
            body.insert("fullPage".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.project_id {
            body.insert("projectId".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.region {
            body.insert("region".to_owned(), serde_json::to_value(value)?);
        }
        if let Some(value) = &params.use_proxy {
            body.insert("useProxy".to_owned(), serde_json::to_value(value)?);
        }
        request.body = crate::client::Body::Json(serde_json::Value::Object(body));
        self.json(request).await
    }
}

#[derive(Debug, Clone, PartialEq)]
pub struct PdfParams {
    /// URL of the webpage to convert to PDF
    pub url: String,
    /// Delay before generating the PDF (in milliseconds)
    pub delay: Option<f64>,
    /// Project to execute the PDF generation in.
    pub project_id: Option<String>,
    /// The desired region for the action to be performed in
    pub region: Option<serde_json::Value>,
    /// Use a Steel-provided residential proxy for generating the PDF
    pub use_proxy: Option<bool>,
}

impl PdfParams {
    pub fn new(url: String) -> Self {
        PdfParams {
            url,
            delay: None,
            project_id: None,
            region: None,
            use_proxy: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct PdfResponse {
    /// URL where the PDF is hosted
    pub url: String,
}

#[derive(Debug, Clone, PartialEq)]
pub struct ScrapeParams {
    /// URL of the webpage to scrape. A URL without a scheme is fetched over HTTPS.
    pub url: String,
    /// Delay before scraping (in milliseconds)
    pub delay: Option<f64>,
    /// Desired format(s) for the scraped content. Default is `html`.
    pub format: Option<Vec<ScrapeParamsFormat>>,
    /// Include a PDF in the response
    pub pdf: Option<bool>,
    /// Project to execute the scrape in.
    pub project_id: Option<String>,
    /// The desired region for the action to be performed in
    pub region: Option<serde_json::Value>,
    /// Include a screenshot in the response
    pub screenshot: Option<bool>,
    /// Use a Steel-provided residential proxy for the scrape
    pub use_proxy: Option<bool>,
}

impl ScrapeParams {
    pub fn new(url: String) -> Self {
        ScrapeParams {
            url,
            delay: None,
            format: None,
            pdf: None,
            project_id: None,
            region: None,
            screenshot: None,
            use_proxy: None,
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum ScrapeParamsFormat {
    #[serde(rename = "html")]
    Html,
    #[serde(rename = "readability")]
    Readability,
    #[serde(rename = "cleaned_html")]
    CleanedHtml,
    #[serde(rename = "markdown")]
    Markdown,
    #[serde(other)]
    Unknown,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ScrapeResponse {
    pub content: ScrapeResponseContent,
    pub links: Vec<ScrapeResponseLink>,
    pub metadata: ScrapeResponseMetadata,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub pdf: Option<ScrapeResponsePdf>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub screenshot: Option<ScrapeResponseScreenshot>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ScrapeResponseContent {
    /// Cleaned HTML content of the webpage
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub cleaned_html: Option<String>,
    /// Raw HTML content of the webpage
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub html: Option<String>,
    /// Webpage content converted to Markdown
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub markdown: Option<String>,
    /// Webpage content in Readability format
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub readability: Option<std::collections::HashMap<String, serde_json::Value>>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ScrapeResponseLink {
    /// Text content of the link
    pub text: String,
    /// URL of the link
    pub url: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ScrapeResponseMetadata {
    /// HTTP status code of the response
    #[serde(rename = "statusCode")]
    pub status_code: i64,
    /// Author of the article content
    #[serde(
        rename = "articleAuthor",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub article_author: Option<String>,
    /// Author of the webpage content
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub author: Option<String>,
    /// Canonical URL of the webpage
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub canonical: Option<String>,
    /// Description of the webpage
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub description: Option<String>,
    /// Favicon URL of the website
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub favicon: Option<String>,
    /// JSON-LD structured data from the webpage
    #[serde(rename = "jsonLd", default, skip_serializing_if = "Option::is_none")]
    pub json_ld: Option<serde_json::Value>,
    /// Keywords associated with the webpage
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub keywords: Option<String>,
    /// Detected language of the webpage
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub language: Option<String>,
    /// Last modification time of the content
    #[serde(
        rename = "modifiedTime",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub modified_time: Option<String>,
    /// Open Graph description
    #[serde(
        rename = "ogDescription",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub og_description: Option<String>,
    /// Open Graph image URL
    #[serde(rename = "ogImage", default, skip_serializing_if = "Option::is_none")]
    pub og_image: Option<String>,
    /// Open Graph site name
    #[serde(
        rename = "ogSiteName",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub og_site_name: Option<String>,
    /// Open Graph title
    #[serde(rename = "ogTitle", default, skip_serializing_if = "Option::is_none")]
    pub og_title: Option<String>,
    /// Open Graph URL
    #[serde(rename = "ogUrl", default, skip_serializing_if = "Option::is_none")]
    pub og_url: Option<String>,
    /// Publication time of the content
    #[serde(
        rename = "publishedTime",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub published_time: Option<String>,
    /// Timestamp when the scrape was performed
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub timestamp: Option<String>,
    /// Title of the webpage
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub title: Option<String>,
    /// Source URL of the scraped page
    #[serde(rename = "urlSource", default, skip_serializing_if = "Option::is_none")]
    pub url_source: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ScrapeResponsePdf {
    /// URL of the generated PDF
    pub url: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ScrapeResponseScreenshot {
    /// URL of the screenshot image
    pub url: String,
}

#[derive(Debug, Clone, PartialEq)]
pub struct ScreenshotParams {
    /// URL of the webpage to capture
    pub url: String,
    /// Delay before capturing the screenshot (in milliseconds)
    pub delay: Option<f64>,
    /// Capture the full page screenshot. Default is `false`.
    pub full_page: Option<bool>,
    /// Project to execute the screenshot in.
    pub project_id: Option<String>,
    /// The desired region for the action to be performed in
    pub region: Option<serde_json::Value>,
    /// Use a Steel-provided residential proxy for capturing the screenshot
    pub use_proxy: Option<bool>,
}

impl ScreenshotParams {
    pub fn new(url: String) -> Self {
        ScreenshotParams {
            url,
            delay: None,
            full_page: None,
            project_id: None,
            region: None,
            use_proxy: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ScreenshotResponse {
    /// URL where the screenshot is hosted
    pub url: String,
}
