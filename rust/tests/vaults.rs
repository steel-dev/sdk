mod common;

#[tokio::test]
async fn create() {
    let client = common::client();
    let result = client
        .vaults()
        .create(steel_rs::VaultCreateParams {
            name: "name".to_owned(),
            description: Some("description".to_owned()),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn retrieve() {
    let client = common::client();
    let result = client
        .vaults()
        .retrieve(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::VaultRetrieveParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn update() {
    let client = common::client();
    let result = client
        .vaults()
        .update(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::VaultUpdateParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                description: Some("description".to_owned()),
                name: Some("name".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client
        .vaults()
        .list(steel_rs::VaultListParams {
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn delete() {
    let client = common::client();
    let result = client
        .vaults()
        .delete(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::VaultDeleteParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
