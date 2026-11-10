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

/** A JavaScript dialog: alert, confirm, prompt, or beforeunload. */
class CommandDialog
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val message: JsonField<String>,
    private val tab: JsonField<String>,
    private val type: JsonField<Type>,
    private val defaultPrompt: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("defaultPrompt")
        @ExcludeMissing
        defaultPrompt: JsonField<String> = JsonMissing.of(),
    ) : this(id, message, tab, type, defaultPrompt, mutableMapOf())

    fun id(): String = id.getRequired("id")

    fun message(): String = message.getRequired("message")

    fun tab(): String = tab.getRequired("tab")

    fun type(): Type = type.getRequired("type")

    fun defaultPrompt(): String? = defaultPrompt.getNullable("defaultPrompt")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

    @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonProperty("defaultPrompt")
    @ExcludeMissing
    fun _defaultPrompt(): JsonField<String> = defaultPrompt

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

        /** Returns a mutable builder for constructing an instance of [CommandDialog]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandDialog]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var message: JsonField<String>? = null
        private var tab: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var defaultPrompt: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandDialog: CommandDialog) = apply {
            this.id = commandDialog.id
            this.message = commandDialog.message
            this.tab = commandDialog.tab
            this.type = commandDialog.type
            this.defaultPrompt = commandDialog.defaultPrompt
            this.additionalProperties = commandDialog.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        fun id(id: JsonField<String>) = apply { this.id = id }

        fun message(message: String) = message(JsonField.of(message))

        fun message(message: JsonField<String>) = apply { this.message = message }

        fun tab(tab: String) = tab(JsonField.of(tab))

        fun tab(tab: JsonField<String>) = apply { this.tab = tab }

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun defaultPrompt(defaultPrompt: String) = defaultPrompt(JsonField.of(defaultPrompt))

        fun defaultPrompt(defaultPrompt: JsonField<String>) = apply {
            this.defaultPrompt = defaultPrompt
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

        fun build(): CommandDialog =
            CommandDialog(
                checkRequired("id", id),
                checkRequired("message", message),
                checkRequired("tab", tab),
                checkRequired("type", type),
                defaultPrompt,
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

    fun validate(): CommandDialog = apply {
        if (validated) {
            return@apply
        }

        id()
        message()
        tab()
        type().validate()
        defaultPrompt()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (message.asKnown().isPresent) 1 else 0) +
            (if (tab.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (defaultPrompt.asKnown().isPresent) 1 else 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val ALERT = of("alert")

            @JvmField val CONFIRM = of("confirm")

            @JvmField val PROMPT = of("prompt")

            @JvmField val BEFOREUNLOAD = of("beforeunload")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            ALERT,
            CONFIRM,
            PROMPT,
            BEFOREUNLOAD,
        }

        enum class Value {
            ALERT,
            CONFIRM,
            PROMPT,
            BEFOREUNLOAD,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                ALERT -> Value.ALERT
                CONFIRM -> Value.CONFIRM
                PROMPT -> Value.PROMPT
                BEFOREUNLOAD -> Value.BEFOREUNLOAD
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                ALERT -> Known.ALERT
                CONFIRM -> Known.CONFIRM
                PROMPT -> Known.PROMPT
                BEFOREUNLOAD -> Known.BEFOREUNLOAD
                else -> throw SteelInvalidDataException("Unknown Type: $value")
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

        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandDialog &&
            this.id == other.id &&
            this.message == other.message &&
            this.tab == other.tab &&
            this.type == other.type &&
            this.defaultPrompt == other.defaultPrompt &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, message, tab, type, defaultPrompt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandDialog{id=$id, message=$message, tab=$tab, type=$type, defaultPrompt=$defaultPrompt, additionalProperties=$additionalProperties}"
}
