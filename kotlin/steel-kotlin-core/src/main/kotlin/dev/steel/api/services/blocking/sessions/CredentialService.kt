package dev.steel.api.services.blocking.sessions

import com.google.errorprone.annotations.MustBeClosed
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

interface CredentialService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialService

    /** @see list */
    fun list(
        id: String,
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = list(params.toBuilder().id(id).build(), requestOptions)

    /** @see list */
    fun list(id: String, params: CredentialListParams): SessionCredentials =
        list(id, params, RequestOptions.none())

    /** @see list */
    fun list(id: String): SessionCredentials = list(id, CredentialListParams.none())

    /** @see list */
    fun list(id: String, requestOptions: RequestOptions): SessionCredentials =
        list(id, CredentialListParams.none(), requestOptions)

    /**
     * The session's credential lookup and the live credentials bound to it (no values). Subject to
     * session organization and project access.
     */
    fun list(params: CredentialListParams, requestOptions: RequestOptions): SessionCredentials

    /** @see list */
    fun list(params: CredentialListParams): SessionCredentials = list(params, RequestOptions.none())

    /** @see attach */
    fun attach(
        id: String,
        params: CredentialAttachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = attach(params.toBuilder().id(id).build(), requestOptions)

    /** @see attach */
    fun attach(id: String, params: CredentialAttachParams): SessionCredentials =
        attach(id, params, RequestOptions.none())

    /** @see attach */
    fun attach(id: String): SessionCredentials = attach(id, CredentialAttachParams.none())

    /** @see attach */
    fun attach(id: String, requestOptions: RequestOptions): SessionCredentials =
        attach(id, CredentialAttachParams.none(), requestOptions)

    /**
     * Attach credentialIds and/or Vault or item references in bindings. Each array accepts at most
     * 100 entries; at most 100 distinct direct items and whole Vaults may be bound. Only
     * credentials can be autofilled. Lookup becomes explicit and remains so. Errors: 400
     * `invalid_reference`, `reference_not_found`, `reference_ambiguous`, `reference_not_supported`,
     * `credential_not_bindable`, or `too_many_credentials`; 409 `credentials_not_enabled` or
     * `session_not_running`; 503 `credential_bindings_busy` (retryable).
     */
    fun attach(params: CredentialAttachParams, requestOptions: RequestOptions): SessionCredentials

    /** @see attach */
    fun attach(params: CredentialAttachParams): SessionCredentials =
        attach(params, RequestOptions.none())

    /** @see detach */
    fun detach(
        credentialId: String,
        params: CredentialDetachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials =
        detach(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see detach */
    fun detach(credentialId: String, params: CredentialDetachParams): SessionCredentials =
        detach(credentialId, params, RequestOptions.none())

    /**
     * Unbind a credential from a running session. The lookup stays `explicit`: with nothing bound,
     * the session fills nothing. Errors: 404 `credential_not_attached`, 409 `session_not_running`,
     * 503 `credential_bindings_busy` (retryable).
     */
    fun detach(params: CredentialDetachParams, requestOptions: RequestOptions): SessionCredentials

    /** @see detach */
    fun detach(params: CredentialDetachParams): SessionCredentials =
        detach(params, RequestOptions.none())

    /** @see detachBinding */
    fun detachBinding(
        id: String,
        params: CredentialDetachBindingParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = detachBinding(params.toBuilder().id(id).build(), requestOptions)

    /** @see detachBinding */
    fun detachBinding(id: String, params: CredentialDetachBindingParams): SessionCredentials =
        detachBinding(id, params, RequestOptions.none())

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
    ): SessionCredentials

    /** @see detachBinding */
    fun detachBinding(params: CredentialDetachBindingParams): SessionCredentials =
        detachBinding(params, RequestOptions.none())

    /** @see replace */
    fun replace(
        id: String,
        params: CredentialReplaceParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = replace(params.toBuilder().id(id).build(), requestOptions)

    /** @see replace */
    fun replace(id: String, params: CredentialReplaceParams): SessionCredentials =
        replace(id, params, RequestOptions.none())

    /** @see replace */
    fun replace(id: String): SessionCredentials = replace(id, CredentialReplaceParams.none())

    /** @see replace */
    fun replace(id: String, requestOptions: RequestOptions): SessionCredentials =
        replace(id, CredentialReplaceParams.none(), requestOptions)

    /**
     * Replace all direct item and Vault bindings, including provider references
     * steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds and/or
     * bindings; empty arrays detach all. At most 100 distinct bindings may be stored. Only
     * credentials can be autofilled. Lookup becomes explicit and remains so.
     */
    fun replace(params: CredentialReplaceParams, requestOptions: RequestOptions): SessionCredentials

    /** @see replace */
    fun replace(params: CredentialReplaceParams): SessionCredentials =
        replace(params, RequestOptions.none())

    /** A view of [CredentialService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CredentialService.WithRawResponse

        /** @see list */
        @MustBeClosed
        fun list(
            id: String,
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            list(params.toBuilder().id(id).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(id: String, params: CredentialListParams): HttpResponseFor<SessionCredentials> =
            list(id, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(id: String): HttpResponseFor<SessionCredentials> =
            list(id, CredentialListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(id: String, requestOptions: RequestOptions): HttpResponseFor<SessionCredentials> =
            list(id, CredentialListParams.none(), requestOptions)

        /**
         * The session's credential lookup and the live credentials bound to it (no values). Subject
         * to session organization and project access.
         */
        @MustBeClosed
        fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see list */
        @MustBeClosed
        fun list(params: CredentialListParams): HttpResponseFor<SessionCredentials> =
            list(params, RequestOptions.none())

        /** @see attach */
        @MustBeClosed
        fun attach(
            id: String,
            params: CredentialAttachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            attach(params.toBuilder().id(id).build(), requestOptions)

        /** @see attach */
        @MustBeClosed
        fun attach(
            id: String,
            params: CredentialAttachParams,
        ): HttpResponseFor<SessionCredentials> = attach(id, params, RequestOptions.none())

        /** @see attach */
        @MustBeClosed
        fun attach(id: String): HttpResponseFor<SessionCredentials> =
            attach(id, CredentialAttachParams.none())

        /** @see attach */
        @MustBeClosed
        fun attach(
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
        @MustBeClosed
        fun attach(
            params: CredentialAttachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see attach */
        @MustBeClosed
        fun attach(params: CredentialAttachParams): HttpResponseFor<SessionCredentials> =
            attach(params, RequestOptions.none())

        /** @see detach */
        @MustBeClosed
        fun detach(
            credentialId: String,
            params: CredentialDetachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            detach(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see detach */
        @MustBeClosed
        fun detach(
            credentialId: String,
            params: CredentialDetachParams,
        ): HttpResponseFor<SessionCredentials> = detach(credentialId, params, RequestOptions.none())

        /**
         * Unbind a credential from a running session. The lookup stays `explicit`: with nothing
         * bound, the session fills nothing. Errors: 404 `credential_not_attached`, 409
         * `session_not_running`, 503 `credential_bindings_busy` (retryable).
         */
        @MustBeClosed
        fun detach(
            params: CredentialDetachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see detach */
        @MustBeClosed
        fun detach(params: CredentialDetachParams): HttpResponseFor<SessionCredentials> =
            detach(params, RequestOptions.none())

        /** @see detachBinding */
        @MustBeClosed
        fun detachBinding(
            id: String,
            params: CredentialDetachBindingParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            detachBinding(params.toBuilder().id(id).build(), requestOptions)

        /** @see detachBinding */
        @MustBeClosed
        fun detachBinding(
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
        @MustBeClosed
        fun detachBinding(
            params: CredentialDetachBindingParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see detachBinding */
        @MustBeClosed
        fun detachBinding(
            params: CredentialDetachBindingParams
        ): HttpResponseFor<SessionCredentials> = detachBinding(params, RequestOptions.none())

        /** @see replace */
        @MustBeClosed
        fun replace(
            id: String,
            params: CredentialReplaceParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> =
            replace(params.toBuilder().id(id).build(), requestOptions)

        /** @see replace */
        @MustBeClosed
        fun replace(
            id: String,
            params: CredentialReplaceParams,
        ): HttpResponseFor<SessionCredentials> = replace(id, params, RequestOptions.none())

        /** @see replace */
        @MustBeClosed
        fun replace(id: String): HttpResponseFor<SessionCredentials> =
            replace(id, CredentialReplaceParams.none())

        /** @see replace */
        @MustBeClosed
        fun replace(
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
        @MustBeClosed
        fun replace(
            params: CredentialReplaceParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials>

        /** @see replace */
        @MustBeClosed
        fun replace(params: CredentialReplaceParams): HttpResponseFor<SessionCredentials> =
            replace(params, RequestOptions.none())
    }
}
