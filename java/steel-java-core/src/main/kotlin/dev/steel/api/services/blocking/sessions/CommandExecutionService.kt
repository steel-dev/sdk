package dev.steel.api.services.blocking.sessions

import com.google.errorprone.annotations.MustBeClosed
import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelResponse
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveResponse
import java.util.function.Consumer

interface CommandExecutionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CommandExecutionService

    /** @see retrieve */
    fun retrieve(
        executionId: String,
        params: CommandExecutionRetrieveParams,
        requestOptions: RequestOptions,
    ): CommandExecutionRetrieveResponse =
        retrieve(params.toBuilder().executionId(executionId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        executionId: String,
        params: CommandExecutionRetrieveParams,
    ): CommandExecutionRetrieveResponse = retrieve(executionId, params, RequestOptions.none())

    /**
     * Waits up to 20 seconds for an execution to finish and returns it. Use it when a run answered
     * with state running.
     */
    fun retrieve(
        params: CommandExecutionRetrieveParams,
        requestOptions: RequestOptions,
    ): CommandExecutionRetrieveResponse

    /** @see retrieve */
    fun retrieve(params: CommandExecutionRetrieveParams): CommandExecutionRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see cancel */
    fun cancel(
        executionId: String,
        params: CommandExecutionCancelParams,
        requestOptions: RequestOptions,
    ): CommandExecutionCancelResponse =
        cancel(params.toBuilder().executionId(executionId).build(), requestOptions)

    /** @see cancel */
    fun cancel(
        executionId: String,
        params: CommandExecutionCancelParams,
    ): CommandExecutionCancelResponse = cancel(executionId, params, RequestOptions.none())

    /**
     * Stops an execution. The running command stops before its next input, and commands that
     * already ran are not undone.
     */
    fun cancel(
        params: CommandExecutionCancelParams,
        requestOptions: RequestOptions,
    ): CommandExecutionCancelResponse

    /** @see cancel */
    fun cancel(params: CommandExecutionCancelParams): CommandExecutionCancelResponse =
        cancel(params, RequestOptions.none())

    /**
     * A view of [CommandExecutionService] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CommandExecutionService.WithRawResponse

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            executionId: String,
            params: CommandExecutionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionRetrieveResponse> =
            retrieve(params.toBuilder().executionId(executionId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            executionId: String,
            params: CommandExecutionRetrieveParams,
        ): HttpResponseFor<CommandExecutionRetrieveResponse> =
            retrieve(executionId, params, RequestOptions.none())

        /**
         * Waits up to 20 seconds for an execution to finish and returns it. Use it when a run
         * answered with state running.
         */
        @MustBeClosed
        fun retrieve(
            params: CommandExecutionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: CommandExecutionRetrieveParams
        ): HttpResponseFor<CommandExecutionRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            executionId: String,
            params: CommandExecutionCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionCancelResponse> =
            cancel(params.toBuilder().executionId(executionId).build(), requestOptions)

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            executionId: String,
            params: CommandExecutionCancelParams,
        ): HttpResponseFor<CommandExecutionCancelResponse> =
            cancel(executionId, params, RequestOptions.none())

        /**
         * Stops an execution. The running command stops before its next input, and commands that
         * already ran are not undone.
         */
        @MustBeClosed
        fun cancel(
            params: CommandExecutionCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionCancelResponse>

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            params: CommandExecutionCancelParams
        ): HttpResponseFor<CommandExecutionCancelResponse> = cancel(params, RequestOptions.none())
    }
}
