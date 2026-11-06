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
import dev.steel.api.core.http.json
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepare
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageResponse
import dev.steel.api.models.sessions.captchas.CaptchaSolveParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveResponse
import dev.steel.api.models.sessions.captchas.CaptchaStatusParams
import dev.steel.api.models.sessions.captchas.CaptchaStatusResponse
import java.util.function.Consumer

class CaptchaServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    CaptchaService {

    private val withRawResponse: CaptchaService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CaptchaService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CaptchaService =
        CaptchaServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun solve(
        params: CaptchaSolveParams,
        requestOptions: RequestOptions,
    ): CaptchaSolveResponse = withRawResponse().solve(params, requestOptions).parse()

    override fun solveImage(
        params: CaptchaSolveImageParams,
        requestOptions: RequestOptions,
    ): CaptchaSolveImageResponse = withRawResponse().solveImage(params, requestOptions).parse()

    override fun status(
        params: CaptchaStatusParams,
        requestOptions: RequestOptions,
    ): List<CaptchaStatusResponse> = withRawResponse().status(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CaptchaService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CaptchaService.WithRawResponse =
            CaptchaServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val solveHandler: Handler<CaptchaSolveResponse> =
            jsonHandler<CaptchaSolveResponse>(clientOptions.jsonMapper)

        override fun solve(
            params: CaptchaSolveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaptchaSolveResponse> {
            checkRequired("sessionId", params.sessionId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "captchas", "solve")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { solveHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val solveImageHandler: Handler<CaptchaSolveImageResponse> =
            jsonHandler<CaptchaSolveImageResponse>(clientOptions.jsonMapper)

        override fun solveImage(
            params: CaptchaSolveImageParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaptchaSolveImageResponse> {
            checkRequired("sessionId", params.sessionId())
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
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { solveImageHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val statusHandler: Handler<List<CaptchaStatusResponse>> =
            jsonHandler<List<CaptchaStatusResponse>>(clientOptions.jsonMapper)

        override fun status(
            params: CaptchaStatusParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<List<CaptchaStatusResponse>> {
            checkRequired("sessionId", params.sessionId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "captchas", "status")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
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
