package dev.steel.example;

import dev.steel.api.client.SteelClient;
import dev.steel.api.client.okhttp.SteelOkHttpClient;
import dev.steel.api.models.ClientPdfParams;
import dev.steel.api.models.ClientScrapeParams;
import dev.steel.api.models.ClientScrapeResponse;
import dev.steel.api.models.ClientScreenshotParams;

public final class QuickActions {
    private QuickActions() {}

    public static void main(String[] args) {
        SteelClient client = SteelOkHttpClient.fromEnv();
        String url = "https://steel.dev";

        ClientScrapeResponse page = client.scrape(ClientScrapeParams.builder()
                .url(url)
                .addFormat(ClientScrapeParams.Format.MARKDOWN)
                .build());
        String markdown = page.content().markdown().orElse("");
        System.out.println(markdown.substring(0, Math.min(500, markdown.length())));

        String screenshot = client.screenshot(
                        ClientScreenshotParams.builder().url(url).fullPage(true).build())
                .url();
        System.out.println("Screenshot: " + screenshot);

        String pdf = client.pdf(ClientPdfParams.builder().url(url).build()).url();
        System.out.println("PDF: " + pdf);
    }
}
