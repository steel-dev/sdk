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
import java.util.concurrent.CompletableFuture
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
    fun create(params: VaultCreateParams, requestOptions: RequestOptions): CompletableFuture<Vault>

    /** @see create */
    fun create(params: VaultCreateParams): CompletableFuture<Vault> =
        create(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: VaultRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Vault> = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(id: String, params: VaultRetrieveParams): CompletableFuture<Vault> =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): CompletableFuture<Vault> = retrieve(id, VaultRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): CompletableFuture<Vault> =
        retrieve(id, VaultRetrieveParams.none(), requestOptions)

    /** Get a Vault */
    fun retrieve(
        params: VaultRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Vault>

    /** @see retrieve */
    fun retrieve(params: VaultRetrieveParams): CompletableFuture<Vault> =
        retrieve(params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: VaultUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Vault> = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(id: String, params: VaultUpdateParams): CompletableFuture<Vault> =
        update(id, params, RequestOptions.none())

    /** @see update */
    fun update(id: String): CompletableFuture<Vault> = update(id, VaultUpdateParams.none())

    /** @see update */
    fun update(id: String, requestOptions: RequestOptions): CompletableFuture<Vault> =
        update(id, VaultUpdateParams.none(), requestOptions)

    /** Update a Vault */
    fun update(params: VaultUpdateParams, requestOptions: RequestOptions): CompletableFuture<Vault>

    /** @see update */
    fun update(params: VaultUpdateParams): CompletableFuture<Vault> =
        update(params, RequestOptions.none())

    /** List Vaults */
    fun list(params: VaultListParams, requestOptions: RequestOptions): CompletableFuture<VaultList>

    /** @see list */
    fun list(params: VaultListParams): CompletableFuture<VaultList> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<VaultList> = list(VaultListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<VaultList> =
        list(VaultListParams.none(), requestOptions)

    /** @see delete */
    fun delete(
        id: String,
        params: VaultDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(id: String, params: VaultDeleteParams): CompletableFuture<Void?> =
        delete(id, params, RequestOptions.none())

    /** @see delete */
    fun delete(id: String): CompletableFuture<Void?> = delete(id, VaultDeleteParams.none())

    /** @see delete */
    fun delete(id: String, requestOptions: RequestOptions): CompletableFuture<Void?> =
        delete(id, VaultDeleteParams.none(), requestOptions)

    /**
     * Soft-delete a Vault. Returns 409 while it contains any live Secrets or credentials; move or
     * delete them first.
     */
    fun delete(params: VaultDeleteParams, requestOptions: RequestOptions): CompletableFuture<Void?>

    /** @see delete */
    fun delete(params: VaultDeleteParams): CompletableFuture<Void?> =
        delete(params, RequestOptions.none())

    /** A view of [VaultServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VaultServiceAsync.WithRawResponse

        /** Create a Vault */
        fun create(
            params: VaultCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Vault>>

        /** @see create */
        fun create(params: VaultCreateParams): CompletableFuture<HttpResponseFor<Vault>> =
            create(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: VaultRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Vault>> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: VaultRetrieveParams,
        ): CompletableFuture<HttpResponseFor<Vault>> = retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(id: String): CompletableFuture<HttpResponseFor<Vault>> =
            retrieve(id, VaultRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Vault>> =
            retrieve(id, VaultRetrieveParams.none(), requestOptions)

        /** Get a Vault */
        fun retrieve(
            params: VaultRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Vault>>

        /** @see retrieve */
        fun retrieve(params: VaultRetrieveParams): CompletableFuture<HttpResponseFor<Vault>> =
            retrieve(params, RequestOptions.none())

        /** @see update */
        fun update(
            id: String,
            params: VaultUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Vault>> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        fun update(
            id: String,
            params: VaultUpdateParams,
        ): CompletableFuture<HttpResponseFor<Vault>> = update(id, params, RequestOptions.none())

        /** @see update */
        fun update(id: String): CompletableFuture<HttpResponseFor<Vault>> =
            update(id, VaultUpdateParams.none())

        /** @see update */
        fun update(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Vault>> =
            update(id, VaultUpdateParams.none(), requestOptions)

        /** Update a Vault */
        fun update(
            params: VaultUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Vault>>

        /** @see update */
        fun update(params: VaultUpdateParams): CompletableFuture<HttpResponseFor<Vault>> =
            update(params, RequestOptions.none())

        /** List Vaults */
        fun list(
            params: VaultListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<VaultList>>

        /** @see list */
        fun list(params: VaultListParams): CompletableFuture<HttpResponseFor<VaultList>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<VaultList>> = list(VaultListParams.none())

        /** @see list */
        fun list(requestOptions: RequestOptions): CompletableFuture<HttpResponseFor<VaultList>> =
            list(VaultListParams.none(), requestOptions)

        /** @see delete */
        fun delete(
            id: String,
            params: VaultDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        fun delete(id: String, params: VaultDeleteParams): CompletableFuture<HttpResponse> =
            delete(id, params, RequestOptions.none())

        /** @see delete */
        fun delete(id: String): CompletableFuture<HttpResponse> =
            delete(id, VaultDeleteParams.none())

        /** @see delete */
        fun delete(id: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
            delete(id, VaultDeleteParams.none(), requestOptions)

        /**
         * Soft-delete a Vault. Returns 409 while it contains any live Secrets or credentials; move
         * or delete them first.
         */
        fun delete(
            params: VaultDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see delete */
        fun delete(params: VaultDeleteParams): CompletableFuture<HttpResponse> =
            delete(params, RequestOptions.none())
    }
}
