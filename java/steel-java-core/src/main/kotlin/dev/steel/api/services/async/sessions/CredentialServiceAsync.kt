package dev.steel.api.services.async.sessions

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.sessions.SessionCredentials
import dev.steel.api.models.sessions.credentials.CredentialAttachParams
import dev.steel.api.models.sessions.credentials.CredentialDetachBindingParams
import dev.steel.api.models.sessions.credentials.CredentialDetachParams
import dev.steel.api.models.sessions.credentials.CredentialListParams
import dev.steel.api.models.sessions.credentials.CredentialReplaceParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CredentialServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialServiceAsync

    /** @see list */
    fun list(
        id: String,
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials> =
        list(params.toBuilder().id(id).build(), requestOptions)

    /** @see list */
    fun list(id: String, params: CredentialListParams): CompletableFuture<SessionCredentials> =
        list(id, params, RequestOptions.none())

    /** @see list */
    fun list(id: String): CompletableFuture<SessionCredentials> =
        list(id, CredentialListParams.none())

    /** @see list */
    fun list(id: String, requestOptions: RequestOptions): CompletableFuture<SessionCredentials> =
        list(id, CredentialListParams.none(), requestOptions)

    /**
     * The session's credential lookup and the live credentials bound to it (no values). Subject to
     * session organization and project access.
     */
    fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials>

    /** @see list */
    fun list(params: CredentialListParams): CompletableFuture<SessionCredentials> =
        list(params, RequestOptions.none())

    /** @see attach */
    fun attach(
        id: String,
        params: CredentialAttachParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials> =
        attach(params.toBuilder().id(id).build(), requestOptions)

    /** @see attach */
    fun attach(id: String, params: CredentialAttachParams): CompletableFuture<SessionCredentials> =
        attach(id, params, RequestOptions.none())

    /** @see attach */
    fun attach(id: String): CompletableFuture<SessionCredentials> =
        attach(id, CredentialAttachParams.none())

    /** @see attach */
    fun attach(id: String, requestOptions: RequestOptions): CompletableFuture<SessionCredentials> =
        attach(id, CredentialAttachParams.none(), requestOptions)

    /**
     * Attach credentialIds and/or Vault or item references in bindings. Each array accepts at most
     * 100 entries; at most 100 distinct direct items and whole Vaults may be bound. Only
     * credentials can be autofilled. Lookup becomes explicit and remains so. Errors: 400
     * `invalid_reference`, `reference_not_found`, `reference_ambiguous`, `reference_not_supported`,
     * `credential_not_bindable`, or `too_many_credentials`; 409 `credentials_not_enabled` or
     * `session_not_running`; 503 `credential_bindings_busy` (retryable).
     */
    fun attach(
        params: CredentialAttachParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials>

    /** @see attach */
    fun attach(params: CredentialAttachParams): CompletableFuture<SessionCredentials> =
        attach(params, RequestOptions.none())

    /** @see detach */
    fun detach(
        credentialId: String,
        params: CredentialDetachParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials> =
        detach(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see detach */
    fun detach(
        credentialId: String,
        params: CredentialDetachParams,
    ): CompletableFuture<SessionCredentials> = detach(credentialId, params, RequestOptions.none())

    /**
     * Unbind a credential from a running session. The lookup stays `explicit`: with nothing bound,
     * the session fills nothing. Errors: 404 `credential_not_attached`, 409 `session_not_running`,
     * 503 `credential_bindings_busy` (retryable).
     */
    fun detach(
        params: CredentialDetachParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials>

    /** @see detach */
    fun detach(params: CredentialDetachParams): CompletableFuture<SessionCredentials> =
        detach(params, RequestOptions.none())

    /** @see detachBinding */
    fun detachBinding(
        id: String,
        params: CredentialDetachBindingParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials> =
        detachBinding(params.toBuilder().id(id).build(), requestOptions)

    /** @see detachBinding */
    fun detachBinding(
        id: String,
        params: CredentialDetachBindingParams,
    ): CompletableFuture<SessionCredentials> = detachBinding(id, params, RequestOptions.none())

    /**
     * Pass reference as a query parameter to detach one Vault or item binding, including
     * steel://connections/<connection>/vaults/<vault>[/<item>]. Does not detach items covered by a
     * whole Vault binding. Errors: 400 `invalid_reference` or `reference_ambiguous`; 404
     * `credential_not_attached`; 409 `session_not_running`; 503 `credential_bindings_busy`
     * (retryable).
     */
    fun detachBinding(
        params: CredentialDetachBindingParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials>

    /** @see detachBinding */
    fun detachBinding(
        params: CredentialDetachBindingParams
    ): CompletableFuture<SessionCredentials> = detachBinding(params, RequestOptions.none())

    /** @see replace */
    fun replace(
        id: String,
        params: CredentialReplaceParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials> =
        replace(params.toBuilder().id(id).build(), requestOptions)

    /** @see replace */
    fun replace(
        id: String,
        params: CredentialReplaceParams,
    ): CompletableFuture<SessionCredentials> = replace(id, params, RequestOptions.none())

    /** @see replace */
    fun replace(id: String): CompletableFuture<SessionCredentials> =
        replace(id, CredentialReplaceParams.none())

    /** @see replace */
    fun replace(id: String, requestOptions: RequestOptions): CompletableFuture<SessionCredentials> =
        replace(id, CredentialReplaceParams.none(), requestOptions)

    /**
     * Replace all direct item and Vault bindings, including provider references
     * steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds and/or
     * bindings; empty arrays detach all. At most 100 distinct bindings may be stored. Only
     * credentials can be autofilled. Lookup becomes explicit and remains so.
     */
    fun replace(
        params: CredentialReplaceParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SessionCredentials>

    /** @see replace */
    fun replace(params: CredentialReplaceParams): CompletableFuture<SessionCredentials> =
        replace(params, RequestOptions.none())

    /**
     * A view of [CredentialServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CredentialServiceAsync.WithRawResponse

        /** @see list */
        fun list(
            id: String,
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            list(params.toBuilder().id(id).build(), requestOptions)

        /** @see list */
        fun list(
            id: String,
            params: CredentialListParams,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            list(id, params, RequestOptions.none())

        /** @see list */
        fun list(id: String): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            list(id, CredentialListParams.none())

        /** @see list */
        fun list(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            list(id, CredentialListParams.none(), requestOptions)

        /**
         * The session's credential lookup and the live credentials bound to it (no values). Subject
         * to session organization and project access.
         */
        fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>>

        /** @see list */
        fun list(
            params: CredentialListParams
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            list(params, RequestOptions.none())

        /** @see attach */
        fun attach(
            id: String,
            params: CredentialAttachParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            attach(params.toBuilder().id(id).build(), requestOptions)

        /** @see attach */
        fun attach(
            id: String,
            params: CredentialAttachParams,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            attach(id, params, RequestOptions.none())

        /** @see attach */
        fun attach(id: String): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            attach(id, CredentialAttachParams.none())

        /** @see attach */
        fun attach(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            attach(id, CredentialAttachParams.none(), requestOptions)

        /**
         * Attach credentialIds and/or Vault or item references in bindings. Each array accepts at
         * most 100 entries; at most 100 distinct direct items and whole Vaults may be bound. Only
         * credentials can be autofilled. Lookup becomes explicit and remains so. Errors: 400
         * `invalid_reference`, `reference_not_found`, `reference_ambiguous`,
         * `reference_not_supported`, `credential_not_bindable`, or `too_many_credentials`; 409
         * `credentials_not_enabled` or `session_not_running`; 503 `credential_bindings_busy`
         * (retryable).
         */
        fun attach(
            params: CredentialAttachParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>>

        /** @see attach */
        fun attach(
            params: CredentialAttachParams
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            attach(params, RequestOptions.none())

        /** @see detach */
        fun detach(
            credentialId: String,
            params: CredentialDetachParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            detach(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see detach */
        fun detach(
            credentialId: String,
            params: CredentialDetachParams,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            detach(credentialId, params, RequestOptions.none())

        /**
         * Unbind a credential from a running session. The lookup stays `explicit`: with nothing
         * bound, the session fills nothing. Errors: 404 `credential_not_attached`, 409
         * `session_not_running`, 503 `credential_bindings_busy` (retryable).
         */
        fun detach(
            params: CredentialDetachParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>>

        /** @see detach */
        fun detach(
            params: CredentialDetachParams
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            detach(params, RequestOptions.none())

        /** @see detachBinding */
        fun detachBinding(
            id: String,
            params: CredentialDetachBindingParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            detachBinding(params.toBuilder().id(id).build(), requestOptions)

        /** @see detachBinding */
        fun detachBinding(
            id: String,
            params: CredentialDetachBindingParams,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            detachBinding(id, params, RequestOptions.none())

        /**
         * Pass reference as a query parameter to detach one Vault or item binding, including
         * steel://connections/<connection>/vaults/<vault>[/<item>]. Does not detach items covered
         * by a whole Vault binding. Errors: 400 `invalid_reference` or `reference_ambiguous`; 404
         * `credential_not_attached`; 409 `session_not_running`; 503 `credential_bindings_busy`
         * (retryable).
         */
        fun detachBinding(
            params: CredentialDetachBindingParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>>

        /** @see detachBinding */
        fun detachBinding(
            params: CredentialDetachBindingParams
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            detachBinding(params, RequestOptions.none())

        /** @see replace */
        fun replace(
            id: String,
            params: CredentialReplaceParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            replace(params.toBuilder().id(id).build(), requestOptions)

        /** @see replace */
        fun replace(
            id: String,
            params: CredentialReplaceParams,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            replace(id, params, RequestOptions.none())

        /** @see replace */
        fun replace(id: String): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            replace(id, CredentialReplaceParams.none())

        /** @see replace */
        fun replace(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            replace(id, CredentialReplaceParams.none(), requestOptions)

        /**
         * Replace all direct item and Vault bindings, including provider references
         * steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds and/or
         * bindings; empty arrays detach all. At most 100 distinct bindings may be stored. Only
         * credentials can be autofilled. Lookup becomes explicit and remains so.
         */
        fun replace(
            params: CredentialReplaceParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SessionCredentials>>

        /** @see replace */
        fun replace(
            params: CredentialReplaceParams
        ): CompletableFuture<HttpResponseFor<SessionCredentials>> =
            replace(params, RequestOptions.none())
    }
}
