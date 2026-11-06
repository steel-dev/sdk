package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonCreator
import dev.steel.api.core.Enum
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonValue
import dev.steel.api.core.Params
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Fetches all active sessions for the current organization. */
class SessionListParams
private constructor(
    private val createdAfter: OffsetDateTime?,
    private val createdBefore: OffsetDateTime?,
    private val cursorId: String?,
    private val limit: Long?,
    private val metadata: Metadata?,
    private val projectId: String?,
    private val releaseReason: ReleaseReason?,
    private val signal: List<Signal>?,
    private val status: Status?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Only sessions created at or after this ISO 8601 timestamp, e.g. 2026-10-01T00:00:00Z. */
    fun createdAfter(): Optional<OffsetDateTime> = Optional.ofNullable(createdAfter)

    /** Only sessions created before this ISO 8601 timestamp. */
    fun createdBefore(): Optional<OffsetDateTime> = Optional.ofNullable(createdBefore)

    /** Cursor ID for pagination */
    fun cursorId(): Optional<String> = Optional.ofNullable(cursorId)

    /** Number of sessions to return. Default is 50, max is 100. */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /**
     * Exact-match metadata filters, e.g. metadata[environment]=production. All entries must match.
     * Values match the stored string exactly; numbers and booleans are stored as strings, so
     * metadata[attempt]=3 matches attempt sent as 3 or "3".
     */
    fun metadata(): Optional<Metadata> = Optional.ofNullable(metadata)

    /** Filter sessions by project */
    fun projectId(): Optional<String> = Optional.ofNullable(projectId)

    /**
     * Filter released sessions by why they ended. Live sessions have no release reason and are
     * excluded whenever this is set. Without an explicit status, matching failed sessions are
     * included.
     */
    fun releaseReason(): Optional<ReleaseReason> = Optional.ofNullable(releaseReason)

    /**
     * Signals the session must carry; repeat the parameter to require several. All must match.
     * Derived signals are finalized at release.
     */
    fun signal(): Optional<List<Signal>> = Optional.ofNullable(signal)

    /** Filter sessions by current status */
    fun status(): Optional<Status> = Optional.ofNullable(status)

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): SessionListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [SessionListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionListParams]. */
    class Builder internal constructor() {

        private var createdAfter: OffsetDateTime? = null
        private var createdBefore: OffsetDateTime? = null
        private var cursorId: String? = null
        private var limit: Long? = null
        private var metadata: Metadata? = null
        private var projectId: String? = null
        private var releaseReason: ReleaseReason? = null
        private var signal: MutableList<Signal>? = null
        private var status: Status? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionListParams: SessionListParams) = apply {
            this.createdAfter = sessionListParams.createdAfter
            this.createdBefore = sessionListParams.createdBefore
            this.cursorId = sessionListParams.cursorId
            this.limit = sessionListParams.limit
            this.metadata = sessionListParams.metadata
            this.projectId = sessionListParams.projectId
            this.releaseReason = sessionListParams.releaseReason
            this.signal = sessionListParams.signal?.toMutableList()
            this.status = sessionListParams.status
            this.additionalHeaders = sessionListParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = sessionListParams.additionalQueryParams.toBuilder()
        }

        /** Only sessions created at or after this ISO 8601 timestamp, e.g. 2026-10-01T00:00:00Z. */
        fun createdAfter(createdAfter: OffsetDateTime?) = apply { this.createdAfter = createdAfter }

        /** Only sessions created at or after this ISO 8601 timestamp, e.g. 2026-10-01T00:00:00Z. */
        fun createdAfter(createdAfter: Optional<OffsetDateTime>) =
            createdAfter(createdAfter.getOrNull())

        /** Only sessions created before this ISO 8601 timestamp. */
        fun createdBefore(createdBefore: OffsetDateTime?) = apply {
            this.createdBefore = createdBefore
        }

        /** Only sessions created before this ISO 8601 timestamp. */
        fun createdBefore(createdBefore: Optional<OffsetDateTime>) =
            createdBefore(createdBefore.getOrNull())

        /** Cursor ID for pagination */
        fun cursorId(cursorId: String?) = apply { this.cursorId = cursorId }

        /** Cursor ID for pagination */
        fun cursorId(cursorId: Optional<String>) = cursorId(cursorId.getOrNull())

        /** Number of sessions to return. Default is 50, max is 100. */
        fun limit(limit: Long?) = apply { this.limit = limit }

        /** Number of sessions to return. Default is 50, max is 100. */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Number of sessions to return. Default is 50, max is 100. */
        fun limit(limit: Optional<Long>) = limit(limit.getOrNull())

        /**
         * Exact-match metadata filters, e.g. metadata[environment]=production. All entries must
         * match. Values match the stored string exactly; numbers and booleans are stored as
         * strings, so metadata[attempt]=3 matches attempt sent as 3 or "3".
         */
        fun metadata(metadata: Metadata?) = apply { this.metadata = metadata }

        /**
         * Exact-match metadata filters, e.g. metadata[environment]=production. All entries must
         * match. Values match the stored string exactly; numbers and booleans are stored as
         * strings, so metadata[attempt]=3 matches attempt sent as 3 or "3".
         */
        fun metadata(metadata: Optional<Metadata>) = metadata(metadata.getOrNull())

        /** Filter sessions by project */
        fun projectId(projectId: String?) = apply { this.projectId = projectId }

        /** Filter sessions by project */
        fun projectId(projectId: Optional<String>) = projectId(projectId.getOrNull())

        /**
         * Filter released sessions by why they ended. Live sessions have no release reason and are
         * excluded whenever this is set. Without an explicit status, matching failed sessions are
         * included.
         */
        fun releaseReason(releaseReason: ReleaseReason?) = apply {
            this.releaseReason = releaseReason
        }

        /**
         * Filter released sessions by why they ended. Live sessions have no release reason and are
         * excluded whenever this is set. Without an explicit status, matching failed sessions are
         * included.
         */
        fun releaseReason(releaseReason: Optional<ReleaseReason>) =
            releaseReason(releaseReason.getOrNull())

        /**
         * Signals the session must carry; repeat the parameter to require several. All must match.
         * Derived signals are finalized at release.
         */
        fun signal(signal: List<Signal>?) = apply { this.signal = signal?.toMutableList() }

        /**
         * Signals the session must carry; repeat the parameter to require several. All must match.
         * Derived signals are finalized at release.
         */
        fun signal(signal: Optional<List<Signal>>) = signal(signal.getOrNull())

        /**
         * Signals the session must carry; repeat the parameter to require several. All must match.
         * Derived signals are finalized at release.
         */
        fun addSignal(signal: Signal) = apply {
            this.signal = (this.signal ?: mutableListOf()).apply { add(signal) }
        }

        /** Filter sessions by current status */
        fun status(status: Status?) = apply { this.status = status }

        /** Filter sessions by current status */
        fun status(status: Optional<Status>) = status(status.getOrNull())

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

        fun build(): SessionListParams =
            SessionListParams(
                createdAfter,
                createdBefore,
                cursorId,
                limit,
                metadata,
                projectId,
                releaseReason,
                signal?.toImmutable(),
                status,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                createdAfter?.let {
                    put("createdAfter", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdBefore?.let {
                    put("createdBefore", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                cursorId?.let { put("cursorId", it) }
                limit?.let { put("limit", it.toString()) }
                metadata?.let { query ->
                    query._additionalProperties().keys().forEach { key ->
                        query._additionalProperties().values(key).forEach { value ->
                            put("metadata[$key]", value)
                        }
                    }
                }
                projectId?.let { put("projectId", it) }
                releaseReason?.let { put("releaseReason", it.toString()) }
                signal?.forEach { put("signal", it.toString()) }
                status?.let { put("status", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    class Metadata private constructor(private val additionalProperties: QueryParams) {

        fun _additionalProperties(): QueryParams = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder internal constructor() {

            private var additionalProperties: QueryParams.Builder = QueryParams.builder()

            @JvmSynthetic
            internal fun from(metadata: Metadata) = apply {
                this.additionalProperties = metadata.additionalProperties.toBuilder()
            }

            fun additionalProperties(additionalProperties: QueryParams) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun additionalProperties(additionalProperties: Map<String, Iterable<String>>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: String) = apply {
                additionalProperties.put(key, value)
            }

            fun putAdditionalProperties(key: String, values: Iterable<String>) = apply {
                additionalProperties.put(key, values)
            }

            fun putAllAdditionalProperties(additionalProperties: QueryParams) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, Iterable<String>>) =
                apply {
                    this.additionalProperties.putAll(additionalProperties)
                }

            fun replaceAdditionalProperties(key: String, value: String) = apply {
                additionalProperties.replace(key, value)
            }

            fun replaceAdditionalProperties(key: String, values: Iterable<String>) = apply {
                additionalProperties.replace(key, values)
            }

            fun replaceAllAdditionalProperties(additionalProperties: QueryParams) = apply {
                this.additionalProperties.replaceAll(additionalProperties)
            }

            fun replaceAllAdditionalProperties(
                additionalProperties: Map<String, Iterable<String>>
            ) = apply { this.additionalProperties.replaceAll(additionalProperties) }

            fun removeAdditionalProperties(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                additionalProperties.removeAll(keys)
            }

            fun build(): Metadata = Metadata(additionalProperties.build())
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Metadata && this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
    }

    class ReleaseReason @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val USER_REQUESTED = of("user_requested")

            @JvmField val TIMEOUT = of("timeout")

            @JvmField val INACTIVITY_TIMEOUT = of("inactivity_timeout")

            @JvmField val CREATION_TIMEOUT = of("creation_timeout")

            @JvmField val STARTUP_FAILED = of("startup_failed")

            @JvmField val BROWSER_CLOSED = of("browser_closed")

            @JvmField val BROWSER_CRASHED = of("browser_crashed")

            @JvmStatic fun of(value: String) = ReleaseReason(JsonField.of(value))
        }

        enum class Known {
            USER_REQUESTED,
            TIMEOUT,
            INACTIVITY_TIMEOUT,
            CREATION_TIMEOUT,
            STARTUP_FAILED,
            BROWSER_CLOSED,
            BROWSER_CRASHED,
        }

        enum class Value {
            USER_REQUESTED,
            TIMEOUT,
            INACTIVITY_TIMEOUT,
            CREATION_TIMEOUT,
            STARTUP_FAILED,
            BROWSER_CLOSED,
            BROWSER_CRASHED,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                USER_REQUESTED -> Value.USER_REQUESTED
                TIMEOUT -> Value.TIMEOUT
                INACTIVITY_TIMEOUT -> Value.INACTIVITY_TIMEOUT
                CREATION_TIMEOUT -> Value.CREATION_TIMEOUT
                STARTUP_FAILED -> Value.STARTUP_FAILED
                BROWSER_CLOSED -> Value.BROWSER_CLOSED
                BROWSER_CRASHED -> Value.BROWSER_CRASHED
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                USER_REQUESTED -> Known.USER_REQUESTED
                TIMEOUT -> Known.TIMEOUT
                INACTIVITY_TIMEOUT -> Known.INACTIVITY_TIMEOUT
                CREATION_TIMEOUT -> Known.CREATION_TIMEOUT
                STARTUP_FAILED -> Known.STARTUP_FAILED
                BROWSER_CLOSED -> Known.BROWSER_CLOSED
                BROWSER_CRASHED -> Known.BROWSER_CRASHED
                else -> throw SteelInvalidDataException("Unknown ReleaseReason: $value")
            }

        fun asString(): String =
            _value().asString().orElseThrow { SteelInvalidDataException("Value is not a String") }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): ReleaseReason = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ReleaseReason && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Signal @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val CAPTCHA_ENCOUNTERED = of("captcha_encountered")

            @JvmField val PROXY_CONNECTION_FAILED = of("proxy_connection_failed")

            @JvmField val SESSION_TIMEOUT_EXCEEDED = of("session_timeout_exceeded")

            @JvmField val BOT_DETECTION_TRIGGERED = of("bot_detection_triggered")

            @JvmField val CONTEXT_RESTORED = of("context_restored")

            @JvmStatic fun of(value: String) = Signal(JsonField.of(value))
        }

        enum class Known {
            CAPTCHA_ENCOUNTERED,
            PROXY_CONNECTION_FAILED,
            SESSION_TIMEOUT_EXCEEDED,
            BOT_DETECTION_TRIGGERED,
            CONTEXT_RESTORED,
        }

        enum class Value {
            CAPTCHA_ENCOUNTERED,
            PROXY_CONNECTION_FAILED,
            SESSION_TIMEOUT_EXCEEDED,
            BOT_DETECTION_TRIGGERED,
            CONTEXT_RESTORED,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                CAPTCHA_ENCOUNTERED -> Value.CAPTCHA_ENCOUNTERED
                PROXY_CONNECTION_FAILED -> Value.PROXY_CONNECTION_FAILED
                SESSION_TIMEOUT_EXCEEDED -> Value.SESSION_TIMEOUT_EXCEEDED
                BOT_DETECTION_TRIGGERED -> Value.BOT_DETECTION_TRIGGERED
                CONTEXT_RESTORED -> Value.CONTEXT_RESTORED
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                CAPTCHA_ENCOUNTERED -> Known.CAPTCHA_ENCOUNTERED
                PROXY_CONNECTION_FAILED -> Known.PROXY_CONNECTION_FAILED
                SESSION_TIMEOUT_EXCEEDED -> Known.SESSION_TIMEOUT_EXCEEDED
                BOT_DETECTION_TRIGGERED -> Known.BOT_DETECTION_TRIGGERED
                CONTEXT_RESTORED -> Known.CONTEXT_RESTORED
                else -> throw SteelInvalidDataException("Unknown Signal: $value")
            }

        fun asString(): String =
            _value().asString().orElseThrow { SteelInvalidDataException("Value is not a String") }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Signal = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Signal && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val LIVE = of("live")

            @JvmField val RELEASED = of("released")

            @JvmField val FAILED = of("failed")

            @JvmStatic fun of(value: String) = Status(JsonField.of(value))
        }

        enum class Known {
            LIVE,
            RELEASED,
            FAILED,
        }

        enum class Value {
            LIVE,
            RELEASED,
            FAILED,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                LIVE -> Value.LIVE
                RELEASED -> Value.RELEASED
                FAILED -> Value.FAILED
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                LIVE -> Known.LIVE
                RELEASED -> Known.RELEASED
                FAILED -> Known.FAILED
                else -> throw SteelInvalidDataException("Unknown Status: $value")
            }

        fun asString(): String =
            _value().asString().orElseThrow { SteelInvalidDataException("Value is not a String") }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Status = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionListParams &&
            this.createdAfter == other.createdAfter &&
            this.createdBefore == other.createdBefore &&
            this.cursorId == other.cursorId &&
            this.limit == other.limit &&
            this.metadata == other.metadata &&
            this.projectId == other.projectId &&
            this.releaseReason == other.releaseReason &&
            this.signal == other.signal &&
            this.status == other.status &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            createdAfter,
            createdBefore,
            cursorId,
            limit,
            metadata,
            projectId,
            releaseReason,
            signal,
            status,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "SessionListParams{createdAfter=$createdAfter, createdBefore=$createdBefore, cursorId=$cursorId, limit=$limit, metadata=$metadata, projectId=$projectId, releaseReason=$releaseReason, signal=$signal, status=$status, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
