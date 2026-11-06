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
import kotlin.io.path.name

/** Fails the command when the element does not have this role or name. */
class CommandExpectation
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val name: JsonField<String>,
    private val role: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("role") @ExcludeMissing role: JsonField<String> = JsonMissing.of(),
    ) : this(name, role, mutableMapOf())

    fun name(): Optional<String> = name.getOptional("name")

    fun role(): Optional<String> = role.getOptional("role")

    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<String> = role

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

        /** Returns a mutable builder for constructing an instance of [CommandExpectation]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandExpectation]. */
    class Builder internal constructor() {

        private var name: JsonField<String> = JsonMissing.of()
        private var role: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandExpectation: CommandExpectation) = apply {
            this.name = commandExpectation.name
            this.role = commandExpectation.role
            this.additionalProperties = commandExpectation.additionalProperties.toMutableMap()
        }

        fun name(name: String) = name(JsonField.of(name))

        fun name(name: JsonField<String>) = apply { this.name = name }

        fun role(role: String) = role(JsonField.of(role))

        fun role(role: JsonField<String>) = apply { this.role = role }

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

        fun build(): CommandExpectation =
            CommandExpectation(name, role, additionalProperties.toMutableMap())
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: SteelInvalidDataException) {
            false
        }

    private var validated: Boolean = false

    fun validate(): CommandExpectation = apply {
        if (validated) {
            return@apply
        }

        name()
        role()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (name.asKnown().isPresent) 1 else 0) + (if (role.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandExpectation &&
            this.name == other.name &&
            this.role == other.role &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(name, role, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandExpectation{name=$name, role=$role, additionalProperties=$additionalProperties}"
}
