@file:Suppress("DEPRECATION")

package dev.steel.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.core.JsonValue
import dev.steel.api.models.credentials.CredentialCreateParams
import dev.steel.api.models.credentials.CredentialDeleteParams
import dev.steel.api.models.credentials.CredentialListParams
import dev.steel.api.models.credentials.CredentialUpdateParams
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CredentialServiceAsyncTest {

    @Test
    fun create() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.credentials()
            val response =
                service.create(
                    CredentialCreateParams.builder()
                        .value(
                            CredentialCreateParams.Value.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .label("label")
                        .namespace("namespace")
                        .origin("origin")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun update() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.credentials()
            val response =
                service.update(
                    CredentialUpdateParams.builder()
                        .label("label")
                        .namespace("namespace")
                        .origin("origin")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .value(
                            CredentialUpdateParams.Value.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
            response.validate()
        }

    @Test
    fun list() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.credentials()
            val response =
                service.list(
                    CredentialListParams.builder()
                        .namespace("namespace")
                        .origin("origin")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun delete() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.credentials()
            val response =
                service.delete(
                    CredentialDeleteParams.builder()
                        .origin("origin")
                        .namespace("namespace")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }
}
