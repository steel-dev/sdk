@file:Suppress("DEPRECATION")

package dev.steel.api.services.blocking

import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.files.FileDeleteParams
import dev.steel.api.models.files.FileDownloadParams
import dev.steel.api.models.files.FileListParams
import dev.steel.api.models.files.FileUploadParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.ResourceLock

@ExtendWith(TestServerExtension::class)
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class FileServiceTest {

    @Test
    fun list() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.files()
        val response = service.list(FileListParams.builder().build())
        response.validate()
    }

    @Test
    fun delete() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.files()
        service.delete(FileDeleteParams.builder().path("path").build())
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.files()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))
        val response = service.download(FileDownloadParams.builder().path("path").build())
        assertThat(response.body()).hasContent("abc")
    }

    @Test
    fun upload() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.files()
        val response =
            service.upload(
                FileUploadParams.builder()
                    .file("Example data".byteInputStream())
                    .path("path")
                    .build()
            )
        response.validate()
    }
}
