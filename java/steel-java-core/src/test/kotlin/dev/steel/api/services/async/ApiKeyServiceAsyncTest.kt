@file:Suppress("DEPRECATION")

package dev.steel.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.apikeys.ApiKeyCreateParams
import kotlin.io.path.name
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ApiKeyServiceAsyncTest {

    @Test
    fun create() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.apiKeys()
        val response =
            service
                .create(
                    ApiKeyCreateParams.builder()
                        .name("name")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .scopeType(ApiKeyCreateParams.ScopeType.PROJECT)
                        .build()
                )
                .get()
        response.validate()
    }
}
