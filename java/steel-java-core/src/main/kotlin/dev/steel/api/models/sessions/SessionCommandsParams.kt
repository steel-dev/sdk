package dev.steel.api.models.sessions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import dev.steel.api.core.BaseDeserializer
import dev.steel.api.core.BaseSerializer
import dev.steel.api.core.Enum
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.Params
import dev.steel.api.core.allMaxBy
import dev.steel.api.core.checkRequired
import dev.steel.api.core.getOrThrow
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/**
 * Drives the session's browser with high-level commands such as snapshot, click, and fill. Commands
 * run in order and stop at the first failure. Send a random Idempotency-Key header to make retries
 * safe.
 */
class SessionCommandsParams
private constructor(
    private val sessionId: String?,
    private val idempotencyKey: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Session ID */
    fun sessionId(): Optional<String> = Optional.ofNullable(sessionId)

    /** A random value. A retry with the same key returns the stored result. */
    fun idempotencyKey(): Optional<String> = Optional.ofNullable(idempotencyKey)

    /**
     * One command, or an array of up to 50. They run in order and stop at the first failure.
     * Commands that already ran are not undone.
     */
    fun commands(): Commands = body.commands()

    /**
     * The next value from your last response. A retry with the same after and commands runs only
     * once.
     */
    fun after(): Optional<String> = body.after()

    /** Returns a snapshot, or a diff against the last one, after the commands. */
    fun observe(): Optional<Observe> = body.observe()

    fun _commands(): JsonField<Commands> = body._commands()

    fun _after(): JsonField<String> = body._after()

    fun _observe(): JsonField<Observe> = body._observe()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [SessionCommandsParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionCommandsParams]. */
    class Builder internal constructor() {

        private var sessionId: String? = null
        private var idempotencyKey: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionCommandsParams: SessionCommandsParams) = apply {
            this.sessionId = sessionCommandsParams.sessionId
            this.idempotencyKey = sessionCommandsParams.idempotencyKey
            this.body = sessionCommandsParams.body.toBuilder()
            this.additionalHeaders = sessionCommandsParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = sessionCommandsParams.additionalQueryParams.toBuilder()
        }

        /** Session ID */
        fun sessionId(sessionId: String?) = apply { this.sessionId = sessionId }

        /** Session ID */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

        /** A random value. A retry with the same key returns the stored result. */
        fun idempotencyKey(idempotencyKey: String?) = apply { this.idempotencyKey = idempotencyKey }

        /** A random value. A retry with the same key returns the stored result. */
        fun idempotencyKey(idempotencyKey: Optional<String>) =
            idempotencyKey(idempotencyKey.getOrNull())

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(commands: Commands) = apply { body.commands(commands) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(commands: JsonField<Commands>) = apply { body.commands(commands) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(navigateCommand: Commands.NavigateCommand) = apply {
            body.commands(navigateCommand)
        }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(backCommand: Commands.BackCommand) = apply { body.commands(backCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(forwardCommand: Commands.ForwardCommand) = apply {
            body.commands(forwardCommand)
        }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(reloadCommand: Commands.ReloadCommand) = apply { body.commands(reloadCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(snapshotCommand: Commands.SnapshotCommand) = apply {
            body.commands(snapshotCommand)
        }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(clickCommand: Commands.ClickCommand) = apply { body.commands(clickCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(dblclickCommand: Commands.DblclickCommand) = apply {
            body.commands(dblclickCommand)
        }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(hoverCommand: Commands.HoverCommand) = apply { body.commands(hoverCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(focusCommand: Commands.FocusCommand) = apply { body.commands(focusCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(typeCommand: Commands.TypeCommand) = apply { body.commands(typeCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(fillCommand: Commands.FillCommand) = apply { body.commands(fillCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(pressCommand: Commands.PressCommand) = apply { body.commands(pressCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(keydownCommand: Commands.KeydownCommand) = apply {
            body.commands(keydownCommand)
        }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(keyupCommand: Commands.KeyupCommand) = apply { body.commands(keyupCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(mouseCommand: Commands.MouseCommand) = apply { body.commands(mouseCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(selectCommand: Commands.SelectCommand) = apply { body.commands(selectCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(checkCommand: Commands.CheckCommand) = apply { body.commands(checkCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(uploadCommand: Commands.UploadCommand) = apply { body.commands(uploadCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(dragCommand: Commands.DragCommand) = apply { body.commands(dragCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(uncheckCommand: Commands.UncheckCommand) = apply {
            body.commands(uncheckCommand)
        }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(getCommand: Commands.GetCommand) = apply { body.commands(getCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(isCommand: Commands.IsCommand) = apply { body.commands(isCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(waitCommand: Commands.WaitCommand) = apply { body.commands(waitCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(screenshotCommand: Commands.ScreenshotCommand) = apply {
            body.commands(screenshotCommand)
        }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(evalCommand: Commands.EvalCommand) = apply { body.commands(evalCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(pdfCommand: Commands.PdfCommand) = apply { body.commands(pdfCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(scrollCommand: Commands.ScrollCommand) = apply { body.commands(scrollCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(findCommand: Commands.FindCommand) = apply { body.commands(findCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(tabCommand: Commands.TabCommand) = apply { body.commands(tabCommand) }

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(unionMember29: List<Command>) = apply { body.commands(unionMember29) }

        /**
         * The next value from your last response. A retry with the same after and commands runs
         * only once.
         */
        fun after(after: String) = apply { body.after(after) }

        /**
         * The next value from your last response. A retry with the same after and commands runs
         * only once.
         */
        fun after(after: JsonField<String>) = apply { body.after(after) }

        /** Returns a snapshot, or a diff against the last one, after the commands. */
        fun observe(observe: Observe) = apply { body.observe(observe) }

        /** Returns a snapshot, or a diff against the last one, after the commands. */
        fun observe(observe: JsonField<Observe>) = apply { body.observe(observe) }

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

        fun build(): SessionCommandsParams =
            SessionCommandsParams(
                sessionId,
                idempotencyKey,
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

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                idempotencyKey?.let { put("idempotency-key", it) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val commands: JsonField<Commands>,
        private val after: JsonField<String>,
        private val observe: JsonField<Observe>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("commands")
            @ExcludeMissing
            commands: JsonField<Commands> = JsonMissing.of(),
            @JsonProperty("after") @ExcludeMissing after: JsonField<String> = JsonMissing.of(),
            @JsonProperty("observe") @ExcludeMissing observe: JsonField<Observe> = JsonMissing.of(),
        ) : this(commands, after, observe, mutableMapOf())

        /**
         * One command, or an array of up to 50. They run in order and stop at the first failure.
         * Commands that already ran are not undone.
         */
        fun commands(): Commands = commands.getRequired("commands")

        /**
         * The next value from your last response. A retry with the same after and commands runs
         * only once.
         */
        fun after(): Optional<String> = after.getOptional("after")

        /** Returns a snapshot, or a diff against the last one, after the commands. */
        fun observe(): Optional<Observe> = observe.getOptional("observe")

        @JsonProperty("commands") @ExcludeMissing fun _commands(): JsonField<Commands> = commands

        @JsonProperty("after") @ExcludeMissing fun _after(): JsonField<String> = after

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var commands: JsonField<Commands>? = null
            private var after: JsonField<String> = JsonMissing.of()
            private var observe: JsonField<Observe> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.commands = body_.commands
                this.after = body_.after
                this.observe = body_.observe
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(commands: Commands) = commands(JsonField.of(commands))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(commands: JsonField<Commands>) = apply { this.commands = commands }

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(navigateCommand: Commands.NavigateCommand) =
                commands(Commands.ofNavigateCommand(navigateCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(backCommand: Commands.BackCommand) =
                commands(Commands.ofBackCommand(backCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(forwardCommand: Commands.ForwardCommand) =
                commands(Commands.ofForwardCommand(forwardCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(reloadCommand: Commands.ReloadCommand) =
                commands(Commands.ofReloadCommand(reloadCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(snapshotCommand: Commands.SnapshotCommand) =
                commands(Commands.ofSnapshotCommand(snapshotCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(clickCommand: Commands.ClickCommand) =
                commands(Commands.ofClickCommand(clickCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(dblclickCommand: Commands.DblclickCommand) =
                commands(Commands.ofDblclickCommand(dblclickCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(hoverCommand: Commands.HoverCommand) =
                commands(Commands.ofHoverCommand(hoverCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(focusCommand: Commands.FocusCommand) =
                commands(Commands.ofFocusCommand(focusCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(typeCommand: Commands.TypeCommand) =
                commands(Commands.ofTypeCommand(typeCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(fillCommand: Commands.FillCommand) =
                commands(Commands.ofFillCommand(fillCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(pressCommand: Commands.PressCommand) =
                commands(Commands.ofPressCommand(pressCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(keydownCommand: Commands.KeydownCommand) =
                commands(Commands.ofKeydownCommand(keydownCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(keyupCommand: Commands.KeyupCommand) =
                commands(Commands.ofKeyupCommand(keyupCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(mouseCommand: Commands.MouseCommand) =
                commands(Commands.ofMouseCommand(mouseCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(selectCommand: Commands.SelectCommand) =
                commands(Commands.ofSelectCommand(selectCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(checkCommand: Commands.CheckCommand) =
                commands(Commands.ofCheckCommand(checkCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(uploadCommand: Commands.UploadCommand) =
                commands(Commands.ofUploadCommand(uploadCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(dragCommand: Commands.DragCommand) =
                commands(Commands.ofDragCommand(dragCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(uncheckCommand: Commands.UncheckCommand) =
                commands(Commands.ofUncheckCommand(uncheckCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(getCommand: Commands.GetCommand) =
                commands(Commands.ofGetCommand(getCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(isCommand: Commands.IsCommand) = commands(Commands.ofIsCommand(isCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(waitCommand: Commands.WaitCommand) =
                commands(Commands.ofWaitCommand(waitCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(screenshotCommand: Commands.ScreenshotCommand) =
                commands(Commands.ofScreenshotCommand(screenshotCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(evalCommand: Commands.EvalCommand) =
                commands(Commands.ofEvalCommand(evalCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(pdfCommand: Commands.PdfCommand) =
                commands(Commands.ofPdfCommand(pdfCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(scrollCommand: Commands.ScrollCommand) =
                commands(Commands.ofScrollCommand(scrollCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(findCommand: Commands.FindCommand) =
                commands(Commands.ofFindCommand(findCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(tabCommand: Commands.TabCommand) =
                commands(Commands.ofTabCommand(tabCommand))

            /**
             * One command, or an array of up to 50. They run in order and stop at the first
             * failure. Commands that already ran are not undone.
             */
            fun commands(unionMember29: List<Command>) =
                commands(Commands.ofUnionMember29(unionMember29))

            /**
             * The next value from your last response. A retry with the same after and commands runs
             * only once.
             */
            fun after(after: String) = after(JsonField.of(after))

            /**
             * The next value from your last response. A retry with the same after and commands runs
             * only once.
             */
            fun after(after: JsonField<String>) = apply { this.after = after }

            /** Returns a snapshot, or a diff against the last one, after the commands. */
            fun observe(observe: Observe) = observe(JsonField.of(observe))

            /** Returns a snapshot, or a diff against the last one, after the commands. */
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

            fun build(): Body =
                Body(
                    checkRequired("commands", commands),
                    after,
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

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            commands().validate()
            after()
            observe().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (commands.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (after.asKnown().isPresent) 1 else 0) +
                (observe.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.commands == other.commands &&
                this.after == other.after &&
                this.observe == other.observe &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(commands, after, observe, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{commands=$commands, after=$after, observe=$observe, additionalProperties=$additionalProperties}"
    }

    @JsonDeserialize(using = Commands.Deserializer::class)
    @JsonSerialize(using = Commands.Serializer::class)
    class Commands
    private constructor(
        private val navigateCommand: NavigateCommand? = null,
        private val backCommand: BackCommand? = null,
        private val forwardCommand: ForwardCommand? = null,
        private val reloadCommand: ReloadCommand? = null,
        private val snapshotCommand: SnapshotCommand? = null,
        private val clickCommand: ClickCommand? = null,
        private val dblclickCommand: DblclickCommand? = null,
        private val hoverCommand: HoverCommand? = null,
        private val focusCommand: FocusCommand? = null,
        private val typeCommand: TypeCommand? = null,
        private val fillCommand: FillCommand? = null,
        private val pressCommand: PressCommand? = null,
        private val keydownCommand: KeydownCommand? = null,
        private val keyupCommand: KeyupCommand? = null,
        private val mouseCommand: MouseCommand? = null,
        private val selectCommand: SelectCommand? = null,
        private val checkCommand: CheckCommand? = null,
        private val uploadCommand: UploadCommand? = null,
        private val dragCommand: DragCommand? = null,
        private val uncheckCommand: UncheckCommand? = null,
        private val getCommand: GetCommand? = null,
        private val isCommand: IsCommand? = null,
        private val waitCommand: WaitCommand? = null,
        private val screenshotCommand: ScreenshotCommand? = null,
        private val evalCommand: EvalCommand? = null,
        private val pdfCommand: PdfCommand? = null,
        private val scrollCommand: ScrollCommand? = null,
        private val findCommand: FindCommand? = null,
        private val tabCommand: TabCommand? = null,
        private val unionMember29: List<Command>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun navigateCommand(): Optional<NavigateCommand> = Optional.ofNullable(navigateCommand)

        fun backCommand(): Optional<BackCommand> = Optional.ofNullable(backCommand)

        fun forwardCommand(): Optional<ForwardCommand> = Optional.ofNullable(forwardCommand)

        fun reloadCommand(): Optional<ReloadCommand> = Optional.ofNullable(reloadCommand)

        fun snapshotCommand(): Optional<SnapshotCommand> = Optional.ofNullable(snapshotCommand)

        fun clickCommand(): Optional<ClickCommand> = Optional.ofNullable(clickCommand)

        fun dblclickCommand(): Optional<DblclickCommand> = Optional.ofNullable(dblclickCommand)

        fun hoverCommand(): Optional<HoverCommand> = Optional.ofNullable(hoverCommand)

        fun focusCommand(): Optional<FocusCommand> = Optional.ofNullable(focusCommand)

        fun typeCommand(): Optional<TypeCommand> = Optional.ofNullable(typeCommand)

        fun fillCommand(): Optional<FillCommand> = Optional.ofNullable(fillCommand)

        fun pressCommand(): Optional<PressCommand> = Optional.ofNullable(pressCommand)

        fun keydownCommand(): Optional<KeydownCommand> = Optional.ofNullable(keydownCommand)

        fun keyupCommand(): Optional<KeyupCommand> = Optional.ofNullable(keyupCommand)

        fun mouseCommand(): Optional<MouseCommand> = Optional.ofNullable(mouseCommand)

        fun selectCommand(): Optional<SelectCommand> = Optional.ofNullable(selectCommand)

        fun checkCommand(): Optional<CheckCommand> = Optional.ofNullable(checkCommand)

        fun uploadCommand(): Optional<UploadCommand> = Optional.ofNullable(uploadCommand)

        fun dragCommand(): Optional<DragCommand> = Optional.ofNullable(dragCommand)

        fun uncheckCommand(): Optional<UncheckCommand> = Optional.ofNullable(uncheckCommand)

        fun getCommand(): Optional<GetCommand> = Optional.ofNullable(getCommand)

        fun isCommand(): Optional<IsCommand> = Optional.ofNullable(isCommand)

        fun waitCommand(): Optional<WaitCommand> = Optional.ofNullable(waitCommand)

        fun screenshotCommand(): Optional<ScreenshotCommand> =
            Optional.ofNullable(screenshotCommand)

        fun evalCommand(): Optional<EvalCommand> = Optional.ofNullable(evalCommand)

        fun pdfCommand(): Optional<PdfCommand> = Optional.ofNullable(pdfCommand)

        fun scrollCommand(): Optional<ScrollCommand> = Optional.ofNullable(scrollCommand)

        fun findCommand(): Optional<FindCommand> = Optional.ofNullable(findCommand)

        fun tabCommand(): Optional<TabCommand> = Optional.ofNullable(tabCommand)

        fun unionMember29(): Optional<List<Command>> = Optional.ofNullable(unionMember29)

        fun isNavigateCommand(): Boolean = navigateCommand != null

        fun isBackCommand(): Boolean = backCommand != null

        fun isForwardCommand(): Boolean = forwardCommand != null

        fun isReloadCommand(): Boolean = reloadCommand != null

        fun isSnapshotCommand(): Boolean = snapshotCommand != null

        fun isClickCommand(): Boolean = clickCommand != null

        fun isDblclickCommand(): Boolean = dblclickCommand != null

        fun isHoverCommand(): Boolean = hoverCommand != null

        fun isFocusCommand(): Boolean = focusCommand != null

        fun isTypeCommand(): Boolean = typeCommand != null

        fun isFillCommand(): Boolean = fillCommand != null

        fun isPressCommand(): Boolean = pressCommand != null

        fun isKeydownCommand(): Boolean = keydownCommand != null

        fun isKeyupCommand(): Boolean = keyupCommand != null

        fun isMouseCommand(): Boolean = mouseCommand != null

        fun isSelectCommand(): Boolean = selectCommand != null

        fun isCheckCommand(): Boolean = checkCommand != null

        fun isUploadCommand(): Boolean = uploadCommand != null

        fun isDragCommand(): Boolean = dragCommand != null

        fun isUncheckCommand(): Boolean = uncheckCommand != null

        fun isGetCommand(): Boolean = getCommand != null

        fun isIsCommand(): Boolean = isCommand != null

        fun isWaitCommand(): Boolean = waitCommand != null

        fun isScreenshotCommand(): Boolean = screenshotCommand != null

        fun isEvalCommand(): Boolean = evalCommand != null

        fun isPdfCommand(): Boolean = pdfCommand != null

        fun isScrollCommand(): Boolean = scrollCommand != null

        fun isFindCommand(): Boolean = findCommand != null

        fun isTabCommand(): Boolean = tabCommand != null

        fun isUnionMember29(): Boolean = unionMember29 != null

        fun asNavigateCommand(): NavigateCommand = navigateCommand.getOrThrow("navigateCommand")

        fun asBackCommand(): BackCommand = backCommand.getOrThrow("backCommand")

        fun asForwardCommand(): ForwardCommand = forwardCommand.getOrThrow("forwardCommand")

        fun asReloadCommand(): ReloadCommand = reloadCommand.getOrThrow("reloadCommand")

        fun asSnapshotCommand(): SnapshotCommand = snapshotCommand.getOrThrow("snapshotCommand")

        fun asClickCommand(): ClickCommand = clickCommand.getOrThrow("clickCommand")

        fun asDblclickCommand(): DblclickCommand = dblclickCommand.getOrThrow("dblclickCommand")

        fun asHoverCommand(): HoverCommand = hoverCommand.getOrThrow("hoverCommand")

        fun asFocusCommand(): FocusCommand = focusCommand.getOrThrow("focusCommand")

        fun asTypeCommand(): TypeCommand = typeCommand.getOrThrow("typeCommand")

        fun asFillCommand(): FillCommand = fillCommand.getOrThrow("fillCommand")

        fun asPressCommand(): PressCommand = pressCommand.getOrThrow("pressCommand")

        fun asKeydownCommand(): KeydownCommand = keydownCommand.getOrThrow("keydownCommand")

        fun asKeyupCommand(): KeyupCommand = keyupCommand.getOrThrow("keyupCommand")

        fun asMouseCommand(): MouseCommand = mouseCommand.getOrThrow("mouseCommand")

        fun asSelectCommand(): SelectCommand = selectCommand.getOrThrow("selectCommand")

        fun asCheckCommand(): CheckCommand = checkCommand.getOrThrow("checkCommand")

        fun asUploadCommand(): UploadCommand = uploadCommand.getOrThrow("uploadCommand")

        fun asDragCommand(): DragCommand = dragCommand.getOrThrow("dragCommand")

        fun asUncheckCommand(): UncheckCommand = uncheckCommand.getOrThrow("uncheckCommand")

        fun asGetCommand(): GetCommand = getCommand.getOrThrow("getCommand")

        fun asIsCommand(): IsCommand = isCommand.getOrThrow("isCommand")

        fun asWaitCommand(): WaitCommand = waitCommand.getOrThrow("waitCommand")

        fun asScreenshotCommand(): ScreenshotCommand =
            screenshotCommand.getOrThrow("screenshotCommand")

        fun asEvalCommand(): EvalCommand = evalCommand.getOrThrow("evalCommand")

        fun asPdfCommand(): PdfCommand = pdfCommand.getOrThrow("pdfCommand")

        fun asScrollCommand(): ScrollCommand = scrollCommand.getOrThrow("scrollCommand")

        fun asFindCommand(): FindCommand = findCommand.getOrThrow("findCommand")

        fun asTabCommand(): TabCommand = tabCommand.getOrThrow("tabCommand")

        fun asUnionMember29(): List<Command> = unionMember29.getOrThrow("unionMember29")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                navigateCommand != null -> visitor.visitNavigateCommand(navigateCommand)
                backCommand != null -> visitor.visitBackCommand(backCommand)
                forwardCommand != null -> visitor.visitForwardCommand(forwardCommand)
                reloadCommand != null -> visitor.visitReloadCommand(reloadCommand)
                snapshotCommand != null -> visitor.visitSnapshotCommand(snapshotCommand)
                clickCommand != null -> visitor.visitClickCommand(clickCommand)
                dblclickCommand != null -> visitor.visitDblclickCommand(dblclickCommand)
                hoverCommand != null -> visitor.visitHoverCommand(hoverCommand)
                focusCommand != null -> visitor.visitFocusCommand(focusCommand)
                typeCommand != null -> visitor.visitTypeCommand(typeCommand)
                fillCommand != null -> visitor.visitFillCommand(fillCommand)
                pressCommand != null -> visitor.visitPressCommand(pressCommand)
                keydownCommand != null -> visitor.visitKeydownCommand(keydownCommand)
                keyupCommand != null -> visitor.visitKeyupCommand(keyupCommand)
                mouseCommand != null -> visitor.visitMouseCommand(mouseCommand)
                selectCommand != null -> visitor.visitSelectCommand(selectCommand)
                checkCommand != null -> visitor.visitCheckCommand(checkCommand)
                uploadCommand != null -> visitor.visitUploadCommand(uploadCommand)
                dragCommand != null -> visitor.visitDragCommand(dragCommand)
                uncheckCommand != null -> visitor.visitUncheckCommand(uncheckCommand)
                getCommand != null -> visitor.visitGetCommand(getCommand)
                isCommand != null -> visitor.visitIsCommand(isCommand)
                waitCommand != null -> visitor.visitWaitCommand(waitCommand)
                screenshotCommand != null -> visitor.visitScreenshotCommand(screenshotCommand)
                evalCommand != null -> visitor.visitEvalCommand(evalCommand)
                pdfCommand != null -> visitor.visitPdfCommand(pdfCommand)
                scrollCommand != null -> visitor.visitScrollCommand(scrollCommand)
                findCommand != null -> visitor.visitFindCommand(findCommand)
                tabCommand != null -> visitor.visitTabCommand(tabCommand)
                unionMember29 != null -> visitor.visitUnionMember29(unionMember29)
                else -> visitor.unknown(_json)
            }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Commands = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitNavigateCommand(navigateCommand: NavigateCommand) {
                        navigateCommand.validate()
                    }

                    override fun visitBackCommand(backCommand: BackCommand) {
                        backCommand.validate()
                    }

                    override fun visitForwardCommand(forwardCommand: ForwardCommand) {
                        forwardCommand.validate()
                    }

                    override fun visitReloadCommand(reloadCommand: ReloadCommand) {
                        reloadCommand.validate()
                    }

                    override fun visitSnapshotCommand(snapshotCommand: SnapshotCommand) {
                        snapshotCommand.validate()
                    }

                    override fun visitClickCommand(clickCommand: ClickCommand) {
                        clickCommand.validate()
                    }

                    override fun visitDblclickCommand(dblclickCommand: DblclickCommand) {
                        dblclickCommand.validate()
                    }

                    override fun visitHoverCommand(hoverCommand: HoverCommand) {
                        hoverCommand.validate()
                    }

                    override fun visitFocusCommand(focusCommand: FocusCommand) {
                        focusCommand.validate()
                    }

                    override fun visitTypeCommand(typeCommand: TypeCommand) {
                        typeCommand.validate()
                    }

                    override fun visitFillCommand(fillCommand: FillCommand) {
                        fillCommand.validate()
                    }

                    override fun visitPressCommand(pressCommand: PressCommand) {
                        pressCommand.validate()
                    }

                    override fun visitKeydownCommand(keydownCommand: KeydownCommand) {
                        keydownCommand.validate()
                    }

                    override fun visitKeyupCommand(keyupCommand: KeyupCommand) {
                        keyupCommand.validate()
                    }

                    override fun visitMouseCommand(mouseCommand: MouseCommand) {
                        mouseCommand.validate()
                    }

                    override fun visitSelectCommand(selectCommand: SelectCommand) {
                        selectCommand.validate()
                    }

                    override fun visitCheckCommand(checkCommand: CheckCommand) {
                        checkCommand.validate()
                    }

                    override fun visitUploadCommand(uploadCommand: UploadCommand) {
                        uploadCommand.validate()
                    }

                    override fun visitDragCommand(dragCommand: DragCommand) {
                        dragCommand.validate()
                    }

                    override fun visitUncheckCommand(uncheckCommand: UncheckCommand) {
                        uncheckCommand.validate()
                    }

                    override fun visitGetCommand(getCommand: GetCommand) {
                        getCommand.validate()
                    }

                    override fun visitIsCommand(isCommand: IsCommand) {
                        isCommand.validate()
                    }

                    override fun visitWaitCommand(waitCommand: WaitCommand) {
                        waitCommand.validate()
                    }

                    override fun visitScreenshotCommand(screenshotCommand: ScreenshotCommand) {
                        screenshotCommand.validate()
                    }

                    override fun visitEvalCommand(evalCommand: EvalCommand) {
                        evalCommand.validate()
                    }

                    override fun visitPdfCommand(pdfCommand: PdfCommand) {
                        pdfCommand.validate()
                    }

                    override fun visitScrollCommand(scrollCommand: ScrollCommand) {
                        scrollCommand.validate()
                    }

                    override fun visitFindCommand(findCommand: FindCommand) {
                        findCommand.validate()
                    }

                    override fun visitTabCommand(tabCommand: TabCommand) {
                        tabCommand.validate()
                    }

                    override fun visitUnionMember29(unionMember29: List<Command>) {
                        unionMember29.forEach { it.validate() }
                    }
                }
            )
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitNavigateCommand(navigateCommand: NavigateCommand) =
                        navigateCommand.validity()

                    override fun visitBackCommand(backCommand: BackCommand) = backCommand.validity()

                    override fun visitForwardCommand(forwardCommand: ForwardCommand) =
                        forwardCommand.validity()

                    override fun visitReloadCommand(reloadCommand: ReloadCommand) =
                        reloadCommand.validity()

                    override fun visitSnapshotCommand(snapshotCommand: SnapshotCommand) =
                        snapshotCommand.validity()

                    override fun visitClickCommand(clickCommand: ClickCommand) =
                        clickCommand.validity()

                    override fun visitDblclickCommand(dblclickCommand: DblclickCommand) =
                        dblclickCommand.validity()

                    override fun visitHoverCommand(hoverCommand: HoverCommand) =
                        hoverCommand.validity()

                    override fun visitFocusCommand(focusCommand: FocusCommand) =
                        focusCommand.validity()

                    override fun visitTypeCommand(typeCommand: TypeCommand) = typeCommand.validity()

                    override fun visitFillCommand(fillCommand: FillCommand) = fillCommand.validity()

                    override fun visitPressCommand(pressCommand: PressCommand) =
                        pressCommand.validity()

                    override fun visitKeydownCommand(keydownCommand: KeydownCommand) =
                        keydownCommand.validity()

                    override fun visitKeyupCommand(keyupCommand: KeyupCommand) =
                        keyupCommand.validity()

                    override fun visitMouseCommand(mouseCommand: MouseCommand) =
                        mouseCommand.validity()

                    override fun visitSelectCommand(selectCommand: SelectCommand) =
                        selectCommand.validity()

                    override fun visitCheckCommand(checkCommand: CheckCommand) =
                        checkCommand.validity()

                    override fun visitUploadCommand(uploadCommand: UploadCommand) =
                        uploadCommand.validity()

                    override fun visitDragCommand(dragCommand: DragCommand) = dragCommand.validity()

                    override fun visitUncheckCommand(uncheckCommand: UncheckCommand) =
                        uncheckCommand.validity()

                    override fun visitGetCommand(getCommand: GetCommand) = getCommand.validity()

                    override fun visitIsCommand(isCommand: IsCommand) = isCommand.validity()

                    override fun visitWaitCommand(waitCommand: WaitCommand) = waitCommand.validity()

                    override fun visitScreenshotCommand(screenshotCommand: ScreenshotCommand) =
                        screenshotCommand.validity()

                    override fun visitEvalCommand(evalCommand: EvalCommand) = evalCommand.validity()

                    override fun visitPdfCommand(pdfCommand: PdfCommand) = pdfCommand.validity()

                    override fun visitScrollCommand(scrollCommand: ScrollCommand) =
                        scrollCommand.validity()

                    override fun visitFindCommand(findCommand: FindCommand) = findCommand.validity()

                    override fun visitTabCommand(tabCommand: TabCommand) = tabCommand.validity()

                    override fun visitUnionMember29(unionMember29: List<Command>) =
                        unionMember29.sumOf { (it.validity()).toInt() }

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Commands &&
                this.navigateCommand == other.navigateCommand &&
                this.backCommand == other.backCommand &&
                this.forwardCommand == other.forwardCommand &&
                this.reloadCommand == other.reloadCommand &&
                this.snapshotCommand == other.snapshotCommand &&
                this.clickCommand == other.clickCommand &&
                this.dblclickCommand == other.dblclickCommand &&
                this.hoverCommand == other.hoverCommand &&
                this.focusCommand == other.focusCommand &&
                this.typeCommand == other.typeCommand &&
                this.fillCommand == other.fillCommand &&
                this.pressCommand == other.pressCommand &&
                this.keydownCommand == other.keydownCommand &&
                this.keyupCommand == other.keyupCommand &&
                this.mouseCommand == other.mouseCommand &&
                this.selectCommand == other.selectCommand &&
                this.checkCommand == other.checkCommand &&
                this.uploadCommand == other.uploadCommand &&
                this.dragCommand == other.dragCommand &&
                this.uncheckCommand == other.uncheckCommand &&
                this.getCommand == other.getCommand &&
                this.isCommand == other.isCommand &&
                this.waitCommand == other.waitCommand &&
                this.screenshotCommand == other.screenshotCommand &&
                this.evalCommand == other.evalCommand &&
                this.pdfCommand == other.pdfCommand &&
                this.scrollCommand == other.scrollCommand &&
                this.findCommand == other.findCommand &&
                this.tabCommand == other.tabCommand &&
                this.unionMember29 == other.unionMember29
        }

        override fun hashCode(): Int =
            Objects.hash(
                navigateCommand,
                backCommand,
                forwardCommand,
                reloadCommand,
                snapshotCommand,
                clickCommand,
                dblclickCommand,
                hoverCommand,
                focusCommand,
                typeCommand,
                fillCommand,
                pressCommand,
                keydownCommand,
                keyupCommand,
                mouseCommand,
                selectCommand,
                checkCommand,
                uploadCommand,
                dragCommand,
                uncheckCommand,
                getCommand,
                isCommand,
                waitCommand,
                screenshotCommand,
                evalCommand,
                pdfCommand,
                scrollCommand,
                findCommand,
                tabCommand,
                unionMember29,
            )

        override fun toString(): String =
            when {
                navigateCommand != null -> "Commands{navigateCommand=$navigateCommand}"
                backCommand != null -> "Commands{backCommand=$backCommand}"
                forwardCommand != null -> "Commands{forwardCommand=$forwardCommand}"
                reloadCommand != null -> "Commands{reloadCommand=$reloadCommand}"
                snapshotCommand != null -> "Commands{snapshotCommand=$snapshotCommand}"
                clickCommand != null -> "Commands{clickCommand=$clickCommand}"
                dblclickCommand != null -> "Commands{dblclickCommand=$dblclickCommand}"
                hoverCommand != null -> "Commands{hoverCommand=$hoverCommand}"
                focusCommand != null -> "Commands{focusCommand=$focusCommand}"
                typeCommand != null -> "Commands{typeCommand=$typeCommand}"
                fillCommand != null -> "Commands{fillCommand=$fillCommand}"
                pressCommand != null -> "Commands{pressCommand=$pressCommand}"
                keydownCommand != null -> "Commands{keydownCommand=$keydownCommand}"
                keyupCommand != null -> "Commands{keyupCommand=$keyupCommand}"
                mouseCommand != null -> "Commands{mouseCommand=$mouseCommand}"
                selectCommand != null -> "Commands{selectCommand=$selectCommand}"
                checkCommand != null -> "Commands{checkCommand=$checkCommand}"
                uploadCommand != null -> "Commands{uploadCommand=$uploadCommand}"
                dragCommand != null -> "Commands{dragCommand=$dragCommand}"
                uncheckCommand != null -> "Commands{uncheckCommand=$uncheckCommand}"
                getCommand != null -> "Commands{getCommand=$getCommand}"
                isCommand != null -> "Commands{isCommand=$isCommand}"
                waitCommand != null -> "Commands{waitCommand=$waitCommand}"
                screenshotCommand != null -> "Commands{screenshotCommand=$screenshotCommand}"
                evalCommand != null -> "Commands{evalCommand=$evalCommand}"
                pdfCommand != null -> "Commands{pdfCommand=$pdfCommand}"
                scrollCommand != null -> "Commands{scrollCommand=$scrollCommand}"
                findCommand != null -> "Commands{findCommand=$findCommand}"
                tabCommand != null -> "Commands{tabCommand=$tabCommand}"
                unionMember29 != null -> "Commands{unionMember29=$unionMember29}"
                _json != null -> "Commands{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Commands")
            }

        companion object {

            @JvmStatic
            fun ofNavigateCommand(navigateCommand: NavigateCommand) =
                Commands(navigateCommand = navigateCommand)

            @JvmStatic
            fun ofBackCommand(backCommand: BackCommand) = Commands(backCommand = backCommand)

            @JvmStatic
            fun ofForwardCommand(forwardCommand: ForwardCommand) =
                Commands(forwardCommand = forwardCommand)

            @JvmStatic
            fun ofReloadCommand(reloadCommand: ReloadCommand) =
                Commands(reloadCommand = reloadCommand)

            @JvmStatic
            fun ofSnapshotCommand(snapshotCommand: SnapshotCommand) =
                Commands(snapshotCommand = snapshotCommand)

            @JvmStatic
            fun ofClickCommand(clickCommand: ClickCommand) = Commands(clickCommand = clickCommand)

            @JvmStatic
            fun ofDblclickCommand(dblclickCommand: DblclickCommand) =
                Commands(dblclickCommand = dblclickCommand)

            @JvmStatic
            fun ofHoverCommand(hoverCommand: HoverCommand) = Commands(hoverCommand = hoverCommand)

            @JvmStatic
            fun ofFocusCommand(focusCommand: FocusCommand) = Commands(focusCommand = focusCommand)

            @JvmStatic
            fun ofTypeCommand(typeCommand: TypeCommand) = Commands(typeCommand = typeCommand)

            @JvmStatic
            fun ofFillCommand(fillCommand: FillCommand) = Commands(fillCommand = fillCommand)

            @JvmStatic
            fun ofPressCommand(pressCommand: PressCommand) = Commands(pressCommand = pressCommand)

            @JvmStatic
            fun ofKeydownCommand(keydownCommand: KeydownCommand) =
                Commands(keydownCommand = keydownCommand)

            @JvmStatic
            fun ofKeyupCommand(keyupCommand: KeyupCommand) = Commands(keyupCommand = keyupCommand)

            @JvmStatic
            fun ofMouseCommand(mouseCommand: MouseCommand) = Commands(mouseCommand = mouseCommand)

            @JvmStatic
            fun ofSelectCommand(selectCommand: SelectCommand) =
                Commands(selectCommand = selectCommand)

            @JvmStatic
            fun ofCheckCommand(checkCommand: CheckCommand) = Commands(checkCommand = checkCommand)

            @JvmStatic
            fun ofUploadCommand(uploadCommand: UploadCommand) =
                Commands(uploadCommand = uploadCommand)

            @JvmStatic
            fun ofDragCommand(dragCommand: DragCommand) = Commands(dragCommand = dragCommand)

            @JvmStatic
            fun ofUncheckCommand(uncheckCommand: UncheckCommand) =
                Commands(uncheckCommand = uncheckCommand)

            @JvmStatic fun ofGetCommand(getCommand: GetCommand) = Commands(getCommand = getCommand)

            @JvmStatic fun ofIsCommand(isCommand: IsCommand) = Commands(isCommand = isCommand)

            @JvmStatic
            fun ofWaitCommand(waitCommand: WaitCommand) = Commands(waitCommand = waitCommand)

            @JvmStatic
            fun ofScreenshotCommand(screenshotCommand: ScreenshotCommand) =
                Commands(screenshotCommand = screenshotCommand)

            @JvmStatic
            fun ofEvalCommand(evalCommand: EvalCommand) = Commands(evalCommand = evalCommand)

            @JvmStatic fun ofPdfCommand(pdfCommand: PdfCommand) = Commands(pdfCommand = pdfCommand)

            @JvmStatic
            fun ofScrollCommand(scrollCommand: ScrollCommand) =
                Commands(scrollCommand = scrollCommand)

            @JvmStatic
            fun ofFindCommand(findCommand: FindCommand) = Commands(findCommand = findCommand)

            @JvmStatic fun ofTabCommand(tabCommand: TabCommand) = Commands(tabCommand = tabCommand)

            @JvmStatic
            fun ofUnionMember29(unionMember29: List<Command>) =
                Commands(unionMember29 = unionMember29.toImmutable())
        }

        interface Visitor<out T> {

            fun visitNavigateCommand(navigateCommand: NavigateCommand): T

            fun visitBackCommand(backCommand: BackCommand): T

            fun visitForwardCommand(forwardCommand: ForwardCommand): T

            fun visitReloadCommand(reloadCommand: ReloadCommand): T

            fun visitSnapshotCommand(snapshotCommand: SnapshotCommand): T

            fun visitClickCommand(clickCommand: ClickCommand): T

            fun visitDblclickCommand(dblclickCommand: DblclickCommand): T

            fun visitHoverCommand(hoverCommand: HoverCommand): T

            fun visitFocusCommand(focusCommand: FocusCommand): T

            fun visitTypeCommand(typeCommand: TypeCommand): T

            fun visitFillCommand(fillCommand: FillCommand): T

            fun visitPressCommand(pressCommand: PressCommand): T

            fun visitKeydownCommand(keydownCommand: KeydownCommand): T

            fun visitKeyupCommand(keyupCommand: KeyupCommand): T

            fun visitMouseCommand(mouseCommand: MouseCommand): T

            fun visitSelectCommand(selectCommand: SelectCommand): T

            fun visitCheckCommand(checkCommand: CheckCommand): T

            fun visitUploadCommand(uploadCommand: UploadCommand): T

            fun visitDragCommand(dragCommand: DragCommand): T

            fun visitUncheckCommand(uncheckCommand: UncheckCommand): T

            fun visitGetCommand(getCommand: GetCommand): T

            fun visitIsCommand(isCommand: IsCommand): T

            fun visitWaitCommand(waitCommand: WaitCommand): T

            fun visitScreenshotCommand(screenshotCommand: ScreenshotCommand): T

            fun visitEvalCommand(evalCommand: EvalCommand): T

            fun visitPdfCommand(pdfCommand: PdfCommand): T

            fun visitScrollCommand(scrollCommand: ScrollCommand): T

            fun visitFindCommand(findCommand: FindCommand): T

            fun visitTabCommand(tabCommand: TabCommand): T

            fun visitUnionMember29(unionMember29: List<Command>): T

            fun unknown(json: JsonValue?): T {
                throw SteelInvalidDataException("Unknown Commands: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Commands>(Commands::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Commands {
                val json = JsonValue.fromJsonNode(node)
                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<NavigateCommand>())?.let {
                                Commands(navigateCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BackCommand>())?.let {
                                Commands(backCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ForwardCommand>())?.let {
                                Commands(forwardCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ReloadCommand>())?.let {
                                Commands(reloadCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<SnapshotCommand>())?.let {
                                Commands(snapshotCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ClickCommand>())?.let {
                                Commands(clickCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<DblclickCommand>())?.let {
                                Commands(dblclickCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<HoverCommand>())?.let {
                                Commands(hoverCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<FocusCommand>())?.let {
                                Commands(focusCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<TypeCommand>())?.let {
                                Commands(typeCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<FillCommand>())?.let {
                                Commands(fillCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<PressCommand>())?.let {
                                Commands(pressCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<KeydownCommand>())?.let {
                                Commands(keydownCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<KeyupCommand>())?.let {
                                Commands(keyupCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<MouseCommand>())?.let {
                                Commands(mouseCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<SelectCommand>())?.let {
                                Commands(selectCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<CheckCommand>())?.let {
                                Commands(checkCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<UploadCommand>())?.let {
                                Commands(uploadCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<DragCommand>())?.let {
                                Commands(dragCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<UncheckCommand>())?.let {
                                Commands(uncheckCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<GetCommand>())?.let {
                                Commands(getCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<IsCommand>())?.let {
                                Commands(isCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<WaitCommand>())?.let {
                                Commands(waitCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ScreenshotCommand>())?.let {
                                Commands(screenshotCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<EvalCommand>())?.let {
                                Commands(evalCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<PdfCommand>())?.let {
                                Commands(pdfCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ScrollCommand>())?.let {
                                Commands(scrollCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<FindCommand>())?.let {
                                Commands(findCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<TabCommand>())?.let {
                                Commands(tabCommand = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<Command>>())?.let {
                                Commands(unionMember29 = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    0 -> Commands(_json = json)
                    1 -> bestMatches.single()
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Commands>(Commands::class) {

            override fun serialize(
                value: Commands,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.navigateCommand != null -> generator.writeObject(value.navigateCommand)
                    value.backCommand != null -> generator.writeObject(value.backCommand)
                    value.forwardCommand != null -> generator.writeObject(value.forwardCommand)
                    value.reloadCommand != null -> generator.writeObject(value.reloadCommand)
                    value.snapshotCommand != null -> generator.writeObject(value.snapshotCommand)
                    value.clickCommand != null -> generator.writeObject(value.clickCommand)
                    value.dblclickCommand != null -> generator.writeObject(value.dblclickCommand)
                    value.hoverCommand != null -> generator.writeObject(value.hoverCommand)
                    value.focusCommand != null -> generator.writeObject(value.focusCommand)
                    value.typeCommand != null -> generator.writeObject(value.typeCommand)
                    value.fillCommand != null -> generator.writeObject(value.fillCommand)
                    value.pressCommand != null -> generator.writeObject(value.pressCommand)
                    value.keydownCommand != null -> generator.writeObject(value.keydownCommand)
                    value.keyupCommand != null -> generator.writeObject(value.keyupCommand)
                    value.mouseCommand != null -> generator.writeObject(value.mouseCommand)
                    value.selectCommand != null -> generator.writeObject(value.selectCommand)
                    value.checkCommand != null -> generator.writeObject(value.checkCommand)
                    value.uploadCommand != null -> generator.writeObject(value.uploadCommand)
                    value.dragCommand != null -> generator.writeObject(value.dragCommand)
                    value.uncheckCommand != null -> generator.writeObject(value.uncheckCommand)
                    value.getCommand != null -> generator.writeObject(value.getCommand)
                    value.isCommand != null -> generator.writeObject(value.isCommand)
                    value.waitCommand != null -> generator.writeObject(value.waitCommand)
                    value.screenshotCommand != null ->
                        generator.writeObject(value.screenshotCommand)
                    value.evalCommand != null -> generator.writeObject(value.evalCommand)
                    value.pdfCommand != null -> generator.writeObject(value.pdfCommand)
                    value.scrollCommand != null -> generator.writeObject(value.scrollCommand)
                    value.findCommand != null -> generator.writeObject(value.findCommand)
                    value.tabCommand != null -> generator.writeObject(value.tabCommand)
                    value.unionMember29 != null -> generator.writeObject(value.unionMember29)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Commands")
                }
            }
        }

        /**
         * Opens a URL in the tab. Only http, https, and about:blank. A URL without a scheme gets
         * https://.
         */
        class NavigateCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val url: JsonField<String>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, url, tab, mutableMapOf())

            fun url(): String = url.getRequired("url")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("navigate")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [NavigateCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [NavigateCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("navigate")
                private var url: JsonField<String>? = null
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(navigateCommand: NavigateCommand) = apply {
                    this.command = navigateCommand.command
                    this.url = navigateCommand.url
                    this.tab = navigateCommand.tab
                    this.additionalProperties = navigateCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun url(url: String) = url(JsonField.of(url))

                fun url(url: JsonField<String>) = apply { this.url = url }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): NavigateCommand =
                    NavigateCommand(
                        command,
                        checkRequired("url", url),
                        tab,
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

            fun validate(): NavigateCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("navigate")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                url()
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("navigate")) 1 else 0 }) +
                    (if (url.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is NavigateCommand &&
                    this.command == other.command &&
                    this.url == other.url &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, url, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "NavigateCommand{command=$command, url=$url, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Goes back one history entry. */
        class BackCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, tab, mutableMapOf())

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("back")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [BackCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [BackCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("back")
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(backCommand: BackCommand) = apply {
                    this.command = backCommand.command
                    this.tab = backCommand.tab
                    this.additionalProperties = backCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): BackCommand =
                    BackCommand(command, tab, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): BackCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("back")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("back")) 1 else 0 }) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is BackCommand &&
                    this.command == other.command &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(command, tab, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "BackCommand{command=$command, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Goes forward one history entry. */
        class ForwardCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, tab, mutableMapOf())

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("forward")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [ForwardCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ForwardCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("forward")
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(forwardCommand: ForwardCommand) = apply {
                    this.command = forwardCommand.command
                    this.tab = forwardCommand.tab
                    this.additionalProperties = forwardCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): ForwardCommand =
                    ForwardCommand(command, tab, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): ForwardCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("forward")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("forward")) 1 else 0 }) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ForwardCommand &&
                    this.command == other.command &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(command, tab, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ForwardCommand{command=$command, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Reloads the tab. */
        class ReloadCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, tab, mutableMapOf())

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("reload")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [ReloadCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ReloadCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("reload")
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(reloadCommand: ReloadCommand) = apply {
                    this.command = reloadCommand.command
                    this.tab = reloadCommand.tab
                    this.additionalProperties = reloadCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): ReloadCommand =
                    ReloadCommand(command, tab, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): ReloadCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("reload")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("reload")) 1 else 0 }) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ReloadCommand &&
                    this.command == other.command &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(command, tab, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ReloadCommand{command=$command, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /**
         * Returns the accessibility tree of the page, with a ref for each element you can act on.
         */
        class SnapshotCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val compact: JsonField<Boolean>,
            private val depth: JsonField<Long>,
            private val interactive: JsonField<Boolean>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("compact")
                @ExcludeMissing
                compact: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("depth") @ExcludeMissing depth: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("interactive")
                @ExcludeMissing
                interactive: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, compact, depth, interactive, tab, mutableMapOf())

            /** Only lines with refs or values, and their ancestors. */
            fun compact(): Optional<Boolean> = compact.getOptional("compact")

            /** Only lines up to this depth. */
            fun depth(): Optional<Long> = depth.getOptional("depth")

            /** Only lines with refs. */
            fun interactive(): Optional<Boolean> = interactive.getOptional("interactive")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("snapshot")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("compact") @ExcludeMissing fun _compact(): JsonField<Boolean> = compact

            @JsonProperty("depth") @ExcludeMissing fun _depth(): JsonField<Long> = depth

            @JsonProperty("interactive")
            @ExcludeMissing
            fun _interactive(): JsonField<Boolean> = interactive

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [SnapshotCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [SnapshotCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("snapshot")
                private var compact: JsonField<Boolean> = JsonMissing.of()
                private var depth: JsonField<Long> = JsonMissing.of()
                private var interactive: JsonField<Boolean> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(snapshotCommand: SnapshotCommand) = apply {
                    this.command = snapshotCommand.command
                    this.compact = snapshotCommand.compact
                    this.depth = snapshotCommand.depth
                    this.interactive = snapshotCommand.interactive
                    this.tab = snapshotCommand.tab
                    this.additionalProperties = snapshotCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** Only lines with refs or values, and their ancestors. */
                fun compact(compact: Boolean) = compact(JsonField.of(compact))

                /** Only lines with refs or values, and their ancestors. */
                fun compact(compact: JsonField<Boolean>) = apply { this.compact = compact }

                /** Only lines up to this depth. */
                fun depth(depth: Long) = depth(JsonField.of(depth))

                /** Only lines up to this depth. */
                fun depth(depth: JsonField<Long>) = apply { this.depth = depth }

                /** Only lines with refs. */
                fun interactive(interactive: Boolean) = interactive(JsonField.of(interactive))

                /** Only lines with refs. */
                fun interactive(interactive: JsonField<Boolean>) = apply {
                    this.interactive = interactive
                }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): SnapshotCommand =
                    SnapshotCommand(
                        command,
                        compact,
                        depth,
                        interactive,
                        tab,
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

            fun validate(): SnapshotCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("snapshot")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                compact()
                depth()
                interactive()
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("snapshot")) 1 else 0 }) +
                    (if (compact.asKnown().isPresent) 1 else 0) +
                    (if (depth.asKnown().isPresent) 1 else 0) +
                    (if (interactive.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is SnapshotCommand &&
                    this.command == other.command &&
                    this.compact == other.compact &&
                    this.depth == other.depth &&
                    this.interactive == other.interactive &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, compact, depth, interactive, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "SnapshotCommand{command=$command, compact=$compact, depth=$depth, interactive=$interactive, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Clicks the element with the mouse. */
        class ClickCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("click")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [ClickCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ClickCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("click")
                private var target: JsonField<CommandTarget>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(clickCommand: ClickCommand) = apply {
                    this.command = clickCommand.command
                    this.target = clickCommand.target
                    this.expect = clickCommand.expect
                    this.frame = clickCommand.frame
                    this.tab = clickCommand.tab
                    this.additionalProperties = clickCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): ClickCommand =
                    ClickCommand(
                        command,
                        checkRequired("target", target),
                        expect,
                        frame,
                        tab,
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

            fun validate(): ClickCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("click")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("click")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ClickCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ClickCommand{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Double-clicks the element with the mouse. */
        class DblclickCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("dblclick")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [DblclickCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [DblclickCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("dblclick")
                private var target: JsonField<CommandTarget>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(dblclickCommand: DblclickCommand) = apply {
                    this.command = dblclickCommand.command
                    this.target = dblclickCommand.target
                    this.expect = dblclickCommand.expect
                    this.frame = dblclickCommand.frame
                    this.tab = dblclickCommand.tab
                    this.additionalProperties = dblclickCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): DblclickCommand =
                    DblclickCommand(
                        command,
                        checkRequired("target", target),
                        expect,
                        frame,
                        tab,
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

            fun validate(): DblclickCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("dblclick")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("dblclick")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is DblclickCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "DblclickCommand{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Moves the mouse onto the element. */
        class HoverCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("hover")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [HoverCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [HoverCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("hover")
                private var target: JsonField<CommandTarget>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(hoverCommand: HoverCommand) = apply {
                    this.command = hoverCommand.command
                    this.target = hoverCommand.target
                    this.expect = hoverCommand.expect
                    this.frame = hoverCommand.frame
                    this.tab = hoverCommand.tab
                    this.additionalProperties = hoverCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): HoverCommand =
                    HoverCommand(
                        command,
                        checkRequired("target", target),
                        expect,
                        frame,
                        tab,
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

            fun validate(): HoverCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("hover")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("hover")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is HoverCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "HoverCommand{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Focuses the element. */
        class FocusCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("focus")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [FocusCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [FocusCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("focus")
                private var target: JsonField<CommandTarget>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(focusCommand: FocusCommand) = apply {
                    this.command = focusCommand.command
                    this.target = focusCommand.target
                    this.expect = focusCommand.expect
                    this.frame = focusCommand.frame
                    this.tab = focusCommand.tab
                    this.additionalProperties = focusCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): FocusCommand =
                    FocusCommand(
                        command,
                        checkRequired("target", target),
                        expect,
                        frame,
                        tab,
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

            fun validate(): FocusCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("focus")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("focus")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FocusCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "FocusCommand{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Types text one key press at a time, after the current content. */
        class TypeCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val text: JsonField<String>,
            private val clear: JsonField<Boolean>,
            private val delay: JsonField<Long>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
                @JsonProperty("clear") @ExcludeMissing clear: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("delay") @ExcludeMissing delay: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, text, clear, delay, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            fun text(): String = text.getRequired("text")

            /** Replace the current content. */
            fun clear(): Optional<Boolean> = clear.getOptional("clear")

            /** Milliseconds to wait between key presses. */
            fun delay(): Optional<Long> = delay.getOptional("delay")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("type")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonProperty("clear") @ExcludeMissing fun _clear(): JsonField<Boolean> = clear

            @JsonProperty("delay") @ExcludeMissing fun _delay(): JsonField<Long> = delay

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [TypeCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [TypeCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("type")
                private var target: JsonField<CommandTarget>? = null
                private var text: JsonField<String>? = null
                private var clear: JsonField<Boolean> = JsonMissing.of()
                private var delay: JsonField<Long> = JsonMissing.of()
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(typeCommand: TypeCommand) = apply {
                    this.command = typeCommand.command
                    this.target = typeCommand.target
                    this.text = typeCommand.text
                    this.clear = typeCommand.clear
                    this.delay = typeCommand.delay
                    this.expect = typeCommand.expect
                    this.frame = typeCommand.frame
                    this.tab = typeCommand.tab
                    this.additionalProperties = typeCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                fun text(text: String) = text(JsonField.of(text))

                fun text(text: JsonField<String>) = apply { this.text = text }

                /** Replace the current content. */
                fun clear(clear: Boolean) = clear(JsonField.of(clear))

                /** Replace the current content. */
                fun clear(clear: JsonField<Boolean>) = apply { this.clear = clear }

                /** Milliseconds to wait between key presses. */
                fun delay(delay: Long) = delay(JsonField.of(delay))

                /** Milliseconds to wait between key presses. */
                fun delay(delay: JsonField<Long>) = apply { this.delay = delay }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): TypeCommand =
                    TypeCommand(
                        command,
                        checkRequired("target", target),
                        checkRequired("text", text),
                        clear,
                        delay,
                        expect,
                        frame,
                        tab,
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

            fun validate(): TypeCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("type")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                text()
                clear()
                delay()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("type")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (text.asKnown().isPresent) 1 else 0) +
                    (if (clear.asKnown().isPresent) 1 else 0) +
                    (if (delay.asKnown().isPresent) 1 else 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is TypeCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.text == other.text &&
                    this.clear == other.clear &&
                    this.delay == other.delay &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    command,
                    target,
                    text,
                    clear,
                    delay,
                    expect,
                    frame,
                    tab,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "TypeCommand{command=$command, target=$target, text=$text, clear=$clear, delay=$delay, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Replaces the content of a field in one step, without key presses. */
        class FillCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val value: JsonField<String>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("value") @ExcludeMissing value: JsonField<String> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, value, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            fun value(): String = value.getRequired("value")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("fill")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<String> = value

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [FillCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [FillCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("fill")
                private var target: JsonField<CommandTarget>? = null
                private var value: JsonField<String>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(fillCommand: FillCommand) = apply {
                    this.command = fillCommand.command
                    this.target = fillCommand.target
                    this.value = fillCommand.value
                    this.expect = fillCommand.expect
                    this.frame = fillCommand.frame
                    this.tab = fillCommand.tab
                    this.additionalProperties = fillCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                fun value(value: String) = value(JsonField.of(value))

                fun value(value: JsonField<String>) = apply { this.value = value }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): FillCommand =
                    FillCommand(
                        command,
                        checkRequired("target", target),
                        checkRequired("value", value),
                        expect,
                        frame,
                        tab,
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

            fun validate(): FillCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("fill")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                value()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("fill")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (value.asKnown().isPresent) 1 else 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FillCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.value == other.value &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, value, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "FillCommand{command=$command, target=$target, value=$value, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Presses a key, such as Enter, or a combination such as Control+a. */
        class PressCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val key: JsonField<String>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val target: JsonField<CommandTarget>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("key") @ExcludeMissing key: JsonField<String> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
            ) : this(command, key, expect, frame, tab, target, mutableMapOf())

            fun key(): String = key.getRequired("key")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): Optional<CommandTarget> = target.getOptional("target")

            /** Expected to always return `JsonValue.from("press")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<String> = key

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

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

                /** Returns a mutable builder for constructing an instance of [PressCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [PressCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("press")
                private var key: JsonField<String>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var target: JsonField<CommandTarget> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(pressCommand: PressCommand) = apply {
                    this.command = pressCommand.command
                    this.key = pressCommand.key
                    this.expect = pressCommand.expect
                    this.frame = pressCommand.frame
                    this.tab = pressCommand.tab
                    this.target = pressCommand.target
                    this.additionalProperties = pressCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun key(key: String) = key(JsonField.of(key))

                fun key(key: JsonField<String>) = apply { this.key = key }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

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

                fun build(): PressCommand =
                    PressCommand(
                        command,
                        checkRequired("key", key),
                        expect,
                        frame,
                        tab,
                        target,
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

            fun validate(): PressCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("press")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                key()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                target().ifPresent { it.validate() }
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("press")) 1 else 0 }) +
                    (if (key.asKnown().isPresent) 1 else 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is PressCommand &&
                    this.command == other.command &&
                    this.key == other.key &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.target == other.target &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, key, expect, frame, tab, target, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "PressCommand{command=$command, key=$key, expect=$expect, frame=$frame, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
        }

        /** Holds a key down until its keyup. */
        class KeydownCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val key: JsonField<String>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("key") @ExcludeMissing key: JsonField<String> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, key, tab, mutableMapOf())

            fun key(): String = key.getRequired("key")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("keydown")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<String> = key

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [KeydownCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [KeydownCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("keydown")
                private var key: JsonField<String>? = null
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(keydownCommand: KeydownCommand) = apply {
                    this.command = keydownCommand.command
                    this.key = keydownCommand.key
                    this.tab = keydownCommand.tab
                    this.additionalProperties = keydownCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun key(key: String) = key(JsonField.of(key))

                fun key(key: JsonField<String>) = apply { this.key = key }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): KeydownCommand =
                    KeydownCommand(
                        command,
                        checkRequired("key", key),
                        tab,
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

            fun validate(): KeydownCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("keydown")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                key()
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("keydown")) 1 else 0 }) +
                    (if (key.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is KeydownCommand &&
                    this.command == other.command &&
                    this.key == other.key &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, key, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "KeydownCommand{command=$command, key=$key, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Lets go of a key held with keydown. */
        class KeyupCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val key: JsonField<String>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("key") @ExcludeMissing key: JsonField<String> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, key, tab, mutableMapOf())

            fun key(): String = key.getRequired("key")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("keyup")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<String> = key

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [KeyupCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [KeyupCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("keyup")
                private var key: JsonField<String>? = null
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(keyupCommand: KeyupCommand) = apply {
                    this.command = keyupCommand.command
                    this.key = keyupCommand.key
                    this.tab = keyupCommand.tab
                    this.additionalProperties = keyupCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun key(key: String) = key(JsonField.of(key))

                fun key(key: JsonField<String>) = apply { this.key = key }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): KeyupCommand =
                    KeyupCommand(
                        command,
                        checkRequired("key", key),
                        tab,
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

            fun validate(): KeyupCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("keyup")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                key()
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("keyup")) 1 else 0 }) +
                    (if (key.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is KeyupCommand &&
                    this.command == other.command &&
                    this.key == other.key &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, key, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "KeyupCommand{command=$command, key=$key, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /**
         * Mouse input at a point in CSS pixels of the visible part of the page, for pages without
         * useful elements such as a canvas.
         */
        class MouseCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonField<SessionCommandsParams.Commands.MouseCommand.Action>,
            private val command: JsonValue,
            private val button: JsonField<Button>,
            private val clicks: JsonField<Long>,
            private val deltaX: JsonField<Double>,
            private val deltaY: JsonField<Double>,
            private val tab: JsonField<String>,
            private val x: JsonField<Double>,
            private val y: JsonField<Double>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action")
                @ExcludeMissing
                action: JsonField<SessionCommandsParams.Commands.MouseCommand.Action> =
                    JsonMissing.of(),
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("button")
                @ExcludeMissing
                button: JsonField<Button> = JsonMissing.of(),
                @JsonProperty("clicks") @ExcludeMissing clicks: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("deltaX")
                @ExcludeMissing
                deltaX: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("deltaY")
                @ExcludeMissing
                deltaY: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("x") @ExcludeMissing x: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("y") @ExcludeMissing y: JsonField<Double> = JsonMissing.of(),
            ) : this(action, command, button, clicks, deltaX, deltaY, tab, x, y, mutableMapOf())

            fun action(): SessionCommandsParams.Commands.MouseCommand.Action =
                action.getRequired("action")

            fun button(): Optional<Button> = button.getOptional("button")

            fun clicks(): Optional<Long> = clicks.getOptional("clicks")

            fun deltaX(): Optional<Double> = deltaX.getOptional("deltaX")

            fun deltaY(): Optional<Double> = deltaY.getOptional("deltaY")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            fun x(): Optional<Double> = x.getOptional("x")

            fun y(): Optional<Double> = y.getOptional("y")

            @JsonProperty("action")
            @ExcludeMissing
            fun _action(): JsonField<SessionCommandsParams.Commands.MouseCommand.Action> = action

            /** Expected to always return `JsonValue.from("mouse")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("button") @ExcludeMissing fun _button(): JsonField<Button> = button

            @JsonProperty("clicks") @ExcludeMissing fun _clicks(): JsonField<Long> = clicks

            @JsonProperty("deltaX") @ExcludeMissing fun _deltaX(): JsonField<Double> = deltaX

            @JsonProperty("deltaY") @ExcludeMissing fun _deltaY(): JsonField<Double> = deltaY

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

            @JsonProperty("x") @ExcludeMissing fun _x(): JsonField<Double> = x

            @JsonProperty("y") @ExcludeMissing fun _y(): JsonField<Double> = y

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

                /** Returns a mutable builder for constructing an instance of [MouseCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [MouseCommand]. */
            class Builder internal constructor() {

                private var action: JsonField<SessionCommandsParams.Commands.MouseCommand.Action>? =
                    null
                private var command: JsonValue = JsonValue.from("mouse")
                private var button: JsonField<Button> = JsonMissing.of()
                private var clicks: JsonField<Long> = JsonMissing.of()
                private var deltaX: JsonField<Double> = JsonMissing.of()
                private var deltaY: JsonField<Double> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var x: JsonField<Double> = JsonMissing.of()
                private var y: JsonField<Double> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(mouseCommand: MouseCommand) = apply {
                    this.action = mouseCommand.action
                    this.command = mouseCommand.command
                    this.button = mouseCommand.button
                    this.clicks = mouseCommand.clicks
                    this.deltaX = mouseCommand.deltaX
                    this.deltaY = mouseCommand.deltaY
                    this.tab = mouseCommand.tab
                    this.x = mouseCommand.x
                    this.y = mouseCommand.y
                    this.additionalProperties = mouseCommand.additionalProperties.toMutableMap()
                }

                fun action(action: SessionCommandsParams.Commands.MouseCommand.Action) =
                    action(JsonField.of(action))

                fun action(action: JsonField<SessionCommandsParams.Commands.MouseCommand.Action>) =
                    apply {
                        this.action = action
                    }

                fun command(command: JsonValue) = apply { this.command = command }

                fun button(button: Button) = button(JsonField.of(button))

                fun button(button: JsonField<Button>) = apply { this.button = button }

                fun clicks(clicks: Long) = clicks(JsonField.of(clicks))

                fun clicks(clicks: JsonField<Long>) = apply { this.clicks = clicks }

                fun deltaX(deltaX: Double) = deltaX(JsonField.of(deltaX))

                fun deltaX(deltaX: JsonField<Double>) = apply { this.deltaX = deltaX }

                fun deltaY(deltaY: Double) = deltaY(JsonField.of(deltaY))

                fun deltaY(deltaY: JsonField<Double>) = apply { this.deltaY = deltaY }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                fun x(x: Double) = x(JsonField.of(x))

                fun x(x: JsonField<Double>) = apply { this.x = x }

                fun y(y: Double) = y(JsonField.of(y))

                fun y(y: JsonField<Double>) = apply { this.y = y }

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

                fun build(): MouseCommand =
                    MouseCommand(
                        checkRequired("action", action),
                        command,
                        button,
                        clicks,
                        deltaX,
                        deltaY,
                        tab,
                        x,
                        y,
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

            fun validate(): MouseCommand = apply {
                if (validated) {
                    return@apply
                }

                action().validate()
                _command().let {
                    if (it != JsonValue.from("mouse")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                button().ifPresent { it.validate() }
                clicks()
                deltaX()
                deltaY()
                tab()
                x()
                y()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (command.let { if (it == JsonValue.from("mouse")) 1 else 0 }) +
                    (button.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (clicks.asKnown().isPresent) 1 else 0) +
                    (if (deltaX.asKnown().isPresent) 1 else 0) +
                    (if (deltaY.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (if (x.asKnown().isPresent) 1 else 0) +
                    (if (y.asKnown().isPresent) 1 else 0)

            class Action @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val MOVE = of("move")

                    @JvmField val DOWN = of("down")

                    @JvmField val UP = of("up")

                    @JvmField val CLICK = of("click")

                    @JvmField val WHEEL = of("wheel")

                    @JvmStatic fun of(value: String) = Action(JsonField.of(value))
                }

                enum class Known {
                    MOVE,
                    DOWN,
                    UP,
                    CLICK,
                    WHEEL,
                }

                enum class Value {
                    MOVE,
                    DOWN,
                    UP,
                    CLICK,
                    WHEEL,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        MOVE -> Value.MOVE
                        DOWN -> Value.DOWN
                        UP -> Value.UP
                        CLICK -> Value.CLICK
                        WHEEL -> Value.WHEEL
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        MOVE -> Known.MOVE
                        DOWN -> Known.DOWN
                        UP -> Known.UP
                        CLICK -> Known.CLICK
                        WHEEL -> Known.WHEEL
                        else -> throw SteelInvalidDataException("Unknown Action: $value")
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

                fun validate(): Action = apply {
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

                    return other is Action && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            class Button @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val LEFT = of("left")

                    @JvmField val RIGHT = of("right")

                    @JvmField val MIDDLE = of("middle")

                    @JvmStatic fun of(value: String) = Button(JsonField.of(value))
                }

                enum class Known {
                    LEFT,
                    RIGHT,
                    MIDDLE,
                }

                enum class Value {
                    LEFT,
                    RIGHT,
                    MIDDLE,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        LEFT -> Value.LEFT
                        RIGHT -> Value.RIGHT
                        MIDDLE -> Value.MIDDLE
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        LEFT -> Known.LEFT
                        RIGHT -> Known.RIGHT
                        MIDDLE -> Known.MIDDLE
                        else -> throw SteelInvalidDataException("Unknown Button: $value")
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

                fun validate(): Button = apply {
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

                    return other is Button && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is MouseCommand &&
                    this.action == other.action &&
                    this.command == other.command &&
                    this.button == other.button &&
                    this.clicks == other.clicks &&
                    this.deltaX == other.deltaX &&
                    this.deltaY == other.deltaY &&
                    this.tab == other.tab &&
                    this.x == other.x &&
                    this.y == other.y &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    action,
                    command,
                    button,
                    clicks,
                    deltaX,
                    deltaY,
                    tab,
                    x,
                    y,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "MouseCommand{action=$action, command=$command, button=$button, clicks=$clicks, deltaX=$deltaX, deltaY=$deltaY, tab=$tab, x=$x, y=$y, additionalProperties=$additionalProperties}"
        }

        /** Selects options. Matches by value, then by label. */
        class SelectCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val values: JsonField<Values>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("values")
                @ExcludeMissing
                values: JsonField<Values> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, values, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            fun values(): Values = values.getRequired("values")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("select")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("values") @ExcludeMissing fun _values(): JsonField<Values> = values

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [SelectCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [SelectCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("select")
                private var target: JsonField<CommandTarget>? = null
                private var values: JsonField<Values>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(selectCommand: SelectCommand) = apply {
                    this.command = selectCommand.command
                    this.target = selectCommand.target
                    this.values = selectCommand.values
                    this.expect = selectCommand.expect
                    this.frame = selectCommand.frame
                    this.tab = selectCommand.tab
                    this.additionalProperties = selectCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                fun values(values: Values) = values(JsonField.of(values))

                fun values(values: JsonField<Values>) = apply { this.values = values }

                fun values(string: String) = values(Values.ofString(string))

                fun values(strings: List<String>) = values(Values.ofStrings(strings))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): SelectCommand =
                    SelectCommand(
                        command,
                        checkRequired("target", target),
                        checkRequired("values", values),
                        expect,
                        frame,
                        tab,
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

            fun validate(): SelectCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("select")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                values().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("select")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (values.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            @JsonDeserialize(using = Values.Deserializer::class)
            @JsonSerialize(using = Values.Serializer::class)
            class Values
            private constructor(
                private val string: String? = null,
                private val strings: List<String>? = null,
                private val _json: JsonValue? = null,
            ) {

                fun string(): Optional<String> = Optional.ofNullable(string)

                fun strings(): Optional<List<String>> = Optional.ofNullable(strings)

                fun isString(): Boolean = string != null

                fun isStrings(): Boolean = strings != null

                fun asString(): String = string.getOrThrow("string")

                fun asStrings(): List<String> = strings.getOrThrow("strings")

                fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                fun <T> accept(visitor: Visitor<T>): T =
                    when {
                        string != null -> visitor.visitString(string)
                        strings != null -> visitor.visitStrings(strings)
                        else -> visitor.unknown(_json)
                    }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: SteelInvalidDataException) {
                        false
                    }

                private var validated: Boolean = false

                fun validate(): Values = apply {
                    if (validated) {
                        return@apply
                    }

                    accept(
                        object : Visitor<Unit> {
                            override fun visitString(string: String) {}

                            override fun visitStrings(strings: List<String>) {}
                        }
                    )
                    validated = true
                }

                @JvmSynthetic
                internal fun validity(): Int =
                    accept(
                        object : Visitor<Int> {
                            override fun visitString(string: String) = 1

                            override fun visitStrings(strings: List<String>) = strings.size

                            override fun unknown(json: JsonValue?) = 0
                        }
                    )

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Values &&
                        this.string == other.string &&
                        this.strings == other.strings
                }

                override fun hashCode(): Int = Objects.hash(string, strings)

                override fun toString(): String =
                    when {
                        string != null -> "Values{string=$string}"
                        strings != null -> "Values{strings=$strings}"
                        _json != null -> "Values{_unknown=$_json}"
                        else -> throw IllegalStateException("Invalid Values")
                    }

                companion object {

                    @JvmStatic fun ofString(string: String) = Values(string = string)

                    @JvmStatic
                    fun ofStrings(strings: List<String>) = Values(strings = strings.toImmutable())
                }

                interface Visitor<out T> {

                    fun visitString(string: String): T

                    fun visitStrings(strings: List<String>): T

                    fun unknown(json: JsonValue?): T {
                        throw SteelInvalidDataException("Unknown Values: $json")
                    }
                }

                internal class Deserializer : BaseDeserializer<Values>(Values::class) {

                    override fun ObjectCodec.deserialize(node: JsonNode): Values {
                        val json = JsonValue.fromJsonNode(node)
                        val bestMatches =
                            sequenceOf(
                                    tryDeserialize(node, jacksonTypeRef<List<String>>())?.let {
                                        Values(strings = it, _json = json)
                                    },
                                    tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                        Values(string = it, _json = json)
                                    },
                                )
                                .filterNotNull()
                                .allMaxBy { it.validity() }
                                .toList()
                        return when (bestMatches.size) {
                            0 -> Values(_json = json)
                            1 -> bestMatches.single()
                            else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                        }
                    }
                }

                internal class Serializer : BaseSerializer<Values>(Values::class) {

                    override fun serialize(
                        value: Values,
                        generator: JsonGenerator,
                        provider: SerializerProvider,
                    ) {
                        when {
                            value.string != null -> generator.writeObject(value.string)
                            value.strings != null -> generator.writeObject(value.strings)
                            value._json != null -> generator.writeObject(value._json)
                            else -> throw IllegalStateException("Invalid Values")
                        }
                    }
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is SelectCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.values == other.values &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, values, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "SelectCommand{command=$command, target=$target, values=$values, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Checks a checkbox, radio button, switch, or toggle button. */
        class CheckCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("check")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [CheckCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [CheckCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("check")
                private var target: JsonField<CommandTarget>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(checkCommand: CheckCommand) = apply {
                    this.command = checkCommand.command
                    this.target = checkCommand.target
                    this.expect = checkCommand.expect
                    this.frame = checkCommand.frame
                    this.tab = checkCommand.tab
                    this.additionalProperties = checkCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): CheckCommand =
                    CheckCommand(
                        command,
                        checkRequired("target", target),
                        expect,
                        frame,
                        tab,
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

            fun validate(): CheckCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("check")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("check")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is CheckCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "CheckCommand{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Sets the files of a file input. Upload the files to the session first. */
        class UploadCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val files: JsonField<Files>,
            private val target: JsonField<CommandTarget>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("files") @ExcludeMissing files: JsonField<Files> = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, files, target, expect, frame, tab, mutableMapOf())

            fun files(): Files = files.getRequired("files")

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("upload")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("files") @ExcludeMissing fun _files(): JsonField<Files> = files

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [UploadCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UploadCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("upload")
                private var files: JsonField<Files>? = null
                private var target: JsonField<CommandTarget>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(uploadCommand: UploadCommand) = apply {
                    this.command = uploadCommand.command
                    this.files = uploadCommand.files
                    this.target = uploadCommand.target
                    this.expect = uploadCommand.expect
                    this.frame = uploadCommand.frame
                    this.tab = uploadCommand.tab
                    this.additionalProperties = uploadCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun files(files: Files) = files(JsonField.of(files))

                fun files(files: JsonField<Files>) = apply { this.files = files }

                fun files(string: String) = files(Files.ofString(string))

                fun files(strings: List<String>) = files(Files.ofStrings(strings))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): UploadCommand =
                    UploadCommand(
                        command,
                        checkRequired("files", files),
                        checkRequired("target", target),
                        expect,
                        frame,
                        tab,
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

            fun validate(): UploadCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("upload")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                files().validate()
                target().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("upload")) 1 else 0 }) +
                    (files.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            @JsonDeserialize(using = Files.Deserializer::class)
            @JsonSerialize(using = Files.Serializer::class)
            class Files
            private constructor(
                private val string: String? = null,
                private val strings: List<String>? = null,
                private val _json: JsonValue? = null,
            ) {

                fun string(): Optional<String> = Optional.ofNullable(string)

                fun strings(): Optional<List<String>> = Optional.ofNullable(strings)

                fun isString(): Boolean = string != null

                fun isStrings(): Boolean = strings != null

                fun asString(): String = string.getOrThrow("string")

                fun asStrings(): List<String> = strings.getOrThrow("strings")

                fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                fun <T> accept(visitor: Visitor<T>): T =
                    when {
                        string != null -> visitor.visitString(string)
                        strings != null -> visitor.visitStrings(strings)
                        else -> visitor.unknown(_json)
                    }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: SteelInvalidDataException) {
                        false
                    }

                private var validated: Boolean = false

                fun validate(): Files = apply {
                    if (validated) {
                        return@apply
                    }

                    accept(
                        object : Visitor<Unit> {
                            override fun visitString(string: String) {}

                            override fun visitStrings(strings: List<String>) {}
                        }
                    )
                    validated = true
                }

                @JvmSynthetic
                internal fun validity(): Int =
                    accept(
                        object : Visitor<Int> {
                            override fun visitString(string: String) = 1

                            override fun visitStrings(strings: List<String>) = strings.size

                            override fun unknown(json: JsonValue?) = 0
                        }
                    )

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Files &&
                        this.string == other.string &&
                        this.strings == other.strings
                }

                override fun hashCode(): Int = Objects.hash(string, strings)

                override fun toString(): String =
                    when {
                        string != null -> "Files{string=$string}"
                        strings != null -> "Files{strings=$strings}"
                        _json != null -> "Files{_unknown=$_json}"
                        else -> throw IllegalStateException("Invalid Files")
                    }

                companion object {

                    @JvmStatic fun ofString(string: String) = Files(string = string)

                    @JvmStatic
                    fun ofStrings(strings: List<String>) = Files(strings = strings.toImmutable())
                }

                interface Visitor<out T> {

                    fun visitString(string: String): T

                    fun visitStrings(strings: List<String>): T

                    fun unknown(json: JsonValue?): T {
                        throw SteelInvalidDataException("Unknown Files: $json")
                    }
                }

                internal class Deserializer : BaseDeserializer<Files>(Files::class) {

                    override fun ObjectCodec.deserialize(node: JsonNode): Files {
                        val json = JsonValue.fromJsonNode(node)
                        val bestMatches =
                            sequenceOf(
                                    tryDeserialize(node, jacksonTypeRef<List<String>>())?.let {
                                        Files(strings = it, _json = json)
                                    },
                                    tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                        Files(string = it, _json = json)
                                    },
                                )
                                .filterNotNull()
                                .allMaxBy { it.validity() }
                                .toList()
                        return when (bestMatches.size) {
                            0 -> Files(_json = json)
                            1 -> bestMatches.single()
                            else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                        }
                    }
                }

                internal class Serializer : BaseSerializer<Files>(Files::class) {

                    override fun serialize(
                        value: Files,
                        generator: JsonGenerator,
                        provider: SerializerProvider,
                    ) {
                        when {
                            value.string != null -> generator.writeObject(value.string)
                            value.strings != null -> generator.writeObject(value.strings)
                            value._json != null -> generator.writeObject(value._json)
                            else -> throw IllegalStateException("Invalid Files")
                        }
                    }
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UploadCommand &&
                    this.command == other.command &&
                    this.files == other.files &&
                    this.target == other.target &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, files, target, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UploadCommand{command=$command, files=$files, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Drags one element onto another with the mouse. */
        class DragCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val source: JsonField<CommandTarget>,
            private val target: JsonField<CommandTarget>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("source")
                @ExcludeMissing
                source: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, source, target, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun source(): CommandTarget = source.getRequired("source")

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("drag")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<CommandTarget> = source

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [DragCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [DragCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("drag")
                private var source: JsonField<CommandTarget>? = null
                private var target: JsonField<CommandTarget>? = null
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(dragCommand: DragCommand) = apply {
                    this.command = dragCommand.command
                    this.source = dragCommand.source
                    this.target = dragCommand.target
                    this.frame = dragCommand.frame
                    this.tab = dragCommand.tab
                    this.additionalProperties = dragCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun source(source: CommandTarget) = source(JsonField.of(source))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun source(source: JsonField<CommandTarget>) = apply { this.source = source }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun source(string: String) = source(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun source(commandLocator: CommandLocator) =
                    source(CommandTarget.ofCommandLocator(commandLocator))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): DragCommand =
                    DragCommand(
                        command,
                        checkRequired("source", source),
                        checkRequired("target", target),
                        frame,
                        tab,
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

            fun validate(): DragCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("drag")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                source().validate()
                target().validate()
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("drag")) 1 else 0 }) +
                    (source.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is DragCommand &&
                    this.command == other.command &&
                    this.source == other.source &&
                    this.target == other.target &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, source, target, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "DragCommand{command=$command, source=$source, target=$target, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Unchecks a checkbox, switch, or toggle button. */
        class UncheckCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("uncheck")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [UncheckCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UncheckCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("uncheck")
                private var target: JsonField<CommandTarget>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(uncheckCommand: UncheckCommand) = apply {
                    this.command = uncheckCommand.command
                    this.target = uncheckCommand.target
                    this.expect = uncheckCommand.expect
                    this.frame = uncheckCommand.frame
                    this.tab = uncheckCommand.tab
                    this.additionalProperties = uncheckCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): UncheckCommand =
                    UncheckCommand(
                        command,
                        checkRequired("target", target),
                        expect,
                        frame,
                        tab,
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

            fun validate(): UncheckCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("uncheck")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("uncheck")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UncheckCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UncheckCommand{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Reads something from the page or an element. */
        class GetCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val what: JsonField<SessionCommandsParams.Commands.GetCommand.What>,
            private val attribute: JsonField<String>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val target: JsonField<CommandTarget>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("what")
                @ExcludeMissing
                what: JsonField<SessionCommandsParams.Commands.GetCommand.What> = JsonMissing.of(),
                @JsonProperty("attribute")
                @ExcludeMissing
                attribute: JsonField<String> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
            ) : this(command, what, attribute, expect, frame, tab, target, mutableMapOf())

            fun what(): SessionCommandsParams.Commands.GetCommand.What = what.getRequired("what")

            /** The attribute to read with attr. */
            fun attribute(): Optional<String> = attribute.getOptional("attribute")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): Optional<CommandTarget> = target.getOptional("target")

            /** Expected to always return `JsonValue.from("get")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("what")
            @ExcludeMissing
            fun _what(): JsonField<SessionCommandsParams.Commands.GetCommand.What> = what

            @JsonProperty("attribute")
            @ExcludeMissing
            fun _attribute(): JsonField<String> = attribute

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

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

                /** Returns a mutable builder for constructing an instance of [GetCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [GetCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("get")
                private var what: JsonField<SessionCommandsParams.Commands.GetCommand.What>? = null
                private var attribute: JsonField<String> = JsonMissing.of()
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var target: JsonField<CommandTarget> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(getCommand: GetCommand) = apply {
                    this.command = getCommand.command
                    this.what = getCommand.what
                    this.attribute = getCommand.attribute
                    this.expect = getCommand.expect
                    this.frame = getCommand.frame
                    this.tab = getCommand.tab
                    this.target = getCommand.target
                    this.additionalProperties = getCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun what(what: SessionCommandsParams.Commands.GetCommand.What) =
                    what(JsonField.of(what))

                fun what(what: JsonField<SessionCommandsParams.Commands.GetCommand.What>) = apply {
                    this.what = what
                }

                /** The attribute to read with attr. */
                fun attribute(attribute: String) = attribute(JsonField.of(attribute))

                /** The attribute to read with attr. */
                fun attribute(attribute: JsonField<String>) = apply { this.attribute = attribute }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

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

                fun build(): GetCommand =
                    GetCommand(
                        command,
                        checkRequired("what", what),
                        attribute,
                        expect,
                        frame,
                        tab,
                        target,
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

            fun validate(): GetCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("get")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                what().validate()
                attribute()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                target().ifPresent { it.validate() }
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("get")) 1 else 0 }) +
                    (what.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (attribute.asKnown().isPresent) 1 else 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0)

            class What @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val TEXT = of("text")

                    @JvmField val HTML = of("html")

                    @JvmField val MARKDOWN = of("markdown")

                    @JvmField val VALUE = of("value")

                    @JvmField val ATTR = of("attr")

                    @JvmField val TITLE = of("title")

                    @JvmField val URL = of("url")

                    @JvmField val COUNT = of("count")

                    @JvmField val BOX = of("box")

                    @JvmField val LOCATOR = of("locator")

                    @JvmStatic fun of(value: String) = What(JsonField.of(value))
                }

                enum class Known {
                    TEXT,
                    HTML,
                    MARKDOWN,
                    VALUE,
                    ATTR,
                    TITLE,
                    URL,
                    COUNT,
                    BOX,
                    LOCATOR,
                }

                enum class Value {
                    TEXT,
                    HTML,
                    MARKDOWN,
                    VALUE,
                    ATTR,
                    TITLE,
                    URL,
                    COUNT,
                    BOX,
                    LOCATOR,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        TEXT -> Value.TEXT
                        HTML -> Value.HTML
                        MARKDOWN -> Value.MARKDOWN
                        VALUE -> Value.VALUE
                        ATTR -> Value.ATTR
                        TITLE -> Value.TITLE
                        URL -> Value.URL
                        COUNT -> Value.COUNT
                        BOX -> Value.BOX
                        LOCATOR -> Value.LOCATOR
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        TEXT -> Known.TEXT
                        HTML -> Known.HTML
                        MARKDOWN -> Known.MARKDOWN
                        VALUE -> Known.VALUE
                        ATTR -> Known.ATTR
                        TITLE -> Known.TITLE
                        URL -> Known.URL
                        COUNT -> Known.COUNT
                        BOX -> Known.BOX
                        LOCATOR -> Known.LOCATOR
                        else -> throw SteelInvalidDataException("Unknown What: $value")
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

                fun validate(): What = apply {
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

                    return other is What && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is GetCommand &&
                    this.command == other.command &&
                    this.what == other.what &&
                    this.attribute == other.attribute &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.target == other.target &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    command,
                    what,
                    attribute,
                    expect,
                    frame,
                    tab,
                    target,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "GetCommand{command=$command, what=$what, attribute=$attribute, expect=$expect, frame=$frame, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
        }

        /** Checks the state of an element. */
        class IsCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val what: JsonField<SessionCommandsParams.Commands.IsCommand.What>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("what")
                @ExcludeMissing
                what: JsonField<SessionCommandsParams.Commands.IsCommand.What> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, what, expect, frame, tab, mutableMapOf())

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): CommandTarget = target.getRequired("target")

            fun what(): SessionCommandsParams.Commands.IsCommand.What = what.getRequired("what")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("is")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("what")
            @ExcludeMissing
            fun _what(): JsonField<SessionCommandsParams.Commands.IsCommand.What> = what

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [IsCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [IsCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("is")
                private var target: JsonField<CommandTarget>? = null
                private var what: JsonField<SessionCommandsParams.Commands.IsCommand.What>? = null
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(isCommand: IsCommand) = apply {
                    this.command = isCommand.command
                    this.target = isCommand.target
                    this.what = isCommand.what
                    this.expect = isCommand.expect
                    this.frame = isCommand.frame
                    this.tab = isCommand.tab
                    this.additionalProperties = isCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                fun what(what: SessionCommandsParams.Commands.IsCommand.What) =
                    what(JsonField.of(what))

                fun what(what: JsonField<SessionCommandsParams.Commands.IsCommand.What>) = apply {
                    this.what = what
                }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): IsCommand =
                    IsCommand(
                        command,
                        checkRequired("target", target),
                        checkRequired("what", what),
                        expect,
                        frame,
                        tab,
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

            fun validate(): IsCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("is")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                what().validate()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("is")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (what.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            class What @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val VISIBLE = of("visible")

                    @JvmField val ENABLED = of("enabled")

                    @JvmField val CHECKED = of("checked")

                    @JvmStatic fun of(value: String) = What(JsonField.of(value))
                }

                enum class Known {
                    VISIBLE,
                    ENABLED,
                    CHECKED,
                }

                enum class Value {
                    VISIBLE,
                    ENABLED,
                    CHECKED,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        VISIBLE -> Value.VISIBLE
                        ENABLED -> Value.ENABLED
                        CHECKED -> Value.CHECKED
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        VISIBLE -> Known.VISIBLE
                        ENABLED -> Known.ENABLED
                        CHECKED -> Known.CHECKED
                        else -> throw SteelInvalidDataException("Unknown What: $value")
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

                fun validate(): What = apply {
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

                    return other is What && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is IsCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.what == other.what &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, what, expect, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "IsCommand{command=$command, target=$target, what=$what, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Waits for one of an element, text, a URL, a load state, a download, or a fixed time. */
        class WaitCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val download: JsonField<Boolean>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val load: JsonField<Load>,
            private val ms: JsonField<Long>,
            private val state: JsonField<State>,
            private val tab: JsonField<String>,
            private val target: JsonField<CommandTarget>,
            private val text: JsonField<String>,
            private val timeout: JsonField<Long>,
            private val url: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("download")
                @ExcludeMissing
                download: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("load") @ExcludeMissing load: JsonField<Load> = JsonMissing.of(),
                @JsonProperty("ms") @ExcludeMissing ms: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("state") @ExcludeMissing state: JsonField<State> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
                @JsonProperty("timeout")
                @ExcludeMissing
                timeout: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            ) : this(
                command,
                download,
                expect,
                frame,
                load,
                ms,
                state,
                tab,
                target,
                text,
                timeout,
                url,
                mutableMapOf(),
            )

            /** Waits for the next download to finish. */
            fun download(): Optional<Boolean> = download.getOptional("download")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            fun load(): Optional<Load> = load.getOptional("load")

            fun ms(): Optional<Long> = ms.getOptional("ms")

            fun state(): Optional<State> = state.getOptional("state")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): Optional<CommandTarget> = target.getOptional("target")

            fun text(): Optional<String> = text.getOptional("text")

            /** Milliseconds. 10000 by default. */
            fun timeout(): Optional<Long> = timeout.getOptional("timeout")

            /** A URL pattern. * matches any characters. */
            fun url(): Optional<String> = url.getOptional("url")

            /** Expected to always return `JsonValue.from("wait")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("download") @ExcludeMissing fun _download(): JsonField<Boolean> = download

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("load") @ExcludeMissing fun _load(): JsonField<Load> = load

            @JsonProperty("ms") @ExcludeMissing fun _ms(): JsonField<Long> = ms

            @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<State> = state

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonProperty("timeout") @ExcludeMissing fun _timeout(): JsonField<Long> = timeout

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

                /** Returns a mutable builder for constructing an instance of [WaitCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [WaitCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("wait")
                private var download: JsonField<Boolean> = JsonMissing.of()
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var load: JsonField<Load> = JsonMissing.of()
                private var ms: JsonField<Long> = JsonMissing.of()
                private var state: JsonField<State> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var target: JsonField<CommandTarget> = JsonMissing.of()
                private var text: JsonField<String> = JsonMissing.of()
                private var timeout: JsonField<Long> = JsonMissing.of()
                private var url: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(waitCommand: WaitCommand) = apply {
                    this.command = waitCommand.command
                    this.download = waitCommand.download
                    this.expect = waitCommand.expect
                    this.frame = waitCommand.frame
                    this.load = waitCommand.load
                    this.ms = waitCommand.ms
                    this.state = waitCommand.state
                    this.tab = waitCommand.tab
                    this.target = waitCommand.target
                    this.text = waitCommand.text
                    this.timeout = waitCommand.timeout
                    this.url = waitCommand.url
                    this.additionalProperties = waitCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** Waits for the next download to finish. */
                fun download(download: Boolean) = download(JsonField.of(download))

                /** Waits for the next download to finish. */
                fun download(download: JsonField<Boolean>) = apply { this.download = download }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                fun load(load: Load) = load(JsonField.of(load))

                fun load(load: JsonField<Load>) = apply { this.load = load }

                fun ms(ms: Long) = ms(JsonField.of(ms))

                fun ms(ms: JsonField<Long>) = apply { this.ms = ms }

                fun state(state: State) = state(JsonField.of(state))

                fun state(state: JsonField<State>) = apply { this.state = state }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                fun text(text: String) = text(JsonField.of(text))

                fun text(text: JsonField<String>) = apply { this.text = text }

                /** Milliseconds. 10000 by default. */
                fun timeout(timeout: Long) = timeout(JsonField.of(timeout))

                /** Milliseconds. 10000 by default. */
                fun timeout(timeout: JsonField<Long>) = apply { this.timeout = timeout }

                /** A URL pattern. * matches any characters. */
                fun url(url: String) = url(JsonField.of(url))

                /** A URL pattern. * matches any characters. */
                fun url(url: JsonField<String>) = apply { this.url = url }

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

                fun build(): WaitCommand =
                    WaitCommand(
                        command,
                        download,
                        expect,
                        frame,
                        load,
                        ms,
                        state,
                        tab,
                        target,
                        text,
                        timeout,
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

            fun validate(): WaitCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("wait")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                download()
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                load().ifPresent { it.validate() }
                ms()
                state().ifPresent { it.validate() }
                tab()
                target().ifPresent { it.validate() }
                text()
                timeout()
                url()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("wait")) 1 else 0 }) +
                    (if (download.asKnown().isPresent) 1 else 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (load.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (ms.asKnown().isPresent) 1 else 0) +
                    (state.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (text.asKnown().isPresent) 1 else 0) +
                    (if (timeout.asKnown().isPresent) 1 else 0) +
                    (if (url.asKnown().isPresent) 1 else 0)

            class Load @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val LOAD = of("load")

                    @JvmField val DOMCONTENTLOADED = of("domcontentloaded")

                    @JvmField val NETWORKIDLE = of("networkidle")

                    @JvmStatic fun of(value: String) = Load(JsonField.of(value))
                }

                enum class Known {
                    LOAD,
                    DOMCONTENTLOADED,
                    NETWORKIDLE,
                }

                enum class Value {
                    LOAD,
                    DOMCONTENTLOADED,
                    NETWORKIDLE,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        LOAD -> Value.LOAD
                        DOMCONTENTLOADED -> Value.DOMCONTENTLOADED
                        NETWORKIDLE -> Value.NETWORKIDLE
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        LOAD -> Known.LOAD
                        DOMCONTENTLOADED -> Known.DOMCONTENTLOADED
                        NETWORKIDLE -> Known.NETWORKIDLE
                        else -> throw SteelInvalidDataException("Unknown Load: $value")
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

                fun validate(): Load = apply {
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

                    return other is Load && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            class State @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val VISIBLE = of("visible")

                    @JvmField val HIDDEN = of("hidden")

                    @JvmField val ATTACHED = of("attached")

                    @JvmField val DETACHED = of("detached")

                    @JvmStatic fun of(value: String) = State(JsonField.of(value))
                }

                enum class Known {
                    VISIBLE,
                    HIDDEN,
                    ATTACHED,
                    DETACHED,
                }

                enum class Value {
                    VISIBLE,
                    HIDDEN,
                    ATTACHED,
                    DETACHED,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        VISIBLE -> Value.VISIBLE
                        HIDDEN -> Value.HIDDEN
                        ATTACHED -> Value.ATTACHED
                        DETACHED -> Value.DETACHED
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        VISIBLE -> Known.VISIBLE
                        HIDDEN -> Known.HIDDEN
                        ATTACHED -> Known.ATTACHED
                        DETACHED -> Known.DETACHED
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

                return other is WaitCommand &&
                    this.command == other.command &&
                    this.download == other.download &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.load == other.load &&
                    this.ms == other.ms &&
                    this.state == other.state &&
                    this.tab == other.tab &&
                    this.target == other.target &&
                    this.text == other.text &&
                    this.timeout == other.timeout &&
                    this.url == other.url &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    command,
                    download,
                    expect,
                    frame,
                    load,
                    ms,
                    state,
                    tab,
                    target,
                    text,
                    timeout,
                    url,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "WaitCommand{command=$command, download=$download, expect=$expect, frame=$frame, load=$load, ms=$ms, state=$state, tab=$tab, target=$target, text=$text, timeout=$timeout, url=$url, additionalProperties=$additionalProperties}"
        }

        /** Captures the page or an element as base64 data. */
        class ScreenshotCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val expect: JsonField<CommandExpectation>,
            private val format: JsonField<Format>,
            private val frame: JsonField<CommandFrame>,
            private val fullPage: JsonField<Boolean>,
            private val quality: JsonField<Long>,
            private val tab: JsonField<String>,
            private val target: JsonField<CommandTarget>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("format")
                @ExcludeMissing
                format: JsonField<Format> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("fullPage")
                @ExcludeMissing
                fullPage: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("quality")
                @ExcludeMissing
                quality: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
            ) : this(command, expect, format, frame, fullPage, quality, tab, target, mutableMapOf())

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            fun format(): Optional<Format> = format.getOptional("format")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            fun fullPage(): Optional<Boolean> = fullPage.getOptional("fullPage")

            fun quality(): Optional<Long> = quality.getOptional("quality")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): Optional<CommandTarget> = target.getOptional("target")

            /** Expected to always return `JsonValue.from("screenshot")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("format") @ExcludeMissing fun _format(): JsonField<Format> = format

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("fullPage") @ExcludeMissing fun _fullPage(): JsonField<Boolean> = fullPage

            @JsonProperty("quality") @ExcludeMissing fun _quality(): JsonField<Long> = quality

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

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
                 * Returns a mutable builder for constructing an instance of [ScreenshotCommand].
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ScreenshotCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("screenshot")
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var format: JsonField<Format> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var fullPage: JsonField<Boolean> = JsonMissing.of()
                private var quality: JsonField<Long> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var target: JsonField<CommandTarget> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(screenshotCommand: ScreenshotCommand) = apply {
                    this.command = screenshotCommand.command
                    this.expect = screenshotCommand.expect
                    this.format = screenshotCommand.format
                    this.frame = screenshotCommand.frame
                    this.fullPage = screenshotCommand.fullPage
                    this.quality = screenshotCommand.quality
                    this.tab = screenshotCommand.tab
                    this.target = screenshotCommand.target
                    this.additionalProperties =
                        screenshotCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                fun format(format: Format) = format(JsonField.of(format))

                fun format(format: JsonField<Format>) = apply { this.format = format }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                fun fullPage(fullPage: Boolean) = fullPage(JsonField.of(fullPage))

                fun fullPage(fullPage: JsonField<Boolean>) = apply { this.fullPage = fullPage }

                fun quality(quality: Long) = quality(JsonField.of(quality))

                fun quality(quality: JsonField<Long>) = apply { this.quality = quality }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

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

                fun build(): ScreenshotCommand =
                    ScreenshotCommand(
                        command,
                        expect,
                        format,
                        frame,
                        fullPage,
                        quality,
                        tab,
                        target,
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

            fun validate(): ScreenshotCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("screenshot")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                expect().ifPresent { it.validate() }
                format().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                fullPage()
                quality()
                tab()
                target().ifPresent { it.validate() }
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("screenshot")) 1 else 0 }) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (format.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (fullPage.asKnown().isPresent) 1 else 0) +
                    (if (quality.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0)

            class Format @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val PNG = of("png")

                    @JvmField val JPEG = of("jpeg")

                    @JvmField val WEBP = of("webp")

                    @JvmStatic fun of(value: String) = Format(JsonField.of(value))
                }

                enum class Known {
                    PNG,
                    JPEG,
                    WEBP,
                }

                enum class Value {
                    PNG,
                    JPEG,
                    WEBP,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        PNG -> Value.PNG
                        JPEG -> Value.JPEG
                        WEBP -> Value.WEBP
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        PNG -> Known.PNG
                        JPEG -> Known.JPEG
                        WEBP -> Known.WEBP
                        else -> throw SteelInvalidDataException("Unknown Format: $value")
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

                fun validate(): Format = apply {
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

                    return other is Format && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ScreenshotCommand &&
                    this.command == other.command &&
                    this.expect == other.expect &&
                    this.format == other.format &&
                    this.frame == other.frame &&
                    this.fullPage == other.fullPage &&
                    this.quality == other.quality &&
                    this.tab == other.tab &&
                    this.target == other.target &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    command,
                    expect,
                    format,
                    frame,
                    fullPage,
                    quality,
                    tab,
                    target,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ScreenshotCommand{command=$command, expect=$expect, format=$format, frame=$frame, fullPage=$fullPage, quality=$quality, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
        }

        /** Runs JavaScript in the page and returns the result as JSON. */
        class EvalCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val script: JsonField<String>,
            private val tab: JsonField<String>,
            private val timeout: JsonField<Long>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("script")
                @ExcludeMissing
                script: JsonField<String> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("timeout") @ExcludeMissing timeout: JsonField<Long> = JsonMissing.of(),
            ) : this(command, script, tab, timeout, mutableMapOf())

            fun script(): String = script.getRequired("script")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Milliseconds. 10000 by default. */
            fun timeout(): Optional<Long> = timeout.getOptional("timeout")

            /** Expected to always return `JsonValue.from("eval")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("script") @ExcludeMissing fun _script(): JsonField<String> = script

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

            @JsonProperty("timeout") @ExcludeMissing fun _timeout(): JsonField<Long> = timeout

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

                /** Returns a mutable builder for constructing an instance of [EvalCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [EvalCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("eval")
                private var script: JsonField<String>? = null
                private var tab: JsonField<String> = JsonMissing.of()
                private var timeout: JsonField<Long> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(evalCommand: EvalCommand) = apply {
                    this.command = evalCommand.command
                    this.script = evalCommand.script
                    this.tab = evalCommand.tab
                    this.timeout = evalCommand.timeout
                    this.additionalProperties = evalCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun script(script: String) = script(JsonField.of(script))

                fun script(script: JsonField<String>) = apply { this.script = script }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                /** Milliseconds. 10000 by default. */
                fun timeout(timeout: Long) = timeout(JsonField.of(timeout))

                /** Milliseconds. 10000 by default. */
                fun timeout(timeout: JsonField<Long>) = apply { this.timeout = timeout }

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

                fun build(): EvalCommand =
                    EvalCommand(
                        command,
                        checkRequired("script", script),
                        tab,
                        timeout,
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

            fun validate(): EvalCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("eval")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                script()
                tab()
                timeout()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("eval")) 1 else 0 }) +
                    (if (script.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (if (timeout.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is EvalCommand &&
                    this.command == other.command &&
                    this.script == other.script &&
                    this.tab == other.tab &&
                    this.timeout == other.timeout &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, script, tab, timeout, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "EvalCommand{command=$command, script=$script, tab=$tab, timeout=$timeout, additionalProperties=$additionalProperties}"
        }

        /** Prints the page to a PDF as base64 data. */
        class PdfCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val landscape: JsonField<Boolean>,
            private val printBackground: JsonField<Boolean>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("landscape")
                @ExcludeMissing
                landscape: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("printBackground")
                @ExcludeMissing
                printBackground: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, landscape, printBackground, tab, mutableMapOf())

            fun landscape(): Optional<Boolean> = landscape.getOptional("landscape")

            fun printBackground(): Optional<Boolean> =
                printBackground.getOptional("printBackground")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("pdf")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("landscape")
            @ExcludeMissing
            fun _landscape(): JsonField<Boolean> = landscape

            @JsonProperty("printBackground")
            @ExcludeMissing
            fun _printBackground(): JsonField<Boolean> = printBackground

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [PdfCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [PdfCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("pdf")
                private var landscape: JsonField<Boolean> = JsonMissing.of()
                private var printBackground: JsonField<Boolean> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(pdfCommand: PdfCommand) = apply {
                    this.command = pdfCommand.command
                    this.landscape = pdfCommand.landscape
                    this.printBackground = pdfCommand.printBackground
                    this.tab = pdfCommand.tab
                    this.additionalProperties = pdfCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                fun landscape(landscape: Boolean) = landscape(JsonField.of(landscape))

                fun landscape(landscape: JsonField<Boolean>) = apply { this.landscape = landscape }

                fun printBackground(printBackground: Boolean) =
                    printBackground(JsonField.of(printBackground))

                fun printBackground(printBackground: JsonField<Boolean>) = apply {
                    this.printBackground = printBackground
                }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): PdfCommand =
                    PdfCommand(
                        command,
                        landscape,
                        printBackground,
                        tab,
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

            fun validate(): PdfCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("pdf")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                landscape()
                printBackground()
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("pdf")) 1 else 0 }) +
                    (if (landscape.asKnown().isPresent) 1 else 0) +
                    (if (printBackground.asKnown().isPresent) 1 else 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is PdfCommand &&
                    this.command == other.command &&
                    this.landscape == other.landscape &&
                    this.printBackground == other.printBackground &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, landscape, printBackground, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "PdfCommand{command=$command, landscape=$landscape, printBackground=$printBackground, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Scrolls the page, or an element when target is set. */
        class ScrollCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val amount: JsonField<Double>,
            private val direction: JsonField<Direction>,
            private val expect: JsonField<CommandExpectation>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val target: JsonField<CommandTarget>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("amount")
                @ExcludeMissing
                amount: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("direction")
                @ExcludeMissing
                direction: JsonField<Direction> = JsonMissing.of(),
                @JsonProperty("expect")
                @ExcludeMissing
                expect: JsonField<CommandExpectation> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
            ) : this(command, amount, direction, expect, frame, tab, target, mutableMapOf())

            /** Pixels. 300 by default. */
            fun amount(): Optional<Double> = amount.getOptional("amount")

            fun direction(): Optional<Direction> = direction.getOptional("direction")

            /** Fails the command when the element does not have this role or name. */
            fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
            fun target(): Optional<CommandTarget> = target.getOptional("target")

            /** Expected to always return `JsonValue.from("scroll")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<Double> = amount

            @JsonProperty("direction")
            @ExcludeMissing
            fun _direction(): JsonField<Direction> = direction

            @JsonProperty("expect")
            @ExcludeMissing
            fun _expect(): JsonField<CommandExpectation> = expect

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

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

                /** Returns a mutable builder for constructing an instance of [ScrollCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ScrollCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("scroll")
                private var amount: JsonField<Double> = JsonMissing.of()
                private var direction: JsonField<Direction> = JsonMissing.of()
                private var expect: JsonField<CommandExpectation> = JsonMissing.of()
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var target: JsonField<CommandTarget> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(scrollCommand: ScrollCommand) = apply {
                    this.command = scrollCommand.command
                    this.amount = scrollCommand.amount
                    this.direction = scrollCommand.direction
                    this.expect = scrollCommand.expect
                    this.frame = scrollCommand.frame
                    this.tab = scrollCommand.tab
                    this.target = scrollCommand.target
                    this.additionalProperties = scrollCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** Pixels. 300 by default. */
                fun amount(amount: Double) = amount(JsonField.of(amount))

                /** Pixels. 300 by default. */
                fun amount(amount: JsonField<Double>) = apply { this.amount = amount }

                fun direction(direction: Direction) = direction(JsonField.of(direction))

                fun direction(direction: JsonField<Direction>) = apply {
                    this.direction = direction
                }

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: CommandExpectation) = expect(JsonField.of(expect))

                /** Fails the command when the element does not have this role or name. */
                fun expect(expect: JsonField<CommandExpectation>) = apply { this.expect = expect }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A ref from a snapshot such as e12, a CSS selector, or a locator. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

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

                fun build(): ScrollCommand =
                    ScrollCommand(
                        command,
                        amount,
                        direction,
                        expect,
                        frame,
                        tab,
                        target,
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

            fun validate(): ScrollCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("scroll")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                amount()
                direction().ifPresent { it.validate() }
                expect().ifPresent { it.validate() }
                frame().ifPresent { it.validate() }
                tab()
                target().ifPresent { it.validate() }
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("scroll")) 1 else 0 }) +
                    (if (amount.asKnown().isPresent) 1 else 0) +
                    (direction.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (expect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0)

            class Direction @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val UP = of("up")

                    @JvmField val DOWN = of("down")

                    @JvmField val LEFT = of("left")

                    @JvmField val RIGHT = of("right")

                    @JvmStatic fun of(value: String) = Direction(JsonField.of(value))
                }

                enum class Known {
                    UP,
                    DOWN,
                    LEFT,
                    RIGHT,
                }

                enum class Value {
                    UP,
                    DOWN,
                    LEFT,
                    RIGHT,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        UP -> Value.UP
                        DOWN -> Value.DOWN
                        LEFT -> Value.LEFT
                        RIGHT -> Value.RIGHT
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        UP -> Known.UP
                        DOWN -> Known.DOWN
                        LEFT -> Known.LEFT
                        RIGHT -> Known.RIGHT
                        else -> throw SteelInvalidDataException("Unknown Direction: $value")
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

                fun validate(): Direction = apply {
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

                    return other is Direction && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ScrollCommand &&
                    this.command == other.command &&
                    this.amount == other.amount &&
                    this.direction == other.direction &&
                    this.expect == other.expect &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.target == other.target &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    command,
                    amount,
                    direction,
                    expect,
                    frame,
                    tab,
                    target,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ScrollCommand{command=$command, amount=$amount, direction=$direction, expect=$expect, frame=$frame, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
        }

        /**
         * Finds elements and returns refs for up to 50 matches. With nth, it returns that one
         * match.
         */
        class FindCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val command: JsonValue,
            private val target: JsonField<CommandTarget>,
            private val frame: JsonField<CommandFrame>,
            private val tab: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("target")
                @ExcludeMissing
                target: JsonField<CommandTarget> = JsonMissing.of(),
                @JsonProperty("frame")
                @ExcludeMissing
                frame: JsonField<CommandFrame> = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            ) : this(command, target, frame, tab, mutableMapOf())

            /** A CSS selector or a locator, not a ref. */
            fun target(): CommandTarget = target.getRequired("target")

            /** The iframe to look in. List nested iframes from the outside in. */
            fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            /** Expected to always return `JsonValue.from("find")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

            @JsonProperty("frame") @ExcludeMissing fun _frame(): JsonField<CommandFrame> = frame

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [FindCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [FindCommand]. */
            class Builder internal constructor() {

                private var command: JsonValue = JsonValue.from("find")
                private var target: JsonField<CommandTarget>? = null
                private var frame: JsonField<CommandFrame> = JsonMissing.of()
                private var tab: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(findCommand: FindCommand) = apply {
                    this.command = findCommand.command
                    this.target = findCommand.target
                    this.frame = findCommand.frame
                    this.tab = findCommand.tab
                    this.additionalProperties = findCommand.additionalProperties.toMutableMap()
                }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A CSS selector or a locator, not a ref. */
                fun target(target: CommandTarget) = target(JsonField.of(target))

                /** A CSS selector or a locator, not a ref. */
                fun target(target: JsonField<CommandTarget>) = apply { this.target = target }

                /** A CSS selector or a locator, not a ref. */
                fun target(string: String) = target(CommandTarget.ofString(string))

                /** A CSS selector or a locator, not a ref. */
                fun target(commandLocator: CommandLocator) =
                    target(CommandTarget.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: CommandFrame) = frame(JsonField.of(frame))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(frame: JsonField<CommandFrame>) = apply { this.frame = frame }

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(string: String) = frame(CommandFrame.ofString(string))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(commandLocator: CommandLocator) =
                    frame(CommandFrame.ofCommandLocator(commandLocator))

                /** The iframe to look in. List nested iframes from the outside in. */
                fun frame(unionMember2: List<CommandTarget>) =
                    frame(CommandFrame.ofUnionMember2(unionMember2))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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

                fun build(): FindCommand =
                    FindCommand(
                        command,
                        checkRequired("target", target),
                        frame,
                        tab,
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

            fun validate(): FindCommand = apply {
                if (validated) {
                    return@apply
                }

                _command().let {
                    if (it != JsonValue.from("find")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                target().validate()
                frame().ifPresent { it.validate() }
                tab()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (command.let { if (it == JsonValue.from("find")) 1 else 0 }) +
                    (target.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (frame.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (tab.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is FindCommand &&
                    this.command == other.command &&
                    this.target == other.target &&
                    this.frame == other.frame &&
                    this.tab == other.tab &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(command, target, frame, tab, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "FindCommand{command=$command, target=$target, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
        }

        /** Lists, opens, or closes tabs. The last tab cannot be closed. */
        class TabCommand
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonField<SessionCommandsParams.Commands.TabCommand.Action>,
            private val command: JsonValue,
            private val tab: JsonField<String>,
            private val url: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action")
                @ExcludeMissing
                action: JsonField<SessionCommandsParams.Commands.TabCommand.Action> =
                    JsonMissing.of(),
                @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
                @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            ) : this(action, command, tab, url, mutableMapOf())

            fun action(): SessionCommandsParams.Commands.TabCommand.Action =
                action.getRequired("action")

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(): Optional<String> = tab.getOptional("tab")

            fun url(): Optional<String> = url.getOptional("url")

            @JsonProperty("action")
            @ExcludeMissing
            fun _action(): JsonField<SessionCommandsParams.Commands.TabCommand.Action> = action

            /** Expected to always return `JsonValue.from("tab")`. It is set by default. */
            @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

            @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

                /** Returns a mutable builder for constructing an instance of [TabCommand]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [TabCommand]. */
            class Builder internal constructor() {

                private var action: JsonField<SessionCommandsParams.Commands.TabCommand.Action>? =
                    null
                private var command: JsonValue = JsonValue.from("tab")
                private var tab: JsonField<String> = JsonMissing.of()
                private var url: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(tabCommand: TabCommand) = apply {
                    this.action = tabCommand.action
                    this.command = tabCommand.command
                    this.tab = tabCommand.tab
                    this.url = tabCommand.url
                    this.additionalProperties = tabCommand.additionalProperties.toMutableMap()
                }

                fun action(action: SessionCommandsParams.Commands.TabCommand.Action) =
                    action(JsonField.of(action))

                fun action(action: JsonField<SessionCommandsParams.Commands.TabCommand.Action>) =
                    apply {
                        this.action = action
                    }

                fun command(command: JsonValue) = apply { this.command = command }

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: String) = tab(JsonField.of(tab))

                /** A tab id such as t2. A command on a ref does not need it. */
                fun tab(tab: JsonField<String>) = apply { this.tab = tab }

                fun url(url: String) = url(JsonField.of(url))

                fun url(url: JsonField<String>) = apply { this.url = url }

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

                fun build(): TabCommand =
                    TabCommand(
                        checkRequired("action", action),
                        command,
                        tab,
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

            fun validate(): TabCommand = apply {
                if (validated) {
                    return@apply
                }

                action().validate()
                _command().let {
                    if (it != JsonValue.from("tab")) {
                        throw SteelInvalidDataException("'command' is invalid, received $it")
                    }
                }
                tab()
                url()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (command.let { if (it == JsonValue.from("tab")) 1 else 0 }) +
                    (if (tab.asKnown().isPresent) 1 else 0) +
                    (if (url.asKnown().isPresent) 1 else 0)

            class Action @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val LIST = of("list")

                    @JvmField val NEW = of("new")

                    @JvmField val CLOSE = of("close")

                    @JvmStatic fun of(value: String) = Action(JsonField.of(value))
                }

                enum class Known {
                    LIST,
                    NEW,
                    CLOSE,
                }

                enum class Value {
                    LIST,
                    NEW,
                    CLOSE,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        LIST -> Value.LIST
                        NEW -> Value.NEW
                        CLOSE -> Value.CLOSE
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        LIST -> Known.LIST
                        NEW -> Known.NEW
                        CLOSE -> Known.CLOSE
                        else -> throw SteelInvalidDataException("Unknown Action: $value")
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

                fun validate(): Action = apply {
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

                    return other is Action && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is TabCommand &&
                    this.action == other.action &&
                    this.command == other.command &&
                    this.tab == other.tab &&
                    this.url == other.url &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(action, command, tab, url, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "TabCommand{action=$action, command=$command, tab=$tab, url=$url, additionalProperties=$additionalProperties}"
        }
    }

    /** Returns a snapshot, or a diff against the last one, after the commands. */
    class Observe
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val mode: JsonField<Mode>,
        private val base: JsonField<String>,
        private val compact: JsonField<Boolean>,
        private val depth: JsonField<Long>,
        private val interactive: JsonField<Boolean>,
        private val tab: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("mode") @ExcludeMissing mode: JsonField<Mode> = JsonMissing.of(),
            @JsonProperty("base") @ExcludeMissing base: JsonField<String> = JsonMissing.of(),
            @JsonProperty("compact") @ExcludeMissing compact: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("depth") @ExcludeMissing depth: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("interactive")
            @ExcludeMissing
            interactive: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
        ) : this(mode, base, compact, depth, interactive, tab, mutableMapOf())

        fun mode(): Mode = mode.getRequired("mode")

        /** A snapshot id to diff against. */
        fun base(): Optional<String> = base.getOptional("base")

        /** Only lines with refs or values, and their ancestors. */
        fun compact(): Optional<Boolean> = compact.getOptional("compact")

        /** Only lines up to this depth. */
        fun depth(): Optional<Long> = depth.getOptional("depth")

        /** Only lines with refs. */
        fun interactive(): Optional<Boolean> = interactive.getOptional("interactive")

        /** A tab id such as t2. A command on a ref does not need it. */
        fun tab(): Optional<String> = tab.getOptional("tab")

        @JsonProperty("mode") @ExcludeMissing fun _mode(): JsonField<Mode> = mode

        @JsonProperty("base") @ExcludeMissing fun _base(): JsonField<String> = base

        @JsonProperty("compact") @ExcludeMissing fun _compact(): JsonField<Boolean> = compact

        @JsonProperty("depth") @ExcludeMissing fun _depth(): JsonField<Long> = depth

        @JsonProperty("interactive")
        @ExcludeMissing
        fun _interactive(): JsonField<Boolean> = interactive

        @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

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

            private var mode: JsonField<Mode>? = null
            private var base: JsonField<String> = JsonMissing.of()
            private var compact: JsonField<Boolean> = JsonMissing.of()
            private var depth: JsonField<Long> = JsonMissing.of()
            private var interactive: JsonField<Boolean> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(observe: Observe) = apply {
                this.mode = observe.mode
                this.base = observe.base
                this.compact = observe.compact
                this.depth = observe.depth
                this.interactive = observe.interactive
                this.tab = observe.tab
                this.additionalProperties = observe.additionalProperties.toMutableMap()
            }

            fun mode(mode: Mode) = mode(JsonField.of(mode))

            fun mode(mode: JsonField<Mode>) = apply { this.mode = mode }

            /** A snapshot id to diff against. */
            fun base(base: String) = base(JsonField.of(base))

            /** A snapshot id to diff against. */
            fun base(base: JsonField<String>) = apply { this.base = base }

            /** Only lines with refs or values, and their ancestors. */
            fun compact(compact: Boolean) = compact(JsonField.of(compact))

            /** Only lines with refs or values, and their ancestors. */
            fun compact(compact: JsonField<Boolean>) = apply { this.compact = compact }

            /** Only lines up to this depth. */
            fun depth(depth: Long) = depth(JsonField.of(depth))

            /** Only lines up to this depth. */
            fun depth(depth: JsonField<Long>) = apply { this.depth = depth }

            /** Only lines with refs. */
            fun interactive(interactive: Boolean) = interactive(JsonField.of(interactive))

            /** Only lines with refs. */
            fun interactive(interactive: JsonField<Boolean>) = apply {
                this.interactive = interactive
            }

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(tab: String) = tab(JsonField.of(tab))

            /** A tab id such as t2. A command on a ref does not need it. */
            fun tab(tab: JsonField<String>) = apply { this.tab = tab }

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
                    checkRequired("mode", mode),
                    base,
                    compact,
                    depth,
                    interactive,
                    tab,
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

            mode().validate()
            base()
            compact()
            depth()
            interactive()
            tab()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (mode.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (base.asKnown().isPresent) 1 else 0) +
                (if (compact.asKnown().isPresent) 1 else 0) +
                (if (depth.asKnown().isPresent) 1 else 0) +
                (if (interactive.asKnown().isPresent) 1 else 0) +
                (if (tab.asKnown().isPresent) 1 else 0)

        class Mode @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val NONE = of("none")

                @JvmField val SNAPSHOT = of("snapshot")

                @JvmField val DIFF = of("diff")

                @JvmStatic fun of(value: String) = Mode(JsonField.of(value))
            }

            enum class Known {
                NONE,
                SNAPSHOT,
                DIFF,
            }

            enum class Value {
                NONE,
                SNAPSHOT,
                DIFF,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    NONE -> Value.NONE
                    SNAPSHOT -> Value.SNAPSHOT
                    DIFF -> Value.DIFF
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    NONE -> Known.NONE
                    SNAPSHOT -> Known.SNAPSHOT
                    DIFF -> Known.DIFF
                    else -> throw SteelInvalidDataException("Unknown Mode: $value")
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

            fun validate(): Mode = apply {
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

                return other is Mode && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Observe &&
                this.mode == other.mode &&
                this.base == other.base &&
                this.compact == other.compact &&
                this.depth == other.depth &&
                this.interactive == other.interactive &&
                this.tab == other.tab &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(mode, base, compact, depth, interactive, tab, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Observe{mode=$mode, base=$base, compact=$compact, depth=$depth, interactive=$interactive, tab=$tab, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionCommandsParams &&
            this.sessionId == other.sessionId &&
            this.idempotencyKey == other.idempotencyKey &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(sessionId, idempotencyKey, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SessionCommandsParams{sessionId=$sessionId, idempotencyKey=$idempotencyKey, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
