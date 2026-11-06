mod common;

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client.files().list().await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn delete() {
    let client = common::client();
    let result = client.files().delete("path").await;
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
    let bytes = client.files().download("path").await.expect("request");
    assert_eq!(&bytes[..], b"abc");
}

#[tokio::test]
async fn upload() {
    let client = common::client();
    let result = client
        .files()
        .upload(steel_rs::FileUploadParams {
            file: steel_rs::Upload::from_bytes("README.md", "Example data"),
            path: Some("path".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
