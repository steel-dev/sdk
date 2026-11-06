package dev.steel.api.services.blocking.sessions

import com.google.errorprone.annotations.MustBeClosed
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
import java.util.function.Consumer

interface FileService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileService

    /** @see list */
    fun list(sessionId: String, params: FileListParams, requestOptions: RequestOptions): Fileslist =
        list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(sessionId: String, params: FileListParams): Fileslist =
        list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(sessionId: String): Fileslist = list(sessionId, FileListParams.none())

    /** @see list */
    fun list(sessionId: String, requestOptions: RequestOptions): Fileslist =
        list(sessionId, FileListParams.none(), requestOptions)

    /** List all files from the session in descending order. */
    fun list(params: FileListParams, requestOptions: RequestOptions): Fileslist

    /** @see list */
    fun list(params: FileListParams): Fileslist = list(params, RequestOptions.none())

    /** @see delete */
    fun delete(path: String, params: FileDeleteParams, requestOptions: RequestOptions) =
        delete(params.toBuilder().path(path).build(), requestOptions)

    /** @see delete */
    fun delete(path: String, params: FileDeleteParams) = delete(path, params, RequestOptions.none())

    /** Delete a file from a session */
    fun delete(params: FileDeleteParams, requestOptions: RequestOptions)

    /** @see delete */
    fun delete(params: FileDeleteParams) = delete(params, RequestOptions.none())

    /** @see deleteAll */
    fun deleteAll(sessionId: String, params: FileDeleteAllParams, requestOptions: RequestOptions) =
        deleteAll(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see deleteAll */
    fun deleteAll(sessionId: String, params: FileDeleteAllParams) =
        deleteAll(sessionId, params, RequestOptions.none())

    /** @see deleteAll */
    fun deleteAll(sessionId: String) = deleteAll(sessionId, FileDeleteAllParams.none())

    /** @see deleteAll */
    fun deleteAll(sessionId: String, requestOptions: RequestOptions) =
        deleteAll(sessionId, FileDeleteAllParams.none(), requestOptions)

    /** Delete all files from a session */
    fun deleteAll(params: FileDeleteAllParams, requestOptions: RequestOptions)

    /** @see deleteAll */
    fun deleteAll(params: FileDeleteAllParams) = deleteAll(params, RequestOptions.none())

    /** @see download */
    @MustBeClosed
    fun download(
        path: String,
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse = download(params.toBuilder().path(path).build(), requestOptions)

    /** @see download */
    @MustBeClosed
    fun download(path: String, params: FileDownloadParams): HttpResponse =
        download(path, params, RequestOptions.none())

    /** Download a file from a session */
    @MustBeClosed
    fun download(params: FileDownloadParams, requestOptions: RequestOptions): HttpResponse

    /** @see download */
    @MustBeClosed
    fun download(params: FileDownloadParams): HttpResponse = download(params, RequestOptions.none())

    /** @see downloadArchive */
    @MustBeClosed
    fun downloadArchive(
        sessionId: String,
        params: FileDownloadArchiveParams,
        requestOptions: RequestOptions,
    ): HttpResponse =
        downloadArchive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see downloadArchive */
    @MustBeClosed
    fun downloadArchive(sessionId: String, params: FileDownloadArchiveParams): HttpResponse =
        downloadArchive(sessionId, params, RequestOptions.none())

    /** @see downloadArchive */
    @MustBeClosed
    fun downloadArchive(sessionId: String): HttpResponse =
        downloadArchive(sessionId, FileDownloadArchiveParams.none())

    /** @see downloadArchive */
    @MustBeClosed
    fun downloadArchive(sessionId: String, requestOptions: RequestOptions): HttpResponse =
        downloadArchive(sessionId, FileDownloadArchiveParams.none(), requestOptions)

    /** Download all files from the session as a zip archive. */
    @MustBeClosed
    fun downloadArchive(
        params: FileDownloadArchiveParams,
        requestOptions: RequestOptions,
    ): HttpResponse

    /** @see downloadArchive */
    @MustBeClosed
    fun downloadArchive(params: FileDownloadArchiveParams): HttpResponse =
        downloadArchive(params, RequestOptions.none())

    /** @see upload */
    fun upload(sessionId: String, params: FileUploadParams, requestOptions: RequestOptions): File =
        upload(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see upload */
    fun upload(sessionId: String, params: FileUploadParams): File =
        upload(sessionId, params, RequestOptions.none())

    /**
     * Uploads a file to a session via `multipart/form-data` with a `file` field that accepts either
     * binary data or a URL string to download from, and an optional `path` field for the file
     * storage path.
     */
    fun upload(params: FileUploadParams, requestOptions: RequestOptions): File

    /** @see upload */
    fun upload(params: FileUploadParams): File = upload(params, RequestOptions.none())

    /** A view of [FileService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileService.WithRawResponse

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            params: FileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Fileslist> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(sessionId: String, params: FileListParams): HttpResponseFor<Fileslist> =
            list(sessionId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(sessionId: String): HttpResponseFor<Fileslist> =
            list(sessionId, FileListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(sessionId: String, requestOptions: RequestOptions): HttpResponseFor<Fileslist> =
            list(sessionId, FileListParams.none(), requestOptions)

        /** List all files from the session in descending order. */
        @MustBeClosed
        fun list(params: FileListParams, requestOptions: RequestOptions): HttpResponseFor<Fileslist>

        /** @see list */
        @MustBeClosed
        fun list(params: FileListParams): HttpResponseFor<Fileslist> =
            list(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            path: String,
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse = delete(params.toBuilder().path(path).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(path: String, params: FileDeleteParams): HttpResponse =
            delete(path, params, RequestOptions.none())

        /** Delete a file from a session */
        @MustBeClosed
        fun delete(params: FileDeleteParams, requestOptions: RequestOptions): HttpResponse

        /** @see delete */
        @MustBeClosed
        fun delete(params: FileDeleteParams): HttpResponse = delete(params, RequestOptions.none())

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(
            sessionId: String,
            params: FileDeleteAllParams,
            requestOptions: RequestOptions,
        ): HttpResponse = deleteAll(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(sessionId: String, params: FileDeleteAllParams): HttpResponse =
            deleteAll(sessionId, params, RequestOptions.none())

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(sessionId: String): HttpResponse =
            deleteAll(sessionId, FileDeleteAllParams.none())

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(sessionId: String, requestOptions: RequestOptions): HttpResponse =
            deleteAll(sessionId, FileDeleteAllParams.none(), requestOptions)

        /** Delete all files from a session */
        @MustBeClosed
        fun deleteAll(params: FileDeleteAllParams, requestOptions: RequestOptions): HttpResponse

        /** @see deleteAll */
        @MustBeClosed
        fun deleteAll(params: FileDeleteAllParams): HttpResponse =
            deleteAll(params, RequestOptions.none())

        /** @see download */
        @MustBeClosed
        fun download(
            path: String,
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse = download(params.toBuilder().path(path).build(), requestOptions)

        /** @see download */
        @MustBeClosed
        fun download(path: String, params: FileDownloadParams): HttpResponse =
            download(path, params, RequestOptions.none())

        /** Download a file from a session */
        @MustBeClosed
        fun download(params: FileDownloadParams, requestOptions: RequestOptions): HttpResponse

        /** @see download */
        @MustBeClosed
        fun download(params: FileDownloadParams): HttpResponse =
            download(params, RequestOptions.none())

        /** @see downloadArchive */
        @MustBeClosed
        fun downloadArchive(
            sessionId: String,
            params: FileDownloadArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponse =
            downloadArchive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see downloadArchive */
        @MustBeClosed
        fun downloadArchive(sessionId: String, params: FileDownloadArchiveParams): HttpResponse =
            downloadArchive(sessionId, params, RequestOptions.none())

        /** @see downloadArchive */
        @MustBeClosed
        fun downloadArchive(sessionId: String): HttpResponse =
            downloadArchive(sessionId, FileDownloadArchiveParams.none())

        /** @see downloadArchive */
        @MustBeClosed
        fun downloadArchive(sessionId: String, requestOptions: RequestOptions): HttpResponse =
            downloadArchive(sessionId, FileDownloadArchiveParams.none(), requestOptions)

        /** Download all files from the session as a zip archive. */
        @MustBeClosed
        fun downloadArchive(
            params: FileDownloadArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponse

        /** @see downloadArchive */
        @MustBeClosed
        fun downloadArchive(params: FileDownloadArchiveParams): HttpResponse =
            downloadArchive(params, RequestOptions.none())

        /** @see upload */
        @MustBeClosed
        fun upload(
            sessionId: String,
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<File> =
            upload(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see upload */
        @MustBeClosed
        fun upload(sessionId: String, params: FileUploadParams): HttpResponseFor<File> =
            upload(sessionId, params, RequestOptions.none())

        /**
         * Uploads a file to a session via `multipart/form-data` with a `file` field that accepts
         * either binary data or a URL string to download from, and an optional `path` field for the
         * file storage path.
         */
        @MustBeClosed
        fun upload(params: FileUploadParams, requestOptions: RequestOptions): HttpResponseFor<File>

        /** @see upload */
        @MustBeClosed
        fun upload(params: FileUploadParams): HttpResponseFor<File> =
            upload(params, RequestOptions.none())
    }
}
