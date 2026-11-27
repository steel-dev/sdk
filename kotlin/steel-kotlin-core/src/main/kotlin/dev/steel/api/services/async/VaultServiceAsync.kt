package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.vaults.Vault
import dev.steel.api.models.vaults.VaultCreateParams
import dev.steel.api.models.vaults.VaultDeleteParams
import dev.steel.api.models.vaults.VaultList
import dev.steel.api.models.vaults.VaultListParams
import dev.steel.api.models.vaults.VaultRetrieveParams
import dev.steel.api.models.vaults.VaultUpdateParams
import java.util.function.Consumer

interface VaultServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VaultServiceAsync

    /** Create a Vault */
    suspend fun create(params: VaultCreateParams, requestOptions: RequestOptions): Vault

    /** @see create */
    suspend fun create(params: VaultCreateParams): Vault = create(params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        id: String,
        params: VaultRetrieveParams,
        requestOptions: RequestOptions,
    ): Vault = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(id: String, params: VaultRetrieveParams): Vault =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(id: String): Vault = retrieve(id, VaultRetrieveParams.none())

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): Vault =
        retrieve(id, VaultRetrieveParams.none(), requestOptions)

    /** Get a Vault */
    suspend fun retrieve(params: VaultRetrieveParams, requestOptions: RequestOptions): Vault

    /** @see retrieve */
    suspend fun retrieve(params: VaultRetrieveParams): Vault =
        retrieve(params, RequestOptions.none())

    /** @see update */
    suspend fun update(
        id: String,
        params: VaultUpdateParams,
        requestOptions: RequestOptions,
    ): Vault = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    suspend fun update(id: String, params: VaultUpdateParams): Vault =
        update(id, params, RequestOptions.none())

    /** @see update */
    suspend fun update(id: String): Vault = update(id, VaultUpdateParams.none())

    /** @see update */
    suspend fun update(id: String, requestOptions: RequestOptions): Vault =
        update(id, VaultUpdateParams.none(), requestOptions)

    /** Update a Vault */
    suspend fun update(params: VaultUpdateParams, requestOptions: RequestOptions): Vault

    /** @see update */
    suspend fun update(params: VaultUpdateParams): Vault = update(params, RequestOptions.none())

    /** List Vaults */
    suspend fun list(params: VaultListParams, requestOptions: RequestOptions): VaultList

    /** @see list */
    suspend fun list(params: VaultListParams): VaultList = list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(): VaultList = list(VaultListParams.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): VaultList =
        list(VaultListParams.none(), requestOptions)

    /** @see delete */
    suspend fun delete(id: String, params: VaultDeleteParams, requestOptions: RequestOptions) =
        delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(id: String, params: VaultDeleteParams) =
        delete(id, params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(id: String) = delete(id, VaultDeleteParams.none())

    /** @see delete */
    suspend fun delete(id: String, requestOptions: RequestOptions) =
        delete(id, VaultDeleteParams.none(), requestOptions)

    /**
     * Soft-delete a Vault. Returns 409 while it contains any live Secrets or credentials; move or
     * delete them first.
     */
    suspend fun delete(params: VaultDeleteParams, requestOptions: RequestOptions)

    /** @see delete */
    suspend fun delete(params: VaultDeleteParams) = delete(params, RequestOptions.none())

    /** A view of [VaultServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VaultServiceAsync.WithRawResponse

        /** Create a Vault */
        suspend fun create(
            params: VaultCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault>

        /** @see create */
        suspend fun create(params: VaultCreateParams): HttpResponseFor<Vault> =
            create(params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            id: String,
            params: VaultRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault> = retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        suspend fun retrieve(id: String, params: VaultRetrieveParams): HttpResponseFor<Vault> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(id: String): HttpResponseFor<Vault> =
            retrieve(id, VaultRetrieveParams.none())

        /** @see retrieve */
        suspend fun retrieve(id: String, requestOptions: RequestOptions): HttpResponseFor<Vault> =
            retrieve(id, VaultRetrieveParams.none(), requestOptions)

        /** Get a Vault */
        suspend fun retrieve(
            params: VaultRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault>

        /** @see retrieve */
        suspend fun retrieve(params: VaultRetrieveParams): HttpResponseFor<Vault> =
            retrieve(params, RequestOptions.none())

        /** @see update */
        suspend fun update(
            id: String,
            params: VaultUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault> = update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        suspend fun update(id: String, params: VaultUpdateParams): HttpResponseFor<Vault> =
            update(id, params, RequestOptions.none())

        /** @see update */
        suspend fun update(id: String): HttpResponseFor<Vault> =
            update(id, VaultUpdateParams.none())

        /** @see update */
        suspend fun update(id: String, requestOptions: RequestOptions): HttpResponseFor<Vault> =
            update(id, VaultUpdateParams.none(), requestOptions)

        /** Update a Vault */
        suspend fun update(
            params: VaultUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault>

        /** @see update */
        suspend fun update(params: VaultUpdateParams): HttpResponseFor<Vault> =
            update(params, RequestOptions.none())

        /** List Vaults */
        suspend fun list(
            params: VaultListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VaultList>

        /** @see list */
        suspend fun list(params: VaultListParams): HttpResponseFor<VaultList> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(): HttpResponseFor<VaultList> = list(VaultListParams.none())

        /** @see list */
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<VaultList> =
            list(VaultListParams.none(), requestOptions)

        /** @see delete */
        suspend fun delete(
            id: String,
            params: VaultDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse = delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        suspend fun delete(id: String, params: VaultDeleteParams): HttpResponse =
            delete(id, params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(id: String): HttpResponse = delete(id, VaultDeleteParams.none())

        /** @see delete */
        suspend fun delete(id: String, requestOptions: RequestOptions): HttpResponse =
            delete(id, VaultDeleteParams.none(), requestOptions)

        /**
         * Soft-delete a Vault. Returns 409 while it contains any live Secrets or credentials; move
         * or delete them first.
         */
        suspend fun delete(params: VaultDeleteParams, requestOptions: RequestOptions): HttpResponse

        /** @see delete */
        suspend fun delete(params: VaultDeleteParams): HttpResponse =
            delete(params, RequestOptions.none())
    }
}
