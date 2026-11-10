package dev.steel.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
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
import dev.steel.api.services.blocking.sessions.CaptchaService
import dev.steel.api.services.blocking.sessions.CommandExecutionService
import dev.steel.api.services.blocking.sessions.FileService
import java.util.function.Consumer

interface SessionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionService

    fun captchas(): CaptchaService

    fun commandExecutions(): CommandExecutionService

    fun files(): FileService

    /** Creates a new session with the provided configuration. */
    fun create(params: SessionCreateParams, requestOptions: RequestOptions): Session

    /** @see create */
    fun create(params: SessionCreateParams): Session = create(params, RequestOptions.none())

    /** @see create */
    fun create(): Session = create(SessionCreateParams.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): Session =
        create(SessionCreateParams.none(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SessionRetrieveParams,
        requestOptions: RequestOptions,
    ): Session = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(id: String, params: SessionRetrieveParams): Session =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): Session = retrieve(id, SessionRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): Session =
        retrieve(id, SessionRetrieveParams.none(), requestOptions)

    /** Retrieves details of a specific session by ID. */
    fun retrieve(params: SessionRetrieveParams, requestOptions: RequestOptions): Session

    /** @see retrieve */
    fun retrieve(params: SessionRetrieveParams): Session = retrieve(params, RequestOptions.none())

    /** Fetches all active sessions for the current organization. */
    fun list(params: SessionListParams, requestOptions: RequestOptions): SessionListPage

    /** @see list */
    fun list(params: SessionListParams): SessionListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(): SessionListPage = list(SessionListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): SessionListPage =
        list(SessionListParams.none(), requestOptions)

    /** @see agentTraces */
    fun agentTraces(
        id: String,
        params: SessionAgentTracesParams,
        requestOptions: RequestOptions,
    ): SessionAgentTracesResponse = agentTraces(params.toBuilder().id(id).build(), requestOptions)

    /** @see agentTraces */
    fun agentTraces(id: String, params: SessionAgentTracesParams): SessionAgentTracesResponse =
        agentTraces(id, params, RequestOptions.none())

    /** @see agentTraces */
    fun agentTraces(id: String): SessionAgentTracesResponse =
        agentTraces(id, SessionAgentTracesParams.none())

    /** @see agentTraces */
    fun agentTraces(id: String, requestOptions: RequestOptions): SessionAgentTracesResponse =
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
    ): SessionAgentTracesResponse

    /** @see agentTraces */
    fun agentTraces(params: SessionAgentTracesParams): SessionAgentTracesResponse =
        agentTraces(params, RequestOptions.none())

    /** @see commands */
    fun commands(
        sessionId: String,
        params: SessionCommandsParams,
        requestOptions: RequestOptions,
    ): SessionCommandsResponse =
        commands(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see commands */
    fun commands(sessionId: String, params: SessionCommandsParams): SessionCommandsResponse =
        commands(sessionId, params, RequestOptions.none())

    /**
     * Drives the session's browser with high-level commands such as snapshot, click, and fill.
     * Commands run in order and stop at the first failure. Send a random Idempotency-Key header to
     * make retries safe.
     */
    fun commands(
        params: SessionCommandsParams,
        requestOptions: RequestOptions,
    ): SessionCommandsResponse

    /** @see commands */
    fun commands(params: SessionCommandsParams): SessionCommandsResponse =
        commands(params, RequestOptions.none())

    /** @see computer */
    fun computer(
        sessionId: String,
        params: SessionComputerParams,
        requestOptions: RequestOptions,
    ): SessionComputerResponse =
        computer(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see computer */
    fun computer(sessionId: String, params: SessionComputerParams): SessionComputerResponse =
        computer(sessionId, params, RequestOptions.none())

    /** Execute computer actions like mouse movements, clicks, keyboard input, and more */
    fun computer(
        params: SessionComputerParams,
        requestOptions: RequestOptions,
    ): SessionComputerResponse

    /** @see computer */
    fun computer(params: SessionComputerParams): SessionComputerResponse =
        computer(params, RequestOptions.none())

    /** @see context */
    fun context(
        id: String,
        params: SessionContextParams,
        requestOptions: RequestOptions,
    ): SessionContext = context(params.toBuilder().id(id).build(), requestOptions)

    /** @see context */
    fun context(id: String, params: SessionContextParams): SessionContext =
        context(id, params, RequestOptions.none())

    /** @see context */
    fun context(id: String): SessionContext = context(id, SessionContextParams.none())

    /** @see context */
    fun context(id: String, requestOptions: RequestOptions): SessionContext =
        context(id, SessionContextParams.none(), requestOptions)

    /** Fetches the context data of a specific session. */
    fun context(params: SessionContextParams, requestOptions: RequestOptions): SessionContext

    /** @see context */
    fun context(params: SessionContextParams): SessionContext =
        context(params, RequestOptions.none())

    /** @see cost */
    fun cost(
        id: String,
        params: SessionCostParams,
        requestOptions: RequestOptions,
    ): SessionCostResponse = cost(params.toBuilder().id(id).build(), requestOptions)

    /** @see cost */
    fun cost(id: String, params: SessionCostParams): SessionCostResponse =
        cost(id, params, RequestOptions.none())

    /** @see cost */
    fun cost(id: String): SessionCostResponse = cost(id, SessionCostParams.none())

    /** @see cost */
    fun cost(id: String, requestOptions: RequestOptions): SessionCostResponse =
        cost(id, SessionCostParams.none(), requestOptions)

    /**
     * Returns the finalized cost of a released session as an exact amount in US dollars (e.g.
     * 0.031905), suitable for precise billing passthrough.
     */
    fun cost(params: SessionCostParams, requestOptions: RequestOptions): SessionCostResponse

    /** @see cost */
    fun cost(params: SessionCostParams): SessionCostResponse = cost(params, RequestOptions.none())

    /** @see events */
    fun events(
        id: String,
        params: SessionEventsParams,
        requestOptions: RequestOptions,
    ): List<JsonValue> = events(params.toBuilder().id(id).build(), requestOptions)

    /** @see events */
    fun events(id: String, params: SessionEventsParams): List<JsonValue> =
        events(id, params, RequestOptions.none())

    /** @see events */
    fun events(id: String): List<JsonValue> = events(id, SessionEventsParams.none())

    /** @see events */
    fun events(id: String, requestOptions: RequestOptions): List<JsonValue> =
        events(id, SessionEventsParams.none(), requestOptions)

    /** This endpoint allows you to get the recorded session events in the RRWeb format */
    fun events(params: SessionEventsParams, requestOptions: RequestOptions): List<JsonValue>

    /** @see events */
    fun events(params: SessionEventsParams): List<JsonValue> = events(params, RequestOptions.none())

    /** @see hls */
    @MustBeClosed
    fun hls(id: String, params: SessionHlsParams, requestOptions: RequestOptions): HttpResponse =
        hls(params.toBuilder().id(id).build(), requestOptions)

    /** @see hls */
    @MustBeClosed
    fun hls(id: String, params: SessionHlsParams): HttpResponse =
        hls(id, params, RequestOptions.none())

    /** @see hls */
    @MustBeClosed fun hls(id: String): HttpResponse = hls(id, SessionHlsParams.none())

    /** @see hls */
    @MustBeClosed
    fun hls(id: String, requestOptions: RequestOptions): HttpResponse =
        hls(id, SessionHlsParams.none(), requestOptions)

    /** Get HLS playlist for a session recording using app auth or an RTC token */
    @MustBeClosed fun hls(params: SessionHlsParams, requestOptions: RequestOptions): HttpResponse

    /** @see hls */
    @MustBeClosed
    fun hls(params: SessionHlsParams): HttpResponse = hls(params, RequestOptions.none())

    /** @see liveDetails */
    fun liveDetails(
        id: String,
        params: SessionLiveDetailsParams,
        requestOptions: RequestOptions,
    ): SessionLiveDetailsResponse = liveDetails(params.toBuilder().id(id).build(), requestOptions)

    /** @see liveDetails */
    fun liveDetails(id: String, params: SessionLiveDetailsParams): SessionLiveDetailsResponse =
        liveDetails(id, params, RequestOptions.none())

    /** @see liveDetails */
    fun liveDetails(id: String): SessionLiveDetailsResponse =
        liveDetails(id, SessionLiveDetailsParams.none())

    /** @see liveDetails */
    fun liveDetails(id: String, requestOptions: RequestOptions): SessionLiveDetailsResponse =
        liveDetails(id, SessionLiveDetailsParams.none(), requestOptions)

    /** Returns the live state of the session, including pages, tabs, and browser state */
    fun liveDetails(
        params: SessionLiveDetailsParams,
        requestOptions: RequestOptions,
    ): SessionLiveDetailsResponse

    /** @see liveDetails */
    fun liveDetails(params: SessionLiveDetailsParams): SessionLiveDetailsResponse =
        liveDetails(params, RequestOptions.none())

    /** @see logs */
    fun logs(
        id: String,
        params: SessionLogsParams,
        requestOptions: RequestOptions,
    ): SessionLogsResponse = logs(params.toBuilder().id(id).build(), requestOptions)

    /** @see logs */
    fun logs(id: String, params: SessionLogsParams): SessionLogsResponse =
        logs(id, params, RequestOptions.none())

    /** @see logs */
    fun logs(id: String): SessionLogsResponse = logs(id, SessionLogsParams.none())

    /** @see logs */
    fun logs(id: String, requestOptions: RequestOptions): SessionLogsResponse =
        logs(id, SessionLogsParams.none(), requestOptions)

    /** Query browser event logs for a session */
    fun logs(params: SessionLogsParams, requestOptions: RequestOptions): SessionLogsResponse

    /** @see logs */
    fun logs(params: SessionLogsParams): SessionLogsResponse = logs(params, RequestOptions.none())

    /** @see release */
    fun release(
        id: String,
        params: SessionReleaseParams,
        requestOptions: RequestOptions,
    ): SessionReleaseResponse = release(params.toBuilder().id(id).build(), requestOptions)

    /** @see release */
    fun release(id: String, params: SessionReleaseParams): SessionReleaseResponse =
        release(id, params, RequestOptions.none())

    /** @see release */
    fun release(id: String): SessionReleaseResponse = release(id, SessionReleaseParams.none())

    /** @see release */
    fun release(id: String, requestOptions: RequestOptions): SessionReleaseResponse =
        release(id, SessionReleaseParams.none(), requestOptions)

    /** Releases a specific session by ID. */
    fun release(
        params: SessionReleaseParams,
        requestOptions: RequestOptions,
    ): SessionReleaseResponse

    /** @see release */
    fun release(params: SessionReleaseParams): SessionReleaseResponse =
        release(params, RequestOptions.none())

    /** Releases all active sessions for the current organization. */
    fun releaseAll(
        params: SessionReleaseAllParams,
        requestOptions: RequestOptions,
    ): SessionReleaseAllResponse

    /** @see releaseAll */
    fun releaseAll(params: SessionReleaseAllParams): SessionReleaseAllResponse =
        releaseAll(params, RequestOptions.none())

    /** @see releaseAll */
    fun releaseAll(): SessionReleaseAllResponse = releaseAll(SessionReleaseAllParams.none())

    /** @see releaseAll */
    fun releaseAll(requestOptions: RequestOptions): SessionReleaseAllResponse =
        releaseAll(SessionReleaseAllParams.none(), requestOptions)

    /** @see replaceMetadata */
    fun replaceMetadata(
        id: String,
        params: SessionReplaceMetadataParams,
        requestOptions: RequestOptions,
    ): SessionReplaceMetadataResponse =
        replaceMetadata(params.toBuilder().id(id).build(), requestOptions)

    /** @see replaceMetadata */
    fun replaceMetadata(
        id: String,
        params: SessionReplaceMetadataParams,
    ): SessionReplaceMetadataResponse = replaceMetadata(id, params, RequestOptions.none())

    /**
     * Atomically replace customer metadata. Send an empty object to clear it. Last accepted write
     * wins; use PATCH to change individual keys. Subject to session organization and project
     * access.
     */
    fun replaceMetadata(
        params: SessionReplaceMetadataParams,
        requestOptions: RequestOptions,
    ): SessionReplaceMetadataResponse

    /** @see replaceMetadata */
    fun replaceMetadata(params: SessionReplaceMetadataParams): SessionReplaceMetadataResponse =
        replaceMetadata(params, RequestOptions.none())

    /**
     * Returns current signal labels and meanings for customer integrations. Uses normal Steel
     * API-key authentication. Includes no session data or administrative signals. Definitions do
     * not establish that detection ran for a particular session.
     */
    fun signalCatalog(
        params: SessionSignalCatalogParams,
        requestOptions: RequestOptions,
    ): SessionSignalCatalogResponse

    /** @see signalCatalog */
    fun signalCatalog(params: SessionSignalCatalogParams): SessionSignalCatalogResponse =
        signalCatalog(params, RequestOptions.none())

    /** @see signalCatalog */
    fun signalCatalog(): SessionSignalCatalogResponse =
        signalCatalog(SessionSignalCatalogParams.none())

    /** @see signalCatalog */
    fun signalCatalog(requestOptions: RequestOptions): SessionSignalCatalogResponse =
        signalCatalog(SessionSignalCatalogParams.none(), requestOptions)

    /** @see updateMetadata */
    fun updateMetadata(
        id: String,
        params: SessionUpdateMetadataParams,
        requestOptions: RequestOptions,
    ): SessionUpdateMetadataResponse =
        updateMetadata(params.toBuilder().id(id).build(), requestOptions)

    /** @see updateMetadata */
    fun updateMetadata(
        id: String,
        params: SessionUpdateMetadataParams,
    ): SessionUpdateMetadataResponse = updateMetadata(id, params, RequestOptions.none())

    /**
     * Atomically merge keys into customer metadata. Set a key to null to remove it; keys you leave
     * out are kept, so concurrent updates to different keys are all kept. Returns the merged
     * metadata, which must stay within the metadata limits. Subject to session organization and
     * project access.
     */
    fun updateMetadata(
        params: SessionUpdateMetadataParams,
        requestOptions: RequestOptions,
    ): SessionUpdateMetadataResponse

    /** @see updateMetadata */
    fun updateMetadata(params: SessionUpdateMetadataParams): SessionUpdateMetadataResponse =
        updateMetadata(params, RequestOptions.none())

    /** A view of [SessionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionService.WithRawResponse

        fun captchas(): CaptchaService.WithRawResponse

        fun commandExecutions(): CommandExecutionService.WithRawResponse

        fun files(): FileService.WithRawResponse

        /** Creates a new session with the provided configuration. */
        @MustBeClosed
        fun create(
            params: SessionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Session>

        /** @see create */
        @MustBeClosed
        fun create(params: SessionCreateParams): HttpResponseFor<Session> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed fun create(): HttpResponseFor<Session> = create(SessionCreateParams.none())

        /** @see create */
        @MustBeClosed
        fun create(requestOptions: RequestOptions): HttpResponseFor<Session> =
            create(SessionCreateParams.none(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: SessionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Session> = retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String, params: SessionRetrieveParams): HttpResponseFor<Session> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String): HttpResponseFor<Session> =
            retrieve(id, SessionRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String, requestOptions: RequestOptions): HttpResponseFor<Session> =
            retrieve(id, SessionRetrieveParams.none(), requestOptions)

        /** Retrieves details of a specific session by ID. */
        @MustBeClosed
        fun retrieve(
            params: SessionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Session>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: SessionRetrieveParams): HttpResponseFor<Session> =
            retrieve(params, RequestOptions.none())

        /** Fetches all active sessions for the current organization. */
        @MustBeClosed
        fun list(
            params: SessionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: SessionListParams): HttpResponseFor<SessionListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed fun list(): HttpResponseFor<SessionListPage> = list(SessionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<SessionListPage> =
            list(SessionListParams.none(), requestOptions)

        /** @see agentTraces */
        @MustBeClosed
        fun agentTraces(
            id: String,
            params: SessionAgentTracesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionAgentTracesResponse> =
            agentTraces(params.toBuilder().id(id).build(), requestOptions)

        /** @see agentTraces */
        @MustBeClosed
        fun agentTraces(
            id: String,
            params: SessionAgentTracesParams,
        ): HttpResponseFor<SessionAgentTracesResponse> =
            agentTraces(id, params, RequestOptions.none())

        /** @see agentTraces */
        @MustBeClosed
        fun agentTraces(id: String): HttpResponseFor<SessionAgentTracesResponse> =
            agentTraces(id, SessionAgentTracesParams.none())

        /** @see agentTraces */
        @MustBeClosed
        fun agentTraces(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionAgentTracesResponse> =
            agentTraces(id, SessionAgentTracesParams.none(), requestOptions)

        /**
         * Query the semantic agent trace timeline for a session: a coalesced, replay-oriented
         * record of user/agent interactions (clicks, typing, scrolls, drags, navigations) with
         * target metadata (role, accessible name, selectors). Returns an empty list for sessions
         * that predate browser-interaction capture.
         */
        @MustBeClosed
        fun agentTraces(
            params: SessionAgentTracesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionAgentTracesResponse>

        /** @see agentTraces */
        @MustBeClosed
        fun agentTraces(
            params: SessionAgentTracesParams
        ): HttpResponseFor<SessionAgentTracesResponse> = agentTraces(params, RequestOptions.none())

        /** @see commands */
        @MustBeClosed
        fun commands(
            sessionId: String,
            params: SessionCommandsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCommandsResponse> =
            commands(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see commands */
        @MustBeClosed
        fun commands(
            sessionId: String,
            params: SessionCommandsParams,
        ): HttpResponseFor<SessionCommandsResponse> =
            commands(sessionId, params, RequestOptions.none())

        /**
         * Drives the session's browser with high-level commands such as snapshot, click, and fill.
         * Commands run in order and stop at the first failure. Send a random Idempotency-Key header
         * to make retries safe.
         */
        @MustBeClosed
        fun commands(
            params: SessionCommandsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCommandsResponse>

        /** @see commands */
        @MustBeClosed
        fun commands(params: SessionCommandsParams): HttpResponseFor<SessionCommandsResponse> =
            commands(params, RequestOptions.none())

        /** @see computer */
        @MustBeClosed
        fun computer(
            sessionId: String,
            params: SessionComputerParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionComputerResponse> =
            computer(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see computer */
        @MustBeClosed
        fun computer(
            sessionId: String,
            params: SessionComputerParams,
        ): HttpResponseFor<SessionComputerResponse> =
            computer(sessionId, params, RequestOptions.none())

        /** Execute computer actions like mouse movements, clicks, keyboard input, and more */
        @MustBeClosed
        fun computer(
            params: SessionComputerParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionComputerResponse>

        /** @see computer */
        @MustBeClosed
        fun computer(params: SessionComputerParams): HttpResponseFor<SessionComputerResponse> =
            computer(params, RequestOptions.none())

        /** @see context */
        @MustBeClosed
        fun context(
            id: String,
            params: SessionContextParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionContext> =
            context(params.toBuilder().id(id).build(), requestOptions)

        /** @see context */
        @MustBeClosed
        fun context(id: String, params: SessionContextParams): HttpResponseFor<SessionContext> =
            context(id, params, RequestOptions.none())

        /** @see context */
        @MustBeClosed
        fun context(id: String): HttpResponseFor<SessionContext> =
            context(id, SessionContextParams.none())

        /** @see context */
        @MustBeClosed
        fun context(id: String, requestOptions: RequestOptions): HttpResponseFor<SessionContext> =
            context(id, SessionContextParams.none(), requestOptions)

        /** Fetches the context data of a specific session. */
        @MustBeClosed
        fun context(
            params: SessionContextParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionContext>

        /** @see context */
        @MustBeClosed
        fun context(params: SessionContextParams): HttpResponseFor<SessionContext> =
            context(params, RequestOptions.none())

        /** @see cost */
        @MustBeClosed
        fun cost(
            id: String,
            params: SessionCostParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCostResponse> =
            cost(params.toBuilder().id(id).build(), requestOptions)

        /** @see cost */
        @MustBeClosed
        fun cost(id: String, params: SessionCostParams): HttpResponseFor<SessionCostResponse> =
            cost(id, params, RequestOptions.none())

        /** @see cost */
        @MustBeClosed
        fun cost(id: String): HttpResponseFor<SessionCostResponse> =
            cost(id, SessionCostParams.none())

        /** @see cost */
        @MustBeClosed
        fun cost(id: String, requestOptions: RequestOptions): HttpResponseFor<SessionCostResponse> =
            cost(id, SessionCostParams.none(), requestOptions)

        /**
         * Returns the finalized cost of a released session as an exact amount in US dollars (e.g.
         * 0.031905), suitable for precise billing passthrough.
         */
        @MustBeClosed
        fun cost(
            params: SessionCostParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCostResponse>

        /** @see cost */
        @MustBeClosed
        fun cost(params: SessionCostParams): HttpResponseFor<SessionCostResponse> =
            cost(params, RequestOptions.none())

        /** @see events */
        @MustBeClosed
        fun events(
            id: String,
            params: SessionEventsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<List<JsonValue>> =
            events(params.toBuilder().id(id).build(), requestOptions)

        /** @see events */
        @MustBeClosed
        fun events(id: String, params: SessionEventsParams): HttpResponseFor<List<JsonValue>> =
            events(id, params, RequestOptions.none())

        /** @see events */
        @MustBeClosed
        fun events(id: String): HttpResponseFor<List<JsonValue>> =
            events(id, SessionEventsParams.none())

        /** @see events */
        @MustBeClosed
        fun events(id: String, requestOptions: RequestOptions): HttpResponseFor<List<JsonValue>> =
            events(id, SessionEventsParams.none(), requestOptions)

        /** This endpoint allows you to get the recorded session events in the RRWeb format */
        @MustBeClosed
        fun events(
            params: SessionEventsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<List<JsonValue>>

        /** @see events */
        @MustBeClosed
        fun events(params: SessionEventsParams): HttpResponseFor<List<JsonValue>> =
            events(params, RequestOptions.none())

        /** @see hls */
        @MustBeClosed
        fun hls(
            id: String,
            params: SessionHlsParams,
            requestOptions: RequestOptions,
        ): HttpResponse = hls(params.toBuilder().id(id).build(), requestOptions)

        /** @see hls */
        @MustBeClosed
        fun hls(id: String, params: SessionHlsParams): HttpResponse =
            hls(id, params, RequestOptions.none())

        /** @see hls */
        @MustBeClosed fun hls(id: String): HttpResponse = hls(id, SessionHlsParams.none())

        /** @see hls */
        @MustBeClosed
        fun hls(id: String, requestOptions: RequestOptions): HttpResponse =
            hls(id, SessionHlsParams.none(), requestOptions)

        /** Get HLS playlist for a session recording using app auth or an RTC token */
        @MustBeClosed
        fun hls(params: SessionHlsParams, requestOptions: RequestOptions): HttpResponse

        /** @see hls */
        @MustBeClosed
        fun hls(params: SessionHlsParams): HttpResponse = hls(params, RequestOptions.none())

        /** @see liveDetails */
        @MustBeClosed
        fun liveDetails(
            id: String,
            params: SessionLiveDetailsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionLiveDetailsResponse> =
            liveDetails(params.toBuilder().id(id).build(), requestOptions)

        /** @see liveDetails */
        @MustBeClosed
        fun liveDetails(
            id: String,
            params: SessionLiveDetailsParams,
        ): HttpResponseFor<SessionLiveDetailsResponse> =
            liveDetails(id, params, RequestOptions.none())

        /** @see liveDetails */
        @MustBeClosed
        fun liveDetails(id: String): HttpResponseFor<SessionLiveDetailsResponse> =
            liveDetails(id, SessionLiveDetailsParams.none())

        /** @see liveDetails */
        @MustBeClosed
        fun liveDetails(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionLiveDetailsResponse> =
            liveDetails(id, SessionLiveDetailsParams.none(), requestOptions)

        /** Returns the live state of the session, including pages, tabs, and browser state */
        @MustBeClosed
        fun liveDetails(
            params: SessionLiveDetailsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionLiveDetailsResponse>

        /** @see liveDetails */
        @MustBeClosed
        fun liveDetails(
            params: SessionLiveDetailsParams
        ): HttpResponseFor<SessionLiveDetailsResponse> = liveDetails(params, RequestOptions.none())

        /** @see logs */
        @MustBeClosed
        fun logs(
            id: String,
            params: SessionLogsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionLogsResponse> =
            logs(params.toBuilder().id(id).build(), requestOptions)

        /** @see logs */
        @MustBeClosed
        fun logs(id: String, params: SessionLogsParams): HttpResponseFor<SessionLogsResponse> =
            logs(id, params, RequestOptions.none())

        /** @see logs */
        @MustBeClosed
        fun logs(id: String): HttpResponseFor<SessionLogsResponse> =
            logs(id, SessionLogsParams.none())

        /** @see logs */
        @MustBeClosed
        fun logs(id: String, requestOptions: RequestOptions): HttpResponseFor<SessionLogsResponse> =
            logs(id, SessionLogsParams.none(), requestOptions)

        /** Query browser event logs for a session */
        @MustBeClosed
        fun logs(
            params: SessionLogsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionLogsResponse>

        /** @see logs */
        @MustBeClosed
        fun logs(params: SessionLogsParams): HttpResponseFor<SessionLogsResponse> =
            logs(params, RequestOptions.none())

        /** @see release */
        @MustBeClosed
        fun release(
            id: String,
            params: SessionReleaseParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReleaseResponse> =
            release(params.toBuilder().id(id).build(), requestOptions)

        /** @see release */
        @MustBeClosed
        fun release(
            id: String,
            params: SessionReleaseParams,
        ): HttpResponseFor<SessionReleaseResponse> = release(id, params, RequestOptions.none())

        /** @see release */
        @MustBeClosed
        fun release(id: String): HttpResponseFor<SessionReleaseResponse> =
            release(id, SessionReleaseParams.none())

        /** @see release */
        @MustBeClosed
        fun release(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReleaseResponse> =
            release(id, SessionReleaseParams.none(), requestOptions)

        /** Releases a specific session by ID. */
        @MustBeClosed
        fun release(
            params: SessionReleaseParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReleaseResponse>

        /** @see release */
        @MustBeClosed
        fun release(params: SessionReleaseParams): HttpResponseFor<SessionReleaseResponse> =
            release(params, RequestOptions.none())

        /** Releases all active sessions for the current organization. */
        @MustBeClosed
        fun releaseAll(
            params: SessionReleaseAllParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReleaseAllResponse>

        /** @see releaseAll */
        @MustBeClosed
        fun releaseAll(
            params: SessionReleaseAllParams
        ): HttpResponseFor<SessionReleaseAllResponse> = releaseAll(params, RequestOptions.none())

        /** @see releaseAll */
        @MustBeClosed
        fun releaseAll(): HttpResponseFor<SessionReleaseAllResponse> =
            releaseAll(SessionReleaseAllParams.none())

        /** @see releaseAll */
        @MustBeClosed
        fun releaseAll(requestOptions: RequestOptions): HttpResponseFor<SessionReleaseAllResponse> =
            releaseAll(SessionReleaseAllParams.none(), requestOptions)

        /** @see replaceMetadata */
        @MustBeClosed
        fun replaceMetadata(
            id: String,
            params: SessionReplaceMetadataParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReplaceMetadataResponse> =
            replaceMetadata(params.toBuilder().id(id).build(), requestOptions)

        /** @see replaceMetadata */
        @MustBeClosed
        fun replaceMetadata(
            id: String,
            params: SessionReplaceMetadataParams,
        ): HttpResponseFor<SessionReplaceMetadataResponse> =
            replaceMetadata(id, params, RequestOptions.none())

        /**
         * Atomically replace customer metadata. Send an empty object to clear it. Last accepted
         * write wins; use PATCH to change individual keys. Subject to session organization and
         * project access.
         */
        @MustBeClosed
        fun replaceMetadata(
            params: SessionReplaceMetadataParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionReplaceMetadataResponse>

        /** @see replaceMetadata */
        @MustBeClosed
        fun replaceMetadata(
            params: SessionReplaceMetadataParams
        ): HttpResponseFor<SessionReplaceMetadataResponse> =
            replaceMetadata(params, RequestOptions.none())

        /**
         * Returns current signal labels and meanings for customer integrations. Uses normal Steel
         * API-key authentication. Includes no session data or administrative signals. Definitions
         * do not establish that detection ran for a particular session.
         */
        @MustBeClosed
        fun signalCatalog(
            params: SessionSignalCatalogParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionSignalCatalogResponse>

        /** @see signalCatalog */
        @MustBeClosed
        fun signalCatalog(
            params: SessionSignalCatalogParams
        ): HttpResponseFor<SessionSignalCatalogResponse> =
            signalCatalog(params, RequestOptions.none())

        /** @see signalCatalog */
        @MustBeClosed
        fun signalCatalog(): HttpResponseFor<SessionSignalCatalogResponse> =
            signalCatalog(SessionSignalCatalogParams.none())

        /** @see signalCatalog */
        @MustBeClosed
        fun signalCatalog(
            requestOptions: RequestOptions
        ): HttpResponseFor<SessionSignalCatalogResponse> =
            signalCatalog(SessionSignalCatalogParams.none(), requestOptions)

        /** @see updateMetadata */
        @MustBeClosed
        fun updateMetadata(
            id: String,
            params: SessionUpdateMetadataParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionUpdateMetadataResponse> =
            updateMetadata(params.toBuilder().id(id).build(), requestOptions)

        /** @see updateMetadata */
        @MustBeClosed
        fun updateMetadata(
            id: String,
            params: SessionUpdateMetadataParams,
        ): HttpResponseFor<SessionUpdateMetadataResponse> =
            updateMetadata(id, params, RequestOptions.none())

        /**
         * Atomically merge keys into customer metadata. Set a key to null to remove it; keys you
         * leave out are kept, so concurrent updates to different keys are all kept. Returns the
         * merged metadata, which must stay within the metadata limits. Subject to session
         * organization and project access.
         */
        @MustBeClosed
        fun updateMetadata(
            params: SessionUpdateMetadataParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionUpdateMetadataResponse>

        /** @see updateMetadata */
        @MustBeClosed
        fun updateMetadata(
            params: SessionUpdateMetadataParams
        ): HttpResponseFor<SessionUpdateMetadataResponse> =
            updateMetadata(params, RequestOptions.none())
    }
}
