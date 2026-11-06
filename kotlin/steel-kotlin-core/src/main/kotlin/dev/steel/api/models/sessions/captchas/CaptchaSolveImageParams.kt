package dev.steel.api.models.sessions.captchas

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
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

/** Solves an image captcha using XPath selectors */
class CaptchaSolveImageParams
private constructor(
    private val sessionId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Session ID */
    fun sessionId(): String? = sessionId

    /** XPath to the captcha image element */
    fun imageXPath(): String = body.imageXPath()

    /** XPath to the captcha input element */
    fun inputXPath(): String = body.inputXPath()

    /** URL where the captcha is located. Defaults to the current page URL */
    fun url(): String? = body.url()

    fun _imageXPath(): JsonField<String> = body._imageXPath()

    fun _inputXPath(): JsonField<String> = body._inputXPath()

    fun _url(): JsonField<String> = body._url()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [CaptchaSolveImageParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CaptchaSolveImageParams]. */
    class Builder internal constructor() {

        private var sessionId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(captchaSolveImageParams: CaptchaSolveImageParams) = apply {
            this.sessionId = captchaSolveImageParams.sessionId
            this.body = captchaSolveImageParams.body.toBuilder()
            this.additionalHeaders = captchaSolveImageParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = captchaSolveImageParams.additionalQueryParams.toBuilder()
        }

        /** Session ID */
        fun sessionId(sessionId: String?) = apply { this.sessionId = sessionId }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** XPath to the captcha image element */
        fun imageXPath(imageXPath: String) = apply { body.imageXPath(imageXPath) }

        /** XPath to the captcha image element */
        fun imageXPath(imageXPath: JsonField<String>) = apply { body.imageXPath(imageXPath) }

        /** XPath to the captcha input element */
        fun inputXPath(inputXPath: String) = apply { body.inputXPath(inputXPath) }

        /** XPath to the captcha input element */
        fun inputXPath(inputXPath: JsonField<String>) = apply { body.inputXPath(inputXPath) }

        /** URL where the captcha is located. Defaults to the current page URL */
        fun url(url: String) = apply { body.url(url) }

        /** URL where the captcha is located. Defaults to the current page URL */
        fun url(url: JsonField<String>) = apply { body.url(url) }

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

        fun build(): CaptchaSolveImageParams =
            CaptchaSolveImageParams(
                sessionId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> sessionId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val imageXPath: JsonField<String>,
        private val inputXPath: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("imageXPath")
            @ExcludeMissing
            imageXPath: JsonField<String> = JsonMissing.of(),
            @JsonProperty("inputXPath")
            @ExcludeMissing
            inputXPath: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(imageXPath, inputXPath, url, mutableMapOf())

        /** XPath to the captcha image element */
        fun imageXPath(): String = imageXPath.getRequired("imageXPath")

        /** XPath to the captcha input element */
        fun inputXPath(): String = inputXPath.getRequired("inputXPath")

        /** URL where the captcha is located. Defaults to the current page URL */
        fun url(): String? = url.getNullable("url")

        @JsonProperty("imageXPath")
        @ExcludeMissing
        fun _imageXPath(): JsonField<String> = imageXPath

        @JsonProperty("inputXPath")
        @ExcludeMissing
        fun _inputXPath(): JsonField<String> = inputXPath

        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

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

            private var imageXPath: JsonField<String>? = null
            private var inputXPath: JsonField<String>? = null
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.imageXPath = body_.imageXPath
                this.inputXPath = body_.inputXPath
                this.url = body_.url
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** XPath to the captcha image element */
            fun imageXPath(imageXPath: String) = imageXPath(JsonField.of(imageXPath))

            /** XPath to the captcha image element */
            fun imageXPath(imageXPath: JsonField<String>) = apply { this.imageXPath = imageXPath }

            /** XPath to the captcha input element */
            fun inputXPath(inputXPath: String) = inputXPath(JsonField.of(inputXPath))

            /** XPath to the captcha input element */
            fun inputXPath(inputXPath: JsonField<String>) = apply { this.inputXPath = inputXPath }

            /** URL where the captcha is located. Defaults to the current page URL */
            fun url(url: String) = url(JsonField.of(url))

            /** URL where the captcha is located. Defaults to the current page URL */
            fun url(url: JsonField<String>) = apply { this.url = url }

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
                    checkRequired("imageXPath", imageXPath),
                    checkRequired("inputXPath", inputXPath),
                    url,
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

            imageXPath()
            inputXPath()
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (imageXPath.asKnown().isPresent) 1 else 0) +
                (if (inputXPath.asKnown().isPresent) 1 else 0) +
                (if (url.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.imageXPath == other.imageXPath &&
                this.inputXPath == other.inputXPath &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(imageXPath, inputXPath, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{imageXPath=$imageXPath, inputXPath=$inputXPath, url=$url, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CaptchaSolveImageParams &&
            this.sessionId == other.sessionId &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(sessionId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "CaptchaSolveImageParams{sessionId=$sessionId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
