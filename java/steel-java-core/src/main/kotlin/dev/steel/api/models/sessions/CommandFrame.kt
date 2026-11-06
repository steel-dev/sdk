package dev.steel.api.models.sessions

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import dev.steel.api.core.BaseDeserializer
import dev.steel.api.core.BaseSerializer
import dev.steel.api.core.JsonValue
import dev.steel.api.core.allMaxBy
import dev.steel.api.core.getOrThrow
import dev.steel.api.core.toImmutable
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Objects
import java.util.Optional

/** The iframe to look in. List nested iframes from the outside in. */
@JsonDeserialize(using = CommandFrame.Deserializer::class)
@JsonSerialize(using = CommandFrame.Serializer::class)
class CommandFrame
private constructor(
    private val string: String? = null,
    private val commandLocator: CommandLocator? = null,
    private val unionMember2: List<CommandTarget>? = null,
    private val _json: JsonValue? = null,
) {

    fun string(): Optional<String> = Optional.ofNullable(string)

    fun commandLocator(): Optional<CommandLocator> = Optional.ofNullable(commandLocator)

    fun unionMember2(): Optional<List<CommandTarget>> = Optional.ofNullable(unionMember2)

    fun isString(): Boolean = string != null

    fun isCommandLocator(): Boolean = commandLocator != null

    fun isUnionMember2(): Boolean = unionMember2 != null

    fun asString(): String = string.getOrThrow("string")

    fun asCommandLocator(): CommandLocator = commandLocator.getOrThrow("commandLocator")

    fun asUnionMember2(): List<CommandTarget> = unionMember2.getOrThrow("unionMember2")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            string != null -> visitor.visitString(string)
            commandLocator != null -> visitor.visitCommandLocator(commandLocator)
            unionMember2 != null -> visitor.visitUnionMember2(unionMember2)
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

    fun validate(): CommandFrame = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitString(string: String) {}

                override fun visitCommandLocator(commandLocator: CommandLocator) {
                    commandLocator.validate()
                }

                override fun visitUnionMember2(unionMember2: List<CommandTarget>) {
                    unionMember2.forEach { it.validate() }
                }
            }
        )
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitString(string: String) = 1

                override fun visitCommandLocator(commandLocator: CommandLocator) =
                    commandLocator.validity()

                override fun visitUnionMember2(unionMember2: List<CommandTarget>) =
                    unionMember2.sumOf { (it.validity()).toInt() }

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandFrame &&
            this.string == other.string &&
            this.commandLocator == other.commandLocator &&
            this.unionMember2 == other.unionMember2
    }

    override fun hashCode(): Int = Objects.hash(string, commandLocator, unionMember2)

    override fun toString(): String =
        when {
            string != null -> "CommandFrame{string=$string}"
            commandLocator != null -> "CommandFrame{commandLocator=$commandLocator}"
            unionMember2 != null -> "CommandFrame{unionMember2=$unionMember2}"
            _json != null -> "CommandFrame{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid CommandFrame")
        }

    companion object {

        @JvmStatic fun ofString(string: String) = CommandFrame(string = string)

        @JvmStatic
        fun ofCommandLocator(commandLocator: CommandLocator) =
            CommandFrame(commandLocator = commandLocator)

        @JvmStatic
        fun ofUnionMember2(unionMember2: List<CommandTarget>) =
            CommandFrame(unionMember2 = unionMember2.toImmutable())
    }

    interface Visitor<out T> {

        fun visitString(string: String): T

        fun visitCommandLocator(commandLocator: CommandLocator): T

        fun visitUnionMember2(unionMember2: List<CommandTarget>): T

        fun unknown(json: JsonValue?): T {
            throw SteelInvalidDataException("Unknown CommandFrame: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<CommandFrame>(CommandFrame::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): CommandFrame {
            val json = JsonValue.fromJsonNode(node)
            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<CommandLocator>())?.let {
                            CommandFrame(commandLocator = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<List<CommandTarget>>())?.let {
                            CommandFrame(unionMember2 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<String>())?.let {
                            CommandFrame(string = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                0 -> CommandFrame(_json = json)
                1 -> bestMatches.single()
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<CommandFrame>(CommandFrame::class) {

        override fun serialize(
            value: CommandFrame,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.string != null -> generator.writeObject(value.string)
                value.commandLocator != null -> generator.writeObject(value.commandLocator)
                value.unionMember2 != null -> generator.writeObject(value.unionMember2)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid CommandFrame")
            }
        }
    }
}
