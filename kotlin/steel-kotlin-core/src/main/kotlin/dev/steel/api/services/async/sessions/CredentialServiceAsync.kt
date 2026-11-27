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
    suspend fun list(
        id: String,
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = list(params.toBuilder().id(id).build(), requestOptions)

    /** @see list */
    suspend fun list(id: String, params: CredentialListParams): SessionCredentials =
        list(id, params, RequestOptions.none())

    /** @see list */
    suspend fun list(id: String): SessionCredentials = list(id, CredentialListParams.none())

    /** @see list */
    suspend fun list(id: String, requestOptions: RequestOptions): SessionCredentials =
        list(id, CredentialListParams.none(), requestOptions)

    /**
     * The session's credential lookup and the live credentials bound to it (no values). Subject to
     * session organization and project access.
     */
    suspend fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): SessionCredentials

    /** @see list */
    suspend fun list(params: CredentialListParams): SessionCredentials =
        list(params, RequestOptions.none())

    /** @see attach */
    suspend fun attach(
        id: String,
        params: CredentialAttachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = attach(params.toBuilder().id(id).build(), requestOptions)

    /** @see attach */
    suspend fun attach(id: String, params: CredentialAttachParams): SessionCredentials =
        attach(id, params, RequestOptions.none())

    /** @see attach */
    suspend fun attach(id: String): SessionCredentials = attach(id, CredentialAttachParams.none())

    /** @see attach */
    suspend fun attach(id: String, requestOptions: RequestOptions): SessionCredentials =
        attach(id, CredentialAttachParams.none(), requestOptions)

    /**
     * Attach credentialIds and/or Vault or item references in bindings. Each array accepts at most
     * 100 entries; at most 100 distinct direct items and whole Vaults may be bound. Only
     * credentials can be autofilled. Lookup becomes explicit and remains so. Errors: 400
     * `invalid_reference`, `reference_not_found`, `reference_ambiguous`, `reference_not_supported`,
     * `credential_not_bindable`, or `too_many_credentials`; 409 `credentials_not_enabled` or
     * `session_not_running`; 503 `credential_bindings_busy` (retryable).
     */
    suspend fun attach(
        params: CredentialAttachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials

    /** @see attach */
    suspend fun attach(params: CredentialAttachParams): SessionCredentials =
        attach(params, RequestOptions.none())

    /** @see detach */
    suspend fun detach(
        credentialId: String,
        params: CredentialDetachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials =
        detach(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see detach */
    suspend fun detach(credentialId: String, params: CredentialDetachParams): SessionCredentials =
        detach(credentialId, params, RequestOptions.none())

    /**
     * Unbind a credential from a running session. The lookup stays `explicit`: with nothing bound,
     * the session fills nothing. Errors: 404 `credential_not_attached`, 409 `session_not_running`,
     * 503 `credential_bindings_busy` (retryable).
     */
    suspend fun detach(
        params: CredentialDetachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials

    /** @see detach */
    suspend fun detach(params: CredentialDetachParams): SessionCredentials =
        detach(params, RequestOptions.none())

    /** @see detachBinding */
    suspend fun detachBinding(
        id: String,
        params: CredentialDetachBindingParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = detachBinding(params.toBuilder().id(id).build(), requestOptions)

    /** @see detachBinding */
    suspend fun detachBinding(
        id: String,
        params: CredentialDetachBindingParams,
    ): SessionCredentials = detachBinding(id, params, RequestOptions.none())

    /**
     * Pass reference as a query parameter to detach one Vault or item binding, including
     * steel://connections/<connection>/vaults/<vault>[/<item>]. Does not detach items covered by a
     * whole Vault binding. Errors: 400 `invalid_reference` or `reference_ambiguous`; 404
     * `credential_not_attached`; 409 `session_not_running`; 503 `credential_bindings_busy`
     * (retryable).
     */
    suspend fun detachBinding(
        params: CredentialDetachBindingParams,
        requestOptions: RequestOptions,
    ): SessionCredentials

    /** @see detachBinding */
    suspend fun detachBinding(params: CredentialDetachBindingParams): SessionCredentials =
        detachBinding(params, RequestOptions.none())

    /** @see replace */
    suspend fun replace(
        id: String,
        params: CredentialReplaceParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = replace(params.toBuilder().id(id).build(), requestOptions)

    /** @see replace */
    suspend fun replace(id: String, params: CredentialReplaceParams): SessionCredentials =
        replace(id, params, RequestOptions.none())

    /** @see replace */
    suspend fun replace(id: String): SessionCredentials =
        replace(id, CredentialReplaceParams.none())

    /** @see replace */
    suspend fun replace(id: String, requestOptions: RequestOptions): SessionCredentials =
        replace(id, CredentialReplaceParams.none(), requestOptions)

    /**
     * Replace all direct item and Vault bindings, including provider references
     * steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds and/or
     * bindings; empty arrays detach all. At most 100 distinct bindings may be stored. Only
     * credentials can be autofilled. Lookup becomes explicit and remains so.
     */
    suspend fun replace(
        params: CredentialReplaceParams,
        requestOptions: RequestOptions,
    ): SessionCredentials

    /** @see replace */
    suspend fun replace(params: CredentialReplaceParams): SessionCredentials =
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
        suspend fun list(
            id: String,
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            list(params.toBuilder().id(id).build(), requestOptions)

        /** @see list */
        suspend fun list(
            id: String,
            params: CredentialListParams,
        ): HttpResponseFor<SessionCredentials> = list(id, params, RequestOptions.none())

        /** @see list */
        suspend fun list(id: String): HttpResponseFor<SessionCredentials> =
            list(id, CredentialListParams.none())

        /** @see list */
        suspend fun list(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            list(id, CredentialListParams.none(), requestOptions)

        /**
         * The session's credential lookup and the live credentials bound to it (no values). Subject
         * to session organization and project access.
         */
        suspend fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see list */
        suspend fun list(params: CredentialListParams): HttpResponseFor<SessionCredentials> =
            list(params, RequestOptions.none())

        /** @see attach */
        suspend fun attach(
            id: String,
            params: CredentialAttachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            attach(params.toBuilder().id(id).build(), requestOptions)

        /** @see attach */
        suspend fun attach(
            id: String,
            params: CredentialAttachParams,
        ): HttpResponseFor<SessionCredentials> = attach(id, params, RequestOptions.none())

        /** @see attach */
        suspend fun attach(id: String): HttpResponseFor<SessionCredentials> =
            attach(id, CredentialAttachParams.none())

        /** @see attach */
        suspend fun attach(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
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
        suspend fun attach(
            params: CredentialAttachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see attach */
        suspend fun attach(params: CredentialAttachParams): HttpResponseFor<SessionCredentials> =
            attach(params, RequestOptions.none())

        /** @see detach */
        suspend fun detach(
            credentialId: String,
            params: CredentialDetachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            detach(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see detach */
        suspend fun detach(
            credentialId: String,
            params: CredentialDetachParams,
        ): HttpResponseFor<SessionCredentials> = detach(credentialId, params, RequestOptions.none())

        /**
         * Unbind a credential from a running session. The lookup stays `explicit`: with nothing
         * bound, the session fills nothing. Errors: 404 `credential_not_attached`, 409
         * `session_not_running`, 503 `credential_bindings_busy` (retryable).
         */
        suspend fun detach(
            params: CredentialDetachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see detach */
        suspend fun detach(params: CredentialDetachParams): HttpResponseFor<SessionCredentials> =
            detach(params, RequestOptions.none())

        /** @see detachBinding */
        suspend fun detachBinding(
            id: String,
            params: CredentialDetachBindingParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            detachBinding(params.toBuilder().id(id).build(), requestOptions)

        /** @see detachBinding */
        suspend fun detachBinding(
            id: String,
            params: CredentialDetachBindingParams,
        ): HttpResponseFor<SessionCredentials> = detachBinding(id, params, RequestOptions.none())

        /**
         * Pass reference as a query parameter to detach one Vault or item binding, including
         * steel://connections/<connection>/vaults/<vault>[/<item>]. Does not detach items covered
         * by a whole Vault binding. Errors: 400 `invalid_reference` or `reference_ambiguous`; 404
         * `credential_not_attached`; 409 `session_not_running`; 503 `credential_bindings_busy`
         * (retryable).
         */
        suspend fun detachBinding(
            params: CredentialDetachBindingParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see detachBinding */
        suspend fun detachBinding(
            params: CredentialDetachBindingParams
        ): HttpResponseFor<SessionCredentials> = detachBinding(params, RequestOptions.none())

        /** @see replace */
        suspend fun replace(
            id: String,
            params: CredentialReplaceParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            replace(params.toBuilder().id(id).build(), requestOptions)

        /** @see replace */
        suspend fun replace(
            id: String,
            params: CredentialReplaceParams,
        ): HttpResponseFor<SessionCredentials> = replace(id, params, RequestOptions.none())

        /** @see replace */
        suspend fun replace(id: String): HttpResponseFor<SessionCredentials> =
            replace(id, CredentialReplaceParams.none())

        /** @see replace */
        suspend fun replace(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            replace(id, CredentialReplaceParams.none(), requestOptions)

        /**
         * Replace all direct item and Vault bindings, including provider references
         * steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds and/or
         * bindings; empty arrays detach all. At most 100 distinct bindings may be stored. Only
         * credentials can be autofilled. Lookup becomes explicit and remains so.
         */
        suspend fun replace(
            params: CredentialReplaceParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see replace */
        suspend fun replace(params: CredentialReplaceParams): HttpResponseFor<SessionCredentials> =
            replace(params, RequestOptions.none())
    }
}
