@file:Suppress("DEPRECATION")

package dev.steel.api.services.blocking

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.projects.ProjectListParams
import dev.steel.api.models.projects.ProjectRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ProjectServiceTest {

    @Test
    fun retrieve() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.projects()
        val response =
            service.retrieve(
                ProjectRetrieveParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
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
        val service = client.projects()
        val response = service.list(ProjectListParams.builder().build())
        response.validate()
    }
}
