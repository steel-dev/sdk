package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.checkKnown
import dev.steel.api.core.checkRequired
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class SessionLiveDetailsResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val pages: JsonField<List<PageModel>>,
    private val sessionViewerFullscreenUrl: JsonField<String>,
    private val sessionViewerUrl: JsonField<String>,
    private val wsUrl: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("pages") @ExcludeMissing pages: JsonField<List<PageModel>> = JsonMissing.of(),
        @JsonProperty("sessionViewerFullscreenUrl")
        @ExcludeMissing
        sessionViewerFullscreenUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("sessionViewerUrl")
        @ExcludeMissing
        sessionViewerUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("wsUrl") @ExcludeMissing wsUrl: JsonField<String> = JsonMissing.of(),
    ) : this(pages, sessionViewerFullscreenUrl, sessionViewerUrl, wsUrl, mutableMapOf())

    fun pages(): List<PageModel> = pages.getRequired("pages")

    fun sessionViewerFullscreenUrl(): String =
        sessionViewerFullscreenUrl.getRequired("sessionViewerFullscreenUrl")

    fun sessionViewerUrl(): String = sessionViewerUrl.getRequired("sessionViewerUrl")

    fun wsUrl(): String = wsUrl.getRequired("wsUrl")

    @JsonProperty("pages") @ExcludeMissing fun _pages(): JsonField<List<PageModel>> = pages

    @JsonProperty("sessionViewerFullscreenUrl")
    @ExcludeMissing
    fun _sessionViewerFullscreenUrl(): JsonField<String> = sessionViewerFullscreenUrl

    @JsonProperty("sessionViewerUrl")
    @ExcludeMissing
    fun _sessionViewerUrl(): JsonField<String> = sessionViewerUrl

    @JsonProperty("wsUrl") @ExcludeMissing fun _wsUrl(): JsonField<String> = wsUrl

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

        /**
         * Returns a mutable builder for constructing an instance of [SessionLiveDetailsResponse].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionLiveDetailsResponse]. */
    class Builder internal constructor() {

        private var pages: JsonField<MutableList<PageModel>>? = null
        private var sessionViewerFullscreenUrl: JsonField<String>? = null
        private var sessionViewerUrl: JsonField<String>? = null
        private var wsUrl: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionLiveDetailsResponse: SessionLiveDetailsResponse) = apply {
            this.pages = sessionLiveDetailsResponse.pages.map { it.toMutableList() }
            this.sessionViewerFullscreenUrl = sessionLiveDetailsResponse.sessionViewerFullscreenUrl
            this.sessionViewerUrl = sessionLiveDetailsResponse.sessionViewerUrl
            this.wsUrl = sessionLiveDetailsResponse.wsUrl
            this.additionalProperties =
                sessionLiveDetailsResponse.additionalProperties.toMutableMap()
        }

        fun pages(pages: List<PageModel>) = pages(JsonField.of(pages))

        fun pages(pages: JsonField<List<PageModel>>) = apply {
            this.pages = pages.map { it.toMutableList() }
        }

        fun addPage(page: PageModel) = apply {
            this.pages =
                (this.pages ?: JsonField.of(mutableListOf())).also {
                    checkKnown("pages", it).add(page)
                }
        }

        fun sessionViewerFullscreenUrl(sessionViewerFullscreenUrl: String) =
            sessionViewerFullscreenUrl(JsonField.of(sessionViewerFullscreenUrl))

        fun sessionViewerFullscreenUrl(sessionViewerFullscreenUrl: JsonField<String>) = apply {
            this.sessionViewerFullscreenUrl = sessionViewerFullscreenUrl
        }

        fun sessionViewerUrl(sessionViewerUrl: String) =
            sessionViewerUrl(JsonField.of(sessionViewerUrl))

        fun sessionViewerUrl(sessionViewerUrl: JsonField<String>) = apply {
            this.sessionViewerUrl = sessionViewerUrl
        }

        fun wsUrl(wsUrl: String) = wsUrl(JsonField.of(wsUrl))

        fun wsUrl(wsUrl: JsonField<String>) = apply { this.wsUrl = wsUrl }

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

        fun build(): SessionLiveDetailsResponse =
            SessionLiveDetailsResponse(
                checkRequired("pages", pages).map { it.toImmutable() },
                checkRequired("sessionViewerFullscreenUrl", sessionViewerFullscreenUrl),
                checkRequired("sessionViewerUrl", sessionViewerUrl),
                checkRequired("wsUrl", wsUrl),
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

    fun validate(): SessionLiveDetailsResponse = apply {
        if (validated) {
            return@apply
        }

        pages().forEach { it.validate() }
        sessionViewerFullscreenUrl()
        sessionViewerUrl()
        wsUrl()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (pages.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (if (sessionViewerFullscreenUrl.asKnown().isPresent) 1 else 0) +
            (if (sessionViewerUrl.asKnown().isPresent) 1 else 0) +
            (if (wsUrl.asKnown().isPresent) 1 else 0)

    class PageModel
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val favicon: JsonField<String>,
        private val sessionViewerFullscreenUrl: JsonField<String>,
        private val sessionViewerUrl: JsonField<String>,
        private val title: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("favicon") @ExcludeMissing favicon: JsonField<String> = JsonMissing.of(),
            @JsonProperty("sessionViewerFullscreenUrl")
            @ExcludeMissing
            sessionViewerFullscreenUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("sessionViewerUrl")
            @ExcludeMissing
            sessionViewerUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            favicon,
            sessionViewerFullscreenUrl,
            sessionViewerUrl,
            title,
            url,
            mutableMapOf(),
        )

        fun id(): String = id.getRequired("id")

        fun favicon(): String? = favicon.getNullable("favicon")

        fun sessionViewerFullscreenUrl(): String =
            sessionViewerFullscreenUrl.getRequired("sessionViewerFullscreenUrl")

        fun sessionViewerUrl(): String = sessionViewerUrl.getRequired("sessionViewerUrl")

        fun title(): String = title.getRequired("title")

        fun url(): String = url.getRequired("url")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("favicon") @ExcludeMissing fun _favicon(): JsonField<String> = favicon

        @JsonProperty("sessionViewerFullscreenUrl")
        @ExcludeMissing
        fun _sessionViewerFullscreenUrl(): JsonField<String> = sessionViewerFullscreenUrl

        @JsonProperty("sessionViewerUrl")
        @ExcludeMissing
        fun _sessionViewerUrl(): JsonField<String> = sessionViewerUrl

        @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

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

            /** Returns a mutable builder for constructing an instance of [PageModel]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [PageModel]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var favicon: JsonField<String>? = null
            private var sessionViewerFullscreenUrl: JsonField<String>? = null
            private var sessionViewerUrl: JsonField<String>? = null
            private var title: JsonField<String>? = null
            private var url: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(pageModel: PageModel) = apply {
                this.id = pageModel.id
                this.favicon = pageModel.favicon
                this.sessionViewerFullscreenUrl = pageModel.sessionViewerFullscreenUrl
                this.sessionViewerUrl = pageModel.sessionViewerUrl
                this.title = pageModel.title
                this.url = pageModel.url
                this.additionalProperties = pageModel.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            fun id(id: JsonField<String>) = apply { this.id = id }

            fun favicon(favicon: String?) = favicon(JsonField.ofNullable(favicon))

            fun favicon(favicon: JsonField<String>) = apply { this.favicon = favicon }

            fun sessionViewerFullscreenUrl(sessionViewerFullscreenUrl: String) =
                sessionViewerFullscreenUrl(JsonField.of(sessionViewerFullscreenUrl))

            fun sessionViewerFullscreenUrl(sessionViewerFullscreenUrl: JsonField<String>) = apply {
                this.sessionViewerFullscreenUrl = sessionViewerFullscreenUrl
            }

            fun sessionViewerUrl(sessionViewerUrl: String) =
                sessionViewerUrl(JsonField.of(sessionViewerUrl))

            fun sessionViewerUrl(sessionViewerUrl: JsonField<String>) = apply {
                this.sessionViewerUrl = sessionViewerUrl
            }

            fun title(title: String) = title(JsonField.of(title))

            fun title(title: JsonField<String>) = apply { this.title = title }

            fun url(url: String) = url(JsonField.of(url))

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

            fun build(): PageModel =
                PageModel(
                    checkRequired("id", id),
                    checkRequired("favicon", favicon),
                    checkRequired("sessionViewerFullscreenUrl", sessionViewerFullscreenUrl),
                    checkRequired("sessionViewerUrl", sessionViewerUrl),
                    checkRequired("title", title),
                    checkRequired("url", url),
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

        fun validate(): PageModel = apply {
            if (validated) {
                return@apply
            }

            id()
            favicon()
            sessionViewerFullscreenUrl()
            sessionViewerUrl()
            title()
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (if (favicon.asKnown().isPresent) 1 else 0) +
                (if (sessionViewerFullscreenUrl.asKnown().isPresent) 1 else 0) +
                (if (sessionViewerUrl.asKnown().isPresent) 1 else 0) +
                (if (title.asKnown().isPresent) 1 else 0) +
                (if (url.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is PageModel &&
                this.id == other.id &&
                this.favicon == other.favicon &&
                this.sessionViewerFullscreenUrl == other.sessionViewerFullscreenUrl &&
                this.sessionViewerUrl == other.sessionViewerUrl &&
                this.title == other.title &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                favicon,
                sessionViewerFullscreenUrl,
                sessionViewerUrl,
                title,
                url,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "PageModel{id=$id, favicon=$favicon, sessionViewerFullscreenUrl=$sessionViewerFullscreenUrl, sessionViewerUrl=$sessionViewerUrl, title=$title, url=$url, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionLiveDetailsResponse &&
            this.pages == other.pages &&
            this.sessionViewerFullscreenUrl == other.sessionViewerFullscreenUrl &&
            this.sessionViewerUrl == other.sessionViewerUrl &&
            this.wsUrl == other.wsUrl &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            pages,
            sessionViewerFullscreenUrl,
            sessionViewerUrl,
            wsUrl,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionLiveDetailsResponse{pages=$pages, sessionViewerFullscreenUrl=$sessionViewerFullscreenUrl, sessionViewerUrl=$sessionViewerUrl, wsUrl=$wsUrl, additionalProperties=$additionalProperties}"
}
