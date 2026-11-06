@file:Suppress("DEPRECATION")

package dev.steel.api.services.blocking.sessions

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveParams
import dev.steel.api.models.sessions.captchas.CaptchaStatusParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CaptchaServiceTest {

    @Test
    fun solve() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().captchas()
        val response =
            service.solve(
                CaptchaSolveParams.builder()
                    .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .pageId("pageId")
                    .taskId("taskId")
                    .url("url")
                    .build()
            )
        response.validate()
    }

    @Test
    fun solveImage() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().captchas()
        val response =
            service.solveImage(
                CaptchaSolveImageParams.builder()
                    .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .imageXPath("imageXPath")
                    .inputXPath("inputXPath")
                    .url("url")
                    .build()
            )
        response.validate()
    }

    @Test
    fun status() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().captchas()
        val response =
            service.status(
                CaptchaStatusParams.builder()
                    .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .build()
            )
        response.forEach { it.validate() }
    }
}
