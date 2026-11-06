package dev.steel.api.models

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** Response from a successful scrape request */
class ClientScrapeResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<Content>,
    private val links: JsonField<List<Link>>,
    private val metadata: JsonField<Metadata>,
    private val pdf: JsonField<Pdf>,
    private val screenshot: JsonField<Screenshot>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content") @ExcludeMissing content: JsonField<Content> = JsonMissing.of(),
        @JsonProperty("links") @ExcludeMissing links: JsonField<List<Link>> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("pdf") @ExcludeMissing pdf: JsonField<Pdf> = JsonMissing.of(),
        @JsonProperty("screenshot")
        @ExcludeMissing
        screenshot: JsonField<Screenshot> = JsonMissing.of(),
    ) : this(content, links, metadata, pdf, screenshot, mutableMapOf())

    fun content(): Content = content.getRequired("content")

    fun links(): List<Link> = links.getRequired("links")

    fun metadata(): Metadata = metadata.getRequired("metadata")

    fun pdf(): Pdf? = pdf.getNullable("pdf")

    fun screenshot(): Screenshot? = screenshot.getNullable("screenshot")

    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

    @JsonProperty("links") @ExcludeMissing fun _links(): JsonField<List<Link>> = links

    @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

    @JsonProperty("pdf") @ExcludeMissing fun _pdf(): JsonField<Pdf> = pdf

    @JsonProperty("screenshot")
    @ExcludeMissing
    fun _screenshot(): JsonField<Screenshot> = screenshot

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

        /** Returns a mutable builder for constructing an instance of [ClientScrapeResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ClientScrapeResponse]. */
    class Builder internal constructor() {

        private var content: JsonField<Content>? = null
        private var links: JsonField<MutableList<Link>>? = null
        private var metadata: JsonField<Metadata>? = null
        private var pdf: JsonField<Pdf> = JsonMissing.of()
        private var screenshot: JsonField<Screenshot> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(clientScrapeResponse: ClientScrapeResponse) = apply {
            this.content = clientScrapeResponse.content
            this.links = clientScrapeResponse.links.map { it.toMutableList() }
            this.metadata = clientScrapeResponse.metadata
            this.pdf = clientScrapeResponse.pdf
            this.screenshot = clientScrapeResponse.screenshot
            this.additionalProperties = clientScrapeResponse.additionalProperties.toMutableMap()
        }

        fun content(content: Content) = content(JsonField.of(content))

        fun content(content: JsonField<Content>) = apply { this.content = content }

        fun links(links: List<Link>) = links(JsonField.of(links))

        fun links(links: JsonField<List<Link>>) = apply {
            this.links = links.map { it.toMutableList() }
        }

        fun addLink(link: Link) = apply {
            this.links =
                (this.links ?: JsonField.of(mutableListOf())).also {
                    checkKnown("links", it).add(link)
                }
        }

        fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

        fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

        fun pdf(pdf: Pdf) = pdf(JsonField.of(pdf))

        fun pdf(pdf: JsonField<Pdf>) = apply { this.pdf = pdf }

        fun screenshot(screenshot: Screenshot) = screenshot(JsonField.of(screenshot))

        fun screenshot(screenshot: JsonField<Screenshot>) = apply { this.screenshot = screenshot }

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

        fun build(): ClientScrapeResponse =
            ClientScrapeResponse(
                checkRequired("content", content),
                checkRequired("links", links).map { it.toImmutable() },
                checkRequired("metadata", metadata),
                pdf,
                screenshot,
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

    fun validate(): ClientScrapeResponse = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        links().forEach { it.validate() }
        metadata().validate()
        pdf()?.let { it.validate() }
        screenshot()?.let { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (content.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (links.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (metadata.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (pdf.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (screenshot.asKnown().getOrNull()?.let { it.validity() } ?: 0)

    class Content
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val cleanedHtml: JsonField<String>,
        private val html: JsonField<String>,
        private val markdown: JsonField<String>,
        private val readability: JsonField<Readability>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("cleaned_html")
            @ExcludeMissing
            cleanedHtml: JsonField<String> = JsonMissing.of(),
            @JsonProperty("html") @ExcludeMissing html: JsonField<String> = JsonMissing.of(),
            @JsonProperty("markdown")
            @ExcludeMissing
            markdown: JsonField<String> = JsonMissing.of(),
            @JsonProperty("readability")
            @ExcludeMissing
            readability: JsonField<Readability> = JsonMissing.of(),
        ) : this(cleanedHtml, html, markdown, readability, mutableMapOf())

        /** Cleaned HTML content of the webpage */
        fun cleanedHtml(): String? = cleanedHtml.getNullable("cleaned_html")

        /** Raw HTML content of the webpage */
        fun html(): String? = html.getNullable("html")

        /** Webpage content converted to Markdown */
        fun markdown(): String? = markdown.getNullable("markdown")

        /** Webpage content in Readability format */
        fun readability(): Readability? = readability.getNullable("readability")

        @JsonProperty("cleaned_html")
        @ExcludeMissing
        fun _cleanedHtml(): JsonField<String> = cleanedHtml

        @JsonProperty("html") @ExcludeMissing fun _html(): JsonField<String> = html

        @JsonProperty("markdown") @ExcludeMissing fun _markdown(): JsonField<String> = markdown

        @JsonProperty("readability")
        @ExcludeMissing
        fun _readability(): JsonField<Readability> = readability

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

            /** Returns a mutable builder for constructing an instance of [Content]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Content]. */
        class Builder internal constructor() {

            private var cleanedHtml: JsonField<String> = JsonMissing.of()
            private var html: JsonField<String> = JsonMissing.of()
            private var markdown: JsonField<String> = JsonMissing.of()
            private var readability: JsonField<Readability> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(content: Content) = apply {
                this.cleanedHtml = content.cleanedHtml
                this.html = content.html
                this.markdown = content.markdown
                this.readability = content.readability
                this.additionalProperties = content.additionalProperties.toMutableMap()
            }

            /** Cleaned HTML content of the webpage */
            fun cleanedHtml(cleanedHtml: String) = cleanedHtml(JsonField.of(cleanedHtml))

            /** Cleaned HTML content of the webpage */
            fun cleanedHtml(cleanedHtml: JsonField<String>) = apply {
                this.cleanedHtml = cleanedHtml
            }

            /** Raw HTML content of the webpage */
            fun html(html: String) = html(JsonField.of(html))

            /** Raw HTML content of the webpage */
            fun html(html: JsonField<String>) = apply { this.html = html }

            /** Webpage content converted to Markdown */
            fun markdown(markdown: String) = markdown(JsonField.of(markdown))

            /** Webpage content converted to Markdown */
            fun markdown(markdown: JsonField<String>) = apply { this.markdown = markdown }

            /** Webpage content in Readability format */
            fun readability(readability: Readability) = readability(JsonField.of(readability))

            /** Webpage content in Readability format */
            fun readability(readability: JsonField<Readability>) = apply {
                this.readability = readability
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

            fun build(): Content =
                Content(
                    cleanedHtml,
                    html,
                    markdown,
                    readability,
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

        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            cleanedHtml()
            html()
            markdown()
            readability()?.let { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (cleanedHtml.asKnown().isPresent) 1 else 0) +
                (if (html.asKnown().isPresent) 1 else 0) +
                (if (markdown.asKnown().isPresent) 1 else 0) +
                (readability.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        class Readability
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
                internal fun from(readability: Readability) = apply {
                    this.additionalProperties = readability.additionalProperties.toMutableMap()
                }

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

                fun build(): Readability = Readability(additionalProperties.toImmutable())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Readability = apply {
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

                return other is Readability &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "Readability{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content &&
                this.cleanedHtml == other.cleanedHtml &&
                this.html == other.html &&
                this.markdown == other.markdown &&
                this.readability == other.readability &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(cleanedHtml, html, markdown, readability, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Content{cleanedHtml=$cleanedHtml, html=$html, markdown=$markdown, readability=$readability, additionalProperties=$additionalProperties}"
    }

    class Link
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val text: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(text, url, mutableMapOf())

        /** Text content of the link */
        fun text(): String = text.getRequired("text")

        /** URL of the link */
        fun url(): String = url.getRequired("url")

        @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

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

            /** Returns a mutable builder for constructing an instance of [Link]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Link]. */
        class Builder internal constructor() {

            private var text: JsonField<String>? = null
            private var url: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(link: Link) = apply {
                this.text = link.text
                this.url = link.url
                this.additionalProperties = link.additionalProperties.toMutableMap()
            }

            /** Text content of the link */
            fun text(text: String) = text(JsonField.of(text))

            /** Text content of the link */
            fun text(text: JsonField<String>) = apply { this.text = text }

            /** URL of the link */
            fun url(url: String) = url(JsonField.of(url))

            /** URL of the link */
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

            fun build(): Link =
                Link(
                    checkRequired("text", text),
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

        fun validate(): Link = apply {
            if (validated) {
                return@apply
            }

            text()
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (text.asKnown().isPresent) 1 else 0) + (if (url.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Link &&
                this.text == other.text &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(text, url, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Link{text=$text, url=$url, additionalProperties=$additionalProperties}"
    }

    class Metadata
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val statusCode: JsonField<Long>,
        private val articleAuthor: JsonField<String>,
        private val author: JsonField<String>,
        private val canonical: JsonField<String>,
        private val description: JsonField<String>,
        private val favicon: JsonField<String>,
        private val jsonLd: JsonValue,
        private val keywords: JsonField<String>,
        private val language: JsonField<String>,
        private val modifiedTime: JsonField<String>,
        private val ogDescription: JsonField<String>,
        private val ogImage: JsonField<String>,
        private val ogSiteName: JsonField<String>,
        private val ogTitle: JsonField<String>,
        private val ogUrl: JsonField<String>,
        private val publishedTime: JsonField<String>,
        private val timestamp: JsonField<OffsetDateTime>,
        private val title: JsonField<String>,
        private val urlSource: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("statusCode")
            @ExcludeMissing
            statusCode: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("articleAuthor")
            @ExcludeMissing
            articleAuthor: JsonField<String> = JsonMissing.of(),
            @JsonProperty("author") @ExcludeMissing author: JsonField<String> = JsonMissing.of(),
            @JsonProperty("canonical")
            @ExcludeMissing
            canonical: JsonField<String> = JsonMissing.of(),
            @JsonProperty("description")
            @ExcludeMissing
            description: JsonField<String> = JsonMissing.of(),
            @JsonProperty("favicon") @ExcludeMissing favicon: JsonField<String> = JsonMissing.of(),
            @JsonProperty("jsonLd") @ExcludeMissing jsonLd: JsonValue = JsonMissing.of(),
            @JsonProperty("keywords")
            @ExcludeMissing
            keywords: JsonField<String> = JsonMissing.of(),
            @JsonProperty("language")
            @ExcludeMissing
            language: JsonField<String> = JsonMissing.of(),
            @JsonProperty("modifiedTime")
            @ExcludeMissing
            modifiedTime: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ogDescription")
            @ExcludeMissing
            ogDescription: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ogImage") @ExcludeMissing ogImage: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ogSiteName")
            @ExcludeMissing
            ogSiteName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ogTitle") @ExcludeMissing ogTitle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ogUrl") @ExcludeMissing ogUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("publishedTime")
            @ExcludeMissing
            publishedTime: JsonField<String> = JsonMissing.of(),
            @JsonProperty("timestamp")
            @ExcludeMissing
            timestamp: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
            @JsonProperty("urlSource")
            @ExcludeMissing
            urlSource: JsonField<String> = JsonMissing.of(),
        ) : this(
            statusCode,
            articleAuthor,
            author,
            canonical,
            description,
            favicon,
            jsonLd,
            keywords,
            language,
            modifiedTime,
            ogDescription,
            ogImage,
            ogSiteName,
            ogTitle,
            ogUrl,
            publishedTime,
            timestamp,
            title,
            urlSource,
            mutableMapOf(),
        )

        /** HTTP status code of the response */
        fun statusCode(): Long = statusCode.getRequired("statusCode")

        /** Author of the article content */
        fun articleAuthor(): String? = articleAuthor.getNullable("articleAuthor")

        /** Author of the webpage content */
        fun author(): String? = author.getNullable("author")

        /** Canonical URL of the webpage */
        fun canonical(): String? = canonical.getNullable("canonical")

        /** Description of the webpage */
        fun description(): String? = description.getNullable("description")

        /** Favicon URL of the website */
        fun favicon(): String? = favicon.getNullable("favicon")

        /** Keywords associated with the webpage */
        fun keywords(): String? = keywords.getNullable("keywords")

        /** Detected language of the webpage */
        fun language(): String? = language.getNullable("language")

        /** Last modification time of the content */
        fun modifiedTime(): String? = modifiedTime.getNullable("modifiedTime")

        /** Open Graph description */
        fun ogDescription(): String? = ogDescription.getNullable("ogDescription")

        /** Open Graph image URL */
        fun ogImage(): String? = ogImage.getNullable("ogImage")

        /** Open Graph site name */
        fun ogSiteName(): String? = ogSiteName.getNullable("ogSiteName")

        /** Open Graph title */
        fun ogTitle(): String? = ogTitle.getNullable("ogTitle")

        /** Open Graph URL */
        fun ogUrl(): String? = ogUrl.getNullable("ogUrl")

        /** Publication time of the content */
        fun publishedTime(): String? = publishedTime.getNullable("publishedTime")

        /** Timestamp when the scrape was performed */
        fun timestamp(): OffsetDateTime? = timestamp.getNullable("timestamp")

        /** Title of the webpage */
        fun title(): String? = title.getNullable("title")

        /** Source URL of the scraped page */
        fun urlSource(): String? = urlSource.getNullable("urlSource")

        @JsonProperty("statusCode") @ExcludeMissing fun _statusCode(): JsonField<Long> = statusCode

        @JsonProperty("articleAuthor")
        @ExcludeMissing
        fun _articleAuthor(): JsonField<String> = articleAuthor

        @JsonProperty("author") @ExcludeMissing fun _author(): JsonField<String> = author

        @JsonProperty("canonical") @ExcludeMissing fun _canonical(): JsonField<String> = canonical

        @JsonProperty("description")
        @ExcludeMissing
        fun _description(): JsonField<String> = description

        @JsonProperty("favicon") @ExcludeMissing fun _favicon(): JsonField<String> = favicon

        @JsonProperty("jsonLd") @ExcludeMissing fun _jsonLd(): JsonValue = jsonLd

        @JsonProperty("keywords") @ExcludeMissing fun _keywords(): JsonField<String> = keywords

        @JsonProperty("language") @ExcludeMissing fun _language(): JsonField<String> = language

        @JsonProperty("modifiedTime")
        @ExcludeMissing
        fun _modifiedTime(): JsonField<String> = modifiedTime

        @JsonProperty("ogDescription")
        @ExcludeMissing
        fun _ogDescription(): JsonField<String> = ogDescription

        @JsonProperty("ogImage") @ExcludeMissing fun _ogImage(): JsonField<String> = ogImage

        @JsonProperty("ogSiteName")
        @ExcludeMissing
        fun _ogSiteName(): JsonField<String> = ogSiteName

        @JsonProperty("ogTitle") @ExcludeMissing fun _ogTitle(): JsonField<String> = ogTitle

        @JsonProperty("ogUrl") @ExcludeMissing fun _ogUrl(): JsonField<String> = ogUrl

        @JsonProperty("publishedTime")
        @ExcludeMissing
        fun _publishedTime(): JsonField<String> = publishedTime

        @JsonProperty("timestamp")
        @ExcludeMissing
        fun _timestamp(): JsonField<OffsetDateTime> = timestamp

        @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

        @JsonProperty("urlSource") @ExcludeMissing fun _urlSource(): JsonField<String> = urlSource

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

            /** Returns a mutable builder for constructing an instance of [Metadata]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Metadata]. */
        class Builder internal constructor() {

            private var statusCode: JsonField<Long>? = null
            private var articleAuthor: JsonField<String> = JsonMissing.of()
            private var author: JsonField<String> = JsonMissing.of()
            private var canonical: JsonField<String> = JsonMissing.of()
            private var description: JsonField<String> = JsonMissing.of()
            private var favicon: JsonField<String> = JsonMissing.of()
            private var jsonLd: JsonValue = JsonMissing.of()
            private var keywords: JsonField<String> = JsonMissing.of()
            private var language: JsonField<String> = JsonMissing.of()
            private var modifiedTime: JsonField<String> = JsonMissing.of()
            private var ogDescription: JsonField<String> = JsonMissing.of()
            private var ogImage: JsonField<String> = JsonMissing.of()
            private var ogSiteName: JsonField<String> = JsonMissing.of()
            private var ogTitle: JsonField<String> = JsonMissing.of()
            private var ogUrl: JsonField<String> = JsonMissing.of()
            private var publishedTime: JsonField<String> = JsonMissing.of()
            private var timestamp: JsonField<OffsetDateTime> = JsonMissing.of()
            private var title: JsonField<String> = JsonMissing.of()
            private var urlSource: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(metadata: Metadata) = apply {
                this.statusCode = metadata.statusCode
                this.articleAuthor = metadata.articleAuthor
                this.author = metadata.author
                this.canonical = metadata.canonical
                this.description = metadata.description
                this.favicon = metadata.favicon
                this.jsonLd = metadata.jsonLd
                this.keywords = metadata.keywords
                this.language = metadata.language
                this.modifiedTime = metadata.modifiedTime
                this.ogDescription = metadata.ogDescription
                this.ogImage = metadata.ogImage
                this.ogSiteName = metadata.ogSiteName
                this.ogTitle = metadata.ogTitle
                this.ogUrl = metadata.ogUrl
                this.publishedTime = metadata.publishedTime
                this.timestamp = metadata.timestamp
                this.title = metadata.title
                this.urlSource = metadata.urlSource
                this.additionalProperties = metadata.additionalProperties.toMutableMap()
            }

            /** HTTP status code of the response */
            fun statusCode(statusCode: Long) = statusCode(JsonField.of(statusCode))

            /** HTTP status code of the response */
            fun statusCode(statusCode: JsonField<Long>) = apply { this.statusCode = statusCode }

            /** Author of the article content */
            fun articleAuthor(articleAuthor: String) = articleAuthor(JsonField.of(articleAuthor))

            /** Author of the article content */
            fun articleAuthor(articleAuthor: JsonField<String>) = apply {
                this.articleAuthor = articleAuthor
            }

            /** Author of the webpage content */
            fun author(author: String) = author(JsonField.of(author))

            /** Author of the webpage content */
            fun author(author: JsonField<String>) = apply { this.author = author }

            /** Canonical URL of the webpage */
            fun canonical(canonical: String) = canonical(JsonField.of(canonical))

            /** Canonical URL of the webpage */
            fun canonical(canonical: JsonField<String>) = apply { this.canonical = canonical }

            /** Description of the webpage */
            fun description(description: String) = description(JsonField.of(description))

            /** Description of the webpage */
            fun description(description: JsonField<String>) = apply {
                this.description = description
            }

            /** Favicon URL of the website */
            fun favicon(favicon: String) = favicon(JsonField.of(favicon))

            /** Favicon URL of the website */
            fun favicon(favicon: JsonField<String>) = apply { this.favicon = favicon }

            /** JSON-LD structured data from the webpage */
            fun jsonLd(jsonLd: JsonValue) = apply { this.jsonLd = jsonLd }

            /** Keywords associated with the webpage */
            fun keywords(keywords: String) = keywords(JsonField.of(keywords))

            /** Keywords associated with the webpage */
            fun keywords(keywords: JsonField<String>) = apply { this.keywords = keywords }

            /** Detected language of the webpage */
            fun language(language: String) = language(JsonField.of(language))

            /** Detected language of the webpage */
            fun language(language: JsonField<String>) = apply { this.language = language }

            /** Last modification time of the content */
            fun modifiedTime(modifiedTime: String) = modifiedTime(JsonField.of(modifiedTime))

            /** Last modification time of the content */
            fun modifiedTime(modifiedTime: JsonField<String>) = apply {
                this.modifiedTime = modifiedTime
            }

            /** Open Graph description */
            fun ogDescription(ogDescription: String) = ogDescription(JsonField.of(ogDescription))

            /** Open Graph description */
            fun ogDescription(ogDescription: JsonField<String>) = apply {
                this.ogDescription = ogDescription
            }

            /** Open Graph image URL */
            fun ogImage(ogImage: String) = ogImage(JsonField.of(ogImage))

            /** Open Graph image URL */
            fun ogImage(ogImage: JsonField<String>) = apply { this.ogImage = ogImage }

            /** Open Graph site name */
            fun ogSiteName(ogSiteName: String) = ogSiteName(JsonField.of(ogSiteName))

            /** Open Graph site name */
            fun ogSiteName(ogSiteName: JsonField<String>) = apply { this.ogSiteName = ogSiteName }

            /** Open Graph title */
            fun ogTitle(ogTitle: String) = ogTitle(JsonField.of(ogTitle))

            /** Open Graph title */
            fun ogTitle(ogTitle: JsonField<String>) = apply { this.ogTitle = ogTitle }

            /** Open Graph URL */
            fun ogUrl(ogUrl: String) = ogUrl(JsonField.of(ogUrl))

            /** Open Graph URL */
            fun ogUrl(ogUrl: JsonField<String>) = apply { this.ogUrl = ogUrl }

            /** Publication time of the content */
            fun publishedTime(publishedTime: String) = publishedTime(JsonField.of(publishedTime))

            /** Publication time of the content */
            fun publishedTime(publishedTime: JsonField<String>) = apply {
                this.publishedTime = publishedTime
            }

            /** Timestamp when the scrape was performed */
            fun timestamp(timestamp: OffsetDateTime) = timestamp(JsonField.of(timestamp))

            /** Timestamp when the scrape was performed */
            fun timestamp(timestamp: JsonField<OffsetDateTime>) = apply {
                this.timestamp = timestamp
            }

            /** Title of the webpage */
            fun title(title: String) = title(JsonField.of(title))

            /** Title of the webpage */
            fun title(title: JsonField<String>) = apply { this.title = title }

            /** Source URL of the scraped page */
            fun urlSource(urlSource: String) = urlSource(JsonField.of(urlSource))

            /** Source URL of the scraped page */
            fun urlSource(urlSource: JsonField<String>) = apply { this.urlSource = urlSource }

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

            fun build(): Metadata =
                Metadata(
                    checkRequired("statusCode", statusCode),
                    articleAuthor,
                    author,
                    canonical,
                    description,
                    favicon,
                    jsonLd,
                    keywords,
                    language,
                    modifiedTime,
                    ogDescription,
                    ogImage,
                    ogSiteName,
                    ogTitle,
                    ogUrl,
                    publishedTime,
                    timestamp,
                    title,
                    urlSource,
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

        fun validate(): Metadata = apply {
            if (validated) {
                return@apply
            }

            statusCode()
            articleAuthor()
            author()
            canonical()
            description()
            favicon()
            keywords()
            language()
            modifiedTime()
            ogDescription()
            ogImage()
            ogSiteName()
            ogTitle()
            ogUrl()
            publishedTime()
            timestamp()
            title()
            urlSource()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (statusCode.asKnown().isPresent) 1 else 0) +
                (if (articleAuthor.asKnown().isPresent) 1 else 0) +
                (if (author.asKnown().isPresent) 1 else 0) +
                (if (canonical.asKnown().isPresent) 1 else 0) +
                (if (description.asKnown().isPresent) 1 else 0) +
                (if (favicon.asKnown().isPresent) 1 else 0) +
                (if (keywords.asKnown().isPresent) 1 else 0) +
                (if (language.asKnown().isPresent) 1 else 0) +
                (if (modifiedTime.asKnown().isPresent) 1 else 0) +
                (if (ogDescription.asKnown().isPresent) 1 else 0) +
                (if (ogImage.asKnown().isPresent) 1 else 0) +
                (if (ogSiteName.asKnown().isPresent) 1 else 0) +
                (if (ogTitle.asKnown().isPresent) 1 else 0) +
                (if (ogUrl.asKnown().isPresent) 1 else 0) +
                (if (publishedTime.asKnown().isPresent) 1 else 0) +
                (if (timestamp.asKnown().isPresent) 1 else 0) +
                (if (title.asKnown().isPresent) 1 else 0) +
                (if (urlSource.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Metadata &&
                this.statusCode == other.statusCode &&
                this.articleAuthor == other.articleAuthor &&
                this.author == other.author &&
                this.canonical == other.canonical &&
                this.description == other.description &&
                this.favicon == other.favicon &&
                this.jsonLd == other.jsonLd &&
                this.keywords == other.keywords &&
                this.language == other.language &&
                this.modifiedTime == other.modifiedTime &&
                this.ogDescription == other.ogDescription &&
                this.ogImage == other.ogImage &&
                this.ogSiteName == other.ogSiteName &&
                this.ogTitle == other.ogTitle &&
                this.ogUrl == other.ogUrl &&
                this.publishedTime == other.publishedTime &&
                this.timestamp == other.timestamp &&
                this.title == other.title &&
                this.urlSource == other.urlSource &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                statusCode,
                articleAuthor,
                author,
                canonical,
                description,
                favicon,
                jsonLd,
                keywords,
                language,
                modifiedTime,
                ogDescription,
                ogImage,
                ogSiteName,
                ogTitle,
                ogUrl,
                publishedTime,
                timestamp,
                title,
                urlSource,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Metadata{statusCode=$statusCode, articleAuthor=$articleAuthor, author=$author, canonical=$canonical, description=$description, favicon=$favicon, jsonLd=$jsonLd, keywords=$keywords, language=$language, modifiedTime=$modifiedTime, ogDescription=$ogDescription, ogImage=$ogImage, ogSiteName=$ogSiteName, ogTitle=$ogTitle, ogUrl=$ogUrl, publishedTime=$publishedTime, timestamp=$timestamp, title=$title, urlSource=$urlSource, additionalProperties=$additionalProperties}"
    }

    class Pdf
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of()
        ) : this(url, mutableMapOf())

        /** URL of the generated PDF */
        fun url(): String = url.getRequired("url")

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

            /** Returns a mutable builder for constructing an instance of [Pdf]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Pdf]. */
        class Builder internal constructor() {

            private var url: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(pdf: Pdf) = apply {
                this.url = pdf.url
                this.additionalProperties = pdf.additionalProperties.toMutableMap()
            }

            /** URL of the generated PDF */
            fun url(url: String) = url(JsonField.of(url))

            /** URL of the generated PDF */
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

            fun build(): Pdf = Pdf(checkRequired("url", url), additionalProperties.toMutableMap())
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Pdf = apply {
            if (validated) {
                return@apply
            }

            url()
            validated = true
        }

        @JvmSynthetic internal fun validity(): Int = (if (url.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Pdf &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(url, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Pdf{url=$url, additionalProperties=$additionalProperties}"
    }

    class Screenshot
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of()
        ) : this(url, mutableMapOf())

        /** URL of the screenshot image */
        fun url(): String = url.getRequired("url")

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

            /** Returns a mutable builder for constructing an instance of [Screenshot]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Screenshot]. */
        class Builder internal constructor() {

            private var url: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(screenshot: Screenshot) = apply {
                this.url = screenshot.url
                this.additionalProperties = screenshot.additionalProperties.toMutableMap()
            }

            /** URL of the screenshot image */
            fun url(url: String) = url(JsonField.of(url))

            /** URL of the screenshot image */
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

            fun build(): Screenshot =
                Screenshot(checkRequired("url", url), additionalProperties.toMutableMap())
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Screenshot = apply {
            if (validated) {
                return@apply
            }

            url()
            validated = true
        }

        @JvmSynthetic internal fun validity(): Int = (if (url.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Screenshot &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(url, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Screenshot{url=$url, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ClientScrapeResponse &&
            this.content == other.content &&
            this.links == other.links &&
            this.metadata == other.metadata &&
            this.pdf == other.pdf &&
            this.screenshot == other.screenshot &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, links, metadata, pdf, screenshot, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ClientScrapeResponse{content=$content, links=$links, metadata=$metadata, pdf=$pdf, screenshot=$screenshot, additionalProperties=$additionalProperties}"
}
