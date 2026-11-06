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
import dev.steel.api.core.prepare
import dev.steel.api.models.ClientPdfParams
import dev.steel.api.models.ClientPdfResponse
import dev.steel.api.models.ClientScrapeParams
import dev.steel.api.models.ClientScrapeResponse
import dev.steel.api.models.ClientScreenshotParams
import dev.steel.api.models.ClientScreenshotResponse
import dev.steel.api.services.blocking.ApiKeyService
import dev.steel.api.services.blocking.ApiKeyServiceImpl
import dev.steel.api.services.blocking.CredentialService
import dev.steel.api.services.blocking.CredentialServiceImpl
import dev.steel.api.services.blocking.ExtensionService
import dev.steel.api.services.blocking.ExtensionServiceImpl
import dev.steel.api.services.blocking.FileService
import dev.steel.api.services.blocking.FileServiceImpl
import dev.steel.api.services.blocking.ProfileService
import dev.steel.api.services.blocking.ProfileServiceImpl
import dev.steel.api.services.blocking.ProjectService
import dev.steel.api.services.blocking.ProjectServiceImpl
import dev.steel.api.services.blocking.SessionService
import dev.steel.api.services.blocking.SessionServiceImpl
import java.util.function.Consumer

class SteelClientImpl(private val clientOptions: ClientOptions) : SteelClient {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "Steel/Java ${getPackageVersion()}")
                .build()

    private val async: SteelClientAsync by lazy { SteelClientAsyncImpl(clientOptions) }

    private val withRawResponse: SteelClient.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptionsWithUserAgent)
    }

    private val apiKeys: ApiKeyService by lazy { ApiKeyServiceImpl(clientOptionsWithUserAgent) }

    private val credentials: CredentialService by lazy {
        CredentialServiceImpl(clientOptionsWithUserAgent)
    }

    private val extensions: ExtensionService by lazy {
        ExtensionServiceImpl(clientOptionsWithUserAgent)
    }

    private val files: FileService by lazy { FileServiceImpl(clientOptionsWithUserAgent) }

    private val profiles: ProfileService by lazy { ProfileServiceImpl(clientOptionsWithUserAgent) }

    private val projects: ProjectService by lazy { ProjectServiceImpl(clientOptionsWithUserAgent) }

    private val sessions: SessionService by lazy { SessionServiceImpl(clientOptionsWithUserAgent) }

    override fun async(): SteelClientAsync = async

    override fun withRawResponse(): SteelClient.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SteelClient =
        SteelClientImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun apiKeys(): ApiKeyService = apiKeys

    override fun credentials(): CredentialService = credentials

    override fun extensions(): ExtensionService = extensions

    override fun files(): FileService = files

    override fun profiles(): ProfileService = profiles

    override fun projects(): ProjectService = projects

    override fun sessions(): SessionService = sessions

    override fun pdf(params: ClientPdfParams, requestOptions: RequestOptions): ClientPdfResponse =
        withRawResponse().pdf(params, requestOptions).parse()

    override fun scrape(
        params: ClientScrapeParams,
        requestOptions: RequestOptions,
    ): ClientScrapeResponse = withRawResponse().scrape(params, requestOptions).parse()

    override fun screenshot(
        params: ClientScreenshotParams,
        requestOptions: RequestOptions,
    ): ClientScreenshotResponse = withRawResponse().screenshot(params, requestOptions).parse()

    override fun close() = clientOptions.close()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SteelClient.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val apiKeys: ApiKeyService.WithRawResponse by lazy {
            ApiKeyServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val credentials: CredentialService.WithRawResponse by lazy {
            CredentialServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val extensions: ExtensionService.WithRawResponse by lazy {
            ExtensionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val files: FileService.WithRawResponse by lazy {
            FileServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val profiles: ProfileService.WithRawResponse by lazy {
            ProfileServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val projects: ProjectService.WithRawResponse by lazy {
            ProjectServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val sessions: SessionService.WithRawResponse by lazy {
            SessionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SteelClient.WithRawResponse =
            SteelClientImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun apiKeys(): ApiKeyService.WithRawResponse = apiKeys

        override fun credentials(): CredentialService.WithRawResponse = credentials

        override fun extensions(): ExtensionService.WithRawResponse = extensions

        override fun files(): FileService.WithRawResponse = files

        override fun profiles(): ProfileService.WithRawResponse = profiles

        override fun projects(): ProjectService.WithRawResponse = projects

        override fun sessions(): SessionService.WithRawResponse = sessions

        private val pdfHandler: Handler<ClientPdfResponse> =
            jsonHandler<ClientPdfResponse>(clientOptions.jsonMapper)

        override fun pdf(
            params: ClientPdfParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientPdfResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "pdf")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { pdfHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val scrapeHandler: Handler<ClientScrapeResponse> =
            jsonHandler<ClientScrapeResponse>(clientOptions.jsonMapper)

        override fun scrape(
            params: ClientScrapeParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientScrapeResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "scrape")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { scrapeHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val screenshotHandler: Handler<ClientScreenshotResponse> =
            jsonHandler<ClientScreenshotResponse>(clientOptions.jsonMapper)

        override fun screenshot(
            params: ClientScreenshotParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientScreenshotResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "screenshot")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
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
