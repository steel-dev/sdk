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
import dev.steel.api.core.checkRequired
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** Session cost breakdown in US dollars */
class SessionCostResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val currency: JsonValue,
    private val totalCost: JsonField<Double>,
    private val unit: JsonValue,
    private val usage: JsonField<Usage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("currency") @ExcludeMissing currency: JsonValue = JsonMissing.of(),
        @JsonProperty("totalCost") @ExcludeMissing totalCost: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("unit") @ExcludeMissing unit: JsonValue = JsonMissing.of(),
        @JsonProperty("usage") @ExcludeMissing usage: JsonField<Usage> = JsonMissing.of(),
    ) : this(id, currency, totalCost, unit, usage, mutableMapOf())

    /** Session ID */
    fun id(): String = id.getRequired("id")

    /**
     * Exact total session cost in US dollars (e.g. 0.031905), with no rounding to whole cents.
     * Rounded only to micro-dollar precision (6 decimal places) for precise billing passthrough.
     */
    fun totalCost(): Double = totalCost.getRequired("totalCost")

    /** Billable usage inputs used to calculate the costs */
    fun usage(): Usage = usage.getRequired("usage")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /** Expected to always return `JsonValue.from("usd")`. It is set by default. */
    @JsonProperty("currency") @ExcludeMissing fun _currency(): JsonValue = currency

    @JsonProperty("totalCost") @ExcludeMissing fun _totalCost(): JsonField<Double> = totalCost

    /** Expected to always return `JsonValue.from("dollar")`. It is set by default. */
    @JsonProperty("unit") @ExcludeMissing fun _unit(): JsonValue = unit

    @JsonProperty("usage") @ExcludeMissing fun _usage(): JsonField<Usage> = usage

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

        /** Returns a mutable builder for constructing an instance of [SessionCostResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionCostResponse]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var currency: JsonValue = JsonValue.from("usd")
        private var totalCost: JsonField<Double>? = null
        private var unit: JsonValue = JsonValue.from("dollar")
        private var usage: JsonField<Usage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionCostResponse: SessionCostResponse) = apply {
            this.id = sessionCostResponse.id
            this.currency = sessionCostResponse.currency
            this.totalCost = sessionCostResponse.totalCost
            this.unit = sessionCostResponse.unit
            this.usage = sessionCostResponse.usage
            this.additionalProperties = sessionCostResponse.additionalProperties.toMutableMap()
        }

        /** Session ID */
        fun id(id: String) = id(JsonField.of(id))

        /** Session ID */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Currency used for the cost values */
        fun currency(currency: JsonValue) = apply { this.currency = currency }

        /**
         * Exact total session cost in US dollars (e.g. 0.031905), with no rounding to whole cents.
         * Rounded only to micro-dollar precision (6 decimal places) for precise billing
         * passthrough.
         */
        fun totalCost(totalCost: Double) = totalCost(JsonField.of(totalCost))

        /**
         * Exact total session cost in US dollars (e.g. 0.031905), with no rounding to whole cents.
         * Rounded only to micro-dollar precision (6 decimal places) for precise billing
         * passthrough.
         */
        fun totalCost(totalCost: JsonField<Double>) = apply { this.totalCost = totalCost }

        /** Cost unit. Values are expressed in US dollars. */
        fun unit(unit: JsonValue) = apply { this.unit = unit }

        /** Billable usage inputs used to calculate the costs */
        fun usage(usage: Usage) = usage(JsonField.of(usage))

        /** Billable usage inputs used to calculate the costs */
        fun usage(usage: JsonField<Usage>) = apply { this.usage = usage }

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

        fun build(): SessionCostResponse =
            SessionCostResponse(
                checkRequired("id", id),
                currency,
                checkRequired("totalCost", totalCost),
                unit,
                checkRequired("usage", usage),
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

    fun validate(): SessionCostResponse = apply {
        if (validated) {
            return@apply
        }

        id()
        _currency().let {
            if (it != JsonValue.from("usd")) {
                throw SteelInvalidDataException("'currency' is invalid, received $it")
            }
        }
        totalCost()
        _unit().let {
            if (it != JsonValue.from("dollar")) {
                throw SteelInvalidDataException("'unit' is invalid, received $it")
            }
        }
        usage().validate()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (currency.let { if (it == JsonValue.from("usd")) 1 else 0 }) +
            (if (totalCost.asKnown().isPresent) 1 else 0) +
            (unit.let { if (it == JsonValue.from("dollar")) 1 else 0 }) +
            (usage.asKnown().getOrNull()?.let { it.validity() } ?: 0)

    /** Billable usage inputs used to calculate the costs */
    class Usage
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val browser: JsonField<Browser>,
        private val captcha: JsonField<Captcha>,
        private val proxy: JsonField<Proxy>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("browser") @ExcludeMissing browser: JsonField<Browser> = JsonMissing.of(),
            @JsonProperty("captcha") @ExcludeMissing captcha: JsonField<Captcha> = JsonMissing.of(),
            @JsonProperty("proxy") @ExcludeMissing proxy: JsonField<Proxy> = JsonMissing.of(),
        ) : this(browser, captcha, proxy, mutableMapOf())

        fun browser(): Browser = browser.getRequired("browser")

        fun captcha(): Captcha = captcha.getRequired("captcha")

        fun proxy(): Proxy = proxy.getRequired("proxy")

        @JsonProperty("browser") @ExcludeMissing fun _browser(): JsonField<Browser> = browser

        @JsonProperty("captcha") @ExcludeMissing fun _captcha(): JsonField<Captcha> = captcha

        @JsonProperty("proxy") @ExcludeMissing fun _proxy(): JsonField<Proxy> = proxy

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

            /** Returns a mutable builder for constructing an instance of [Usage]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Usage]. */
        class Builder internal constructor() {

            private var browser: JsonField<Browser>? = null
            private var captcha: JsonField<Captcha>? = null
            private var proxy: JsonField<Proxy>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(usage: Usage) = apply {
                this.browser = usage.browser
                this.captcha = usage.captcha
                this.proxy = usage.proxy
                this.additionalProperties = usage.additionalProperties.toMutableMap()
            }

            fun browser(browser: Browser) = browser(JsonField.of(browser))

            fun browser(browser: JsonField<Browser>) = apply { this.browser = browser }

            fun captcha(captcha: Captcha) = captcha(JsonField.of(captcha))

            fun captcha(captcha: JsonField<Captcha>) = apply { this.captcha = captcha }

            fun proxy(proxy: Proxy) = proxy(JsonField.of(proxy))

            fun proxy(proxy: JsonField<Proxy>) = apply { this.proxy = proxy }

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

            fun build(): Usage =
                Usage(
                    checkRequired("browser", browser),
                    checkRequired("captcha", captcha),
                    checkRequired("proxy", proxy),
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

        fun validate(): Usage = apply {
            if (validated) {
                return@apply
            }

            browser().validate()
            captcha().validate()
            proxy().validate()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (browser.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (captcha.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (proxy.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        class Browser
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val unit: JsonField<UnitModel>,
            private val value: JsonField<Double>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("unit") @ExcludeMissing unit: JsonField<UnitModel> = JsonMissing.of(),
                @JsonProperty("value") @ExcludeMissing value: JsonField<Double> = JsonMissing.of(),
            ) : this(unit, value, mutableMapOf())

            /** Billing unit for browser usage */
            fun unit(): UnitModel = unit.getRequired("unit")

            /** Billable browser usage value */
            fun value(): Double = value.getRequired("value")

            @JsonProperty("unit") @ExcludeMissing fun _unit(): JsonField<UnitModel> = unit

            @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<Double> = value

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

                /** Returns a mutable builder for constructing an instance of [Browser]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Browser]. */
            class Builder internal constructor() {

                private var unit: JsonField<UnitModel>? = null
                private var value: JsonField<Double>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(browser: Browser) = apply {
                    this.unit = browser.unit
                    this.value = browser.value
                    this.additionalProperties = browser.additionalProperties.toMutableMap()
                }

                /** Billing unit for browser usage */
                fun unit(unit: UnitModel) = unit(JsonField.of(unit))

                /** Billing unit for browser usage */
                fun unit(unit: JsonField<UnitModel>) = apply { this.unit = unit }

                /** Billable browser usage value */
                fun value(value: Double) = value(JsonField.of(value))

                /** Billable browser usage value */
                fun value(value: JsonField<Double>) = apply { this.value = value }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                fun build(): Browser =
                    Browser(
                        checkRequired("unit", unit),
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

            fun validate(): Browser = apply {
                if (validated) {
                    return@apply
                }

                unit().validate()
                value()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (unit.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (value.asKnown().isPresent) 1 else 0)

            class UnitModel @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val MINUTE = of("minute")

                    @JvmField val SECOND = of("second")

                    @JvmStatic fun of(value: String) = UnitModel(JsonField.of(value))
                }

                enum class Known {
                    MINUTE,
                    SECOND,
                }

                enum class Value {
                    MINUTE,
                    SECOND,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        MINUTE -> Value.MINUTE
                        SECOND -> Value.SECOND
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        MINUTE -> Known.MINUTE
                        SECOND -> Known.SECOND
                        else -> throw SteelInvalidDataException("Unknown UnitModel: $value")
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

                fun validate(): UnitModel = apply {
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

                    return other is UnitModel && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Browser &&
                    this.unit == other.unit &&
                    this.value == other.value &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(unit, value, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Browser{unit=$unit, value=$value, additionalProperties=$additionalProperties}"
        }

        class Captcha
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val solves: JsonField<Long>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("solves") @ExcludeMissing solves: JsonField<Long> = JsonMissing.of()
            ) : this(solves, mutableMapOf())

            /** Billable captcha solves */
            fun solves(): Long = solves.getRequired("solves")

            @JsonProperty("solves") @ExcludeMissing fun _solves(): JsonField<Long> = solves

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

                /** Returns a mutable builder for constructing an instance of [Captcha]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Captcha]. */
            class Builder internal constructor() {

                private var solves: JsonField<Long>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(captcha: Captcha) = apply {
                    this.solves = captcha.solves
                    this.additionalProperties = captcha.additionalProperties.toMutableMap()
                }

                /** Billable captcha solves */
                fun solves(solves: Long) = solves(JsonField.of(solves))

                /** Billable captcha solves */
                fun solves(solves: JsonField<Long>) = apply { this.solves = solves }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                fun build(): Captcha =
                    Captcha(checkRequired("solves", solves), additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Captcha = apply {
                if (validated) {
                    return@apply
                }

                solves()
                validated = true
            }

            @JvmSynthetic internal fun validity(): Int = (if (solves.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Captcha &&
                    this.solves == other.solves &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(solves, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Captcha{solves=$solves, additionalProperties=$additionalProperties}"
        }

        class Proxy
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val bytes: JsonField<Long>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("bytes") @ExcludeMissing bytes: JsonField<Long> = JsonMissing.of()
            ) : this(bytes, mutableMapOf())

            /** Billable Steel proxy bytes for the session */
            fun bytes(): Long = bytes.getRequired("bytes")

            @JsonProperty("bytes") @ExcludeMissing fun _bytes(): JsonField<Long> = bytes

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

                /** Returns a mutable builder for constructing an instance of [Proxy]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Proxy]. */
            class Builder internal constructor() {

                private var bytes: JsonField<Long>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(proxy: Proxy) = apply {
                    this.bytes = proxy.bytes
                    this.additionalProperties = proxy.additionalProperties.toMutableMap()
                }

                /** Billable Steel proxy bytes for the session */
                fun bytes(bytes: Long) = bytes(JsonField.of(bytes))

                /** Billable Steel proxy bytes for the session */
                fun bytes(bytes: JsonField<Long>) = apply { this.bytes = bytes }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                fun build(): Proxy =
                    Proxy(checkRequired("bytes", bytes), additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Proxy = apply {
                if (validated) {
                    return@apply
                }

                bytes()
                validated = true
            }

            @JvmSynthetic internal fun validity(): Int = (if (bytes.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Proxy &&
                    this.bytes == other.bytes &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(bytes, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Proxy{bytes=$bytes, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Usage &&
                this.browser == other.browser &&
                this.captcha == other.captcha &&
                this.proxy == other.proxy &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(browser, captcha, proxy, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Usage{browser=$browser, captcha=$captcha, proxy=$proxy, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionCostResponse &&
            this.id == other.id &&
            this.currency == other.currency &&
            this.totalCost == other.totalCost &&
            this.unit == other.unit &&
            this.usage == other.usage &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, currency, totalCost, unit, usage, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionCostResponse{id=$id, currency=$currency, totalCost=$totalCost, unit=$unit, usage=$usage, additionalProperties=$additionalProperties}"
}
