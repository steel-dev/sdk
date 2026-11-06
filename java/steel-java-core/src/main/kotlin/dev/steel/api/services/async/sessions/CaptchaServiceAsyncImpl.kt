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
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageResponse
import dev.steel.api.models.sessions.captchas.CaptchaSolveParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveResponse
import dev.steel.api.models.sessions.captchas.CaptchaStatusParams
import dev.steel.api.models.sessions.captchas.CaptchaStatusResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class CaptchaServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CaptchaServiceAsync {

    private val withRawResponse: CaptchaServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CaptchaServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CaptchaServiceAsync =
        CaptchaServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun solve(
        params: CaptchaSolveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaptchaSolveResponse> =
        withRawResponse().solve(params, requestOptions).thenApply { it.parse() }

    override fun solveImage(
        params: CaptchaSolveImageParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaptchaSolveImageResponse> =
        withRawResponse().solveImage(params, requestOptions).thenApply { it.parse() }

    override fun status(
        params: CaptchaStatusParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<List<CaptchaStatusResponse>> =
        withRawResponse().status(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CaptchaServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CaptchaServiceAsync.WithRawResponse =
            CaptchaServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val solveHandler: Handler<CaptchaSolveResponse> =
            jsonHandler<CaptchaSolveResponse>(clientOptions.jsonMapper)

        override fun solve(
            params: CaptchaSolveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveResponse>> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "captchas", "solve")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { solveHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val solveImageHandler: Handler<CaptchaSolveImageResponse> =
            jsonHandler<CaptchaSolveImageResponse>(clientOptions.jsonMapper)

        override fun solveImage(
            params: CaptchaSolveImageParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveImageResponse>> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "captchas",
                        "solve-image",
                    )
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { solveImageHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val statusHandler: Handler<List<CaptchaStatusResponse>> =
            jsonHandler<List<CaptchaStatusResponse>>(clientOptions.jsonMapper)

        override fun status(
            params: CaptchaStatusParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<CaptchaStatusResponse>>> {
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "captchas", "status")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { statusHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.forEach { it.validate() }
                                }
                            }
                    }
                }
        }
    }
}
