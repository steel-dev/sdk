mod common;

#[tokio::test]
async fn create() {
    let client = common::client();
    let result = client
        .secret_connections()
        .create(steel_rs::SecretConnectionCreateParams {
            auth: steel_rs::SecretConnectionCreateParamsAuth {
                token: Some("x".to_owned()),
                mount: Some("x".to_owned()),
                role_id: Some("x".to_owned()),
                secret_id: Some("x".to_owned()),
                service_account_token: Some("x".to_owned()),
                r#type: Some(steel_rs::SecretConnectionCreateParamsAuthType::Token),
            },
            name: "x".to_owned(),
            provider: steel_rs::SecretConnectionCreateParamsProvider::V1password,
            config: Some(steel_rs::SecretConnectionCreateParamsConfig {
                address: "x".to_owned(),
                ca_pem: Some("x".to_owned()),
                namespace: Some("x".to_owned()),
            }),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            token_budget: Some(steel_rs::SecretConnectionCreateParamsTokenBudget {
                burst: 2,
                refill_per_second: 0.001,
            }),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn retrieve() {
    let client = common::client();
    let result = client
        .secret_connections()
        .retrieve(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SecretConnectionRetrieveParams {
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
        .secret_connections()
        .update(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SecretConnectionUpdateParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                auth: Some(steel_rs::SecretConnectionUpdateParamsAuth {
                    token: Some("x".to_owned()),
                    mount: Some("x".to_owned()),
                    role_id: Some("x".to_owned()),
                    secret_id: Some("x".to_owned()),
                    service_account_token: Some("x".to_owned()),
                    r#type: Some(steel_rs::SecretConnectionUpdateParamsAuthType::Token),
                }),
                config: Some(steel_rs::SecretConnectionUpdateParamsConfig {
                    address: "x".to_owned(),
                    ca_pem: Some("x".to_owned()),
                    namespace: Some("x".to_owned()),
                }),
                enabled: Some(true),
                name: Some("x".to_owned()),
                token_budget: Some(steel_rs::SecretConnectionUpdateParamsTokenBudget {
                    burst: 2,
                    refill_per_second: 0.001,
                }),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client
        .secret_connections()
        .list(steel_rs::SecretConnectionListParams {
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn delete() {
    let client = common::client();
    let result = client
        .secret_connections()
        .delete(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SecretConnectionDeleteParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn test() {
    let client = common::client();
    let result = client
        .secret_connections()
        .test(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SecretConnectionTestParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
