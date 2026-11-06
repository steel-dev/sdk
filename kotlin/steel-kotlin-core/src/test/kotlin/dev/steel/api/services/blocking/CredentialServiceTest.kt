@file:Suppress("DEPRECATION")

package dev.steel.api.services.blocking

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.core.JsonValue
import dev.steel.api.models.credentials.CredentialCreateParams
import dev.steel.api.models.credentials.CredentialDeleteParams
import dev.steel.api.models.credentials.CredentialListParams
import dev.steel.api.models.credentials.CredentialUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CredentialServiceTest {

    @Test
    fun create() {
        val client =
            SteelOkHttpClient.builder()
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
    fun update() {
        val client =
            SteelOkHttpClient.builder()
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
    fun list() {
        val client =
            SteelOkHttpClient.builder()
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
    fun delete() {
        val client =
            SteelOkHttpClient.builder()
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
