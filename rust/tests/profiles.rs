mod common;

#[tokio::test]
async fn create() {
    let client = common::client();
    let result = client
        .profiles()
        .create(steel_rs::ProfileCreateParams {
            user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
            dimensions: Some(steel_rs::ProfileCreateParamsDimensions {
                height: 0.0,
                width: 0.0,
            }),
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            proxy_url: Some("https://example.com".to_owned()),
            use_proxy: Some("useProxy".to_owned()),
            user_agent: Some("userAgent".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn update() {
    let client = common::client();
    let result = client
        .profiles()
        .update(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::ProfileUpdateParams {
                user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                query_project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                dimensions: Some(steel_rs::ProfileUpdateParamsDimensions {
                    height: 0.0,
                    width: 0.0,
                }),
                body_project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                proxy_url: Some("https://example.com".to_owned()),
                use_proxy: Some("useProxy".to_owned()),
                user_agent: Some("userAgent".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client
        .profiles()
        .list(steel_rs::ProfileListParams {
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn get() {
    let client = common::client();
    let result = client
        .profiles()
        .get(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::ProfileGetParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
