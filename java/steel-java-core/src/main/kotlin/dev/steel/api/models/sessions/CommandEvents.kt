package dev.steel.api.models.sessions

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

/** Navigations, opened and closed tabs, dialogs, and downloads since eventsAfter. */
class CommandEvents
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cursor: JsonField<String>,
    private val items: JsonField<List<Item>>,
    private val more: JsonField<Boolean>,
    private val truncated: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cursor") @ExcludeMissing cursor: JsonField<String> = JsonMissing.of(),
        @JsonProperty("items") @ExcludeMissing items: JsonField<List<Item>> = JsonMissing.of(),
        @JsonProperty("more") @ExcludeMissing more: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("truncated") @ExcludeMissing truncated: JsonField<Boolean> = JsonMissing.of(),
    ) : this(cursor, items, more, truncated, mutableMapOf())

    fun cursor(): String = cursor.getRequired("cursor")

    fun items(): List<Item> = items.getRequired("items")

    fun more(): Optional<Boolean> = more.getOptional("more")

    fun truncated(): Optional<Boolean> = truncated.getOptional("truncated")

    @JsonProperty("cursor") @ExcludeMissing fun _cursor(): JsonField<String> = cursor

    @JsonProperty("items") @ExcludeMissing fun _items(): JsonField<List<Item>> = items

    @JsonProperty("more") @ExcludeMissing fun _more(): JsonField<Boolean> = more

    @JsonProperty("truncated") @ExcludeMissing fun _truncated(): JsonField<Boolean> = truncated

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

        /** Returns a mutable builder for constructing an instance of [CommandEvents]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CommandEvents]. */
    class Builder internal constructor() {

        private var cursor: JsonField<String>? = null
        private var items: JsonField<MutableList<Item>>? = null
        private var more: JsonField<Boolean> = JsonMissing.of()
        private var truncated: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(commandEvents: CommandEvents) = apply {
            this.cursor = commandEvents.cursor
            this.items = commandEvents.items.map { it.toMutableList() }
            this.more = commandEvents.more
            this.truncated = commandEvents.truncated
            this.additionalProperties = commandEvents.additionalProperties.toMutableMap()
        }

        fun cursor(cursor: String) = cursor(JsonField.of(cursor))

        fun cursor(cursor: JsonField<String>) = apply { this.cursor = cursor }

        fun items(items: List<Item>) = items(JsonField.of(items))

        fun items(items: JsonField<List<Item>>) = apply {
            this.items = items.map { it.toMutableList() }
        }

        fun addItem(item: Item) = apply {
            this.items =
                (this.items ?: JsonField.of(mutableListOf())).also {
                    checkKnown("items", it).add(item)
                }
        }

        fun more(more: Boolean) = more(JsonField.of(more))

        fun more(more: JsonField<Boolean>) = apply { this.more = more }

        fun truncated(truncated: Boolean) = truncated(JsonField.of(truncated))

        fun truncated(truncated: JsonField<Boolean>) = apply { this.truncated = truncated }

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

        fun build(): CommandEvents =
            CommandEvents(
                checkRequired("cursor", cursor),
                checkRequired("items", items).map { it.toImmutable() },
                more,
                truncated,
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

    fun validate(): CommandEvents = apply {
        if (validated) {
            return@apply
        }

        cursor()
        items().forEach { it.validate() }
        more()
        truncated()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (cursor.asKnown().isPresent) 1 else 0) +
            (items.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (if (more.asKnown().isPresent) 1 else 0) +
            (if (truncated.asKnown().isPresent) 1 else 0)

    class Item
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val at: JsonField<String>,
        private val tab: JsonField<String>,
        private val type: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("at") @ExcludeMissing at: JsonField<String> = JsonMissing.of(),
            @JsonProperty("tab") @ExcludeMissing tab: JsonField<String> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
        ) : this(id, at, tab, type, mutableMapOf())

        fun id(): String = id.getRequired("id")

        fun at(): String = at.getRequired("at")

        fun tab(): String = tab.getRequired("tab")

        fun type(): String = type.getRequired("type")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("at") @ExcludeMissing fun _at(): JsonField<String> = at

        @JsonProperty("tab") @ExcludeMissing fun _tab(): JsonField<String> = tab

        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

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

            /** Returns a mutable builder for constructing an instance of [Item]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Item]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var at: JsonField<String>? = null
            private var tab: JsonField<String>? = null
            private var type: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(item: Item) = apply {
                this.id = item.id
                this.at = item.at
                this.tab = item.tab
                this.type = item.type
                this.additionalProperties = item.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            fun id(id: JsonField<String>) = apply { this.id = id }

            fun at(at: String) = at(JsonField.of(at))

            fun at(at: JsonField<String>) = apply { this.at = at }

            fun tab(tab: String) = tab(JsonField.of(tab))

            fun tab(tab: JsonField<String>) = apply { this.tab = tab }

            fun type(type: String) = type(JsonField.of(type))

            fun type(type: JsonField<String>) = apply { this.type = type }

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

            fun build(): Item =
                Item(
                    checkRequired("id", id),
                    checkRequired("at", at),
                    checkRequired("tab", tab),
                    checkRequired("type", type),
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

        fun validate(): Item = apply {
            if (validated) {
                return@apply
            }

            id()
            at()
            tab()
            type()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (if (at.asKnown().isPresent) 1 else 0) +
                (if (tab.asKnown().isPresent) 1 else 0) +
                (if (type.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Item &&
                this.id == other.id &&
                this.at == other.at &&
                this.tab == other.tab &&
                this.type == other.type &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(id, at, tab, type, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Item{id=$id, at=$at, tab=$tab, type=$type, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommandEvents &&
            this.cursor == other.cursor &&
            this.items == other.items &&
            this.more == other.more &&
            this.truncated == other.truncated &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(cursor, items, more, truncated, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommandEvents{cursor=$cursor, items=$items, more=$more, truncated=$truncated, additionalProperties=$additionalProperties}"
}
