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
    suspend fun create(
        params: CredentialCreateParams,
        requestOptions: RequestOptions,
    ): CredentialCreateResponse

    /** @see create */
    suspend fun create(params: CredentialCreateParams): CredentialCreateResponse =
        create(params, RequestOptions.none())

    /** Encrypts and updates credentials for an origin */
    suspend fun update(
        params: CredentialUpdateParams,
        requestOptions: RequestOptions,
    ): CredentialUpdateResponse

    /** @see update */
    suspend fun update(params: CredentialUpdateParams): CredentialUpdateResponse =
        update(params, RequestOptions.none())

    /** @see update */
    suspend fun update(): CredentialUpdateResponse = update(CredentialUpdateParams.none())

    /** @see update */
    suspend fun update(requestOptions: RequestOptions): CredentialUpdateResponse =
        update(CredentialUpdateParams.none(), requestOptions)

    /** Fetches all credential metadata for the current organization. */
    suspend fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): CredentialListResponse

    /** @see list */
    suspend fun list(params: CredentialListParams): CredentialListResponse =
        list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(): CredentialListResponse = list(CredentialListParams.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): CredentialListResponse =
        list(CredentialListParams.none(), requestOptions)

    /** Deletes encrypted credentials from the database */
    suspend fun delete(
        params: CredentialDeleteParams,
        requestOptions: RequestOptions,
    ): CredentialDeleteResponse

    /** @see delete */
    suspend fun delete(params: CredentialDeleteParams): CredentialDeleteResponse =
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
        suspend fun create(
            params: CredentialCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialCreateResponse>

        /** @see create */
        suspend fun create(
            params: CredentialCreateParams
        ): HttpResponseFor<CredentialCreateResponse> = create(params, RequestOptions.none())

        /** Encrypts and updates credentials for an origin */
        suspend fun update(
            params: CredentialUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialUpdateResponse>

        /** @see update */
        suspend fun update(
            params: CredentialUpdateParams
        ): HttpResponseFor<CredentialUpdateResponse> = update(params, RequestOptions.none())

        /** @see update */
        suspend fun update(): HttpResponseFor<CredentialUpdateResponse> =
            update(CredentialUpdateParams.none())

        /** @see update */
        suspend fun update(
            requestOptions: RequestOptions
        ): HttpResponseFor<CredentialUpdateResponse> =
            update(CredentialUpdateParams.none(), requestOptions)

        /** Fetches all credential metadata for the current organization. */
        suspend fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialListResponse>

        /** @see list */
        suspend fun list(params: CredentialListParams): HttpResponseFor<CredentialListResponse> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(): HttpResponseFor<CredentialListResponse> =
            list(CredentialListParams.none())

        /** @see list */
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<CredentialListResponse> =
            list(CredentialListParams.none(), requestOptions)

        /** Deletes encrypted credentials from the database */
        suspend fun delete(
            params: CredentialDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialDeleteResponse>

        /** @see delete */
        suspend fun delete(
            params: CredentialDeleteParams
        ): HttpResponseFor<CredentialDeleteResponse> = delete(params, RequestOptions.none())
    }
}
