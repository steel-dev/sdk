package dev.steel.api.models.apikeys

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

class ApiKey
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val key: JsonField<String>,
    private val projectId: JsonField<String>,
    private val scopeType: JsonField<ScopeType>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val name: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("createdAt")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("key") @ExcludeMissing key: JsonField<String> = JsonMissing.of(),
        @JsonProperty("projectId") @ExcludeMissing projectId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("scopeType")
        @ExcludeMissing
        scopeType: JsonField<ScopeType> = JsonMissing.of(),
        @JsonProperty("updatedAt")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
    ) : this(id, createdAt, key, projectId, scopeType, updatedAt, name, mutableMapOf())

    fun id(): String = id.getRequired("id")

    fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

    /**
     * The full secret on creation or rotation only. Metadata reads return a masked preview. Store a
     * newly created secret securely; it cannot be retrieved later.
     */
    fun key(): String = key.getRequired("key")

    /** The pinned project, or null for a global key, which binds to no project. */
    fun projectId(): Optional<String> = projectId.getOptional("projectId")

    /**
     * `project` pins the key to one project. `global` authorizes access across the organization and
     * has no project binding.
     */
    fun scopeType(): ScopeType = scopeType.getRequired("scopeType")

    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updatedAt")

    fun name(): Optional<String> = name.getOptional("name")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonProperty("createdAt")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<String> = key

    @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

    @JsonProperty("scopeType") @ExcludeMissing fun _scopeType(): JsonField<ScopeType> = scopeType

    @JsonProperty("updatedAt")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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

        /** Returns a mutable builder for constructing an instance of [ApiKey]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ApiKey]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var key: JsonField<String>? = null
        private var projectId: JsonField<String>? = null
        private var scopeType: JsonField<ScopeType>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var name: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(apiKey: ApiKey) = apply {
            this.id = apiKey.id
            this.createdAt = apiKey.createdAt
            this.key = apiKey.key
            this.projectId = apiKey.projectId
            this.scopeType = apiKey.scopeType
            this.updatedAt = apiKey.updatedAt
            this.name = apiKey.name
            this.additionalProperties = apiKey.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        fun id(id: JsonField<String>) = apply { this.id = id }

        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /**
         * The full secret on creation or rotation only. Metadata reads return a masked preview.
         * Store a newly created secret securely; it cannot be retrieved later.
         */
        fun key(key: String) = key(JsonField.of(key))

        /**
         * The full secret on creation or rotation only. Metadata reads return a masked preview.
         * Store a newly created secret securely; it cannot be retrieved later.
         */
        fun key(key: JsonField<String>) = apply { this.key = key }

        /** The pinned project, or null for a global key, which binds to no project. */
        fun projectId(projectId: String?) = projectId(JsonField.ofNullable(projectId))

        /** The pinned project, or null for a global key, which binds to no project. */
        fun projectId(projectId: Optional<String>) = projectId(projectId.getOrNull())

        /** The pinned project, or null for a global key, which binds to no project. */
        fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

        /**
         * `project` pins the key to one project. `global` authorizes access across the organization
         * and has no project binding.
         */
        fun scopeType(scopeType: ScopeType) = scopeType(JsonField.of(scopeType))

        /**
         * `project` pins the key to one project. `global` authorizes access across the organization
         * and has no project binding.
         */
        fun scopeType(scopeType: JsonField<ScopeType>) = apply { this.scopeType = scopeType }

        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        fun name(name: String) = name(JsonField.of(name))

        fun name(name: JsonField<String>) = apply { this.name = name }

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

        fun build(): ApiKey =
            ApiKey(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("key", key),
                checkRequired("projectId", projectId),
                checkRequired("scopeType", scopeType),
                checkRequired("updatedAt", updatedAt),
                name,
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

    fun validate(): ApiKey = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        key()
        projectId()
        scopeType().validate()
        updatedAt()
        name()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (key.asKnown().isPresent) 1 else 0) +
            (if (projectId.asKnown().isPresent) 1 else 0) +
            (scopeType.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0)

    class ScopeType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val PROJECT = of("project")

            @JvmField val GLOBAL = of("global")

            @JvmStatic fun of(value: String) = ScopeType(JsonField.of(value))
        }

        enum class Known {
            PROJECT,
            GLOBAL,
        }

        enum class Value {
            PROJECT,
            GLOBAL,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                PROJECT -> Value.PROJECT
                GLOBAL -> Value.GLOBAL
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                PROJECT -> Known.PROJECT
                GLOBAL -> Known.GLOBAL
                else -> throw SteelInvalidDataException("Unknown ScopeType: $value")
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

        fun validate(): ScopeType = apply {
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

            return other is ScopeType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ApiKey &&
            this.id == other.id &&
            this.createdAt == other.createdAt &&
            this.key == other.key &&
            this.projectId == other.projectId &&
            this.scopeType == other.scopeType &&
            this.updatedAt == other.updatedAt &&
            this.name == other.name &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            key,
            projectId,
            scopeType,
            updatedAt,
            name,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ApiKey{id=$id, createdAt=$createdAt, key=$key, projectId=$projectId, scopeType=$scopeType, updatedAt=$updatedAt, name=$name, additionalProperties=$additionalProperties}"
}
