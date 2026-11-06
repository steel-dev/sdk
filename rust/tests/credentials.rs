mod common;

#[tokio::test]
async fn create() {
    let client = common::client();
    let result = client
        .credentials()
        .create(steel_rs::CredentialCreateParams {
            value: std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]),
            label: Some("label".to_owned()),
            namespace: Some("namespace".to_owned()),
            origin: Some("origin".to_owned()),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn update() {
    let client = common::client();
    let result = client
        .credentials()
        .update(steel_rs::CredentialUpdateParams {
            label: Some("label".to_owned()),
            namespace: Some("namespace".to_owned()),
            origin: Some("origin".to_owned()),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            value: Some(std::collections::HashMap::from([(
                "foo".to_owned(),
                "string".to_owned(),
            )])),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client
        .credentials()
        .list(steel_rs::CredentialListParams {
            namespace: Some("namespace".to_owned()),
            origin: Some("origin".to_owned()),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn delete() {
    let client = common::client();
    let result = client
        .credentials()
        .delete(steel_rs::CredentialDeleteParams {
            origin: "origin".to_owned(),
            namespace: Some("namespace".to_owned()),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
