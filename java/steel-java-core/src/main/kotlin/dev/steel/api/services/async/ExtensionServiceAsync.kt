package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.extensions.ExtensionDeleteAllParams
import dev.steel.api.models.extensions.ExtensionDeleteAllResponse
import dev.steel.api.models.extensions.ExtensionDeleteParams
import dev.steel.api.models.extensions.ExtensionDeleteResponse
import dev.steel.api.models.extensions.ExtensionDownloadParams
import dev.steel.api.models.extensions.ExtensionListParams
import dev.steel.api.models.extensions.ExtensionListResponse
import dev.steel.api.models.extensions.ExtensionUpdateParams
import dev.steel.api.models.extensions.ExtensionUpdateResponse
import dev.steel.api.models.extensions.ExtensionUploadParams
import dev.steel.api.models.extensions.ExtensionUploadResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ExtensionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExtensionServiceAsync

    /** @see update */
    fun update(
        extensionId: String,
        params: ExtensionUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionUpdateResponse> =
        update(params.toBuilder().extensionId(extensionId).build(), requestOptions)

    /** @see update */
    fun update(
        extensionId: String,
        params: ExtensionUpdateParams,
    ): CompletableFuture<ExtensionUpdateResponse> =
        update(extensionId, params, RequestOptions.none())

    /** @see update */
    fun update(extensionId: String): CompletableFuture<ExtensionUpdateResponse> =
        update(extensionId, ExtensionUpdateParams.none())

    /** @see update */
    fun update(
        extensionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionUpdateResponse> =
        update(extensionId, ExtensionUpdateParams.none(), requestOptions)

    /** Update a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization */
    fun update(
        params: ExtensionUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionUpdateResponse>

    /** @see update */
    fun update(params: ExtensionUpdateParams): CompletableFuture<ExtensionUpdateResponse> =
        update(params, RequestOptions.none())

    /** List all extensions for the organization */
    fun list(
        params: ExtensionListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionListResponse>

    /** @see list */
    fun list(params: ExtensionListParams): CompletableFuture<ExtensionListResponse> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<ExtensionListResponse> = list(ExtensionListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ExtensionListResponse> =
        list(ExtensionListParams.none(), requestOptions)

    /** @see delete */
    fun delete(
        extensionId: String,
        params: ExtensionDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionDeleteResponse> =
        delete(params.toBuilder().extensionId(extensionId).build(), requestOptions)

    /** @see delete */
    fun delete(
        extensionId: String,
        params: ExtensionDeleteParams,
    ): CompletableFuture<ExtensionDeleteResponse> =
        delete(extensionId, params, RequestOptions.none())

    /** @see delete */
    fun delete(extensionId: String): CompletableFuture<ExtensionDeleteResponse> =
        delete(extensionId, ExtensionDeleteParams.none())

    /** @see delete */
    fun delete(
        extensionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionDeleteResponse> =
        delete(extensionId, ExtensionDeleteParams.none(), requestOptions)

    /** Delete an extension by ID */
    fun delete(
        params: ExtensionDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionDeleteResponse>

    /** @see delete */
    fun delete(params: ExtensionDeleteParams): CompletableFuture<ExtensionDeleteResponse> =
        delete(params, RequestOptions.none())

    /** Delete all extensions for the organization */
    fun deleteAll(
        params: ExtensionDeleteAllParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionDeleteAllResponse>

    /** @see deleteAll */
    fun deleteAll(params: ExtensionDeleteAllParams): CompletableFuture<ExtensionDeleteAllResponse> =
        deleteAll(params, RequestOptions.none())

    /** @see deleteAll */
    fun deleteAll(): CompletableFuture<ExtensionDeleteAllResponse> =
        deleteAll(ExtensionDeleteAllParams.none())

    /** @see deleteAll */
    fun deleteAll(requestOptions: RequestOptions): CompletableFuture<ExtensionDeleteAllResponse> =
        deleteAll(ExtensionDeleteAllParams.none(), requestOptions)

    /** @see download */
    fun download(
        extensionId: String,
        params: ExtensionDownloadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        download(params.toBuilder().extensionId(extensionId).build(), requestOptions)

    /** @see download */
    fun download(
        extensionId: String,
        params: ExtensionDownloadParams,
    ): CompletableFuture<HttpResponse> = download(extensionId, params, RequestOptions.none())

    /** @see download */
    fun download(extensionId: String): CompletableFuture<HttpResponse> =
        download(extensionId, ExtensionDownloadParams.none())

    /** @see download */
    fun download(
        extensionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        download(extensionId, ExtensionDownloadParams.none(), requestOptions)

    /** Download an extension file by extension ID */
    fun download(
        params: ExtensionDownloadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse>

    /** @see download */
    fun download(params: ExtensionDownloadParams): CompletableFuture<HttpResponse> =
        download(params, RequestOptions.none())

    /** Upload a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization */
    fun upload(
        params: ExtensionUploadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExtensionUploadResponse>

    /** @see upload */
    fun upload(params: ExtensionUploadParams): CompletableFuture<ExtensionUploadResponse> =
        upload(params, RequestOptions.none())

    /** @see upload */
    fun upload(): CompletableFuture<ExtensionUploadResponse> = upload(ExtensionUploadParams.none())

    /** @see upload */
    fun upload(requestOptions: RequestOptions): CompletableFuture<ExtensionUploadResponse> =
        upload(ExtensionUploadParams.none(), requestOptions)

    /**
     * A view of [ExtensionServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ExtensionServiceAsync.WithRawResponse

        /** @see update */
        fun update(
            extensionId: String,
            params: ExtensionUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionUpdateResponse>> =
            update(params.toBuilder().extensionId(extensionId).build(), requestOptions)

        /** @see update */
        fun update(
            extensionId: String,
            params: ExtensionUpdateParams,
        ): CompletableFuture<HttpResponseFor<ExtensionUpdateResponse>> =
            update(extensionId, params, RequestOptions.none())

        /** @see update */
        fun update(
            extensionId: String
        ): CompletableFuture<HttpResponseFor<ExtensionUpdateResponse>> =
            update(extensionId, ExtensionUpdateParams.none())

        /** @see update */
        fun update(
            extensionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionUpdateResponse>> =
            update(extensionId, ExtensionUpdateParams.none(), requestOptions)

        /**
         * Update a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization
         */
        fun update(
            params: ExtensionUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionUpdateResponse>>

        /** @see update */
        fun update(
            params: ExtensionUpdateParams
        ): CompletableFuture<HttpResponseFor<ExtensionUpdateResponse>> =
            update(params, RequestOptions.none())

        /** List all extensions for the organization */
        fun list(
            params: ExtensionListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionListResponse>>

        /** @see list */
        fun list(
            params: ExtensionListParams
        ): CompletableFuture<HttpResponseFor<ExtensionListResponse>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<ExtensionListResponse>> =
            list(ExtensionListParams.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ExtensionListResponse>> =
            list(ExtensionListParams.none(), requestOptions)

        /** @see delete */
        fun delete(
            extensionId: String,
            params: ExtensionDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteResponse>> =
            delete(params.toBuilder().extensionId(extensionId).build(), requestOptions)

        /** @see delete */
        fun delete(
            extensionId: String,
            params: ExtensionDeleteParams,
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteResponse>> =
            delete(extensionId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            extensionId: String
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteResponse>> =
            delete(extensionId, ExtensionDeleteParams.none())

        /** @see delete */
        fun delete(
            extensionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteResponse>> =
            delete(extensionId, ExtensionDeleteParams.none(), requestOptions)

        /** Delete an extension by ID */
        fun delete(
            params: ExtensionDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteResponse>>

        /** @see delete */
        fun delete(
            params: ExtensionDeleteParams
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** Delete all extensions for the organization */
        fun deleteAll(
            params: ExtensionDeleteAllParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteAllResponse>>

        /** @see deleteAll */
        fun deleteAll(
            params: ExtensionDeleteAllParams
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteAllResponse>> =
            deleteAll(params, RequestOptions.none())

        /** @see deleteAll */
        fun deleteAll(): CompletableFuture<HttpResponseFor<ExtensionDeleteAllResponse>> =
            deleteAll(ExtensionDeleteAllParams.none())

        /** @see deleteAll */
        fun deleteAll(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ExtensionDeleteAllResponse>> =
            deleteAll(ExtensionDeleteAllParams.none(), requestOptions)

        /** @see download */
        fun download(
            extensionId: String,
            params: ExtensionDownloadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            download(params.toBuilder().extensionId(extensionId).build(), requestOptions)

        /** @see download */
        fun download(
            extensionId: String,
            params: ExtensionDownloadParams,
        ): CompletableFuture<HttpResponse> = download(extensionId, params, RequestOptions.none())

        /** @see download */
        fun download(extensionId: String): CompletableFuture<HttpResponse> =
            download(extensionId, ExtensionDownloadParams.none())

        /** @see download */
        fun download(
            extensionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            download(extensionId, ExtensionDownloadParams.none(), requestOptions)

        /** Download an extension file by extension ID */
        fun download(
            params: ExtensionDownloadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see download */
        fun download(params: ExtensionDownloadParams): CompletableFuture<HttpResponse> =
            download(params, RequestOptions.none())

        /**
         * Upload a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization
         */
        fun upload(
            params: ExtensionUploadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExtensionUploadResponse>>

        /** @see upload */
        fun upload(
            params: ExtensionUploadParams
        ): CompletableFuture<HttpResponseFor<ExtensionUploadResponse>> =
            upload(params, RequestOptions.none())

        /** @see upload */
        fun upload(): CompletableFuture<HttpResponseFor<ExtensionUploadResponse>> =
            upload(ExtensionUploadParams.none())

        /** @see upload */
        fun upload(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ExtensionUploadResponse>> =
            upload(ExtensionUploadParams.none(), requestOptions)
    }
}
