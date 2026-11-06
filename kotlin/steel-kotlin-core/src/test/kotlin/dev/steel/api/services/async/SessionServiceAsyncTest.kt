@file:Suppress("DEPRECATION")

package dev.steel.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.core.JsonValue
import dev.steel.api.models.sessions.SessionAgentTracesParams
import dev.steel.api.models.sessions.SessionCommandsParams
import dev.steel.api.models.sessions.SessionComputerParams
import dev.steel.api.models.sessions.SessionContextParams
import dev.steel.api.models.sessions.SessionCostParams
import dev.steel.api.models.sessions.SessionCreateParams
import dev.steel.api.models.sessions.SessionEventsParams
import dev.steel.api.models.sessions.SessionHlsParams
import dev.steel.api.models.sessions.SessionListParams
import dev.steel.api.models.sessions.SessionLiveDetailsParams
import dev.steel.api.models.sessions.SessionLogsParams
import dev.steel.api.models.sessions.SessionReleaseAllParams
import dev.steel.api.models.sessions.SessionReleaseParams
import dev.steel.api.models.sessions.SessionReplaceMetadataParams
import dev.steel.api.models.sessions.SessionRetrieveParams
import dev.steel.api.models.sessions.SessionSignalCatalogParams
import dev.steel.api.models.sessions.SessionUpdateMetadataParams
import java.time.OffsetDateTime
import kotlin.io.path.name
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.ResourceLock

@ExtendWith(TestServerExtension::class)
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class SessionServiceAsyncTest {

    @Test
    fun create() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.create(
                    SessionCreateParams.builder()
                        .blockAds(true)
                        .caCertificates(listOf("string"))
                        .concurrency(-9007199254740991L)
                        .credentials(
                            SessionCreateParams.Credentials.builder()
                                .autoSubmit(true)
                                .blurFields(true)
                                .credentialProvider("credentialProvider")
                                .exactOrigin(true)
                                .origins(listOf("string"))
                                .build()
                        )
                        .debugConfig(
                            SessionCreateParams.DebugConfig.builder()
                                .interactive(true)
                                .systemCursor(true)
                                .build()
                        )
                        .deviceConfig(
                            SessionCreateParams.DeviceConfig.builder()
                                .device(SessionCreateParams.DeviceConfig.Device.DESKTOP)
                                .build()
                        )
                        .dimensions(
                            SessionCreateParams.Dimensions.builder()
                                .height(-9007199254740991L)
                                .width(-9007199254740991L)
                                .build()
                        )
                        .experimentalFeatures(listOf("string"))
                        .extensionIds(listOf("string"))
                        .fullscreen(true)
                        .headless(true)
                        .inactivityTimeout(1L)
                        .isSelenium(true)
                        .metadata(
                            SessionCreateParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .namespace("namespace")
                        .optimizeBandwidth(true)
                        .persistProfile(true)
                        .profileId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .proxyUrl("https://example.com")
                        .recording(true)
                        .region(JsonValue.from(mapOf<String, Any?>()))
                        .sessionContext(
                            SessionCreateParams.SessionContext.builder()
                                .cookies(
                                    listOf(
                                        SessionCreateParams.SessionContext.Cookie.builder()
                                            .name("name")
                                            .value("value")
                                            .domain("domain")
                                            .expires(0.0)
                                            .httpOnly(true)
                                            .partitionKey(
                                                SessionCreateParams.SessionContext.Cookie
                                                    .PartitionKey
                                                    .builder()
                                                    .hasCrossSiteAncestor(true)
                                                    .topLevelSite("topLevelSite")
                                                    .build()
                                            )
                                            .path("path")
                                            .priority(
                                                SessionCreateParams.SessionContext.Cookie.Priority
                                                    .LOW
                                            )
                                            .sameParty(true)
                                            .sameSite(
                                                SessionCreateParams.SessionContext.Cookie.SameSite
                                                    .STRICT
                                            )
                                            .secure(true)
                                            .session(true)
                                            .size(0.0)
                                            .sourcePort(0.0)
                                            .sourceScheme(
                                                SessionCreateParams.SessionContext.Cookie
                                                    .SourceScheme
                                                    .UNSET
                                            )
                                            .url("url")
                                            .build()
                                    )
                                )
                                .indexedDb(
                                    SessionCreateParams.SessionContext.IndexedDb.builder()
                                        .putAdditionalProperty(
                                            "foo",
                                            JsonValue.from(
                                                listOf<Any?>(
                                                    mapOf<String, Any?>(
                                                        "id" to 0,
                                                        "data" to
                                                            listOf<Any?>(
                                                                mapOf<String, Any?>(
                                                                    "id" to 0,
                                                                    "name" to "name",
                                                                    "records" to
                                                                        listOf<Any?>(
                                                                            mapOf<String, Any?>(
                                                                                "key" to
                                                                                    mapOf<
                                                                                        String,
                                                                                        Any?,
                                                                                    >(),
                                                                                "value" to
                                                                                    mapOf<
                                                                                        String,
                                                                                        Any?,
                                                                                    >(),
                                                                                "blobFiles" to
                                                                                    listOf<Any?>(
                                                                                        mapOf<
                                                                                            String,
                                                                                            Any?,
                                                                                        >(
                                                                                            "blobNumber" to
                                                                                                0,
                                                                                            "mimeType" to
                                                                                                "mimeType",
                                                                                            "size" to
                                                                                                0,
                                                                                            "filename" to
                                                                                                "filename",
                                                                                            "lastModified" to
                                                                                                "2019-12-27T18:11:19.117Z",
                                                                                            "path" to
                                                                                                "path",
                                                                                        )
                                                                                    ),
                                                                            )
                                                                        ),
                                                                )
                                                            ),
                                                        "name" to "name",
                                                    )
                                                )
                                            ),
                                        )
                                        .build()
                                )
                                .localStorage(
                                    SessionCreateParams.SessionContext.LocalStorage.builder()
                                        .putAdditionalProperty(
                                            "foo",
                                            JsonValue.from(mapOf<String, Any?>("foo" to "string")),
                                        )
                                        .build()
                                )
                                .sessionStorage(
                                    SessionCreateParams.SessionContext.SessionStorage.builder()
                                        .putAdditionalProperty(
                                            "foo",
                                            JsonValue.from(mapOf<String, Any?>("foo" to "string")),
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .solveCaptcha(true)
                        .stealthConfig(
                            SessionCreateParams.StealthConfig.builder()
                                .autoCaptchaSolving(true)
                                .humanizeInteractions(true)
                                .skipFingerprintInjection(true)
                                .build()
                        )
                        .timeout(-9007199254740991L)
                        .useProxy(true)
                        .userAgent("userAgent")
                        .build()
                )
            response.validate()
        }

    @Test
    fun retrieve() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.retrieve(
                    SessionRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
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
            val service = client.sessions()
            val page =
                service.list(
                    SessionListParams.builder()
                        .createdAfter(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdBefore(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .cursorId("cursorId")
                        .limit(1L)
                        .metadata(
                            SessionListParams.Metadata.builder()
                                .putAdditionalProperty("foo", "x")
                                .build()
                        )
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .releaseReason(SessionListParams.ReleaseReason.USER_REQUESTED)
                        .signal(listOf(SessionListParams.Signal.CAPTCHA_ENCOUNTERED))
                        .status(SessionListParams.Status.LIVE)
                        .build()
                )
            page.response().validate()
        }

    @Test
    fun agentTraces() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.agentTraces(
                    SessionAgentTracesParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .endTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .eventTypes(listOf("string"))
                        .limit(1L)
                        .namespace("namespace")
                        .offset(0L)
                        .startTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
            response.validate()
        }

    @Test
    fun commands() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .idempotencyKey("idempotency-key")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .tab("t321669910225")
                                .build()
                        )
                        .after("x")
                        .eventsAfter("c-321669910225")
                        .observe(
                            SessionCommandsParams.Observe.builder()
                                .mode(SessionCommandsParams.Observe.Mode.NONE)
                                .base("x")
                                .compact(true)
                                .depth(0L)
                                .interactive(true)
                                .tab("t321669910225")
                                .build()
                        )
                        .build()
                )
            response.validate()
        }

    @Test
    fun computer() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.computer(
                    SessionComputerParams.builder()
                        .sessionId("sessionId")
                        .body(
                            SessionComputerParams.Body.ofMoveMouse(
                                SessionComputerParams.Body.MoveMouse.builder()
                                    .coordinates(listOf(0.0, 0.0))
                                    .holdKeys(listOf("string"))
                                    .screenshot(true)
                                    .build()
                            )
                        )
                        .build()
                )
            response.validate()
        }

    @Test
    fun context() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.context(
                    SessionContextParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun cost() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.cost(
                    SessionCostParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
            response.validate()
        }

    @Test
    fun events() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            service.events(
                SessionEventsParams.builder()
                    .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                    .compressed(true)
                    .limit(1L)
                    .pointer("pointer")
                    .build()
            )
        }

    @Test
    fun hls(wmRuntimeInfo: WireMockRuntimeInfo) =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(wmRuntimeInfo.httpBaseUrl)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))
            val response =
                service.hls(
                    SessionHlsParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
            assertThat(response.body()).hasContent("abc")
        }

    @Test
    fun liveDetails() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.liveDetails(
                    SessionLiveDetailsParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun logs() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.logs(
                    SessionLogsParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .endTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .eventTypes(listOf("string"))
                        .limit(1L)
                        .namespace("namespace")
                        .offset(0L)
                        .startTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
            response.validate()
        }

    @Test
    fun release() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.release(
                    SessionReleaseParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun releaseAll() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.releaseAll(
                    SessionReleaseAllParams.builder()
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
            response.validate()
        }

    @Test
    fun replaceMetadata() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.replaceMetadata(
                    SessionReplaceMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionReplaceMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
            response.validate()
        }

    @Test
    fun signalCatalog() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response = service.signalCatalog(SessionSignalCatalogParams.builder().build())
            response.validate()
        }

    @Test
    fun updateMetadata() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions()
            val response =
                service.updateMetadata(
                    SessionUpdateMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionUpdateMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
            response.validate()
        }
}
