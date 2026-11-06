mod common;

#[tokio::test]
async fn update() {
    let client = common::client();
    let result = client
        .extensions()
        .update(
            "extensionId",
            steel_rs::ExtensionUpdateParams {
                file: Some(steel_rs::Upload::from_bytes("README.md", "Example data")),
                url: Some("https://example.com".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client.extensions().list().await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn delete() {
    let client = common::client();
    let result = client.extensions().delete("extensionId").await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn delete_all() {
    let client = common::client();
    let result = client.extensions().delete_all().await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn download() {
    let server = wiremock::MockServer::start().await;
    wiremock::Mock::given(wiremock::matchers::any())
        .respond_with(wiremock::ResponseTemplate::new(200).set_body_bytes(b"abc".to_vec()))
        .mount(&server)
        .await;
    let client = common::client_for(&server.uri());
    let bytes = client
        .extensions()
        .download("extensionId")
        .await
        .expect("request");
    assert_eq!(&bytes[..], b"abc");
}

#[tokio::test]
async fn upload() {
    let client = common::client();
    let result = client
        .extensions()
        .upload(steel_rs::ExtensionUploadParams {
            file: Some(steel_rs::Upload::from_bytes("README.md", "Example data")),
            url: Some("https://example.com".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
