@file:Suppress("DEPRECATION")

package dev.steel.api.client

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.core.JsonValue
import dev.steel.api.models.ClientPdfParams
import dev.steel.api.models.ClientScrapeParams
import dev.steel.api.models.ClientScreenshotParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SteelClientTest {

    @Test
    fun pdf() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client
        val response =
            service.pdf(
                ClientPdfParams.builder()
                    .url("https://example.com")
                    .delay(0.0)
                    .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .region(JsonValue.from(mapOf<String, Any?>()))
                    .useProxy(true)
                    .build()
            )
        response.validate()
    }

    @Test
    fun scrape() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client
        val response =
            service.scrape(
                ClientScrapeParams.builder()
                    .url("url")
                    .delay(0.0)
                    .format(listOf(ClientScrapeParams.Format.HTML))
                    .pdf(true)
                    .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .region(JsonValue.from(mapOf<String, Any?>()))
                    .screenshot(true)
                    .useProxy(true)
                    .build()
            )
        response.validate()
    }

    @Test
    fun screenshot() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client
        val response =
            service.screenshot(
                ClientScreenshotParams.builder()
                    .url("https://example.com")
                    .delay(0.0)
                    .fullPage(true)
                    .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .region(JsonValue.from(mapOf<String, Any?>()))
                    .useProxy(true)
                    .build()
            )
        response.validate()
    }
}
