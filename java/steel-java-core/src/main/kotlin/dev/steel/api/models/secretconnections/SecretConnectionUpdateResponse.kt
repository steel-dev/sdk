package dev.steel.api.models.secretconnections

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

class SecretConnectionUpdateResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val enabled: JsonField<Boolean>,
    private val name: JsonField<String>,
    private val provider: JsonField<Provider>,
    private val tokenBudget: JsonField<TokenBudget>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val vaultFingerprint: JsonField<String>,
    private val version: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("createdAt")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("enabled") @ExcludeMissing enabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("provider") @ExcludeMissing provider: JsonField<Provider> = JsonMissing.of(),
        @JsonProperty("tokenBudget")
        @ExcludeMissing
        tokenBudget: JsonField<TokenBudget> = JsonMissing.of(),
        @JsonProperty("updatedAt")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("vaultFingerprint")
        @ExcludeMissing
        vaultFingerprint: JsonField<String> = JsonMissing.of(),
        @JsonProperty("version") @ExcludeMissing version: JsonField<Long> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        enabled,
        name,
        provider,
        tokenBudget,
        updatedAt,
        vaultFingerprint,
        version,
        mutableMapOf(),
    )

    fun id(): String = id.getRequired("id")

    fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

    fun enabled(): Boolean = enabled.getRequired("enabled")

    fun name(): String = name.getRequired("name")

    fun provider(): Provider = provider.getRequired("provider")

    fun tokenBudget(): Optional<TokenBudget> = tokenBudget.getOptional("tokenBudget")

    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updatedAt")

    /**
     * For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs. For HashiCorp
     * Vault, a SHA-256 fingerprint of the normalized endpoint and namespace. Neither proves
     * immutable account or server identity.
     */
    fun vaultFingerprint(): String = vaultFingerprint.getRequired("vaultFingerprint")

    fun version(): Long = version.getRequired("version")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonProperty("createdAt")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    @JsonProperty("enabled") @ExcludeMissing fun _enabled(): JsonField<Boolean> = enabled

    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    @JsonProperty("provider") @ExcludeMissing fun _provider(): JsonField<Provider> = provider

    @JsonProperty("tokenBudget")
    @ExcludeMissing
    fun _tokenBudget(): JsonField<TokenBudget> = tokenBudget

    @JsonProperty("updatedAt")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    @JsonProperty("vaultFingerprint")
    @ExcludeMissing
    fun _vaultFingerprint(): JsonField<String> = vaultFingerprint

    @JsonProperty("version") @ExcludeMissing fun _version(): JsonField<Long> = version

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
         * Returns a mutable builder for constructing an instance of
         * [SecretConnectionUpdateResponse].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SecretConnectionUpdateResponse]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var enabled: JsonField<Boolean>? = null
        private var name: JsonField<String>? = null
        private var provider: JsonField<Provider>? = null
        private var tokenBudget: JsonField<TokenBudget>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var vaultFingerprint: JsonField<String>? = null
        private var version: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(secretConnectionUpdateResponse: SecretConnectionUpdateResponse) = apply {
            this.id = secretConnectionUpdateResponse.id
            this.createdAt = secretConnectionUpdateResponse.createdAt
            this.enabled = secretConnectionUpdateResponse.enabled
            this.name = secretConnectionUpdateResponse.name
            this.provider = secretConnectionUpdateResponse.provider
            this.tokenBudget = secretConnectionUpdateResponse.tokenBudget
            this.updatedAt = secretConnectionUpdateResponse.updatedAt
            this.vaultFingerprint = secretConnectionUpdateResponse.vaultFingerprint
            this.version = secretConnectionUpdateResponse.version
            this.additionalProperties =
                secretConnectionUpdateResponse.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        fun id(id: JsonField<String>) = apply { this.id = id }

        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        fun enabled(enabled: Boolean) = enabled(JsonField.of(enabled))

        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

        fun name(name: String) = name(JsonField.of(name))

        fun name(name: JsonField<String>) = apply { this.name = name }

        fun provider(provider: Provider) = provider(JsonField.of(provider))

        fun provider(provider: JsonField<Provider>) = apply { this.provider = provider }

        fun tokenBudget(tokenBudget: TokenBudget?) = tokenBudget(JsonField.ofNullable(tokenBudget))

        fun tokenBudget(tokenBudget: Optional<TokenBudget>) = tokenBudget(tokenBudget.getOrNull())

        fun tokenBudget(tokenBudget: JsonField<TokenBudget>) = apply {
            this.tokenBudget = tokenBudget
        }

        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /**
         * For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs. For
         * HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and namespace. Neither
         * proves immutable account or server identity.
         */
        fun vaultFingerprint(vaultFingerprint: String) =
            vaultFingerprint(JsonField.of(vaultFingerprint))

        /**
         * For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs. For
         * HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and namespace. Neither
         * proves immutable account or server identity.
         */
        fun vaultFingerprint(vaultFingerprint: JsonField<String>) = apply {
            this.vaultFingerprint = vaultFingerprint
        }

        fun version(version: Long) = version(JsonField.of(version))

        fun version(version: JsonField<Long>) = apply { this.version = version }

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

        fun build(): SecretConnectionUpdateResponse =
            SecretConnectionUpdateResponse(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("enabled", enabled),
                checkRequired("name", name),
                checkRequired("provider", provider),
                checkRequired("tokenBudget", tokenBudget),
                checkRequired("updatedAt", updatedAt),
                checkRequired("vaultFingerprint", vaultFingerprint),
                checkRequired("version", version),
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

    fun validate(): SecretConnectionUpdateResponse = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        enabled()
        name()
        provider().validate()
        tokenBudget().ifPresent { it.validate() }
        updatedAt()
        vaultFingerprint()
        version()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (enabled.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (provider.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (tokenBudget.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (vaultFingerprint.asKnown().isPresent) 1 else 0) +
            (if (version.asKnown().isPresent) 1 else 0)

    class Provider @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val _1PASSWORD = of("1password")

            @JvmField val HASHICORP_VAULT = of("hashicorp_vault")

            @JvmStatic fun of(value: String) = Provider(JsonField.of(value))
        }

        enum class Known {
            _1PASSWORD,
            HASHICORP_VAULT,
        }

        enum class Value {
            _1PASSWORD,
            HASHICORP_VAULT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                _1PASSWORD -> Value._1PASSWORD
                HASHICORP_VAULT -> Value.HASHICORP_VAULT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                _1PASSWORD -> Known._1PASSWORD
                HASHICORP_VAULT -> Known.HASHICORP_VAULT
                else -> throw SteelInvalidDataException("Unknown Provider: $value")
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

        fun validate(): Provider = apply {
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

            return other is Provider && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class TokenBudget
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val burst: JsonField<Long>,
        private val refillPerSecond: JsonField<Double>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("burst") @ExcludeMissing burst: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("refillPerSecond")
            @ExcludeMissing
            refillPerSecond: JsonField<Double> = JsonMissing.of(),
        ) : this(burst, refillPerSecond, mutableMapOf())

        fun burst(): Long = burst.getRequired("burst")

        /** Tokens per second, with at most three decimal places. */
        fun refillPerSecond(): Double = refillPerSecond.getRequired("refillPerSecond")

        @JsonProperty("burst") @ExcludeMissing fun _burst(): JsonField<Long> = burst

        @JsonProperty("refillPerSecond")
        @ExcludeMissing
        fun _refillPerSecond(): JsonField<Double> = refillPerSecond

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

            /** Returns a mutable builder for constructing an instance of [TokenBudget]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [TokenBudget]. */
        class Builder internal constructor() {

            private var burst: JsonField<Long>? = null
            private var refillPerSecond: JsonField<Double>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(tokenBudget: TokenBudget) = apply {
                this.burst = tokenBudget.burst
                this.refillPerSecond = tokenBudget.refillPerSecond
                this.additionalProperties = tokenBudget.additionalProperties.toMutableMap()
            }

            fun burst(burst: Long) = burst(JsonField.of(burst))

            fun burst(burst: JsonField<Long>) = apply { this.burst = burst }

            /** Tokens per second, with at most three decimal places. */
            fun refillPerSecond(refillPerSecond: Double) =
                refillPerSecond(JsonField.of(refillPerSecond))

            /** Tokens per second, with at most three decimal places. */
            fun refillPerSecond(refillPerSecond: JsonField<Double>) = apply {
                this.refillPerSecond = refillPerSecond
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

            fun build(): TokenBudget =
                TokenBudget(
                    checkRequired("burst", burst),
                    checkRequired("refillPerSecond", refillPerSecond),
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

        fun validate(): TokenBudget = apply {
            if (validated) {
                return@apply
            }

            burst()
            refillPerSecond()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (burst.asKnown().isPresent) 1 else 0) +
                (if (refillPerSecond.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is TokenBudget &&
                this.burst == other.burst &&
                this.refillPerSecond == other.refillPerSecond &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(burst, refillPerSecond, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "TokenBudget{burst=$burst, refillPerSecond=$refillPerSecond, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SecretConnectionUpdateResponse &&
            this.id == other.id &&
            this.createdAt == other.createdAt &&
            this.enabled == other.enabled &&
            this.name == other.name &&
            this.provider == other.provider &&
            this.tokenBudget == other.tokenBudget &&
            this.updatedAt == other.updatedAt &&
            this.vaultFingerprint == other.vaultFingerprint &&
            this.version == other.version &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            enabled,
            name,
            provider,
            tokenBudget,
            updatedAt,
            vaultFingerprint,
            version,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SecretConnectionUpdateResponse{id=$id, createdAt=$createdAt, enabled=$enabled, name=$name, provider=$provider, tokenBudget=$tokenBudget, updatedAt=$updatedAt, vaultFingerprint=$vaultFingerprint, version=$version, additionalProperties=$additionalProperties}"
}
