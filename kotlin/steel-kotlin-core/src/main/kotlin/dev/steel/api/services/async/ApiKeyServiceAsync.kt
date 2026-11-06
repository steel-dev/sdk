package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.apikeys.ApiKey
import dev.steel.api.models.apikeys.ApiKeyCreateParams
import java.util.function.Consumer

/** Create project-scoped API keys using an organization-scoped API key. */
interface ApiKeyServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ApiKeyServiceAsync

    /**
     * Create a project-scoped API key using a global (organization-scoped) API key. Supply
     * projectId for an active project in the authenticated organization; scopeType must be project
     * or omitted. Project-scoped API keys cannot create keys, and global keys cannot create other
     * global keys. Dashboard users may also create project keys, or global keys with organization
     * admin access. Returns the full secret once with Cache-Control: no-store; save it securely.
     * The new key is independent: revoking the creating global key does not revoke keys it created.
     * Missing projectId returns 400; insufficient scope returns 403; an unavailable or foreign
     * project returns 404.
     */
    suspend fun create(params: ApiKeyCreateParams, requestOptions: RequestOptions): ApiKey

    /** @see create */
    suspend fun create(params: ApiKeyCreateParams): ApiKey = create(params, RequestOptions.none())

    /** @see create */
    suspend fun create(): ApiKey = create(ApiKeyCreateParams.none())

    /** @see create */
    suspend fun create(requestOptions: RequestOptions): ApiKey =
        create(ApiKeyCreateParams.none(), requestOptions)

    /**
     * A view of [ApiKeyServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ApiKeyServiceAsync.WithRawResponse

        /**
         * Create a project-scoped API key using a global (organization-scoped) API key. Supply
         * projectId for an active project in the authenticated organization; scopeType must be
         * project or omitted. Project-scoped API keys cannot create keys, and global keys cannot
         * create other global keys. Dashboard users may also create project keys, or global keys
         * with organization admin access. Returns the full secret once with Cache-Control:
         * no-store; save it securely. The new key is independent: revoking the creating global key
         * does not revoke keys it created. Missing projectId returns 400; insufficient scope
         * returns 403; an unavailable or foreign project returns 404.
         */
        suspend fun create(
            params: ApiKeyCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ApiKey>

        /** @see create */
        suspend fun create(params: ApiKeyCreateParams): HttpResponseFor<ApiKey> =
            create(params, RequestOptions.none())

        /** @see create */
        suspend fun create(): HttpResponseFor<ApiKey> = create(ApiKeyCreateParams.none())

        /** @see create */
        suspend fun create(requestOptions: RequestOptions): HttpResponseFor<ApiKey> =
            create(ApiKeyCreateParams.none(), requestOptions)
    }
}
