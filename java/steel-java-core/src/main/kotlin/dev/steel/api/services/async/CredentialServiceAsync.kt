package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.credentials.CredentialCreateParams
import dev.steel.api.models.credentials.CredentialCreateResponse
import dev.steel.api.models.credentials.CredentialDeleteParams
import dev.steel.api.models.credentials.CredentialDeleteResponse
import dev.steel.api.models.credentials.CredentialListParams
import dev.steel.api.models.credentials.CredentialListResponse
import dev.steel.api.models.credentials.CredentialUpdateParams
import dev.steel.api.models.credentials.CredentialUpdateResponse
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

    /** Encrypts and stores credentials for an origin */
    fun create(
        params: CredentialCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CredentialCreateResponse>

    /** @see create */
    fun create(params: CredentialCreateParams): CompletableFuture<CredentialCreateResponse> =
        create(params, RequestOptions.none())

    /** Encrypts and updates credentials for an origin */
    fun update(
        params: CredentialUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CredentialUpdateResponse>

    /** @see update */
    fun update(params: CredentialUpdateParams): CompletableFuture<CredentialUpdateResponse> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(): CompletableFuture<CredentialUpdateResponse> =
        update(CredentialUpdateParams.none())

    /** @see update */
    fun update(requestOptions: RequestOptions): CompletableFuture<CredentialUpdateResponse> =
        update(CredentialUpdateParams.none(), requestOptions)

    /** Fetches all credential metadata for the current organization. */
    fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CredentialListResponse>

    /** @see list */
    fun list(params: CredentialListParams): CompletableFuture<CredentialListResponse> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<CredentialListResponse> = list(CredentialListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<CredentialListResponse> =
        list(CredentialListParams.none(), requestOptions)

    /** Deletes encrypted credentials from the database */
    fun delete(
        params: CredentialDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CredentialDeleteResponse>

    /** @see delete */
    fun delete(params: CredentialDeleteParams): CompletableFuture<CredentialDeleteResponse> =
        delete(params, RequestOptions.none())

    /**
     * A view of [CredentialServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CredentialServiceAsync.WithRawResponse

        /** Encrypts and stores credentials for an origin */
        fun create(
            params: CredentialCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CredentialCreateResponse>>

        /** @see create */
        fun create(
            params: CredentialCreateParams
        ): CompletableFuture<HttpResponseFor<CredentialCreateResponse>> =
            create(params, RequestOptions.none())

        /** Encrypts and updates credentials for an origin */
        fun update(
            params: CredentialUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CredentialUpdateResponse>>

        /** @see update */
        fun update(
            params: CredentialUpdateParams
        ): CompletableFuture<HttpResponseFor<CredentialUpdateResponse>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(): CompletableFuture<HttpResponseFor<CredentialUpdateResponse>> =
            update(CredentialUpdateParams.none())

        /** @see update */
        fun update(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<CredentialUpdateResponse>> =
            update(CredentialUpdateParams.none(), requestOptions)

        /** Fetches all credential metadata for the current organization. */
        fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CredentialListResponse>>

        /** @see list */
        fun list(
            params: CredentialListParams
        ): CompletableFuture<HttpResponseFor<CredentialListResponse>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<CredentialListResponse>> =
            list(CredentialListParams.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<CredentialListResponse>> =
            list(CredentialListParams.none(), requestOptions)

        /** Deletes encrypted credentials from the database */
        fun delete(
            params: CredentialDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CredentialDeleteResponse>>

        /** @see delete */
        fun delete(
            params: CredentialDeleteParams
        ): CompletableFuture<HttpResponseFor<CredentialDeleteResponse>> =
            delete(params, RequestOptions.none())
    }
}
