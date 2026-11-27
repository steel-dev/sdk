package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.secretconnections.SecretConnectionCreateParams
import dev.steel.api.models.secretconnections.SecretConnectionCreateResponse
import dev.steel.api.models.secretconnections.SecretConnectionDeleteParams
import dev.steel.api.models.secretconnections.SecretConnectionListParams
import dev.steel.api.models.secretconnections.SecretConnectionListResponse
import dev.steel.api.models.secretconnections.SecretConnectionRetrieveParams
import dev.steel.api.models.secretconnections.SecretConnectionRetrieveResponse
import dev.steel.api.models.secretconnections.SecretConnectionTestParams
import dev.steel.api.models.secretconnections.SecretConnectionUpdateParams
import dev.steel.api.models.secretconnections.SecretConnectionUpdateResponse
import java.util.function.Consumer

interface SecretConnectionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SecretConnectionServiceAsync

    /** Create a secret connection */
    suspend fun create(
        params: SecretConnectionCreateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionCreateResponse

    /** @see create */
    suspend fun create(params: SecretConnectionCreateParams): SecretConnectionCreateResponse =
        create(params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        id: String,
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): SecretConnectionRetrieveResponse =
        retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        id: String,
        params: SecretConnectionRetrieveParams,
    ): SecretConnectionRetrieveResponse = retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(id: String): SecretConnectionRetrieveResponse =
        retrieve(id, SecretConnectionRetrieveParams.none())

    /** @see retrieve */
    suspend fun retrieve(
        id: String,
        requestOptions: RequestOptions,
    ): SecretConnectionRetrieveResponse =
        retrieve(id, SecretConnectionRetrieveParams.none(), requestOptions)

    /** Get a secret connection */
    suspend fun retrieve(
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): SecretConnectionRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(params: SecretConnectionRetrieveParams): SecretConnectionRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see update */
    suspend fun update(
        id: String,
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionUpdateResponse = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    suspend fun update(
        id: String,
        params: SecretConnectionUpdateParams,
    ): SecretConnectionUpdateResponse = update(id, params, RequestOptions.none())

    /** @see update */
    suspend fun update(id: String): SecretConnectionUpdateResponse =
        update(id, SecretConnectionUpdateParams.none())

    /** @see update */
    suspend fun update(id: String, requestOptions: RequestOptions): SecretConnectionUpdateResponse =
        update(id, SecretConnectionUpdateParams.none(), requestOptions)

    /** Update a secret connection */
    suspend fun update(
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionUpdateResponse

    /** @see update */
    suspend fun update(params: SecretConnectionUpdateParams): SecretConnectionUpdateResponse =
        update(params, RequestOptions.none())

    /** List secret connections */
    suspend fun list(
        params: SecretConnectionListParams,
        requestOptions: RequestOptions,
    ): SecretConnectionListResponse

    /** @see list */
    suspend fun list(params: SecretConnectionListParams): SecretConnectionListResponse =
        list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(): SecretConnectionListResponse = list(SecretConnectionListParams.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): SecretConnectionListResponse =
        list(SecretConnectionListParams.none(), requestOptions)

    /** @see delete */
    suspend fun delete(
        id: String,
        params: SecretConnectionDeleteParams,
        requestOptions: RequestOptions,
    ) = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(id: String, params: SecretConnectionDeleteParams) =
        delete(id, params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(id: String) = delete(id, SecretConnectionDeleteParams.none())

    /** @see delete */
    suspend fun delete(id: String, requestOptions: RequestOptions) =
        delete(id, SecretConnectionDeleteParams.none(), requestOptions)

    /** Delete a secret connection */
    suspend fun delete(params: SecretConnectionDeleteParams, requestOptions: RequestOptions)

    /** @see delete */
    suspend fun delete(params: SecretConnectionDeleteParams) = delete(params, RequestOptions.none())

    /** @see test */
    suspend fun test(
        id: String,
        params: SecretConnectionTestParams,
        requestOptions: RequestOptions,
    ) = test(params.toBuilder().id(id).build(), requestOptions)

    /** @see test */
    suspend fun test(id: String, params: SecretConnectionTestParams) =
        test(id, params, RequestOptions.none())

    /** @see test */
    suspend fun test(id: String) = test(id, SecretConnectionTestParams.none())

    /** @see test */
    suspend fun test(id: String, requestOptions: RequestOptions) =
        test(id, SecretConnectionTestParams.none(), requestOptions)

    /** Test a secret connection */
    suspend fun test(params: SecretConnectionTestParams, requestOptions: RequestOptions)

    /** @see test */
    suspend fun test(params: SecretConnectionTestParams) = test(params, RequestOptions.none())

    /**
     * A view of [SecretConnectionServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SecretConnectionServiceAsync.WithRawResponse

        /** Create a secret connection */
        suspend fun create(
            params: SecretConnectionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionCreateResponse>

        /** @see create */
        suspend fun create(
            params: SecretConnectionCreateParams
        ): HttpResponseFor<SecretConnectionCreateResponse> = create(params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            id: String,
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        suspend fun retrieve(
            id: String,
            params: SecretConnectionRetrieveParams,
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(id: String): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(id, SecretConnectionRetrieveParams.none())

        /** @see retrieve */
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(id, SecretConnectionRetrieveParams.none(), requestOptions)

        /** Get a secret connection */
        suspend fun retrieve(
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionRetrieveResponse>

        /** @see retrieve */
        suspend fun retrieve(
            params: SecretConnectionRetrieveParams
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see update */
        suspend fun update(
            id: String,
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        suspend fun update(
            id: String,
            params: SecretConnectionUpdateParams,
        ): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(id, params, RequestOptions.none())

        /** @see update */
        suspend fun update(id: String): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(id, SecretConnectionUpdateParams.none())

        /** @see update */
        suspend fun update(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(id, SecretConnectionUpdateParams.none(), requestOptions)

        /** Update a secret connection */
        suspend fun update(
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionUpdateResponse>

        /** @see update */
        suspend fun update(
            params: SecretConnectionUpdateParams
        ): HttpResponseFor<SecretConnectionUpdateResponse> = update(params, RequestOptions.none())

        /** List secret connections */
        suspend fun list(
            params: SecretConnectionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionListResponse>

        /** @see list */
        suspend fun list(
            params: SecretConnectionListParams
        ): HttpResponseFor<SecretConnectionListResponse> = list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(): HttpResponseFor<SecretConnectionListResponse> =
            list(SecretConnectionListParams.none())

        /** @see list */
        suspend fun list(
            requestOptions: RequestOptions
        ): HttpResponseFor<SecretConnectionListResponse> =
            list(SecretConnectionListParams.none(), requestOptions)

        /** @see delete */
        suspend fun delete(
            id: String,
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse = delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        suspend fun delete(id: String, params: SecretConnectionDeleteParams): HttpResponse =
            delete(id, params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(id: String): HttpResponse =
            delete(id, SecretConnectionDeleteParams.none())

        /** @see delete */
        suspend fun delete(id: String, requestOptions: RequestOptions): HttpResponse =
            delete(id, SecretConnectionDeleteParams.none(), requestOptions)

        /** Delete a secret connection */
        suspend fun delete(
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse

        /** @see delete */
        suspend fun delete(params: SecretConnectionDeleteParams): HttpResponse =
            delete(params, RequestOptions.none())

        /** @see test */
        suspend fun test(
            id: String,
            params: SecretConnectionTestParams,
            requestOptions: RequestOptions,
        ): HttpResponse = test(params.toBuilder().id(id).build(), requestOptions)

        /** @see test */
        suspend fun test(id: String, params: SecretConnectionTestParams): HttpResponse =
            test(id, params, RequestOptions.none())

        /** @see test */
        suspend fun test(id: String): HttpResponse = test(id, SecretConnectionTestParams.none())

        /** @see test */
        suspend fun test(id: String, requestOptions: RequestOptions): HttpResponse =
            test(id, SecretConnectionTestParams.none(), requestOptions)

        /** Test a secret connection */
        suspend fun test(
            params: SecretConnectionTestParams,
            requestOptions: RequestOptions,
        ): HttpResponse

        /** @see test */
        suspend fun test(params: SecretConnectionTestParams): HttpResponse =
            test(params, RequestOptions.none())
    }
}
