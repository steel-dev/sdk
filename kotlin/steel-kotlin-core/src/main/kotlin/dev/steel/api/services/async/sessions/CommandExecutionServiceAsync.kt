package dev.steel.api.services.async.sessions

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelResponse
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveResponse
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
    suspend fun retrieve(
        executionId: String,
        params: CommandExecutionRetrieveParams,
        requestOptions: RequestOptions,
    ): CommandExecutionRetrieveResponse =
        retrieve(params.toBuilder().executionId(executionId).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        executionId: String,
        params: CommandExecutionRetrieveParams,
    ): CommandExecutionRetrieveResponse = retrieve(executionId, params, RequestOptions.none())

    /**
     * Waits up to 20 seconds for an execution to finish and returns it. Use it when a run answered
     * with state running.
     */
    suspend fun retrieve(
        params: CommandExecutionRetrieveParams,
        requestOptions: RequestOptions,
    ): CommandExecutionRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(params: CommandExecutionRetrieveParams): CommandExecutionRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see cancel */
    suspend fun cancel(
        executionId: String,
        params: CommandExecutionCancelParams,
        requestOptions: RequestOptions,
    ): CommandExecutionCancelResponse =
        cancel(params.toBuilder().executionId(executionId).build(), requestOptions)

    /** @see cancel */
    suspend fun cancel(
        executionId: String,
        params: CommandExecutionCancelParams,
    ): CommandExecutionCancelResponse = cancel(executionId, params, RequestOptions.none())

    /**
     * Stops an execution. The running command stops before its next input, and commands that
     * already ran are not undone.
     */
    suspend fun cancel(
        params: CommandExecutionCancelParams,
        requestOptions: RequestOptions,
    ): CommandExecutionCancelResponse

    /** @see cancel */
    suspend fun cancel(params: CommandExecutionCancelParams): CommandExecutionCancelResponse =
        cancel(params, RequestOptions.none())

    /**
     * A view of [CommandExecutionServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CommandExecutionServiceAsync.WithRawResponse

        /** @see retrieve */
        suspend fun retrieve(
            executionId: String,
            params: CommandExecutionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionRetrieveResponse> =
            retrieve(params.toBuilder().executionId(executionId).build(), requestOptions)

        /** @see retrieve */
        suspend fun retrieve(
            executionId: String,
            params: CommandExecutionRetrieveParams,
        ): HttpResponseFor<CommandExecutionRetrieveResponse> =
            retrieve(executionId, params, RequestOptions.none())

        /**
         * Waits up to 20 seconds for an execution to finish and returns it. Use it when a run
         * answered with state running.
         */
        suspend fun retrieve(
            params: CommandExecutionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionRetrieveResponse>

        /** @see retrieve */
        suspend fun retrieve(
            params: CommandExecutionRetrieveParams
        ): HttpResponseFor<CommandExecutionRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see cancel */
        suspend fun cancel(
            executionId: String,
            params: CommandExecutionCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionCancelResponse> =
            cancel(params.toBuilder().executionId(executionId).build(), requestOptions)

        /** @see cancel */
        suspend fun cancel(
            executionId: String,
            params: CommandExecutionCancelParams,
        ): HttpResponseFor<CommandExecutionCancelResponse> =
            cancel(executionId, params, RequestOptions.none())

        /**
         * Stops an execution. The running command stops before its next input, and commands that
         * already ran are not undone.
         */
        suspend fun cancel(
            params: CommandExecutionCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionCancelResponse>

        /** @see cancel */
        suspend fun cancel(
            params: CommandExecutionCancelParams
        ): HttpResponseFor<CommandExecutionCancelResponse> = cancel(params, RequestOptions.none())
    }
}
