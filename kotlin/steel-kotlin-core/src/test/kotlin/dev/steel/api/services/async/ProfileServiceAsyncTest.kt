@file:Suppress("DEPRECATION")

package dev.steel.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.profiles.ProfileCreateParams
import dev.steel.api.models.profiles.ProfileGetParams
import dev.steel.api.models.profiles.ProfileListParams
import dev.steel.api.models.profiles.ProfileUpdateParams
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ProfileServiceAsyncTest {

    @Test
    fun create() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.profiles()
            val response =
                service.create(
                    ProfileCreateParams.builder()
                        .userDataDir("Example data".byteInputStream())
                        .dimensions(
                            ProfileCreateParams.Dimensions.builder().height(0.0).width(0.0).build()
                        )
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .proxyUrl("https://example.com")
                        .useProxy("useProxy")
                        .userAgent("userAgent")
                        .build()
                )
            response.validate()
        }

    @Test
    fun update() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.profiles()
            val response =
                service.update(
                    ProfileUpdateParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .queryProjectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .userDataDir("Example data".byteInputStream())
                        .dimensions(
                            ProfileUpdateParams.Dimensions.builder().height(0.0).width(0.0).build()
                        )
                        .bodyProjectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .proxyUrl("https://example.com")
                        .useProxy("useProxy")
                        .userAgent("userAgent")
                        .build()
                )
            response.validate()
        }

    @Test
    fun list() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.profiles()
            val response =
                service.list(
                    ProfileListParams.builder()
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun get() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.profiles()
            val response =
                service.get(
                    ProfileGetParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }
}
