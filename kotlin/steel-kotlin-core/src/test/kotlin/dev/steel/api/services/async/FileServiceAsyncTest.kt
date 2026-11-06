@file:Suppress("DEPRECATION")

package dev.steel.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.files.FileDeleteParams
import dev.steel.api.models.files.FileDownloadParams
import dev.steel.api.models.files.FileListParams
import dev.steel.api.models.files.FileUploadParams
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.ResourceLock

@ExtendWith(TestServerExtension::class)
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class FileServiceAsyncTest {

    @Test
    fun list() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.files()
            val response = service.list(FileListParams.builder().build())
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
            val service = client.files()
            service.delete(FileDeleteParams.builder().path("path").build())
        }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(wmRuntimeInfo.httpBaseUrl)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.files()
            stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))
            val response = service.download(FileDownloadParams.builder().path("path").build())
            assertThat(response.body()).hasContent("abc")
        }

    @Test
    fun upload() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
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
