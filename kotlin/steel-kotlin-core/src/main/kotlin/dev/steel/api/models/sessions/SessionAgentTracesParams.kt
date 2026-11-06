package dev.steel.api.models.sessions

import dev.steel.api.core.Params
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import kotlin.io.path.name

/**
 * Query the semantic agent trace timeline for a session: a coalesced, replay-oriented record of
 * user/agent interactions (clicks, typing, scrolls, drags, navigations) with target metadata (role,
 * accessible name, selectors). Returns an empty list for sessions that predate browser-interaction
 * capture.
 */
class SessionAgentTracesParams
private constructor(
    private val id: String?,
    private val endTime: OffsetDateTime?,
    private val eventTypes: List<String>?,
    private val limit: Long?,
    private val namespace: String?,
    private val offset: Long?,
    private val startTime: OffsetDateTime?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun id(): String? = id

    fun endTime(): OffsetDateTime? = endTime

    fun eventTypes(): List<String>? = eventTypes

    fun limit(): Long? = limit

    fun namespace(): String? = namespace

    fun offset(): Long? = offset

    fun startTime(): OffsetDateTime? = startTime

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): SessionAgentTracesParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [SessionAgentTracesParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionAgentTracesParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var endTime: OffsetDateTime? = null
        private var eventTypes: MutableList<String>? = null
        private var limit: Long? = null
        private var namespace: String? = null
        private var offset: Long? = null
        private var startTime: OffsetDateTime? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionAgentTracesParams: SessionAgentTracesParams) = apply {
            this.id = sessionAgentTracesParams.id
            this.endTime = sessionAgentTracesParams.endTime
            this.eventTypes = sessionAgentTracesParams.eventTypes?.toMutableList()
            this.limit = sessionAgentTracesParams.limit
            this.namespace = sessionAgentTracesParams.namespace
            this.offset = sessionAgentTracesParams.offset
            this.startTime = sessionAgentTracesParams.startTime
            this.additionalHeaders = sessionAgentTracesParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = sessionAgentTracesParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        fun endTime(endTime: OffsetDateTime?) = apply { this.endTime = endTime }

        fun eventTypes(eventTypes: List<String>?) = apply {
            this.eventTypes = eventTypes?.toMutableList()
        }

        fun addEventType(eventType: String) = apply {
            this.eventTypes = (this.eventTypes ?: mutableListOf()).apply { add(eventType) }
        }

        fun limit(limit: Long?) = apply { this.limit = limit }

        fun limit(limit: Long) = limit(limit as Long?)

        fun namespace(namespace: String?) = apply { this.namespace = namespace }

        fun offset(offset: Long?) = apply { this.offset = offset }

        fun offset(offset: Long) = offset(offset as Long?)

        fun startTime(startTime: OffsetDateTime?) = apply { this.startTime = startTime }

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

        fun build(): SessionAgentTracesParams =
            SessionAgentTracesParams(
                id,
                endTime,
                eventTypes?.toImmutable(),
                limit,
                namespace,
                offset,
                startTime,
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
                endTime?.let { put("endTime", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it)) }
                eventTypes?.forEach { put("eventTypes", it) }
                limit?.let { put("limit", it.toString()) }
                namespace?.let { put("namespace", it) }
                offset?.let { put("offset", it.toString()) }
                startTime?.let {
                    put("startTime", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionAgentTracesParams &&
            this.id == other.id &&
            this.endTime == other.endTime &&
            this.eventTypes == other.eventTypes &&
            this.limit == other.limit &&
            this.namespace == other.namespace &&
            this.offset == other.offset &&
            this.startTime == other.startTime &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            id,
            endTime,
            eventTypes,
            limit,
            namespace,
            offset,
            startTime,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "SessionAgentTracesParams{id=$id, endTime=$endTime, eventTypes=$eventTypes, limit=$limit, namespace=$namespace, offset=$offset, startTime=$startTime, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
