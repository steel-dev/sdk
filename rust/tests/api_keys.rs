mod common;

#[tokio::test]
async fn create() {
    let client = common::client();
    let result = client
        .api_keys()
        .create(steel_rs::ApiKeyCreateParams {
            name: Some("name".to_owned()),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            scope_type: Some(steel_rs::ApiKeyCreateParamsScopeType::Project),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
