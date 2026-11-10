package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.Enum
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

class SessionCommandsResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val execution: JsonField<Execution>,
    private val page: JsonField<PageModel>,
    private val results: JsonField<List<CommandResult>>,
    private val bodyExpired: JsonField<Boolean>,
    private val dialog: JsonField<CommandDialog>,
    private val epochChanged: JsonField<Boolean>,
    private val next: JsonField<String>,
    private val observe: JsonField<Observe>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("execution")
        @ExcludeMissing
        execution: JsonField<Execution> = JsonMissing.of(),
        @JsonProperty("page") @ExcludeMissing page: JsonField<PageModel> = JsonMissing.of(),
        @JsonProperty("results")
        @ExcludeMissing
        results: JsonField<List<CommandResult>> = JsonMissing.of(),
        @JsonProperty("bodyExpired")
        @ExcludeMissing
        bodyExpired: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("dialog") @ExcludeMissing dialog: JsonField<CommandDialog> = JsonMissing.of(),
        @JsonProperty("epochChanged")
        @ExcludeMissing
        epochChanged: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("next") @ExcludeMissing next: JsonField<String> = JsonMissing.of(),
        @JsonProperty("observe") @ExcludeMissing observe: JsonField<Observe> = JsonMissing.of(),
    ) : this(
        id,
        execution,
        page,
        results,
        bodyExpired,
        dialog,
        epochChanged,
        next,
        observe,
        mutableMapOf(),
    )

    /**
     * The id of this execution, the same as execution.id. Use it to get or cancel the execution.
     */
    fun id(): String = id.getRequired("id")

    fun execution(): Execution = execution.getRequired("execution")

    /** The tab the execution used. */
    fun page(): PageModel? = page.getNullable("page")

    fun results(): List<CommandResult> = results.getRequired("results")

    fun bodyExpired(): Boolean? = bodyExpired.getNullable("bodyExpired")

    /** A dialog that is open on the tab and blocks it until it closes. */
    fun dialog(): CommandDialog? = dialog.getNullable("dialog")

    /**
     * Set when the execution ran with after. Whether another execution, a quick action, or a
     * browser restart changed the session since then. Input from CDP clients or the live view does
     * not count.
     */
    fun epochChanged(): Boolean? = epochChanged.getNullable("epochChanged")

    /** Send it as after in the next request. */
    fun next(): String? = next.getNullable("next")

    fun observe(): Observe? = observe.getNullable("observe")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonProperty("execution") @ExcludeMissing fun _execution(): JsonField<Execution> = execution

    @JsonProperty("page") @ExcludeMissing fun _page(): JsonField<PageModel> = page

    @JsonProperty("results")
    @ExcludeMissing
    fun _results(): JsonField<List<CommandResult>> = results

    @JsonProperty("bodyExpired")
    @ExcludeMissing
    fun _bodyExpired(): JsonField<Boolean> = bodyExpired

    @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

    @JsonProperty("epochChanged")
    @ExcludeMissing
    fun _epochChanged(): JsonField<Boolean> = epochChanged

    @JsonProperty("next") @ExcludeMissing fun _next(): JsonField<String> = next

    @JsonProperty("observe") @ExcludeMissing fun _observe(): JsonField<Observe> = observe

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

        /** Returns a mutable builder for constructing an instance of [SessionCommandsResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionCommandsResponse]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var execution: JsonField<Execution>? = null
        private var page: JsonField<PageModel>? = null
        private var results: JsonField<MutableList<CommandResult>>? = null
        private var bodyExpired: JsonField<Boolean> = JsonMissing.of()
        private var dialog: JsonField<CommandDialog> = JsonMissing.of()
        private var epochChanged: JsonField<Boolean> = JsonMissing.of()
        private var next: JsonField<String> = JsonMissing.of()
        private var observe: JsonField<Observe> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionCommandsResponse: SessionCommandsResponse) = apply {
            this.id = sessionCommandsResponse.id
            this.execution = sessionCommandsResponse.execution
            this.page = sessionCommandsResponse.page
            this.results = sessionCommandsResponse.results.map { it.toMutableList() }
            this.bodyExpired = sessionCommandsResponse.bodyExpired
            this.dialog = sessionCommandsResponse.dialog
            this.epochChanged = sessionCommandsResponse.epochChanged
            this.next = sessionCommandsResponse.next
            this.observe = sessionCommandsResponse.observe
            this.additionalProperties = sessionCommandsResponse.additionalProperties.toMutableMap()
        }

        /**
         * The id of this execution, the same as execution.id. Use it to get or cancel the
         * execution.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * The id of this execution, the same as execution.id. Use it to get or cancel the
         * execution.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun execution(execution: Execution) = execution(JsonField.of(execution))

        fun execution(execution: JsonField<Execution>) = apply { this.execution = execution }

        /** The tab the execution used. */
        fun page(page: PageModel?) = page(JsonField.ofNullable(page))

        /** The tab the execution used. */
        fun page(page: JsonField<PageModel>) = apply { this.page = page }

        fun results(results: List<CommandResult>) = results(JsonField.of(results))

        fun results(results: JsonField<List<CommandResult>>) = apply {
            this.results = results.map { it.toMutableList() }
        }

        fun addResult(result: CommandResult) = apply {
            this.results =
                (this.results ?: JsonField.of(mutableListOf())).also {
                    checkKnown("results", it).add(result)
                }
        }

        fun bodyExpired(bodyExpired: Boolean) = bodyExpired(JsonField.of(bodyExpired))

        fun bodyExpired(bodyExpired: JsonField<Boolean>) = apply { this.bodyExpired = bodyExpired }

        /** A dialog that is open on the tab and blocks it until it closes. */
        fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

        /** A dialog that is open on the tab and blocks it until it closes. */
        fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

        /**
         * Set when the execution ran with after. Whether another execution, a quick action, or a
         * browser restart changed the session since then. Input from CDP clients or the live view
         * does not count.
         */
        fun epochChanged(epochChanged: Boolean) = epochChanged(JsonField.of(epochChanged))

        /**
         * Set when the execution ran with after. Whether another execution, a quick action, or a
         * browser restart changed the session since then. Input from CDP clients or the live view
         * does not count.
         */
        fun epochChanged(epochChanged: JsonField<Boolean>) = apply {
            this.epochChanged = epochChanged
        }

        /** Send it as after in the next request. */
        fun next(next: String) = next(JsonField.of(next))

        /** Send it as after in the next request. */
        fun next(next: JsonField<String>) = apply { this.next = next }

        fun observe(observe: Observe) = observe(JsonField.of(observe))

        fun observe(observe: JsonField<Observe>) = apply { this.observe = observe }

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

        fun build(): SessionCommandsResponse =
            SessionCommandsResponse(
                checkRequired("id", id),
                checkRequired("execution", execution),
                checkRequired("page", page),
                checkRequired("results", results).map { it.toImmutable() },
                bodyExpired,
                dialog,
                epochChanged,
                next,
                observe,
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

    fun validate(): SessionCommandsResponse = apply {
        if (validated) {
            return@apply
        }

        id()
        execution().validate()
        page()?.let { it.validate() }
        results().forEach { it.validate() }
        bodyExpired()
        dialog()?.let { it.validate() }
        epochChanged()
        next()
        observe()?.let { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (execution.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (page.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (results.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (if (bodyExpired.asKnown().isPresent) 1 else 0) +
            (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (epochChanged.asKnown().isPresent) 1 else 0) +
            (if (next.asKnown().isPresent) 1 else 0) +
            (observe.asKnown().getOrNull()?.let { it.validity() } ?: 0)

    class Execution
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val effect: JsonField<Effect>,
        private val ran: JsonField<Long>,
        private val replayed: JsonField<Boolean>,
        private val state: JsonField<State>,
        private val error: JsonField<CommandErrorInfo>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("effect") @ExcludeMissing effect: JsonField<Effect> = JsonMissing.of(),
            @JsonProperty("ran") @ExcludeMissing ran: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("replayed")
            @ExcludeMissing
            replayed: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("state") @ExcludeMissing state: JsonField<State> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
        ) : this(id, effect, ran, replayed, state, error, mutableMapOf())

        fun id(): String = id.getRequired("id")

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): Effect = effect.getRequired("effect")

        /** Commands started. */
        fun ran(): Long = ran.getRequired("ran")

        fun replayed(): Boolean = replayed.getRequired("replayed")

        fun state(): State = state.getRequired("state")

        fun error(): CommandErrorInfo? = error.getNullable("error")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("effect") @ExcludeMissing fun _effect(): JsonField<Effect> = effect

        @JsonProperty("ran") @ExcludeMissing fun _ran(): JsonField<Long> = ran

        @JsonProperty("replayed") @ExcludeMissing fun _replayed(): JsonField<Boolean> = replayed

        @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<State> = state

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

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

            /** Returns a mutable builder for constructing an instance of [Execution]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Execution]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var effect: JsonField<Effect>? = null
            private var ran: JsonField<Long>? = null
            private var replayed: JsonField<Boolean>? = null
            private var state: JsonField<State>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(execution: Execution) = apply {
                this.id = execution.id
                this.effect = execution.effect
                this.ran = execution.ran
                this.replayed = execution.replayed
                this.state = execution.state
                this.error = execution.error
                this.additionalProperties = execution.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            fun id(id: JsonField<String>) = apply { this.id = id }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<Effect>) = apply { this.effect = effect }

            /** Commands started. */
            fun ran(ran: Long) = ran(JsonField.of(ran))

            /** Commands started. */
            fun ran(ran: JsonField<Long>) = apply { this.ran = ran }

            fun replayed(replayed: Boolean) = replayed(JsonField.of(replayed))

            fun replayed(replayed: JsonField<Boolean>) = apply { this.replayed = replayed }

            fun state(state: State) = state(JsonField.of(state))

            fun state(state: JsonField<State>) = apply { this.state = state }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

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

            fun build(): Execution =
                Execution(
                    checkRequired("id", id),
                    checkRequired("effect", effect),
                    checkRequired("ran", ran),
                    checkRequired("replayed", replayed),
                    checkRequired("state", state),
                    error,
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

        fun validate(): Execution = apply {
            if (validated) {
                return@apply
            }

            id()
            effect().validate()
            ran()
            replayed()
            state().validate()
            error()?.let { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ran.asKnown().isPresent) 1 else 0) +
                (if (replayed.asKnown().isPresent) 1 else 0) +
                (state.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        class Effect @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val NONE = of("none")

                @JvmField val APPLIED = of("applied")

                @JvmField val UNKNOWN = of("unknown")

                @JvmStatic fun of(value: String) = Effect(JsonField.of(value))
            }

            enum class Known {
                NONE,
                APPLIED,
                UNKNOWN,
            }

            enum class Value {
                NONE,
                APPLIED,
                UNKNOWN,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    NONE -> Value.NONE
                    APPLIED -> Value.APPLIED
                    UNKNOWN -> Value.UNKNOWN
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    NONE -> Known.NONE
                    APPLIED -> Known.APPLIED
                    UNKNOWN -> Known.UNKNOWN
                    else -> throw SteelInvalidDataException("Unknown Effect: $value")
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

            fun validate(): Effect = apply {
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

                return other is Effect && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        class State @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val QUEUED = of("queued")

                @JvmField val RUNNING = of("running")

                @JvmField val COMPLETED = of("completed")

                @JvmField val FAILED = of("failed")

                @JvmField val CANCELLED = of("cancelled")

                @JvmField val TIMEOUT = of("timeout")

                @JvmField val EXPIRED = of("expired")

                @JvmStatic fun of(value: String) = State(JsonField.of(value))
            }

            enum class Known {
                QUEUED,
                RUNNING,
                COMPLETED,
                FAILED,
                CANCELLED,
                TIMEOUT,
                EXPIRED,
            }

            enum class Value {
                QUEUED,
                RUNNING,
                COMPLETED,
                FAILED,
                CANCELLED,
                TIMEOUT,
                EXPIRED,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    QUEUED -> Value.QUEUED
                    RUNNING -> Value.RUNNING
                    COMPLETED -> Value.COMPLETED
                    FAILED -> Value.FAILED
                    CANCELLED -> Value.CANCELLED
                    TIMEOUT -> Value.TIMEOUT
                    EXPIRED -> Value.EXPIRED
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    QUEUED -> Known.QUEUED
                    RUNNING -> Known.RUNNING
                    COMPLETED -> Known.COMPLETED
                    FAILED -> Known.FAILED
                    CANCELLED -> Known.CANCELLED
                    TIMEOUT -> Known.TIMEOUT
                    EXPIRED -> Known.EXPIRED
                    else -> throw SteelInvalidDataException("Unknown State: $value")
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

            fun validate(): State = apply {
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

                return other is State && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Execution &&
                this.id == other.id &&
                this.effect == other.effect &&
                this.ran == other.ran &&
                this.replayed == other.replayed &&
                this.state == other.state &&
                this.error == other.error &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, effect, ran, replayed, state, error, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Execution{id=$id, effect=$effect, ran=$ran, replayed=$replayed, state=$state, error=$error, additionalProperties=$additionalProperties}"
    }

    /** The tab the execution used. */
    class PageModel
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val tab: JsonField<String>,
        private val title: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(tab, title, url, mutableMapOf())

        fun tab(): String = tab.getRequired("tab")

        fun title(): String = title.getRequired("title")

        fun url(): String = url.getRequired("url")

        @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

            private var tab: JsonField<String>? = null
            private var title: JsonField<String>? = null
            private var url: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(pageModel: PageModel) = apply {
                this.tab = pageModel.tab
                this.title = pageModel.title
                this.url = pageModel.url
                this.additionalProperties = pageModel.additionalProperties.toMutableMap()
            }

            fun tab(tab: String) = tab(JsonField.of(tab))

            fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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
                    checkRequired("tab", tab),
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

            tab()
            title()
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (tab.asKnown().isPresent) 1 else 0) +
                (if (title.asKnown().isPresent) 1 else 0) +
                (if (url.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is PageModel &&
                this.tab == other.tab &&
                this.title == other.title &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(tab, title, url, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "PageModel{tab=$tab, title=$title, url=$url, additionalProperties=$additionalProperties}"
    }

    class Observe
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val additions: JsonField<Long>,
        private val base: JsonField<String>,
        private val diff: JsonField<String>,
        private val error: JsonField<CommandErrorInfo>,
        private val full: JsonField<Boolean>,
        private val removals: JsonField<Long>,
        private val snapshot: JsonField<String>,
        private val snapshotId: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("additions")
            @ExcludeMissing
            additions: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("base") @ExcludeMissing base: JsonField<String> = JsonMissing.of(),
            @JsonProperty("diff") @ExcludeMissing diff: JsonField<String> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("full") @ExcludeMissing full: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("removals") @ExcludeMissing removals: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("snapshot")
            @ExcludeMissing
            snapshot: JsonField<String> = JsonMissing.of(),
            @JsonProperty("snapshotId")
            @ExcludeMissing
            snapshotId: JsonField<String> = JsonMissing.of(),
        ) : this(additions, base, diff, error, full, removals, snapshot, snapshotId, mutableMapOf())

        fun additions(): Long? = additions.getNullable("additions")

        fun base(): String? = base.getNullable("base")

        fun diff(): String? = diff.getNullable("diff")

        fun error(): CommandErrorInfo? = error.getNullable("error")

        fun full(): Boolean? = full.getNullable("full")

        fun removals(): Long? = removals.getNullable("removals")

        fun snapshot(): String? = snapshot.getNullable("snapshot")

        fun snapshotId(): String? = snapshotId.getNullable("snapshotId")

        @JsonProperty("additions") @ExcludeMissing fun _additions(): JsonField<Long> = additions

        @JsonProperty("base") @ExcludeMissing fun _base(): JsonField<String> = base

        @JsonProperty("diff") @ExcludeMissing fun _diff(): JsonField<String> = diff

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("full") @ExcludeMissing fun _full(): JsonField<Boolean> = full

        @JsonProperty("removals") @ExcludeMissing fun _removals(): JsonField<Long> = removals

        @JsonProperty("snapshot") @ExcludeMissing fun _snapshot(): JsonField<String> = snapshot

        @JsonProperty("snapshotId")
        @ExcludeMissing
        fun _snapshotId(): JsonField<String> = snapshotId

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

            /** Returns a mutable builder for constructing an instance of [Observe]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Observe]. */
        class Builder internal constructor() {

            private var additions: JsonField<Long> = JsonMissing.of()
            private var base: JsonField<String> = JsonMissing.of()
            private var diff: JsonField<String> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var full: JsonField<Boolean> = JsonMissing.of()
            private var removals: JsonField<Long> = JsonMissing.of()
            private var snapshot: JsonField<String> = JsonMissing.of()
            private var snapshotId: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(observe: Observe) = apply {
                this.additions = observe.additions
                this.base = observe.base
                this.diff = observe.diff
                this.error = observe.error
                this.full = observe.full
                this.removals = observe.removals
                this.snapshot = observe.snapshot
                this.snapshotId = observe.snapshotId
                this.additionalProperties = observe.additionalProperties.toMutableMap()
            }

            fun additions(additions: Long) = additions(JsonField.of(additions))

            fun additions(additions: JsonField<Long>) = apply { this.additions = additions }

            fun base(base: String) = base(JsonField.of(base))

            fun base(base: JsonField<String>) = apply { this.base = base }

            fun diff(diff: String) = diff(JsonField.of(diff))

            fun diff(diff: JsonField<String>) = apply { this.diff = diff }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun full(full: Boolean) = full(JsonField.of(full))

            fun full(full: JsonField<Boolean>) = apply { this.full = full }

            fun removals(removals: Long) = removals(JsonField.of(removals))

            fun removals(removals: JsonField<Long>) = apply { this.removals = removals }

            fun snapshot(snapshot: String) = snapshot(JsonField.of(snapshot))

            fun snapshot(snapshot: JsonField<String>) = apply { this.snapshot = snapshot }

            fun snapshotId(snapshotId: String) = snapshotId(JsonField.of(snapshotId))

            fun snapshotId(snapshotId: JsonField<String>) = apply { this.snapshotId = snapshotId }

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

            fun build(): Observe =
                Observe(
                    additions,
                    base,
                    diff,
                    error,
                    full,
                    removals,
                    snapshot,
                    snapshotId,
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

        fun validate(): Observe = apply {
            if (validated) {
                return@apply
            }

            additions()
            base()
            diff()
            error()?.let { it.validate() }
            full()
            removals()
            snapshot()
            snapshotId()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (additions.asKnown().isPresent) 1 else 0) +
                (if (base.asKnown().isPresent) 1 else 0) +
                (if (diff.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (full.asKnown().isPresent) 1 else 0) +
                (if (removals.asKnown().isPresent) 1 else 0) +
                (if (snapshot.asKnown().isPresent) 1 else 0) +
                (if (snapshotId.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Observe &&
                this.additions == other.additions &&
                this.base == other.base &&
                this.diff == other.diff &&
                this.error == other.error &&
                this.full == other.full &&
                this.removals == other.removals &&
                this.snapshot == other.snapshot &&
                this.snapshotId == other.snapshotId &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                additions,
                base,
                diff,
                error,
                full,
                removals,
                snapshot,
                snapshotId,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Observe{additions=$additions, base=$base, diff=$diff, error=$error, full=$full, removals=$removals, snapshot=$snapshot, snapshotId=$snapshotId, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionCommandsResponse &&
            this.id == other.id &&
            this.execution == other.execution &&
            this.page == other.page &&
            this.results == other.results &&
            this.bodyExpired == other.bodyExpired &&
            this.dialog == other.dialog &&
            this.epochChanged == other.epochChanged &&
            this.next == other.next &&
            this.observe == other.observe &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            execution,
            page,
            results,
            bodyExpired,
            dialog,
            epochChanged,
            next,
            observe,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SessionCommandsResponse{id=$id, execution=$execution, page=$page, results=$results, bodyExpired=$bodyExpired, dialog=$dialog, epochChanged=$epochChanged, next=$next, observe=$observe, additionalProperties=$additionalProperties}"
}
