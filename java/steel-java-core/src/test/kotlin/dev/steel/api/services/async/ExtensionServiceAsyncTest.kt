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
import dev.steel.api.models.extensions.ExtensionDeleteAllParams
import dev.steel.api.models.extensions.ExtensionDeleteParams
import dev.steel.api.models.extensions.ExtensionDownloadParams
import dev.steel.api.models.extensions.ExtensionListParams
import dev.steel.api.models.extensions.ExtensionUpdateParams
import dev.steel.api.models.extensions.ExtensionUploadParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.ResourceLock

@ExtendWith(TestServerExtension::class)
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ExtensionServiceAsyncTest {

    @Test
    fun update() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.extensions()
        val response =
            service
                .update(
                    ExtensionUpdateParams.builder()
                        .extensionId("extensionId")
                        .file("Example data".byteInputStream())
                        .url("https://example.com")
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
        val service = client.extensions()
        val response = service.list(ExtensionListParams.builder().build()).get()
        response.validate()
    }

    @Test
    fun delete() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.extensions()
        val response =
            service.delete(ExtensionDeleteParams.builder().extensionId("extensionId").build()).get()
        response.validate()
    }

    @Test
    fun deleteAll() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.extensions()
        val response = service.deleteAll(ExtensionDeleteAllParams.builder().build()).get()
        response.validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.extensions()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))
        val response =
            service
                .download(ExtensionDownloadParams.builder().extensionId("extensionId").build())
                .get()
        assertThat(response.body()).hasContent("abc")
    }

    @Test
    fun upload() {
        val client =
            SteelOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .steelApiKey("My Steel API Key")
                .build()
        val service = client.extensions()
        val response =
            service
                .upload(
                    ExtensionUploadParams.builder()
                        .file("Example data".byteInputStream())
                        .url("https://example.com")
                        .build()
                )
                .get()
        response.validate()
    }
}
