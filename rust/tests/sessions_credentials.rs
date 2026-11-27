mod common;

#[tokio::test]
async fn list() {
    let client = common::client();
    let result = client
        .sessions()
        .credentials()
        .list("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn attach() {
    let client = common::client();
    let result = client
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
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn detach() {
    let client = common::client();
    let result = client
        .sessions()
        .credentials()
        .detach(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn detach_binding() {
    let client = common::client();
    let result = client
        .sessions()
        .credentials()
        .detach_binding(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SessionCredentialDetachBindingParams {
                reference: "reference".to_owned(),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn replace() {
    let client = common::client();
    let result = client
        .sessions()
        .credentials()
        .replace(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SessionCredentialReplaceParams {
                bindings: Some(vec![
                    steel_rs::SessionCredentialReplaceParamsBinding::String("string".to_owned()),
                ]),
                credential_ids: Some(vec!["182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e".to_owned()]),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
