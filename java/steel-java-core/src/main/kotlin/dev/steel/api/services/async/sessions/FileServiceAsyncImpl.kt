package dev.steel.api.services.async.sessions

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.checkRequired
import dev.steel.api.core.handlers.errorBodyHandler
import dev.steel.api.core.handlers.errorHandler
import dev.steel.api.core.handlers.jsonHandler
import dev.steel.api.core.http.HttpMethod
import dev.steel.api.core.http.HttpRequest
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponse.Handler
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.core.http.multipartFormData
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepareAsync
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
import kotlin.jvm.optionals.getOrNull

class FileServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    FileServiceAsync {

    private val withRawResponse: FileServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): FileServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync =
        FileServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: FileListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Fileslist> =
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: FileDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> =
        withRawResponse().delete(params, requestOptions).thenAccept { it.close() }

    override fun deleteAll(
        params: FileDeleteAllParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> =
        withRawResponse().deleteAll(params, requestOptions).thenAccept { it.close() }

    override fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> = withRawResponse().download(params, requestOptions)

    override fun downloadArchive(
        params: FileDownloadArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> = withRawResponse().downloadArchive(params, requestOptions)

    override fun upload(
        params: FileUploadParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<File> =
        withRawResponse().upload(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        FileServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): FileServiceAsync.WithRawResponse =
            FileServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<Fileslist> =
            jsonHandler<Fileslist>(clientOptions.jsonMapper)

        override fun list(
            params: FileListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Fileslist>> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        override fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            checkRequired("path", params.path().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "files",
                        params._pathParam(1),
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response -> errorHandler.handle(response) }
        }

        override fun deleteAll(
            params: FileDeleteAllParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response -> errorHandler.handle(response) }
        }

        override fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            checkRequired("path", params.path().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "files",
                        params._pathParam(1),
                    )
                    .putHeader("Accept", "application/octet-stream")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response -> errorHandler.handle(response) }
        }

        override fun downloadArchive(
            params: FileDownloadArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files.zip")
                    .putHeader("Accept", "application/zip")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response -> errorHandler.handle(response) }
        }

        private val uploadHandler: Handler<File> = jsonHandler<File>(clientOptions.jsonMapper)

        override fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<File>> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files")
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { uploadHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }
    }
}
