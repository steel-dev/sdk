package dev.steel.api.client

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.getPackageVersion
import dev.steel.api.core.handlers.errorBodyHandler
import dev.steel.api.core.handlers.errorHandler
import dev.steel.api.core.handlers.jsonHandler
import dev.steel.api.core.http.HttpMethod
import dev.steel.api.core.http.HttpRequest
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponse.Handler
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.core.http.json
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepareAsync
import dev.steel.api.models.ClientPdfParams
import dev.steel.api.models.ClientPdfResponse
import dev.steel.api.models.ClientScrapeParams
import dev.steel.api.models.ClientScrapeResponse
import dev.steel.api.models.ClientScreenshotParams
import dev.steel.api.models.ClientScreenshotResponse
import dev.steel.api.services.async.ApiKeyServiceAsync
import dev.steel.api.services.async.ApiKeyServiceAsyncImpl
import dev.steel.api.services.async.CredentialServiceAsync
import dev.steel.api.services.async.CredentialServiceAsyncImpl
import dev.steel.api.services.async.ExtensionServiceAsync
import dev.steel.api.services.async.ExtensionServiceAsyncImpl
import dev.steel.api.services.async.FileServiceAsync
import dev.steel.api.services.async.FileServiceAsyncImpl
import dev.steel.api.services.async.ProfileServiceAsync
import dev.steel.api.services.async.ProfileServiceAsyncImpl
import dev.steel.api.services.async.ProjectServiceAsync
import dev.steel.api.services.async.ProjectServiceAsyncImpl
import dev.steel.api.services.async.SessionServiceAsync
import dev.steel.api.services.async.SessionServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class SteelClientAsyncImpl(private val clientOptions: ClientOptions) : SteelClientAsync {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "Steel/Java ${getPackageVersion()}")
                .build()

    private val sync: SteelClient by lazy { SteelClientImpl(clientOptions) }

    private val withRawResponse: SteelClientAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptionsWithUserAgent)
    }

    private val apiKeys: ApiKeyServiceAsync by lazy {
        ApiKeyServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val credentials: CredentialServiceAsync by lazy {
        CredentialServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val extensions: ExtensionServiceAsync by lazy {
        ExtensionServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val files: FileServiceAsync by lazy { FileServiceAsyncImpl(clientOptionsWithUserAgent) }

    private val profiles: ProfileServiceAsync by lazy {
        ProfileServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val projects: ProjectServiceAsync by lazy {
        ProjectServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val sessions: SessionServiceAsync by lazy {
        SessionServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    override fun sync(): SteelClient = sync

    override fun withRawResponse(): SteelClientAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SteelClientAsync =
        SteelClientAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun apiKeys(): ApiKeyServiceAsync = apiKeys

    override fun credentials(): CredentialServiceAsync = credentials

    override fun extensions(): ExtensionServiceAsync = extensions

    override fun files(): FileServiceAsync = files

    override fun profiles(): ProfileServiceAsync = profiles

    override fun projects(): ProjectServiceAsync = projects

    override fun sessions(): SessionServiceAsync = sessions

    override fun pdf(
        params: ClientPdfParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ClientPdfResponse> =
        withRawResponse().pdf(params, requestOptions).thenApply { it.parse() }

    override fun scrape(
        params: ClientScrapeParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ClientScrapeResponse> =
        withRawResponse().scrape(params, requestOptions).thenApply { it.parse() }

    override fun screenshot(
        params: ClientScreenshotParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ClientScreenshotResponse> =
        withRawResponse().screenshot(params, requestOptions).thenApply { it.parse() }

    override fun close() = clientOptions.close()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SteelClientAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val apiKeys: ApiKeyServiceAsync.WithRawResponse by lazy {
            ApiKeyServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val credentials: CredentialServiceAsync.WithRawResponse by lazy {
            CredentialServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val extensions: ExtensionServiceAsync.WithRawResponse by lazy {
            ExtensionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val files: FileServiceAsync.WithRawResponse by lazy {
            FileServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val profiles: ProfileServiceAsync.WithRawResponse by lazy {
            ProfileServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val projects: ProjectServiceAsync.WithRawResponse by lazy {
            ProjectServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val sessions: SessionServiceAsync.WithRawResponse by lazy {
            SessionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SteelClientAsync.WithRawResponse =
            SteelClientAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun apiKeys(): ApiKeyServiceAsync.WithRawResponse = apiKeys

        override fun credentials(): CredentialServiceAsync.WithRawResponse = credentials

        override fun extensions(): ExtensionServiceAsync.WithRawResponse = extensions

        override fun files(): FileServiceAsync.WithRawResponse = files

        override fun profiles(): ProfileServiceAsync.WithRawResponse = profiles

        override fun projects(): ProjectServiceAsync.WithRawResponse = projects

        override fun sessions(): SessionServiceAsync.WithRawResponse = sessions

        private val pdfHandler: Handler<ClientPdfResponse> =
            jsonHandler<ClientPdfResponse>(clientOptions.jsonMapper)

        override fun pdf(
            params: ClientPdfParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ClientPdfResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "pdf")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { pdfHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val scrapeHandler: Handler<ClientScrapeResponse> =
            jsonHandler<ClientScrapeResponse>(clientOptions.jsonMapper)

        override fun scrape(
            params: ClientScrapeParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ClientScrapeResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "scrape")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { scrapeHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val screenshotHandler: Handler<ClientScreenshotResponse> =
            jsonHandler<ClientScreenshotResponse>(clientOptions.jsonMapper)

        override fun screenshot(
            params: ClientScreenshotParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ClientScreenshotResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "screenshot")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { screenshotHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }
    }
}
