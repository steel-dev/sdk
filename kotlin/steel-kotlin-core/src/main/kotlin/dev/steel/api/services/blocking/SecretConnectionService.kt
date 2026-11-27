package dev.steel.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
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

interface SecretConnectionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SecretConnectionService

    /** Create a secret connection */
    fun create(
        params: SecretConnectionCreateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionCreateResponse

    /** @see create */
    fun create(params: SecretConnectionCreateParams): SecretConnectionCreateResponse =
        create(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): SecretConnectionRetrieveResponse =
        retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SecretConnectionRetrieveParams,
    ): SecretConnectionRetrieveResponse = retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): SecretConnectionRetrieveResponse =
        retrieve(id, SecretConnectionRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): SecretConnectionRetrieveResponse =
        retrieve(id, SecretConnectionRetrieveParams.none(), requestOptions)

    /** Get a secret connection */
    fun retrieve(
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): SecretConnectionRetrieveResponse

    /** @see retrieve */
    fun retrieve(params: SecretConnectionRetrieveParams): SecretConnectionRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionUpdateResponse = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(id: String, params: SecretConnectionUpdateParams): SecretConnectionUpdateResponse =
        update(id, params, RequestOptions.none())

    /** @see update */
    fun update(id: String): SecretConnectionUpdateResponse =
        update(id, SecretConnectionUpdateParams.none())

    /** @see update */
    fun update(id: String, requestOptions: RequestOptions): SecretConnectionUpdateResponse =
        update(id, SecretConnectionUpdateParams.none(), requestOptions)

    /** Update a secret connection */
    fun update(
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionUpdateResponse

    /** @see update */
    fun update(params: SecretConnectionUpdateParams): SecretConnectionUpdateResponse =
        update(params, RequestOptions.none())

    /** List secret connections */
    fun list(
        params: SecretConnectionListParams,
        requestOptions: RequestOptions,
    ): SecretConnectionListResponse

    /** @see list */
    fun list(params: SecretConnectionListParams): SecretConnectionListResponse =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): SecretConnectionListResponse = list(SecretConnectionListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): SecretConnectionListResponse =
        list(SecretConnectionListParams.none(), requestOptions)

    /** @see delete */
    fun delete(id: String, params: SecretConnectionDeleteParams, requestOptions: RequestOptions) =
        delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(id: String, params: SecretConnectionDeleteParams) =
        delete(id, params, RequestOptions.none())

    /** @see delete */
    fun delete(id: String) = delete(id, SecretConnectionDeleteParams.none())

    /** @see delete */
    fun delete(id: String, requestOptions: RequestOptions) =
        delete(id, SecretConnectionDeleteParams.none(), requestOptions)

    /** Delete a secret connection */
    fun delete(params: SecretConnectionDeleteParams, requestOptions: RequestOptions)

    /** @see delete */
    fun delete(params: SecretConnectionDeleteParams) = delete(params, RequestOptions.none())

    /** @see test */
    fun test(id: String, params: SecretConnectionTestParams, requestOptions: RequestOptions) =
        test(params.toBuilder().id(id).build(), requestOptions)

    /** @see test */
    fun test(id: String, params: SecretConnectionTestParams) =
        test(id, params, RequestOptions.none())

    /** @see test */
    fun test(id: String) = test(id, SecretConnectionTestParams.none())

    /** @see test */
    fun test(id: String, requestOptions: RequestOptions) =
        test(id, SecretConnectionTestParams.none(), requestOptions)

    /** Test a secret connection */
    fun test(params: SecretConnectionTestParams, requestOptions: RequestOptions)

    /** @see test */
    fun test(params: SecretConnectionTestParams) = test(params, RequestOptions.none())

    /**
     * A view of [SecretConnectionService] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SecretConnectionService.WithRawResponse

        /** Create a secret connection */
        @MustBeClosed
        fun create(
            params: SecretConnectionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionCreateResponse>

        /** @see create */
        @MustBeClosed
        fun create(
            params: SecretConnectionCreateParams
        ): HttpResponseFor<SecretConnectionCreateResponse> = create(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: SecretConnectionRetrieveParams,
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(id, SecretConnectionRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(id, SecretConnectionRetrieveParams.none(), requestOptions)

        /** Get a secret connection */
        @MustBeClosed
        fun retrieve(
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SecretConnectionRetrieveParams
        ): HttpResponseFor<SecretConnectionRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            id: String,
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            id: String,
            params: SecretConnectionUpdateParams,
        ): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(id, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(id: String): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(id, SecretConnectionUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionUpdateResponse> =
            update(id, SecretConnectionUpdateParams.none(), requestOptions)

        /** Update a secret connection */
        @MustBeClosed
        fun update(
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionUpdateResponse>

        /** @see update */
        @MustBeClosed
        fun update(
            params: SecretConnectionUpdateParams
        ): HttpResponseFor<SecretConnectionUpdateResponse> = update(params, RequestOptions.none())

        /** List secret connections */
        @MustBeClosed
        fun list(
            params: SecretConnectionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionListResponse>

        /** @see list */
        @MustBeClosed
        fun list(
            params: SecretConnectionListParams
        ): HttpResponseFor<SecretConnectionListResponse> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(): HttpResponseFor<SecretConnectionListResponse> =
            list(SecretConnectionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<SecretConnectionListResponse> =
            list(SecretConnectionListParams.none(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            id: String,
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse = delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(id: String, params: SecretConnectionDeleteParams): HttpResponse =
            delete(id, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(id: String): HttpResponse = delete(id, SecretConnectionDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(id: String, requestOptions: RequestOptions): HttpResponse =
            delete(id, SecretConnectionDeleteParams.none(), requestOptions)

        /** Delete a secret connection */
        @MustBeClosed
        fun delete(
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse

        /** @see delete */
        @MustBeClosed
        fun delete(params: SecretConnectionDeleteParams): HttpResponse =
            delete(params, RequestOptions.none())

        /** @see test */
        @MustBeClosed
        fun test(
            id: String,
            params: SecretConnectionTestParams,
            requestOptions: RequestOptions,
        ): HttpResponse = test(params.toBuilder().id(id).build(), requestOptions)

        /** @see test */
        @MustBeClosed
        fun test(id: String, params: SecretConnectionTestParams): HttpResponse =
            test(id, params, RequestOptions.none())

        /** @see test */
        @MustBeClosed
        fun test(id: String): HttpResponse = test(id, SecretConnectionTestParams.none())

        /** @see test */
        @MustBeClosed
        fun test(id: String, requestOptions: RequestOptions): HttpResponse =
            test(id, SecretConnectionTestParams.none(), requestOptions)

        /** Test a secret connection */
        @MustBeClosed
        fun test(params: SecretConnectionTestParams, requestOptions: RequestOptions): HttpResponse

        /** @see test */
        @MustBeClosed
        fun test(params: SecretConnectionTestParams): HttpResponse =
            test(params, RequestOptions.none())
    }
}
