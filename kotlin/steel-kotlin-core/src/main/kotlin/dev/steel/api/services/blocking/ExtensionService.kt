package dev.steel.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
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
import java.util.function.Consumer

interface ExtensionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExtensionService

    /** @see update */
    fun update(
        extensionId: String,
        params: ExtensionUpdateParams,
        requestOptions: RequestOptions,
    ): ExtensionUpdateResponse =
        update(params.toBuilder().extensionId(extensionId).build(), requestOptions)

    /** @see update */
    fun update(extensionId: String, params: ExtensionUpdateParams): ExtensionUpdateResponse =
        update(extensionId, params, RequestOptions.none())

    /** @see update */
    fun update(extensionId: String): ExtensionUpdateResponse =
        update(extensionId, ExtensionUpdateParams.none())

    /** @see update */
    fun update(extensionId: String, requestOptions: RequestOptions): ExtensionUpdateResponse =
        update(extensionId, ExtensionUpdateParams.none(), requestOptions)

    /** Update a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization */
    fun update(
        params: ExtensionUpdateParams,
        requestOptions: RequestOptions,
    ): ExtensionUpdateResponse

    /** @see update */
    fun update(params: ExtensionUpdateParams): ExtensionUpdateResponse =
        update(params, RequestOptions.none())

    /** List all extensions for the organization */
    fun list(params: ExtensionListParams, requestOptions: RequestOptions): ExtensionListResponse

    /** @see list */
    fun list(params: ExtensionListParams): ExtensionListResponse =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): ExtensionListResponse = list(ExtensionListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ExtensionListResponse =
        list(ExtensionListParams.none(), requestOptions)

    /** @see delete */
    fun delete(
        extensionId: String,
        params: ExtensionDeleteParams,
        requestOptions: RequestOptions,
    ): ExtensionDeleteResponse =
        delete(params.toBuilder().extensionId(extensionId).build(), requestOptions)

    /** @see delete */
    fun delete(extensionId: String, params: ExtensionDeleteParams): ExtensionDeleteResponse =
        delete(extensionId, params, RequestOptions.none())

    /** @see delete */
    fun delete(extensionId: String): ExtensionDeleteResponse =
        delete(extensionId, ExtensionDeleteParams.none())

    /** @see delete */
    fun delete(extensionId: String, requestOptions: RequestOptions): ExtensionDeleteResponse =
        delete(extensionId, ExtensionDeleteParams.none(), requestOptions)

    /** Delete an extension by ID */
    fun delete(
        params: ExtensionDeleteParams,
        requestOptions: RequestOptions,
    ): ExtensionDeleteResponse

    /** @see delete */
    fun delete(params: ExtensionDeleteParams): ExtensionDeleteResponse =
        delete(params, RequestOptions.none())

    /** Delete all extensions for the organization */
    fun deleteAll(
        params: ExtensionDeleteAllParams,
        requestOptions: RequestOptions,
    ): ExtensionDeleteAllResponse

    /** @see deleteAll */
    fun deleteAll(params: ExtensionDeleteAllParams): ExtensionDeleteAllResponse =
        deleteAll(params, RequestOptions.none())

    /** @see deleteAll */
    fun deleteAll(): ExtensionDeleteAllResponse = deleteAll(ExtensionDeleteAllParams.none())

    /** @see deleteAll */
    fun deleteAll(requestOptions: RequestOptions): ExtensionDeleteAllResponse =
        deleteAll(ExtensionDeleteAllParams.none(), requestOptions)

    /** @see download */
    @MustBeClosed
    fun download(
        extensionId: String,
        params: ExtensionDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse = download(params.toBuilder().extensionId(extensionId).build(), requestOptions)

    /** @see download */
    @MustBeClosed
    fun download(extensionId: String, params: ExtensionDownloadParams): HttpResponse =
        download(extensionId, params, RequestOptions.none())

    /** @see download */
    @MustBeClosed
    fun download(extensionId: String): HttpResponse =
        download(extensionId, ExtensionDownloadParams.none())

    /** @see download */
    @MustBeClosed
    fun download(extensionId: String, requestOptions: RequestOptions): HttpResponse =
        download(extensionId, ExtensionDownloadParams.none(), requestOptions)

    /** Download an extension file by extension ID */
    @MustBeClosed
    fun download(params: ExtensionDownloadParams, requestOptions: RequestOptions): HttpResponse

    /** @see download */
    @MustBeClosed
    fun download(params: ExtensionDownloadParams): HttpResponse =
        download(params, RequestOptions.none())

    /** Upload a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization */
    fun upload(
        params: ExtensionUploadParams,
        requestOptions: RequestOptions,
    ): ExtensionUploadResponse

    /** @see upload */
    fun upload(params: ExtensionUploadParams): ExtensionUploadResponse =
        upload(params, RequestOptions.none())

    /** @see upload */
    fun upload(): ExtensionUploadResponse = upload(ExtensionUploadParams.none())

    /** @see upload */
    fun upload(requestOptions: RequestOptions): ExtensionUploadResponse =
        upload(ExtensionUploadParams.none(), requestOptions)

    /** A view of [ExtensionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExtensionService.WithRawResponse

        /** @see update */
        @MustBeClosed
        fun update(
            extensionId: String,
            params: ExtensionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionUpdateResponse> =
            update(params.toBuilder().extensionId(extensionId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            extensionId: String,
            params: ExtensionUpdateParams,
        ): HttpResponseFor<ExtensionUpdateResponse> =
            update(extensionId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(extensionId: String): HttpResponseFor<ExtensionUpdateResponse> =
            update(extensionId, ExtensionUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            extensionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionUpdateResponse> =
            update(extensionId, ExtensionUpdateParams.none(), requestOptions)

        /**
         * Update a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization
         */
        @MustBeClosed
        fun update(
            params: ExtensionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionUpdateResponse>

        /** @see update */
        @MustBeClosed
        fun update(params: ExtensionUpdateParams): HttpResponseFor<ExtensionUpdateResponse> =
            update(params, RequestOptions.none())

        /** List all extensions for the organization */
        @MustBeClosed
        fun list(
            params: ExtensionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionListResponse>

        /** @see list */
        @MustBeClosed
        fun list(params: ExtensionListParams): HttpResponseFor<ExtensionListResponse> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(): HttpResponseFor<ExtensionListResponse> = list(ExtensionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ExtensionListResponse> =
            list(ExtensionListParams.none(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            extensionId: String,
            params: ExtensionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionDeleteResponse> =
            delete(params.toBuilder().extensionId(extensionId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            extensionId: String,
            params: ExtensionDeleteParams,
        ): HttpResponseFor<ExtensionDeleteResponse> =
            delete(extensionId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(extensionId: String): HttpResponseFor<ExtensionDeleteResponse> =
            delete(extensionId, ExtensionDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            extensionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionDeleteResponse> =
            delete(extensionId, ExtensionDeleteParams.none(), requestOptions)

        /** Delete an extension by ID */
        @MustBeClosed
        fun delete(
            params: ExtensionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionDeleteResponse>

        /** @see delete */
        @MustBeClosed
        fun delete(params: ExtensionDeleteParams): HttpResponseFor<ExtensionDeleteResponse> =
            delete(params, RequestOptions.none())

        /** Delete all extensions for the organization */
        @MustBeClosed
        fun deleteAll(
            params: ExtensionDeleteAllParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionDeleteAllResponse>

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(
            params: ExtensionDeleteAllParams
        ): HttpResponseFor<ExtensionDeleteAllResponse> = deleteAll(params, RequestOptions.none())

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(): HttpResponseFor<ExtensionDeleteAllResponse> =
            deleteAll(ExtensionDeleteAllParams.none())

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(requestOptions: RequestOptions): HttpResponseFor<ExtensionDeleteAllResponse> =
            deleteAll(ExtensionDeleteAllParams.none(), requestOptions)

        /** @see download */
        @MustBeClosed
        fun download(
            extensionId: String,
            params: ExtensionDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse =
            download(params.toBuilder().extensionId(extensionId).build(), requestOptions)

        /** @see download */
        @MustBeClosed
        fun download(extensionId: String, params: ExtensionDownloadParams): HttpResponse =
            download(extensionId, params, RequestOptions.none())

        /** @see download */
        @MustBeClosed
        fun download(extensionId: String): HttpResponse =
            download(extensionId, ExtensionDownloadParams.none())

        /** @see download */
        @MustBeClosed
        fun download(extensionId: String, requestOptions: RequestOptions): HttpResponse =
            download(extensionId, ExtensionDownloadParams.none(), requestOptions)

        /** Download an extension file by extension ID */
        @MustBeClosed
        fun download(params: ExtensionDownloadParams, requestOptions: RequestOptions): HttpResponse

        /** @see download */
        @MustBeClosed
        fun download(params: ExtensionDownloadParams): HttpResponse =
            download(params, RequestOptions.none())

        /**
         * Upload a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization
         */
        @MustBeClosed
        fun upload(
            params: ExtensionUploadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionUploadResponse>

        /** @see upload */
        @MustBeClosed
        fun upload(params: ExtensionUploadParams): HttpResponseFor<ExtensionUploadResponse> =
            upload(params, RequestOptions.none())

        /** @see upload */
        @MustBeClosed
        fun upload(): HttpResponseFor<ExtensionUploadResponse> =
            upload(ExtensionUploadParams.none())

        /** @see upload */
        @MustBeClosed
        fun upload(requestOptions: RequestOptions): HttpResponseFor<ExtensionUploadResponse> =
            upload(ExtensionUploadParams.none(), requestOptions)
    }
}
