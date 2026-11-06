package dev.steel.api.services.async.sessions

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveImageResponse
import dev.steel.api.models.sessions.captchas.CaptchaSolveParams
import dev.steel.api.models.sessions.captchas.CaptchaSolveResponse
import dev.steel.api.models.sessions.captchas.CaptchaStatusParams
import dev.steel.api.models.sessions.captchas.CaptchaStatusResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CaptchaServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CaptchaServiceAsync

    /** @see solve */
    fun solve(
        sessionId: String,
        params: CaptchaSolveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaptchaSolveResponse> =
        solve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see solve */
    fun solve(
        sessionId: String,
        params: CaptchaSolveParams,
    ): CompletableFuture<CaptchaSolveResponse> = solve(sessionId, params, RequestOptions.none())

    /** @see solve */
    fun solve(sessionId: String): CompletableFuture<CaptchaSolveResponse> =
        solve(sessionId, CaptchaSolveParams.none())

    /** @see solve */
    fun solve(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaptchaSolveResponse> =
        solve(sessionId, CaptchaSolveParams.none(), requestOptions)

    /**
     * Solves captcha(s) for the session. If pageId, url, or taskId is provided, solves that
     * specific captcha. If no parameters are provided, solves all detected captchas. Use this when
     * autoCaptchaSolving is disabled in stealthConfig.
     */
    fun solve(
        params: CaptchaSolveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaptchaSolveResponse>

    /** @see solve */
    fun solve(params: CaptchaSolveParams): CompletableFuture<CaptchaSolveResponse> =
        solve(params, RequestOptions.none())

    /** @see solveImage */
    fun solveImage(
        sessionId: String,
        params: CaptchaSolveImageParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaptchaSolveImageResponse> =
        solveImage(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see solveImage */
    fun solveImage(
        sessionId: String,
        params: CaptchaSolveImageParams,
    ): CompletableFuture<CaptchaSolveImageResponse> =
        solveImage(sessionId, params, RequestOptions.none())

    /** Solves an image captcha using XPath selectors */
    fun solveImage(
        params: CaptchaSolveImageParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaptchaSolveImageResponse>

    /** @see solveImage */
    fun solveImage(params: CaptchaSolveImageParams): CompletableFuture<CaptchaSolveImageResponse> =
        solveImage(params, RequestOptions.none())

    /** @see status */
    fun status(
        sessionId: String,
        params: CaptchaStatusParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<List<CaptchaStatusResponse>> =
        status(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see status */
    fun status(
        sessionId: String,
        params: CaptchaStatusParams,
    ): CompletableFuture<List<CaptchaStatusResponse>> =
        status(sessionId, params, RequestOptions.none())

    /** @see status */
    fun status(sessionId: String): CompletableFuture<List<CaptchaStatusResponse>> =
        status(sessionId, CaptchaStatusParams.none())

    /** @see status */
    fun status(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<List<CaptchaStatusResponse>> =
        status(sessionId, CaptchaStatusParams.none(), requestOptions)

    /** Gets the current captcha status for a session */
    fun status(
        params: CaptchaStatusParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<List<CaptchaStatusResponse>>

    /** @see status */
    fun status(params: CaptchaStatusParams): CompletableFuture<List<CaptchaStatusResponse>> =
        status(params, RequestOptions.none())

    /**
     * A view of [CaptchaServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CaptchaServiceAsync.WithRawResponse

        /** @see solve */
        fun solve(
            sessionId: String,
            params: CaptchaSolveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveResponse>> =
            solve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see solve */
        fun solve(
            sessionId: String,
            params: CaptchaSolveParams,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveResponse>> =
            solve(sessionId, params, RequestOptions.none())

        /** @see solve */
        fun solve(sessionId: String): CompletableFuture<HttpResponseFor<CaptchaSolveResponse>> =
            solve(sessionId, CaptchaSolveParams.none())

        /** @see solve */
        fun solve(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveResponse>> =
            solve(sessionId, CaptchaSolveParams.none(), requestOptions)

        /**
         * Solves captcha(s) for the session. If pageId, url, or taskId is provided, solves that
         * specific captcha. If no parameters are provided, solves all detected captchas. Use this
         * when autoCaptchaSolving is disabled in stealthConfig.
         */
        fun solve(
            params: CaptchaSolveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveResponse>>

        /** @see solve */
        fun solve(
            params: CaptchaSolveParams
        ): CompletableFuture<HttpResponseFor<CaptchaSolveResponse>> =
            solve(params, RequestOptions.none())

        /** @see solveImage */
        fun solveImage(
            sessionId: String,
            params: CaptchaSolveImageParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveImageResponse>> =
            solveImage(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see solveImage */
        fun solveImage(
            sessionId: String,
            params: CaptchaSolveImageParams,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveImageResponse>> =
            solveImage(sessionId, params, RequestOptions.none())

        /** Solves an image captcha using XPath selectors */
        fun solveImage(
            params: CaptchaSolveImageParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaptchaSolveImageResponse>>

        /** @see solveImage */
        fun solveImage(
            params: CaptchaSolveImageParams
        ): CompletableFuture<HttpResponseFor<CaptchaSolveImageResponse>> =
            solveImage(params, RequestOptions.none())

        /** @see status */
        fun status(
            sessionId: String,
            params: CaptchaStatusParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<CaptchaStatusResponse>>> =
            status(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see status */
        fun status(
            sessionId: String,
            params: CaptchaStatusParams,
        ): CompletableFuture<HttpResponseFor<List<CaptchaStatusResponse>>> =
            status(sessionId, params, RequestOptions.none())

        /** @see status */
        fun status(
            sessionId: String
        ): CompletableFuture<HttpResponseFor<List<CaptchaStatusResponse>>> =
            status(sessionId, CaptchaStatusParams.none())

        /** @see status */
        fun status(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<CaptchaStatusResponse>>> =
            status(sessionId, CaptchaStatusParams.none(), requestOptions)

        /** Gets the current captcha status for a session */
        fun status(
            params: CaptchaStatusParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<List<CaptchaStatusResponse>>>

        /** @see status */
        fun status(
            params: CaptchaStatusParams
        ): CompletableFuture<HttpResponseFor<List<CaptchaStatusResponse>>> =
            status(params, RequestOptions.none())
    }
}
