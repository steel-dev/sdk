#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = steel_rs::Client::new();
    let url = "https://steel.dev";

    let page = client
        .scrape(steel_rs::ScrapeParams {
            format: Some(vec![steel_rs::ScrapeParamsFormat::Markdown]),
            ..steel_rs::ScrapeParams::new(url.to_owned())
        })
        .await?;
    let markdown = page.content.markdown.unwrap_or_default();
    println!("{}", markdown.chars().take(500).collect::<String>());

    let screenshot = client
        .screenshot(steel_rs::ScreenshotParams {
            full_page: Some(true),
            ..steel_rs::ScreenshotParams::new(url.to_owned())
        })
        .await?;
    println!("Screenshot: {}", screenshot.url);

    let pdf = client.pdf(steel_rs::PdfParams::new(url.to_owned())).await?;
    println!("PDF: {}", pdf.url);
    Ok(())
}
