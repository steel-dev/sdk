package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import dev.steel.api.core.BaseDeserializer
import dev.steel.api.core.BaseSerializer
import dev.steel.api.core.Enum
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.allMaxBy
import dev.steel.api.core.checkKnown
import dev.steel.api.core.checkRequired
import dev.steel.api.core.getOrThrow
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** The credentials a session is bound to */
class SessionCredentials
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val bindings: JsonField<List<Binding>>,
    private val credentials: JsonField<List<Credential>>,
    private val credentialsEnabled: JsonField<Boolean>,
    private val lookup: JsonField<Lookup>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("bindings")
        @ExcludeMissing
        bindings: JsonField<List<Binding>> = JsonMissing.of(),
        @JsonProperty("credentials")
        @ExcludeMissing
        credentials: JsonField<List<Credential>> = JsonMissing.of(),
        @JsonProperty("credentialsEnabled")
        @ExcludeMissing
        credentialsEnabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("lookup") @ExcludeMissing lookup: JsonField<Lookup> = JsonMissing.of(),
    ) : this(bindings, credentials, credentialsEnabled, lookup, mutableMapOf())

    /** Bindings, including KV references with their allowed origins and soft-deleted targets. */
    fun bindings(): List<Binding> = bindings.getRequired("bindings")

    /** Live credentials bound to the session, oldest binding first. No values. */
    fun credentials(): List<Credential> = credentials.getRequired("credentials")

    /**
     * Whether the session was created with a `credentials` configuration. Only then can credentials
     * be attached; otherwise attach and replace answer 409 `credentials_not_enabled`.
     */
    fun credentialsEnabled(): Boolean = credentialsEnabled.getRequired("credentialsEnabled")

    /**
     * `explicit`: the session fills only the credentials listed here. `implicit`: it fills
     * credentials stored for its namespace and the page origin.
     */
    fun lookup(): Lookup = lookup.getRequired("lookup")

    @JsonProperty("bindings") @ExcludeMissing fun _bindings(): JsonField<List<Binding>> = bindings

    @JsonProperty("credentials")
    @ExcludeMissing
    fun _credentials(): JsonField<List<Credential>> = credentials

    @JsonProperty("credentialsEnabled")
    @ExcludeMissing
    fun _credentialsEnabled(): JsonField<Boolean> = credentialsEnabled

    @JsonProperty("lookup") @ExcludeMissing fun _lookup(): JsonField<Lookup> = lookup

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

        /** Returns a mutable builder for constructing an instance of [SessionCredentials]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionCredentials]. */
    class Builder internal constructor() {

        private var bindings: JsonField<MutableList<Binding>>? = null
        private var credentials: JsonField<MutableList<Credential>>? = null
        private var credentialsEnabled: JsonField<Boolean>? = null
        private var lookup: JsonField<Lookup>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionCredentials: SessionCredentials) = apply {
            this.bindings = sessionCredentials.bindings.map { it.toMutableList() }
            this.credentials = sessionCredentials.credentials.map { it.toMutableList() }
            this.credentialsEnabled = sessionCredentials.credentialsEnabled
            this.lookup = sessionCredentials.lookup
            this.additionalProperties = sessionCredentials.additionalProperties.toMutableMap()
        }

        /**
         * Bindings, including KV references with their allowed origins and soft-deleted targets.
         */
        fun bindings(bindings: List<Binding>) = bindings(JsonField.of(bindings))

        /**
         * Bindings, including KV references with their allowed origins and soft-deleted targets.
         */
        fun bindings(bindings: JsonField<List<Binding>>) = apply {
            this.bindings = bindings.map { it.toMutableList() }
        }

        /**
         * Bindings, including KV references with their allowed origins and soft-deleted targets.
         */
        fun addBinding(binding: Binding) = apply {
            this.bindings =
                (this.bindings ?: JsonField.of(mutableListOf())).also {
                    checkKnown("bindings", it).add(binding)
                }
        }

        /** Live credentials bound to the session, oldest binding first. No values. */
        fun credentials(credentials: List<Credential>) = credentials(JsonField.of(credentials))

        /** Live credentials bound to the session, oldest binding first. No values. */
        fun credentials(credentials: JsonField<List<Credential>>) = apply {
            this.credentials = credentials.map { it.toMutableList() }
        }

        /** Live credentials bound to the session, oldest binding first. No values. */
        fun addCredential(credential: Credential) = apply {
            this.credentials =
                (this.credentials ?: JsonField.of(mutableListOf())).also {
                    checkKnown("credentials", it).add(credential)
                }
        }

        /**
         * Whether the session was created with a `credentials` configuration. Only then can
         * credentials be attached; otherwise attach and replace answer 409
         * `credentials_not_enabled`.
         */
        fun credentialsEnabled(credentialsEnabled: Boolean) =
            credentialsEnabled(JsonField.of(credentialsEnabled))

        /**
         * Whether the session was created with a `credentials` configuration. Only then can
         * credentials be attached; otherwise attach and replace answer 409
         * `credentials_not_enabled`.
         */
        fun credentialsEnabled(credentialsEnabled: JsonField<Boolean>) = apply {
            this.credentialsEnabled = credentialsEnabled
        }

        /**
         * `explicit`: the session fills only the credentials listed here. `implicit`: it fills
         * credentials stored for its namespace and the page origin.
         */
        fun lookup(lookup: Lookup) = lookup(JsonField.of(lookup))

        /**
         * `explicit`: the session fills only the credentials listed here. `implicit`: it fills
         * credentials stored for its namespace and the page origin.
         */
        fun lookup(lookup: JsonField<Lookup>) = apply { this.lookup = lookup }

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

        fun build(): SessionCredentials =
            SessionCredentials(
                checkRequired("bindings", bindings).map { it.toImmutable() },
                checkRequired("credentials", credentials).map { it.toImmutable() },
                checkRequired("credentialsEnabled", credentialsEnabled),
                checkRequired("lookup", lookup),
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

    fun validate(): SessionCredentials = apply {
        if (validated) {
            return@apply
        }

        bindings().forEach { it.validate() }
        credentials().forEach { it.validate() }
        credentialsEnabled()
        lookup().validate()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (bindings.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (credentials.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (if (credentialsEnabled.asKnown().isPresent) 1 else 0) +
            (lookup.asKnown().getOrNull()?.let { it.validity() } ?: 0)

    @JsonDeserialize(using = Binding.Deserializer::class)
    @JsonSerialize(using = Binding.Serializer::class)
    class Binding
    private constructor(
        private val string: String? = null,
        private val unionMember1: UnionMember1? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): String? = string

        fun unionMember1(): UnionMember1? = unionMember1

        fun isString(): Boolean = string != null

        fun isUnionMember1(): Boolean = unionMember1 != null

        fun asString(): String = string.getOrThrow("string")

        fun asUnionMember1(): UnionMember1 = unionMember1.getOrThrow("unionMember1")

        fun _json(): JsonValue? = _json

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                string != null -> visitor.visitString(string)
                unionMember1 != null -> visitor.visitUnionMember1(unionMember1)
                else -> visitor.unknown(_json)
            }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Binding = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitString(string: String) {}

                    override fun visitUnionMember1(unionMember1: UnionMember1) {
                        unionMember1.validate()
                    }
                }
            )
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitString(string: String) = 1

                    override fun visitUnionMember1(unionMember1: UnionMember1) =
                        unionMember1.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Binding &&
                this.string == other.string &&
                this.unionMember1 == other.unionMember1
        }

        override fun hashCode(): Int = Objects.hash(string, unionMember1)

        override fun toString(): String =
            when {
                string != null -> "Binding{string=$string}"
                unionMember1 != null -> "Binding{unionMember1=$unionMember1}"
                _json != null -> "Binding{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Binding")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Binding(string = string)

            @JvmStatic
            fun ofUnionMember1(unionMember1: UnionMember1) = Binding(unionMember1 = unionMember1)
        }

        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitUnionMember1(unionMember1: UnionMember1): T

            fun unknown(json: JsonValue?): T {
                throw SteelInvalidDataException("Unknown Binding: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Binding>(Binding::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Binding {
                val json = JsonValue.fromJsonNode(node)
                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<UnionMember1>())?.let {
                                Binding(unionMember1 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Binding(string = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    0 -> Binding(_json = json)
                    1 -> bestMatches.single()
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Binding>(Binding::class) {

            override fun serialize(
                value: Binding,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.unionMember1 != null -> generator.writeObject(value.unionMember1)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Binding")
                }
            }
        }

        class UnionMember1
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val allowedOrigins: JsonField<List<String>>,
            private val reference: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("allowedOrigins")
                @ExcludeMissing
                allowedOrigins: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("reference")
                @ExcludeMissing
                reference: JsonField<String> = JsonMissing.of(),
            ) : this(allowedOrigins, reference, mutableMapOf())

            fun allowedOrigins(): List<String> = allowedOrigins.getRequired("allowedOrigins")

            fun reference(): String = reference.getRequired("reference")

            @JsonProperty("allowedOrigins")
            @ExcludeMissing
            fun _allowedOrigins(): JsonField<List<String>> = allowedOrigins

            @JsonProperty("reference")
            @ExcludeMissing
            fun _reference(): JsonField<String> = reference

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

                /** Returns a mutable builder for constructing an instance of [UnionMember1]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UnionMember1]. */
            class Builder internal constructor() {

                private var allowedOrigins: JsonField<MutableList<String>>? = null
                private var reference: JsonField<String>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(unionMember1: UnionMember1) = apply {
                    this.allowedOrigins = unionMember1.allowedOrigins.map { it.toMutableList() }
                    this.reference = unionMember1.reference
                    this.additionalProperties = unionMember1.additionalProperties.toMutableMap()
                }

                fun allowedOrigins(allowedOrigins: List<String>) =
                    allowedOrigins(JsonField.of(allowedOrigins))

                fun allowedOrigins(allowedOrigins: JsonField<List<String>>) = apply {
                    this.allowedOrigins = allowedOrigins.map { it.toMutableList() }
                }

                fun addAllowedOrigin(allowedOrigin: String) = apply {
                    this.allowedOrigins =
                        (this.allowedOrigins ?: JsonField.of(mutableListOf())).also {
                            checkKnown("allowedOrigins", it).add(allowedOrigin)
                        }
                }

                fun reference(reference: String) = reference(JsonField.of(reference))

                fun reference(reference: JsonField<String>) = apply { this.reference = reference }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                fun build(): UnionMember1 =
                    UnionMember1(
                        checkRequired("allowedOrigins", allowedOrigins).map { it.toImmutable() },
                        checkRequired("reference", reference),
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

            fun validate(): UnionMember1 = apply {
                if (validated) {
                    return@apply
                }

                allowedOrigins()
                reference()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (allowedOrigins.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (reference.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UnionMember1 &&
                    this.allowedOrigins == other.allowedOrigins &&
                    this.reference == other.reference &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(allowedOrigins, reference, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UnionMember1{allowedOrigins=$allowedOrigins, reference=$reference, additionalProperties=$additionalProperties}"
        }
    }

    class Credential
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val additionalOrigins: JsonField<List<String>>,
        private val name: JsonField<String>,
        private val namespace: JsonField<String>,
        private val origin: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("additionalOrigins")
            @ExcludeMissing
            additionalOrigins: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("namespace")
            @ExcludeMissing
            namespace: JsonField<String> = JsonMissing.of(),
            @JsonProperty("origin") @ExcludeMissing origin: JsonField<String> = JsonMissing.of(),
        ) : this(id, additionalOrigins, name, namespace, origin, mutableMapOf())

        /** The credential's id (a Secret of kind `credential`) */
        fun id(): String = id.getRequired("id")

        /** Other origins the credential may be filled on */
        fun additionalOrigins(): List<String> = additionalOrigins.getRequired("additionalOrigins")

        /** The credential's name */
        fun name(): String = name.getRequired("name")

        /** The credential's namespace */
        fun namespace(): String = namespace.getRequired("namespace")

        /** The website origin the credential is for */
        fun origin(): String = origin.getRequired("origin")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("additionalOrigins")
        @ExcludeMissing
        fun _additionalOrigins(): JsonField<List<String>> = additionalOrigins

        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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

            private var id: JsonField<String>? = null
            private var additionalOrigins: JsonField<MutableList<String>>? = null
            private var name: JsonField<String>? = null
            private var namespace: JsonField<String>? = null
            private var origin: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(credential: Credential) = apply {
                this.id = credential.id
                this.additionalOrigins = credential.additionalOrigins.map { it.toMutableList() }
                this.name = credential.name
                this.namespace = credential.namespace
                this.origin = credential.origin
                this.additionalProperties = credential.additionalProperties.toMutableMap()
            }

            /** The credential's id (a Secret of kind `credential`) */
            fun id(id: String) = id(JsonField.of(id))

            /** The credential's id (a Secret of kind `credential`) */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** Other origins the credential may be filled on */
            fun additionalOrigins(additionalOrigins: List<String>) =
                additionalOrigins(JsonField.of(additionalOrigins))

            /** Other origins the credential may be filled on */
            fun additionalOrigins(additionalOrigins: JsonField<List<String>>) = apply {
                this.additionalOrigins = additionalOrigins.map { it.toMutableList() }
            }

            /** Other origins the credential may be filled on */
            fun addAdditionalOrigin(additionalOrigin: String) = apply {
                this.additionalOrigins =
                    (this.additionalOrigins ?: JsonField.of(mutableListOf())).also {
                        checkKnown("additionalOrigins", it).add(additionalOrigin)
                    }
            }

            /** The credential's name */
            fun name(name: String) = name(JsonField.of(name))

            /** The credential's name */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /** The credential's namespace */
            fun namespace(namespace: String) = namespace(JsonField.of(namespace))

            /** The credential's namespace */
            fun namespace(namespace: JsonField<String>) = apply { this.namespace = namespace }

            /** The website origin the credential is for */
            fun origin(origin: String) = origin(JsonField.of(origin))

            /** The website origin the credential is for */
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
                    checkRequired("id", id),
                    checkRequired("additionalOrigins", additionalOrigins).map { it.toImmutable() },
                    checkRequired("name", name),
                    checkRequired("namespace", namespace),
                    checkRequired("origin", origin),
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

            id()
            additionalOrigins()
            name()
            namespace()
            origin()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (additionalOrigins.asKnown().getOrNull()?.let { it.size } ?: 0) +
                (if (name.asKnown().isPresent) 1 else 0) +
                (if (namespace.asKnown().isPresent) 1 else 0) +
                (if (origin.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Credential &&
                this.id == other.id &&
                this.additionalOrigins == other.additionalOrigins &&
                this.name == other.name &&
                this.namespace == other.namespace &&
                this.origin == other.origin &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, additionalOrigins, name, namespace, origin, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Credential{id=$id, additionalOrigins=$additionalOrigins, name=$name, namespace=$namespace, origin=$origin, additionalProperties=$additionalProperties}"
    }

    class Lookup @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val IMPLICIT = of("implicit")

            @JvmField val EXPLICIT = of("explicit")

            @JvmStatic fun of(value: String) = Lookup(JsonField.of(value))
        }

        enum class Known {
            IMPLICIT,
            EXPLICIT,
        }

        enum class Value {
            IMPLICIT,
            EXPLICIT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                IMPLICIT -> Value.IMPLICIT
                EXPLICIT -> Value.EXPLICIT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                IMPLICIT -> Known.IMPLICIT
                EXPLICIT -> Known.EXPLICIT
                else -> throw SteelInvalidDataException("Unknown Lookup: $value")
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

        fun validate(): Lookup = apply {
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

            return other is Lookup && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionCredentials &&
            this.bindings == other.bindings &&
            this.credentials == other.credentials &&
            this.credentialsEnabled == other.credentialsEnabled &&
            this.lookup == other.lookup &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(bindings, credentials, credentialsEnabled, lookup, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionCredentials{bindings=$bindings, credentials=$credentials, credentialsEnabled=$credentialsEnabled, lookup=$lookup, additionalProperties=$additionalProperties}"
}
