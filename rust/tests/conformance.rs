#[path = "conformance/harness.rs"]
mod harness;

use futures_util::StreamExt;

#[tokio::test]
async fn pdf_full() {
    harness::conform("pdf/full", |client| async move {
        let value = client
            .pdf(steel_rs::PdfParams {
                url: "https://example.com".to_owned(),
                delay: Some(0.0),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                region: Some(serde_json::json!({})),
                use_proxy: Some(true),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn pdf_required() {
    harness::conform("pdf/required", |client| async move {
        let value = client
            .pdf(steel_rs::PdfParams {
                url: "https://example.com".to_owned(),
                delay: None,
                project_id: None,
                region: None,
                use_proxy: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn pdf_nulls() {
    harness::conform("pdf/nulls", |client| async move {
        let value = client
            .pdf(steel_rs::PdfParams {
                url: "https://example.com".to_owned(),
                delay: None,
                project_id: None,
                region: None,
                use_proxy: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn scrape_full() {
    harness::conform("scrape/full", |client| async move {
        let value = client
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
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn scrape_required() {
    harness::conform("scrape/required", |client| async move {
        let value = client
            .scrape(steel_rs::ScrapeParams {
                url: "url".to_owned(),
                delay: None,
                format: None,
                pdf: None,
                project_id: None,
                region: None,
                screenshot: None,
                use_proxy: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn scrape_nulls() {
    harness::conform("scrape/nulls", |client| async move {
        let value = client
            .scrape(steel_rs::ScrapeParams {
                url: "url".to_owned(),
                delay: None,
                format: None,
                pdf: None,
                project_id: None,
                region: None,
                screenshot: None,
                use_proxy: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn screenshot_full() {
    harness::conform("screenshot/full", |client| async move {
        let value = client
            .screenshot(steel_rs::ScreenshotParams {
                url: "https://example.com".to_owned(),
                delay: Some(0.0),
                full_page: Some(true),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                region: Some(serde_json::json!({})),
                use_proxy: Some(true),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn screenshot_required() {
    harness::conform("screenshot/required", |client| async move {
        let value = client
            .screenshot(steel_rs::ScreenshotParams {
                url: "https://example.com".to_owned(),
                delay: None,
                full_page: None,
                project_id: None,
                region: None,
                use_proxy: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn screenshot_nulls() {
    harness::conform("screenshot/nulls", |client| async move {
        let value = client
            .screenshot(steel_rs::ScreenshotParams {
                url: "https://example.com".to_owned(),
                delay: None,
                full_page: None,
                project_id: None,
                region: None,
                use_proxy: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn api_keys_create_full() {
    harness::conform("api_keys.create/full", |client| async move {
        let value = client
            .api_keys()
            .create(steel_rs::ApiKeyCreateParams {
                name: Some("name".to_owned()),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                scope_type: Some(steel_rs::ApiKeyCreateParamsScopeType::Project),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn api_keys_create_required() {
    harness::conform("api_keys.create/required", |client| async move {
        let value = client
            .api_keys()
            .create(steel_rs::ApiKeyCreateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn api_keys_create_nulls() {
    harness::conform("api_keys.create/nulls", |client| async move {
        let value = client
            .api_keys()
            .create(steel_rs::ApiKeyCreateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn api_keys_create_unknown() {
    harness::conform("api_keys.create/unknown", |client| async move {
        let value = client
            .api_keys()
            .create(steel_rs::ApiKeyCreateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_create_full() {
    harness::conform("credentials.create/full", |client| async move {
        let value = client
            .credentials()
            .create(steel_rs::CredentialCreateParams {
                value: std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]),
                additional_origins: Some(vec!["x".to_owned()]),
                label: Some("label".to_owned()),
                namespace: Some("namespace".to_owned()),
                origin: Some("origin".to_owned()),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_create_required() {
    harness::conform("credentials.create/required", |client| async move {
        let value = client
            .credentials()
            .create(steel_rs::CredentialCreateParams {
                value: std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]),
                additional_origins: None,
                label: None,
                namespace: None,
                origin: None,
                project_id: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_create_nulls() {
    harness::conform("credentials.create/nulls", |client| async move {
        let value = client
            .credentials()
            .create(steel_rs::CredentialCreateParams {
                value: std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]),
                additional_origins: None,
                label: None,
                namespace: None,
                origin: None,
                project_id: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_update_full() {
    harness::conform("credentials.update/full", |client| async move {
        let value = client
            .credentials()
            .update(steel_rs::CredentialUpdateParams {
                additional_origins: Some(vec!["x".to_owned()]),
                label: Some("label".to_owned()),
                namespace: Some("namespace".to_owned()),
                origin: Some("origin".to_owned()),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                value: Some(std::collections::HashMap::from([(
                    "foo".to_owned(),
                    "string".to_owned(),
                )])),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_update_required() {
    harness::conform("credentials.update/required", |client| async move {
        let value = client
            .credentials()
            .update(steel_rs::CredentialUpdateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_update_nulls() {
    harness::conform("credentials.update/nulls", |client| async move {
        let value = client
            .credentials()
            .update(steel_rs::CredentialUpdateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_list_full() {
    harness::conform("credentials.list/full", |client| async move {
        let value = client
            .credentials()
            .list(steel_rs::CredentialListParams {
                namespace: Some("namespace".to_owned()),
                origin: Some("origin".to_owned()),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_list_required() {
    harness::conform("credentials.list/required", |client| async move {
        let value = client
            .credentials()
            .list(steel_rs::CredentialListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_list_nulls() {
    harness::conform("credentials.list/nulls", |client| async move {
        let value = client
            .credentials()
            .list(steel_rs::CredentialListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_list_escaped() {
    harness::conform("credentials.list/escaped", |client| async move {
        let value = client
            .credentials()
            .list(steel_rs::CredentialListParams {
                namespace: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                origin: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_delete_full() {
    harness::conform("credentials.delete/full", |client| async move {
        let value = client
            .credentials()
            .delete(steel_rs::CredentialDeleteParams {
                origin: "origin".to_owned(),
                namespace: Some("namespace".to_owned()),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_delete_required() {
    harness::conform("credentials.delete/required", |client| async move {
        let value = client
            .credentials()
            .delete(steel_rs::CredentialDeleteParams {
                origin: "origin".to_owned(),
                namespace: None,
                project_id: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn credentials_delete_nulls() {
    harness::conform("credentials.delete/nulls", |client| async move {
        let value = client
            .credentials()
            .delete(steel_rs::CredentialDeleteParams {
                origin: "origin".to_owned(),
                namespace: None,
                project_id: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_update_full() {
    harness::conform("extensions.update/full", |client| async move {
        let value = client
            .extensions()
            .update(
                "extensionId",
                steel_rs::ExtensionUpdateParams {
                    file: Some(steel_rs::Upload::from_bytes("README.md", "Example data")),
                    url: Some("https://example.com".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_update_required() {
    harness::conform("extensions.update/required", |client| async move {
        let value = client
            .extensions()
            .update(
                "extensionId",
                steel_rs::ExtensionUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_update_nulls() {
    harness::conform("extensions.update/nulls", |client| async move {
        let value = client
            .extensions()
            .update(
                "extensionId",
                steel_rs::ExtensionUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_update_escaped() {
    harness::conform("extensions.update/escaped", |client| async move {
        let value = client
            .extensions()
            .update(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::ExtensionUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_list_full() {
    harness::conform("extensions.list/full", |client| async move {
        let value = client.extensions().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_list_required() {
    harness::conform("extensions.list/required", |client| async move {
        let value = client.extensions().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_list_nulls() {
    harness::conform("extensions.list/nulls", |client| async move {
        let value = client.extensions().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_delete_full() {
    harness::conform("extensions.delete/full", |client| async move {
        let value = client
            .extensions()
            .delete("extensionId")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_delete_required() {
    harness::conform("extensions.delete/required", |client| async move {
        let value = client
            .extensions()
            .delete("extensionId")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_delete_nulls() {
    harness::conform("extensions.delete/nulls", |client| async move {
        let value = client
            .extensions()
            .delete("extensionId")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_delete_escaped() {
    harness::conform("extensions.delete/escaped", |client| async move {
        let value = client
            .extensions()
            .delete("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_delete_all_full() {
    harness::conform("extensions.delete_all/full", |client| async move {
        let value = client.extensions().delete_all().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_delete_all_required() {
    harness::conform("extensions.delete_all/required", |client| async move {
        let value = client.extensions().delete_all().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_delete_all_nulls() {
    harness::conform("extensions.delete_all/nulls", |client| async move {
        let value = client.extensions().delete_all().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_download_full() {
    harness::conform("extensions.download/full", |client| async move {
        let bytes = client
            .extensions()
            .download("extensionId")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn extensions_download_required() {
    harness::conform("extensions.download/required", |client| async move {
        let bytes = client
            .extensions()
            .download("extensionId")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn extensions_download_escaped() {
    harness::conform("extensions.download/escaped", |client| async move {
        let bytes = client
            .extensions()
            .download("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn extensions_upload_full() {
    harness::conform("extensions.upload/full", |client| async move {
        let value = client
            .extensions()
            .upload(steel_rs::ExtensionUploadParams {
                file: Some(steel_rs::Upload::from_bytes("README.md", "Example data")),
                url: Some("https://example.com".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_upload_required() {
    harness::conform("extensions.upload/required", |client| async move {
        let value = client
            .extensions()
            .upload(steel_rs::ExtensionUploadParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn extensions_upload_nulls() {
    harness::conform("extensions.upload/nulls", |client| async move {
        let value = client
            .extensions()
            .upload(steel_rs::ExtensionUploadParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn files_list_full() {
    harness::conform("files.list/full", |client| async move {
        let value = client.files().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn files_list_required() {
    harness::conform("files.list/required", |client| async move {
        let value = client.files().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn files_list_nulls() {
    harness::conform("files.list/nulls", |client| async move {
        let value = client.files().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn files_delete_full() {
    harness::conform("files.delete/full", |client| async move {
        client.files().delete("path").await.expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn files_delete_required() {
    harness::conform("files.delete/required", |client| async move {
        client.files().delete("path").await.expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn files_delete_escaped() {
    harness::conform("files.delete/escaped", |client| async move {
        client
            .files()
            .delete("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn files_download_full() {
    harness::conform("files.download/full", |client| async move {
        let bytes = client.files().download("path").await.expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn files_download_required() {
    harness::conform("files.download/required", |client| async move {
        let bytes = client.files().download("path").await.expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn files_download_escaped() {
    harness::conform("files.download/escaped", |client| async move {
        let bytes = client
            .files()
            .download("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn files_upload_full() {
    harness::conform("files.upload/full", |client| async move {
        let value = client
            .files()
            .upload(steel_rs::FileUploadParams {
                file: steel_rs::Upload::from_bytes("README.md", "Example data"),
                path: Some("path".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn files_upload_required() {
    harness::conform("files.upload/required", |client| async move {
        let value = client
            .files()
            .upload(steel_rs::FileUploadParams {
                file: steel_rs::Upload::from_bytes("README.md", "Example data"),
                path: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn files_upload_nulls() {
    harness::conform("files.upload/nulls", |client| async move {
        let value = client
            .files()
            .upload(steel_rs::FileUploadParams {
                file: steel_rs::Upload::from_bytes("README.md", "Example data"),
                path: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_create_full() {
    harness::conform("profiles.create/full", |client| async move {
        let value = client
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
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_create_required() {
    harness::conform("profiles.create/required", |client| async move {
        let value = client
            .profiles()
            .create(steel_rs::ProfileCreateParams {
                user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                dimensions: None,
                project_id: None,
                proxy_url: None,
                use_proxy: None,
                user_agent: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_create_nulls() {
    harness::conform("profiles.create/nulls", |client| async move {
        let value = client
            .profiles()
            .create(steel_rs::ProfileCreateParams {
                user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                dimensions: None,
                project_id: None,
                proxy_url: None,
                use_proxy: None,
                user_agent: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_create_unknown() {
    harness::conform("profiles.create/unknown", |client| async move {
        let value = client
            .profiles()
            .create(steel_rs::ProfileCreateParams {
                user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                dimensions: None,
                project_id: None,
                proxy_url: None,
                use_proxy: None,
                user_agent: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_update_full() {
    harness::conform("profiles.update/full", |client| async move {
        let value = client
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
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_update_required() {
    harness::conform("profiles.update/required", |client| async move {
        let value = client
            .profiles()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileUpdateParams {
                    user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    query_project_id: None,
                    dimensions: None,
                    body_project_id: None,
                    proxy_url: None,
                    use_proxy: None,
                    user_agent: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_update_nulls() {
    harness::conform("profiles.update/nulls", |client| async move {
        let value = client
            .profiles()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileUpdateParams {
                    user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    query_project_id: None,
                    dimensions: None,
                    body_project_id: None,
                    proxy_url: None,
                    use_proxy: None,
                    user_agent: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_update_unknown() {
    harness::conform("profiles.update/unknown", |client| async move {
        let value = client
            .profiles()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileUpdateParams {
                    user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    query_project_id: None,
                    dimensions: None,
                    body_project_id: None,
                    proxy_url: None,
                    use_proxy: None,
                    user_agent: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_update_escaped() {
    harness::conform("profiles.update/escaped", |client| async move {
        let value = client
            .profiles()
            .update(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::ProfileUpdateParams {
                    user_data_dir: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    query_project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                    dimensions: None,
                    body_project_id: None,
                    proxy_url: None,
                    use_proxy: None,
                    user_agent: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_list_full() {
    harness::conform("profiles.list/full", |client| async move {
        let value = client
            .profiles()
            .list(steel_rs::ProfileListParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_list_required() {
    harness::conform("profiles.list/required", |client| async move {
        let value = client
            .profiles()
            .list(steel_rs::ProfileListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_list_nulls() {
    harness::conform("profiles.list/nulls", |client| async move {
        let value = client
            .profiles()
            .list(steel_rs::ProfileListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_list_unknown() {
    harness::conform("profiles.list/unknown", |client| async move {
        let value = client
            .profiles()
            .list(steel_rs::ProfileListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_list_escaped() {
    harness::conform("profiles.list/escaped", |client| async move {
        let value = client
            .profiles()
            .list(steel_rs::ProfileListParams {
                project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_delete_full() {
    harness::conform("profiles.delete/full", |client| async move {
        let value = client
            .profiles()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileDeleteParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_delete_required() {
    harness::conform("profiles.delete/required", |client| async move {
        let value = client
            .profiles()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileDeleteParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_delete_nulls() {
    harness::conform("profiles.delete/nulls", |client| async move {
        let value = client
            .profiles()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileDeleteParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_delete_unknown() {
    harness::conform("profiles.delete/unknown", |client| async move {
        let value = client
            .profiles()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileDeleteParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_delete_escaped() {
    harness::conform("profiles.delete/escaped", |client| async move {
        let value = client
            .profiles()
            .delete(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::ProfileDeleteParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_get_full() {
    harness::conform("profiles.get/full", |client| async move {
        let value = client
            .profiles()
            .get(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileGetParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_get_required() {
    harness::conform("profiles.get/required", |client| async move {
        let value = client
            .profiles()
            .get(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileGetParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_get_nulls() {
    harness::conform("profiles.get/nulls", |client| async move {
        let value = client
            .profiles()
            .get(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileGetParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_get_unknown() {
    harness::conform("profiles.get/unknown", |client| async move {
        let value = client
            .profiles()
            .get(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::ProfileGetParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn profiles_get_escaped() {
    harness::conform("profiles.get/escaped", |client| async move {
        let value = client
            .profiles()
            .get(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::ProfileGetParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn projects_retrieve_full() {
    harness::conform("projects.retrieve/full", |client| async move {
        let value = client
            .projects()
            .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn projects_retrieve_required() {
    harness::conform("projects.retrieve/required", |client| async move {
        let value = client
            .projects()
            .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn projects_retrieve_nulls() {
    harness::conform("projects.retrieve/nulls", |client| async move {
        let value = client
            .projects()
            .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn projects_retrieve_escaped() {
    harness::conform("projects.retrieve/escaped", |client| async move {
        let value = client
            .projects()
            .retrieve("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn projects_list_full() {
    harness::conform("projects.list/full", |client| async move {
        let value = client.projects().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn projects_list_required() {
    harness::conform("projects.list/required", |client| async move {
        let value = client.projects().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn projects_list_nulls() {
    harness::conform("projects.list/nulls", |client| async move {
        let value = client.projects().list().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_create_full() {
    harness::conform("secret_connections.create/full", |client| async move {
        let value = client
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
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_create_required() {
    harness::conform("secret_connections.create/required", |client| async move {
        let value = client
            .secret_connections()
            .create(steel_rs::SecretConnectionCreateParams {
                auth: steel_rs::SecretConnectionCreateParamsAuth {
                    ..Default::default()
                },
                name: "x".to_owned(),
                provider: steel_rs::SecretConnectionCreateParamsProvider::V1password,
                config: None,
                project_id: None,
                token_budget: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_create_nulls() {
    harness::conform("secret_connections.create/nulls", |client| async move {
        let value = client
            .secret_connections()
            .create(steel_rs::SecretConnectionCreateParams {
                auth: steel_rs::SecretConnectionCreateParamsAuth {
                    ..Default::default()
                },
                name: "x".to_owned(),
                provider: steel_rs::SecretConnectionCreateParamsProvider::V1password,
                config: None,
                project_id: None,
                token_budget: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_create_unknown() {
    harness::conform("secret_connections.create/unknown", |client| async move {
        let value = client
            .secret_connections()
            .create(steel_rs::SecretConnectionCreateParams {
                auth: steel_rs::SecretConnectionCreateParamsAuth {
                    ..Default::default()
                },
                name: "x".to_owned(),
                provider: steel_rs::SecretConnectionCreateParamsProvider::V1password,
                config: None,
                project_id: None,
                token_budget: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_retrieve_full() {
    harness::conform("secret_connections.retrieve/full", |client| async move {
        let value = client
            .secret_connections()
            .retrieve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionRetrieveParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_retrieve_required() {
    harness::conform(
        "secret_connections.retrieve/required",
        |client| async move {
            let value = client
                .secret_connections()
                .retrieve(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SecretConnectionRetrieveParams {
                        ..Default::default()
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn secret_connections_retrieve_nulls() {
    harness::conform("secret_connections.retrieve/nulls", |client| async move {
        let value = client
            .secret_connections()
            .retrieve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionRetrieveParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_retrieve_unknown() {
    harness::conform("secret_connections.retrieve/unknown", |client| async move {
        let value = client
            .secret_connections()
            .retrieve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionRetrieveParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_retrieve_escaped() {
    harness::conform("secret_connections.retrieve/escaped", |client| async move {
        let value = client
            .secret_connections()
            .retrieve(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SecretConnectionRetrieveParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_update_full() {
    harness::conform("secret_connections.update/full", |client| async move {
        let value = client
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
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_update_required() {
    harness::conform("secret_connections.update/required", |client| async move {
        let value = client
            .secret_connections()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_update_nulls() {
    harness::conform("secret_connections.update/nulls", |client| async move {
        let value = client
            .secret_connections()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_update_unknown() {
    harness::conform("secret_connections.update/unknown", |client| async move {
        let value = client
            .secret_connections()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_update_escaped() {
    harness::conform("secret_connections.update/escaped", |client| async move {
        let value = client
            .secret_connections()
            .update(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SecretConnectionUpdateParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_list_full() {
    harness::conform("secret_connections.list/full", |client| async move {
        let value = client
            .secret_connections()
            .list(steel_rs::SecretConnectionListParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_list_required() {
    harness::conform("secret_connections.list/required", |client| async move {
        let value = client
            .secret_connections()
            .list(steel_rs::SecretConnectionListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_list_nulls() {
    harness::conform("secret_connections.list/nulls", |client| async move {
        let value = client
            .secret_connections()
            .list(steel_rs::SecretConnectionListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_list_unknown() {
    harness::conform("secret_connections.list/unknown", |client| async move {
        let value = client
            .secret_connections()
            .list(steel_rs::SecretConnectionListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_list_escaped() {
    harness::conform("secret_connections.list/escaped", |client| async move {
        let value = client
            .secret_connections()
            .list(steel_rs::SecretConnectionListParams {
                project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn secret_connections_delete_full() {
    harness::conform("secret_connections.delete/full", |client| async move {
        client
            .secret_connections()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionDeleteParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn secret_connections_delete_required() {
    harness::conform("secret_connections.delete/required", |client| async move {
        client
            .secret_connections()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionDeleteParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn secret_connections_delete_escaped() {
    harness::conform("secret_connections.delete/escaped", |client| async move {
        client
            .secret_connections()
            .delete(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SecretConnectionDeleteParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn secret_connections_test_full() {
    harness::conform("secret_connections.test/full", |client| async move {
        client
            .secret_connections()
            .test(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionTestParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn secret_connections_test_required() {
    harness::conform("secret_connections.test/required", |client| async move {
        client
            .secret_connections()
            .test(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SecretConnectionTestParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn secret_connections_test_escaped() {
    harness::conform("secret_connections.test/escaped", |client| async move {
        client
            .secret_connections()
            .test(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SecretConnectionTestParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn sessions_create_full() {
    harness::conform("sessions.create/full", |client| async move {
        let value = client.sessions().create(steel_rs::SessionCreateParams { block_ads: Some(true), ca_certificates: Some(vec!["string".to_owned()]), concurrency: Some(-9007199254740991), credentials: Some(steel_rs::SessionCreateParamsCredentials { auto_submit: Some(true), bindings: Some(vec![steel_rs::SessionCreateParamsCredentialsBinding::String("string".to_owned())]), blur_fields: Some(true), credential_ids: Some(vec!["182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()]), credential_provider: Some("credentialProvider".to_owned()), dom_trigger: Some(true), enabled: Some(true), exact_origin: Some(true), lookup: Some(steel_rs::SessionCreateParamsCredentialsLookup::Implicit), origins: Some(vec!["string".to_owned()]) }), debug_config: Some(steel_rs::SessionCreateParamsDebugConfig { interactive: Some(true), system_cursor: Some(true) }), device_config: Some(steel_rs::SessionCreateParamsDeviceConfig { device: Some(steel_rs::SessionCreateParamsDeviceConfigDevice::Desktop) }), dimensions: Some(steel_rs::SessionCreateParamsDimensions { height: -9007199254740991, width: -9007199254740991 }), experimental_features: Some(vec!["string".to_owned()]), extension_ids: Some(vec!["string".to_owned()]), fullscreen: Some(true), headless: Some(true), inactivity_timeout: Some(1), is_selenium: Some(true), metadata: Some(std::collections::HashMap::from([("foo".to_owned(), steel_rs::SessionCreateParamsMetadata::String("string".to_owned()))])), namespace: Some("namespace".to_owned()), optimize_bandwidth: Some(steel_rs::SessionCreateParamsOptimizeBandwidth::Bool(true)), persist_profile: Some(true), profile_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()), project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()), proxy_url: Some("https://example.com".to_owned()), recording: Some(true), region: Some(serde_json::json!({})), session_context: Some(steel_rs::SessionCreateParamsSessionContext { cookies: Some(vec![steel_rs::SessionCreateParamsSessionContextCookie { name: "name".to_owned(), value: "value".to_owned(), domain: Some("domain".to_owned()), expires: Some(0.0), http_only: Some(true), partition_key: Some(steel_rs::SessionCreateParamsSessionContextCookiePartitionKey { has_cross_site_ancestor: true, top_level_site: "topLevelSite".to_owned() }), path: Some("path".to_owned()), priority: Some(steel_rs::SessionCreateParamsSessionContextCookiePriority::Low), same_party: Some(true), same_site: Some(steel_rs::SessionCreateParamsSessionContextCookieSameSite::Strict), secure: Some(true), session: Some(true), size: Some(0.0), source_port: Some(0.0), source_scheme: Some(steel_rs::SessionCreateParamsSessionContextCookieSourceScheme::Unset), url: Some("url".to_owned()) }]), indexed_db: Some(std::collections::HashMap::from([("foo".to_owned(), vec![steel_rs::SessionCreateParamsSessionContextIndexedDb { id: 0.0, data: vec![steel_rs::SessionCreateParamsSessionContextIndexedDbData { id: 0.0, name: "name".to_owned(), records: vec![steel_rs::SessionCreateParamsSessionContextIndexedDbDataRecord { key: serde_json::json!({}), value: serde_json::json!({}), blob_files: Some(vec![steel_rs::SessionCreateParamsSessionContextIndexedDbDataRecordBlobFile { blob_number: 0.0, mime_type: "mimeType".to_owned(), size: 0.0, filename: Some("filename".to_owned()), last_modified: Some("2019-12-27T18:11:19.117Z".to_owned()), path: Some("path".to_owned()) }]) }] }], name: "name".to_owned() }])])), local_storage: Some(std::collections::HashMap::from([("foo".to_owned(), std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]))])), session_storage: Some(std::collections::HashMap::from([("foo".to_owned(), std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]))])) }), session_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()), solve_captcha: Some(true), stealth_config: Some(steel_rs::SessionCreateParamsStealthConfig { auto_captcha_solving: Some(true), humanize_interactions: Some(true), skip_fingerprint_injection: Some(true) }), timeout: Some(-9007199254740991), use_proxy: Some(serde_json::json!(true)), user_agent: Some("userAgent".to_owned()) }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_create_required() {
    harness::conform("sessions.create/required", |client| async move {
        let value = client
            .sessions()
            .create(steel_rs::SessionCreateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_create_nulls() {
    harness::conform("sessions.create/nulls", |client| async move {
        let value = client
            .sessions()
            .create(steel_rs::SessionCreateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_create_unknown() {
    harness::conform("sessions.create/unknown", |client| async move {
        let value = client
            .sessions()
            .create(steel_rs::SessionCreateParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_retrieve_full() {
    harness::conform("sessions.retrieve/full", |client| async move {
        let value = client
            .sessions()
            .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_retrieve_required() {
    harness::conform("sessions.retrieve/required", |client| async move {
        let value = client
            .sessions()
            .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_retrieve_nulls() {
    harness::conform("sessions.retrieve/nulls", |client| async move {
        let value = client
            .sessions()
            .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_retrieve_unknown() {
    harness::conform("sessions.retrieve/unknown", |client| async move {
        let value = client
            .sessions()
            .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_retrieve_escaped() {
    harness::conform("sessions.retrieve/escaped", |client| async move {
        let value = client
            .sessions()
            .retrieve("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_list_full() {
    harness::conform("sessions.list/full", |client| async move {
        let page = client
            .sessions()
            .list(steel_rs::SessionListParams {
                created_after: Some("2019-12-27T18:11:19.117Z".to_owned()),
                created_before: Some("2019-12-27T18:11:19.117Z".to_owned()),
                cursor_id: Some("cursorId".to_owned()),
                limit: Some(1),
                metadata: Some(std::collections::HashMap::from([(
                    "foo".to_owned(),
                    "x".to_owned(),
                )])),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                release_reason: Some(steel_rs::SessionListParamsReleaseReason::UserRequested),
                signal: Some(vec![steel_rs::SessionListParamsSignal::CaptchaEncountered]),
                status: Some(steel_rs::SessionListParamsStatus::Live),
            })
            .await
            .expect("request");
        harness::Outcome::Items(serde_json::to_value(page.items()).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_list_required() {
    harness::conform("sessions.list/required", |client| async move {
        let page = client
            .sessions()
            .list(steel_rs::SessionListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Items(serde_json::to_value(page.items()).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_list_nulls() {
    harness::conform("sessions.list/nulls", |client| async move {
        let page = client
            .sessions()
            .list(steel_rs::SessionListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Items(serde_json::to_value(page.items()).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_list_unknown() {
    harness::conform("sessions.list/unknown", |client| async move {
        let page = client
            .sessions()
            .list(steel_rs::SessionListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Items(serde_json::to_value(page.items()).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_list_escaped() {
    harness::conform("sessions.list/escaped", |client| async move {
        let page = client
            .sessions()
            .list(steel_rs::SessionListParams {
                cursor_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Items(serde_json::to_value(page.items()).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_list_pages() {
    harness::conform("sessions.list/pages", |client| async move {
        let mut ids = Vec::new();
        let mut stream =
            std::pin::pin!(client
                .sessions()
                .list_auto_paging(steel_rs::SessionListParams {
                    ..Default::default()
                }));
        while let Some(item) = stream.next().await {
            let item = item.expect("page");
            ids.push(item.id.clone());
        }
        harness::Outcome::Ids(ids)
    })
    .await;
}

#[tokio::test]
async fn sessions_agent_traces_full() {
    harness::conform("sessions.agent_traces/full", |client| async move {
        let value = client
            .sessions()
            .agent_traces(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionAgentTracesParams {
                    end_time: Some("2019-12-27T18:11:19.117Z".to_owned()),
                    event_types: Some(vec!["string".to_owned()]),
                    limit: Some(1),
                    namespace: Some("namespace".to_owned()),
                    offset: Some(0),
                    start_time: Some("2019-12-27T18:11:19.117Z".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_agent_traces_required() {
    harness::conform("sessions.agent_traces/required", |client| async move {
        let value = client
            .sessions()
            .agent_traces(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionAgentTracesParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_agent_traces_nulls() {
    harness::conform("sessions.agent_traces/nulls", |client| async move {
        let value = client
            .sessions()
            .agent_traces(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionAgentTracesParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_agent_traces_escaped() {
    harness::conform("sessions.agent_traces/escaped", |client| async move {
        let value = client
            .sessions()
            .agent_traces(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionAgentTracesParams {
                    namespace: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_full() {
    harness::conform("sessions.commands/full", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: Some("t321669910225".to_owned()) }), after: Some("x".to_owned()), observe: Some(steel_rs::SessionCommandsParamsObserve { mode: steel_rs::SessionCommandsParamsObserveMode::Snapshot, base: Some("x".to_owned()), compact: Some(true), depth: Some(0), interactive: Some(true), tab: Some("t321669910225".to_owned()) }), idempotency_key: Some("idempotency-key".to_owned()) }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_required() {
    harness::conform("sessions.commands/required", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_nulls() {
    harness::conform("sessions.commands/nulls", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_unknown() {
    harness::conform("sessions.commands/unknown", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_1() {
    harness::conform("sessions.commands/variant-0-1", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_2() {
    harness::conform("sessions.commands/variant-0-2", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_3() {
    harness::conform("sessions.commands/variant-0-3", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_4() {
    harness::conform("sessions.commands/variant-0-4", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_5() {
    harness::conform("sessions.commands/variant-0-5", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_6() {
    harness::conform("sessions.commands/variant-0-6", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_7() {
    harness::conform("sessions.commands/variant-0-7", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_8() {
    harness::conform("sessions.commands/variant-0-8", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_9() {
    harness::conform("sessions.commands/variant-0-9", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_10() {
    harness::conform("sessions.commands/variant-0-10", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_11() {
    harness::conform("sessions.commands/variant-0-11", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_12() {
    harness::conform("sessions.commands/variant-0-12", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_13() {
    harness::conform("sessions.commands/variant-0-13", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_14() {
    harness::conform("sessions.commands/variant-0-14", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_15() {
    harness::conform("sessions.commands/variant-0-15", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_16() {
    harness::conform("sessions.commands/variant-0-16", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_17() {
    harness::conform("sessions.commands/variant-0-17", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_18() {
    harness::conform("sessions.commands/variant-0-18", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_19() {
    harness::conform("sessions.commands/variant-0-19", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_20() {
    harness::conform("sessions.commands/variant-0-20", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_21() {
    harness::conform("sessions.commands/variant-0-21", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_22() {
    harness::conform("sessions.commands/variant-0-22", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_23() {
    harness::conform("sessions.commands/variant-0-23", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_24() {
    harness::conform("sessions.commands/variant-0-24", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_25() {
    harness::conform("sessions.commands/variant-0-25", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_26() {
    harness::conform("sessions.commands/variant-0-26", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_27() {
    harness::conform("sessions.commands/variant-0-27", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_variant_0_28() {
    harness::conform("sessions.commands/variant-0-28", |client| async move {
        let value = client.sessions().commands("sessionId", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_commands_escaped() {
    harness::conform("sessions.commands/escaped", |client| async move {
        let value = client.sessions().commands("a b/c?d#e%f+g&h=i é", steel_rs::SessionCommandsParams { commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(steel_rs::SessionCommandsParamsCommandsNavigateCommand { command: steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate, url: "x".to_owned(), tab: None }), after: None, observe: None, idempotency_key: None }).await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_computer_full() {
    harness::conform("sessions.computer/full", |client| async move {
        let value = client
            .sessions()
            .computer(
                "sessionId",
                steel_rs::SessionComputerParams::MoveMouse(
                    steel_rs::SessionComputerParamsMoveMouse {
                        coordinates: vec![0.0, 0.0],
                        hold_keys: Some(vec!["string".to_owned()]),
                        screenshot: Some(true),
                    },
                ),
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_computer_required() {
    harness::conform("sessions.computer/required", |client| async move {
        let value = client
            .sessions()
            .computer(
                "sessionId",
                steel_rs::SessionComputerParams::MoveMouse(
                    steel_rs::SessionComputerParamsMoveMouse {
                        coordinates: vec![0.0, 0.0],
                        hold_keys: None,
                        screenshot: None,
                    },
                ),
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_computer_nulls() {
    harness::conform("sessions.computer/nulls", |client| async move {
        let value = client
            .sessions()
            .computer(
                "sessionId",
                steel_rs::SessionComputerParams::MoveMouse(
                    steel_rs::SessionComputerParamsMoveMouse {
                        coordinates: vec![0.0, 0.0],
                        hold_keys: None,
                        screenshot: None,
                    },
                ),
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_computer_escaped() {
    harness::conform("sessions.computer/escaped", |client| async move {
        let value = client
            .sessions()
            .computer(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionComputerParams::MoveMouse(
                    steel_rs::SessionComputerParamsMoveMouse {
                        coordinates: vec![0.0, 0.0],
                        hold_keys: None,
                        screenshot: None,
                    },
                ),
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_context_full() {
    harness::conform("sessions.context/full", |client| async move {
        let value = client
            .sessions()
            .context("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_context_required() {
    harness::conform("sessions.context/required", |client| async move {
        let value = client
            .sessions()
            .context("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_context_nulls() {
    harness::conform("sessions.context/nulls", |client| async move {
        let value = client
            .sessions()
            .context("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_context_unknown() {
    harness::conform("sessions.context/unknown", |client| async move {
        let value = client
            .sessions()
            .context("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_context_escaped() {
    harness::conform("sessions.context/escaped", |client| async move {
        let value = client
            .sessions()
            .context("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_cost_full() {
    harness::conform("sessions.cost/full", |client| async move {
        let value = client
            .sessions()
            .cost("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_cost_required() {
    harness::conform("sessions.cost/required", |client| async move {
        let value = client
            .sessions()
            .cost("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_cost_nulls() {
    harness::conform("sessions.cost/nulls", |client| async move {
        let value = client
            .sessions()
            .cost("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_cost_unknown() {
    harness::conform("sessions.cost/unknown", |client| async move {
        let value = client
            .sessions()
            .cost("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_cost_escaped() {
    harness::conform("sessions.cost/escaped", |client| async move {
        let value = client
            .sessions()
            .cost("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_events_full() {
    harness::conform("sessions.events/full", |client| async move {
        let value = client
            .sessions()
            .events(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionEventsParams {
                    compressed: Some(true),
                    limit: Some(1),
                    pointer: Some("pointer".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_events_required() {
    harness::conform("sessions.events/required", |client| async move {
        let value = client
            .sessions()
            .events(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionEventsParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_events_nulls() {
    harness::conform("sessions.events/nulls", |client| async move {
        let value = client
            .sessions()
            .events(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionEventsParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_events_escaped() {
    harness::conform("sessions.events/escaped", |client| async move {
        let value = client
            .sessions()
            .events(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionEventsParams {
                    pointer: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_hls_full() {
    harness::conform("sessions.hls/full", |client| async move {
        let bytes = client
            .sessions()
            .hls("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn sessions_hls_required() {
    harness::conform("sessions.hls/required", |client| async move {
        let bytes = client
            .sessions()
            .hls("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn sessions_hls_escaped() {
    harness::conform("sessions.hls/escaped", |client| async move {
        let bytes = client
            .sessions()
            .hls("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn sessions_live_details_full() {
    harness::conform("sessions.live_details/full", |client| async move {
        let value = client
            .sessions()
            .live_details("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_live_details_required() {
    harness::conform("sessions.live_details/required", |client| async move {
        let value = client
            .sessions()
            .live_details("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_live_details_nulls() {
    harness::conform("sessions.live_details/nulls", |client| async move {
        let value = client
            .sessions()
            .live_details("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_live_details_escaped() {
    harness::conform("sessions.live_details/escaped", |client| async move {
        let value = client
            .sessions()
            .live_details("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_logs_full() {
    harness::conform("sessions.logs/full", |client| async move {
        let value = client
            .sessions()
            .logs(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionLogsParams {
                    end_time: Some("2019-12-27T18:11:19.117Z".to_owned()),
                    event_types: Some(vec!["string".to_owned()]),
                    limit: Some(1),
                    namespace: Some("namespace".to_owned()),
                    offset: Some(0),
                    start_time: Some("2019-12-27T18:11:19.117Z".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_logs_required() {
    harness::conform("sessions.logs/required", |client| async move {
        let value = client
            .sessions()
            .logs(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionLogsParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_logs_nulls() {
    harness::conform("sessions.logs/nulls", |client| async move {
        let value = client
            .sessions()
            .logs(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionLogsParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_logs_escaped() {
    harness::conform("sessions.logs/escaped", |client| async move {
        let value = client
            .sessions()
            .logs(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionLogsParams {
                    namespace: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_full() {
    harness::conform("sessions.release/full", |client| async move {
        let value = client
            .sessions()
            .release("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_required() {
    harness::conform("sessions.release/required", |client| async move {
        let value = client
            .sessions()
            .release("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_nulls() {
    harness::conform("sessions.release/nulls", |client| async move {
        let value = client
            .sessions()
            .release("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_escaped() {
    harness::conform("sessions.release/escaped", |client| async move {
        let value = client
            .sessions()
            .release("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_all_full() {
    harness::conform("sessions.release_all/full", |client| async move {
        let value = client
            .sessions()
            .release_all(steel_rs::SessionReleaseAllParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_all_required() {
    harness::conform("sessions.release_all/required", |client| async move {
        let value = client
            .sessions()
            .release_all(steel_rs::SessionReleaseAllParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_all_nulls() {
    harness::conform("sessions.release_all/nulls", |client| async move {
        let value = client
            .sessions()
            .release_all(steel_rs::SessionReleaseAllParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_release_all_escaped() {
    harness::conform("sessions.release_all/escaped", |client| async move {
        let value = client
            .sessions()
            .release_all(steel_rs::SessionReleaseAllParams {
                project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_replace_metadata_full() {
    harness::conform("sessions.replace_metadata/full", |client| async move {
        let value = client
            .sessions()
            .replace_metadata(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionReplaceMetadataParams {
                    metadata: std::collections::HashMap::from([(
                        "foo".to_owned(),
                        steel_rs::SessionReplaceMetadataParamsMetadata::String("string".to_owned()),
                    )]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_replace_metadata_required() {
    harness::conform("sessions.replace_metadata/required", |client| async move {
        let value = client
            .sessions()
            .replace_metadata(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionReplaceMetadataParams {
                    metadata: std::collections::HashMap::from([(
                        "foo".to_owned(),
                        steel_rs::SessionReplaceMetadataParamsMetadata::String("string".to_owned()),
                    )]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_replace_metadata_nulls() {
    harness::conform("sessions.replace_metadata/nulls", |client| async move {
        let value = client
            .sessions()
            .replace_metadata(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionReplaceMetadataParams {
                    metadata: std::collections::HashMap::from([(
                        "foo".to_owned(),
                        steel_rs::SessionReplaceMetadataParamsMetadata::String("string".to_owned()),
                    )]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_replace_metadata_escaped() {
    harness::conform("sessions.replace_metadata/escaped", |client| async move {
        let value = client
            .sessions()
            .replace_metadata(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionReplaceMetadataParams {
                    metadata: std::collections::HashMap::from([(
                        "foo".to_owned(),
                        steel_rs::SessionReplaceMetadataParamsMetadata::String("string".to_owned()),
                    )]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_signal_catalog_full() {
    harness::conform("sessions.signal_catalog/full", |client| async move {
        let value = client.sessions().signal_catalog().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_signal_catalog_required() {
    harness::conform("sessions.signal_catalog/required", |client| async move {
        let value = client.sessions().signal_catalog().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_signal_catalog_nulls() {
    harness::conform("sessions.signal_catalog/nulls", |client| async move {
        let value = client.sessions().signal_catalog().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_signal_catalog_unknown() {
    harness::conform("sessions.signal_catalog/unknown", |client| async move {
        let value = client.sessions().signal_catalog().await.expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_update_metadata_full() {
    harness::conform("sessions.update_metadata/full", |client| async move {
        let value = client
            .sessions()
            .update_metadata(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionUpdateMetadataParams {
                    metadata: std::collections::HashMap::from([(
                        "foo".to_owned(),
                        Some(steel_rs::SessionUpdateMetadataParamsMetadata::String(
                            "string".to_owned(),
                        )),
                    )]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_update_metadata_required() {
    harness::conform("sessions.update_metadata/required", |client| async move {
        let value = client
            .sessions()
            .update_metadata(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionUpdateMetadataParams {
                    metadata: std::collections::HashMap::from([(
                        "foo".to_owned(),
                        Some(steel_rs::SessionUpdateMetadataParamsMetadata::String(
                            "string".to_owned(),
                        )),
                    )]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_update_metadata_nulls() {
    harness::conform("sessions.update_metadata/nulls", |client| async move {
        let value = client
            .sessions()
            .update_metadata(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionUpdateMetadataParams {
                    metadata: std::collections::HashMap::from([("foo".to_owned(), None)]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_update_metadata_escaped() {
    harness::conform("sessions.update_metadata/escaped", |client| async move {
        let value = client
            .sessions()
            .update_metadata(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionUpdateMetadataParams {
                    metadata: std::collections::HashMap::from([(
                        "foo".to_owned(),
                        Some(steel_rs::SessionUpdateMetadataParamsMetadata::String(
                            "string".to_owned(),
                        )),
                    )]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_full() {
    harness::conform("sessions.captchas.solve/full", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .solve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCaptchaSolveParams {
                    page_id: Some("pageId".to_owned()),
                    task_id: Some("taskId".to_owned()),
                    url: Some("url".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_required() {
    harness::conform("sessions.captchas.solve/required", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .solve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCaptchaSolveParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_nulls() {
    harness::conform("sessions.captchas.solve/nulls", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .solve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCaptchaSolveParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_escaped() {
    harness::conform("sessions.captchas.solve/escaped", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .solve(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionCaptchaSolveParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_image_full() {
    harness::conform("sessions.captchas.solve_image/full", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .solve_image(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCaptchaSolveImageParams {
                    image_x_path: "imageXPath".to_owned(),
                    input_x_path: "inputXPath".to_owned(),
                    url: Some("url".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_image_required() {
    harness::conform(
        "sessions.captchas.solve_image/required",
        |client| async move {
            let value = client
                .sessions()
                .captchas()
                .solve_image(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCaptchaSolveImageParams {
                        image_x_path: "imageXPath".to_owned(),
                        input_x_path: "inputXPath".to_owned(),
                        url: None,
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_image_nulls() {
    harness::conform("sessions.captchas.solve_image/nulls", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .solve_image(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCaptchaSolveImageParams {
                    image_x_path: "imageXPath".to_owned(),
                    input_x_path: "inputXPath".to_owned(),
                    url: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_solve_image_escaped() {
    harness::conform(
        "sessions.captchas.solve_image/escaped",
        |client| async move {
            let value = client
                .sessions()
                .captchas()
                .solve_image(
                    "a b/c?d#e%f+g&h=i é",
                    steel_rs::SessionCaptchaSolveImageParams {
                        image_x_path: "imageXPath".to_owned(),
                        input_x_path: "inputXPath".to_owned(),
                        url: None,
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_captchas_status_full() {
    harness::conform("sessions.captchas.status/full", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .status("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_status_required() {
    harness::conform("sessions.captchas.status/required", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .status("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_status_nulls() {
    harness::conform("sessions.captchas.status/nulls", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .status("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_captchas_status_escaped() {
    harness::conform("sessions.captchas.status/escaped", |client| async move {
        let value = client
            .sessions()
            .captchas()
            .status("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_full() {
    harness::conform(
        "sessions.command_executions.retrieve/full",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_required() {
    harness::conform(
        "sessions.command_executions.retrieve/required",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_nulls() {
    harness::conform(
        "sessions.command_executions.retrieve/nulls",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_unknown() {
    harness::conform(
        "sessions.command_executions.retrieve/unknown",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_1() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-1",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_2() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-2",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_3() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-3",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_4() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-4",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_5() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-5",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_6() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-6",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_7() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-7",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_8() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-8",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_9() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-9",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_10() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-10",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_11() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-11",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_12() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-12",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_13() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-13",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_14() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-14",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_15() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-15",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_16() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-16",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_17() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-17",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_18() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-18",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_19() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-19",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_20() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-20",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_21() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-21",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_22() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-22",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_23() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-23",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_24() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-24",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_25() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-25",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_26() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-26",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_27() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-27",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_variant_0_28() {
    harness::conform(
        "sessions.command_executions.retrieve/variant-0-28",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_retrieve_escaped() {
    harness::conform(
        "sessions.command_executions.retrieve/escaped",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .retrieve("a b/c?d#e%f+g&h=i é", "a b/c?d#e%f+g&h=i é")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_full() {
    harness::conform(
        "sessions.command_executions.cancel/full",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_required() {
    harness::conform(
        "sessions.command_executions.cancel/required",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_nulls() {
    harness::conform(
        "sessions.command_executions.cancel/nulls",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_unknown() {
    harness::conform(
        "sessions.command_executions.cancel/unknown",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_1() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-1",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_2() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-2",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_3() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-3",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_4() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-4",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_5() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-5",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_6() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-6",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_7() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-7",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_8() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-8",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_9() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-9",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_10() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-10",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_11() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-11",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_12() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-12",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_13() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-13",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_14() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-14",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_15() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-15",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_16() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-16",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_17() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-17",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_18() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-18",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_19() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-19",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_20() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-20",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_21() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-21",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_22() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-22",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_23() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-23",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_24() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-24",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_25() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-25",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_26() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-26",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_27() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-27",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_variant_0_28() {
    harness::conform(
        "sessions.command_executions.cancel/variant-0-28",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("sessionId", "executionId")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_command_executions_cancel_escaped() {
    harness::conform(
        "sessions.command_executions.cancel/escaped",
        |client| async move {
            let value = client
                .sessions()
                .command_executions()
                .cancel("a b/c?d#e%f+g&h=i é", "a b/c?d#e%f+g&h=i é")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_list_full() {
    harness::conform("sessions.credentials.list/full", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .list("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_list_required() {
    harness::conform("sessions.credentials.list/required", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .list("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_list_nulls() {
    harness::conform("sessions.credentials.list/nulls", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .list("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_list_unknown() {
    harness::conform("sessions.credentials.list/unknown", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .list("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_list_variant_0_1() {
    harness::conform(
        "sessions.credentials.list/variant-0-1",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .list("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_list_escaped() {
    harness::conform("sessions.credentials.list/escaped", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .list("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_attach_full() {
    harness::conform("sessions.credentials.attach/full", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .attach(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCredentialAttachParams {
                    bindings: Some(vec![
                        steel_rs::SessionCredentialAttachParamsBinding::String("string".to_owned()),
                    ]),
                    credential_ids: Some(vec!["182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_attach_required() {
    harness::conform(
        "sessions.credentials.attach/required",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .attach(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialAttachParams {
                        ..Default::default()
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_attach_nulls() {
    harness::conform("sessions.credentials.attach/nulls", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .attach(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCredentialAttachParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_attach_unknown() {
    harness::conform("sessions.credentials.attach/unknown", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .attach(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCredentialAttachParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_attach_variant_0_1() {
    harness::conform(
        "sessions.credentials.attach/variant-0-1",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .attach(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialAttachParams {
                        ..Default::default()
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_attach_escaped() {
    harness::conform("sessions.credentials.attach/escaped", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .attach(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionCredentialAttachParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_full() {
    harness::conform("sessions.credentials.detach/full", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .detach(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_required() {
    harness::conform(
        "sessions.credentials.detach/required",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_nulls() {
    harness::conform("sessions.credentials.detach/nulls", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .detach(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_unknown() {
    harness::conform("sessions.credentials.detach/unknown", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .detach(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_variant_0_1() {
    harness::conform(
        "sessions.credentials.detach/variant-0-1",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_escaped() {
    harness::conform("sessions.credentials.detach/escaped", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .detach("a b/c?d#e%f+g&h=i é", "a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_binding_full() {
    harness::conform(
        "sessions.credentials.detach_binding/full",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach_binding(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialDetachBindingParams {
                        reference: "reference".to_owned(),
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_binding_required() {
    harness::conform(
        "sessions.credentials.detach_binding/required",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach_binding(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialDetachBindingParams {
                        reference: "reference".to_owned(),
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_binding_nulls() {
    harness::conform(
        "sessions.credentials.detach_binding/nulls",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach_binding(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialDetachBindingParams {
                        reference: "reference".to_owned(),
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_binding_unknown() {
    harness::conform(
        "sessions.credentials.detach_binding/unknown",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach_binding(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialDetachBindingParams {
                        reference: "reference".to_owned(),
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_binding_variant_0_1() {
    harness::conform(
        "sessions.credentials.detach_binding/variant-0-1",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach_binding(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialDetachBindingParams {
                        reference: "reference".to_owned(),
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_detach_binding_escaped() {
    harness::conform(
        "sessions.credentials.detach_binding/escaped",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .detach_binding(
                    "a b/c?d#e%f+g&h=i é",
                    steel_rs::SessionCredentialDetachBindingParams {
                        reference: "a b/c?d#e%f+g&h=i é".to_owned(),
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_replace_full() {
    harness::conform("sessions.credentials.replace/full", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .replace(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCredentialReplaceParams {
                    bindings: Some(vec![
                        steel_rs::SessionCredentialReplaceParamsBinding::String(
                            "string".to_owned(),
                        ),
                    ]),
                    credential_ids: Some(vec!["182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()]),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_replace_required() {
    harness::conform(
        "sessions.credentials.replace/required",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .replace(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialReplaceParams {
                        ..Default::default()
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_replace_nulls() {
    harness::conform("sessions.credentials.replace/nulls", |client| async move {
        let value = client
            .sessions()
            .credentials()
            .replace(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::SessionCredentialReplaceParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_credentials_replace_unknown() {
    harness::conform(
        "sessions.credentials.replace/unknown",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .replace(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialReplaceParams {
                        ..Default::default()
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_replace_variant_0_1() {
    harness::conform(
        "sessions.credentials.replace/variant-0-1",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .replace(
                    "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                    steel_rs::SessionCredentialReplaceParams {
                        ..Default::default()
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_credentials_replace_escaped() {
    harness::conform(
        "sessions.credentials.replace/escaped",
        |client| async move {
            let value = client
                .sessions()
                .credentials()
                .replace(
                    "a b/c?d#e%f+g&h=i é",
                    steel_rs::SessionCredentialReplaceParams {
                        ..Default::default()
                    },
                )
                .await
                .expect("request");
            harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_files_list_full() {
    harness::conform("sessions.files.list/full", |client| async move {
        let value = client
            .sessions()
            .files()
            .list("sessionId")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_list_required() {
    harness::conform("sessions.files.list/required", |client| async move {
        let value = client
            .sessions()
            .files()
            .list("sessionId")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_list_nulls() {
    harness::conform("sessions.files.list/nulls", |client| async move {
        let value = client
            .sessions()
            .files()
            .list("sessionId")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_list_escaped() {
    harness::conform("sessions.files.list/escaped", |client| async move {
        let value = client
            .sessions()
            .files()
            .list("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_delete_full() {
    harness::conform("sessions.files.delete/full", |client| async move {
        client
            .sessions()
            .files()
            .delete("sessionId", "path")
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn sessions_files_delete_required() {
    harness::conform("sessions.files.delete/required", |client| async move {
        client
            .sessions()
            .files()
            .delete("sessionId", "path")
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn sessions_files_delete_escaped() {
    harness::conform("sessions.files.delete/escaped", |client| async move {
        client
            .sessions()
            .files()
            .delete("a b/c?d#e%f+g&h=i é", "a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn sessions_files_delete_all_full() {
    harness::conform("sessions.files.delete_all/full", |client| async move {
        client
            .sessions()
            .files()
            .delete_all("sessionId")
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn sessions_files_delete_all_required() {
    harness::conform("sessions.files.delete_all/required", |client| async move {
        client
            .sessions()
            .files()
            .delete_all("sessionId")
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn sessions_files_delete_all_escaped() {
    harness::conform("sessions.files.delete_all/escaped", |client| async move {
        client
            .sessions()
            .files()
            .delete_all("a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn sessions_files_download_full() {
    harness::conform("sessions.files.download/full", |client| async move {
        let bytes = client
            .sessions()
            .files()
            .download("sessionId", "path")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_download_required() {
    harness::conform("sessions.files.download/required", |client| async move {
        let bytes = client
            .sessions()
            .files()
            .download("sessionId", "path")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_download_escaped() {
    harness::conform("sessions.files.download/escaped", |client| async move {
        let bytes = client
            .sessions()
            .files()
            .download("a b/c?d#e%f+g&h=i é", "a b/c?d#e%f+g&h=i é")
            .await
            .expect("request");
        harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_download_archive_full() {
    harness::conform(
        "sessions.files.download_archive/full",
        |client| async move {
            let bytes = client
                .sessions()
                .files()
                .download_archive("sessionId")
                .await
                .expect("request");
            harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_files_download_archive_required() {
    harness::conform(
        "sessions.files.download_archive/required",
        |client| async move {
            let bytes = client
                .sessions()
                .files()
                .download_archive("sessionId")
                .await
                .expect("request");
            harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_files_download_archive_escaped() {
    harness::conform(
        "sessions.files.download_archive/escaped",
        |client| async move {
            let bytes = client
                .sessions()
                .files()
                .download_archive("a b/c?d#e%f+g&h=i é")
                .await
                .expect("request");
            harness::Outcome::Text(String::from_utf8(bytes.to_vec()).expect("utf-8"))
        },
    )
    .await;
}

#[tokio::test]
async fn sessions_files_upload_full() {
    harness::conform("sessions.files.upload/full", |client| async move {
        let value = client
            .sessions()
            .files()
            .upload(
                "sessionId",
                steel_rs::SessionFileUploadParams {
                    file: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    path: Some("path".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_upload_required() {
    harness::conform("sessions.files.upload/required", |client| async move {
        let value = client
            .sessions()
            .files()
            .upload(
                "sessionId",
                steel_rs::SessionFileUploadParams {
                    file: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    path: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_upload_nulls() {
    harness::conform("sessions.files.upload/nulls", |client| async move {
        let value = client
            .sessions()
            .files()
            .upload(
                "sessionId",
                steel_rs::SessionFileUploadParams {
                    file: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    path: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn sessions_files_upload_escaped() {
    harness::conform("sessions.files.upload/escaped", |client| async move {
        let value = client
            .sessions()
            .files()
            .upload(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::SessionFileUploadParams {
                    file: steel_rs::Upload::from_bytes("README.md", "Example data"),
                    path: None,
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_create_full() {
    harness::conform("vaults.create/full", |client| async move {
        let value = client
            .vaults()
            .create(steel_rs::VaultCreateParams {
                name: "name".to_owned(),
                description: Some("description".to_owned()),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_create_required() {
    harness::conform("vaults.create/required", |client| async move {
        let value = client
            .vaults()
            .create(steel_rs::VaultCreateParams {
                name: "name".to_owned(),
                description: None,
                project_id: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_create_nulls() {
    harness::conform("vaults.create/nulls", |client| async move {
        let value = client
            .vaults()
            .create(steel_rs::VaultCreateParams {
                name: "name".to_owned(),
                description: None,
                project_id: None,
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_retrieve_full() {
    harness::conform("vaults.retrieve/full", |client| async move {
        let value = client
            .vaults()
            .retrieve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultRetrieveParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_retrieve_required() {
    harness::conform("vaults.retrieve/required", |client| async move {
        let value = client
            .vaults()
            .retrieve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultRetrieveParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_retrieve_nulls() {
    harness::conform("vaults.retrieve/nulls", |client| async move {
        let value = client
            .vaults()
            .retrieve(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultRetrieveParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_retrieve_escaped() {
    harness::conform("vaults.retrieve/escaped", |client| async move {
        let value = client
            .vaults()
            .retrieve(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::VaultRetrieveParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_update_full() {
    harness::conform("vaults.update/full", |client| async move {
        let value = client
            .vaults()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultUpdateParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                    description: Some("description".to_owned()),
                    name: Some("name".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_update_required() {
    harness::conform("vaults.update/required", |client| async move {
        let value = client
            .vaults()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_update_nulls() {
    harness::conform("vaults.update/nulls", |client| async move {
        let value = client
            .vaults()
            .update(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultUpdateParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_update_escaped() {
    harness::conform("vaults.update/escaped", |client| async move {
        let value = client
            .vaults()
            .update(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::VaultUpdateParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_list_full() {
    harness::conform("vaults.list/full", |client| async move {
        let value = client
            .vaults()
            .list(steel_rs::VaultListParams {
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_list_required() {
    harness::conform("vaults.list/required", |client| async move {
        let value = client
            .vaults()
            .list(steel_rs::VaultListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_list_nulls() {
    harness::conform("vaults.list/nulls", |client| async move {
        let value = client
            .vaults()
            .list(steel_rs::VaultListParams {
                ..Default::default()
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_list_escaped() {
    harness::conform("vaults.list/escaped", |client| async move {
        let value = client
            .vaults()
            .list(steel_rs::VaultListParams {
                project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
            })
            .await
            .expect("request");
        harness::Outcome::Json(serde_json::to_value(&value).expect("serialize"))
    })
    .await;
}

#[tokio::test]
async fn vaults_delete_full() {
    harness::conform("vaults.delete/full", |client| async move {
        client
            .vaults()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultDeleteParams {
                    project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn vaults_delete_required() {
    harness::conform("vaults.delete/required", |client| async move {
        client
            .vaults()
            .delete(
                "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
                steel_rs::VaultDeleteParams {
                    ..Default::default()
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}

#[tokio::test]
async fn vaults_delete_escaped() {
    harness::conform("vaults.delete/escaped", |client| async move {
        client
            .vaults()
            .delete(
                "a b/c?d#e%f+g&h=i é",
                steel_rs::VaultDeleteParams {
                    project_id: Some("a b/c?d#e%f+g&h=i é".to_owned()),
                },
            )
            .await
            .expect("request");
        harness::Outcome::Empty
    })
    .await;
}
