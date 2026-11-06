package dev.steel.api.models.files

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import dev.steel.api.core.ExcludeMissing
import dev.steel.api.core.JsonField
import dev.steel.api.core.JsonMissing
import dev.steel.api.core.JsonValue
import dev.steel.api.core.checkRequired
import dev.steel.api.errors.SteelInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

class File
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val lastModified: JsonField<OffsetDateTime>,
    private val path: JsonField<String>,
    private val size: JsonField<Double>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("lastModified")
        @ExcludeMissing
        lastModified: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
        @JsonProperty("size") @ExcludeMissing size: JsonField<Double> = JsonMissing.of(),
    ) : this(lastModified, path, size, mutableMapOf())

    /** Timestamp when the file was created */
    fun lastModified(): OffsetDateTime = lastModified.getRequired("lastModified")

    /** Path to the file in the storage system */
    fun path(): String = path.getRequired("path")

    /** Size of the file in bytes */
    fun size(): Double = size.getRequired("size")

    @JsonProperty("lastModified")
    @ExcludeMissing
    fun _lastModified(): JsonField<OffsetDateTime> = lastModified

    @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

    @JsonProperty("size") @ExcludeMissing fun _size(): JsonField<Double> = size

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

        /** Returns a mutable builder for constructing an instance of [File]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [File]. */
    class Builder internal constructor() {

        private var lastModified: JsonField<OffsetDateTime>? = null
        private var path: JsonField<String>? = null
        private var size: JsonField<Double>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(file: File) = apply {
            this.lastModified = file.lastModified
            this.path = file.path
            this.size = file.size
            this.additionalProperties = file.additionalProperties.toMutableMap()
        }

        /** Timestamp when the file was created */
        fun lastModified(lastModified: OffsetDateTime) = lastModified(JsonField.of(lastModified))

        /** Timestamp when the file was created */
        fun lastModified(lastModified: JsonField<OffsetDateTime>) = apply {
            this.lastModified = lastModified
        }

        /** Path to the file in the storage system */
        fun path(path: String) = path(JsonField.of(path))

        /** Path to the file in the storage system */
        fun path(path: JsonField<String>) = apply { this.path = path }

        /** Size of the file in bytes */
        fun size(size: Double) = size(JsonField.of(size))

        /** Size of the file in bytes */
        fun size(size: JsonField<Double>) = apply { this.size = size }

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

        fun build(): File =
            File(
                checkRequired("lastModified", lastModified),
                checkRequired("path", path),
                checkRequired("size", size),
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

    fun validate(): File = apply {
        if (validated) {
            return@apply
        }

        lastModified()
        path()
        size()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (lastModified.asKnown().isPresent) 1 else 0) +
            (if (path.asKnown().isPresent) 1 else 0) +
            (if (size.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is File &&
            this.lastModified == other.lastModified &&
            this.path == other.path &&
            this.size == other.size &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(lastModified, path, size, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "File{lastModified=$lastModified, path=$path, size=$size, additionalProperties=$additionalProperties}"
}
