package dev.steel.api.models.projects

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.io.path.name

class Project
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val isDefault: JsonField<Boolean>,
    private val isProduction: JsonField<Boolean>,
    private val name: JsonField<String>,
    private val promotedToProductionAt: JsonField<OffsetDateTime>,
    private val slug: JsonField<String>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("createdAt")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("isDefault") @ExcludeMissing isDefault: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isProduction")
        @ExcludeMissing
        isProduction: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("promotedToProductionAt")
        @ExcludeMissing
        promotedToProductionAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("slug") @ExcludeMissing slug: JsonField<String> = JsonMissing.of(),
        @JsonProperty("updatedAt")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        isDefault,
        isProduction,
        name,
        promotedToProductionAt,
        slug,
        updatedAt,
        mutableMapOf(),
    )

    fun id(): String = id.getRequired("id")

    fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

    fun isDefault(): Boolean = isDefault.getRequired("isDefault")

    fun isProduction(): Boolean = isProduction.getRequired("isProduction")

    fun name(): String = name.getRequired("name")

    fun promotedToProductionAt(): OffsetDateTime? =
        promotedToProductionAt.getNullable("promotedToProductionAt")

    fun slug(): String = slug.getRequired("slug")

    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updatedAt")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonProperty("createdAt")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    @JsonProperty("isDefault") @ExcludeMissing fun _isDefault(): JsonField<Boolean> = isDefault

    @JsonProperty("isProduction")
    @ExcludeMissing
    fun _isProduction(): JsonField<Boolean> = isProduction

    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    @JsonProperty("promotedToProductionAt")
    @ExcludeMissing
    fun _promotedToProductionAt(): JsonField<OffsetDateTime> = promotedToProductionAt

    @JsonProperty("slug") @ExcludeMissing fun _slug(): JsonField<String> = slug

    @JsonProperty("updatedAt")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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

        /** Returns a mutable builder for constructing an instance of [Project]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [Project]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var isDefault: JsonField<Boolean>? = null
        private var isProduction: JsonField<Boolean>? = null
        private var name: JsonField<String>? = null
        private var promotedToProductionAt: JsonField<OffsetDateTime>? = null
        private var slug: JsonField<String>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(project: Project) = apply {
            this.id = project.id
            this.createdAt = project.createdAt
            this.isDefault = project.isDefault
            this.isProduction = project.isProduction
            this.name = project.name
            this.promotedToProductionAt = project.promotedToProductionAt
            this.slug = project.slug
            this.updatedAt = project.updatedAt
            this.additionalProperties = project.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        fun id(id: JsonField<String>) = apply { this.id = id }

        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        fun isDefault(isDefault: Boolean) = isDefault(JsonField.of(isDefault))

        fun isDefault(isDefault: JsonField<Boolean>) = apply { this.isDefault = isDefault }

        fun isProduction(isProduction: Boolean) = isProduction(JsonField.of(isProduction))

        fun isProduction(isProduction: JsonField<Boolean>) = apply {
            this.isProduction = isProduction
        }

        fun name(name: String) = name(JsonField.of(name))

        fun name(name: JsonField<String>) = apply { this.name = name }

        fun promotedToProductionAt(promotedToProductionAt: OffsetDateTime?) =
            promotedToProductionAt(JsonField.ofNullable(promotedToProductionAt))

        fun promotedToProductionAt(promotedToProductionAt: JsonField<OffsetDateTime>) = apply {
            this.promotedToProductionAt = promotedToProductionAt
        }

        fun slug(slug: String) = slug(JsonField.of(slug))

        fun slug(slug: JsonField<String>) = apply { this.slug = slug }

        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

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

        fun build(): Project =
            Project(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("isDefault", isDefault),
                checkRequired("isProduction", isProduction),
                checkRequired("name", name),
                checkRequired("promotedToProductionAt", promotedToProductionAt),
                checkRequired("slug", slug),
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

    fun validate(): Project = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        isDefault()
        isProduction()
        name()
        promotedToProductionAt()
        slug()
        updatedAt()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (isDefault.asKnown().isPresent) 1 else 0) +
            (if (isProduction.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (promotedToProductionAt.asKnown().isPresent) 1 else 0) +
            (if (slug.asKnown().isPresent) 1 else 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Project &&
            this.id == other.id &&
            this.createdAt == other.createdAt &&
            this.isDefault == other.isDefault &&
            this.isProduction == other.isProduction &&
            this.name == other.name &&
            this.promotedToProductionAt == other.promotedToProductionAt &&
            this.slug == other.slug &&
            this.updatedAt == other.updatedAt &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            isDefault,
            isProduction,
            name,
            promotedToProductionAt,
            slug,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Project{id=$id, createdAt=$createdAt, isDefault=$isDefault, isProduction=$isProduction, name=$name, promotedToProductionAt=$promotedToProductionAt, slug=$slug, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
