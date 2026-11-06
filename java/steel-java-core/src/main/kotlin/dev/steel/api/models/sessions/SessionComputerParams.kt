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
import dev.steel.api.core.checkKnown
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

/** Execute computer actions like mouse movements, clicks, keyboard input, and more */
class SessionComputerParams
private constructor(
    private val sessionId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Session ID */
    fun sessionId(): Optional<String> = Optional.ofNullable(sessionId)

    fun body(): Body = body

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [SessionComputerParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionComputerParams]. */
    class Builder internal constructor() {

        private var sessionId: String? = null
        private var body: Body? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionComputerParams: SessionComputerParams) = apply {
            this.sessionId = sessionComputerParams.sessionId
            this.body = sessionComputerParams.body
            this.additionalHeaders = sessionComputerParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = sessionComputerParams.additionalQueryParams.toBuilder()
        }

        /** Session ID */
        fun sessionId(sessionId: String?) = apply { this.sessionId = sessionId }

        /** Session ID */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

        fun body(body: Body) = apply { this.body = body }

        fun body(moveMouse: Body.MoveMouse) = body(Body.ofMoveMouse(moveMouse))

        fun body(clickMouse: Body.ClickMouse) = body(Body.ofClickMouse(clickMouse))

        fun body(dragMouse: Body.DragMouse) = body(Body.ofDragMouse(dragMouse))

        fun body(scroll: Body.Scroll) = body(Body.ofScroll(scroll))

        fun body(pressKey: Body.PressKey) = body(Body.ofPressKey(pressKey))

        fun body(typeText: Body.TypeText) = body(Body.ofTypeText(typeText))

        fun body(wait_: Body.Wait) = body(Body.ofWait(wait_))

        fun body(takeScreenshot: Body.TakeScreenshot) = body(Body.ofTakeScreenshot(takeScreenshot))

        fun body(getCursorPosition: Body.GetCursorPosition) =
            body(Body.ofGetCursorPosition(getCursorPosition))

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

        fun build(): SessionComputerParams =
            SessionComputerParams(
                sessionId,
                checkRequired("body", body),
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

    @JsonDeserialize(using = Body.Deserializer::class)
    @JsonSerialize(using = Body.Serializer::class)
    class Body
    private constructor(
        private val moveMouse: MoveMouse? = null,
        private val clickMouse: ClickMouse? = null,
        private val dragMouse: DragMouse? = null,
        private val scroll: Scroll? = null,
        private val pressKey: PressKey? = null,
        private val typeText: TypeText? = null,
        private val wait_: Wait? = null,
        private val takeScreenshot: TakeScreenshot? = null,
        private val getCursorPosition: GetCursorPosition? = null,
        private val _json: JsonValue? = null,
    ) {

        fun moveMouse(): Optional<MoveMouse> = Optional.ofNullable(moveMouse)

        fun clickMouse(): Optional<ClickMouse> = Optional.ofNullable(clickMouse)

        fun dragMouse(): Optional<DragMouse> = Optional.ofNullable(dragMouse)

        fun scroll(): Optional<Scroll> = Optional.ofNullable(scroll)

        fun pressKey(): Optional<PressKey> = Optional.ofNullable(pressKey)

        fun typeText(): Optional<TypeText> = Optional.ofNullable(typeText)

        fun wait_(): Optional<Wait> = Optional.ofNullable(wait_)

        fun takeScreenshot(): Optional<TakeScreenshot> = Optional.ofNullable(takeScreenshot)

        fun getCursorPosition(): Optional<GetCursorPosition> =
            Optional.ofNullable(getCursorPosition)

        fun isMoveMouse(): Boolean = moveMouse != null

        fun isClickMouse(): Boolean = clickMouse != null

        fun isDragMouse(): Boolean = dragMouse != null

        fun isScroll(): Boolean = scroll != null

        fun isPressKey(): Boolean = pressKey != null

        fun isTypeText(): Boolean = typeText != null

        fun isWait(): Boolean = wait_ != null

        fun isTakeScreenshot(): Boolean = takeScreenshot != null

        fun isGetCursorPosition(): Boolean = getCursorPosition != null

        fun asMoveMouse(): MoveMouse = moveMouse.getOrThrow("moveMouse")

        fun asClickMouse(): ClickMouse = clickMouse.getOrThrow("clickMouse")

        fun asDragMouse(): DragMouse = dragMouse.getOrThrow("dragMouse")

        fun asScroll(): Scroll = scroll.getOrThrow("scroll")

        fun asPressKey(): PressKey = pressKey.getOrThrow("pressKey")

        fun asTypeText(): TypeText = typeText.getOrThrow("typeText")

        fun asWait(): Wait = wait_.getOrThrow("wait_")

        fun asTakeScreenshot(): TakeScreenshot = takeScreenshot.getOrThrow("takeScreenshot")

        fun asGetCursorPosition(): GetCursorPosition =
            getCursorPosition.getOrThrow("getCursorPosition")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                moveMouse != null -> visitor.visitMoveMouse(moveMouse)
                clickMouse != null -> visitor.visitClickMouse(clickMouse)
                dragMouse != null -> visitor.visitDragMouse(dragMouse)
                scroll != null -> visitor.visitScroll(scroll)
                pressKey != null -> visitor.visitPressKey(pressKey)
                typeText != null -> visitor.visitTypeText(typeText)
                wait_ != null -> visitor.visitWait(wait_)
                takeScreenshot != null -> visitor.visitTakeScreenshot(takeScreenshot)
                getCursorPosition != null -> visitor.visitGetCursorPosition(getCursorPosition)
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

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitMoveMouse(moveMouse: MoveMouse) {
                        moveMouse.validate()
                    }

                    override fun visitClickMouse(clickMouse: ClickMouse) {
                        clickMouse.validate()
                    }

                    override fun visitDragMouse(dragMouse: DragMouse) {
                        dragMouse.validate()
                    }

                    override fun visitScroll(scroll: Scroll) {
                        scroll.validate()
                    }

                    override fun visitPressKey(pressKey: PressKey) {
                        pressKey.validate()
                    }

                    override fun visitTypeText(typeText: TypeText) {
                        typeText.validate()
                    }

                    override fun visitWait(wait_: Wait) {
                        wait_.validate()
                    }

                    override fun visitTakeScreenshot(takeScreenshot: TakeScreenshot) {
                        takeScreenshot.validate()
                    }

                    override fun visitGetCursorPosition(getCursorPosition: GetCursorPosition) {
                        getCursorPosition.validate()
                    }
                }
            )
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitMoveMouse(moveMouse: MoveMouse) = moveMouse.validity()

                    override fun visitClickMouse(clickMouse: ClickMouse) = clickMouse.validity()

                    override fun visitDragMouse(dragMouse: DragMouse) = dragMouse.validity()

                    override fun visitScroll(scroll: Scroll) = scroll.validity()

                    override fun visitPressKey(pressKey: PressKey) = pressKey.validity()

                    override fun visitTypeText(typeText: TypeText) = typeText.validity()

                    override fun visitWait(wait_: Wait) = wait_.validity()

                    override fun visitTakeScreenshot(takeScreenshot: TakeScreenshot) =
                        takeScreenshot.validity()

                    override fun visitGetCursorPosition(getCursorPosition: GetCursorPosition) =
                        getCursorPosition.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.moveMouse == other.moveMouse &&
                this.clickMouse == other.clickMouse &&
                this.dragMouse == other.dragMouse &&
                this.scroll == other.scroll &&
                this.pressKey == other.pressKey &&
                this.typeText == other.typeText &&
                this.wait_ == other.wait_ &&
                this.takeScreenshot == other.takeScreenshot &&
                this.getCursorPosition == other.getCursorPosition
        }

        override fun hashCode(): Int =
            Objects.hash(
                moveMouse,
                clickMouse,
                dragMouse,
                scroll,
                pressKey,
                typeText,
                wait_,
                takeScreenshot,
                getCursorPosition,
            )

        override fun toString(): String =
            when {
                moveMouse != null -> "Body{moveMouse=$moveMouse}"
                clickMouse != null -> "Body{clickMouse=$clickMouse}"
                dragMouse != null -> "Body{dragMouse=$dragMouse}"
                scroll != null -> "Body{scroll=$scroll}"
                pressKey != null -> "Body{pressKey=$pressKey}"
                typeText != null -> "Body{typeText=$typeText}"
                wait_ != null -> "Body{wait_=$wait_}"
                takeScreenshot != null -> "Body{takeScreenshot=$takeScreenshot}"
                getCursorPosition != null -> "Body{getCursorPosition=$getCursorPosition}"
                _json != null -> "Body{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Body")
            }

        companion object {

            @JvmStatic fun ofMoveMouse(moveMouse: MoveMouse) = Body(moveMouse = moveMouse)

            @JvmStatic fun ofClickMouse(clickMouse: ClickMouse) = Body(clickMouse = clickMouse)

            @JvmStatic fun ofDragMouse(dragMouse: DragMouse) = Body(dragMouse = dragMouse)

            @JvmStatic fun ofScroll(scroll: Scroll) = Body(scroll = scroll)

            @JvmStatic fun ofPressKey(pressKey: PressKey) = Body(pressKey = pressKey)

            @JvmStatic fun ofTypeText(typeText: TypeText) = Body(typeText = typeText)

            @JvmStatic fun ofWait(wait_: Wait) = Body(wait_ = wait_)

            @JvmStatic
            fun ofTakeScreenshot(takeScreenshot: TakeScreenshot) =
                Body(takeScreenshot = takeScreenshot)

            @JvmStatic
            fun ofGetCursorPosition(getCursorPosition: GetCursorPosition) =
                Body(getCursorPosition = getCursorPosition)
        }

        interface Visitor<out T> {

            fun visitMoveMouse(moveMouse: MoveMouse): T

            fun visitClickMouse(clickMouse: ClickMouse): T

            fun visitDragMouse(dragMouse: DragMouse): T

            fun visitScroll(scroll: Scroll): T

            fun visitPressKey(pressKey: PressKey): T

            fun visitTypeText(typeText: TypeText): T

            fun visitWait(wait_: Wait): T

            fun visitTakeScreenshot(takeScreenshot: TakeScreenshot): T

            fun visitGetCursorPosition(getCursorPosition: GetCursorPosition): T

            fun unknown(json: JsonValue?): T {
                throw SteelInvalidDataException("Unknown Body: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Body>(Body::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Body {
                val json = JsonValue.fromJsonNode(node)
                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<MoveMouse>())?.let {
                                Body(moveMouse = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<ClickMouse>())?.let {
                                Body(clickMouse = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<DragMouse>())?.let {
                                Body(dragMouse = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<Scroll>())?.let {
                                Body(scroll = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<PressKey>())?.let {
                                Body(pressKey = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<TypeText>())?.let {
                                Body(typeText = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<Wait>())?.let {
                                Body(wait_ = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<TakeScreenshot>())?.let {
                                Body(takeScreenshot = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<GetCursorPosition>())?.let {
                                Body(getCursorPosition = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    0 -> Body(_json = json)
                    1 -> bestMatches.single()
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Body>(Body::class) {

            override fun serialize(
                value: Body,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.moveMouse != null -> generator.writeObject(value.moveMouse)
                    value.clickMouse != null -> generator.writeObject(value.clickMouse)
                    value.dragMouse != null -> generator.writeObject(value.dragMouse)
                    value.scroll != null -> generator.writeObject(value.scroll)
                    value.pressKey != null -> generator.writeObject(value.pressKey)
                    value.typeText != null -> generator.writeObject(value.typeText)
                    value.wait_ != null -> generator.writeObject(value.wait_)
                    value.takeScreenshot != null -> generator.writeObject(value.takeScreenshot)
                    value.getCursorPosition != null ->
                        generator.writeObject(value.getCursorPosition)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Body")
                }
            }
        }

        class MoveMouse
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val coordinates: JsonField<List<Double>>,
            private val holdKeys: JsonField<List<String>>,
            private val screenshot: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of(),
                @JsonProperty("coordinates")
                @ExcludeMissing
                coordinates: JsonField<List<Double>> = JsonMissing.of(),
                @JsonProperty("hold_keys")
                @ExcludeMissing
                holdKeys: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("screenshot")
                @ExcludeMissing
                screenshot: JsonField<Boolean> = JsonMissing.of(),
            ) : this(action, coordinates, holdKeys, screenshot, mutableMapOf())

            /** X and Y coordinates [x, y] */
            fun coordinates(): List<Double> = coordinates.getRequired("coordinates")

            /** Keys to hold while moving */
            fun holdKeys(): Optional<List<String>> = holdKeys.getOptional("hold_keys")

            /** Whether to take a screenshot after the action */
            fun screenshot(): Optional<Boolean> = screenshot.getOptional("screenshot")

            /** Expected to always return `JsonValue.from("move_mouse")`. It is set by default. */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

            @JsonProperty("coordinates")
            @ExcludeMissing
            fun _coordinates(): JsonField<List<Double>> = coordinates

            @JsonProperty("hold_keys")
            @ExcludeMissing
            fun _holdKeys(): JsonField<List<String>> = holdKeys

            @JsonProperty("screenshot")
            @ExcludeMissing
            fun _screenshot(): JsonField<Boolean> = screenshot

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

                /** Returns a mutable builder for constructing an instance of [MoveMouse]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [MoveMouse]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("move_mouse")
                private var coordinates: JsonField<MutableList<Double>>? = null
                private var holdKeys: JsonField<MutableList<String>>? = null
                private var screenshot: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(moveMouse: MoveMouse) = apply {
                    this.action = moveMouse.action
                    this.coordinates = moveMouse.coordinates.map { it.toMutableList() }
                    this.holdKeys = moveMouse.holdKeys.map { it.toMutableList() }
                    this.screenshot = moveMouse.screenshot
                    this.additionalProperties = moveMouse.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

                /** X and Y coordinates [x, y] */
                fun coordinates(coordinates: List<Double>) = coordinates(JsonField.of(coordinates))

                /** X and Y coordinates [x, y] */
                fun coordinates(coordinates: JsonField<List<Double>>) = apply {
                    this.coordinates = coordinates.map { it.toMutableList() }
                }

                /** X and Y coordinates [x, y] */
                fun addCoordinate(coordinate: Double) = apply {
                    this.coordinates =
                        (this.coordinates ?: JsonField.of(mutableListOf())).also {
                            checkKnown("coordinates", it).add(coordinate)
                        }
                }

                /** Keys to hold while moving */
                fun holdKeys(holdKeys: List<String>) = holdKeys(JsonField.of(holdKeys))

                /** Keys to hold while moving */
                fun holdKeys(holdKeys: JsonField<List<String>>) = apply {
                    this.holdKeys = holdKeys.map { it.toMutableList() }
                }

                /** Keys to hold while moving */
                fun addHoldKey(holdKey: String) = apply {
                    this.holdKeys =
                        (this.holdKeys ?: JsonField.of(mutableListOf())).also {
                            checkKnown("hold_keys", it).add(holdKey)
                        }
                }

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: Boolean) = screenshot(JsonField.of(screenshot))

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: JsonField<Boolean>) = apply {
                    this.screenshot = screenshot
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

                fun build(): MoveMouse =
                    MoveMouse(
                        action,
                        checkRequired("coordinates", coordinates).map { it.toImmutable() },
                        (holdKeys ?: JsonMissing.of()).map { it.toImmutable() },
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

            fun validate(): MoveMouse = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("move_mouse")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                coordinates()
                holdKeys()
                screenshot()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("move_mouse")) 1 else 0 }) +
                    (coordinates.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (holdKeys.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (screenshot.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is MoveMouse &&
                    this.action == other.action &&
                    this.coordinates == other.coordinates &&
                    this.holdKeys == other.holdKeys &&
                    this.screenshot == other.screenshot &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(action, coordinates, holdKeys, screenshot, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "MoveMouse{action=$action, coordinates=$coordinates, holdKeys=$holdKeys, screenshot=$screenshot, additionalProperties=$additionalProperties}"
        }

        class ClickMouse
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val button: JsonField<Button>,
            private val clickType: JsonField<ClickType>,
            private val coordinates: JsonField<List<Double>>,
            private val holdKeys: JsonField<List<String>>,
            private val numClicks: JsonField<Double>,
            private val screenshot: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of(),
                @JsonProperty("button")
                @ExcludeMissing
                button: JsonField<Button> = JsonMissing.of(),
                @JsonProperty("click_type")
                @ExcludeMissing
                clickType: JsonField<ClickType> = JsonMissing.of(),
                @JsonProperty("coordinates")
                @ExcludeMissing
                coordinates: JsonField<List<Double>> = JsonMissing.of(),
                @JsonProperty("hold_keys")
                @ExcludeMissing
                holdKeys: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("num_clicks")
                @ExcludeMissing
                numClicks: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("screenshot")
                @ExcludeMissing
                screenshot: JsonField<Boolean> = JsonMissing.of(),
            ) : this(
                action,
                button,
                clickType,
                coordinates,
                holdKeys,
                numClicks,
                screenshot,
                mutableMapOf(),
            )

            /** Mouse button to click. Defaults to 'left' */
            fun button(): Optional<Button> = button.getOptional("button")

            /** Type of click (down, up, or click). Defaults to 'click' */
            fun clickType(): Optional<ClickType> = clickType.getOptional("click_type")

            /** X and Y coordinates [x, y] */
            fun coordinates(): Optional<List<Double>> = coordinates.getOptional("coordinates")

            /** Keys to hold while clicking */
            fun holdKeys(): Optional<List<String>> = holdKeys.getOptional("hold_keys")

            /** Number of clicks. Defaults to 1 */
            fun numClicks(): Optional<Double> = numClicks.getOptional("num_clicks")

            /** Whether to take a screenshot after the action */
            fun screenshot(): Optional<Boolean> = screenshot.getOptional("screenshot")

            /** Expected to always return `JsonValue.from("click_mouse")`. It is set by default. */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

            @JsonProperty("button") @ExcludeMissing fun _button(): JsonField<Button> = button

            @JsonProperty("click_type")
            @ExcludeMissing
            fun _clickType(): JsonField<ClickType> = clickType

            @JsonProperty("coordinates")
            @ExcludeMissing
            fun _coordinates(): JsonField<List<Double>> = coordinates

            @JsonProperty("hold_keys")
            @ExcludeMissing
            fun _holdKeys(): JsonField<List<String>> = holdKeys

            @JsonProperty("num_clicks")
            @ExcludeMissing
            fun _numClicks(): JsonField<Double> = numClicks

            @JsonProperty("screenshot")
            @ExcludeMissing
            fun _screenshot(): JsonField<Boolean> = screenshot

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

                /** Returns a mutable builder for constructing an instance of [ClickMouse]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [ClickMouse]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("click_mouse")
                private var button: JsonField<Button> = JsonMissing.of()
                private var clickType: JsonField<ClickType> = JsonMissing.of()
                private var coordinates: JsonField<MutableList<Double>>? = null
                private var holdKeys: JsonField<MutableList<String>>? = null
                private var numClicks: JsonField<Double> = JsonMissing.of()
                private var screenshot: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(clickMouse: ClickMouse) = apply {
                    this.action = clickMouse.action
                    this.button = clickMouse.button
                    this.clickType = clickMouse.clickType
                    this.coordinates = clickMouse.coordinates.map { it.toMutableList() }
                    this.holdKeys = clickMouse.holdKeys.map { it.toMutableList() }
                    this.numClicks = clickMouse.numClicks
                    this.screenshot = clickMouse.screenshot
                    this.additionalProperties = clickMouse.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

                /** Mouse button to click. Defaults to 'left' */
                fun button(button: Button) = button(JsonField.of(button))

                /** Mouse button to click. Defaults to 'left' */
                fun button(button: JsonField<Button>) = apply { this.button = button }

                /** Type of click (down, up, or click). Defaults to 'click' */
                fun clickType(clickType: ClickType) = clickType(JsonField.of(clickType))

                /** Type of click (down, up, or click). Defaults to 'click' */
                fun clickType(clickType: JsonField<ClickType>) = apply {
                    this.clickType = clickType
                }

                /** X and Y coordinates [x, y] */
                fun coordinates(coordinates: List<Double>) = coordinates(JsonField.of(coordinates))

                /** X and Y coordinates [x, y] */
                fun coordinates(coordinates: JsonField<List<Double>>) = apply {
                    this.coordinates = coordinates.map { it.toMutableList() }
                }

                /** X and Y coordinates [x, y] */
                fun addCoordinate(coordinate: Double) = apply {
                    this.coordinates =
                        (this.coordinates ?: JsonField.of(mutableListOf())).also {
                            checkKnown("coordinates", it).add(coordinate)
                        }
                }

                /** Keys to hold while clicking */
                fun holdKeys(holdKeys: List<String>) = holdKeys(JsonField.of(holdKeys))

                /** Keys to hold while clicking */
                fun holdKeys(holdKeys: JsonField<List<String>>) = apply {
                    this.holdKeys = holdKeys.map { it.toMutableList() }
                }

                /** Keys to hold while clicking */
                fun addHoldKey(holdKey: String) = apply {
                    this.holdKeys =
                        (this.holdKeys ?: JsonField.of(mutableListOf())).also {
                            checkKnown("hold_keys", it).add(holdKey)
                        }
                }

                /** Number of clicks. Defaults to 1 */
                fun numClicks(numClicks: Double) = numClicks(JsonField.of(numClicks))

                /** Number of clicks. Defaults to 1 */
                fun numClicks(numClicks: JsonField<Double>) = apply { this.numClicks = numClicks }

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: Boolean) = screenshot(JsonField.of(screenshot))

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: JsonField<Boolean>) = apply {
                    this.screenshot = screenshot
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

                fun build(): ClickMouse =
                    ClickMouse(
                        action,
                        button,
                        clickType,
                        (coordinates ?: JsonMissing.of()).map { it.toImmutable() },
                        (holdKeys ?: JsonMissing.of()).map { it.toImmutable() },
                        numClicks,
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

            fun validate(): ClickMouse = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("click_mouse")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                button().ifPresent { it.validate() }
                clickType().ifPresent { it.validate() }
                coordinates()
                holdKeys()
                numClicks()
                screenshot()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("click_mouse")) 1 else 0 }) +
                    (button.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (clickType.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (coordinates.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (holdKeys.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (numClicks.asKnown().isPresent) 1 else 0) +
                    (if (screenshot.asKnown().isPresent) 1 else 0)

            class Button @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val LEFT = of("left")

                    @JvmField val RIGHT = of("right")

                    @JvmField val MIDDLE = of("middle")

                    @JvmField val BACK = of("back")

                    @JvmField val FORWARD = of("forward")

                    @JvmStatic fun of(value: String) = Button(JsonField.of(value))
                }

                enum class Known {
                    LEFT,
                    RIGHT,
                    MIDDLE,
                    BACK,
                    FORWARD,
                }

                enum class Value {
                    LEFT,
                    RIGHT,
                    MIDDLE,
                    BACK,
                    FORWARD,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        LEFT -> Value.LEFT
                        RIGHT -> Value.RIGHT
                        MIDDLE -> Value.MIDDLE
                        BACK -> Value.BACK
                        FORWARD -> Value.FORWARD
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        LEFT -> Known.LEFT
                        RIGHT -> Known.RIGHT
                        MIDDLE -> Known.MIDDLE
                        BACK -> Known.BACK
                        FORWARD -> Known.FORWARD
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

            class ClickType @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val DOWN = of("down")

                    @JvmField val UP = of("up")

                    @JvmField val CLICK = of("click")

                    @JvmStatic fun of(value: String) = ClickType(JsonField.of(value))
                }

                enum class Known {
                    DOWN,
                    UP,
                    CLICK,
                }

                enum class Value {
                    DOWN,
                    UP,
                    CLICK,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        DOWN -> Value.DOWN
                        UP -> Value.UP
                        CLICK -> Value.CLICK
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        DOWN -> Known.DOWN
                        UP -> Known.UP
                        CLICK -> Known.CLICK
                        else -> throw SteelInvalidDataException("Unknown ClickType: $value")
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

                fun validate(): ClickType = apply {
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

                    return other is ClickType && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ClickMouse &&
                    this.action == other.action &&
                    this.button == other.button &&
                    this.clickType == other.clickType &&
                    this.coordinates == other.coordinates &&
                    this.holdKeys == other.holdKeys &&
                    this.numClicks == other.numClicks &&
                    this.screenshot == other.screenshot &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    action,
                    button,
                    clickType,
                    coordinates,
                    holdKeys,
                    numClicks,
                    screenshot,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "ClickMouse{action=$action, button=$button, clickType=$clickType, coordinates=$coordinates, holdKeys=$holdKeys, numClicks=$numClicks, screenshot=$screenshot, additionalProperties=$additionalProperties}"
        }

        class DragMouse
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val path: JsonField<List<List<Double>>>,
            private val holdKeys: JsonField<List<String>>,
            private val screenshot: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of(),
                @JsonProperty("path")
                @ExcludeMissing
                path: JsonField<List<List<Double>>> = JsonMissing.of(),
                @JsonProperty("hold_keys")
                @ExcludeMissing
                holdKeys: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("screenshot")
                @ExcludeMissing
                screenshot: JsonField<Boolean> = JsonMissing.of(),
            ) : this(action, path, holdKeys, screenshot, mutableMapOf())

            /** Array of [x, y] coordinate pairs */
            fun path(): List<List<Double>> = path.getRequired("path")

            /** Keys to hold while dragging */
            fun holdKeys(): Optional<List<String>> = holdKeys.getOptional("hold_keys")

            /** Whether to take a screenshot after the action */
            fun screenshot(): Optional<Boolean> = screenshot.getOptional("screenshot")

            /** Expected to always return `JsonValue.from("drag_mouse")`. It is set by default. */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

            @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<List<List<Double>>> = path

            @JsonProperty("hold_keys")
            @ExcludeMissing
            fun _holdKeys(): JsonField<List<String>> = holdKeys

            @JsonProperty("screenshot")
            @ExcludeMissing
            fun _screenshot(): JsonField<Boolean> = screenshot

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

                /** Returns a mutable builder for constructing an instance of [DragMouse]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [DragMouse]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("drag_mouse")
                private var path: JsonField<MutableList<List<Double>>>? = null
                private var holdKeys: JsonField<MutableList<String>>? = null
                private var screenshot: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(dragMouse: DragMouse) = apply {
                    this.action = dragMouse.action
                    this.path = dragMouse.path.map { it.toMutableList() }
                    this.holdKeys = dragMouse.holdKeys.map { it.toMutableList() }
                    this.screenshot = dragMouse.screenshot
                    this.additionalProperties = dragMouse.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

                /** Array of [x, y] coordinate pairs */
                fun path(path: List<List<Double>>) = path(JsonField.of(path))

                /** Array of [x, y] coordinate pairs */
                fun path(path: JsonField<List<List<Double>>>) = apply {
                    this.path = path.map { it.toMutableList() }
                }

                /** Array of [x, y] coordinate pairs */
                fun addPath(path: List<Double>) = apply {
                    this.path =
                        (this.path ?: JsonField.of(mutableListOf())).also {
                            checkKnown("path", it).add(path)
                        }
                }

                /** Keys to hold while dragging */
                fun holdKeys(holdKeys: List<String>) = holdKeys(JsonField.of(holdKeys))

                /** Keys to hold while dragging */
                fun holdKeys(holdKeys: JsonField<List<String>>) = apply {
                    this.holdKeys = holdKeys.map { it.toMutableList() }
                }

                /** Keys to hold while dragging */
                fun addHoldKey(holdKey: String) = apply {
                    this.holdKeys =
                        (this.holdKeys ?: JsonField.of(mutableListOf())).also {
                            checkKnown("hold_keys", it).add(holdKey)
                        }
                }

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: Boolean) = screenshot(JsonField.of(screenshot))

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: JsonField<Boolean>) = apply {
                    this.screenshot = screenshot
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

                fun build(): DragMouse =
                    DragMouse(
                        action,
                        checkRequired("path", path).map { it.toImmutable() },
                        (holdKeys ?: JsonMissing.of()).map { it.toImmutable() },
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

            fun validate(): DragMouse = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("drag_mouse")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                path()
                holdKeys()
                screenshot()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("drag_mouse")) 1 else 0 }) +
                    (path.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (holdKeys.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (screenshot.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is DragMouse &&
                    this.action == other.action &&
                    this.path == other.path &&
                    this.holdKeys == other.holdKeys &&
                    this.screenshot == other.screenshot &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(action, path, holdKeys, screenshot, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "DragMouse{action=$action, path=$path, holdKeys=$holdKeys, screenshot=$screenshot, additionalProperties=$additionalProperties}"
        }

        class Scroll
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val coordinates: JsonField<List<Double>>,
            private val deltaX: JsonField<Double>,
            private val deltaY: JsonField<Double>,
            private val holdKeys: JsonField<List<String>>,
            private val screenshot: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of(),
                @JsonProperty("coordinates")
                @ExcludeMissing
                coordinates: JsonField<List<Double>> = JsonMissing.of(),
                @JsonProperty("delta_x")
                @ExcludeMissing
                deltaX: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("delta_y")
                @ExcludeMissing
                deltaY: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("hold_keys")
                @ExcludeMissing
                holdKeys: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("screenshot")
                @ExcludeMissing
                screenshot: JsonField<Boolean> = JsonMissing.of(),
            ) : this(action, coordinates, deltaX, deltaY, holdKeys, screenshot, mutableMapOf())

            /** X and Y coordinates [x, y] */
            fun coordinates(): Optional<List<Double>> = coordinates.getOptional("coordinates")

            /** Horizontal scroll amount. Defaults to 0 */
            fun deltaX(): Optional<Double> = deltaX.getOptional("delta_x")

            /** Vertical scroll amount. Defaults to 0 */
            fun deltaY(): Optional<Double> = deltaY.getOptional("delta_y")

            /** Keys to hold while scrolling */
            fun holdKeys(): Optional<List<String>> = holdKeys.getOptional("hold_keys")

            /** Whether to take a screenshot after the action */
            fun screenshot(): Optional<Boolean> = screenshot.getOptional("screenshot")

            /** Expected to always return `JsonValue.from("scroll")`. It is set by default. */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

            @JsonProperty("coordinates")
            @ExcludeMissing
            fun _coordinates(): JsonField<List<Double>> = coordinates

            @JsonProperty("delta_x") @ExcludeMissing fun _deltaX(): JsonField<Double> = deltaX

            @JsonProperty("delta_y") @ExcludeMissing fun _deltaY(): JsonField<Double> = deltaY

            @JsonProperty("hold_keys")
            @ExcludeMissing
            fun _holdKeys(): JsonField<List<String>> = holdKeys

            @JsonProperty("screenshot")
            @ExcludeMissing
            fun _screenshot(): JsonField<Boolean> = screenshot

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

                /** Returns a mutable builder for constructing an instance of [Scroll]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Scroll]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("scroll")
                private var coordinates: JsonField<MutableList<Double>>? = null
                private var deltaX: JsonField<Double> = JsonMissing.of()
                private var deltaY: JsonField<Double> = JsonMissing.of()
                private var holdKeys: JsonField<MutableList<String>>? = null
                private var screenshot: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(scroll: Scroll) = apply {
                    this.action = scroll.action
                    this.coordinates = scroll.coordinates.map { it.toMutableList() }
                    this.deltaX = scroll.deltaX
                    this.deltaY = scroll.deltaY
                    this.holdKeys = scroll.holdKeys.map { it.toMutableList() }
                    this.screenshot = scroll.screenshot
                    this.additionalProperties = scroll.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

                /** X and Y coordinates [x, y] */
                fun coordinates(coordinates: List<Double>) = coordinates(JsonField.of(coordinates))

                /** X and Y coordinates [x, y] */
                fun coordinates(coordinates: JsonField<List<Double>>) = apply {
                    this.coordinates = coordinates.map { it.toMutableList() }
                }

                /** X and Y coordinates [x, y] */
                fun addCoordinate(coordinate: Double) = apply {
                    this.coordinates =
                        (this.coordinates ?: JsonField.of(mutableListOf())).also {
                            checkKnown("coordinates", it).add(coordinate)
                        }
                }

                /** Horizontal scroll amount. Defaults to 0 */
                fun deltaX(deltaX: Double) = deltaX(JsonField.of(deltaX))

                /** Horizontal scroll amount. Defaults to 0 */
                fun deltaX(deltaX: JsonField<Double>) = apply { this.deltaX = deltaX }

                /** Vertical scroll amount. Defaults to 0 */
                fun deltaY(deltaY: Double) = deltaY(JsonField.of(deltaY))

                /** Vertical scroll amount. Defaults to 0 */
                fun deltaY(deltaY: JsonField<Double>) = apply { this.deltaY = deltaY }

                /** Keys to hold while scrolling */
                fun holdKeys(holdKeys: List<String>) = holdKeys(JsonField.of(holdKeys))

                /** Keys to hold while scrolling */
                fun holdKeys(holdKeys: JsonField<List<String>>) = apply {
                    this.holdKeys = holdKeys.map { it.toMutableList() }
                }

                /** Keys to hold while scrolling */
                fun addHoldKey(holdKey: String) = apply {
                    this.holdKeys =
                        (this.holdKeys ?: JsonField.of(mutableListOf())).also {
                            checkKnown("hold_keys", it).add(holdKey)
                        }
                }

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: Boolean) = screenshot(JsonField.of(screenshot))

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: JsonField<Boolean>) = apply {
                    this.screenshot = screenshot
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

                fun build(): Scroll =
                    Scroll(
                        action,
                        (coordinates ?: JsonMissing.of()).map { it.toImmutable() },
                        deltaX,
                        deltaY,
                        (holdKeys ?: JsonMissing.of()).map { it.toImmutable() },
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

            fun validate(): Scroll = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("scroll")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                coordinates()
                deltaX()
                deltaY()
                holdKeys()
                screenshot()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("scroll")) 1 else 0 }) +
                    (coordinates.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (deltaX.asKnown().isPresent) 1 else 0) +
                    (if (deltaY.asKnown().isPresent) 1 else 0) +
                    (holdKeys.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (screenshot.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Scroll &&
                    this.action == other.action &&
                    this.coordinates == other.coordinates &&
                    this.deltaX == other.deltaX &&
                    this.deltaY == other.deltaY &&
                    this.holdKeys == other.holdKeys &&
                    this.screenshot == other.screenshot &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    action,
                    coordinates,
                    deltaX,
                    deltaY,
                    holdKeys,
                    screenshot,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Scroll{action=$action, coordinates=$coordinates, deltaX=$deltaX, deltaY=$deltaY, holdKeys=$holdKeys, screenshot=$screenshot, additionalProperties=$additionalProperties}"
        }

        class PressKey
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val keys: JsonField<List<String>>,
            private val duration: JsonField<Double>,
            private val screenshot: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of(),
                @JsonProperty("keys")
                @ExcludeMissing
                keys: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("duration")
                @ExcludeMissing
                duration: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("screenshot")
                @ExcludeMissing
                screenshot: JsonField<Boolean> = JsonMissing.of(),
            ) : this(action, keys, duration, screenshot, mutableMapOf())

            /** Keys to press */
            fun keys(): List<String> = keys.getRequired("keys")

            /** Duration to hold keys in seconds */
            fun duration(): Optional<Double> = duration.getOptional("duration")

            /** Whether to take a screenshot after the action */
            fun screenshot(): Optional<Boolean> = screenshot.getOptional("screenshot")

            /** Expected to always return `JsonValue.from("press_key")`. It is set by default. */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

            @JsonProperty("keys") @ExcludeMissing fun _keys(): JsonField<List<String>> = keys

            @JsonProperty("duration") @ExcludeMissing fun _duration(): JsonField<Double> = duration

            @JsonProperty("screenshot")
            @ExcludeMissing
            fun _screenshot(): JsonField<Boolean> = screenshot

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

                /** Returns a mutable builder for constructing an instance of [PressKey]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [PressKey]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("press_key")
                private var keys: JsonField<MutableList<String>>? = null
                private var duration: JsonField<Double> = JsonMissing.of()
                private var screenshot: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(pressKey: PressKey) = apply {
                    this.action = pressKey.action
                    this.keys = pressKey.keys.map { it.toMutableList() }
                    this.duration = pressKey.duration
                    this.screenshot = pressKey.screenshot
                    this.additionalProperties = pressKey.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

                /** Keys to press */
                fun keys(keys: List<String>) = keys(JsonField.of(keys))

                /** Keys to press */
                fun keys(keys: JsonField<List<String>>) = apply {
                    this.keys = keys.map { it.toMutableList() }
                }

                /** Keys to press */
                fun addKey(key: String) = apply {
                    this.keys =
                        (this.keys ?: JsonField.of(mutableListOf())).also {
                            checkKnown("keys", it).add(key)
                        }
                }

                /** Duration to hold keys in seconds */
                fun duration(duration: Double) = duration(JsonField.of(duration))

                /** Duration to hold keys in seconds */
                fun duration(duration: JsonField<Double>) = apply { this.duration = duration }

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: Boolean) = screenshot(JsonField.of(screenshot))

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: JsonField<Boolean>) = apply {
                    this.screenshot = screenshot
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

                fun build(): PressKey =
                    PressKey(
                        action,
                        checkRequired("keys", keys).map { it.toImmutable() },
                        duration,
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

            fun validate(): PressKey = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("press_key")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                keys()
                duration()
                screenshot()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("press_key")) 1 else 0 }) +
                    (keys.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (duration.asKnown().isPresent) 1 else 0) +
                    (if (screenshot.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is PressKey &&
                    this.action == other.action &&
                    this.keys == other.keys &&
                    this.duration == other.duration &&
                    this.screenshot == other.screenshot &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(action, keys, duration, screenshot, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "PressKey{action=$action, keys=$keys, duration=$duration, screenshot=$screenshot, additionalProperties=$additionalProperties}"
        }

        class TypeText
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val text: JsonField<String>,
            private val holdKeys: JsonField<List<String>>,
            private val screenshot: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
                @JsonProperty("hold_keys")
                @ExcludeMissing
                holdKeys: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("screenshot")
                @ExcludeMissing
                screenshot: JsonField<Boolean> = JsonMissing.of(),
            ) : this(action, text, holdKeys, screenshot, mutableMapOf())

            /** Text to type */
            fun text(): String = text.getRequired("text")

            /** Keys to hold while typing */
            fun holdKeys(): Optional<List<String>> = holdKeys.getOptional("hold_keys")

            /** Whether to take a screenshot after the action */
            fun screenshot(): Optional<Boolean> = screenshot.getOptional("screenshot")

            /** Expected to always return `JsonValue.from("type_text")`. It is set by default. */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonProperty("hold_keys")
            @ExcludeMissing
            fun _holdKeys(): JsonField<List<String>> = holdKeys

            @JsonProperty("screenshot")
            @ExcludeMissing
            fun _screenshot(): JsonField<Boolean> = screenshot

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

                /** Returns a mutable builder for constructing an instance of [TypeText]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [TypeText]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("type_text")
                private var text: JsonField<String>? = null
                private var holdKeys: JsonField<MutableList<String>>? = null
                private var screenshot: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(typeText: TypeText) = apply {
                    this.action = typeText.action
                    this.text = typeText.text
                    this.holdKeys = typeText.holdKeys.map { it.toMutableList() }
                    this.screenshot = typeText.screenshot
                    this.additionalProperties = typeText.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

                /** Text to type */
                fun text(text: String) = text(JsonField.of(text))

                /** Text to type */
                fun text(text: JsonField<String>) = apply { this.text = text }

                /** Keys to hold while typing */
                fun holdKeys(holdKeys: List<String>) = holdKeys(JsonField.of(holdKeys))

                /** Keys to hold while typing */
                fun holdKeys(holdKeys: JsonField<List<String>>) = apply {
                    this.holdKeys = holdKeys.map { it.toMutableList() }
                }

                /** Keys to hold while typing */
                fun addHoldKey(holdKey: String) = apply {
                    this.holdKeys =
                        (this.holdKeys ?: JsonField.of(mutableListOf())).also {
                            checkKnown("hold_keys", it).add(holdKey)
                        }
                }

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: Boolean) = screenshot(JsonField.of(screenshot))

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: JsonField<Boolean>) = apply {
                    this.screenshot = screenshot
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

                fun build(): TypeText =
                    TypeText(
                        action,
                        checkRequired("text", text),
                        (holdKeys ?: JsonMissing.of()).map { it.toImmutable() },
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

            fun validate(): TypeText = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("type_text")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                text()
                holdKeys()
                screenshot()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("type_text")) 1 else 0 }) +
                    (if (text.asKnown().isPresent) 1 else 0) +
                    (holdKeys.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (screenshot.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is TypeText &&
                    this.action == other.action &&
                    this.text == other.text &&
                    this.holdKeys == other.holdKeys &&
                    this.screenshot == other.screenshot &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(action, text, holdKeys, screenshot, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "TypeText{action=$action, text=$text, holdKeys=$holdKeys, screenshot=$screenshot, additionalProperties=$additionalProperties}"
        }

        class Wait
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val duration: JsonField<Double>,
            private val screenshot: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of(),
                @JsonProperty("duration")
                @ExcludeMissing
                duration: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("screenshot")
                @ExcludeMissing
                screenshot: JsonField<Boolean> = JsonMissing.of(),
            ) : this(action, duration, screenshot, mutableMapOf())

            /** Duration to wait in seconds */
            fun duration(): Double = duration.getRequired("duration")

            /** Whether to take a screenshot after the action */
            fun screenshot(): Optional<Boolean> = screenshot.getOptional("screenshot")

            /** Expected to always return `JsonValue.from("wait")`. It is set by default. */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

            @JsonProperty("duration") @ExcludeMissing fun _duration(): JsonField<Double> = duration

            @JsonProperty("screenshot")
            @ExcludeMissing
            fun _screenshot(): JsonField<Boolean> = screenshot

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

                /** Returns a mutable builder for constructing an instance of [Wait]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Wait]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("wait")
                private var duration: JsonField<Double>? = null
                private var screenshot: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(wait_: Wait) = apply {
                    this.action = wait_.action
                    this.duration = wait_.duration
                    this.screenshot = wait_.screenshot
                    this.additionalProperties = wait_.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

                /** Duration to wait in seconds */
                fun duration(duration: Double) = duration(JsonField.of(duration))

                /** Duration to wait in seconds */
                fun duration(duration: JsonField<Double>) = apply { this.duration = duration }

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: Boolean) = screenshot(JsonField.of(screenshot))

                /** Whether to take a screenshot after the action */
                fun screenshot(screenshot: JsonField<Boolean>) = apply {
                    this.screenshot = screenshot
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

                fun build(): Wait =
                    Wait(
                        action,
                        checkRequired("duration", duration),
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

            fun validate(): Wait = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("wait")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                duration()
                screenshot()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("wait")) 1 else 0 }) +
                    (if (duration.asKnown().isPresent) 1 else 0) +
                    (if (screenshot.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Wait &&
                    this.action == other.action &&
                    this.duration == other.duration &&
                    this.screenshot == other.screenshot &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(action, duration, screenshot, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Wait{action=$action, duration=$duration, screenshot=$screenshot, additionalProperties=$additionalProperties}"
        }

        class TakeScreenshot
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of()
            ) : this(action, mutableMapOf())

            /**
             * Expected to always return `JsonValue.from("take_screenshot")`. It is set by default.
             */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

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

                /** Returns a mutable builder for constructing an instance of [TakeScreenshot]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [TakeScreenshot]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("take_screenshot")
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(takeScreenshot: TakeScreenshot) = apply {
                    this.action = takeScreenshot.action
                    this.additionalProperties = takeScreenshot.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

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

                fun build(): TakeScreenshot =
                    TakeScreenshot(action, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): TakeScreenshot = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("take_screenshot")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("take_screenshot")) 1 else 0 })

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is TakeScreenshot &&
                    this.action == other.action &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(action, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "TakeScreenshot{action=$action, additionalProperties=$additionalProperties}"
        }

        class GetCursorPosition
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val action: JsonValue,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action") @ExcludeMissing action: JsonValue = JsonMissing.of()
            ) : this(action, mutableMapOf())

            /**
             * Expected to always return `JsonValue.from("get_cursor_position")`. It is set by
             * default.
             */
            @JsonProperty("action") @ExcludeMissing fun _action(): JsonValue = action

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
                 * Returns a mutable builder for constructing an instance of [GetCursorPosition].
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [GetCursorPosition]. */
            class Builder internal constructor() {

                private var action: JsonValue = JsonValue.from("get_cursor_position")
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(getCursorPosition: GetCursorPosition) = apply {
                    this.action = getCursorPosition.action
                    this.additionalProperties =
                        getCursorPosition.additionalProperties.toMutableMap()
                }

                fun action(action: JsonValue) = apply { this.action = action }

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

                fun build(): GetCursorPosition =
                    GetCursorPosition(action, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): GetCursorPosition = apply {
                if (validated) {
                    return@apply
                }

                _action().let {
                    if (it != JsonValue.from("get_cursor_position")) {
                        throw SteelInvalidDataException("'action' is invalid, received $it")
                    }
                }
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (action.let { if (it == JsonValue.from("get_cursor_position")) 1 else 0 })

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is GetCursorPosition &&
                    this.action == other.action &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(action, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "GetCursorPosition{action=$action, additionalProperties=$additionalProperties}"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionComputerParams &&
            this.sessionId == other.sessionId &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(sessionId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SessionComputerParams{sessionId=$sessionId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
