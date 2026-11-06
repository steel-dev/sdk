package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name

/** Finds an element. Set exactly one of role, text, label, placeholder, alt, title, testid, css. */
class CommandLocator
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val alt: JsonField<String>,
    private val css: JsonField<String>,
    private val exact: JsonField<Boolean>,
    private val label: JsonField<String>,
    private val name: JsonField<String>,
    private val nth: JsonField<Long>,
    private val placeholder: JsonField<String>,
    private val role: JsonField<String>,
    private val testid: JsonField<String>,
    private val text: JsonField<String>,
    private val title: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("alt") @ExcludeMissing alt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("css") @ExcludeMissing css: JsonField<String> = JsonMissing.of(),
        @JsonProperty("exact") @ExcludeMissing exact: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("label") @ExcludeMissing label: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("nth") @ExcludeMissing nth: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("placeholder")
        @ExcludeMissing
        placeholder: JsonField<String> = JsonMissing.of(),
        @JsonProperty("role") @ExcludeMissing role: JsonField<String> = JsonMissing.of(),
        @JsonProperty("testid") @ExcludeMissing testid: JsonField<String> = JsonMissing.of(),
        @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
    ) : this(
        alt,
        css,
        exact,
        label,
        name,
        nth,
        placeholder,
        role,
        testid,
        text,
        title,
        mutableMapOf(),
    )

    fun alt(): Optional<String> = alt.getOptional("alt")

    fun css(): Optional<String> = css.getOptional("css")

    /** Match the whole text. Text matches a case-insensitive substring by default. */
    fun exact(): Optional<Boolean> = exact.getOptional("exact")

    fun label(): Optional<String> = label.getOptional("label")

    /** The accessible name. Works only with role. */
    fun name(): Optional<String> = name.getOptional("name")

    /** Picks one of several matches. Negative values count from the end. */
    fun nth(): Optional<Long> = nth.getOptional("nth")

    fun placeholder(): Optional<String> = placeholder.getOptional("placeholder")

    fun role(): Optional<String> = role.getOptional("role")

    fun testid(): Optional<String> = testid.getOptional("testid")

    fun text(): Optional<String> = text.getOptional("text")

    fun title(): Optional<String> = title.getOptional("title")

    @JsonProperty("alt") @ExcludeMissing fun _alt(): JsonField<String> = alt

    @JsonProperty("css") @ExcludeMissing fun _css(): JsonField<String> = css

    @JsonProperty("exact") @ExcludeMissing fun _exact(): JsonField<Boolean> = exact

    @JsonProperty("label") @ExcludeMissing fun _label(): JsonField<String> = label

    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    @JsonProperty("nth") @ExcludeMissing fun _nth(): JsonField<Long> = nth

    @JsonProperty("placeholder") @ExcludeMissing fun _placeholder(): JsonField<String> = placeholder

    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<String> = role

    @JsonProperty("testid") @ExcludeMissing fun _testid(): JsonField<String> = testid

    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

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

        /** Returns a mutable builder for constructing an instance of [CommandLocator]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandLocator]. */
    class Builder internal constructor() {

        private var alt: JsonField<String> = JsonMissing.of()
        private var css: JsonField<String> = JsonMissing.of()
        private var exact: JsonField<Boolean> = JsonMissing.of()
        private var label: JsonField<String> = JsonMissing.of()
        private var name: JsonField<String> = JsonMissing.of()
        private var nth: JsonField<Long> = JsonMissing.of()
        private var placeholder: JsonField<String> = JsonMissing.of()
        private var role: JsonField<String> = JsonMissing.of()
        private var testid: JsonField<String> = JsonMissing.of()
        private var text: JsonField<String> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandLocator: CommandLocator) = apply {
            this.alt = commandLocator.alt
            this.css = commandLocator.css
            this.exact = commandLocator.exact
            this.label = commandLocator.label
            this.name = commandLocator.name
            this.nth = commandLocator.nth
            this.placeholder = commandLocator.placeholder
            this.role = commandLocator.role
            this.testid = commandLocator.testid
            this.text = commandLocator.text
            this.title = commandLocator.title
            this.additionalProperties = commandLocator.additionalProperties.toMutableMap()
        }

        fun alt(alt: String) = alt(JsonField.of(alt))

        fun alt(alt: JsonField<String>) = apply { this.alt = alt }

        fun css(css: String) = css(JsonField.of(css))

        fun css(css: JsonField<String>) = apply { this.css = css }

        /** Match the whole text. Text matches a case-insensitive substring by default. */
        fun exact(exact: Boolean) = exact(JsonField.of(exact))

        /** Match the whole text. Text matches a case-insensitive substring by default. */
        fun exact(exact: JsonField<Boolean>) = apply { this.exact = exact }

        fun label(label: String) = label(JsonField.of(label))

        fun label(label: JsonField<String>) = apply { this.label = label }

        /** The accessible name. Works only with role. */
        fun name(name: String) = name(JsonField.of(name))

        /** The accessible name. Works only with role. */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** Picks one of several matches. Negative values count from the end. */
        fun nth(nth: Long) = nth(JsonField.of(nth))

        /** Picks one of several matches. Negative values count from the end. */
        fun nth(nth: JsonField<Long>) = apply { this.nth = nth }

        fun placeholder(placeholder: String) = placeholder(JsonField.of(placeholder))

        fun placeholder(placeholder: JsonField<String>) = apply { this.placeholder = placeholder }

        fun role(role: String) = role(JsonField.of(role))

        fun role(role: JsonField<String>) = apply { this.role = role }

        fun testid(testid: String) = testid(JsonField.of(testid))

        fun testid(testid: JsonField<String>) = apply { this.testid = testid }

        fun text(text: String) = text(JsonField.of(text))

        fun text(text: JsonField<String>) = apply { this.text = text }

        fun title(title: String) = title(JsonField.of(title))

        fun title(title: JsonField<String>) = apply { this.title = title }

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

        fun build(): CommandLocator =
            CommandLocator(
                alt,
                css,
                exact,
                label,
                name,
                nth,
                placeholder,
                role,
                testid,
                text,
                title,
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

    fun validate(): CommandLocator = apply {
        if (validated) {
            return@apply
        }

        alt()
        css()
        exact()
        label()
        name()
        nth()
        placeholder()
        role()
        testid()
        text()
        title()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (alt.asKnown().isPresent) 1 else 0) +
            (if (css.asKnown().isPresent) 1 else 0) +
            (if (exact.asKnown().isPresent) 1 else 0) +
            (if (label.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (nth.asKnown().isPresent) 1 else 0) +
            (if (placeholder.asKnown().isPresent) 1 else 0) +
            (if (role.asKnown().isPresent) 1 else 0) +
            (if (testid.asKnown().isPresent) 1 else 0) +
            (if (text.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandLocator &&
            this.alt == other.alt &&
            this.css == other.css &&
            this.exact == other.exact &&
            this.label == other.label &&
            this.name == other.name &&
            this.nth == other.nth &&
            this.placeholder == other.placeholder &&
            this.role == other.role &&
            this.testid == other.testid &&
            this.text == other.text &&
            this.title == other.title &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            alt,
            css,
            exact,
            label,
            name,
            nth,
            placeholder,
            role,
            testid,
            text,
            title,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandLocator{alt=$alt, css=$css, exact=$exact, label=$label, name=$name, nth=$nth, placeholder=$placeholder, role=$role, testid=$testid, text=$text, title=$title, additionalProperties=$additionalProperties}"
}
