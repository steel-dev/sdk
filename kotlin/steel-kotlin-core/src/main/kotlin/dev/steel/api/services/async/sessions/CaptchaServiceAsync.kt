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
    suspend fun solve(
        sessionId: String,
        params: CaptchaSolveParams,
        requestOptions: RequestOptions,
    ): CaptchaSolveResponse = solve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see solve */
    suspend fun solve(sessionId: String, params: CaptchaSolveParams): CaptchaSolveResponse =
        solve(sessionId, params, RequestOptions.none())

    /** @see solve */
    suspend fun solve(sessionId: String): CaptchaSolveResponse =
        solve(sessionId, CaptchaSolveParams.none())

    /** @see solve */
    suspend fun solve(sessionId: String, requestOptions: RequestOptions): CaptchaSolveResponse =
        solve(sessionId, CaptchaSolveParams.none(), requestOptions)

    /**
     * Solves captcha(s) for the session. If pageId, url, or taskId is provided, solves that
     * specific captcha. If no parameters are provided, solves all detected captchas. Use this when
     * autoCaptchaSolving is disabled in stealthConfig.
     */
    suspend fun solve(
        params: CaptchaSolveParams,
        requestOptions: RequestOptions,
    ): CaptchaSolveResponse

    /** @see solve */
    suspend fun solve(params: CaptchaSolveParams): CaptchaSolveResponse =
        solve(params, RequestOptions.none())

    /** @see solveImage */
    suspend fun solveImage(
        sessionId: String,
        params: CaptchaSolveImageParams,
        requestOptions: RequestOptions,
    ): CaptchaSolveImageResponse =
        solveImage(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see solveImage */
    suspend fun solveImage(
        sessionId: String,
        params: CaptchaSolveImageParams,
    ): CaptchaSolveImageResponse = solveImage(sessionId, params, RequestOptions.none())

    /** Solves an image captcha using XPath selectors */
    suspend fun solveImage(
        params: CaptchaSolveImageParams,
        requestOptions: RequestOptions,
    ): CaptchaSolveImageResponse

    /** @see solveImage */
    suspend fun solveImage(params: CaptchaSolveImageParams): CaptchaSolveImageResponse =
        solveImage(params, RequestOptions.none())

    /** @see status */
    suspend fun status(
        sessionId: String,
        params: CaptchaStatusParams,
        requestOptions: RequestOptions,
    ): List<CaptchaStatusResponse> =
        status(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see status */
    suspend fun status(
        sessionId: String,
        params: CaptchaStatusParams,
    ): List<CaptchaStatusResponse> = status(sessionId, params, RequestOptions.none())

    /** @see status */
    suspend fun status(sessionId: String): List<CaptchaStatusResponse> =
        status(sessionId, CaptchaStatusParams.none())

    /** @see status */
    suspend fun status(
        sessionId: String,
        requestOptions: RequestOptions,
    ): List<CaptchaStatusResponse> = status(sessionId, CaptchaStatusParams.none(), requestOptions)

    /** Gets the current captcha status for a session */
    suspend fun status(
        params: CaptchaStatusParams,
        requestOptions: RequestOptions,
    ): List<CaptchaStatusResponse>

    /** @see status */
    suspend fun status(params: CaptchaStatusParams): List<CaptchaStatusResponse> =
        status(params, RequestOptions.none())

    /**
     * A view of [CaptchaServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CaptchaServiceAsync.WithRawResponse

        /** @see solve */
        suspend fun solve(
            sessionId: String,
            params: CaptchaSolveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaptchaSolveResponse> =
            solve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see solve */
        suspend fun solve(
            sessionId: String,
            params: CaptchaSolveParams,
        ): HttpResponseFor<CaptchaSolveResponse> = solve(sessionId, params, RequestOptions.none())

        /** @see solve */
        suspend fun solve(sessionId: String): HttpResponseFor<CaptchaSolveResponse> =
            solve(sessionId, CaptchaSolveParams.none())

        /** @see solve */
        suspend fun solve(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaptchaSolveResponse> =
            solve(sessionId, CaptchaSolveParams.none(), requestOptions)

        /**
         * Solves captcha(s) for the session. If pageId, url, or taskId is provided, solves that
         * specific captcha. If no parameters are provided, solves all detected captchas. Use this
         * when autoCaptchaSolving is disabled in stealthConfig.
         */
        suspend fun solve(
            params: CaptchaSolveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaptchaSolveResponse>

        /** @see solve */
        suspend fun solve(params: CaptchaSolveParams): HttpResponseFor<CaptchaSolveResponse> =
            solve(params, RequestOptions.none())

        /** @see solveImage */
        suspend fun solveImage(
            sessionId: String,
            params: CaptchaSolveImageParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaptchaSolveImageResponse> =
            solveImage(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see solveImage */
        suspend fun solveImage(
            sessionId: String,
            params: CaptchaSolveImageParams,
        ): HttpResponseFor<CaptchaSolveImageResponse> =
            solveImage(sessionId, params, RequestOptions.none())

        /** Solves an image captcha using XPath selectors */
        suspend fun solveImage(
            params: CaptchaSolveImageParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaptchaSolveImageResponse>

        /** @see solveImage */
        suspend fun solveImage(
            params: CaptchaSolveImageParams
        ): HttpResponseFor<CaptchaSolveImageResponse> = solveImage(params, RequestOptions.none())

        /** @see status */
        suspend fun status(
            sessionId: String,
            params: CaptchaStatusParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<List<CaptchaStatusResponse>> =
            status(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see status */
        suspend fun status(
            sessionId: String,
            params: CaptchaStatusParams,
        ): HttpResponseFor<List<CaptchaStatusResponse>> =
            status(sessionId, params, RequestOptions.none())

        /** @see status */
        suspend fun status(sessionId: String): HttpResponseFor<List<CaptchaStatusResponse>> =
            status(sessionId, CaptchaStatusParams.none())

        /** @see status */
        suspend fun status(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<List<CaptchaStatusResponse>> =
            status(sessionId, CaptchaStatusParams.none(), requestOptions)

        /** Gets the current captcha status for a session */
        suspend fun status(
            params: CaptchaStatusParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<List<CaptchaStatusResponse>>

        /** @see status */
        suspend fun status(
            params: CaptchaStatusParams
        ): HttpResponseFor<List<CaptchaStatusResponse>> = status(params, RequestOptions.none())
    }
}
