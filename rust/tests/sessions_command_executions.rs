mod common;

#[tokio::test]
async fn retrieve() {
    let client = common::client();
    let result = client
        .sessions()
        .command_executions()
        .retrieve("sessionId", "executionId")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn cancel() {
    let client = common::client();
    let result = client
        .sessions()
        .command_executions()
        .cancel("sessionId", "executionId")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
