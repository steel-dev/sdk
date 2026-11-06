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
    private val accepted: JsonField<Boolean>,
    private val by: JsonField<By>,
    private val defaultPrompt: JsonField<String>,
    private val text: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("accepted") @ExcludeMissing accepted: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("by") @ExcludeMissing by: JsonField<By> = JsonMissing.of(),
        @JsonProperty("defaultPrompt")
        @ExcludeMissing
        defaultPrompt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
    ) : this(id, message, tab, type, accepted, by, defaultPrompt, text, mutableMapOf())

    fun id(): String = id.getRequired("id")

    fun message(): String = message.getRequired("message")

    fun tab(): String = tab.getRequired("tab")

    fun type(): Type = type.getRequired("type")

    fun accepted(): Boolean? = accepted.getNullable("accepted")

    fun by(): By? = by.getNullable("by")

    fun defaultPrompt(): String? = defaultPrompt.getNullable("defaultPrompt")

    fun text(): String? = text.getNullable("text")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

    @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonProperty("accepted") @ExcludeMissing fun _accepted(): JsonField<Boolean> = accepted

    @JsonProperty("by") @ExcludeMissing fun _by(): JsonField<By> = by

    @JsonProperty("defaultPrompt")
    @ExcludeMissing
    fun _defaultPrompt(): JsonField<String> = defaultPrompt

    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

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
        private var accepted: JsonField<Boolean> = JsonMissing.of()
        private var by: JsonField<By> = JsonMissing.of()
        private var defaultPrompt: JsonField<String> = JsonMissing.of()
        private var text: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandDialog: CommandDialog) = apply {
            this.id = commandDialog.id
            this.message = commandDialog.message
            this.tab = commandDialog.tab
            this.type = commandDialog.type
            this.accepted = commandDialog.accepted
            this.by = commandDialog.by
            this.defaultPrompt = commandDialog.defaultPrompt
            this.text = commandDialog.text
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

        fun accepted(accepted: Boolean) = accepted(JsonField.of(accepted))

        fun accepted(accepted: JsonField<Boolean>) = apply { this.accepted = accepted }

        fun by(by: By) = by(JsonField.of(by))

        fun by(by: JsonField<By>) = apply { this.by = by }

        fun defaultPrompt(defaultPrompt: String) = defaultPrompt(JsonField.of(defaultPrompt))

        fun defaultPrompt(defaultPrompt: JsonField<String>) = apply {
            this.defaultPrompt = defaultPrompt
        }

        fun text(text: String) = text(JsonField.of(text))

        fun text(text: JsonField<String>) = apply { this.text = text }

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
                accepted,
                by,
                defaultPrompt,
                text,
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
        accepted()
        by()?.let { it.validate() }
        defaultPrompt()
        text()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (message.asKnown().isPresent) 1 else 0) +
            (if (tab.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (accepted.asKnown().isPresent) 1 else 0) +
            (by.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (defaultPrompt.asKnown().isPresent) 1 else 0) +
            (if (text.asKnown().isPresent) 1 else 0)

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

    class By @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val COMMAND = of("command")

            @JvmField val CLIENT = of("client")

            @JvmField val CLOSED = of("closed")

            @JvmStatic fun of(value: String) = By(JsonField.of(value))
        }

        enum class Known {
            COMMAND,
            CLIENT,
            CLOSED,
        }

        enum class Value {
            COMMAND,
            CLIENT,
            CLOSED,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                COMMAND -> Value.COMMAND
                CLIENT -> Value.CLIENT
                CLOSED -> Value.CLOSED
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                COMMAND -> Known.COMMAND
                CLIENT -> Known.CLIENT
                CLOSED -> Known.CLOSED
                else -> throw SteelInvalidDataException("Unknown By: $value")
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

        fun validate(): By = apply {
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

            return other is By && value == other.value
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
            this.accepted == other.accepted &&
            this.by == other.by &&
            this.defaultPrompt == other.defaultPrompt &&
            this.text == other.text &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            message,
            tab,
            type,
            accepted,
            by,
            defaultPrompt,
            text,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandDialog{id=$id, message=$message, tab=$tab, type=$type, accepted=$accepted, by=$by, defaultPrompt=$defaultPrompt, text=$text, additionalProperties=$additionalProperties}"
}
