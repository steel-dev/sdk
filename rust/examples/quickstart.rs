#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = steel_rs::Client::new();

    let session = client
        .sessions()
        .create(steel_rs::SessionCreateParams {
            inactivity_timeout: Some(3 * 60 * 1000),
            ..Default::default()
        })
        .await?;
    println!("Watch the session live at {}", session.session_viewer_url);
    println!(
        "Connect Playwright or Puppeteer to {}&apiKey=<your API key>",
        session.websocket_url
    );

    client.sessions().release(&session.id).await?;
    Ok(())
}
