package dev.steel.api.models.sessions.commandexecutions

import dev.steel.api.core.Params
import dev.steel.api.core.checkRequired
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/**
 * Stops an execution. The running command stops before its next input, and commands that already
 * ran are not undone.
 */
class CommandExecutionCancelParams
private constructor(
    private val sessionId: String,
    private val executionId: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Session ID */
    fun sessionId(): String = sessionId

    /** Execution ID, such as x-1, from the id of a run */
    fun executionId(): Optional<String> = Optional.ofNullable(executionId)

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CommandExecutionCancelParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandExecutionCancelParams]. */
    class Builder internal constructor() {

        private var sessionId: String? = null
        private var executionId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(commandExecutionCancelParams: CommandExecutionCancelParams) = apply {
            this.sessionId = commandExecutionCancelParams.sessionId
            this.executionId = commandExecutionCancelParams.executionId
            this.additionalHeaders = commandExecutionCancelParams.additionalHeaders.toBuilder()
            this.additionalQueryParams =
                commandExecutionCancelParams.additionalQueryParams.toBuilder()
        }

        /** Session ID */
        fun sessionId(sessionId: String) = apply { this.sessionId = sessionId }

        /** Execution ID, such as x-1, from the id of a run */
        fun executionId(executionId: String?) = apply { this.executionId = executionId }

        /** Execution ID, such as x-1, from the id of a run */
        fun executionId(executionId: Optional<String>) = executionId(executionId.getOrNull())

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        fun build(): CommandExecutionCancelParams =
            CommandExecutionCancelParams(
                checkRequired("sessionId", sessionId),
                executionId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> sessionId
            1 -> executionId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandExecutionCancelParams &&
            this.sessionId == other.sessionId &&
            this.executionId == other.executionId &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(sessionId, executionId, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "CommandExecutionCancelParams{sessionId=$sessionId, executionId=$executionId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
