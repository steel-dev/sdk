package dev.steel.api.models.extensions

import dev.steel.api.core.JsonValue
import dev.steel.api.core.MultipartField
import dev.steel.api.core.Params
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import java.io.InputStream
import java.nio.file.Path
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.inputStream
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Update a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the organization */
class ExtensionUpdateParams
private constructor(
    private val extensionId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Extension ID */
    fun extensionId(): Optional<String> = Optional.ofNullable(extensionId)

    /** Extension .zip/.crx file */
    fun file(): Optional<InputStream> = body.file()

    /** Extension URL */
    fun url(): Optional<String> = body.url()

    fun _file(): MultipartField<InputStream> = body._file()

    fun _url(): MultipartField<String> = body._url()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): ExtensionUpdateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [ExtensionUpdateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ExtensionUpdateParams]. */
    class Builder internal constructor() {

        private var extensionId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(extensionUpdateParams: ExtensionUpdateParams) = apply {
            this.extensionId = extensionUpdateParams.extensionId
            this.body = extensionUpdateParams.body.toBuilder()
            this.additionalHeaders = extensionUpdateParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = extensionUpdateParams.additionalQueryParams.toBuilder()
        }

        /** Extension ID */
        fun extensionId(extensionId: String?) = apply { this.extensionId = extensionId }

        /** Extension ID */
        fun extensionId(extensionId: Optional<String>) = extensionId(extensionId.getOrNull())

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Extension .zip/.crx file */
        fun file(file: InputStream) = apply { body.file(file) }

        /** Extension .zip/.crx file */
        fun file(file: ByteArray) = apply { body.file(file) }

        /** Extension .zip/.crx file */
        fun file(path: Path) = apply { body.file(path) }

        /** Extension .zip/.crx file */
        fun file(file: MultipartField<InputStream>) = apply { body.file(file) }

        /** Extension URL */
        fun url(url: String) = apply { body.url(url) }

        /** Extension URL */
        fun url(url: MultipartField<String>) = apply { body.url(url) }

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

        fun build(): ExtensionUpdateParams =
            ExtensionUpdateParams(
                extensionId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Map<String, MultipartField<*>> =
        (mapOf<String, MultipartField<*>>("file" to _file(), "url" to _url()) +
                _additionalBodyProperties().mapValues { (_, value) -> MultipartField.of(value) })
            .toImmutable()

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> extensionId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    private constructor(
        private val file: MultipartField<InputStream>,
        private val url: MultipartField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        /** Extension .zip/.crx file */
        fun file(): Optional<InputStream> = file.value.getOptional("file")

        /** Extension URL */
        fun url(): Optional<String> = url.value.getOptional("url")

        fun _file(): MultipartField<InputStream> = file

        fun _url(): MultipartField<String> = url

        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder internal constructor() {

            private var file: MultipartField<InputStream> = MultipartField.of(null)
            private var url: MultipartField<String> = MultipartField.of(null)
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.file = body_.file
                this.url = body_.url
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** Extension .zip/.crx file */
            fun file(file: InputStream) = file(MultipartField.of(file))

            /** Extension .zip/.crx file */
            fun file(file: ByteArray) = file(file.inputStream())

            /** Extension .zip/.crx file */
            fun file(path: Path) =
                file(
                    MultipartField.builder<InputStream>()
                        .value(path.inputStream())
                        .filename(path.name)
                        .build()
                )

            /** Extension .zip/.crx file */
            fun file(file: MultipartField<InputStream>) = apply { this.file = file }

            /** Extension URL */
            fun url(url: String) = url(MultipartField.of(url))

            /** Extension URL */
            fun url(url: MultipartField<String>) = apply { this.url = url }

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

            fun build(): Body = Body(file, url, additionalProperties.toMutableMap())
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.file == other.file &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(file, url, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{file=$file, url=$url, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ExtensionUpdateParams &&
            this.extensionId == other.extensionId &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(extensionId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ExtensionUpdateParams{extensionId=$extensionId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
