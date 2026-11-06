package dev.steel.api.models.sessions

import dev.steel.api.core.Params
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** This endpoint allows you to get the recorded session events in the RRWeb format */
class SessionEventsParams
private constructor(
    private val id: String?,
    private val compressed: Boolean?,
    private val limit: Long?,
    private val pointer: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun id(): Optional<String> = Optional.ofNullable(id)

    /** Compress the events */
    fun compressed(): Optional<Boolean> = Optional.ofNullable(compressed)

    /** Optional pagination limit */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Opaque pagination token. Pass the Next-Cursor header value to get the next page. */
    fun pointer(): Optional<String> = Optional.ofNullable(pointer)

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): SessionEventsParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [SessionEventsParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionEventsParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var compressed: Boolean? = null
        private var limit: Long? = null
        private var pointer: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionEventsParams: SessionEventsParams) = apply {
            this.id = sessionEventsParams.id
            this.compressed = sessionEventsParams.compressed
            this.limit = sessionEventsParams.limit
            this.pointer = sessionEventsParams.pointer
            this.additionalHeaders = sessionEventsParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = sessionEventsParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        fun id(id: Optional<String>) = id(id.getOrNull())

        /** Compress the events */
        fun compressed(compressed: Boolean?) = apply { this.compressed = compressed }

        /** Compress the events */
        fun compressed(compressed: Boolean) = compressed(compressed as Boolean?)

        /** Compress the events */
        fun compressed(compressed: Optional<Boolean>) = compressed(compressed.getOrNull())

        /** Optional pagination limit */
        fun limit(limit: Long?) = apply { this.limit = limit }

        /** Optional pagination limit */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Optional pagination limit */
        fun limit(limit: Optional<Long>) = limit(limit.getOrNull())

        /** Opaque pagination token. Pass the Next-Cursor header value to get the next page. */
        fun pointer(pointer: String?) = apply { this.pointer = pointer }

        /** Opaque pagination token. Pass the Next-Cursor header value to get the next page. */
        fun pointer(pointer: Optional<String>) = pointer(pointer.getOrNull())

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

        fun build(): SessionEventsParams =
            SessionEventsParams(
                id,
                compressed,
                limit,
                pointer,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> id ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                compressed?.let { put("compressed", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                pointer?.let { put("pointer", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionEventsParams &&
            this.id == other.id &&
            this.compressed == other.compressed &&
            this.limit == other.limit &&
            this.pointer == other.pointer &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(id, compressed, limit, pointer, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SessionEventsParams{id=$id, compressed=$compressed, limit=$limit, pointer=$pointer, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
