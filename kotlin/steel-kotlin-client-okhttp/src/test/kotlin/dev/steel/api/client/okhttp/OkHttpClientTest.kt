package dev.steel.api.client.okhttp

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import dev.steel.api.core.http.HttpMethod
import dev.steel.api.core.http.HttpRequest
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class OkHttpClientTest {

    private lateinit var baseUrl: String
    private lateinit var httpClient: OkHttpClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
        httpClient = OkHttpClient.builder().build()
    }

    @Test
    fun executeAsync_whenCancelled_cancelsUnderlyingCall() = runBlocking {
        stubFor(post(urlPathEqualTo("/something")).willReturn(ok().withFixedDelay(5_000)))
        val job =
            launch(start = CoroutineStart.UNDISPATCHED) {
                httpClient.executeAsync(
                    HttpRequest.builder()
                        .method(HttpMethod.POST)
                        .baseUrl(baseUrl)
                        .addPathSegment("something")
                        .build()
                )
            }
        val call = httpClient.okHttpClient.dispatcher.runningCalls().single()

        job.cancelAndJoin()

        assertThat(call.isCanceled()).isTrue()
    }
}
