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
import dev.steel.api.core.Params
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/**
 * Create a project-scoped API key using a global (organization-scoped) API key. Supply projectId
 * for an active project in the authenticated organization; scopeType must be project or omitted.
 * Project-scoped API keys cannot create keys, and global keys cannot create other global keys.
 * Dashboard users may also create project keys, or global keys with organization admin access.
 * Returns the full secret once with Cache-Control: no-store; save it securely. The new key is
 * independent: revoking the creating global key does not revoke keys it created. Missing projectId
 * returns 400; insufficient scope returns 403; an unavailable or foreign project returns 404.
 */
class ApiKeyCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** A human-readable label for the new key. Defaults to Default. */
    fun name(): Optional<String> = body.name()

    /**
     * The active project to bind the new key to, belonging to the authenticated organization.
     * Required when authenticating with a global API key. Dashboard users may omit it to use their
     * default project.
     */
    fun projectId(): Optional<String> = body.projectId()

    /**
     * Defaults to `project`. Global API keys may only create project-scoped keys. Only organization
     * admins using dashboard authentication may create a `global` key; projectId is ignored for
     * those keys.
     */
    fun scopeType(): Optional<ScopeType> = body.scopeType()

    fun _name(): JsonField<String> = body._name()

    fun _projectId(): JsonField<String> = body._projectId()

    fun _scopeType(): JsonField<ScopeType> = body._scopeType()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): ApiKeyCreateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [ApiKeyCreateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ApiKeyCreateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(apiKeyCreateParams: ApiKeyCreateParams) = apply {
            this.body = apiKeyCreateParams.body.toBuilder()
            this.additionalHeaders = apiKeyCreateParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = apiKeyCreateParams.additionalQueryParams.toBuilder()
        }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** A human-readable label for the new key. Defaults to Default. */
        fun name(name: String) = apply { body.name(name) }

        /** A human-readable label for the new key. Defaults to Default. */
        fun name(name: JsonField<String>) = apply { body.name(name) }

        /**
         * The active project to bind the new key to, belonging to the authenticated organization.
         * Required when authenticating with a global API key. Dashboard users may omit it to use
         * their default project.
         */
        fun projectId(projectId: String) = apply { body.projectId(projectId) }

        /**
         * The active project to bind the new key to, belonging to the authenticated organization.
         * Required when authenticating with a global API key. Dashboard users may omit it to use
         * their default project.
         */
        fun projectId(projectId: JsonField<String>) = apply { body.projectId(projectId) }

        /**
         * Defaults to `project`. Global API keys may only create project-scoped keys. Only
         * organization admins using dashboard authentication may create a `global` key; projectId
         * is ignored for those keys.
         */
        fun scopeType(scopeType: ScopeType) = apply { body.scopeType(scopeType) }

        /**
         * Defaults to `project`. Global API keys may only create project-scoped keys. Only
         * organization admins using dashboard authentication may create a `global` key; projectId
         * is ignored for those keys.
         */
        fun scopeType(scopeType: JsonField<ScopeType>) = apply { body.scopeType(scopeType) }

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

        fun build(): ApiKeyCreateParams =
            ApiKeyCreateParams(
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
        private val name: JsonField<String>,
        private val projectId: JsonField<String>,
        private val scopeType: JsonField<ScopeType>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("projectId")
            @ExcludeMissing
            projectId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("scopeType")
            @ExcludeMissing
            scopeType: JsonField<ScopeType> = JsonMissing.of(),
        ) : this(name, projectId, scopeType, mutableMapOf())

        /** A human-readable label for the new key. Defaults to Default. */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * The active project to bind the new key to, belonging to the authenticated organization.
         * Required when authenticating with a global API key. Dashboard users may omit it to use
         * their default project.
         */
        fun projectId(): Optional<String> = projectId.getOptional("projectId")

        /**
         * Defaults to `project`. Global API keys may only create project-scoped keys. Only
         * organization admins using dashboard authentication may create a `global` key; projectId
         * is ignored for those keys.
         */
        fun scopeType(): Optional<ScopeType> = scopeType.getOptional("scopeType")

        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

        @JsonProperty("scopeType")
        @ExcludeMissing
        fun _scopeType(): JsonField<ScopeType> = scopeType

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

            private var name: JsonField<String> = JsonMissing.of()
            private var projectId: JsonField<String> = JsonMissing.of()
            private var scopeType: JsonField<ScopeType> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.name = body_.name
                this.projectId = body_.projectId
                this.scopeType = body_.scopeType
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** A human-readable label for the new key. Defaults to Default. */
            fun name(name: String) = name(JsonField.of(name))

            /** A human-readable label for the new key. Defaults to Default. */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /**
             * The active project to bind the new key to, belonging to the authenticated
             * organization. Required when authenticating with a global API key. Dashboard users may
             * omit it to use their default project.
             */
            fun projectId(projectId: String) = projectId(JsonField.of(projectId))

            /**
             * The active project to bind the new key to, belonging to the authenticated
             * organization. Required when authenticating with a global API key. Dashboard users may
             * omit it to use their default project.
             */
            fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

            /**
             * Defaults to `project`. Global API keys may only create project-scoped keys. Only
             * organization admins using dashboard authentication may create a `global` key;
             * projectId is ignored for those keys.
             */
            fun scopeType(scopeType: ScopeType) = scopeType(JsonField.of(scopeType))

            /**
             * Defaults to `project`. Global API keys may only create project-scoped keys. Only
             * organization admins using dashboard authentication may create a `global` key;
             * projectId is ignored for those keys.
             */
            fun scopeType(scopeType: JsonField<ScopeType>) = apply { this.scopeType = scopeType }

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
                Body(name, projectId, scopeType, additionalProperties.toMutableMap())
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

            name()
            projectId()
            scopeType().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (name.asKnown().isPresent) 1 else 0) +
                (if (projectId.asKnown().isPresent) 1 else 0) +
                (scopeType.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.name == other.name &&
                this.projectId == other.projectId &&
                this.scopeType == other.scopeType &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(name, projectId, scopeType, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{name=$name, projectId=$projectId, scopeType=$scopeType, additionalProperties=$additionalProperties}"
    }

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

        return other is ApiKeyCreateParams &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ApiKeyCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
