@file:Suppress("DEPRECATION")

package dev.steel.api.conformance

import dev.steel.api.client.SteelClient
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.core.JsonValue
import dev.steel.api.models.ClientPdfParams
import dev.steel.api.models.ClientScrapeParams
import dev.steel.api.models.ClientScreenshotParams
import dev.steel.api.models.apikeys.ApiKeyCreateParams
import dev.steel.api.models.credentials.CredentialCreateParams
import dev.steel.api.models.credentials.CredentialDeleteParams
import dev.steel.api.models.credentials.CredentialListParams
import dev.steel.api.models.credentials.CredentialUpdateParams
import dev.steel.api.models.extensions.ExtensionDeleteAllParams
import dev.steel.api.models.extensions.ExtensionDeleteParams
import dev.steel.api.models.extensions.ExtensionDownloadParams
import dev.steel.api.models.extensions.ExtensionListParams
import dev.steel.api.models.extensions.ExtensionUpdateParams
import dev.steel.api.models.extensions.ExtensionUploadParams
import dev.steel.api.models.files.FileDeleteParams
import dev.steel.api.models.files.FileDownloadParams
import dev.steel.api.models.files.FileListParams
import dev.steel.api.models.files.FileUploadParams
import dev.steel.api.models.profiles.ProfileCreateParams
import dev.steel.api.models.profiles.ProfileGetParams
import dev.steel.api.models.profiles.ProfileListParams
import dev.steel.api.models.profiles.ProfileUpdateParams
import dev.steel.api.models.projects.ProjectListParams
import dev.steel.api.models.projects.ProjectRetrieveParams
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
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveParams
import dev.steel.api.models.sessions.captchas.CaptchaStatusParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveParams
import dev.steel.api.models.sessions.files.FileDeleteAllParams
import dev.steel.api.models.sessions.files.FileDownloadArchiveParams
import java.time.OffsetDateTime
import kotlin.io.path.name
import org.junit.jupiter.api.Test

internal class ConformanceTest {

    private fun make(baseUrl: String, apiKey: String): SteelClient =
        SteelOkHttpClient.builder().baseUrl(baseUrl).steelApiKey(apiKey).maxRetries(0).build()

    @Test
    fun pdfFull() {
        Harness.conform("pdf/full", ::make) { client ->
            client
                .pdf(
                    ClientPdfParams.builder()
                        .url("https://example.com")
                        .delay(0.0)
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .region(JsonValue.from(mapOf<String, Any?>()))
                        .useProxy(true)
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun pdfRequired() {
        Harness.conform("pdf/required", ::make) { client ->
            client.pdf(ClientPdfParams.builder().url("https://example.com").build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun pdfNulls() {
        Harness.conform("pdf/nulls", ::make) { client ->
            client.pdf(ClientPdfParams.builder().url("https://example.com").build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun scrapeFull() {
        Harness.conform("scrape/full", ::make) { client ->
            client
                .scrape(
                    ClientScrapeParams.builder()
                        .url("url")
                        .delay(0.0)
                        .format(listOf(ClientScrapeParams.Format.HTML))
                        .pdf(true)
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .region(JsonValue.from(mapOf<String, Any?>()))
                        .screenshot(true)
                        .useProxy(true)
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun scrapeRequired() {
        Harness.conform("scrape/required", ::make) { client ->
            client.scrape(ClientScrapeParams.builder().url("url").build()).also { it.validate() }
        }
    }

    @Test
    fun scrapeNulls() {
        Harness.conform("scrape/nulls", ::make) { client ->
            client.scrape(ClientScrapeParams.builder().url("url").build()).also { it.validate() }
        }
    }

    @Test
    fun screenshotFull() {
        Harness.conform("screenshot/full", ::make) { client ->
            client
                .screenshot(
                    ClientScreenshotParams.builder()
                        .url("https://example.com")
                        .delay(0.0)
                        .fullPage(true)
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .region(JsonValue.from(mapOf<String, Any?>()))
                        .useProxy(true)
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun screenshotRequired() {
        Harness.conform("screenshot/required", ::make) { client ->
            client
                .screenshot(ClientScreenshotParams.builder().url("https://example.com").build())
                .also { it.validate() }
        }
    }

    @Test
    fun screenshotNulls() {
        Harness.conform("screenshot/nulls", ::make) { client ->
            client
                .screenshot(ClientScreenshotParams.builder().url("https://example.com").build())
                .also { it.validate() }
        }
    }

    @Test
    fun apiKeysCreateFull() {
        Harness.conform("api_keys.create/full", ::make) { client ->
            client
                .apiKeys()
                .create(
                    ApiKeyCreateParams.builder()
                        .name("name")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .scopeType(ApiKeyCreateParams.ScopeType.PROJECT)
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun apiKeysCreateRequired() {
        Harness.conform("api_keys.create/required", ::make) { client ->
            client.apiKeys().create(ApiKeyCreateParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun apiKeysCreateNulls() {
        Harness.conform("api_keys.create/nulls", ::make) { client ->
            client.apiKeys().create(ApiKeyCreateParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun apiKeysCreateUnknown() {
        Harness.conform("api_keys.create/unknown", ::make) { client ->
            client.apiKeys().create(ApiKeyCreateParams.builder().build())
        }
    }

    @Test
    fun credentialsCreateFull() {
        Harness.conform("credentials.create/full", ::make) { client ->
            client
                .credentials()
                .create(
                    CredentialCreateParams.builder()
                        .value(
                            CredentialCreateParams.Value.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .label("label")
                        .namespace("namespace")
                        .origin("origin")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsCreateRequired() {
        Harness.conform("credentials.create/required", ::make) { client ->
            client
                .credentials()
                .create(
                    CredentialCreateParams.builder()
                        .value(
                            CredentialCreateParams.Value.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsCreateNulls() {
        Harness.conform("credentials.create/nulls", ::make) { client ->
            client
                .credentials()
                .create(
                    CredentialCreateParams.builder()
                        .value(
                            CredentialCreateParams.Value.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsUpdateFull() {
        Harness.conform("credentials.update/full", ::make) { client ->
            client
                .credentials()
                .update(
                    CredentialUpdateParams.builder()
                        .label("label")
                        .namespace("namespace")
                        .origin("origin")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .value(
                            CredentialUpdateParams.Value.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsUpdateRequired() {
        Harness.conform("credentials.update/required", ::make) { client ->
            client.credentials().update(CredentialUpdateParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun credentialsUpdateNulls() {
        Harness.conform("credentials.update/nulls", ::make) { client ->
            client.credentials().update(CredentialUpdateParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun credentialsListFull() {
        Harness.conform("credentials.list/full", ::make) { client ->
            client
                .credentials()
                .list(
                    CredentialListParams.builder()
                        .namespace("namespace")
                        .origin("origin")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsListRequired() {
        Harness.conform("credentials.list/required", ::make) { client ->
            client.credentials().list(CredentialListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun credentialsListNulls() {
        Harness.conform("credentials.list/nulls", ::make) { client ->
            client.credentials().list(CredentialListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun credentialsListEscaped() {
        Harness.conform("credentials.list/escaped", ::make) { client ->
            client
                .credentials()
                .list(
                    CredentialListParams.builder()
                        .namespace("a b/c?d#e%f+g&h=i é")
                        .origin("a b/c?d#e%f+g&h=i é")
                        .projectId("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsDeleteFull() {
        Harness.conform("credentials.delete/full", ::make) { client ->
            client
                .credentials()
                .delete(
                    CredentialDeleteParams.builder()
                        .origin("origin")
                        .namespace("namespace")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsDeleteRequired() {
        Harness.conform("credentials.delete/required", ::make) { client ->
            client
                .credentials()
                .delete(CredentialDeleteParams.builder().origin("origin").build())
                .also { it.validate() }
        }
    }

    @Test
    fun credentialsDeleteNulls() {
        Harness.conform("credentials.delete/nulls", ::make) { client ->
            client
                .credentials()
                .delete(CredentialDeleteParams.builder().origin("origin").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsUpdateFull() {
        Harness.conform("extensions.update/full", ::make) { client ->
            client
                .extensions()
                .update(
                    ExtensionUpdateParams.builder()
                        .extensionId("extensionId")
                        .file("Example data".byteInputStream())
                        .url("https://example.com")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsUpdateRequired() {
        Harness.conform("extensions.update/required", ::make) { client ->
            client
                .extensions()
                .update(ExtensionUpdateParams.builder().extensionId("extensionId").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsUpdateNulls() {
        Harness.conform("extensions.update/nulls", ::make) { client ->
            client
                .extensions()
                .update(ExtensionUpdateParams.builder().extensionId("extensionId").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsUpdateEscaped() {
        Harness.conform("extensions.update/escaped", ::make) { client ->
            client
                .extensions()
                .update(ExtensionUpdateParams.builder().extensionId("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsListFull() {
        Harness.conform("extensions.list/full", ::make) { client ->
            client.extensions().list(ExtensionListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun extensionsListRequired() {
        Harness.conform("extensions.list/required", ::make) { client ->
            client.extensions().list(ExtensionListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun extensionsListNulls() {
        Harness.conform("extensions.list/nulls", ::make) { client ->
            client.extensions().list(ExtensionListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun extensionsDeleteFull() {
        Harness.conform("extensions.delete/full", ::make) { client ->
            client
                .extensions()
                .delete(ExtensionDeleteParams.builder().extensionId("extensionId").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsDeleteRequired() {
        Harness.conform("extensions.delete/required", ::make) { client ->
            client
                .extensions()
                .delete(ExtensionDeleteParams.builder().extensionId("extensionId").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsDeleteNulls() {
        Harness.conform("extensions.delete/nulls", ::make) { client ->
            client
                .extensions()
                .delete(ExtensionDeleteParams.builder().extensionId("extensionId").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsDeleteEscaped() {
        Harness.conform("extensions.delete/escaped", ::make) { client ->
            client
                .extensions()
                .delete(ExtensionDeleteParams.builder().extensionId("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsDeleteAllFull() {
        Harness.conform("extensions.delete_all/full", ::make) { client ->
            client.extensions().deleteAll(ExtensionDeleteAllParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun extensionsDeleteAllRequired() {
        Harness.conform("extensions.delete_all/required", ::make) { client ->
            client.extensions().deleteAll(ExtensionDeleteAllParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun extensionsDeleteAllNulls() {
        Harness.conform("extensions.delete_all/nulls", ::make) { client ->
            client.extensions().deleteAll(ExtensionDeleteAllParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun extensionsDownloadFull() {
        Harness.conform("extensions.download/full", ::make) { client ->
            client
                .extensions()
                .download(ExtensionDownloadParams.builder().extensionId("extensionId").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun extensionsDownloadRequired() {
        Harness.conform("extensions.download/required", ::make) { client ->
            client
                .extensions()
                .download(ExtensionDownloadParams.builder().extensionId("extensionId").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun extensionsDownloadEscaped() {
        Harness.conform("extensions.download/escaped", ::make) { client ->
            client
                .extensions()
                .download(
                    ExtensionDownloadParams.builder().extensionId("a b/c?d#e%f+g&h=i é").build()
                )
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun extensionsUploadFull() {
        Harness.conform("extensions.upload/full", ::make) { client ->
            client
                .extensions()
                .upload(
                    ExtensionUploadParams.builder()
                        .file("Example data".byteInputStream())
                        .url("https://example.com")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun extensionsUploadRequired() {
        Harness.conform("extensions.upload/required", ::make) { client ->
            client.extensions().upload(ExtensionUploadParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun extensionsUploadNulls() {
        Harness.conform("extensions.upload/nulls", ::make) { client ->
            client.extensions().upload(ExtensionUploadParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun filesListFull() {
        Harness.conform("files.list/full", ::make) { client ->
            client.files().list(FileListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun filesListRequired() {
        Harness.conform("files.list/required", ::make) { client ->
            client.files().list(FileListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun filesListNulls() {
        Harness.conform("files.list/nulls", ::make) { client ->
            client.files().list(FileListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun filesDeleteFull() {
        Harness.conform("files.delete/full", ::make) { client ->
            client.files().delete(FileDeleteParams.builder().path("path").build())
        }
    }

    @Test
    fun filesDeleteRequired() {
        Harness.conform("files.delete/required", ::make) { client ->
            client.files().delete(FileDeleteParams.builder().path("path").build())
        }
    }

    @Test
    fun filesDeleteEscaped() {
        Harness.conform("files.delete/escaped", ::make) { client ->
            client.files().delete(FileDeleteParams.builder().path("a b/c?d#e%f+g&h=i é").build())
        }
    }

    @Test
    fun filesDownloadFull() {
        Harness.conform("files.download/full", ::make) { client ->
            client.files().download(FileDownloadParams.builder().path("path").build()).use {
                it.body().readBytes().toString(Charsets.UTF_8)
            }
        }
    }

    @Test
    fun filesDownloadRequired() {
        Harness.conform("files.download/required", ::make) { client ->
            client.files().download(FileDownloadParams.builder().path("path").build()).use {
                it.body().readBytes().toString(Charsets.UTF_8)
            }
        }
    }

    @Test
    fun filesDownloadEscaped() {
        Harness.conform("files.download/escaped", ::make) { client ->
            client
                .files()
                .download(FileDownloadParams.builder().path("a b/c?d#e%f+g&h=i é").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun filesUploadFull() {
        Harness.conform("files.upload/full", ::make) { client ->
            client
                .files()
                .upload(
                    FileUploadParams.builder()
                        .file("Example data".byteInputStream())
                        .path("path")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun filesUploadRequired() {
        Harness.conform("files.upload/required", ::make) { client ->
            client
                .files()
                .upload(FileUploadParams.builder().file("Example data".byteInputStream()).build())
                .also { it.validate() }
        }
    }

    @Test
    fun filesUploadNulls() {
        Harness.conform("files.upload/nulls", ::make) { client ->
            client
                .files()
                .upload(FileUploadParams.builder().file("Example data".byteInputStream()).build())
                .also { it.validate() }
        }
    }

    @Test
    fun profilesCreateFull() {
        Harness.conform("profiles.create/full", ::make) { client ->
            client
                .profiles()
                .create(
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
                .also { it.validate() }
        }
    }

    @Test
    fun profilesCreateRequired() {
        Harness.conform("profiles.create/required", ::make) { client ->
            client
                .profiles()
                .create(
                    ProfileCreateParams.builder()
                        .userDataDir("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun profilesCreateNulls() {
        Harness.conform("profiles.create/nulls", ::make) { client ->
            client
                .profiles()
                .create(
                    ProfileCreateParams.builder()
                        .userDataDir("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun profilesCreateUnknown() {
        Harness.conform("profiles.create/unknown", ::make) { client ->
            client
                .profiles()
                .create(
                    ProfileCreateParams.builder()
                        .userDataDir("Example data".byteInputStream())
                        .build()
                )
        }
    }

    @Test
    fun profilesUpdateFull() {
        Harness.conform("profiles.update/full", ::make) { client ->
            client
                .profiles()
                .update(
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
                .also { it.validate() }
        }
    }

    @Test
    fun profilesUpdateRequired() {
        Harness.conform("profiles.update/required", ::make) { client ->
            client
                .profiles()
                .update(
                    ProfileUpdateParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .userDataDir("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun profilesUpdateNulls() {
        Harness.conform("profiles.update/nulls", ::make) { client ->
            client
                .profiles()
                .update(
                    ProfileUpdateParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .userDataDir("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun profilesUpdateUnknown() {
        Harness.conform("profiles.update/unknown", ::make) { client ->
            client
                .profiles()
                .update(
                    ProfileUpdateParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .userDataDir("Example data".byteInputStream())
                        .build()
                )
        }
    }

    @Test
    fun profilesUpdateEscaped() {
        Harness.conform("profiles.update/escaped", ::make) { client ->
            client
                .profiles()
                .update(
                    ProfileUpdateParams.builder()
                        .id("a b/c?d#e%f+g&h=i é")
                        .queryProjectId("a b/c?d#e%f+g&h=i é")
                        .userDataDir("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun profilesListFull() {
        Harness.conform("profiles.list/full", ::make) { client ->
            client
                .profiles()
                .list(
                    ProfileListParams.builder()
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun profilesListRequired() {
        Harness.conform("profiles.list/required", ::make) { client ->
            client.profiles().list(ProfileListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun profilesListNulls() {
        Harness.conform("profiles.list/nulls", ::make) { client ->
            client.profiles().list(ProfileListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun profilesListUnknown() {
        Harness.conform("profiles.list/unknown", ::make) { client ->
            client.profiles().list(ProfileListParams.builder().build())
        }
    }

    @Test
    fun profilesListEscaped() {
        Harness.conform("profiles.list/escaped", ::make) { client ->
            client
                .profiles()
                .list(ProfileListParams.builder().projectId("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun profilesGetFull() {
        Harness.conform("profiles.get/full", ::make) { client ->
            client
                .profiles()
                .get(
                    ProfileGetParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun profilesGetRequired() {
        Harness.conform("profiles.get/required", ::make) { client ->
            client
                .profiles()
                .get(ProfileGetParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build())
                .also { it.validate() }
        }
    }

    @Test
    fun profilesGetNulls() {
        Harness.conform("profiles.get/nulls", ::make) { client ->
            client
                .profiles()
                .get(ProfileGetParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build())
                .also { it.validate() }
        }
    }

    @Test
    fun profilesGetUnknown() {
        Harness.conform("profiles.get/unknown", ::make) { client ->
            client
                .profiles()
                .get(ProfileGetParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build())
        }
    }

    @Test
    fun profilesGetEscaped() {
        Harness.conform("profiles.get/escaped", ::make) { client ->
            client
                .profiles()
                .get(
                    ProfileGetParams.builder()
                        .id("a b/c?d#e%f+g&h=i é")
                        .projectId("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun projectsRetrieveFull() {
        Harness.conform("projects.retrieve/full", ::make) { client ->
            client
                .projects()
                .retrieve(
                    ProjectRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun projectsRetrieveRequired() {
        Harness.conform("projects.retrieve/required", ::make) { client ->
            client
                .projects()
                .retrieve(
                    ProjectRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun projectsRetrieveNulls() {
        Harness.conform("projects.retrieve/nulls", ::make) { client ->
            client
                .projects()
                .retrieve(
                    ProjectRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun projectsRetrieveEscaped() {
        Harness.conform("projects.retrieve/escaped", ::make) { client ->
            client
                .projects()
                .retrieve(ProjectRetrieveParams.builder().id("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun projectsListFull() {
        Harness.conform("projects.list/full", ::make) { client ->
            client.projects().list(ProjectListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun projectsListRequired() {
        Harness.conform("projects.list/required", ::make) { client ->
            client.projects().list(ProjectListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun projectsListNulls() {
        Harness.conform("projects.list/nulls", ::make) { client ->
            client.projects().list(ProjectListParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun sessionsCreateFull() {
        Harness.conform("sessions.create/full", ::make) { client ->
            client
                .sessions()
                .create(
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
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCreateRequired() {
        Harness.conform("sessions.create/required", ::make) { client ->
            client.sessions().create(SessionCreateParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun sessionsCreateNulls() {
        Harness.conform("sessions.create/nulls", ::make) { client ->
            client.sessions().create(SessionCreateParams.builder().build()).also { it.validate() }
        }
    }

    @Test
    fun sessionsCreateUnknown() {
        Harness.conform("sessions.create/unknown", ::make) { client ->
            client.sessions().create(SessionCreateParams.builder().build())
        }
    }

    @Test
    fun sessionsRetrieveFull() {
        Harness.conform("sessions.retrieve/full", ::make) { client ->
            client
                .sessions()
                .retrieve(
                    SessionRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsRetrieveRequired() {
        Harness.conform("sessions.retrieve/required", ::make) { client ->
            client
                .sessions()
                .retrieve(
                    SessionRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsRetrieveNulls() {
        Harness.conform("sessions.retrieve/nulls", ::make) { client ->
            client
                .sessions()
                .retrieve(
                    SessionRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsRetrieveUnknown() {
        Harness.conform("sessions.retrieve/unknown", ::make) { client ->
            client
                .sessions()
                .retrieve(
                    SessionRetrieveParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
        }
    }

    @Test
    fun sessionsRetrieveEscaped() {
        Harness.conform("sessions.retrieve/escaped", ::make) { client ->
            client
                .sessions()
                .retrieve(SessionRetrieveParams.builder().id("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsListFull() {
        Harness.conform("sessions.list/full", ::make) { client ->
            client
                .sessions()
                .list(
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
                .response()
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsListRequired() {
        Harness.conform("sessions.list/required", ::make) { client ->
            client.sessions().list(SessionListParams.builder().build()).response().also {
                it.validate()
            }
        }
    }

    @Test
    fun sessionsListNulls() {
        Harness.conform("sessions.list/nulls", ::make) { client ->
            client.sessions().list(SessionListParams.builder().build()).response().also {
                it.validate()
            }
        }
    }

    @Test
    fun sessionsListUnknown() {
        Harness.conform("sessions.list/unknown", ::make) { client ->
            client.sessions().list(SessionListParams.builder().build()).response()
        }
    }

    @Test
    fun sessionsListEscaped() {
        Harness.conform("sessions.list/escaped", ::make) { client ->
            client
                .sessions()
                .list(
                    SessionListParams.builder()
                        .cursorId("a b/c?d#e%f+g&h=i é")
                        .projectId("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .response()
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsListPages() {
        Harness.conform("sessions.list/pages", ::make) { client ->
            client.sessions().list(SessionListParams.builder().build()).autoPager().map { it.id() }
        }
    }

    @Test
    fun sessionsAgentTracesFull() {
        Harness.conform("sessions.agent_traces/full", ::make) { client ->
            client
                .sessions()
                .agentTraces(
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
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsAgentTracesRequired() {
        Harness.conform("sessions.agent_traces/required", ::make) { client ->
            client
                .sessions()
                .agentTraces(
                    SessionAgentTracesParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsAgentTracesNulls() {
        Harness.conform("sessions.agent_traces/nulls", ::make) { client ->
            client
                .sessions()
                .agentTraces(
                    SessionAgentTracesParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsAgentTracesEscaped() {
        Harness.conform("sessions.agent_traces/escaped", ::make) { client ->
            client
                .sessions()
                .agentTraces(
                    SessionAgentTracesParams.builder()
                        .id("a b/c?d#e%f+g&h=i é")
                        .namespace("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsFull() {
        Harness.conform("sessions.commands/full", ::make) { client ->
            client
                .sessions()
                .commands(
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
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsRequired() {
        Harness.conform("sessions.commands/required", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsNulls() {
        Harness.conform("sessions.commands/nulls", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsUnknown() {
        Harness.conform("sessions.commands/unknown", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
        }
    }

    @Test
    fun sessionsCommandsVariant01() {
        Harness.conform("sessions.commands/variant-0-1", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant02() {
        Harness.conform("sessions.commands/variant-0-2", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant03() {
        Harness.conform("sessions.commands/variant-0-3", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant04() {
        Harness.conform("sessions.commands/variant-0-4", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant05() {
        Harness.conform("sessions.commands/variant-0-5", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant06() {
        Harness.conform("sessions.commands/variant-0-6", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant07() {
        Harness.conform("sessions.commands/variant-0-7", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant08() {
        Harness.conform("sessions.commands/variant-0-8", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant09() {
        Harness.conform("sessions.commands/variant-0-9", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant010() {
        Harness.conform("sessions.commands/variant-0-10", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant011() {
        Harness.conform("sessions.commands/variant-0-11", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant012() {
        Harness.conform("sessions.commands/variant-0-12", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant013() {
        Harness.conform("sessions.commands/variant-0-13", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant014() {
        Harness.conform("sessions.commands/variant-0-14", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant015() {
        Harness.conform("sessions.commands/variant-0-15", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant016() {
        Harness.conform("sessions.commands/variant-0-16", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant017() {
        Harness.conform("sessions.commands/variant-0-17", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant018() {
        Harness.conform("sessions.commands/variant-0-18", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant019() {
        Harness.conform("sessions.commands/variant-0-19", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant020() {
        Harness.conform("sessions.commands/variant-0-20", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant021() {
        Harness.conform("sessions.commands/variant-0-21", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant022() {
        Harness.conform("sessions.commands/variant-0-22", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant023() {
        Harness.conform("sessions.commands/variant-0-23", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant024() {
        Harness.conform("sessions.commands/variant-0-24", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant025() {
        Harness.conform("sessions.commands/variant-0-25", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant026() {
        Harness.conform("sessions.commands/variant-0-26", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant027() {
        Harness.conform("sessions.commands/variant-0-27", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsVariant028() {
        Harness.conform("sessions.commands/variant-0-28", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("sessionId")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandsEscaped() {
        Harness.conform("sessions.commands/escaped", ::make) { client ->
            client
                .sessions()
                .commands(
                    SessionCommandsParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .commands(
                            SessionCommandsParams.Commands.NavigateCommand.builder()
                                .url("x")
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsComputerFull() {
        Harness.conform("sessions.computer/full", ::make) { client ->
            client
                .sessions()
                .computer(
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
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsComputerRequired() {
        Harness.conform("sessions.computer/required", ::make) { client ->
            client
                .sessions()
                .computer(
                    SessionComputerParams.builder()
                        .sessionId("sessionId")
                        .body(
                            SessionComputerParams.Body.ofMoveMouse(
                                SessionComputerParams.Body.MoveMouse.builder()
                                    .coordinates(listOf(0.0, 0.0))
                                    .build()
                            )
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsComputerNulls() {
        Harness.conform("sessions.computer/nulls", ::make) { client ->
            client
                .sessions()
                .computer(
                    SessionComputerParams.builder()
                        .sessionId("sessionId")
                        .body(
                            SessionComputerParams.Body.ofMoveMouse(
                                SessionComputerParams.Body.MoveMouse.builder()
                                    .coordinates(listOf(0.0, 0.0))
                                    .build()
                            )
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsComputerEscaped() {
        Harness.conform("sessions.computer/escaped", ::make) { client ->
            client
                .sessions()
                .computer(
                    SessionComputerParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .body(
                            SessionComputerParams.Body.ofMoveMouse(
                                SessionComputerParams.Body.MoveMouse.builder()
                                    .coordinates(listOf(0.0, 0.0))
                                    .build()
                            )
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsContextFull() {
        Harness.conform("sessions.context/full", ::make) { client ->
            client
                .sessions()
                .context(
                    SessionContextParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsContextRequired() {
        Harness.conform("sessions.context/required", ::make) { client ->
            client
                .sessions()
                .context(
                    SessionContextParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsContextNulls() {
        Harness.conform("sessions.context/nulls", ::make) { client ->
            client
                .sessions()
                .context(
                    SessionContextParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsContextUnknown() {
        Harness.conform("sessions.context/unknown", ::make) { client ->
            client
                .sessions()
                .context(
                    SessionContextParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
        }
    }

    @Test
    fun sessionsContextEscaped() {
        Harness.conform("sessions.context/escaped", ::make) { client ->
            client
                .sessions()
                .context(SessionContextParams.builder().id("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCostFull() {
        Harness.conform("sessions.cost/full", ::make) { client ->
            client
                .sessions()
                .cost(
                    SessionCostParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCostRequired() {
        Harness.conform("sessions.cost/required", ::make) { client ->
            client
                .sessions()
                .cost(
                    SessionCostParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCostNulls() {
        Harness.conform("sessions.cost/nulls", ::make) { client ->
            client
                .sessions()
                .cost(
                    SessionCostParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCostUnknown() {
        Harness.conform("sessions.cost/unknown", ::make) { client ->
            client
                .sessions()
                .cost(
                    SessionCostParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
        }
    }

    @Test
    fun sessionsCostEscaped() {
        Harness.conform("sessions.cost/escaped", ::make) { client ->
            client
                .sessions()
                .cost(SessionCostParams.builder().id("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsEventsFull() {
        Harness.conform("sessions.events/full", ::make) { client ->
            client
                .sessions()
                .events(
                    SessionEventsParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .compressed(true)
                        .limit(1L)
                        .pointer("pointer")
                        .build()
                )
        }
    }

    @Test
    fun sessionsEventsRequired() {
        Harness.conform("sessions.events/required", ::make) { client ->
            client
                .sessions()
                .events(
                    SessionEventsParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
        }
    }

    @Test
    fun sessionsEventsNulls() {
        Harness.conform("sessions.events/nulls", ::make) { client ->
            client
                .sessions()
                .events(
                    SessionEventsParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
        }
    }

    @Test
    fun sessionsEventsEscaped() {
        Harness.conform("sessions.events/escaped", ::make) { client ->
            client
                .sessions()
                .events(
                    SessionEventsParams.builder()
                        .id("a b/c?d#e%f+g&h=i é")
                        .pointer("a b/c?d#e%f+g&h=i é")
                        .build()
                )
        }
    }

    @Test
    fun sessionsHlsFull() {
        Harness.conform("sessions.hls/full", ::make) { client ->
            client
                .sessions()
                .hls(SessionHlsParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsHlsRequired() {
        Harness.conform("sessions.hls/required", ::make) { client ->
            client
                .sessions()
                .hls(SessionHlsParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsHlsEscaped() {
        Harness.conform("sessions.hls/escaped", ::make) { client ->
            client
                .sessions()
                .hls(SessionHlsParams.builder().id("a b/c?d#e%f+g&h=i é").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsLiveDetailsFull() {
        Harness.conform("sessions.live_details/full", ::make) { client ->
            client
                .sessions()
                .liveDetails(
                    SessionLiveDetailsParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsLiveDetailsRequired() {
        Harness.conform("sessions.live_details/required", ::make) { client ->
            client
                .sessions()
                .liveDetails(
                    SessionLiveDetailsParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsLiveDetailsNulls() {
        Harness.conform("sessions.live_details/nulls", ::make) { client ->
            client
                .sessions()
                .liveDetails(
                    SessionLiveDetailsParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsLiveDetailsEscaped() {
        Harness.conform("sessions.live_details/escaped", ::make) { client ->
            client
                .sessions()
                .liveDetails(SessionLiveDetailsParams.builder().id("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsLogsFull() {
        Harness.conform("sessions.logs/full", ::make) { client ->
            client
                .sessions()
                .logs(
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
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsLogsRequired() {
        Harness.conform("sessions.logs/required", ::make) { client ->
            client
                .sessions()
                .logs(
                    SessionLogsParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsLogsNulls() {
        Harness.conform("sessions.logs/nulls", ::make) { client ->
            client
                .sessions()
                .logs(
                    SessionLogsParams.builder().id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e").build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsLogsEscaped() {
        Harness.conform("sessions.logs/escaped", ::make) { client ->
            client
                .sessions()
                .logs(
                    SessionLogsParams.builder()
                        .id("a b/c?d#e%f+g&h=i é")
                        .namespace("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReleaseFull() {
        Harness.conform("sessions.release/full", ::make) { client ->
            client
                .sessions()
                .release(
                    SessionReleaseParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReleaseRequired() {
        Harness.conform("sessions.release/required", ::make) { client ->
            client
                .sessions()
                .release(
                    SessionReleaseParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReleaseNulls() {
        Harness.conform("sessions.release/nulls", ::make) { client ->
            client
                .sessions()
                .release(
                    SessionReleaseParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReleaseEscaped() {
        Harness.conform("sessions.release/escaped", ::make) { client ->
            client
                .sessions()
                .release(SessionReleaseParams.builder().id("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReleaseAllFull() {
        Harness.conform("sessions.release_all/full", ::make) { client ->
            client
                .sessions()
                .releaseAll(
                    SessionReleaseAllParams.builder()
                        .projectId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReleaseAllRequired() {
        Harness.conform("sessions.release_all/required", ::make) { client ->
            client.sessions().releaseAll(SessionReleaseAllParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun sessionsReleaseAllNulls() {
        Harness.conform("sessions.release_all/nulls", ::make) { client ->
            client.sessions().releaseAll(SessionReleaseAllParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun sessionsReleaseAllEscaped() {
        Harness.conform("sessions.release_all/escaped", ::make) { client ->
            client
                .sessions()
                .releaseAll(
                    SessionReleaseAllParams.builder().projectId("a b/c?d#e%f+g&h=i é").build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReplaceMetadataFull() {
        Harness.conform("sessions.replace_metadata/full", ::make) { client ->
            client
                .sessions()
                .replaceMetadata(
                    SessionReplaceMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionReplaceMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReplaceMetadataRequired() {
        Harness.conform("sessions.replace_metadata/required", ::make) { client ->
            client
                .sessions()
                .replaceMetadata(
                    SessionReplaceMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionReplaceMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReplaceMetadataNulls() {
        Harness.conform("sessions.replace_metadata/nulls", ::make) { client ->
            client
                .sessions()
                .replaceMetadata(
                    SessionReplaceMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionReplaceMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsReplaceMetadataEscaped() {
        Harness.conform("sessions.replace_metadata/escaped", ::make) { client ->
            client
                .sessions()
                .replaceMetadata(
                    SessionReplaceMetadataParams.builder()
                        .id("a b/c?d#e%f+g&h=i é")
                        .metadata(
                            SessionReplaceMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsSignalCatalogFull() {
        Harness.conform("sessions.signal_catalog/full", ::make) { client ->
            client.sessions().signalCatalog(SessionSignalCatalogParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun sessionsSignalCatalogRequired() {
        Harness.conform("sessions.signal_catalog/required", ::make) { client ->
            client.sessions().signalCatalog(SessionSignalCatalogParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun sessionsSignalCatalogNulls() {
        Harness.conform("sessions.signal_catalog/nulls", ::make) { client ->
            client.sessions().signalCatalog(SessionSignalCatalogParams.builder().build()).also {
                it.validate()
            }
        }
    }

    @Test
    fun sessionsSignalCatalogUnknown() {
        Harness.conform("sessions.signal_catalog/unknown", ::make) { client ->
            client.sessions().signalCatalog(SessionSignalCatalogParams.builder().build())
        }
    }

    @Test
    fun sessionsUpdateMetadataFull() {
        Harness.conform("sessions.update_metadata/full", ::make) { client ->
            client
                .sessions()
                .updateMetadata(
                    SessionUpdateMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionUpdateMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsUpdateMetadataRequired() {
        Harness.conform("sessions.update_metadata/required", ::make) { client ->
            client
                .sessions()
                .updateMetadata(
                    SessionUpdateMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionUpdateMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsUpdateMetadataNulls() {
        Harness.conform("sessions.update_metadata/nulls", ::make) { client ->
            client
                .sessions()
                .updateMetadata(
                    SessionUpdateMetadataParams.builder()
                        .id("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .metadata(
                            SessionUpdateMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from(null))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsUpdateMetadataEscaped() {
        Harness.conform("sessions.update_metadata/escaped", ::make) { client ->
            client
                .sessions()
                .updateMetadata(
                    SessionUpdateMetadataParams.builder()
                        .id("a b/c?d#e%f+g&h=i é")
                        .metadata(
                            SessionUpdateMetadataParams.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveFull() {
        Harness.conform("sessions.captchas.solve/full", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solve(
                    CaptchaSolveParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .pageId("pageId")
                        .taskId("taskId")
                        .url("url")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveRequired() {
        Harness.conform("sessions.captchas.solve/required", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solve(
                    CaptchaSolveParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveNulls() {
        Harness.conform("sessions.captchas.solve/nulls", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solve(
                    CaptchaSolveParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveEscaped() {
        Harness.conform("sessions.captchas.solve/escaped", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solve(CaptchaSolveParams.builder().sessionId("a b/c?d#e%f+g&h=i é").build())
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveImageFull() {
        Harness.conform("sessions.captchas.solve_image/full", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solveImage(
                    CaptchaSolveImageParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .imageXPath("imageXPath")
                        .inputXPath("inputXPath")
                        .url("url")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveImageRequired() {
        Harness.conform("sessions.captchas.solve_image/required", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solveImage(
                    CaptchaSolveImageParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .imageXPath("imageXPath")
                        .inputXPath("inputXPath")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveImageNulls() {
        Harness.conform("sessions.captchas.solve_image/nulls", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solveImage(
                    CaptchaSolveImageParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .imageXPath("imageXPath")
                        .inputXPath("inputXPath")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasSolveImageEscaped() {
        Harness.conform("sessions.captchas.solve_image/escaped", ::make) { client ->
            client
                .sessions()
                .captchas()
                .solveImage(
                    CaptchaSolveImageParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .imageXPath("imageXPath")
                        .inputXPath("inputXPath")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCaptchasStatusFull() {
        Harness.conform("sessions.captchas.status/full", ::make) { client ->
            client
                .sessions()
                .captchas()
                .status(
                    CaptchaStatusParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.forEach { it.validate() } }
        }
    }

    @Test
    fun sessionsCaptchasStatusRequired() {
        Harness.conform("sessions.captchas.status/required", ::make) { client ->
            client
                .sessions()
                .captchas()
                .status(
                    CaptchaStatusParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.forEach { it.validate() } }
        }
    }

    @Test
    fun sessionsCaptchasStatusNulls() {
        Harness.conform("sessions.captchas.status/nulls", ::make) { client ->
            client
                .sessions()
                .captchas()
                .status(
                    CaptchaStatusParams.builder()
                        .sessionId("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
                        .build()
                )
                .also { it.forEach { it.validate() } }
        }
    }

    @Test
    fun sessionsCaptchasStatusEscaped() {
        Harness.conform("sessions.captchas.status/escaped", ::make) { client ->
            client
                .sessions()
                .captchas()
                .status(CaptchaStatusParams.builder().sessionId("a b/c?d#e%f+g&h=i é").build())
                .also { it.forEach { it.validate() } }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveFull() {
        Harness.conform("sessions.command_executions.retrieve/full", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveRequired() {
        Harness.conform("sessions.command_executions.retrieve/required", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveNulls() {
        Harness.conform("sessions.command_executions.retrieve/nulls", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveUnknown() {
        Harness.conform("sessions.command_executions.retrieve/unknown", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant01() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-1", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant02() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-2", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant03() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-3", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant04() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-4", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant05() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-5", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant06() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-6", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant07() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-7", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant08() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-8", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant09() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-9", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant010() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-10", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant011() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-11", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant012() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-12", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant013() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-13", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant014() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-14", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant015() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-15", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant016() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-16", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant017() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-17", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant018() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-18", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant019() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-19", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant020() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-20", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant021() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-21", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant022() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-22", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant023() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-23", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant024() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-24", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant025() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-25", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant026() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-26", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant027() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-27", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveVariant028() {
        Harness.conform("sessions.command_executions.retrieve/variant-0-28", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsRetrieveEscaped() {
        Harness.conform("sessions.command_executions.retrieve/escaped", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .executionId("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelFull() {
        Harness.conform("sessions.command_executions.cancel/full", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelRequired() {
        Harness.conform("sessions.command_executions.cancel/required", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelNulls() {
        Harness.conform("sessions.command_executions.cancel/nulls", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelUnknown() {
        Harness.conform("sessions.command_executions.cancel/unknown", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant01() {
        Harness.conform("sessions.command_executions.cancel/variant-0-1", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant02() {
        Harness.conform("sessions.command_executions.cancel/variant-0-2", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant03() {
        Harness.conform("sessions.command_executions.cancel/variant-0-3", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant04() {
        Harness.conform("sessions.command_executions.cancel/variant-0-4", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant05() {
        Harness.conform("sessions.command_executions.cancel/variant-0-5", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant06() {
        Harness.conform("sessions.command_executions.cancel/variant-0-6", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant07() {
        Harness.conform("sessions.command_executions.cancel/variant-0-7", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant08() {
        Harness.conform("sessions.command_executions.cancel/variant-0-8", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant09() {
        Harness.conform("sessions.command_executions.cancel/variant-0-9", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant010() {
        Harness.conform("sessions.command_executions.cancel/variant-0-10", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant011() {
        Harness.conform("sessions.command_executions.cancel/variant-0-11", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant012() {
        Harness.conform("sessions.command_executions.cancel/variant-0-12", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant013() {
        Harness.conform("sessions.command_executions.cancel/variant-0-13", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant014() {
        Harness.conform("sessions.command_executions.cancel/variant-0-14", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant015() {
        Harness.conform("sessions.command_executions.cancel/variant-0-15", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant016() {
        Harness.conform("sessions.command_executions.cancel/variant-0-16", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant017() {
        Harness.conform("sessions.command_executions.cancel/variant-0-17", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant018() {
        Harness.conform("sessions.command_executions.cancel/variant-0-18", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant019() {
        Harness.conform("sessions.command_executions.cancel/variant-0-19", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant020() {
        Harness.conform("sessions.command_executions.cancel/variant-0-20", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant021() {
        Harness.conform("sessions.command_executions.cancel/variant-0-21", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant022() {
        Harness.conform("sessions.command_executions.cancel/variant-0-22", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant023() {
        Harness.conform("sessions.command_executions.cancel/variant-0-23", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant024() {
        Harness.conform("sessions.command_executions.cancel/variant-0-24", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant025() {
        Harness.conform("sessions.command_executions.cancel/variant-0-25", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant026() {
        Harness.conform("sessions.command_executions.cancel/variant-0-26", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant027() {
        Harness.conform("sessions.command_executions.cancel/variant-0-27", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelVariant028() {
        Harness.conform("sessions.command_executions.cancel/variant-0-28", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsCommandExecutionsCancelEscaped() {
        Harness.conform("sessions.command_executions.cancel/escaped", ::make) { client ->
            client
                .sessions()
                .commandExecutions()
                .cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .executionId("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesListFull() {
        Harness.conform("sessions.files.list/full", ::make) { client ->
            client
                .sessions()
                .files()
                .list(
                    dev.steel.api.models.sessions.files.FileListParams.builder()
                        .sessionId("sessionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesListRequired() {
        Harness.conform("sessions.files.list/required", ::make) { client ->
            client
                .sessions()
                .files()
                .list(
                    dev.steel.api.models.sessions.files.FileListParams.builder()
                        .sessionId("sessionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesListNulls() {
        Harness.conform("sessions.files.list/nulls", ::make) { client ->
            client
                .sessions()
                .files()
                .list(
                    dev.steel.api.models.sessions.files.FileListParams.builder()
                        .sessionId("sessionId")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesListEscaped() {
        Harness.conform("sessions.files.list/escaped", ::make) { client ->
            client
                .sessions()
                .files()
                .list(
                    dev.steel.api.models.sessions.files.FileListParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesDeleteFull() {
        Harness.conform("sessions.files.delete/full", ::make) { client ->
            client
                .sessions()
                .files()
                .delete(
                    dev.steel.api.models.sessions.files.FileDeleteParams.builder()
                        .sessionId("sessionId")
                        .path("path")
                        .build()
                )
        }
    }

    @Test
    fun sessionsFilesDeleteRequired() {
        Harness.conform("sessions.files.delete/required", ::make) { client ->
            client
                .sessions()
                .files()
                .delete(
                    dev.steel.api.models.sessions.files.FileDeleteParams.builder()
                        .sessionId("sessionId")
                        .path("path")
                        .build()
                )
        }
    }

    @Test
    fun sessionsFilesDeleteEscaped() {
        Harness.conform("sessions.files.delete/escaped", ::make) { client ->
            client
                .sessions()
                .files()
                .delete(
                    dev.steel.api.models.sessions.files.FileDeleteParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .path("a b/c?d#e%f+g&h=i é")
                        .build()
                )
        }
    }

    @Test
    fun sessionsFilesDeleteAllFull() {
        Harness.conform("sessions.files.delete_all/full", ::make) { client ->
            client
                .sessions()
                .files()
                .deleteAll(FileDeleteAllParams.builder().sessionId("sessionId").build())
        }
    }

    @Test
    fun sessionsFilesDeleteAllRequired() {
        Harness.conform("sessions.files.delete_all/required", ::make) { client ->
            client
                .sessions()
                .files()
                .deleteAll(FileDeleteAllParams.builder().sessionId("sessionId").build())
        }
    }

    @Test
    fun sessionsFilesDeleteAllEscaped() {
        Harness.conform("sessions.files.delete_all/escaped", ::make) { client ->
            client
                .sessions()
                .files()
                .deleteAll(FileDeleteAllParams.builder().sessionId("a b/c?d#e%f+g&h=i é").build())
        }
    }

    @Test
    fun sessionsFilesDownloadFull() {
        Harness.conform("sessions.files.download/full", ::make) { client ->
            client
                .sessions()
                .files()
                .download(
                    dev.steel.api.models.sessions.files.FileDownloadParams.builder()
                        .sessionId("sessionId")
                        .path("path")
                        .build()
                )
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsFilesDownloadRequired() {
        Harness.conform("sessions.files.download/required", ::make) { client ->
            client
                .sessions()
                .files()
                .download(
                    dev.steel.api.models.sessions.files.FileDownloadParams.builder()
                        .sessionId("sessionId")
                        .path("path")
                        .build()
                )
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsFilesDownloadEscaped() {
        Harness.conform("sessions.files.download/escaped", ::make) { client ->
            client
                .sessions()
                .files()
                .download(
                    dev.steel.api.models.sessions.files.FileDownloadParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .path("a b/c?d#e%f+g&h=i é")
                        .build()
                )
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsFilesDownloadArchiveFull() {
        Harness.conform("sessions.files.download_archive/full", ::make) { client ->
            client
                .sessions()
                .files()
                .downloadArchive(FileDownloadArchiveParams.builder().sessionId("sessionId").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsFilesDownloadArchiveRequired() {
        Harness.conform("sessions.files.download_archive/required", ::make) { client ->
            client
                .sessions()
                .files()
                .downloadArchive(FileDownloadArchiveParams.builder().sessionId("sessionId").build())
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsFilesDownloadArchiveEscaped() {
        Harness.conform("sessions.files.download_archive/escaped", ::make) { client ->
            client
                .sessions()
                .files()
                .downloadArchive(
                    FileDownloadArchiveParams.builder().sessionId("a b/c?d#e%f+g&h=i é").build()
                )
                .use { it.body().readBytes().toString(Charsets.UTF_8) }
        }
    }

    @Test
    fun sessionsFilesUploadFull() {
        Harness.conform("sessions.files.upload/full", ::make) { client ->
            client
                .sessions()
                .files()
                .upload(
                    dev.steel.api.models.sessions.files.FileUploadParams.builder()
                        .sessionId("sessionId")
                        .file("Example data".byteInputStream())
                        .path("path")
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesUploadRequired() {
        Harness.conform("sessions.files.upload/required", ::make) { client ->
            client
                .sessions()
                .files()
                .upload(
                    dev.steel.api.models.sessions.files.FileUploadParams.builder()
                        .sessionId("sessionId")
                        .file("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesUploadNulls() {
        Harness.conform("sessions.files.upload/nulls", ::make) { client ->
            client
                .sessions()
                .files()
                .upload(
                    dev.steel.api.models.sessions.files.FileUploadParams.builder()
                        .sessionId("sessionId")
                        .file("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }

    @Test
    fun sessionsFilesUploadEscaped() {
        Harness.conform("sessions.files.upload/escaped", ::make) { client ->
            client
                .sessions()
                .files()
                .upload(
                    dev.steel.api.models.sessions.files.FileUploadParams.builder()
                        .sessionId("a b/c?d#e%f+g&h=i é")
                        .file("Example data".byteInputStream())
                        .build()
                )
                .also { it.validate() }
        }
    }
}
