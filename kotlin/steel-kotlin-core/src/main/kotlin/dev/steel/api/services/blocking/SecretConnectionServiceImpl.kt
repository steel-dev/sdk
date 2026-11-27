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
import dev.steel.api.core.http.json
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepare
import dev.steel.api.models.secretconnections.SecretConnectionCreateParams
import dev.steel.api.models.secretconnections.SecretConnectionCreateResponse
import dev.steel.api.models.secretconnections.SecretConnectionDeleteParams
import dev.steel.api.models.secretconnections.SecretConnectionListParams
import dev.steel.api.models.secretconnections.SecretConnectionListResponse
import dev.steel.api.models.secretconnections.SecretConnectionRetrieveParams
import dev.steel.api.models.secretconnections.SecretConnectionRetrieveResponse
import dev.steel.api.models.secretconnections.SecretConnectionTestParams
import dev.steel.api.models.secretconnections.SecretConnectionUpdateParams
import dev.steel.api.models.secretconnections.SecretConnectionUpdateResponse
import java.util.function.Consumer

class SecretConnectionServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    SecretConnectionService {

    private val withRawResponse: SecretConnectionService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): SecretConnectionService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SecretConnectionService =
        SecretConnectionServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: SecretConnectionCreateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionCreateResponse = withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): SecretConnectionRetrieveResponse = withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): SecretConnectionUpdateResponse = withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: SecretConnectionListParams,
        requestOptions: RequestOptions,
    ): SecretConnectionListResponse = withRawResponse().list(params, requestOptions).parse()

    override fun delete(params: SecretConnectionDeleteParams, requestOptions: RequestOptions) {
        withRawResponse().delete(params, requestOptions).close()
    }

    override fun test(params: SecretConnectionTestParams, requestOptions: RequestOptions) {
        withRawResponse().test(params, requestOptions).close()
    }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SecretConnectionService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SecretConnectionService.WithRawResponse =
            SecretConnectionServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<SecretConnectionCreateResponse> =
            jsonHandler<SecretConnectionCreateResponse>(clientOptions.jsonMapper)

        override fun create(
            params: SecretConnectionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
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

        private val retrieveHandler: Handler<SecretConnectionRetrieveResponse> =
            jsonHandler<SecretConnectionRetrieveResponse>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionRetrieveResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val updateHandler: Handler<SecretConnectionUpdateResponse> =
            jsonHandler<SecretConnectionUpdateResponse>(clientOptions.jsonMapper)

        override fun update(
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionUpdateResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0))
                    .body(json(clientOptions.jsonMapper, params._body()))
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

        private val listHandler: Handler<SecretConnectionListResponse> =
            jsonHandler<SecretConnectionListResponse>(clientOptions.jsonMapper)

        override fun list(
            params: SecretConnectionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SecretConnectionListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections")
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
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }

        override fun test(
            params: SecretConnectionTestParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0), "test")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response)
        }
    }
}
