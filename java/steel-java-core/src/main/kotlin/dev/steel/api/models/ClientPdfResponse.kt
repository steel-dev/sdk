package dev.steel.api.models

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
import java.util.Collections
import java.util.Objects

class ClientPdfResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val url: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of()
    ) : this(url, mutableMapOf())

    /** URL where the PDF is hosted */
    fun url(): String = url.getRequired("url")

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

        /** Returns a mutable builder for constructing an instance of [ClientPdfResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ClientPdfResponse]. */
    class Builder internal constructor() {

        private var url: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(clientPdfResponse: ClientPdfResponse) = apply {
            this.url = clientPdfResponse.url
            this.additionalProperties = clientPdfResponse.additionalProperties.toMutableMap()
        }

        /** URL where the PDF is hosted */
        fun url(url: String) = url(JsonField.of(url))

        /** URL where the PDF is hosted */
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

        fun build(): ClientPdfResponse =
            ClientPdfResponse(checkRequired("url", url), additionalProperties.toMutableMap())
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: SteelInvalidDataException) {
            false
        }

    private var validated: Boolean = false

    fun validate(): ClientPdfResponse = apply {
        if (validated) {
            return@apply
        }

        url()
        validated = true
    }

    @JvmSynthetic internal fun validity(): Int = (if (url.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ClientPdfResponse &&
            this.url == other.url &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(url, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ClientPdfResponse{url=$url, additionalProperties=$additionalProperties}"
}
