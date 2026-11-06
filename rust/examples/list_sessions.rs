use futures_util::StreamExt;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = steel_rs::Client::new();

    let sessions = client
        .sessions()
        .list_auto_paging(steel_rs::SessionListParams {
            status: Some(steel_rs::SessionListParamsStatus::Live),
            ..Default::default()
        });
    let mut sessions = std::pin::pin!(sessions);
    while let Some(session) = sessions.next().await {
        let session = session?;
        println!("{} started at {}", session.id, session.created_at);
    }
    Ok(())
}
