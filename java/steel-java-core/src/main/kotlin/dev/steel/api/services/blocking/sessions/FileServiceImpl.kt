package dev.steel.api.services.blocking.sessions

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
import dev.steel.api.core.prepare
import dev.steel.api.models.files.File
import dev.steel.api.models.files.Fileslist
import dev.steel.api.models.sessions.files.FileDeleteAllParams
import dev.steel.api.models.sessions.files.FileDeleteParams
import dev.steel.api.models.sessions.files.FileDownloadArchiveParams
import dev.steel.api.models.sessions.files.FileDownloadParams
import dev.steel.api.models.sessions.files.FileListParams
import dev.steel.api.models.sessions.files.FileUploadParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class FileServiceImpl internal constructor(private val clientOptions: ClientOptions) : FileService {

    private val withRawResponse: FileService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): FileService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileService =
        FileServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(params: FileListParams, requestOptions: RequestOptions): Fileslist =
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(params: FileDeleteParams, requestOptions: RequestOptions) {
        withRawResponse().delete(params, requestOptions).close()
    }

    override fun deleteAll(params: FileDeleteAllParams, requestOptions: RequestOptions) {
        withRawResponse().deleteAll(params, requestOptions).close()
    }

    override fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse = withRawResponse().download(params, requestOptions)

    override fun downloadArchive(
        params: FileDownloadArchiveParams,
        requestOptions: RequestOptions,
    ): HttpResponse = withRawResponse().downloadArchive(params, requestOptions)

    override fun upload(params: FileUploadParams, requestOptions: RequestOptions): File =
        withRawResponse().upload(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        FileService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): FileService.WithRawResponse =
            FileServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<Fileslist> =
            jsonHandler<Fileslist>(clientOptions.jsonMapper)

        override fun list(
            params: FileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Fileslist> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        override fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
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
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }

        override fun deleteAll(
            params: FileDeleteAllParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }

        override fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
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
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }

        override fun downloadArchive(
            params: FileDownloadArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files.zip")
                    .putHeader("Accept", "application/zip")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }

        private val uploadHandler: Handler<File> = jsonHandler<File>(clientOptions.jsonMapper)

        override fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<File> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "files")
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
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
