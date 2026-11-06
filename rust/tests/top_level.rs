mod common;

#[tokio::test]
async fn pdf() {
    let client = common::client();
    let result = client
        .pdf(steel_rs::PdfParams {
            url: "https://example.com".to_owned(),
            delay: Some(0.0),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            region: Some(serde_json::json!({})),
            use_proxy: Some(true),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn scrape() {
    let client = common::client();
    let result = client
        .scrape(steel_rs::ScrapeParams {
            url: "url".to_owned(),
            delay: Some(0.0),
            format: Some(vec![steel_rs::ScrapeParamsFormat::Html]),
            pdf: Some(true),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            region: Some(serde_json::json!({})),
            screenshot: Some(true),
            use_proxy: Some(true),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn screenshot() {
    let client = common::client();
    let result = client
        .screenshot(steel_rs::ScreenshotParams {
            url: "https://example.com".to_owned(),
            delay: Some(0.0),
            full_page: Some(true),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            region: Some(serde_json::json!({})),
            use_proxy: Some(true),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
