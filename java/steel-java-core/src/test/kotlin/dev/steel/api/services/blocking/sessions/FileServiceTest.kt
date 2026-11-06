@file:Suppress("DEPRECATION")

package dev.steel.api.services.blocking.sessions

import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.sessions.files.FileDeleteAllParams
import dev.steel.api.models.sessions.files.FileDeleteParams
import dev.steel.api.models.sessions.files.FileDownloadArchiveParams
import dev.steel.api.models.sessions.files.FileDownloadParams
import dev.steel.api.models.sessions.files.FileListParams
import dev.steel.api.models.sessions.files.FileUploadParams
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
        val service = client.sessions().files()
        val response = service.list(FileListParams.builder().sessionId("sessionId").build())
        response.validate()
    }

    @Test
    fun delete() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().files()
        service.delete(FileDeleteParams.builder().sessionId("sessionId").path("path").build())
    }

    @Test
    fun deleteAll() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().files()
        service.deleteAll(FileDeleteAllParams.builder().sessionId("sessionId").build())
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().files()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))
        val response =
            service.download(
                FileDownloadParams.builder().sessionId("sessionId").path("path").build()
            )
        assertThat(response.body()).hasContent("abc")
    }

    @Test
    fun downloadArchive(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().files()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))
        val response =
            service.downloadArchive(
                FileDownloadArchiveParams.builder().sessionId("sessionId").build()
            )
        assertThat(response.body()).hasContent("abc")
    }

    @Test
    fun upload() {
        val client =
            SteelOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.sessions().files()
        val response =
            service.upload(
                FileUploadParams.builder()
                    .sessionId("sessionId")
                    .file("Example data".byteInputStream())
                    .path("path")
                    .build()
            )
        response.validate()
    }
}
