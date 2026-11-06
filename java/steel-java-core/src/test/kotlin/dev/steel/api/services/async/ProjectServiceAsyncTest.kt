@file:Suppress("DEPRECATION")

package dev.steel.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.projects.ProjectListParams
import dev.steel.api.models.projects.ProjectRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ProjectServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.projects()
        val response =
            service
                .retrieve(
                    ProjectRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
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
        val service = client.projects()
        val response = service.list(ProjectListParams.builder().build()).get()
        response.validate()
    }
}
