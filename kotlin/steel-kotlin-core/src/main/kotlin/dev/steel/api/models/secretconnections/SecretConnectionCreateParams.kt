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
import dev.steel.api.core.Params
import dev.steel.api.core.checkRequired
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Create a secret connection */
class SecretConnectionCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Write-only provider authentication. Supply a service account token for 1Password, a token for
     * HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
     */
    fun auth(): Auth = body.auth()

    fun name(): String = body.name()

    fun provider(): Provider = body.provider()

    fun config(): Config? = body.config()

    fun projectId(): String? = body.projectId()

    fun tokenBudget(): TokenBudget? = body.tokenBudget()

    fun _auth(): JsonField<Auth> = body._auth()

    fun _name(): JsonField<String> = body._name()

    fun _provider(): JsonField<Provider> = body._provider()

    fun _config(): JsonField<Config> = body._config()

    fun _projectId(): JsonField<String> = body._projectId()

    fun _tokenBudget(): JsonField<TokenBudget> = body._tokenBudget()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SecretConnectionCreateParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SecretConnectionCreateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(secretConnectionCreateParams: SecretConnectionCreateParams) = apply {
            this.body = secretConnectionCreateParams.body.toBuilder()
            this.additionalHeaders = secretConnectionCreateParams.additionalHeaders.toBuilder()
            this.additionalQueryParams =
                secretConnectionCreateParams.additionalQueryParams.toBuilder()
        }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Write-only provider authentication. Supply a service account token for 1Password, a token
         * for HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
         */
        fun auth(auth: Auth) = apply { body.auth(auth) }

        /**
         * Write-only provider authentication. Supply a service account token for 1Password, a token
         * for HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
         */
        fun auth(auth: JsonField<Auth>) = apply { body.auth(auth) }

        fun name(name: String) = apply { body.name(name) }

        fun name(name: JsonField<String>) = apply { body.name(name) }

        fun provider(provider: Provider) = apply { body.provider(provider) }

        fun provider(provider: JsonField<Provider>) = apply { body.provider(provider) }

        fun config(config: Config) = apply { body.config(config) }

        fun config(config: JsonField<Config>) = apply { body.config(config) }

        fun projectId(projectId: String) = apply { body.projectId(projectId) }

        fun projectId(projectId: JsonField<String>) = apply { body.projectId(projectId) }

        fun tokenBudget(tokenBudget: TokenBudget?) = apply { body.tokenBudget(tokenBudget) }

        fun tokenBudget(tokenBudget: JsonField<TokenBudget>) = apply {
            body.tokenBudget(tokenBudget)
        }

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

        fun build(): SecretConnectionCreateParams =
            SecretConnectionCreateParams(
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
        private val auth: JsonField<Auth>,
        private val name: JsonField<String>,
        private val provider: JsonField<Provider>,
        private val config: JsonField<Config>,
        private val projectId: JsonField<String>,
        private val tokenBudget: JsonField<TokenBudget>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("auth") @ExcludeMissing auth: JsonField<Auth> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("provider")
            @ExcludeMissing
            provider: JsonField<Provider> = JsonMissing.of(),
            @JsonProperty("config") @ExcludeMissing config: JsonField<Config> = JsonMissing.of(),
            @JsonProperty("projectId")
            @ExcludeMissing
            projectId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("tokenBudget")
            @ExcludeMissing
            tokenBudget: JsonField<TokenBudget> = JsonMissing.of(),
        ) : this(auth, name, provider, config, projectId, tokenBudget, mutableMapOf())

        /**
         * Write-only provider authentication. Supply a service account token for 1Password, a token
         * for HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
         */
        fun auth(): Auth = auth.getRequired("auth")

        fun name(): String = name.getRequired("name")

        fun provider(): Provider = provider.getRequired("provider")

        fun config(): Config? = config.getNullable("config")

        fun projectId(): String? = projectId.getNullable("projectId")

        fun tokenBudget(): TokenBudget? = tokenBudget.getNullable("tokenBudget")

        @JsonProperty("auth") @ExcludeMissing fun _auth(): JsonField<Auth> = auth

        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        @JsonProperty("provider") @ExcludeMissing fun _provider(): JsonField<Provider> = provider

        @JsonProperty("config") @ExcludeMissing fun _config(): JsonField<Config> = config

        @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

        @JsonProperty("tokenBudget")
        @ExcludeMissing
        fun _tokenBudget(): JsonField<TokenBudget> = tokenBudget

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

            private var auth: JsonField<Auth>? = null
            private var name: JsonField<String>? = null
            private var provider: JsonField<Provider>? = null
            private var config: JsonField<Config> = JsonMissing.of()
            private var projectId: JsonField<String> = JsonMissing.of()
            private var tokenBudget: JsonField<TokenBudget> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.auth = body_.auth
                this.name = body_.name
                this.provider = body_.provider
                this.config = body_.config
                this.projectId = body_.projectId
                this.tokenBudget = body_.tokenBudget
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /**
             * Write-only provider authentication. Supply a service account token for 1Password, a
             * token for HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
             */
            fun auth(auth: Auth) = auth(JsonField.of(auth))

            /**
             * Write-only provider authentication. Supply a service account token for 1Password, a
             * token for HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
             */
            fun auth(auth: JsonField<Auth>) = apply { this.auth = auth }

            fun name(name: String) = name(JsonField.of(name))

            fun name(name: JsonField<String>) = apply { this.name = name }

            fun provider(provider: Provider) = provider(JsonField.of(provider))

            fun provider(provider: JsonField<Provider>) = apply { this.provider = provider }

            fun config(config: Config) = config(JsonField.of(config))

            fun config(config: JsonField<Config>) = apply { this.config = config }

            fun projectId(projectId: String) = projectId(JsonField.of(projectId))

            fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

            fun tokenBudget(tokenBudget: TokenBudget?) =
                tokenBudget(JsonField.ofNullable(tokenBudget))

            fun tokenBudget(tokenBudget: JsonField<TokenBudget>) = apply {
                this.tokenBudget = tokenBudget
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
                    checkRequired("auth", auth),
                    checkRequired("name", name),
                    checkRequired("provider", provider),
                    config,
                    projectId,
                    tokenBudget,
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

            auth().validate()
            name()
            provider().validate()
            config()?.let { it.validate() }
            projectId()
            tokenBudget()?.let { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (auth.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (name.asKnown().isPresent) 1 else 0) +
                (provider.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (config.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (projectId.asKnown().isPresent) 1 else 0) +
                (tokenBudget.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.auth == other.auth &&
                this.name == other.name &&
                this.provider == other.provider &&
                this.config == other.config &&
                this.projectId == other.projectId &&
                this.tokenBudget == other.tokenBudget &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(auth, name, provider, config, projectId, tokenBudget, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{auth=$auth, name=$name, provider=$provider, config=$config, projectId=$projectId, tokenBudget=$tokenBudget, additionalProperties=$additionalProperties}"
    }

    /**
     * Write-only provider authentication. Supply a service account token for 1Password, a token for
     * HashiCorp token auth, or a role ID and secret ID for HashiCorp AppRole.
     */
    class Auth
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val token: JsonField<String>,
        private val mount: JsonField<String>,
        private val roleId: JsonField<String>,
        private val secretId: JsonField<String>,
        private val serviceAccountToken: JsonField<String>,
        private val type: JsonField<Type>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("token") @ExcludeMissing token: JsonField<String> = JsonMissing.of(),
            @JsonProperty("mount") @ExcludeMissing mount: JsonField<String> = JsonMissing.of(),
            @JsonProperty("roleId") @ExcludeMissing roleId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("secretId")
            @ExcludeMissing
            secretId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("serviceAccountToken")
            @ExcludeMissing
            serviceAccountToken: JsonField<String> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        ) : this(token, mount, roleId, secretId, serviceAccountToken, type, mutableMapOf())

        /** Write-only; never returned by the API. */
        fun token(): String? = token.getNullable("token")

        fun mount(): String? = mount.getNullable("mount")

        /** Write-only; never returned by the API. */
        fun roleId(): String? = roleId.getNullable("roleId")

        /** Write-only; never returned by the API. */
        fun secretId(): String? = secretId.getNullable("secretId")

        /** Write-only; never returned by the API. */
        fun serviceAccountToken(): String? = serviceAccountToken.getNullable("serviceAccountToken")

        fun type(): Type? = type.getNullable("type")

        @JsonProperty("token") @ExcludeMissing fun _token(): JsonField<String> = token

        @JsonProperty("mount") @ExcludeMissing fun _mount(): JsonField<String> = mount

        @JsonProperty("roleId") @ExcludeMissing fun _roleId(): JsonField<String> = roleId

        @JsonProperty("secretId") @ExcludeMissing fun _secretId(): JsonField<String> = secretId

        @JsonProperty("serviceAccountToken")
        @ExcludeMissing
        fun _serviceAccountToken(): JsonField<String> = serviceAccountToken

        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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

            /** Returns a mutable builder for constructing an instance of [Auth]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Auth]. */
        class Builder internal constructor() {

            private var token: JsonField<String> = JsonMissing.of()
            private var mount: JsonField<String> = JsonMissing.of()
            private var roleId: JsonField<String> = JsonMissing.of()
            private var secretId: JsonField<String> = JsonMissing.of()
            private var serviceAccountToken: JsonField<String> = JsonMissing.of()
            private var type: JsonField<Type> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(auth: Auth) = apply {
                this.token = auth.token
                this.mount = auth.mount
                this.roleId = auth.roleId
                this.secretId = auth.secretId
                this.serviceAccountToken = auth.serviceAccountToken
                this.type = auth.type
                this.additionalProperties = auth.additionalProperties.toMutableMap()
            }

            /** Write-only; never returned by the API. */
            fun token(token: String) = token(JsonField.of(token))

            /** Write-only; never returned by the API. */
            fun token(token: JsonField<String>) = apply { this.token = token }

            fun mount(mount: String) = mount(JsonField.of(mount))

            fun mount(mount: JsonField<String>) = apply { this.mount = mount }

            /** Write-only; never returned by the API. */
            fun roleId(roleId: String) = roleId(JsonField.of(roleId))

            /** Write-only; never returned by the API. */
            fun roleId(roleId: JsonField<String>) = apply { this.roleId = roleId }

            /** Write-only; never returned by the API. */
            fun secretId(secretId: String) = secretId(JsonField.of(secretId))

            /** Write-only; never returned by the API. */
            fun secretId(secretId: JsonField<String>) = apply { this.secretId = secretId }

            /** Write-only; never returned by the API. */
            fun serviceAccountToken(serviceAccountToken: String) =
                serviceAccountToken(JsonField.of(serviceAccountToken))

            /** Write-only; never returned by the API. */
            fun serviceAccountToken(serviceAccountToken: JsonField<String>) = apply {
                this.serviceAccountToken = serviceAccountToken
            }

            fun type(type: Type) = type(JsonField.of(type))

            fun type(type: JsonField<Type>) = apply { this.type = type }

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

            fun build(): Auth =
                Auth(
                    token,
                    mount,
                    roleId,
                    secretId,
                    serviceAccountToken,
                    type,
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

        fun validate(): Auth = apply {
            if (validated) {
                return@apply
            }

            token()
            mount()
            roleId()
            secretId()
            serviceAccountToken()
            type()?.let { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (token.asKnown().isPresent) 1 else 0) +
                (if (mount.asKnown().isPresent) 1 else 0) +
                (if (roleId.asKnown().isPresent) 1 else 0) +
                (if (secretId.asKnown().isPresent) 1 else 0) +
                (if (serviceAccountToken.asKnown().isPresent) 1 else 0) +
                (type.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val TOKEN = of("token")

                @JvmField val APPROLE = of("approle")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))
            }

            enum class Known {
                TOKEN,
                APPROLE,
            }

            enum class Value {
                TOKEN,
                APPROLE,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    TOKEN -> Value.TOKEN
                    APPROLE -> Value.APPROLE
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    TOKEN -> Known.TOKEN
                    APPROLE -> Known.APPROLE
                    else -> throw SteelInvalidDataException("Unknown Type: $value")
                }

            fun asString(): String =
                _value().asString().orElseThrow {
                    SteelInvalidDataException("Value is not a String")
                }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Type = apply {
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

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Auth &&
                this.token == other.token &&
                this.mount == other.mount &&
                this.roleId == other.roleId &&
                this.secretId == other.secretId &&
                this.serviceAccountToken == other.serviceAccountToken &&
                this.type == other.type &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                token,
                mount,
                roleId,
                secretId,
                serviceAccountToken,
                type,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Auth{token=$token, mount=$mount, roleId=$roleId, secretId=$secretId, serviceAccountToken=$serviceAccountToken, type=$type, additionalProperties=$additionalProperties}"
    }

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

    class Config
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val address: JsonField<String>,
        private val caPem: JsonField<String>,
        private val namespace: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("address") @ExcludeMissing address: JsonField<String> = JsonMissing.of(),
            @JsonProperty("caPem") @ExcludeMissing caPem: JsonField<String> = JsonMissing.of(),
            @JsonProperty("namespace")
            @ExcludeMissing
            namespace: JsonField<String> = JsonMissing.of(),
        ) : this(address, caPem, namespace, mutableMapOf())

        fun address(): String = address.getRequired("address")

        fun caPem(): String? = caPem.getNullable("caPem")

        fun namespace(): String? = namespace.getNullable("namespace")

        @JsonProperty("address") @ExcludeMissing fun _address(): JsonField<String> = address

        @JsonProperty("caPem") @ExcludeMissing fun _caPem(): JsonField<String> = caPem

        @JsonProperty("namespace") @ExcludeMissing fun _namespace(): JsonField<String> = namespace

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

            /** Returns a mutable builder for constructing an instance of [Config]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Config]. */
        class Builder internal constructor() {

            private var address: JsonField<String>? = null
            private var caPem: JsonField<String> = JsonMissing.of()
            private var namespace: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(config: Config) = apply {
                this.address = config.address
                this.caPem = config.caPem
                this.namespace = config.namespace
                this.additionalProperties = config.additionalProperties.toMutableMap()
            }

            fun address(address: String) = address(JsonField.of(address))

            fun address(address: JsonField<String>) = apply { this.address = address }

            fun caPem(caPem: String) = caPem(JsonField.of(caPem))

            fun caPem(caPem: JsonField<String>) = apply { this.caPem = caPem }

            fun namespace(namespace: String) = namespace(JsonField.of(namespace))

            fun namespace(namespace: JsonField<String>) = apply { this.namespace = namespace }

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

            fun build(): Config =
                Config(
                    checkRequired("address", address),
                    caPem,
                    namespace,
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

        fun validate(): Config = apply {
            if (validated) {
                return@apply
            }

            address()
            caPem()
            namespace()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (address.asKnown().isPresent) 1 else 0) +
                (if (caPem.asKnown().isPresent) 1 else 0) +
                (if (namespace.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Config &&
                this.address == other.address &&
                this.caPem == other.caPem &&
                this.namespace == other.namespace &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(address, caPem, namespace, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Config{address=$address, caPem=$caPem, namespace=$namespace, additionalProperties=$additionalProperties}"
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

        return other is SecretConnectionCreateParams &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SecretConnectionCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
