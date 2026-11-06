package dev.steel.api.client

import com.google.errorprone.annotations.MustBeClosed
import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.ClientPdfParams
import dev.steel.api.models.ClientPdfResponse
import dev.steel.api.models.ClientScrapeParams
import dev.steel.api.models.ClientScrapeResponse
import dev.steel.api.models.ClientScreenshotParams
import dev.steel.api.models.ClientScreenshotResponse
import dev.steel.api.services.blocking.ApiKeyService
import dev.steel.api.services.blocking.CredentialService
import dev.steel.api.services.blocking.ExtensionService
import dev.steel.api.services.blocking.FileService
import dev.steel.api.services.blocking.ProfileService
import dev.steel.api.services.blocking.ProjectService
import dev.steel.api.services.blocking.SessionService
import java.util.function.Consumer

interface SteelClient {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SteelClient

    fun async(): SteelClientAsync

    fun apiKeys(): ApiKeyService

    fun credentials(): CredentialService

    fun extensions(): ExtensionService

    fun files(): FileService

    fun profiles(): ProfileService

    fun projects(): ProjectService

    fun sessions(): SessionService

    /** Generates a PDF from a specified webpage. */
    fun pdf(params: ClientPdfParams, requestOptions: RequestOptions): ClientPdfResponse

    /** @see pdf */
    fun pdf(params: ClientPdfParams): ClientPdfResponse = pdf(params, RequestOptions.none())

    /** Extracts content from a specified URL. */
    fun scrape(params: ClientScrapeParams, requestOptions: RequestOptions): ClientScrapeResponse

    /** @see scrape */
    fun scrape(params: ClientScrapeParams): ClientScrapeResponse =
        scrape(params, RequestOptions.none())

    /** Captures a screenshot of a specified webpage. */
    fun screenshot(
        params: ClientScreenshotParams,
        requestOptions: RequestOptions,
    ): ClientScreenshotResponse

    /** @see screenshot */
    fun screenshot(params: ClientScreenshotParams): ClientScreenshotResponse =
        screenshot(params, RequestOptions.none())

    fun close()

    /** A view of [SteelClient] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): SteelClient.WithRawResponse

        fun apiKeys(): ApiKeyService.WithRawResponse

        fun credentials(): CredentialService.WithRawResponse

        fun extensions(): ExtensionService.WithRawResponse

        fun files(): FileService.WithRawResponse

        fun profiles(): ProfileService.WithRawResponse

        fun projects(): ProjectService.WithRawResponse

        fun sessions(): SessionService.WithRawResponse

        /** Generates a PDF from a specified webpage. */
        @MustBeClosed
        fun pdf(
            params: ClientPdfParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientPdfResponse>

        /** @see pdf */
        @MustBeClosed
        fun pdf(params: ClientPdfParams): HttpResponseFor<ClientPdfResponse> =
            pdf(params, RequestOptions.none())

        /** Extracts content from a specified URL. */
        @MustBeClosed
        fun scrape(
            params: ClientScrapeParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientScrapeResponse>

        /** @see scrape */
        @MustBeClosed
        fun scrape(params: ClientScrapeParams): HttpResponseFor<ClientScrapeResponse> =
            scrape(params, RequestOptions.none())

        /** Captures a screenshot of a specified webpage. */
        @MustBeClosed
        fun screenshot(
            params: ClientScreenshotParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ClientScreenshotResponse>

        /** @see screenshot */
        @MustBeClosed
        fun screenshot(params: ClientScreenshotParams): HttpResponseFor<ClientScreenshotResponse> =
            screenshot(params, RequestOptions.none())
    }
}
