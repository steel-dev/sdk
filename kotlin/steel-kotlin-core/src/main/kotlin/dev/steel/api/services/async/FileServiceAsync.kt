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
    suspend fun list(params: FileListParams, requestOptions: RequestOptions): Fileslist

    /** @see list */
    suspend fun list(params: FileListParams): Fileslist = list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(): Fileslist = list(FileListParams.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): Fileslist =
        list(FileListParams.none(), requestOptions)

    /** @see delete */
    suspend fun delete(path: String, params: FileDeleteParams, requestOptions: RequestOptions) =
        delete(params.toBuilder().path(path).build(), requestOptions)

    /** @see delete */
    suspend fun delete(path: String, params: FileDeleteParams) =
        delete(path, params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(path: String) = delete(path, FileDeleteParams.none())

    /** @see delete */
    suspend fun delete(path: String, requestOptions: RequestOptions) =
        delete(path, FileDeleteParams.none(), requestOptions)

    /** Delete a file from global storage */
    suspend fun delete(params: FileDeleteParams, requestOptions: RequestOptions)

    /** @see delete */
    suspend fun delete(params: FileDeleteParams) = delete(params, RequestOptions.none())

    /** @see download */
    suspend fun download(
        path: String,
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse = download(params.toBuilder().path(path).build(), requestOptions)

    /** @see download */
    suspend fun download(path: String, params: FileDownloadParams): HttpResponse =
        download(path, params, RequestOptions.none())

    /** @see download */
    suspend fun download(path: String): HttpResponse = download(path, FileDownloadParams.none())

    /** @see download */
    suspend fun download(path: String, requestOptions: RequestOptions): HttpResponse =
        download(path, FileDownloadParams.none(), requestOptions)

    /** Download a file from global storage */
    suspend fun download(params: FileDownloadParams, requestOptions: RequestOptions): HttpResponse

    /** @see download */
    suspend fun download(params: FileDownloadParams): HttpResponse =
        download(params, RequestOptions.none())

    /**
     * Uploads a file to global storage via `multipart/form-data` with a `file` field that accepts
     * either binary data or a URL string to download from, and an optional `path` field for the
     * file storage path.
     */
    suspend fun upload(params: FileUploadParams, requestOptions: RequestOptions): File

    /** @see upload */
    suspend fun upload(params: FileUploadParams): File = upload(params, RequestOptions.none())

    /** A view of [FileServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync.WithRawResponse

        /** List all global files for the organization in descending order. */
        suspend fun list(
            params: FileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Fileslist>

        /** @see list */
        suspend fun list(params: FileListParams): HttpResponseFor<Fileslist> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(): HttpResponseFor<Fileslist> = list(FileListParams.none())

        /** @see list */
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<Fileslist> =
            list(FileListParams.none(), requestOptions)

        /** @see delete */
        suspend fun delete(
            path: String,
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse = delete(params.toBuilder().path(path).build(), requestOptions)

        /** @see delete */
        suspend fun delete(path: String, params: FileDeleteParams): HttpResponse =
            delete(path, params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(path: String): HttpResponse = delete(path, FileDeleteParams.none())

        /** @see delete */
        suspend fun delete(path: String, requestOptions: RequestOptions): HttpResponse =
            delete(path, FileDeleteParams.none(), requestOptions)

        /** Delete a file from global storage */
        suspend fun delete(params: FileDeleteParams, requestOptions: RequestOptions): HttpResponse

        /** @see delete */
        suspend fun delete(params: FileDeleteParams): HttpResponse =
            delete(params, RequestOptions.none())

        /** @see download */
        suspend fun download(
            path: String,
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse = download(params.toBuilder().path(path).build(), requestOptions)

        /** @see download */
        suspend fun download(path: String, params: FileDownloadParams): HttpResponse =
            download(path, params, RequestOptions.none())

        /** @see download */
        suspend fun download(path: String): HttpResponse = download(path, FileDownloadParams.none())

        /** @see download */
        suspend fun download(path: String, requestOptions: RequestOptions): HttpResponse =
            download(path, FileDownloadParams.none(), requestOptions)

        /** Download a file from global storage */
        suspend fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse

        /** @see download */
        suspend fun download(params: FileDownloadParams): HttpResponse =
            download(params, RequestOptions.none())

        /**
         * Uploads a file to global storage via `multipart/form-data` with a `file` field that
         * accepts either binary data or a URL string to download from, and an optional `path` field
         * for the file storage path.
         */
        suspend fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<File>

        /** @see upload */
        suspend fun upload(params: FileUploadParams): HttpResponseFor<File> =
            upload(params, RequestOptions.none())
    }
}
