package dev.steel.api.models.sessions.credentials

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
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.Params
import dev.steel.api.core.allMaxBy
import dev.steel.api.core.checkKnown
import dev.steel.api.core.checkRequired
import dev.steel.api.core.getOrThrow
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/**
 * Replace all direct item and Vault bindings, including provider references
 * steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds and/or bindings;
 * empty arrays detach all. At most 100 distinct bindings may be stored. Only credentials can be
 * autofilled. Lookup becomes explicit and remains so.
 */
class CredentialReplaceParams
private constructor(
    private val id: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun id(): String? = id

    /** At most 100 bindings; KV bindings may specify allowedOrigins. */
    fun bindings(): List<Binding>? = body.bindings()

    /**
     * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding makes the
     * session's lookup `explicit` for good.
     */
    fun credentialIds(): List<String>? = body.credentialIds()

    fun _bindings(): JsonField<List<Binding>> = body._bindings()

    fun _credentialIds(): JsonField<List<String>> = body._credentialIds()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): CredentialReplaceParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [CredentialReplaceParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CredentialReplaceParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(credentialReplaceParams: CredentialReplaceParams) = apply {
            this.id = credentialReplaceParams.id
            this.body = credentialReplaceParams.body.toBuilder()
            this.additionalHeaders = credentialReplaceParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = credentialReplaceParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** At most 100 bindings; KV bindings may specify allowedOrigins. */
        fun bindings(bindings: List<Binding>) = apply { body.bindings(bindings) }

        /** At most 100 bindings; KV bindings may specify allowedOrigins. */
        fun bindings(bindings: JsonField<List<Binding>>) = apply { body.bindings(bindings) }

        /** At most 100 bindings; KV bindings may specify allowedOrigins. */
        fun addBinding(binding: Binding) = apply { body.addBinding(binding) }

        /**
         * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding makes
         * the session's lookup `explicit` for good.
         */
        fun credentialIds(credentialIds: List<String>) = apply { body.credentialIds(credentialIds) }

        /**
         * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding makes
         * the session's lookup `explicit` for good.
         */
        fun credentialIds(credentialIds: JsonField<List<String>>) = apply {
            body.credentialIds(credentialIds)
        }

        /**
         * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding makes
         * the session's lookup `explicit` for good.
         */
        fun addCredentialId(credentialId: String) = apply { body.addCredentialId(credentialId) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        fun build(): CredentialReplaceParams =
            CredentialReplaceParams(
                id,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> id ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val bindings: JsonField<List<Binding>>,
        private val credentialIds: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("bindings")
            @ExcludeMissing
            bindings: JsonField<List<Binding>> = JsonMissing.of(),
            @JsonProperty("credentialIds")
            @ExcludeMissing
            credentialIds: JsonField<List<String>> = JsonMissing.of(),
        ) : this(bindings, credentialIds, mutableMapOf())

        /** At most 100 bindings; KV bindings may specify allowedOrigins. */
        fun bindings(): List<Binding>? = bindings.getNullable("bindings")

        /**
         * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding makes
         * the session's lookup `explicit` for good.
         */
        fun credentialIds(): List<String>? = credentialIds.getNullable("credentialIds")

        @JsonProperty("bindings")
        @ExcludeMissing
        fun _bindings(): JsonField<List<Binding>> = bindings

        @JsonProperty("credentialIds")
        @ExcludeMissing
        fun _credentialIds(): JsonField<List<String>> = credentialIds

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var bindings: JsonField<MutableList<Binding>>? = null
            private var credentialIds: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.bindings = body_.bindings.map { it.toMutableList() }
                this.credentialIds = body_.credentialIds.map { it.toMutableList() }
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** At most 100 bindings; KV bindings may specify allowedOrigins. */
            fun bindings(bindings: List<Binding>) = bindings(JsonField.of(bindings))

            /** At most 100 bindings; KV bindings may specify allowedOrigins. */
            fun bindings(bindings: JsonField<List<Binding>>) = apply {
                this.bindings = bindings.map { it.toMutableList() }
            }

            /** At most 100 bindings; KV bindings may specify allowedOrigins. */
            fun addBinding(binding: Binding) = apply {
                this.bindings =
                    (this.bindings ?: JsonField.of(mutableListOf())).also {
                        checkKnown("bindings", it).add(binding)
                    }
            }

            /**
             * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding
             * makes the session's lookup `explicit` for good.
             */
            fun credentialIds(credentialIds: List<String>) =
                credentialIds(JsonField.of(credentialIds))

            /**
             * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding
             * makes the session's lookup `explicit` for good.
             */
            fun credentialIds(credentialIds: JsonField<List<String>>) = apply {
                this.credentialIds = credentialIds.map { it.toMutableList() }
            }

            /**
             * Ids of credentials (Secrets of kind `credential`) in the session's project. Binding
             * makes the session's lookup `explicit` for good.
             */
            fun addCredentialId(credentialId: String) = apply {
                this.credentialIds =
                    (this.credentialIds ?: JsonField.of(mutableListOf())).also {
                        checkKnown("credentialIds", it).add(credentialId)
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

            fun build(): Body =
                Body(
                    (bindings ?: JsonMissing.of()).map { it.toImmutable() },
                    (credentialIds ?: JsonMissing.of()).map { it.toImmutable() },
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

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            bindings()?.let { it.forEach { it.validate() } }
            credentialIds()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (bindings.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
                (credentialIds.asKnown().getOrNull()?.let { it.size } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.bindings == other.bindings &&
                this.credentialIds == other.credentialIds &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(bindings, credentialIds, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{bindings=$bindings, credentialIds=$credentialIds, additionalProperties=$additionalProperties}"
    }

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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CredentialReplaceParams &&
            this.id == other.id &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(id, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "CredentialReplaceParams{id=$id, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
