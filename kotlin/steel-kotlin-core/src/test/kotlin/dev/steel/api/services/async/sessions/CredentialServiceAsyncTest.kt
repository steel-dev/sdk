@file:Suppress("DEPRECATION")

package dev.steel.api.services.async.sessions

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.sessions.credentials.CredentialAttachParams
import dev.steel.api.models.sessions.credentials.CredentialDetachBindingParams
import dev.steel.api.models.sessions.credentials.CredentialDetachParams
import dev.steel.api.models.sessions.credentials.CredentialListParams
import dev.steel.api.models.sessions.credentials.CredentialReplaceParams
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CredentialServiceAsyncTest {

    @Test
    fun list() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions().credentials()
            val response =
                service.list(
                    CredentialListParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun attach() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions().credentials()
            val response =
                service.attach(
                    CredentialAttachParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .bindings(listOf(CredentialAttachParams.Binding.ofString("string")))
                        .credentialIds(listOf("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"))
                        .build()
                )
            response.validate()
        }

    @Test
    fun detach() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions().credentials()
            val response =
                service.detach(
                    CredentialDetachParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .credentialId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun detachBinding() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions().credentials()
            val response =
                service.detachBinding(
                    CredentialDetachBindingParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .reference("reference")
                        .build()
                )
            response.validate()
        }

    @Test
    fun replace() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions().credentials()
            val response =
                service.replace(
                    CredentialReplaceParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .bindings(listOf(CredentialReplaceParams.Binding.ofString("string")))
                        .credentialIds(listOf("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"))
                        .build()
                )
            response.validate()
        }
}
