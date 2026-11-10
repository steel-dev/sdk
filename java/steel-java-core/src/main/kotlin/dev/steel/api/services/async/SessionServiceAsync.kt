package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.JsonValue
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponseFor
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
import dev.steel.api.services.async.sessions.CaptchaServiceAsync
import dev.steel.api.services.async.sessions.CommandExecutionServiceAsync
import dev.steel.api.services.async.sessions.FileServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface SessionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionServiceAsync

    fun captchas(): CaptchaServiceAsync

    fun commandExecutions(): CommandExecutionServiceAsync

    fun files(): FileServiceAsync

    /** Creates a new session with the provided configuration. */
    fun create(
        params: SessionCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Session>

    /** @see create */
    fun create(params: SessionCreateParams): CompletableFuture<Session> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(): CompletableFuture<Session> = create(SessionCreateParams.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): CompletableFuture<Session> =
        create(SessionCreateParams.none(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SessionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Session> = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(id: String, params: SessionRetrieveParams): CompletableFuture<Session> =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): CompletableFuture<Session> =
        retrieve(id, SessionRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): CompletableFuture<Session> =
        retrieve(id, SessionRetrieveParams.none(), requestOptions)

    /** Retrieves details of a specific session by ID. */
    fun retrieve(
        params: SessionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Session>

    /** @see retrieve */
    fun retrieve(params: SessionRetrieveParams): CompletableFuture<Session> =
        retrieve(params, RequestOptions.none())

    /** Fetches all active sessions for the current organization. */
    fun list(
        params: SessionListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionListPageAsync>

    /** @see list */
    fun list(params: SessionListParams): CompletableFuture<SessionListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<SessionListPageAsync> = list(SessionListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<SessionListPageAsync> =
        list(SessionListParams.none(), requestOptions)

    /** @see agentTraces */
    fun agentTraces(
        id: String,
        params: SessionAgentTracesParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionAgentTracesResponse> =
        agentTraces(params.toBuilder().id(id).build(), requestOptions)

    /** @see agentTraces */
    fun agentTraces(
        id: String,
        params: SessionAgentTracesParams,
    ): CompletableFuture<SessionAgentTracesResponse> =
        agentTraces(id, params, RequestOptions.none())

    /** @see agentTraces */
    fun agentTraces(id: String): CompletableFuture<SessionAgentTracesResponse> =
        agentTraces(id, SessionAgentTracesParams.none())

    /** @see agentTraces */
    fun agentTraces(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionAgentTracesResponse> =
        agentTraces(id, SessionAgentTracesParams.none(), requestOptions)

    /**
     * Query the semantic agent trace timeline for a session: a coalesced, replay-oriented record of
     * user/agent interactions (clicks, typing, scrolls, drags, navigations) with target metadata
     * (role, accessible name, selectors). Returns an empty list for sessions that predate
     * browser-interaction capture.
     */
    fun agentTraces(
        params: SessionAgentTracesParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionAgentTracesResponse>

    /** @see agentTraces */
    fun agentTraces(
        params: SessionAgentTracesParams
    ): CompletableFuture<SessionAgentTracesResponse> = agentTraces(params, RequestOptions.none())

    /** @see commands */
    fun commands(
        sessionId: String,
        params: SessionCommandsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCommandsResponse> =
        commands(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see commands */
    fun commands(
        sessionId: String,
        params: SessionCommandsParams,
    ): CompletableFuture<SessionCommandsResponse> =
        commands(sessionId, params, RequestOptions.none())

    /**
     * Drives the session's browser with high-level commands such as snapshot, click, and fill.
     * Commands run in order and stop at the first failure. Send a random Idempotency-Key header to
     * make retries safe.
     */
    fun commands(
        params: SessionCommandsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCommandsResponse>

    /** @see commands */
    fun commands(params: SessionCommandsParams): CompletableFuture<SessionCommandsResponse> =
        commands(params, RequestOptions.none())

    /** @see computer */
    fun computer(
        sessionId: String,
        params: SessionComputerParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionComputerResponse> =
        computer(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see computer */
    fun computer(
        sessionId: String,
        params: SessionComputerParams,
    ): CompletableFuture<SessionComputerResponse> =
        computer(sessionId, params, RequestOptions.none())

    /** Execute computer actions like mouse movements, clicks, keyboard input, and more */
    fun computer(
        params: SessionComputerParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionComputerResponse>

    /** @see computer */
    fun computer(params: SessionComputerParams): CompletableFuture<SessionComputerResponse> =
        computer(params, RequestOptions.none())

    /** @see context */
    fun context(
        id: String,
        params: SessionContextParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionContext> =
        context(params.toBuilder().id(id).build(), requestOptions)

    /** @see context */
    fun context(id: String, params: SessionContextParams): CompletableFuture<SessionContext> =
        context(id, params, RequestOptions.none())

    /** @see context */
    fun context(id: String): CompletableFuture<SessionContext> =
        context(id, SessionContextParams.none())

    /** @see context */
    fun context(id: String, requestOptions: RequestOptions): CompletableFuture<SessionContext> =
        context(id, SessionContextParams.none(), requestOptions)

    /** Fetches the context data of a specific session. */
    fun context(
        params: SessionContextParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionContext>

    /** @see context */
    fun context(params: SessionContextParams): CompletableFuture<SessionContext> =
        context(params, RequestOptions.none())

    /** @see cost */
    fun cost(
        id: String,
        params: SessionCostParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCostResponse> =
        cost(params.toBuilder().id(id).build(), requestOptions)

    /** @see cost */
    fun cost(id: String, params: SessionCostParams): CompletableFuture<SessionCostResponse> =
        cost(id, params, RequestOptions.none())

    /** @see cost */
    fun cost(id: String): CompletableFuture<SessionCostResponse> =
        cost(id, SessionCostParams.none())

    /** @see cost */
    fun cost(id: String, requestOptions: RequestOptions): CompletableFuture<SessionCostResponse> =
        cost(id, SessionCostParams.none(), requestOptions)

    /**
     * Returns the finalized cost of a released session as an exact amount in US dollars (e.g.
     * 0.031905), suitable for precise billing passthrough.
     */
    fun cost(
        params: SessionCostParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCostResponse>

    /** @see cost */
    fun cost(params: SessionCostParams): CompletableFuture<SessionCostResponse> =
        cost(params, RequestOptions.none())

    /** @see events */
    fun events(
        id: String,
        params: SessionEventsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<List<JsonValue>> =
        events(params.toBuilder().id(id).build(), requestOptions)

    /** @see events */
    fun events(id: String, params: SessionEventsParams): CompletableFuture<List<JsonValue>> =
        events(id, params, RequestOptions.none())

    /** @see events */
    fun events(id: String): CompletableFuture<List<JsonValue>> =
        events(id, SessionEventsParams.none())

    /** @see events */
    fun events(id: String, requestOptions: RequestOptions): CompletableFuture<List<JsonValue>> =
        events(id, SessionEventsParams.none(), requestOptions)

    /** This endpoint allows you to get the recorded session events in the RRWeb format */
    fun events(
        params: SessionEventsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<List<JsonValue>>

    /** @see events */
    fun events(params: SessionEventsParams): CompletableFuture<List<JsonValue>> =
        events(params, RequestOptions.none())

    /** @see hls */
    fun hls(
        id: String,
        params: SessionHlsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> = hls(params.toBuilder().id(id).build(), requestOptions)

    /** @see hls */
    fun hls(id: String, params: SessionHlsParams): CompletableFuture<HttpResponse> =
        hls(id, params, RequestOptions.none())

    /** @see hls */
    fun hls(id: String): CompletableFuture<HttpResponse> = hls(id, SessionHlsParams.none())

    /** @see hls */
    fun hls(id: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
        hls(id, SessionHlsParams.none(), requestOptions)

    /** Get HLS playlist for a session recording using app auth or an RTC token */
    fun hls(
        params: SessionHlsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse>

    /** @see hls */
    fun hls(params: SessionHlsParams): CompletableFuture<HttpResponse> =
        hls(params, RequestOptions.none())

    /** @see liveDetails */
    fun liveDetails(
        id: String,
        params: SessionLiveDetailsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionLiveDetailsResponse> =
        liveDetails(params.toBuilder().id(id).build(), requestOptions)

    /** @see liveDetails */
    fun liveDetails(
        id: String,
        params: SessionLiveDetailsParams,
    ): CompletableFuture<SessionLiveDetailsResponse> =
        liveDetails(id, params, RequestOptions.none())

    /** @see liveDetails */
    fun liveDetails(id: String): CompletableFuture<SessionLiveDetailsResponse> =
        liveDetails(id, SessionLiveDetailsParams.none())

    /** @see liveDetails */
    fun liveDetails(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionLiveDetailsResponse> =
        liveDetails(id, SessionLiveDetailsParams.none(), requestOptions)

    /** Returns the live state of the session, including pages, tabs, and browser state */
    fun liveDetails(
        params: SessionLiveDetailsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionLiveDetailsResponse>

    /** @see liveDetails */
    fun liveDetails(
        params: SessionLiveDetailsParams
    ): CompletableFuture<SessionLiveDetailsResponse> = liveDetails(params, RequestOptions.none())

    /** @see logs */
    fun logs(
        id: String,
        params: SessionLogsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionLogsResponse> =
        logs(params.toBuilder().id(id).build(), requestOptions)

    /** @see logs */
    fun logs(id: String, params: SessionLogsParams): CompletableFuture<SessionLogsResponse> =
        logs(id, params, RequestOptions.none())

    /** @see logs */
    fun logs(id: String): CompletableFuture<SessionLogsResponse> =
        logs(id, SessionLogsParams.none())

    /** @see logs */
    fun logs(id: String, requestOptions: RequestOptions): CompletableFuture<SessionLogsResponse> =
        logs(id, SessionLogsParams.none(), requestOptions)

    /** Query browser event logs for a session */
    fun logs(
        params: SessionLogsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionLogsResponse>

    /** @see logs */
    fun logs(params: SessionLogsParams): CompletableFuture<SessionLogsResponse> =
        logs(params, RequestOptions.none())

    /** @see release */
    fun release(
        id: String,
        params: SessionReleaseParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReleaseResponse> =
        release(params.toBuilder().id(id).build(), requestOptions)

    /** @see release */
    fun release(
        id: String,
        params: SessionReleaseParams,
    ): CompletableFuture<SessionReleaseResponse> = release(id, params, RequestOptions.none())

    /** @see release */
    fun release(id: String): CompletableFuture<SessionReleaseResponse> =
        release(id, SessionReleaseParams.none())

    /** @see release */
    fun release(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReleaseResponse> =
        release(id, SessionReleaseParams.none(), requestOptions)

    /** Releases a specific session by ID. */
    fun release(
        params: SessionReleaseParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReleaseResponse>

    /** @see release */
    fun release(params: SessionReleaseParams): CompletableFuture<SessionReleaseResponse> =
        release(params, RequestOptions.none())

    /** Releases all active sessions for the current organization. */
    fun releaseAll(
        params: SessionReleaseAllParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReleaseAllResponse>

    /** @see releaseAll */
    fun releaseAll(params: SessionReleaseAllParams): CompletableFuture<SessionReleaseAllResponse> =
        releaseAll(params, RequestOptions.none())

    /** @see releaseAll */
    fun releaseAll(): CompletableFuture<SessionReleaseAllResponse> =
        releaseAll(SessionReleaseAllParams.none())

    /** @see releaseAll */
    fun releaseAll(requestOptions: RequestOptions): CompletableFuture<SessionReleaseAllResponse> =
        releaseAll(SessionReleaseAllParams.none(), requestOptions)

    /** @see replaceMetadata */
    fun replaceMetadata(
        id: String,
        params: SessionReplaceMetadataParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReplaceMetadataResponse> =
        replaceMetadata(params.toBuilder().id(id).build(), requestOptions)

    /** @see replaceMetadata */
    fun replaceMetadata(
        id: String,
        params: SessionReplaceMetadataParams,
    ): CompletableFuture<SessionReplaceMetadataResponse> =
        replaceMetadata(id, params, RequestOptions.none())

    /**
     * Atomically replace customer metadata. Send an empty object to clear it. Last accepted write
     * wins; use PATCH to change individual keys. Subject to session organization and project
     * access.
     */
    fun replaceMetadata(
        params: SessionReplaceMetadataParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionReplaceMetadataResponse>

    /** @see replaceMetadata */
    fun replaceMetadata(
        params: SessionReplaceMetadataParams
    ): CompletableFuture<SessionReplaceMetadataResponse> =
        replaceMetadata(params, RequestOptions.none())

    /**
     * Returns current signal labels and meanings for customer integrations. Uses normal Steel
     * API-key authentication. Includes no session data or administrative signals. Definitions do
     * not establish that detection ran for a particular session.
     */
    fun signalCatalog(
        params: SessionSignalCatalogParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionSignalCatalogResponse>

    /** @see signalCatalog */
    fun signalCatalog(
        params: SessionSignalCatalogParams
    ): CompletableFuture<SessionSignalCatalogResponse> =
        signalCatalog(params, RequestOptions.none())

    /** @see signalCatalog */
    fun signalCatalog(): CompletableFuture<SessionSignalCatalogResponse> =
        signalCatalog(SessionSignalCatalogParams.none())

    /** @see signalCatalog */
    fun signalCatalog(
        requestOptions: RequestOptions
    ): CompletableFuture<SessionSignalCatalogResponse> =
        signalCatalog(SessionSignalCatalogParams.none(), requestOptions)

    /** @see updateMetadata */
    fun updateMetadata(
        id: String,
        params: SessionUpdateMetadataParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionUpdateMetadataResponse> =
        updateMetadata(params.toBuilder().id(id).build(), requestOptions)

    /** @see updateMetadata */
    fun updateMetadata(
        id: String,
        params: SessionUpdateMetadataParams,
    ): CompletableFuture<SessionUpdateMetadataResponse> =
        updateMetadata(id, params, RequestOptions.none())

    /**
     * Atomically merge keys into customer metadata. Set a key to null to remove it; keys you leave
     * out are kept, so concurrent updates to different keys are all kept. Returns the merged
     * metadata, which must stay within the metadata limits. Subject to session organization and
     * project access.
     */
    fun updateMetadata(
        params: SessionUpdateMetadataParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionUpdateMetadataResponse>

    /** @see updateMetadata */
    fun updateMetadata(
        params: SessionUpdateMetadataParams
    ): CompletableFuture<SessionUpdateMetadataResponse> =
        updateMetadata(params, RequestOptions.none())

    /**
     * A view of [SessionServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SessionServiceAsync.WithRawResponse

        fun captchas(): CaptchaServiceAsync.WithRawResponse

        fun commandExecutions(): CommandExecutionServiceAsync.WithRawResponse

        fun files(): FileServiceAsync.WithRawResponse

        /** Creates a new session with the provided configuration. */
        fun create(
            params: SessionCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Session>>

        /** @see create */
        fun create(params: SessionCreateParams): CompletableFuture<HttpResponseFor<Session>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(): CompletableFuture<HttpResponseFor<Session>> =
            create(SessionCreateParams.none())

        /** @see create */
        fun create(requestOptions: RequestOptions): CompletableFuture<HttpResponseFor<Session>> =
            create(SessionCreateParams.none(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SessionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Session>> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SessionRetrieveParams,
        ): CompletableFuture<HttpResponseFor<Session>> = retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(id: String): CompletableFuture<HttpResponseFor<Session>> =
            retrieve(id, SessionRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Session>> =
            retrieve(id, SessionRetrieveParams.none(), requestOptions)

        /** Retrieves details of a specific session by ID. */
        fun retrieve(
            params: SessionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Session>>

        /** @see retrieve */
        fun retrieve(params: SessionRetrieveParams): CompletableFuture<HttpResponseFor<Session>> =
            retrieve(params, RequestOptions.none())

        /** Fetches all active sessions for the current organization. */
        fun list(
            params: SessionListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionListPageAsync>>

        /** @see list */
        fun list(
            params: SessionListParams
        ): CompletableFuture<HttpResponseFor<SessionListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<SessionListPageAsync>> =
            list(SessionListParams.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SessionListPageAsync>> =
            list(SessionListParams.none(), requestOptions)

        /** @see agentTraces */
        fun agentTraces(
            id: String,
            params: SessionAgentTracesParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionAgentTracesResponse>> =
            agentTraces(params.toBuilder().id(id).build(), requestOptions)

        /** @see agentTraces */
        fun agentTraces(
            id: String,
            params: SessionAgentTracesParams,
        ): CompletableFuture<HttpResponseFor<SessionAgentTracesResponse>> =
            agentTraces(id, params, RequestOptions.none())

        /** @see agentTraces */
        fun agentTraces(
            id: String
        ): CompletableFuture<HttpResponseFor<SessionAgentTracesResponse>> =
            agentTraces(id, SessionAgentTracesParams.none())

        /** @see agentTraces */
        fun agentTraces(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionAgentTracesResponse>> =
            agentTraces(id, SessionAgentTracesParams.none(), requestOptions)

        /**
         * Query the semantic agent trace timeline for a session: a coalesced, replay-oriented
         * record of user/agent interactions (clicks, typing, scrolls, drags, navigations) with
         * target metadata (role, accessible name, selectors). Returns an empty list for sessions
         * that predate browser-interaction capture.
         */
        fun agentTraces(
            params: SessionAgentTracesParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionAgentTracesResponse>>

        /** @see agentTraces */
        fun agentTraces(
            params: SessionAgentTracesParams
        ): CompletableFuture<HttpResponseFor<SessionAgentTracesResponse>> =
            agentTraces(params, RequestOptions.none())

        /** @see commands */
        fun commands(
            sessionId: String,
            params: SessionCommandsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCommandsResponse>> =
            commands(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see commands */
        fun commands(
            sessionId: String,
            params: SessionCommandsParams,
        ): CompletableFuture<HttpResponseFor<SessionCommandsResponse>> =
            commands(sessionId, params, RequestOptions.none())

        /**
         * Drives the session's browser with high-level commands such as snapshot, click, and fill.
         * Commands run in order and stop at the first failure. Send a random Idempotency-Key header
         * to make retries safe.
         */
        fun commands(
            params: SessionCommandsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCommandsResponse>>

        /** @see commands */
        fun commands(
            params: SessionCommandsParams
        ): CompletableFuture<HttpResponseFor<SessionCommandsResponse>> =
            commands(params, RequestOptions.none())

        /** @see computer */
        fun computer(
            sessionId: String,
            params: SessionComputerParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionComputerResponse>> =
            computer(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see computer */
        fun computer(
            sessionId: String,
            params: SessionComputerParams,
        ): CompletableFuture<HttpResponseFor<SessionComputerResponse>> =
            computer(sessionId, params, RequestOptions.none())

        /** Execute computer actions like mouse movements, clicks, keyboard input, and more */
        fun computer(
            params: SessionComputerParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionComputerResponse>>

        /** @see computer */
        fun computer(
            params: SessionComputerParams
        ): CompletableFuture<HttpResponseFor<SessionComputerResponse>> =
            computer(params, RequestOptions.none())

        /** @see context */
        fun context(
            id: String,
            params: SessionContextParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionContext>> =
            context(params.toBuilder().id(id).build(), requestOptions)

        /** @see context */
        fun context(
            id: String,
            params: SessionContextParams,
        ): CompletableFuture<HttpResponseFor<SessionContext>> =
            context(id, params, RequestOptions.none())

        /** @see context */
        fun context(id: String): CompletableFuture<HttpResponseFor<SessionContext>> =
            context(id, SessionContextParams.none())

        /** @see context */
        fun context(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionContext>> =
            context(id, SessionContextParams.none(), requestOptions)

        /** Fetches the context data of a specific session. */
        fun context(
            params: SessionContextParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionContext>>

        /** @see context */
        fun context(
            params: SessionContextParams
        ): CompletableFuture<HttpResponseFor<SessionContext>> =
            context(params, RequestOptions.none())

        /** @see cost */
        fun cost(
            id: String,
            params: SessionCostParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCostResponse>> =
            cost(params.toBuilder().id(id).build(), requestOptions)

        /** @see cost */
        fun cost(
            id: String,
            params: SessionCostParams,
        ): CompletableFuture<HttpResponseFor<SessionCostResponse>> =
            cost(id, params, RequestOptions.none())

        /** @see cost */
        fun cost(id: String): CompletableFuture<HttpResponseFor<SessionCostResponse>> =
            cost(id, SessionCostParams.none())

        /** @see cost */
        fun cost(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCostResponse>> =
            cost(id, SessionCostParams.none(), requestOptions)

        /**
         * Returns the finalized cost of a released session as an exact amount in US dollars (e.g.
         * 0.031905), suitable for precise billing passthrough.
         */
        fun cost(
            params: SessionCostParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCostResponse>>

        /** @see cost */
        fun cost(
            params: SessionCostParams
        ): CompletableFuture<HttpResponseFor<SessionCostResponse>> =
            cost(params, RequestOptions.none())

        /** @see events */
        fun events(
            id: String,
            params: SessionEventsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<JsonValue>>> =
            events(params.toBuilder().id(id).build(), requestOptions)

        /** @see events */
        fun events(
            id: String,
            params: SessionEventsParams,
        ): CompletableFuture<HttpResponseFor<List<JsonValue>>> =
            events(id, params, RequestOptions.none())

        /** @see events */
        fun events(id: String): CompletableFuture<HttpResponseFor<List<JsonValue>>> =
            events(id, SessionEventsParams.none())

        /** @see events */
        fun events(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<JsonValue>>> =
            events(id, SessionEventsParams.none(), requestOptions)

        /** This endpoint allows you to get the recorded session events in the RRWeb format */
        fun events(
            params: SessionEventsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<JsonValue>>>

        /** @see events */
        fun events(
            params: SessionEventsParams
        ): CompletableFuture<HttpResponseFor<List<JsonValue>>> =
            events(params, RequestOptions.none())

        /** @see hls */
        fun hls(
            id: String,
            params: SessionHlsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> = hls(params.toBuilder().id(id).build(), requestOptions)

        /** @see hls */
        fun hls(id: String, params: SessionHlsParams): CompletableFuture<HttpResponse> =
            hls(id, params, RequestOptions.none())

        /** @see hls */
        fun hls(id: String): CompletableFuture<HttpResponse> = hls(id, SessionHlsParams.none())

        /** @see hls */
        fun hls(id: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
            hls(id, SessionHlsParams.none(), requestOptions)

        /** Get HLS playlist for a session recording using app auth or an RTC token */
        fun hls(
            params: SessionHlsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see hls */
        fun hls(params: SessionHlsParams): CompletableFuture<HttpResponse> =
            hls(params, RequestOptions.none())

        /** @see liveDetails */
        fun liveDetails(
            id: String,
            params: SessionLiveDetailsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLiveDetailsResponse>> =
            liveDetails(params.toBuilder().id(id).build(), requestOptions)

        /** @see liveDetails */
        fun liveDetails(
            id: String,
            params: SessionLiveDetailsParams,
        ): CompletableFuture<HttpResponseFor<SessionLiveDetailsResponse>> =
            liveDetails(id, params, RequestOptions.none())

        /** @see liveDetails */
        fun liveDetails(
            id: String
        ): CompletableFuture<HttpResponseFor<SessionLiveDetailsResponse>> =
            liveDetails(id, SessionLiveDetailsParams.none())

        /** @see liveDetails */
        fun liveDetails(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLiveDetailsResponse>> =
            liveDetails(id, SessionLiveDetailsParams.none(), requestOptions)

        /** Returns the live state of the session, including pages, tabs, and browser state */
        fun liveDetails(
            params: SessionLiveDetailsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLiveDetailsResponse>>

        /** @see liveDetails */
        fun liveDetails(
            params: SessionLiveDetailsParams
        ): CompletableFuture<HttpResponseFor<SessionLiveDetailsResponse>> =
            liveDetails(params, RequestOptions.none())

        /** @see logs */
        fun logs(
            id: String,
            params: SessionLogsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLogsResponse>> =
            logs(params.toBuilder().id(id).build(), requestOptions)

        /** @see logs */
        fun logs(
            id: String,
            params: SessionLogsParams,
        ): CompletableFuture<HttpResponseFor<SessionLogsResponse>> =
            logs(id, params, RequestOptions.none())

        /** @see logs */
        fun logs(id: String): CompletableFuture<HttpResponseFor<SessionLogsResponse>> =
            logs(id, SessionLogsParams.none())

        /** @see logs */
        fun logs(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLogsResponse>> =
            logs(id, SessionLogsParams.none(), requestOptions)

        /** Query browser event logs for a session */
        fun logs(
            params: SessionLogsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionLogsResponse>>

        /** @see logs */
        fun logs(
            params: SessionLogsParams
        ): CompletableFuture<HttpResponseFor<SessionLogsResponse>> =
            logs(params, RequestOptions.none())

        /** @see release */
        fun release(
            id: String,
            params: SessionReleaseParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReleaseResponse>> =
            release(params.toBuilder().id(id).build(), requestOptions)

        /** @see release */
        fun release(
            id: String,
            params: SessionReleaseParams,
        ): CompletableFuture<HttpResponseFor<SessionReleaseResponse>> =
            release(id, params, RequestOptions.none())

        /** @see release */
        fun release(id: String): CompletableFuture<HttpResponseFor<SessionReleaseResponse>> =
            release(id, SessionReleaseParams.none())

        /** @see release */
        fun release(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReleaseResponse>> =
            release(id, SessionReleaseParams.none(), requestOptions)

        /** Releases a specific session by ID. */
        fun release(
            params: SessionReleaseParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReleaseResponse>>

        /** @see release */
        fun release(
            params: SessionReleaseParams
        ): CompletableFuture<HttpResponseFor<SessionReleaseResponse>> =
            release(params, RequestOptions.none())

        /** Releases all active sessions for the current organization. */
        fun releaseAll(
            params: SessionReleaseAllParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReleaseAllResponse>>

        /** @see releaseAll */
        fun releaseAll(
            params: SessionReleaseAllParams
        ): CompletableFuture<HttpResponseFor<SessionReleaseAllResponse>> =
            releaseAll(params, RequestOptions.none())

        /** @see releaseAll */
        fun releaseAll(): CompletableFuture<HttpResponseFor<SessionReleaseAllResponse>> =
            releaseAll(SessionReleaseAllParams.none())

        /** @see releaseAll */
        fun releaseAll(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SessionReleaseAllResponse>> =
            releaseAll(SessionReleaseAllParams.none(), requestOptions)

        /** @see replaceMetadata */
        fun replaceMetadata(
            id: String,
            params: SessionReplaceMetadataParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReplaceMetadataResponse>> =
            replaceMetadata(params.toBuilder().id(id).build(), requestOptions)

        /** @see replaceMetadata */
        fun replaceMetadata(
            id: String,
            params: SessionReplaceMetadataParams,
        ): CompletableFuture<HttpResponseFor<SessionReplaceMetadataResponse>> =
            replaceMetadata(id, params, RequestOptions.none())

        /**
         * Atomically replace customer metadata. Send an empty object to clear it. Last accepted
         * write wins; use PATCH to change individual keys. Subject to session organization and
         * project access.
         */
        fun replaceMetadata(
            params: SessionReplaceMetadataParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionReplaceMetadataResponse>>

        /** @see replaceMetadata */
        fun replaceMetadata(
            params: SessionReplaceMetadataParams
        ): CompletableFuture<HttpResponseFor<SessionReplaceMetadataResponse>> =
            replaceMetadata(params, RequestOptions.none())

        /**
         * Returns current signal labels and meanings for customer integrations. Uses normal Steel
         * API-key authentication. Includes no session data or administrative signals. Definitions
         * do not establish that detection ran for a particular session.
         */
        fun signalCatalog(
            params: SessionSignalCatalogParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionSignalCatalogResponse>>

        /** @see signalCatalog */
        fun signalCatalog(
            params: SessionSignalCatalogParams
        ): CompletableFuture<HttpResponseFor<SessionSignalCatalogResponse>> =
            signalCatalog(params, RequestOptions.none())

        /** @see signalCatalog */
        fun signalCatalog(): CompletableFuture<HttpResponseFor<SessionSignalCatalogResponse>> =
            signalCatalog(SessionSignalCatalogParams.none())

        /** @see signalCatalog */
        fun signalCatalog(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SessionSignalCatalogResponse>> =
            signalCatalog(SessionSignalCatalogParams.none(), requestOptions)

        /** @see updateMetadata */
        fun updateMetadata(
            id: String,
            params: SessionUpdateMetadataParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionUpdateMetadataResponse>> =
            updateMetadata(params.toBuilder().id(id).build(), requestOptions)

        /** @see updateMetadata */
        fun updateMetadata(
            id: String,
            params: SessionUpdateMetadataParams,
        ): CompletableFuture<HttpResponseFor<SessionUpdateMetadataResponse>> =
            updateMetadata(id, params, RequestOptions.none())

        /**
         * Atomically merge keys into customer metadata. Set a key to null to remove it; keys you
         * leave out are kept, so concurrent updates to different keys are all kept. Returns the
         * merged metadata, which must stay within the metadata limits. Subject to session
         * organization and project access.
         */
        fun updateMetadata(
            params: SessionUpdateMetadataParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionUpdateMetadataResponse>>

        /** @see updateMetadata */
        fun updateMetadata(
            params: SessionUpdateMetadataParams
        ): CompletableFuture<HttpResponseFor<SessionUpdateMetadataResponse>> =
            updateMetadata(params, RequestOptions.none())
    }
}
