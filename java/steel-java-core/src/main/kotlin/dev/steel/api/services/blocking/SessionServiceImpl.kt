package dev.steel.api.services.blocking

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.JsonValue
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.checkRequired
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
import dev.steel.api.models.sessions.Session
import dev.steel.api.models.sessions.SessionAgentTracesParams
import dev.steel.api.models.sessions.SessionAgentTracesResponse
import dev.steel.api.models.sessions.SessionCommandsParams
import dev.steel.api.models.sessions.SessionCommandsResponse
import dev.steel.api.models.sessions.SessionComputerParams
import dev.steel.api.models.sessions.SessionComputerResponse
import dev.steel.api.models.sessions.SessionContext
import dev.steel.api.models.sessions.SessionContextParams
import dev.steel.api.models.sessions.SessionCostParams
import dev.steel.api.models.sessions.SessionCostResponse
import dev.steel.api.models.sessions.SessionCreateParams
import dev.steel.api.models.sessions.SessionEventsParams
import dev.steel.api.models.sessions.SessionHlsParams
import dev.steel.api.models.sessions.SessionListPage
import dev.steel.api.models.sessions.SessionListParams
import dev.steel.api.models.sessions.SessionLiveDetailsParams
import dev.steel.api.models.sessions.SessionLiveDetailsResponse
import dev.steel.api.models.sessions.SessionLogsParams
import dev.steel.api.models.sessions.SessionLogsResponse
import dev.steel.api.models.sessions.SessionReleaseAllParams
import dev.steel.api.models.sessions.SessionReleaseAllResponse
import dev.steel.api.models.sessions.SessionReleaseParams
import dev.steel.api.models.sessions.SessionReleaseResponse
import dev.steel.api.models.sessions.SessionReplaceMetadataParams
import dev.steel.api.models.sessions.SessionReplaceMetadataResponse
import dev.steel.api.models.sessions.SessionRetrieveParams
import dev.steel.api.models.sessions.SessionSignalCatalogParams
import dev.steel.api.models.sessions.SessionSignalCatalogResponse
import dev.steel.api.models.sessions.SessionUpdateMetadataParams
import dev.steel.api.models.sessions.SessionUpdateMetadataResponse
import dev.steel.api.models.sessions.Sessionslist
import dev.steel.api.services.blocking.sessions.CaptchaService
import dev.steel.api.services.blocking.sessions.CaptchaServiceImpl
import dev.steel.api.services.blocking.sessions.CommandExecutionService
import dev.steel.api.services.blocking.sessions.CommandExecutionServiceImpl
import dev.steel.api.services.blocking.sessions.FileService
import dev.steel.api.services.blocking.sessions.FileServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SessionServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    SessionService {

    private val withRawResponse: SessionService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val captchas: CaptchaService by lazy { CaptchaServiceImpl(clientOptions) }

    private val commandExecutions: CommandExecutionService by lazy {
        CommandExecutionServiceImpl(clientOptions)
    }

    private val files: FileService by lazy { FileServiceImpl(clientOptions) }

    override fun withRawResponse(): SessionService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionService =
        SessionServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun captchas(): CaptchaService = captchas

    override fun commandExecutions(): CommandExecutionService = commandExecutions

    override fun files(): FileService = files

    override fun create(params: SessionCreateParams, requestOptions: RequestOptions): Session =
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(params: SessionRetrieveParams, requestOptions: RequestOptions): Session =
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: SessionListParams, requestOptions: RequestOptions): SessionListPage =
        withRawResponse().list(params, requestOptions).parse()

    override fun agentTraces(
        params: SessionAgentTracesParams,
        requestOptions: RequestOptions,
    ): SessionAgentTracesResponse = withRawResponse().agentTraces(params, requestOptions).parse()

    override fun commands(
        params: SessionCommandsParams,
        requestOptions: RequestOptions,
    ): SessionCommandsResponse = withRawResponse().commands(params, requestOptions).parse()

    override fun computer(
        params: SessionComputerParams,
        requestOptions: RequestOptions,
    ): SessionComputerResponse = withRawResponse().computer(params, requestOptions).parse()

    override fun context(
        params: SessionContextParams,
        requestOptions: RequestOptions,
    ): SessionContext = withRawResponse().context(params, requestOptions).parse()

    override fun cost(
        params: SessionCostParams,
        requestOptions: RequestOptions,
    ): SessionCostResponse = withRawResponse().cost(params, requestOptions).parse()

    override fun events(
        params: SessionEventsParams,
        requestOptions: RequestOptions,
    ): List<JsonValue> = withRawResponse().events(params, requestOptions).parse()

    override fun hls(params: SessionHlsParams, requestOptions: RequestOptions): HttpResponse =
        withRawResponse().hls(params, requestOptions)

    override fun liveDetails(
        params: SessionLiveDetailsParams,
        requestOptions: RequestOptions,
    ): SessionLiveDetailsResponse = withRawResponse().liveDetails(params, requestOptions).parse()

    override fun logs(
        params: SessionLogsParams,
        requestOptions: RequestOptions,
    ): SessionLogsResponse = withRawResponse().logs(params, requestOptions).parse()

    override fun release(
        params: SessionReleaseParams,
        requestOptions: RequestOptions,
    ): SessionReleaseResponse = withRawResponse().release(params, requestOptions).parse()

    override fun releaseAll(
        params: SessionReleaseAllParams,
        requestOptions: RequestOptions,
    ): SessionReleaseAllResponse = withRawResponse().releaseAll(params, requestOptions).parse()

    override fun replaceMetadata(
        params: SessionReplaceMetadataParams,
        requestOptions: RequestOptions,
    ): SessionReplaceMetadataResponse =
        withRawResponse().replaceMetadata(params, requestOptions).parse()

    override fun signalCatalog(
        params: SessionSignalCatalogParams,
        requestOptions: RequestOptions,
    ): SessionSignalCatalogResponse =
        withRawResponse().signalCatalog(params, requestOptions).parse()

    override fun updateMetadata(
        params: SessionUpdateMetadataParams,
        requestOptions: RequestOptions,
    ): SessionUpdateMetadataResponse =
        withRawResponse().updateMetadata(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SessionService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val captchas: CaptchaService.WithRawResponse by lazy {
            CaptchaServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val commandExecutions: CommandExecutionService.WithRawResponse by lazy {
            CommandExecutionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val files: FileService.WithRawResponse by lazy {
            FileServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SessionService.WithRawResponse =
            SessionServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun captchas(): CaptchaService.WithRawResponse = captchas

        override fun commandExecutions(): CommandExecutionService.WithRawResponse =
            commandExecutions

        override fun files(): FileService.WithRawResponse = files

        private val createHandler: Handler<Session> = jsonHandler<Session>(clientOptions.jsonMapper)

        override fun create(
            params: SessionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Session> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<Session> =
            jsonHandler<Session>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SessionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Session> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<Sessionslist> =
            jsonHandler<Sessionslist>(clientOptions.jsonMapper)

        override fun list(
            params: SessionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
                    .let {
                        SessionListPage.builder()
                            .service(SessionServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val agentTracesHandler: Handler<SessionAgentTracesResponse> =
            jsonHandler<SessionAgentTracesResponse>(clientOptions.jsonMapper)

        override fun agentTraces(
            params: SessionAgentTracesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionAgentTracesResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "agent-traces")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { agentTracesHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val commandsHandler: Handler<SessionCommandsResponse> =
            jsonHandler<SessionCommandsResponse>(clientOptions.jsonMapper)

        override fun commands(
            params: SessionCommandsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCommandsResponse> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "commands")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { commandsHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val computerHandler: Handler<SessionComputerResponse> =
            jsonHandler<SessionComputerResponse>(clientOptions.jsonMapper)

        override fun computer(
            params: SessionComputerParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionComputerResponse> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "computer")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { computerHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val contextHandler: Handler<SessionContext> =
            jsonHandler<SessionContext>(clientOptions.jsonMapper)

        override fun context(
            params: SessionContextParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionContext> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "context")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { contextHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val costHandler: Handler<SessionCostResponse> =
            jsonHandler<SessionCostResponse>(clientOptions.jsonMapper)

        override fun cost(
            params: SessionCostParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCostResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "cost")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { costHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val eventsHandler: Handler<List<JsonValue>> =
            jsonHandler<List<JsonValue>>(clientOptions.jsonMapper)

        override fun events(
            params: SessionEventsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<List<JsonValue>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "events")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response.use { eventsHandler.handle(it) }
            }
        }

        override fun hls(params: SessionHlsParams, requestOptions: RequestOptions): HttpResponse {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "hls")
                    .putHeader("Accept", "application/vnd.apple.mpegurl")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }

        private val liveDetailsHandler: Handler<SessionLiveDetailsResponse> =
            jsonHandler<SessionLiveDetailsResponse>(clientOptions.jsonMapper)

        override fun liveDetails(
            params: SessionLiveDetailsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionLiveDetailsResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "live-details")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { liveDetailsHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val logsHandler: Handler<SessionLogsResponse> =
            jsonHandler<SessionLogsResponse>(clientOptions.jsonMapper)

        override fun logs(
            params: SessionLogsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionLogsResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "logs")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { logsHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val releaseHandler: Handler<SessionReleaseResponse> =
            jsonHandler<SessionReleaseResponse>(clientOptions.jsonMapper)

        override fun release(
            params: SessionReleaseParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReleaseResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "release")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { releaseHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val releaseAllHandler: Handler<SessionReleaseAllResponse> =
            jsonHandler<SessionReleaseAllResponse>(clientOptions.jsonMapper)

        override fun releaseAll(
            params: SessionReleaseAllParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReleaseAllResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", "release")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { releaseAllHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val replaceMetadataHandler: Handler<SessionReplaceMetadataResponse> =
            jsonHandler<SessionReplaceMetadataResponse>(clientOptions.jsonMapper)

        override fun replaceMetadata(
            params: SessionReplaceMetadataParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReplaceMetadataResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PUT)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "metadata")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { replaceMetadataHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val signalCatalogHandler: Handler<SessionSignalCatalogResponse> =
            jsonHandler<SessionSignalCatalogResponse>(clientOptions.jsonMapper)

        override fun signalCatalog(
            params: SessionSignalCatalogParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionSignalCatalogResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", "signals", "catalog")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { signalCatalogHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val updateMetadataHandler: Handler<SessionUpdateMetadataResponse> =
            jsonHandler<SessionUpdateMetadataResponse>(clientOptions.jsonMapper)

        override fun updateMetadata(
            params: SessionUpdateMetadataParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionUpdateMetadataResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "metadata")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateMetadataHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
