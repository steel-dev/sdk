package dev.steel.api.services.async

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
import dev.steel.api.core.prepareAsync
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
import dev.steel.api.models.sessions.SessionListPageAsync
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
import dev.steel.api.services.async.sessions.CaptchaServiceAsync
import dev.steel.api.services.async.sessions.CaptchaServiceAsyncImpl
import dev.steel.api.services.async.sessions.CommandExecutionServiceAsync
import dev.steel.api.services.async.sessions.CommandExecutionServiceAsyncImpl
import dev.steel.api.services.async.sessions.FileServiceAsync
import dev.steel.api.services.async.sessions.FileServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SessionServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SessionServiceAsync {

    private val withRawResponse: SessionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val captchas: CaptchaServiceAsync by lazy { CaptchaServiceAsyncImpl(clientOptions) }

    private val commandExecutions: CommandExecutionServiceAsync by lazy {
        CommandExecutionServiceAsyncImpl(clientOptions)
    }

    private val files: FileServiceAsync by lazy { FileServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): SessionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionServiceAsync =
        SessionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun captchas(): CaptchaServiceAsync = captchas

    override fun commandExecutions(): CommandExecutionServiceAsync = commandExecutions

    override fun files(): FileServiceAsync = files

    override fun create(
        params: SessionCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Session> =
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: SessionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Session> =
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: SessionListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionListPageAsync> =
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun agentTraces(
        params: SessionAgentTracesParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionAgentTracesResponse> =
        withRawResponse().agentTraces(params, requestOptions).thenApply { it.parse() }

    override fun commands(
        params: SessionCommandsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCommandsResponse> =
        withRawResponse().commands(params, requestOptions).thenApply { it.parse() }

    override fun computer(
        params: SessionComputerParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionComputerResponse> =
        withRawResponse().computer(params, requestOptions).thenApply { it.parse() }

    override fun context(
        params: SessionContextParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionContext> =
        withRawResponse().context(params, requestOptions).thenApply { it.parse() }

    override fun cost(
        params: SessionCostParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCostResponse> =
        withRawResponse().cost(params, requestOptions).thenApply { it.parse() }

    override fun events(
        params: SessionEventsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<List<JsonValue>> =
        withRawResponse().events(params, requestOptions).thenApply { it.parse() }

    override fun hls(
        params: SessionHlsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> = withRawResponse().hls(params, requestOptions)

    override fun liveDetails(
        params: SessionLiveDetailsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionLiveDetailsResponse> =
        withRawResponse().liveDetails(params, requestOptions).thenApply { it.parse() }

    override fun logs(
        params: SessionLogsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionLogsResponse> =
        withRawResponse().logs(params, requestOptions).thenApply { it.parse() }

    override fun release(
        params: SessionReleaseParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReleaseResponse> =
        withRawResponse().release(params, requestOptions).thenApply { it.parse() }

    override fun releaseAll(
        params: SessionReleaseAllParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReleaseAllResponse> =
        withRawResponse().releaseAll(params, requestOptions).thenApply { it.parse() }

    override fun replaceMetadata(
        params: SessionReplaceMetadataParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReplaceMetadataResponse> =
        withRawResponse().replaceMetadata(params, requestOptions).thenApply { it.parse() }

    override fun signalCatalog(
        params: SessionSignalCatalogParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionSignalCatalogResponse> =
        withRawResponse().signalCatalog(params, requestOptions).thenApply { it.parse() }

    override fun updateMetadata(
        params: SessionUpdateMetadataParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionUpdateMetadataResponse> =
        withRawResponse().updateMetadata(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SessionServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val captchas: CaptchaServiceAsync.WithRawResponse by lazy {
            CaptchaServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val commandExecutions: CommandExecutionServiceAsync.WithRawResponse by lazy {
            CommandExecutionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val files: FileServiceAsync.WithRawResponse by lazy {
            FileServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SessionServiceAsync.WithRawResponse =
            SessionServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun captchas(): CaptchaServiceAsync.WithRawResponse = captchas

        override fun commandExecutions(): CommandExecutionServiceAsync.WithRawResponse =
            commandExecutions

        override fun files(): FileServiceAsync.WithRawResponse = files

        private val createHandler: Handler<Session> = jsonHandler<Session>(clientOptions.jsonMapper)

        override fun create(
            params: SessionCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Session>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val retrieveHandler: Handler<Session> =
            jsonHandler<Session>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SessionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Session>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<Sessionslist> =
            jsonHandler<Sessionslist>(clientOptions.jsonMapper)

        override fun list(
            params: SessionListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                SessionListPageAsync.builder()
                                    .service(SessionServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val agentTracesHandler: Handler<SessionAgentTracesResponse> =
            jsonHandler<SessionAgentTracesResponse>(clientOptions.jsonMapper)

        override fun agentTraces(
            params: SessionAgentTracesParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionAgentTracesResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "agent-traces")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { agentTracesHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val commandsHandler: Handler<SessionCommandsResponse> =
            jsonHandler<SessionCommandsResponse>(clientOptions.jsonMapper)

        override fun commands(
            params: SessionCommandsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCommandsResponse>> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "commands")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { commandsHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val computerHandler: Handler<SessionComputerResponse> =
            jsonHandler<SessionComputerResponse>(clientOptions.jsonMapper)

        override fun computer(
            params: SessionComputerParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionComputerResponse>> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "computer")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { computerHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val contextHandler: Handler<SessionContext> =
            jsonHandler<SessionContext>(clientOptions.jsonMapper)

        override fun context(
            params: SessionContextParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionContext>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "context")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { contextHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val costHandler: Handler<SessionCostResponse> =
            jsonHandler<SessionCostResponse>(clientOptions.jsonMapper)

        override fun cost(
            params: SessionCostParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCostResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "cost")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { costHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val eventsHandler: Handler<List<JsonValue>> =
            jsonHandler<List<JsonValue>>(clientOptions.jsonMapper)

        override fun events(
            params: SessionEventsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<JsonValue>>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "events")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response.use { eventsHandler.handle(it) }
                    }
                }
        }

        override fun hls(
            params: SessionHlsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "hls")
                    .putHeader("Accept", "application/vnd.apple.mpegurl")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response -> errorHandler.handle(response) }
        }

        private val liveDetailsHandler: Handler<SessionLiveDetailsResponse> =
            jsonHandler<SessionLiveDetailsResponse>(clientOptions.jsonMapper)

        override fun liveDetails(
            params: SessionLiveDetailsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLiveDetailsResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "live-details")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { liveDetailsHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val logsHandler: Handler<SessionLogsResponse> =
            jsonHandler<SessionLogsResponse>(clientOptions.jsonMapper)

        override fun logs(
            params: SessionLogsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLogsResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "logs")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { logsHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val releaseHandler: Handler<SessionReleaseResponse> =
            jsonHandler<SessionReleaseResponse>(clientOptions.jsonMapper)

        override fun release(
            params: SessionReleaseParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReleaseResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "release")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { releaseHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val releaseAllHandler: Handler<SessionReleaseAllResponse> =
            jsonHandler<SessionReleaseAllResponse>(clientOptions.jsonMapper)

        override fun releaseAll(
            params: SessionReleaseAllParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReleaseAllResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", "release")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { releaseAllHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val replaceMetadataHandler: Handler<SessionReplaceMetadataResponse> =
            jsonHandler<SessionReplaceMetadataResponse>(clientOptions.jsonMapper)

        override fun replaceMetadata(
            params: SessionReplaceMetadataParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReplaceMetadataResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PUT)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "metadata")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { replaceMetadataHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val signalCatalogHandler: Handler<SessionSignalCatalogResponse> =
            jsonHandler<SessionSignalCatalogResponse>(clientOptions.jsonMapper)

        override fun signalCatalog(
            params: SessionSignalCatalogParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionSignalCatalogResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", "signals", "catalog")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { signalCatalogHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val updateMetadataHandler: Handler<SessionUpdateMetadataResponse> =
            jsonHandler<SessionUpdateMetadataResponse>(clientOptions.jsonMapper)

        override fun updateMetadata(
            params: SessionUpdateMetadataParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionUpdateMetadataResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "metadata")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
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
}
