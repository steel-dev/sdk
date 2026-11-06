package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.checkRequired
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional

class CommandErrorInfo
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val code: JsonField<String>,
    private val message: JsonField<String>,
    private val reason: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("code") @ExcludeMissing code: JsonField<String> = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        @JsonProperty("reason") @ExcludeMissing reason: JsonField<String> = JsonMissing.of(),
    ) : this(code, message, reason, mutableMapOf())

    /** Error code, such as stale_ref or not_actionable. */
    fun code(): String = code.getRequired("code")

    fun message(): String = message.getRequired("message")

    /** More detail, such as covered or detached. */
    fun reason(): Optional<String> = reason.getOptional("reason")

    @JsonProperty("code") @ExcludeMissing fun _code(): JsonField<String> = code

    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

    @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<String> = reason

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

        /** Returns a mutable builder for constructing an instance of [CommandErrorInfo]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandErrorInfo]. */
    class Builder internal constructor() {

        private var code: JsonField<String>? = null
        private var message: JsonField<String>? = null
        private var reason: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandErrorInfo: CommandErrorInfo) = apply {
            this.code = commandErrorInfo.code
            this.message = commandErrorInfo.message
            this.reason = commandErrorInfo.reason
            this.additionalProperties = commandErrorInfo.additionalProperties.toMutableMap()
        }

        /** Error code, such as stale_ref or not_actionable. */
        fun code(code: String) = code(JsonField.of(code))

        /** Error code, such as stale_ref or not_actionable. */
        fun code(code: JsonField<String>) = apply { this.code = code }

        fun message(message: String) = message(JsonField.of(message))

        fun message(message: JsonField<String>) = apply { this.message = message }

        /** More detail, such as covered or detached. */
        fun reason(reason: String) = reason(JsonField.of(reason))

        /** More detail, such as covered or detached. */
        fun reason(reason: JsonField<String>) = apply { this.reason = reason }

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

        fun build(): CommandErrorInfo =
            CommandErrorInfo(
                checkRequired("code", code),
                checkRequired("message", message),
                reason,
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

    fun validate(): CommandErrorInfo = apply {
        if (validated) {
            return@apply
        }

        code()
        message()
        reason()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (code.asKnown().isPresent) 1 else 0) +
            (if (message.asKnown().isPresent) 1 else 0) +
            (if (reason.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandErrorInfo &&
            this.code == other.code &&
            this.message == other.message &&
            this.reason == other.reason &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(code, message, reason, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandErrorInfo{code=$code, message=$message, reason=$reason, additionalProperties=$additionalProperties}"
}
