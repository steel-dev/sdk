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
import dev.steel.api.errors.SteelInvalidDataException
import java.util.Objects
import java.util.Optional

/** A ref from a snapshot such as e12, a CSS selector, or a locator. */
@JsonDeserialize(using = CommandTarget.Deserializer::class)
@JsonSerialize(using = CommandTarget.Serializer::class)
class CommandTarget
private constructor(
    private val string: String? = null,
    private val commandLocator: CommandLocator? = null,
    private val _json: JsonValue? = null,
) {

    fun string(): Optional<String> = Optional.ofNullable(string)

    fun commandLocator(): Optional<CommandLocator> = Optional.ofNullable(commandLocator)

    fun isString(): Boolean = string != null

    fun isCommandLocator(): Boolean = commandLocator != null

    fun asString(): String = string.getOrThrow("string")

    fun asCommandLocator(): CommandLocator = commandLocator.getOrThrow("commandLocator")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            string != null -> visitor.visitString(string)
            commandLocator != null -> visitor.visitCommandLocator(commandLocator)
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

    fun validate(): CommandTarget = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitString(string: String) {}

                override fun visitCommandLocator(commandLocator: CommandLocator) {
                    commandLocator.validate()
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

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandTarget &&
            this.string == other.string &&
            this.commandLocator == other.commandLocator
    }

    override fun hashCode(): Int = Objects.hash(string, commandLocator)

    override fun toString(): String =
        when {
            string != null -> "CommandTarget{string=$string}"
            commandLocator != null -> "CommandTarget{commandLocator=$commandLocator}"
            _json != null -> "CommandTarget{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid CommandTarget")
        }

    companion object {

        @JvmStatic fun ofString(string: String) = CommandTarget(string = string)

        @JvmStatic
        fun ofCommandLocator(commandLocator: CommandLocator) =
            CommandTarget(commandLocator = commandLocator)
    }

    interface Visitor<out T> {

        fun visitString(string: String): T

        fun visitCommandLocator(commandLocator: CommandLocator): T

        fun unknown(json: JsonValue?): T {
            throw SteelInvalidDataException("Unknown CommandTarget: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<CommandTarget>(CommandTarget::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): CommandTarget {
            val json = JsonValue.fromJsonNode(node)
            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<CommandLocator>())?.let {
                            CommandTarget(commandLocator = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<String>())?.let {
                            CommandTarget(string = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                0 -> CommandTarget(_json = json)
                1 -> bestMatches.single()
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<CommandTarget>(CommandTarget::class) {

        override fun serialize(
            value: CommandTarget,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.string != null -> generator.writeObject(value.string)
                value.commandLocator != null -> generator.writeObject(value.commandLocator)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid CommandTarget")
            }
        }
    }
}
