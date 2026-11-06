package dev.steel.api.models

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

/** Captures a screenshot of a specified webpage. */
class ClientScreenshotParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** URL of the webpage to capture */
    fun url(): String = body.url()

    /** Delay before capturing the screenshot (in milliseconds) */
    fun delay(): Double? = body.delay()

    /** Capture the full page screenshot. Default is `false`. */
    fun fullPage(): Boolean? = body.fullPage()

    /** Project to execute the screenshot in. */
    fun projectId(): String? = body.projectId()

    /** Use a Steel-provided residential proxy for capturing the screenshot */
    fun useProxy(): Boolean? = body.useProxy()

    fun _url(): JsonField<String> = body._url()

    fun _delay(): JsonField<Double> = body._delay()

    fun _fullPage(): JsonField<Boolean> = body._fullPage()

    fun _projectId(): JsonField<String> = body._projectId()

    fun _region(): JsonValue = body._region()

    fun _useProxy(): JsonField<Boolean> = body._useProxy()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [ClientScreenshotParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ClientScreenshotParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(clientScreenshotParams: ClientScreenshotParams) = apply {
            this.body = clientScreenshotParams.body.toBuilder()
            this.additionalHeaders = clientScreenshotParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = clientScreenshotParams.additionalQueryParams.toBuilder()
        }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** URL of the webpage to capture */
        fun url(url: String) = apply { body.url(url) }

        /** URL of the webpage to capture */
        fun url(url: JsonField<String>) = apply { body.url(url) }

        /** Delay before capturing the screenshot (in milliseconds) */
        fun delay(delay: Double) = apply { body.delay(delay) }

        /** Delay before capturing the screenshot (in milliseconds) */
        fun delay(delay: JsonField<Double>) = apply { body.delay(delay) }

        /** Capture the full page screenshot. Default is `false`. */
        fun fullPage(fullPage: Boolean) = apply { body.fullPage(fullPage) }

        /** Capture the full page screenshot. Default is `false`. */
        fun fullPage(fullPage: JsonField<Boolean>) = apply { body.fullPage(fullPage) }

        /** Project to execute the screenshot in. */
        fun projectId(projectId: String) = apply { body.projectId(projectId) }

        /** Project to execute the screenshot in. */
        fun projectId(projectId: JsonField<String>) = apply { body.projectId(projectId) }

        /** The desired region for the action to be performed in */
        fun region(region: JsonValue) = apply { body.region(region) }

        /** Use a Steel-provided residential proxy for capturing the screenshot */
        fun useProxy(useProxy: Boolean) = apply { body.useProxy(useProxy) }

        /** Use a Steel-provided residential proxy for capturing the screenshot */
        fun useProxy(useProxy: JsonField<Boolean>) = apply { body.useProxy(useProxy) }

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

        fun build(): ClientScreenshotParams =
            ClientScreenshotParams(
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
        private val url: JsonField<String>,
        private val delay: JsonField<Double>,
        private val fullPage: JsonField<Boolean>,
        private val projectId: JsonField<String>,
        private val region: JsonValue,
        private val useProxy: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            @JsonProperty("delay") @ExcludeMissing delay: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("fullPage")
            @ExcludeMissing
            fullPage: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("projectId")
            @ExcludeMissing
            projectId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("region") @ExcludeMissing region: JsonValue = JsonMissing.of(),
            @JsonProperty("useProxy")
            @ExcludeMissing
            useProxy: JsonField<Boolean> = JsonMissing.of(),
        ) : this(url, delay, fullPage, projectId, region, useProxy, mutableMapOf())

        /** URL of the webpage to capture */
        fun url(): String = url.getRequired("url")

        /** Delay before capturing the screenshot (in milliseconds) */
        fun delay(): Double? = delay.getNullable("delay")

        /** Capture the full page screenshot. Default is `false`. */
        fun fullPage(): Boolean? = fullPage.getNullable("fullPage")

        /** Project to execute the screenshot in. */
        fun projectId(): String? = projectId.getNullable("projectId")

        /** Use a Steel-provided residential proxy for capturing the screenshot */
        fun useProxy(): Boolean? = useProxy.getNullable("useProxy")

        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

        @JsonProperty("delay") @ExcludeMissing fun _delay(): JsonField<Double> = delay

        @JsonProperty("fullPage") @ExcludeMissing fun _fullPage(): JsonField<Boolean> = fullPage

        @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

        @JsonProperty("region") @ExcludeMissing fun _region(): JsonValue = region

        @JsonProperty("useProxy") @ExcludeMissing fun _useProxy(): JsonField<Boolean> = useProxy

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

            private var url: JsonField<String>? = null
            private var delay: JsonField<Double> = JsonMissing.of()
            private var fullPage: JsonField<Boolean> = JsonMissing.of()
            private var projectId: JsonField<String> = JsonMissing.of()
            private var region: JsonValue = JsonMissing.of()
            private var useProxy: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.url = body_.url
                this.delay = body_.delay
                this.fullPage = body_.fullPage
                this.projectId = body_.projectId
                this.region = body_.region
                this.useProxy = body_.useProxy
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** URL of the webpage to capture */
            fun url(url: String) = url(JsonField.of(url))

            /** URL of the webpage to capture */
            fun url(url: JsonField<String>) = apply { this.url = url }

            /** Delay before capturing the screenshot (in milliseconds) */
            fun delay(delay: Double) = delay(JsonField.of(delay))

            /** Delay before capturing the screenshot (in milliseconds) */
            fun delay(delay: JsonField<Double>) = apply { this.delay = delay }

            /** Capture the full page screenshot. Default is `false`. */
            fun fullPage(fullPage: Boolean) = fullPage(JsonField.of(fullPage))

            /** Capture the full page screenshot. Default is `false`. */
            fun fullPage(fullPage: JsonField<Boolean>) = apply { this.fullPage = fullPage }

            /** Project to execute the screenshot in. */
            fun projectId(projectId: String) = projectId(JsonField.of(projectId))

            /** Project to execute the screenshot in. */
            fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

            /** The desired region for the action to be performed in */
            fun region(region: JsonValue) = apply { this.region = region }

            /** Use a Steel-provided residential proxy for capturing the screenshot */
            fun useProxy(useProxy: Boolean) = useProxy(JsonField.of(useProxy))

            /** Use a Steel-provided residential proxy for capturing the screenshot */
            fun useProxy(useProxy: JsonField<Boolean>) = apply { this.useProxy = useProxy }

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
                    checkRequired("url", url),
                    delay,
                    fullPage,
                    projectId,
                    region,
                    useProxy,
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

            url()
            delay()
            fullPage()
            projectId()
            useProxy()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (url.asKnown().isPresent) 1 else 0) +
                (if (delay.asKnown().isPresent) 1 else 0) +
                (if (fullPage.asKnown().isPresent) 1 else 0) +
                (if (projectId.asKnown().isPresent) 1 else 0) +
                (if (useProxy.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.url == other.url &&
                this.delay == other.delay &&
                this.fullPage == other.fullPage &&
                this.projectId == other.projectId &&
                this.region == other.region &&
                this.useProxy == other.useProxy &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(url, delay, fullPage, projectId, region, useProxy, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{url=$url, delay=$delay, fullPage=$fullPage, projectId=$projectId, region=$region, useProxy=$useProxy, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ClientScreenshotParams &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ClientScreenshotParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
