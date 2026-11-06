package dev.steel.api.services.async

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
import dev.steel.api.models.files.FileDeleteParams
import dev.steel.api.models.files.FileDownloadParams
import dev.steel.api.models.files.FileListParams
import dev.steel.api.models.files.FileUploadParams
import dev.steel.api.models.files.Fileslist
import java.util.function.Consumer

class FileServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    FileServiceAsync {

    private val withRawResponse: FileServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): FileServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync =
        FileServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun list(params: FileListParams, requestOptions: RequestOptions): Fileslist =
        withRawResponse().list(params, requestOptions).parse()

    override suspend fun delete(params: FileDeleteParams, requestOptions: RequestOptions) {
        withRawResponse().delete(params, requestOptions).close()
    }

    override suspend fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse = withRawResponse().download(params, requestOptions)

    override suspend fun upload(params: FileUploadParams, requestOptions: RequestOptions): File =
        withRawResponse().upload(params, requestOptions).parse()

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

        override suspend fun list(
            params: FileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Fileslist> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
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

        override suspend fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            checkRequired("path", params.path())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response)
        }

        override suspend fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            checkRequired("path", params.path())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files", params._pathParam(0))
                    .putHeader("Accept", "application/octet-stream")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response)
        }

        private val uploadHandler: Handler<File> = jsonHandler<File>(clientOptions.jsonMapper)

        override suspend fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<File> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files")
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
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
