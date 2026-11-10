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
import dev.steel.api.core.allMaxBy
import dev.steel.api.core.checkRequired
import dev.steel.api.core.getOrThrow
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@JsonDeserialize(using = Command.Deserializer::class)
@JsonSerialize(using = Command.Serializer::class)
class Command
private constructor(
    private val navigate: Navigate? = null,
    private val back: Back? = null,
    private val forward: Forward? = null,
    private val reload: Reload? = null,
    private val snapshot: Snapshot? = null,
    private val click: Click? = null,
    private val dblclick: Dblclick? = null,
    private val hover: Hover? = null,
    private val focus: Focus? = null,
    private val type: Type? = null,
    private val fill: Fill? = null,
    private val press: Press? = null,
    private val keydown: Keydown? = null,
    private val keyup: Keyup? = null,
    private val mouse: Mouse? = null,
    private val select: Select? = null,
    private val check: Check? = null,
    private val upload: Upload? = null,
    private val drag: Drag? = null,
    private val uncheck: Uncheck? = null,
    private val get: Get? = null,
    private val is_: Is? = null,
    private val wait_: Wait? = null,
    private val screenshot: Screenshot? = null,
    private val eval: Eval? = null,
    private val pdf: Pdf? = null,
    private val scroll: Scroll? = null,
    private val find: Find? = null,
    private val tab: Tab? = null,
    private val _json: JsonValue? = null,
) {

    fun navigate(): Optional<Navigate> = Optional.ofNullable(navigate)

    fun back(): Optional<Back> = Optional.ofNullable(back)

    fun forward(): Optional<Forward> = Optional.ofNullable(forward)

    fun reload(): Optional<Reload> = Optional.ofNullable(reload)

    fun snapshot(): Optional<Snapshot> = Optional.ofNullable(snapshot)

    fun click(): Optional<Click> = Optional.ofNullable(click)

    fun dblclick(): Optional<Dblclick> = Optional.ofNullable(dblclick)

    fun hover(): Optional<Hover> = Optional.ofNullable(hover)

    fun focus(): Optional<Focus> = Optional.ofNullable(focus)

    fun type(): Optional<Type> = Optional.ofNullable(type)

    fun fill(): Optional<Fill> = Optional.ofNullable(fill)

    fun press(): Optional<Press> = Optional.ofNullable(press)

    fun keydown(): Optional<Keydown> = Optional.ofNullable(keydown)

    fun keyup(): Optional<Keyup> = Optional.ofNullable(keyup)

    fun mouse(): Optional<Mouse> = Optional.ofNullable(mouse)

    fun select(): Optional<Select> = Optional.ofNullable(select)

    fun check(): Optional<Check> = Optional.ofNullable(check)

    fun upload(): Optional<Upload> = Optional.ofNullable(upload)

    fun drag(): Optional<Drag> = Optional.ofNullable(drag)

    fun uncheck(): Optional<Uncheck> = Optional.ofNullable(uncheck)

    fun get(): Optional<Get> = Optional.ofNullable(get)

    fun is_(): Optional<Is> = Optional.ofNullable(is_)

    fun wait_(): Optional<Wait> = Optional.ofNullable(wait_)

    fun screenshot(): Optional<Screenshot> = Optional.ofNullable(screenshot)

    fun eval(): Optional<Eval> = Optional.ofNullable(eval)

    fun pdf(): Optional<Pdf> = Optional.ofNullable(pdf)

    fun scroll(): Optional<Scroll> = Optional.ofNullable(scroll)

    fun find(): Optional<Find> = Optional.ofNullable(find)

    fun tab(): Optional<Tab> = Optional.ofNullable(tab)

    fun isNavigate(): Boolean = navigate != null

    fun isBack(): Boolean = back != null

    fun isForward(): Boolean = forward != null

    fun isReload(): Boolean = reload != null

    fun isSnapshot(): Boolean = snapshot != null

    fun isClick(): Boolean = click != null

    fun isDblclick(): Boolean = dblclick != null

    fun isHover(): Boolean = hover != null

    fun isFocus(): Boolean = focus != null

    fun isType(): Boolean = type != null

    fun isFill(): Boolean = fill != null

    fun isPress(): Boolean = press != null

    fun isKeydown(): Boolean = keydown != null

    fun isKeyup(): Boolean = keyup != null

    fun isMouse(): Boolean = mouse != null

    fun isSelect(): Boolean = select != null

    fun isCheck(): Boolean = check != null

    fun isUpload(): Boolean = upload != null

    fun isDrag(): Boolean = drag != null

    fun isUncheck(): Boolean = uncheck != null

    fun isGet(): Boolean = get != null

    fun isIs(): Boolean = is_ != null

    fun isWait(): Boolean = wait_ != null

    fun isScreenshot(): Boolean = screenshot != null

    fun isEval(): Boolean = eval != null

    fun isPdf(): Boolean = pdf != null

    fun isScroll(): Boolean = scroll != null

    fun isFind(): Boolean = find != null

    fun isTab(): Boolean = tab != null

    fun asNavigate(): Navigate = navigate.getOrThrow("navigate")

    fun asBack(): Back = back.getOrThrow("back")

    fun asForward(): Forward = forward.getOrThrow("forward")

    fun asReload(): Reload = reload.getOrThrow("reload")

    fun asSnapshot(): Snapshot = snapshot.getOrThrow("snapshot")

    fun asClick(): Click = click.getOrThrow("click")

    fun asDblclick(): Dblclick = dblclick.getOrThrow("dblclick")

    fun asHover(): Hover = hover.getOrThrow("hover")

    fun asFocus(): Focus = focus.getOrThrow("focus")

    fun asType(): Type = type.getOrThrow("type")

    fun asFill(): Fill = fill.getOrThrow("fill")

    fun asPress(): Press = press.getOrThrow("press")

    fun asKeydown(): Keydown = keydown.getOrThrow("keydown")

    fun asKeyup(): Keyup = keyup.getOrThrow("keyup")

    fun asMouse(): Mouse = mouse.getOrThrow("mouse")

    fun asSelect(): Select = select.getOrThrow("select")

    fun asCheck(): Check = check.getOrThrow("check")

    fun asUpload(): Upload = upload.getOrThrow("upload")

    fun asDrag(): Drag = drag.getOrThrow("drag")

    fun asUncheck(): Uncheck = uncheck.getOrThrow("uncheck")

    fun asGet(): Get = get.getOrThrow("get")

    fun asIs(): Is = is_.getOrThrow("is_")

    fun asWait(): Wait = wait_.getOrThrow("wait_")

    fun asScreenshot(): Screenshot = screenshot.getOrThrow("screenshot")

    fun asEval(): Eval = eval.getOrThrow("eval")

    fun asPdf(): Pdf = pdf.getOrThrow("pdf")

    fun asScroll(): Scroll = scroll.getOrThrow("scroll")

    fun asFind(): Find = find.getOrThrow("find")

    fun asTab(): Tab = tab.getOrThrow("tab")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            navigate != null -> visitor.visitNavigate(navigate)
            back != null -> visitor.visitBack(back)
            forward != null -> visitor.visitForward(forward)
            reload != null -> visitor.visitReload(reload)
            snapshot != null -> visitor.visitSnapshot(snapshot)
            click != null -> visitor.visitClick(click)
            dblclick != null -> visitor.visitDblclick(dblclick)
            hover != null -> visitor.visitHover(hover)
            focus != null -> visitor.visitFocus(focus)
            type != null -> visitor.visitType(type)
            fill != null -> visitor.visitFill(fill)
            press != null -> visitor.visitPress(press)
            keydown != null -> visitor.visitKeydown(keydown)
            keyup != null -> visitor.visitKeyup(keyup)
            mouse != null -> visitor.visitMouse(mouse)
            select != null -> visitor.visitSelect(select)
            check != null -> visitor.visitCheck(check)
            upload != null -> visitor.visitUpload(upload)
            drag != null -> visitor.visitDrag(drag)
            uncheck != null -> visitor.visitUncheck(uncheck)
            get != null -> visitor.visitGet(get)
            is_ != null -> visitor.visitIs(is_)
            wait_ != null -> visitor.visitWait(wait_)
            screenshot != null -> visitor.visitScreenshot(screenshot)
            eval != null -> visitor.visitEval(eval)
            pdf != null -> visitor.visitPdf(pdf)
            scroll != null -> visitor.visitScroll(scroll)
            find != null -> visitor.visitFind(find)
            tab != null -> visitor.visitTab(tab)
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

    fun validate(): Command = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitNavigate(navigate: Navigate) {
                    navigate.validate()
                }

                override fun visitBack(back: Back) {
                    back.validate()
                }

                override fun visitForward(forward: Forward) {
                    forward.validate()
                }

                override fun visitReload(reload: Reload) {
                    reload.validate()
                }

                override fun visitSnapshot(snapshot: Snapshot) {
                    snapshot.validate()
                }

                override fun visitClick(click: Click) {
                    click.validate()
                }

                override fun visitDblclick(dblclick: Dblclick) {
                    dblclick.validate()
                }

                override fun visitHover(hover: Hover) {
                    hover.validate()
                }

                override fun visitFocus(focus: Focus) {
                    focus.validate()
                }

                override fun visitType(type: Type) {
                    type.validate()
                }

                override fun visitFill(fill: Fill) {
                    fill.validate()
                }

                override fun visitPress(press: Press) {
                    press.validate()
                }

                override fun visitKeydown(keydown: Keydown) {
                    keydown.validate()
                }

                override fun visitKeyup(keyup: Keyup) {
                    keyup.validate()
                }

                override fun visitMouse(mouse: Mouse) {
                    mouse.validate()
                }

                override fun visitSelect(select: Select) {
                    select.validate()
                }

                override fun visitCheck(check: Check) {
                    check.validate()
                }

                override fun visitUpload(upload: Upload) {
                    upload.validate()
                }

                override fun visitDrag(drag: Drag) {
                    drag.validate()
                }

                override fun visitUncheck(uncheck: Uncheck) {
                    uncheck.validate()
                }

                override fun visitGet(get: Get) {
                    get.validate()
                }

                override fun visitIs(is_: Is) {
                    is_.validate()
                }

                override fun visitWait(wait_: Wait) {
                    wait_.validate()
                }

                override fun visitScreenshot(screenshot: Screenshot) {
                    screenshot.validate()
                }

                override fun visitEval(eval: Eval) {
                    eval.validate()
                }

                override fun visitPdf(pdf: Pdf) {
                    pdf.validate()
                }

                override fun visitScroll(scroll: Scroll) {
                    scroll.validate()
                }

                override fun visitFind(find: Find) {
                    find.validate()
                }

                override fun visitTab(tab: Tab) {
                    tab.validate()
                }
            }
        )
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitNavigate(navigate: Navigate) = navigate.validity()

                override fun visitBack(back: Back) = back.validity()

                override fun visitForward(forward: Forward) = forward.validity()

                override fun visitReload(reload: Reload) = reload.validity()

                override fun visitSnapshot(snapshot: Snapshot) = snapshot.validity()

                override fun visitClick(click: Click) = click.validity()

                override fun visitDblclick(dblclick: Dblclick) = dblclick.validity()

                override fun visitHover(hover: Hover) = hover.validity()

                override fun visitFocus(focus: Focus) = focus.validity()

                override fun visitType(type: Type) = type.validity()

                override fun visitFill(fill: Fill) = fill.validity()

                override fun visitPress(press: Press) = press.validity()

                override fun visitKeydown(keydown: Keydown) = keydown.validity()

                override fun visitKeyup(keyup: Keyup) = keyup.validity()

                override fun visitMouse(mouse: Mouse) = mouse.validity()

                override fun visitSelect(select: Select) = select.validity()

                override fun visitCheck(check: Check) = check.validity()

                override fun visitUpload(upload: Upload) = upload.validity()

                override fun visitDrag(drag: Drag) = drag.validity()

                override fun visitUncheck(uncheck: Uncheck) = uncheck.validity()

                override fun visitGet(get: Get) = get.validity()

                override fun visitIs(is_: Is) = is_.validity()

                override fun visitWait(wait_: Wait) = wait_.validity()

                override fun visitScreenshot(screenshot: Screenshot) = screenshot.validity()

                override fun visitEval(eval: Eval) = eval.validity()

                override fun visitPdf(pdf: Pdf) = pdf.validity()

                override fun visitScroll(scroll: Scroll) = scroll.validity()

                override fun visitFind(find: Find) = find.validity()

                override fun visitTab(tab: Tab) = tab.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Command &&
            this.navigate == other.navigate &&
            this.back == other.back &&
            this.forward == other.forward &&
            this.reload == other.reload &&
            this.snapshot == other.snapshot &&
            this.click == other.click &&
            this.dblclick == other.dblclick &&
            this.hover == other.hover &&
            this.focus == other.focus &&
            this.type == other.type &&
            this.fill == other.fill &&
            this.press == other.press &&
            this.keydown == other.keydown &&
            this.keyup == other.keyup &&
            this.mouse == other.mouse &&
            this.select == other.select &&
            this.check == other.check &&
            this.upload == other.upload &&
            this.drag == other.drag &&
            this.uncheck == other.uncheck &&
            this.get == other.get &&
            this.is_ == other.is_ &&
            this.wait_ == other.wait_ &&
            this.screenshot == other.screenshot &&
            this.eval == other.eval &&
            this.pdf == other.pdf &&
            this.scroll == other.scroll &&
            this.find == other.find &&
            this.tab == other.tab
    }

    override fun hashCode(): Int =
        Objects.hash(
            navigate,
            back,
            forward,
            reload,
            snapshot,
            click,
            dblclick,
            hover,
            focus,
            type,
            fill,
            press,
            keydown,
            keyup,
            mouse,
            select,
            check,
            upload,
            drag,
            uncheck,
            get,
            is_,
            wait_,
            screenshot,
            eval,
            pdf,
            scroll,
            find,
            tab,
        )

    override fun toString(): String =
        when {
            navigate != null -> "Command{navigate=$navigate}"
            back != null -> "Command{back=$back}"
            forward != null -> "Command{forward=$forward}"
            reload != null -> "Command{reload=$reload}"
            snapshot != null -> "Command{snapshot=$snapshot}"
            click != null -> "Command{click=$click}"
            dblclick != null -> "Command{dblclick=$dblclick}"
            hover != null -> "Command{hover=$hover}"
            focus != null -> "Command{focus=$focus}"
            type != null -> "Command{type=$type}"
            fill != null -> "Command{fill=$fill}"
            press != null -> "Command{press=$press}"
            keydown != null -> "Command{keydown=$keydown}"
            keyup != null -> "Command{keyup=$keyup}"
            mouse != null -> "Command{mouse=$mouse}"
            select != null -> "Command{select=$select}"
            check != null -> "Command{check=$check}"
            upload != null -> "Command{upload=$upload}"
            drag != null -> "Command{drag=$drag}"
            uncheck != null -> "Command{uncheck=$uncheck}"
            get != null -> "Command{get=$get}"
            is_ != null -> "Command{is_=$is_}"
            wait_ != null -> "Command{wait_=$wait_}"
            screenshot != null -> "Command{screenshot=$screenshot}"
            eval != null -> "Command{eval=$eval}"
            pdf != null -> "Command{pdf=$pdf}"
            scroll != null -> "Command{scroll=$scroll}"
            find != null -> "Command{find=$find}"
            tab != null -> "Command{tab=$tab}"
            _json != null -> "Command{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid Command")
        }

    companion object {

        @JvmStatic fun ofNavigate(navigate: Navigate) = Command(navigate = navigate)

        @JvmStatic fun ofBack(back: Back) = Command(back = back)

        @JvmStatic fun ofForward(forward: Forward) = Command(forward = forward)

        @JvmStatic fun ofReload(reload: Reload) = Command(reload = reload)

        @JvmStatic fun ofSnapshot(snapshot: Snapshot) = Command(snapshot = snapshot)

        @JvmStatic fun ofClick(click: Click) = Command(click = click)

        @JvmStatic fun ofDblclick(dblclick: Dblclick) = Command(dblclick = dblclick)

        @JvmStatic fun ofHover(hover: Hover) = Command(hover = hover)

        @JvmStatic fun ofFocus(focus: Focus) = Command(focus = focus)

        @JvmStatic fun ofType(type: Type) = Command(type = type)

        @JvmStatic fun ofFill(fill: Fill) = Command(fill = fill)

        @JvmStatic fun ofPress(press: Press) = Command(press = press)

        @JvmStatic fun ofKeydown(keydown: Keydown) = Command(keydown = keydown)

        @JvmStatic fun ofKeyup(keyup: Keyup) = Command(keyup = keyup)

        @JvmStatic fun ofMouse(mouse: Mouse) = Command(mouse = mouse)

        @JvmStatic fun ofSelect(select: Select) = Command(select = select)

        @JvmStatic fun ofCheck(check: Check) = Command(check = check)

        @JvmStatic fun ofUpload(upload: Upload) = Command(upload = upload)

        @JvmStatic fun ofDrag(drag: Drag) = Command(drag = drag)

        @JvmStatic fun ofUncheck(uncheck: Uncheck) = Command(uncheck = uncheck)

        @JvmStatic fun ofGet(get: Get) = Command(get = get)

        @JvmStatic fun ofIs(is_: Is) = Command(is_ = is_)

        @JvmStatic fun ofWait(wait_: Wait) = Command(wait_ = wait_)

        @JvmStatic fun ofScreenshot(screenshot: Screenshot) = Command(screenshot = screenshot)

        @JvmStatic fun ofEval(eval: Eval) = Command(eval = eval)

        @JvmStatic fun ofPdf(pdf: Pdf) = Command(pdf = pdf)

        @JvmStatic fun ofScroll(scroll: Scroll) = Command(scroll = scroll)

        @JvmStatic fun ofFind(find: Find) = Command(find = find)

        @JvmStatic fun ofTab(tab: Tab) = Command(tab = tab)
    }

    interface Visitor<out T> {

        fun visitNavigate(navigate: Navigate): T

        fun visitBack(back: Back): T

        fun visitForward(forward: Forward): T

        fun visitReload(reload: Reload): T

        fun visitSnapshot(snapshot: Snapshot): T

        fun visitClick(click: Click): T

        fun visitDblclick(dblclick: Dblclick): T

        fun visitHover(hover: Hover): T

        fun visitFocus(focus: Focus): T

        fun visitType(type: Type): T

        fun visitFill(fill: Fill): T

        fun visitPress(press: Press): T

        fun visitKeydown(keydown: Keydown): T

        fun visitKeyup(keyup: Keyup): T

        fun visitMouse(mouse: Mouse): T

        fun visitSelect(select: Select): T

        fun visitCheck(check: Check): T

        fun visitUpload(upload: Upload): T

        fun visitDrag(drag: Drag): T

        fun visitUncheck(uncheck: Uncheck): T

        fun visitGet(get: Get): T

        fun visitIs(is_: Is): T

        fun visitWait(wait_: Wait): T

        fun visitScreenshot(screenshot: Screenshot): T

        fun visitEval(eval: Eval): T

        fun visitPdf(pdf: Pdf): T

        fun visitScroll(scroll: Scroll): T

        fun visitFind(find: Find): T

        fun visitTab(tab: Tab): T

        fun unknown(json: JsonValue?): T {
            throw SteelInvalidDataException("Unknown Command: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<Command>(Command::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): Command {
            val json = JsonValue.fromJsonNode(node)
            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<Navigate>())?.let {
                            Command(navigate = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Back>())?.let {
                            Command(back = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Forward>())?.let {
                            Command(forward = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Reload>())?.let {
                            Command(reload = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Snapshot>())?.let {
                            Command(snapshot = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Click>())?.let {
                            Command(click = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Dblclick>())?.let {
                            Command(dblclick = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Hover>())?.let {
                            Command(hover = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Focus>())?.let {
                            Command(focus = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Type>())?.let {
                            Command(type = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Fill>())?.let {
                            Command(fill = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Press>())?.let {
                            Command(press = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Keydown>())?.let {
                            Command(keydown = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Keyup>())?.let {
                            Command(keyup = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Mouse>())?.let {
                            Command(mouse = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Select>())?.let {
                            Command(select = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Check>())?.let {
                            Command(check = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Upload>())?.let {
                            Command(upload = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Drag>())?.let {
                            Command(drag = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Uncheck>())?.let {
                            Command(uncheck = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Get>())?.let {
                            Command(get = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Is>())?.let {
                            Command(is_ = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Wait>())?.let {
                            Command(wait_ = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Screenshot>())?.let {
                            Command(screenshot = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Eval>())?.let {
                            Command(eval = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Pdf>())?.let {
                            Command(pdf = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Scroll>())?.let {
                            Command(scroll = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Find>())?.let {
                            Command(find = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Tab>())?.let {
                            Command(tab = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                0 -> Command(_json = json)
                1 -> bestMatches.single()
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<Command>(Command::class) {

        override fun serialize(
            value: Command,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.navigate != null -> generator.writeObject(value.navigate)
                value.back != null -> generator.writeObject(value.back)
                value.forward != null -> generator.writeObject(value.forward)
                value.reload != null -> generator.writeObject(value.reload)
                value.snapshot != null -> generator.writeObject(value.snapshot)
                value.click != null -> generator.writeObject(value.click)
                value.dblclick != null -> generator.writeObject(value.dblclick)
                value.hover != null -> generator.writeObject(value.hover)
                value.focus != null -> generator.writeObject(value.focus)
                value.type != null -> generator.writeObject(value.type)
                value.fill != null -> generator.writeObject(value.fill)
                value.press != null -> generator.writeObject(value.press)
                value.keydown != null -> generator.writeObject(value.keydown)
                value.keyup != null -> generator.writeObject(value.keyup)
                value.mouse != null -> generator.writeObject(value.mouse)
                value.select != null -> generator.writeObject(value.select)
                value.check != null -> generator.writeObject(value.check)
                value.upload != null -> generator.writeObject(value.upload)
                value.drag != null -> generator.writeObject(value.drag)
                value.uncheck != null -> generator.writeObject(value.uncheck)
                value.get != null -> generator.writeObject(value.get)
                value.is_ != null -> generator.writeObject(value.is_)
                value.wait_ != null -> generator.writeObject(value.wait_)
                value.screenshot != null -> generator.writeObject(value.screenshot)
                value.eval != null -> generator.writeObject(value.eval)
                value.pdf != null -> generator.writeObject(value.pdf)
                value.scroll != null -> generator.writeObject(value.scroll)
                value.find != null -> generator.writeObject(value.find)
                value.tab != null -> generator.writeObject(value.tab)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid Command")
            }
        }
    }

    /**
     * Opens a URL in the tab. Only http, https, and about:blank. A URL without a scheme gets
     * https://.
     */
    class Navigate
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

            /** Returns a mutable builder for constructing an instance of [Navigate]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Navigate]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("navigate")
            private var url: JsonField<String>? = null
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(navigate: Navigate) = apply {
                this.command = navigate.command
                this.url = navigate.url
                this.tab = navigate.tab
                this.additionalProperties = navigate.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Navigate =
                Navigate(
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

        fun validate(): Navigate = apply {
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

            return other is Navigate &&
                this.command == other.command &&
                this.url == other.url &&
                this.tab == other.tab &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(command, url, tab, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Navigate{command=$command, url=$url, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Goes back one history entry. */
    class Back
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

            /** Returns a mutable builder for constructing an instance of [Back]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Back]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("back")
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(back: Back) = apply {
                this.command = back.command
                this.tab = back.tab
                this.additionalProperties = back.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Back = Back(command, tab, additionalProperties.toMutableMap())
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Back = apply {
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

            return other is Back &&
                this.command == other.command &&
                this.tab == other.tab &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(command, tab, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Back{command=$command, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Goes forward one history entry. */
    class Forward
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

            /** Returns a mutable builder for constructing an instance of [Forward]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Forward]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("forward")
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(forward: Forward) = apply {
                this.command = forward.command
                this.tab = forward.tab
                this.additionalProperties = forward.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Forward = Forward(command, tab, additionalProperties.toMutableMap())
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Forward = apply {
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

            return other is Forward &&
                this.command == other.command &&
                this.tab == other.tab &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(command, tab, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Forward{command=$command, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Reloads the tab. */
    class Reload
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

            /** Returns a mutable builder for constructing an instance of [Reload]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Reload]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("reload")
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(reload: Reload) = apply {
                this.command = reload.command
                this.tab = reload.tab
                this.additionalProperties = reload.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Reload = Reload(command, tab, additionalProperties.toMutableMap())
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Reload = apply {
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

            return other is Reload &&
                this.command == other.command &&
                this.tab == other.tab &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(command, tab, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Reload{command=$command, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Returns the accessibility tree of the page, with a ref for each element you can act on. */
    class Snapshot
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
            @JsonProperty("compact") @ExcludeMissing compact: JsonField<Boolean> = JsonMissing.of(),
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

            /** Returns a mutable builder for constructing an instance of [Snapshot]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Snapshot]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("snapshot")
            private var compact: JsonField<Boolean> = JsonMissing.of()
            private var depth: JsonField<Long> = JsonMissing.of()
            private var interactive: JsonField<Boolean> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(snapshot: Snapshot) = apply {
                this.command = snapshot.command
                this.compact = snapshot.compact
                this.depth = snapshot.depth
                this.interactive = snapshot.interactive
                this.tab = snapshot.tab
                this.additionalProperties = snapshot.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Snapshot =
                Snapshot(
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

        fun validate(): Snapshot = apply {
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

            return other is Snapshot &&
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
            "Snapshot{command=$command, compact=$compact, depth=$depth, interactive=$interactive, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Clicks the element with the mouse. */
    class Click
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

            /** Returns a mutable builder for constructing an instance of [Click]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Click]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("click")
            private var target: JsonField<CommandTarget>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(click: Click) = apply {
                this.command = click.command
                this.target = click.target
                this.expect = click.expect
                this.frame = click.frame
                this.tab = click.tab
                this.additionalProperties = click.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Click =
                Click(
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

        fun validate(): Click = apply {
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

            return other is Click &&
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
            "Click{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Double-clicks the element with the mouse. */
    class Dblclick
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

            /** Returns a mutable builder for constructing an instance of [Dblclick]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Dblclick]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("dblclick")
            private var target: JsonField<CommandTarget>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(dblclick: Dblclick) = apply {
                this.command = dblclick.command
                this.target = dblclick.target
                this.expect = dblclick.expect
                this.frame = dblclick.frame
                this.tab = dblclick.tab
                this.additionalProperties = dblclick.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Dblclick =
                Dblclick(
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

        fun validate(): Dblclick = apply {
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

            return other is Dblclick &&
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
            "Dblclick{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Moves the mouse onto the element. */
    class Hover
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

            /** Returns a mutable builder for constructing an instance of [Hover]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Hover]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("hover")
            private var target: JsonField<CommandTarget>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(hover: Hover) = apply {
                this.command = hover.command
                this.target = hover.target
                this.expect = hover.expect
                this.frame = hover.frame
                this.tab = hover.tab
                this.additionalProperties = hover.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Hover =
                Hover(
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

        fun validate(): Hover = apply {
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

            return other is Hover &&
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
            "Hover{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Focuses the element. */
    class Focus
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

            /** Returns a mutable builder for constructing an instance of [Focus]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Focus]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("focus")
            private var target: JsonField<CommandTarget>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(focus: Focus) = apply {
                this.command = focus.command
                this.target = focus.target
                this.expect = focus.expect
                this.frame = focus.frame
                this.tab = focus.tab
                this.additionalProperties = focus.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Focus =
                Focus(
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

        fun validate(): Focus = apply {
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

            return other is Focus &&
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
            "Focus{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Types text one key press at a time, after the current content. */
    class Type
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

            /** Returns a mutable builder for constructing an instance of [Type]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Type]. */
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
            internal fun from(type: Type) = apply {
                this.command = type.command
                this.target = type.target
                this.text = type.text
                this.clear = type.clear
                this.delay = type.delay
                this.expect = type.expect
                this.frame = type.frame
                this.tab = type.tab
                this.additionalProperties = type.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Type =
                Type(
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

        fun validate(): Type = apply {
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

            return other is Type &&
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
            "Type{command=$command, target=$target, text=$text, clear=$clear, delay=$delay, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Replaces the content of a field in one step, without key presses. */
    class Fill
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

            /** Returns a mutable builder for constructing an instance of [Fill]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Fill]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("fill")
            private var target: JsonField<CommandTarget>? = null
            private var value: JsonField<String>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(fill: Fill) = apply {
                this.command = fill.command
                this.target = fill.target
                this.value = fill.value
                this.expect = fill.expect
                this.frame = fill.frame
                this.tab = fill.tab
                this.additionalProperties = fill.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Fill =
                Fill(
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

        fun validate(): Fill = apply {
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

            return other is Fill &&
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
            "Fill{command=$command, target=$target, value=$value, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Presses a key, such as Enter, or a combination such as Control+a. */
    class Press
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

            /** Returns a mutable builder for constructing an instance of [Press]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Press]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("press")
            private var key: JsonField<String>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var target: JsonField<CommandTarget> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(press: Press) = apply {
                this.command = press.command
                this.key = press.key
                this.expect = press.expect
                this.frame = press.frame
                this.tab = press.tab
                this.target = press.target
                this.additionalProperties = press.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Press =
                Press(
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

        fun validate(): Press = apply {
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

            return other is Press &&
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
            "Press{command=$command, key=$key, expect=$expect, frame=$frame, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
    }

    /** Holds a key down until its keyup. */
    class Keydown
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

            /** Returns a mutable builder for constructing an instance of [Keydown]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Keydown]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("keydown")
            private var key: JsonField<String>? = null
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(keydown: Keydown) = apply {
                this.command = keydown.command
                this.key = keydown.key
                this.tab = keydown.tab
                this.additionalProperties = keydown.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Keydown =
                Keydown(
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

        fun validate(): Keydown = apply {
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

            return other is Keydown &&
                this.command == other.command &&
                this.key == other.key &&
                this.tab == other.tab &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(command, key, tab, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Keydown{command=$command, key=$key, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Lets go of a key held with keydown. */
    class Keyup
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

            /** Returns a mutable builder for constructing an instance of [Keyup]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Keyup]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("keyup")
            private var key: JsonField<String>? = null
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(keyup: Keyup) = apply {
                this.command = keyup.command
                this.key = keyup.key
                this.tab = keyup.tab
                this.additionalProperties = keyup.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Keyup =
                Keyup(command, checkRequired("key", key), tab, additionalProperties.toMutableMap())
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: SteelInvalidDataException) {
                false
            }

        private var validated: Boolean = false

        fun validate(): Keyup = apply {
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

            return other is Keyup &&
                this.command == other.command &&
                this.key == other.key &&
                this.tab == other.tab &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(command, key, tab, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Keyup{command=$command, key=$key, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /**
     * Mouse input at a point in CSS pixels of the visible part of the page, for pages without
     * useful elements such as a canvas.
     */
    class Mouse
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val action: JsonField<Command.Mouse.Action>,
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
            action: JsonField<Command.Mouse.Action> = JsonMissing.of(),
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("button") @ExcludeMissing button: JsonField<Button> = JsonMissing.of(),
            @JsonProperty("clicks") @ExcludeMissing clicks: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("deltaX") @ExcludeMissing deltaX: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("deltaY") @ExcludeMissing deltaY: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            @JsonProperty("x") @ExcludeMissing x: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("y") @ExcludeMissing y: JsonField<Double> = JsonMissing.of(),
        ) : this(action, command, button, clicks, deltaX, deltaY, tab, x, y, mutableMapOf())

        fun action(): Command.Mouse.Action = action.getRequired("action")

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
        fun _action(): JsonField<Command.Mouse.Action> = action

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

            /** Returns a mutable builder for constructing an instance of [Mouse]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Mouse]. */
        class Builder internal constructor() {

            private var action: JsonField<Command.Mouse.Action>? = null
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
            internal fun from(mouse: Mouse) = apply {
                this.action = mouse.action
                this.command = mouse.command
                this.button = mouse.button
                this.clicks = mouse.clicks
                this.deltaX = mouse.deltaX
                this.deltaY = mouse.deltaY
                this.tab = mouse.tab
                this.x = mouse.x
                this.y = mouse.y
                this.additionalProperties = mouse.additionalProperties.toMutableMap()
            }

            fun action(action: Command.Mouse.Action) = action(JsonField.of(action))

            fun action(action: JsonField<Command.Mouse.Action>) = apply { this.action = action }

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Mouse =
                Mouse(
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

        fun validate(): Mouse = apply {
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

        class Action @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

        class Button @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            return other is Mouse &&
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
            "Mouse{action=$action, command=$command, button=$button, clicks=$clicks, deltaX=$deltaX, deltaY=$deltaY, tab=$tab, x=$x, y=$y, additionalProperties=$additionalProperties}"
    }

    /** Selects options. Matches by value, then by label. */
    class Select
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
            @JsonProperty("values") @ExcludeMissing values: JsonField<Values> = JsonMissing.of(),
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

            /** Returns a mutable builder for constructing an instance of [Select]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Select]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("select")
            private var target: JsonField<CommandTarget>? = null
            private var values: JsonField<Values>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(select: Select) = apply {
                this.command = select.command
                this.target = select.target
                this.values = select.values
                this.expect = select.expect
                this.frame = select.frame
                this.tab = select.tab
                this.additionalProperties = select.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Select =
                Select(
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

        fun validate(): Select = apply {
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

            return other is Select &&
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
            "Select{command=$command, target=$target, values=$values, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Checks a checkbox, radio button, switch, or toggle button. */
    class Check
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

            /** Returns a mutable builder for constructing an instance of [Check]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Check]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("check")
            private var target: JsonField<CommandTarget>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(check: Check) = apply {
                this.command = check.command
                this.target = check.target
                this.expect = check.expect
                this.frame = check.frame
                this.tab = check.tab
                this.additionalProperties = check.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Check =
                Check(
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

        fun validate(): Check = apply {
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

            return other is Check &&
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
            "Check{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Sets the files of a file input. Upload the files to the session first. */
    class Upload
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

            /** Returns a mutable builder for constructing an instance of [Upload]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Upload]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("upload")
            private var files: JsonField<Files>? = null
            private var target: JsonField<CommandTarget>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(upload: Upload) = apply {
                this.command = upload.command
                this.files = upload.files
                this.target = upload.target
                this.expect = upload.expect
                this.frame = upload.frame
                this.tab = upload.tab
                this.additionalProperties = upload.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Upload =
                Upload(
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

        fun validate(): Upload = apply {
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

            return other is Upload &&
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
            "Upload{command=$command, files=$files, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Drags one element onto another with the mouse. */
    class Drag
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

            /** Returns a mutable builder for constructing an instance of [Drag]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Drag]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("drag")
            private var source: JsonField<CommandTarget>? = null
            private var target: JsonField<CommandTarget>? = null
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(drag: Drag) = apply {
                this.command = drag.command
                this.source = drag.source
                this.target = drag.target
                this.frame = drag.frame
                this.tab = drag.tab
                this.additionalProperties = drag.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Drag =
                Drag(
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

        fun validate(): Drag = apply {
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

            return other is Drag &&
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
            "Drag{command=$command, source=$source, target=$target, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Unchecks a checkbox, switch, or toggle button. */
    class Uncheck
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

            /** Returns a mutable builder for constructing an instance of [Uncheck]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Uncheck]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("uncheck")
            private var target: JsonField<CommandTarget>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(uncheck: Uncheck) = apply {
                this.command = uncheck.command
                this.target = uncheck.target
                this.expect = uncheck.expect
                this.frame = uncheck.frame
                this.tab = uncheck.tab
                this.additionalProperties = uncheck.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Uncheck =
                Uncheck(
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

        fun validate(): Uncheck = apply {
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

            return other is Uncheck &&
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
            "Uncheck{command=$command, target=$target, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Reads something from the page or an element. */
    class Get
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val what: JsonField<Command.Get.What>,
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
            what: JsonField<Command.Get.What> = JsonMissing.of(),
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

        fun what(): Command.Get.What = what.getRequired("what")

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

        @JsonProperty("what") @ExcludeMissing fun _what(): JsonField<Command.Get.What> = what

        @JsonProperty("attribute") @ExcludeMissing fun _attribute(): JsonField<String> = attribute

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

            /** Returns a mutable builder for constructing an instance of [Get]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Get]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("get")
            private var what: JsonField<Command.Get.What>? = null
            private var attribute: JsonField<String> = JsonMissing.of()
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var target: JsonField<CommandTarget> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(get: Get) = apply {
                this.command = get.command
                this.what = get.what
                this.attribute = get.attribute
                this.expect = get.expect
                this.frame = get.frame
                this.tab = get.tab
                this.target = get.target
                this.additionalProperties = get.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            fun what(what: Command.Get.What) = what(JsonField.of(what))

            fun what(what: JsonField<Command.Get.What>) = apply { this.what = what }

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Get =
                Get(
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

        fun validate(): Get = apply {
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

        class What @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            return other is Get &&
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
            Objects.hash(command, what, attribute, expect, frame, tab, target, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Get{command=$command, what=$what, attribute=$attribute, expect=$expect, frame=$frame, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
    }

    /** Checks the state of an element. */
    class Is
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val target: JsonField<CommandTarget>,
        private val what: JsonField<Command.Is.What>,
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
            what: JsonField<Command.Is.What> = JsonMissing.of(),
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

        fun what(): Command.Is.What = what.getRequired("what")

        /** Fails the command when the element does not have this role or name. */
        fun expect(): Optional<CommandExpectation> = expect.getOptional("expect")

        /** The iframe to look in. List nested iframes from the outside in. */
        fun frame(): Optional<CommandFrame> = frame.getOptional("frame")

        /** A tab id such as t2. A command on a ref does not need it. */
        fun tab(): Optional<String> = tab.getOptional("tab")

        /** Expected to always return `JsonValue.from("is")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<CommandTarget> = target

        @JsonProperty("what") @ExcludeMissing fun _what(): JsonField<Command.Is.What> = what

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

            /** Returns a mutable builder for constructing an instance of [Is]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Is]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("is")
            private var target: JsonField<CommandTarget>? = null
            private var what: JsonField<Command.Is.What>? = null
            private var expect: JsonField<CommandExpectation> = JsonMissing.of()
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(is_: Is) = apply {
                this.command = is_.command
                this.target = is_.target
                this.what = is_.what
                this.expect = is_.expect
                this.frame = is_.frame
                this.tab = is_.tab
                this.additionalProperties = is_.additionalProperties.toMutableMap()
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

            fun what(what: Command.Is.What) = what(JsonField.of(what))

            fun what(what: JsonField<Command.Is.What>) = apply { this.what = what }

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Is =
                Is(
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

        fun validate(): Is = apply {
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

        class What @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            return other is Is &&
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
            "Is{command=$command, target=$target, what=$what, expect=$expect, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Waits for one of an element, text, a URL, a load state, a download, or a fixed time. */
    class Wait
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
            @JsonProperty("timeout") @ExcludeMissing timeout: JsonField<Long> = JsonMissing.of(),
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

            /** Returns a mutable builder for constructing an instance of [Wait]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Wait]. */
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
            internal fun from(wait_: Wait) = apply {
                this.command = wait_.command
                this.download = wait_.download
                this.expect = wait_.expect
                this.frame = wait_.frame
                this.load = wait_.load
                this.ms = wait_.ms
                this.state = wait_.state
                this.tab = wait_.tab
                this.target = wait_.target
                this.text = wait_.text
                this.timeout = wait_.timeout
                this.url = wait_.url
                this.additionalProperties = wait_.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Wait =
                Wait(
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

        fun validate(): Wait = apply {
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

        class Load @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

        class State @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            return other is Wait &&
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
            "Wait{command=$command, download=$download, expect=$expect, frame=$frame, load=$load, ms=$ms, state=$state, tab=$tab, target=$target, text=$text, timeout=$timeout, url=$url, additionalProperties=$additionalProperties}"
    }

    /** Captures the page or an element as base64 data. */
    class Screenshot
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
            @JsonProperty("format") @ExcludeMissing format: JsonField<Format> = JsonMissing.of(),
            @JsonProperty("frame")
            @ExcludeMissing
            frame: JsonField<CommandFrame> = JsonMissing.of(),
            @JsonProperty("fullPage")
            @ExcludeMissing
            fullPage: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("quality") @ExcludeMissing quality: JsonField<Long> = JsonMissing.of(),
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

            /** Returns a mutable builder for constructing an instance of [Screenshot]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Screenshot]. */
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
            internal fun from(screenshot: Screenshot) = apply {
                this.command = screenshot.command
                this.expect = screenshot.expect
                this.format = screenshot.format
                this.frame = screenshot.frame
                this.fullPage = screenshot.fullPage
                this.quality = screenshot.quality
                this.tab = screenshot.tab
                this.target = screenshot.target
                this.additionalProperties = screenshot.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Screenshot =
                Screenshot(
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

        fun validate(): Screenshot = apply {
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

        class Format @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            return other is Screenshot &&
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
            "Screenshot{command=$command, expect=$expect, format=$format, frame=$frame, fullPage=$fullPage, quality=$quality, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
    }

    /** Runs JavaScript in the page and returns the result as JSON. */
    class Eval
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
            @JsonProperty("script") @ExcludeMissing script: JsonField<String> = JsonMissing.of(),
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

            /** Returns a mutable builder for constructing an instance of [Eval]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Eval]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("eval")
            private var script: JsonField<String>? = null
            private var tab: JsonField<String> = JsonMissing.of()
            private var timeout: JsonField<Long> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(eval: Eval) = apply {
                this.command = eval.command
                this.script = eval.script
                this.tab = eval.tab
                this.timeout = eval.timeout
                this.additionalProperties = eval.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Eval =
                Eval(
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

        fun validate(): Eval = apply {
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

            return other is Eval &&
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
            "Eval{command=$command, script=$script, tab=$tab, timeout=$timeout, additionalProperties=$additionalProperties}"
    }

    /** Prints the page to a PDF as base64 data. */
    class Pdf
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

        fun printBackground(): Optional<Boolean> = printBackground.getOptional("printBackground")

        /** A tab id such as t2. A command on a ref does not need it. */
        fun tab(): Optional<String> = tab.getOptional("tab")

        /** Expected to always return `JsonValue.from("pdf")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("landscape") @ExcludeMissing fun _landscape(): JsonField<Boolean> = landscape

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

            /** Returns a mutable builder for constructing an instance of [Pdf]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Pdf]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("pdf")
            private var landscape: JsonField<Boolean> = JsonMissing.of()
            private var printBackground: JsonField<Boolean> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(pdf: Pdf) = apply {
                this.command = pdf.command
                this.landscape = pdf.landscape
                this.printBackground = pdf.printBackground
                this.tab = pdf.tab
                this.additionalProperties = pdf.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Pdf =
                Pdf(command, landscape, printBackground, tab, additionalProperties.toMutableMap())
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

            return other is Pdf &&
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
            "Pdf{command=$command, landscape=$landscape, printBackground=$printBackground, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Scrolls the page, or an element when target is set. */
    class Scroll
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
            @JsonProperty("amount") @ExcludeMissing amount: JsonField<Double> = JsonMissing.of(),
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

            /** Returns a mutable builder for constructing an instance of [Scroll]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Scroll]. */
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
            internal fun from(scroll: Scroll) = apply {
                this.command = scroll.command
                this.amount = scroll.amount
                this.direction = scroll.direction
                this.expect = scroll.expect
                this.frame = scroll.frame
                this.tab = scroll.tab
                this.target = scroll.target
                this.additionalProperties = scroll.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** Pixels. 300 by default. */
            fun amount(amount: Double) = amount(JsonField.of(amount))

            /** Pixels. 300 by default. */
            fun amount(amount: JsonField<Double>) = apply { this.amount = amount }

            fun direction(direction: Direction) = direction(JsonField.of(direction))

            fun direction(direction: JsonField<Direction>) = apply { this.direction = direction }

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Scroll =
                Scroll(
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

        fun validate(): Scroll = apply {
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

            return other is Scroll &&
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
            "Scroll{command=$command, amount=$amount, direction=$direction, expect=$expect, frame=$frame, tab=$tab, target=$target, additionalProperties=$additionalProperties}"
    }

    /**
     * Finds elements and returns refs for up to 50 matches. With nth, it returns that one match.
     */
    class Find
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

            /** Returns a mutable builder for constructing an instance of [Find]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Find]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("find")
            private var target: JsonField<CommandTarget>? = null
            private var frame: JsonField<CommandFrame> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(find: Find) = apply {
                this.command = find.command
                this.target = find.target
                this.frame = find.frame
                this.tab = find.tab
                this.additionalProperties = find.additionalProperties.toMutableMap()
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Find =
                Find(
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

        fun validate(): Find = apply {
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

            return other is Find &&
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
            "Find{command=$command, target=$target, frame=$frame, tab=$tab, additionalProperties=$additionalProperties}"
    }

    /** Lists, opens, or closes tabs. The last tab cannot be closed. */
    class Tab
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val action: JsonField<Command.Tab.Action>,
        private val command: JsonValue,
        private val tab: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("action")
            @ExcludeMissing
            action: JsonField<Command.Tab.Action> = JsonMissing.of(),
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(action, command, tab, url, mutableMapOf())

        fun action(): Command.Tab.Action = action.getRequired("action")

        /** A tab id such as t2. A command on a ref does not need it. */
        fun tab(): Optional<String> = tab.getOptional("tab")

        fun url(): Optional<String> = url.getOptional("url")

        @JsonProperty("action")
        @ExcludeMissing
        fun _action(): JsonField<Command.Tab.Action> = action

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

            /** Returns a mutable builder for constructing an instance of [Tab]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Tab]. */
        class Builder internal constructor() {

            private var action: JsonField<Command.Tab.Action>? = null
            private var command: JsonValue = JsonValue.from("tab")
            private var tab: JsonField<String> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(tab: Tab) = apply {
                this.action = tab.action
                this.command = tab.command
                this.tab = tab.tab
                this.url = tab.url
                this.additionalProperties = tab.additionalProperties.toMutableMap()
            }

            fun action(action: Command.Tab.Action) = action(JsonField.of(action))

            fun action(action: JsonField<Command.Tab.Action>) = apply { this.action = action }

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): Tab =
                Tab(
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

        fun validate(): Tab = apply {
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

        class Action @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            return other is Tab &&
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
            "Tab{action=$action, command=$command, tab=$tab, url=$url, additionalProperties=$additionalProperties}"
    }
}
