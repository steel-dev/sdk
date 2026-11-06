package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional

class SessionComputerResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val base64Image: JsonField<String>,
    private val error: JsonField<String>,
    private val output: JsonField<String>,
    private val system: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("base64_image")
        @ExcludeMissing
        base64Image: JsonField<String> = JsonMissing.of(),
        @JsonProperty("error") @ExcludeMissing error: JsonField<String> = JsonMissing.of(),
        @JsonProperty("output") @ExcludeMissing output: JsonField<String> = JsonMissing.of(),
        @JsonProperty("system") @ExcludeMissing system: JsonField<String> = JsonMissing.of(),
    ) : this(base64Image, error, output, system, mutableMapOf())

    /** Base64 encoded screenshot if requested */
    fun base64Image(): Optional<String> = base64Image.getOptional("base64_image")

    /** Error message if action failed */
    fun error(): Optional<String> = error.getOptional("error")

    /** Output message from the action */
    fun output(): Optional<String> = output.getOptional("output")

    /** System information */
    fun system(): Optional<String> = system.getOptional("system")

    @JsonProperty("base64_image")
    @ExcludeMissing
    fun _base64Image(): JsonField<String> = base64Image

    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<String> = error

    @JsonProperty("output") @ExcludeMissing fun _output(): JsonField<String> = output

    @JsonProperty("system") @ExcludeMissing fun _system(): JsonField<String> = system

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

        /** Returns a mutable builder for constructing an instance of [SessionComputerResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionComputerResponse]. */
    class Builder internal constructor() {

        private var base64Image: JsonField<String> = JsonMissing.of()
        private var error: JsonField<String> = JsonMissing.of()
        private var output: JsonField<String> = JsonMissing.of()
        private var system: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionComputerResponse: SessionComputerResponse) = apply {
            this.base64Image = sessionComputerResponse.base64Image
            this.error = sessionComputerResponse.error
            this.output = sessionComputerResponse.output
            this.system = sessionComputerResponse.system
            this.additionalProperties = sessionComputerResponse.additionalProperties.toMutableMap()
        }

        /** Base64 encoded screenshot if requested */
        fun base64Image(base64Image: String) = base64Image(JsonField.of(base64Image))

        /** Base64 encoded screenshot if requested */
        fun base64Image(base64Image: JsonField<String>) = apply { this.base64Image = base64Image }

        /** Error message if action failed */
        fun error(error: String) = error(JsonField.of(error))

        /** Error message if action failed */
        fun error(error: JsonField<String>) = apply { this.error = error }

        /** Output message from the action */
        fun output(output: String) = output(JsonField.of(output))

        /** Output message from the action */
        fun output(output: JsonField<String>) = apply { this.output = output }

        /** System information */
        fun system(system: String) = system(JsonField.of(system))

        /** System information */
        fun system(system: JsonField<String>) = apply { this.system = system }

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

        fun build(): SessionComputerResponse =
            SessionComputerResponse(
                base64Image,
                error,
                output,
                system,
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

    fun validate(): SessionComputerResponse = apply {
        if (validated) {
            return@apply
        }

        base64Image()
        error()
        output()
        system()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (base64Image.asKnown().isPresent) 1 else 0) +
            (if (error.asKnown().isPresent) 1 else 0) +
            (if (output.asKnown().isPresent) 1 else 0) +
            (if (system.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionComputerResponse &&
            this.base64Image == other.base64Image &&
            this.error == other.error &&
            this.output == other.output &&
            this.system == other.system &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(base64Image, error, output, system, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionComputerResponse{base64Image=$base64Image, error=$error, output=$output, system=$system, additionalProperties=$additionalProperties}"
}
