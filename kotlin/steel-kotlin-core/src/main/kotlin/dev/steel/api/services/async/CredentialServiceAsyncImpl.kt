package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.handlers.errorBodyHandler
import dev.steel.api.core.handlers.errorHandler
import dev.steel.api.core.handlers.jsonHandler
import dev.steel.api.core.http.HttpMethod
import dev.steel.api.core.http.HttpRequest
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponse.Handler
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.core.http.json
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepareAsync
import dev.steel.api.models.credentials.CredentialCreateParams
import dev.steel.api.models.credentials.CredentialCreateResponse
import dev.steel.api.models.credentials.CredentialDeleteParams
import dev.steel.api.models.credentials.CredentialDeleteResponse
import dev.steel.api.models.credentials.CredentialListParams
import dev.steel.api.models.credentials.CredentialListResponse
import dev.steel.api.models.credentials.CredentialUpdateParams
import dev.steel.api.models.credentials.CredentialUpdateResponse
import java.util.function.Consumer

class CredentialServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CredentialServiceAsync {

    private val withRawResponse: CredentialServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CredentialServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialServiceAsync =
        CredentialServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun create(
        params: CredentialCreateParams,
        requestOptions: RequestOptions,
    ): CredentialCreateResponse = withRawResponse().create(params, requestOptions).parse()

    override suspend fun update(
        params: CredentialUpdateParams,
        requestOptions: RequestOptions,
    ): CredentialUpdateResponse = withRawResponse().update(params, requestOptions).parse()

    override suspend fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): CredentialListResponse = withRawResponse().list(params, requestOptions).parse()

    override suspend fun delete(
        params: CredentialDeleteParams,
        requestOptions: RequestOptions,
    ): CredentialDeleteResponse = withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CredentialServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CredentialServiceAsync.WithRawResponse =
            CredentialServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<CredentialCreateResponse> =
            jsonHandler<CredentialCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: CredentialCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "credentials")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val updateHandler: Handler<CredentialUpdateResponse> =
            jsonHandler<CredentialUpdateResponse>(clientOptions.jsonMapper)

        override suspend fun update(
            params: CredentialUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialUpdateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PUT)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "credentials")
                    .body(json(clientOptions.jsonMapper, params._body()))
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

        private val listHandler: Handler<CredentialListResponse> =
            jsonHandler<CredentialListResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "credentials")
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

        private val deleteHandler: Handler<CredentialDeleteResponse> =
            jsonHandler<CredentialDeleteResponse>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: CredentialDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialDeleteResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "credentials")
                    .body(json(clientOptions.jsonMapper, params._body()))
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
    }
}
