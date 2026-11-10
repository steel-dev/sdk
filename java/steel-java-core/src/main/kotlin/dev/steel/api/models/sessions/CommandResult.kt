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
import dev.steel.api.core.checkKnown
import dev.steel.api.core.checkRequired
import dev.steel.api.core.getOrThrow
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Result of one command, with the fields of that command. */
@JsonDeserialize(using = CommandResult.Deserializer::class)
@JsonSerialize(using = CommandResult.Serializer::class)
class CommandResult
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
    private val uncheck: Uncheck? = null,
    private val upload: Upload? = null,
    private val drag: Drag? = null,
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

    fun uncheck(): Optional<Uncheck> = Optional.ofNullable(uncheck)

    fun upload(): Optional<Upload> = Optional.ofNullable(upload)

    fun drag(): Optional<Drag> = Optional.ofNullable(drag)

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

    fun isUncheck(): Boolean = uncheck != null

    fun isUpload(): Boolean = upload != null

    fun isDrag(): Boolean = drag != null

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

    fun asUncheck(): Uncheck = uncheck.getOrThrow("uncheck")

    fun asUpload(): Upload = upload.getOrThrow("upload")

    fun asDrag(): Drag = drag.getOrThrow("drag")

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
            uncheck != null -> visitor.visitUncheck(uncheck)
            upload != null -> visitor.visitUpload(upload)
            drag != null -> visitor.visitDrag(drag)
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

    fun validate(): CommandResult = apply {
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

                override fun visitUncheck(uncheck: Uncheck) {
                    uncheck.validate()
                }

                override fun visitUpload(upload: Upload) {
                    upload.validate()
                }

                override fun visitDrag(drag: Drag) {
                    drag.validate()
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

                override fun visitUncheck(uncheck: Uncheck) = uncheck.validity()

                override fun visitUpload(upload: Upload) = upload.validity()

                override fun visitDrag(drag: Drag) = drag.validity()

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

        return other is CommandResult &&
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
            this.uncheck == other.uncheck &&
            this.upload == other.upload &&
            this.drag == other.drag &&
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
            uncheck,
            upload,
            drag,
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
            navigate != null -> "CommandResult{navigate=$navigate}"
            back != null -> "CommandResult{back=$back}"
            forward != null -> "CommandResult{forward=$forward}"
            reload != null -> "CommandResult{reload=$reload}"
            snapshot != null -> "CommandResult{snapshot=$snapshot}"
            click != null -> "CommandResult{click=$click}"
            dblclick != null -> "CommandResult{dblclick=$dblclick}"
            hover != null -> "CommandResult{hover=$hover}"
            focus != null -> "CommandResult{focus=$focus}"
            type != null -> "CommandResult{type=$type}"
            fill != null -> "CommandResult{fill=$fill}"
            press != null -> "CommandResult{press=$press}"
            keydown != null -> "CommandResult{keydown=$keydown}"
            keyup != null -> "CommandResult{keyup=$keyup}"
            mouse != null -> "CommandResult{mouse=$mouse}"
            select != null -> "CommandResult{select=$select}"
            check != null -> "CommandResult{check=$check}"
            uncheck != null -> "CommandResult{uncheck=$uncheck}"
            upload != null -> "CommandResult{upload=$upload}"
            drag != null -> "CommandResult{drag=$drag}"
            get != null -> "CommandResult{get=$get}"
            is_ != null -> "CommandResult{is_=$is_}"
            wait_ != null -> "CommandResult{wait_=$wait_}"
            screenshot != null -> "CommandResult{screenshot=$screenshot}"
            eval != null -> "CommandResult{eval=$eval}"
            pdf != null -> "CommandResult{pdf=$pdf}"
            scroll != null -> "CommandResult{scroll=$scroll}"
            find != null -> "CommandResult{find=$find}"
            tab != null -> "CommandResult{tab=$tab}"
            _json != null -> "CommandResult{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid CommandResult")
        }

    companion object {

        @JvmStatic fun ofNavigate(navigate: Navigate) = CommandResult(navigate = navigate)

        @JvmStatic fun ofBack(back: Back) = CommandResult(back = back)

        @JvmStatic fun ofForward(forward: Forward) = CommandResult(forward = forward)

        @JvmStatic fun ofReload(reload: Reload) = CommandResult(reload = reload)

        @JvmStatic fun ofSnapshot(snapshot: Snapshot) = CommandResult(snapshot = snapshot)

        @JvmStatic fun ofClick(click: Click) = CommandResult(click = click)

        @JvmStatic fun ofDblclick(dblclick: Dblclick) = CommandResult(dblclick = dblclick)

        @JvmStatic fun ofHover(hover: Hover) = CommandResult(hover = hover)

        @JvmStatic fun ofFocus(focus: Focus) = CommandResult(focus = focus)

        @JvmStatic fun ofType(type: Type) = CommandResult(type = type)

        @JvmStatic fun ofFill(fill: Fill) = CommandResult(fill = fill)

        @JvmStatic fun ofPress(press: Press) = CommandResult(press = press)

        @JvmStatic fun ofKeydown(keydown: Keydown) = CommandResult(keydown = keydown)

        @JvmStatic fun ofKeyup(keyup: Keyup) = CommandResult(keyup = keyup)

        @JvmStatic fun ofMouse(mouse: Mouse) = CommandResult(mouse = mouse)

        @JvmStatic fun ofSelect(select: Select) = CommandResult(select = select)

        @JvmStatic fun ofCheck(check: Check) = CommandResult(check = check)

        @JvmStatic fun ofUncheck(uncheck: Uncheck) = CommandResult(uncheck = uncheck)

        @JvmStatic fun ofUpload(upload: Upload) = CommandResult(upload = upload)

        @JvmStatic fun ofDrag(drag: Drag) = CommandResult(drag = drag)

        @JvmStatic fun ofGet(get: Get) = CommandResult(get = get)

        @JvmStatic fun ofIs(is_: Is) = CommandResult(is_ = is_)

        @JvmStatic fun ofWait(wait_: Wait) = CommandResult(wait_ = wait_)

        @JvmStatic fun ofScreenshot(screenshot: Screenshot) = CommandResult(screenshot = screenshot)

        @JvmStatic fun ofEval(eval: Eval) = CommandResult(eval = eval)

        @JvmStatic fun ofPdf(pdf: Pdf) = CommandResult(pdf = pdf)

        @JvmStatic fun ofScroll(scroll: Scroll) = CommandResult(scroll = scroll)

        @JvmStatic fun ofFind(find: Find) = CommandResult(find = find)

        @JvmStatic fun ofTab(tab: Tab) = CommandResult(tab = tab)
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

        fun visitUncheck(uncheck: Uncheck): T

        fun visitUpload(upload: Upload): T

        fun visitDrag(drag: Drag): T

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
            throw SteelInvalidDataException("Unknown CommandResult: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<CommandResult>(CommandResult::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): CommandResult {
            val json = JsonValue.fromJsonNode(node)
            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<Navigate>())?.let {
                            CommandResult(navigate = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Back>())?.let {
                            CommandResult(back = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Forward>())?.let {
                            CommandResult(forward = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Reload>())?.let {
                            CommandResult(reload = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Snapshot>())?.let {
                            CommandResult(snapshot = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Click>())?.let {
                            CommandResult(click = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Dblclick>())?.let {
                            CommandResult(dblclick = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Hover>())?.let {
                            CommandResult(hover = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Focus>())?.let {
                            CommandResult(focus = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Type>())?.let {
                            CommandResult(type = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Fill>())?.let {
                            CommandResult(fill = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Press>())?.let {
                            CommandResult(press = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Keydown>())?.let {
                            CommandResult(keydown = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Keyup>())?.let {
                            CommandResult(keyup = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Mouse>())?.let {
                            CommandResult(mouse = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Select>())?.let {
                            CommandResult(select = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Check>())?.let {
                            CommandResult(check = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Uncheck>())?.let {
                            CommandResult(uncheck = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Upload>())?.let {
                            CommandResult(upload = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Drag>())?.let {
                            CommandResult(drag = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Get>())?.let {
                            CommandResult(get = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Is>())?.let {
                            CommandResult(is_ = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Wait>())?.let {
                            CommandResult(wait_ = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Screenshot>())?.let {
                            CommandResult(screenshot = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Eval>())?.let {
                            CommandResult(eval = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Pdf>())?.let {
                            CommandResult(pdf = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Scroll>())?.let {
                            CommandResult(scroll = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Find>())?.let {
                            CommandResult(find = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Tab>())?.let {
                            CommandResult(tab = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                0 -> CommandResult(_json = json)
                1 -> bestMatches.single()
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<CommandResult>(CommandResult::class) {

        override fun serialize(
            value: CommandResult,
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
                value.uncheck != null -> generator.writeObject(value.uncheck)
                value.upload != null -> generator.writeObject(value.upload)
                value.drag != null -> generator.writeObject(value.drag)
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
                else -> throw IllegalStateException("Invalid CommandResult")
            }
        }
    }

    /** Result of a navigate command. */
    class Navigate
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Navigate.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Navigate.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(command, effect, ok, error, url, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Navigate.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun url(): Optional<String> = url.getOptional("url")

        /** Expected to always return `JsonValue.from("navigate")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Navigate.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

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

            /** Returns a mutable builder for constructing an instance of [Navigate]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Navigate]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("navigate")
            private var effect: JsonField<CommandResult.Navigate.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(navigate: Navigate) = apply {
                this.command = navigate.command
                this.effect = navigate.effect
                this.ok = navigate.ok
                this.error = navigate.error
                this.url = navigate.url
                this.additionalProperties = navigate.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Navigate.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Navigate.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

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

            fun build(): Navigate =
                Navigate(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
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

        fun validate(): Navigate = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("navigate")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("navigate")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (url.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Navigate &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Navigate{command=$command, effect=$effect, ok=$ok, error=$error, url=$url, additionalProperties=$additionalProperties}"
    }

    /** Result of a back command. */
    class Back
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Back.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val navigated: JsonField<Boolean>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Back.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("navigated")
            @ExcludeMissing
            navigated: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(command, effect, ok, error, navigated, url, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Back.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** False when there was no history entry. */
        fun navigated(): Optional<Boolean> = navigated.getOptional("navigated")

        fun url(): Optional<String> = url.getOptional("url")

        /** Expected to always return `JsonValue.from("back")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Back.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("navigated") @ExcludeMissing fun _navigated(): JsonField<Boolean> = navigated

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

            /** Returns a mutable builder for constructing an instance of [Back]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Back]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("back")
            private var effect: JsonField<CommandResult.Back.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var navigated: JsonField<Boolean> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(back: Back) = apply {
                this.command = back.command
                this.effect = back.effect
                this.ok = back.ok
                this.error = back.error
                this.navigated = back.navigated
                this.url = back.url
                this.additionalProperties = back.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Back.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Back.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** False when there was no history entry. */
            fun navigated(navigated: Boolean) = navigated(JsonField.of(navigated))

            /** False when there was no history entry. */
            fun navigated(navigated: JsonField<Boolean>) = apply { this.navigated = navigated }

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

            fun build(): Back =
                Back(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
                    navigated,
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

        fun validate(): Back = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("back")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            navigated()
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("back")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (navigated.asKnown().isPresent) 1 else 0) +
                (if (url.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Back &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.navigated == other.navigated &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, navigated, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Back{command=$command, effect=$effect, ok=$ok, error=$error, navigated=$navigated, url=$url, additionalProperties=$additionalProperties}"
    }

    /** Result of a forward command. */
    class Forward
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Forward.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val navigated: JsonField<Boolean>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Forward.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("navigated")
            @ExcludeMissing
            navigated: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(command, effect, ok, error, navigated, url, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Forward.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** False when there was no history entry. */
        fun navigated(): Optional<Boolean> = navigated.getOptional("navigated")

        fun url(): Optional<String> = url.getOptional("url")

        /** Expected to always return `JsonValue.from("forward")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Forward.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("navigated") @ExcludeMissing fun _navigated(): JsonField<Boolean> = navigated

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

            /** Returns a mutable builder for constructing an instance of [Forward]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Forward]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("forward")
            private var effect: JsonField<CommandResult.Forward.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var navigated: JsonField<Boolean> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(forward: Forward) = apply {
                this.command = forward.command
                this.effect = forward.effect
                this.ok = forward.ok
                this.error = forward.error
                this.navigated = forward.navigated
                this.url = forward.url
                this.additionalProperties = forward.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Forward.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Forward.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** False when there was no history entry. */
            fun navigated(navigated: Boolean) = navigated(JsonField.of(navigated))

            /** False when there was no history entry. */
            fun navigated(navigated: JsonField<Boolean>) = apply { this.navigated = navigated }

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

            fun build(): Forward =
                Forward(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
                    navigated,
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

        fun validate(): Forward = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("forward")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            navigated()
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("forward")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (navigated.asKnown().isPresent) 1 else 0) +
                (if (url.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Forward &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.navigated == other.navigated &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, navigated, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Forward{command=$command, effect=$effect, ok=$ok, error=$error, navigated=$navigated, url=$url, additionalProperties=$additionalProperties}"
    }

    /** Result of a reload command. */
    class Reload
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Reload.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Reload.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(command, effect, ok, error, url, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Reload.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun url(): Optional<String> = url.getOptional("url")

        /** Expected to always return `JsonValue.from("reload")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Reload.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

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

            /** Returns a mutable builder for constructing an instance of [Reload]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Reload]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("reload")
            private var effect: JsonField<CommandResult.Reload.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(reload: Reload) = apply {
                this.command = reload.command
                this.effect = reload.effect
                this.ok = reload.ok
                this.error = reload.error
                this.url = reload.url
                this.additionalProperties = reload.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Reload.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Reload.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

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

            fun build(): Reload =
                Reload(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
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

        fun validate(): Reload = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("reload")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            url()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("reload")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (url.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Reload &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.url == other.url &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Reload{command=$command, effect=$effect, ok=$ok, error=$error, url=$url, additionalProperties=$additionalProperties}"
    }

    /** Result of a snapshot command. */
    class Snapshot
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Snapshot.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val snapshot: JsonField<String>,
        private val snapshotId: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Snapshot.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("snapshot")
            @ExcludeMissing
            snapshot: JsonField<String> = JsonMissing.of(),
            @JsonProperty("snapshotId")
            @ExcludeMissing
            snapshotId: JsonField<String> = JsonMissing.of(),
        ) : this(command, effect, ok, error, snapshot, snapshotId, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Snapshot.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun snapshot(): Optional<String> = snapshot.getOptional("snapshot")

        fun snapshotId(): Optional<String> = snapshotId.getOptional("snapshotId")

        /** Expected to always return `JsonValue.from("snapshot")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Snapshot.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

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

            /** Returns a mutable builder for constructing an instance of [Snapshot]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Snapshot]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("snapshot")
            private var effect: JsonField<CommandResult.Snapshot.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var snapshot: JsonField<String> = JsonMissing.of()
            private var snapshotId: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(snapshot: Snapshot) = apply {
                this.command = snapshot.command
                this.effect = snapshot.effect
                this.ok = snapshot.ok
                this.error = snapshot.error
                this.snapshot = snapshot.snapshot
                this.snapshotId = snapshot.snapshotId
                this.additionalProperties = snapshot.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Snapshot.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Snapshot.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

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

            fun build(): Snapshot =
                Snapshot(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
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

        fun validate(): Snapshot = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("snapshot")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            snapshot()
            snapshotId()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("snapshot")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (snapshot.asKnown().isPresent) 1 else 0) +
                (if (snapshotId.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Snapshot &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.snapshot == other.snapshot &&
                this.snapshotId == other.snapshotId &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, snapshot, snapshotId, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Snapshot{command=$command, effect=$effect, ok=$ok, error=$error, snapshot=$snapshot, snapshotId=$snapshotId, additionalProperties=$additionalProperties}"
    }

    /** Result of a click command. */
    class Click
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Click.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Click.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Click.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("click")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Click.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Click.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(click: Click) = apply {
                this.command = click.command
                this.effect = click.effect
                this.ok = click.ok
                this.dialog = click.dialog
                this.error = click.error
                this.loading = click.loading
                this.additionalProperties = click.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Click.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Click.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("click")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Click &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Click{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a dblclick command. */
    class Dblclick
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Dblclick.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Dblclick.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Dblclick.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("dblclick")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Dblclick.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Dblclick.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(dblclick: Dblclick) = apply {
                this.command = dblclick.command
                this.effect = dblclick.effect
                this.ok = dblclick.ok
                this.dialog = dblclick.dialog
                this.error = dblclick.error
                this.loading = dblclick.loading
                this.additionalProperties = dblclick.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Dblclick.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Dblclick.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("dblclick")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Dblclick &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Dblclick{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a hover command. */
    class Hover
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Hover.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Hover.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Hover.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** Expected to always return `JsonValue.from("hover")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Hover.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

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

            /** Returns a mutable builder for constructing an instance of [Hover]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Hover]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("hover")
            private var effect: JsonField<CommandResult.Hover.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(hover: Hover) = apply {
                this.command = hover.command
                this.effect = hover.effect
                this.ok = hover.ok
                this.dialog = hover.dialog
                this.error = hover.error
                this.additionalProperties = hover.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Hover.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Hover.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

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

            fun build(): Hover =
                Hover(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
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

        fun validate(): Hover = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("hover")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("hover")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Hover &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Hover{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, additionalProperties=$additionalProperties}"
    }

    /** Result of a focus command. */
    class Focus
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Focus.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Focus.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
        ) : this(command, effect, ok, error, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Focus.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** Expected to always return `JsonValue.from("focus")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Focus.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

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

            /** Returns a mutable builder for constructing an instance of [Focus]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Focus]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("focus")
            private var effect: JsonField<CommandResult.Focus.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(focus: Focus) = apply {
                this.command = focus.command
                this.effect = focus.effect
                this.ok = focus.ok
                this.error = focus.error
                this.additionalProperties = focus.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Focus.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Focus.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

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

            fun build(): Focus =
                Focus(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
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

        fun validate(): Focus = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("focus")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("focus")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Focus &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Focus{command=$command, effect=$effect, ok=$ok, error=$error, additionalProperties=$additionalProperties}"
    }

    /** Result of a type command. */
    class Type
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Type.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Type.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Type.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("type")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Type.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Type.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(type: Type) = apply {
                this.command = type.command
                this.effect = type.effect
                this.ok = type.ok
                this.dialog = type.dialog
                this.error = type.error
                this.loading = type.loading
                this.additionalProperties = type.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Type.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Type.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("type")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Type{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a fill command. */
    class Fill
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Fill.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Fill.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Fill.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("fill")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Fill.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Fill.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(fill: Fill) = apply {
                this.command = fill.command
                this.effect = fill.effect
                this.ok = fill.ok
                this.dialog = fill.dialog
                this.error = fill.error
                this.loading = fill.loading
                this.additionalProperties = fill.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Fill.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Fill.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("fill")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Fill &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Fill{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a press command. */
    class Press
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Press.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Press.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Press.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("press")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Press.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Press.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(press: Press) = apply {
                this.command = press.command
                this.effect = press.effect
                this.ok = press.ok
                this.dialog = press.dialog
                this.error = press.error
                this.loading = press.loading
                this.additionalProperties = press.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Press.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Press.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("press")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Press &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Press{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a keydown command. */
    class Keydown
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Keydown.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Keydown.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Keydown.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("keydown")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Keydown.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Keydown.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(keydown: Keydown) = apply {
                this.command = keydown.command
                this.effect = keydown.effect
                this.ok = keydown.ok
                this.dialog = keydown.dialog
                this.error = keydown.error
                this.loading = keydown.loading
                this.additionalProperties = keydown.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Keydown.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Keydown.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("keydown")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Keydown &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Keydown{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a keyup command. */
    class Keyup
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Keyup.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Keyup.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Keyup.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("keyup")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Keyup.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Keyup.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(keyup: Keyup) = apply {
                this.command = keyup.command
                this.effect = keyup.effect
                this.ok = keyup.ok
                this.dialog = keyup.dialog
                this.error = keyup.error
                this.loading = keyup.loading
                this.additionalProperties = keyup.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Keyup.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Keyup.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                Keyup(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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

        fun validate(): Keyup = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("keyup")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("keyup")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Keyup &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Keyup{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a mouse command. */
    class Mouse
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Mouse.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Mouse.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Mouse.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("mouse")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Mouse.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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

            private var command: JsonValue = JsonValue.from("mouse")
            private var effect: JsonField<CommandResult.Mouse.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(mouse: Mouse) = apply {
                this.command = mouse.command
                this.effect = mouse.effect
                this.ok = mouse.ok
                this.dialog = mouse.dialog
                this.error = mouse.error
                this.loading = mouse.loading
                this.additionalProperties = mouse.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Mouse.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Mouse.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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

            _command().let {
                if (it != JsonValue.from("mouse")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("mouse")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Mouse &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Mouse{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a select command. */
    class Select
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Select.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val selected: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Select.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("selected")
            @ExcludeMissing
            selected: JsonField<List<String>> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, selected, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Select.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** The values of the selected options. */
        fun selected(): Optional<List<String>> = selected.getOptional("selected")

        /** Expected to always return `JsonValue.from("select")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Select.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

        @JsonProperty("selected")
        @ExcludeMissing
        fun _selected(): JsonField<List<String>> = selected

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
            private var effect: JsonField<CommandResult.Select.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var selected: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(select: Select) = apply {
                this.command = select.command
                this.effect = select.effect
                this.ok = select.ok
                this.dialog = select.dialog
                this.error = select.error
                this.loading = select.loading
                this.selected = select.selected.map { it.toMutableList() }
                this.additionalProperties = select.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Select.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Select.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

            /** The values of the selected options. */
            fun selected(selected: List<String>) = selected(JsonField.of(selected))

            /** The values of the selected options. */
            fun selected(selected: JsonField<List<String>>) = apply {
                this.selected = selected.map { it.toMutableList() }
            }

            /** The values of the selected options. */
            fun addSelected(selected: String) = apply {
                this.selected =
                    (this.selected ?: JsonField.of(mutableListOf())).also {
                        checkKnown("selected", it).add(selected)
                    }
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

            fun build(): Select =
                Select(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
                    (selected ?: JsonMissing.of()).map { it.toImmutable() },
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            selected()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("select")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0) +
                (selected.asKnown().getOrNull()?.let { it.size } ?: 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Select &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.selected == other.selected &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                command,
                effect,
                ok,
                dialog,
                error,
                loading,
                selected,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Select{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, selected=$selected, additionalProperties=$additionalProperties}"
    }

    /** Result of a check command. */
    class Check
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Check.Effect>,
        private val ok: JsonField<Boolean>,
        private val checked: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Check.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("checked") @ExcludeMissing checked: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, checked, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Check.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun checked(): Optional<Boolean> = checked.getOptional("checked")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("check")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Check.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("checked") @ExcludeMissing fun _checked(): JsonField<Boolean> = checked

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Check.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var checked: JsonField<Boolean> = JsonMissing.of()
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(check: Check) = apply {
                this.command = check.command
                this.effect = check.effect
                this.ok = check.ok
                this.checked = check.checked
                this.dialog = check.dialog
                this.error = check.error
                this.loading = check.loading
                this.additionalProperties = check.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Check.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Check.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun checked(checked: Boolean) = checked(JsonField.of(checked))

            fun checked(checked: JsonField<Boolean>) = apply { this.checked = checked }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    checked,
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            checked()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("check")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (if (checked.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Check &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.checked == other.checked &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, checked, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Check{command=$command, effect=$effect, ok=$ok, checked=$checked, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a uncheck command. */
    class Uncheck
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Uncheck.Effect>,
        private val ok: JsonField<Boolean>,
        private val checked: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Uncheck.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("checked") @ExcludeMissing checked: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, checked, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Uncheck.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun checked(): Optional<Boolean> = checked.getOptional("checked")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("uncheck")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Uncheck.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("checked") @ExcludeMissing fun _checked(): JsonField<Boolean> = checked

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Uncheck.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var checked: JsonField<Boolean> = JsonMissing.of()
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(uncheck: Uncheck) = apply {
                this.command = uncheck.command
                this.effect = uncheck.effect
                this.ok = uncheck.ok
                this.checked = uncheck.checked
                this.dialog = uncheck.dialog
                this.error = uncheck.error
                this.loading = uncheck.loading
                this.additionalProperties = uncheck.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Uncheck.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Uncheck.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun checked(checked: Boolean) = checked(JsonField.of(checked))

            fun checked(checked: JsonField<Boolean>) = apply { this.checked = checked }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    checked,
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            checked()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("uncheck")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (if (checked.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Uncheck &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.checked == other.checked &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, checked, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Uncheck{command=$command, effect=$effect, ok=$ok, checked=$checked, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a upload command. */
    class Upload
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Upload.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val files: JsonField<List<String>>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Upload.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("files")
            @ExcludeMissing
            files: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, files, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Upload.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun files(): Optional<List<String>> = files.getOptional("files")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("upload")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Upload.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("files") @ExcludeMissing fun _files(): JsonField<List<String>> = files

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Upload.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var files: JsonField<MutableList<String>>? = null
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(upload: Upload) = apply {
                this.command = upload.command
                this.effect = upload.effect
                this.ok = upload.ok
                this.dialog = upload.dialog
                this.error = upload.error
                this.files = upload.files.map { it.toMutableList() }
                this.loading = upload.loading
                this.additionalProperties = upload.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Upload.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Upload.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun files(files: List<String>) = files(JsonField.of(files))

            fun files(files: JsonField<List<String>>) = apply {
                this.files = files.map { it.toMutableList() }
            }

            fun addFile(file: String) = apply {
                this.files =
                    (this.files ?: JsonField.of(mutableListOf())).also {
                        checkKnown("files", it).add(file)
                    }
            }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    (files ?: JsonMissing.of()).map { it.toImmutable() },
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            files()
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("upload")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (files.asKnown().getOrNull()?.let { it.size } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Upload &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.files == other.files &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, files, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Upload{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, files=$files, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a drag command. */
    class Drag
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Drag.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Drag.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Drag.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("drag")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Drag.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

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
            private var effect: JsonField<CommandResult.Drag.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(drag: Drag) = apply {
                this.command = drag.command
                this.effect = drag.effect
                this.ok = drag.ok
                this.dialog = drag.dialog
                this.error = drag.error
                this.loading = drag.loading
                this.additionalProperties = drag.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Drag.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Drag.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("drag")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Drag &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Drag{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, additionalProperties=$additionalProperties}"
    }

    /** Result of a get command. */
    class Get
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Get.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val masked: JsonField<Boolean>,
        private val value: JsonValue,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Get.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("masked") @ExcludeMissing masked: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("value") @ExcludeMissing value: JsonValue = JsonMissing.of(),
        ) : this(command, effect, ok, error, masked, value, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Get.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The value is a secret, so it is left out. */
        fun masked(): Optional<Boolean> = masked.getOptional("masked")

        /** Expected to always return `JsonValue.from("get")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Get.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("masked") @ExcludeMissing fun _masked(): JsonField<Boolean> = masked

        @JsonProperty("value") @ExcludeMissing fun _value(): JsonValue = value

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
            private var effect: JsonField<CommandResult.Get.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var masked: JsonField<Boolean> = JsonMissing.of()
            private var value: JsonValue = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(get: Get) = apply {
                this.command = get.command
                this.effect = get.effect
                this.ok = get.ok
                this.error = get.error
                this.masked = get.masked
                this.value = get.value
                this.additionalProperties = get.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Get.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Get.Effect>) = apply { this.effect = effect }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The value is a secret, so it is left out. */
            fun masked(masked: Boolean) = masked(JsonField.of(masked))

            /** The value is a secret, so it is left out. */
            fun masked(masked: JsonField<Boolean>) = apply { this.masked = masked }

            /**
             * A string for text, html, markdown, value, attr, title, and url. A number for count. {
             * x, y, width, height } or null for box. A list of locators for locator.
             */
            fun value(value: JsonValue) = apply { this.value = value }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
                    masked,
                    value,
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
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            masked()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("get")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (masked.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Get &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.masked == other.masked &&
                this.value == other.value &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, masked, value, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Get{command=$command, effect=$effect, ok=$ok, error=$error, masked=$masked, value=$value, additionalProperties=$additionalProperties}"
    }

    /** Result of a is command. */
    class Is
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Is.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val value: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Is.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("value") @ExcludeMissing value: JsonField<Boolean> = JsonMissing.of(),
        ) : this(command, effect, ok, error, value, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Is.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun value(): Optional<Boolean> = value.getOptional("value")

        /** Expected to always return `JsonValue.from("is")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Is.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<Boolean> = value

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
            private var effect: JsonField<CommandResult.Is.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var value: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(is_: Is) = apply {
                this.command = is_.command
                this.effect = is_.effect
                this.ok = is_.ok
                this.error = is_.error
                this.value = is_.value
                this.additionalProperties = is_.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Is.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Is.Effect>) = apply { this.effect = effect }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun value(value: Boolean) = value(JsonField.of(value))

            fun value(value: JsonField<Boolean>) = apply { this.value = value }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
                    value,
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
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            value()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("is")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (value.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Is &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.value == other.value &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, value, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Is{command=$command, effect=$effect, ok=$ok, error=$error, value=$value, additionalProperties=$additionalProperties}"
    }

    /** Result of a wait command. */
    class Wait
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Wait.Effect>,
        private val ok: JsonField<Boolean>,
        private val bytes: JsonField<Long>,
        private val error: JsonField<CommandErrorInfo>,
        private val file: JsonField<String>,
        private val waited: JsonField<Waited>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Wait.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("bytes") @ExcludeMissing bytes: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("file") @ExcludeMissing file: JsonField<String> = JsonMissing.of(),
            @JsonProperty("waited") @ExcludeMissing waited: JsonField<Waited> = JsonMissing.of(),
        ) : this(command, effect, ok, bytes, error, file, waited, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Wait.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun bytes(): Optional<Long> = bytes.getOptional("bytes")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The downloaded file, for download. */
        fun file(): Optional<String> = file.getOptional("file")

        fun waited(): Optional<Waited> = waited.getOptional("waited")

        /** Expected to always return `JsonValue.from("wait")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Wait.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("bytes") @ExcludeMissing fun _bytes(): JsonField<Long> = bytes

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("file") @ExcludeMissing fun _file(): JsonField<String> = file

        @JsonProperty("waited") @ExcludeMissing fun _waited(): JsonField<Waited> = waited

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
            private var effect: JsonField<CommandResult.Wait.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var bytes: JsonField<Long> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var file: JsonField<String> = JsonMissing.of()
            private var waited: JsonField<Waited> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(wait_: Wait) = apply {
                this.command = wait_.command
                this.effect = wait_.effect
                this.ok = wait_.ok
                this.bytes = wait_.bytes
                this.error = wait_.error
                this.file = wait_.file
                this.waited = wait_.waited
                this.additionalProperties = wait_.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Wait.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Wait.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun bytes(bytes: Long) = bytes(JsonField.of(bytes))

            fun bytes(bytes: JsonField<Long>) = apply { this.bytes = bytes }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The downloaded file, for download. */
            fun file(file: String) = file(JsonField.of(file))

            /** The downloaded file, for download. */
            fun file(file: JsonField<String>) = apply { this.file = file }

            fun waited(waited: Waited) = waited(JsonField.of(waited))

            fun waited(waited: JsonField<Waited>) = apply { this.waited = waited }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    bytes,
                    error,
                    file,
                    waited,
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
            effect().validate()
            ok()
            bytes()
            error().ifPresent { it.validate() }
            file()
            waited().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("wait")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (if (bytes.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (file.asKnown().isPresent) 1 else 0) +
                (waited.asKnown().getOrNull()?.let { it.validity() } ?: 0)

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

        class Waited @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val MS = of("ms")

                @JvmField val TARGET = of("target")

                @JvmField val TEXT = of("text")

                @JvmField val URL = of("url")

                @JvmField val LOAD = of("load")

                @JvmField val DOWNLOAD = of("download")

                @JvmStatic fun of(value: String) = Waited(JsonField.of(value))
            }

            enum class Known {
                MS,
                TARGET,
                TEXT,
                URL,
                LOAD,
                DOWNLOAD,
            }

            enum class Value {
                MS,
                TARGET,
                TEXT,
                URL,
                LOAD,
                DOWNLOAD,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    MS -> Value.MS
                    TARGET -> Value.TARGET
                    TEXT -> Value.TEXT
                    URL -> Value.URL
                    LOAD -> Value.LOAD
                    DOWNLOAD -> Value.DOWNLOAD
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    MS -> Known.MS
                    TARGET -> Known.TARGET
                    TEXT -> Known.TEXT
                    URL -> Known.URL
                    LOAD -> Known.LOAD
                    DOWNLOAD -> Known.DOWNLOAD
                    else -> throw SteelInvalidDataException("Unknown Waited: $value")
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

            fun validate(): Waited = apply {
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

                return other is Waited && value == other.value
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
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.bytes == other.bytes &&
                this.error == other.error &&
                this.file == other.file &&
                this.waited == other.waited &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, bytes, error, file, waited, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Wait{command=$command, effect=$effect, ok=$ok, bytes=$bytes, error=$error, file=$file, waited=$waited, additionalProperties=$additionalProperties}"
    }

    /** Result of a screenshot command. */
    class Screenshot
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Screenshot.Effect>,
        private val ok: JsonField<Boolean>,
        private val data: JsonField<String>,
        private val error: JsonField<CommandErrorInfo>,
        private val format: JsonField<CommandResult.Screenshot.Format>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Screenshot.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("format")
            @ExcludeMissing
            format: JsonField<CommandResult.Screenshot.Format> = JsonMissing.of(),
        ) : this(command, effect, ok, data, error, format, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Screenshot.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun data(): Optional<String> = data.getOptional("data")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun format(): Optional<CommandResult.Screenshot.Format> = format.getOptional("format")

        /** Expected to always return `JsonValue.from("screenshot")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Screenshot.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("format")
        @ExcludeMissing
        fun _format(): JsonField<CommandResult.Screenshot.Format> = format

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
            private var effect: JsonField<CommandResult.Screenshot.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var data: JsonField<String> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var format: JsonField<CommandResult.Screenshot.Format> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(screenshot: Screenshot) = apply {
                this.command = screenshot.command
                this.effect = screenshot.effect
                this.ok = screenshot.ok
                this.data = screenshot.data
                this.error = screenshot.error
                this.format = screenshot.format
                this.additionalProperties = screenshot.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Screenshot.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Screenshot.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun data(data: String) = data(JsonField.of(data))

            fun data(data: JsonField<String>) = apply { this.data = data }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun format(format: CommandResult.Screenshot.Format) = format(JsonField.of(format))

            fun format(format: JsonField<CommandResult.Screenshot.Format>) = apply {
                this.format = format
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

            fun build(): Screenshot =
                Screenshot(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    data,
                    error,
                    format,
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
            effect().validate()
            ok()
            data()
            error().ifPresent { it.validate() }
            format().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("screenshot")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (if (data.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (format.asKnown().getOrNull()?.let { it.validity() } ?: 0)

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
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.data == other.data &&
                this.error == other.error &&
                this.format == other.format &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, data, error, format, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Screenshot{command=$command, effect=$effect, ok=$ok, data=$data, error=$error, format=$format, additionalProperties=$additionalProperties}"
    }

    /** Result of a eval command. */
    class Eval
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Eval.Effect>,
        private val ok: JsonField<Boolean>,
        private val dialog: JsonField<CommandDialog>,
        private val error: JsonField<CommandErrorInfo>,
        private val loading: JsonField<Boolean>,
        private val value: JsonValue,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Eval.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("dialog")
            @ExcludeMissing
            dialog: JsonField<CommandDialog> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("loading") @ExcludeMissing loading: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("value") @ExcludeMissing value: JsonValue = JsonMissing.of(),
        ) : this(command, effect, ok, dialog, error, loading, value, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Eval.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        /** A dialog that the command opened. It blocks the tab until it closes. */
        fun dialog(): Optional<CommandDialog> = dialog.getOptional("dialog")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        /** The command started a navigation that was still loading when it returned. */
        fun loading(): Optional<Boolean> = loading.getOptional("loading")

        /** Expected to always return `JsonValue.from("eval")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Eval.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("dialog") @ExcludeMissing fun _dialog(): JsonField<CommandDialog> = dialog

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("loading") @ExcludeMissing fun _loading(): JsonField<Boolean> = loading

        @JsonProperty("value") @ExcludeMissing fun _value(): JsonValue = value

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
            private var effect: JsonField<CommandResult.Eval.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var dialog: JsonField<CommandDialog> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var loading: JsonField<Boolean> = JsonMissing.of()
            private var value: JsonValue = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(eval: Eval) = apply {
                this.command = eval.command
                this.effect = eval.effect
                this.ok = eval.ok
                this.dialog = eval.dialog
                this.error = eval.error
                this.loading = eval.loading
                this.value = eval.value
                this.additionalProperties = eval.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Eval.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Eval.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: CommandDialog) = dialog(JsonField.of(dialog))

            /** A dialog that the command opened. It blocks the tab until it closes. */
            fun dialog(dialog: JsonField<CommandDialog>) = apply { this.dialog = dialog }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: Boolean) = loading(JsonField.of(loading))

            /** The command started a navigation that was still loading when it returned. */
            fun loading(loading: JsonField<Boolean>) = apply { this.loading = loading }

            /** The result of the script as JSON. */
            fun value(value: JsonValue) = apply { this.value = value }

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
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    dialog,
                    error,
                    loading,
                    value,
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
            effect().validate()
            ok()
            dialog().ifPresent { it.validate() }
            error().ifPresent { it.validate() }
            loading()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("eval")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (dialog.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (loading.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Eval &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.dialog == other.dialog &&
                this.error == other.error &&
                this.loading == other.loading &&
                this.value == other.value &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, dialog, error, loading, value, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Eval{command=$command, effect=$effect, ok=$ok, dialog=$dialog, error=$error, loading=$loading, value=$value, additionalProperties=$additionalProperties}"
    }

    /** Result of a pdf command. */
    class Pdf
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Pdf.Effect>,
        private val ok: JsonField<Boolean>,
        private val data: JsonField<String>,
        private val error: JsonField<CommandErrorInfo>,
        private val format: JsonField<CommandResult.Pdf.Format>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Pdf.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("format")
            @ExcludeMissing
            format: JsonField<CommandResult.Pdf.Format> = JsonMissing.of(),
        ) : this(command, effect, ok, data, error, format, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Pdf.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun data(): Optional<String> = data.getOptional("data")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun format(): Optional<CommandResult.Pdf.Format> = format.getOptional("format")

        /** Expected to always return `JsonValue.from("pdf")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Pdf.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("format")
        @ExcludeMissing
        fun _format(): JsonField<CommandResult.Pdf.Format> = format

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
            private var effect: JsonField<CommandResult.Pdf.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var data: JsonField<String> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var format: JsonField<CommandResult.Pdf.Format> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(pdf: Pdf) = apply {
                this.command = pdf.command
                this.effect = pdf.effect
                this.ok = pdf.ok
                this.data = pdf.data
                this.error = pdf.error
                this.format = pdf.format
                this.additionalProperties = pdf.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Pdf.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Pdf.Effect>) = apply { this.effect = effect }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun data(data: String) = data(JsonField.of(data))

            fun data(data: JsonField<String>) = apply { this.data = data }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun format(format: CommandResult.Pdf.Format) = format(JsonField.of(format))

            fun format(format: JsonField<CommandResult.Pdf.Format>) = apply { this.format = format }

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
                Pdf(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    data,
                    error,
                    format,
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

        fun validate(): Pdf = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("pdf")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            data()
            error().ifPresent { it.validate() }
            format().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("pdf")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (if (data.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (format.asKnown().getOrNull()?.let { it.validity() } ?: 0)

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

        class Format @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val PDF = of("pdf")

                @JvmStatic fun of(value: String) = Format(JsonField.of(value))
            }

            enum class Known {
                PDF
            }

            enum class Value {
                PDF,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    PDF -> Value.PDF
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    PDF -> Known.PDF
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

            return other is Pdf &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.data == other.data &&
                this.error == other.error &&
                this.format == other.format &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, data, error, format, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Pdf{command=$command, effect=$effect, ok=$ok, data=$data, error=$error, format=$format, additionalProperties=$additionalProperties}"
    }

    /** Result of a scroll command. */
    class Scroll
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Scroll.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val moved: JsonField<Boolean>,
        private val x: JsonField<Double>,
        private val y: JsonField<Double>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Scroll.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("moved") @ExcludeMissing moved: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("x") @ExcludeMissing x: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("y") @ExcludeMissing y: JsonField<Double> = JsonMissing.of(),
        ) : this(command, effect, ok, error, moved, x, y, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Scroll.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun moved(): Optional<Boolean> = moved.getOptional("moved")

        fun x(): Optional<Double> = x.getOptional("x")

        fun y(): Optional<Double> = y.getOptional("y")

        /** Expected to always return `JsonValue.from("scroll")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Scroll.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("moved") @ExcludeMissing fun _moved(): JsonField<Boolean> = moved

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

            /** Returns a mutable builder for constructing an instance of [Scroll]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Scroll]. */
        class Builder internal constructor() {

            private var command: JsonValue = JsonValue.from("scroll")
            private var effect: JsonField<CommandResult.Scroll.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var moved: JsonField<Boolean> = JsonMissing.of()
            private var x: JsonField<Double> = JsonMissing.of()
            private var y: JsonField<Double> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(scroll: Scroll) = apply {
                this.command = scroll.command
                this.effect = scroll.effect
                this.ok = scroll.ok
                this.error = scroll.error
                this.moved = scroll.moved
                this.x = scroll.x
                this.y = scroll.y
                this.additionalProperties = scroll.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Scroll.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Scroll.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun moved(moved: Boolean) = moved(JsonField.of(moved))

            fun moved(moved: JsonField<Boolean>) = apply { this.moved = moved }

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

            fun build(): Scroll =
                Scroll(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
                    moved,
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

        fun validate(): Scroll = apply {
            if (validated) {
                return@apply
            }

            _command().let {
                if (it != JsonValue.from("scroll")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            moved()
            x()
            y()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("scroll")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (moved.asKnown().isPresent) 1 else 0) +
                (if (x.asKnown().isPresent) 1 else 0) +
                (if (y.asKnown().isPresent) 1 else 0)

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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Scroll &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.moved == other.moved &&
                this.x == other.x &&
                this.y == other.y &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, moved, x, y, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Scroll{command=$command, effect=$effect, ok=$ok, error=$error, moved=$moved, x=$x, y=$y, additionalProperties=$additionalProperties}"
    }

    /** Result of a find command. */
    class Find
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Find.Effect>,
        private val ok: JsonField<Boolean>,
        private val count: JsonField<Long>,
        private val error: JsonField<CommandErrorInfo>,
        private val matches: JsonField<List<Match>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Find.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("matches")
            @ExcludeMissing
            matches: JsonField<List<Match>> = JsonMissing.of(),
        ) : this(command, effect, ok, count, error, matches, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Find.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun count(): Optional<Long> = count.getOptional("count")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun matches(): Optional<List<Match>> = matches.getOptional("matches")

        /** Expected to always return `JsonValue.from("find")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Find.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("matches") @ExcludeMissing fun _matches(): JsonField<List<Match>> = matches

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
            private var effect: JsonField<CommandResult.Find.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var count: JsonField<Long> = JsonMissing.of()
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var matches: JsonField<MutableList<Match>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(find: Find) = apply {
                this.command = find.command
                this.effect = find.effect
                this.ok = find.ok
                this.count = find.count
                this.error = find.error
                this.matches = find.matches.map { it.toMutableList() }
                this.additionalProperties = find.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Find.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Find.Effect>) = apply {
                this.effect = effect
            }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun count(count: Long) = count(JsonField.of(count))

            fun count(count: JsonField<Long>) = apply { this.count = count }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun matches(matches: List<Match>) = matches(JsonField.of(matches))

            fun matches(matches: JsonField<List<Match>>) = apply {
                this.matches = matches.map { it.toMutableList() }
            }

            fun addMatch(match: Match) = apply {
                this.matches =
                    (this.matches ?: JsonField.of(mutableListOf())).also {
                        checkKnown("matches", it).add(match)
                    }
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

            fun build(): Find =
                Find(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    count,
                    error,
                    (matches ?: JsonMissing.of()).map { it.toImmutable() },
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
            effect().validate()
            ok()
            count()
            error().ifPresent { it.validate() }
            matches().ifPresent { it.forEach { it.validate() } }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("find")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (if (count.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (matches.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0)

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

        class Match
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val name: JsonField<String>,
            private val ref: JsonField<String>,
            private val role: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
                @JsonProperty("ref") @ExcludeMissing ref: JsonField<String> = JsonMissing.of(),
                @JsonProperty("role") @ExcludeMissing role: JsonField<String> = JsonMissing.of(),
            ) : this(name, ref, role, mutableMapOf())

            fun name(): String = name.getRequired("name")

            fun ref(): String = ref.getRequired("ref")

            fun role(): String = role.getRequired("role")

            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            @JsonProperty("ref") @ExcludeMissing fun _ref(): JsonField<String> = ref

            @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<String> = role

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

                /** Returns a mutable builder for constructing an instance of [Match]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Match]. */
            class Builder internal constructor() {

                private var name: JsonField<String>? = null
                private var ref: JsonField<String>? = null
                private var role: JsonField<String>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(match: Match) = apply {
                    this.name = match.name
                    this.ref = match.ref
                    this.role = match.role
                    this.additionalProperties = match.additionalProperties.toMutableMap()
                }

                fun name(name: String) = name(JsonField.of(name))

                fun name(name: JsonField<String>) = apply { this.name = name }

                fun ref(ref: String) = ref(JsonField.of(ref))

                fun ref(ref: JsonField<String>) = apply { this.ref = ref }

                fun role(role: String) = role(JsonField.of(role))

                fun role(role: JsonField<String>) = apply { this.role = role }

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

                fun build(): Match =
                    Match(
                        checkRequired("name", name),
                        checkRequired("ref", ref),
                        checkRequired("role", role),
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

            fun validate(): Match = apply {
                if (validated) {
                    return@apply
                }

                name()
                ref()
                role()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (if (name.asKnown().isPresent) 1 else 0) +
                    (if (ref.asKnown().isPresent) 1 else 0) +
                    (if (role.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Match &&
                    this.name == other.name &&
                    this.ref == other.ref &&
                    this.role == other.role &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(name, ref, role, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Match{name=$name, ref=$ref, role=$role, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Find &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.count == other.count &&
                this.error == other.error &&
                this.matches == other.matches &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, count, error, matches, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Find{command=$command, effect=$effect, ok=$ok, count=$count, error=$error, matches=$matches, additionalProperties=$additionalProperties}"
    }

    /** Result of a tab command. */
    class Tab
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val command: JsonValue,
        private val effect: JsonField<CommandResult.Tab.Effect>,
        private val ok: JsonField<Boolean>,
        private val error: JsonField<CommandErrorInfo>,
        private val tab: JsonField<String>,
        private val tabs: JsonField<List<TabModel>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
            @JsonProperty("effect")
            @ExcludeMissing
            effect: JsonField<CommandResult.Tab.Effect> = JsonMissing.of(),
            @JsonProperty("ok") @ExcludeMissing ok: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("error")
            @ExcludeMissing
            error: JsonField<CommandErrorInfo> = JsonMissing.of(),
            @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            @JsonProperty("tabs") @ExcludeMissing tabs: JsonField<List<TabModel>> = JsonMissing.of(),
        ) : this(command, effect, ok, error, tab, tabs, mutableMapOf())

        /** What happened to the page: nothing, a change, or a possible change. */
        fun effect(): CommandResult.Tab.Effect = effect.getRequired("effect")

        fun ok(): Boolean = ok.getRequired("ok")

        fun error(): Optional<CommandErrorInfo> = error.getOptional("error")

        fun tab(): Optional<String> = tab.getOptional("tab")

        fun tabs(): Optional<List<TabModel>> = tabs.getOptional("tabs")

        /** Expected to always return `JsonValue.from("tab")`. It is set by default. */
        @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

        @JsonProperty("effect")
        @ExcludeMissing
        fun _effect(): JsonField<CommandResult.Tab.Effect> = effect

        @JsonProperty("ok") @ExcludeMissing fun _ok(): JsonField<Boolean> = ok

        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<CommandErrorInfo> = error

        @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

        @JsonProperty("tabs") @ExcludeMissing fun _tabs(): JsonField<List<TabModel>> = tabs

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

            private var command: JsonValue = JsonValue.from("tab")
            private var effect: JsonField<CommandResult.Tab.Effect>? = null
            private var ok: JsonField<Boolean>? = null
            private var error: JsonField<CommandErrorInfo> = JsonMissing.of()
            private var tab: JsonField<String> = JsonMissing.of()
            private var tabs: JsonField<MutableList<TabModel>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(tab: Tab) = apply {
                this.command = tab.command
                this.effect = tab.effect
                this.ok = tab.ok
                this.error = tab.error
                this.tab = tab.tab
                this.tabs = tab.tabs.map { it.toMutableList() }
                this.additionalProperties = tab.additionalProperties.toMutableMap()
            }

            fun command(command: JsonValue) = apply { this.command = command }

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: CommandResult.Tab.Effect) = effect(JsonField.of(effect))

            /** What happened to the page: nothing, a change, or a possible change. */
            fun effect(effect: JsonField<CommandResult.Tab.Effect>) = apply { this.effect = effect }

            fun ok(ok: Boolean) = ok(JsonField.of(ok))

            fun ok(ok: JsonField<Boolean>) = apply { this.ok = ok }

            fun error(error: CommandErrorInfo) = error(JsonField.of(error))

            fun error(error: JsonField<CommandErrorInfo>) = apply { this.error = error }

            fun tab(tab: String) = tab(JsonField.of(tab))

            fun tab(tab: JsonField<String>) = apply { this.tab = tab }

            fun tabs(tabs: List<TabModel>) = tabs(JsonField.of(tabs))

            fun tabs(tabs: JsonField<List<TabModel>>) = apply {
                this.tabs = tabs.map { it.toMutableList() }
            }

            fun addTab(tab: TabModel) = apply {
                this.tabs =
                    (this.tabs ?: JsonField.of(mutableListOf())).also {
                        checkKnown("tabs", it).add(tab)
                    }
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

            fun build(): Tab =
                Tab(
                    command,
                    checkRequired("effect", effect),
                    checkRequired("ok", ok),
                    error,
                    tab,
                    (tabs ?: JsonMissing.of()).map { it.toImmutable() },
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

            _command().let {
                if (it != JsonValue.from("tab")) {
                    throw SteelInvalidDataException("'command' is invalid, received $it")
                }
            }
            effect().validate()
            ok()
            error().ifPresent { it.validate() }
            tab()
            tabs().ifPresent { it.forEach { it.validate() } }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (command.let { if (it == JsonValue.from("tab")) 1 else 0 }) +
                (effect.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (ok.asKnown().isPresent) 1 else 0) +
                (error.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (tab.asKnown().isPresent) 1 else 0) +
                (tabs.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0)

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

        class TabModel
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

                /** Returns a mutable builder for constructing an instance of [TabModel]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [TabModel]. */
            class Builder internal constructor() {

                private var tab: JsonField<String>? = null
                private var title: JsonField<String>? = null
                private var url: JsonField<String>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(tabModel: TabModel) = apply {
                    this.tab = tabModel.tab
                    this.title = tabModel.title
                    this.url = tabModel.url
                    this.additionalProperties = tabModel.additionalProperties.toMutableMap()
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

                fun build(): TabModel =
                    TabModel(
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

            fun validate(): TabModel = apply {
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

                return other is TabModel &&
                    this.tab == other.tab &&
                    this.title == other.title &&
                    this.url == other.url &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(tab, title, url, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "TabModel{tab=$tab, title=$title, url=$url, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tab &&
                this.command == other.command &&
                this.effect == other.effect &&
                this.ok == other.ok &&
                this.error == other.error &&
                this.tab == other.tab &&
                this.tabs == other.tabs &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(command, effect, ok, error, tab, tabs, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Tab{command=$command, effect=$effect, ok=$ok, error=$error, tab=$tab, tabs=$tabs, additionalProperties=$additionalProperties}"
    }
}
