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

/** Input still held down on a tab. It applies to later input until released. */
class CommandHeld
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val keys: JsonField<List<String>>,
    private val tab: JsonField<String>,
    private val button: JsonField<Button>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("keys") @ExcludeMissing keys: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
        @JsonProperty("button") @ExcludeMissing button: JsonField<Button> = JsonMissing.of(),
    ) : this(keys, tab, button, mutableMapOf())

    /** Keys still held down, for example with keyboard down. */
    fun keys(): List<String> = keys.getRequired("keys")

    fun tab(): String = tab.getRequired("tab")

    /** A mouse button held down with mouse down. */
    fun button(): Button? = button.getNullable("button")

    @JsonProperty("keys") @ExcludeMissing fun _keys(): JsonField<List<String>> = keys

    @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

    @JsonProperty("button") @ExcludeMissing fun _button(): JsonField<Button> = button

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

        /** Returns a mutable builder for constructing an instance of [CommandHeld]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandHeld]. */
    class Builder internal constructor() {

        private var keys: JsonField<MutableList<String>>? = null
        private var tab: JsonField<String>? = null
        private var button: JsonField<Button> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandHeld: CommandHeld) = apply {
            this.keys = commandHeld.keys.map { it.toMutableList() }
            this.tab = commandHeld.tab
            this.button = commandHeld.button
            this.additionalProperties = commandHeld.additionalProperties.toMutableMap()
        }

        /** Keys still held down, for example with keyboard down. */
        fun keys(keys: List<String>) = keys(JsonField.of(keys))

        /** Keys still held down, for example with keyboard down. */
        fun keys(keys: JsonField<List<String>>) = apply {
            this.keys = keys.map { it.toMutableList() }
        }

        /** Keys still held down, for example with keyboard down. */
        fun addKey(key: String) = apply {
            this.keys =
                (this.keys ?: JsonField.of(mutableListOf())).also {
                    checkKnown("keys", it).add(key)
                }
        }

        fun tab(tab: String) = tab(JsonField.of(tab))

        fun tab(tab: JsonField<String>) = apply { this.tab = tab }

        /** A mouse button held down with mouse down. */
        fun button(button: Button) = button(JsonField.of(button))

        /** A mouse button held down with mouse down. */
        fun button(button: JsonField<Button>) = apply { this.button = button }

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

        fun build(): CommandHeld =
            CommandHeld(
                checkRequired("keys", keys).map { it.toImmutable() },
                checkRequired("tab", tab),
                button,
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

    fun validate(): CommandHeld = apply {
        if (validated) {
            return@apply
        }

        keys()
        tab()
        button()?.let { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (keys.asKnown().getOrNull()?.let { it.size } ?: 0) +
            (if (tab.asKnown().isPresent) 1 else 0) +
            (button.asKnown().getOrNull()?.let { it.validity() } ?: 0)

    class Button @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val LEFT = of("left")

            @JvmField val RIGHT = of("right")

            @JvmField val MIDDLE = of("middle")

            @JvmStatic fun of(value: String) = Button(JsonField.of(value))
        }

        enum class Known {
            LEFT,
            RIGHT,
            MIDDLE,
        }

        enum class Value {
            LEFT,
            RIGHT,
            MIDDLE,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                LEFT -> Value.LEFT
                RIGHT -> Value.RIGHT
                MIDDLE -> Value.MIDDLE
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                LEFT -> Known.LEFT
                RIGHT -> Known.RIGHT
                MIDDLE -> Known.MIDDLE
                else -> throw SteelInvalidDataException("Unknown Button: $value")
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

        fun validate(): Button = apply {
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

            return other is Button && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandHeld &&
            this.keys == other.keys &&
            this.tab == other.tab &&
            this.button == other.button &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(keys, tab, button, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandHeld{keys=$keys, tab=$tab, button=$button, additionalProperties=$additionalProperties}"
}
