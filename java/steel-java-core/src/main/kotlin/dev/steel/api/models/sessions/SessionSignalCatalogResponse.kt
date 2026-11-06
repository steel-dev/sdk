package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.Enum
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.checkKnown
import dev.steel.api.core.checkRequired
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** Current customer-visible signal definitions, not per-session detection evidence */
class SessionSignalCatalogResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val semantics: JsonField<String>,
    private val signals: JsonField<List<Signal>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("semantics") @ExcludeMissing semantics: JsonField<String> = JsonMissing.of(),
        @JsonProperty("signals") @ExcludeMissing signals: JsonField<List<Signal>> = JsonMissing.of(),
    ) : this(semantics, signals, mutableMapOf())

    /** Interpretation and coverage limitations of session signals */
    fun semantics(): String = semantics.getRequired("semantics")

    fun signals(): List<Signal> = signals.getRequired("signals")

    @JsonProperty("semantics") @ExcludeMissing fun _semantics(): JsonField<String> = semantics

    @JsonProperty("signals") @ExcludeMissing fun _signals(): JsonField<List<Signal>> = signals

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

        /**
         * Returns a mutable builder for constructing an instance of [SessionSignalCatalogResponse].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionSignalCatalogResponse]. */
    class Builder internal constructor() {

        private var semantics: JsonField<String>? = null
        private var signals: JsonField<MutableList<Signal>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionSignalCatalogResponse: SessionSignalCatalogResponse) = apply {
            this.semantics = sessionSignalCatalogResponse.semantics
            this.signals = sessionSignalCatalogResponse.signals.map { it.toMutableList() }
            this.additionalProperties =
                sessionSignalCatalogResponse.additionalProperties.toMutableMap()
        }

        /** Interpretation and coverage limitations of session signals */
        fun semantics(semantics: String) = semantics(JsonField.of(semantics))

        /** Interpretation and coverage limitations of session signals */
        fun semantics(semantics: JsonField<String>) = apply { this.semantics = semantics }

        fun signals(signals: List<Signal>) = signals(JsonField.of(signals))

        fun signals(signals: JsonField<List<Signal>>) = apply {
            this.signals = signals.map { it.toMutableList() }
        }

        fun addSignal(signal: Signal) = apply {
            this.signals =
                (this.signals ?: JsonField.of(mutableListOf())).also {
                    checkKnown("signals", it).add(signal)
                }
        }

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

        fun build(): SessionSignalCatalogResponse =
            SessionSignalCatalogResponse(
                checkRequired("semantics", semantics),
                checkRequired("signals", signals).map { it.toImmutable() },
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

    fun validate(): SessionSignalCatalogResponse = apply {
        if (validated) {
            return@apply
        }

        semantics()
        signals().forEach { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (semantics.asKnown().isPresent) 1 else 0) +
            (signals.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0)

    class Signal
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val description: JsonField<String>,
        private val label: JsonField<String>,
        private val severity: JsonField<Severity>,
        private val source: JsonField<Source>,
        private val value: JsonField<ValueModel>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("description")
            @ExcludeMissing
            description: JsonField<String> = JsonMissing.of(),
            @JsonProperty("label") @ExcludeMissing label: JsonField<String> = JsonMissing.of(),
            @JsonProperty("severity")
            @ExcludeMissing
            severity: JsonField<Severity> = JsonMissing.of(),
            @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
            @JsonProperty("value") @ExcludeMissing value: JsonField<ValueModel> = JsonMissing.of(),
        ) : this(description, label, severity, source, value, mutableMapOf())

        fun description(): String = description.getRequired("description")

        fun label(): String = label.getRequired("label")

        fun severity(): Severity = severity.getRequired("severity")

        fun source(): Source = source.getRequired("source")

        fun value(): ValueModel = value.getRequired("value")

        @JsonProperty("description")
        @ExcludeMissing
        fun _description(): JsonField<String> = description

        @JsonProperty("label") @ExcludeMissing fun _label(): JsonField<String> = label

        @JsonProperty("severity") @ExcludeMissing fun _severity(): JsonField<Severity> = severity

        @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

        @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<ValueModel> = value

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

            /** Returns a mutable builder for constructing an instance of [Signal]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Signal]. */
        class Builder internal constructor() {

            private var description: JsonField<String>? = null
            private var label: JsonField<String>? = null
            private var severity: JsonField<Severity>? = null
            private var source: JsonField<Source>? = null
            private var value: JsonField<ValueModel>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(signal: Signal) = apply {
                this.description = signal.description
                this.label = signal.label
                this.severity = signal.severity
                this.source = signal.source
                this.value = signal.value
                this.additionalProperties = signal.additionalProperties.toMutableMap()
            }

            fun description(description: String) = description(JsonField.of(description))

            fun description(description: JsonField<String>) = apply {
                this.description = description
            }

            fun label(label: String) = label(JsonField.of(label))

            fun label(label: JsonField<String>) = apply { this.label = label }

            fun severity(severity: Severity) = severity(JsonField.of(severity))

            fun severity(severity: JsonField<Severity>) = apply { this.severity = severity }

            fun source(source: Source) = source(JsonField.of(source))

            fun source(source: JsonField<Source>) = apply { this.source = source }

            fun value(value: ValueModel) = value(JsonField.of(value))

            fun value(value: JsonField<ValueModel>) = apply { this.value = value }

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

            fun build(): Signal =
                Signal(
                    checkRequired("description", description),
                    checkRequired("label", label),
                    checkRequired("severity", severity),
                    checkRequired("source", source),
                    checkRequired("value", value),
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

        fun validate(): Signal = apply {
            if (validated) {
                return@apply
            }

            description()
            label()
            severity().validate()
            source().validate()
            value().validate()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (description.asKnown().isPresent) 1 else 0) +
                (if (label.asKnown().isPresent) 1 else 0) +
                (severity.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (source.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (value.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        class Severity @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val INFO = of("info")

                @JvmField val WARNING = of("warning")

                @JvmField val DANGER = of("danger")

                @JvmField val SUCCESS = of("success")

                @JvmStatic fun of(value: String) = Severity(JsonField.of(value))
            }

            enum class Known {
                INFO,
                WARNING,
                DANGER,
                SUCCESS,
            }

            enum class Value {
                INFO,
                WARNING,
                DANGER,
                SUCCESS,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    INFO -> Value.INFO
                    WARNING -> Value.WARNING
                    DANGER -> Value.DANGER
                    SUCCESS -> Value.SUCCESS
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    INFO -> Known.INFO
                    WARNING -> Known.WARNING
                    DANGER -> Known.DANGER
                    SUCCESS -> Known.SUCCESS
                    else -> throw SteelInvalidDataException("Unknown Severity: $value")
                }

            fun asString(): String =
                _value().asString().orElseThrow {
                    SteelInvalidDataException("Value is not a String")
                }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Severity = apply {
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

                return other is Severity && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        class Source @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val MANUAL = of("manual")

                @JvmField val DERIVED = of("derived")

                @JvmStatic fun of(value: String) = Source(JsonField.of(value))
            }

            enum class Known {
                MANUAL,
                DERIVED,
            }

            enum class Value {
                MANUAL,
                DERIVED,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    MANUAL -> Value.MANUAL
                    DERIVED -> Value.DERIVED
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    MANUAL -> Known.MANUAL
                    DERIVED -> Known.DERIVED
                    else -> throw SteelInvalidDataException("Unknown Source: $value")
                }

            fun asString(): String =
                _value().asString().orElseThrow {
                    SteelInvalidDataException("Value is not a String")
                }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Source = apply {
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

                return other is Source && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        class ValueModel @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val CAPTCHA_ENCOUNTERED = of("captcha_encountered")

                @JvmField val PROXY_CONNECTION_FAILED = of("proxy_connection_failed")

                @JvmField val SESSION_TIMEOUT_EXCEEDED = of("session_timeout_exceeded")

                @JvmField val BOT_DETECTION_TRIGGERED = of("bot_detection_triggered")

                @JvmField val CONTEXT_RESTORED = of("context_restored")

                @JvmStatic fun of(value: String) = ValueModel(JsonField.of(value))
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
                    else -> throw SteelInvalidDataException("Unknown ValueModel: $value")
                }

            fun asString(): String =
                _value().asString().orElseThrow {
                    SteelInvalidDataException("Value is not a String")
                }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): ValueModel = apply {
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

                return other is ValueModel && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Signal &&
                this.description == other.description &&
                this.label == other.label &&
                this.severity == other.severity &&
                this.source == other.source &&
                this.value == other.value &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(description, label, severity, source, value, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Signal{description=$description, label=$label, severity=$severity, source=$source, value=$value, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionSignalCatalogResponse &&
            this.semantics == other.semantics &&
            this.signals == other.signals &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(semantics, signals, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionSignalCatalogResponse{semantics=$semantics, signals=$signals, additionalProperties=$additionalProperties}"
}
