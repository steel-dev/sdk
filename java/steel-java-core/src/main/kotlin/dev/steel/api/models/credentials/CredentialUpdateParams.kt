package dev.steel.api.models.credentials

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.Params
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Encrypts and updates credentials for an origin */
class CredentialUpdateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Label for the credential */
    fun label(): Optional<String> = body.label()

    /** The namespace the credential is stored against. Defaults to "default". */
    fun namespace(): Optional<String> = body.namespace()

    /** Website origin the credential is for */
    fun origin(): Optional<String> = body.origin()

    /** Project to update the credential in. */
    fun projectId(): Optional<String> = body.projectId()

    /** Value for the credential */
    fun value(): Optional<Value> = body.value()

    fun _label(): JsonField<String> = body._label()

    fun _namespace(): JsonField<String> = body._namespace()

    fun _origin(): JsonField<String> = body._origin()

    fun _projectId(): JsonField<String> = body._projectId()

    fun _value(): JsonField<Value> = body._value()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): CredentialUpdateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [CredentialUpdateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CredentialUpdateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(credentialUpdateParams: CredentialUpdateParams) = apply {
            this.body = credentialUpdateParams.body.toBuilder()
            this.additionalHeaders = credentialUpdateParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = credentialUpdateParams.additionalQueryParams.toBuilder()
        }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Label for the credential */
        fun label(label: String) = apply { body.label(label) }

        /** Label for the credential */
        fun label(label: JsonField<String>) = apply { body.label(label) }

        /** The namespace the credential is stored against. Defaults to "default". */
        fun namespace(namespace: String) = apply { body.namespace(namespace) }

        /** The namespace the credential is stored against. Defaults to "default". */
        fun namespace(namespace: JsonField<String>) = apply { body.namespace(namespace) }

        /** Website origin the credential is for */
        fun origin(origin: String) = apply { body.origin(origin) }

        /** Website origin the credential is for */
        fun origin(origin: JsonField<String>) = apply { body.origin(origin) }

        /** Project to update the credential in. */
        fun projectId(projectId: String) = apply { body.projectId(projectId) }

        /** Project to update the credential in. */
        fun projectId(projectId: JsonField<String>) = apply { body.projectId(projectId) }

        /** Value for the credential */
        fun value(value: Value) = apply { body.value(value) }

        /** Value for the credential */
        fun value(value: JsonField<Value>) = apply { body.value(value) }

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

        fun build(): CredentialUpdateParams =
            CredentialUpdateParams(
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val label: JsonField<String>,
        private val namespace: JsonField<String>,
        private val origin: JsonField<String>,
        private val projectId: JsonField<String>,
        private val value: JsonField<Value>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("label") @ExcludeMissing label: JsonField<String> = JsonMissing.of(),
            @JsonProperty("namespace")
            @ExcludeMissing
            namespace: JsonField<String> = JsonMissing.of(),
            @JsonProperty("origin") @ExcludeMissing origin: JsonField<String> = JsonMissing.of(),
            @JsonProperty("projectId")
            @ExcludeMissing
            projectId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("value") @ExcludeMissing value: JsonField<Value> = JsonMissing.of(),
        ) : this(label, namespace, origin, projectId, value, mutableMapOf())

        /** Label for the credential */
        fun label(): Optional<String> = label.getOptional("label")

        /** The namespace the credential is stored against. Defaults to "default". */
        fun namespace(): Optional<String> = namespace.getOptional("namespace")

        /** Website origin the credential is for */
        fun origin(): Optional<String> = origin.getOptional("origin")

        /** Project to update the credential in. */
        fun projectId(): Optional<String> = projectId.getOptional("projectId")

        /** Value for the credential */
        fun value(): Optional<Value> = value.getOptional("value")

        @JsonProperty("label") @ExcludeMissing fun _label(): JsonField<String> = label

        @JsonProperty("namespace") @ExcludeMissing fun _namespace(): JsonField<String> = namespace

        @JsonProperty("origin") @ExcludeMissing fun _origin(): JsonField<String> = origin

        @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

        @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<Value> = value

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

            private var label: JsonField<String> = JsonMissing.of()
            private var namespace: JsonField<String> = JsonMissing.of()
            private var origin: JsonField<String> = JsonMissing.of()
            private var projectId: JsonField<String> = JsonMissing.of()
            private var value: JsonField<Value> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.label = body_.label
                this.namespace = body_.namespace
                this.origin = body_.origin
                this.projectId = body_.projectId
                this.value = body_.value
                this.additionalProperties = body_.additionalProperties.toMutableMap()
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

            /** Project to update the credential in. */
            fun projectId(projectId: String) = projectId(JsonField.of(projectId))

            /** Project to update the credential in. */
            fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

            /** Value for the credential */
            fun value(value: Value) = value(JsonField.of(value))

            /** Value for the credential */
            fun value(value: JsonField<Value>) = apply { this.value = value }

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
                    label,
                    namespace,
                    origin,
                    projectId,
                    value,
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

            label()
            namespace()
            origin()
            projectId()
            value().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (label.asKnown().isPresent) 1 else 0) +
                (if (namespace.asKnown().isPresent) 1 else 0) +
                (if (origin.asKnown().isPresent) 1 else 0) +
                (if (projectId.asKnown().isPresent) 1 else 0) +
                (value.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.label == other.label &&
                this.namespace == other.namespace &&
                this.origin == other.origin &&
                this.projectId == other.projectId &&
                this.value == other.value &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(label, namespace, origin, projectId, value, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{label=$label, namespace=$namespace, origin=$origin, projectId=$projectId, value=$value, additionalProperties=$additionalProperties}"
    }

    class Value
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(value: Value) = apply {
                this.additionalProperties = value.additionalProperties.toMutableMap()
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

            fun build(): Value = Value(additionalProperties.toImmutable())
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Value = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Value && this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Value{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CredentialUpdateParams &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "CredentialUpdateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
