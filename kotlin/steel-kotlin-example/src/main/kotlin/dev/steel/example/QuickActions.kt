package dev.steel.example

import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.ClientPdfParams
import dev.steel.api.models.ClientScrapeParams
import dev.steel.api.models.ClientScreenshotParams

fun main() {
    val client = SteelOkHttpClient.fromEnv()
    val url = "https://steel.dev"

    val page =
        client.scrape(
            ClientScrapeParams.builder()
                .url(url)
                .addFormat(ClientScrapeParams.Format.MARKDOWN)
                .build()
        )
    println(page.content().markdown()?.take(500))

    val screenshot =
        client.screenshot(ClientScreenshotParams.builder().url(url).fullPage(true).build())
    println("Screenshot: ${screenshot.url()}")

    val pdf = client.pdf(ClientPdfParams.builder().url(url).build())
    println("PDF: ${pdf.url()}")
}
