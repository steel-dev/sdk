package dev.steel.api.models.sessions

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Response containing a list of browser sessions with pagination details. */
class Sessionslist
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val nextCursor: JsonField<String>,
    private val sessions: JsonField<List<Session>>,
    private val totalCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("nextCursor")
        @ExcludeMissing
        nextCursor: JsonField<String> = JsonMissing.of(),
        @JsonProperty("sessions")
        @ExcludeMissing
        sessions: JsonField<List<Session>> = JsonMissing.of(),
        @JsonProperty("totalCount") @ExcludeMissing totalCount: JsonField<Long> = JsonMissing.of(),
    ) : this(nextCursor, sessions, totalCount, mutableMapOf())

    /** Cursor for the next page of results. Null if no more pages. */
    fun nextCursor(): Optional<String> = nextCursor.getOptional("nextCursor")

    /** List of browser sessions */
    fun sessions(): List<Session> = sessions.getRequired("sessions")

    /**
     * Exact number of live sessions in the organization/project scope. Only included for
     * status=live without metadata, signal, releaseReason, createdAfter or createdBefore filters.
     * Other queries omit this field; use nextCursor to paginate until it is null.
     */
    fun totalCount(): Optional<Long> = totalCount.getOptional("totalCount")

    @JsonProperty("nextCursor") @ExcludeMissing fun _nextCursor(): JsonField<String> = nextCursor

    @JsonProperty("sessions") @ExcludeMissing fun _sessions(): JsonField<List<Session>> = sessions

    @JsonProperty("totalCount") @ExcludeMissing fun _totalCount(): JsonField<Long> = totalCount

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

        /** Returns a mutable builder for constructing an instance of [Sessionslist]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [Sessionslist]. */
    class Builder internal constructor() {

        private var nextCursor: JsonField<String>? = null
        private var sessions: JsonField<MutableList<Session>>? = null
        private var totalCount: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(sessionslist: Sessionslist) = apply {
            this.nextCursor = sessionslist.nextCursor
            this.sessions = sessionslist.sessions.map { it.toMutableList() }
            this.totalCount = sessionslist.totalCount
            this.additionalProperties = sessionslist.additionalProperties.toMutableMap()
        }

        /** Cursor for the next page of results. Null if no more pages. */
        fun nextCursor(nextCursor: String?) = nextCursor(JsonField.ofNullable(nextCursor))

        /** Cursor for the next page of results. Null if no more pages. */
        fun nextCursor(nextCursor: Optional<String>) = nextCursor(nextCursor.getOrNull())

        /** Cursor for the next page of results. Null if no more pages. */
        fun nextCursor(nextCursor: JsonField<String>) = apply { this.nextCursor = nextCursor }

        /** List of browser sessions */
        fun sessions(sessions: List<Session>) = sessions(JsonField.of(sessions))

        /** List of browser sessions */
        fun sessions(sessions: JsonField<List<Session>>) = apply {
            this.sessions = sessions.map { it.toMutableList() }
        }

        /** List of browser sessions */
        fun addSession(session: Session) = apply {
            this.sessions =
                (this.sessions ?: JsonField.of(mutableListOf())).also {
                    checkKnown("sessions", it).add(session)
                }
        }

        /**
         * Exact number of live sessions in the organization/project scope. Only included for
         * status=live without metadata, signal, releaseReason, createdAfter or createdBefore
         * filters. Other queries omit this field; use nextCursor to paginate until it is null.
         */
        fun totalCount(totalCount: Long) = totalCount(JsonField.of(totalCount))

        /**
         * Exact number of live sessions in the organization/project scope. Only included for
         * status=live without metadata, signal, releaseReason, createdAfter or createdBefore
         * filters. Other queries omit this field; use nextCursor to paginate until it is null.
         */
        fun totalCount(totalCount: JsonField<Long>) = apply { this.totalCount = totalCount }

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

        fun build(): Sessionslist =
            Sessionslist(
                checkRequired("nextCursor", nextCursor),
                checkRequired("sessions", sessions).map { it.toImmutable() },
                totalCount,
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

    fun validate(): Sessionslist = apply {
        if (validated) {
            return@apply
        }

        nextCursor()
        sessions().forEach { it.validate() }
        totalCount()
        validated = true
    }

    @JvmSynthetic
    internal fun validity(): Int =
        (if (nextCursor.asKnown().isPresent) 1 else 0) +
            (sessions.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
            (if (totalCount.asKnown().isPresent) 1 else 0)

    /**
     * Represents the data structure for a browser session, including its configuration and status.
     */
    class Session
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val browserMode: JsonField<BrowserMode>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val creditsUsed: JsonField<Long>,
        private val debugUrl: JsonField<String>,
        private val dimensions: JsonField<Dimensions>,
        private val duration: JsonField<Long>,
        private val eventCount: JsonField<Long>,
        private val optimizeBandwidth: JsonField<OptimizeBandwidth>,
        private val proxyBytesUsed: JsonField<Long>,
        private val proxySource: JsonField<ProxySource>,
        private val sessionViewerUrl: JsonField<String>,
        private val signals: JsonField<List<Signal>>,
        private val status: JsonField<Status>,
        private val timeout: JsonField<Long>,
        private val websocketUrl: JsonField<String>,
        private val debugConfig: JsonField<DebugConfig>,
        private val deviceConfig: JsonField<DeviceConfig>,
        private val fullscreen: JsonField<Boolean>,
        private val headless: JsonField<Boolean>,
        private val inactivityTimeout: JsonField<Long>,
        private val isSelenium: JsonField<Boolean>,
        private val metadata: JsonField<Metadata>,
        private val persistProfile: JsonField<Boolean>,
        private val profileId: JsonField<String>,
        private val projectId: JsonField<String>,
        private val region: JsonField<String>,
        private val releaseReason: JsonField<ReleaseReason>,
        private val solveCaptcha: JsonField<Boolean>,
        private val stealthConfig: JsonField<StealthConfig>,
        private val userAgent: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("browserMode")
            @ExcludeMissing
            browserMode: JsonField<BrowserMode> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("creditsUsed")
            @ExcludeMissing
            creditsUsed: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("debugUrl")
            @ExcludeMissing
            debugUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("dimensions")
            @ExcludeMissing
            dimensions: JsonField<Dimensions> = JsonMissing.of(),
            @JsonProperty("duration") @ExcludeMissing duration: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("eventCount")
            @ExcludeMissing
            eventCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("optimizeBandwidth")
            @ExcludeMissing
            optimizeBandwidth: JsonField<OptimizeBandwidth> = JsonMissing.of(),
            @JsonProperty("proxyBytesUsed")
            @ExcludeMissing
            proxyBytesUsed: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("proxySource")
            @ExcludeMissing
            proxySource: JsonField<ProxySource> = JsonMissing.of(),
            @JsonProperty("sessionViewerUrl")
            @ExcludeMissing
            sessionViewerUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("signals")
            @ExcludeMissing
            signals: JsonField<List<Signal>> = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
            @JsonProperty("timeout") @ExcludeMissing timeout: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("websocketUrl")
            @ExcludeMissing
            websocketUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("debugConfig")
            @ExcludeMissing
            debugConfig: JsonField<DebugConfig> = JsonMissing.of(),
            @JsonProperty("deviceConfig")
            @ExcludeMissing
            deviceConfig: JsonField<DeviceConfig> = JsonMissing.of(),
            @JsonProperty("fullscreen")
            @ExcludeMissing
            fullscreen: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("headless")
            @ExcludeMissing
            headless: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("inactivityTimeout")
            @ExcludeMissing
            inactivityTimeout: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("isSelenium")
            @ExcludeMissing
            isSelenium: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
            @JsonProperty("persistProfile")
            @ExcludeMissing
            persistProfile: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("profileId")
            @ExcludeMissing
            profileId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("projectId")
            @ExcludeMissing
            projectId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("region") @ExcludeMissing region: JsonField<String> = JsonMissing.of(),
            @JsonProperty("releaseReason")
            @ExcludeMissing
            releaseReason: JsonField<ReleaseReason> = JsonMissing.of(),
            @JsonProperty("solveCaptcha")
            @ExcludeMissing
            solveCaptcha: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("stealthConfig")
            @ExcludeMissing
            stealthConfig: JsonField<StealthConfig> = JsonMissing.of(),
            @JsonProperty("userAgent")
            @ExcludeMissing
            userAgent: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            browserMode,
            createdAt,
            creditsUsed,
            debugUrl,
            dimensions,
            duration,
            eventCount,
            optimizeBandwidth,
            proxyBytesUsed,
            proxySource,
            sessionViewerUrl,
            signals,
            status,
            timeout,
            websocketUrl,
            debugConfig,
            deviceConfig,
            fullscreen,
            headless,
            inactivityTimeout,
            isSelenium,
            metadata,
            persistProfile,
            profileId,
            projectId,
            region,
            releaseReason,
            solveCaptcha,
            stealthConfig,
            userAgent,
            mutableMapOf(),
        )

        /** Unique identifier for the session */
        fun id(): String = id.getRequired("id")

        /** Browser mode used for this session. */
        fun browserMode(): BrowserMode = browserMode.getRequired("browserMode")

        /** Timestamp when the session started */
        fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

        /** Amount of credits consumed by the session */
        fun creditsUsed(): Long = creditsUsed.getRequired("creditsUsed")

        /** URL for debugging the session */
        fun debugUrl(): String = debugUrl.getRequired("debugUrl")

        /** Viewport and browser window dimensions for the session */
        fun dimensions(): Dimensions = dimensions.getRequired("dimensions")

        /** Duration of the session in milliseconds */
        fun duration(): Long = duration.getRequired("duration")

        /** Number of events processed in the session */
        fun eventCount(): Long = eventCount.getRequired("eventCount")

        /** Bandwidth optimizations that were applied to the session. */
        fun optimizeBandwidth(): OptimizeBandwidth =
            optimizeBandwidth.getRequired("optimizeBandwidth")

        /** Amount of data transmitted through the proxy */
        fun proxyBytesUsed(): Long = proxyBytesUsed.getRequired("proxyBytesUsed")

        /** Source of the proxy used for the session */
        fun proxySource(): Optional<ProxySource> = proxySource.getOptional("proxySource")

        /** URL to view session details */
        fun sessionViewerUrl(): String = sessionViewerUrl.getRequired("sessionViewerUrl")

        /**
         * Signals observed during the session. Filled in within a few seconds after the session
         * ends, and empty until then. An empty list does not mean nothing happened: detection is
         * heuristic and coverage is incomplete.
         */
        fun signals(): List<Signal> = signals.getRequired("signals")

        /** Status of the session */
        fun status(): Status = status.getRequired("status")

        /** Session timeout duration in milliseconds */
        fun timeout(): Long = timeout.getRequired("timeout")

        /** URL for the session's WebSocket connection */
        fun websocketUrl(): String = websocketUrl.getRequired("websocketUrl")

        /**
         * Configuration for the debug URL and session viewer. Controls interaction capabilities and
         * cursor visibility.
         */
        fun debugConfig(): Optional<DebugConfig> = debugConfig.getOptional("debugConfig")

        /** Device configuration for the session */
        fun deviceConfig(): Optional<DeviceConfig> = deviceConfig.getOptional("deviceConfig")

        /** Launch the browser in fullscreen mode, covering the full screen with no Chrome UI. */
        fun fullscreen(): Optional<Boolean> = fullscreen.getOptional("fullscreen")

        /** Indicates if the session is headless or headful */
        fun headless(): Optional<Boolean> = headless.getOptional("headless")

        /** Inactivity timeout in milliseconds, if one was set when the session was created */
        fun inactivityTimeout(): Optional<Long> = inactivityTimeout.getOptional("inactivityTimeout")

        /** Indicates if Selenium is used in the session */
        fun isSelenium(): Optional<Boolean> = isSelenium.getOptional("isSelenium")

        /**
         * Customer-defined flat metadata. Values are always strings: numbers and booleans sent on
         * write come back in their string form.
         */
        fun metadata(): Optional<Metadata> = metadata.getOptional("metadata")

        /** This flag will persist the profile for the session. */
        fun persistProfile(): Optional<Boolean> = persistProfile.getOptional("persistProfile")

        /** The ID of the profile associated with the session */
        fun profileId(): Optional<String> = profileId.getOptional("profileId")

        /** The project associated with the session */
        fun projectId(): Optional<String> = projectId.getOptional("projectId")

        /** The region where the session was created. */
        fun region(): Optional<String> = region.getOptional("region")

        /**
         * Why the session reached a terminal state. Null while the session is live, or when the
         * reason is unknown (e.g. sessions created before this was tracked). One of: user_requested
         * (released via the API/SDK), timeout (hard `timeout` elapsed), inactivity_timeout (no
         * activity for the configured window), creation_timeout (never started in time),
         * startup_failed (could not be dispatched), browser_closed (the browser or agent closed
         * itself — not a crash), browser_crashed (the browser crashed or its machine became
         * unresponsive).
         */
        fun releaseReason(): Optional<ReleaseReason> = releaseReason.getOptional("releaseReason")

        /** Indicates if captcha solving is enabled */
        fun solveCaptcha(): Optional<Boolean> = solveCaptcha.getOptional("solveCaptcha")

        /** Stealth configuration for the session */
        fun stealthConfig(): Optional<StealthConfig> = stealthConfig.getOptional("stealthConfig")

        /** User agent string used in the session */
        fun userAgent(): Optional<String> = userAgent.getOptional("userAgent")

        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        @JsonProperty("browserMode")
        @ExcludeMissing
        fun _browserMode(): JsonField<BrowserMode> = browserMode

        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        @JsonProperty("creditsUsed")
        @ExcludeMissing
        fun _creditsUsed(): JsonField<Long> = creditsUsed

        @JsonProperty("debugUrl") @ExcludeMissing fun _debugUrl(): JsonField<String> = debugUrl

        @JsonProperty("dimensions")
        @ExcludeMissing
        fun _dimensions(): JsonField<Dimensions> = dimensions

        @JsonProperty("duration") @ExcludeMissing fun _duration(): JsonField<Long> = duration

        @JsonProperty("eventCount") @ExcludeMissing fun _eventCount(): JsonField<Long> = eventCount

        @JsonProperty("optimizeBandwidth")
        @ExcludeMissing
        fun _optimizeBandwidth(): JsonField<OptimizeBandwidth> = optimizeBandwidth

        @JsonProperty("proxyBytesUsed")
        @ExcludeMissing
        fun _proxyBytesUsed(): JsonField<Long> = proxyBytesUsed

        @JsonProperty("proxySource")
        @ExcludeMissing
        fun _proxySource(): JsonField<ProxySource> = proxySource

        @JsonProperty("sessionViewerUrl")
        @ExcludeMissing
        fun _sessionViewerUrl(): JsonField<String> = sessionViewerUrl

        @JsonProperty("signals") @ExcludeMissing fun _signals(): JsonField<List<Signal>> = signals

        @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

        @JsonProperty("timeout") @ExcludeMissing fun _timeout(): JsonField<Long> = timeout

        @JsonProperty("websocketUrl")
        @ExcludeMissing
        fun _websocketUrl(): JsonField<String> = websocketUrl

        @JsonProperty("debugConfig")
        @ExcludeMissing
        fun _debugConfig(): JsonField<DebugConfig> = debugConfig

        @JsonProperty("deviceConfig")
        @ExcludeMissing
        fun _deviceConfig(): JsonField<DeviceConfig> = deviceConfig

        @JsonProperty("fullscreen")
        @ExcludeMissing
        fun _fullscreen(): JsonField<Boolean> = fullscreen

        @JsonProperty("headless") @ExcludeMissing fun _headless(): JsonField<Boolean> = headless

        @JsonProperty("inactivityTimeout")
        @ExcludeMissing
        fun _inactivityTimeout(): JsonField<Long> = inactivityTimeout

        @JsonProperty("isSelenium")
        @ExcludeMissing
        fun _isSelenium(): JsonField<Boolean> = isSelenium

        @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

        @JsonProperty("persistProfile")
        @ExcludeMissing
        fun _persistProfile(): JsonField<Boolean> = persistProfile

        @JsonProperty("profileId") @ExcludeMissing fun _profileId(): JsonField<String> = profileId

        @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

        @JsonProperty("region") @ExcludeMissing fun _region(): JsonField<String> = region

        @JsonProperty("releaseReason")
        @ExcludeMissing
        fun _releaseReason(): JsonField<ReleaseReason> = releaseReason

        @JsonProperty("solveCaptcha")
        @ExcludeMissing
        fun _solveCaptcha(): JsonField<Boolean> = solveCaptcha

        @JsonProperty("stealthConfig")
        @ExcludeMissing
        fun _stealthConfig(): JsonField<StealthConfig> = stealthConfig

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

            /** Returns a mutable builder for constructing an instance of [Session]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Session]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var browserMode: JsonField<BrowserMode>? = null
            private var createdAt: JsonField<OffsetDateTime>? = null
            private var creditsUsed: JsonField<Long>? = null
            private var debugUrl: JsonField<String>? = null
            private var dimensions: JsonField<Dimensions>? = null
            private var duration: JsonField<Long>? = null
            private var eventCount: JsonField<Long>? = null
            private var optimizeBandwidth: JsonField<OptimizeBandwidth>? = null
            private var proxyBytesUsed: JsonField<Long>? = null
            private var proxySource: JsonField<ProxySource>? = null
            private var sessionViewerUrl: JsonField<String>? = null
            private var signals: JsonField<MutableList<Signal>>? = null
            private var status: JsonField<Status>? = null
            private var timeout: JsonField<Long>? = null
            private var websocketUrl: JsonField<String>? = null
            private var debugConfig: JsonField<DebugConfig> = JsonMissing.of()
            private var deviceConfig: JsonField<DeviceConfig> = JsonMissing.of()
            private var fullscreen: JsonField<Boolean> = JsonMissing.of()
            private var headless: JsonField<Boolean> = JsonMissing.of()
            private var inactivityTimeout: JsonField<Long> = JsonMissing.of()
            private var isSelenium: JsonField<Boolean> = JsonMissing.of()
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var persistProfile: JsonField<Boolean> = JsonMissing.of()
            private var profileId: JsonField<String> = JsonMissing.of()
            private var projectId: JsonField<String> = JsonMissing.of()
            private var region: JsonField<String> = JsonMissing.of()
            private var releaseReason: JsonField<ReleaseReason> = JsonMissing.of()
            private var solveCaptcha: JsonField<Boolean> = JsonMissing.of()
            private var stealthConfig: JsonField<StealthConfig> = JsonMissing.of()
            private var userAgent: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(session: Session) = apply {
                this.id = session.id
                this.browserMode = session.browserMode
                this.createdAt = session.createdAt
                this.creditsUsed = session.creditsUsed
                this.debugUrl = session.debugUrl
                this.dimensions = session.dimensions
                this.duration = session.duration
                this.eventCount = session.eventCount
                this.optimizeBandwidth = session.optimizeBandwidth
                this.proxyBytesUsed = session.proxyBytesUsed
                this.proxySource = session.proxySource
                this.sessionViewerUrl = session.sessionViewerUrl
                this.signals = session.signals.map { it.toMutableList() }
                this.status = session.status
                this.timeout = session.timeout
                this.websocketUrl = session.websocketUrl
                this.debugConfig = session.debugConfig
                this.deviceConfig = session.deviceConfig
                this.fullscreen = session.fullscreen
                this.headless = session.headless
                this.inactivityTimeout = session.inactivityTimeout
                this.isSelenium = session.isSelenium
                this.metadata = session.metadata
                this.persistProfile = session.persistProfile
                this.profileId = session.profileId
                this.projectId = session.projectId
                this.region = session.region
                this.releaseReason = session.releaseReason
                this.solveCaptcha = session.solveCaptcha
                this.stealthConfig = session.stealthConfig
                this.userAgent = session.userAgent
                this.additionalProperties = session.additionalProperties.toMutableMap()
            }

            /** Unique identifier for the session */
            fun id(id: String) = id(JsonField.of(id))

            /** Unique identifier for the session */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** Browser mode used for this session. */
            fun browserMode(browserMode: BrowserMode) = browserMode(JsonField.of(browserMode))

            /** Browser mode used for this session. */
            fun browserMode(browserMode: JsonField<BrowserMode>) = apply {
                this.browserMode = browserMode
            }

            /** Timestamp when the session started */
            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Timestamp when the session started */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            /** Amount of credits consumed by the session */
            fun creditsUsed(creditsUsed: Long) = creditsUsed(JsonField.of(creditsUsed))

            /** Amount of credits consumed by the session */
            fun creditsUsed(creditsUsed: JsonField<Long>) = apply { this.creditsUsed = creditsUsed }

            /** URL for debugging the session */
            fun debugUrl(debugUrl: String) = debugUrl(JsonField.of(debugUrl))

            /** URL for debugging the session */
            fun debugUrl(debugUrl: JsonField<String>) = apply { this.debugUrl = debugUrl }

            /** Viewport and browser window dimensions for the session */
            fun dimensions(dimensions: Dimensions) = dimensions(JsonField.of(dimensions))

            /** Viewport and browser window dimensions for the session */
            fun dimensions(dimensions: JsonField<Dimensions>) = apply {
                this.dimensions = dimensions
            }

            /** Duration of the session in milliseconds */
            fun duration(duration: Long) = duration(JsonField.of(duration))

            /** Duration of the session in milliseconds */
            fun duration(duration: JsonField<Long>) = apply { this.duration = duration }

            /** Number of events processed in the session */
            fun eventCount(eventCount: Long) = eventCount(JsonField.of(eventCount))

            /** Number of events processed in the session */
            fun eventCount(eventCount: JsonField<Long>) = apply { this.eventCount = eventCount }

            /** Bandwidth optimizations that were applied to the session. */
            fun optimizeBandwidth(optimizeBandwidth: OptimizeBandwidth) =
                optimizeBandwidth(JsonField.of(optimizeBandwidth))

            /** Bandwidth optimizations that were applied to the session. */
            fun optimizeBandwidth(optimizeBandwidth: JsonField<OptimizeBandwidth>) = apply {
                this.optimizeBandwidth = optimizeBandwidth
            }

            /** Amount of data transmitted through the proxy */
            fun proxyBytesUsed(proxyBytesUsed: Long) = proxyBytesUsed(JsonField.of(proxyBytesUsed))

            /** Amount of data transmitted through the proxy */
            fun proxyBytesUsed(proxyBytesUsed: JsonField<Long>) = apply {
                this.proxyBytesUsed = proxyBytesUsed
            }

            /** Source of the proxy used for the session */
            fun proxySource(proxySource: ProxySource?) =
                proxySource(JsonField.ofNullable(proxySource))

            /** Source of the proxy used for the session */
            fun proxySource(proxySource: Optional<ProxySource>) =
                proxySource(proxySource.getOrNull())

            /** Source of the proxy used for the session */
            fun proxySource(proxySource: JsonField<ProxySource>) = apply {
                this.proxySource = proxySource
            }

            /** URL to view session details */
            fun sessionViewerUrl(sessionViewerUrl: String) =
                sessionViewerUrl(JsonField.of(sessionViewerUrl))

            /** URL to view session details */
            fun sessionViewerUrl(sessionViewerUrl: JsonField<String>) = apply {
                this.sessionViewerUrl = sessionViewerUrl
            }

            /**
             * Signals observed during the session. Filled in within a few seconds after the session
             * ends, and empty until then. An empty list does not mean nothing happened: detection
             * is heuristic and coverage is incomplete.
             */
            fun signals(signals: List<Signal>) = signals(JsonField.of(signals))

            /**
             * Signals observed during the session. Filled in within a few seconds after the session
             * ends, and empty until then. An empty list does not mean nothing happened: detection
             * is heuristic and coverage is incomplete.
             */
            fun signals(signals: JsonField<List<Signal>>) = apply {
                this.signals = signals.map { it.toMutableList() }
            }

            /**
             * Signals observed during the session. Filled in within a few seconds after the session
             * ends, and empty until then. An empty list does not mean nothing happened: detection
             * is heuristic and coverage is incomplete.
             */
            fun addSignal(signal: Signal) = apply {
                this.signals =
                    (this.signals ?: JsonField.of(mutableListOf())).also {
                        checkKnown("signals", it).add(signal)
                    }
            }

            /** Status of the session */
            fun status(status: Status) = status(JsonField.of(status))

            /** Status of the session */
            fun status(status: JsonField<Status>) = apply { this.status = status }

            /** Session timeout duration in milliseconds */
            fun timeout(timeout: Long) = timeout(JsonField.of(timeout))

            /** Session timeout duration in milliseconds */
            fun timeout(timeout: JsonField<Long>) = apply { this.timeout = timeout }

            /** URL for the session's WebSocket connection */
            fun websocketUrl(websocketUrl: String) = websocketUrl(JsonField.of(websocketUrl))

            /** URL for the session's WebSocket connection */
            fun websocketUrl(websocketUrl: JsonField<String>) = apply {
                this.websocketUrl = websocketUrl
            }

            /**
             * Configuration for the debug URL and session viewer. Controls interaction capabilities
             * and cursor visibility.
             */
            fun debugConfig(debugConfig: DebugConfig) = debugConfig(JsonField.of(debugConfig))

            /**
             * Configuration for the debug URL and session viewer. Controls interaction capabilities
             * and cursor visibility.
             */
            fun debugConfig(debugConfig: JsonField<DebugConfig>) = apply {
                this.debugConfig = debugConfig
            }

            /** Device configuration for the session */
            fun deviceConfig(deviceConfig: DeviceConfig) = deviceConfig(JsonField.of(deviceConfig))

            /** Device configuration for the session */
            fun deviceConfig(deviceConfig: JsonField<DeviceConfig>) = apply {
                this.deviceConfig = deviceConfig
            }

            /**
             * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
             */
            fun fullscreen(fullscreen: Boolean) = fullscreen(JsonField.of(fullscreen))

            /**
             * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
             */
            fun fullscreen(fullscreen: JsonField<Boolean>) = apply { this.fullscreen = fullscreen }

            /** Indicates if the session is headless or headful */
            fun headless(headless: Boolean) = headless(JsonField.of(headless))

            /** Indicates if the session is headless or headful */
            fun headless(headless: JsonField<Boolean>) = apply { this.headless = headless }

            /** Inactivity timeout in milliseconds, if one was set when the session was created */
            fun inactivityTimeout(inactivityTimeout: Long) =
                inactivityTimeout(JsonField.of(inactivityTimeout))

            /** Inactivity timeout in milliseconds, if one was set when the session was created */
            fun inactivityTimeout(inactivityTimeout: JsonField<Long>) = apply {
                this.inactivityTimeout = inactivityTimeout
            }

            /** Indicates if Selenium is used in the session */
            fun isSelenium(isSelenium: Boolean) = isSelenium(JsonField.of(isSelenium))

            /** Indicates if Selenium is used in the session */
            fun isSelenium(isSelenium: JsonField<Boolean>) = apply { this.isSelenium = isSelenium }

            /**
             * Customer-defined flat metadata. Values are always strings: numbers and booleans sent
             * on write come back in their string form.
             */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /**
             * Customer-defined flat metadata. Values are always strings: numbers and booleans sent
             * on write come back in their string form.
             */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            /** This flag will persist the profile for the session. */
            fun persistProfile(persistProfile: Boolean) =
                persistProfile(JsonField.of(persistProfile))

            /** This flag will persist the profile for the session. */
            fun persistProfile(persistProfile: JsonField<Boolean>) = apply {
                this.persistProfile = persistProfile
            }

            /** The ID of the profile associated with the session */
            fun profileId(profileId: String) = profileId(JsonField.of(profileId))

            /** The ID of the profile associated with the session */
            fun profileId(profileId: JsonField<String>) = apply { this.profileId = profileId }

            /** The project associated with the session */
            fun projectId(projectId: String?) = projectId(JsonField.ofNullable(projectId))

            /** The project associated with the session */
            fun projectId(projectId: Optional<String>) = projectId(projectId.getOrNull())

            /** The project associated with the session */
            fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

            /** The region where the session was created. */
            fun region(region: String) = region(JsonField.of(region))

            /** The region where the session was created. */
            fun region(region: JsonField<String>) = apply { this.region = region }

            /**
             * Why the session reached a terminal state. Null while the session is live, or when the
             * reason is unknown (e.g. sessions created before this was tracked). One of:
             * user_requested (released via the API/SDK), timeout (hard `timeout` elapsed),
             * inactivity_timeout (no activity for the configured window), creation_timeout (never
             * started in time), startup_failed (could not be dispatched), browser_closed (the
             * browser or agent closed itself — not a crash), browser_crashed (the browser crashed
             * or its machine became unresponsive).
             */
            fun releaseReason(releaseReason: ReleaseReason?) =
                releaseReason(JsonField.ofNullable(releaseReason))

            /**
             * Why the session reached a terminal state. Null while the session is live, or when the
             * reason is unknown (e.g. sessions created before this was tracked). One of:
             * user_requested (released via the API/SDK), timeout (hard `timeout` elapsed),
             * inactivity_timeout (no activity for the configured window), creation_timeout (never
             * started in time), startup_failed (could not be dispatched), browser_closed (the
             * browser or agent closed itself — not a crash), browser_crashed (the browser crashed
             * or its machine became unresponsive).
             */
            fun releaseReason(releaseReason: Optional<ReleaseReason>) =
                releaseReason(releaseReason.getOrNull())

            /**
             * Why the session reached a terminal state. Null while the session is live, or when the
             * reason is unknown (e.g. sessions created before this was tracked). One of:
             * user_requested (released via the API/SDK), timeout (hard `timeout` elapsed),
             * inactivity_timeout (no activity for the configured window), creation_timeout (never
             * started in time), startup_failed (could not be dispatched), browser_closed (the
             * browser or agent closed itself — not a crash), browser_crashed (the browser crashed
             * or its machine became unresponsive).
             */
            fun releaseReason(releaseReason: JsonField<ReleaseReason>) = apply {
                this.releaseReason = releaseReason
            }

            /** Indicates if captcha solving is enabled */
            fun solveCaptcha(solveCaptcha: Boolean) = solveCaptcha(JsonField.of(solveCaptcha))

            /** Indicates if captcha solving is enabled */
            fun solveCaptcha(solveCaptcha: JsonField<Boolean>) = apply {
                this.solveCaptcha = solveCaptcha
            }

            /** Stealth configuration for the session */
            fun stealthConfig(stealthConfig: StealthConfig) =
                stealthConfig(JsonField.of(stealthConfig))

            /** Stealth configuration for the session */
            fun stealthConfig(stealthConfig: JsonField<StealthConfig>) = apply {
                this.stealthConfig = stealthConfig
            }

            /** User agent string used in the session */
            fun userAgent(userAgent: String) = userAgent(JsonField.of(userAgent))

            /** User agent string used in the session */
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

            fun build(): Session =
                Session(
                    checkRequired("id", id),
                    checkRequired("browserMode", browserMode),
                    checkRequired("createdAt", createdAt),
                    checkRequired("creditsUsed", creditsUsed),
                    checkRequired("debugUrl", debugUrl),
                    checkRequired("dimensions", dimensions),
                    checkRequired("duration", duration),
                    checkRequired("eventCount", eventCount),
                    checkRequired("optimizeBandwidth", optimizeBandwidth),
                    checkRequired("proxyBytesUsed", proxyBytesUsed),
                    checkRequired("proxySource", proxySource),
                    checkRequired("sessionViewerUrl", sessionViewerUrl),
                    checkRequired("signals", signals).map { it.toImmutable() },
                    checkRequired("status", status),
                    checkRequired("timeout", timeout),
                    checkRequired("websocketUrl", websocketUrl),
                    debugConfig,
                    deviceConfig,
                    fullscreen,
                    headless,
                    inactivityTimeout,
                    isSelenium,
                    metadata,
                    persistProfile,
                    profileId,
                    projectId,
                    region,
                    releaseReason,
                    solveCaptcha,
                    stealthConfig,
                    userAgent,
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

        fun validate(): Session = apply {
            if (validated) {
                return@apply
            }

            id()
            browserMode().validate()
            createdAt()
            creditsUsed()
            debugUrl()
            dimensions().validate()
            duration()
            eventCount()
            optimizeBandwidth().validate()
            proxyBytesUsed()
            proxySource().ifPresent { it.validate() }
            sessionViewerUrl()
            signals().forEach { it.validate() }
            status().validate()
            timeout()
            websocketUrl()
            debugConfig().ifPresent { it.validate() }
            deviceConfig().ifPresent { it.validate() }
            fullscreen()
            headless()
            inactivityTimeout()
            isSelenium()
            metadata().ifPresent { it.validate() }
            persistProfile()
            profileId()
            projectId()
            region()
            releaseReason().ifPresent { it.validate() }
            solveCaptcha()
            stealthConfig().ifPresent { it.validate() }
            userAgent()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (id.asKnown().isPresent) 1 else 0) +
                (browserMode.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (createdAt.asKnown().isPresent) 1 else 0) +
                (if (creditsUsed.asKnown().isPresent) 1 else 0) +
                (if (debugUrl.asKnown().isPresent) 1 else 0) +
                (dimensions.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (duration.asKnown().isPresent) 1 else 0) +
                (if (eventCount.asKnown().isPresent) 1 else 0) +
                (optimizeBandwidth.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (proxyBytesUsed.asKnown().isPresent) 1 else 0) +
                (proxySource.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (sessionViewerUrl.asKnown().isPresent) 1 else 0) +
                (signals.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
                (status.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (timeout.asKnown().isPresent) 1 else 0) +
                (if (websocketUrl.asKnown().isPresent) 1 else 0) +
                (debugConfig.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (deviceConfig.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (fullscreen.asKnown().isPresent) 1 else 0) +
                (if (headless.asKnown().isPresent) 1 else 0) +
                (if (inactivityTimeout.asKnown().isPresent) 1 else 0) +
                (if (isSelenium.asKnown().isPresent) 1 else 0) +
                (metadata.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (persistProfile.asKnown().isPresent) 1 else 0) +
                (if (profileId.asKnown().isPresent) 1 else 0) +
                (if (projectId.asKnown().isPresent) 1 else 0) +
                (if (region.asKnown().isPresent) 1 else 0) +
                (releaseReason.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (solveCaptcha.asKnown().isPresent) 1 else 0) +
                (stealthConfig.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (userAgent.asKnown().isPresent) 1 else 0)

        class BrowserMode @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val STEALTH = of("stealth")

                @JvmField val STANDARD = of("standard")

                @JvmField val UNKNOWN = of("unknown")

                @JvmStatic fun of(value: String) = BrowserMode(JsonField.of(value))
            }

            enum class Known {
                STEALTH,
                STANDARD,
                UNKNOWN,
            }

            enum class Value {
                STEALTH,
                STANDARD,
                UNKNOWN,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    STEALTH -> Value.STEALTH
                    STANDARD -> Value.STANDARD
                    UNKNOWN -> Value.UNKNOWN
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    STEALTH -> Known.STEALTH
                    STANDARD -> Known.STANDARD
                    UNKNOWN -> Known.UNKNOWN
                    else -> throw SteelInvalidDataException("Unknown BrowserMode: $value")
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

            fun validate(): BrowserMode = apply {
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

                return other is BrowserMode && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        /** Viewport and browser window dimensions for the session */
        class Dimensions
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val height: JsonField<Long>,
            private val width: JsonField<Long>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("height") @ExcludeMissing height: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("width") @ExcludeMissing width: JsonField<Long> = JsonMissing.of(),
            ) : this(height, width, mutableMapOf())

            /** Height of the browser window */
            fun height(): Long = height.getRequired("height")

            /** Width of the browser window */
            fun width(): Long = width.getRequired("width")

            @JsonProperty("height") @ExcludeMissing fun _height(): JsonField<Long> = height

            @JsonProperty("width") @ExcludeMissing fun _width(): JsonField<Long> = width

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

                private var height: JsonField<Long>? = null
                private var width: JsonField<Long>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(dimensions: Dimensions) = apply {
                    this.height = dimensions.height
                    this.width = dimensions.width
                    this.additionalProperties = dimensions.additionalProperties.toMutableMap()
                }

                /** Height of the browser window */
                fun height(height: Long) = height(JsonField.of(height))

                /** Height of the browser window */
                fun height(height: JsonField<Long>) = apply { this.height = height }

                /** Width of the browser window */
                fun width(width: Long) = width(JsonField.of(width))

                /** Width of the browser window */
                fun width(width: JsonField<Long>) = apply { this.width = width }

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

        /** Bandwidth optimizations that were applied to the session. */
        class OptimizeBandwidth
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val blockHosts: JsonField<List<String>>,
            private val blockImages: JsonField<Boolean>,
            private val blockMedia: JsonField<Boolean>,
            private val blockStylesheets: JsonField<Boolean>,
            private val blockUrlPatterns: JsonField<List<String>>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("blockHosts")
                @ExcludeMissing
                blockHosts: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("blockImages")
                @ExcludeMissing
                blockImages: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("blockMedia")
                @ExcludeMissing
                blockMedia: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("blockStylesheets")
                @ExcludeMissing
                blockStylesheets: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("blockUrlPatterns")
                @ExcludeMissing
                blockUrlPatterns: JsonField<List<String>> = JsonMissing.of(),
            ) : this(
                blockHosts,
                blockImages,
                blockMedia,
                blockStylesheets,
                blockUrlPatterns,
                mutableMapOf(),
            )

            fun blockHosts(): Optional<List<String>> = blockHosts.getOptional("blockHosts")

            fun blockImages(): Optional<Boolean> = blockImages.getOptional("blockImages")

            fun blockMedia(): Optional<Boolean> = blockMedia.getOptional("blockMedia")

            fun blockStylesheets(): Optional<Boolean> =
                blockStylesheets.getOptional("blockStylesheets")

            fun blockUrlPatterns(): Optional<List<String>> =
                blockUrlPatterns.getOptional("blockUrlPatterns")

            @JsonProperty("blockHosts")
            @ExcludeMissing
            fun _blockHosts(): JsonField<List<String>> = blockHosts

            @JsonProperty("blockImages")
            @ExcludeMissing
            fun _blockImages(): JsonField<Boolean> = blockImages

            @JsonProperty("blockMedia")
            @ExcludeMissing
            fun _blockMedia(): JsonField<Boolean> = blockMedia

            @JsonProperty("blockStylesheets")
            @ExcludeMissing
            fun _blockStylesheets(): JsonField<Boolean> = blockStylesheets

            @JsonProperty("blockUrlPatterns")
            @ExcludeMissing
            fun _blockUrlPatterns(): JsonField<List<String>> = blockUrlPatterns

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
                 * Returns a mutable builder for constructing an instance of [OptimizeBandwidth].
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [OptimizeBandwidth]. */
            class Builder internal constructor() {

                private var blockHosts: JsonField<MutableList<String>>? = null
                private var blockImages: JsonField<Boolean> = JsonMissing.of()
                private var blockMedia: JsonField<Boolean> = JsonMissing.of()
                private var blockStylesheets: JsonField<Boolean> = JsonMissing.of()
                private var blockUrlPatterns: JsonField<MutableList<String>>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(optimizeBandwidth: OptimizeBandwidth) = apply {
                    this.blockHosts = optimizeBandwidth.blockHosts.map { it.toMutableList() }
                    this.blockImages = optimizeBandwidth.blockImages
                    this.blockMedia = optimizeBandwidth.blockMedia
                    this.blockStylesheets = optimizeBandwidth.blockStylesheets
                    this.blockUrlPatterns =
                        optimizeBandwidth.blockUrlPatterns.map { it.toMutableList() }
                    this.additionalProperties =
                        optimizeBandwidth.additionalProperties.toMutableMap()
                }

                fun blockHosts(blockHosts: List<String>) = blockHosts(JsonField.of(blockHosts))

                fun blockHosts(blockHosts: JsonField<List<String>>) = apply {
                    this.blockHosts = blockHosts.map { it.toMutableList() }
                }

                fun addBlockHost(blockHost: String) = apply {
                    this.blockHosts =
                        (this.blockHosts ?: JsonField.of(mutableListOf())).also {
                            checkKnown("blockHosts", it).add(blockHost)
                        }
                }

                fun blockImages(blockImages: Boolean) = blockImages(JsonField.of(blockImages))

                fun blockImages(blockImages: JsonField<Boolean>) = apply {
                    this.blockImages = blockImages
                }

                fun blockMedia(blockMedia: Boolean) = blockMedia(JsonField.of(blockMedia))

                fun blockMedia(blockMedia: JsonField<Boolean>) = apply {
                    this.blockMedia = blockMedia
                }

                fun blockStylesheets(blockStylesheets: Boolean) =
                    blockStylesheets(JsonField.of(blockStylesheets))

                fun blockStylesheets(blockStylesheets: JsonField<Boolean>) = apply {
                    this.blockStylesheets = blockStylesheets
                }

                fun blockUrlPatterns(blockUrlPatterns: List<String>) =
                    blockUrlPatterns(JsonField.of(blockUrlPatterns))

                fun blockUrlPatterns(blockUrlPatterns: JsonField<List<String>>) = apply {
                    this.blockUrlPatterns = blockUrlPatterns.map { it.toMutableList() }
                }

                fun addBlockUrlPattern(blockUrlPattern: String) = apply {
                    this.blockUrlPatterns =
                        (this.blockUrlPatterns ?: JsonField.of(mutableListOf())).also {
                            checkKnown("blockUrlPatterns", it).add(blockUrlPattern)
                        }
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

                fun build(): OptimizeBandwidth =
                    OptimizeBandwidth(
                        (blockHosts ?: JsonMissing.of()).map { it.toImmutable() },
                        blockImages,
                        blockMedia,
                        blockStylesheets,
                        (blockUrlPatterns ?: JsonMissing.of()).map { it.toImmutable() },
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

            fun validate(): OptimizeBandwidth = apply {
                if (validated) {
                    return@apply
                }

                blockHosts()
                blockImages()
                blockMedia()
                blockStylesheets()
                blockUrlPatterns()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (blockHosts.asKnown().getOrNull()?.let { it.size } ?: 0) +
                    (if (blockImages.asKnown().isPresent) 1 else 0) +
                    (if (blockMedia.asKnown().isPresent) 1 else 0) +
                    (if (blockStylesheets.asKnown().isPresent) 1 else 0) +
                    (blockUrlPatterns.asKnown().getOrNull()?.let { it.size } ?: 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is OptimizeBandwidth &&
                    this.blockHosts == other.blockHosts &&
                    this.blockImages == other.blockImages &&
                    this.blockMedia == other.blockMedia &&
                    this.blockStylesheets == other.blockStylesheets &&
                    this.blockUrlPatterns == other.blockUrlPatterns &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    blockHosts,
                    blockImages,
                    blockMedia,
                    blockStylesheets,
                    blockUrlPatterns,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "OptimizeBandwidth{blockHosts=$blockHosts, blockImages=$blockImages, blockMedia=$blockMedia, blockStylesheets=$blockStylesheets, blockUrlPatterns=$blockUrlPatterns, additionalProperties=$additionalProperties}"
        }

        class ProxySource @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val STEEL = of("steel")

                @JvmField val EXTERNAL = of("external")

                @JvmStatic fun of(value: String) = ProxySource(JsonField.of(value))
            }

            enum class Known {
                STEEL,
                EXTERNAL,
            }

            enum class Value {
                STEEL,
                EXTERNAL,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    STEEL -> Value.STEEL
                    EXTERNAL -> Value.EXTERNAL
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    STEEL -> Known.STEEL
                    EXTERNAL -> Known.EXTERNAL
                    else -> throw SteelInvalidDataException("Unknown ProxySource: $value")
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

            fun validate(): ProxySource = apply {
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

                return other is ProxySource && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        class Signal @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val CAPTCHA_ENCOUNTERED = of("captcha_encountered")

                @JvmField val PROXY_CONNECTION_FAILED = of("proxy_connection_failed")

                @JvmField val SESSION_TIMEOUT_EXCEEDED = of("session_timeout_exceeded")

                @JvmField val BOT_DETECTION_TRIGGERED = of("bot_detection_triggered")

                @JvmField val CONTEXT_RESTORED = of("context_restored")

                @JvmStatic fun of(value: String) = Signal(JsonField.of(value))
            }

            enum class Known {
                CAPTCHA_ENCOUNTERED,
                PROXY_CONNECTION_FAILED,
                SESSION_TIMEOUT_EXCEEDED,
                BOT_DETECTION_TRIGGERED,
                CONTEXT_RESTORED,
            }

            enum class Value {
                CAPTCHA_ENCOUNTERED,
                PROXY_CONNECTION_FAILED,
                SESSION_TIMEOUT_EXCEEDED,
                BOT_DETECTION_TRIGGERED,
                CONTEXT_RESTORED,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    CAPTCHA_ENCOUNTERED -> Value.CAPTCHA_ENCOUNTERED
                    PROXY_CONNECTION_FAILED -> Value.PROXY_CONNECTION_FAILED
                    SESSION_TIMEOUT_EXCEEDED -> Value.SESSION_TIMEOUT_EXCEEDED
                    BOT_DETECTION_TRIGGERED -> Value.BOT_DETECTION_TRIGGERED
                    CONTEXT_RESTORED -> Value.CONTEXT_RESTORED
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    CAPTCHA_ENCOUNTERED -> Known.CAPTCHA_ENCOUNTERED
                    PROXY_CONNECTION_FAILED -> Known.PROXY_CONNECTION_FAILED
                    SESSION_TIMEOUT_EXCEEDED -> Known.SESSION_TIMEOUT_EXCEEDED
                    BOT_DETECTION_TRIGGERED -> Known.BOT_DETECTION_TRIGGERED
                    CONTEXT_RESTORED -> Known.CONTEXT_RESTORED
                    else -> throw SteelInvalidDataException("Unknown Signal: $value")
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

            fun validate(): Signal = apply {
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

                return other is Signal && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val LIVE = of("live")

                @JvmField val RELEASED = of("released")

                @JvmField val FAILED = of("failed")

                @JvmStatic fun of(value: String) = Status(JsonField.of(value))
            }

            enum class Known {
                LIVE,
                RELEASED,
                FAILED,
            }

            enum class Value {
                LIVE,
                RELEASED,
                FAILED,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    LIVE -> Value.LIVE
                    RELEASED -> Value.RELEASED
                    FAILED -> Value.FAILED
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    LIVE -> Known.LIVE
                    RELEASED -> Known.RELEASED
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

        /**
         * Configuration for the debug URL and session viewer. Controls interaction capabilities and
         * cursor visibility.
         */
        class DebugConfig
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val interactive: JsonField<Boolean>,
            private val systemCursor: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("interactive")
                @ExcludeMissing
                interactive: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("systemCursor")
                @ExcludeMissing
                systemCursor: JsonField<Boolean> = JsonMissing.of(),
            ) : this(interactive, systemCursor, mutableMapOf())

            /**
             * Whether interaction is allowed via the debug URL viewer. When false, the session
             * viewer is view-only.
             */
            fun interactive(): Optional<Boolean> = interactive.getOptional("interactive")

            /**
             * Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode only).
             */
            fun systemCursor(): Optional<Boolean> = systemCursor.getOptional("systemCursor")

            @JsonProperty("interactive")
            @ExcludeMissing
            fun _interactive(): JsonField<Boolean> = interactive

            @JsonProperty("systemCursor")
            @ExcludeMissing
            fun _systemCursor(): JsonField<Boolean> = systemCursor

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

                /** Returns a mutable builder for constructing an instance of [DebugConfig]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [DebugConfig]. */
            class Builder internal constructor() {

                private var interactive: JsonField<Boolean> = JsonMissing.of()
                private var systemCursor: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(debugConfig: DebugConfig) = apply {
                    this.interactive = debugConfig.interactive
                    this.systemCursor = debugConfig.systemCursor
                    this.additionalProperties = debugConfig.additionalProperties.toMutableMap()
                }

                /**
                 * Whether interaction is allowed via the debug URL viewer. When false, the session
                 * viewer is view-only.
                 */
                fun interactive(interactive: Boolean) = interactive(JsonField.of(interactive))

                /**
                 * Whether interaction is allowed via the debug URL viewer. When false, the session
                 * viewer is view-only.
                 */
                fun interactive(interactive: JsonField<Boolean>) = apply {
                    this.interactive = interactive
                }

                /**
                 * Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode
                 * only).
                 */
                fun systemCursor(systemCursor: Boolean) = systemCursor(JsonField.of(systemCursor))

                /**
                 * Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode
                 * only).
                 */
                fun systemCursor(systemCursor: JsonField<Boolean>) = apply {
                    this.systemCursor = systemCursor
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

                fun build(): DebugConfig =
                    DebugConfig(interactive, systemCursor, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): DebugConfig = apply {
                if (validated) {
                    return@apply
                }

                interactive()
                systemCursor()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (if (interactive.asKnown().isPresent) 1 else 0) +
                    (if (systemCursor.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is DebugConfig &&
                    this.interactive == other.interactive &&
                    this.systemCursor == other.systemCursor &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(interactive, systemCursor, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "DebugConfig{interactive=$interactive, systemCursor=$systemCursor, additionalProperties=$additionalProperties}"
        }

        /** Device configuration for the session */
        class DeviceConfig
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val device: JsonField<Device>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("device") @ExcludeMissing device: JsonField<Device> = JsonMissing.of()
            ) : this(device, mutableMapOf())

            fun device(): Optional<Device> = device.getOptional("device")

            @JsonProperty("device") @ExcludeMissing fun _device(): JsonField<Device> = device

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

                /** Returns a mutable builder for constructing an instance of [DeviceConfig]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [DeviceConfig]. */
            class Builder internal constructor() {

                private var device: JsonField<Device> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(deviceConfig: DeviceConfig) = apply {
                    this.device = deviceConfig.device
                    this.additionalProperties = deviceConfig.additionalProperties.toMutableMap()
                }

                fun device(device: Device) = device(JsonField.of(device))

                fun device(device: JsonField<Device>) = apply { this.device = device }

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

                fun build(): DeviceConfig =
                    DeviceConfig(device, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): DeviceConfig = apply {
                if (validated) {
                    return@apply
                }

                device().ifPresent { it.validate() }
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (device.asKnown().getOrNull()?.let { it.validity() } ?: 0)

            class Device @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val DESKTOP = of("desktop")

                    @JvmField val MOBILE = of("mobile")

                    @JvmStatic fun of(value: String) = Device(JsonField.of(value))
                }

                enum class Known {
                    DESKTOP,
                    MOBILE,
                }

                enum class Value {
                    DESKTOP,
                    MOBILE,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        DESKTOP -> Value.DESKTOP
                        MOBILE -> Value.MOBILE
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        DESKTOP -> Known.DESKTOP
                        MOBILE -> Known.MOBILE
                        else -> throw SteelInvalidDataException("Unknown Device: $value")
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

                fun validate(): Device = apply {
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

                    return other is Device && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is DeviceConfig &&
                    this.device == other.device &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(device, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "DeviceConfig{device=$device, additionalProperties=$additionalProperties}"
        }

        class Metadata
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

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(metadata: Metadata) = apply {
                    this.additionalProperties = metadata.additionalProperties.toMutableMap()
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

                fun build(): Metadata = Metadata(additionalProperties.toImmutable())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Metadata = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Metadata && this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
        }

        class ReleaseReason @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val USER_REQUESTED = of("user_requested")

                @JvmField val TIMEOUT = of("timeout")

                @JvmField val INACTIVITY_TIMEOUT = of("inactivity_timeout")

                @JvmField val CREATION_TIMEOUT = of("creation_timeout")

                @JvmField val STARTUP_FAILED = of("startup_failed")

                @JvmField val BROWSER_CLOSED = of("browser_closed")

                @JvmField val BROWSER_CRASHED = of("browser_crashed")

                @JvmStatic fun of(value: String) = ReleaseReason(JsonField.of(value))
            }

            enum class Known {
                USER_REQUESTED,
                TIMEOUT,
                INACTIVITY_TIMEOUT,
                CREATION_TIMEOUT,
                STARTUP_FAILED,
                BROWSER_CLOSED,
                BROWSER_CRASHED,
            }

            enum class Value {
                USER_REQUESTED,
                TIMEOUT,
                INACTIVITY_TIMEOUT,
                CREATION_TIMEOUT,
                STARTUP_FAILED,
                BROWSER_CLOSED,
                BROWSER_CRASHED,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    USER_REQUESTED -> Value.USER_REQUESTED
                    TIMEOUT -> Value.TIMEOUT
                    INACTIVITY_TIMEOUT -> Value.INACTIVITY_TIMEOUT
                    CREATION_TIMEOUT -> Value.CREATION_TIMEOUT
                    STARTUP_FAILED -> Value.STARTUP_FAILED
                    BROWSER_CLOSED -> Value.BROWSER_CLOSED
                    BROWSER_CRASHED -> Value.BROWSER_CRASHED
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    USER_REQUESTED -> Known.USER_REQUESTED
                    TIMEOUT -> Known.TIMEOUT
                    INACTIVITY_TIMEOUT -> Known.INACTIVITY_TIMEOUT
                    CREATION_TIMEOUT -> Known.CREATION_TIMEOUT
                    STARTUP_FAILED -> Known.STARTUP_FAILED
                    BROWSER_CLOSED -> Known.BROWSER_CLOSED
                    BROWSER_CRASHED -> Known.BROWSER_CRASHED
                    else -> throw SteelInvalidDataException("Unknown ReleaseReason: $value")
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

            fun validate(): ReleaseReason = apply {
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

                return other is ReleaseReason && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        /** Stealth configuration for the session */
        class StealthConfig
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val autoCaptchaSolving: JsonField<Boolean>,
            private val humanizeInteractions: JsonField<Boolean>,
            private val skipFingerprintInjection: JsonField<Boolean>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("autoCaptchaSolving")
                @ExcludeMissing
                autoCaptchaSolving: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("humanizeInteractions")
                @ExcludeMissing
                humanizeInteractions: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("skipFingerprintInjection")
                @ExcludeMissing
                skipFingerprintInjection: JsonField<Boolean> = JsonMissing.of(),
            ) : this(
                autoCaptchaSolving,
                humanizeInteractions,
                skipFingerprintInjection,
                mutableMapOf(),
            )

            /**
             * When true, captchas will be automatically solved when detected. When false, use the
             * solve endpoints to manually initiate solving.
             */
            fun autoCaptchaSolving(): Optional<Boolean> =
                autoCaptchaSolving.getOptional("autoCaptchaSolving")

            /**
             * This flag will make the browser act more human-like by moving the mouse in a more
             * natural way
             */
            fun humanizeInteractions(): Optional<Boolean> =
                humanizeInteractions.getOptional("humanizeInteractions")

            /** This flag will skip the fingerprint generation for the session. */
            fun skipFingerprintInjection(): Optional<Boolean> =
                skipFingerprintInjection.getOptional("skipFingerprintInjection")

            @JsonProperty("autoCaptchaSolving")
            @ExcludeMissing
            fun _autoCaptchaSolving(): JsonField<Boolean> = autoCaptchaSolving

            @JsonProperty("humanizeInteractions")
            @ExcludeMissing
            fun _humanizeInteractions(): JsonField<Boolean> = humanizeInteractions

            @JsonProperty("skipFingerprintInjection")
            @ExcludeMissing
            fun _skipFingerprintInjection(): JsonField<Boolean> = skipFingerprintInjection

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

                /** Returns a mutable builder for constructing an instance of [StealthConfig]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [StealthConfig]. */
            class Builder internal constructor() {

                private var autoCaptchaSolving: JsonField<Boolean> = JsonMissing.of()
                private var humanizeInteractions: JsonField<Boolean> = JsonMissing.of()
                private var skipFingerprintInjection: JsonField<Boolean> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(stealthConfig: StealthConfig) = apply {
                    this.autoCaptchaSolving = stealthConfig.autoCaptchaSolving
                    this.humanizeInteractions = stealthConfig.humanizeInteractions
                    this.skipFingerprintInjection = stealthConfig.skipFingerprintInjection
                    this.additionalProperties = stealthConfig.additionalProperties.toMutableMap()
                }

                /**
                 * When true, captchas will be automatically solved when detected. When false, use
                 * the solve endpoints to manually initiate solving.
                 */
                fun autoCaptchaSolving(autoCaptchaSolving: Boolean) =
                    autoCaptchaSolving(JsonField.of(autoCaptchaSolving))

                /**
                 * When true, captchas will be automatically solved when detected. When false, use
                 * the solve endpoints to manually initiate solving.
                 */
                fun autoCaptchaSolving(autoCaptchaSolving: JsonField<Boolean>) = apply {
                    this.autoCaptchaSolving = autoCaptchaSolving
                }

                /**
                 * This flag will make the browser act more human-like by moving the mouse in a more
                 * natural way
                 */
                fun humanizeInteractions(humanizeInteractions: Boolean) =
                    humanizeInteractions(JsonField.of(humanizeInteractions))

                /**
                 * This flag will make the browser act more human-like by moving the mouse in a more
                 * natural way
                 */
                fun humanizeInteractions(humanizeInteractions: JsonField<Boolean>) = apply {
                    this.humanizeInteractions = humanizeInteractions
                }

                /** This flag will skip the fingerprint generation for the session. */
                fun skipFingerprintInjection(skipFingerprintInjection: Boolean) =
                    skipFingerprintInjection(JsonField.of(skipFingerprintInjection))

                /** This flag will skip the fingerprint generation for the session. */
                fun skipFingerprintInjection(skipFingerprintInjection: JsonField<Boolean>) = apply {
                    this.skipFingerprintInjection = skipFingerprintInjection
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

                fun build(): StealthConfig =
                    StealthConfig(
                        autoCaptchaSolving,
                        humanizeInteractions,
                        skipFingerprintInjection,
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

            fun validate(): StealthConfig = apply {
                if (validated) {
                    return@apply
                }

                autoCaptchaSolving()
                humanizeInteractions()
                skipFingerprintInjection()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (if (autoCaptchaSolving.asKnown().isPresent) 1 else 0) +
                    (if (humanizeInteractions.asKnown().isPresent) 1 else 0) +
                    (if (skipFingerprintInjection.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is StealthConfig &&
                    this.autoCaptchaSolving == other.autoCaptchaSolving &&
                    this.humanizeInteractions == other.humanizeInteractions &&
                    this.skipFingerprintInjection == other.skipFingerprintInjection &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    autoCaptchaSolving,
                    humanizeInteractions,
                    skipFingerprintInjection,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "StealthConfig{autoCaptchaSolving=$autoCaptchaSolving, humanizeInteractions=$humanizeInteractions, skipFingerprintInjection=$skipFingerprintInjection, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Session &&
                this.id == other.id &&
                this.browserMode == other.browserMode &&
                this.createdAt == other.createdAt &&
                this.creditsUsed == other.creditsUsed &&
                this.debugUrl == other.debugUrl &&
                this.dimensions == other.dimensions &&
                this.duration == other.duration &&
                this.eventCount == other.eventCount &&
                this.optimizeBandwidth == other.optimizeBandwidth &&
                this.proxyBytesUsed == other.proxyBytesUsed &&
                this.proxySource == other.proxySource &&
                this.sessionViewerUrl == other.sessionViewerUrl &&
                this.signals == other.signals &&
                this.status == other.status &&
                this.timeout == other.timeout &&
                this.websocketUrl == other.websocketUrl &&
                this.debugConfig == other.debugConfig &&
                this.deviceConfig == other.deviceConfig &&
                this.fullscreen == other.fullscreen &&
                this.headless == other.headless &&
                this.inactivityTimeout == other.inactivityTimeout &&
                this.isSelenium == other.isSelenium &&
                this.metadata == other.metadata &&
                this.persistProfile == other.persistProfile &&
                this.profileId == other.profileId &&
                this.projectId == other.projectId &&
                this.region == other.region &&
                this.releaseReason == other.releaseReason &&
                this.solveCaptcha == other.solveCaptcha &&
                this.stealthConfig == other.stealthConfig &&
                this.userAgent == other.userAgent &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                browserMode,
                createdAt,
                creditsUsed,
                debugUrl,
                dimensions,
                duration,
                eventCount,
                optimizeBandwidth,
                proxyBytesUsed,
                proxySource,
                sessionViewerUrl,
                signals,
                status,
                timeout,
                websocketUrl,
                debugConfig,
                deviceConfig,
                fullscreen,
                headless,
                inactivityTimeout,
                isSelenium,
                metadata,
                persistProfile,
                profileId,
                projectId,
                region,
                releaseReason,
                solveCaptcha,
                stealthConfig,
                userAgent,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Session{id=$id, browserMode=$browserMode, createdAt=$createdAt, creditsUsed=$creditsUsed, debugUrl=$debugUrl, dimensions=$dimensions, duration=$duration, eventCount=$eventCount, optimizeBandwidth=$optimizeBandwidth, proxyBytesUsed=$proxyBytesUsed, proxySource=$proxySource, sessionViewerUrl=$sessionViewerUrl, signals=$signals, status=$status, timeout=$timeout, websocketUrl=$websocketUrl, debugConfig=$debugConfig, deviceConfig=$deviceConfig, fullscreen=$fullscreen, headless=$headless, inactivityTimeout=$inactivityTimeout, isSelenium=$isSelenium, metadata=$metadata, persistProfile=$persistProfile, profileId=$profileId, projectId=$projectId, region=$region, releaseReason=$releaseReason, solveCaptcha=$solveCaptcha, stealthConfig=$stealthConfig, userAgent=$userAgent, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Sessionslist &&
            this.nextCursor == other.nextCursor &&
            this.sessions == other.sessions &&
            this.totalCount == other.totalCount &&
            this.additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(nextCursor, sessions, totalCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Sessionslist{nextCursor=$nextCursor, sessions=$sessions, totalCount=$totalCount, additionalProperties=$additionalProperties}"
}
