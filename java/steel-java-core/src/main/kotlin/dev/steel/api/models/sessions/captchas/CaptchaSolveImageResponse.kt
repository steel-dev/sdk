package dev.steel.api.models.sessions.captchas

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

class CaptchaSolveImageResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val success: JsonField<Boolean>,
    private val message: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("success") @ExcludeMissing success: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
    ) : this(success, message, mutableMapOf())

    /** Whether the action was successful */
    fun success(): Boolean = success.getRequired("success")

    /** Response message */
    fun message(): Optional<String> = message.getOptional("message")

    @JsonProperty("success") @ExcludeMissing fun _success(): JsonField<Boolean> = success

    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

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
         * Returns a mutable builder for constructing an instance of [CaptchaSolveImageResponse].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CaptchaSolveImageResponse]. */
    class Builder internal constructor() {

        private var success: JsonField<Boolean>? = null
        private var message: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(captchaSolveImageResponse: CaptchaSolveImageResponse) = apply {
            this.success = captchaSolveImageResponse.success
            this.message = captchaSolveImageResponse.message
            this.additionalProperties =
                captchaSolveImageResponse.additionalProperties.toMutableMap()
        }

        /** Whether the action was successful */
        fun success(success: Boolean) = success(JsonField.of(success))

        /** Whether the action was successful */
        fun success(success: JsonField<Boolean>) = apply { this.success = success }

        /** Response message */
        fun message(message: String) = message(JsonField.of(message))

        /** Response message */
        fun message(message: JsonField<String>) = apply { this.message = message }

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

        fun build(): CaptchaSolveImageResponse =
            CaptchaSolveImageResponse(
                checkRequired("success", success),
                message,
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

    fun validate(): CaptchaSolveImageResponse = apply {
        if (validated) {
            return@apply
        }

        success()
        message()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (success.asKnown().isPresent) 1 else 0) + (if (message.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CaptchaSolveImageResponse &&
            this.success == other.success &&
            this.message == other.message &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(success, message, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CaptchaSolveImageResponse{success=$success, message=$message, additionalProperties=$additionalProperties}"
}
