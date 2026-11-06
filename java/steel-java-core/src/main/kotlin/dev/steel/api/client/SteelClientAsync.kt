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
import java.util.concurrent.CompletableFuture
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
    fun pdf(
        params: ClientPdfParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ClientPdfResponse>

    /** @see pdf */
    fun pdf(params: ClientPdfParams): CompletableFuture<ClientPdfResponse> =
        pdf(params, RequestOptions.none())

    /** Extracts content from a specified URL. */
    fun scrape(
        params: ClientScrapeParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ClientScrapeResponse>

    /** @see scrape */
    fun scrape(params: ClientScrapeParams): CompletableFuture<ClientScrapeResponse> =
        scrape(params, RequestOptions.none())

    /** Captures a screenshot of a specified webpage. */
    fun screenshot(
        params: ClientScreenshotParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ClientScreenshotResponse>

    /** @see screenshot */
    fun screenshot(params: ClientScreenshotParams): CompletableFuture<ClientScreenshotResponse> =
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
        fun pdf(
            params: ClientPdfParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ClientPdfResponse>>

        /** @see pdf */
        fun pdf(params: ClientPdfParams): CompletableFuture<HttpResponseFor<ClientPdfResponse>> =
            pdf(params, RequestOptions.none())

        /** Extracts content from a specified URL. */
        fun scrape(
            params: ClientScrapeParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ClientScrapeResponse>>

        /** @see scrape */
        fun scrape(
            params: ClientScrapeParams
        ): CompletableFuture<HttpResponseFor<ClientScrapeResponse>> =
            scrape(params, RequestOptions.none())

        /** Captures a screenshot of a specified webpage. */
        fun screenshot(
            params: ClientScreenshotParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ClientScreenshotResponse>>

        /** @see screenshot */
        fun screenshot(
            params: ClientScreenshotParams
        ): CompletableFuture<HttpResponseFor<ClientScreenshotResponse>> =
            screenshot(params, RequestOptions.none())
    }
}
