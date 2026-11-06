mod common;

#[tokio::test]
async fn create() {
    let client = common::client();
    let result = client.sessions().create(steel_rs::SessionCreateParams { block_ads: Some(true), ca_certificates: Some(vec!["string".to_owned()]), concurrency: Some(-9007199254740991), credentials: Some(steel_rs::SessionCreateParamsCredentials { auto_submit: Some(true), blur_fields: Some(true), credential_provider: Some("credentialProvider".to_owned()), exact_origin: Some(true), origins: Some(vec!["string".to_owned()]) }), debug_config: Some(steel_rs::SessionCreateParamsDebugConfig { interactive: Some(true), system_cursor: Some(true) }), device_config: Some(steel_rs::SessionCreateParamsDeviceConfig { device: Some(steel_rs::SessionCreateParamsDeviceConfigDevice::Desktop) }), dimensions: Some(steel_rs::SessionCreateParamsDimensions { height: -9007199254740991, width: -9007199254740991 }), experimental_features: Some(vec!["string".to_owned()]), extension_ids: Some(vec!["string".to_owned()]), fullscreen: Some(true), headless: Some(true), inactivity_timeout: Some(1), is_selenium: Some(true), metadata: Some(std::collections::HashMap::from([("foo".to_owned(), steel_rs::SessionCreateParamsMetadata::String("string".to_owned()))])), namespace: Some("namespace".to_owned()), optimize_bandwidth: Some(steel_rs::SessionCreateParamsOptimizeBandwidth::Bool(true)), persist_profile: Some(true), profile_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()), project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()), proxy_url: Some("https://example.com".to_owned()), recording: Some(true), region: Some(serde_json::json!({})), session_context: Some(steel_rs::SessionCreateParamsSessionContext { cookies: Some(vec![steel_rs::SessionCreateParamsSessionContextCookie { name: "name".to_owned(), value: "value".to_owned(), domain: Some("domain".to_owned()), expires: Some(0.0), http_only: Some(true), partition_key: Some(steel_rs::SessionCreateParamsSessionContextCookiePartitionKey { has_cross_site_ancestor: true, top_level_site: "topLevelSite".to_owned() }), path: Some("path".to_owned()), priority: Some(steel_rs::SessionCreateParamsSessionContextCookiePriority::Low), same_party: Some(true), same_site: Some(steel_rs::SessionCreateParamsSessionContextCookieSameSite::Strict), secure: Some(true), session: Some(true), size: Some(0.0), source_port: Some(0.0), source_scheme: Some(steel_rs::SessionCreateParamsSessionContextCookieSourceScheme::Unset), url: Some("url".to_owned()) }]), indexed_db: Some(std::collections::HashMap::from([("foo".to_owned(), vec![steel_rs::SessionCreateParamsSessionContextIndexedDb { id: 0.0, data: vec![steel_rs::SessionCreateParamsSessionContextIndexedDbData { id: 0.0, name: "name".to_owned(), records: vec![steel_rs::SessionCreateParamsSessionContextIndexedDbDataRecord { key: serde_json::json!({}), value: serde_json::json!({}), blob_files: Some(vec![steel_rs::SessionCreateParamsSessionContextIndexedDbDataRecordBlobFile { blob_number: 0.0, mime_type: "mimeType".to_owned(), size: 0.0, filename: Some("filename".to_owned()), last_modified: Some("2019-12-27T18:11:19.117Z".to_owned()), path: Some("path".to_owned()) }]) }] }], name: "name".to_owned() }])])), local_storage: Some(std::collections::HashMap::from([("foo".to_owned(), std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]))])), session_storage: Some(std::collections::HashMap::from([("foo".to_owned(), std::collections::HashMap::from([("foo".to_owned(), "string".to_owned())]))])) }), session_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()), solve_captcha: Some(true), stealth_config: Some(steel_rs::SessionCreateParamsStealthConfig { auto_captcha_solving: Some(true), humanize_interactions: Some(true), skip_fingerprint_injection: Some(true) }), timeout: Some(-9007199254740991), use_proxy: Some(serde_json::json!(true)), user_agent: Some("userAgent".to_owned()) }).await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn retrieve() {
    let client = common::client();
    let result = client
        .sessions()
        .retrieve("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client
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
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn list_auto_paging() {
    use futures_util::StreamExt;
    let client = common::client();
    let mut stream =
        std::pin::pin!(client
            .sessions()
            .list_auto_paging(steel_rs::SessionListParams {
                created_after: Some("2019-12-27T18:11:19.117Z".to_owned()),
                created_before: Some("2019-12-27T18:11:19.117Z".to_owned()),
                cursor_id: Some("cursorId".to_owned()),
                limit: Some(1),
                metadata: Some(std::collections::HashMap::from([(
                    "foo".to_owned(),
                    "x".to_owned()
                )])),
                project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
                release_reason: Some(steel_rs::SessionListParamsReleaseReason::UserRequested),
                signal: Some(vec![steel_rs::SessionListParamsSignal::CaptchaEncountered]),
                status: Some(steel_rs::SessionListParamsStatus::Live)
            }));
    let first = stream.next().await;
    assert!(first.is_some_and(|item| item.is_ok()));
}

#[tokio::test]
async fn agent_traces() {
    let client = common::client();
    let result = client
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
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn commands() {
    let client = common::client();
    let result = client
        .sessions()
        .commands(
            "sessionId",
            steel_rs::SessionCommandsParams {
                commands: steel_rs::SessionCommandsParamsCommands::NavigateCommand(
                    steel_rs::SessionCommandsParamsCommandsNavigateCommand {
                        command:
                            steel_rs::SessionCommandsParamsCommandsNavigateCommandCommand::Navigate,
                        url: "x".to_owned(),
                        tab: Some("t321669910225".to_owned()),
                    },
                ),
                after: Some("x".to_owned()),
                events_after: Some("c-321669910225".to_owned()),
                observe: Some(steel_rs::SessionCommandsParamsObserve {
                    mode: steel_rs::SessionCommandsParamsObserveMode::None,
                    base: Some("x".to_owned()),
                    compact: Some(true),
                    depth: Some(0),
                    interactive: Some(true),
                    tab: Some("t321669910225".to_owned()),
                }),
                idempotency_key: Some("idempotency-key".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn computer() {
    let client = common::client();
    let result = client
        .sessions()
        .computer(
            "sessionId",
            steel_rs::SessionComputerParams::MoveMouse(steel_rs::SessionComputerParamsMoveMouse {
                coordinates: vec![0.0, 0.0],
                hold_keys: Some(vec!["string".to_owned()]),
                screenshot: Some(true),
            }),
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn context() {
    let client = common::client();
    let result = client
        .sessions()
        .context("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn cost() {
    let client = common::client();
    let result = client
        .sessions()
        .cost("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn events() {
    let client = common::client();
    let result = client
        .sessions()
        .events(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SessionEventsParams {
                compressed: Some(true),
                limit: Some(1),
                pointer: Some("pointer".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn hls() {
    let server = wiremock::MockServer::start().await;
    wiremock::Mock::given(wiremock::matchers::any())
        .respond_with(wiremock::ResponseTemplate::new(200).set_body_bytes(b"abc".to_vec()))
        .mount(&server)
        .await;
    let client = common::client_for(&server.uri());
    let bytes = client
        .sessions()
        .hls("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await
        .expect("request");
    assert_eq!(&bytes[..], b"abc");
}

#[tokio::test]
async fn live_details() {
    let client = common::client();
    let result = client
        .sessions()
        .live_details("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn logs() {
    let client = common::client();
    let result = client
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
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn release() {
    let client = common::client();
    let result = client
        .sessions()
        .release("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn release_all() {
    let client = common::client();
    let result = client
        .sessions()
        .release_all(steel_rs::SessionReleaseAllParams {
            project_id: Some("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()),
        })
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn replace_metadata() {
    let client = common::client();
    let result = client
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
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn signal_catalog() {
    let client = common::client();
    let result = client.sessions().signal_catalog().await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn update_metadata() {
    let client = common::client();
    let result = client
        .sessions()
        .update_metadata(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SessionUpdateMetadataParams {
                metadata: std::collections::HashMap::from([(
                    "foo".to_owned(),
                    steel_rs::SessionUpdateMetadataParamsMetadata::String("string".to_owned()),
                )]),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
