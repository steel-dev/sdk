package dev.steel.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
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

interface VaultService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VaultService

    /** Create a Vault */
    fun create(params: VaultCreateParams, requestOptions: RequestOptions): Vault

    /** @see create */
    fun create(params: VaultCreateParams): Vault = create(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String, params: VaultRetrieveParams, requestOptions: RequestOptions): Vault =
        retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(id: String, params: VaultRetrieveParams): Vault =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): Vault = retrieve(id, VaultRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): Vault =
        retrieve(id, VaultRetrieveParams.none(), requestOptions)

    /** Get a Vault */
    fun retrieve(params: VaultRetrieveParams, requestOptions: RequestOptions): Vault

    /** @see retrieve */
    fun retrieve(params: VaultRetrieveParams): Vault = retrieve(params, RequestOptions.none())

    /** @see update */
    fun update(id: String, params: VaultUpdateParams, requestOptions: RequestOptions): Vault =
        update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(id: String, params: VaultUpdateParams): Vault =
        update(id, params, RequestOptions.none())

    /** @see update */
    fun update(id: String): Vault = update(id, VaultUpdateParams.none())

    /** @see update */
    fun update(id: String, requestOptions: RequestOptions): Vault =
        update(id, VaultUpdateParams.none(), requestOptions)

    /** Update a Vault */
    fun update(params: VaultUpdateParams, requestOptions: RequestOptions): Vault

    /** @see update */
    fun update(params: VaultUpdateParams): Vault = update(params, RequestOptions.none())

    /** List Vaults */
    fun list(params: VaultListParams, requestOptions: RequestOptions): VaultList

    /** @see list */
    fun list(params: VaultListParams): VaultList = list(params, RequestOptions.none())

    /** @see list */
    fun list(): VaultList = list(VaultListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): VaultList =
        list(VaultListParams.none(), requestOptions)

    /** @see delete */
    fun delete(id: String, params: VaultDeleteParams, requestOptions: RequestOptions) =
        delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(id: String, params: VaultDeleteParams) = delete(id, params, RequestOptions.none())

    /** @see delete */
    fun delete(id: String) = delete(id, VaultDeleteParams.none())

    /** @see delete */
    fun delete(id: String, requestOptions: RequestOptions) =
        delete(id, VaultDeleteParams.none(), requestOptions)

    /**
     * Soft-delete a Vault. Returns 409 while it contains any live Secrets or credentials; move or
     * delete them first.
     */
    fun delete(params: VaultDeleteParams, requestOptions: RequestOptions)

    /** @see delete */
    fun delete(params: VaultDeleteParams) = delete(params, RequestOptions.none())

    /** A view of [VaultService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): VaultService.WithRawResponse

        /** Create a Vault */
        @MustBeClosed
        fun create(
            params: VaultCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault>

        /** @see create */
        @MustBeClosed
        fun create(params: VaultCreateParams): HttpResponseFor<Vault> =
            create(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: VaultRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault> = retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String, params: VaultRetrieveParams): HttpResponseFor<Vault> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String): HttpResponseFor<Vault> = retrieve(id, VaultRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String, requestOptions: RequestOptions): HttpResponseFor<Vault> =
            retrieve(id, VaultRetrieveParams.none(), requestOptions)

        /** Get a Vault */
        @MustBeClosed
        fun retrieve(
            params: VaultRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: VaultRetrieveParams): HttpResponseFor<Vault> =
            retrieve(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            id: String,
            params: VaultUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault> = update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(id: String, params: VaultUpdateParams): HttpResponseFor<Vault> =
            update(id, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(id: String): HttpResponseFor<Vault> = update(id, VaultUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(id: String, requestOptions: RequestOptions): HttpResponseFor<Vault> =
            update(id, VaultUpdateParams.none(), requestOptions)

        /** Update a Vault */
        @MustBeClosed
        fun update(
            params: VaultUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Vault>

        /** @see update */
        @MustBeClosed
        fun update(params: VaultUpdateParams): HttpResponseFor<Vault> =
            update(params, RequestOptions.none())

        /** List Vaults */
        @MustBeClosed
        fun list(
            params: VaultListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VaultList>

        /** @see list */
        @MustBeClosed
        fun list(params: VaultListParams): HttpResponseFor<VaultList> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed fun list(): HttpResponseFor<VaultList> = list(VaultListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<VaultList> =
            list(VaultListParams.none(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            id: String,
            params: VaultDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse = delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(id: String, params: VaultDeleteParams): HttpResponse =
            delete(id, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed fun delete(id: String): HttpResponse = delete(id, VaultDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(id: String, requestOptions: RequestOptions): HttpResponse =
            delete(id, VaultDeleteParams.none(), requestOptions)

        /**
         * Soft-delete a Vault. Returns 409 while it contains any live Secrets or credentials; move
         * or delete them first.
         */
        @MustBeClosed
        fun delete(params: VaultDeleteParams, requestOptions: RequestOptions): HttpResponse

        /** @see delete */
        @MustBeClosed
        fun delete(params: VaultDeleteParams): HttpResponse = delete(params, RequestOptions.none())
    }
}
