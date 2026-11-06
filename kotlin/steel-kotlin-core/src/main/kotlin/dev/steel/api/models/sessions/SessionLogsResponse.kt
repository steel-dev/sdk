package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.checkKnown
import dev.steel.api.core.checkRequired
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class SessionLogsResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val events: JsonField<List<Event>>,
    private val hasMore: JsonField<Boolean>,
    private val total: JsonField<Double>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("events") @ExcludeMissing events: JsonField<List<Event>> = JsonMissing.of(),
        @JsonProperty("hasMore") @ExcludeMissing hasMore: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("total") @ExcludeMissing total: JsonField<Double> = JsonMissing.of(),
    ) : this(events, hasMore, total, mutableMapOf())

    fun events(): List<Event> = events.getRequired("events")

    fun hasMore(): Boolean = hasMore.getRequired("hasMore")

    fun total(): Double = total.getRequired("total")

    @JsonProperty("events") @ExcludeMissing fun _events(): JsonField<List<Event>> = events

    @JsonProperty("hasMore") @ExcludeMissing fun _hasMore(): JsonField<Boolean> = hasMore

    @JsonProperty("total") @ExcludeMissing fun _total(): JsonField<Double> = total

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [SessionLogsResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionLogsResponse]. */
    class Builder internal constructor() {

        private var events: JsonField<MutableList<Event>>? = null
        private var hasMore: JsonField<Boolean>? = null
        private var total: JsonField<Double>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionLogsResponse: SessionLogsResponse) = apply {
            this.events = sessionLogsResponse.events.map { it.toMutableList() }
            this.hasMore = sessionLogsResponse.hasMore
            this.total = sessionLogsResponse.total
            this.additionalProperties = sessionLogsResponse.additionalProperties.toMutableMap()
        }

        fun events(events: List<Event>) = events(JsonField.of(events))

        fun events(events: JsonField<List<Event>>) = apply {
            this.events = events.map { it.toMutableList() }
        }

        fun addEvent(event: Event) = apply {
            this.events =
                (this.events ?: JsonField.of(mutableListOf())).also {
                    checkKnown("events", it).add(event)
                }
        }

        fun hasMore(hasMore: Boolean) = hasMore(JsonField.of(hasMore))

        fun hasMore(hasMore: JsonField<Boolean>) = apply { this.hasMore = hasMore }

        fun total(total: Double) = total(JsonField.of(total))

        fun total(total: JsonField<Double>) = apply { this.total = total }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        fun build(): SessionLogsResponse =
            SessionLogsResponse(
                checkRequired("events", events).map { it.toImmutable() },
                checkRequired("hasMore", hasMore),
                checkRequired("total", total),
                additionalProperties.toMutableMap(),
            )
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: SteelInvalidDataException) {
            false
        }

    private var validated: Boolean = false

    fun validate(): SessionLogsResponse = apply {
        if (validated) {
            return@apply
        }

        events().forEach { it.validate() }
        hasMore()
        total()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (events.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (if (hasMore.asKnown().isPresent) 1 else 0) +
            (if (total.asKnown().isPresent) 1 else 0)

    class Event
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val log: JsonField<String>,
        private val timestamp: JsonField<OffsetDateTime>,
        private val type: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("log") @ExcludeMissing log: JsonField<String> = JsonMissing.of(),
            @JsonProperty("timestamp")
            @ExcludeMissing
            timestamp: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
        ) : this(id, log, timestamp, type, mutableMapOf())

        fun id(): String = id.getRequired("id")

        fun log(): String = log.getRequired("log")

        fun timestamp(): OffsetDateTime = timestamp.getRequired("timestamp")

        fun type(): String = type.getRequired("type")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("log") @ExcludeMissing fun _log(): JsonField<String> = log

        @JsonProperty("timestamp")
        @ExcludeMissing
        fun _timestamp(): JsonField<OffsetDateTime> = timestamp

        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Event]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Event]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var log: JsonField<String>? = null
            private var timestamp: JsonField<OffsetDateTime>? = null
            private var type: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(event: Event) = apply {
                this.id = event.id
                this.log = event.log
                this.timestamp = event.timestamp
                this.type = event.type
                this.additionalProperties = event.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            fun id(id: JsonField<String>) = apply { this.id = id }

            fun log(log: String) = log(JsonField.of(log))

            fun log(log: JsonField<String>) = apply { this.log = log }

            fun timestamp(timestamp: OffsetDateTime) = timestamp(JsonField.of(timestamp))

            fun timestamp(timestamp: JsonField<OffsetDateTime>) = apply {
                this.timestamp = timestamp
            }

            fun type(type: String) = type(JsonField.of(type))

            fun type(type: JsonField<String>) = apply { this.type = type }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Event =
                Event(
                    checkRequired("id", id),
                    checkRequired("log", log),
                    checkRequired("timestamp", timestamp),
                    checkRequired("type", type),
                    additionalProperties.toMutableMap(),
                )
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Event = apply {
            if (validated) {
                return@apply
            }

            id()
            log()
            timestamp()
            type()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (if (log.asKnown().isPresent) 1 else 0) +
                (if (timestamp.asKnown().isPresent) 1 else 0) +
                (if (type.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Event &&
                this.id == other.id &&
                this.log == other.log &&
                this.timestamp == other.timestamp &&
                this.type == other.type &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, log, timestamp, type, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Event{id=$id, log=$log, timestamp=$timestamp, type=$type, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionLogsResponse &&
            this.events == other.events &&
            this.hasMore == other.hasMore &&
            this.total == other.total &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(events, hasMore, total, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionLogsResponse{events=$events, hasMore=$hasMore, total=$total, additionalProperties=$additionalProperties}"
}
