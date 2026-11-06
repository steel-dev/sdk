package dev.steel.api.client

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.ClientPdfParams
import dev.steel.api.models.ClientPdfResponse
import dev.steel.api.models.ClientScrapeParams
import dev.steel.api.models.ClientScrapeResponse
import dev.steel.api.models.ClientScreenshotParams
import dev.steel.api.models.ClientScreenshotResponse
import dev.steel.api.services.async.ApiKeyServiceAsync
import dev.steel.api.services.async.CredentialServiceAsync
import dev.steel.api.services.async.ExtensionServiceAsync
import dev.steel.api.services.async.FileServiceAsync
import dev.steel.api.services.async.ProfileServiceAsync
import dev.steel.api.services.async.ProjectServiceAsync
import dev.steel.api.services.async.SessionServiceAsync
import java.util.function.Consumer

interface SteelClientAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SteelClientAsync

    fun sync(): SteelClient

    fun apiKeys(): ApiKeyServiceAsync

    fun credentials(): CredentialServiceAsync

    fun extensions(): ExtensionServiceAsync

    fun files(): FileServiceAsync

    fun profiles(): ProfileServiceAsync

    fun projects(): ProjectServiceAsync

    fun sessions(): SessionServiceAsync

    /** Generates a PDF from a specified webpage. */
    suspend fun pdf(params: ClientPdfParams, requestOptions: RequestOptions): ClientPdfResponse

    /** @see pdf */
    suspend fun pdf(params: ClientPdfParams): ClientPdfResponse = pdf(params, RequestOptions.none())

    /** Extracts content from a specified URL. */
    suspend fun scrape(
        params: ClientScrapeParams,
        requestOptions: RequestOptions,
    ): ClientScrapeResponse

    /** @see scrape */
    suspend fun scrape(params: ClientScrapeParams): ClientScrapeResponse =
        scrape(params, RequestOptions.none())

    /** Captures a screenshot of a specified webpage. */
    suspend fun screenshot(
        params: ClientScreenshotParams,
        requestOptions: RequestOptions,
    ): ClientScreenshotResponse

    /** @see screenshot */
    suspend fun screenshot(params: ClientScreenshotParams): ClientScreenshotResponse =
        screenshot(params, RequestOptions.none())

    fun close()

    /** A view of [SteelClientAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): SteelClientAsync.WithRawResponse

        fun apiKeys(): ApiKeyServiceAsync.WithRawResponse

        fun credentials(): CredentialServiceAsync.WithRawResponse

        fun extensions(): ExtensionServiceAsync.WithRawResponse

        fun files(): FileServiceAsync.WithRawResponse

        fun profiles(): ProfileServiceAsync.WithRawResponse

        fun projects(): ProjectServiceAsync.WithRawResponse

        fun sessions(): SessionServiceAsync.WithRawResponse

        /** Generates a PDF from a specified webpage. */
        suspend fun pdf(
            params: ClientPdfParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientPdfResponse>

        /** @see pdf */
        suspend fun pdf(params: ClientPdfParams): HttpResponseFor<ClientPdfResponse> =
            pdf(params, RequestOptions.none())

        /** Extracts content from a specified URL. */
        suspend fun scrape(
            params: ClientScrapeParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientScrapeResponse>

        /** @see scrape */
        suspend fun scrape(params: ClientScrapeParams): HttpResponseFor<ClientScrapeResponse> =
            scrape(params, RequestOptions.none())

        /** Captures a screenshot of a specified webpage. */
        suspend fun screenshot(
            params: ClientScreenshotParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientScreenshotResponse>

        /** @see screenshot */
        suspend fun screenshot(
            params: ClientScreenshotParams
        ): HttpResponseFor<ClientScreenshotResponse> = screenshot(params, RequestOptions.none())
    }
}
