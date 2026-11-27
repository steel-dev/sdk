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
import kotlin.jvm.optionals.getOrNull

class CredentialListResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val credentials: JsonField<List<Credential>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("credentials")
        @ExcludeMissing
        credentials: JsonField<List<Credential>> = JsonMissing.of()
    ) : this(credentials, mutableMapOf())

    fun credentials(): List<Credential> = credentials.getRequired("credentials")

    @JsonProperty("credentials")
    @ExcludeMissing
    fun _credentials(): JsonField<List<Credential>> = credentials

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

        /** Returns a mutable builder for constructing an instance of [CredentialListResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CredentialListResponse]. */
    class Builder internal constructor() {

        private var credentials: JsonField<MutableList<Credential>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(credentialListResponse: CredentialListResponse) = apply {
            this.credentials = credentialListResponse.credentials.map { it.toMutableList() }
            this.additionalProperties = credentialListResponse.additionalProperties.toMutableMap()
        }

        fun credentials(credentials: List<Credential>) = credentials(JsonField.of(credentials))

        fun credentials(credentials: JsonField<List<Credential>>) = apply {
            this.credentials = credentials.map { it.toMutableList() }
        }

        fun addCredential(credential: Credential) = apply {
            this.credentials =
                (this.credentials ?: JsonField.of(mutableListOf())).also {
                    checkKnown("credentials", it).add(credential)
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

        fun build(): CredentialListResponse =
            CredentialListResponse(
                checkRequired("credentials", credentials).map { it.toImmutable() },
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

    fun validate(): CredentialListResponse = apply {
        if (validated) {
            return@apply
        }

        credentials().forEach { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (credentials.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0)

    class Credential
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
            @JsonProperty("namespace")
            @ExcludeMissing
            namespace: JsonField<String> = JsonMissing.of(),
            @JsonProperty("origin") @ExcludeMissing origin: JsonField<String> = JsonMissing.of(),
        ) : this(createdAt, updatedAt, additionalOrigins, label, namespace, origin, mutableMapOf())

        /** Date and time the credential was created */
        fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

        /** Date and time the credential was last updated */
        fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updatedAt")

        /**
         * Canonical http(s) origins, besides `origin`, this credential may be filled on, such as
         * its login host or identity provider (e.g. `https://login.example.com`). Only the
         * credential's origin and these ever match a page, exactly; at most 20.
         */
        fun additionalOrigins(): List<String>? = additionalOrigins.getNullable("additionalOrigins")

        /** Label for the credential */
        fun label(): String? = label.getNullable("label")

        /** The namespace the credential is stored against. Defaults to "default". */
        fun namespace(): String? = namespace.getNullable("namespace")

        /** Website origin the credential is for */
        fun origin(): String? = origin.getNullable("origin")

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

            /** Returns a mutable builder for constructing an instance of [Credential]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Credential]. */
        class Builder internal constructor() {

            private var createdAt: JsonField<OffsetDateTime>? = null
            private var updatedAt: JsonField<OffsetDateTime>? = null
            private var additionalOrigins: JsonField<MutableList<String>>? = null
            private var label: JsonField<String> = JsonMissing.of()
            private var namespace: JsonField<String> = JsonMissing.of()
            private var origin: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(credential: Credential) = apply {
                this.createdAt = credential.createdAt
                this.updatedAt = credential.updatedAt
                this.additionalOrigins = credential.additionalOrigins.map { it.toMutableList() }
                this.label = credential.label
                this.namespace = credential.namespace
                this.origin = credential.origin
                this.additionalProperties = credential.additionalProperties.toMutableMap()
            }

            /** Date and time the credential was created */
            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Date and time the credential was created */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            /** Date and time the credential was last updated */
            fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

            /** Date and time the credential was last updated */
            fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply {
                this.updatedAt = updatedAt
            }

            /**
             * Canonical http(s) origins, besides `origin`, this credential may be filled on, such
             * as its login host or identity provider (e.g. `https://login.example.com`). Only the
             * credential's origin and these ever match a page, exactly; at most 20.
             */
            fun additionalOrigins(additionalOrigins: List<String>) =
                additionalOrigins(JsonField.of(additionalOrigins))

            /**
             * Canonical http(s) origins, besides `origin`, this credential may be filled on, such
             * as its login host or identity provider (e.g. `https://login.example.com`). Only the
             * credential's origin and these ever match a page, exactly; at most 20.
             */
            fun additionalOrigins(additionalOrigins: JsonField<List<String>>) = apply {
                this.additionalOrigins = additionalOrigins.map { it.toMutableList() }
            }

            /**
             * Canonical http(s) origins, besides `origin`, this credential may be filled on, such
             * as its login host or identity provider (e.g. `https://login.example.com`). Only the
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

            fun build(): Credential =
                Credential(
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

        fun validate(): Credential = apply {
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

            return other is Credential &&
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
            "Credential{createdAt=$createdAt, updatedAt=$updatedAt, additionalOrigins=$additionalOrigins, label=$label, namespace=$namespace, origin=$origin, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CredentialListResponse &&
            this.credentials == other.credentials &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(credentials, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CredentialListResponse{credentials=$credentials, additionalProperties=$additionalProperties}"
}
