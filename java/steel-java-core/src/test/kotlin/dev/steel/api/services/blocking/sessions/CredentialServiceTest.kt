@file:Suppress("DEPRECATION")

package dev.steel.api.services.blocking.sessions

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.sessions.credentials.CredentialAttachParams
import dev.steel.api.models.sessions.credentials.CredentialDetachBindingParams
import dev.steel.api.models.sessions.credentials.CredentialDetachParams
import dev.steel.api.models.sessions.credentials.CredentialListParams
import dev.steel.api.models.sessions.credentials.CredentialReplaceParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CredentialServiceTest {

    @Test
    fun list() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().credentials()
        val response =
            service.list(
                CredentialListParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
            )
        response.validate()
    }

    @Test
    fun attach() {
        val client =
            SteelOkHttpClient.builder()
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
    fun detach() {
        val client =
            SteelOkHttpClient.builder()
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
    fun detachBinding() {
        val client =
            SteelOkHttpClient.builder()
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
    fun replace() {
        val client =
            SteelOkHttpClient.builder()
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
