package dev.steel.api.models.profiles

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.MultipartField
import dev.steel.api.core.Params
import dev.steel.api.core.checkRequired
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.io.InputStream
import java.nio.file.Path
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.inputStream
import kotlin.io.path.name

/** Create a new profile */
class ProfileCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The user data directory associated with the profile */
    fun userDataDir(): InputStream = body.userDataDir()

    /** The dimensions associated with the profile */
    fun dimensions(): Optional<Dimensions> = body.dimensions()

    /** Project to create the profile in */
    fun projectId(): Optional<String> = body.projectId()

    /** The proxy associated with the profile */
    fun proxyUrl(): Optional<String> = body.proxyUrl()

    /**
     * JSON-encoded proxy configuration associated with the profile. For fixed IPs, send
     * {"type":"fixed","id":"fixed:<id>"}.
     */
    fun useProxy(): Optional<String> = body.useProxy()

    /** The user agent associated with the profile */
    fun userAgent(): Optional<String> = body.userAgent()

    fun _userDataDir(): MultipartField<InputStream> = body._userDataDir()

    fun _dimensions(): MultipartField<Dimensions> = body._dimensions()

    fun _projectId(): MultipartField<String> = body._projectId()

    fun _proxyUrl(): MultipartField<String> = body._proxyUrl()

    fun _useProxy(): MultipartField<String> = body._useProxy()

    fun _userAgent(): MultipartField<String> = body._userAgent()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [ProfileCreateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ProfileCreateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(profileCreateParams: ProfileCreateParams) = apply {
            this.body = profileCreateParams.body.toBuilder()
            this.additionalHeaders = profileCreateParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = profileCreateParams.additionalQueryParams.toBuilder()
        }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** The user data directory associated with the profile */
        fun userDataDir(userDataDir: InputStream) = apply { body.userDataDir(userDataDir) }

        /** The user data directory associated with the profile */
        fun userDataDir(userDataDir: ByteArray) = apply { body.userDataDir(userDataDir) }

        /** The user data directory associated with the profile */
        fun userDataDir(path: Path) = apply { body.userDataDir(path) }

        /** The user data directory associated with the profile */
        fun userDataDir(userDataDir: MultipartField<InputStream>) = apply {
            body.userDataDir(userDataDir)
        }

        /** The dimensions associated with the profile */
        fun dimensions(dimensions: Dimensions) = apply { body.dimensions(dimensions) }

        /** The dimensions associated with the profile */
        fun dimensions(dimensions: MultipartField<Dimensions>) = apply {
            body.dimensions(dimensions)
        }

        /** Project to create the profile in */
        fun projectId(projectId: String) = apply { body.projectId(projectId) }

        /** Project to create the profile in */
        fun projectId(projectId: MultipartField<String>) = apply { body.projectId(projectId) }

        /** The proxy associated with the profile */
        fun proxyUrl(proxyUrl: String) = apply { body.proxyUrl(proxyUrl) }

        /** The proxy associated with the profile */
        fun proxyUrl(proxyUrl: MultipartField<String>) = apply { body.proxyUrl(proxyUrl) }

        /**
         * JSON-encoded proxy configuration associated with the profile. For fixed IPs, send
         * {"type":"fixed","id":"fixed:<id>"}.
         */
        fun useProxy(useProxy: String) = apply { body.useProxy(useProxy) }

        /**
         * JSON-encoded proxy configuration associated with the profile. For fixed IPs, send
         * {"type":"fixed","id":"fixed:<id>"}.
         */
        fun useProxy(useProxy: MultipartField<String>) = apply { body.useProxy(useProxy) }

        /** The user agent associated with the profile */
        fun userAgent(userAgent: String) = apply { body.userAgent(userAgent) }

        /** The user agent associated with the profile */
        fun userAgent(userAgent: MultipartField<String>) = apply { body.userAgent(userAgent) }

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

        fun build(): ProfileCreateParams =
            ProfileCreateParams(
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Map<String, MultipartField<*>> =
        (mapOf<String, MultipartField<*>>(
                "userDataDir" to _userDataDir(),
                "dimensions" to _dimensions(),
                "projectId" to _projectId(),
                "proxyUrl" to _proxyUrl(),
                "useProxy" to _useProxy(),
                "userAgent" to _userAgent(),
            ) + _additionalBodyProperties().mapValues { (_, value) -> MultipartField.of(value) })
            .toImmutable()

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    private constructor(
        private val userDataDir: MultipartField<InputStream>,
        private val dimensions: MultipartField<Dimensions>,
        private val projectId: MultipartField<String>,
        private val proxyUrl: MultipartField<String>,
        private val useProxy: MultipartField<String>,
        private val userAgent: MultipartField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        /** The user data directory associated with the profile */
        fun userDataDir(): InputStream = userDataDir.value.getRequired("userDataDir")

        /** The dimensions associated with the profile */
        fun dimensions(): Optional<Dimensions> = dimensions.value.getOptional("dimensions")

        /** Project to create the profile in */
        fun projectId(): Optional<String> = projectId.value.getOptional("projectId")

        /** The proxy associated with the profile */
        fun proxyUrl(): Optional<String> = proxyUrl.value.getOptional("proxyUrl")

        /**
         * JSON-encoded proxy configuration associated with the profile. For fixed IPs, send
         * {"type":"fixed","id":"fixed:<id>"}.
         */
        fun useProxy(): Optional<String> = useProxy.value.getOptional("useProxy")

        /** The user agent associated with the profile */
        fun userAgent(): Optional<String> = userAgent.value.getOptional("userAgent")

        fun _userDataDir(): MultipartField<InputStream> = userDataDir

        fun _dimensions(): MultipartField<Dimensions> = dimensions

        fun _projectId(): MultipartField<String> = projectId

        fun _proxyUrl(): MultipartField<String> = proxyUrl

        fun _useProxy(): MultipartField<String> = useProxy

        fun _userAgent(): MultipartField<String> = userAgent

        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder internal constructor() {

            private var userDataDir: MultipartField<InputStream>? = null
            private var dimensions: MultipartField<Dimensions> = MultipartField.of(null)
            private var projectId: MultipartField<String> = MultipartField.of(null)
            private var proxyUrl: MultipartField<String> = MultipartField.of(null)
            private var useProxy: MultipartField<String> = MultipartField.of(null)
            private var userAgent: MultipartField<String> = MultipartField.of(null)
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.userDataDir = body_.userDataDir
                this.dimensions = body_.dimensions
                this.projectId = body_.projectId
                this.proxyUrl = body_.proxyUrl
                this.useProxy = body_.useProxy
                this.userAgent = body_.userAgent
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** The user data directory associated with the profile */
            fun userDataDir(userDataDir: InputStream) = userDataDir(MultipartField.of(userDataDir))

            /** The user data directory associated with the profile */
            fun userDataDir(userDataDir: ByteArray) = userDataDir(userDataDir.inputStream())

            /** The user data directory associated with the profile */
            fun userDataDir(path: Path) =
                userDataDir(
                    MultipartField.builder<InputStream>()
                        .value(path.inputStream())
                        .filename(path.name)
                        .build()
                )

            /** The user data directory associated with the profile */
            fun userDataDir(userDataDir: MultipartField<InputStream>) = apply {
                this.userDataDir = userDataDir
            }

            /** The dimensions associated with the profile */
            fun dimensions(dimensions: Dimensions) = dimensions(MultipartField.of(dimensions))

            /** The dimensions associated with the profile */
            fun dimensions(dimensions: MultipartField<Dimensions>) = apply {
                this.dimensions = dimensions
            }

            /** Project to create the profile in */
            fun projectId(projectId: String) = projectId(MultipartField.of(projectId))

            /** Project to create the profile in */
            fun projectId(projectId: MultipartField<String>) = apply { this.projectId = projectId }

            /** The proxy associated with the profile */
            fun proxyUrl(proxyUrl: String) = proxyUrl(MultipartField.of(proxyUrl))

            /** The proxy associated with the profile */
            fun proxyUrl(proxyUrl: MultipartField<String>) = apply { this.proxyUrl = proxyUrl }

            /**
             * JSON-encoded proxy configuration associated with the profile. For fixed IPs, send
             * {"type":"fixed","id":"fixed:<id>"}.
             */
            fun useProxy(useProxy: String) = useProxy(MultipartField.of(useProxy))

            /**
             * JSON-encoded proxy configuration associated with the profile. For fixed IPs, send
             * {"type":"fixed","id":"fixed:<id>"}.
             */
            fun useProxy(useProxy: MultipartField<String>) = apply { this.useProxy = useProxy }

            /** The user agent associated with the profile */
            fun userAgent(userAgent: String) = userAgent(MultipartField.of(userAgent))

            /** The user agent associated with the profile */
            fun userAgent(userAgent: MultipartField<String>) = apply { this.userAgent = userAgent }

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
                    checkRequired("userDataDir", userDataDir),
                    dimensions,
                    projectId,
                    proxyUrl,
                    useProxy,
                    userAgent,
                    additionalProperties.toMutableMap(),
                )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.userDataDir == other.userDataDir &&
                this.dimensions == other.dimensions &&
                this.projectId == other.projectId &&
                this.proxyUrl == other.proxyUrl &&
                this.useProxy == other.useProxy &&
                this.userAgent == other.userAgent &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                userDataDir,
                dimensions,
                projectId,
                proxyUrl,
                useProxy,
                userAgent,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{userDataDir=$userDataDir, dimensions=$dimensions, projectId=$projectId, proxyUrl=$proxyUrl, useProxy=$useProxy, userAgent=$userAgent, additionalProperties=$additionalProperties}"
    }

    /** The dimensions associated with the profile */
    class Dimensions
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val height: JsonField<Double>,
        private val width: JsonField<Double>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("height") @ExcludeMissing height: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("width") @ExcludeMissing width: JsonField<Double> = JsonMissing.of(),
        ) : this(height, width, mutableMapOf())

        fun height(): Double = height.getRequired("height")

        fun width(): Double = width.getRequired("width")

        @JsonProperty("height") @ExcludeMissing fun _height(): JsonField<Double> = height

        @JsonProperty("width") @ExcludeMissing fun _width(): JsonField<Double> = width

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

            /** Returns a mutable builder for constructing an instance of [Dimensions]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Dimensions]. */
        class Builder internal constructor() {

            private var height: JsonField<Double>? = null
            private var width: JsonField<Double>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(dimensions: Dimensions) = apply {
                this.height = dimensions.height
                this.width = dimensions.width
                this.additionalProperties = dimensions.additionalProperties.toMutableMap()
            }

            fun height(height: Double) = height(JsonField.of(height))

            fun height(height: JsonField<Double>) = apply { this.height = height }

            fun width(width: Double) = width(JsonField.of(width))

            fun width(width: JsonField<Double>) = apply { this.width = width }

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

            fun build(): Dimensions =
                Dimensions(
                    checkRequired("height", height),
                    checkRequired("width", width),
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

        fun validate(): Dimensions = apply {
            if (validated) {
                return@apply
            }

            height()
            width()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (height.asKnown().isPresent) 1 else 0) + (if (width.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Dimensions &&
                this.height == other.height &&
                this.width == other.width &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(height, width, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Dimensions{height=$height, width=$width, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ProfileCreateParams &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ProfileCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
