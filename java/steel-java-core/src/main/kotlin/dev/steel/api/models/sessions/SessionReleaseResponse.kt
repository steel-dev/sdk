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

/** Response for releasing a single session. */
class SessionReleaseResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val message: JsonField<String>,
    private val success: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        @JsonProperty("success") @ExcludeMissing success: JsonField<Boolean> = JsonMissing.of(),
    ) : this(message, success, mutableMapOf())

    /** Details about the outcome of the release operation */
    fun message(): String = message.getRequired("message")

    /** Indicates if the session was successfully released */
    fun success(): Boolean = success.getRequired("success")

    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

    @JsonProperty("success") @ExcludeMissing fun _success(): JsonField<Boolean> = success

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

        /** Returns a mutable builder for constructing an instance of [SessionReleaseResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionReleaseResponse]. */
    class Builder internal constructor() {

        private var message: JsonField<String>? = null
        private var success: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionReleaseResponse: SessionReleaseResponse) = apply {
            this.message = sessionReleaseResponse.message
            this.success = sessionReleaseResponse.success
            this.additionalProperties = sessionReleaseResponse.additionalProperties.toMutableMap()
        }

        /** Details about the outcome of the release operation */
        fun message(message: String) = message(JsonField.of(message))

        /** Details about the outcome of the release operation */
        fun message(message: JsonField<String>) = apply { this.message = message }

        /** Indicates if the session was successfully released */
        fun success(success: Boolean) = success(JsonField.of(success))

        /** Indicates if the session was successfully released */
        fun success(success: JsonField<Boolean>) = apply { this.success = success }

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

        fun build(): SessionReleaseResponse =
            SessionReleaseResponse(
                checkRequired("message", message),
                checkRequired("success", success),
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

    fun validate(): SessionReleaseResponse = apply {
        if (validated) {
            return@apply
        }

        message()
        success()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (message.asKnown().isPresent) 1 else 0) + (if (success.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionReleaseResponse &&
            this.message == other.message &&
            this.success == other.success &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(message, success, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionReleaseResponse{message=$message, success=$success, additionalProperties=$additionalProperties}"
}
