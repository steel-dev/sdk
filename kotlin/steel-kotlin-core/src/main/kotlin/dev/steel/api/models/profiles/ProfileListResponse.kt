package dev.steel.api.models.profiles

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

class ProfileListResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val count: JsonField<Double>,
    private val profiles: JsonField<List<Profile>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("count") @ExcludeMissing count: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("profiles")
        @ExcludeMissing
        profiles: JsonField<List<Profile>> = JsonMissing.of(),
    ) : this(count, profiles, mutableMapOf())

    /** The total number of profiles */
    fun count(): Double = count.getRequired("count")

    /** The list of profiles */
    fun profiles(): List<Profile> = profiles.getRequired("profiles")

    @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Double> = count

    @JsonProperty("profiles") @ExcludeMissing fun _profiles(): JsonField<List<Profile>> = profiles

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

        /** Returns a mutable builder for constructing an instance of [ProfileListResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ProfileListResponse]. */
    class Builder internal constructor() {

        private var count: JsonField<Double>? = null
        private var profiles: JsonField<MutableList<Profile>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(profileListResponse: ProfileListResponse) = apply {
            this.count = profileListResponse.count
            this.profiles = profileListResponse.profiles.map { it.toMutableList() }
            this.additionalProperties = profileListResponse.additionalProperties.toMutableMap()
        }

        /** The total number of profiles */
        fun count(count: Double) = count(JsonField.of(count))

        /** The total number of profiles */
        fun count(count: JsonField<Double>) = apply { this.count = count }

        /** The list of profiles */
        fun profiles(profiles: List<Profile>) = profiles(JsonField.of(profiles))

        /** The list of profiles */
        fun profiles(profiles: JsonField<List<Profile>>) = apply {
            this.profiles = profiles.map { it.toMutableList() }
        }

        /** The list of profiles */
        fun addProfile(profile: Profile) = apply {
            this.profiles =
                (this.profiles ?: JsonField.of(mutableListOf())).also {
                    checkKnown("profiles", it).add(profile)
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

        fun build(): ProfileListResponse =
            ProfileListResponse(
                checkRequired("count", count),
                checkRequired("profiles", profiles).map { it.toImmutable() },
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

    fun validate(): ProfileListResponse = apply {
        if (validated) {
            return@apply
        }

        count()
        profiles().forEach { it.validate() }
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (count.asKnown().isPresent) 1 else 0) +
            (profiles.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0)

    class Profile
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val credentialsConfig: JsonValue,
        private val dimensions: JsonField<Dimensions>,
        private val extensionIds: JsonField<List<String>>,
        private val fingerprint: JsonField<Fingerprint>,
        private val projectId: JsonField<String>,
        private val sourceSessionId: JsonField<String>,
        private val status: JsonField<Status>,
        private val updatedAt: JsonField<OffsetDateTime>,
        private val useProxyConfig: JsonValue,
        private val userAgent: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("credentialsConfig")
            @ExcludeMissing
            credentialsConfig: JsonValue = JsonMissing.of(),
            @JsonProperty("dimensions")
            @ExcludeMissing
            dimensions: JsonField<Dimensions> = JsonMissing.of(),
            @JsonProperty("extensionIds")
            @ExcludeMissing
            extensionIds: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("fingerprint")
            @ExcludeMissing
            fingerprint: JsonField<Fingerprint> = JsonMissing.of(),
            @JsonProperty("projectId")
            @ExcludeMissing
            projectId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("sourceSessionId")
            @ExcludeMissing
            sourceSessionId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
            @JsonProperty("updatedAt")
            @ExcludeMissing
            updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("useProxyConfig")
            @ExcludeMissing
            useProxyConfig: JsonValue = JsonMissing.of(),
            @JsonProperty("userAgent")
            @ExcludeMissing
            userAgent: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            createdAt,
            credentialsConfig,
            dimensions,
            extensionIds,
            fingerprint,
            projectId,
            sourceSessionId,
            status,
            updatedAt,
            useProxyConfig,
            userAgent,
            mutableMapOf(),
        )

        /** The unique identifier for the profile */
        fun id(): String = id.getRequired("id")

        /** The date and time when the profile was created */
        fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

        /** The dimensions associated with the profile */
        fun dimensions(): Dimensions? = dimensions.getNullable("dimensions")

        /** The extension IDs associated with the profile */
        fun extensionIds(): List<String>? = extensionIds.getNullable("extensionIds")

        /** The fingerprint associated with the profile */
        fun fingerprint(): Fingerprint? = fingerprint.getNullable("fingerprint")

        /** The project ID associated with the profile */
        fun projectId(): String? = projectId.getNullable("projectId")

        /** The last session ID associated with the profile */
        fun sourceSessionId(): String? = sourceSessionId.getNullable("sourceSessionId")

        /** The status of the profile */
        fun status(): Status = status.getRequired("status")

        /** The date and time when the profile was last updated */
        fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updatedAt")

        /** The user agent associated with the profile */
        fun userAgent(): String? = userAgent.getNullable("userAgent")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        @JsonProperty("credentialsConfig")
        @ExcludeMissing
        fun _credentialsConfig(): JsonValue = credentialsConfig

        @JsonProperty("dimensions")
        @ExcludeMissing
        fun _dimensions(): JsonField<Dimensions> = dimensions

        @JsonProperty("extensionIds")
        @ExcludeMissing
        fun _extensionIds(): JsonField<List<String>> = extensionIds

        @JsonProperty("fingerprint")
        @ExcludeMissing
        fun _fingerprint(): JsonField<Fingerprint> = fingerprint

        @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

        @JsonProperty("sourceSessionId")
        @ExcludeMissing
        fun _sourceSessionId(): JsonField<String> = sourceSessionId

        @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

        @JsonProperty("updatedAt")
        @ExcludeMissing
        fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

        @JsonProperty("useProxyConfig")
        @ExcludeMissing
        fun _useProxyConfig(): JsonValue = useProxyConfig

        @JsonProperty("userAgent") @ExcludeMissing fun _userAgent(): JsonField<String> = userAgent

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

            /** Returns a mutable builder for constructing an instance of [Profile]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Profile]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var createdAt: JsonField<OffsetDateTime>? = null
            private var credentialsConfig: JsonValue? = null
            private var dimensions: JsonField<Dimensions>? = null
            private var extensionIds: JsonField<MutableList<String>>? = null
            private var fingerprint: JsonField<Fingerprint>? = null
            private var projectId: JsonField<String>? = null
            private var sourceSessionId: JsonField<String>? = null
            private var status: JsonField<Status>? = null
            private var updatedAt: JsonField<OffsetDateTime>? = null
            private var useProxyConfig: JsonValue? = null
            private var userAgent: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(profile: Profile) = apply {
                this.id = profile.id
                this.createdAt = profile.createdAt
                this.credentialsConfig = profile.credentialsConfig
                this.dimensions = profile.dimensions
                this.extensionIds = profile.extensionIds.map { it.toMutableList() }
                this.fingerprint = profile.fingerprint
                this.projectId = profile.projectId
                this.sourceSessionId = profile.sourceSessionId
                this.status = profile.status
                this.updatedAt = profile.updatedAt
                this.useProxyConfig = profile.useProxyConfig
                this.userAgent = profile.userAgent
                this.additionalProperties = profile.additionalProperties.toMutableMap()
            }

            /** The unique identifier for the profile */
            fun id(id: String) = id(JsonField.of(id))

            /** The unique identifier for the profile */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** The date and time when the profile was created */
            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** The date and time when the profile was created */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            /** The credentials configuration associated with the profile */
            fun credentialsConfig(credentialsConfig: JsonValue) = apply {
                this.credentialsConfig = credentialsConfig
            }

            /** The dimensions associated with the profile */
            fun dimensions(dimensions: Dimensions?) = dimensions(JsonField.ofNullable(dimensions))

            /** The dimensions associated with the profile */
            fun dimensions(dimensions: JsonField<Dimensions>) = apply {
                this.dimensions = dimensions
            }

            /** The extension IDs associated with the profile */
            fun extensionIds(extensionIds: List<String>?) =
                extensionIds(JsonField.ofNullable(extensionIds))

            /** The extension IDs associated with the profile */
            fun extensionIds(extensionIds: JsonField<List<String>>) = apply {
                this.extensionIds = extensionIds.map { it.toMutableList() }
            }

            /** The extension IDs associated with the profile */
            fun addExtensionId(extensionId: String) = apply {
                this.extensionIds =
                    (this.extensionIds ?: JsonField.of(mutableListOf())).also {
                        checkKnown("extensionIds", it).add(extensionId)
                    }
            }

            /** The fingerprint associated with the profile */
            fun fingerprint(fingerprint: Fingerprint?) =
                fingerprint(JsonField.ofNullable(fingerprint))

            /** The fingerprint associated with the profile */
            fun fingerprint(fingerprint: JsonField<Fingerprint>) = apply {
                this.fingerprint = fingerprint
            }

            /** The project ID associated with the profile */
            fun projectId(projectId: String?) = projectId(JsonField.ofNullable(projectId))

            /** The project ID associated with the profile */
            fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

            /** The last session ID associated with the profile */
            fun sourceSessionId(sourceSessionId: String?) =
                sourceSessionId(JsonField.ofNullable(sourceSessionId))

            /** The last session ID associated with the profile */
            fun sourceSessionId(sourceSessionId: JsonField<String>) = apply {
                this.sourceSessionId = sourceSessionId
            }

            /** The status of the profile */
            fun status(status: Status) = status(JsonField.of(status))

            /** The status of the profile */
            fun status(status: JsonField<Status>) = apply { this.status = status }

            /** The date and time when the profile was last updated */
            fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

            /** The date and time when the profile was last updated */
            fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply {
                this.updatedAt = updatedAt
            }

            /** The proxy configuration associated with the profile */
            fun useProxyConfig(useProxyConfig: JsonValue) = apply {
                this.useProxyConfig = useProxyConfig
            }

            /** The user agent associated with the profile */
            fun userAgent(userAgent: String?) = userAgent(JsonField.ofNullable(userAgent))

            /** The user agent associated with the profile */
            fun userAgent(userAgent: JsonField<String>) = apply { this.userAgent = userAgent }

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

            fun build(): Profile =
                Profile(
                    checkRequired("id", id),
                    checkRequired("createdAt", createdAt),
                    checkRequired("credentialsConfig", credentialsConfig),
                    checkRequired("dimensions", dimensions),
                    checkRequired("extensionIds", extensionIds).map { it.toImmutable() },
                    checkRequired("fingerprint", fingerprint),
                    checkRequired("projectId", projectId),
                    checkRequired("sourceSessionId", sourceSessionId),
                    checkRequired("status", status),
                    checkRequired("updatedAt", updatedAt),
                    checkRequired("useProxyConfig", useProxyConfig),
                    checkRequired("userAgent", userAgent),
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

        fun validate(): Profile = apply {
            if (validated) {
                return@apply
            }

            id()
            createdAt()
            dimensions()?.let { it.validate() }
            extensionIds()
            fingerprint()?.let { it.validate() }
            projectId()
            sourceSessionId()
            status().validate()
            updatedAt()
            userAgent()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (if (createdAt.asKnown().isPresent) 1 else 0) +
                (dimensions.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (extensionIds.asKnown().getOrNull()?.let { it.size } ?: 0) +
                (fingerprint.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (projectId.asKnown().isPresent) 1 else 0) +
                (if (sourceSessionId.asKnown().isPresent) 1 else 0) +
                (status.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (updatedAt.asKnown().isPresent) 1 else 0) +
                (if (userAgent.asKnown().isPresent) 1 else 0)

        /** The dimensions associated with the profile */
        class Dimensions
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val height: JsonField<Double>,
            private val width: JsonField<Double>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("height")
                @ExcludeMissing
                height: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("width") @ExcludeMissing width: JsonField<Double> = JsonMissing.of(),
            ) : this(height, width, mutableMapOf())

            fun height(): Double = height.getRequired("height")

            fun width(): Double = width.getRequired("width")

            @JsonProperty("height") @ExcludeMissing fun _height(): JsonField<Double> = height

            @JsonProperty("width") @ExcludeMissing fun _width(): JsonField<Double> = width

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

                /** Returns a mutable builder for constructing an instance of [Dimensions]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Dimensions]. */
            class Builder internal constructor() {

                private var height: JsonField<Double>? = null
                private var width: JsonField<Double>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(dimensions: Dimensions) = apply {
                    this.height = dimensions.height
                    this.width = dimensions.width
                    this.additionalProperties = dimensions.additionalProperties.toMutableMap()
                }

                fun height(height: Double) = height(JsonField.of(height))

                fun height(height: JsonField<Double>) = apply { this.height = height }

                fun width(width: Double) = width(JsonField.of(width))

                fun width(width: JsonField<Double>) = apply { this.width = width }

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

                fun build(): Dimensions =
                    Dimensions(
                        checkRequired("height", height),
                        checkRequired("width", width),
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

            fun validate(): Dimensions = apply {
                if (validated) {
                    return@apply
                }

                height()
                width()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (if (height.asKnown().isPresent) 1 else 0) +
                    (if (width.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Dimensions &&
                    this.height == other.height &&
                    this.width == other.width &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(height, width, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Dimensions{height=$height, width=$width, additionalProperties=$additionalProperties}"
        }

        /** The fingerprint associated with the profile */
        class Fingerprint
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val fingerprint: JsonField<FingerprintModel>,
            private val headers_: JsonField<HeadersModel>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("fingerprint")
                @ExcludeMissing
                fingerprint: JsonField<FingerprintModel> = JsonMissing.of(),
                @JsonProperty("headers")
                @ExcludeMissing
                headers_: JsonField<HeadersModel> = JsonMissing.of(),
            ) : this(fingerprint, headers_, mutableMapOf())

            fun fingerprint(): FingerprintModel = fingerprint.getRequired("fingerprint")

            fun headers_(): HeadersModel = headers_.getRequired("headers")

            @JsonProperty("fingerprint")
            @ExcludeMissing
            fun _fingerprint(): JsonField<FingerprintModel> = fingerprint

            @JsonProperty("headers")
            @ExcludeMissing
            fun _headers_(): JsonField<HeadersModel> = headers_

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

                /** Returns a mutable builder for constructing an instance of [Fingerprint]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Fingerprint]. */
            class Builder internal constructor() {

                private var fingerprint: JsonField<FingerprintModel>? = null
                private var headers_: JsonField<HeadersModel>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(fingerprint: Fingerprint) = apply {
                    this.fingerprint = fingerprint.fingerprint
                    this.headers_ = fingerprint.headers_
                    this.additionalProperties = fingerprint.additionalProperties.toMutableMap()
                }

                fun fingerprint(fingerprint: FingerprintModel) =
                    fingerprint(JsonField.of(fingerprint))

                fun fingerprint(fingerprint: JsonField<FingerprintModel>) = apply {
                    this.fingerprint = fingerprint
                }

                fun headers_(headers_: HeadersModel) = headers_(JsonField.of(headers_))

                fun headers_(headers_: JsonField<HeadersModel>) = apply { this.headers_ = headers_ }

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

                fun build(): Fingerprint =
                    Fingerprint(
                        checkRequired("fingerprint", fingerprint),
                        checkRequired("headers", headers_),
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

            fun validate(): Fingerprint = apply {
                if (validated) {
                    return@apply
                }

                fingerprint().validate()
                headers_().validate()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (fingerprint.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (headers_.asKnown().getOrNull()?.let { it.validity() } ?: 0)

            class FingerprintModel
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val audioCodecs: JsonField<AudioCodecs>,
                private val battery: JsonField<Battery>,
                private val fonts: JsonField<List<String>>,
                private val mockWebRtc: JsonField<Boolean>,
                private val multimediaDevices: JsonField<MultimediaDevices>,
                private val navigator: JsonField<Navigator>,
                private val pluginsData: JsonField<PluginsData>,
                private val screen: JsonField<Screen>,
                private val slim: JsonField<Boolean>,
                private val videoCard: JsonField<VideoCard>,
                private val videoCodecs: JsonField<VideoCodecs>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("audioCodecs")
                    @ExcludeMissing
                    audioCodecs: JsonField<AudioCodecs> = JsonMissing.of(),
                    @JsonProperty("battery")
                    @ExcludeMissing
                    battery: JsonField<Battery> = JsonMissing.of(),
                    @JsonProperty("fonts")
                    @ExcludeMissing
                    fonts: JsonField<List<String>> = JsonMissing.of(),
                    @JsonProperty("mockWebRTC")
                    @ExcludeMissing
                    mockWebRtc: JsonField<Boolean> = JsonMissing.of(),
                    @JsonProperty("multimediaDevices")
                    @ExcludeMissing
                    multimediaDevices: JsonField<MultimediaDevices> = JsonMissing.of(),
                    @JsonProperty("navigator")
                    @ExcludeMissing
                    navigator: JsonField<Navigator> = JsonMissing.of(),
                    @JsonProperty("pluginsData")
                    @ExcludeMissing
                    pluginsData: JsonField<PluginsData> = JsonMissing.of(),
                    @JsonProperty("screen")
                    @ExcludeMissing
                    screen: JsonField<Screen> = JsonMissing.of(),
                    @JsonProperty("slim")
                    @ExcludeMissing
                    slim: JsonField<Boolean> = JsonMissing.of(),
                    @JsonProperty("videoCard")
                    @ExcludeMissing
                    videoCard: JsonField<VideoCard> = JsonMissing.of(),
                    @JsonProperty("videoCodecs")
                    @ExcludeMissing
                    videoCodecs: JsonField<VideoCodecs> = JsonMissing.of(),
                ) : this(
                    audioCodecs,
                    battery,
                    fonts,
                    mockWebRtc,
                    multimediaDevices,
                    navigator,
                    pluginsData,
                    screen,
                    slim,
                    videoCard,
                    videoCodecs,
                    mutableMapOf(),
                )

                fun audioCodecs(): AudioCodecs = audioCodecs.getRequired("audioCodecs")

                fun battery(): Battery = battery.getRequired("battery")

                fun fonts(): List<String> = fonts.getRequired("fonts")

                fun mockWebRtc(): Boolean? = mockWebRtc.getNullable("mockWebRTC")

                fun multimediaDevices(): MultimediaDevices =
                    multimediaDevices.getRequired("multimediaDevices")

                fun navigator(): Navigator = navigator.getRequired("navigator")

                fun pluginsData(): PluginsData = pluginsData.getRequired("pluginsData")

                fun screen(): Screen = screen.getRequired("screen")

                fun slim(): Boolean? = slim.getNullable("slim")

                fun videoCard(): VideoCard = videoCard.getRequired("videoCard")

                fun videoCodecs(): VideoCodecs = videoCodecs.getRequired("videoCodecs")

                @JsonProperty("audioCodecs")
                @ExcludeMissing
                fun _audioCodecs(): JsonField<AudioCodecs> = audioCodecs

                @JsonProperty("battery")
                @ExcludeMissing
                fun _battery(): JsonField<Battery> = battery

                @JsonProperty("fonts") @ExcludeMissing fun _fonts(): JsonField<List<String>> = fonts

                @JsonProperty("mockWebRTC")
                @ExcludeMissing
                fun _mockWebRtc(): JsonField<Boolean> = mockWebRtc

                @JsonProperty("multimediaDevices")
                @ExcludeMissing
                fun _multimediaDevices(): JsonField<MultimediaDevices> = multimediaDevices

                @JsonProperty("navigator")
                @ExcludeMissing
                fun _navigator(): JsonField<Navigator> = navigator

                @JsonProperty("pluginsData")
                @ExcludeMissing
                fun _pluginsData(): JsonField<PluginsData> = pluginsData

                @JsonProperty("screen") @ExcludeMissing fun _screen(): JsonField<Screen> = screen

                @JsonProperty("slim") @ExcludeMissing fun _slim(): JsonField<Boolean> = slim

                @JsonProperty("videoCard")
                @ExcludeMissing
                fun _videoCard(): JsonField<VideoCard> = videoCard

                @JsonProperty("videoCodecs")
                @ExcludeMissing
                fun _videoCodecs(): JsonField<VideoCodecs> = videoCodecs

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
                     * Returns a mutable builder for constructing an instance of [FingerprintModel].
                     */
                    @JvmStatic fun builder() = Builder()
                }

                /** A builder for [FingerprintModel]. */
                class Builder internal constructor() {

                    private var audioCodecs: JsonField<AudioCodecs>? = null
                    private var battery: JsonField<Battery>? = null
                    private var fonts: JsonField<MutableList<String>>? = null
                    private var mockWebRtc: JsonField<Boolean>? = null
                    private var multimediaDevices: JsonField<MultimediaDevices>? = null
                    private var navigator: JsonField<Navigator>? = null
                    private var pluginsData: JsonField<PluginsData>? = null
                    private var screen: JsonField<Screen>? = null
                    private var slim: JsonField<Boolean>? = null
                    private var videoCard: JsonField<VideoCard>? = null
                    private var videoCodecs: JsonField<VideoCodecs>? = null
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    @JvmSynthetic
                    internal fun from(fingerprintModel: FingerprintModel) = apply {
                        this.audioCodecs = fingerprintModel.audioCodecs
                        this.battery = fingerprintModel.battery
                        this.fonts = fingerprintModel.fonts.map { it.toMutableList() }
                        this.mockWebRtc = fingerprintModel.mockWebRtc
                        this.multimediaDevices = fingerprintModel.multimediaDevices
                        this.navigator = fingerprintModel.navigator
                        this.pluginsData = fingerprintModel.pluginsData
                        this.screen = fingerprintModel.screen
                        this.slim = fingerprintModel.slim
                        this.videoCard = fingerprintModel.videoCard
                        this.videoCodecs = fingerprintModel.videoCodecs
                        this.additionalProperties =
                            fingerprintModel.additionalProperties.toMutableMap()
                    }

                    fun audioCodecs(audioCodecs: AudioCodecs) =
                        audioCodecs(JsonField.of(audioCodecs))

                    fun audioCodecs(audioCodecs: JsonField<AudioCodecs>) = apply {
                        this.audioCodecs = audioCodecs
                    }

                    fun battery(battery: Battery) = battery(JsonField.of(battery))

                    fun battery(battery: JsonField<Battery>) = apply { this.battery = battery }

                    fun fonts(fonts: List<String>) = fonts(JsonField.of(fonts))

                    fun fonts(fonts: JsonField<List<String>>) = apply {
                        this.fonts = fonts.map { it.toMutableList() }
                    }

                    fun addFont(font: String) = apply {
                        this.fonts =
                            (this.fonts ?: JsonField.of(mutableListOf())).also {
                                checkKnown("fonts", it).add(font)
                            }
                    }

                    fun mockWebRtc(mockWebRtc: Boolean?) =
                        mockWebRtc(JsonField.ofNullable(mockWebRtc))

                    fun mockWebRtc(mockWebRtc: Boolean) = mockWebRtc(mockWebRtc as Boolean?)

                    fun mockWebRtc(mockWebRtc: JsonField<Boolean>) = apply {
                        this.mockWebRtc = mockWebRtc
                    }

                    fun multimediaDevices(multimediaDevices: MultimediaDevices) =
                        multimediaDevices(JsonField.of(multimediaDevices))

                    fun multimediaDevices(multimediaDevices: JsonField<MultimediaDevices>) = apply {
                        this.multimediaDevices = multimediaDevices
                    }

                    fun navigator(navigator: Navigator) = navigator(JsonField.of(navigator))

                    fun navigator(navigator: JsonField<Navigator>) = apply {
                        this.navigator = navigator
                    }

                    fun pluginsData(pluginsData: PluginsData) =
                        pluginsData(JsonField.of(pluginsData))

                    fun pluginsData(pluginsData: JsonField<PluginsData>) = apply {
                        this.pluginsData = pluginsData
                    }

                    fun screen(screen: Screen) = screen(JsonField.of(screen))

                    fun screen(screen: JsonField<Screen>) = apply { this.screen = screen }

                    fun slim(slim: Boolean?) = slim(JsonField.ofNullable(slim))

                    fun slim(slim: Boolean) = slim(slim as Boolean?)

                    fun slim(slim: JsonField<Boolean>) = apply { this.slim = slim }

                    fun videoCard(videoCard: VideoCard) = videoCard(JsonField.of(videoCard))

                    fun videoCard(videoCard: JsonField<VideoCard>) = apply {
                        this.videoCard = videoCard
                    }

                    fun videoCodecs(videoCodecs: VideoCodecs) =
                        videoCodecs(JsonField.of(videoCodecs))

                    fun videoCodecs(videoCodecs: JsonField<VideoCodecs>) = apply {
                        this.videoCodecs = videoCodecs
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

                    fun build(): FingerprintModel =
                        FingerprintModel(
                            checkRequired("audioCodecs", audioCodecs),
                            checkRequired("battery", battery),
                            checkRequired("fonts", fonts).map { it.toImmutable() },
                            checkRequired("mockWebRTC", mockWebRtc),
                            checkRequired("multimediaDevices", multimediaDevices),
                            checkRequired("navigator", navigator),
                            checkRequired("pluginsData", pluginsData),
                            checkRequired("screen", screen),
                            checkRequired("slim", slim),
                            checkRequired("videoCard", videoCard),
                            checkRequired("videoCodecs", videoCodecs),
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

                fun validate(): FingerprintModel = apply {
                    if (validated) {
                        return@apply
                    }

                    audioCodecs().validate()
                    battery().validate()
                    fonts()
                    mockWebRtc()
                    multimediaDevices().validate()
                    navigator().validate()
                    pluginsData().validate()
                    screen().validate()
                    slim()
                    videoCard().validate()
                    videoCodecs().validate()
                    validated = true
                }

                @JvmSynthetic
                internal fun validity(): Int =
                    (audioCodecs.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (battery.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (fonts.asKnown().getOrNull()?.let { it.size } ?: 0) +
                        (if (mockWebRtc.asKnown().isPresent) 1 else 0) +
                        (multimediaDevices.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (navigator.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (pluginsData.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (screen.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (if (slim.asKnown().isPresent) 1 else 0) +
                        (videoCard.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (videoCodecs.asKnown().getOrNull()?.let { it.validity() } ?: 0)

                class AudioCodecs
                @JsonCreator
                private constructor(
                    @com.fasterxml.jackson.annotation.JsonValue
                    private val additionalProperties: Map<String, JsonValue>
                ) {

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        @JvmStatic fun builder() = Builder()
                    }

                    class Builder internal constructor() {

                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(audioCodecs: AudioCodecs) = apply {
                            this.additionalProperties =
                                audioCodecs.additionalProperties.toMutableMap()
                        }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): AudioCodecs = AudioCodecs(additionalProperties.toImmutable())
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: SteelInvalidDataException) {
                            false
                        }

                    private var validated: Boolean = false

                    fun validate(): AudioCodecs = apply {
                        if (validated) {
                            return@apply
                        }

                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        additionalProperties.count { (_, value) ->
                            !value.isNull() && !value.isMissing()
                        }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is AudioCodecs &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "AudioCodecs{additionalProperties=$additionalProperties}"
                }

                class Battery
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val charging: JsonField<Boolean>,
                    private val chargingTime: JsonField<Double>,
                    private val dischargingTime: JsonField<Double>,
                    private val level: JsonField<Double>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("charging")
                        @ExcludeMissing
                        charging: JsonField<Boolean> = JsonMissing.of(),
                        @JsonProperty("chargingTime")
                        @ExcludeMissing
                        chargingTime: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("dischargingTime")
                        @ExcludeMissing
                        dischargingTime: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("level")
                        @ExcludeMissing
                        level: JsonField<Double> = JsonMissing.of(),
                    ) : this(charging, chargingTime, dischargingTime, level, mutableMapOf())

                    fun charging(): Boolean? = charging.getNullable("charging")

                    fun chargingTime(): Double? = chargingTime.getNullable("chargingTime")

                    fun dischargingTime(): Double? = dischargingTime.getNullable("dischargingTime")

                    fun level(): Double? = level.getNullable("level")

                    @JsonProperty("charging")
                    @ExcludeMissing
                    fun _charging(): JsonField<Boolean> = charging

                    @JsonProperty("chargingTime")
                    @ExcludeMissing
                    fun _chargingTime(): JsonField<Double> = chargingTime

                    @JsonProperty("dischargingTime")
                    @ExcludeMissing
                    fun _dischargingTime(): JsonField<Double> = dischargingTime

                    @JsonProperty("level") @ExcludeMissing fun _level(): JsonField<Double> = level

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

                        /** Returns a mutable builder for constructing an instance of [Battery]. */
                        @JvmStatic fun builder() = Builder()
                    }

                    /** A builder for [Battery]. */
                    class Builder internal constructor() {

                        private var charging: JsonField<Boolean>? = null
                        private var chargingTime: JsonField<Double>? = null
                        private var dischargingTime: JsonField<Double>? = null
                        private var level: JsonField<Double>? = null
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(battery: Battery) = apply {
                            this.charging = battery.charging
                            this.chargingTime = battery.chargingTime
                            this.dischargingTime = battery.dischargingTime
                            this.level = battery.level
                            this.additionalProperties = battery.additionalProperties.toMutableMap()
                        }

                        fun charging(charging: Boolean?) = charging(JsonField.ofNullable(charging))

                        fun charging(charging: Boolean) = charging(charging as Boolean?)

                        fun charging(charging: JsonField<Boolean>) = apply {
                            this.charging = charging
                        }

                        fun chargingTime(chargingTime: Double?) =
                            chargingTime(JsonField.ofNullable(chargingTime))

                        fun chargingTime(chargingTime: Double) =
                            chargingTime(chargingTime as Double?)

                        fun chargingTime(chargingTime: JsonField<Double>) = apply {
                            this.chargingTime = chargingTime
                        }

                        fun dischargingTime(dischargingTime: Double?) =
                            dischargingTime(JsonField.ofNullable(dischargingTime))

                        fun dischargingTime(dischargingTime: Double) =
                            dischargingTime(dischargingTime as Double?)

                        fun dischargingTime(dischargingTime: JsonField<Double>) = apply {
                            this.dischargingTime = dischargingTime
                        }

                        fun level(level: Double?) = level(JsonField.ofNullable(level))

                        fun level(level: Double) = level(level as Double?)

                        fun level(level: JsonField<Double>) = apply { this.level = level }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): Battery =
                            Battery(
                                checkRequired("charging", charging),
                                checkRequired("chargingTime", chargingTime),
                                checkRequired("dischargingTime", dischargingTime),
                                checkRequired("level", level),
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

                    fun validate(): Battery = apply {
                        if (validated) {
                            return@apply
                        }

                        charging()
                        chargingTime()
                        dischargingTime()
                        level()
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        (if (charging.asKnown().isPresent) 1 else 0) +
                            (if (chargingTime.asKnown().isPresent) 1 else 0) +
                            (if (dischargingTime.asKnown().isPresent) 1 else 0) +
                            (if (level.asKnown().isPresent) 1 else 0)

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Battery &&
                            this.charging == other.charging &&
                            this.chargingTime == other.chargingTime &&
                            this.dischargingTime == other.dischargingTime &&
                            this.level == other.level &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(
                            charging,
                            chargingTime,
                            dischargingTime,
                            level,
                            additionalProperties,
                        )
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Battery{charging=$charging, chargingTime=$chargingTime, dischargingTime=$dischargingTime, level=$level, additionalProperties=$additionalProperties}"
                }

                class MultimediaDevices
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val micros: JsonField<List<Micro>>,
                    private val speakers: JsonField<List<Speaker>>,
                    private val webcams: JsonField<List<Webcam>>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("micros")
                        @ExcludeMissing
                        micros: JsonField<List<Micro>> = JsonMissing.of(),
                        @JsonProperty("speakers")
                        @ExcludeMissing
                        speakers: JsonField<List<Speaker>> = JsonMissing.of(),
                        @JsonProperty("webcams")
                        @ExcludeMissing
                        webcams: JsonField<List<Webcam>> = JsonMissing.of(),
                    ) : this(micros, speakers, webcams, mutableMapOf())

                    fun micros(): List<Micro> = micros.getRequired("micros")

                    fun speakers(): List<Speaker> = speakers.getRequired("speakers")

                    fun webcams(): List<Webcam> = webcams.getRequired("webcams")

                    @JsonProperty("micros")
                    @ExcludeMissing
                    fun _micros(): JsonField<List<Micro>> = micros

                    @JsonProperty("speakers")
                    @ExcludeMissing
                    fun _speakers(): JsonField<List<Speaker>> = speakers

                    @JsonProperty("webcams")
                    @ExcludeMissing
                    fun _webcams(): JsonField<List<Webcam>> = webcams

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
                         * [MultimediaDevices].
                         */
                        @JvmStatic fun builder() = Builder()
                    }

                    /** A builder for [MultimediaDevices]. */
                    class Builder internal constructor() {

                        private var micros: JsonField<MutableList<Micro>>? = null
                        private var speakers: JsonField<MutableList<Speaker>>? = null
                        private var webcams: JsonField<MutableList<Webcam>>? = null
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(multimediaDevices: MultimediaDevices) = apply {
                            this.micros = multimediaDevices.micros.map { it.toMutableList() }
                            this.speakers = multimediaDevices.speakers.map { it.toMutableList() }
                            this.webcams = multimediaDevices.webcams.map { it.toMutableList() }
                            this.additionalProperties =
                                multimediaDevices.additionalProperties.toMutableMap()
                        }

                        fun micros(micros: List<Micro>) = micros(JsonField.of(micros))

                        fun micros(micros: JsonField<List<Micro>>) = apply {
                            this.micros = micros.map { it.toMutableList() }
                        }

                        fun addMicro(micro: Micro) = apply {
                            this.micros =
                                (this.micros ?: JsonField.of(mutableListOf())).also {
                                    checkKnown("micros", it).add(micro)
                                }
                        }

                        fun speakers(speakers: List<Speaker>) = speakers(JsonField.of(speakers))

                        fun speakers(speakers: JsonField<List<Speaker>>) = apply {
                            this.speakers = speakers.map { it.toMutableList() }
                        }

                        fun addSpeaker(speaker: Speaker) = apply {
                            this.speakers =
                                (this.speakers ?: JsonField.of(mutableListOf())).also {
                                    checkKnown("speakers", it).add(speaker)
                                }
                        }

                        fun webcams(webcams: List<Webcam>) = webcams(JsonField.of(webcams))

                        fun webcams(webcams: JsonField<List<Webcam>>) = apply {
                            this.webcams = webcams.map { it.toMutableList() }
                        }

                        fun addWebcam(webcam: Webcam) = apply {
                            this.webcams =
                                (this.webcams ?: JsonField.of(mutableListOf())).also {
                                    checkKnown("webcams", it).add(webcam)
                                }
                        }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): MultimediaDevices =
                            MultimediaDevices(
                                checkRequired("micros", micros).map { it.toImmutable() },
                                checkRequired("speakers", speakers).map { it.toImmutable() },
                                checkRequired("webcams", webcams).map { it.toImmutable() },
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

                    fun validate(): MultimediaDevices = apply {
                        if (validated) {
                            return@apply
                        }

                        micros().forEach { it.validate() }
                        speakers().forEach { it.validate() }
                        webcams().forEach { it.validate() }
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        (micros.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } }
                            ?: 0) +
                            (speakers.asKnown().getOrNull()?.let {
                                it.sumOf { (it.validity()).toInt() }
                            } ?: 0) +
                            (webcams.asKnown().getOrNull()?.let {
                                it.sumOf { (it.validity()).toInt() }
                            } ?: 0)

                    class Micro
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val deviceId: JsonField<String>,
                        private val groupId: JsonField<String>,
                        private val kind: JsonField<String>,
                        private val label: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("deviceId")
                            @ExcludeMissing
                            deviceId: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("groupId")
                            @ExcludeMissing
                            groupId: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("kind")
                            @ExcludeMissing
                            kind: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("label")
                            @ExcludeMissing
                            label: JsonField<String> = JsonMissing.of(),
                        ) : this(deviceId, groupId, kind, label, mutableMapOf())

                        fun deviceId(): String? = deviceId.getNullable("deviceId")

                        fun groupId(): String? = groupId.getNullable("groupId")

                        fun kind(): String? = kind.getNullable("kind")

                        fun label(): String? = label.getNullable("label")

                        @JsonProperty("deviceId")
                        @ExcludeMissing
                        fun _deviceId(): JsonField<String> = deviceId

                        @JsonProperty("groupId")
                        @ExcludeMissing
                        fun _groupId(): JsonField<String> = groupId

                        @JsonProperty("kind") @ExcludeMissing fun _kind(): JsonField<String> = kind

                        @JsonProperty("label")
                        @ExcludeMissing
                        fun _label(): JsonField<String> = label

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
                             * Returns a mutable builder for constructing an instance of [Micro].
                             */
                            @JvmStatic fun builder() = Builder()
                        }

                        /** A builder for [Micro]. */
                        class Builder internal constructor() {

                            private var deviceId: JsonField<String>? = null
                            private var groupId: JsonField<String>? = null
                            private var kind: JsonField<String>? = null
                            private var label: JsonField<String>? = null
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            @JvmSynthetic
                            internal fun from(micro: Micro) = apply {
                                this.deviceId = micro.deviceId
                                this.groupId = micro.groupId
                                this.kind = micro.kind
                                this.label = micro.label
                                this.additionalProperties =
                                    micro.additionalProperties.toMutableMap()
                            }

                            fun deviceId(deviceId: String?) =
                                deviceId(JsonField.ofNullable(deviceId))

                            fun deviceId(deviceId: JsonField<String>) = apply {
                                this.deviceId = deviceId
                            }

                            fun groupId(groupId: String?) = groupId(JsonField.ofNullable(groupId))

                            fun groupId(groupId: JsonField<String>) = apply {
                                this.groupId = groupId
                            }

                            fun kind(kind: String?) = kind(JsonField.ofNullable(kind))

                            fun kind(kind: JsonField<String>) = apply { this.kind = kind }

                            fun label(label: String?) = label(JsonField.ofNullable(label))

                            fun label(label: JsonField<String>) = apply { this.label = label }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply { this.additionalProperties.putAll(additionalProperties) }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            fun build(): Micro =
                                Micro(
                                    checkRequired("deviceId", deviceId),
                                    checkRequired("groupId", groupId),
                                    checkRequired("kind", kind),
                                    checkRequired("label", label),
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

                        fun validate(): Micro = apply {
                            if (validated) {
                                return@apply
                            }

                            deviceId()
                            groupId()
                            kind()
                            label()
                            validated = true
                        }

                        @JvmSynthetic
                        internal fun validity(): Int =
                            (if (deviceId.asKnown().isPresent) 1 else 0) +
                                (if (groupId.asKnown().isPresent) 1 else 0) +
                                (if (kind.asKnown().isPresent) 1 else 0) +
                                (if (label.asKnown().isPresent) 1 else 0)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Micro &&
                                this.deviceId == other.deviceId &&
                                this.groupId == other.groupId &&
                                this.kind == other.kind &&
                                this.label == other.label &&
                                this.additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(deviceId, groupId, kind, label, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Micro{deviceId=$deviceId, groupId=$groupId, kind=$kind, label=$label, additionalProperties=$additionalProperties}"
                    }

                    class Speaker
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val deviceId: JsonField<String>,
                        private val groupId: JsonField<String>,
                        private val kind: JsonField<String>,
                        private val label: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("deviceId")
                            @ExcludeMissing
                            deviceId: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("groupId")
                            @ExcludeMissing
                            groupId: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("kind")
                            @ExcludeMissing
                            kind: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("label")
                            @ExcludeMissing
                            label: JsonField<String> = JsonMissing.of(),
                        ) : this(deviceId, groupId, kind, label, mutableMapOf())

                        fun deviceId(): String? = deviceId.getNullable("deviceId")

                        fun groupId(): String? = groupId.getNullable("groupId")

                        fun kind(): String? = kind.getNullable("kind")

                        fun label(): String? = label.getNullable("label")

                        @JsonProperty("deviceId")
                        @ExcludeMissing
                        fun _deviceId(): JsonField<String> = deviceId

                        @JsonProperty("groupId")
                        @ExcludeMissing
                        fun _groupId(): JsonField<String> = groupId

                        @JsonProperty("kind") @ExcludeMissing fun _kind(): JsonField<String> = kind

                        @JsonProperty("label")
                        @ExcludeMissing
                        fun _label(): JsonField<String> = label

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
                             * Returns a mutable builder for constructing an instance of [Speaker].
                             */
                            @JvmStatic fun builder() = Builder()
                        }

                        /** A builder for [Speaker]. */
                        class Builder internal constructor() {

                            private var deviceId: JsonField<String>? = null
                            private var groupId: JsonField<String>? = null
                            private var kind: JsonField<String>? = null
                            private var label: JsonField<String>? = null
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            @JvmSynthetic
                            internal fun from(speaker: Speaker) = apply {
                                this.deviceId = speaker.deviceId
                                this.groupId = speaker.groupId
                                this.kind = speaker.kind
                                this.label = speaker.label
                                this.additionalProperties =
                                    speaker.additionalProperties.toMutableMap()
                            }

                            fun deviceId(deviceId: String?) =
                                deviceId(JsonField.ofNullable(deviceId))

                            fun deviceId(deviceId: JsonField<String>) = apply {
                                this.deviceId = deviceId
                            }

                            fun groupId(groupId: String?) = groupId(JsonField.ofNullable(groupId))

                            fun groupId(groupId: JsonField<String>) = apply {
                                this.groupId = groupId
                            }

                            fun kind(kind: String?) = kind(JsonField.ofNullable(kind))

                            fun kind(kind: JsonField<String>) = apply { this.kind = kind }

                            fun label(label: String?) = label(JsonField.ofNullable(label))

                            fun label(label: JsonField<String>) = apply { this.label = label }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply { this.additionalProperties.putAll(additionalProperties) }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            fun build(): Speaker =
                                Speaker(
                                    checkRequired("deviceId", deviceId),
                                    checkRequired("groupId", groupId),
                                    checkRequired("kind", kind),
                                    checkRequired("label", label),
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

                        fun validate(): Speaker = apply {
                            if (validated) {
                                return@apply
                            }

                            deviceId()
                            groupId()
                            kind()
                            label()
                            validated = true
                        }

                        @JvmSynthetic
                        internal fun validity(): Int =
                            (if (deviceId.asKnown().isPresent) 1 else 0) +
                                (if (groupId.asKnown().isPresent) 1 else 0) +
                                (if (kind.asKnown().isPresent) 1 else 0) +
                                (if (label.asKnown().isPresent) 1 else 0)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Speaker &&
                                this.deviceId == other.deviceId &&
                                this.groupId == other.groupId &&
                                this.kind == other.kind &&
                                this.label == other.label &&
                                this.additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(deviceId, groupId, kind, label, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Speaker{deviceId=$deviceId, groupId=$groupId, kind=$kind, label=$label, additionalProperties=$additionalProperties}"
                    }

                    class Webcam
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val deviceId: JsonField<String>,
                        private val groupId: JsonField<String>,
                        private val kind: JsonField<String>,
                        private val label: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("deviceId")
                            @ExcludeMissing
                            deviceId: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("groupId")
                            @ExcludeMissing
                            groupId: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("kind")
                            @ExcludeMissing
                            kind: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("label")
                            @ExcludeMissing
                            label: JsonField<String> = JsonMissing.of(),
                        ) : this(deviceId, groupId, kind, label, mutableMapOf())

                        fun deviceId(): String? = deviceId.getNullable("deviceId")

                        fun groupId(): String? = groupId.getNullable("groupId")

                        fun kind(): String? = kind.getNullable("kind")

                        fun label(): String? = label.getNullable("label")

                        @JsonProperty("deviceId")
                        @ExcludeMissing
                        fun _deviceId(): JsonField<String> = deviceId

                        @JsonProperty("groupId")
                        @ExcludeMissing
                        fun _groupId(): JsonField<String> = groupId

                        @JsonProperty("kind") @ExcludeMissing fun _kind(): JsonField<String> = kind

                        @JsonProperty("label")
                        @ExcludeMissing
                        fun _label(): JsonField<String> = label

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
                             * Returns a mutable builder for constructing an instance of [Webcam].
                             */
                            @JvmStatic fun builder() = Builder()
                        }

                        /** A builder for [Webcam]. */
                        class Builder internal constructor() {

                            private var deviceId: JsonField<String>? = null
                            private var groupId: JsonField<String>? = null
                            private var kind: JsonField<String>? = null
                            private var label: JsonField<String>? = null
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            @JvmSynthetic
                            internal fun from(webcam: Webcam) = apply {
                                this.deviceId = webcam.deviceId
                                this.groupId = webcam.groupId
                                this.kind = webcam.kind
                                this.label = webcam.label
                                this.additionalProperties =
                                    webcam.additionalProperties.toMutableMap()
                            }

                            fun deviceId(deviceId: String?) =
                                deviceId(JsonField.ofNullable(deviceId))

                            fun deviceId(deviceId: JsonField<String>) = apply {
                                this.deviceId = deviceId
                            }

                            fun groupId(groupId: String?) = groupId(JsonField.ofNullable(groupId))

                            fun groupId(groupId: JsonField<String>) = apply {
                                this.groupId = groupId
                            }

                            fun kind(kind: String?) = kind(JsonField.ofNullable(kind))

                            fun kind(kind: JsonField<String>) = apply { this.kind = kind }

                            fun label(label: String?) = label(JsonField.ofNullable(label))

                            fun label(label: JsonField<String>) = apply { this.label = label }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply { this.additionalProperties.putAll(additionalProperties) }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            fun build(): Webcam =
                                Webcam(
                                    checkRequired("deviceId", deviceId),
                                    checkRequired("groupId", groupId),
                                    checkRequired("kind", kind),
                                    checkRequired("label", label),
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

                        fun validate(): Webcam = apply {
                            if (validated) {
                                return@apply
                            }

                            deviceId()
                            groupId()
                            kind()
                            label()
                            validated = true
                        }

                        @JvmSynthetic
                        internal fun validity(): Int =
                            (if (deviceId.asKnown().isPresent) 1 else 0) +
                                (if (groupId.asKnown().isPresent) 1 else 0) +
                                (if (kind.asKnown().isPresent) 1 else 0) +
                                (if (label.asKnown().isPresent) 1 else 0)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Webcam &&
                                this.deviceId == other.deviceId &&
                                this.groupId == other.groupId &&
                                this.kind == other.kind &&
                                this.label == other.label &&
                                this.additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(deviceId, groupId, kind, label, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Webcam{deviceId=$deviceId, groupId=$groupId, kind=$kind, label=$label, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is MultimediaDevices &&
                            this.micros == other.micros &&
                            this.speakers == other.speakers &&
                            this.webcams == other.webcams &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(micros, speakers, webcams, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "MultimediaDevices{micros=$micros, speakers=$speakers, webcams=$webcams, additionalProperties=$additionalProperties}"
                }

                class Navigator
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val appCodeName: JsonField<String>,
                    private val appName: JsonField<String>,
                    private val appVersion: JsonField<String>,
                    private val deviceMemory: JsonField<Double>,
                    private val extraProperties: JsonField<ExtraProperties>,
                    private val hardwareConcurrency: JsonField<Double>,
                    private val language: JsonField<String>,
                    private val languages: JsonField<List<String>>,
                    private val maxTouchPoints: JsonField<Double>,
                    private val oscpu: JsonField<String>,
                    private val platform: JsonField<String>,
                    private val product: JsonField<String>,
                    private val productSub: JsonField<String>,
                    private val userAgent: JsonField<String>,
                    private val userAgentData: JsonField<UserAgentData>,
                    private val vendor: JsonField<String>,
                    private val vendorSub: JsonField<String>,
                    private val webdriver: JsonField<Boolean>,
                    private val doNotTrack: JsonField<String>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("appCodeName")
                        @ExcludeMissing
                        appCodeName: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("appName")
                        @ExcludeMissing
                        appName: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("appVersion")
                        @ExcludeMissing
                        appVersion: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("deviceMemory")
                        @ExcludeMissing
                        deviceMemory: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("extraProperties")
                        @ExcludeMissing
                        extraProperties: JsonField<ExtraProperties> = JsonMissing.of(),
                        @JsonProperty("hardwareConcurrency")
                        @ExcludeMissing
                        hardwareConcurrency: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("language")
                        @ExcludeMissing
                        language: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("languages")
                        @ExcludeMissing
                        languages: JsonField<List<String>> = JsonMissing.of(),
                        @JsonProperty("maxTouchPoints")
                        @ExcludeMissing
                        maxTouchPoints: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("oscpu")
                        @ExcludeMissing
                        oscpu: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("platform")
                        @ExcludeMissing
                        platform: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("product")
                        @ExcludeMissing
                        product: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("productSub")
                        @ExcludeMissing
                        productSub: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("userAgent")
                        @ExcludeMissing
                        userAgent: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("userAgentData")
                        @ExcludeMissing
                        userAgentData: JsonField<UserAgentData> = JsonMissing.of(),
                        @JsonProperty("vendor")
                        @ExcludeMissing
                        vendor: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("vendorSub")
                        @ExcludeMissing
                        vendorSub: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("webdriver")
                        @ExcludeMissing
                        webdriver: JsonField<Boolean> = JsonMissing.of(),
                        @JsonProperty("doNotTrack")
                        @ExcludeMissing
                        doNotTrack: JsonField<String> = JsonMissing.of(),
                    ) : this(
                        appCodeName,
                        appName,
                        appVersion,
                        deviceMemory,
                        extraProperties,
                        hardwareConcurrency,
                        language,
                        languages,
                        maxTouchPoints,
                        oscpu,
                        platform,
                        product,
                        productSub,
                        userAgent,
                        userAgentData,
                        vendor,
                        vendorSub,
                        webdriver,
                        doNotTrack,
                        mutableMapOf(),
                    )

                    fun appCodeName(): String? = appCodeName.getNullable("appCodeName")

                    fun appName(): String? = appName.getNullable("appName")

                    fun appVersion(): String? = appVersion.getNullable("appVersion")

                    fun deviceMemory(): Double? = deviceMemory.getNullable("deviceMemory")

                    fun extraProperties(): ExtraProperties =
                        extraProperties.getRequired("extraProperties")

                    fun hardwareConcurrency(): Double? =
                        hardwareConcurrency.getNullable("hardwareConcurrency")

                    fun language(): String? = language.getNullable("language")

                    fun languages(): List<String> = languages.getRequired("languages")

                    fun maxTouchPoints(): Double? = maxTouchPoints.getNullable("maxTouchPoints")

                    fun oscpu(): String? = oscpu.getNullable("oscpu")

                    fun platform(): String? = platform.getNullable("platform")

                    fun product(): String? = product.getNullable("product")

                    fun productSub(): String? = productSub.getNullable("productSub")

                    fun userAgent(): String? = userAgent.getNullable("userAgent")

                    fun userAgentData(): UserAgentData = userAgentData.getRequired("userAgentData")

                    fun vendor(): String? = vendor.getNullable("vendor")

                    fun vendorSub(): String? = vendorSub.getNullable("vendorSub")

                    fun webdriver(): Boolean? = webdriver.getNullable("webdriver")

                    fun doNotTrack(): String? = doNotTrack.getNullable("doNotTrack")

                    @JsonProperty("appCodeName")
                    @ExcludeMissing
                    fun _appCodeName(): JsonField<String> = appCodeName

                    @JsonProperty("appName")
                    @ExcludeMissing
                    fun _appName(): JsonField<String> = appName

                    @JsonProperty("appVersion")
                    @ExcludeMissing
                    fun _appVersion(): JsonField<String> = appVersion

                    @JsonProperty("deviceMemory")
                    @ExcludeMissing
                    fun _deviceMemory(): JsonField<Double> = deviceMemory

                    @JsonProperty("extraProperties")
                    @ExcludeMissing
                    fun _extraProperties(): JsonField<ExtraProperties> = extraProperties

                    @JsonProperty("hardwareConcurrency")
                    @ExcludeMissing
                    fun _hardwareConcurrency(): JsonField<Double> = hardwareConcurrency

                    @JsonProperty("language")
                    @ExcludeMissing
                    fun _language(): JsonField<String> = language

                    @JsonProperty("languages")
                    @ExcludeMissing
                    fun _languages(): JsonField<List<String>> = languages

                    @JsonProperty("maxTouchPoints")
                    @ExcludeMissing
                    fun _maxTouchPoints(): JsonField<Double> = maxTouchPoints

                    @JsonProperty("oscpu") @ExcludeMissing fun _oscpu(): JsonField<String> = oscpu

                    @JsonProperty("platform")
                    @ExcludeMissing
                    fun _platform(): JsonField<String> = platform

                    @JsonProperty("product")
                    @ExcludeMissing
                    fun _product(): JsonField<String> = product

                    @JsonProperty("productSub")
                    @ExcludeMissing
                    fun _productSub(): JsonField<String> = productSub

                    @JsonProperty("userAgent")
                    @ExcludeMissing
                    fun _userAgent(): JsonField<String> = userAgent

                    @JsonProperty("userAgentData")
                    @ExcludeMissing
                    fun _userAgentData(): JsonField<UserAgentData> = userAgentData

                    @JsonProperty("vendor")
                    @ExcludeMissing
                    fun _vendor(): JsonField<String> = vendor

                    @JsonProperty("vendorSub")
                    @ExcludeMissing
                    fun _vendorSub(): JsonField<String> = vendorSub

                    @JsonProperty("webdriver")
                    @ExcludeMissing
                    fun _webdriver(): JsonField<Boolean> = webdriver

                    @JsonProperty("doNotTrack")
                    @ExcludeMissing
                    fun _doNotTrack(): JsonField<String> = doNotTrack

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
                         * Returns a mutable builder for constructing an instance of [Navigator].
                         */
                        @JvmStatic fun builder() = Builder()
                    }

                    /** A builder for [Navigator]. */
                    class Builder internal constructor() {

                        private var appCodeName: JsonField<String>? = null
                        private var appName: JsonField<String>? = null
                        private var appVersion: JsonField<String>? = null
                        private var deviceMemory: JsonField<Double>? = null
                        private var extraProperties: JsonField<ExtraProperties>? = null
                        private var hardwareConcurrency: JsonField<Double>? = null
                        private var language: JsonField<String>? = null
                        private var languages: JsonField<MutableList<String>>? = null
                        private var maxTouchPoints: JsonField<Double>? = null
                        private var oscpu: JsonField<String>? = null
                        private var platform: JsonField<String>? = null
                        private var product: JsonField<String>? = null
                        private var productSub: JsonField<String>? = null
                        private var userAgent: JsonField<String>? = null
                        private var userAgentData: JsonField<UserAgentData>? = null
                        private var vendor: JsonField<String>? = null
                        private var vendorSub: JsonField<String>? = null
                        private var webdriver: JsonField<Boolean>? = null
                        private var doNotTrack: JsonField<String> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(navigator: Navigator) = apply {
                            this.appCodeName = navigator.appCodeName
                            this.appName = navigator.appName
                            this.appVersion = navigator.appVersion
                            this.deviceMemory = navigator.deviceMemory
                            this.extraProperties = navigator.extraProperties
                            this.hardwareConcurrency = navigator.hardwareConcurrency
                            this.language = navigator.language
                            this.languages = navigator.languages.map { it.toMutableList() }
                            this.maxTouchPoints = navigator.maxTouchPoints
                            this.oscpu = navigator.oscpu
                            this.platform = navigator.platform
                            this.product = navigator.product
                            this.productSub = navigator.productSub
                            this.userAgent = navigator.userAgent
                            this.userAgentData = navigator.userAgentData
                            this.vendor = navigator.vendor
                            this.vendorSub = navigator.vendorSub
                            this.webdriver = navigator.webdriver
                            this.doNotTrack = navigator.doNotTrack
                            this.additionalProperties =
                                navigator.additionalProperties.toMutableMap()
                        }

                        fun appCodeName(appCodeName: String?) =
                            appCodeName(JsonField.ofNullable(appCodeName))

                        fun appCodeName(appCodeName: JsonField<String>) = apply {
                            this.appCodeName = appCodeName
                        }

                        fun appName(appName: String?) = appName(JsonField.ofNullable(appName))

                        fun appName(appName: JsonField<String>) = apply { this.appName = appName }

                        fun appVersion(appVersion: String?) =
                            appVersion(JsonField.ofNullable(appVersion))

                        fun appVersion(appVersion: JsonField<String>) = apply {
                            this.appVersion = appVersion
                        }

                        fun deviceMemory(deviceMemory: Double?) =
                            deviceMemory(JsonField.ofNullable(deviceMemory))

                        fun deviceMemory(deviceMemory: Double) =
                            deviceMemory(deviceMemory as Double?)

                        fun deviceMemory(deviceMemory: JsonField<Double>) = apply {
                            this.deviceMemory = deviceMemory
                        }

                        fun extraProperties(extraProperties: ExtraProperties) =
                            extraProperties(JsonField.of(extraProperties))

                        fun extraProperties(extraProperties: JsonField<ExtraProperties>) = apply {
                            this.extraProperties = extraProperties
                        }

                        fun hardwareConcurrency(hardwareConcurrency: Double?) =
                            hardwareConcurrency(JsonField.ofNullable(hardwareConcurrency))

                        fun hardwareConcurrency(hardwareConcurrency: Double) =
                            hardwareConcurrency(hardwareConcurrency as Double?)

                        fun hardwareConcurrency(hardwareConcurrency: JsonField<Double>) = apply {
                            this.hardwareConcurrency = hardwareConcurrency
                        }

                        fun language(language: String?) = language(JsonField.ofNullable(language))

                        fun language(language: JsonField<String>) = apply {
                            this.language = language
                        }

                        fun languages(languages: List<String>) = languages(JsonField.of(languages))

                        fun languages(languages: JsonField<List<String>>) = apply {
                            this.languages = languages.map { it.toMutableList() }
                        }

                        fun addLanguage(language: String) = apply {
                            this.languages =
                                (this.languages ?: JsonField.of(mutableListOf())).also {
                                    checkKnown("languages", it).add(language)
                                }
                        }

                        fun maxTouchPoints(maxTouchPoints: Double?) =
                            maxTouchPoints(JsonField.ofNullable(maxTouchPoints))

                        fun maxTouchPoints(maxTouchPoints: Double) =
                            maxTouchPoints(maxTouchPoints as Double?)

                        fun maxTouchPoints(maxTouchPoints: JsonField<Double>) = apply {
                            this.maxTouchPoints = maxTouchPoints
                        }

                        fun oscpu(oscpu: String?) = oscpu(JsonField.ofNullable(oscpu))

                        fun oscpu(oscpu: JsonField<String>) = apply { this.oscpu = oscpu }

                        fun platform(platform: String?) = platform(JsonField.ofNullable(platform))

                        fun platform(platform: JsonField<String>) = apply {
                            this.platform = platform
                        }

                        fun product(product: String?) = product(JsonField.ofNullable(product))

                        fun product(product: JsonField<String>) = apply { this.product = product }

                        fun productSub(productSub: String?) =
                            productSub(JsonField.ofNullable(productSub))

                        fun productSub(productSub: JsonField<String>) = apply {
                            this.productSub = productSub
                        }

                        fun userAgent(userAgent: String?) =
                            userAgent(JsonField.ofNullable(userAgent))

                        fun userAgent(userAgent: JsonField<String>) = apply {
                            this.userAgent = userAgent
                        }

                        fun userAgentData(userAgentData: UserAgentData) =
                            userAgentData(JsonField.of(userAgentData))

                        fun userAgentData(userAgentData: JsonField<UserAgentData>) = apply {
                            this.userAgentData = userAgentData
                        }

                        fun vendor(vendor: String?) = vendor(JsonField.ofNullable(vendor))

                        fun vendor(vendor: JsonField<String>) = apply { this.vendor = vendor }

                        fun vendorSub(vendorSub: String?) =
                            vendorSub(JsonField.ofNullable(vendorSub))

                        fun vendorSub(vendorSub: JsonField<String>) = apply {
                            this.vendorSub = vendorSub
                        }

                        fun webdriver(webdriver: Boolean?) =
                            webdriver(JsonField.ofNullable(webdriver))

                        fun webdriver(webdriver: Boolean) = webdriver(webdriver as Boolean?)

                        fun webdriver(webdriver: JsonField<Boolean>) = apply {
                            this.webdriver = webdriver
                        }

                        fun doNotTrack(doNotTrack: String?) =
                            doNotTrack(JsonField.ofNullable(doNotTrack))

                        fun doNotTrack(doNotTrack: JsonField<String>) = apply {
                            this.doNotTrack = doNotTrack
                        }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): Navigator =
                            Navigator(
                                checkRequired("appCodeName", appCodeName),
                                checkRequired("appName", appName),
                                checkRequired("appVersion", appVersion),
                                checkRequired("deviceMemory", deviceMemory),
                                checkRequired("extraProperties", extraProperties),
                                checkRequired("hardwareConcurrency", hardwareConcurrency),
                                checkRequired("language", language),
                                checkRequired("languages", languages).map { it.toImmutable() },
                                checkRequired("maxTouchPoints", maxTouchPoints),
                                checkRequired("oscpu", oscpu),
                                checkRequired("platform", platform),
                                checkRequired("product", product),
                                checkRequired("productSub", productSub),
                                checkRequired("userAgent", userAgent),
                                checkRequired("userAgentData", userAgentData),
                                checkRequired("vendor", vendor),
                                checkRequired("vendorSub", vendorSub),
                                checkRequired("webdriver", webdriver),
                                doNotTrack,
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

                    fun validate(): Navigator = apply {
                        if (validated) {
                            return@apply
                        }

                        appCodeName()
                        appName()
                        appVersion()
                        deviceMemory()
                        extraProperties().validate()
                        hardwareConcurrency()
                        language()
                        languages()
                        maxTouchPoints()
                        oscpu()
                        platform()
                        product()
                        productSub()
                        userAgent()
                        userAgentData().validate()
                        vendor()
                        vendorSub()
                        webdriver()
                        doNotTrack()
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        (if (appCodeName.asKnown().isPresent) 1 else 0) +
                            (if (appName.asKnown().isPresent) 1 else 0) +
                            (if (appVersion.asKnown().isPresent) 1 else 0) +
                            (if (deviceMemory.asKnown().isPresent) 1 else 0) +
                            (extraProperties.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                            (if (hardwareConcurrency.asKnown().isPresent) 1 else 0) +
                            (if (language.asKnown().isPresent) 1 else 0) +
                            (languages.asKnown().getOrNull()?.let { it.size } ?: 0) +
                            (if (maxTouchPoints.asKnown().isPresent) 1 else 0) +
                            (if (oscpu.asKnown().isPresent) 1 else 0) +
                            (if (platform.asKnown().isPresent) 1 else 0) +
                            (if (product.asKnown().isPresent) 1 else 0) +
                            (if (productSub.asKnown().isPresent) 1 else 0) +
                            (if (userAgent.asKnown().isPresent) 1 else 0) +
                            (userAgentData.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                            (if (vendor.asKnown().isPresent) 1 else 0) +
                            (if (vendorSub.asKnown().isPresent) 1 else 0) +
                            (if (webdriver.asKnown().isPresent) 1 else 0) +
                            (if (doNotTrack.asKnown().isPresent) 1 else 0)

                    class ExtraProperties
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val globalPrivacyControl: JsonField<Boolean>,
                        private val installedApps: JsonField<List<String>>,
                        private val pdfViewerEnabled: JsonField<Boolean>,
                        private val vendorFlavors: JsonField<List<String>>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("globalPrivacyControl")
                            @ExcludeMissing
                            globalPrivacyControl: JsonField<Boolean> = JsonMissing.of(),
                            @JsonProperty("installedApps")
                            @ExcludeMissing
                            installedApps: JsonField<List<String>> = JsonMissing.of(),
                            @JsonProperty("pdfViewerEnabled")
                            @ExcludeMissing
                            pdfViewerEnabled: JsonField<Boolean> = JsonMissing.of(),
                            @JsonProperty("vendorFlavors")
                            @ExcludeMissing
                            vendorFlavors: JsonField<List<String>> = JsonMissing.of(),
                        ) : this(
                            globalPrivacyControl,
                            installedApps,
                            pdfViewerEnabled,
                            vendorFlavors,
                            mutableMapOf(),
                        )

                        fun globalPrivacyControl(): Boolean? =
                            globalPrivacyControl.getNullable("globalPrivacyControl")

                        fun installedApps(): List<String> =
                            installedApps.getRequired("installedApps")

                        fun pdfViewerEnabled(): Boolean? =
                            pdfViewerEnabled.getNullable("pdfViewerEnabled")

                        fun vendorFlavors(): List<String> =
                            vendorFlavors.getRequired("vendorFlavors")

                        @JsonProperty("globalPrivacyControl")
                        @ExcludeMissing
                        fun _globalPrivacyControl(): JsonField<Boolean> = globalPrivacyControl

                        @JsonProperty("installedApps")
                        @ExcludeMissing
                        fun _installedApps(): JsonField<List<String>> = installedApps

                        @JsonProperty("pdfViewerEnabled")
                        @ExcludeMissing
                        fun _pdfViewerEnabled(): JsonField<Boolean> = pdfViewerEnabled

                        @JsonProperty("vendorFlavors")
                        @ExcludeMissing
                        fun _vendorFlavors(): JsonField<List<String>> = vendorFlavors

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
                             * [ExtraProperties].
                             */
                            @JvmStatic fun builder() = Builder()
                        }

                        /** A builder for [ExtraProperties]. */
                        class Builder internal constructor() {

                            private var globalPrivacyControl: JsonField<Boolean>? = null
                            private var installedApps: JsonField<MutableList<String>>? = null
                            private var pdfViewerEnabled: JsonField<Boolean>? = null
                            private var vendorFlavors: JsonField<MutableList<String>>? = null
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            @JvmSynthetic
                            internal fun from(extraProperties: ExtraProperties) = apply {
                                this.globalPrivacyControl = extraProperties.globalPrivacyControl
                                this.installedApps =
                                    extraProperties.installedApps.map { it.toMutableList() }
                                this.pdfViewerEnabled = extraProperties.pdfViewerEnabled
                                this.vendorFlavors =
                                    extraProperties.vendorFlavors.map { it.toMutableList() }
                                this.additionalProperties =
                                    extraProperties.additionalProperties.toMutableMap()
                            }

                            fun globalPrivacyControl(globalPrivacyControl: Boolean?) =
                                globalPrivacyControl(JsonField.ofNullable(globalPrivacyControl))

                            fun globalPrivacyControl(globalPrivacyControl: Boolean) =
                                globalPrivacyControl(globalPrivacyControl as Boolean?)

                            fun globalPrivacyControl(globalPrivacyControl: JsonField<Boolean>) =
                                apply {
                                    this.globalPrivacyControl = globalPrivacyControl
                                }

                            fun installedApps(installedApps: List<String>) =
                                installedApps(JsonField.of(installedApps))

                            fun installedApps(installedApps: JsonField<List<String>>) = apply {
                                this.installedApps = installedApps.map { it.toMutableList() }
                            }

                            fun addInstalledApp(installedApp: String) = apply {
                                this.installedApps =
                                    (this.installedApps ?: JsonField.of(mutableListOf())).also {
                                        checkKnown("installedApps", it).add(installedApp)
                                    }
                            }

                            fun pdfViewerEnabled(pdfViewerEnabled: Boolean?) =
                                pdfViewerEnabled(JsonField.ofNullable(pdfViewerEnabled))

                            fun pdfViewerEnabled(pdfViewerEnabled: Boolean) =
                                pdfViewerEnabled(pdfViewerEnabled as Boolean?)

                            fun pdfViewerEnabled(pdfViewerEnabled: JsonField<Boolean>) = apply {
                                this.pdfViewerEnabled = pdfViewerEnabled
                            }

                            fun vendorFlavors(vendorFlavors: List<String>) =
                                vendorFlavors(JsonField.of(vendorFlavors))

                            fun vendorFlavors(vendorFlavors: JsonField<List<String>>) = apply {
                                this.vendorFlavors = vendorFlavors.map { it.toMutableList() }
                            }

                            fun addVendorFlavor(vendorFlavor: String) = apply {
                                this.vendorFlavors =
                                    (this.vendorFlavors ?: JsonField.of(mutableListOf())).also {
                                        checkKnown("vendorFlavors", it).add(vendorFlavor)
                                    }
                            }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply { this.additionalProperties.putAll(additionalProperties) }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            fun build(): ExtraProperties =
                                ExtraProperties(
                                    checkRequired("globalPrivacyControl", globalPrivacyControl),
                                    checkRequired("installedApps", installedApps).map {
                                        it.toImmutable()
                                    },
                                    checkRequired("pdfViewerEnabled", pdfViewerEnabled),
                                    checkRequired("vendorFlavors", vendorFlavors).map {
                                        it.toImmutable()
                                    },
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

                        fun validate(): ExtraProperties = apply {
                            if (validated) {
                                return@apply
                            }

                            globalPrivacyControl()
                            installedApps()
                            pdfViewerEnabled()
                            vendorFlavors()
                            validated = true
                        }

                        @JvmSynthetic
                        internal fun validity(): Int =
                            (if (globalPrivacyControl.asKnown().isPresent) 1 else 0) +
                                (installedApps.asKnown().getOrNull()?.let { it.size } ?: 0) +
                                (if (pdfViewerEnabled.asKnown().isPresent) 1 else 0) +
                                (vendorFlavors.asKnown().getOrNull()?.let { it.size } ?: 0)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is ExtraProperties &&
                                this.globalPrivacyControl == other.globalPrivacyControl &&
                                this.installedApps == other.installedApps &&
                                this.pdfViewerEnabled == other.pdfViewerEnabled &&
                                this.vendorFlavors == other.vendorFlavors &&
                                this.additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(
                                globalPrivacyControl,
                                installedApps,
                                pdfViewerEnabled,
                                vendorFlavors,
                                additionalProperties,
                            )
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "ExtraProperties{globalPrivacyControl=$globalPrivacyControl, installedApps=$installedApps, pdfViewerEnabled=$pdfViewerEnabled, vendorFlavors=$vendorFlavors, additionalProperties=$additionalProperties}"
                    }

                    class UserAgentData
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val brands: JsonField<List<Brand>>,
                        private val mobile: JsonField<Boolean>,
                        private val platform: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("brands")
                            @ExcludeMissing
                            brands: JsonField<List<Brand>> = JsonMissing.of(),
                            @JsonProperty("mobile")
                            @ExcludeMissing
                            mobile: JsonField<Boolean> = JsonMissing.of(),
                            @JsonProperty("platform")
                            @ExcludeMissing
                            platform: JsonField<String> = JsonMissing.of(),
                        ) : this(brands, mobile, platform, mutableMapOf())

                        fun brands(): List<Brand> = brands.getRequired("brands")

                        fun mobile(): Boolean? = mobile.getNullable("mobile")

                        fun platform(): String? = platform.getNullable("platform")

                        @JsonProperty("brands")
                        @ExcludeMissing
                        fun _brands(): JsonField<List<Brand>> = brands

                        @JsonProperty("mobile")
                        @ExcludeMissing
                        fun _mobile(): JsonField<Boolean> = mobile

                        @JsonProperty("platform")
                        @ExcludeMissing
                        fun _platform(): JsonField<String> = platform

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
                             * [UserAgentData].
                             */
                            @JvmStatic fun builder() = Builder()
                        }

                        /** A builder for [UserAgentData]. */
                        class Builder internal constructor() {

                            private var brands: JsonField<MutableList<Brand>>? = null
                            private var mobile: JsonField<Boolean>? = null
                            private var platform: JsonField<String>? = null
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            @JvmSynthetic
                            internal fun from(userAgentData: UserAgentData) = apply {
                                this.brands = userAgentData.brands.map { it.toMutableList() }
                                this.mobile = userAgentData.mobile
                                this.platform = userAgentData.platform
                                this.additionalProperties =
                                    userAgentData.additionalProperties.toMutableMap()
                            }

                            fun brands(brands: List<Brand>) = brands(JsonField.of(brands))

                            fun brands(brands: JsonField<List<Brand>>) = apply {
                                this.brands = brands.map { it.toMutableList() }
                            }

                            fun addBrand(brand: Brand) = apply {
                                this.brands =
                                    (this.brands ?: JsonField.of(mutableListOf())).also {
                                        checkKnown("brands", it).add(brand)
                                    }
                            }

                            fun mobile(mobile: Boolean?) = mobile(JsonField.ofNullable(mobile))

                            fun mobile(mobile: Boolean) = mobile(mobile as Boolean?)

                            fun mobile(mobile: JsonField<Boolean>) = apply { this.mobile = mobile }

                            fun platform(platform: String?) =
                                platform(JsonField.ofNullable(platform))

                            fun platform(platform: JsonField<String>) = apply {
                                this.platform = platform
                            }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply { this.additionalProperties.putAll(additionalProperties) }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            fun build(): UserAgentData =
                                UserAgentData(
                                    checkRequired("brands", brands).map { it.toImmutable() },
                                    checkRequired("mobile", mobile),
                                    checkRequired("platform", platform),
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

                        fun validate(): UserAgentData = apply {
                            if (validated) {
                                return@apply
                            }

                            brands().forEach { it.validate() }
                            mobile()
                            platform()
                            validated = true
                        }

                        @JvmSynthetic
                        internal fun validity(): Int =
                            (brands.asKnown().getOrNull()?.let {
                                it.sumOf { (it.validity()).toInt() }
                            } ?: 0) +
                                (if (mobile.asKnown().isPresent) 1 else 0) +
                                (if (platform.asKnown().isPresent) 1 else 0)

                        class Brand
                        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                        private constructor(
                            private val brand: JsonField<String>,
                            private val version: JsonField<String>,
                            private val additionalProperties: MutableMap<String, JsonValue>,
                        ) {

                            @JsonCreator
                            private constructor(
                                @JsonProperty("brand")
                                @ExcludeMissing
                                brand: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("version")
                                @ExcludeMissing
                                version: JsonField<String> = JsonMissing.of(),
                            ) : this(brand, version, mutableMapOf())

                            fun brand(): String? = brand.getNullable("brand")

                            fun version(): String? = version.getNullable("version")

                            @JsonProperty("brand")
                            @ExcludeMissing
                            fun _brand(): JsonField<String> = brand

                            @JsonProperty("version")
                            @ExcludeMissing
                            fun _version(): JsonField<String> = version

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
                                 * [Brand].
                                 */
                                @JvmStatic fun builder() = Builder()
                            }

                            /** A builder for [Brand]. */
                            class Builder internal constructor() {

                                private var brand: JsonField<String>? = null
                                private var version: JsonField<String>? = null
                                private var additionalProperties: MutableMap<String, JsonValue> =
                                    mutableMapOf()

                                @JvmSynthetic
                                internal fun from(brand: Brand) = apply {
                                    this.brand = brand.brand
                                    this.version = brand.version
                                    this.additionalProperties =
                                        brand.additionalProperties.toMutableMap()
                                }

                                fun brand(brand: String?) = brand(JsonField.ofNullable(brand))

                                fun brand(brand: JsonField<String>) = apply { this.brand = brand }

                                fun version(version: String?) =
                                    version(JsonField.ofNullable(version))

                                fun version(version: JsonField<String>) = apply {
                                    this.version = version
                                }

                                fun additionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                    additionalProperties.put(key, value)
                                }

                                fun putAllAdditionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply { this.additionalProperties.putAll(additionalProperties) }

                                fun removeAdditionalProperty(key: String) = apply {
                                    additionalProperties.remove(key)
                                }

                                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                    keys.forEach(::removeAdditionalProperty)
                                }

                                fun build(): Brand =
                                    Brand(
                                        checkRequired("brand", brand),
                                        checkRequired("version", version),
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

                            fun validate(): Brand = apply {
                                if (validated) {
                                    return@apply
                                }

                                brand()
                                version()
                                validated = true
                            }

                            @JvmSynthetic
                            internal fun validity(): Int =
                                (if (brand.asKnown().isPresent) 1 else 0) +
                                    (if (version.asKnown().isPresent) 1 else 0)

                            override fun equals(other: Any?): Boolean {
                                if (this === other) {
                                    return true
                                }

                                return other is Brand &&
                                    this.brand == other.brand &&
                                    this.version == other.version &&
                                    this.additionalProperties == other.additionalProperties
                            }

                            private val hashCode: Int by lazy {
                                Objects.hash(brand, version, additionalProperties)
                            }

                            override fun hashCode(): Int = hashCode

                            override fun toString() =
                                "Brand{brand=$brand, version=$version, additionalProperties=$additionalProperties}"
                        }

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is UserAgentData &&
                                this.brands == other.brands &&
                                this.mobile == other.mobile &&
                                this.platform == other.platform &&
                                this.additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(brands, mobile, platform, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "UserAgentData{brands=$brands, mobile=$mobile, platform=$platform, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Navigator &&
                            this.appCodeName == other.appCodeName &&
                            this.appName == other.appName &&
                            this.appVersion == other.appVersion &&
                            this.deviceMemory == other.deviceMemory &&
                            this.extraProperties == other.extraProperties &&
                            this.hardwareConcurrency == other.hardwareConcurrency &&
                            this.language == other.language &&
                            this.languages == other.languages &&
                            this.maxTouchPoints == other.maxTouchPoints &&
                            this.oscpu == other.oscpu &&
                            this.platform == other.platform &&
                            this.product == other.product &&
                            this.productSub == other.productSub &&
                            this.userAgent == other.userAgent &&
                            this.userAgentData == other.userAgentData &&
                            this.vendor == other.vendor &&
                            this.vendorSub == other.vendorSub &&
                            this.webdriver == other.webdriver &&
                            this.doNotTrack == other.doNotTrack &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(
                            appCodeName,
                            appName,
                            appVersion,
                            deviceMemory,
                            extraProperties,
                            hardwareConcurrency,
                            language,
                            languages,
                            maxTouchPoints,
                            oscpu,
                            platform,
                            product,
                            productSub,
                            userAgent,
                            userAgentData,
                            vendor,
                            vendorSub,
                            webdriver,
                            doNotTrack,
                            additionalProperties,
                        )
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Navigator{appCodeName=$appCodeName, appName=$appName, appVersion=$appVersion, deviceMemory=$deviceMemory, extraProperties=$extraProperties, hardwareConcurrency=$hardwareConcurrency, language=$language, languages=$languages, maxTouchPoints=$maxTouchPoints, oscpu=$oscpu, platform=$platform, product=$product, productSub=$productSub, userAgent=$userAgent, userAgentData=$userAgentData, vendor=$vendor, vendorSub=$vendorSub, webdriver=$webdriver, doNotTrack=$doNotTrack, additionalProperties=$additionalProperties}"
                }

                class PluginsData
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val mimeTypes: JsonField<List<String>>,
                    private val plugins: JsonField<List<Plugin>>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("mimeTypes")
                        @ExcludeMissing
                        mimeTypes: JsonField<List<String>> = JsonMissing.of(),
                        @JsonProperty("plugins")
                        @ExcludeMissing
                        plugins: JsonField<List<Plugin>> = JsonMissing.of(),
                    ) : this(mimeTypes, plugins, mutableMapOf())

                    fun mimeTypes(): List<String> = mimeTypes.getRequired("mimeTypes")

                    fun plugins(): List<Plugin> = plugins.getRequired("plugins")

                    @JsonProperty("mimeTypes")
                    @ExcludeMissing
                    fun _mimeTypes(): JsonField<List<String>> = mimeTypes

                    @JsonProperty("plugins")
                    @ExcludeMissing
                    fun _plugins(): JsonField<List<Plugin>> = plugins

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
                         * Returns a mutable builder for constructing an instance of [PluginsData].
                         */
                        @JvmStatic fun builder() = Builder()
                    }

                    /** A builder for [PluginsData]. */
                    class Builder internal constructor() {

                        private var mimeTypes: JsonField<MutableList<String>>? = null
                        private var plugins: JsonField<MutableList<Plugin>>? = null
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(pluginsData: PluginsData) = apply {
                            this.mimeTypes = pluginsData.mimeTypes.map { it.toMutableList() }
                            this.plugins = pluginsData.plugins.map { it.toMutableList() }
                            this.additionalProperties =
                                pluginsData.additionalProperties.toMutableMap()
                        }

                        fun mimeTypes(mimeTypes: List<String>) = mimeTypes(JsonField.of(mimeTypes))

                        fun mimeTypes(mimeTypes: JsonField<List<String>>) = apply {
                            this.mimeTypes = mimeTypes.map { it.toMutableList() }
                        }

                        fun addMimeType(mimeType: String) = apply {
                            this.mimeTypes =
                                (this.mimeTypes ?: JsonField.of(mutableListOf())).also {
                                    checkKnown("mimeTypes", it).add(mimeType)
                                }
                        }

                        fun plugins(plugins: List<Plugin>) = plugins(JsonField.of(plugins))

                        fun plugins(plugins: JsonField<List<Plugin>>) = apply {
                            this.plugins = plugins.map { it.toMutableList() }
                        }

                        fun addPlugin(plugin: Plugin) = apply {
                            this.plugins =
                                (this.plugins ?: JsonField.of(mutableListOf())).also {
                                    checkKnown("plugins", it).add(plugin)
                                }
                        }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): PluginsData =
                            PluginsData(
                                checkRequired("mimeTypes", mimeTypes).map { it.toImmutable() },
                                checkRequired("plugins", plugins).map { it.toImmutable() },
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

                    fun validate(): PluginsData = apply {
                        if (validated) {
                            return@apply
                        }

                        mimeTypes()
                        plugins().forEach { it.validate() }
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        (mimeTypes.asKnown().getOrNull()?.let { it.size } ?: 0) +
                            (plugins.asKnown().getOrNull()?.let {
                                it.sumOf { (it.validity()).toInt() }
                            } ?: 0)

                    class Plugin
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val description: JsonField<String>,
                        private val filename: JsonField<String>,
                        private val mimeTypes: JsonField<List<MimeType>>,
                        private val name: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("description")
                            @ExcludeMissing
                            description: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("filename")
                            @ExcludeMissing
                            filename: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("mimeTypes")
                            @ExcludeMissing
                            mimeTypes: JsonField<List<MimeType>> = JsonMissing.of(),
                            @JsonProperty("name")
                            @ExcludeMissing
                            name: JsonField<String> = JsonMissing.of(),
                        ) : this(description, filename, mimeTypes, name, mutableMapOf())

                        fun description(): String? = description.getNullable("description")

                        fun filename(): String? = filename.getNullable("filename")

                        fun mimeTypes(): List<MimeType> = mimeTypes.getRequired("mimeTypes")

                        fun name(): String? = name.getNullable("name")

                        @JsonProperty("description")
                        @ExcludeMissing
                        fun _description(): JsonField<String> = description

                        @JsonProperty("filename")
                        @ExcludeMissing
                        fun _filename(): JsonField<String> = filename

                        @JsonProperty("mimeTypes")
                        @ExcludeMissing
                        fun _mimeTypes(): JsonField<List<MimeType>> = mimeTypes

                        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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
                             * Returns a mutable builder for constructing an instance of [Plugin].
                             */
                            @JvmStatic fun builder() = Builder()
                        }

                        /** A builder for [Plugin]. */
                        class Builder internal constructor() {

                            private var description: JsonField<String>? = null
                            private var filename: JsonField<String>? = null
                            private var mimeTypes: JsonField<MutableList<MimeType>>? = null
                            private var name: JsonField<String>? = null
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            @JvmSynthetic
                            internal fun from(plugin: Plugin) = apply {
                                this.description = plugin.description
                                this.filename = plugin.filename
                                this.mimeTypes = plugin.mimeTypes.map { it.toMutableList() }
                                this.name = plugin.name
                                this.additionalProperties =
                                    plugin.additionalProperties.toMutableMap()
                            }

                            fun description(description: String?) =
                                description(JsonField.ofNullable(description))

                            fun description(description: JsonField<String>) = apply {
                                this.description = description
                            }

                            fun filename(filename: String?) =
                                filename(JsonField.ofNullable(filename))

                            fun filename(filename: JsonField<String>) = apply {
                                this.filename = filename
                            }

                            fun mimeTypes(mimeTypes: List<MimeType>) =
                                mimeTypes(JsonField.of(mimeTypes))

                            fun mimeTypes(mimeTypes: JsonField<List<MimeType>>) = apply {
                                this.mimeTypes = mimeTypes.map { it.toMutableList() }
                            }

                            fun addMimeType(mimeType: MimeType) = apply {
                                this.mimeTypes =
                                    (this.mimeTypes ?: JsonField.of(mutableListOf())).also {
                                        checkKnown("mimeTypes", it).add(mimeType)
                                    }
                            }

                            fun name(name: String?) = name(JsonField.ofNullable(name))

                            fun name(name: JsonField<String>) = apply { this.name = name }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply { this.additionalProperties.putAll(additionalProperties) }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            fun build(): Plugin =
                                Plugin(
                                    checkRequired("description", description),
                                    checkRequired("filename", filename),
                                    checkRequired("mimeTypes", mimeTypes).map { it.toImmutable() },
                                    checkRequired("name", name),
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

                        fun validate(): Plugin = apply {
                            if (validated) {
                                return@apply
                            }

                            description()
                            filename()
                            mimeTypes().forEach { it.validate() }
                            name()
                            validated = true
                        }

                        @JvmSynthetic
                        internal fun validity(): Int =
                            (if (description.asKnown().isPresent) 1 else 0) +
                                (if (filename.asKnown().isPresent) 1 else 0) +
                                (mimeTypes.asKnown().getOrNull()?.let {
                                    it.sumOf { (it.validity()).toInt() }
                                } ?: 0) +
                                (if (name.asKnown().isPresent) 1 else 0)

                        class MimeType
                        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                        private constructor(
                            private val description: JsonField<String>,
                            private val enabledPlugin: JsonField<String>,
                            private val suffixes: JsonField<String>,
                            private val type: JsonField<String>,
                            private val additionalProperties: MutableMap<String, JsonValue>,
                        ) {

                            @JsonCreator
                            private constructor(
                                @JsonProperty("description")
                                @ExcludeMissing
                                description: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("enabledPlugin")
                                @ExcludeMissing
                                enabledPlugin: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("suffixes")
                                @ExcludeMissing
                                suffixes: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("type")
                                @ExcludeMissing
                                type: JsonField<String> = JsonMissing.of(),
                            ) : this(description, enabledPlugin, suffixes, type, mutableMapOf())

                            fun description(): String? = description.getNullable("description")

                            fun enabledPlugin(): String? =
                                enabledPlugin.getNullable("enabledPlugin")

                            fun suffixes(): String? = suffixes.getNullable("suffixes")

                            fun type(): String? = type.getNullable("type")

                            @JsonProperty("description")
                            @ExcludeMissing
                            fun _description(): JsonField<String> = description

                            @JsonProperty("enabledPlugin")
                            @ExcludeMissing
                            fun _enabledPlugin(): JsonField<String> = enabledPlugin

                            @JsonProperty("suffixes")
                            @ExcludeMissing
                            fun _suffixes(): JsonField<String> = suffixes

                            @JsonProperty("type")
                            @ExcludeMissing
                            fun _type(): JsonField<String> = type

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
                                 * [MimeType].
                                 */
                                @JvmStatic fun builder() = Builder()
                            }

                            /** A builder for [MimeType]. */
                            class Builder internal constructor() {

                                private var description: JsonField<String>? = null
                                private var enabledPlugin: JsonField<String>? = null
                                private var suffixes: JsonField<String>? = null
                                private var type: JsonField<String>? = null
                                private var additionalProperties: MutableMap<String, JsonValue> =
                                    mutableMapOf()

                                @JvmSynthetic
                                internal fun from(mimeType: MimeType) = apply {
                                    this.description = mimeType.description
                                    this.enabledPlugin = mimeType.enabledPlugin
                                    this.suffixes = mimeType.suffixes
                                    this.type = mimeType.type
                                    this.additionalProperties =
                                        mimeType.additionalProperties.toMutableMap()
                                }

                                fun description(description: String?) =
                                    description(JsonField.ofNullable(description))

                                fun description(description: JsonField<String>) = apply {
                                    this.description = description
                                }

                                fun enabledPlugin(enabledPlugin: String?) =
                                    enabledPlugin(JsonField.ofNullable(enabledPlugin))

                                fun enabledPlugin(enabledPlugin: JsonField<String>) = apply {
                                    this.enabledPlugin = enabledPlugin
                                }

                                fun suffixes(suffixes: String?) =
                                    suffixes(JsonField.ofNullable(suffixes))

                                fun suffixes(suffixes: JsonField<String>) = apply {
                                    this.suffixes = suffixes
                                }

                                fun type(type: String?) = type(JsonField.ofNullable(type))

                                fun type(type: JsonField<String>) = apply { this.type = type }

                                fun additionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                    additionalProperties.put(key, value)
                                }

                                fun putAllAdditionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply { this.additionalProperties.putAll(additionalProperties) }

                                fun removeAdditionalProperty(key: String) = apply {
                                    additionalProperties.remove(key)
                                }

                                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                    keys.forEach(::removeAdditionalProperty)
                                }

                                fun build(): MimeType =
                                    MimeType(
                                        checkRequired("description", description),
                                        checkRequired("enabledPlugin", enabledPlugin),
                                        checkRequired("suffixes", suffixes),
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

                            fun validate(): MimeType = apply {
                                if (validated) {
                                    return@apply
                                }

                                description()
                                enabledPlugin()
                                suffixes()
                                type()
                                validated = true
                            }

                            @JvmSynthetic
                            internal fun validity(): Int =
                                (if (description.asKnown().isPresent) 1 else 0) +
                                    (if (enabledPlugin.asKnown().isPresent) 1 else 0) +
                                    (if (suffixes.asKnown().isPresent) 1 else 0) +
                                    (if (type.asKnown().isPresent) 1 else 0)

                            override fun equals(other: Any?): Boolean {
                                if (this === other) {
                                    return true
                                }

                                return other is MimeType &&
                                    this.description == other.description &&
                                    this.enabledPlugin == other.enabledPlugin &&
                                    this.suffixes == other.suffixes &&
                                    this.type == other.type &&
                                    this.additionalProperties == other.additionalProperties
                            }

                            private val hashCode: Int by lazy {
                                Objects.hash(
                                    description,
                                    enabledPlugin,
                                    suffixes,
                                    type,
                                    additionalProperties,
                                )
                            }

                            override fun hashCode(): Int = hashCode

                            override fun toString() =
                                "MimeType{description=$description, enabledPlugin=$enabledPlugin, suffixes=$suffixes, type=$type, additionalProperties=$additionalProperties}"
                        }

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Plugin &&
                                this.description == other.description &&
                                this.filename == other.filename &&
                                this.mimeTypes == other.mimeTypes &&
                                this.name == other.name &&
                                this.additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(
                                description,
                                filename,
                                mimeTypes,
                                name,
                                additionalProperties,
                            )
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Plugin{description=$description, filename=$filename, mimeTypes=$mimeTypes, name=$name, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is PluginsData &&
                            this.mimeTypes == other.mimeTypes &&
                            this.plugins == other.plugins &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(mimeTypes, plugins, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "PluginsData{mimeTypes=$mimeTypes, plugins=$plugins, additionalProperties=$additionalProperties}"
                }

                class Screen
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val availHeight: JsonField<Double>,
                    private val availLeft: JsonField<Double>,
                    private val availTop: JsonField<Double>,
                    private val availWidth: JsonField<Double>,
                    private val clientHeight: JsonField<Double>,
                    private val clientWidth: JsonField<Double>,
                    private val colorDepth: JsonField<Double>,
                    private val devicePixelRatio: JsonField<Double>,
                    private val hasHdr: JsonField<Boolean>,
                    private val height: JsonField<Double>,
                    private val innerHeight: JsonField<Double>,
                    private val innerWidth: JsonField<Double>,
                    private val outerHeight: JsonField<Double>,
                    private val outerWidth: JsonField<Double>,
                    private val pageXOffset: JsonField<Double>,
                    private val pageYOffset: JsonField<Double>,
                    private val pixelDepth: JsonField<Double>,
                    private val screenX: JsonField<Double>,
                    private val width: JsonField<Double>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("availHeight")
                        @ExcludeMissing
                        availHeight: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("availLeft")
                        @ExcludeMissing
                        availLeft: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("availTop")
                        @ExcludeMissing
                        availTop: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("availWidth")
                        @ExcludeMissing
                        availWidth: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("clientHeight")
                        @ExcludeMissing
                        clientHeight: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("clientWidth")
                        @ExcludeMissing
                        clientWidth: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("colorDepth")
                        @ExcludeMissing
                        colorDepth: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("devicePixelRatio")
                        @ExcludeMissing
                        devicePixelRatio: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("hasHDR")
                        @ExcludeMissing
                        hasHdr: JsonField<Boolean> = JsonMissing.of(),
                        @JsonProperty("height")
                        @ExcludeMissing
                        height: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("innerHeight")
                        @ExcludeMissing
                        innerHeight: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("innerWidth")
                        @ExcludeMissing
                        innerWidth: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("outerHeight")
                        @ExcludeMissing
                        outerHeight: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("outerWidth")
                        @ExcludeMissing
                        outerWidth: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("pageXOffset")
                        @ExcludeMissing
                        pageXOffset: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("pageYOffset")
                        @ExcludeMissing
                        pageYOffset: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("pixelDepth")
                        @ExcludeMissing
                        pixelDepth: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("screenX")
                        @ExcludeMissing
                        screenX: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("width")
                        @ExcludeMissing
                        width: JsonField<Double> = JsonMissing.of(),
                    ) : this(
                        availHeight,
                        availLeft,
                        availTop,
                        availWidth,
                        clientHeight,
                        clientWidth,
                        colorDepth,
                        devicePixelRatio,
                        hasHdr,
                        height,
                        innerHeight,
                        innerWidth,
                        outerHeight,
                        outerWidth,
                        pageXOffset,
                        pageYOffset,
                        pixelDepth,
                        screenX,
                        width,
                        mutableMapOf(),
                    )

                    fun availHeight(): Double? = availHeight.getNullable("availHeight")

                    fun availLeft(): Double? = availLeft.getNullable("availLeft")

                    fun availTop(): Double? = availTop.getNullable("availTop")

                    fun availWidth(): Double? = availWidth.getNullable("availWidth")

                    fun clientHeight(): Double? = clientHeight.getNullable("clientHeight")

                    fun clientWidth(): Double? = clientWidth.getNullable("clientWidth")

                    fun colorDepth(): Double? = colorDepth.getNullable("colorDepth")

                    fun devicePixelRatio(): Double? =
                        devicePixelRatio.getNullable("devicePixelRatio")

                    fun hasHdr(): Boolean? = hasHdr.getNullable("hasHDR")

                    fun height(): Double? = height.getNullable("height")

                    fun innerHeight(): Double? = innerHeight.getNullable("innerHeight")

                    fun innerWidth(): Double? = innerWidth.getNullable("innerWidth")

                    fun outerHeight(): Double? = outerHeight.getNullable("outerHeight")

                    fun outerWidth(): Double? = outerWidth.getNullable("outerWidth")

                    fun pageXOffset(): Double? = pageXOffset.getNullable("pageXOffset")

                    fun pageYOffset(): Double? = pageYOffset.getNullable("pageYOffset")

                    fun pixelDepth(): Double? = pixelDepth.getNullable("pixelDepth")

                    fun screenX(): Double? = screenX.getNullable("screenX")

                    fun width(): Double? = width.getNullable("width")

                    @JsonProperty("availHeight")
                    @ExcludeMissing
                    fun _availHeight(): JsonField<Double> = availHeight

                    @JsonProperty("availLeft")
                    @ExcludeMissing
                    fun _availLeft(): JsonField<Double> = availLeft

                    @JsonProperty("availTop")
                    @ExcludeMissing
                    fun _availTop(): JsonField<Double> = availTop

                    @JsonProperty("availWidth")
                    @ExcludeMissing
                    fun _availWidth(): JsonField<Double> = availWidth

                    @JsonProperty("clientHeight")
                    @ExcludeMissing
                    fun _clientHeight(): JsonField<Double> = clientHeight

                    @JsonProperty("clientWidth")
                    @ExcludeMissing
                    fun _clientWidth(): JsonField<Double> = clientWidth

                    @JsonProperty("colorDepth")
                    @ExcludeMissing
                    fun _colorDepth(): JsonField<Double> = colorDepth

                    @JsonProperty("devicePixelRatio")
                    @ExcludeMissing
                    fun _devicePixelRatio(): JsonField<Double> = devicePixelRatio

                    @JsonProperty("hasHDR")
                    @ExcludeMissing
                    fun _hasHdr(): JsonField<Boolean> = hasHdr

                    @JsonProperty("height")
                    @ExcludeMissing
                    fun _height(): JsonField<Double> = height

                    @JsonProperty("innerHeight")
                    @ExcludeMissing
                    fun _innerHeight(): JsonField<Double> = innerHeight

                    @JsonProperty("innerWidth")
                    @ExcludeMissing
                    fun _innerWidth(): JsonField<Double> = innerWidth

                    @JsonProperty("outerHeight")
                    @ExcludeMissing
                    fun _outerHeight(): JsonField<Double> = outerHeight

                    @JsonProperty("outerWidth")
                    @ExcludeMissing
                    fun _outerWidth(): JsonField<Double> = outerWidth

                    @JsonProperty("pageXOffset")
                    @ExcludeMissing
                    fun _pageXOffset(): JsonField<Double> = pageXOffset

                    @JsonProperty("pageYOffset")
                    @ExcludeMissing
                    fun _pageYOffset(): JsonField<Double> = pageYOffset

                    @JsonProperty("pixelDepth")
                    @ExcludeMissing
                    fun _pixelDepth(): JsonField<Double> = pixelDepth

                    @JsonProperty("screenX")
                    @ExcludeMissing
                    fun _screenX(): JsonField<Double> = screenX

                    @JsonProperty("width") @ExcludeMissing fun _width(): JsonField<Double> = width

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

                        /** Returns a mutable builder for constructing an instance of [Screen]. */
                        @JvmStatic fun builder() = Builder()
                    }

                    /** A builder for [Screen]. */
                    class Builder internal constructor() {

                        private var availHeight: JsonField<Double>? = null
                        private var availLeft: JsonField<Double>? = null
                        private var availTop: JsonField<Double>? = null
                        private var availWidth: JsonField<Double>? = null
                        private var clientHeight: JsonField<Double>? = null
                        private var clientWidth: JsonField<Double>? = null
                        private var colorDepth: JsonField<Double>? = null
                        private var devicePixelRatio: JsonField<Double>? = null
                        private var hasHdr: JsonField<Boolean>? = null
                        private var height: JsonField<Double>? = null
                        private var innerHeight: JsonField<Double>? = null
                        private var innerWidth: JsonField<Double>? = null
                        private var outerHeight: JsonField<Double>? = null
                        private var outerWidth: JsonField<Double>? = null
                        private var pageXOffset: JsonField<Double>? = null
                        private var pageYOffset: JsonField<Double>? = null
                        private var pixelDepth: JsonField<Double>? = null
                        private var screenX: JsonField<Double>? = null
                        private var width: JsonField<Double>? = null
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(screen: Screen) = apply {
                            this.availHeight = screen.availHeight
                            this.availLeft = screen.availLeft
                            this.availTop = screen.availTop
                            this.availWidth = screen.availWidth
                            this.clientHeight = screen.clientHeight
                            this.clientWidth = screen.clientWidth
                            this.colorDepth = screen.colorDepth
                            this.devicePixelRatio = screen.devicePixelRatio
                            this.hasHdr = screen.hasHdr
                            this.height = screen.height
                            this.innerHeight = screen.innerHeight
                            this.innerWidth = screen.innerWidth
                            this.outerHeight = screen.outerHeight
                            this.outerWidth = screen.outerWidth
                            this.pageXOffset = screen.pageXOffset
                            this.pageYOffset = screen.pageYOffset
                            this.pixelDepth = screen.pixelDepth
                            this.screenX = screen.screenX
                            this.width = screen.width
                            this.additionalProperties = screen.additionalProperties.toMutableMap()
                        }

                        fun availHeight(availHeight: Double?) =
                            availHeight(JsonField.ofNullable(availHeight))

                        fun availHeight(availHeight: Double) = availHeight(availHeight as Double?)

                        fun availHeight(availHeight: JsonField<Double>) = apply {
                            this.availHeight = availHeight
                        }

                        fun availLeft(availLeft: Double?) =
                            availLeft(JsonField.ofNullable(availLeft))

                        fun availLeft(availLeft: Double) = availLeft(availLeft as Double?)

                        fun availLeft(availLeft: JsonField<Double>) = apply {
                            this.availLeft = availLeft
                        }

                        fun availTop(availTop: Double?) = availTop(JsonField.ofNullable(availTop))

                        fun availTop(availTop: Double) = availTop(availTop as Double?)

                        fun availTop(availTop: JsonField<Double>) = apply {
                            this.availTop = availTop
                        }

                        fun availWidth(availWidth: Double?) =
                            availWidth(JsonField.ofNullable(availWidth))

                        fun availWidth(availWidth: Double) = availWidth(availWidth as Double?)

                        fun availWidth(availWidth: JsonField<Double>) = apply {
                            this.availWidth = availWidth
                        }

                        fun clientHeight(clientHeight: Double?) =
                            clientHeight(JsonField.ofNullable(clientHeight))

                        fun clientHeight(clientHeight: Double) =
                            clientHeight(clientHeight as Double?)

                        fun clientHeight(clientHeight: JsonField<Double>) = apply {
                            this.clientHeight = clientHeight
                        }

                        fun clientWidth(clientWidth: Double?) =
                            clientWidth(JsonField.ofNullable(clientWidth))

                        fun clientWidth(clientWidth: Double) = clientWidth(clientWidth as Double?)

                        fun clientWidth(clientWidth: JsonField<Double>) = apply {
                            this.clientWidth = clientWidth
                        }

                        fun colorDepth(colorDepth: Double?) =
                            colorDepth(JsonField.ofNullable(colorDepth))

                        fun colorDepth(colorDepth: Double) = colorDepth(colorDepth as Double?)

                        fun colorDepth(colorDepth: JsonField<Double>) = apply {
                            this.colorDepth = colorDepth
                        }

                        fun devicePixelRatio(devicePixelRatio: Double?) =
                            devicePixelRatio(JsonField.ofNullable(devicePixelRatio))

                        fun devicePixelRatio(devicePixelRatio: Double) =
                            devicePixelRatio(devicePixelRatio as Double?)

                        fun devicePixelRatio(devicePixelRatio: JsonField<Double>) = apply {
                            this.devicePixelRatio = devicePixelRatio
                        }

                        fun hasHdr(hasHdr: Boolean?) = hasHdr(JsonField.ofNullable(hasHdr))

                        fun hasHdr(hasHdr: Boolean) = hasHdr(hasHdr as Boolean?)

                        fun hasHdr(hasHdr: JsonField<Boolean>) = apply { this.hasHdr = hasHdr }

                        fun height(height: Double?) = height(JsonField.ofNullable(height))

                        fun height(height: Double) = height(height as Double?)

                        fun height(height: JsonField<Double>) = apply { this.height = height }

                        fun innerHeight(innerHeight: Double?) =
                            innerHeight(JsonField.ofNullable(innerHeight))

                        fun innerHeight(innerHeight: Double) = innerHeight(innerHeight as Double?)

                        fun innerHeight(innerHeight: JsonField<Double>) = apply {
                            this.innerHeight = innerHeight
                        }

                        fun innerWidth(innerWidth: Double?) =
                            innerWidth(JsonField.ofNullable(innerWidth))

                        fun innerWidth(innerWidth: Double) = innerWidth(innerWidth as Double?)

                        fun innerWidth(innerWidth: JsonField<Double>) = apply {
                            this.innerWidth = innerWidth
                        }

                        fun outerHeight(outerHeight: Double?) =
                            outerHeight(JsonField.ofNullable(outerHeight))

                        fun outerHeight(outerHeight: Double) = outerHeight(outerHeight as Double?)

                        fun outerHeight(outerHeight: JsonField<Double>) = apply {
                            this.outerHeight = outerHeight
                        }

                        fun outerWidth(outerWidth: Double?) =
                            outerWidth(JsonField.ofNullable(outerWidth))

                        fun outerWidth(outerWidth: Double) = outerWidth(outerWidth as Double?)

                        fun outerWidth(outerWidth: JsonField<Double>) = apply {
                            this.outerWidth = outerWidth
                        }

                        fun pageXOffset(pageXOffset: Double?) =
                            pageXOffset(JsonField.ofNullable(pageXOffset))

                        fun pageXOffset(pageXOffset: Double) = pageXOffset(pageXOffset as Double?)

                        fun pageXOffset(pageXOffset: JsonField<Double>) = apply {
                            this.pageXOffset = pageXOffset
                        }

                        fun pageYOffset(pageYOffset: Double?) =
                            pageYOffset(JsonField.ofNullable(pageYOffset))

                        fun pageYOffset(pageYOffset: Double) = pageYOffset(pageYOffset as Double?)

                        fun pageYOffset(pageYOffset: JsonField<Double>) = apply {
                            this.pageYOffset = pageYOffset
                        }

                        fun pixelDepth(pixelDepth: Double?) =
                            pixelDepth(JsonField.ofNullable(pixelDepth))

                        fun pixelDepth(pixelDepth: Double) = pixelDepth(pixelDepth as Double?)

                        fun pixelDepth(pixelDepth: JsonField<Double>) = apply {
                            this.pixelDepth = pixelDepth
                        }

                        fun screenX(screenX: Double?) = screenX(JsonField.ofNullable(screenX))

                        fun screenX(screenX: Double) = screenX(screenX as Double?)

                        fun screenX(screenX: JsonField<Double>) = apply { this.screenX = screenX }

                        fun width(width: Double?) = width(JsonField.ofNullable(width))

                        fun width(width: Double) = width(width as Double?)

                        fun width(width: JsonField<Double>) = apply { this.width = width }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): Screen =
                            Screen(
                                checkRequired("availHeight", availHeight),
                                checkRequired("availLeft", availLeft),
                                checkRequired("availTop", availTop),
                                checkRequired("availWidth", availWidth),
                                checkRequired("clientHeight", clientHeight),
                                checkRequired("clientWidth", clientWidth),
                                checkRequired("colorDepth", colorDepth),
                                checkRequired("devicePixelRatio", devicePixelRatio),
                                checkRequired("hasHDR", hasHdr),
                                checkRequired("height", height),
                                checkRequired("innerHeight", innerHeight),
                                checkRequired("innerWidth", innerWidth),
                                checkRequired("outerHeight", outerHeight),
                                checkRequired("outerWidth", outerWidth),
                                checkRequired("pageXOffset", pageXOffset),
                                checkRequired("pageYOffset", pageYOffset),
                                checkRequired("pixelDepth", pixelDepth),
                                checkRequired("screenX", screenX),
                                checkRequired("width", width),
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

                    fun validate(): Screen = apply {
                        if (validated) {
                            return@apply
                        }

                        availHeight()
                        availLeft()
                        availTop()
                        availWidth()
                        clientHeight()
                        clientWidth()
                        colorDepth()
                        devicePixelRatio()
                        hasHdr()
                        height()
                        innerHeight()
                        innerWidth()
                        outerHeight()
                        outerWidth()
                        pageXOffset()
                        pageYOffset()
                        pixelDepth()
                        screenX()
                        width()
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        (if (availHeight.asKnown().isPresent) 1 else 0) +
                            (if (availLeft.asKnown().isPresent) 1 else 0) +
                            (if (availTop.asKnown().isPresent) 1 else 0) +
                            (if (availWidth.asKnown().isPresent) 1 else 0) +
                            (if (clientHeight.asKnown().isPresent) 1 else 0) +
                            (if (clientWidth.asKnown().isPresent) 1 else 0) +
                            (if (colorDepth.asKnown().isPresent) 1 else 0) +
                            (if (devicePixelRatio.asKnown().isPresent) 1 else 0) +
                            (if (hasHdr.asKnown().isPresent) 1 else 0) +
                            (if (height.asKnown().isPresent) 1 else 0) +
                            (if (innerHeight.asKnown().isPresent) 1 else 0) +
                            (if (innerWidth.asKnown().isPresent) 1 else 0) +
                            (if (outerHeight.asKnown().isPresent) 1 else 0) +
                            (if (outerWidth.asKnown().isPresent) 1 else 0) +
                            (if (pageXOffset.asKnown().isPresent) 1 else 0) +
                            (if (pageYOffset.asKnown().isPresent) 1 else 0) +
                            (if (pixelDepth.asKnown().isPresent) 1 else 0) +
                            (if (screenX.asKnown().isPresent) 1 else 0) +
                            (if (width.asKnown().isPresent) 1 else 0)

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Screen &&
                            this.availHeight == other.availHeight &&
                            this.availLeft == other.availLeft &&
                            this.availTop == other.availTop &&
                            this.availWidth == other.availWidth &&
                            this.clientHeight == other.clientHeight &&
                            this.clientWidth == other.clientWidth &&
                            this.colorDepth == other.colorDepth &&
                            this.devicePixelRatio == other.devicePixelRatio &&
                            this.hasHdr == other.hasHdr &&
                            this.height == other.height &&
                            this.innerHeight == other.innerHeight &&
                            this.innerWidth == other.innerWidth &&
                            this.outerHeight == other.outerHeight &&
                            this.outerWidth == other.outerWidth &&
                            this.pageXOffset == other.pageXOffset &&
                            this.pageYOffset == other.pageYOffset &&
                            this.pixelDepth == other.pixelDepth &&
                            this.screenX == other.screenX &&
                            this.width == other.width &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(
                            availHeight,
                            availLeft,
                            availTop,
                            availWidth,
                            clientHeight,
                            clientWidth,
                            colorDepth,
                            devicePixelRatio,
                            hasHdr,
                            height,
                            innerHeight,
                            innerWidth,
                            outerHeight,
                            outerWidth,
                            pageXOffset,
                            pageYOffset,
                            pixelDepth,
                            screenX,
                            width,
                            additionalProperties,
                        )
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Screen{availHeight=$availHeight, availLeft=$availLeft, availTop=$availTop, availWidth=$availWidth, clientHeight=$clientHeight, clientWidth=$clientWidth, colorDepth=$colorDepth, devicePixelRatio=$devicePixelRatio, hasHdr=$hasHdr, height=$height, innerHeight=$innerHeight, innerWidth=$innerWidth, outerHeight=$outerHeight, outerWidth=$outerWidth, pageXOffset=$pageXOffset, pageYOffset=$pageYOffset, pixelDepth=$pixelDepth, screenX=$screenX, width=$width, additionalProperties=$additionalProperties}"
                }

                class VideoCard
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val renderer: JsonField<String>,
                    private val vendor: JsonField<String>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("renderer")
                        @ExcludeMissing
                        renderer: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("vendor")
                        @ExcludeMissing
                        vendor: JsonField<String> = JsonMissing.of(),
                    ) : this(renderer, vendor, mutableMapOf())

                    fun renderer(): String? = renderer.getNullable("renderer")

                    fun vendor(): String? = vendor.getNullable("vendor")

                    @JsonProperty("renderer")
                    @ExcludeMissing
                    fun _renderer(): JsonField<String> = renderer

                    @JsonProperty("vendor")
                    @ExcludeMissing
                    fun _vendor(): JsonField<String> = vendor

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
                         * Returns a mutable builder for constructing an instance of [VideoCard].
                         */
                        @JvmStatic fun builder() = Builder()
                    }

                    /** A builder for [VideoCard]. */
                    class Builder internal constructor() {

                        private var renderer: JsonField<String>? = null
                        private var vendor: JsonField<String>? = null
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(videoCard: VideoCard) = apply {
                            this.renderer = videoCard.renderer
                            this.vendor = videoCard.vendor
                            this.additionalProperties =
                                videoCard.additionalProperties.toMutableMap()
                        }

                        fun renderer(renderer: String?) = renderer(JsonField.ofNullable(renderer))

                        fun renderer(renderer: JsonField<String>) = apply {
                            this.renderer = renderer
                        }

                        fun vendor(vendor: String?) = vendor(JsonField.ofNullable(vendor))

                        fun vendor(vendor: JsonField<String>) = apply { this.vendor = vendor }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): VideoCard =
                            VideoCard(
                                checkRequired("renderer", renderer),
                                checkRequired("vendor", vendor),
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

                    fun validate(): VideoCard = apply {
                        if (validated) {
                            return@apply
                        }

                        renderer()
                        vendor()
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        (if (renderer.asKnown().isPresent) 1 else 0) +
                            (if (vendor.asKnown().isPresent) 1 else 0)

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is VideoCard &&
                            this.renderer == other.renderer &&
                            this.vendor == other.vendor &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(renderer, vendor, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "VideoCard{renderer=$renderer, vendor=$vendor, additionalProperties=$additionalProperties}"
                }

                class VideoCodecs
                @JsonCreator
                private constructor(
                    @com.fasterxml.jackson.annotation.JsonValue
                    private val additionalProperties: Map<String, JsonValue>
                ) {

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        @JvmStatic fun builder() = Builder()
                    }

                    class Builder internal constructor() {

                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        @JvmSynthetic
                        internal fun from(videoCodecs: VideoCodecs) = apply {
                            this.additionalProperties =
                                videoCodecs.additionalProperties.toMutableMap()
                        }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply { this.additionalProperties.putAll(additionalProperties) }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        fun build(): VideoCodecs = VideoCodecs(additionalProperties.toImmutable())
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: SteelInvalidDataException) {
                            false
                        }

                    private var validated: Boolean = false

                    fun validate(): VideoCodecs = apply {
                        if (validated) {
                            return@apply
                        }

                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int =
                        additionalProperties.count { (_, value) ->
                            !value.isNull() && !value.isMissing()
                        }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is VideoCodecs &&
                            this.additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "VideoCodecs{additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is FingerprintModel &&
                        this.audioCodecs == other.audioCodecs &&
                        this.battery == other.battery &&
                        this.fonts == other.fonts &&
                        this.mockWebRtc == other.mockWebRtc &&
                        this.multimediaDevices == other.multimediaDevices &&
                        this.navigator == other.navigator &&
                        this.pluginsData == other.pluginsData &&
                        this.screen == other.screen &&
                        this.slim == other.slim &&
                        this.videoCard == other.videoCard &&
                        this.videoCodecs == other.videoCodecs &&
                        this.additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(
                        audioCodecs,
                        battery,
                        fonts,
                        mockWebRtc,
                        multimediaDevices,
                        navigator,
                        pluginsData,
                        screen,
                        slim,
                        videoCard,
                        videoCodecs,
                        additionalProperties,
                    )
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "FingerprintModel{audioCodecs=$audioCodecs, battery=$battery, fonts=$fonts, mockWebRtc=$mockWebRtc, multimediaDevices=$multimediaDevices, navigator=$navigator, pluginsData=$pluginsData, screen=$screen, slim=$slim, videoCard=$videoCard, videoCodecs=$videoCodecs, additionalProperties=$additionalProperties}"
            }

            class HeadersModel
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val userAgent: JsonField<String>,
                private val accept: JsonField<String>,
                private val acceptEncoding: JsonField<String>,
                private val acceptLanguage: JsonField<String>,
                private val dnt: JsonField<String>,
                private val secChUa: JsonField<String>,
                private val secChUaMobile: JsonField<String>,
                private val secChUaPlatform: JsonField<String>,
                private val secFetchDest: JsonField<String>,
                private val secFetchMode: JsonField<String>,
                private val secFetchSite: JsonField<String>,
                private val secFetchUser: JsonField<String>,
                private val upgradeInsecureRequests: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("user-agent")
                    @ExcludeMissing
                    userAgent: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("accept")
                    @ExcludeMissing
                    accept: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("accept-encoding")
                    @ExcludeMissing
                    acceptEncoding: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("accept-language")
                    @ExcludeMissing
                    acceptLanguage: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("dnt") @ExcludeMissing dnt: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("sec-ch-ua")
                    @ExcludeMissing
                    secChUa: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("sec-ch-ua-mobile")
                    @ExcludeMissing
                    secChUaMobile: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("sec-ch-ua-platform")
                    @ExcludeMissing
                    secChUaPlatform: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("sec-fetch-dest")
                    @ExcludeMissing
                    secFetchDest: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("sec-fetch-mode")
                    @ExcludeMissing
                    secFetchMode: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("sec-fetch-site")
                    @ExcludeMissing
                    secFetchSite: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("sec-fetch-user")
                    @ExcludeMissing
                    secFetchUser: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("upgrade-insecure-requests")
                    @ExcludeMissing
                    upgradeInsecureRequests: JsonField<String> = JsonMissing.of(),
                ) : this(
                    userAgent,
                    accept,
                    acceptEncoding,
                    acceptLanguage,
                    dnt,
                    secChUa,
                    secChUaMobile,
                    secChUaPlatform,
                    secFetchDest,
                    secFetchMode,
                    secFetchSite,
                    secFetchUser,
                    upgradeInsecureRequests,
                    mutableMapOf(),
                )

                fun userAgent(): String? = userAgent.getNullable("user-agent")

                fun accept(): String? = accept.getNullable("accept")

                fun acceptEncoding(): String? = acceptEncoding.getNullable("accept-encoding")

                fun acceptLanguage(): String? = acceptLanguage.getNullable("accept-language")

                fun dnt(): String? = dnt.getNullable("dnt")

                fun secChUa(): String? = secChUa.getNullable("sec-ch-ua")

                fun secChUaMobile(): String? = secChUaMobile.getNullable("sec-ch-ua-mobile")

                fun secChUaPlatform(): String? = secChUaPlatform.getNullable("sec-ch-ua-platform")

                fun secFetchDest(): String? = secFetchDest.getNullable("sec-fetch-dest")

                fun secFetchMode(): String? = secFetchMode.getNullable("sec-fetch-mode")

                fun secFetchSite(): String? = secFetchSite.getNullable("sec-fetch-site")

                fun secFetchUser(): String? = secFetchUser.getNullable("sec-fetch-user")

                fun upgradeInsecureRequests(): String? =
                    upgradeInsecureRequests.getNullable("upgrade-insecure-requests")

                @JsonProperty("user-agent")
                @ExcludeMissing
                fun _userAgent(): JsonField<String> = userAgent

                @JsonProperty("accept") @ExcludeMissing fun _accept(): JsonField<String> = accept

                @JsonProperty("accept-encoding")
                @ExcludeMissing
                fun _acceptEncoding(): JsonField<String> = acceptEncoding

                @JsonProperty("accept-language")
                @ExcludeMissing
                fun _acceptLanguage(): JsonField<String> = acceptLanguage

                @JsonProperty("dnt") @ExcludeMissing fun _dnt(): JsonField<String> = dnt

                @JsonProperty("sec-ch-ua")
                @ExcludeMissing
                fun _secChUa(): JsonField<String> = secChUa

                @JsonProperty("sec-ch-ua-mobile")
                @ExcludeMissing
                fun _secChUaMobile(): JsonField<String> = secChUaMobile

                @JsonProperty("sec-ch-ua-platform")
                @ExcludeMissing
                fun _secChUaPlatform(): JsonField<String> = secChUaPlatform

                @JsonProperty("sec-fetch-dest")
                @ExcludeMissing
                fun _secFetchDest(): JsonField<String> = secFetchDest

                @JsonProperty("sec-fetch-mode")
                @ExcludeMissing
                fun _secFetchMode(): JsonField<String> = secFetchMode

                @JsonProperty("sec-fetch-site")
                @ExcludeMissing
                fun _secFetchSite(): JsonField<String> = secFetchSite

                @JsonProperty("sec-fetch-user")
                @ExcludeMissing
                fun _secFetchUser(): JsonField<String> = secFetchUser

                @JsonProperty("upgrade-insecure-requests")
                @ExcludeMissing
                fun _upgradeInsecureRequests(): JsonField<String> = upgradeInsecureRequests

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

                    /** Returns a mutable builder for constructing an instance of [HeadersModel]. */
                    @JvmStatic fun builder() = Builder()
                }

                /** A builder for [HeadersModel]. */
                class Builder internal constructor() {

                    private var userAgent: JsonField<String>? = null
                    private var accept: JsonField<String> = JsonMissing.of()
                    private var acceptEncoding: JsonField<String> = JsonMissing.of()
                    private var acceptLanguage: JsonField<String> = JsonMissing.of()
                    private var dnt: JsonField<String> = JsonMissing.of()
                    private var secChUa: JsonField<String> = JsonMissing.of()
                    private var secChUaMobile: JsonField<String> = JsonMissing.of()
                    private var secChUaPlatform: JsonField<String> = JsonMissing.of()
                    private var secFetchDest: JsonField<String> = JsonMissing.of()
                    private var secFetchMode: JsonField<String> = JsonMissing.of()
                    private var secFetchSite: JsonField<String> = JsonMissing.of()
                    private var secFetchUser: JsonField<String> = JsonMissing.of()
                    private var upgradeInsecureRequests: JsonField<String> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    @JvmSynthetic
                    internal fun from(headersModel: HeadersModel) = apply {
                        this.userAgent = headersModel.userAgent
                        this.accept = headersModel.accept
                        this.acceptEncoding = headersModel.acceptEncoding
                        this.acceptLanguage = headersModel.acceptLanguage
                        this.dnt = headersModel.dnt
                        this.secChUa = headersModel.secChUa
                        this.secChUaMobile = headersModel.secChUaMobile
                        this.secChUaPlatform = headersModel.secChUaPlatform
                        this.secFetchDest = headersModel.secFetchDest
                        this.secFetchMode = headersModel.secFetchMode
                        this.secFetchSite = headersModel.secFetchSite
                        this.secFetchUser = headersModel.secFetchUser
                        this.upgradeInsecureRequests = headersModel.upgradeInsecureRequests
                        this.additionalProperties = headersModel.additionalProperties.toMutableMap()
                    }

                    fun userAgent(userAgent: String?) = userAgent(JsonField.ofNullable(userAgent))

                    fun userAgent(userAgent: JsonField<String>) = apply {
                        this.userAgent = userAgent
                    }

                    fun accept(accept: String?) = accept(JsonField.ofNullable(accept))

                    fun accept(accept: JsonField<String>) = apply { this.accept = accept }

                    fun acceptEncoding(acceptEncoding: String?) =
                        acceptEncoding(JsonField.ofNullable(acceptEncoding))

                    fun acceptEncoding(acceptEncoding: JsonField<String>) = apply {
                        this.acceptEncoding = acceptEncoding
                    }

                    fun acceptLanguage(acceptLanguage: String?) =
                        acceptLanguage(JsonField.ofNullable(acceptLanguage))

                    fun acceptLanguage(acceptLanguage: JsonField<String>) = apply {
                        this.acceptLanguage = acceptLanguage
                    }

                    fun dnt(dnt: String?) = dnt(JsonField.ofNullable(dnt))

                    fun dnt(dnt: JsonField<String>) = apply { this.dnt = dnt }

                    fun secChUa(secChUa: String?) = secChUa(JsonField.ofNullable(secChUa))

                    fun secChUa(secChUa: JsonField<String>) = apply { this.secChUa = secChUa }

                    fun secChUaMobile(secChUaMobile: String?) =
                        secChUaMobile(JsonField.ofNullable(secChUaMobile))

                    fun secChUaMobile(secChUaMobile: JsonField<String>) = apply {
                        this.secChUaMobile = secChUaMobile
                    }

                    fun secChUaPlatform(secChUaPlatform: String?) =
                        secChUaPlatform(JsonField.ofNullable(secChUaPlatform))

                    fun secChUaPlatform(secChUaPlatform: JsonField<String>) = apply {
                        this.secChUaPlatform = secChUaPlatform
                    }

                    fun secFetchDest(secFetchDest: String?) =
                        secFetchDest(JsonField.ofNullable(secFetchDest))

                    fun secFetchDest(secFetchDest: JsonField<String>) = apply {
                        this.secFetchDest = secFetchDest
                    }

                    fun secFetchMode(secFetchMode: String?) =
                        secFetchMode(JsonField.ofNullable(secFetchMode))

                    fun secFetchMode(secFetchMode: JsonField<String>) = apply {
                        this.secFetchMode = secFetchMode
                    }

                    fun secFetchSite(secFetchSite: String?) =
                        secFetchSite(JsonField.ofNullable(secFetchSite))

                    fun secFetchSite(secFetchSite: JsonField<String>) = apply {
                        this.secFetchSite = secFetchSite
                    }

                    fun secFetchUser(secFetchUser: String?) =
                        secFetchUser(JsonField.ofNullable(secFetchUser))

                    fun secFetchUser(secFetchUser: JsonField<String>) = apply {
                        this.secFetchUser = secFetchUser
                    }

                    fun upgradeInsecureRequests(upgradeInsecureRequests: String?) =
                        upgradeInsecureRequests(JsonField.ofNullable(upgradeInsecureRequests))

                    fun upgradeInsecureRequests(upgradeInsecureRequests: JsonField<String>) =
                        apply {
                            this.upgradeInsecureRequests = upgradeInsecureRequests
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

                    fun build(): HeadersModel =
                        HeadersModel(
                            checkRequired("user-agent", userAgent),
                            accept,
                            acceptEncoding,
                            acceptLanguage,
                            dnt,
                            secChUa,
                            secChUaMobile,
                            secChUaPlatform,
                            secFetchDest,
                            secFetchMode,
                            secFetchSite,
                            secFetchUser,
                            upgradeInsecureRequests,
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

                fun validate(): HeadersModel = apply {
                    if (validated) {
                        return@apply
                    }

                    userAgent()
                    accept()
                    acceptEncoding()
                    acceptLanguage()
                    dnt()
                    secChUa()
                    secChUaMobile()
                    secChUaPlatform()
                    secFetchDest()
                    secFetchMode()
                    secFetchSite()
                    secFetchUser()
                    upgradeInsecureRequests()
                    validated = true
                }

                @JvmSynthetic
                internal fun validity(): Int =
                    (if (userAgent.asKnown().isPresent) 1 else 0) +
                        (if (accept.asKnown().isPresent) 1 else 0) +
                        (if (acceptEncoding.asKnown().isPresent) 1 else 0) +
                        (if (acceptLanguage.asKnown().isPresent) 1 else 0) +
                        (if (dnt.asKnown().isPresent) 1 else 0) +
                        (if (secChUa.asKnown().isPresent) 1 else 0) +
                        (if (secChUaMobile.asKnown().isPresent) 1 else 0) +
                        (if (secChUaPlatform.asKnown().isPresent) 1 else 0) +
                        (if (secFetchDest.asKnown().isPresent) 1 else 0) +
                        (if (secFetchMode.asKnown().isPresent) 1 else 0) +
                        (if (secFetchSite.asKnown().isPresent) 1 else 0) +
                        (if (secFetchUser.asKnown().isPresent) 1 else 0) +
                        (if (upgradeInsecureRequests.asKnown().isPresent) 1 else 0)

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is HeadersModel &&
                        this.userAgent == other.userAgent &&
                        this.accept == other.accept &&
                        this.acceptEncoding == other.acceptEncoding &&
                        this.acceptLanguage == other.acceptLanguage &&
                        this.dnt == other.dnt &&
                        this.secChUa == other.secChUa &&
                        this.secChUaMobile == other.secChUaMobile &&
                        this.secChUaPlatform == other.secChUaPlatform &&
                        this.secFetchDest == other.secFetchDest &&
                        this.secFetchMode == other.secFetchMode &&
                        this.secFetchSite == other.secFetchSite &&
                        this.secFetchUser == other.secFetchUser &&
                        this.upgradeInsecureRequests == other.upgradeInsecureRequests &&
                        this.additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(
                        userAgent,
                        accept,
                        acceptEncoding,
                        acceptLanguage,
                        dnt,
                        secChUa,
                        secChUaMobile,
                        secChUaPlatform,
                        secFetchDest,
                        secFetchMode,
                        secFetchSite,
                        secFetchUser,
                        upgradeInsecureRequests,
                        additionalProperties,
                    )
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "HeadersModel{userAgent=$userAgent, accept=$accept, acceptEncoding=$acceptEncoding, acceptLanguage=$acceptLanguage, dnt=$dnt, secChUa=$secChUa, secChUaMobile=$secChUaMobile, secChUaPlatform=$secChUaPlatform, secFetchDest=$secFetchDest, secFetchMode=$secFetchMode, secFetchSite=$secFetchSite, secFetchUser=$secFetchUser, upgradeInsecureRequests=$upgradeInsecureRequests, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Fingerprint &&
                    this.fingerprint == other.fingerprint &&
                    this.headers_ == other.headers_ &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(fingerprint, headers_, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Fingerprint{fingerprint=$fingerprint, headers_=$headers_, additionalProperties=$additionalProperties}"
        }

        class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val UPLOADING = of("UPLOADING")

                @JvmField val READY = of("READY")

                @JvmField val FAILED = of("FAILED")

                @JvmStatic fun of(value: String) = Status(JsonField.of(value))
            }

            enum class Known {
                UPLOADING,
                READY,
                FAILED,
            }

            enum class Value {
                UPLOADING,
                READY,
                FAILED,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    UPLOADING -> Value.UPLOADING
                    READY -> Value.READY
                    FAILED -> Value.FAILED
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    UPLOADING -> Known.UPLOADING
                    READY -> Known.READY
                    FAILED -> Known.FAILED
                    else -> throw SteelInvalidDataException("Unknown Status: $value")
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

            fun validate(): Status = apply {
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

                return other is Status && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Profile &&
                this.id == other.id &&
                this.createdAt == other.createdAt &&
                this.credentialsConfig == other.credentialsConfig &&
                this.dimensions == other.dimensions &&
                this.extensionIds == other.extensionIds &&
                this.fingerprint == other.fingerprint &&
                this.projectId == other.projectId &&
                this.sourceSessionId == other.sourceSessionId &&
                this.status == other.status &&
                this.updatedAt == other.updatedAt &&
                this.useProxyConfig == other.useProxyConfig &&
                this.userAgent == other.userAgent &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                createdAt,
                credentialsConfig,
                dimensions,
                extensionIds,
                fingerprint,
                projectId,
                sourceSessionId,
                status,
                updatedAt,
                useProxyConfig,
                userAgent,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Profile{id=$id, createdAt=$createdAt, credentialsConfig=$credentialsConfig, dimensions=$dimensions, extensionIds=$extensionIds, fingerprint=$fingerprint, projectId=$projectId, sourceSessionId=$sourceSessionId, status=$status, updatedAt=$updatedAt, useProxyConfig=$useProxyConfig, userAgent=$userAgent, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ProfileListResponse &&
            this.count == other.count &&
            this.profiles == other.profiles &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(count, profiles, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ProfileListResponse{count=$count, profiles=$profiles, additionalProperties=$additionalProperties}"
}
