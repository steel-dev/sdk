package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.files.File
import dev.steel.api.models.files.FileDeleteParams
import dev.steel.api.models.files.FileDownloadParams
import dev.steel.api.models.files.FileListParams
import dev.steel.api.models.files.FileUploadParams
import dev.steel.api.models.files.Fileslist
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface FileServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync

    /** List all global files for the organization in descending order. */
    fun list(params: FileListParams, requestOptions: RequestOptions): CompletableFuture<Fileslist>

    /** @see list */
    fun list(params: FileListParams): CompletableFuture<Fileslist> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<Fileslist> = list(FileListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<Fileslist> =
        list(FileListParams.none(), requestOptions)

    /** @see delete */
    fun delete(
        path: String,
        params: FileDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> = delete(params.toBuilder().path(path).build(), requestOptions)

    /** @see delete */
    fun delete(path: String, params: FileDeleteParams): CompletableFuture<Void?> =
        delete(path, params, RequestOptions.none())

    /** @see delete */
    fun delete(path: String): CompletableFuture<Void?> = delete(path, FileDeleteParams.none())

    /** @see delete */
    fun delete(path: String, requestOptions: RequestOptions): CompletableFuture<Void?> =
        delete(path, FileDeleteParams.none(), requestOptions)

    /** Delete a file from global storage */
    fun delete(params: FileDeleteParams, requestOptions: RequestOptions): CompletableFuture<Void?>

    /** @see delete */
    fun delete(params: FileDeleteParams): CompletableFuture<Void?> =
        delete(params, RequestOptions.none())

    /** @see download */
    fun download(
        path: String,
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        download(params.toBuilder().path(path).build(), requestOptions)

    /** @see download */
    fun download(path: String, params: FileDownloadParams): CompletableFuture<HttpResponse> =
        download(path, params, RequestOptions.none())

    /** @see download */
    fun download(path: String): CompletableFuture<HttpResponse> =
        download(path, FileDownloadParams.none())

    /** @see download */
    fun download(path: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
        download(path, FileDownloadParams.none(), requestOptions)

    /** Download a file from global storage */
    fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse>

    /** @see download */
    fun download(params: FileDownloadParams): CompletableFuture<HttpResponse> =
        download(params, RequestOptions.none())

    /**
     * Uploads a file to global storage via `multipart/form-data` with a `file` field that accepts
     * either binary data or a URL string to download from, and an optional `path` field for the
     * file storage path.
     */
    fun upload(params: FileUploadParams, requestOptions: RequestOptions): CompletableFuture<File>

    /** @see upload */
    fun upload(params: FileUploadParams): CompletableFuture<File> =
        upload(params, RequestOptions.none())

    /** A view of [FileServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync.WithRawResponse

        /** List all global files for the organization in descending order. */
        fun list(
            params: FileListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Fileslist>>

        /** @see list */
        fun list(params: FileListParams): CompletableFuture<HttpResponseFor<Fileslist>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<Fileslist>> = list(FileListParams.none())

        /** @see list */
        fun list(requestOptions: RequestOptions): CompletableFuture<HttpResponseFor<Fileslist>> =
            list(FileListParams.none(), requestOptions)

        /** @see delete */
        fun delete(
            path: String,
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            delete(params.toBuilder().path(path).build(), requestOptions)

        /** @see delete */
        fun delete(path: String, params: FileDeleteParams): CompletableFuture<HttpResponse> =
            delete(path, params, RequestOptions.none())

        /** @see delete */
        fun delete(path: String): CompletableFuture<HttpResponse> =
            delete(path, FileDeleteParams.none())

        /** @see delete */
        fun delete(path: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
            delete(path, FileDeleteParams.none(), requestOptions)

        /** Delete a file from global storage */
        fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see delete */
        fun delete(params: FileDeleteParams): CompletableFuture<HttpResponse> =
            delete(params, RequestOptions.none())

        /** @see download */
        fun download(
            path: String,
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            download(params.toBuilder().path(path).build(), requestOptions)

        /** @see download */
        fun download(path: String, params: FileDownloadParams): CompletableFuture<HttpResponse> =
            download(path, params, RequestOptions.none())

        /** @see download */
        fun download(path: String): CompletableFuture<HttpResponse> =
            download(path, FileDownloadParams.none())

        /** @see download */
        fun download(
            path: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            download(path, FileDownloadParams.none(), requestOptions)

        /** Download a file from global storage */
        fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see download */
        fun download(params: FileDownloadParams): CompletableFuture<HttpResponse> =
            download(params, RequestOptions.none())

        /**
         * Uploads a file to global storage via `multipart/form-data` with a `file` field that
         * accepts either binary data or a URL string to download from, and an optional `path` field
         * for the file storage path.
         */
        fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<File>>

        /** @see upload */
        fun upload(params: FileUploadParams): CompletableFuture<HttpResponseFor<File>> =
            upload(params, RequestOptions.none())
    }
}
