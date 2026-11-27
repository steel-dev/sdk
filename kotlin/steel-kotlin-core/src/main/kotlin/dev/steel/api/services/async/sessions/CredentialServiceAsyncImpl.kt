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
import dev.steel.api.core.http.json
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepareAsync
import dev.steel.api.models.sessions.SessionCredentials
import dev.steel.api.models.sessions.credentials.CredentialAttachParams
import dev.steel.api.models.sessions.credentials.CredentialDetachBindingParams
import dev.steel.api.models.sessions.credentials.CredentialDetachParams
import dev.steel.api.models.sessions.credentials.CredentialListParams
import dev.steel.api.models.sessions.credentials.CredentialReplaceParams
import java.util.function.Consumer

class CredentialServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CredentialServiceAsync {

    private val withRawResponse: CredentialServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CredentialServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialServiceAsync =
        CredentialServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = withRawResponse().list(params, requestOptions).parse()

    override suspend fun attach(
        params: CredentialAttachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = withRawResponse().attach(params, requestOptions).parse()

    override suspend fun detach(
        params: CredentialDetachParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = withRawResponse().detach(params, requestOptions).parse()

    override suspend fun detachBinding(
        params: CredentialDetachBindingParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = withRawResponse().detachBinding(params, requestOptions).parse()

    override suspend fun replace(
        params: CredentialReplaceParams,
        requestOptions: RequestOptions,
    ): SessionCredentials = withRawResponse().replace(params, requestOptions).parse()

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

        private val listHandler: Handler<SessionCredentials> =
            jsonHandler<SessionCredentials>(clientOptions.jsonMapper)

        override suspend fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "credentials")
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

        private val attachHandler: Handler<SessionCredentials> =
            jsonHandler<SessionCredentials>(clientOptions.jsonMapper)

        override suspend fun attach(
            params: CredentialAttachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "credentials")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { attachHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val detachHandler: Handler<SessionCredentials> =
            jsonHandler<SessionCredentials>(clientOptions.jsonMapper)

        override suspend fun detach(
            params: CredentialDetachParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> {
            checkRequired("credentialId", params.credentialId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "credentials",
                        params._pathParam(1),
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { detachHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val detachBindingHandler: Handler<SessionCredentials> =
            jsonHandler<SessionCredentials>(clientOptions.jsonMapper)

        override suspend fun detachBinding(
            params: CredentialDetachBindingParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "credentials",
                        "bindings",
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { detachBindingHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val replaceHandler: Handler<SessionCredentials> =
            jsonHandler<SessionCredentials>(clientOptions.jsonMapper)

        override suspend fun replace(
            params: CredentialReplaceParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SessionCredentials> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PUT)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "credentials")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { replaceHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
