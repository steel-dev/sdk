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
import dev.steel.api.core.http.json
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepareAsync
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
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SecretConnectionServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) : SecretConnectionServiceAsync {

    private val withRawResponse: SecretConnectionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): SecretConnectionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): SecretConnectionServiceAsync =
        SecretConnectionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: SecretConnectionCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionCreateResponse> =
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: SecretConnectionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionRetrieveResponse> =
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: SecretConnectionUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionUpdateResponse> =
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: SecretConnectionListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SecretConnectionListResponse> =
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: SecretConnectionDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> =
        withRawResponse().delete(params, requestOptions).thenAccept { it.close() }

    override fun test(
        params: SecretConnectionTestParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Void?> =
        withRawResponse().test(params, requestOptions).thenAccept { it.close() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SecretConnectionServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SecretConnectionServiceAsync.WithRawResponse =
            SecretConnectionServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<SecretConnectionCreateResponse> =
            jsonHandler<SecretConnectionCreateResponse>(clientOptions.jsonMapper)

        override fun create(
            params: SecretConnectionCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionCreateResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val retrieveHandler: Handler<SecretConnectionRetrieveResponse> =
            jsonHandler<SecretConnectionRetrieveResponse>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SecretConnectionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionRetrieveResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val updateHandler: Handler<SecretConnectionUpdateResponse> =
            jsonHandler<SecretConnectionUpdateResponse>(clientOptions.jsonMapper)

        override fun update(
            params: SecretConnectionUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionUpdateResponse>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0))
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { updateHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<SecretConnectionListResponse> =
            jsonHandler<SecretConnectionListResponse>(clientOptions.jsonMapper)

        override fun list(
            params: SecretConnectionListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SecretConnectionListResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections")
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
            params: SecretConnectionDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response -> errorHandler.handle(response) }
        }

        override fun test(
            params: SecretConnectionTestParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "secret-connections", params._pathParam(0), "test")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response -> errorHandler.handle(response) }
        }
    }
}
