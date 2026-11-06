package dev.steel.api.models.extensions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
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
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Response containing a list of extensions for the organization */
class ExtensionListResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val count: JsonField<Double>,
    private val extensions: JsonField<List<Extension>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("count") @ExcludeMissing count: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("extensions")
        @ExcludeMissing
        extensions: JsonField<List<Extension>> = JsonMissing.of(),
    ) : this(count, extensions, mutableMapOf())

    /** Total number of extensions */
    fun count(): Double = count.getRequired("count")

    /** List of extensions for the organization */
    fun extensions(): List<Extension> = extensions.getRequired("extensions")

    @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Double> = count

    @JsonProperty("extensions")
    @ExcludeMissing
    fun _extensions(): JsonField<List<Extension>> = extensions

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

        /** Returns a mutable builder for constructing an instance of [ExtensionListResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ExtensionListResponse]. */
    class Builder internal constructor() {

        private var count: JsonField<Double>? = null
        private var extensions: JsonField<MutableList<Extension>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(extensionListResponse: ExtensionListResponse) = apply {
            this.count = extensionListResponse.count
            this.extensions = extensionListResponse.extensions.map { it.toMutableList() }
            this.additionalProperties = extensionListResponse.additionalProperties.toMutableMap()
        }

        /** Total number of extensions */
        fun count(count: Double) = count(JsonField.of(count))

        /** Total number of extensions */
        fun count(count: JsonField<Double>) = apply { this.count = count }

        /** List of extensions for the organization */
        fun extensions(extensions: List<Extension>) = extensions(JsonField.of(extensions))

        /** List of extensions for the organization */
        fun extensions(extensions: JsonField<List<Extension>>) = apply {
            this.extensions = extensions.map { it.toMutableList() }
        }

        /** List of extensions for the organization */
        fun addExtension(extension: Extension) = apply {
            this.extensions =
                (this.extensions ?: JsonField.of(mutableListOf())).also {
                    checkKnown("extensions", it).add(extension)
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

        fun build(): ExtensionListResponse =
            ExtensionListResponse(
                checkRequired("count", count),
                checkRequired("extensions", extensions).map { it.toImmutable() },
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

    fun validate(): ExtensionListResponse = apply {
        if (validated) {
            return@apply
        }

        count()
        extensions().forEach { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (count.asKnown().isPresent) 1 else 0) +
            (extensions.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0)

    class Extension
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val createdAt: JsonField<String>,
        private val name: JsonField<String>,
        private val updatedAt: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<String> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("updatedAt")
            @ExcludeMissing
            updatedAt: JsonField<String> = JsonMissing.of(),
        ) : this(id, createdAt, name, updatedAt, mutableMapOf())

        /** Unique extension identifier (e.g., ext_12345) */
        fun id(): String = id.getRequired("id")

        /** Creation timestamp */
        fun createdAt(): String = createdAt.getRequired("createdAt")

        /** Extension name */
        fun name(): String = name.getRequired("name")

        /** Last update timestamp */
        fun updatedAt(): String = updatedAt.getRequired("updatedAt")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("createdAt") @ExcludeMissing fun _createdAt(): JsonField<String> = createdAt

        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        @JsonProperty("updatedAt") @ExcludeMissing fun _updatedAt(): JsonField<String> = updatedAt

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

            /** Returns a mutable builder for constructing an instance of [Extension]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Extension]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var createdAt: JsonField<String>? = null
            private var name: JsonField<String>? = null
            private var updatedAt: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(extension: Extension) = apply {
                this.id = extension.id
                this.createdAt = extension.createdAt
                this.name = extension.name
                this.updatedAt = extension.updatedAt
                this.additionalProperties = extension.additionalProperties.toMutableMap()
            }

            /** Unique extension identifier (e.g., ext_12345) */
            fun id(id: String) = id(JsonField.of(id))

            /** Unique extension identifier (e.g., ext_12345) */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** Creation timestamp */
            fun createdAt(createdAt: String) = createdAt(JsonField.of(createdAt))

            /** Creation timestamp */
            fun createdAt(createdAt: JsonField<String>) = apply { this.createdAt = createdAt }

            /** Extension name */
            fun name(name: String) = name(JsonField.of(name))

            /** Extension name */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /** Last update timestamp */
            fun updatedAt(updatedAt: String) = updatedAt(JsonField.of(updatedAt))

            /** Last update timestamp */
            fun updatedAt(updatedAt: JsonField<String>) = apply { this.updatedAt = updatedAt }

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

            fun build(): Extension =
                Extension(
                    checkRequired("id", id),
                    checkRequired("createdAt", createdAt),
                    checkRequired("name", name),
                    checkRequired("updatedAt", updatedAt),
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

        fun validate(): Extension = apply {
            if (validated) {
                return@apply
            }

            id()
            createdAt()
            name()
            updatedAt()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (if (createdAt.asKnown().isPresent) 1 else 0) +
                (if (name.asKnown().isPresent) 1 else 0) +
                (if (updatedAt.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Extension &&
                this.id == other.id &&
                this.createdAt == other.createdAt &&
                this.name == other.name &&
                this.updatedAt == other.updatedAt &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, createdAt, name, updatedAt, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Extension{id=$id, createdAt=$createdAt, name=$name, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ExtensionListResponse &&
            this.count == other.count &&
            this.extensions == other.extensions &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(count, extensions, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ExtensionListResponse{count=$count, extensions=$extensions, additionalProperties=$additionalProperties}"
}
