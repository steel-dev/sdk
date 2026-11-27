@file:Suppress("DEPRECATION")

package dev.steel.api.services.blocking

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.vaults.VaultCreateParams
import dev.steel.api.models.vaults.VaultDeleteParams
import dev.steel.api.models.vaults.VaultListParams
import dev.steel.api.models.vaults.VaultRetrieveParams
import dev.steel.api.models.vaults.VaultUpdateParams
import kotlin.io.path.name
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class VaultServiceTest {

    @Test
    fun create() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.vaults()
        val response =
            service.create(
                VaultCreateParams.builder()
                    .name("name")
                    .description("description")
                    .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .build()
            )
        response.validate()
    }

    @Test
    fun retrieve() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.vaults()
        val response =
            service.retrieve(
                VaultRetrieveParams.builder()
                    .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
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
        val service = client.vaults()
        val response =
            service.update(
                VaultUpdateParams.builder()
                    .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .description("description")
                    .name("name")
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
        val service = client.vaults()
        val response =
            service.list(
                VaultListParams.builder().projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
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
        val service = client.vaults()
        service.delete(
            VaultDeleteParams.builder()
                .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                .build()
        )
    }
}
