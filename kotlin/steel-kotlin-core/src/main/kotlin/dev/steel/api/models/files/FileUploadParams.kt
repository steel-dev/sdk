package dev.steel.api.models.files

import dev.steel.api.core.JsonValue
import dev.steel.api.core.MultipartField
import dev.steel.api.core.Params
import dev.steel.api.core.checkRequired
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import java.io.InputStream
import java.nio.file.Path
import java.util.Collections
import java.util.Objects
import kotlin.io.path.inputStream
import kotlin.io.path.name

/**
 * Uploads a file to global storage via `multipart/form-data` with a `file` field that accepts
 * either binary data or a URL string to download from, and an optional `path` field for the file
 * storage path.
 */
class FileUploadParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The file to upload (binary) or URL string to download from */
    fun file(): InputStream = body.file()

    /** Path to the file in the storage system */
    fun path(): String? = body.path()

    fun _file(): MultipartField<InputStream> = body._file()

    fun _path(): MultipartField<String> = body._path()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [FileUploadParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FileUploadParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(fileUploadParams: FileUploadParams) = apply {
            this.body = fileUploadParams.body.toBuilder()
            this.additionalHeaders = fileUploadParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = fileUploadParams.additionalQueryParams.toBuilder()
        }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** The file to upload (binary) or URL string to download from */
        fun file(file: InputStream) = apply { body.file(file) }

        /** The file to upload (binary) or URL string to download from */
        fun file(file: ByteArray) = apply { body.file(file) }

        /** The file to upload (binary) or URL string to download from */
        fun file(path: Path) = apply { body.file(path) }

        /** The file to upload (binary) or URL string to download from */
        fun file(file: MultipartField<InputStream>) = apply { body.file(file) }

        /** Path to the file in the storage system */
        fun path(path: String) = apply { body.path(path) }

        /** Path to the file in the storage system */
        fun path(path: MultipartField<String>) = apply { body.path(path) }

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

        fun build(): FileUploadParams =
            FileUploadParams(body.build(), additionalHeaders.build(), additionalQueryParams.build())
    }

    fun _body(): Map<String, MultipartField<*>> =
        (mapOf<String, MultipartField<*>>("file" to _file(), "path" to _path()) +
                _additionalBodyProperties().mapValues { (_, value) -> MultipartField.of(value) })
            .toImmutable()

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    private constructor(
        private val file: MultipartField<InputStream>,
        private val path: MultipartField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        /** The file to upload (binary) or URL string to download from */
        fun file(): InputStream = file.value.getRequired("file")

        /** Path to the file in the storage system */
        fun path(): String? = path.value.getNullable("path")

        fun _file(): MultipartField<InputStream> = file

        fun _path(): MultipartField<String> = path

        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder internal constructor() {

            private var file: MultipartField<InputStream>? = null
            private var path: MultipartField<String> = MultipartField.of(null)
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.file = body_.file
                this.path = body_.path
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** The file to upload (binary) or URL string to download from */
            fun file(file: InputStream) = file(MultipartField.of(file))

            /** The file to upload (binary) or URL string to download from */
            fun file(file: ByteArray) = file(file.inputStream())

            /** The file to upload (binary) or URL string to download from */
            fun file(path: Path) =
                file(
                    MultipartField.builder<InputStream>()
                        .value(path.inputStream())
                        .filename(path.name)
                        .build()
                )

            /** The file to upload (binary) or URL string to download from */
            fun file(file: MultipartField<InputStream>) = apply { this.file = file }

            /** Path to the file in the storage system */
            fun path(path: String) = path(MultipartField.of(path))

            /** Path to the file in the storage system */
            fun path(path: MultipartField<String>) = apply { this.path = path }

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
                Body(checkRequired("file", file), path, additionalProperties.toMutableMap())
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.file == other.file &&
                this.path == other.path &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(file, path, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{file=$file, path=$path, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FileUploadParams &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "FileUploadParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
