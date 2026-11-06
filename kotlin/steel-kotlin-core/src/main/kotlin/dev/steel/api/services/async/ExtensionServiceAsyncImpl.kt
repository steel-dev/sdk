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

class ExtensionServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ExtensionServiceAsync {

    private val withRawResponse: ExtensionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ExtensionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExtensionServiceAsync =
        ExtensionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun update(
        params: ExtensionUpdateParams,
        requestOptions: RequestOptions,
    ): ExtensionUpdateResponse = withRawResponse().update(params, requestOptions).parse()

    override suspend fun list(
        params: ExtensionListParams,
        requestOptions: RequestOptions,
    ): ExtensionListResponse = withRawResponse().list(params, requestOptions).parse()

    override suspend fun delete(
        params: ExtensionDeleteParams,
        requestOptions: RequestOptions,
    ): ExtensionDeleteResponse = withRawResponse().delete(params, requestOptions).parse()

    override suspend fun deleteAll(
        params: ExtensionDeleteAllParams,
        requestOptions: RequestOptions,
    ): ExtensionDeleteAllResponse = withRawResponse().deleteAll(params, requestOptions).parse()

    override suspend fun download(
        params: ExtensionDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse = withRawResponse().download(params, requestOptions)

    override suspend fun upload(
        params: ExtensionUploadParams,
        requestOptions: RequestOptions,
    ): ExtensionUploadResponse = withRawResponse().upload(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ExtensionServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ExtensionServiceAsync.WithRawResponse =
            ExtensionServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val updateHandler: Handler<ExtensionUpdateResponse> =
            jsonHandler<ExtensionUpdateResponse>(clientOptions.jsonMapper)

        override suspend fun update(
            params: ExtensionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionUpdateResponse> {
            checkRequired("extensionId", params.extensionId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PUT)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions", params._pathParam(0))
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<ExtensionListResponse> =
            jsonHandler<ExtensionListResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: ExtensionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions")
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

        private val deleteHandler: Handler<ExtensionDeleteResponse> =
            jsonHandler<ExtensionDeleteResponse>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: ExtensionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionDeleteResponse> {
            checkRequired("extensionId", params.extensionId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { deleteHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val deleteAllHandler: Handler<ExtensionDeleteAllResponse> =
            jsonHandler<ExtensionDeleteAllResponse>(clientOptions.jsonMapper)

        override suspend fun deleteAll(
            params: ExtensionDeleteAllParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionDeleteAllResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { deleteAllHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        override suspend fun download(
            params: ExtensionDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            checkRequired("extensionId", params.extensionId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions", params._pathParam(0))
                    .putHeader("Accept", "application/zip")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response)
        }

        private val uploadHandler: Handler<ExtensionUploadResponse> =
            jsonHandler<ExtensionUploadResponse>(clientOptions.jsonMapper)

        override suspend fun upload(
            params: ExtensionUploadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionUploadResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions")
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
