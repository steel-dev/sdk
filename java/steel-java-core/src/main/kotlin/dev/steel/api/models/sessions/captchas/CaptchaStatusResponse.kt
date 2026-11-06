package dev.steel.api.models.sessions.captchas

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class CaptchaStatusResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val isSolvingCaptcha: JsonField<Boolean>,
    private val pageId: JsonField<String>,
    private val tasks: JsonField<List<JsonValue>>,
    private val url: JsonField<String>,
    private val created: JsonField<Double>,
    private val lastUpdated: JsonField<Double>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("isSolvingCaptcha")
        @ExcludeMissing
        isSolvingCaptcha: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("pageId") @ExcludeMissing pageId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tasks") @ExcludeMissing tasks: JsonField<List<JsonValue>> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created") @ExcludeMissing created: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("lastUpdated")
        @ExcludeMissing
        lastUpdated: JsonField<Double> = JsonMissing.of(),
    ) : this(isSolvingCaptcha, pageId, tasks, url, created, lastUpdated, mutableMapOf())

    /** Whether a captcha is currently being solved */
    fun isSolvingCaptcha(): Boolean = isSolvingCaptcha.getRequired("isSolvingCaptcha")

    /** The page ID where the captcha is located */
    fun pageId(): String = pageId.getRequired("pageId")

    /** Array of captcha tasks */
    fun tasks(): List<JsonValue> = tasks.getRequired("tasks")

    /** The URL where the captcha is located */
    fun url(): String = url.getRequired("url")

    /** Timestamp when the state was created */
    fun created(): Optional<Double> = created.getOptional("created")

    /** Timestamp when the state was last updated */
    fun lastUpdated(): Optional<Double> = lastUpdated.getOptional("lastUpdated")

    @JsonProperty("isSolvingCaptcha")
    @ExcludeMissing
    fun _isSolvingCaptcha(): JsonField<Boolean> = isSolvingCaptcha

    @JsonProperty("pageId") @ExcludeMissing fun _pageId(): JsonField<String> = pageId

    @JsonProperty("tasks") @ExcludeMissing fun _tasks(): JsonField<List<JsonValue>> = tasks

    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    @JsonProperty("created") @ExcludeMissing fun _created(): JsonField<Double> = created

    @JsonProperty("lastUpdated") @ExcludeMissing fun _lastUpdated(): JsonField<Double> = lastUpdated

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

        /** Returns a mutable builder for constructing an instance of [CaptchaStatusResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CaptchaStatusResponse]. */
    class Builder internal constructor() {

        private var isSolvingCaptcha: JsonField<Boolean>? = null
        private var pageId: JsonField<String>? = null
        private var tasks: JsonField<MutableList<JsonValue>>? = null
        private var url: JsonField<String>? = null
        private var created: JsonField<Double> = JsonMissing.of()
        private var lastUpdated: JsonField<Double> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(captchaStatusResponse: CaptchaStatusResponse) = apply {
            this.isSolvingCaptcha = captchaStatusResponse.isSolvingCaptcha
            this.pageId = captchaStatusResponse.pageId
            this.tasks = captchaStatusResponse.tasks.map { it.toMutableList() }
            this.url = captchaStatusResponse.url
            this.created = captchaStatusResponse.created
            this.lastUpdated = captchaStatusResponse.lastUpdated
            this.additionalProperties = captchaStatusResponse.additionalProperties.toMutableMap()
        }

        /** Whether a captcha is currently being solved */
        fun isSolvingCaptcha(isSolvingCaptcha: Boolean) =
            isSolvingCaptcha(JsonField.of(isSolvingCaptcha))

        /** Whether a captcha is currently being solved */
        fun isSolvingCaptcha(isSolvingCaptcha: JsonField<Boolean>) = apply {
            this.isSolvingCaptcha = isSolvingCaptcha
        }

        /** The page ID where the captcha is located */
        fun pageId(pageId: String) = pageId(JsonField.of(pageId))

        /** The page ID where the captcha is located */
        fun pageId(pageId: JsonField<String>) = apply { this.pageId = pageId }

        /** Array of captcha tasks */
        fun tasks(tasks: List<JsonValue>) = tasks(JsonField.of(tasks))

        /** Array of captcha tasks */
        fun tasks(tasks: JsonField<List<JsonValue>>) = apply {
            this.tasks = tasks.map { it.toMutableList() }
        }

        /** Array of captcha tasks */
        fun addTask(task: JsonValue) = apply {
            this.tasks =
                (this.tasks ?: JsonField.of(mutableListOf())).also {
                    checkKnown("tasks", it).add(task)
                }
        }

        /** The URL where the captcha is located */
        fun url(url: String) = url(JsonField.of(url))

        /** The URL where the captcha is located */
        fun url(url: JsonField<String>) = apply { this.url = url }

        /** Timestamp when the state was created */
        fun created(created: Double) = created(JsonField.of(created))

        /** Timestamp when the state was created */
        fun created(created: JsonField<Double>) = apply { this.created = created }

        /** Timestamp when the state was last updated */
        fun lastUpdated(lastUpdated: Double) = lastUpdated(JsonField.of(lastUpdated))

        /** Timestamp when the state was last updated */
        fun lastUpdated(lastUpdated: JsonField<Double>) = apply { this.lastUpdated = lastUpdated }

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

        fun build(): CaptchaStatusResponse =
            CaptchaStatusResponse(
                checkRequired("isSolvingCaptcha", isSolvingCaptcha),
                checkRequired("pageId", pageId),
                checkRequired("tasks", tasks).map { it.toImmutable() },
                checkRequired("url", url),
                created,
                lastUpdated,
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

    fun validate(): CaptchaStatusResponse = apply {
        if (validated) {
            return@apply
        }

        isSolvingCaptcha()
        pageId()
        tasks()
        url()
        created()
        lastUpdated()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (isSolvingCaptcha.asKnown().isPresent) 1 else 0) +
            (if (pageId.asKnown().isPresent) 1 else 0) +
            (tasks.asKnown().getOrNull()?.let { it.size } ?: 0) +
            (if (url.asKnown().isPresent) 1 else 0) +
            (if (created.asKnown().isPresent) 1 else 0) +
            (if (lastUpdated.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CaptchaStatusResponse &&
            this.isSolvingCaptcha == other.isSolvingCaptcha &&
            this.pageId == other.pageId &&
            this.tasks == other.tasks &&
            this.url == other.url &&
            this.created == other.created &&
            this.lastUpdated == other.lastUpdated &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            isSolvingCaptcha,
            pageId,
            tasks,
            url,
            created,
            lastUpdated,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CaptchaStatusResponse{isSolvingCaptcha=$isSolvingCaptcha, pageId=$pageId, tasks=$tasks, url=$url, created=$created, lastUpdated=$lastUpdated, additionalProperties=$additionalProperties}"
}
