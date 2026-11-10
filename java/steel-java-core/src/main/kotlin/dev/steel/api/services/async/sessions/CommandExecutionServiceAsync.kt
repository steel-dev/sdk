package dev.steel.api.services.async.sessions

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelResponse
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CommandExecutionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CommandExecutionServiceAsync

    /** @see retrieve */
    fun retrieve(
        executionId: String,
        params: CommandExecutionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CommandExecutionRetrieveResponse> =
        retrieve(params.toBuilder().executionId(executionId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        executionId: String,
        params: CommandExecutionRetrieveParams,
    ): CompletableFuture<CommandExecutionRetrieveResponse> =
        retrieve(executionId, params, RequestOptions.none())

    /**
     * Waits up to 20 seconds for an execution to finish and returns it. Use it when a run answered
     * with state running.
     */
    fun retrieve(
        params: CommandExecutionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CommandExecutionRetrieveResponse>

    /** @see retrieve */
    fun retrieve(
        params: CommandExecutionRetrieveParams
    ): CompletableFuture<CommandExecutionRetrieveResponse> = retrieve(params, RequestOptions.none())

    /** @see cancel */
    fun cancel(
        executionId: String,
        params: CommandExecutionCancelParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CommandExecutionCancelResponse> =
        cancel(params.toBuilder().executionId(executionId).build(), requestOptions)

    /** @see cancel */
    fun cancel(
        executionId: String,
        params: CommandExecutionCancelParams,
    ): CompletableFuture<CommandExecutionCancelResponse> =
        cancel(executionId, params, RequestOptions.none())

    /**
     * Stops an execution. The running command stops before its next input, and commands that
     * already ran are not undone.
     */
    fun cancel(
        params: CommandExecutionCancelParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CommandExecutionCancelResponse>

    /** @see cancel */
    fun cancel(
        params: CommandExecutionCancelParams
    ): CompletableFuture<CommandExecutionCancelResponse> = cancel(params, RequestOptions.none())

    /**
     * A view of [CommandExecutionServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CommandExecutionServiceAsync.WithRawResponse

        /** @see retrieve */
        fun retrieve(
            executionId: String,
            params: CommandExecutionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CommandExecutionRetrieveResponse>> =
            retrieve(params.toBuilder().executionId(executionId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            executionId: String,
            params: CommandExecutionRetrieveParams,
        ): CompletableFuture<HttpResponseFor<CommandExecutionRetrieveResponse>> =
            retrieve(executionId, params, RequestOptions.none())

        /**
         * Waits up to 20 seconds for an execution to finish and returns it. Use it when a run
         * answered with state running.
         */
        fun retrieve(
            params: CommandExecutionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CommandExecutionRetrieveResponse>>

        /** @see retrieve */
        fun retrieve(
            params: CommandExecutionRetrieveParams
        ): CompletableFuture<HttpResponseFor<CommandExecutionRetrieveResponse>> =
            retrieve(params, RequestOptions.none())

        /** @see cancel */
        fun cancel(
            executionId: String,
            params: CommandExecutionCancelParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CommandExecutionCancelResponse>> =
            cancel(params.toBuilder().executionId(executionId).build(), requestOptions)

        /** @see cancel */
        fun cancel(
            executionId: String,
            params: CommandExecutionCancelParams,
        ): CompletableFuture<HttpResponseFor<CommandExecutionCancelResponse>> =
            cancel(executionId, params, RequestOptions.none())

        /**
         * Stops an execution. The running command stops before its next input, and commands that
         * already ran are not undone.
         */
        fun cancel(
            params: CommandExecutionCancelParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CommandExecutionCancelResponse>>

        /** @see cancel */
        fun cancel(
            params: CommandExecutionCancelParams
        ): CompletableFuture<HttpResponseFor<CommandExecutionCancelResponse>> =
            cancel(params, RequestOptions.none())
    }
}
