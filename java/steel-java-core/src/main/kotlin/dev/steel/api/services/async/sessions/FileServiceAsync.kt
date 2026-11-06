package dev.steel.api.services.async.sessions

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.files.File
import dev.steel.api.models.files.Fileslist
import dev.steel.api.models.sessions.files.FileDeleteAllParams
import dev.steel.api.models.sessions.files.FileDeleteParams
import dev.steel.api.models.sessions.files.FileDownloadArchiveParams
import dev.steel.api.models.sessions.files.FileDownloadParams
import dev.steel.api.models.sessions.files.FileListParams
import dev.steel.api.models.sessions.files.FileUploadParams
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

    /** @see list */
    fun list(
        sessionId: String,
        params: FileListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Fileslist> =
        list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(sessionId: String, params: FileListParams): CompletableFuture<Fileslist> =
        list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(sessionId: String): CompletableFuture<Fileslist> =
        list(sessionId, FileListParams.none())

    /** @see list */
    fun list(sessionId: String, requestOptions: RequestOptions): CompletableFuture<Fileslist> =
        list(sessionId, FileListParams.none(), requestOptions)

    /** List all files from the session in descending order. */
    fun list(params: FileListParams, requestOptions: RequestOptions): CompletableFuture<Fileslist>

    /** @see list */
    fun list(params: FileListParams): CompletableFuture<Fileslist> =
        list(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        path: String,
        params: FileDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> = delete(params.toBuilder().path(path).build(), requestOptions)

    /** @see delete */
    fun delete(path: String, params: FileDeleteParams): CompletableFuture<Void?> =
        delete(path, params, RequestOptions.none())

    /** Delete a file from a session */
    fun delete(params: FileDeleteParams, requestOptions: RequestOptions): CompletableFuture<Void?>

    /** @see delete */
    fun delete(params: FileDeleteParams): CompletableFuture<Void?> =
        delete(params, RequestOptions.none())

    /** @see deleteAll */
    fun deleteAll(
        sessionId: String,
        params: FileDeleteAllParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> =
        deleteAll(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see deleteAll */
    fun deleteAll(sessionId: String, params: FileDeleteAllParams): CompletableFuture<Void?> =
        deleteAll(sessionId, params, RequestOptions.none())

    /** @see deleteAll */
    fun deleteAll(sessionId: String): CompletableFuture<Void?> =
        deleteAll(sessionId, FileDeleteAllParams.none())

    /** @see deleteAll */
    fun deleteAll(sessionId: String, requestOptions: RequestOptions): CompletableFuture<Void?> =
        deleteAll(sessionId, FileDeleteAllParams.none(), requestOptions)

    /** Delete all files from a session */
    fun deleteAll(
        params: FileDeleteAllParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?>

    /** @see deleteAll */
    fun deleteAll(params: FileDeleteAllParams): CompletableFuture<Void?> =
        deleteAll(params, RequestOptions.none())

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

    /** Download a file from a session */
    fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse>

    /** @see download */
    fun download(params: FileDownloadParams): CompletableFuture<HttpResponse> =
        download(params, RequestOptions.none())

    /** @see downloadArchive */
    fun downloadArchive(
        sessionId: String,
        params: FileDownloadArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        downloadArchive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see downloadArchive */
    fun downloadArchive(
        sessionId: String,
        params: FileDownloadArchiveParams,
    ): CompletableFuture<HttpResponse> = downloadArchive(sessionId, params, RequestOptions.none())

    /** @see downloadArchive */
    fun downloadArchive(sessionId: String): CompletableFuture<HttpResponse> =
        downloadArchive(sessionId, FileDownloadArchiveParams.none())

    /** @see downloadArchive */
    fun downloadArchive(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        downloadArchive(sessionId, FileDownloadArchiveParams.none(), requestOptions)

    /** Download all files from the session as a zip archive. */
    fun downloadArchive(
        params: FileDownloadArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse>

    /** @see downloadArchive */
    fun downloadArchive(params: FileDownloadArchiveParams): CompletableFuture<HttpResponse> =
        downloadArchive(params, RequestOptions.none())

    /** @see upload */
    fun upload(
        sessionId: String,
        params: FileUploadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<File> =
        upload(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see upload */
    fun upload(sessionId: String, params: FileUploadParams): CompletableFuture<File> =
        upload(sessionId, params, RequestOptions.none())

    /**
     * Uploads a file to a session via `multipart/form-data` with a `file` field that accepts either
     * binary data or a URL string to download from, and an optional `path` field for the file
     * storage path.
     */
    fun upload(params: FileUploadParams, requestOptions: RequestOptions): CompletableFuture<File>

    /** @see upload */
    fun upload(params: FileUploadParams): CompletableFuture<File> =
        upload(params, RequestOptions.none())

    /** A view of [FileServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync.WithRawResponse

        /** @see list */
        fun list(
            sessionId: String,
            params: FileListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Fileslist>> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        fun list(
            sessionId: String,
            params: FileListParams,
        ): CompletableFuture<HttpResponseFor<Fileslist>> =
            list(sessionId, params, RequestOptions.none())

        /** @see list */
        fun list(sessionId: String): CompletableFuture<HttpResponseFor<Fileslist>> =
            list(sessionId, FileListParams.none())

        /** @see list */
        fun list(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Fileslist>> =
            list(sessionId, FileListParams.none(), requestOptions)

        /** List all files from the session in descending order. */
        fun list(
            params: FileListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Fileslist>>

        /** @see list */
        fun list(params: FileListParams): CompletableFuture<HttpResponseFor<Fileslist>> =
            list(params, RequestOptions.none())

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

        /** Delete a file from a session */
        fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see delete */
        fun delete(params: FileDeleteParams): CompletableFuture<HttpResponse> =
            delete(params, RequestOptions.none())

        /** @see deleteAll */
        fun deleteAll(
            sessionId: String,
            params: FileDeleteAllParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            deleteAll(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see deleteAll */
        fun deleteAll(
            sessionId: String,
            params: FileDeleteAllParams,
        ): CompletableFuture<HttpResponse> = deleteAll(sessionId, params, RequestOptions.none())

        /** @see deleteAll */
        fun deleteAll(sessionId: String): CompletableFuture<HttpResponse> =
            deleteAll(sessionId, FileDeleteAllParams.none())

        /** @see deleteAll */
        fun deleteAll(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            deleteAll(sessionId, FileDeleteAllParams.none(), requestOptions)

        /** Delete all files from a session */
        fun deleteAll(
            params: FileDeleteAllParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see deleteAll */
        fun deleteAll(params: FileDeleteAllParams): CompletableFuture<HttpResponse> =
            deleteAll(params, RequestOptions.none())

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

        /** Download a file from a session */
        fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see download */
        fun download(params: FileDownloadParams): CompletableFuture<HttpResponse> =
            download(params, RequestOptions.none())

        /** @see downloadArchive */
        fun downloadArchive(
            sessionId: String,
            params: FileDownloadArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            downloadArchive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see downloadArchive */
        fun downloadArchive(
            sessionId: String,
            params: FileDownloadArchiveParams,
        ): CompletableFuture<HttpResponse> =
            downloadArchive(sessionId, params, RequestOptions.none())

        /** @see downloadArchive */
        fun downloadArchive(sessionId: String): CompletableFuture<HttpResponse> =
            downloadArchive(sessionId, FileDownloadArchiveParams.none())

        /** @see downloadArchive */
        fun downloadArchive(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            downloadArchive(sessionId, FileDownloadArchiveParams.none(), requestOptions)

        /** Download all files from the session as a zip archive. */
        fun downloadArchive(
            params: FileDownloadArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse>

        /** @see downloadArchive */
        fun downloadArchive(params: FileDownloadArchiveParams): CompletableFuture<HttpResponse> =
            downloadArchive(params, RequestOptions.none())

        /** @see upload */
        fun upload(
            sessionId: String,
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<File>> =
            upload(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see upload */
        fun upload(
            sessionId: String,
            params: FileUploadParams,
        ): CompletableFuture<HttpResponseFor<File>> =
            upload(sessionId, params, RequestOptions.none())

        /**
         * Uploads a file to a session via `multipart/form-data` with a `file` field that accepts
         * either binary data or a URL string to download from, and an optional `path` field for the
         * file storage path.
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
