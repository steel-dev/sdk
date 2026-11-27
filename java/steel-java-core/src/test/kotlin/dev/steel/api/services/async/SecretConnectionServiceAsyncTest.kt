@file:Suppress("DEPRECATION")

package dev.steel.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.secretconnections.SecretConnectionCreateParams
import dev.steel.api.models.secretconnections.SecretConnectionDeleteParams
import dev.steel.api.models.secretconnections.SecretConnectionListParams
import dev.steel.api.models.secretconnections.SecretConnectionRetrieveParams
import dev.steel.api.models.secretconnections.SecretConnectionTestParams
import dev.steel.api.models.secretconnections.SecretConnectionUpdateParams
import kotlin.io.path.name
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SecretConnectionServiceAsyncTest {

    @Test
    fun create() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.secretConnections()
        val response =
            service
                .create(
                    SecretConnectionCreateParams.builder()
                        .auth(
                            SecretConnectionCreateParams.Auth.builder()
                                .token("x")
                                .mount("x")
                                .roleId("x")
                                .secretId("x")
                                .serviceAccountToken("x")
                                .type(SecretConnectionCreateParams.Auth.Type.TOKEN)
                                .build()
                        )
                        .name("x")
                        .provider(SecretConnectionCreateParams.Provider._1PASSWORD)
                        .config(
                            SecretConnectionCreateParams.Config.builder()
                                .address("x")
                                .caPem("x")
                                .namespace("x")
                                .build()
                        )
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .tokenBudget(
                            SecretConnectionCreateParams.TokenBudget.builder()
                                .burst(2L)
                                .refillPerSecond(0.001)
                                .build()
                        )
                        .build()
                )
                .get()
        response.validate()
    }

    @Test
    fun retrieve() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.secretConnections()
        val response =
            service
                .retrieve(
                    SecretConnectionRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .get()
        response.validate()
    }

    @Test
    fun update() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.secretConnections()
        val response =
            service
                .update(
                    SecretConnectionUpdateParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .auth(
                            SecretConnectionUpdateParams.Auth.builder()
                                .token("x")
                                .mount("x")
                                .roleId("x")
                                .secretId("x")
                                .serviceAccountToken("x")
                                .type(SecretConnectionUpdateParams.Auth.Type.TOKEN)
                                .build()
                        )
                        .config(
                            SecretConnectionUpdateParams.Config.builder()
                                .address("x")
                                .caPem("x")
                                .namespace("x")
                                .build()
                        )
                        .enabled(true)
                        .name("x")
                        .tokenBudget(
                            SecretConnectionUpdateParams.TokenBudget.builder()
                                .burst(2L)
                                .refillPerSecond(0.001)
                                .build()
                        )
                        .build()
                )
                .get()
        response.validate()
    }

    @Test
    fun list() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.secretConnections()
        val response =
            service
                .list(
                    SecretConnectionListParams.builder()
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .get()
        response.validate()
    }

    @Test
    fun delete() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.secretConnections()
        service
            .delete(
                SecretConnectionDeleteParams.builder()
                    .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .build()
            )
            .get()
    }

    @Test
    fun test() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.secretConnections()
        service
            .test(
                SecretConnectionTestParams.builder()
                    .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .build()
            )
            .get()
    }
}
