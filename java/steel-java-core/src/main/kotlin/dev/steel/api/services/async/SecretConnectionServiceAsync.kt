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
import java.util.concurrent.CompletableFuture
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
    fun create(
        params: SecretConnectionCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionCreateResponse>

    /** @see create */
    fun create(
        params: SecretConnectionCreateParams
    ): CompletableFuture<SecretConnectionCreateResponse> = create(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionRetrieveResponse> =
        retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SecretConnectionRetrieveParams,
    ): CompletableFuture<SecretConnectionRetrieveResponse> =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): CompletableFuture<SecretConnectionRetrieveResponse> =
        retrieve(id, SecretConnectionRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionRetrieveResponse> =
        retrieve(id, SecretConnectionRetrieveParams.none(), requestOptions)

    /** Get a secret connection */
    fun retrieve(
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionRetrieveResponse>

    /** @see retrieve */
    fun retrieve(
        params: SecretConnectionRetrieveParams
    ): CompletableFuture<SecretConnectionRetrieveResponse> = retrieve(params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionUpdateResponse> =
        update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(
        id: String,
        params: SecretConnectionUpdateParams,
    ): CompletableFuture<SecretConnectionUpdateResponse> = update(id, params, RequestOptions.none())

    /** @see update */
    fun update(id: String): CompletableFuture<SecretConnectionUpdateResponse> =
        update(id, SecretConnectionUpdateParams.none())

    /** @see update */
    fun update(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionUpdateResponse> =
        update(id, SecretConnectionUpdateParams.none(), requestOptions)

    /** Update a secret connection */
    fun update(
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionUpdateResponse>

    /** @see update */
    fun update(
        params: SecretConnectionUpdateParams
    ): CompletableFuture<SecretConnectionUpdateResponse> = update(params, RequestOptions.none())

    /** List secret connections */
    fun list(
        params: SecretConnectionListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionListResponse>

    /** @see list */
    fun list(params: SecretConnectionListParams): CompletableFuture<SecretConnectionListResponse> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<SecretConnectionListResponse> =
        list(SecretConnectionListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<SecretConnectionListResponse> =
        list(SecretConnectionListParams.none(), requestOptions)

    /** @see delete */
    fun delete(
        id: String,
        params: SecretConnectionDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(id: String, params: SecretConnectionDeleteParams): CompletableFuture<Void?> =
        delete(id, params, RequestOptions.none())

    /** @see delete */
    fun delete(id: String): CompletableFuture<Void?> =
        delete(id, SecretConnectionDeleteParams.none())

    /** @see delete */
    fun delete(id: String, requestOptions: RequestOptions): CompletableFuture<Void?> =
        delete(id, SecretConnectionDeleteParams.none(), requestOptions)

    /** Delete a secret connection */
    fun delete(
        params: SecretConnectionDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?>

    /** @see delete */
    fun delete(params: SecretConnectionDeleteParams): CompletableFuture<Void?> =
        delete(params, RequestOptions.none())

    /** @see test */
    fun test(
        id: String,
        params: SecretConnectionTestParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> = test(params.toBuilder().id(id).build(), requestOptions)

    /** @see test */
    fun test(id: String, params: SecretConnectionTestParams): CompletableFuture<Void?> =
        test(id, params, RequestOptions.none())

    /** @see test */
    fun test(id: String): CompletableFuture<Void?> = test(id, SecretConnectionTestParams.none())

    /** @see test */
    fun test(id: String, requestOptions: RequestOptions): CompletableFuture<Void?> =
        test(id, SecretConnectionTestParams.none(), requestOptions)

    /** Test a secret connection */
    fun test(
        params: SecretConnectionTestParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?>

    /** @see test */
    fun test(params: SecretConnectionTestParams): CompletableFuture<Void?> =
        test(params, RequestOptions.none())

    /**
     * A view of [SecretConnectionServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SecretConnectionServiceAsync.WithRawResponse

        /** Create a secret connection */
        fun create(
            params: SecretConnectionCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionCreateResponse>>

        /** @see create */
        fun create(
            params: SecretConnectionCreateParams
        ): CompletableFuture<HttpResponseFor<SecretConnectionCreateResponse>> =
            create(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionRetrieveResponse>> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SecretConnectionRetrieveParams,
        ): CompletableFuture<HttpResponseFor<SecretConnectionRetrieveResponse>> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            id: String
        ): CompletableFuture<HttpResponseFor<SecretConnectionRetrieveResponse>> =
            retrieve(id, SecretConnectionRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionRetrieveResponse>> =
            retrieve(id, SecretConnectionRetrieveParams.none(), requestOptions)

        /** Get a secret connection */
        fun retrieve(
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionRetrieveResponse>>

        /** @see retrieve */
        fun retrieve(
            params: SecretConnectionRetrieveParams
        ): CompletableFuture<HttpResponseFor<SecretConnectionRetrieveResponse>> =
            retrieve(params, RequestOptions.none())

        /** @see update */
        fun update(
            id: String,
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionUpdateResponse>> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        fun update(
            id: String,
            params: SecretConnectionUpdateParams,
        ): CompletableFuture<HttpResponseFor<SecretConnectionUpdateResponse>> =
            update(id, params, RequestOptions.none())

        /** @see update */
        fun update(id: String): CompletableFuture<HttpResponseFor<SecretConnectionUpdateResponse>> =
            update(id, SecretConnectionUpdateParams.none())

        /** @see update */
        fun update(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionUpdateResponse>> =
            update(id, SecretConnectionUpdateParams.none(), requestOptions)

        /** Update a secret connection */
        fun update(
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionUpdateResponse>>

        /** @see update */
        fun update(
            params: SecretConnectionUpdateParams
        ): CompletableFuture<HttpResponseFor<SecretConnectionUpdateResponse>> =
            update(params, RequestOptions.none())

        /** List secret connections */
        fun list(
            params: SecretConnectionListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionListResponse>>

        /** @see list */
        fun list(
            params: SecretConnectionListParams
        ): CompletableFuture<HttpResponseFor<SecretConnectionListResponse>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<SecretConnectionListResponse>> =
            list(SecretConnectionListParams.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SecretConnectionListResponse>> =
            list(SecretConnectionListParams.none(), requestOptions)

        /** @see delete */
        fun delete(
            id: String,
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        fun delete(
            id: String,
            params: SecretConnectionDeleteParams,
        ): CompletableFuture<HttpResponse> = delete(id, params, RequestOptions.none())

        /** @see delete */
        fun delete(id: String): CompletableFuture<HttpResponse> =
            delete(id, SecretConnectionDeleteParams.none())

        /** @see delete */
        fun delete(id: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
            delete(id, SecretConnectionDeleteParams.none(), requestOptions)

        /** Delete a secret connection */
        fun delete(
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see delete */
        fun delete(params: SecretConnectionDeleteParams): CompletableFuture<HttpResponse> =
            delete(params, RequestOptions.none())

        /** @see test */
        fun test(
            id: String,
            params: SecretConnectionTestParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> = test(params.toBuilder().id(id).build(), requestOptions)

        /** @see test */
        fun test(id: String, params: SecretConnectionTestParams): CompletableFuture<HttpResponse> =
            test(id, params, RequestOptions.none())

        /** @see test */
        fun test(id: String): CompletableFuture<HttpResponse> =
            test(id, SecretConnectionTestParams.none())

        /** @see test */
        fun test(id: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
            test(id, SecretConnectionTestParams.none(), requestOptions)

        /** Test a secret connection */
        fun test(
            params: SecretConnectionTestParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see test */
        fun test(params: SecretConnectionTestParams): CompletableFuture<HttpResponse> =
            test(params, RequestOptions.none())
    }
}
