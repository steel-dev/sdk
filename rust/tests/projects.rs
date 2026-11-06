mod common;

#[tokio::test]
async fn retrieve() {
    let client = common::client();
    let result = client
        .projects()
        .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client.projects().list().await;
    assert!(result.is_ok(), "{:?}", result.err());
}
