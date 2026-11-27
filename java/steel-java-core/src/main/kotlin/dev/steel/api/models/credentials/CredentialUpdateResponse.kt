package dev.steel.api.models.credentials

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class CredentialUpdateResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val createdAt: JsonField<OffsetDateTime>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalOrigins: JsonField<List<String>>,
    private val label: JsonField<String>,
    private val namespace: JsonField<String>,
    private val origin: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("createdAt")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("updatedAt")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("additionalOrigins")
        @ExcludeMissing
        additionalOrigins: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("label") @ExcludeMissing label: JsonField<String> = JsonMissing.of(),
        @JsonProperty("namespace") @ExcludeMissing namespace: JsonField<String> = JsonMissing.of(),
        @JsonProperty("origin") @ExcludeMissing origin: JsonField<String> = JsonMissing.of(),
    ) : this(createdAt, updatedAt, additionalOrigins, label, namespace, origin, mutableMapOf())

    /** Date and time the credential was created */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

    /** Date and time the credential was last updated */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updatedAt")

    /**
     * Canonical http(s) origins, besides `origin`, this credential may be filled on, such as its
     * login host or identity provider (e.g. `https://login.example.com`). Only the credential's
     * origin and these ever match a page, exactly; at most 20.
     */
    fun additionalOrigins(): Optional<List<String>> =
        additionalOrigins.getOptional("additionalOrigins")

    /** Label for the credential */
    fun label(): Optional<String> = label.getOptional("label")

    /** The namespace the credential is stored against. Defaults to "default". */
    fun namespace(): Optional<String> = namespace.getOptional("namespace")

    /** Website origin the credential is for */
    fun origin(): Optional<String> = origin.getOptional("origin")

    @JsonProperty("createdAt")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    @JsonProperty("updatedAt")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    @JsonProperty("additionalOrigins")
    @ExcludeMissing
    fun _additionalOrigins(): JsonField<List<String>> = additionalOrigins

    @JsonProperty("label") @ExcludeMissing fun _label(): JsonField<String> = label

    @JsonProperty("namespace") @ExcludeMissing fun _namespace(): JsonField<String> = namespace

    @JsonProperty("origin") @ExcludeMissing fun _origin(): JsonField<String> = origin

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

        /** Returns a mutable builder for constructing an instance of [CredentialUpdateResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CredentialUpdateResponse]. */
    class Builder internal constructor() {

        private var createdAt: JsonField<OffsetDateTime>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalOrigins: JsonField<MutableList<String>>? = null
        private var label: JsonField<String> = JsonMissing.of()
        private var namespace: JsonField<String> = JsonMissing.of()
        private var origin: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(credentialUpdateResponse: CredentialUpdateResponse) = apply {
            this.createdAt = credentialUpdateResponse.createdAt
            this.updatedAt = credentialUpdateResponse.updatedAt
            this.additionalOrigins =
                credentialUpdateResponse.additionalOrigins.map { it.toMutableList() }
            this.label = credentialUpdateResponse.label
            this.namespace = credentialUpdateResponse.namespace
            this.origin = credentialUpdateResponse.origin
            this.additionalProperties = credentialUpdateResponse.additionalProperties.toMutableMap()
        }

        /** Date and time the credential was created */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /** Date and time the credential was created */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Date and time the credential was last updated */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /** Date and time the credential was last updated */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /**
         * Canonical http(s) origins, besides `origin`, this credential may be filled on, such as
         * its login host or identity provider (e.g. `https://login.example.com`). Only the
         * credential's origin and these ever match a page, exactly; at most 20.
         */
        fun additionalOrigins(additionalOrigins: List<String>) =
            additionalOrigins(JsonField.of(additionalOrigins))

        /**
         * Canonical http(s) origins, besides `origin`, this credential may be filled on, such as
         * its login host or identity provider (e.g. `https://login.example.com`). Only the
         * credential's origin and these ever match a page, exactly; at most 20.
         */
        fun additionalOrigins(additionalOrigins: JsonField<List<String>>) = apply {
            this.additionalOrigins = additionalOrigins.map { it.toMutableList() }
        }

        /**
         * Canonical http(s) origins, besides `origin`, this credential may be filled on, such as
         * its login host or identity provider (e.g. `https://login.example.com`). Only the
         * credential's origin and these ever match a page, exactly; at most 20.
         */
        fun addAdditionalOrigin(additionalOrigin: String) = apply {
            this.additionalOrigins =
                (this.additionalOrigins ?: JsonField.of(mutableListOf())).also {
                    checkKnown("additionalOrigins", it).add(additionalOrigin)
                }
        }

        /** Label for the credential */
        fun label(label: String) = label(JsonField.of(label))

        /** Label for the credential */
        fun label(label: JsonField<String>) = apply { this.label = label }

        /** The namespace the credential is stored against. Defaults to "default". */
        fun namespace(namespace: String) = namespace(JsonField.of(namespace))

        /** The namespace the credential is stored against. Defaults to "default". */
        fun namespace(namespace: JsonField<String>) = apply { this.namespace = namespace }

        /** Website origin the credential is for */
        fun origin(origin: String) = origin(JsonField.of(origin))

        /** Website origin the credential is for */
        fun origin(origin: JsonField<String>) = apply { this.origin = origin }

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

        fun build(): CredentialUpdateResponse =
            CredentialUpdateResponse(
                checkRequired("createdAt", createdAt),
                checkRequired("updatedAt", updatedAt),
                (additionalOrigins ?: JsonMissing.of()).map { it.toImmutable() },
                label,
                namespace,
                origin,
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

    fun validate(): CredentialUpdateResponse = apply {
        if (validated) {
            return@apply
        }

        createdAt()
        updatedAt()
        additionalOrigins()
        label()
        namespace()
        origin()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (additionalOrigins.asKnown().getOrNull()?.let { it.size } ?: 0) +
            (if (label.asKnown().isPresent) 1 else 0) +
            (if (namespace.asKnown().isPresent) 1 else 0) +
            (if (origin.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CredentialUpdateResponse &&
            this.createdAt == other.createdAt &&
            this.updatedAt == other.updatedAt &&
            this.additionalOrigins == other.additionalOrigins &&
            this.label == other.label &&
            this.namespace == other.namespace &&
            this.origin == other.origin &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            createdAt,
            updatedAt,
            additionalOrigins,
            label,
            namespace,
            origin,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CredentialUpdateResponse{createdAt=$createdAt, updatedAt=$updatedAt, additionalOrigins=$additionalOrigins, label=$label, namespace=$namespace, origin=$origin, additionalProperties=$additionalProperties}"
}
