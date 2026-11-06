package dev.steel.api.services.blocking

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

class ExtensionServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ExtensionService {

    private val withRawResponse: ExtensionService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ExtensionService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExtensionService =
        ExtensionServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun update(
        params: ExtensionUpdateParams,
        requestOptions: RequestOptions,
    ): ExtensionUpdateResponse = withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: ExtensionListParams,
        requestOptions: RequestOptions,
    ): ExtensionListResponse = withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: ExtensionDeleteParams,
        requestOptions: RequestOptions,
    ): ExtensionDeleteResponse = withRawResponse().delete(params, requestOptions).parse()

    override fun deleteAll(
        params: ExtensionDeleteAllParams,
        requestOptions: RequestOptions,
    ): ExtensionDeleteAllResponse = withRawResponse().deleteAll(params, requestOptions).parse()

    override fun download(
        params: ExtensionDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse = withRawResponse().download(params, requestOptions)

    override fun upload(
        params: ExtensionUploadParams,
        requestOptions: RequestOptions,
    ): ExtensionUploadResponse = withRawResponse().upload(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ExtensionService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ExtensionService.WithRawResponse =
            ExtensionServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val updateHandler: Handler<ExtensionUpdateResponse> =
            jsonHandler<ExtensionUpdateResponse>(clientOptions.jsonMapper)

        override fun update(
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
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
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

        override fun list(
            params: ExtensionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions")
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

        private val deleteHandler: Handler<ExtensionDeleteResponse> =
            jsonHandler<ExtensionDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
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
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
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

        override fun deleteAll(
            params: ExtensionDeleteAllParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtensionDeleteAllResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "extensions")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
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

        override fun download(
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
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }

        private val uploadHandler: Handler<ExtensionUploadResponse> =
            jsonHandler<ExtensionUploadResponse>(clientOptions.jsonMapper)

        override fun upload(
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
