package dev.steel.api.models.sessions.commandexecutions

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
import dev.steel.api.models.sessions.CommandDialog
import dev.steel.api.models.sessions.CommandErrorInfo
import dev.steel.api.models.sessions.CommandHeld
import dev.steel.api.models.sessions.CommandResult
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class CommandExecutionCancelResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val effect: JsonField<Effect>,
    private val page: JsonField<PageModel>,
    private val ran: JsonField<Long>,
    private val replayed: JsonField<Boolean>,
    private val results: JsonField<List<CommandResult>>,
    private val state: JsonField<State>,
    private val bodyExpired: JsonField<Boolean>,
    private val dialog: JsonField<CommandDialog>,
    private val epochChanged: JsonField<Boolean>,
    private val held: JsonField<List<CommandHeld>>,
    private val next: JsonField<String>,
    private val observe: JsonField<Observe>,
    private val stopped: JsonField<CommandErrorInfo>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("effect") @ExcludeMissing effect: JsonField<Effect> = JsonMissing.of(),
        @JsonProperty("page") @ExcludeMissing page: JsonField<PageModel> = JsonMissing.of(),
        @JsonProperty("ran") @ExcludeMissing ran: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("replayed") @ExcludeMissing replayed: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("results")
        @ExcludeMissing
        results: JsonField<List<CommandResult>> = JsonMissing.of(),
        @JsonProperty("state") @ExcludeMissing state: JsonField<State> = JsonMissing.of(),
        @JsonProperty("bodyExpired")
        @ExcludeMissing
        bodyExpired: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("dialog") @ExcludeMissing dialog: JsonField<CommandDialog> = JsonMissing.of(),
        @JsonProperty("epochChanged")
        @ExcludeMissing
        epochChanged: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("held") @ExcludeMissing held: JsonField<List<CommandHeld>> = JsonMissing.of(),
        @JsonProperty("next") @ExcludeMissing next: JsonField<String> = JsonMissing.of(),
        @JsonProperty("observe") @ExcludeMissing observe: JsonField<Observe> = JsonMissing.of(),
        @JsonProperty("stopped")
        @ExcludeMissing
        stopped: JsonField<CommandErrorInfo> = JsonMissing.of(),
    ) : this(
        id,
        effect,
        page,
        ran,
        replayed,
        results,
        state,
        bodyExpired,
        dialog,
        epochChanged,
        held,
        next,
        observe,
        stopped,
        mutableMapOf(),
    )

    /** The id of this execution. Use it to get or cancel the execution. */
    fun id(): String = id.getRequired("id")

    /** What happened to the page: nothing, a change, or a possible change. */
    fun effect(): Effect = effect.getRequired("effect")

    /** The tab the execution used. */
    fun page(): PageModel? = page.getNullable("page")

    /** Commands started. */
    fun ran(): Long = ran.getRequired("ran")

    /** This is the stored result of an earlier request. Nothing ran again. */
    fun replayed(): Boolean = replayed.getRequired("replayed")

    fun results(): List<CommandResult> = results.getRequired("results")

    fun state(): State = state.getRequired("state")

    fun bodyExpired(): Boolean? = bodyExpired.getNullable("bodyExpired")

    /** A dialog that is open on the tab and blocks it until it closes. */
    fun dialog(): CommandDialog? = dialog.getNullable("dialog")

    /**
     * Set when the execution ran with after. Whether another execution, a quick action, or a
     * browser restart changed the session since then. Input from CDP clients or the live view does
     * not count.
     */
    fun epochChanged(): Boolean? = epochChanged.getNullable("epochChanged")

    /** Tabs with keys or a mouse button still held down. Left out when nothing is held. */
    fun held(): List<CommandHeld>? = held.getNullable("held")

    /** Send it as after in the next request. */
    fun next(): String? = next.getNullable("next")

    fun observe(): Observe? = observe.getNullable("observe")

    /**
     * Why the execution stopped early, such as a cancel, a timeout, or a browser restart. A failed
     * command has its error in results instead.
     */
    fun stopped(): CommandErrorInfo? = stopped.getNullable("stopped")

    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonProperty("effect") @ExcludeMissing fun _effect(): JsonField<Effect> = effect

    @JsonProperty("page") @ExcludeMissing fun _page(): JsonField<PageModel> = page

    @JsonProperty("ran") @ExcludeMissing fun _ran(): JsonField<Long> = ran

    @JsonProperty("replayed") @ExcludeMissing fun _replayed(): JsonField<Boolean> = replayed

    @JsonProperty("results")
    @ExcludeMissing
    fun _results(): JsonField<List<CommandResult>> = results

    @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<State> = state

    @JsonProperty("bodyExpired")
    @ExcludeMissing
    fun _bodyExpired(): JsonField<Boolean> = bodyExpired

    @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

    @JsonProperty("epochChanged")
    @ExcludeMissing
    fun _epochChanged(): JsonField<Boolean> = epochChanged

    @JsonProperty("held") @ExcludeMissing fun _held(): JsonField<List<CommandHeld>> = held

    @JsonProperty("next") @ExcludeMissing fun _next(): JsonField<String> = next

    @JsonProperty("observe") @ExcludeMissing fun _observe(): JsonField<Observe> = observe

    @JsonProperty("stopped") @ExcludeMissing fun _stopped(): JsonField<CommandErrorInfo> = stopped

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
         * Returns a mutable builder for constructing an instance of
         * [CommandExecutionCancelResponse].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandExecutionCancelResponse]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var effect: JsonField<Effect>? = null
        private var page: JsonField<PageModel>? = null
        private var ran: JsonField<Long>? = null
        private var replayed: JsonField<Boolean>? = null
        private var results: JsonField<MutableList<CommandResult>>? = null
        private var state: JsonField<State>? = null
        private var bodyExpired: JsonField<Boolean> = JsonMissing.of()
        private var dialog: JsonField<CommandDialog> = JsonMissing.of()
        private var epochChanged: JsonField<Boolean> = JsonMissing.of()
        private var held: JsonField<MutableList<CommandHeld>>? = null
        private var next: JsonField<String> = JsonMissing.of()
        private var observe: JsonField<Observe> = JsonMissing.of()
        private var stopped: JsonField<CommandErrorInfo> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandExecutionCancelResponse: CommandExecutionCancelResponse) = apply {
            this.id = commandExecutionCancelResponse.id
            this.effect = commandExecutionCancelResponse.effect
            this.page = commandExecutionCancelResponse.page
            this.ran = commandExecutionCancelResponse.ran
            this.replayed = commandExecutionCancelResponse.replayed
            this.results = commandExecutionCancelResponse.results.map { it.toMutableList() }
            this.state = commandExecutionCancelResponse.state
            this.bodyExpired = commandExecutionCancelResponse.bodyExpired
            this.dialog = commandExecutionCancelResponse.dialog
            this.epochChanged = commandExecutionCancelResponse.epochChanged
            this.held = commandExecutionCancelResponse.held.map { it.toMutableList() }
            this.next = commandExecutionCancelResponse.next
            this.observe = commandExecutionCancelResponse.observe
            this.stopped = commandExecutionCancelResponse.stopped
            this.additionalProperties =
                commandExecutionCancelResponse.additionalProperties.toMutableMap()
        }

        /** The id of this execution. Use it to get or cancel the execution. */
        fun id(id: String) = id(JsonField.of(id))

        /** The id of this execution. Use it to get or cancel the execution. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(effect: Effect) = effect(JsonField.of(effect))

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(effect: JsonField<Effect>) = apply { this.effect = effect }

        /** The tab the execution used. */
        fun page(page: PageModel?) = page(JsonField.ofNullable(page))

        /** The tab the execution used. */
        fun page(page: JsonField<PageModel>) = apply { this.page = page }

        /** Commands started. */
        fun ran(ran: Long) = ran(JsonField.of(ran))

        /** Commands started. */
        fun ran(ran: JsonField<Long>) = apply { this.ran = ran }

        /** This is the stored result of an earlier request. Nothing ran again. */
        fun replayed(replayed: Boolean) = replayed(JsonField.of(replayed))

        /** This is the stored result of an earlier request. Nothing ran again. */
        fun replayed(replayed: JsonField<Boolean>) = apply { this.replayed = replayed }

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

        fun state(state: State) = state(JsonField.of(state))

        fun state(state: JsonField<State>) = apply { this.state = state }

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

        /** Tabs with keys or a mouse button still held down. Left out when nothing is held. */
        fun held(held: List<CommandHeld>) = held(JsonField.of(held))

        /** Tabs with keys or a mouse button still held down. Left out when nothing is held. */
        fun held(held: JsonField<List<CommandHeld>>) = apply {
            this.held = held.map { it.toMutableList() }
        }

        /** Tabs with keys or a mouse button still held down. Left out when nothing is held. */
        fun addHeld(held: CommandHeld) = apply {
            this.held =
                (this.held ?: JsonField.of(mutableListOf())).also {
                    checkKnown("held", it).add(held)
                }
        }

        /** Send it as after in the next request. */
        fun next(next: String) = next(JsonField.of(next))

        /** Send it as after in the next request. */
        fun next(next: JsonField<String>) = apply { this.next = next }

        fun observe(observe: Observe) = observe(JsonField.of(observe))

        fun observe(observe: JsonField<Observe>) = apply { this.observe = observe }

        /**
         * Why the execution stopped early, such as a cancel, a timeout, or a browser restart. A
         * failed command has its error in results instead.
         */
        fun stopped(stopped: CommandErrorInfo) = stopped(JsonField.of(stopped))

        /**
         * Why the execution stopped early, such as a cancel, a timeout, or a browser restart. A
         * failed command has its error in results instead.
         */
        fun stopped(stopped: JsonField<CommandErrorInfo>) = apply { this.stopped = stopped }

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

        fun build(): CommandExecutionCancelResponse =
            CommandExecutionCancelResponse(
                checkRequired("id", id),
                checkRequired("effect", effect),
                checkRequired("page", page),
                checkRequired("ran", ran),
                checkRequired("replayed", replayed),
                checkRequired("results", results).map { it.toImmutable() },
                checkRequired("state", state),
                bodyExpired,
                dialog,
                epochChanged,
                (held ?: JsonMissing.of()).map { it.toImmutable() },
                next,
                observe,
                stopped,
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

    fun validate(): CommandExecutionCancelResponse = apply {
        if (validated) {
            return@apply
        }

        id()
        effect().validate()
        page()?.let { it.validate() }
        ran()
        replayed()
        results().forEach { it.validate() }
        state().validate()
        bodyExpired()
        dialog()?.let { it.validate() }
        epochChanged()
        held()?.let { it.forEach { it.validate() } }
        next()
        observe()?.let { it.validate() }
        stopped()?.let { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (page.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (ran.asKnown().isPresent) 1 else 0) +
            (if (replayed.asKnown().isPresent) 1 else 0) +
            (results.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (state.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (bodyExpired.asKnown().isPresent) 1 else 0) +
            (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (if (epochChanged.asKnown().isPresent) 1 else 0) +
            (held.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (if (next.asKnown().isPresent) 1 else 0) +
            (observe.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
            (stopped.asKnown().getOrNull()?.let { it.validity() } ?: 0)

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
            _value().asString().orElseThrow { SteelInvalidDataException("Value is not a String") }

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

    class State @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val RUNNING = of("running")

            @JvmField val COMPLETED = of("completed")

            @JvmField val FAILED = of("failed")

            @JvmField val CANCELLED = of("cancelled")

            @JvmField val TIMEOUT = of("timeout")

            @JvmStatic fun of(value: String) = State(JsonField.of(value))
        }

        enum class Known {
            RUNNING,
            COMPLETED,
            FAILED,
            CANCELLED,
            TIMEOUT,
        }

        enum class Value {
            RUNNING,
            COMPLETED,
            FAILED,
            CANCELLED,
            TIMEOUT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                RUNNING -> Value.RUNNING
                COMPLETED -> Value.COMPLETED
                FAILED -> Value.FAILED
                CANCELLED -> Value.CANCELLED
                TIMEOUT -> Value.TIMEOUT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                RUNNING -> Known.RUNNING
                COMPLETED -> Known.COMPLETED
                FAILED -> Known.FAILED
                CANCELLED -> Known.CANCELLED
                TIMEOUT -> Known.TIMEOUT
                else -> throw SteelInvalidDataException("Unknown State: $value")
            }

        fun asString(): String =
            _value().asString().orElseThrow { SteelInvalidDataException("Value is not a String") }

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

        return other is CommandExecutionCancelResponse &&
            this.id == other.id &&
            this.effect == other.effect &&
            this.page == other.page &&
            this.ran == other.ran &&
            this.replayed == other.replayed &&
            this.results == other.results &&
            this.state == other.state &&
            this.bodyExpired == other.bodyExpired &&
            this.dialog == other.dialog &&
            this.epochChanged == other.epochChanged &&
            this.held == other.held &&
            this.next == other.next &&
            this.observe == other.observe &&
            this.stopped == other.stopped &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            effect,
            page,
            ran,
            replayed,
            results,
            state,
            bodyExpired,
            dialog,
            epochChanged,
            held,
            next,
            observe,
            stopped,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandExecutionCancelResponse{id=$id, effect=$effect, page=$page, ran=$ran, replayed=$replayed, results=$results, state=$state, bodyExpired=$bodyExpired, dialog=$dialog, epochChanged=$epochChanged, held=$held, next=$next, observe=$observe, stopped=$stopped, additionalProperties=$additionalProperties}"
}
