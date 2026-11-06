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

/** Creates a new session with the provided configuration. */
class SessionCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Block ads in the browser session. Default is false. */
    fun blockAds(): Optional<Boolean> = body.blockAds()

    /**
     * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN EXPERIMENTAL
     * FEATURE.
     */
    fun caCertificates(): Optional<List<String>> = body.caCertificates()

    /** Number of sessions to create concurrently (check your plan limit) */
    fun concurrency(): Optional<Long> = body.concurrency()

    /** Configuration for session credentials */
    fun credentials(): Optional<Credentials> = body.credentials()

    /**
     * Configuration for the debug URL and session viewer. Controls interaction capabilities, cursor
     * visibility, and other debug-related settings.
     */
    fun debugConfig(): Optional<DebugConfig> = body.debugConfig()

    /**
     * Device configuration for the session. Specify 'mobile' for mobile device fingerprints and
     * configurations.
     */
    fun deviceConfig(): Optional<DeviceConfig> = body.deviceConfig()

    /**
     * Viewport and browser window dimensions for the session. Mobile sessions require dimensions of
     * at least 508x1074; smaller mobile dimensions are rejected with a 400 response.
     */
    fun dimensions(): Optional<Dimensions> = body.dimensions()

    /** Enable experimental features for the session. */
    fun experimentalFeatures(): Optional<List<String>> = body.experimentalFeatures()

    /**
     * Array of extension IDs to install in the session. Use ['all_ext'] to install all uploaded
     * extensions.
     */
    fun extensionIds(): Optional<List<String>> = body.extensionIds()

    /**
     * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI. Default is
     * false.
     */
    fun fullscreen(): Optional<Boolean> = body.fullscreen()

    /** Enable headless browser mode (disable Headful mode) */
    fun headless(): Optional<Boolean> = body.headless()

    /**
     * Inactivity timeout in milliseconds. When set, the session is released if no CDP command or
     * remote input is received for this duration, even if `timeout` has not yet elapsed. Note that
     * `timeout` remains the hard cap on session lifetime: if `inactivityTimeout` is greater than or
     * equal to the effective `timeout`, it has no effect since `timeout` always elapses first. Omit
     * to disable.
     */
    fun inactivityTimeout(): Optional<Long> = body.inactivityTimeout()

    /**
     * Deprecated. Selenium sessions are no longer supported, and a request that sets this to true
     * is rejected with 400. Connect over CDP with Playwright or Puppeteer instead.
     */
    @Deprecated("deprecated")
    @Suppress("DEPRECATION")
    fun isSelenium(): Optional<Boolean> = body.isSelenium()

    /**
     * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must start with
     * a letter, use only letters, digits, underscores, dots or hyphens, and be at most 64
     * characters; steel_ is reserved. Values are strings (up to 1024 characters, valid Unicode
     * without NUL), finite numbers or booleans. Numbers and booleans are stored as strings, so 3
     * and "3" are the same value.
     */
    fun metadata(): Optional<Metadata> = body.metadata()

    /** The namespace the session should be created against. Defaults to "default". */
    fun namespace(): Optional<String> = body.namespace()

    /**
     * Enable bandwidth optimizations. Passing true enables all flags (except hosts/patterns).
     * Object allows granular control.
     */
    fun optimizeBandwidth(): Optional<OptimizeBandwidth> = body.optimizeBandwidth()

    /** This flag will persist the user profile for the session. */
    fun persistProfile(): Optional<Boolean> = body.persistProfile()

    /** This flag will set the profile for the session. */
    fun profileId(): Optional<String> = body.profileId()

    /**
     * The project to create the session in. When provided, the session namespace is resolved from
     * the project.
     */
    fun projectId(): Optional<String> = body.projectId()

    /**
     * Custom proxy URL for the browser session. Overrides useProxy, disabling Steel-provided
     * proxies in favor of your specified proxy. Format: http(s)://username:password@hostname:port
     */
    fun proxyUrl(): Optional<String> = body.proxyUrl()

    /**
     * Record the headful session for playback after it ends. Default is true. Disabling recording
     * does not affect live WHEP viewing.
     */
    fun recording(): Optional<Boolean> = body.recording()

    /**
     * Session context data to be used in the created session. Sessions will start with an empty
     * context by default.
     */
    fun sessionContext(): Optional<SessionContext> = body.sessionContext()

    /** Optional custom UUID for the session */
    fun sessionId(): Optional<String> = body.sessionId()

    /** Enable automatic captcha solving. Default is false. */
    fun solveCaptcha(): Optional<Boolean> = body.solveCaptcha()

    /** Stealth configuration for the session */
    fun stealthConfig(): Optional<StealthConfig> = body.stealthConfig()

    /** Session timeout duration in milliseconds. Default is 300000 (5 minutes). */
    fun timeout(): Optional<Long> = body.timeout()

    /** Simple boolean to enable/disable Steel proxies */
    fun useProxy(): Optional<UseProxy> = body.useProxy()

    /** Custom user agent string for the browser session */
    fun userAgent(): Optional<String> = body.userAgent()

    fun _blockAds(): JsonField<Boolean> = body._blockAds()

    fun _caCertificates(): JsonField<List<String>> = body._caCertificates()

    fun _concurrency(): JsonField<Long> = body._concurrency()

    fun _credentials(): JsonField<Credentials> = body._credentials()

    fun _debugConfig(): JsonField<DebugConfig> = body._debugConfig()

    fun _deviceConfig(): JsonField<DeviceConfig> = body._deviceConfig()

    fun _dimensions(): JsonField<Dimensions> = body._dimensions()

    fun _experimentalFeatures(): JsonField<List<String>> = body._experimentalFeatures()

    fun _extensionIds(): JsonField<List<String>> = body._extensionIds()

    fun _fullscreen(): JsonField<Boolean> = body._fullscreen()

    fun _headless(): JsonField<Boolean> = body._headless()

    fun _inactivityTimeout(): JsonField<Long> = body._inactivityTimeout()

    fun _isSelenium(): JsonField<Boolean> = body._isSelenium()

    fun _metadata(): JsonField<Metadata> = body._metadata()

    fun _namespace(): JsonField<String> = body._namespace()

    fun _optimizeBandwidth(): JsonField<OptimizeBandwidth> = body._optimizeBandwidth()

    fun _persistProfile(): JsonField<Boolean> = body._persistProfile()

    fun _profileId(): JsonField<String> = body._profileId()

    fun _projectId(): JsonField<String> = body._projectId()

    fun _proxyUrl(): JsonField<String> = body._proxyUrl()

    fun _recording(): JsonField<Boolean> = body._recording()

    fun _region(): JsonValue = body._region()

    fun _sessionContext(): JsonField<SessionContext> = body._sessionContext()

    fun _sessionId(): JsonField<String> = body._sessionId()

    fun _solveCaptcha(): JsonField<Boolean> = body._solveCaptcha()

    fun _stealthConfig(): JsonField<StealthConfig> = body._stealthConfig()

    fun _timeout(): JsonField<Long> = body._timeout()

    fun _useProxy(): JsonField<UseProxy> = body._useProxy()

    fun _userAgent(): JsonField<String> = body._userAgent()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): SessionCreateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [SessionCreateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionCreateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionCreateParams: SessionCreateParams) = apply {
            this.body = sessionCreateParams.body.toBuilder()
            this.additionalHeaders = sessionCreateParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = sessionCreateParams.additionalQueryParams.toBuilder()
        }

        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Block ads in the browser session. Default is false. */
        fun blockAds(blockAds: Boolean) = apply { body.blockAds(blockAds) }

        /** Block ads in the browser session. Default is false. */
        fun blockAds(blockAds: JsonField<Boolean>) = apply { body.blockAds(blockAds) }

        /**
         * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
         * EXPERIMENTAL FEATURE.
         */
        fun caCertificates(caCertificates: List<String>) = apply {
            body.caCertificates(caCertificates)
        }

        /**
         * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
         * EXPERIMENTAL FEATURE.
         */
        fun caCertificates(caCertificates: JsonField<List<String>>) = apply {
            body.caCertificates(caCertificates)
        }

        /**
         * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
         * EXPERIMENTAL FEATURE.
         */
        fun addCaCertificate(caCertificate: String) = apply { body.addCaCertificate(caCertificate) }

        /** Number of sessions to create concurrently (check your plan limit) */
        fun concurrency(concurrency: Long) = apply { body.concurrency(concurrency) }

        /** Number of sessions to create concurrently (check your plan limit) */
        fun concurrency(concurrency: JsonField<Long>) = apply { body.concurrency(concurrency) }

        /** Configuration for session credentials */
        fun credentials(credentials: Credentials) = apply { body.credentials(credentials) }

        /** Configuration for session credentials */
        fun credentials(credentials: JsonField<Credentials>) = apply {
            body.credentials(credentials)
        }

        /**
         * Configuration for the debug URL and session viewer. Controls interaction capabilities,
         * cursor visibility, and other debug-related settings.
         */
        fun debugConfig(debugConfig: DebugConfig) = apply { body.debugConfig(debugConfig) }

        /**
         * Configuration for the debug URL and session viewer. Controls interaction capabilities,
         * cursor visibility, and other debug-related settings.
         */
        fun debugConfig(debugConfig: JsonField<DebugConfig>) = apply {
            body.debugConfig(debugConfig)
        }

        /**
         * Device configuration for the session. Specify 'mobile' for mobile device fingerprints and
         * configurations.
         */
        fun deviceConfig(deviceConfig: DeviceConfig) = apply { body.deviceConfig(deviceConfig) }

        /**
         * Device configuration for the session. Specify 'mobile' for mobile device fingerprints and
         * configurations.
         */
        fun deviceConfig(deviceConfig: JsonField<DeviceConfig>) = apply {
            body.deviceConfig(deviceConfig)
        }

        /**
         * Viewport and browser window dimensions for the session. Mobile sessions require
         * dimensions of at least 508x1074; smaller mobile dimensions are rejected with a 400
         * response.
         */
        fun dimensions(dimensions: Dimensions) = apply { body.dimensions(dimensions) }

        /**
         * Viewport and browser window dimensions for the session. Mobile sessions require
         * dimensions of at least 508x1074; smaller mobile dimensions are rejected with a 400
         * response.
         */
        fun dimensions(dimensions: JsonField<Dimensions>) = apply { body.dimensions(dimensions) }

        /** Enable experimental features for the session. */
        fun experimentalFeatures(experimentalFeatures: List<String>) = apply {
            body.experimentalFeatures(experimentalFeatures)
        }

        /** Enable experimental features for the session. */
        fun experimentalFeatures(experimentalFeatures: JsonField<List<String>>) = apply {
            body.experimentalFeatures(experimentalFeatures)
        }

        /** Enable experimental features for the session. */
        fun addExperimentalFeature(experimentalFeature: String) = apply {
            body.addExperimentalFeature(experimentalFeature)
        }

        /**
         * Array of extension IDs to install in the session. Use ['all_ext'] to install all uploaded
         * extensions.
         */
        fun extensionIds(extensionIds: List<String>) = apply { body.extensionIds(extensionIds) }

        /**
         * Array of extension IDs to install in the session. Use ['all_ext'] to install all uploaded
         * extensions.
         */
        fun extensionIds(extensionIds: JsonField<List<String>>) = apply {
            body.extensionIds(extensionIds)
        }

        /**
         * Array of extension IDs to install in the session. Use ['all_ext'] to install all uploaded
         * extensions.
         */
        fun addExtensionId(extensionId: String) = apply { body.addExtensionId(extensionId) }

        /**
         * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
         * Default is false.
         */
        fun fullscreen(fullscreen: Boolean) = apply { body.fullscreen(fullscreen) }

        /**
         * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
         * Default is false.
         */
        fun fullscreen(fullscreen: JsonField<Boolean>) = apply { body.fullscreen(fullscreen) }

        /** Enable headless browser mode (disable Headful mode) */
        fun headless(headless: Boolean) = apply { body.headless(headless) }

        /** Enable headless browser mode (disable Headful mode) */
        fun headless(headless: JsonField<Boolean>) = apply { body.headless(headless) }

        /**
         * Inactivity timeout in milliseconds. When set, the session is released if no CDP command
         * or remote input is received for this duration, even if `timeout` has not yet elapsed.
         * Note that `timeout` remains the hard cap on session lifetime: if `inactivityTimeout` is
         * greater than or equal to the effective `timeout`, it has no effect since `timeout` always
         * elapses first. Omit to disable.
         */
        fun inactivityTimeout(inactivityTimeout: Long) = apply {
            body.inactivityTimeout(inactivityTimeout)
        }

        /**
         * Inactivity timeout in milliseconds. When set, the session is released if no CDP command
         * or remote input is received for this duration, even if `timeout` has not yet elapsed.
         * Note that `timeout` remains the hard cap on session lifetime: if `inactivityTimeout` is
         * greater than or equal to the effective `timeout`, it has no effect since `timeout` always
         * elapses first. Omit to disable.
         */
        fun inactivityTimeout(inactivityTimeout: JsonField<Long>) = apply {
            body.inactivityTimeout(inactivityTimeout)
        }

        /**
         * Deprecated. Selenium sessions are no longer supported, and a request that sets this to
         * true is rejected with 400. Connect over CDP with Playwright or Puppeteer instead.
         */
        @Deprecated("deprecated")
        @Suppress("DEPRECATION")
        fun isSelenium(isSelenium: Boolean) = apply { body.isSelenium(isSelenium) }

        /**
         * Deprecated. Selenium sessions are no longer supported, and a request that sets this to
         * true is rejected with 400. Connect over CDP with Playwright or Puppeteer instead.
         */
        @Deprecated("deprecated")
        @Suppress("DEPRECATION")
        fun isSelenium(isSelenium: JsonField<Boolean>) = apply { body.isSelenium(isSelenium) }

        /**
         * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must start
         * with a letter, use only letters, digits, underscores, dots or hyphens, and be at most 64
         * characters; steel_ is reserved. Values are strings (up to 1024 characters, valid Unicode
         * without NUL), finite numbers or booleans. Numbers and booleans are stored as strings, so
         * 3 and "3" are the same value.
         */
        fun metadata(metadata: Metadata) = apply { body.metadata(metadata) }

        /**
         * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must start
         * with a letter, use only letters, digits, underscores, dots or hyphens, and be at most 64
         * characters; steel_ is reserved. Values are strings (up to 1024 characters, valid Unicode
         * without NUL), finite numbers or booleans. Numbers and booleans are stored as strings, so
         * 3 and "3" are the same value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { body.metadata(metadata) }

        /** The namespace the session should be created against. Defaults to "default". */
        fun namespace(namespace: String) = apply { body.namespace(namespace) }

        /** The namespace the session should be created against. Defaults to "default". */
        fun namespace(namespace: JsonField<String>) = apply { body.namespace(namespace) }

        /**
         * Enable bandwidth optimizations. Passing true enables all flags (except hosts/patterns).
         * Object allows granular control.
         */
        fun optimizeBandwidth(optimizeBandwidth: OptimizeBandwidth) = apply {
            body.optimizeBandwidth(optimizeBandwidth)
        }

        /**
         * Enable bandwidth optimizations. Passing true enables all flags (except hosts/patterns).
         * Object allows granular control.
         */
        fun optimizeBandwidth(optimizeBandwidth: JsonField<OptimizeBandwidth>) = apply {
            body.optimizeBandwidth(optimizeBandwidth)
        }

        /**
         * Enable bandwidth optimizations. Passing true enables all flags (except hosts/patterns).
         * Object allows granular control.
         */
        fun optimizeBandwidth(bool: Boolean) = apply { body.optimizeBandwidth(bool) }

        /**
         * Enable bandwidth optimizations. Passing true enables all flags (except hosts/patterns).
         * Object allows granular control.
         */
        fun optimizeBandwidth(unionMember1: OptimizeBandwidth.UnionMember1) = apply {
            body.optimizeBandwidth(unionMember1)
        }

        /** This flag will persist the user profile for the session. */
        fun persistProfile(persistProfile: Boolean) = apply { body.persistProfile(persistProfile) }

        /** This flag will persist the user profile for the session. */
        fun persistProfile(persistProfile: JsonField<Boolean>) = apply {
            body.persistProfile(persistProfile)
        }

        /** This flag will set the profile for the session. */
        fun profileId(profileId: String) = apply { body.profileId(profileId) }

        /** This flag will set the profile for the session. */
        fun profileId(profileId: JsonField<String>) = apply { body.profileId(profileId) }

        /**
         * The project to create the session in. When provided, the session namespace is resolved
         * from the project.
         */
        fun projectId(projectId: String) = apply { body.projectId(projectId) }

        /**
         * The project to create the session in. When provided, the session namespace is resolved
         * from the project.
         */
        fun projectId(projectId: JsonField<String>) = apply { body.projectId(projectId) }

        /**
         * Custom proxy URL for the browser session. Overrides useProxy, disabling Steel-provided
         * proxies in favor of your specified proxy. Format:
         * http(s)://username:password@hostname:port
         */
        fun proxyUrl(proxyUrl: String) = apply { body.proxyUrl(proxyUrl) }

        /**
         * Custom proxy URL for the browser session. Overrides useProxy, disabling Steel-provided
         * proxies in favor of your specified proxy. Format:
         * http(s)://username:password@hostname:port
         */
        fun proxyUrl(proxyUrl: JsonField<String>) = apply { body.proxyUrl(proxyUrl) }

        /**
         * Record the headful session for playback after it ends. Default is true. Disabling
         * recording does not affect live WHEP viewing.
         */
        fun recording(recording: Boolean) = apply { body.recording(recording) }

        /**
         * Record the headful session for playback after it ends. Default is true. Disabling
         * recording does not affect live WHEP viewing.
         */
        fun recording(recording: JsonField<Boolean>) = apply { body.recording(recording) }

        /**
         * The desired region for the session. Available: us-east, us-west, us-central, eu-west,
         * eu-central, ap-northeast, ap-southeast, sa-east. Legacy codes (iad, lax, ord) are also
         * accepted.
         */
        fun region(region: JsonValue) = apply { body.region(region) }

        /**
         * Session context data to be used in the created session. Sessions will start with an empty
         * context by default.
         */
        fun sessionContext(sessionContext: SessionContext) = apply {
            body.sessionContext(sessionContext)
        }

        /**
         * Session context data to be used in the created session. Sessions will start with an empty
         * context by default.
         */
        fun sessionContext(sessionContext: JsonField<SessionContext>) = apply {
            body.sessionContext(sessionContext)
        }

        /** Optional custom UUID for the session */
        fun sessionId(sessionId: String) = apply { body.sessionId(sessionId) }

        /** Optional custom UUID for the session */
        fun sessionId(sessionId: JsonField<String>) = apply { body.sessionId(sessionId) }

        /** Enable automatic captcha solving. Default is false. */
        fun solveCaptcha(solveCaptcha: Boolean) = apply { body.solveCaptcha(solveCaptcha) }

        /** Enable automatic captcha solving. Default is false. */
        fun solveCaptcha(solveCaptcha: JsonField<Boolean>) = apply {
            body.solveCaptcha(solveCaptcha)
        }

        /** Stealth configuration for the session */
        fun stealthConfig(stealthConfig: StealthConfig) = apply {
            body.stealthConfig(stealthConfig)
        }

        /** Stealth configuration for the session */
        fun stealthConfig(stealthConfig: JsonField<StealthConfig>) = apply {
            body.stealthConfig(stealthConfig)
        }

        /** Session timeout duration in milliseconds. Default is 300000 (5 minutes). */
        fun timeout(timeout: Long) = apply { body.timeout(timeout) }

        /** Session timeout duration in milliseconds. Default is 300000 (5 minutes). */
        fun timeout(timeout: JsonField<Long>) = apply { body.timeout(timeout) }

        /** Simple boolean to enable/disable Steel proxies */
        fun useProxy(useProxy: UseProxy) = apply { body.useProxy(useProxy) }

        /** Simple boolean to enable/disable Steel proxies */
        fun useProxy(useProxy: JsonField<UseProxy>) = apply { body.useProxy(useProxy) }

        /** Simple boolean to enable/disable Steel proxies */
        fun useProxy(bool: Boolean) = apply { body.useProxy(bool) }

        /** Simple boolean to enable/disable Steel proxies */
        fun useProxy(geolocation: UseProxy.Geolocation) = apply { body.useProxy(geolocation) }

        /** Simple boolean to enable/disable Steel proxies */
        fun useProxy(server: UseProxy.Server) = apply { body.useProxy(server) }

        /** Simple boolean to enable/disable Steel proxies */
        fun useProxy(unionMember3: UseProxy.UnionMember3) = apply { body.useProxy(unionMember3) }

        /** Custom user agent string for the browser session */
        fun userAgent(userAgent: String) = apply { body.userAgent(userAgent) }

        /** Custom user agent string for the browser session */
        fun userAgent(userAgent: JsonField<String>) = apply { body.userAgent(userAgent) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

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

        fun build(): SessionCreateParams =
            SessionCreateParams(
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val blockAds: JsonField<Boolean>,
        private val caCertificates: JsonField<List<String>>,
        private val concurrency: JsonField<Long>,
        private val credentials: JsonField<Credentials>,
        private val debugConfig: JsonField<DebugConfig>,
        private val deviceConfig: JsonField<DeviceConfig>,
        private val dimensions: JsonField<Dimensions>,
        private val experimentalFeatures: JsonField<List<String>>,
        private val extensionIds: JsonField<List<String>>,
        private val fullscreen: JsonField<Boolean>,
        private val headless: JsonField<Boolean>,
        private val inactivityTimeout: JsonField<Long>,
        private val isSelenium: JsonField<Boolean>,
        private val metadata: JsonField<Metadata>,
        private val namespace: JsonField<String>,
        private val optimizeBandwidth: JsonField<OptimizeBandwidth>,
        private val persistProfile: JsonField<Boolean>,
        private val profileId: JsonField<String>,
        private val projectId: JsonField<String>,
        private val proxyUrl: JsonField<String>,
        private val recording: JsonField<Boolean>,
        private val region: JsonValue,
        private val sessionContext: JsonField<SessionContext>,
        private val sessionId: JsonField<String>,
        private val solveCaptcha: JsonField<Boolean>,
        private val stealthConfig: JsonField<StealthConfig>,
        private val timeout: JsonField<Long>,
        private val useProxy: JsonField<UseProxy>,
        private val userAgent: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("blockAds")
            @ExcludeMissing
            blockAds: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("caCertificates")
            @ExcludeMissing
            caCertificates: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("concurrency")
            @ExcludeMissing
            concurrency: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("credentials")
            @ExcludeMissing
            credentials: JsonField<Credentials> = JsonMissing.of(),
            @JsonProperty("debugConfig")
            @ExcludeMissing
            debugConfig: JsonField<DebugConfig> = JsonMissing.of(),
            @JsonProperty("deviceConfig")
            @ExcludeMissing
            deviceConfig: JsonField<DeviceConfig> = JsonMissing.of(),
            @JsonProperty("dimensions")
            @ExcludeMissing
            dimensions: JsonField<Dimensions> = JsonMissing.of(),
            @JsonProperty("experimentalFeatures")
            @ExcludeMissing
            experimentalFeatures: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("extensionIds")
            @ExcludeMissing
            extensionIds: JsonField<List<String>> = JsonMissing.of(),
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
            @JsonProperty("namespace")
            @ExcludeMissing
            namespace: JsonField<String> = JsonMissing.of(),
            @JsonProperty("optimizeBandwidth")
            @ExcludeMissing
            optimizeBandwidth: JsonField<OptimizeBandwidth> = JsonMissing.of(),
            @JsonProperty("persistProfile")
            @ExcludeMissing
            persistProfile: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("profileId")
            @ExcludeMissing
            profileId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("projectId")
            @ExcludeMissing
            projectId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("proxyUrl")
            @ExcludeMissing
            proxyUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("recording")
            @ExcludeMissing
            recording: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("region") @ExcludeMissing region: JsonValue = JsonMissing.of(),
            @JsonProperty("sessionContext")
            @ExcludeMissing
            sessionContext: JsonField<SessionContext> = JsonMissing.of(),
            @JsonProperty("sessionId")
            @ExcludeMissing
            sessionId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("solveCaptcha")
            @ExcludeMissing
            solveCaptcha: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("stealthConfig")
            @ExcludeMissing
            stealthConfig: JsonField<StealthConfig> = JsonMissing.of(),
            @JsonProperty("timeout") @ExcludeMissing timeout: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("useProxy")
            @ExcludeMissing
            useProxy: JsonField<UseProxy> = JsonMissing.of(),
            @JsonProperty("userAgent")
            @ExcludeMissing
            userAgent: JsonField<String> = JsonMissing.of(),
        ) : this(
            blockAds,
            caCertificates,
            concurrency,
            credentials,
            debugConfig,
            deviceConfig,
            dimensions,
            experimentalFeatures,
            extensionIds,
            fullscreen,
            headless,
            inactivityTimeout,
            isSelenium,
            metadata,
            namespace,
            optimizeBandwidth,
            persistProfile,
            profileId,
            projectId,
            proxyUrl,
            recording,
            region,
            sessionContext,
            sessionId,
            solveCaptcha,
            stealthConfig,
            timeout,
            useProxy,
            userAgent,
            mutableMapOf(),
        )

        /** Block ads in the browser session. Default is false. */
        fun blockAds(): Optional<Boolean> = blockAds.getOptional("blockAds")

        /**
         * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
         * EXPERIMENTAL FEATURE.
         */
        fun caCertificates(): Optional<List<String>> = caCertificates.getOptional("caCertificates")

        /** Number of sessions to create concurrently (check your plan limit) */
        fun concurrency(): Optional<Long> = concurrency.getOptional("concurrency")

        /** Configuration for session credentials */
        fun credentials(): Optional<Credentials> = credentials.getOptional("credentials")

        /**
         * Configuration for the debug URL and session viewer. Controls interaction capabilities,
         * cursor visibility, and other debug-related settings.
         */
        fun debugConfig(): Optional<DebugConfig> = debugConfig.getOptional("debugConfig")

        /**
         * Device configuration for the session. Specify 'mobile' for mobile device fingerprints and
         * configurations.
         */
        fun deviceConfig(): Optional<DeviceConfig> = deviceConfig.getOptional("deviceConfig")

        /**
         * Viewport and browser window dimensions for the session. Mobile sessions require
         * dimensions of at least 508x1074; smaller mobile dimensions are rejected with a 400
         * response.
         */
        fun dimensions(): Optional<Dimensions> = dimensions.getOptional("dimensions")

        /** Enable experimental features for the session. */
        fun experimentalFeatures(): Optional<List<String>> =
            experimentalFeatures.getOptional("experimentalFeatures")

        /**
         * Array of extension IDs to install in the session. Use ['all_ext'] to install all uploaded
         * extensions.
         */
        fun extensionIds(): Optional<List<String>> = extensionIds.getOptional("extensionIds")

        /**
         * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
         * Default is false.
         */
        fun fullscreen(): Optional<Boolean> = fullscreen.getOptional("fullscreen")

        /** Enable headless browser mode (disable Headful mode) */
        fun headless(): Optional<Boolean> = headless.getOptional("headless")

        /**
         * Inactivity timeout in milliseconds. When set, the session is released if no CDP command
         * or remote input is received for this duration, even if `timeout` has not yet elapsed.
         * Note that `timeout` remains the hard cap on session lifetime: if `inactivityTimeout` is
         * greater than or equal to the effective `timeout`, it has no effect since `timeout` always
         * elapses first. Omit to disable.
         */
        fun inactivityTimeout(): Optional<Long> = inactivityTimeout.getOptional("inactivityTimeout")

        /**
         * Deprecated. Selenium sessions are no longer supported, and a request that sets this to
         * true is rejected with 400. Connect over CDP with Playwright or Puppeteer instead.
         */
        @Deprecated("deprecated")
        @Suppress("DEPRECATION")
        fun isSelenium(): Optional<Boolean> = isSelenium.getOptional("isSelenium")

        /**
         * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must start
         * with a letter, use only letters, digits, underscores, dots or hyphens, and be at most 64
         * characters; steel_ is reserved. Values are strings (up to 1024 characters, valid Unicode
         * without NUL), finite numbers or booleans. Numbers and booleans are stored as strings, so
         * 3 and "3" are the same value.
         */
        fun metadata(): Optional<Metadata> = metadata.getOptional("metadata")

        /** The namespace the session should be created against. Defaults to "default". */
        fun namespace(): Optional<String> = namespace.getOptional("namespace")

        /**
         * Enable bandwidth optimizations. Passing true enables all flags (except hosts/patterns).
         * Object allows granular control.
         */
        fun optimizeBandwidth(): Optional<OptimizeBandwidth> =
            optimizeBandwidth.getOptional("optimizeBandwidth")

        /** This flag will persist the user profile for the session. */
        fun persistProfile(): Optional<Boolean> = persistProfile.getOptional("persistProfile")

        /** This flag will set the profile for the session. */
        fun profileId(): Optional<String> = profileId.getOptional("profileId")

        /**
         * The project to create the session in. When provided, the session namespace is resolved
         * from the project.
         */
        fun projectId(): Optional<String> = projectId.getOptional("projectId")

        /**
         * Custom proxy URL for the browser session. Overrides useProxy, disabling Steel-provided
         * proxies in favor of your specified proxy. Format:
         * http(s)://username:password@hostname:port
         */
        fun proxyUrl(): Optional<String> = proxyUrl.getOptional("proxyUrl")

        /**
         * Record the headful session for playback after it ends. Default is true. Disabling
         * recording does not affect live WHEP viewing.
         */
        fun recording(): Optional<Boolean> = recording.getOptional("recording")

        /**
         * Session context data to be used in the created session. Sessions will start with an empty
         * context by default.
         */
        fun sessionContext(): Optional<SessionContext> =
            sessionContext.getOptional("sessionContext")

        /** Optional custom UUID for the session */
        fun sessionId(): Optional<String> = sessionId.getOptional("sessionId")

        /** Enable automatic captcha solving. Default is false. */
        fun solveCaptcha(): Optional<Boolean> = solveCaptcha.getOptional("solveCaptcha")

        /** Stealth configuration for the session */
        fun stealthConfig(): Optional<StealthConfig> = stealthConfig.getOptional("stealthConfig")

        /** Session timeout duration in milliseconds. Default is 300000 (5 minutes). */
        fun timeout(): Optional<Long> = timeout.getOptional("timeout")

        /** Simple boolean to enable/disable Steel proxies */
        fun useProxy(): Optional<UseProxy> = useProxy.getOptional("useProxy")

        /** Custom user agent string for the browser session */
        fun userAgent(): Optional<String> = userAgent.getOptional("userAgent")

        @JsonProperty("blockAds") @ExcludeMissing fun _blockAds(): JsonField<Boolean> = blockAds

        @JsonProperty("caCertificates")
        @ExcludeMissing
        fun _caCertificates(): JsonField<List<String>> = caCertificates

        @JsonProperty("concurrency")
        @ExcludeMissing
        fun _concurrency(): JsonField<Long> = concurrency

        @JsonProperty("credentials")
        @ExcludeMissing
        fun _credentials(): JsonField<Credentials> = credentials

        @JsonProperty("debugConfig")
        @ExcludeMissing
        fun _debugConfig(): JsonField<DebugConfig> = debugConfig

        @JsonProperty("deviceConfig")
        @ExcludeMissing
        fun _deviceConfig(): JsonField<DeviceConfig> = deviceConfig

        @JsonProperty("dimensions")
        @ExcludeMissing
        fun _dimensions(): JsonField<Dimensions> = dimensions

        @JsonProperty("experimentalFeatures")
        @ExcludeMissing
        fun _experimentalFeatures(): JsonField<List<String>> = experimentalFeatures

        @JsonProperty("extensionIds")
        @ExcludeMissing
        fun _extensionIds(): JsonField<List<String>> = extensionIds

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

        @JsonProperty("namespace") @ExcludeMissing fun _namespace(): JsonField<String> = namespace

        @JsonProperty("optimizeBandwidth")
        @ExcludeMissing
        fun _optimizeBandwidth(): JsonField<OptimizeBandwidth> = optimizeBandwidth

        @JsonProperty("persistProfile")
        @ExcludeMissing
        fun _persistProfile(): JsonField<Boolean> = persistProfile

        @JsonProperty("profileId") @ExcludeMissing fun _profileId(): JsonField<String> = profileId

        @JsonProperty("projectId") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

        @JsonProperty("proxyUrl") @ExcludeMissing fun _proxyUrl(): JsonField<String> = proxyUrl

        @JsonProperty("recording") @ExcludeMissing fun _recording(): JsonField<Boolean> = recording

        @JsonProperty("region") @ExcludeMissing fun _region(): JsonValue = region

        @JsonProperty("sessionContext")
        @ExcludeMissing
        fun _sessionContext(): JsonField<SessionContext> = sessionContext

        @JsonProperty("sessionId") @ExcludeMissing fun _sessionId(): JsonField<String> = sessionId

        @JsonProperty("solveCaptcha")
        @ExcludeMissing
        fun _solveCaptcha(): JsonField<Boolean> = solveCaptcha

        @JsonProperty("stealthConfig")
        @ExcludeMissing
        fun _stealthConfig(): JsonField<StealthConfig> = stealthConfig

        @JsonProperty("timeout") @ExcludeMissing fun _timeout(): JsonField<Long> = timeout

        @JsonProperty("useProxy") @ExcludeMissing fun _useProxy(): JsonField<UseProxy> = useProxy

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var blockAds: JsonField<Boolean> = JsonMissing.of()
            private var caCertificates: JsonField<MutableList<String>>? = null
            private var concurrency: JsonField<Long> = JsonMissing.of()
            private var credentials: JsonField<Credentials> = JsonMissing.of()
            private var debugConfig: JsonField<DebugConfig> = JsonMissing.of()
            private var deviceConfig: JsonField<DeviceConfig> = JsonMissing.of()
            private var dimensions: JsonField<Dimensions> = JsonMissing.of()
            private var experimentalFeatures: JsonField<MutableList<String>>? = null
            private var extensionIds: JsonField<MutableList<String>>? = null
            private var fullscreen: JsonField<Boolean> = JsonMissing.of()
            private var headless: JsonField<Boolean> = JsonMissing.of()
            private var inactivityTimeout: JsonField<Long> = JsonMissing.of()
            private var isSelenium: JsonField<Boolean> = JsonMissing.of()
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var namespace: JsonField<String> = JsonMissing.of()
            private var optimizeBandwidth: JsonField<OptimizeBandwidth> = JsonMissing.of()
            private var persistProfile: JsonField<Boolean> = JsonMissing.of()
            private var profileId: JsonField<String> = JsonMissing.of()
            private var projectId: JsonField<String> = JsonMissing.of()
            private var proxyUrl: JsonField<String> = JsonMissing.of()
            private var recording: JsonField<Boolean> = JsonMissing.of()
            private var region: JsonValue = JsonMissing.of()
            private var sessionContext: JsonField<SessionContext> = JsonMissing.of()
            private var sessionId: JsonField<String> = JsonMissing.of()
            private var solveCaptcha: JsonField<Boolean> = JsonMissing.of()
            private var stealthConfig: JsonField<StealthConfig> = JsonMissing.of()
            private var timeout: JsonField<Long> = JsonMissing.of()
            private var useProxy: JsonField<UseProxy> = JsonMissing.of()
            private var userAgent: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body_: Body) = apply {
                this.blockAds = body_.blockAds
                this.caCertificates = body_.caCertificates.map { it.toMutableList() }
                this.concurrency = body_.concurrency
                this.credentials = body_.credentials
                this.debugConfig = body_.debugConfig
                this.deviceConfig = body_.deviceConfig
                this.dimensions = body_.dimensions
                this.experimentalFeatures = body_.experimentalFeatures.map { it.toMutableList() }
                this.extensionIds = body_.extensionIds.map { it.toMutableList() }
                this.fullscreen = body_.fullscreen
                this.headless = body_.headless
                this.inactivityTimeout = body_.inactivityTimeout
                this.isSelenium = body_.isSelenium
                this.metadata = body_.metadata
                this.namespace = body_.namespace
                this.optimizeBandwidth = body_.optimizeBandwidth
                this.persistProfile = body_.persistProfile
                this.profileId = body_.profileId
                this.projectId = body_.projectId
                this.proxyUrl = body_.proxyUrl
                this.recording = body_.recording
                this.region = body_.region
                this.sessionContext = body_.sessionContext
                this.sessionId = body_.sessionId
                this.solveCaptcha = body_.solveCaptcha
                this.stealthConfig = body_.stealthConfig
                this.timeout = body_.timeout
                this.useProxy = body_.useProxy
                this.userAgent = body_.userAgent
                this.additionalProperties = body_.additionalProperties.toMutableMap()
            }

            /** Block ads in the browser session. Default is false. */
            fun blockAds(blockAds: Boolean) = blockAds(JsonField.of(blockAds))

            /** Block ads in the browser session. Default is false. */
            fun blockAds(blockAds: JsonField<Boolean>) = apply { this.blockAds = blockAds }

            /**
             * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
             * EXPERIMENTAL FEATURE.
             */
            fun caCertificates(caCertificates: List<String>) =
                caCertificates(JsonField.of(caCertificates))

            /**
             * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
             * EXPERIMENTAL FEATURE.
             */
            fun caCertificates(caCertificates: JsonField<List<String>>) = apply {
                this.caCertificates = caCertificates.map { it.toMutableList() }
            }

            /**
             * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
             * EXPERIMENTAL FEATURE.
             */
            fun addCaCertificate(caCertificate: String) = apply {
                this.caCertificates =
                    (this.caCertificates ?: JsonField.of(mutableListOf())).also {
                        checkKnown("caCertificates", it).add(caCertificate)
                    }
            }

            /** Number of sessions to create concurrently (check your plan limit) */
            fun concurrency(concurrency: Long) = concurrency(JsonField.of(concurrency))

            /** Number of sessions to create concurrently (check your plan limit) */
            fun concurrency(concurrency: JsonField<Long>) = apply { this.concurrency = concurrency }

            /** Configuration for session credentials */
            fun credentials(credentials: Credentials) = credentials(JsonField.of(credentials))

            /** Configuration for session credentials */
            fun credentials(credentials: JsonField<Credentials>) = apply {
                this.credentials = credentials
            }

            /**
             * Configuration for the debug URL and session viewer. Controls interaction
             * capabilities, cursor visibility, and other debug-related settings.
             */
            fun debugConfig(debugConfig: DebugConfig) = debugConfig(JsonField.of(debugConfig))

            /**
             * Configuration for the debug URL and session viewer. Controls interaction
             * capabilities, cursor visibility, and other debug-related settings.
             */
            fun debugConfig(debugConfig: JsonField<DebugConfig>) = apply {
                this.debugConfig = debugConfig
            }

            /**
             * Device configuration for the session. Specify 'mobile' for mobile device fingerprints
             * and configurations.
             */
            fun deviceConfig(deviceConfig: DeviceConfig) = deviceConfig(JsonField.of(deviceConfig))

            /**
             * Device configuration for the session. Specify 'mobile' for mobile device fingerprints
             * and configurations.
             */
            fun deviceConfig(deviceConfig: JsonField<DeviceConfig>) = apply {
                this.deviceConfig = deviceConfig
            }

            /**
             * Viewport and browser window dimensions for the session. Mobile sessions require
             * dimensions of at least 508x1074; smaller mobile dimensions are rejected with a 400
             * response.
             */
            fun dimensions(dimensions: Dimensions) = dimensions(JsonField.of(dimensions))

            /**
             * Viewport and browser window dimensions for the session. Mobile sessions require
             * dimensions of at least 508x1074; smaller mobile dimensions are rejected with a 400
             * response.
             */
            fun dimensions(dimensions: JsonField<Dimensions>) = apply {
                this.dimensions = dimensions
            }

            /** Enable experimental features for the session. */
            fun experimentalFeatures(experimentalFeatures: List<String>) =
                experimentalFeatures(JsonField.of(experimentalFeatures))

            /** Enable experimental features for the session. */
            fun experimentalFeatures(experimentalFeatures: JsonField<List<String>>) = apply {
                this.experimentalFeatures = experimentalFeatures.map { it.toMutableList() }
            }

            /** Enable experimental features for the session. */
            fun addExperimentalFeature(experimentalFeature: String) = apply {
                this.experimentalFeatures =
                    (this.experimentalFeatures ?: JsonField.of(mutableListOf())).also {
                        checkKnown("experimentalFeatures", it).add(experimentalFeature)
                    }
            }

            /**
             * Array of extension IDs to install in the session. Use ['all_ext'] to install all
             * uploaded extensions.
             */
            fun extensionIds(extensionIds: List<String>) = extensionIds(JsonField.of(extensionIds))

            /**
             * Array of extension IDs to install in the session. Use ['all_ext'] to install all
             * uploaded extensions.
             */
            fun extensionIds(extensionIds: JsonField<List<String>>) = apply {
                this.extensionIds = extensionIds.map { it.toMutableList() }
            }

            /**
             * Array of extension IDs to install in the session. Use ['all_ext'] to install all
             * uploaded extensions.
             */
            fun addExtensionId(extensionId: String) = apply {
                this.extensionIds =
                    (this.extensionIds ?: JsonField.of(mutableListOf())).also {
                        checkKnown("extensionIds", it).add(extensionId)
                    }
            }

            /**
             * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
             * Default is false.
             */
            fun fullscreen(fullscreen: Boolean) = fullscreen(JsonField.of(fullscreen))

            /**
             * Launch the browser in fullscreen mode, covering the full screen with no Chrome UI.
             * Default is false.
             */
            fun fullscreen(fullscreen: JsonField<Boolean>) = apply { this.fullscreen = fullscreen }

            /** Enable headless browser mode (disable Headful mode) */
            fun headless(headless: Boolean) = headless(JsonField.of(headless))

            /** Enable headless browser mode (disable Headful mode) */
            fun headless(headless: JsonField<Boolean>) = apply { this.headless = headless }

            /**
             * Inactivity timeout in milliseconds. When set, the session is released if no CDP
             * command or remote input is received for this duration, even if `timeout` has not yet
             * elapsed. Note that `timeout` remains the hard cap on session lifetime: if
             * `inactivityTimeout` is greater than or equal to the effective `timeout`, it has no
             * effect since `timeout` always elapses first. Omit to disable.
             */
            fun inactivityTimeout(inactivityTimeout: Long) =
                inactivityTimeout(JsonField.of(inactivityTimeout))

            /**
             * Inactivity timeout in milliseconds. When set, the session is released if no CDP
             * command or remote input is received for this duration, even if `timeout` has not yet
             * elapsed. Note that `timeout` remains the hard cap on session lifetime: if
             * `inactivityTimeout` is greater than or equal to the effective `timeout`, it has no
             * effect since `timeout` always elapses first. Omit to disable.
             */
            fun inactivityTimeout(inactivityTimeout: JsonField<Long>) = apply {
                this.inactivityTimeout = inactivityTimeout
            }

            /**
             * Deprecated. Selenium sessions are no longer supported, and a request that sets this
             * to true is rejected with 400. Connect over CDP with Playwright or Puppeteer instead.
             */
            @Deprecated("deprecated")
            @Suppress("DEPRECATION")
            fun isSelenium(isSelenium: Boolean) = isSelenium(JsonField.of(isSelenium))

            /**
             * Deprecated. Selenium sessions are no longer supported, and a request that sets this
             * to true is rejected with 400. Connect over CDP with Playwright or Puppeteer instead.
             */
            @Deprecated("deprecated")
            @Suppress("DEPRECATION")
            fun isSelenium(isSelenium: JsonField<Boolean>) = apply { this.isSelenium = isSelenium }

            /**
             * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must
             * start with a letter, use only letters, digits, underscores, dots or hyphens, and be
             * at most 64 characters; steel_ is reserved. Values are strings (up to 1024 characters,
             * valid Unicode without NUL), finite numbers or booleans. Numbers and booleans are
             * stored as strings, so 3 and "3" are the same value.
             */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /**
             * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys must
             * start with a letter, use only letters, digits, underscores, dots or hyphens, and be
             * at most 64 characters; steel_ is reserved. Values are strings (up to 1024 characters,
             * valid Unicode without NUL), finite numbers or booleans. Numbers and booleans are
             * stored as strings, so 3 and "3" are the same value.
             */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            /** The namespace the session should be created against. Defaults to "default". */
            fun namespace(namespace: String) = namespace(JsonField.of(namespace))

            /** The namespace the session should be created against. Defaults to "default". */
            fun namespace(namespace: JsonField<String>) = apply { this.namespace = namespace }

            /**
             * Enable bandwidth optimizations. Passing true enables all flags (except
             * hosts/patterns). Object allows granular control.
             */
            fun optimizeBandwidth(optimizeBandwidth: OptimizeBandwidth) =
                optimizeBandwidth(JsonField.of(optimizeBandwidth))

            /**
             * Enable bandwidth optimizations. Passing true enables all flags (except
             * hosts/patterns). Object allows granular control.
             */
            fun optimizeBandwidth(optimizeBandwidth: JsonField<OptimizeBandwidth>) = apply {
                this.optimizeBandwidth = optimizeBandwidth
            }

            /**
             * Enable bandwidth optimizations. Passing true enables all flags (except
             * hosts/patterns). Object allows granular control.
             */
            fun optimizeBandwidth(bool: Boolean) = optimizeBandwidth(OptimizeBandwidth.ofBool(bool))

            /**
             * Enable bandwidth optimizations. Passing true enables all flags (except
             * hosts/patterns). Object allows granular control.
             */
            fun optimizeBandwidth(unionMember1: OptimizeBandwidth.UnionMember1) =
                optimizeBandwidth(OptimizeBandwidth.ofUnionMember1(unionMember1))

            /** This flag will persist the user profile for the session. */
            fun persistProfile(persistProfile: Boolean) =
                persistProfile(JsonField.of(persistProfile))

            /** This flag will persist the user profile for the session. */
            fun persistProfile(persistProfile: JsonField<Boolean>) = apply {
                this.persistProfile = persistProfile
            }

            /** This flag will set the profile for the session. */
            fun profileId(profileId: String) = profileId(JsonField.of(profileId))

            /** This flag will set the profile for the session. */
            fun profileId(profileId: JsonField<String>) = apply { this.profileId = profileId }

            /**
             * The project to create the session in. When provided, the session namespace is
             * resolved from the project.
             */
            fun projectId(projectId: String) = projectId(JsonField.of(projectId))

            /**
             * The project to create the session in. When provided, the session namespace is
             * resolved from the project.
             */
            fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

            /**
             * Custom proxy URL for the browser session. Overrides useProxy, disabling
             * Steel-provided proxies in favor of your specified proxy. Format:
             * http(s)://username:password@hostname:port
             */
            fun proxyUrl(proxyUrl: String) = proxyUrl(JsonField.of(proxyUrl))

            /**
             * Custom proxy URL for the browser session. Overrides useProxy, disabling
             * Steel-provided proxies in favor of your specified proxy. Format:
             * http(s)://username:password@hostname:port
             */
            fun proxyUrl(proxyUrl: JsonField<String>) = apply { this.proxyUrl = proxyUrl }

            /**
             * Record the headful session for playback after it ends. Default is true. Disabling
             * recording does not affect live WHEP viewing.
             */
            fun recording(recording: Boolean) = recording(JsonField.of(recording))

            /**
             * Record the headful session for playback after it ends. Default is true. Disabling
             * recording does not affect live WHEP viewing.
             */
            fun recording(recording: JsonField<Boolean>) = apply { this.recording = recording }

            /**
             * The desired region for the session. Available: us-east, us-west, us-central, eu-west,
             * eu-central, ap-northeast, ap-southeast, sa-east. Legacy codes (iad, lax, ord) are
             * also accepted.
             */
            fun region(region: JsonValue) = apply { this.region = region }

            /**
             * Session context data to be used in the created session. Sessions will start with an
             * empty context by default.
             */
            fun sessionContext(sessionContext: SessionContext) =
                sessionContext(JsonField.of(sessionContext))

            /**
             * Session context data to be used in the created session. Sessions will start with an
             * empty context by default.
             */
            fun sessionContext(sessionContext: JsonField<SessionContext>) = apply {
                this.sessionContext = sessionContext
            }

            /** Optional custom UUID for the session */
            fun sessionId(sessionId: String) = sessionId(JsonField.of(sessionId))

            /** Optional custom UUID for the session */
            fun sessionId(sessionId: JsonField<String>) = apply { this.sessionId = sessionId }

            /** Enable automatic captcha solving. Default is false. */
            fun solveCaptcha(solveCaptcha: Boolean) = solveCaptcha(JsonField.of(solveCaptcha))

            /** Enable automatic captcha solving. Default is false. */
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

            /** Session timeout duration in milliseconds. Default is 300000 (5 minutes). */
            fun timeout(timeout: Long) = timeout(JsonField.of(timeout))

            /** Session timeout duration in milliseconds. Default is 300000 (5 minutes). */
            fun timeout(timeout: JsonField<Long>) = apply { this.timeout = timeout }

            /** Simple boolean to enable/disable Steel proxies */
            fun useProxy(useProxy: UseProxy) = useProxy(JsonField.of(useProxy))

            /** Simple boolean to enable/disable Steel proxies */
            fun useProxy(useProxy: JsonField<UseProxy>) = apply { this.useProxy = useProxy }

            /** Simple boolean to enable/disable Steel proxies */
            fun useProxy(bool: Boolean) = useProxy(UseProxy.ofBool(bool))

            /** Simple boolean to enable/disable Steel proxies */
            fun useProxy(geolocation: UseProxy.Geolocation) =
                useProxy(UseProxy.ofGeolocation(geolocation))

            /** Simple boolean to enable/disable Steel proxies */
            fun useProxy(server: UseProxy.Server) = useProxy(UseProxy.ofServer(server))

            /** Simple boolean to enable/disable Steel proxies */
            fun useProxy(unionMember3: UseProxy.UnionMember3) =
                useProxy(UseProxy.ofUnionMember3(unionMember3))

            /** Custom user agent string for the browser session */
            fun userAgent(userAgent: String) = userAgent(JsonField.of(userAgent))

            /** Custom user agent string for the browser session */
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

            fun build(): Body =
                Body(
                    blockAds,
                    (caCertificates ?: JsonMissing.of()).map { it.toImmutable() },
                    concurrency,
                    credentials,
                    debugConfig,
                    deviceConfig,
                    dimensions,
                    (experimentalFeatures ?: JsonMissing.of()).map { it.toImmutable() },
                    (extensionIds ?: JsonMissing.of()).map { it.toImmutable() },
                    fullscreen,
                    headless,
                    inactivityTimeout,
                    isSelenium,
                    metadata,
                    namespace,
                    optimizeBandwidth,
                    persistProfile,
                    profileId,
                    projectId,
                    proxyUrl,
                    recording,
                    region,
                    sessionContext,
                    sessionId,
                    solveCaptcha,
                    stealthConfig,
                    timeout,
                    useProxy,
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

        @Suppress("DEPRECATION")
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            blockAds()
            caCertificates()
            concurrency()
            credentials().ifPresent { it.validate() }
            debugConfig().ifPresent { it.validate() }
            deviceConfig().ifPresent { it.validate() }
            dimensions().ifPresent { it.validate() }
            experimentalFeatures()
            extensionIds()
            fullscreen()
            headless()
            inactivityTimeout()
            isSelenium()
            metadata().ifPresent { it.validate() }
            namespace()
            optimizeBandwidth().ifPresent { it.validate() }
            persistProfile()
            profileId()
            projectId()
            proxyUrl()
            recording()
            sessionContext().ifPresent { it.validate() }
            sessionId()
            solveCaptcha()
            stealthConfig().ifPresent { it.validate() }
            timeout()
            useProxy().ifPresent { it.validate() }
            userAgent()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (blockAds.asKnown().isPresent) 1 else 0) +
                (caCertificates.asKnown().getOrNull()?.let { it.size } ?: 0) +
                (if (concurrency.asKnown().isPresent) 1 else 0) +
                (credentials.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (debugConfig.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (deviceConfig.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (dimensions.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (experimentalFeatures.asKnown().getOrNull()?.let { it.size } ?: 0) +
                (extensionIds.asKnown().getOrNull()?.let { it.size } ?: 0) +
                (if (fullscreen.asKnown().isPresent) 1 else 0) +
                (if (headless.asKnown().isPresent) 1 else 0) +
                (if (inactivityTimeout.asKnown().isPresent) 1 else 0) +
                (if (isSelenium.asKnown().isPresent) 1 else 0) +
                (metadata.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (namespace.asKnown().isPresent) 1 else 0) +
                (optimizeBandwidth.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (persistProfile.asKnown().isPresent) 1 else 0) +
                (if (profileId.asKnown().isPresent) 1 else 0) +
                (if (projectId.asKnown().isPresent) 1 else 0) +
                (if (proxyUrl.asKnown().isPresent) 1 else 0) +
                (if (recording.asKnown().isPresent) 1 else 0) +
                (sessionContext.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (sessionId.asKnown().isPresent) 1 else 0) +
                (if (solveCaptcha.asKnown().isPresent) 1 else 0) +
                (stealthConfig.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (timeout.asKnown().isPresent) 1 else 0) +
                (useProxy.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (if (userAgent.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                this.blockAds == other.blockAds &&
                this.caCertificates == other.caCertificates &&
                this.concurrency == other.concurrency &&
                this.credentials == other.credentials &&
                this.debugConfig == other.debugConfig &&
                this.deviceConfig == other.deviceConfig &&
                this.dimensions == other.dimensions &&
                this.experimentalFeatures == other.experimentalFeatures &&
                this.extensionIds == other.extensionIds &&
                this.fullscreen == other.fullscreen &&
                this.headless == other.headless &&
                this.inactivityTimeout == other.inactivityTimeout &&
                this.isSelenium == other.isSelenium &&
                this.metadata == other.metadata &&
                this.namespace == other.namespace &&
                this.optimizeBandwidth == other.optimizeBandwidth &&
                this.persistProfile == other.persistProfile &&
                this.profileId == other.profileId &&
                this.projectId == other.projectId &&
                this.proxyUrl == other.proxyUrl &&
                this.recording == other.recording &&
                this.region == other.region &&
                this.sessionContext == other.sessionContext &&
                this.sessionId == other.sessionId &&
                this.solveCaptcha == other.solveCaptcha &&
                this.stealthConfig == other.stealthConfig &&
                this.timeout == other.timeout &&
                this.useProxy == other.useProxy &&
                this.userAgent == other.userAgent &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                blockAds,
                caCertificates,
                concurrency,
                credentials,
                debugConfig,
                deviceConfig,
                dimensions,
                experimentalFeatures,
                extensionIds,
                fullscreen,
                headless,
                inactivityTimeout,
                isSelenium,
                metadata,
                namespace,
                optimizeBandwidth,
                persistProfile,
                profileId,
                projectId,
                proxyUrl,
                recording,
                region,
                sessionContext,
                sessionId,
                solveCaptcha,
                stealthConfig,
                timeout,
                useProxy,
                userAgent,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{blockAds=$blockAds, caCertificates=$caCertificates, concurrency=$concurrency, credentials=$credentials, debugConfig=$debugConfig, deviceConfig=$deviceConfig, dimensions=$dimensions, experimentalFeatures=$experimentalFeatures, extensionIds=$extensionIds, fullscreen=$fullscreen, headless=$headless, inactivityTimeout=$inactivityTimeout, isSelenium=$isSelenium, metadata=$metadata, namespace=$namespace, optimizeBandwidth=$optimizeBandwidth, persistProfile=$persistProfile, profileId=$profileId, projectId=$projectId, proxyUrl=$proxyUrl, recording=$recording, region=$region, sessionContext=$sessionContext, sessionId=$sessionId, solveCaptcha=$solveCaptcha, stealthConfig=$stealthConfig, timeout=$timeout, useProxy=$useProxy, userAgent=$userAgent, additionalProperties=$additionalProperties}"
    }

    /** Configuration for session credentials */
    class Credentials
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val autoSubmit: JsonField<Boolean>,
        private val blurFields: JsonField<Boolean>,
        private val credentialProvider: JsonField<String>,
        private val exactOrigin: JsonField<Boolean>,
        private val origins: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("autoSubmit")
            @ExcludeMissing
            autoSubmit: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("blurFields")
            @ExcludeMissing
            blurFields: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("credentialProvider")
            @ExcludeMissing
            credentialProvider: JsonField<String> = JsonMissing.of(),
            @JsonProperty("exactOrigin")
            @ExcludeMissing
            exactOrigin: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("origins")
            @ExcludeMissing
            origins: JsonField<List<String>> = JsonMissing.of(),
        ) : this(autoSubmit, blurFields, credentialProvider, exactOrigin, origins, mutableMapOf())

        fun autoSubmit(): Optional<Boolean> = autoSubmit.getOptional("autoSubmit")

        fun blurFields(): Optional<Boolean> = blurFields.getOptional("blurFields")

        /** Credential provider to use for runtime credential resolution. Defaults to "steel". */
        fun credentialProvider(): Optional<String> =
            credentialProvider.getOptional("credentialProvider")

        fun exactOrigin(): Optional<Boolean> = exactOrigin.getOptional("exactOrigin")

        /** Optional allowlist of credential origins to inject into this session. */
        fun origins(): Optional<List<String>> = origins.getOptional("origins")

        @JsonProperty("autoSubmit")
        @ExcludeMissing
        fun _autoSubmit(): JsonField<Boolean> = autoSubmit

        @JsonProperty("blurFields")
        @ExcludeMissing
        fun _blurFields(): JsonField<Boolean> = blurFields

        @JsonProperty("credentialProvider")
        @ExcludeMissing
        fun _credentialProvider(): JsonField<String> = credentialProvider

        @JsonProperty("exactOrigin")
        @ExcludeMissing
        fun _exactOrigin(): JsonField<Boolean> = exactOrigin

        @JsonProperty("origins") @ExcludeMissing fun _origins(): JsonField<List<String>> = origins

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

            /** Returns a mutable builder for constructing an instance of [Credentials]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Credentials]. */
        class Builder internal constructor() {

            private var autoSubmit: JsonField<Boolean> = JsonMissing.of()
            private var blurFields: JsonField<Boolean> = JsonMissing.of()
            private var credentialProvider: JsonField<String> = JsonMissing.of()
            private var exactOrigin: JsonField<Boolean> = JsonMissing.of()
            private var origins: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(credentials: Credentials) = apply {
                this.autoSubmit = credentials.autoSubmit
                this.blurFields = credentials.blurFields
                this.credentialProvider = credentials.credentialProvider
                this.exactOrigin = credentials.exactOrigin
                this.origins = credentials.origins.map { it.toMutableList() }
                this.additionalProperties = credentials.additionalProperties.toMutableMap()
            }

            fun autoSubmit(autoSubmit: Boolean) = autoSubmit(JsonField.of(autoSubmit))

            fun autoSubmit(autoSubmit: JsonField<Boolean>) = apply { this.autoSubmit = autoSubmit }

            fun blurFields(blurFields: Boolean) = blurFields(JsonField.of(blurFields))

            fun blurFields(blurFields: JsonField<Boolean>) = apply { this.blurFields = blurFields }

            /**
             * Credential provider to use for runtime credential resolution. Defaults to "steel".
             */
            fun credentialProvider(credentialProvider: String) =
                credentialProvider(JsonField.of(credentialProvider))

            /**
             * Credential provider to use for runtime credential resolution. Defaults to "steel".
             */
            fun credentialProvider(credentialProvider: JsonField<String>) = apply {
                this.credentialProvider = credentialProvider
            }

            fun exactOrigin(exactOrigin: Boolean) = exactOrigin(JsonField.of(exactOrigin))

            fun exactOrigin(exactOrigin: JsonField<Boolean>) = apply {
                this.exactOrigin = exactOrigin
            }

            /** Optional allowlist of credential origins to inject into this session. */
            fun origins(origins: List<String>) = origins(JsonField.of(origins))

            /** Optional allowlist of credential origins to inject into this session. */
            fun origins(origins: JsonField<List<String>>) = apply {
                this.origins = origins.map { it.toMutableList() }
            }

            /** Optional allowlist of credential origins to inject into this session. */
            fun addOrigin(origin: String) = apply {
                this.origins =
                    (this.origins ?: JsonField.of(mutableListOf())).also {
                        checkKnown("origins", it).add(origin)
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

            fun build(): Credentials =
                Credentials(
                    autoSubmit,
                    blurFields,
                    credentialProvider,
                    exactOrigin,
                    (origins ?: JsonMissing.of()).map { it.toImmutable() },
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

        fun validate(): Credentials = apply {
            if (validated) {
                return@apply
            }

            autoSubmit()
            blurFields()
            credentialProvider()
            exactOrigin()
            origins()
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (if (autoSubmit.asKnown().isPresent) 1 else 0) +
                (if (blurFields.asKnown().isPresent) 1 else 0) +
                (if (credentialProvider.asKnown().isPresent) 1 else 0) +
                (if (exactOrigin.asKnown().isPresent) 1 else 0) +
                (origins.asKnown().getOrNull()?.let { it.size } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Credentials &&
                this.autoSubmit == other.autoSubmit &&
                this.blurFields == other.blurFields &&
                this.credentialProvider == other.credentialProvider &&
                this.exactOrigin == other.exactOrigin &&
                this.origins == other.origins &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                autoSubmit,
                blurFields,
                credentialProvider,
                exactOrigin,
                origins,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Credentials{autoSubmit=$autoSubmit, blurFields=$blurFields, credentialProvider=$credentialProvider, exactOrigin=$exactOrigin, origins=$origins, additionalProperties=$additionalProperties}"
    }

    /**
     * Configuration for the debug URL and session viewer. Controls interaction capabilities, cursor
     * visibility, and other debug-related settings.
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
         * Allow interaction with the browser session via the debug URL viewer. When false, the
         * session viewer will be view-only. Default is true.
         */
        fun interactive(): Optional<Boolean> = interactive.getOptional("interactive")

        /**
         * Show the OS-level mouse cursor in the WebRTC stream (headful mode only). When false, the
         * system cursor will not be rendered in the stream. Default is true.
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
             * Allow interaction with the browser session via the debug URL viewer. When false, the
             * session viewer will be view-only. Default is true.
             */
            fun interactive(interactive: Boolean) = interactive(JsonField.of(interactive))

            /**
             * Allow interaction with the browser session via the debug URL viewer. When false, the
             * session viewer will be view-only. Default is true.
             */
            fun interactive(interactive: JsonField<Boolean>) = apply {
                this.interactive = interactive
            }

            /**
             * Show the OS-level mouse cursor in the WebRTC stream (headful mode only). When false,
             * the system cursor will not be rendered in the stream. Default is true.
             */
            fun systemCursor(systemCursor: Boolean) = systemCursor(JsonField.of(systemCursor))

            /**
             * Show the OS-level mouse cursor in the WebRTC stream (headful mode only). When false,
             * the system cursor will not be rendered in the stream. Default is true.
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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

    /**
     * Device configuration for the session. Specify 'mobile' for mobile device fingerprints and
     * configurations.
     */
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): DeviceConfig = DeviceConfig(device, additionalProperties.toMutableMap())
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
        internal fun validity(): Int = (device.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        class Device @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

    /**
     * Viewport and browser window dimensions for the session. Mobile sessions require dimensions of
     * at least 508x1074; smaller mobile dimensions are rejected with a 400 response.
     */
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

        /** Height of the session */
        fun height(): Long = height.getRequired("height")

        /** Width of the session */
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

            /** Height of the session */
            fun height(height: Long) = height(JsonField.of(height))

            /** Height of the session */
            fun height(height: JsonField<Long>) = apply { this.height = height }

            /** Width of the session */
            fun width(width: Long) = width(JsonField.of(width))

            /** Width of the session */
            fun width(width: JsonField<Long>) = apply { this.width = width }

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
            (if (height.asKnown().isPresent) 1 else 0) + (if (width.asKnown().isPresent) 1 else 0)

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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

    @JsonDeserialize(using = OptimizeBandwidth.Deserializer::class)
    @JsonSerialize(using = OptimizeBandwidth.Serializer::class)
    class OptimizeBandwidth
    private constructor(
        private val bool: Boolean? = null,
        private val unionMember1: UnionMember1? = null,
        private val _json: JsonValue? = null,
    ) {

        fun bool(): Optional<Boolean> = Optional.ofNullable(bool)

        fun unionMember1(): Optional<UnionMember1> = Optional.ofNullable(unionMember1)

        fun isBool(): Boolean = bool != null

        fun isUnionMember1(): Boolean = unionMember1 != null

        fun asBool(): Boolean = bool.getOrThrow("bool")

        fun asUnionMember1(): UnionMember1 = unionMember1.getOrThrow("unionMember1")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                bool != null -> visitor.visitBool(bool)
                unionMember1 != null -> visitor.visitUnionMember1(unionMember1)
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

        fun validate(): OptimizeBandwidth = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBool(bool: Boolean) {}

                    override fun visitUnionMember1(unionMember1: UnionMember1) {
                        unionMember1.validate()
                    }
                }
            )
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitBool(bool: Boolean) = 1

                    override fun visitUnionMember1(unionMember1: UnionMember1) =
                        unionMember1.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is OptimizeBandwidth &&
                this.bool == other.bool &&
                this.unionMember1 == other.unionMember1
        }

        override fun hashCode(): Int = Objects.hash(bool, unionMember1)

        override fun toString(): String =
            when {
                bool != null -> "OptimizeBandwidth{bool=$bool}"
                unionMember1 != null -> "OptimizeBandwidth{unionMember1=$unionMember1}"
                _json != null -> "OptimizeBandwidth{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid OptimizeBandwidth")
            }

        companion object {

            @JvmStatic fun ofBool(bool: Boolean) = OptimizeBandwidth(bool = bool)

            @JvmStatic
            fun ofUnionMember1(unionMember1: UnionMember1) =
                OptimizeBandwidth(unionMember1 = unionMember1)
        }

        interface Visitor<out T> {

            fun visitBool(bool: Boolean): T

            fun visitUnionMember1(unionMember1: UnionMember1): T

            fun unknown(json: JsonValue?): T {
                throw SteelInvalidDataException("Unknown OptimizeBandwidth: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<OptimizeBandwidth>(OptimizeBandwidth::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): OptimizeBandwidth {
                val json = JsonValue.fromJsonNode(node)
                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<UnionMember1>())?.let {
                                OptimizeBandwidth(unionMember1 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<Boolean>())?.let {
                                OptimizeBandwidth(bool = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    0 -> OptimizeBandwidth(_json = json)
                    1 -> bestMatches.single()
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<OptimizeBandwidth>(OptimizeBandwidth::class) {

            override fun serialize(
                value: OptimizeBandwidth,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.bool != null -> generator.writeObject(value.bool)
                    value.unionMember1 != null -> generator.writeObject(value.unionMember1)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid OptimizeBandwidth")
                }
            }
        }

        class UnionMember1
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

                /** Returns a mutable builder for constructing an instance of [UnionMember1]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UnionMember1]. */
            class Builder internal constructor() {

                private var blockHosts: JsonField<MutableList<String>>? = null
                private var blockImages: JsonField<Boolean> = JsonMissing.of()
                private var blockMedia: JsonField<Boolean> = JsonMissing.of()
                private var blockStylesheets: JsonField<Boolean> = JsonMissing.of()
                private var blockUrlPatterns: JsonField<MutableList<String>>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(unionMember1: UnionMember1) = apply {
                    this.blockHosts = unionMember1.blockHosts.map { it.toMutableList() }
                    this.blockImages = unionMember1.blockImages
                    this.blockMedia = unionMember1.blockMedia
                    this.blockStylesheets = unionMember1.blockStylesheets
                    this.blockUrlPatterns = unionMember1.blockUrlPatterns.map { it.toMutableList() }
                    this.additionalProperties = unionMember1.additionalProperties.toMutableMap()
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

                fun build(): UnionMember1 =
                    UnionMember1(
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

            fun validate(): UnionMember1 = apply {
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

                return other is UnionMember1 &&
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
                "UnionMember1{blockHosts=$blockHosts, blockImages=$blockImages, blockMedia=$blockMedia, blockStylesheets=$blockStylesheets, blockUrlPatterns=$blockUrlPatterns, additionalProperties=$additionalProperties}"
        }
    }

    /**
     * Session context data to be used in the created session. Sessions will start with an empty
     * context by default.
     */
    class SessionContext
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val cookies: JsonField<List<Cookie>>,
        private val indexedDb: JsonField<IndexedDb>,
        private val localStorage: JsonField<LocalStorage>,
        private val sessionStorage: JsonField<SessionStorage>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("cookies")
            @ExcludeMissing
            cookies: JsonField<List<Cookie>> = JsonMissing.of(),
            @JsonProperty("indexedDB")
            @ExcludeMissing
            indexedDb: JsonField<IndexedDb> = JsonMissing.of(),
            @JsonProperty("localStorage")
            @ExcludeMissing
            localStorage: JsonField<LocalStorage> = JsonMissing.of(),
            @JsonProperty("sessionStorage")
            @ExcludeMissing
            sessionStorage: JsonField<SessionStorage> = JsonMissing.of(),
        ) : this(cookies, indexedDb, localStorage, sessionStorage, mutableMapOf())

        /** Cookies to initialize in the session */
        fun cookies(): Optional<List<Cookie>> = cookies.getOptional("cookies")

        /** Domain-specific indexedDB items to initialize in the session */
        fun indexedDb(): Optional<IndexedDb> = indexedDb.getOptional("indexedDB")

        /** Domain-specific localStorage items to initialize in the session */
        fun localStorage(): Optional<LocalStorage> = localStorage.getOptional("localStorage")

        /** Domain-specific sessionStorage items to initialize in the session */
        fun sessionStorage(): Optional<SessionStorage> =
            sessionStorage.getOptional("sessionStorage")

        @JsonProperty("cookies") @ExcludeMissing fun _cookies(): JsonField<List<Cookie>> = cookies

        @JsonProperty("indexedDB")
        @ExcludeMissing
        fun _indexedDb(): JsonField<IndexedDb> = indexedDb

        @JsonProperty("localStorage")
        @ExcludeMissing
        fun _localStorage(): JsonField<LocalStorage> = localStorage

        @JsonProperty("sessionStorage")
        @ExcludeMissing
        fun _sessionStorage(): JsonField<SessionStorage> = sessionStorage

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

            /** Returns a mutable builder for constructing an instance of [SessionContext]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [SessionContext]. */
        class Builder internal constructor() {

            private var cookies: JsonField<MutableList<Cookie>>? = null
            private var indexedDb: JsonField<IndexedDb> = JsonMissing.of()
            private var localStorage: JsonField<LocalStorage> = JsonMissing.of()
            private var sessionStorage: JsonField<SessionStorage> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(sessionContext: SessionContext) = apply {
                this.cookies = sessionContext.cookies.map { it.toMutableList() }
                this.indexedDb = sessionContext.indexedDb
                this.localStorage = sessionContext.localStorage
                this.sessionStorage = sessionContext.sessionStorage
                this.additionalProperties = sessionContext.additionalProperties.toMutableMap()
            }

            /** Cookies to initialize in the session */
            fun cookies(cookies: List<Cookie>) = cookies(JsonField.of(cookies))

            /** Cookies to initialize in the session */
            fun cookies(cookies: JsonField<List<Cookie>>) = apply {
                this.cookies = cookies.map { it.toMutableList() }
            }

            /** Cookies to initialize in the session */
            fun addCookie(cookie: Cookie) = apply {
                this.cookies =
                    (this.cookies ?: JsonField.of(mutableListOf())).also {
                        checkKnown("cookies", it).add(cookie)
                    }
            }

            /** Domain-specific indexedDB items to initialize in the session */
            fun indexedDb(indexedDb: IndexedDb) = indexedDb(JsonField.of(indexedDb))

            /** Domain-specific indexedDB items to initialize in the session */
            fun indexedDb(indexedDb: JsonField<IndexedDb>) = apply { this.indexedDb = indexedDb }

            /** Domain-specific localStorage items to initialize in the session */
            fun localStorage(localStorage: LocalStorage) = localStorage(JsonField.of(localStorage))

            /** Domain-specific localStorage items to initialize in the session */
            fun localStorage(localStorage: JsonField<LocalStorage>) = apply {
                this.localStorage = localStorage
            }

            /** Domain-specific sessionStorage items to initialize in the session */
            fun sessionStorage(sessionStorage: SessionStorage) =
                sessionStorage(JsonField.of(sessionStorage))

            /** Domain-specific sessionStorage items to initialize in the session */
            fun sessionStorage(sessionStorage: JsonField<SessionStorage>) = apply {
                this.sessionStorage = sessionStorage
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

            fun build(): SessionContext =
                SessionContext(
                    (cookies ?: JsonMissing.of()).map { it.toImmutable() },
                    indexedDb,
                    localStorage,
                    sessionStorage,
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

        fun validate(): SessionContext = apply {
            if (validated) {
                return@apply
            }

            cookies().ifPresent { it.forEach { it.validate() } }
            indexedDb().ifPresent { it.validate() }
            localStorage().ifPresent { it.validate() }
            sessionStorage().ifPresent { it.validate() }
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            (cookies.asKnown().getOrNull()?.let { it.sumOf { (it.validity()).toInt() } } ?: 0) +
                (indexedDb.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (localStorage.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                (sessionStorage.asKnown().getOrNull()?.let { it.validity() } ?: 0)

        class Cookie
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val name: JsonField<String>,
            private val value: JsonField<String>,
            private val domain: JsonField<String>,
            private val expires: JsonField<Double>,
            private val httpOnly: JsonField<Boolean>,
            private val partitionKey: JsonField<PartitionKey>,
            private val path: JsonField<String>,
            private val priority: JsonField<Priority>,
            private val sameParty: JsonField<Boolean>,
            private val sameSite: JsonField<SameSite>,
            private val secure: JsonField<Boolean>,
            private val session: JsonField<Boolean>,
            private val size: JsonField<Double>,
            private val sourcePort: JsonField<Double>,
            private val sourceScheme: JsonField<SourceScheme>,
            private val url: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
                @JsonProperty("value") @ExcludeMissing value: JsonField<String> = JsonMissing.of(),
                @JsonProperty("domain")
                @ExcludeMissing
                domain: JsonField<String> = JsonMissing.of(),
                @JsonProperty("expires")
                @ExcludeMissing
                expires: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("httpOnly")
                @ExcludeMissing
                httpOnly: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("partitionKey")
                @ExcludeMissing
                partitionKey: JsonField<PartitionKey> = JsonMissing.of(),
                @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
                @JsonProperty("priority")
                @ExcludeMissing
                priority: JsonField<Priority> = JsonMissing.of(),
                @JsonProperty("sameParty")
                @ExcludeMissing
                sameParty: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("sameSite")
                @ExcludeMissing
                sameSite: JsonField<SameSite> = JsonMissing.of(),
                @JsonProperty("secure")
                @ExcludeMissing
                secure: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("session")
                @ExcludeMissing
                session: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("size") @ExcludeMissing size: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("sourcePort")
                @ExcludeMissing
                sourcePort: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("sourceScheme")
                @ExcludeMissing
                sourceScheme: JsonField<SourceScheme> = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            ) : this(
                name,
                value,
                domain,
                expires,
                httpOnly,
                partitionKey,
                path,
                priority,
                sameParty,
                sameSite,
                secure,
                session,
                size,
                sourcePort,
                sourceScheme,
                url,
                mutableMapOf(),
            )

            /** The name of the cookie */
            fun name(): String = name.getRequired("name")

            /** The value of the cookie */
            fun value(): String = value.getRequired("value")

            /** The domain of the cookie */
            fun domain(): Optional<String> = domain.getOptional("domain")

            /** The expiration date of the cookie */
            fun expires(): Optional<Double> = expires.getOptional("expires")

            /** Whether the cookie is HTTP only */
            fun httpOnly(): Optional<Boolean> = httpOnly.getOptional("httpOnly")

            /** The partition key of the cookie */
            fun partitionKey(): Optional<PartitionKey> = partitionKey.getOptional("partitionKey")

            /** The path of the cookie */
            fun path(): Optional<String> = path.getOptional("path")

            /** The priority of the cookie */
            fun priority(): Optional<Priority> = priority.getOptional("priority")

            /** Whether the cookie is a same party cookie */
            fun sameParty(): Optional<Boolean> = sameParty.getOptional("sameParty")

            /** The same site attribute of the cookie */
            fun sameSite(): Optional<SameSite> = sameSite.getOptional("sameSite")

            /** Whether the cookie is secure */
            fun secure(): Optional<Boolean> = secure.getOptional("secure")

            /** Whether the cookie is a session cookie */
            fun session(): Optional<Boolean> = session.getOptional("session")

            /** The size of the cookie */
            fun size(): Optional<Double> = size.getOptional("size")

            /** The source port of the cookie */
            fun sourcePort(): Optional<Double> = sourcePort.getOptional("sourcePort")

            /** The source scheme of the cookie */
            fun sourceScheme(): Optional<SourceScheme> = sourceScheme.getOptional("sourceScheme")

            /** The URL of the cookie */
            fun url(): Optional<String> = url.getOptional("url")

            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<String> = value

            @JsonProperty("domain") @ExcludeMissing fun _domain(): JsonField<String> = domain

            @JsonProperty("expires") @ExcludeMissing fun _expires(): JsonField<Double> = expires

            @JsonProperty("httpOnly") @ExcludeMissing fun _httpOnly(): JsonField<Boolean> = httpOnly

            @JsonProperty("partitionKey")
            @ExcludeMissing
            fun _partitionKey(): JsonField<PartitionKey> = partitionKey

            @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

            @JsonProperty("priority")
            @ExcludeMissing
            fun _priority(): JsonField<Priority> = priority

            @JsonProperty("sameParty")
            @ExcludeMissing
            fun _sameParty(): JsonField<Boolean> = sameParty

            @JsonProperty("sameSite")
            @ExcludeMissing
            fun _sameSite(): JsonField<SameSite> = sameSite

            @JsonProperty("secure") @ExcludeMissing fun _secure(): JsonField<Boolean> = secure

            @JsonProperty("session") @ExcludeMissing fun _session(): JsonField<Boolean> = session

            @JsonProperty("size") @ExcludeMissing fun _size(): JsonField<Double> = size

            @JsonProperty("sourcePort")
            @ExcludeMissing
            fun _sourcePort(): JsonField<Double> = sourcePort

            @JsonProperty("sourceScheme")
            @ExcludeMissing
            fun _sourceScheme(): JsonField<SourceScheme> = sourceScheme

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

                /** Returns a mutable builder for constructing an instance of [Cookie]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Cookie]. */
            class Builder internal constructor() {

                private var name: JsonField<String>? = null
                private var value: JsonField<String>? = null
                private var domain: JsonField<String> = JsonMissing.of()
                private var expires: JsonField<Double> = JsonMissing.of()
                private var httpOnly: JsonField<Boolean> = JsonMissing.of()
                private var partitionKey: JsonField<PartitionKey> = JsonMissing.of()
                private var path: JsonField<String> = JsonMissing.of()
                private var priority: JsonField<Priority> = JsonMissing.of()
                private var sameParty: JsonField<Boolean> = JsonMissing.of()
                private var sameSite: JsonField<SameSite> = JsonMissing.of()
                private var secure: JsonField<Boolean> = JsonMissing.of()
                private var session: JsonField<Boolean> = JsonMissing.of()
                private var size: JsonField<Double> = JsonMissing.of()
                private var sourcePort: JsonField<Double> = JsonMissing.of()
                private var sourceScheme: JsonField<SourceScheme> = JsonMissing.of()
                private var url: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(cookie: Cookie) = apply {
                    this.name = cookie.name
                    this.value = cookie.value
                    this.domain = cookie.domain
                    this.expires = cookie.expires
                    this.httpOnly = cookie.httpOnly
                    this.partitionKey = cookie.partitionKey
                    this.path = cookie.path
                    this.priority = cookie.priority
                    this.sameParty = cookie.sameParty
                    this.sameSite = cookie.sameSite
                    this.secure = cookie.secure
                    this.session = cookie.session
                    this.size = cookie.size
                    this.sourcePort = cookie.sourcePort
                    this.sourceScheme = cookie.sourceScheme
                    this.url = cookie.url
                    this.additionalProperties = cookie.additionalProperties.toMutableMap()
                }

                /** The name of the cookie */
                fun name(name: String) = name(JsonField.of(name))

                /** The name of the cookie */
                fun name(name: JsonField<String>) = apply { this.name = name }

                /** The value of the cookie */
                fun value(value: String) = value(JsonField.of(value))

                /** The value of the cookie */
                fun value(value: JsonField<String>) = apply { this.value = value }

                /** The domain of the cookie */
                fun domain(domain: String) = domain(JsonField.of(domain))

                /** The domain of the cookie */
                fun domain(domain: JsonField<String>) = apply { this.domain = domain }

                /** The expiration date of the cookie */
                fun expires(expires: Double) = expires(JsonField.of(expires))

                /** The expiration date of the cookie */
                fun expires(expires: JsonField<Double>) = apply { this.expires = expires }

                /** Whether the cookie is HTTP only */
                fun httpOnly(httpOnly: Boolean) = httpOnly(JsonField.of(httpOnly))

                /** Whether the cookie is HTTP only */
                fun httpOnly(httpOnly: JsonField<Boolean>) = apply { this.httpOnly = httpOnly }

                /** The partition key of the cookie */
                fun partitionKey(partitionKey: PartitionKey) =
                    partitionKey(JsonField.of(partitionKey))

                /** The partition key of the cookie */
                fun partitionKey(partitionKey: JsonField<PartitionKey>) = apply {
                    this.partitionKey = partitionKey
                }

                /** The path of the cookie */
                fun path(path: String) = path(JsonField.of(path))

                /** The path of the cookie */
                fun path(path: JsonField<String>) = apply { this.path = path }

                /** The priority of the cookie */
                fun priority(priority: Priority) = priority(JsonField.of(priority))

                /** The priority of the cookie */
                fun priority(priority: JsonField<Priority>) = apply { this.priority = priority }

                /** Whether the cookie is a same party cookie */
                fun sameParty(sameParty: Boolean) = sameParty(JsonField.of(sameParty))

                /** Whether the cookie is a same party cookie */
                fun sameParty(sameParty: JsonField<Boolean>) = apply { this.sameParty = sameParty }

                /** The same site attribute of the cookie */
                fun sameSite(sameSite: SameSite) = sameSite(JsonField.of(sameSite))

                /** The same site attribute of the cookie */
                fun sameSite(sameSite: JsonField<SameSite>) = apply { this.sameSite = sameSite }

                /** Whether the cookie is secure */
                fun secure(secure: Boolean) = secure(JsonField.of(secure))

                /** Whether the cookie is secure */
                fun secure(secure: JsonField<Boolean>) = apply { this.secure = secure }

                /** Whether the cookie is a session cookie */
                fun session(session: Boolean) = session(JsonField.of(session))

                /** Whether the cookie is a session cookie */
                fun session(session: JsonField<Boolean>) = apply { this.session = session }

                /** The size of the cookie */
                fun size(size: Double) = size(JsonField.of(size))

                /** The size of the cookie */
                fun size(size: JsonField<Double>) = apply { this.size = size }

                /** The source port of the cookie */
                fun sourcePort(sourcePort: Double) = sourcePort(JsonField.of(sourcePort))

                /** The source port of the cookie */
                fun sourcePort(sourcePort: JsonField<Double>) = apply {
                    this.sourcePort = sourcePort
                }

                /** The source scheme of the cookie */
                fun sourceScheme(sourceScheme: SourceScheme) =
                    sourceScheme(JsonField.of(sourceScheme))

                /** The source scheme of the cookie */
                fun sourceScheme(sourceScheme: JsonField<SourceScheme>) = apply {
                    this.sourceScheme = sourceScheme
                }

                /** The URL of the cookie */
                fun url(url: String) = url(JsonField.of(url))

                /** The URL of the cookie */
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

                fun build(): Cookie =
                    Cookie(
                        checkRequired("name", name),
                        checkRequired("value", value),
                        domain,
                        expires,
                        httpOnly,
                        partitionKey,
                        path,
                        priority,
                        sameParty,
                        sameSite,
                        secure,
                        session,
                        size,
                        sourcePort,
                        sourceScheme,
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

            fun validate(): Cookie = apply {
                if (validated) {
                    return@apply
                }

                name()
                value()
                domain()
                expires()
                httpOnly()
                partitionKey().ifPresent { it.validate() }
                path()
                priority().ifPresent { it.validate() }
                sameParty()
                sameSite().ifPresent { it.validate() }
                secure()
                session()
                size()
                sourcePort()
                sourceScheme().ifPresent { it.validate() }
                url()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (if (name.asKnown().isPresent) 1 else 0) +
                    (if (value.asKnown().isPresent) 1 else 0) +
                    (if (domain.asKnown().isPresent) 1 else 0) +
                    (if (expires.asKnown().isPresent) 1 else 0) +
                    (if (httpOnly.asKnown().isPresent) 1 else 0) +
                    (partitionKey.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (path.asKnown().isPresent) 1 else 0) +
                    (priority.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (sameParty.asKnown().isPresent) 1 else 0) +
                    (sameSite.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (secure.asKnown().isPresent) 1 else 0) +
                    (if (session.asKnown().isPresent) 1 else 0) +
                    (if (size.asKnown().isPresent) 1 else 0) +
                    (if (sourcePort.asKnown().isPresent) 1 else 0) +
                    (sourceScheme.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                    (if (url.asKnown().isPresent) 1 else 0)

            /** The partition key of the cookie */
            class PartitionKey
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val hasCrossSiteAncestor: JsonField<Boolean>,
                private val topLevelSite: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("hasCrossSiteAncestor")
                    @ExcludeMissing
                    hasCrossSiteAncestor: JsonField<Boolean> = JsonMissing.of(),
                    @JsonProperty("topLevelSite")
                    @ExcludeMissing
                    topLevelSite: JsonField<String> = JsonMissing.of(),
                ) : this(hasCrossSiteAncestor, topLevelSite, mutableMapOf())

                /**
                 * Indicates if the cookie has any ancestors that are cross-site to the
                 * topLevelSite.
                 */
                fun hasCrossSiteAncestor(): Boolean =
                    hasCrossSiteAncestor.getRequired("hasCrossSiteAncestor")

                /**
                 * The site of the top-level URL the browser was visiting at the start of the
                 * request to the endpoint that set the cookie.
                 */
                fun topLevelSite(): String = topLevelSite.getRequired("topLevelSite")

                @JsonProperty("hasCrossSiteAncestor")
                @ExcludeMissing
                fun _hasCrossSiteAncestor(): JsonField<Boolean> = hasCrossSiteAncestor

                @JsonProperty("topLevelSite")
                @ExcludeMissing
                fun _topLevelSite(): JsonField<String> = topLevelSite

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

                    /** Returns a mutable builder for constructing an instance of [PartitionKey]. */
                    @JvmStatic fun builder() = Builder()
                }

                /** A builder for [PartitionKey]. */
                class Builder internal constructor() {

                    private var hasCrossSiteAncestor: JsonField<Boolean>? = null
                    private var topLevelSite: JsonField<String>? = null
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    @JvmSynthetic
                    internal fun from(partitionKey: PartitionKey) = apply {
                        this.hasCrossSiteAncestor = partitionKey.hasCrossSiteAncestor
                        this.topLevelSite = partitionKey.topLevelSite
                        this.additionalProperties = partitionKey.additionalProperties.toMutableMap()
                    }

                    /**
                     * Indicates if the cookie has any ancestors that are cross-site to the
                     * topLevelSite.
                     */
                    fun hasCrossSiteAncestor(hasCrossSiteAncestor: Boolean) =
                        hasCrossSiteAncestor(JsonField.of(hasCrossSiteAncestor))

                    /**
                     * Indicates if the cookie has any ancestors that are cross-site to the
                     * topLevelSite.
                     */
                    fun hasCrossSiteAncestor(hasCrossSiteAncestor: JsonField<Boolean>) = apply {
                        this.hasCrossSiteAncestor = hasCrossSiteAncestor
                    }

                    /**
                     * The site of the top-level URL the browser was visiting at the start of the
                     * request to the endpoint that set the cookie.
                     */
                    fun topLevelSite(topLevelSite: String) =
                        topLevelSite(JsonField.of(topLevelSite))

                    /**
                     * The site of the top-level URL the browser was visiting at the start of the
                     * request to the endpoint that set the cookie.
                     */
                    fun topLevelSite(topLevelSite: JsonField<String>) = apply {
                        this.topLevelSite = topLevelSite
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

                    fun build(): PartitionKey =
                        PartitionKey(
                            checkRequired("hasCrossSiteAncestor", hasCrossSiteAncestor),
                            checkRequired("topLevelSite", topLevelSite),
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

                fun validate(): PartitionKey = apply {
                    if (validated) {
                        return@apply
                    }

                    hasCrossSiteAncestor()
                    topLevelSite()
                    validated = true
                }

                @JvmSynthetic
                internal fun validity(): Int =
                    (if (hasCrossSiteAncestor.asKnown().isPresent) 1 else 0) +
                        (if (topLevelSite.asKnown().isPresent) 1 else 0)

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is PartitionKey &&
                        this.hasCrossSiteAncestor == other.hasCrossSiteAncestor &&
                        this.topLevelSite == other.topLevelSite &&
                        this.additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(hasCrossSiteAncestor, topLevelSite, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "PartitionKey{hasCrossSiteAncestor=$hasCrossSiteAncestor, topLevelSite=$topLevelSite, additionalProperties=$additionalProperties}"
            }

            class Priority @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val LOW = of("Low")

                    @JvmField val MEDIUM = of("Medium")

                    @JvmField val HIGH = of("High")

                    @JvmStatic fun of(value: String) = Priority(JsonField.of(value))
                }

                enum class Known {
                    LOW,
                    MEDIUM,
                    HIGH,
                }

                enum class Value {
                    LOW,
                    MEDIUM,
                    HIGH,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        LOW -> Value.LOW
                        MEDIUM -> Value.MEDIUM
                        HIGH -> Value.HIGH
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        LOW -> Known.LOW
                        MEDIUM -> Known.MEDIUM
                        HIGH -> Known.HIGH
                        else -> throw SteelInvalidDataException("Unknown Priority: $value")
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

                fun validate(): Priority = apply {
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

                    return other is Priority && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            class SameSite @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val STRICT = of("Strict")

                    @JvmField val LAX = of("Lax")

                    @JvmField val NONE = of("None")

                    @JvmStatic fun of(value: String) = SameSite(JsonField.of(value))
                }

                enum class Known {
                    STRICT,
                    LAX,
                    NONE,
                }

                enum class Value {
                    STRICT,
                    LAX,
                    NONE,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        STRICT -> Value.STRICT
                        LAX -> Value.LAX
                        NONE -> Value.NONE
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        STRICT -> Known.STRICT
                        LAX -> Known.LAX
                        NONE -> Known.NONE
                        else -> throw SteelInvalidDataException("Unknown SameSite: $value")
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

                fun validate(): SameSite = apply {
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

                    return other is SameSite && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            class SourceScheme
            @JsonCreator
            private constructor(private val value: JsonField<String>) : Enum {

                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val UNSET = of("Unset")

                    @JvmField val NON_SECURE = of("NonSecure")

                    @JvmField val SECURE = of("Secure")

                    @JvmStatic fun of(value: String) = SourceScheme(JsonField.of(value))
                }

                enum class Known {
                    UNSET,
                    NON_SECURE,
                    SECURE,
                }

                enum class Value {
                    UNSET,
                    NON_SECURE,
                    SECURE,
                    _UNKNOWN,
                }

                fun value(): Value =
                    when (this) {
                        UNSET -> Value.UNSET
                        NON_SECURE -> Value.NON_SECURE
                        SECURE -> Value.SECURE
                        else -> Value._UNKNOWN
                    }

                fun known(): Known =
                    when (this) {
                        UNSET -> Known.UNSET
                        NON_SECURE -> Known.NON_SECURE
                        SECURE -> Known.SECURE
                        else -> throw SteelInvalidDataException("Unknown SourceScheme: $value")
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

                fun validate(): SourceScheme = apply {
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

                    return other is SourceScheme && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Cookie &&
                    this.name == other.name &&
                    this.value == other.value &&
                    this.domain == other.domain &&
                    this.expires == other.expires &&
                    this.httpOnly == other.httpOnly &&
                    this.partitionKey == other.partitionKey &&
                    this.path == other.path &&
                    this.priority == other.priority &&
                    this.sameParty == other.sameParty &&
                    this.sameSite == other.sameSite &&
                    this.secure == other.secure &&
                    this.session == other.session &&
                    this.size == other.size &&
                    this.sourcePort == other.sourcePort &&
                    this.sourceScheme == other.sourceScheme &&
                    this.url == other.url &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    name,
                    value,
                    domain,
                    expires,
                    httpOnly,
                    partitionKey,
                    path,
                    priority,
                    sameParty,
                    sameSite,
                    secure,
                    session,
                    size,
                    sourcePort,
                    sourceScheme,
                    url,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Cookie{name=$name, value=$value, domain=$domain, expires=$expires, httpOnly=$httpOnly, partitionKey=$partitionKey, path=$path, priority=$priority, sameParty=$sameParty, sameSite=$sameSite, secure=$secure, session=$session, size=$size, sourcePort=$sourcePort, sourceScheme=$sourceScheme, url=$url, additionalProperties=$additionalProperties}"
        }

        class IndexedDb
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
                internal fun from(indexedDb: IndexedDb) = apply {
                    this.additionalProperties = indexedDb.additionalProperties.toMutableMap()
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

                fun build(): IndexedDb = IndexedDb(additionalProperties.toImmutable())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): IndexedDb = apply {
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

                return other is IndexedDb && this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "IndexedDb{additionalProperties=$additionalProperties}"
        }

        class LocalStorage
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
                internal fun from(localStorage: LocalStorage) = apply {
                    this.additionalProperties = localStorage.additionalProperties.toMutableMap()
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

                fun build(): LocalStorage = LocalStorage(additionalProperties.toImmutable())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): LocalStorage = apply {
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

                return other is LocalStorage &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "LocalStorage{additionalProperties=$additionalProperties}"
        }

        class SessionStorage
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
                internal fun from(sessionStorage: SessionStorage) = apply {
                    this.additionalProperties = sessionStorage.additionalProperties.toMutableMap()
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

                fun build(): SessionStorage = SessionStorage(additionalProperties.toImmutable())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): SessionStorage = apply {
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

                return other is SessionStorage &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "SessionStorage{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is SessionContext &&
                this.cookies == other.cookies &&
                this.indexedDb == other.indexedDb &&
                this.localStorage == other.localStorage &&
                this.sessionStorage == other.sessionStorage &&
                this.additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(cookies, indexedDb, localStorage, sessionStorage, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "SessionContext{cookies=$cookies, indexedDb=$indexedDb, localStorage=$localStorage, sessionStorage=$sessionStorage, additionalProperties=$additionalProperties}"
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
        ) : this(autoCaptchaSolving, humanizeInteractions, skipFingerprintInjection, mutableMapOf())

        /**
         * When true, captchas will be automatically solved when detected. When false, use the solve
         * endpoints to manually initiate solving.
         */
        fun autoCaptchaSolving(): Optional<Boolean> =
            autoCaptchaSolving.getOptional("autoCaptchaSolving")

        /**
         * This flag will make the browser act more human-like by moving the mouse in a more natural
         * way.
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
             * When true, captchas will be automatically solved when detected. When false, use the
             * solve endpoints to manually initiate solving.
             */
            fun autoCaptchaSolving(autoCaptchaSolving: Boolean) =
                autoCaptchaSolving(JsonField.of(autoCaptchaSolving))

            /**
             * When true, captchas will be automatically solved when detected. When false, use the
             * solve endpoints to manually initiate solving.
             */
            fun autoCaptchaSolving(autoCaptchaSolving: JsonField<Boolean>) = apply {
                this.autoCaptchaSolving = autoCaptchaSolving
            }

            /**
             * This flag will make the browser act more human-like by moving the mouse in a more
             * natural way.
             */
            fun humanizeInteractions(humanizeInteractions: Boolean) =
                humanizeInteractions(JsonField.of(humanizeInteractions))

            /**
             * This flag will make the browser act more human-like by moving the mouse in a more
             * natural way.
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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

    @JsonDeserialize(using = UseProxy.Deserializer::class)
    @JsonSerialize(using = UseProxy.Serializer::class)
    class UseProxy
    private constructor(
        private val bool: Boolean? = null,
        private val geolocation: Geolocation? = null,
        private val server: Server? = null,
        private val unionMember3: UnionMember3? = null,
        private val json: JsonValue? = null,
        private val _json: JsonValue? = null,
    ) {

        fun bool(): Optional<Boolean> = Optional.ofNullable(bool)

        fun geolocation(): Optional<Geolocation> = Optional.ofNullable(geolocation)

        fun server(): Optional<Server> = Optional.ofNullable(server)

        fun unionMember3(): Optional<UnionMember3> = Optional.ofNullable(unionMember3)

        fun json(): Optional<JsonValue> = Optional.ofNullable(json)

        fun isBool(): Boolean = bool != null

        fun isGeolocation(): Boolean = geolocation != null

        fun isServer(): Boolean = server != null

        fun isUnionMember3(): Boolean = unionMember3 != null

        fun isJson(): Boolean = json != null

        fun asBool(): Boolean = bool.getOrThrow("bool")

        fun asGeolocation(): Geolocation = geolocation.getOrThrow("geolocation")

        fun asServer(): Server = server.getOrThrow("server")

        fun asUnionMember3(): UnionMember3 = unionMember3.getOrThrow("unionMember3")

        fun asJson(): JsonValue = json.getOrThrow("json")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                bool != null -> visitor.visitBool(bool)
                geolocation != null -> visitor.visitGeolocation(geolocation)
                server != null -> visitor.visitServer(server)
                unionMember3 != null -> visitor.visitUnionMember3(unionMember3)
                json != null -> visitor.visitJson(json)
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

        fun validate(): UseProxy = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBool(bool: Boolean) {}

                    override fun visitGeolocation(geolocation: Geolocation) {
                        geolocation.validate()
                    }

                    override fun visitServer(server: Server) {
                        server.validate()
                    }

                    override fun visitUnionMember3(unionMember3: UnionMember3) {
                        unionMember3.validate()
                    }

                    override fun visitJson(json: JsonValue) {}
                }
            )
            validated = true
        }

        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitBool(bool: Boolean) = 1

                    override fun visitGeolocation(geolocation: Geolocation) = geolocation.validity()

                    override fun visitServer(server: Server) = server.validity()

                    override fun visitUnionMember3(unionMember3: UnionMember3) =
                        unionMember3.validity()

                    override fun visitJson(json: JsonValue) = 0

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is UseProxy &&
                this.bool == other.bool &&
                this.geolocation == other.geolocation &&
                this.server == other.server &&
                this.unionMember3 == other.unionMember3 &&
                this.json == other.json
        }

        override fun hashCode(): Int = Objects.hash(bool, geolocation, server, unionMember3, json)

        override fun toString(): String =
            when {
                bool != null -> "UseProxy{bool=$bool}"
                geolocation != null -> "UseProxy{geolocation=$geolocation}"
                server != null -> "UseProxy{server=$server}"
                unionMember3 != null -> "UseProxy{unionMember3=$unionMember3}"
                json != null -> "UseProxy{json=$json}"
                _json != null -> "UseProxy{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid UseProxy")
            }

        companion object {

            @JvmStatic fun ofBool(bool: Boolean) = UseProxy(bool = bool)

            @JvmStatic
            fun ofGeolocation(geolocation: Geolocation) = UseProxy(geolocation = geolocation)

            @JvmStatic fun ofServer(server: Server) = UseProxy(server = server)

            @JvmStatic
            fun ofUnionMember3(unionMember3: UnionMember3) = UseProxy(unionMember3 = unionMember3)

            @JvmStatic fun ofJson(json: JsonValue) = UseProxy(json = json)
        }

        interface Visitor<out T> {

            fun visitBool(bool: Boolean): T

            fun visitGeolocation(geolocation: Geolocation): T

            fun visitServer(server: Server): T

            fun visitUnionMember3(unionMember3: UnionMember3): T

            fun visitJson(json: JsonValue): T

            fun unknown(json: JsonValue?): T {
                throw SteelInvalidDataException("Unknown UseProxy: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<UseProxy>(UseProxy::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): UseProxy {
                val json = JsonValue.fromJsonNode(node)
                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<Geolocation>())?.let {
                                UseProxy(geolocation = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<Server>())?.let {
                                UseProxy(server = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<UnionMember3>())?.let {
                                UseProxy(unionMember3 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<Boolean>())?.let {
                                UseProxy(bool = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<JsonValue>())?.let {
                                UseProxy(json = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    0 -> UseProxy(_json = json)
                    1 -> bestMatches.single()
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<UseProxy>(UseProxy::class) {

            override fun serialize(
                value: UseProxy,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.bool != null -> generator.writeObject(value.bool)
                    value.geolocation != null -> generator.writeObject(value.geolocation)
                    value.server != null -> generator.writeObject(value.server)
                    value.unionMember3 != null -> generator.writeObject(value.unionMember3)
                    value.json != null -> generator.writeObject(value.json)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid UseProxy")
                }
            }
        }

        class Geolocation
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val geolocation: JsonField<GeolocationModel>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("geolocation")
                @ExcludeMissing
                geolocation: JsonField<GeolocationModel> = JsonMissing.of()
            ) : this(geolocation, mutableMapOf())

            /** Geographic location for the proxy */
            fun geolocation(): GeolocationModel = geolocation.getRequired("geolocation")

            @JsonProperty("geolocation")
            @ExcludeMissing
            fun _geolocation(): JsonField<GeolocationModel> = geolocation

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

                /** Returns a mutable builder for constructing an instance of [Geolocation]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Geolocation]. */
            class Builder internal constructor() {

                private var geolocation: JsonField<GeolocationModel>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(geolocation: Geolocation) = apply {
                    this.geolocation = geolocation.geolocation
                    this.additionalProperties = geolocation.additionalProperties.toMutableMap()
                }

                /** Geographic location for the proxy */
                fun geolocation(geolocation: GeolocationModel) =
                    geolocation(JsonField.of(geolocation))

                /** Geographic location for the proxy */
                fun geolocation(geolocation: JsonField<GeolocationModel>) = apply {
                    this.geolocation = geolocation
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

                fun build(): Geolocation =
                    Geolocation(
                        checkRequired("geolocation", geolocation),
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

            fun validate(): Geolocation = apply {
                if (validated) {
                    return@apply
                }

                geolocation().validate()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (geolocation.asKnown().getOrNull()?.let { it.validity() } ?: 0)

            /** Geographic location for the proxy */
            class GeolocationModel
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val country: JsonField<Country>,
                private val city: JsonField<City>,
                private val state: JsonField<State>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("country")
                    @ExcludeMissing
                    country: JsonField<Country> = JsonMissing.of(),
                    @JsonProperty("city") @ExcludeMissing city: JsonField<City> = JsonMissing.of(),
                    @JsonProperty("state")
                    @ExcludeMissing
                    state: JsonField<State> = JsonMissing.of(),
                ) : this(country, city, state, mutableMapOf())

                /** Country code (e.g., 'US', 'GB', 'DE') - ISO 3166-1 alpha-2 */
                fun country(): Country = country.getRequired("country")

                /** City name (e.g., 'NEW_YORK', 'LOS_ANGELES') */
                fun city(): Optional<City> = city.getOptional("city")

                /** State code (e.g., 'NY', 'CA') - US states only */
                fun state(): Optional<State> = state.getOptional("state")

                @JsonProperty("country")
                @ExcludeMissing
                fun _country(): JsonField<Country> = country

                @JsonProperty("city") @ExcludeMissing fun _city(): JsonField<City> = city

                @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<State> = state

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
                     * Returns a mutable builder for constructing an instance of [GeolocationModel].
                     */
                    @JvmStatic fun builder() = Builder()
                }

                /** A builder for [GeolocationModel]. */
                class Builder internal constructor() {

                    private var country: JsonField<Country>? = null
                    private var city: JsonField<City> = JsonMissing.of()
                    private var state: JsonField<State> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    @JvmSynthetic
                    internal fun from(geolocationModel: GeolocationModel) = apply {
                        this.country = geolocationModel.country
                        this.city = geolocationModel.city
                        this.state = geolocationModel.state
                        this.additionalProperties =
                            geolocationModel.additionalProperties.toMutableMap()
                    }

                    /** Country code (e.g., 'US', 'GB', 'DE') - ISO 3166-1 alpha-2 */
                    fun country(country: Country) = country(JsonField.of(country))

                    /** Country code (e.g., 'US', 'GB', 'DE') - ISO 3166-1 alpha-2 */
                    fun country(country: JsonField<Country>) = apply { this.country = country }

                    /** City name (e.g., 'NEW_YORK', 'LOS_ANGELES') */
                    fun city(city: City) = city(JsonField.of(city))

                    /** City name (e.g., 'NEW_YORK', 'LOS_ANGELES') */
                    fun city(city: JsonField<City>) = apply { this.city = city }

                    /** State code (e.g., 'NY', 'CA') - US states only */
                    fun state(state: State) = state(JsonField.of(state))

                    /** State code (e.g., 'NY', 'CA') - US states only */
                    fun state(state: JsonField<State>) = apply { this.state = state }

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

                    fun build(): GeolocationModel =
                        GeolocationModel(
                            checkRequired("country", country),
                            city,
                            state,
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

                fun validate(): GeolocationModel = apply {
                    if (validated) {
                        return@apply
                    }

                    country().validate()
                    city().ifPresent { it.validate() }
                    state().ifPresent { it.validate() }
                    validated = true
                }

                @JvmSynthetic
                internal fun validity(): Int =
                    (country.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (city.asKnown().getOrNull()?.let { it.validity() } ?: 0) +
                        (state.asKnown().getOrNull()?.let { it.validity() } ?: 0)

                class Country
                @JsonCreator
                private constructor(private val value: JsonField<String>) : Enum {

                    @com.fasterxml.jackson.annotation.JsonValue
                    fun _value(): JsonField<String> = value

                    companion object {

                        @JvmField val US = of("US")

                        @JvmField val CA = of("CA")

                        @JvmField val MX = of("MX")

                        @JvmField val GB = of("GB")

                        @JvmField val DE = of("DE")

                        @JvmField val FR = of("FR")

                        @JvmField val IT = of("IT")

                        @JvmField val ES = of("ES")

                        @JvmField val PL = of("PL")

                        @JvmField val NL = of("NL")

                        @JvmField val SE = of("SE")

                        @JvmField val NO = of("NO")

                        @JvmField val DK = of("DK")

                        @JvmField val FI = of("FI")

                        @JvmField val CH = of("CH")

                        @JvmField val AT = of("AT")

                        @JvmField val BE = of("BE")

                        @JvmField val IE = of("IE")

                        @JvmField val PT = of("PT")

                        @JvmField val GR = of("GR")

                        @JvmField val CZ = of("CZ")

                        @JvmField val HU = of("HU")

                        @JvmField val RO = of("RO")

                        @JvmField val BG = of("BG")

                        @JvmField val SK = of("SK")

                        @JvmField val SI = of("SI")

                        @JvmField val HR = of("HR")

                        @JvmField val EE = of("EE")

                        @JvmField val LV = of("LV")

                        @JvmField val LT = of("LT")

                        @JvmField val LU = of("LU")

                        @JvmField val MT = of("MT")

                        @JvmField val CY = of("CY")

                        @JvmField val IS = of("IS")

                        @JvmField val LI = of("LI")

                        @JvmField val MC = of("MC")

                        @JvmField val SM = of("SM")

                        @JvmField val VA = of("VA")

                        @JvmField val JP = of("JP")

                        @JvmField val KR = of("KR")

                        @JvmField val CN = of("CN")

                        @JvmField val HK = of("HK")

                        @JvmField val TW = of("TW")

                        @JvmField val SG = of("SG")

                        @JvmField val AU = of("AU")

                        @JvmField val NZ = of("NZ")

                        @JvmField val IN = of("IN")

                        @JvmField val TH = of("TH")

                        @JvmField val MY = of("MY")

                        @JvmField val PH = of("PH")

                        @JvmField val ID = of("ID")

                        @JvmField val VN = of("VN")

                        @JvmField val AF = of("AF")

                        @JvmField val BD = of("BD")

                        @JvmField val BN = of("BN")

                        @JvmField val KH = of("KH")

                        @JvmField val LA = of("LA")

                        @JvmField val LK = of("LK")

                        @JvmField val MM = of("MM")

                        @JvmField val NP = of("NP")

                        @JvmField val PK = of("PK")

                        @JvmField val FJ = of("FJ")

                        @JvmField val PG = of("PG")

                        @JvmField val AE = of("AE")

                        @JvmField val SA = of("SA")

                        @JvmField val IL = of("IL")

                        @JvmField val TR = of("TR")

                        @JvmField val IR = of("IR")

                        @JvmField val IQ = of("IQ")

                        @JvmField val JO = of("JO")

                        @JvmField val KW = of("KW")

                        @JvmField val LB = of("LB")

                        @JvmField val OM = of("OM")

                        @JvmField val QA = of("QA")

                        @JvmField val BH = of("BH")

                        @JvmField val YE = of("YE")

                        @JvmField val SY = of("SY")

                        @JvmField val ZA = of("ZA")

                        @JvmField val EG = of("EG")

                        @JvmField val MA = of("MA")

                        @JvmField val NG = of("NG")

                        @JvmField val KE = of("KE")

                        @JvmField val DZ = of("DZ")

                        @JvmField val AO = of("AO")

                        @JvmField val BW = of("BW")

                        @JvmField val ET = of("ET")

                        @JvmField val GH = of("GH")

                        @JvmField val CI = of("CI")

                        @JvmField val LY = of("LY")

                        @JvmField val MZ = of("MZ")

                        @JvmField val RW = of("RW")

                        @JvmField val SN = of("SN")

                        @JvmField val TN = of("TN")

                        @JvmField val UG = of("UG")

                        @JvmField val ZM = of("ZM")

                        @JvmField val ZW = of("ZW")

                        @JvmField val TZ = of("TZ")

                        @JvmField val MU = of("MU")

                        @JvmField val SC = of("SC")

                        @JvmField val BR = of("BR")

                        @JvmField val AR = of("AR")

                        @JvmField val CL = of("CL")

                        @JvmField val CO = of("CO")

                        @JvmField val PE = of("PE")

                        @JvmField val VE = of("VE")

                        @JvmField val EC = of("EC")

                        @JvmField val UY = of("UY")

                        @JvmField val PY = of("PY")

                        @JvmField val BO = of("BO")

                        @JvmField val CR = of("CR")

                        @JvmField val CU = of("CU")

                        @JvmField val DO = of("DO")

                        @JvmField val GT = of("GT")

                        @JvmField val HN = of("HN")

                        @JvmField val JM = of("JM")

                        @JvmField val NI = of("NI")

                        @JvmField val PA = of("PA")

                        @JvmField val SV = of("SV")

                        @JvmField val TT = of("TT")

                        @JvmField val BB = of("BB")

                        @JvmField val BZ = of("BZ")

                        @JvmField val GY = of("GY")

                        @JvmField val SR = of("SR")

                        @JvmField val RU = of("RU")

                        @JvmField val UA = of("UA")

                        @JvmField val BY = of("BY")

                        @JvmField val KZ = of("KZ")

                        @JvmField val UZ = of("UZ")

                        @JvmField val AZ = of("AZ")

                        @JvmField val GE = of("GE")

                        @JvmField val AM = of("AM")

                        @JvmField val MD = of("MD")

                        @JvmField val MK = of("MK")

                        @JvmField val AL = of("AL")

                        @JvmField val BA = of("BA")

                        @JvmField val RS = of("RS")

                        @JvmField val ME = of("ME")

                        @JvmField val XK = of("XK")

                        @JvmField val MN = of("MN")

                        @JvmField val KG = of("KG")

                        @JvmField val TJ = of("TJ")

                        @JvmField val TM = of("TM")

                        @JvmStatic fun of(value: String) = Country(JsonField.of(value))
                    }

                    enum class Known {
                        US,
                        CA,
                        MX,
                        GB,
                        DE,
                        FR,
                        IT,
                        ES,
                        PL,
                        NL,
                        SE,
                        NO,
                        DK,
                        FI,
                        CH,
                        AT,
                        BE,
                        IE,
                        PT,
                        GR,
                        CZ,
                        HU,
                        RO,
                        BG,
                        SK,
                        SI,
                        HR,
                        EE,
                        LV,
                        LT,
                        LU,
                        MT,
                        CY,
                        IS,
                        LI,
                        MC,
                        SM,
                        VA,
                        JP,
                        KR,
                        CN,
                        HK,
                        TW,
                        SG,
                        AU,
                        NZ,
                        IN,
                        TH,
                        MY,
                        PH,
                        ID,
                        VN,
                        AF,
                        BD,
                        BN,
                        KH,
                        LA,
                        LK,
                        MM,
                        NP,
                        PK,
                        FJ,
                        PG,
                        AE,
                        SA,
                        IL,
                        TR,
                        IR,
                        IQ,
                        JO,
                        KW,
                        LB,
                        OM,
                        QA,
                        BH,
                        YE,
                        SY,
                        ZA,
                        EG,
                        MA,
                        NG,
                        KE,
                        DZ,
                        AO,
                        BW,
                        ET,
                        GH,
                        CI,
                        LY,
                        MZ,
                        RW,
                        SN,
                        TN,
                        UG,
                        ZM,
                        ZW,
                        TZ,
                        MU,
                        SC,
                        BR,
                        AR,
                        CL,
                        CO,
                        PE,
                        VE,
                        EC,
                        UY,
                        PY,
                        BO,
                        CR,
                        CU,
                        DO,
                        GT,
                        HN,
                        JM,
                        NI,
                        PA,
                        SV,
                        TT,
                        BB,
                        BZ,
                        GY,
                        SR,
                        RU,
                        UA,
                        BY,
                        KZ,
                        UZ,
                        AZ,
                        GE,
                        AM,
                        MD,
                        MK,
                        AL,
                        BA,
                        RS,
                        ME,
                        XK,
                        MN,
                        KG,
                        TJ,
                        TM,
                    }

                    enum class Value {
                        US,
                        CA,
                        MX,
                        GB,
                        DE,
                        FR,
                        IT,
                        ES,
                        PL,
                        NL,
                        SE,
                        NO,
                        DK,
                        FI,
                        CH,
                        AT,
                        BE,
                        IE,
                        PT,
                        GR,
                        CZ,
                        HU,
                        RO,
                        BG,
                        SK,
                        SI,
                        HR,
                        EE,
                        LV,
                        LT,
                        LU,
                        MT,
                        CY,
                        IS,
                        LI,
                        MC,
                        SM,
                        VA,
                        JP,
                        KR,
                        CN,
                        HK,
                        TW,
                        SG,
                        AU,
                        NZ,
                        IN,
                        TH,
                        MY,
                        PH,
                        ID,
                        VN,
                        AF,
                        BD,
                        BN,
                        KH,
                        LA,
                        LK,
                        MM,
                        NP,
                        PK,
                        FJ,
                        PG,
                        AE,
                        SA,
                        IL,
                        TR,
                        IR,
                        IQ,
                        JO,
                        KW,
                        LB,
                        OM,
                        QA,
                        BH,
                        YE,
                        SY,
                        ZA,
                        EG,
                        MA,
                        NG,
                        KE,
                        DZ,
                        AO,
                        BW,
                        ET,
                        GH,
                        CI,
                        LY,
                        MZ,
                        RW,
                        SN,
                        TN,
                        UG,
                        ZM,
                        ZW,
                        TZ,
                        MU,
                        SC,
                        BR,
                        AR,
                        CL,
                        CO,
                        PE,
                        VE,
                        EC,
                        UY,
                        PY,
                        BO,
                        CR,
                        CU,
                        DO,
                        GT,
                        HN,
                        JM,
                        NI,
                        PA,
                        SV,
                        TT,
                        BB,
                        BZ,
                        GY,
                        SR,
                        RU,
                        UA,
                        BY,
                        KZ,
                        UZ,
                        AZ,
                        GE,
                        AM,
                        MD,
                        MK,
                        AL,
                        BA,
                        RS,
                        ME,
                        XK,
                        MN,
                        KG,
                        TJ,
                        TM,
                        _UNKNOWN,
                    }

                    fun value(): Value =
                        when (this) {
                            US -> Value.US
                            CA -> Value.CA
                            MX -> Value.MX
                            GB -> Value.GB
                            DE -> Value.DE
                            FR -> Value.FR
                            IT -> Value.IT
                            ES -> Value.ES
                            PL -> Value.PL
                            NL -> Value.NL
                            SE -> Value.SE
                            NO -> Value.NO
                            DK -> Value.DK
                            FI -> Value.FI
                            CH -> Value.CH
                            AT -> Value.AT
                            BE -> Value.BE
                            IE -> Value.IE
                            PT -> Value.PT
                            GR -> Value.GR
                            CZ -> Value.CZ
                            HU -> Value.HU
                            RO -> Value.RO
                            BG -> Value.BG
                            SK -> Value.SK
                            SI -> Value.SI
                            HR -> Value.HR
                            EE -> Value.EE
                            LV -> Value.LV
                            LT -> Value.LT
                            LU -> Value.LU
                            MT -> Value.MT
                            CY -> Value.CY
                            IS -> Value.IS
                            LI -> Value.LI
                            MC -> Value.MC
                            SM -> Value.SM
                            VA -> Value.VA
                            JP -> Value.JP
                            KR -> Value.KR
                            CN -> Value.CN
                            HK -> Value.HK
                            TW -> Value.TW
                            SG -> Value.SG
                            AU -> Value.AU
                            NZ -> Value.NZ
                            IN -> Value.IN
                            TH -> Value.TH
                            MY -> Value.MY
                            PH -> Value.PH
                            ID -> Value.ID
                            VN -> Value.VN
                            AF -> Value.AF
                            BD -> Value.BD
                            BN -> Value.BN
                            KH -> Value.KH
                            LA -> Value.LA
                            LK -> Value.LK
                            MM -> Value.MM
                            NP -> Value.NP
                            PK -> Value.PK
                            FJ -> Value.FJ
                            PG -> Value.PG
                            AE -> Value.AE
                            SA -> Value.SA
                            IL -> Value.IL
                            TR -> Value.TR
                            IR -> Value.IR
                            IQ -> Value.IQ
                            JO -> Value.JO
                            KW -> Value.KW
                            LB -> Value.LB
                            OM -> Value.OM
                            QA -> Value.QA
                            BH -> Value.BH
                            YE -> Value.YE
                            SY -> Value.SY
                            ZA -> Value.ZA
                            EG -> Value.EG
                            MA -> Value.MA
                            NG -> Value.NG
                            KE -> Value.KE
                            DZ -> Value.DZ
                            AO -> Value.AO
                            BW -> Value.BW
                            ET -> Value.ET
                            GH -> Value.GH
                            CI -> Value.CI
                            LY -> Value.LY
                            MZ -> Value.MZ
                            RW -> Value.RW
                            SN -> Value.SN
                            TN -> Value.TN
                            UG -> Value.UG
                            ZM -> Value.ZM
                            ZW -> Value.ZW
                            TZ -> Value.TZ
                            MU -> Value.MU
                            SC -> Value.SC
                            BR -> Value.BR
                            AR -> Value.AR
                            CL -> Value.CL
                            CO -> Value.CO
                            PE -> Value.PE
                            VE -> Value.VE
                            EC -> Value.EC
                            UY -> Value.UY
                            PY -> Value.PY
                            BO -> Value.BO
                            CR -> Value.CR
                            CU -> Value.CU
                            DO -> Value.DO
                            GT -> Value.GT
                            HN -> Value.HN
                            JM -> Value.JM
                            NI -> Value.NI
                            PA -> Value.PA
                            SV -> Value.SV
                            TT -> Value.TT
                            BB -> Value.BB
                            BZ -> Value.BZ
                            GY -> Value.GY
                            SR -> Value.SR
                            RU -> Value.RU
                            UA -> Value.UA
                            BY -> Value.BY
                            KZ -> Value.KZ
                            UZ -> Value.UZ
                            AZ -> Value.AZ
                            GE -> Value.GE
                            AM -> Value.AM
                            MD -> Value.MD
                            MK -> Value.MK
                            AL -> Value.AL
                            BA -> Value.BA
                            RS -> Value.RS
                            ME -> Value.ME
                            XK -> Value.XK
                            MN -> Value.MN
                            KG -> Value.KG
                            TJ -> Value.TJ
                            TM -> Value.TM
                            else -> Value._UNKNOWN
                        }

                    fun known(): Known =
                        when (this) {
                            US -> Known.US
                            CA -> Known.CA
                            MX -> Known.MX
                            GB -> Known.GB
                            DE -> Known.DE
                            FR -> Known.FR
                            IT -> Known.IT
                            ES -> Known.ES
                            PL -> Known.PL
                            NL -> Known.NL
                            SE -> Known.SE
                            NO -> Known.NO
                            DK -> Known.DK
                            FI -> Known.FI
                            CH -> Known.CH
                            AT -> Known.AT
                            BE -> Known.BE
                            IE -> Known.IE
                            PT -> Known.PT
                            GR -> Known.GR
                            CZ -> Known.CZ
                            HU -> Known.HU
                            RO -> Known.RO
                            BG -> Known.BG
                            SK -> Known.SK
                            SI -> Known.SI
                            HR -> Known.HR
                            EE -> Known.EE
                            LV -> Known.LV
                            LT -> Known.LT
                            LU -> Known.LU
                            MT -> Known.MT
                            CY -> Known.CY
                            IS -> Known.IS
                            LI -> Known.LI
                            MC -> Known.MC
                            SM -> Known.SM
                            VA -> Known.VA
                            JP -> Known.JP
                            KR -> Known.KR
                            CN -> Known.CN
                            HK -> Known.HK
                            TW -> Known.TW
                            SG -> Known.SG
                            AU -> Known.AU
                            NZ -> Known.NZ
                            IN -> Known.IN
                            TH -> Known.TH
                            MY -> Known.MY
                            PH -> Known.PH
                            ID -> Known.ID
                            VN -> Known.VN
                            AF -> Known.AF
                            BD -> Known.BD
                            BN -> Known.BN
                            KH -> Known.KH
                            LA -> Known.LA
                            LK -> Known.LK
                            MM -> Known.MM
                            NP -> Known.NP
                            PK -> Known.PK
                            FJ -> Known.FJ
                            PG -> Known.PG
                            AE -> Known.AE
                            SA -> Known.SA
                            IL -> Known.IL
                            TR -> Known.TR
                            IR -> Known.IR
                            IQ -> Known.IQ
                            JO -> Known.JO
                            KW -> Known.KW
                            LB -> Known.LB
                            OM -> Known.OM
                            QA -> Known.QA
                            BH -> Known.BH
                            YE -> Known.YE
                            SY -> Known.SY
                            ZA -> Known.ZA
                            EG -> Known.EG
                            MA -> Known.MA
                            NG -> Known.NG
                            KE -> Known.KE
                            DZ -> Known.DZ
                            AO -> Known.AO
                            BW -> Known.BW
                            ET -> Known.ET
                            GH -> Known.GH
                            CI -> Known.CI
                            LY -> Known.LY
                            MZ -> Known.MZ
                            RW -> Known.RW
                            SN -> Known.SN
                            TN -> Known.TN
                            UG -> Known.UG
                            ZM -> Known.ZM
                            ZW -> Known.ZW
                            TZ -> Known.TZ
                            MU -> Known.MU
                            SC -> Known.SC
                            BR -> Known.BR
                            AR -> Known.AR
                            CL -> Known.CL
                            CO -> Known.CO
                            PE -> Known.PE
                            VE -> Known.VE
                            EC -> Known.EC
                            UY -> Known.UY
                            PY -> Known.PY
                            BO -> Known.BO
                            CR -> Known.CR
                            CU -> Known.CU
                            DO -> Known.DO
                            GT -> Known.GT
                            HN -> Known.HN
                            JM -> Known.JM
                            NI -> Known.NI
                            PA -> Known.PA
                            SV -> Known.SV
                            TT -> Known.TT
                            BB -> Known.BB
                            BZ -> Known.BZ
                            GY -> Known.GY
                            SR -> Known.SR
                            RU -> Known.RU
                            UA -> Known.UA
                            BY -> Known.BY
                            KZ -> Known.KZ
                            UZ -> Known.UZ
                            AZ -> Known.AZ
                            GE -> Known.GE
                            AM -> Known.AM
                            MD -> Known.MD
                            MK -> Known.MK
                            AL -> Known.AL
                            BA -> Known.BA
                            RS -> Known.RS
                            ME -> Known.ME
                            XK -> Known.XK
                            MN -> Known.MN
                            KG -> Known.KG
                            TJ -> Known.TJ
                            TM -> Known.TM
                            else -> throw SteelInvalidDataException("Unknown Country: $value")
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

                    fun validate(): Country = apply {
                        if (validated) {
                            return@apply
                        }

                        known()
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Country && value == other.value
                    }

                    override fun hashCode() = value.hashCode()

                    override fun toString() = value.toString()
                }

                class City @JsonCreator private constructor(private val value: JsonField<String>) :
                    Enum {

                    @com.fasterxml.jackson.annotation.JsonValue
                    fun _value(): JsonField<String> = value

                    companion object {

                        @JvmField val A_CORUNA = of("A_CORUNA")

                        @JvmField val ABIDJAN = of("ABIDJAN")

                        @JvmField val ABU_DHABI = of("ABU_DHABI")

                        @JvmField val ABUJA = of("ABUJA")

                        @JvmField val ACAPULCO_DE_JUAREZ = of("ACAPULCO_DE JUAREZ")

                        @JvmField val ACCRA = of("ACCRA")

                        @JvmField val ADANA = of("ADANA")

                        @JvmField val ADAPAZARI = of("ADAPAZARI")

                        @JvmField val ADDIS_ABABA = of("ADDIS_ABABA")

                        @JvmField val ADELAIDE = of("ADELAIDE")

                        @JvmField val AFYONKARAHISAR = of("AFYONKARAHISAR")

                        @JvmField val AGADIR = of("AGADIR")

                        @JvmField val AGUAS_LINDAS_DE_GOIAS = of("AGUAS_LINDAS DE GOIAS")

                        @JvmField val AGUASCALIENTES = of("AGUASCALIENTES")

                        @JvmField val AHMEDABAD = of("AHMEDABAD")

                        @JvmField val AIZAWL = of("AIZAWL")

                        @JvmField val AJMAN = of("AJMAN")

                        @JvmField val AKRON = of("AKRON")

                        @JvmField val AKSARAY = of("AKSARAY")

                        @JvmField val AL_AIN_CITY = of("AL_AIN CITY")

                        @JvmField val AL_MANSURAH = of("AL_MANSURAH")

                        @JvmField val AL_QATIF = of("AL_QATIF")

                        @JvmField val ALAJUELA = of("ALAJUELA")

                        @JvmField val ALBANY = of("ALBANY")

                        @JvmField val ALBUQUERQUE = of("ALBUQUERQUE")

                        @JvmField val ALEXANDRIA = of("ALEXANDRIA")

                        @JvmField val ALGIERS = of("ALGIERS")

                        @JvmField val ALICANTE = of("ALICANTE")

                        @JvmField val ALMADA = of("ALMADA")

                        @JvmField val ALMATY = of("ALMATY")

                        @JvmField val ALMERE_STAD = of("ALMERE_STAD")

                        @JvmField val ALVORADA = of("ALVORADA")

                        @JvmField val AMADORA = of("AMADORA")

                        @JvmField val AMASYA = of("AMASYA")

                        @JvmField val AMBATO = of("AMBATO")

                        @JvmField val AMERICANA = of("AMERICANA")

                        @JvmField val AMMAN = of("AMMAN")

                        @JvmField val AMSTERDAM = of("AMSTERDAM")

                        @JvmField val ANANINDEUA = of("ANANINDEUA")

                        @JvmField val ANAPOLIS = of("ANAPOLIS")

                        @JvmField val ANGELES_CITY = of("ANGELES_CITY")

                        @JvmField val ANGERS = of("ANGERS")

                        @JvmField val ANGRA_DOS_REIS = of("ANGRA_DOS REIS")

                        @JvmField val ANKARA = of("ANKARA")

                        @JvmField val ANTAKYA = of("ANTAKYA")

                        @JvmField val ANTALYA = of("ANTALYA")

                        @JvmField val ANTANANARIVO = of("ANTANANARIVO")

                        @JvmField val ANTIPOLO_CITY = of("ANTIPOLO_CITY")

                        @JvmField val ANTOFAGASTA = of("ANTOFAGASTA")

                        @JvmField val ANTWERP = of("ANTWERP")

                        @JvmField val APARECIDA_DE_GOIANIA = of("APARECIDA_DE GOIANIA")

                        @JvmField val APODACA = of("APODACA")

                        @JvmField val ARACAJU = of("ARACAJU")

                        @JvmField val ARACATUBA = of("ARACATUBA")

                        @JvmField val ARAD = of("ARAD")

                        @JvmField val ARAGUAINA = of("ARAGUAINA")

                        @JvmField val ARAPIRACA = of("ARAPIRACA")

                        @JvmField val ARARAQUARA = of("ARARAQUARA")

                        @JvmField val AREQUIPA = of("AREQUIPA")

                        @JvmField val ARICA = of("ARICA")

                        @JvmField val ARLINGTON = of("ARLINGTON")

                        @JvmField val ARYANAH = of("ARYANAH")

                        @JvmField val ASTANA = of("ASTANA")

                        @JvmField val ASUNCION = of("ASUNCION")

                        @JvmField val ASYUT = of("ASYUT")

                        @JvmField val ATAKUM = of("ATAKUM")

                        @JvmField val ATHENS = of("ATHENS")

                        @JvmField val ATIBAIA = of("ATIBAIA")

                        @JvmField val ATLANTA = of("ATLANTA")

                        @JvmField val AUBURN = of("AUBURN")

                        @JvmField val AUCKLAND = of("AUCKLAND")

                        @JvmField val AURORA = of("AURORA")

                        @JvmField val AUSTIN = of("AUSTIN")

                        @JvmField val AVELLANEDA = of("AVELLANEDA")

                        @JvmField val AYDIN = of("AYDIN")

                        @JvmField val AZCAPOTZALCO = of("AZCAPOTZALCO")

                        @JvmField val BACOLOD_CITY = of("BACOLOD_CITY")

                        @JvmField val BACOOR = of("BACOOR")

                        @JvmField val BAGHDAD = of("BAGHDAD")

                        @JvmField val BAGUIO_CITY = of("BAGUIO_CITY")

                        @JvmField val BAHIA_BLANCA = of("BAHIA_BLANCA")

                        @JvmField val BAKERSFIELD = of("BAKERSFIELD")

                        @JvmField val BAKU = of("BAKU")

                        @JvmField val BALIKESIR = of("BALIKESIR")

                        @JvmField val BALIKPAPAN = of("BALIKPAPAN")

                        @JvmField val BALNEARIO_CAMBORIU = of("BALNEARIO_CAMBORIU")

                        @JvmField val BALTIMORE = of("BALTIMORE")

                        @JvmField val BANDAR_LAMPUNG = of("BANDAR_LAMPUNG")

                        @JvmField val BANDAR_SERI_BEGAWAN = of("BANDAR_SERI BEGAWAN")

                        @JvmField val BANDUNG = of("BANDUNG")

                        @JvmField val BANGKOK = of("BANGKOK")

                        @JvmField val BANJA_LUKA = of("BANJA_LUKA")

                        @JvmField val BANJARMASIN = of("BANJARMASIN")

                        @JvmField val BARCELONA = of("BARCELONA")

                        @JvmField val BARI = of("BARI")

                        @JvmField val BARQUISIMETO = of("BARQUISIMETO")

                        @JvmField val BARRA_MANSA = of("BARRA_MANSA")

                        @JvmField val BARRANQUILLA = of("BARRANQUILLA")

                        @JvmField val BARUERI = of("BARUERI")

                        @JvmField val BATAM = of("BATAM")

                        @JvmField val BATANGAS = of("BATANGAS")

                        @JvmField val BATMAN = of("BATMAN")

                        @JvmField val BATNA_CITY = of("BATNA_CITY")

                        @JvmField val BATON_ROUGE = of("BATON_ROUGE")

                        @JvmField val BATUMI = of("BATUMI")

                        @JvmField val BAURU = of("BAURU")

                        @JvmField val BEIRUT = of("BEIRUT")

                        @JvmField val BEJAIA = of("BEJAIA")

                        @JvmField val BEKASI = of("BEKASI")

                        @JvmField val BELEM = of("BELEM")

                        @JvmField val BELFAST = of("BELFAST")

                        @JvmField val BELFORD_ROXO = of("BELFORD_ROXO")

                        @JvmField val BELGRADE = of("BELGRADE")

                        @JvmField val BELO_HORIZONTE = of("BELO_HORIZONTE")

                        @JvmField val BENGALURU = of("BENGALURU")

                        @JvmField val BENI_MELLAL = of("BENI_MELLAL")

                        @JvmField val BERAZATEGUI = of("BERAZATEGUI")

                        @JvmField val BERN = of("BERN")

                        @JvmField val BETIM = of("BETIM")

                        @JvmField val BHARATPUR = of("BHARATPUR")

                        @JvmField val BHOPAL = of("BHOPAL")

                        @JvmField val BHUBANESWAR = of("BHUBANESWAR")

                        @JvmField val BIALYSTOK = of("BIALYSTOK")

                        @JvmField val BIEN_HOA = of("BIEN_HOA")

                        @JvmField val BILBAO = of("BILBAO")

                        @JvmField val BILECIK = of("BILECIK")

                        @JvmField val BIRATNAGAR = of("BIRATNAGAR")

                        @JvmField val BIRMINGHAM = of("BIRMINGHAM")

                        @JvmField val BISHKEK = of("BISHKEK")

                        @JvmField val BIZERTE = of("BIZERTE")

                        @JvmField val BLIDA = of("BLIDA")

                        @JvmField val BLOEMFONTEIN = of("BLOEMFONTEIN")

                        @JvmField val BLOOMINGTON = of("BLOOMINGTON")

                        @JvmField val BLUMENAU = of("BLUMENAU")

                        @JvmField val BOA_VISTA = of("BOA_VISTA")

                        @JvmField val BOCHUM = of("BOCHUM")

                        @JvmField val BOGOR = of("BOGOR")

                        @JvmField val BOGOTA = of("BOGOTA")

                        @JvmField val BOISE = of("BOISE")

                        @JvmField val BOKSBURG = of("BOKSBURG")

                        @JvmField val BOLOGNA = of("BOLOGNA")

                        @JvmField val BOLU = of("BOLU")

                        @JvmField val BORDEAUX = of("BORDEAUX")

                        @JvmField val BOSTON = of("BOSTON")

                        @JvmField val BOTUCATU = of("BOTUCATU")

                        @JvmField val BRADFORD = of("BRADFORD")

                        @JvmField val BRAGA = of("BRAGA")

                        @JvmField val BRAGANCA_PAULISTA = of("BRAGANCA_PAULISTA")

                        @JvmField val BRAMPTON = of("BRAMPTON")

                        @JvmField val BRASILIA = of("BRASILIA")

                        @JvmField val BRASOV = of("BRASOV")

                        @JvmField val BRATISLAVA = of("BRATISLAVA")

                        @JvmField val BREMEN = of("BREMEN")

                        @JvmField val BRESCIA = of("BRESCIA")

                        @JvmField val BREST = of("BREST")

                        @JvmField val BRIDGETOWN = of("BRIDGETOWN")

                        @JvmField val BRISBANE = of("BRISBANE")

                        @JvmField val BRISTOL = of("BRISTOL")

                        @JvmField val BRNO = of("BRNO")

                        @JvmField val BROOKLYN = of("BROOKLYN")

                        @JvmField val BRUSSELS = of("BRUSSELS")

                        @JvmField val BUCARAMANGA = of("BUCARAMANGA")

                        @JvmField val BUCHAREST = of("BUCHAREST")

                        @JvmField val BUDAPEST = of("BUDAPEST")

                        @JvmField val BUENOS_AIRES = of("BUENOS_AIRES")

                        @JvmField val BUFFALO = of("BUFFALO")

                        @JvmField val BUK_GU = of("BUK_GU")

                        @JvmField val BUKHARA = of("BUKHARA")

                        @JvmField val BURGAS = of("BURGAS")

                        @JvmField val BURNABY = of("BURNABY")

                        @JvmField val BURSA = of("BURSA")

                        @JvmField val BUTUAN = of("BUTUAN")

                        @JvmField val BYDGOSZCZ = of("BYDGOSZCZ")

                        @JvmField val CABANATUAN_CITY = of("CABANATUAN_CITY")

                        @JvmField val CABO_FRIO = of("CABO_FRIO")

                        @JvmField val CABUYAO = of("CABUYAO")

                        @JvmField val CACHOEIRO_DE_ITAPEMIRIM = of("CACHOEIRO_DE ITAPEMIRIM")

                        @JvmField val CAGAYAN_DE_ORO = of("CAGAYAN_DE ORO")

                        @JvmField val CAGLIARI = of("CAGLIARI")

                        @JvmField val CAIRO = of("CAIRO")

                        @JvmField val CALAMBA = of("CALAMBA")

                        @JvmField val CALGARY = of("CALGARY")

                        @JvmField val CALOOCAN_CITY = of("CALOOCAN_CITY")

                        @JvmField val CAMACARI = of("CAMACARI")

                        @JvmField val CAMARAGIBE = of("CAMARAGIBE")

                        @JvmField val CAMPECHE = of("CAMPECHE")

                        @JvmField val CAMPINA_GRANDE = of("CAMPINA_GRANDE")

                        @JvmField val CAMPINAS = of("CAMPINAS")

                        @JvmField val CAMPO_GRANDE = of("CAMPO_GRANDE")

                        @JvmField val CAMPO_LARGO = of("CAMPO_LARGO")

                        @JvmField val CAMPOS_DOS_GOYTACAZES = of("CAMPOS_DOS GOYTACAZES")

                        @JvmField val CAN_THO = of("CAN_THO")

                        @JvmField val CANOAS = of("CANOAS")

                        @JvmField val CANTON = of("CANTON")

                        @JvmField val CAPE_TOWN = of("CAPE_TOWN")

                        @JvmField val CARACAS = of("CARACAS")

                        @JvmField val CARAGUATATUBA = of("CARAGUATATUBA")

                        @JvmField val CARAPICUIBA = of("CARAPICUIBA")

                        @JvmField val CARDIFF = of("CARDIFF")

                        @JvmField val CARIACICA = of("CARIACICA")

                        @JvmField val CARMONA = of("CARMONA")

                        @JvmField val CARTAGENA = of("CARTAGENA")

                        @JvmField val CARUARU = of("CARUARU")

                        @JvmField val CASABLANCA = of("CASABLANCA")

                        @JvmField val CASCAVEL = of("CASCAVEL")

                        @JvmField val CASEROS = of("CASEROS")

                        @JvmField val CASTANHAL = of("CASTANHAL")

                        @JvmField val CASTRIES = of("CASTRIES")

                        @JvmField val CATALAO = of("CATALAO")

                        @JvmField val CATAMARCA = of("CATAMARCA")

                        @JvmField val CATANDUVA = of("CATANDUVA")

                        @JvmField val CATANIA = of("CATANIA")

                        @JvmField val CAUCAIA = of("CAUCAIA")

                        @JvmField val CAXIAS_DO_SUL = of("CAXIAS_DO SUL")

                        @JvmField val CEBU_CITY = of("CEBU_CITY")

                        @JvmField val CENTRAL = of("CENTRAL")

                        @JvmField val CENTRO = of("CENTRO")

                        @JvmField val CENTURION = of("CENTURION")

                        @JvmField val CHAGUANAS = of("CHAGUANAS")

                        @JvmField val CHANDIGARH = of("CHANDIGARH")

                        @JvmField val CHANDLER = of("CHANDLER")

                        @JvmField val CHANG_HUA = of("CHANG_HUA")

                        @JvmField val CHAPECO = of("CHAPECO")

                        @JvmField val CHARLESTON = of("CHARLESTON")

                        @JvmField val CHARLOTTE = of("CHARLOTTE")

                        @JvmField val CHELYABINSK = of("CHELYABINSK")

                        @JvmField val CHENNAI = of("CHENNAI")

                        @JvmField val CHERKASY = of("CHERKASY")

                        @JvmField val CHERNIVTSI = of("CHERNIVTSI")

                        @JvmField val CHIA = of("CHIA")

                        @JvmField val CHIANG_MAI = of("CHIANG_MAI")

                        @JvmField val CHICLAYO = of("CHICLAYO")

                        @JvmField val CHIHUAHUA_CITY = of("CHIHUAHUA_CITY")

                        @JvmField val CHIMBOTE = of("CHIMBOTE")

                        @JvmField val CHISINAU = of("CHISINAU")

                        @JvmField val CHITTAGONG = of("CHITTAGONG")

                        @JvmField val CHRISTCHURCH = of("CHRISTCHURCH")

                        @JvmField val CINCINNATI = of("CINCINNATI")

                        @JvmField val CIREBON = of("CIREBON")

                        @JvmField val CITY_OF_MUNTINLUPA = of("CITY_OF MUNTINLUPA")

                        @JvmField val CIUDAD_DEL_ESTE = of("CIUDAD_DEL ESTE")

                        @JvmField val CIUDAD_GUAYANA = of("CIUDAD_GUAYANA")

                        @JvmField val CIUDAD_JUAREZ = of("CIUDAD_JUAREZ")

                        @JvmField val CIUDAD_NEZAHUALCOYOTL = of("CIUDAD_NEZAHUALCOYOTL")

                        @JvmField val CIUDAD_OBREGON = of("CIUDAD_OBREGON")

                        @JvmField val CLEVELAND = of("CLEVELAND")

                        @JvmField val CLUJ_NAPOCA = of("CLUJ_NAPOCA")

                        @JvmField val COCHABAMBA = of("COCHABAMBA")

                        @JvmField val COIMBATORE = of("COIMBATORE")

                        @JvmField val COIMBRA = of("COIMBRA")

                        @JvmField val COLOGNE = of("COLOGNE")

                        @JvmField val COLOMBO = of("COLOMBO")

                        @JvmField val COLORADO_SPRINGS = of("COLORADO_SPRINGS")

                        @JvmField val COLUMBIA = of("COLUMBIA")

                        @JvmField val COLUMBUS = of("COLUMBUS")

                        @JvmField val COMODORO_RIVADAVIA = of("COMODORO_RIVADAVIA")

                        @JvmField val CONCEPCION = of("CONCEPCION")

                        @JvmField val CONCORD = of("CONCORD")

                        @JvmField val CONSTANTA = of("CONSTANTA")

                        @JvmField val CONSTANTINE = of("CONSTANTINE")

                        @JvmField val CONTAGEM = of("CONTAGEM")

                        @JvmField val COPENHAGEN = of("COPENHAGEN")

                        @JvmField val CORDOBA = of("CORDOBA")

                        @JvmField val CORRIENTES = of("CORRIENTES")

                        @JvmField val CORUM = of("CORUM")

                        @JvmField val COTIA = of("COTIA")

                        @JvmField val COVENTRY = of("COVENTRY")

                        @JvmField val CRAIOVA = of("CRAIOVA")

                        @JvmField val CRICIUMA = of("CRICIUMA")

                        @JvmField val CROYDON = of("CROYDON")

                        @JvmField val CUAUTITLAN_IZCALLI = of("CUAUTITLAN_IZCALLI")

                        @JvmField val CUCUTA = of("CUCUTA")

                        @JvmField val CUENCA = of("CUENCA")

                        @JvmField val CUERNAVACA = of("CUERNAVACA")

                        @JvmField val CUIABA = of("CUIABA")

                        @JvmField val CULIACAN = of("CULIACAN")

                        @JvmField val CURITIBA = of("CURITIBA")

                        @JvmField val CUSCO = of("CUSCO")

                        @JvmField val DA_NANG = of("DA_NANG")

                        @JvmField val DAGUPAN = of("DAGUPAN")

                        @JvmField val DAKAR = of("DAKAR")

                        @JvmField val DALLAS = of("DALLAS")

                        @JvmField val DAMIETTA = of("DAMIETTA")

                        @JvmField val DAMMAM = of("DAMMAM")

                        @JvmField val DAR_ES_SALAAM = of("DAR_ES SALAAM")

                        @JvmField val DASMARINAS = of("DASMARINAS")

                        @JvmField val DAVAO_CITY = of("DAVAO_CITY")

                        @JvmField val DAYTON = of("DAYTON")

                        @JvmField val DEBRECEN = of("DEBRECEN")

                        @JvmField val DECATUR = of("DECATUR")

                        @JvmField val DEHRADUN = of("DEHRADUN")

                        @JvmField val DELHI = of("DELHI")

                        @JvmField val DENIZLI = of("DENIZLI")

                        @JvmField val DENPASAR = of("DENPASAR")

                        @JvmField val DENVER = of("DENVER")

                        @JvmField val DEPOK = of("DEPOK")

                        @JvmField val DERBY = of("DERBY")

                        @JvmField val DETROIT = of("DETROIT")

                        @JvmField val DHAKA = of("DHAKA")

                        @JvmField val DIADEMA = of("DIADEMA")

                        @JvmField val DIVINOPOLIS = of("DIVINOPOLIS")

                        @JvmField val DIYARBAKIR = of("DIYARBAKIR")

                        @JvmField val DJELFA = of("DJELFA")

                        @JvmField val DNIPRO = of("DNIPRO")

                        @JvmField val DOHA = of("DOHA")

                        @JvmField val DORTMUND = of("DORTMUND")

                        @JvmField val DOURADOS = of("DOURADOS")

                        @JvmField val DRESDEN = of("DRESDEN")

                        @JvmField val DUBAI = of("DUBAI")

                        @JvmField val DUBLIN = of("DUBLIN")

                        @JvmField val DUEZCE = of("DUEZCE")

                        @JvmField val DUISBURG = of("DUISBURG")

                        @JvmField val DUQUE_DE_CAXIAS = of("DUQUE_DE CAXIAS")

                        @JvmField val DURANGO = of("DURANGO")

                        @JvmField val DURBAN = of("DURBAN")

                        @JvmField val DUSSELDORF = of("DUSSELDORF")

                        @JvmField val ECATEPEC = of("ECATEPEC")

                        @JvmField val EDINBURGH = of("EDINBURGH")

                        @JvmField val EDIRNE = of("EDIRNE")

                        @JvmField val EDMONTON = of("EDMONTON")

                        @JvmField val EL_JADIDA = of("EL_JADIDA")

                        @JvmField val EL_PASO = of("EL_PASO")

                        @JvmField val ELAZIG = of("ELAZIG")

                        @JvmField val EMBU = of("EMBU")

                        @JvmField val ENSENADA = of("ENSENADA")

                        @JvmField val ERBIL = of("ERBIL")

                        @JvmField val ERZURUM = of("ERZURUM")

                        @JvmField val ESKISEHIR = of("ESKISEHIR")

                        @JvmField val ESPOO = of("ESPOO")

                        @JvmField val ESSEN = of("ESSEN")

                        @JvmField val FAISALABAD = of("FAISALABAD")

                        @JvmField val FAYETTEVILLE = of("FAYETTEVILLE")

                        @JvmField val FAZENDA_RIO_GRANDE = of("FAZENDA_RIO GRANDE")

                        @JvmField val FEIRA_DE_SANTANA = of("FEIRA_DE SANTANA")

                        @JvmField val FES = of("FES")

                        @JvmField val FLORENCE = of("FLORENCE")

                        @JvmField val FLORENCIO_VARELA = of("FLORENCIO_VARELA")

                        @JvmField val FLORIANOPOLIS = of("FLORIANOPOLIS")

                        @JvmField val FONTANA = of("FONTANA")

                        @JvmField val FORMOSA = of("FORMOSA")

                        @JvmField val FORT_LAUDERDALE = of("FORT_LAUDERDALE")

                        @JvmField val FORT_WAYNE = of("FORT_WAYNE")

                        @JvmField val FORT_WORTH = of("FORT_WORTH")

                        @JvmField val FORTALEZA = of("FORTALEZA")

                        @JvmField val FOZ_DO_IGUACU = of("FOZ_DO IGUACU")

                        @JvmField val FRANCA = of("FRANCA")

                        @JvmField val FRANCISCO_MORATO = of("FRANCISCO_MORATO")

                        @JvmField val FRANCO_DA_ROCHA = of("FRANCO_DA ROCHA")

                        @JvmField val FRANKFURT_AM_MAIN = of("FRANKFURT_AM MAIN")

                        @JvmField val FREDERICKSBURG = of("FREDERICKSBURG")

                        @JvmField val FRESNO = of("FRESNO")

                        @JvmField val FUNCHAL = of("FUNCHAL")

                        @JvmField val GABORONE = of("GABORONE")

                        @JvmField val GAINESVILLE = of("GAINESVILLE")

                        @JvmField val GALATI = of("GALATI")

                        @JvmField val GANGNAM_GU = of("GANGNAM_GU")

                        @JvmField val GARANHUNS = of("GARANHUNS")

                        @JvmField val GATINEAU = of("GATINEAU")

                        @JvmField val GAZIANTEP = of("GAZIANTEP")

                        @JvmField val GDANSK = of("GDANSK")

                        @JvmField val GDYNIA = of("GDYNIA")

                        @JvmField val GENERAL_TRIAS = of("GENERAL_TRIAS")

                        @JvmField val GENEVA = of("GENEVA")

                        @JvmField val GENOA = of("GENOA")

                        @JvmField val GEORGE_TOWN = of("GEORGE_TOWN")

                        @JvmField val GEORGETOWN = of("GEORGETOWN")

                        @JvmField val GHAZIABAD = of("GHAZIABAD")

                        @JvmField val GHENT = of("GHENT")

                        @JvmField val GIJON = of("GIJON")

                        @JvmField val GIRESUN = of("GIRESUN")

                        @JvmField val GIZA = of("GIZA")

                        @JvmField val GLASGOW = of("GLASGOW")

                        @JvmField val GLENDALE = of("GLENDALE")

                        @JvmField val GLIWICE = of("GLIWICE")

                        @JvmField val GOIANIA = of("GOIANIA")

                        @JvmField val GOMEL = of("GOMEL")

                        @JvmField val GOTHENBURG = of("GOTHENBURG")

                        @JvmField val GOVERNADOR_VALADARES = of("GOVERNADOR_VALADARES")

                        @JvmField val GOYANG_SI = of("GOYANG_SI")

                        @JvmField val GRANADA = of("GRANADA")

                        @JvmField val GRAND_RAPIDS = of("GRAND_RAPIDS")

                        @JvmField val GRAVATAI = of("GRAVATAI")

                        @JvmField val GRAZ = of("GRAZ")

                        @JvmField val GREENSBORO = of("GREENSBORO")

                        @JvmField val GREENVILLE = of("GREENVILLE")

                        @JvmField val GUADALAJARA = of("GUADALAJARA")

                        @JvmField val GUADALUPE = of("GUADALUPE")

                        @JvmField val GUANGZHOU = of("GUANGZHOU")

                        @JvmField val GUARAPUAVA = of("GUARAPUAVA")

                        @JvmField val GUARATINGUETA = of("GUARATINGUETA")

                        @JvmField val GUARUJA = of("GUARUJA")

                        @JvmField val GUARULHOS = of("GUARULHOS")

                        @JvmField val GUATEMALA_CITY = of("GUATEMALA_CITY")

                        @JvmField val GUAYAQUIL = of("GUAYAQUIL")

                        @JvmField val GUJRANWALA = of("GUJRANWALA")

                        @JvmField val GURUGRAM = of("GURUGRAM")

                        @JvmField val GUSTAVO_ADOLFO_MADERO = of("GUSTAVO_ADOLFO MADERO")

                        @JvmField val GUWAHATI = of("GUWAHATI")

                        @JvmField val GWANAK_GU = of("GWANAK_GU")

                        @JvmField val HACKNEY = of("HACKNEY")

                        @JvmField val HAIFA = of("HAIFA")

                        @JvmField val HAIPHONG = of("HAIPHONG")

                        @JvmField val HAMBURG = of("HAMBURG")

                        @JvmField val HAMILTON = of("HAMILTON")

                        @JvmField val HANOI = of("HANOI")

                        @JvmField val HANOVER = of("HANOVER")

                        @JvmField val HARARE = of("HARARE")

                        @JvmField val HAVANA = of("HAVANA")

                        @JvmField val HELSINKI = of("HELSINKI")

                        @JvmField val HENDERSON = of("HENDERSON")

                        @JvmField val HEREDIA = of("HEREDIA")

                        @JvmField val HERMOSILLO = of("HERMOSILLO")

                        @JvmField val HIALEAH = of("HIALEAH")

                        @JvmField val HO_CHI_MINH_CITY = of("HO_CHI MINH CITY")

                        @JvmField val HOLLYWOOD = of("HOLLYWOOD")

                        @JvmField val HOLON = of("HOLON")

                        @JvmField val HONOLULU = of("HONOLULU")

                        @JvmField val HORTOLANDIA = of("HORTOLANDIA")

                        @JvmField val HRODNA = of("HRODNA")

                        @JvmField val HSINCHU = of("HSINCHU")

                        @JvmField val HUANCAYO = of("HUANCAYO")

                        @JvmField val HUANUCO = of("HUANUCO")

                        @JvmField val HULL = of("HULL")

                        @JvmField val HURLINGHAM = of("HURLINGHAM")

                        @JvmField val HYDERABAD = of("HYDERABAD")

                        @JvmField val IASI = of("IASI")

                        @JvmField val IBAGUE = of("IBAGUE")

                        @JvmField val ICA = of("ICA")

                        @JvmField val ILAM = of("ILAM")

                        @JvmField val ILFORD = of("ILFORD")

                        @JvmField val ILIGAN = of("ILIGAN")

                        @JvmField val ILOILO_CITY = of("ILOILO_CITY")

                        @JvmField val IMPERATRIZ = of("IMPERATRIZ")

                        @JvmField val IMUS = of("IMUS")

                        @JvmField val INCHEON = of("INCHEON")

                        @JvmField val INDAIATUBA = of("INDAIATUBA")

                        @JvmField val INDIANAPOLIS = of("INDIANAPOLIS")

                        @JvmField val INDORE = of("INDORE")

                        @JvmField val IPATINGA = of("IPATINGA")

                        @JvmField val IPOH = of("IPOH")

                        @JvmField val IQUIQUE = of("IQUIQUE")

                        @JvmField val IRVINE = of("IRVINE")

                        @JvmField val ISIDRO_CASANOVA = of("ISIDRO_CASANOVA")

                        @JvmField val ISLAMABAD = of("ISLAMABAD")

                        @JvmField val ISLINGTON = of("ISLINGTON")

                        @JvmField val ISMAILIA = of("ISMAILIA")

                        @JvmField val ISPARTA = of("ISPARTA")

                        @JvmField val ISTANBUL = of("ISTANBUL")

                        @JvmField val ITABORAI = of("ITABORAI")

                        @JvmField val ITABUNA = of("ITABUNA")

                        @JvmField val ITAJAI = of("ITAJAI")

                        @JvmField val ITANHAEM = of("ITANHAEM")

                        @JvmField val ITAPEVI = of("ITAPEVI")

                        @JvmField val ITAQUAQUECETUBA = of("ITAQUAQUECETUBA")

                        @JvmField val ITUZAINGO = of("ITUZAINGO")

                        @JvmField val IZMIR = of("IZMIR")

                        @JvmField val IZTAPALAPA = of("IZTAPALAPA")

                        @JvmField val JABOATAO_DOS_GUARARAPES = of("JABOATAO_DOS GUARARAPES")

                        @JvmField val JACAREI = of("JACAREI")

                        @JvmField val JACKSON = of("JACKSON")

                        @JvmField val JACKSONVILLE = of("JACKSONVILLE")

                        @JvmField val JAIPUR = of("JAIPUR")

                        @JvmField val JAKARTA = of("JAKARTA")

                        @JvmField val JARAGUA_DO_SUL = of("JARAGUA_DO SUL")

                        @JvmField val JAU = of("JAU")

                        @JvmField val JEDDAH = of("JEDDAH")

                        @JvmField val JEMBER = of("JEMBER")

                        @JvmField val JERUSALEM = of("JERUSALEM")

                        @JvmField val JOAO_MONLEVADE = of("JOAO_MONLEVADE")

                        @JvmField val JOAO_PESSOA = of("JOAO_PESSOA")

                        @JvmField val JODHPUR = of("JODHPUR")

                        @JvmField val JOHANNESBURG = of("JOHANNESBURG")

                        @JvmField val JOHOR_BAHRU = of("JOHOR_BAHRU")

                        @JvmField val JOINVILLE = of("JOINVILLE")

                        @JvmField val JOSE_C_PAZ = of("JOSE_C PAZ")

                        @JvmField val JOSE_MARIA_EZEIZA = of("JOSE_MARIA EZEIZA")

                        @JvmField val JUAREZ = of("JUAREZ")

                        @JvmField val JUAZEIRO_DO_NORTE = of("JUAZEIRO_DO NORTE")

                        @JvmField val JUIZ_DE_FORA = of("JUIZ_DE FORA")

                        @JvmField val JUNDIAI = of("JUNDIAI")

                        @JvmField val KAHRAMANMARAS = of("KAHRAMANMARAS")

                        @JvmField val KAMPALA = of("KAMPALA")

                        @JvmField val KANPUR = of("KANPUR")

                        @JvmField val KANSAS_CITY = of("KANSAS_CITY")

                        @JvmField val KAOHSIUNG_CITY = of("KAOHSIUNG_CITY")

                        @JvmField val KARABUK = of("KARABUK")

                        @JvmField val KARACHI = of("KARACHI")

                        @JvmField val KARLSRUHE = of("KARLSRUHE")

                        @JvmField val KARNAL = of("KARNAL")

                        @JvmField val KASKI = of("KASKI")

                        @JvmField val KASTAMONU = of("KASTAMONU")

                        @JvmField val KATHMANDU = of("KATHMANDU")

                        @JvmField val KATOWICE = of("KATOWICE")

                        @JvmField val KATSINA = of("KATSINA")

                        @JvmField val KATY = of("KATY")

                        @JvmField val KAUNAS = of("KAUNAS")

                        @JvmField val KAYSERI = of("KAYSERI")

                        @JvmField val KAZAN = of("KAZAN")

                        @JvmField val KECSKEMET = of("KECSKEMET")

                        @JvmField val KEDIRI = of("KEDIRI")

                        @JvmField val KENITRA = of("KENITRA")

                        @JvmField val KHARKIV = of("KHARKIV")

                        @JvmField val KHMELNYTSKYI = of("KHMELNYTSKYI")

                        @JvmField val KHON_KAEN = of("KHON_KAEN")

                        @JvmField val KIELCE = of("KIELCE")

                        @JvmField val KIGALI = of("KIGALI")

                        @JvmField val KINGSTON = of("KINGSTON")

                        @JvmField val KIRKLARELI = of("KIRKLARELI")

                        @JvmField val KISSIMMEE = of("KISSIMMEE")

                        @JvmField val KITCHENER = of("KITCHENER")

                        @JvmField val KLAIPEDA = of("KLAIPEDA")

                        @JvmField val KNOXVILLE = of("KNOXVILLE")

                        @JvmField val KOCHI = of("KOCHI")

                        @JvmField val KOLKATA = of("KOLKATA")

                        @JvmField val KOLLAM = of("KOLLAM")

                        @JvmField val KONYA = of("KONYA")

                        @JvmField val KOSEKOY = of("KOSEKOY")

                        @JvmField val KOSICE = of("KOSICE")

                        @JvmField val KOTA_KINABALU = of("KOTA_KINABALU")

                        @JvmField val KOZHIKODE = of("KOZHIKODE")

                        @JvmField val KRAKOW = of("KRAKOW")

                        @JvmField val KRASNODAR = of("KRASNODAR")

                        @JvmField val KRYVYI_RIH = of("KRYVYI_RIH")

                        @JvmField val KUALA_LUMPUR = of("KUALA_LUMPUR")

                        @JvmField val KUCHING = of("KUCHING")

                        @JvmField val KUTAHYA = of("KUTAHYA")

                        @JvmField val KUTAISI = of("KUTAISI")

                        @JvmField val KUWAIT_CITY = of("KUWAIT_CITY")

                        @JvmField val KYIV = of("KYIV")

                        @JvmField val LA_PAZ = of("LA_PAZ")

                        @JvmField val LA_PLATA = of("LA_PLATA")

                        @JvmField val LA_RIOJA = of("LA_RIOJA")

                        @JvmField val LA_SERENA = of("LA_SERENA")

                        @JvmField val LAFAYETTE = of("LAFAYETTE")

                        @JvmField val LAFERRERE = of("LAFERRERE")

                        @JvmField val LAGES = of("LAGES")

                        @JvmField val LAGOS = of("LAGOS")

                        @JvmField val LAHORE = of("LAHORE")

                        @JvmField val LAHUG = of("LAHUG")

                        @JvmField val LAKE_WORTH = of("LAKE_WORTH")

                        @JvmField val LAKELAND = of("LAKELAND")

                        @JvmField val LANCASTER = of("LANCASTER")

                        @JvmField val LANUS = of("LANUS")

                        @JvmField val LAS_PALMAS_DE_GRAN_CANARIA = of("LAS_PALMAS DE GRAN CANARIA")

                        @JvmField val LAS_PINAS = of("LAS_PINAS")

                        @JvmField val LAS_VEGAS = of("LAS_VEGAS")

                        @JvmField val LAUSANNE = of("LAUSANNE")

                        @JvmField val LAVAL = of("LAVAL")

                        @JvmField val LAWRENCEVILLE = of("LAWRENCEVILLE")

                        @JvmField val LE_MANS = of("LE_MANS")

                        @JvmField val LEEDS = of("LEEDS")

                        @JvmField val LEICESTER = of("LEICESTER")

                        @JvmField val LEIPZIG = of("LEIPZIG")

                        @JvmField val LEON = of("LEON")

                        @JvmField val LEXINGTON = of("LEXINGTON")

                        @JvmField val LIBREVILLE = of("LIBREVILLE")

                        @JvmField val LIEGE = of("LIEGE")

                        @JvmField val LILLE = of("LILLE")

                        @JvmField val LIMA = of("LIMA")

                        @JvmField val LIMASSOL = of("LIMASSOL")

                        @JvmField val LIMEIRA = of("LIMEIRA")

                        @JvmField val LINCOLN = of("LINCOLN")

                        @JvmField val LINHARES = of("LINHARES")

                        @JvmField val LIPA_CITY = of("LIPA_CITY")

                        @JvmField val LISBON = of("LISBON")

                        @JvmField val LIVERPOOL = of("LIVERPOOL")

                        @JvmField val LJUBLJANA = of("LJUBLJANA")

                        @JvmField val LODZ = of("LODZ")

                        @JvmField val LOJA = of("LOJA")

                        @JvmField val LOMAS_DE_ZAMORA = of("LOMAS_DE ZAMORA")

                        @JvmField val LOME = of("LOME")

                        @JvmField val LONDRINA = of("LONDRINA")

                        @JvmField val LONG_BEACH = of("LONG_BEACH")

                        @JvmField val LONGUEUIL = of("LONGUEUIL")

                        @JvmField val LOUISVILLE = of("LOUISVILLE")

                        @JvmField val LUANDA = of("LUANDA")

                        @JvmField val LUBLIN = of("LUBLIN")

                        @JvmField val LUCENA_CITY = of("LUCENA_CITY")

                        @JvmField val LUCKNOW = of("LUCKNOW")

                        @JvmField val LUDHIANA = of("LUDHIANA")

                        @JvmField val LUSAKA = of("LUSAKA")

                        @JvmField val LUXEMBOURG = of("LUXEMBOURG")

                        @JvmField val LUZIANIA = of("LUZIANIA")

                        @JvmField val LVIV = of("LVIV")

                        @JvmField val LYON = of("LYON")

                        @JvmField val MABALACAT = of("MABALACAT")

                        @JvmField val MACAE = of("MACAE")

                        @JvmField val MACAO = of("MACAO")

                        @JvmField val MACAPA = of("MACAPA")

                        @JvmField val MACEIO = of("MACEIO")

                        @JvmField val MACHALA = of("MACHALA")

                        @JvmField val MADISON = of("MADISON")

                        @JvmField val MADRID = of("MADRID")

                        @JvmField val MAGE = of("MAGE")

                        @JvmField val MAGELANG = of("MAGELANG")

                        @JvmField val MAGNESIA_AD_SIPYLUM = of("MAGNESIA_AD SIPYLUM")

                        @JvmField val MAKASSAR = of("MAKASSAR")

                        @JvmField val MAKATI_CITY = of("MAKATI_CITY")

                        @JvmField val MALABON = of("MALABON")

                        @JvmField val MALAGA = of("MALAGA")

                        @JvmField val MALANG = of("MALANG")

                        @JvmField val MALAPPURAM = of("MALAPPURAM")

                        @JvmField val MALDONADO = of("MALDONADO")

                        @JvmField val MALE = of("MALE")

                        @JvmField val MALMO = of("MALMO")

                        @JvmField val MANADO = of("MANADO")

                        @JvmField val MANAGUA = of("MANAGUA")

                        @JvmField val MANAMA = of("MANAMA")

                        @JvmField val MANAUS = of("MANAUS")

                        @JvmField val MANCHESTER = of("MANCHESTER")

                        @JvmField val MANDALUYONG_CITY = of("MANDALUYONG_CITY")

                        @JvmField val MANILA = of("MANILA")

                        @JvmField val MANIZALES = of("MANIZALES")

                        @JvmField val MANNHEIM = of("MANNHEIM")

                        @JvmField val MAPUTO = of("MAPUTO")

                        @JvmField val MAR_DEL_PLATA = of("MAR_DEL PLATA")

                        @JvmField val MARABA = of("MARABA")

                        @JvmField val MARACAIBO = of("MARACAIBO")

                        @JvmField val MARACANAU = of("MARACANAU")

                        @JvmField val MARACAY = of("MARACAY")

                        @JvmField val MARDIN = of("MARDIN")

                        @JvmField val MARIBOR = of("MARIBOR")

                        @JvmField val MARICA = of("MARICA")

                        @JvmField val MARIETTA = of("MARIETTA")

                        @JvmField val MARIKINA_CITY = of("MARIKINA_CITY")

                        @JvmField val MARILIA = of("MARILIA")

                        @JvmField val MARINGA = of("MARINGA")

                        @JvmField val MARRAKESH = of("MARRAKESH")

                        @JvmField val MARSEILLE = of("MARSEILLE")

                        @JvmField val MAUA = of("MAUA")

                        @JvmField val MAZATLAN = of("MAZATLAN")

                        @JvmField val MEDAN = of("MEDAN")

                        @JvmField val MEDELLIN = of("MEDELLIN")

                        @JvmField val MEDINA = of("MEDINA")

                        @JvmField val MEERUT = of("MEERUT")

                        @JvmField val MEKNES = of("MEKNES")

                        @JvmField val MELBOURNE = of("MELBOURNE")

                        @JvmField val MEMPHIS = of("MEMPHIS")

                        @JvmField val MENDOZA = of("MENDOZA")

                        @JvmField val MERIDA = of("MERIDA")

                        @JvmField val MERKEZ = of("MERKEZ")

                        @JvmField val MERLO = of("MERLO")

                        @JvmField val MERSIN = of("MERSIN")

                        @JvmField val MESA = of("MESA")

                        @JvmField val MEXICALI = of("MEXICALI")

                        @JvmField val MEXICO_CITY = of("MEXICO_CITY")

                        @JvmField val MILAN = of("MILAN")

                        @JvmField val MILTON_KEYNES = of("MILTON_KEYNES")

                        @JvmField val MILWAUKEE = of("MILWAUKEE")

                        @JvmField val MINNEAPOLIS = of("MINNEAPOLIS")

                        @JvmField val MINSK = of("MINSK")

                        @JvmField val MISKOLC = of("MISKOLC")

                        @JvmField val MISSISSAUGA = of("MISSISSAUGA")

                        @JvmField val MOGI_DAS_CRUZES = of("MOGI_DAS CRUZES")

                        @JvmField val MOHALI = of("MOHALI")

                        @JvmField val MONROE = of("MONROE")

                        @JvmField val MONTE_GRANDE = of("MONTE_GRANDE")

                        @JvmField val MONTEGO_BAY = of("MONTEGO_BAY")

                        @JvmField val MONTERREY = of("MONTERREY")

                        @JvmField val MONTES_CLAROS = of("MONTES_CLAROS")

                        @JvmField val MONTEVIDEO = of("MONTEVIDEO")

                        @JvmField val MONTGOMERY = of("MONTGOMERY")

                        @JvmField val MONTPELLIER = of("MONTPELLIER")

                        @JvmField val MONTREAL = of("MONTREAL")

                        @JvmField val MORELIA = of("MORELIA")

                        @JvmField val MORENO = of("MORENO")

                        @JvmField val MORON = of("MORON")

                        @JvmField val MOSSORO = of("MOSSORO")

                        @JvmField val MUGLA = of("MUGLA")

                        @JvmField val MULTAN = of("MULTAN")

                        @JvmField val MUMBAI = of("MUMBAI")

                        @JvmField val MUNICH = of("MUNICH")

                        @JvmField val MURCIA = of("MURCIA")

                        @JvmField val MUSCAT = of("MUSCAT")

                        @JvmField val MUZAFFARGARH = of("MUZAFFARGARH")

                        @JvmField val MYKOLAYIV = of("MYKOLAYIV")

                        @JvmField val NAALDWIJK = of("NAALDWIJK")

                        @JvmField val NAGA = of("NAGA")

                        @JvmField val NAGPUR = of("NAGPUR")

                        @JvmField val NAIROBI = of("NAIROBI")

                        @JvmField val NANTES = of("NANTES")

                        @JvmField val NAPLES = of("NAPLES")

                        @JvmField val NASHVILLE = of("NASHVILLE")

                        @JvmField val NASSAU = of("NASSAU")

                        @JvmField val NASUGBU = of("NASUGBU")

                        @JvmField val NATAL = of("NATAL")

                        @JvmField val NAUCALPAN = of("NAUCALPAN")

                        @JvmField val NAVI_MUMBAI = of("NAVI_MUMBAI")

                        @JvmField val NEIVA = of("NEIVA")

                        @JvmField val NEUQUEN = of("NEUQUEN")

                        @JvmField val NEVSEHIR = of("NEVSEHIR")

                        @JvmField val NEW_DELHI = of("NEW_DELHI")

                        @JvmField val NEW_ORLEANS = of("NEW_ORLEANS")

                        @JvmField val NEW_TAIPEI = of("NEW_TAIPEI")

                        @JvmField val NEWARK = of("NEWARK")

                        @JvmField val NEWCASTLE_UPON_TYNE = of("NEWCASTLE_UPON TYNE")

                        @JvmField val NHA_TRANG = of("NHA_TRANG")

                        @JvmField val NICE = of("NICE")

                        @JvmField val NICOSIA = of("NICOSIA")

                        @JvmField val NILOPOLIS = of("NILOPOLIS")

                        @JvmField val NIS = of("NIS")

                        @JvmField val NITEROI = of("NITEROI")

                        @JvmField val NITRA = of("NITRA")

                        @JvmField val NIZHNIY_NOVGOROD = of("NIZHNIY_NOVGOROD")

                        @JvmField val NOGALES = of("NOGALES")

                        @JvmField val NOIDA = of("NOIDA")

                        @JvmField val NORTHAMPTON = of("NORTHAMPTON")

                        @JvmField val NORWICH = of("NORWICH")

                        @JvmField val NOTTINGHAM = of("NOTTINGHAM")

                        @JvmField val NOVA_FRIBURGO = of("NOVA_FRIBURGO")

                        @JvmField val NOVA_IGUACU = of("NOVA_IGUACU")

                        @JvmField val NOVI_SAD = of("NOVI_SAD")

                        @JvmField val NOVO_HAMBURGO = of("NOVO_HAMBURGO")

                        @JvmField val NOVOSIBIRSK = of("NOVOSIBIRSK")

                        @JvmField val NUREMBERG = of("NUREMBERG")

                        @JvmField val OAKLAND = of("OAKLAND")

                        @JvmField val OAXACA_CITY = of("OAXACA_CITY")

                        @JvmField val ODESA = of("ODESA")

                        @JvmField val OKLAHOMA_CITY = of("OKLAHOMA_CITY")

                        @JvmField val OLINDA = of("OLINDA")

                        @JvmField val OLOMOUC = of("OLOMOUC")

                        @JvmField val OLONGAPO_CITY = of("OLONGAPO_CITY")

                        @JvmField val OLSZTYN = of("OLSZTYN")

                        @JvmField val OMAHA = of("OMAHA")

                        @JvmField val ORADEA = of("ORADEA")

                        @JvmField val ORAN = of("ORAN")

                        @JvmField val ORDU = of("ORDU")

                        @JvmField val ORLANDO = of("ORLANDO")

                        @JvmField val OSASCO = of("OSASCO")

                        @JvmField val OSLO = of("OSLO")

                        @JvmField val OSMANIYE = of("OSMANIYE")

                        @JvmField val OSTRAVA = of("OSTRAVA")

                        @JvmField val OTTAWA = of("OTTAWA")

                        @JvmField val OUJDA = of("OUJDA")

                        @JvmField val OURINHOS = of("OURINHOS")

                        @JvmField val PACHUCA = of("PACHUCA")

                        @JvmField val PADOVA = of("PADOVA")

                        @JvmField val PALAKKAD = of("PALAKKAD")

                        @JvmField val PALEMBANG = of("PALEMBANG")

                        @JvmField val PALERMO = of("PALERMO")

                        @JvmField val PALHOCA = of("PALHOCA")

                        @JvmField val PALMA = of("PALMA")

                        @JvmField val PALMAS = of("PALMAS")

                        @JvmField val PANAMA_CITY = of("PANAMA_CITY")

                        @JvmField val PARAMARIBO = of("PARAMARIBO")

                        @JvmField val PARANA = of("PARANA")

                        @JvmField val PARANAGUA = of("PARANAGUA")

                        @JvmField val PARANAQUE_CITY = of("PARANAQUE_CITY")

                        @JvmField val PARAUAPEBAS = of("PARAUAPEBAS")

                        @JvmField val PARIS = of("PARIS")

                        @JvmField val PARNAIBA = of("PARNAIBA")

                        @JvmField val PARNAMIRIM = of("PARNAMIRIM")

                        @JvmField val PASSO_FUNDO = of("PASSO_FUNDO")

                        @JvmField val PASTO = of("PASTO")

                        @JvmField val PATAN = of("PATAN")

                        @JvmField val PATNA = of("PATNA")

                        @JvmField val PATOS_DE_MINAS = of("PATOS_DE MINAS")

                        @JvmField val PAULISTA = of("PAULISTA")

                        @JvmField val PECS = of("PECS")

                        @JvmField val PEKANBARU = of("PEKANBARU")

                        @JvmField val PELOTAS = of("PELOTAS")

                        @JvmField val PEORIA = of("PEORIA")

                        @JvmField val PEREIRA = of("PEREIRA")

                        @JvmField val PERM = of("PERM")

                        @JvmField val PERTH = of("PERTH")

                        @JvmField val PESCARA = of("PESCARA")

                        @JvmField val PESHAWAR = of("PESHAWAR")

                        @JvmField val PETAH_TIKVA = of("PETAH_TIKVA")

                        @JvmField val PETALING_JAYA = of("PETALING_JAYA")

                        @JvmField val PETROLINA = of("PETROLINA")

                        @JvmField val PETROPOLIS = of("PETROPOLIS")

                        @JvmField val PHILADELPHIA = of("PHILADELPHIA")

                        @JvmField val PHNOM_PENH = of("PHNOM_PENH")

                        @JvmField val PHOENIX = of("PHOENIX")

                        @JvmField val PILAR = of("PILAR")

                        @JvmField val PINDAMONHANGABA = of("PINDAMONHANGABA")

                        @JvmField val PIRACICABA = of("PIRACICABA")

                        @JvmField val PITESTI = of("PITESTI")

                        @JvmField val PITTSBURGH = of("PITTSBURGH")

                        @JvmField val PIURA = of("PIURA")

                        @JvmField val PLANO = of("PLANO")

                        @JvmField val PLOIESTI = of("PLOIESTI")

                        @JvmField val PLOVDIV = of("PLOVDIV")

                        @JvmField val PLYMOUTH = of("PLYMOUTH")

                        @JvmField val POCOS_DE_CALDAS = of("POCOS_DE CALDAS")

                        @JvmField val PODGORICA = of("PODGORICA")

                        @JvmField val POLTAVA = of("POLTAVA")

                        @JvmField val PONTA_GROSSA = of("PONTA_GROSSA")

                        @JvmField val PONTIANAK = of("PONTIANAK")

                        @JvmField val POPAYAN = of("POPAYAN")

                        @JvmField val PORT_AU_PRINCE = of("PORT_AU PRINCE")

                        @JvmField val PORT_ELIZABETH = of("PORT_ELIZABETH")

                        @JvmField val PORT_HARCOURT = of("PORT_HARCOURT")

                        @JvmField val PORT_LOUIS = of("PORT_LOUIS")

                        @JvmField val PORT_MONTT = of("PORT_MONTT")

                        @JvmField val PORT_OF_SPAIN = of("PORT_OF SPAIN")

                        @JvmField val PORT_SAID = of("PORT_SAID")

                        @JvmField val PORTLAND = of("PORTLAND")

                        @JvmField val PORTO = of("PORTO")

                        @JvmField val PORTO_ALEGRE = of("PORTO_ALEGRE")

                        @JvmField val PORTO_SEGURO = of("PORTO_SEGURO")

                        @JvmField val PORTO_VELHO = of("PORTO_VELHO")

                        @JvmField val PORTOVIEJO = of("PORTOVIEJO")

                        @JvmField val POSADAS = of("POSADAS")

                        @JvmField val POUSO_ALEGRE = of("POUSO_ALEGRE")

                        @JvmField val POZNAN = of("POZNAN")

                        @JvmField val PRAGUE = of("PRAGUE")

                        @JvmField val PRAIA_GRANDE = of("PRAIA_GRANDE")

                        @JvmField val PRESIDENTE_PRUDENTE = of("PRESIDENTE_PRUDENTE")

                        @JvmField val PRETORIA = of("PRETORIA")

                        @JvmField val PRISTINA = of("PRISTINA")

                        @JvmField val PROVIDENCE = of("PROVIDENCE")

                        @JvmField val PUCALLPA = of("PUCALLPA")

                        @JvmField val PUCHONG_BATU_DUA_BELAS = of("PUCHONG_BATU DUA BELAS")

                        @JvmField val PUEBLA_CITY = of("PUEBLA_CITY")

                        @JvmField val PUNE = of("PUNE")

                        @JvmField val QUEBEC = of("QUEBEC")

                        @JvmField val QUEENS = of("QUEENS")

                        @JvmField val QUEIMADOS = of("QUEIMADOS")

                        @JvmField val QUERETARO_CITY = of("QUERETARO_CITY")

                        @JvmField val QUEZON_CITY = of("QUEZON_CITY")

                        @JvmField val QUILMES = of("QUILMES")

                        @JvmField val QUITO = of("QUITO")

                        @JvmField val RABAT = of("RABAT")

                        @JvmField val RAIPUR = of("RAIPUR")

                        @JvmField val RAJKOT = of("RAJKOT")

                        @JvmField val RAJSHAHI = of("RAJSHAHI")

                        @JvmField val RALEIGH = of("RALEIGH")

                        @JvmField val RAMAT_GAN = of("RAMAT_GAN")

                        @JvmField val RANCAGUA = of("RANCAGUA")

                        @JvmField val RANCHI = of("RANCHI")

                        @JvmField val RAS_AL_KHAIMAH = of("RAS_AL KHAIMAH")

                        @JvmField val RAWALPINDI = of("RAWALPINDI")

                        @JvmField val READING = of("READING")

                        @JvmField val RECIFE = of("RECIFE")

                        @JvmField val REGINA = of("REGINA")

                        @JvmField val RENNES = of("RENNES")

                        @JvmField val RENO = of("RENO")

                        @JvmField val RESISTENCIA = of("RESISTENCIA")

                        @JvmField val REYKJAVIK = of("REYKJAVIK")

                        @JvmField val REYNOSA = of("REYNOSA")

                        @JvmField val RIBEIRAO_DAS_NEVES = of("RIBEIRAO_DAS NEVES")

                        @JvmField val RIBEIRAO_PRETO = of("RIBEIRAO_PRETO")

                        @JvmField val RICHMOND = of("RICHMOND")

                        @JvmField val RIGA = of("RIGA")

                        @JvmField val RIO_BRANCO = of("RIO_BRANCO")

                        @JvmField val RIO_CLARO = of("RIO_CLARO")

                        @JvmField val RIO_CUARTO = of("RIO_CUARTO")

                        @JvmField val RIO_DE_JANEIRO = of("RIO_DE JANEIRO")

                        @JvmField val RIO_DO_SUL = of("RIO_DO SUL")

                        @JvmField val RIO_GALLEGOS = of("RIO_GALLEGOS")

                        @JvmField val RIO_GRANDE = of("RIO_GRANDE")

                        @JvmField val RISHON_LETSIYYON = of("RISHON_LETSIYYON")

                        @JvmField val RIVERSIDE = of("RIVERSIDE")

                        @JvmField val RIYADH = of("RIYADH")

                        @JvmField val RIZE = of("RIZE")

                        @JvmField val ROCHESTER = of("ROCHESTER")

                        @JvmField val ROME = of("ROME")

                        @JvmField val RONDONOPOLIS = of("RONDONOPOLIS")

                        @JvmField val ROSARIO = of("ROSARIO")

                        @JvmField val ROSEAU = of("ROSEAU")

                        @JvmField val ROSTOV_ON_DON = of("ROSTOV_ON DON")

                        @JvmField val ROTTERDAM = of("ROTTERDAM")

                        @JvmField val ROUEN = of("ROUEN")

                        @JvmField val ROUSSE = of("ROUSSE")

                        @JvmField val RZESZOW = of("RZESZOW")

                        @JvmField val SACRAMENTO = of("SACRAMENTO")

                        @JvmField val SAGAR = of("SAGAR")

                        @JvmField val SAINT_PAUL = of("SAINT_PAUL")

                        @JvmField val SALE = of("SALE")

                        @JvmField val SALT_LAKE_CITY = of("SALT_LAKE CITY")

                        @JvmField val SALTA = of("SALTA")

                        @JvmField val SALTILLO = of("SALTILLO")

                        @JvmField val SALVADOR = of("SALVADOR")

                        @JvmField val SAMARA = of("SAMARA")

                        @JvmField val SAMARINDA = of("SAMARINDA")

                        @JvmField val SAMARKAND = of("SAMARKAND")

                        @JvmField val SAMSUN = of("SAMSUN")

                        @JvmField val SAN_ANTONIO = of("SAN_ANTONIO")

                        @JvmField val SAN_DIEGO = of("SAN_DIEGO")

                        @JvmField val SAN_FERNANDO = of("SAN_FERNANDO")

                        @JvmField val SAN_FRANCISCO = of("SAN_FRANCISCO")

                        @JvmField val SAN_JOSE = of("SAN_JOSE")

                        @JvmField val SAN_JOSE_DEL_MONTE = of("SAN_JOSE DEL MONTE")

                        @JvmField val SAN_JUAN = of("SAN_JUAN")

                        @JvmField val SAN_JUSTO = of("SAN_JUSTO")

                        @JvmField val SAN_LUIS = of("SAN_LUIS")

                        @JvmField val SAN_LUIS_POTOSI_CITY = of("SAN_LUIS POTOSI CITY")

                        @JvmField val SAN_MIGUEL = of("SAN_MIGUEL")

                        @JvmField val SAN_MIGUEL_DE_TUCUMAN = of("SAN_MIGUEL DE TUCUMAN")

                        @JvmField val SAN_PABLO_CITY = of("SAN_PABLO CITY")

                        @JvmField val SAN_PEDRO = of("SAN_PEDRO")

                        @JvmField val SAN_PEDRO_SULA = of("SAN_PEDRO SULA")

                        @JvmField val SAN_SALVADOR = of("SAN_SALVADOR")

                        @JvmField val SAN_SALVADOR_DE_JUJUY = of("SAN_SALVADOR DE JUJUY")

                        @JvmField val SANAA = of("SANAA")

                        @JvmField val SANLIURFA = of("SANLIURFA")

                        @JvmField val SANTA_CRUZ = of("SANTA_CRUZ")

                        @JvmField val SANTA_CRUZ_DE_TENERIFE = of("SANTA_CRUZ DE TENERIFE")

                        @JvmField val SANTA_CRUZ_DO_SUL = of("SANTA_CRUZ DO SUL")

                        @JvmField val SANTA_FE = of("SANTA_FE")

                        @JvmField val SANTA_LUZIA = of("SANTA_LUZIA")

                        @JvmField val SANTA_MARIA = of("SANTA_MARIA")

                        @JvmField val SANTA_MARTA = of("SANTA_MARTA")

                        @JvmField val SANTA_ROSA = of("SANTA_ROSA")

                        @JvmField val SANTAREM = of("SANTAREM")

                        @JvmField val SANTIAGO = of("SANTIAGO")

                        @JvmField val SANTIAGO_DE_CALI = of("SANTIAGO_DE CALI")

                        @JvmField val SANTIAGO_DE_LOS_CABALLEROS = of("SANTIAGO_DE LOS CABALLEROS")

                        @JvmField val SANTO_ANDRE = of("SANTO_ANDRE")

                        @JvmField val SANTO_DOMINGO = of("SANTO_DOMINGO")

                        @JvmField val SANTO_DOMINGO_ESTE = of("SANTO_DOMINGO ESTE")

                        @JvmField val SANTOS = of("SANTOS")

                        @JvmField val SAO_BERNARDO_DO_CAMPO = of("SAO_BERNARDO DO CAMPO")

                        @JvmField val SAO_CARLOS = of("SAO_CARLOS")

                        @JvmField val SAO_GONCALO = of("SAO_GONCALO")

                        @JvmField val SAO_JOAO_DE_MERITI = of("SAO_JOAO DE MERITI")

                        @JvmField val SAO_JOSE = of("SAO_JOSE")

                        @JvmField val SAO_JOSE_DO_RIO_PRETO = of("SAO_JOSE DO RIO PRETO")

                        @JvmField val SAO_JOSE_DOS_CAMPOS = of("SAO_JOSE DOS CAMPOS")

                        @JvmField val SAO_JOSE_DOS_PINHAIS = of("SAO_JOSE DOS PINHAIS")

                        @JvmField val SAO_LEOPOLDO = of("SAO_LEOPOLDO")

                        @JvmField val SAO_LUIS = of("SAO_LUIS")

                        @JvmField val SAO_PAULO = of("SAO_PAULO")

                        @JvmField val SAO_VICENTE = of("SAO_VICENTE")

                        @JvmField val SARAJEVO = of("SARAJEVO")

                        @JvmField val SASKATOON = of("SASKATOON")

                        @JvmField val SCARBOROUGH = of("SCARBOROUGH")

                        @JvmField val SEATTLE = of("SEATTLE")

                        @JvmField val SEMARANG = of("SEMARANG")

                        @JvmField val SEO_GU = of("SEO_GU")

                        @JvmField val SEONGNAM_SI = of("SEONGNAM_SI")

                        @JvmField val SEOUL = of("SEOUL")

                        @JvmField val SERRA = of("SERRA")

                        @JvmField val SETE_LAGOAS = of("SETE_LAGOAS")

                        @JvmField val SETIF = of("SETIF")

                        @JvmField val SETUBAL = of("SETUBAL")

                        @JvmField val SEVILLE = of("SEVILLE")

                        @JvmField val SFAX = of("SFAX")

                        @JvmField val SHAH_ALAM = of("SHAH_ALAM")

                        @JvmField val SHANGHAI = of("SHANGHAI")

                        @JvmField val SHARJAH = of("SHARJAH")

                        @JvmField val SHEFFIELD = of("SHEFFIELD")

                        @JvmField val SHENZHEN = of("SHENZHEN")

                        @JvmField val SHIMLA = of("SHIMLA")

                        @JvmField val SIAULIAI = of("SIAULIAI")

                        @JvmField val SIBIU = of("SIBIU")

                        @JvmField val SIDOARJO = of("SIDOARJO")

                        @JvmField val SIKAR = of("SIKAR")

                        @JvmField val SILVER_SPRING = of("SILVER_SPRING")

                        @JvmField val SINOP = of("SINOP")

                        @JvmField val SIVAS = of("SIVAS")

                        @JvmField val SKIKDA = of("SKIKDA")

                        @JvmField val SKOPJE = of("SKOPJE")

                        @JvmField val SLOUGH = of("SLOUGH")

                        @JvmField val SOBRAL = of("SOBRAL")

                        @JvmField val SOFIA = of("SOFIA")

                        @JvmField val SOROCABA = of("SOROCABA")

                        @JvmField val SOUSSE = of("SOUSSE")

                        @JvmField val SOUTH_TANGERANG = of("SOUTH_TANGERANG")

                        @JvmField val SOUTHAMPTON = of("SOUTHAMPTON")

                        @JvmField val SOUTHWARK = of("SOUTHWARK")

                        @JvmField val SPLIT = of("SPLIT")

                        @JvmField val SPOKANE = of("SPOKANE")

                        @JvmField val SPRING = of("SPRING")

                        @JvmField val SPRINGFIELD = of("SPRINGFIELD")

                        @JvmField val ST_LOUIS = of("ST_LOUIS")

                        @JvmField val ST_PETERSBURG = of("ST_PETERSBURG")

                        @JvmField val STARA_ZAGORA = of("STARA_ZAGORA")

                        @JvmField val STATEN_ISLAND = of("STATEN_ISLAND")

                        @JvmField val STOCKHOLM = of("STOCKHOLM")

                        @JvmField val STOCKTON = of("STOCKTON")

                        @JvmField val STOKE_ON_TRENT = of("STOKE_ON TRENT")

                        @JvmField val STRASBOURG = of("STRASBOURG")

                        @JvmField val STUTTGART = of("STUTTGART")

                        @JvmField val SUMARE = of("SUMARE")

                        @JvmField val SURABAYA = of("SURABAYA")

                        @JvmField val SURAKARTA = of("SURAKARTA")

                        @JvmField val SURAT = of("SURAT")

                        @JvmField val SURREY = of("SURREY")

                        @JvmField val SUWON = of("SUWON")

                        @JvmField val SUZANO = of("SUZANO")

                        @JvmField val SYDNEY = of("SYDNEY")

                        @JvmField val SZCZECIN = of("SZCZECIN")

                        @JvmField val SZEGED = of("SZEGED")

                        @JvmField val SZEKESFEHERVAR = of("SZEKESFEHERVAR")

                        @JvmField val TABOAO_DA_SERRA = of("TABOAO_DA SERRA")

                        @JvmField val TACNA = of("TACNA")

                        @JvmField val TACOMA = of("TACOMA")

                        @JvmField val TAGUIG = of("TAGUIG")

                        @JvmField val TAICHUNG = of("TAICHUNG")

                        @JvmField val TAINAN_CITY = of("TAINAN_CITY")

                        @JvmField val TAIPEI = of("TAIPEI")

                        @JvmField val TALAVERA = of("TALAVERA")

                        @JvmField val TALCA = of("TALCA")

                        @JvmField val TALLAHASSEE = of("TALLAHASSEE")

                        @JvmField val TALLINN = of("TALLINN")

                        @JvmField val TAMPA = of("TAMPA")

                        @JvmField val TAMPERE = of("TAMPERE")

                        @JvmField val TAMPICO = of("TAMPICO")

                        @JvmField val TANGERANG = of("TANGERANG")

                        @JvmField val TANGIER = of("TANGIER")

                        @JvmField val TANTA = of("TANTA")

                        @JvmField val TANZA = of("TANZA")

                        @JvmField val TAOYUAN_DISTRICT = of("TAOYUAN_DISTRICT")

                        @JvmField val TAPPAHANNOCK = of("TAPPAHANNOCK")

                        @JvmField val TARLAC_CITY = of("TARLAC_CITY")

                        @JvmField val TASHKENT = of("TASHKENT")

                        @JvmField val TASIKMALAYA = of("TASIKMALAYA")

                        @JvmField val TATUI = of("TATUI")

                        @JvmField val TAUBATE = of("TAUBATE")

                        @JvmField val TBILISI = of("TBILISI")

                        @JvmField val TEGUCIGALPA = of("TEGUCIGALPA")

                        @JvmField val TEHRAN = of("TEHRAN")

                        @JvmField val TEIXEIRA_DE_FREITAS = of("TEIXEIRA_DE FREITAS")

                        @JvmField val TEKIRDAG = of("TEKIRDAG")

                        @JvmField val TEL_AVIV = of("TEL_AVIV")

                        @JvmField val TEMUCO = of("TEMUCO")

                        @JvmField val TEPIC = of("TEPIC")

                        @JvmField val TERESINA = of("TERESINA")

                        @JvmField val TERNOPIL = of("TERNOPIL")

                        @JvmField val TERRASSA = of("TERRASSA")

                        @JvmField val TETOUAN = of("TETOUAN")

                        @JvmField val THANE = of("THANE")

                        @JvmField val THE_BRONX = of("THE_BRONX")

                        @JvmField val THE_HAGUE = of("THE_HAGUE")

                        @JvmField val THESSALONIKI = of("THESSALONIKI")

                        @JvmField val THIRUVANANTHAPURAM = of("THIRUVANANTHAPURAM")

                        @JvmField val THRISSUR = of("THRISSUR")

                        @JvmField val TIJUANA = of("TIJUANA")

                        @JvmField val TIMISOARA = of("TIMISOARA")

                        @JvmField val TIRANA = of("TIRANA")

                        @JvmField val TLALNEPANTLA = of("TLALNEPANTLA")

                        @JvmField val TLAXCALA_CITY = of("TLAXCALA_CITY")

                        @JvmField val TLEMCEN = of("TLEMCEN")

                        @JvmField val TOKAT_PROVINCE = of("TOKAT_PROVINCE")

                        @JvmField val TOKYO = of("TOKYO")

                        @JvmField val TOLUCA = of("TOLUCA")

                        @JvmField val TORONTO = of("TORONTO")

                        @JvmField val TORREON = of("TORREON")

                        @JvmField val TOULOUSE = of("TOULOUSE")

                        @JvmField val TRABZON = of("TRABZON")

                        @JvmField val TRUJILLO = of("TRUJILLO")

                        @JvmField val TUBARAO = of("TUBARAO")

                        @JvmField val TUCSON = of("TUCSON")

                        @JvmField val TUGUEGARAO_CITY = of("TUGUEGARAO_CITY")

                        @JvmField val TULSA = of("TULSA")

                        @JvmField val TUNIS = of("TUNIS")

                        @JvmField val TUNJA = of("TUNJA")

                        @JvmField val TURIN = of("TURIN")

                        @JvmField val TUXTLA_GUTIERREZ = of("TUXTLA_GUTIERREZ")

                        @JvmField val TUZLA = of("TUZLA")

                        @JvmField val UBERABA = of("UBERABA")

                        @JvmField val UBERLANDIA = of("UBERLANDIA")

                        @JvmField val UFA = of("UFA")

                        @JvmField val ULAN_BATOR = of("ULAN_BATOR")

                        @JvmField val UMEDA = of("UMEDA")

                        @JvmField val URDANETA = of("URDANETA")

                        @JvmField val USAK = of("USAK")

                        @JvmField val VADODARA = of("VADODARA")

                        @JvmField val VALENCIA = of("VALENCIA")

                        @JvmField val VALINHOS = of("VALINHOS")

                        @JvmField val VALLADOLID = of("VALLADOLID")

                        @JvmField val VALLEDUPAR = of("VALLEDUPAR")

                        @JvmField val VALPARAISO = of("VALPARAISO")

                        @JvmField val VALPARAISO_DE_GOIAS = of("VALPARAISO_DE GOIAS")

                        @JvmField val VAN = of("VAN")

                        @JvmField val VANCOUVER = of("VANCOUVER")

                        @JvmField val VARANASI = of("VARANASI")

                        @JvmField val VARGINHA = of("VARGINHA")

                        @JvmField val VARNA = of("VARNA")

                        @JvmField val VARZEA_PAULISTA = of("VARZEA_PAULISTA")

                        @JvmField val VENUSTIANO_CARRANZA = of("VENUSTIANO_CARRANZA")

                        @JvmField val VERACRUZ = of("VERACRUZ")

                        @JvmField val VERONA = of("VERONA")

                        @JvmField val VIAMAO = of("VIAMAO")

                        @JvmField val VICTORIA = of("VICTORIA")

                        @JvmField val VIENNA = of("VIENNA")

                        @JvmField val VIENTIANE = of("VIENTIANE")

                        @JvmField val VIGO = of("VIGO")

                        @JvmField val VIJAYAWADA = of("VIJAYAWADA")

                        @JvmField val VILA_NOVA_DE_GAIA = of("VILA_NOVA DE GAIA")

                        @JvmField val VILA_VELHA = of("VILA_VELHA")

                        @JvmField val VILLA_BALLESTER = of("VILLA_BALLESTER")

                        @JvmField val VILLAVICENCIO = of("VILLAVICENCIO")

                        @JvmField val VILNIUS = of("VILNIUS")

                        @JvmField val VINA_DEL_MAR = of("VINA_DEL MAR")

                        @JvmField val VINNYTSIA = of("VINNYTSIA")

                        @JvmField val VIRGINIA_BEACH = of("VIRGINIA_BEACH")

                        @JvmField val VISAKHAPATNAM = of("VISAKHAPATNAM")

                        @JvmField val VITORIA = of("VITORIA")

                        @JvmField val VITORIA_DA_CONQUISTA = of("VITORIA_DA CONQUISTA")

                        @JvmField val VITORIA_DE_SANTO_ANTAO = of("VITORIA_DE SANTO ANTAO")

                        @JvmField val VOLTA_REDONDA = of("VOLTA_REDONDA")

                        @JvmField val VORONEZH = of("VORONEZH")

                        @JvmField val WARSAW = of("WARSAW")

                        @JvmField val WASHINGTON = of("WASHINGTON")

                        @JvmField val WELLINGTON = of("WELLINGTON")

                        @JvmField val WEST_PALM_BEACH = of("WEST_PALM BEACH")

                        @JvmField val WICHITA = of("WICHITA")

                        @JvmField val WILLEMSTAD = of("WILLEMSTAD")

                        @JvmField val WILMINGTON = of("WILMINGTON")

                        @JvmField val WINDHOEK = of("WINDHOEK")

                        @JvmField val WINDSOR = of("WINDSOR")

                        @JvmField val WINNIPEG = of("WINNIPEG")

                        @JvmField val WOLVERHAMPTON = of("WOLVERHAMPTON")

                        @JvmField val WOODBRIDGE = of("WOODBRIDGE")

                        @JvmField val WROCLAW = of("WROCLAW")

                        @JvmField val WUPPERTAL = of("WUPPERTAL")

                        @JvmField val XALAPA = of("XALAPA")

                        @JvmField val YALOVA = of("YALOVA")

                        @JvmField val YANGON = of("YANGON")

                        @JvmField val YEKATERINBURG = of("YEKATERINBURG")

                        @JvmField val YEREVAN = of("YEREVAN")

                        @JvmField val YOGYAKARTA = of("YOGYAKARTA")

                        @JvmField val YOKOHAMA = of("YOKOHAMA")

                        @JvmField val YONGIN_SI = of("YONGIN_SI")

                        @JvmField val ZABRZE = of("ZABRZE")

                        @JvmField val ZAGAZIG = of("ZAGAZIG")

                        @JvmField val ZAGREB = of("ZAGREB")

                        @JvmField val ZAMBOANGA_CITY = of("ZAMBOANGA_CITY")

                        @JvmField val ZAPOPAN = of("ZAPOPAN")

                        @JvmField val ZAPORIZHZHYA = of("ZAPORIZHZHYA")

                        @JvmField val ZARAGOZA = of("ZARAGOZA")

                        @JvmField val ZHONGLI_DISTRICT = of("ZHONGLI_DISTRICT")

                        @JvmField val ZIELONA_GORA = of("ZIELONA_GORA")

                        @JvmField val ZONGULDAK = of("ZONGULDAK")

                        @JvmField val ZURICH = of("ZURICH")

                        @JvmStatic fun of(value: String) = City(JsonField.of(value))
                    }

                    enum class Known {
                        A_CORUNA,
                        ABIDJAN,
                        ABU_DHABI,
                        ABUJA,
                        ACAPULCO_DE_JUAREZ,
                        ACCRA,
                        ADANA,
                        ADAPAZARI,
                        ADDIS_ABABA,
                        ADELAIDE,
                        AFYONKARAHISAR,
                        AGADIR,
                        AGUAS_LINDAS_DE_GOIAS,
                        AGUASCALIENTES,
                        AHMEDABAD,
                        AIZAWL,
                        AJMAN,
                        AKRON,
                        AKSARAY,
                        AL_AIN_CITY,
                        AL_MANSURAH,
                        AL_QATIF,
                        ALAJUELA,
                        ALBANY,
                        ALBUQUERQUE,
                        ALEXANDRIA,
                        ALGIERS,
                        ALICANTE,
                        ALMADA,
                        ALMATY,
                        ALMERE_STAD,
                        ALVORADA,
                        AMADORA,
                        AMASYA,
                        AMBATO,
                        AMERICANA,
                        AMMAN,
                        AMSTERDAM,
                        ANANINDEUA,
                        ANAPOLIS,
                        ANGELES_CITY,
                        ANGERS,
                        ANGRA_DOS_REIS,
                        ANKARA,
                        ANTAKYA,
                        ANTALYA,
                        ANTANANARIVO,
                        ANTIPOLO_CITY,
                        ANTOFAGASTA,
                        ANTWERP,
                        APARECIDA_DE_GOIANIA,
                        APODACA,
                        ARACAJU,
                        ARACATUBA,
                        ARAD,
                        ARAGUAINA,
                        ARAPIRACA,
                        ARARAQUARA,
                        AREQUIPA,
                        ARICA,
                        ARLINGTON,
                        ARYANAH,
                        ASTANA,
                        ASUNCION,
                        ASYUT,
                        ATAKUM,
                        ATHENS,
                        ATIBAIA,
                        ATLANTA,
                        AUBURN,
                        AUCKLAND,
                        AURORA,
                        AUSTIN,
                        AVELLANEDA,
                        AYDIN,
                        AZCAPOTZALCO,
                        BACOLOD_CITY,
                        BACOOR,
                        BAGHDAD,
                        BAGUIO_CITY,
                        BAHIA_BLANCA,
                        BAKERSFIELD,
                        BAKU,
                        BALIKESIR,
                        BALIKPAPAN,
                        BALNEARIO_CAMBORIU,
                        BALTIMORE,
                        BANDAR_LAMPUNG,
                        BANDAR_SERI_BEGAWAN,
                        BANDUNG,
                        BANGKOK,
                        BANJA_LUKA,
                        BANJARMASIN,
                        BARCELONA,
                        BARI,
                        BARQUISIMETO,
                        BARRA_MANSA,
                        BARRANQUILLA,
                        BARUERI,
                        BATAM,
                        BATANGAS,
                        BATMAN,
                        BATNA_CITY,
                        BATON_ROUGE,
                        BATUMI,
                        BAURU,
                        BEIRUT,
                        BEJAIA,
                        BEKASI,
                        BELEM,
                        BELFAST,
                        BELFORD_ROXO,
                        BELGRADE,
                        BELO_HORIZONTE,
                        BENGALURU,
                        BENI_MELLAL,
                        BERAZATEGUI,
                        BERN,
                        BETIM,
                        BHARATPUR,
                        BHOPAL,
                        BHUBANESWAR,
                        BIALYSTOK,
                        BIEN_HOA,
                        BILBAO,
                        BILECIK,
                        BIRATNAGAR,
                        BIRMINGHAM,
                        BISHKEK,
                        BIZERTE,
                        BLIDA,
                        BLOEMFONTEIN,
                        BLOOMINGTON,
                        BLUMENAU,
                        BOA_VISTA,
                        BOCHUM,
                        BOGOR,
                        BOGOTA,
                        BOISE,
                        BOKSBURG,
                        BOLOGNA,
                        BOLU,
                        BORDEAUX,
                        BOSTON,
                        BOTUCATU,
                        BRADFORD,
                        BRAGA,
                        BRAGANCA_PAULISTA,
                        BRAMPTON,
                        BRASILIA,
                        BRASOV,
                        BRATISLAVA,
                        BREMEN,
                        BRESCIA,
                        BREST,
                        BRIDGETOWN,
                        BRISBANE,
                        BRISTOL,
                        BRNO,
                        BROOKLYN,
                        BRUSSELS,
                        BUCARAMANGA,
                        BUCHAREST,
                        BUDAPEST,
                        BUENOS_AIRES,
                        BUFFALO,
                        BUK_GU,
                        BUKHARA,
                        BURGAS,
                        BURNABY,
                        BURSA,
                        BUTUAN,
                        BYDGOSZCZ,
                        CABANATUAN_CITY,
                        CABO_FRIO,
                        CABUYAO,
                        CACHOEIRO_DE_ITAPEMIRIM,
                        CAGAYAN_DE_ORO,
                        CAGLIARI,
                        CAIRO,
                        CALAMBA,
                        CALGARY,
                        CALOOCAN_CITY,
                        CAMACARI,
                        CAMARAGIBE,
                        CAMPECHE,
                        CAMPINA_GRANDE,
                        CAMPINAS,
                        CAMPO_GRANDE,
                        CAMPO_LARGO,
                        CAMPOS_DOS_GOYTACAZES,
                        CAN_THO,
                        CANOAS,
                        CANTON,
                        CAPE_TOWN,
                        CARACAS,
                        CARAGUATATUBA,
                        CARAPICUIBA,
                        CARDIFF,
                        CARIACICA,
                        CARMONA,
                        CARTAGENA,
                        CARUARU,
                        CASABLANCA,
                        CASCAVEL,
                        CASEROS,
                        CASTANHAL,
                        CASTRIES,
                        CATALAO,
                        CATAMARCA,
                        CATANDUVA,
                        CATANIA,
                        CAUCAIA,
                        CAXIAS_DO_SUL,
                        CEBU_CITY,
                        CENTRAL,
                        CENTRO,
                        CENTURION,
                        CHAGUANAS,
                        CHANDIGARH,
                        CHANDLER,
                        CHANG_HUA,
                        CHAPECO,
                        CHARLESTON,
                        CHARLOTTE,
                        CHELYABINSK,
                        CHENNAI,
                        CHERKASY,
                        CHERNIVTSI,
                        CHIA,
                        CHIANG_MAI,
                        CHICLAYO,
                        CHIHUAHUA_CITY,
                        CHIMBOTE,
                        CHISINAU,
                        CHITTAGONG,
                        CHRISTCHURCH,
                        CINCINNATI,
                        CIREBON,
                        CITY_OF_MUNTINLUPA,
                        CIUDAD_DEL_ESTE,
                        CIUDAD_GUAYANA,
                        CIUDAD_JUAREZ,
                        CIUDAD_NEZAHUALCOYOTL,
                        CIUDAD_OBREGON,
                        CLEVELAND,
                        CLUJ_NAPOCA,
                        COCHABAMBA,
                        COIMBATORE,
                        COIMBRA,
                        COLOGNE,
                        COLOMBO,
                        COLORADO_SPRINGS,
                        COLUMBIA,
                        COLUMBUS,
                        COMODORO_RIVADAVIA,
                        CONCEPCION,
                        CONCORD,
                        CONSTANTA,
                        CONSTANTINE,
                        CONTAGEM,
                        COPENHAGEN,
                        CORDOBA,
                        CORRIENTES,
                        CORUM,
                        COTIA,
                        COVENTRY,
                        CRAIOVA,
                        CRICIUMA,
                        CROYDON,
                        CUAUTITLAN_IZCALLI,
                        CUCUTA,
                        CUENCA,
                        CUERNAVACA,
                        CUIABA,
                        CULIACAN,
                        CURITIBA,
                        CUSCO,
                        DA_NANG,
                        DAGUPAN,
                        DAKAR,
                        DALLAS,
                        DAMIETTA,
                        DAMMAM,
                        DAR_ES_SALAAM,
                        DASMARINAS,
                        DAVAO_CITY,
                        DAYTON,
                        DEBRECEN,
                        DECATUR,
                        DEHRADUN,
                        DELHI,
                        DENIZLI,
                        DENPASAR,
                        DENVER,
                        DEPOK,
                        DERBY,
                        DETROIT,
                        DHAKA,
                        DIADEMA,
                        DIVINOPOLIS,
                        DIYARBAKIR,
                        DJELFA,
                        DNIPRO,
                        DOHA,
                        DORTMUND,
                        DOURADOS,
                        DRESDEN,
                        DUBAI,
                        DUBLIN,
                        DUEZCE,
                        DUISBURG,
                        DUQUE_DE_CAXIAS,
                        DURANGO,
                        DURBAN,
                        DUSSELDORF,
                        ECATEPEC,
                        EDINBURGH,
                        EDIRNE,
                        EDMONTON,
                        EL_JADIDA,
                        EL_PASO,
                        ELAZIG,
                        EMBU,
                        ENSENADA,
                        ERBIL,
                        ERZURUM,
                        ESKISEHIR,
                        ESPOO,
                        ESSEN,
                        FAISALABAD,
                        FAYETTEVILLE,
                        FAZENDA_RIO_GRANDE,
                        FEIRA_DE_SANTANA,
                        FES,
                        FLORENCE,
                        FLORENCIO_VARELA,
                        FLORIANOPOLIS,
                        FONTANA,
                        FORMOSA,
                        FORT_LAUDERDALE,
                        FORT_WAYNE,
                        FORT_WORTH,
                        FORTALEZA,
                        FOZ_DO_IGUACU,
                        FRANCA,
                        FRANCISCO_MORATO,
                        FRANCO_DA_ROCHA,
                        FRANKFURT_AM_MAIN,
                        FREDERICKSBURG,
                        FRESNO,
                        FUNCHAL,
                        GABORONE,
                        GAINESVILLE,
                        GALATI,
                        GANGNAM_GU,
                        GARANHUNS,
                        GATINEAU,
                        GAZIANTEP,
                        GDANSK,
                        GDYNIA,
                        GENERAL_TRIAS,
                        GENEVA,
                        GENOA,
                        GEORGE_TOWN,
                        GEORGETOWN,
                        GHAZIABAD,
                        GHENT,
                        GIJON,
                        GIRESUN,
                        GIZA,
                        GLASGOW,
                        GLENDALE,
                        GLIWICE,
                        GOIANIA,
                        GOMEL,
                        GOTHENBURG,
                        GOVERNADOR_VALADARES,
                        GOYANG_SI,
                        GRANADA,
                        GRAND_RAPIDS,
                        GRAVATAI,
                        GRAZ,
                        GREENSBORO,
                        GREENVILLE,
                        GUADALAJARA,
                        GUADALUPE,
                        GUANGZHOU,
                        GUARAPUAVA,
                        GUARATINGUETA,
                        GUARUJA,
                        GUARULHOS,
                        GUATEMALA_CITY,
                        GUAYAQUIL,
                        GUJRANWALA,
                        GURUGRAM,
                        GUSTAVO_ADOLFO_MADERO,
                        GUWAHATI,
                        GWANAK_GU,
                        HACKNEY,
                        HAIFA,
                        HAIPHONG,
                        HAMBURG,
                        HAMILTON,
                        HANOI,
                        HANOVER,
                        HARARE,
                        HAVANA,
                        HELSINKI,
                        HENDERSON,
                        HEREDIA,
                        HERMOSILLO,
                        HIALEAH,
                        HO_CHI_MINH_CITY,
                        HOLLYWOOD,
                        HOLON,
                        HONOLULU,
                        HORTOLANDIA,
                        HRODNA,
                        HSINCHU,
                        HUANCAYO,
                        HUANUCO,
                        HULL,
                        HURLINGHAM,
                        HYDERABAD,
                        IASI,
                        IBAGUE,
                        ICA,
                        ILAM,
                        ILFORD,
                        ILIGAN,
                        ILOILO_CITY,
                        IMPERATRIZ,
                        IMUS,
                        INCHEON,
                        INDAIATUBA,
                        INDIANAPOLIS,
                        INDORE,
                        IPATINGA,
                        IPOH,
                        IQUIQUE,
                        IRVINE,
                        ISIDRO_CASANOVA,
                        ISLAMABAD,
                        ISLINGTON,
                        ISMAILIA,
                        ISPARTA,
                        ISTANBUL,
                        ITABORAI,
                        ITABUNA,
                        ITAJAI,
                        ITANHAEM,
                        ITAPEVI,
                        ITAQUAQUECETUBA,
                        ITUZAINGO,
                        IZMIR,
                        IZTAPALAPA,
                        JABOATAO_DOS_GUARARAPES,
                        JACAREI,
                        JACKSON,
                        JACKSONVILLE,
                        JAIPUR,
                        JAKARTA,
                        JARAGUA_DO_SUL,
                        JAU,
                        JEDDAH,
                        JEMBER,
                        JERUSALEM,
                        JOAO_MONLEVADE,
                        JOAO_PESSOA,
                        JODHPUR,
                        JOHANNESBURG,
                        JOHOR_BAHRU,
                        JOINVILLE,
                        JOSE_C_PAZ,
                        JOSE_MARIA_EZEIZA,
                        JUAREZ,
                        JUAZEIRO_DO_NORTE,
                        JUIZ_DE_FORA,
                        JUNDIAI,
                        KAHRAMANMARAS,
                        KAMPALA,
                        KANPUR,
                        KANSAS_CITY,
                        KAOHSIUNG_CITY,
                        KARABUK,
                        KARACHI,
                        KARLSRUHE,
                        KARNAL,
                        KASKI,
                        KASTAMONU,
                        KATHMANDU,
                        KATOWICE,
                        KATSINA,
                        KATY,
                        KAUNAS,
                        KAYSERI,
                        KAZAN,
                        KECSKEMET,
                        KEDIRI,
                        KENITRA,
                        KHARKIV,
                        KHMELNYTSKYI,
                        KHON_KAEN,
                        KIELCE,
                        KIGALI,
                        KINGSTON,
                        KIRKLARELI,
                        KISSIMMEE,
                        KITCHENER,
                        KLAIPEDA,
                        KNOXVILLE,
                        KOCHI,
                        KOLKATA,
                        KOLLAM,
                        KONYA,
                        KOSEKOY,
                        KOSICE,
                        KOTA_KINABALU,
                        KOZHIKODE,
                        KRAKOW,
                        KRASNODAR,
                        KRYVYI_RIH,
                        KUALA_LUMPUR,
                        KUCHING,
                        KUTAHYA,
                        KUTAISI,
                        KUWAIT_CITY,
                        KYIV,
                        LA_PAZ,
                        LA_PLATA,
                        LA_RIOJA,
                        LA_SERENA,
                        LAFAYETTE,
                        LAFERRERE,
                        LAGES,
                        LAGOS,
                        LAHORE,
                        LAHUG,
                        LAKE_WORTH,
                        LAKELAND,
                        LANCASTER,
                        LANUS,
                        LAS_PALMAS_DE_GRAN_CANARIA,
                        LAS_PINAS,
                        LAS_VEGAS,
                        LAUSANNE,
                        LAVAL,
                        LAWRENCEVILLE,
                        LE_MANS,
                        LEEDS,
                        LEICESTER,
                        LEIPZIG,
                        LEON,
                        LEXINGTON,
                        LIBREVILLE,
                        LIEGE,
                        LILLE,
                        LIMA,
                        LIMASSOL,
                        LIMEIRA,
                        LINCOLN,
                        LINHARES,
                        LIPA_CITY,
                        LISBON,
                        LIVERPOOL,
                        LJUBLJANA,
                        LODZ,
                        LOJA,
                        LOMAS_DE_ZAMORA,
                        LOME,
                        LONDRINA,
                        LONG_BEACH,
                        LONGUEUIL,
                        LOUISVILLE,
                        LUANDA,
                        LUBLIN,
                        LUCENA_CITY,
                        LUCKNOW,
                        LUDHIANA,
                        LUSAKA,
                        LUXEMBOURG,
                        LUZIANIA,
                        LVIV,
                        LYON,
                        MABALACAT,
                        MACAE,
                        MACAO,
                        MACAPA,
                        MACEIO,
                        MACHALA,
                        MADISON,
                        MADRID,
                        MAGE,
                        MAGELANG,
                        MAGNESIA_AD_SIPYLUM,
                        MAKASSAR,
                        MAKATI_CITY,
                        MALABON,
                        MALAGA,
                        MALANG,
                        MALAPPURAM,
                        MALDONADO,
                        MALE,
                        MALMO,
                        MANADO,
                        MANAGUA,
                        MANAMA,
                        MANAUS,
                        MANCHESTER,
                        MANDALUYONG_CITY,
                        MANILA,
                        MANIZALES,
                        MANNHEIM,
                        MAPUTO,
                        MAR_DEL_PLATA,
                        MARABA,
                        MARACAIBO,
                        MARACANAU,
                        MARACAY,
                        MARDIN,
                        MARIBOR,
                        MARICA,
                        MARIETTA,
                        MARIKINA_CITY,
                        MARILIA,
                        MARINGA,
                        MARRAKESH,
                        MARSEILLE,
                        MAUA,
                        MAZATLAN,
                        MEDAN,
                        MEDELLIN,
                        MEDINA,
                        MEERUT,
                        MEKNES,
                        MELBOURNE,
                        MEMPHIS,
                        MENDOZA,
                        MERIDA,
                        MERKEZ,
                        MERLO,
                        MERSIN,
                        MESA,
                        MEXICALI,
                        MEXICO_CITY,
                        MILAN,
                        MILTON_KEYNES,
                        MILWAUKEE,
                        MINNEAPOLIS,
                        MINSK,
                        MISKOLC,
                        MISSISSAUGA,
                        MOGI_DAS_CRUZES,
                        MOHALI,
                        MONROE,
                        MONTE_GRANDE,
                        MONTEGO_BAY,
                        MONTERREY,
                        MONTES_CLAROS,
                        MONTEVIDEO,
                        MONTGOMERY,
                        MONTPELLIER,
                        MONTREAL,
                        MORELIA,
                        MORENO,
                        MORON,
                        MOSSORO,
                        MUGLA,
                        MULTAN,
                        MUMBAI,
                        MUNICH,
                        MURCIA,
                        MUSCAT,
                        MUZAFFARGARH,
                        MYKOLAYIV,
                        NAALDWIJK,
                        NAGA,
                        NAGPUR,
                        NAIROBI,
                        NANTES,
                        NAPLES,
                        NASHVILLE,
                        NASSAU,
                        NASUGBU,
                        NATAL,
                        NAUCALPAN,
                        NAVI_MUMBAI,
                        NEIVA,
                        NEUQUEN,
                        NEVSEHIR,
                        NEW_DELHI,
                        NEW_ORLEANS,
                        NEW_TAIPEI,
                        NEWARK,
                        NEWCASTLE_UPON_TYNE,
                        NHA_TRANG,
                        NICE,
                        NICOSIA,
                        NILOPOLIS,
                        NIS,
                        NITEROI,
                        NITRA,
                        NIZHNIY_NOVGOROD,
                        NOGALES,
                        NOIDA,
                        NORTHAMPTON,
                        NORWICH,
                        NOTTINGHAM,
                        NOVA_FRIBURGO,
                        NOVA_IGUACU,
                        NOVI_SAD,
                        NOVO_HAMBURGO,
                        NOVOSIBIRSK,
                        NUREMBERG,
                        OAKLAND,
                        OAXACA_CITY,
                        ODESA,
                        OKLAHOMA_CITY,
                        OLINDA,
                        OLOMOUC,
                        OLONGAPO_CITY,
                        OLSZTYN,
                        OMAHA,
                        ORADEA,
                        ORAN,
                        ORDU,
                        ORLANDO,
                        OSASCO,
                        OSLO,
                        OSMANIYE,
                        OSTRAVA,
                        OTTAWA,
                        OUJDA,
                        OURINHOS,
                        PACHUCA,
                        PADOVA,
                        PALAKKAD,
                        PALEMBANG,
                        PALERMO,
                        PALHOCA,
                        PALMA,
                        PALMAS,
                        PANAMA_CITY,
                        PARAMARIBO,
                        PARANA,
                        PARANAGUA,
                        PARANAQUE_CITY,
                        PARAUAPEBAS,
                        PARIS,
                        PARNAIBA,
                        PARNAMIRIM,
                        PASSO_FUNDO,
                        PASTO,
                        PATAN,
                        PATNA,
                        PATOS_DE_MINAS,
                        PAULISTA,
                        PECS,
                        PEKANBARU,
                        PELOTAS,
                        PEORIA,
                        PEREIRA,
                        PERM,
                        PERTH,
                        PESCARA,
                        PESHAWAR,
                        PETAH_TIKVA,
                        PETALING_JAYA,
                        PETROLINA,
                        PETROPOLIS,
                        PHILADELPHIA,
                        PHNOM_PENH,
                        PHOENIX,
                        PILAR,
                        PINDAMONHANGABA,
                        PIRACICABA,
                        PITESTI,
                        PITTSBURGH,
                        PIURA,
                        PLANO,
                        PLOIESTI,
                        PLOVDIV,
                        PLYMOUTH,
                        POCOS_DE_CALDAS,
                        PODGORICA,
                        POLTAVA,
                        PONTA_GROSSA,
                        PONTIANAK,
                        POPAYAN,
                        PORT_AU_PRINCE,
                        PORT_ELIZABETH,
                        PORT_HARCOURT,
                        PORT_LOUIS,
                        PORT_MONTT,
                        PORT_OF_SPAIN,
                        PORT_SAID,
                        PORTLAND,
                        PORTO,
                        PORTO_ALEGRE,
                        PORTO_SEGURO,
                        PORTO_VELHO,
                        PORTOVIEJO,
                        POSADAS,
                        POUSO_ALEGRE,
                        POZNAN,
                        PRAGUE,
                        PRAIA_GRANDE,
                        PRESIDENTE_PRUDENTE,
                        PRETORIA,
                        PRISTINA,
                        PROVIDENCE,
                        PUCALLPA,
                        PUCHONG_BATU_DUA_BELAS,
                        PUEBLA_CITY,
                        PUNE,
                        QUEBEC,
                        QUEENS,
                        QUEIMADOS,
                        QUERETARO_CITY,
                        QUEZON_CITY,
                        QUILMES,
                        QUITO,
                        RABAT,
                        RAIPUR,
                        RAJKOT,
                        RAJSHAHI,
                        RALEIGH,
                        RAMAT_GAN,
                        RANCAGUA,
                        RANCHI,
                        RAS_AL_KHAIMAH,
                        RAWALPINDI,
                        READING,
                        RECIFE,
                        REGINA,
                        RENNES,
                        RENO,
                        RESISTENCIA,
                        REYKJAVIK,
                        REYNOSA,
                        RIBEIRAO_DAS_NEVES,
                        RIBEIRAO_PRETO,
                        RICHMOND,
                        RIGA,
                        RIO_BRANCO,
                        RIO_CLARO,
                        RIO_CUARTO,
                        RIO_DE_JANEIRO,
                        RIO_DO_SUL,
                        RIO_GALLEGOS,
                        RIO_GRANDE,
                        RISHON_LETSIYYON,
                        RIVERSIDE,
                        RIYADH,
                        RIZE,
                        ROCHESTER,
                        ROME,
                        RONDONOPOLIS,
                        ROSARIO,
                        ROSEAU,
                        ROSTOV_ON_DON,
                        ROTTERDAM,
                        ROUEN,
                        ROUSSE,
                        RZESZOW,
                        SACRAMENTO,
                        SAGAR,
                        SAINT_PAUL,
                        SALE,
                        SALT_LAKE_CITY,
                        SALTA,
                        SALTILLO,
                        SALVADOR,
                        SAMARA,
                        SAMARINDA,
                        SAMARKAND,
                        SAMSUN,
                        SAN_ANTONIO,
                        SAN_DIEGO,
                        SAN_FERNANDO,
                        SAN_FRANCISCO,
                        SAN_JOSE,
                        SAN_JOSE_DEL_MONTE,
                        SAN_JUAN,
                        SAN_JUSTO,
                        SAN_LUIS,
                        SAN_LUIS_POTOSI_CITY,
                        SAN_MIGUEL,
                        SAN_MIGUEL_DE_TUCUMAN,
                        SAN_PABLO_CITY,
                        SAN_PEDRO,
                        SAN_PEDRO_SULA,
                        SAN_SALVADOR,
                        SAN_SALVADOR_DE_JUJUY,
                        SANAA,
                        SANLIURFA,
                        SANTA_CRUZ,
                        SANTA_CRUZ_DE_TENERIFE,
                        SANTA_CRUZ_DO_SUL,
                        SANTA_FE,
                        SANTA_LUZIA,
                        SANTA_MARIA,
                        SANTA_MARTA,
                        SANTA_ROSA,
                        SANTAREM,
                        SANTIAGO,
                        SANTIAGO_DE_CALI,
                        SANTIAGO_DE_LOS_CABALLEROS,
                        SANTO_ANDRE,
                        SANTO_DOMINGO,
                        SANTO_DOMINGO_ESTE,
                        SANTOS,
                        SAO_BERNARDO_DO_CAMPO,
                        SAO_CARLOS,
                        SAO_GONCALO,
                        SAO_JOAO_DE_MERITI,
                        SAO_JOSE,
                        SAO_JOSE_DO_RIO_PRETO,
                        SAO_JOSE_DOS_CAMPOS,
                        SAO_JOSE_DOS_PINHAIS,
                        SAO_LEOPOLDO,
                        SAO_LUIS,
                        SAO_PAULO,
                        SAO_VICENTE,
                        SARAJEVO,
                        SASKATOON,
                        SCARBOROUGH,
                        SEATTLE,
                        SEMARANG,
                        SEO_GU,
                        SEONGNAM_SI,
                        SEOUL,
                        SERRA,
                        SETE_LAGOAS,
                        SETIF,
                        SETUBAL,
                        SEVILLE,
                        SFAX,
                        SHAH_ALAM,
                        SHANGHAI,
                        SHARJAH,
                        SHEFFIELD,
                        SHENZHEN,
                        SHIMLA,
                        SIAULIAI,
                        SIBIU,
                        SIDOARJO,
                        SIKAR,
                        SILVER_SPRING,
                        SINOP,
                        SIVAS,
                        SKIKDA,
                        SKOPJE,
                        SLOUGH,
                        SOBRAL,
                        SOFIA,
                        SOROCABA,
                        SOUSSE,
                        SOUTH_TANGERANG,
                        SOUTHAMPTON,
                        SOUTHWARK,
                        SPLIT,
                        SPOKANE,
                        SPRING,
                        SPRINGFIELD,
                        ST_LOUIS,
                        ST_PETERSBURG,
                        STARA_ZAGORA,
                        STATEN_ISLAND,
                        STOCKHOLM,
                        STOCKTON,
                        STOKE_ON_TRENT,
                        STRASBOURG,
                        STUTTGART,
                        SUMARE,
                        SURABAYA,
                        SURAKARTA,
                        SURAT,
                        SURREY,
                        SUWON,
                        SUZANO,
                        SYDNEY,
                        SZCZECIN,
                        SZEGED,
                        SZEKESFEHERVAR,
                        TABOAO_DA_SERRA,
                        TACNA,
                        TACOMA,
                        TAGUIG,
                        TAICHUNG,
                        TAINAN_CITY,
                        TAIPEI,
                        TALAVERA,
                        TALCA,
                        TALLAHASSEE,
                        TALLINN,
                        TAMPA,
                        TAMPERE,
                        TAMPICO,
                        TANGERANG,
                        TANGIER,
                        TANTA,
                        TANZA,
                        TAOYUAN_DISTRICT,
                        TAPPAHANNOCK,
                        TARLAC_CITY,
                        TASHKENT,
                        TASIKMALAYA,
                        TATUI,
                        TAUBATE,
                        TBILISI,
                        TEGUCIGALPA,
                        TEHRAN,
                        TEIXEIRA_DE_FREITAS,
                        TEKIRDAG,
                        TEL_AVIV,
                        TEMUCO,
                        TEPIC,
                        TERESINA,
                        TERNOPIL,
                        TERRASSA,
                        TETOUAN,
                        THANE,
                        THE_BRONX,
                        THE_HAGUE,
                        THESSALONIKI,
                        THIRUVANANTHAPURAM,
                        THRISSUR,
                        TIJUANA,
                        TIMISOARA,
                        TIRANA,
                        TLALNEPANTLA,
                        TLAXCALA_CITY,
                        TLEMCEN,
                        TOKAT_PROVINCE,
                        TOKYO,
                        TOLUCA,
                        TORONTO,
                        TORREON,
                        TOULOUSE,
                        TRABZON,
                        TRUJILLO,
                        TUBARAO,
                        TUCSON,
                        TUGUEGARAO_CITY,
                        TULSA,
                        TUNIS,
                        TUNJA,
                        TURIN,
                        TUXTLA_GUTIERREZ,
                        TUZLA,
                        UBERABA,
                        UBERLANDIA,
                        UFA,
                        ULAN_BATOR,
                        UMEDA,
                        URDANETA,
                        USAK,
                        VADODARA,
                        VALENCIA,
                        VALINHOS,
                        VALLADOLID,
                        VALLEDUPAR,
                        VALPARAISO,
                        VALPARAISO_DE_GOIAS,
                        VAN,
                        VANCOUVER,
                        VARANASI,
                        VARGINHA,
                        VARNA,
                        VARZEA_PAULISTA,
                        VENUSTIANO_CARRANZA,
                        VERACRUZ,
                        VERONA,
                        VIAMAO,
                        VICTORIA,
                        VIENNA,
                        VIENTIANE,
                        VIGO,
                        VIJAYAWADA,
                        VILA_NOVA_DE_GAIA,
                        VILA_VELHA,
                        VILLA_BALLESTER,
                        VILLAVICENCIO,
                        VILNIUS,
                        VINA_DEL_MAR,
                        VINNYTSIA,
                        VIRGINIA_BEACH,
                        VISAKHAPATNAM,
                        VITORIA,
                        VITORIA_DA_CONQUISTA,
                        VITORIA_DE_SANTO_ANTAO,
                        VOLTA_REDONDA,
                        VORONEZH,
                        WARSAW,
                        WASHINGTON,
                        WELLINGTON,
                        WEST_PALM_BEACH,
                        WICHITA,
                        WILLEMSTAD,
                        WILMINGTON,
                        WINDHOEK,
                        WINDSOR,
                        WINNIPEG,
                        WOLVERHAMPTON,
                        WOODBRIDGE,
                        WROCLAW,
                        WUPPERTAL,
                        XALAPA,
                        YALOVA,
                        YANGON,
                        YEKATERINBURG,
                        YEREVAN,
                        YOGYAKARTA,
                        YOKOHAMA,
                        YONGIN_SI,
                        ZABRZE,
                        ZAGAZIG,
                        ZAGREB,
                        ZAMBOANGA_CITY,
                        ZAPOPAN,
                        ZAPORIZHZHYA,
                        ZARAGOZA,
                        ZHONGLI_DISTRICT,
                        ZIELONA_GORA,
                        ZONGULDAK,
                        ZURICH,
                    }

                    enum class Value {
                        A_CORUNA,
                        ABIDJAN,
                        ABU_DHABI,
                        ABUJA,
                        ACAPULCO_DE_JUAREZ,
                        ACCRA,
                        ADANA,
                        ADAPAZARI,
                        ADDIS_ABABA,
                        ADELAIDE,
                        AFYONKARAHISAR,
                        AGADIR,
                        AGUAS_LINDAS_DE_GOIAS,
                        AGUASCALIENTES,
                        AHMEDABAD,
                        AIZAWL,
                        AJMAN,
                        AKRON,
                        AKSARAY,
                        AL_AIN_CITY,
                        AL_MANSURAH,
                        AL_QATIF,
                        ALAJUELA,
                        ALBANY,
                        ALBUQUERQUE,
                        ALEXANDRIA,
                        ALGIERS,
                        ALICANTE,
                        ALMADA,
                        ALMATY,
                        ALMERE_STAD,
                        ALVORADA,
                        AMADORA,
                        AMASYA,
                        AMBATO,
                        AMERICANA,
                        AMMAN,
                        AMSTERDAM,
                        ANANINDEUA,
                        ANAPOLIS,
                        ANGELES_CITY,
                        ANGERS,
                        ANGRA_DOS_REIS,
                        ANKARA,
                        ANTAKYA,
                        ANTALYA,
                        ANTANANARIVO,
                        ANTIPOLO_CITY,
                        ANTOFAGASTA,
                        ANTWERP,
                        APARECIDA_DE_GOIANIA,
                        APODACA,
                        ARACAJU,
                        ARACATUBA,
                        ARAD,
                        ARAGUAINA,
                        ARAPIRACA,
                        ARARAQUARA,
                        AREQUIPA,
                        ARICA,
                        ARLINGTON,
                        ARYANAH,
                        ASTANA,
                        ASUNCION,
                        ASYUT,
                        ATAKUM,
                        ATHENS,
                        ATIBAIA,
                        ATLANTA,
                        AUBURN,
                        AUCKLAND,
                        AURORA,
                        AUSTIN,
                        AVELLANEDA,
                        AYDIN,
                        AZCAPOTZALCO,
                        BACOLOD_CITY,
                        BACOOR,
                        BAGHDAD,
                        BAGUIO_CITY,
                        BAHIA_BLANCA,
                        BAKERSFIELD,
                        BAKU,
                        BALIKESIR,
                        BALIKPAPAN,
                        BALNEARIO_CAMBORIU,
                        BALTIMORE,
                        BANDAR_LAMPUNG,
                        BANDAR_SERI_BEGAWAN,
                        BANDUNG,
                        BANGKOK,
                        BANJA_LUKA,
                        BANJARMASIN,
                        BARCELONA,
                        BARI,
                        BARQUISIMETO,
                        BARRA_MANSA,
                        BARRANQUILLA,
                        BARUERI,
                        BATAM,
                        BATANGAS,
                        BATMAN,
                        BATNA_CITY,
                        BATON_ROUGE,
                        BATUMI,
                        BAURU,
                        BEIRUT,
                        BEJAIA,
                        BEKASI,
                        BELEM,
                        BELFAST,
                        BELFORD_ROXO,
                        BELGRADE,
                        BELO_HORIZONTE,
                        BENGALURU,
                        BENI_MELLAL,
                        BERAZATEGUI,
                        BERN,
                        BETIM,
                        BHARATPUR,
                        BHOPAL,
                        BHUBANESWAR,
                        BIALYSTOK,
                        BIEN_HOA,
                        BILBAO,
                        BILECIK,
                        BIRATNAGAR,
                        BIRMINGHAM,
                        BISHKEK,
                        BIZERTE,
                        BLIDA,
                        BLOEMFONTEIN,
                        BLOOMINGTON,
                        BLUMENAU,
                        BOA_VISTA,
                        BOCHUM,
                        BOGOR,
                        BOGOTA,
                        BOISE,
                        BOKSBURG,
                        BOLOGNA,
                        BOLU,
                        BORDEAUX,
                        BOSTON,
                        BOTUCATU,
                        BRADFORD,
                        BRAGA,
                        BRAGANCA_PAULISTA,
                        BRAMPTON,
                        BRASILIA,
                        BRASOV,
                        BRATISLAVA,
                        BREMEN,
                        BRESCIA,
                        BREST,
                        BRIDGETOWN,
                        BRISBANE,
                        BRISTOL,
                        BRNO,
                        BROOKLYN,
                        BRUSSELS,
                        BUCARAMANGA,
                        BUCHAREST,
                        BUDAPEST,
                        BUENOS_AIRES,
                        BUFFALO,
                        BUK_GU,
                        BUKHARA,
                        BURGAS,
                        BURNABY,
                        BURSA,
                        BUTUAN,
                        BYDGOSZCZ,
                        CABANATUAN_CITY,
                        CABO_FRIO,
                        CABUYAO,
                        CACHOEIRO_DE_ITAPEMIRIM,
                        CAGAYAN_DE_ORO,
                        CAGLIARI,
                        CAIRO,
                        CALAMBA,
                        CALGARY,
                        CALOOCAN_CITY,
                        CAMACARI,
                        CAMARAGIBE,
                        CAMPECHE,
                        CAMPINA_GRANDE,
                        CAMPINAS,
                        CAMPO_GRANDE,
                        CAMPO_LARGO,
                        CAMPOS_DOS_GOYTACAZES,
                        CAN_THO,
                        CANOAS,
                        CANTON,
                        CAPE_TOWN,
                        CARACAS,
                        CARAGUATATUBA,
                        CARAPICUIBA,
                        CARDIFF,
                        CARIACICA,
                        CARMONA,
                        CARTAGENA,
                        CARUARU,
                        CASABLANCA,
                        CASCAVEL,
                        CASEROS,
                        CASTANHAL,
                        CASTRIES,
                        CATALAO,
                        CATAMARCA,
                        CATANDUVA,
                        CATANIA,
                        CAUCAIA,
                        CAXIAS_DO_SUL,
                        CEBU_CITY,
                        CENTRAL,
                        CENTRO,
                        CENTURION,
                        CHAGUANAS,
                        CHANDIGARH,
                        CHANDLER,
                        CHANG_HUA,
                        CHAPECO,
                        CHARLESTON,
                        CHARLOTTE,
                        CHELYABINSK,
                        CHENNAI,
                        CHERKASY,
                        CHERNIVTSI,
                        CHIA,
                        CHIANG_MAI,
                        CHICLAYO,
                        CHIHUAHUA_CITY,
                        CHIMBOTE,
                        CHISINAU,
                        CHITTAGONG,
                        CHRISTCHURCH,
                        CINCINNATI,
                        CIREBON,
                        CITY_OF_MUNTINLUPA,
                        CIUDAD_DEL_ESTE,
                        CIUDAD_GUAYANA,
                        CIUDAD_JUAREZ,
                        CIUDAD_NEZAHUALCOYOTL,
                        CIUDAD_OBREGON,
                        CLEVELAND,
                        CLUJ_NAPOCA,
                        COCHABAMBA,
                        COIMBATORE,
                        COIMBRA,
                        COLOGNE,
                        COLOMBO,
                        COLORADO_SPRINGS,
                        COLUMBIA,
                        COLUMBUS,
                        COMODORO_RIVADAVIA,
                        CONCEPCION,
                        CONCORD,
                        CONSTANTA,
                        CONSTANTINE,
                        CONTAGEM,
                        COPENHAGEN,
                        CORDOBA,
                        CORRIENTES,
                        CORUM,
                        COTIA,
                        COVENTRY,
                        CRAIOVA,
                        CRICIUMA,
                        CROYDON,
                        CUAUTITLAN_IZCALLI,
                        CUCUTA,
                        CUENCA,
                        CUERNAVACA,
                        CUIABA,
                        CULIACAN,
                        CURITIBA,
                        CUSCO,
                        DA_NANG,
                        DAGUPAN,
                        DAKAR,
                        DALLAS,
                        DAMIETTA,
                        DAMMAM,
                        DAR_ES_SALAAM,
                        DASMARINAS,
                        DAVAO_CITY,
                        DAYTON,
                        DEBRECEN,
                        DECATUR,
                        DEHRADUN,
                        DELHI,
                        DENIZLI,
                        DENPASAR,
                        DENVER,
                        DEPOK,
                        DERBY,
                        DETROIT,
                        DHAKA,
                        DIADEMA,
                        DIVINOPOLIS,
                        DIYARBAKIR,
                        DJELFA,
                        DNIPRO,
                        DOHA,
                        DORTMUND,
                        DOURADOS,
                        DRESDEN,
                        DUBAI,
                        DUBLIN,
                        DUEZCE,
                        DUISBURG,
                        DUQUE_DE_CAXIAS,
                        DURANGO,
                        DURBAN,
                        DUSSELDORF,
                        ECATEPEC,
                        EDINBURGH,
                        EDIRNE,
                        EDMONTON,
                        EL_JADIDA,
                        EL_PASO,
                        ELAZIG,
                        EMBU,
                        ENSENADA,
                        ERBIL,
                        ERZURUM,
                        ESKISEHIR,
                        ESPOO,
                        ESSEN,
                        FAISALABAD,
                        FAYETTEVILLE,
                        FAZENDA_RIO_GRANDE,
                        FEIRA_DE_SANTANA,
                        FES,
                        FLORENCE,
                        FLORENCIO_VARELA,
                        FLORIANOPOLIS,
                        FONTANA,
                        FORMOSA,
                        FORT_LAUDERDALE,
                        FORT_WAYNE,
                        FORT_WORTH,
                        FORTALEZA,
                        FOZ_DO_IGUACU,
                        FRANCA,
                        FRANCISCO_MORATO,
                        FRANCO_DA_ROCHA,
                        FRANKFURT_AM_MAIN,
                        FREDERICKSBURG,
                        FRESNO,
                        FUNCHAL,
                        GABORONE,
                        GAINESVILLE,
                        GALATI,
                        GANGNAM_GU,
                        GARANHUNS,
                        GATINEAU,
                        GAZIANTEP,
                        GDANSK,
                        GDYNIA,
                        GENERAL_TRIAS,
                        GENEVA,
                        GENOA,
                        GEORGE_TOWN,
                        GEORGETOWN,
                        GHAZIABAD,
                        GHENT,
                        GIJON,
                        GIRESUN,
                        GIZA,
                        GLASGOW,
                        GLENDALE,
                        GLIWICE,
                        GOIANIA,
                        GOMEL,
                        GOTHENBURG,
                        GOVERNADOR_VALADARES,
                        GOYANG_SI,
                        GRANADA,
                        GRAND_RAPIDS,
                        GRAVATAI,
                        GRAZ,
                        GREENSBORO,
                        GREENVILLE,
                        GUADALAJARA,
                        GUADALUPE,
                        GUANGZHOU,
                        GUARAPUAVA,
                        GUARATINGUETA,
                        GUARUJA,
                        GUARULHOS,
                        GUATEMALA_CITY,
                        GUAYAQUIL,
                        GUJRANWALA,
                        GURUGRAM,
                        GUSTAVO_ADOLFO_MADERO,
                        GUWAHATI,
                        GWANAK_GU,
                        HACKNEY,
                        HAIFA,
                        HAIPHONG,
                        HAMBURG,
                        HAMILTON,
                        HANOI,
                        HANOVER,
                        HARARE,
                        HAVANA,
                        HELSINKI,
                        HENDERSON,
                        HEREDIA,
                        HERMOSILLO,
                        HIALEAH,
                        HO_CHI_MINH_CITY,
                        HOLLYWOOD,
                        HOLON,
                        HONOLULU,
                        HORTOLANDIA,
                        HRODNA,
                        HSINCHU,
                        HUANCAYO,
                        HUANUCO,
                        HULL,
                        HURLINGHAM,
                        HYDERABAD,
                        IASI,
                        IBAGUE,
                        ICA,
                        ILAM,
                        ILFORD,
                        ILIGAN,
                        ILOILO_CITY,
                        IMPERATRIZ,
                        IMUS,
                        INCHEON,
                        INDAIATUBA,
                        INDIANAPOLIS,
                        INDORE,
                        IPATINGA,
                        IPOH,
                        IQUIQUE,
                        IRVINE,
                        ISIDRO_CASANOVA,
                        ISLAMABAD,
                        ISLINGTON,
                        ISMAILIA,
                        ISPARTA,
                        ISTANBUL,
                        ITABORAI,
                        ITABUNA,
                        ITAJAI,
                        ITANHAEM,
                        ITAPEVI,
                        ITAQUAQUECETUBA,
                        ITUZAINGO,
                        IZMIR,
                        IZTAPALAPA,
                        JABOATAO_DOS_GUARARAPES,
                        JACAREI,
                        JACKSON,
                        JACKSONVILLE,
                        JAIPUR,
                        JAKARTA,
                        JARAGUA_DO_SUL,
                        JAU,
                        JEDDAH,
                        JEMBER,
                        JERUSALEM,
                        JOAO_MONLEVADE,
                        JOAO_PESSOA,
                        JODHPUR,
                        JOHANNESBURG,
                        JOHOR_BAHRU,
                        JOINVILLE,
                        JOSE_C_PAZ,
                        JOSE_MARIA_EZEIZA,
                        JUAREZ,
                        JUAZEIRO_DO_NORTE,
                        JUIZ_DE_FORA,
                        JUNDIAI,
                        KAHRAMANMARAS,
                        KAMPALA,
                        KANPUR,
                        KANSAS_CITY,
                        KAOHSIUNG_CITY,
                        KARABUK,
                        KARACHI,
                        KARLSRUHE,
                        KARNAL,
                        KASKI,
                        KASTAMONU,
                        KATHMANDU,
                        KATOWICE,
                        KATSINA,
                        KATY,
                        KAUNAS,
                        KAYSERI,
                        KAZAN,
                        KECSKEMET,
                        KEDIRI,
                        KENITRA,
                        KHARKIV,
                        KHMELNYTSKYI,
                        KHON_KAEN,
                        KIELCE,
                        KIGALI,
                        KINGSTON,
                        KIRKLARELI,
                        KISSIMMEE,
                        KITCHENER,
                        KLAIPEDA,
                        KNOXVILLE,
                        KOCHI,
                        KOLKATA,
                        KOLLAM,
                        KONYA,
                        KOSEKOY,
                        KOSICE,
                        KOTA_KINABALU,
                        KOZHIKODE,
                        KRAKOW,
                        KRASNODAR,
                        KRYVYI_RIH,
                        KUALA_LUMPUR,
                        KUCHING,
                        KUTAHYA,
                        KUTAISI,
                        KUWAIT_CITY,
                        KYIV,
                        LA_PAZ,
                        LA_PLATA,
                        LA_RIOJA,
                        LA_SERENA,
                        LAFAYETTE,
                        LAFERRERE,
                        LAGES,
                        LAGOS,
                        LAHORE,
                        LAHUG,
                        LAKE_WORTH,
                        LAKELAND,
                        LANCASTER,
                        LANUS,
                        LAS_PALMAS_DE_GRAN_CANARIA,
                        LAS_PINAS,
                        LAS_VEGAS,
                        LAUSANNE,
                        LAVAL,
                        LAWRENCEVILLE,
                        LE_MANS,
                        LEEDS,
                        LEICESTER,
                        LEIPZIG,
                        LEON,
                        LEXINGTON,
                        LIBREVILLE,
                        LIEGE,
                        LILLE,
                        LIMA,
                        LIMASSOL,
                        LIMEIRA,
                        LINCOLN,
                        LINHARES,
                        LIPA_CITY,
                        LISBON,
                        LIVERPOOL,
                        LJUBLJANA,
                        LODZ,
                        LOJA,
                        LOMAS_DE_ZAMORA,
                        LOME,
                        LONDRINA,
                        LONG_BEACH,
                        LONGUEUIL,
                        LOUISVILLE,
                        LUANDA,
                        LUBLIN,
                        LUCENA_CITY,
                        LUCKNOW,
                        LUDHIANA,
                        LUSAKA,
                        LUXEMBOURG,
                        LUZIANIA,
                        LVIV,
                        LYON,
                        MABALACAT,
                        MACAE,
                        MACAO,
                        MACAPA,
                        MACEIO,
                        MACHALA,
                        MADISON,
                        MADRID,
                        MAGE,
                        MAGELANG,
                        MAGNESIA_AD_SIPYLUM,
                        MAKASSAR,
                        MAKATI_CITY,
                        MALABON,
                        MALAGA,
                        MALANG,
                        MALAPPURAM,
                        MALDONADO,
                        MALE,
                        MALMO,
                        MANADO,
                        MANAGUA,
                        MANAMA,
                        MANAUS,
                        MANCHESTER,
                        MANDALUYONG_CITY,
                        MANILA,
                        MANIZALES,
                        MANNHEIM,
                        MAPUTO,
                        MAR_DEL_PLATA,
                        MARABA,
                        MARACAIBO,
                        MARACANAU,
                        MARACAY,
                        MARDIN,
                        MARIBOR,
                        MARICA,
                        MARIETTA,
                        MARIKINA_CITY,
                        MARILIA,
                        MARINGA,
                        MARRAKESH,
                        MARSEILLE,
                        MAUA,
                        MAZATLAN,
                        MEDAN,
                        MEDELLIN,
                        MEDINA,
                        MEERUT,
                        MEKNES,
                        MELBOURNE,
                        MEMPHIS,
                        MENDOZA,
                        MERIDA,
                        MERKEZ,
                        MERLO,
                        MERSIN,
                        MESA,
                        MEXICALI,
                        MEXICO_CITY,
                        MILAN,
                        MILTON_KEYNES,
                        MILWAUKEE,
                        MINNEAPOLIS,
                        MINSK,
                        MISKOLC,
                        MISSISSAUGA,
                        MOGI_DAS_CRUZES,
                        MOHALI,
                        MONROE,
                        MONTE_GRANDE,
                        MONTEGO_BAY,
                        MONTERREY,
                        MONTES_CLAROS,
                        MONTEVIDEO,
                        MONTGOMERY,
                        MONTPELLIER,
                        MONTREAL,
                        MORELIA,
                        MORENO,
                        MORON,
                        MOSSORO,
                        MUGLA,
                        MULTAN,
                        MUMBAI,
                        MUNICH,
                        MURCIA,
                        MUSCAT,
                        MUZAFFARGARH,
                        MYKOLAYIV,
                        NAALDWIJK,
                        NAGA,
                        NAGPUR,
                        NAIROBI,
                        NANTES,
                        NAPLES,
                        NASHVILLE,
                        NASSAU,
                        NASUGBU,
                        NATAL,
                        NAUCALPAN,
                        NAVI_MUMBAI,
                        NEIVA,
                        NEUQUEN,
                        NEVSEHIR,
                        NEW_DELHI,
                        NEW_ORLEANS,
                        NEW_TAIPEI,
                        NEWARK,
                        NEWCASTLE_UPON_TYNE,
                        NHA_TRANG,
                        NICE,
                        NICOSIA,
                        NILOPOLIS,
                        NIS,
                        NITEROI,
                        NITRA,
                        NIZHNIY_NOVGOROD,
                        NOGALES,
                        NOIDA,
                        NORTHAMPTON,
                        NORWICH,
                        NOTTINGHAM,
                        NOVA_FRIBURGO,
                        NOVA_IGUACU,
                        NOVI_SAD,
                        NOVO_HAMBURGO,
                        NOVOSIBIRSK,
                        NUREMBERG,
                        OAKLAND,
                        OAXACA_CITY,
                        ODESA,
                        OKLAHOMA_CITY,
                        OLINDA,
                        OLOMOUC,
                        OLONGAPO_CITY,
                        OLSZTYN,
                        OMAHA,
                        ORADEA,
                        ORAN,
                        ORDU,
                        ORLANDO,
                        OSASCO,
                        OSLO,
                        OSMANIYE,
                        OSTRAVA,
                        OTTAWA,
                        OUJDA,
                        OURINHOS,
                        PACHUCA,
                        PADOVA,
                        PALAKKAD,
                        PALEMBANG,
                        PALERMO,
                        PALHOCA,
                        PALMA,
                        PALMAS,
                        PANAMA_CITY,
                        PARAMARIBO,
                        PARANA,
                        PARANAGUA,
                        PARANAQUE_CITY,
                        PARAUAPEBAS,
                        PARIS,
                        PARNAIBA,
                        PARNAMIRIM,
                        PASSO_FUNDO,
                        PASTO,
                        PATAN,
                        PATNA,
                        PATOS_DE_MINAS,
                        PAULISTA,
                        PECS,
                        PEKANBARU,
                        PELOTAS,
                        PEORIA,
                        PEREIRA,
                        PERM,
                        PERTH,
                        PESCARA,
                        PESHAWAR,
                        PETAH_TIKVA,
                        PETALING_JAYA,
                        PETROLINA,
                        PETROPOLIS,
                        PHILADELPHIA,
                        PHNOM_PENH,
                        PHOENIX,
                        PILAR,
                        PINDAMONHANGABA,
                        PIRACICABA,
                        PITESTI,
                        PITTSBURGH,
                        PIURA,
                        PLANO,
                        PLOIESTI,
                        PLOVDIV,
                        PLYMOUTH,
                        POCOS_DE_CALDAS,
                        PODGORICA,
                        POLTAVA,
                        PONTA_GROSSA,
                        PONTIANAK,
                        POPAYAN,
                        PORT_AU_PRINCE,
                        PORT_ELIZABETH,
                        PORT_HARCOURT,
                        PORT_LOUIS,
                        PORT_MONTT,
                        PORT_OF_SPAIN,
                        PORT_SAID,
                        PORTLAND,
                        PORTO,
                        PORTO_ALEGRE,
                        PORTO_SEGURO,
                        PORTO_VELHO,
                        PORTOVIEJO,
                        POSADAS,
                        POUSO_ALEGRE,
                        POZNAN,
                        PRAGUE,
                        PRAIA_GRANDE,
                        PRESIDENTE_PRUDENTE,
                        PRETORIA,
                        PRISTINA,
                        PROVIDENCE,
                        PUCALLPA,
                        PUCHONG_BATU_DUA_BELAS,
                        PUEBLA_CITY,
                        PUNE,
                        QUEBEC,
                        QUEENS,
                        QUEIMADOS,
                        QUERETARO_CITY,
                        QUEZON_CITY,
                        QUILMES,
                        QUITO,
                        RABAT,
                        RAIPUR,
                        RAJKOT,
                        RAJSHAHI,
                        RALEIGH,
                        RAMAT_GAN,
                        RANCAGUA,
                        RANCHI,
                        RAS_AL_KHAIMAH,
                        RAWALPINDI,
                        READING,
                        RECIFE,
                        REGINA,
                        RENNES,
                        RENO,
                        RESISTENCIA,
                        REYKJAVIK,
                        REYNOSA,
                        RIBEIRAO_DAS_NEVES,
                        RIBEIRAO_PRETO,
                        RICHMOND,
                        RIGA,
                        RIO_BRANCO,
                        RIO_CLARO,
                        RIO_CUARTO,
                        RIO_DE_JANEIRO,
                        RIO_DO_SUL,
                        RIO_GALLEGOS,
                        RIO_GRANDE,
                        RISHON_LETSIYYON,
                        RIVERSIDE,
                        RIYADH,
                        RIZE,
                        ROCHESTER,
                        ROME,
                        RONDONOPOLIS,
                        ROSARIO,
                        ROSEAU,
                        ROSTOV_ON_DON,
                        ROTTERDAM,
                        ROUEN,
                        ROUSSE,
                        RZESZOW,
                        SACRAMENTO,
                        SAGAR,
                        SAINT_PAUL,
                        SALE,
                        SALT_LAKE_CITY,
                        SALTA,
                        SALTILLO,
                        SALVADOR,
                        SAMARA,
                        SAMARINDA,
                        SAMARKAND,
                        SAMSUN,
                        SAN_ANTONIO,
                        SAN_DIEGO,
                        SAN_FERNANDO,
                        SAN_FRANCISCO,
                        SAN_JOSE,
                        SAN_JOSE_DEL_MONTE,
                        SAN_JUAN,
                        SAN_JUSTO,
                        SAN_LUIS,
                        SAN_LUIS_POTOSI_CITY,
                        SAN_MIGUEL,
                        SAN_MIGUEL_DE_TUCUMAN,
                        SAN_PABLO_CITY,
                        SAN_PEDRO,
                        SAN_PEDRO_SULA,
                        SAN_SALVADOR,
                        SAN_SALVADOR_DE_JUJUY,
                        SANAA,
                        SANLIURFA,
                        SANTA_CRUZ,
                        SANTA_CRUZ_DE_TENERIFE,
                        SANTA_CRUZ_DO_SUL,
                        SANTA_FE,
                        SANTA_LUZIA,
                        SANTA_MARIA,
                        SANTA_MARTA,
                        SANTA_ROSA,
                        SANTAREM,
                        SANTIAGO,
                        SANTIAGO_DE_CALI,
                        SANTIAGO_DE_LOS_CABALLEROS,
                        SANTO_ANDRE,
                        SANTO_DOMINGO,
                        SANTO_DOMINGO_ESTE,
                        SANTOS,
                        SAO_BERNARDO_DO_CAMPO,
                        SAO_CARLOS,
                        SAO_GONCALO,
                        SAO_JOAO_DE_MERITI,
                        SAO_JOSE,
                        SAO_JOSE_DO_RIO_PRETO,
                        SAO_JOSE_DOS_CAMPOS,
                        SAO_JOSE_DOS_PINHAIS,
                        SAO_LEOPOLDO,
                        SAO_LUIS,
                        SAO_PAULO,
                        SAO_VICENTE,
                        SARAJEVO,
                        SASKATOON,
                        SCARBOROUGH,
                        SEATTLE,
                        SEMARANG,
                        SEO_GU,
                        SEONGNAM_SI,
                        SEOUL,
                        SERRA,
                        SETE_LAGOAS,
                        SETIF,
                        SETUBAL,
                        SEVILLE,
                        SFAX,
                        SHAH_ALAM,
                        SHANGHAI,
                        SHARJAH,
                        SHEFFIELD,
                        SHENZHEN,
                        SHIMLA,
                        SIAULIAI,
                        SIBIU,
                        SIDOARJO,
                        SIKAR,
                        SILVER_SPRING,
                        SINOP,
                        SIVAS,
                        SKIKDA,
                        SKOPJE,
                        SLOUGH,
                        SOBRAL,
                        SOFIA,
                        SOROCABA,
                        SOUSSE,
                        SOUTH_TANGERANG,
                        SOUTHAMPTON,
                        SOUTHWARK,
                        SPLIT,
                        SPOKANE,
                        SPRING,
                        SPRINGFIELD,
                        ST_LOUIS,
                        ST_PETERSBURG,
                        STARA_ZAGORA,
                        STATEN_ISLAND,
                        STOCKHOLM,
                        STOCKTON,
                        STOKE_ON_TRENT,
                        STRASBOURG,
                        STUTTGART,
                        SUMARE,
                        SURABAYA,
                        SURAKARTA,
                        SURAT,
                        SURREY,
                        SUWON,
                        SUZANO,
                        SYDNEY,
                        SZCZECIN,
                        SZEGED,
                        SZEKESFEHERVAR,
                        TABOAO_DA_SERRA,
                        TACNA,
                        TACOMA,
                        TAGUIG,
                        TAICHUNG,
                        TAINAN_CITY,
                        TAIPEI,
                        TALAVERA,
                        TALCA,
                        TALLAHASSEE,
                        TALLINN,
                        TAMPA,
                        TAMPERE,
                        TAMPICO,
                        TANGERANG,
                        TANGIER,
                        TANTA,
                        TANZA,
                        TAOYUAN_DISTRICT,
                        TAPPAHANNOCK,
                        TARLAC_CITY,
                        TASHKENT,
                        TASIKMALAYA,
                        TATUI,
                        TAUBATE,
                        TBILISI,
                        TEGUCIGALPA,
                        TEHRAN,
                        TEIXEIRA_DE_FREITAS,
                        TEKIRDAG,
                        TEL_AVIV,
                        TEMUCO,
                        TEPIC,
                        TERESINA,
                        TERNOPIL,
                        TERRASSA,
                        TETOUAN,
                        THANE,
                        THE_BRONX,
                        THE_HAGUE,
                        THESSALONIKI,
                        THIRUVANANTHAPURAM,
                        THRISSUR,
                        TIJUANA,
                        TIMISOARA,
                        TIRANA,
                        TLALNEPANTLA,
                        TLAXCALA_CITY,
                        TLEMCEN,
                        TOKAT_PROVINCE,
                        TOKYO,
                        TOLUCA,
                        TORONTO,
                        TORREON,
                        TOULOUSE,
                        TRABZON,
                        TRUJILLO,
                        TUBARAO,
                        TUCSON,
                        TUGUEGARAO_CITY,
                        TULSA,
                        TUNIS,
                        TUNJA,
                        TURIN,
                        TUXTLA_GUTIERREZ,
                        TUZLA,
                        UBERABA,
                        UBERLANDIA,
                        UFA,
                        ULAN_BATOR,
                        UMEDA,
                        URDANETA,
                        USAK,
                        VADODARA,
                        VALENCIA,
                        VALINHOS,
                        VALLADOLID,
                        VALLEDUPAR,
                        VALPARAISO,
                        VALPARAISO_DE_GOIAS,
                        VAN,
                        VANCOUVER,
                        VARANASI,
                        VARGINHA,
                        VARNA,
                        VARZEA_PAULISTA,
                        VENUSTIANO_CARRANZA,
                        VERACRUZ,
                        VERONA,
                        VIAMAO,
                        VICTORIA,
                        VIENNA,
                        VIENTIANE,
                        VIGO,
                        VIJAYAWADA,
                        VILA_NOVA_DE_GAIA,
                        VILA_VELHA,
                        VILLA_BALLESTER,
                        VILLAVICENCIO,
                        VILNIUS,
                        VINA_DEL_MAR,
                        VINNYTSIA,
                        VIRGINIA_BEACH,
                        VISAKHAPATNAM,
                        VITORIA,
                        VITORIA_DA_CONQUISTA,
                        VITORIA_DE_SANTO_ANTAO,
                        VOLTA_REDONDA,
                        VORONEZH,
                        WARSAW,
                        WASHINGTON,
                        WELLINGTON,
                        WEST_PALM_BEACH,
                        WICHITA,
                        WILLEMSTAD,
                        WILMINGTON,
                        WINDHOEK,
                        WINDSOR,
                        WINNIPEG,
                        WOLVERHAMPTON,
                        WOODBRIDGE,
                        WROCLAW,
                        WUPPERTAL,
                        XALAPA,
                        YALOVA,
                        YANGON,
                        YEKATERINBURG,
                        YEREVAN,
                        YOGYAKARTA,
                        YOKOHAMA,
                        YONGIN_SI,
                        ZABRZE,
                        ZAGAZIG,
                        ZAGREB,
                        ZAMBOANGA_CITY,
                        ZAPOPAN,
                        ZAPORIZHZHYA,
                        ZARAGOZA,
                        ZHONGLI_DISTRICT,
                        ZIELONA_GORA,
                        ZONGULDAK,
                        ZURICH,
                        _UNKNOWN,
                    }

                    fun value(): Value =
                        when (this) {
                            A_CORUNA -> Value.A_CORUNA
                            ABIDJAN -> Value.ABIDJAN
                            ABU_DHABI -> Value.ABU_DHABI
                            ABUJA -> Value.ABUJA
                            ACAPULCO_DE_JUAREZ -> Value.ACAPULCO_DE_JUAREZ
                            ACCRA -> Value.ACCRA
                            ADANA -> Value.ADANA
                            ADAPAZARI -> Value.ADAPAZARI
                            ADDIS_ABABA -> Value.ADDIS_ABABA
                            ADELAIDE -> Value.ADELAIDE
                            AFYONKARAHISAR -> Value.AFYONKARAHISAR
                            AGADIR -> Value.AGADIR
                            AGUAS_LINDAS_DE_GOIAS -> Value.AGUAS_LINDAS_DE_GOIAS
                            AGUASCALIENTES -> Value.AGUASCALIENTES
                            AHMEDABAD -> Value.AHMEDABAD
                            AIZAWL -> Value.AIZAWL
                            AJMAN -> Value.AJMAN
                            AKRON -> Value.AKRON
                            AKSARAY -> Value.AKSARAY
                            AL_AIN_CITY -> Value.AL_AIN_CITY
                            AL_MANSURAH -> Value.AL_MANSURAH
                            AL_QATIF -> Value.AL_QATIF
                            ALAJUELA -> Value.ALAJUELA
                            ALBANY -> Value.ALBANY
                            ALBUQUERQUE -> Value.ALBUQUERQUE
                            ALEXANDRIA -> Value.ALEXANDRIA
                            ALGIERS -> Value.ALGIERS
                            ALICANTE -> Value.ALICANTE
                            ALMADA -> Value.ALMADA
                            ALMATY -> Value.ALMATY
                            ALMERE_STAD -> Value.ALMERE_STAD
                            ALVORADA -> Value.ALVORADA
                            AMADORA -> Value.AMADORA
                            AMASYA -> Value.AMASYA
                            AMBATO -> Value.AMBATO
                            AMERICANA -> Value.AMERICANA
                            AMMAN -> Value.AMMAN
                            AMSTERDAM -> Value.AMSTERDAM
                            ANANINDEUA -> Value.ANANINDEUA
                            ANAPOLIS -> Value.ANAPOLIS
                            ANGELES_CITY -> Value.ANGELES_CITY
                            ANGERS -> Value.ANGERS
                            ANGRA_DOS_REIS -> Value.ANGRA_DOS_REIS
                            ANKARA -> Value.ANKARA
                            ANTAKYA -> Value.ANTAKYA
                            ANTALYA -> Value.ANTALYA
                            ANTANANARIVO -> Value.ANTANANARIVO
                            ANTIPOLO_CITY -> Value.ANTIPOLO_CITY
                            ANTOFAGASTA -> Value.ANTOFAGASTA
                            ANTWERP -> Value.ANTWERP
                            APARECIDA_DE_GOIANIA -> Value.APARECIDA_DE_GOIANIA
                            APODACA -> Value.APODACA
                            ARACAJU -> Value.ARACAJU
                            ARACATUBA -> Value.ARACATUBA
                            ARAD -> Value.ARAD
                            ARAGUAINA -> Value.ARAGUAINA
                            ARAPIRACA -> Value.ARAPIRACA
                            ARARAQUARA -> Value.ARARAQUARA
                            AREQUIPA -> Value.AREQUIPA
                            ARICA -> Value.ARICA
                            ARLINGTON -> Value.ARLINGTON
                            ARYANAH -> Value.ARYANAH
                            ASTANA -> Value.ASTANA
                            ASUNCION -> Value.ASUNCION
                            ASYUT -> Value.ASYUT
                            ATAKUM -> Value.ATAKUM
                            ATHENS -> Value.ATHENS
                            ATIBAIA -> Value.ATIBAIA
                            ATLANTA -> Value.ATLANTA
                            AUBURN -> Value.AUBURN
                            AUCKLAND -> Value.AUCKLAND
                            AURORA -> Value.AURORA
                            AUSTIN -> Value.AUSTIN
                            AVELLANEDA -> Value.AVELLANEDA
                            AYDIN -> Value.AYDIN
                            AZCAPOTZALCO -> Value.AZCAPOTZALCO
                            BACOLOD_CITY -> Value.BACOLOD_CITY
                            BACOOR -> Value.BACOOR
                            BAGHDAD -> Value.BAGHDAD
                            BAGUIO_CITY -> Value.BAGUIO_CITY
                            BAHIA_BLANCA -> Value.BAHIA_BLANCA
                            BAKERSFIELD -> Value.BAKERSFIELD
                            BAKU -> Value.BAKU
                            BALIKESIR -> Value.BALIKESIR
                            BALIKPAPAN -> Value.BALIKPAPAN
                            BALNEARIO_CAMBORIU -> Value.BALNEARIO_CAMBORIU
                            BALTIMORE -> Value.BALTIMORE
                            BANDAR_LAMPUNG -> Value.BANDAR_LAMPUNG
                            BANDAR_SERI_BEGAWAN -> Value.BANDAR_SERI_BEGAWAN
                            BANDUNG -> Value.BANDUNG
                            BANGKOK -> Value.BANGKOK
                            BANJA_LUKA -> Value.BANJA_LUKA
                            BANJARMASIN -> Value.BANJARMASIN
                            BARCELONA -> Value.BARCELONA
                            BARI -> Value.BARI
                            BARQUISIMETO -> Value.BARQUISIMETO
                            BARRA_MANSA -> Value.BARRA_MANSA
                            BARRANQUILLA -> Value.BARRANQUILLA
                            BARUERI -> Value.BARUERI
                            BATAM -> Value.BATAM
                            BATANGAS -> Value.BATANGAS
                            BATMAN -> Value.BATMAN
                            BATNA_CITY -> Value.BATNA_CITY
                            BATON_ROUGE -> Value.BATON_ROUGE
                            BATUMI -> Value.BATUMI
                            BAURU -> Value.BAURU
                            BEIRUT -> Value.BEIRUT
                            BEJAIA -> Value.BEJAIA
                            BEKASI -> Value.BEKASI
                            BELEM -> Value.BELEM
                            BELFAST -> Value.BELFAST
                            BELFORD_ROXO -> Value.BELFORD_ROXO
                            BELGRADE -> Value.BELGRADE
                            BELO_HORIZONTE -> Value.BELO_HORIZONTE
                            BENGALURU -> Value.BENGALURU
                            BENI_MELLAL -> Value.BENI_MELLAL
                            BERAZATEGUI -> Value.BERAZATEGUI
                            BERN -> Value.BERN
                            BETIM -> Value.BETIM
                            BHARATPUR -> Value.BHARATPUR
                            BHOPAL -> Value.BHOPAL
                            BHUBANESWAR -> Value.BHUBANESWAR
                            BIALYSTOK -> Value.BIALYSTOK
                            BIEN_HOA -> Value.BIEN_HOA
                            BILBAO -> Value.BILBAO
                            BILECIK -> Value.BILECIK
                            BIRATNAGAR -> Value.BIRATNAGAR
                            BIRMINGHAM -> Value.BIRMINGHAM
                            BISHKEK -> Value.BISHKEK
                            BIZERTE -> Value.BIZERTE
                            BLIDA -> Value.BLIDA
                            BLOEMFONTEIN -> Value.BLOEMFONTEIN
                            BLOOMINGTON -> Value.BLOOMINGTON
                            BLUMENAU -> Value.BLUMENAU
                            BOA_VISTA -> Value.BOA_VISTA
                            BOCHUM -> Value.BOCHUM
                            BOGOR -> Value.BOGOR
                            BOGOTA -> Value.BOGOTA
                            BOISE -> Value.BOISE
                            BOKSBURG -> Value.BOKSBURG
                            BOLOGNA -> Value.BOLOGNA
                            BOLU -> Value.BOLU
                            BORDEAUX -> Value.BORDEAUX
                            BOSTON -> Value.BOSTON
                            BOTUCATU -> Value.BOTUCATU
                            BRADFORD -> Value.BRADFORD
                            BRAGA -> Value.BRAGA
                            BRAGANCA_PAULISTA -> Value.BRAGANCA_PAULISTA
                            BRAMPTON -> Value.BRAMPTON
                            BRASILIA -> Value.BRASILIA
                            BRASOV -> Value.BRASOV
                            BRATISLAVA -> Value.BRATISLAVA
                            BREMEN -> Value.BREMEN
                            BRESCIA -> Value.BRESCIA
                            BREST -> Value.BREST
                            BRIDGETOWN -> Value.BRIDGETOWN
                            BRISBANE -> Value.BRISBANE
                            BRISTOL -> Value.BRISTOL
                            BRNO -> Value.BRNO
                            BROOKLYN -> Value.BROOKLYN
                            BRUSSELS -> Value.BRUSSELS
                            BUCARAMANGA -> Value.BUCARAMANGA
                            BUCHAREST -> Value.BUCHAREST
                            BUDAPEST -> Value.BUDAPEST
                            BUENOS_AIRES -> Value.BUENOS_AIRES
                            BUFFALO -> Value.BUFFALO
                            BUK_GU -> Value.BUK_GU
                            BUKHARA -> Value.BUKHARA
                            BURGAS -> Value.BURGAS
                            BURNABY -> Value.BURNABY
                            BURSA -> Value.BURSA
                            BUTUAN -> Value.BUTUAN
                            BYDGOSZCZ -> Value.BYDGOSZCZ
                            CABANATUAN_CITY -> Value.CABANATUAN_CITY
                            CABO_FRIO -> Value.CABO_FRIO
                            CABUYAO -> Value.CABUYAO
                            CACHOEIRO_DE_ITAPEMIRIM -> Value.CACHOEIRO_DE_ITAPEMIRIM
                            CAGAYAN_DE_ORO -> Value.CAGAYAN_DE_ORO
                            CAGLIARI -> Value.CAGLIARI
                            CAIRO -> Value.CAIRO
                            CALAMBA -> Value.CALAMBA
                            CALGARY -> Value.CALGARY
                            CALOOCAN_CITY -> Value.CALOOCAN_CITY
                            CAMACARI -> Value.CAMACARI
                            CAMARAGIBE -> Value.CAMARAGIBE
                            CAMPECHE -> Value.CAMPECHE
                            CAMPINA_GRANDE -> Value.CAMPINA_GRANDE
                            CAMPINAS -> Value.CAMPINAS
                            CAMPO_GRANDE -> Value.CAMPO_GRANDE
                            CAMPO_LARGO -> Value.CAMPO_LARGO
                            CAMPOS_DOS_GOYTACAZES -> Value.CAMPOS_DOS_GOYTACAZES
                            CAN_THO -> Value.CAN_THO
                            CANOAS -> Value.CANOAS
                            CANTON -> Value.CANTON
                            CAPE_TOWN -> Value.CAPE_TOWN
                            CARACAS -> Value.CARACAS
                            CARAGUATATUBA -> Value.CARAGUATATUBA
                            CARAPICUIBA -> Value.CARAPICUIBA
                            CARDIFF -> Value.CARDIFF
                            CARIACICA -> Value.CARIACICA
                            CARMONA -> Value.CARMONA
                            CARTAGENA -> Value.CARTAGENA
                            CARUARU -> Value.CARUARU
                            CASABLANCA -> Value.CASABLANCA
                            CASCAVEL -> Value.CASCAVEL
                            CASEROS -> Value.CASEROS
                            CASTANHAL -> Value.CASTANHAL
                            CASTRIES -> Value.CASTRIES
                            CATALAO -> Value.CATALAO
                            CATAMARCA -> Value.CATAMARCA
                            CATANDUVA -> Value.CATANDUVA
                            CATANIA -> Value.CATANIA
                            CAUCAIA -> Value.CAUCAIA
                            CAXIAS_DO_SUL -> Value.CAXIAS_DO_SUL
                            CEBU_CITY -> Value.CEBU_CITY
                            CENTRAL -> Value.CENTRAL
                            CENTRO -> Value.CENTRO
                            CENTURION -> Value.CENTURION
                            CHAGUANAS -> Value.CHAGUANAS
                            CHANDIGARH -> Value.CHANDIGARH
                            CHANDLER -> Value.CHANDLER
                            CHANG_HUA -> Value.CHANG_HUA
                            CHAPECO -> Value.CHAPECO
                            CHARLESTON -> Value.CHARLESTON
                            CHARLOTTE -> Value.CHARLOTTE
                            CHELYABINSK -> Value.CHELYABINSK
                            CHENNAI -> Value.CHENNAI
                            CHERKASY -> Value.CHERKASY
                            CHERNIVTSI -> Value.CHERNIVTSI
                            CHIA -> Value.CHIA
                            CHIANG_MAI -> Value.CHIANG_MAI
                            CHICLAYO -> Value.CHICLAYO
                            CHIHUAHUA_CITY -> Value.CHIHUAHUA_CITY
                            CHIMBOTE -> Value.CHIMBOTE
                            CHISINAU -> Value.CHISINAU
                            CHITTAGONG -> Value.CHITTAGONG
                            CHRISTCHURCH -> Value.CHRISTCHURCH
                            CINCINNATI -> Value.CINCINNATI
                            CIREBON -> Value.CIREBON
                            CITY_OF_MUNTINLUPA -> Value.CITY_OF_MUNTINLUPA
                            CIUDAD_DEL_ESTE -> Value.CIUDAD_DEL_ESTE
                            CIUDAD_GUAYANA -> Value.CIUDAD_GUAYANA
                            CIUDAD_JUAREZ -> Value.CIUDAD_JUAREZ
                            CIUDAD_NEZAHUALCOYOTL -> Value.CIUDAD_NEZAHUALCOYOTL
                            CIUDAD_OBREGON -> Value.CIUDAD_OBREGON
                            CLEVELAND -> Value.CLEVELAND
                            CLUJ_NAPOCA -> Value.CLUJ_NAPOCA
                            COCHABAMBA -> Value.COCHABAMBA
                            COIMBATORE -> Value.COIMBATORE
                            COIMBRA -> Value.COIMBRA
                            COLOGNE -> Value.COLOGNE
                            COLOMBO -> Value.COLOMBO
                            COLORADO_SPRINGS -> Value.COLORADO_SPRINGS
                            COLUMBIA -> Value.COLUMBIA
                            COLUMBUS -> Value.COLUMBUS
                            COMODORO_RIVADAVIA -> Value.COMODORO_RIVADAVIA
                            CONCEPCION -> Value.CONCEPCION
                            CONCORD -> Value.CONCORD
                            CONSTANTA -> Value.CONSTANTA
                            CONSTANTINE -> Value.CONSTANTINE
                            CONTAGEM -> Value.CONTAGEM
                            COPENHAGEN -> Value.COPENHAGEN
                            CORDOBA -> Value.CORDOBA
                            CORRIENTES -> Value.CORRIENTES
                            CORUM -> Value.CORUM
                            COTIA -> Value.COTIA
                            COVENTRY -> Value.COVENTRY
                            CRAIOVA -> Value.CRAIOVA
                            CRICIUMA -> Value.CRICIUMA
                            CROYDON -> Value.CROYDON
                            CUAUTITLAN_IZCALLI -> Value.CUAUTITLAN_IZCALLI
                            CUCUTA -> Value.CUCUTA
                            CUENCA -> Value.CUENCA
                            CUERNAVACA -> Value.CUERNAVACA
                            CUIABA -> Value.CUIABA
                            CULIACAN -> Value.CULIACAN
                            CURITIBA -> Value.CURITIBA
                            CUSCO -> Value.CUSCO
                            DA_NANG -> Value.DA_NANG
                            DAGUPAN -> Value.DAGUPAN
                            DAKAR -> Value.DAKAR
                            DALLAS -> Value.DALLAS
                            DAMIETTA -> Value.DAMIETTA
                            DAMMAM -> Value.DAMMAM
                            DAR_ES_SALAAM -> Value.DAR_ES_SALAAM
                            DASMARINAS -> Value.DASMARINAS
                            DAVAO_CITY -> Value.DAVAO_CITY
                            DAYTON -> Value.DAYTON
                            DEBRECEN -> Value.DEBRECEN
                            DECATUR -> Value.DECATUR
                            DEHRADUN -> Value.DEHRADUN
                            DELHI -> Value.DELHI
                            DENIZLI -> Value.DENIZLI
                            DENPASAR -> Value.DENPASAR
                            DENVER -> Value.DENVER
                            DEPOK -> Value.DEPOK
                            DERBY -> Value.DERBY
                            DETROIT -> Value.DETROIT
                            DHAKA -> Value.DHAKA
                            DIADEMA -> Value.DIADEMA
                            DIVINOPOLIS -> Value.DIVINOPOLIS
                            DIYARBAKIR -> Value.DIYARBAKIR
                            DJELFA -> Value.DJELFA
                            DNIPRO -> Value.DNIPRO
                            DOHA -> Value.DOHA
                            DORTMUND -> Value.DORTMUND
                            DOURADOS -> Value.DOURADOS
                            DRESDEN -> Value.DRESDEN
                            DUBAI -> Value.DUBAI
                            DUBLIN -> Value.DUBLIN
                            DUEZCE -> Value.DUEZCE
                            DUISBURG -> Value.DUISBURG
                            DUQUE_DE_CAXIAS -> Value.DUQUE_DE_CAXIAS
                            DURANGO -> Value.DURANGO
                            DURBAN -> Value.DURBAN
                            DUSSELDORF -> Value.DUSSELDORF
                            ECATEPEC -> Value.ECATEPEC
                            EDINBURGH -> Value.EDINBURGH
                            EDIRNE -> Value.EDIRNE
                            EDMONTON -> Value.EDMONTON
                            EL_JADIDA -> Value.EL_JADIDA
                            EL_PASO -> Value.EL_PASO
                            ELAZIG -> Value.ELAZIG
                            EMBU -> Value.EMBU
                            ENSENADA -> Value.ENSENADA
                            ERBIL -> Value.ERBIL
                            ERZURUM -> Value.ERZURUM
                            ESKISEHIR -> Value.ESKISEHIR
                            ESPOO -> Value.ESPOO
                            ESSEN -> Value.ESSEN
                            FAISALABAD -> Value.FAISALABAD
                            FAYETTEVILLE -> Value.FAYETTEVILLE
                            FAZENDA_RIO_GRANDE -> Value.FAZENDA_RIO_GRANDE
                            FEIRA_DE_SANTANA -> Value.FEIRA_DE_SANTANA
                            FES -> Value.FES
                            FLORENCE -> Value.FLORENCE
                            FLORENCIO_VARELA -> Value.FLORENCIO_VARELA
                            FLORIANOPOLIS -> Value.FLORIANOPOLIS
                            FONTANA -> Value.FONTANA
                            FORMOSA -> Value.FORMOSA
                            FORT_LAUDERDALE -> Value.FORT_LAUDERDALE
                            FORT_WAYNE -> Value.FORT_WAYNE
                            FORT_WORTH -> Value.FORT_WORTH
                            FORTALEZA -> Value.FORTALEZA
                            FOZ_DO_IGUACU -> Value.FOZ_DO_IGUACU
                            FRANCA -> Value.FRANCA
                            FRANCISCO_MORATO -> Value.FRANCISCO_MORATO
                            FRANCO_DA_ROCHA -> Value.FRANCO_DA_ROCHA
                            FRANKFURT_AM_MAIN -> Value.FRANKFURT_AM_MAIN
                            FREDERICKSBURG -> Value.FREDERICKSBURG
                            FRESNO -> Value.FRESNO
                            FUNCHAL -> Value.FUNCHAL
                            GABORONE -> Value.GABORONE
                            GAINESVILLE -> Value.GAINESVILLE
                            GALATI -> Value.GALATI
                            GANGNAM_GU -> Value.GANGNAM_GU
                            GARANHUNS -> Value.GARANHUNS
                            GATINEAU -> Value.GATINEAU
                            GAZIANTEP -> Value.GAZIANTEP
                            GDANSK -> Value.GDANSK
                            GDYNIA -> Value.GDYNIA
                            GENERAL_TRIAS -> Value.GENERAL_TRIAS
                            GENEVA -> Value.GENEVA
                            GENOA -> Value.GENOA
                            GEORGE_TOWN -> Value.GEORGE_TOWN
                            GEORGETOWN -> Value.GEORGETOWN
                            GHAZIABAD -> Value.GHAZIABAD
                            GHENT -> Value.GHENT
                            GIJON -> Value.GIJON
                            GIRESUN -> Value.GIRESUN
                            GIZA -> Value.GIZA
                            GLASGOW -> Value.GLASGOW
                            GLENDALE -> Value.GLENDALE
                            GLIWICE -> Value.GLIWICE
                            GOIANIA -> Value.GOIANIA
                            GOMEL -> Value.GOMEL
                            GOTHENBURG -> Value.GOTHENBURG
                            GOVERNADOR_VALADARES -> Value.GOVERNADOR_VALADARES
                            GOYANG_SI -> Value.GOYANG_SI
                            GRANADA -> Value.GRANADA
                            GRAND_RAPIDS -> Value.GRAND_RAPIDS
                            GRAVATAI -> Value.GRAVATAI
                            GRAZ -> Value.GRAZ
                            GREENSBORO -> Value.GREENSBORO
                            GREENVILLE -> Value.GREENVILLE
                            GUADALAJARA -> Value.GUADALAJARA
                            GUADALUPE -> Value.GUADALUPE
                            GUANGZHOU -> Value.GUANGZHOU
                            GUARAPUAVA -> Value.GUARAPUAVA
                            GUARATINGUETA -> Value.GUARATINGUETA
                            GUARUJA -> Value.GUARUJA
                            GUARULHOS -> Value.GUARULHOS
                            GUATEMALA_CITY -> Value.GUATEMALA_CITY
                            GUAYAQUIL -> Value.GUAYAQUIL
                            GUJRANWALA -> Value.GUJRANWALA
                            GURUGRAM -> Value.GURUGRAM
                            GUSTAVO_ADOLFO_MADERO -> Value.GUSTAVO_ADOLFO_MADERO
                            GUWAHATI -> Value.GUWAHATI
                            GWANAK_GU -> Value.GWANAK_GU
                            HACKNEY -> Value.HACKNEY
                            HAIFA -> Value.HAIFA
                            HAIPHONG -> Value.HAIPHONG
                            HAMBURG -> Value.HAMBURG
                            HAMILTON -> Value.HAMILTON
                            HANOI -> Value.HANOI
                            HANOVER -> Value.HANOVER
                            HARARE -> Value.HARARE
                            HAVANA -> Value.HAVANA
                            HELSINKI -> Value.HELSINKI
                            HENDERSON -> Value.HENDERSON
                            HEREDIA -> Value.HEREDIA
                            HERMOSILLO -> Value.HERMOSILLO
                            HIALEAH -> Value.HIALEAH
                            HO_CHI_MINH_CITY -> Value.HO_CHI_MINH_CITY
                            HOLLYWOOD -> Value.HOLLYWOOD
                            HOLON -> Value.HOLON
                            HONOLULU -> Value.HONOLULU
                            HORTOLANDIA -> Value.HORTOLANDIA
                            HRODNA -> Value.HRODNA
                            HSINCHU -> Value.HSINCHU
                            HUANCAYO -> Value.HUANCAYO
                            HUANUCO -> Value.HUANUCO
                            HULL -> Value.HULL
                            HURLINGHAM -> Value.HURLINGHAM
                            HYDERABAD -> Value.HYDERABAD
                            IASI -> Value.IASI
                            IBAGUE -> Value.IBAGUE
                            ICA -> Value.ICA
                            ILAM -> Value.ILAM
                            ILFORD -> Value.ILFORD
                            ILIGAN -> Value.ILIGAN
                            ILOILO_CITY -> Value.ILOILO_CITY
                            IMPERATRIZ -> Value.IMPERATRIZ
                            IMUS -> Value.IMUS
                            INCHEON -> Value.INCHEON
                            INDAIATUBA -> Value.INDAIATUBA
                            INDIANAPOLIS -> Value.INDIANAPOLIS
                            INDORE -> Value.INDORE
                            IPATINGA -> Value.IPATINGA
                            IPOH -> Value.IPOH
                            IQUIQUE -> Value.IQUIQUE
                            IRVINE -> Value.IRVINE
                            ISIDRO_CASANOVA -> Value.ISIDRO_CASANOVA
                            ISLAMABAD -> Value.ISLAMABAD
                            ISLINGTON -> Value.ISLINGTON
                            ISMAILIA -> Value.ISMAILIA
                            ISPARTA -> Value.ISPARTA
                            ISTANBUL -> Value.ISTANBUL
                            ITABORAI -> Value.ITABORAI
                            ITABUNA -> Value.ITABUNA
                            ITAJAI -> Value.ITAJAI
                            ITANHAEM -> Value.ITANHAEM
                            ITAPEVI -> Value.ITAPEVI
                            ITAQUAQUECETUBA -> Value.ITAQUAQUECETUBA
                            ITUZAINGO -> Value.ITUZAINGO
                            IZMIR -> Value.IZMIR
                            IZTAPALAPA -> Value.IZTAPALAPA
                            JABOATAO_DOS_GUARARAPES -> Value.JABOATAO_DOS_GUARARAPES
                            JACAREI -> Value.JACAREI
                            JACKSON -> Value.JACKSON
                            JACKSONVILLE -> Value.JACKSONVILLE
                            JAIPUR -> Value.JAIPUR
                            JAKARTA -> Value.JAKARTA
                            JARAGUA_DO_SUL -> Value.JARAGUA_DO_SUL
                            JAU -> Value.JAU
                            JEDDAH -> Value.JEDDAH
                            JEMBER -> Value.JEMBER
                            JERUSALEM -> Value.JERUSALEM
                            JOAO_MONLEVADE -> Value.JOAO_MONLEVADE
                            JOAO_PESSOA -> Value.JOAO_PESSOA
                            JODHPUR -> Value.JODHPUR
                            JOHANNESBURG -> Value.JOHANNESBURG
                            JOHOR_BAHRU -> Value.JOHOR_BAHRU
                            JOINVILLE -> Value.JOINVILLE
                            JOSE_C_PAZ -> Value.JOSE_C_PAZ
                            JOSE_MARIA_EZEIZA -> Value.JOSE_MARIA_EZEIZA
                            JUAREZ -> Value.JUAREZ
                            JUAZEIRO_DO_NORTE -> Value.JUAZEIRO_DO_NORTE
                            JUIZ_DE_FORA -> Value.JUIZ_DE_FORA
                            JUNDIAI -> Value.JUNDIAI
                            KAHRAMANMARAS -> Value.KAHRAMANMARAS
                            KAMPALA -> Value.KAMPALA
                            KANPUR -> Value.KANPUR
                            KANSAS_CITY -> Value.KANSAS_CITY
                            KAOHSIUNG_CITY -> Value.KAOHSIUNG_CITY
                            KARABUK -> Value.KARABUK
                            KARACHI -> Value.KARACHI
                            KARLSRUHE -> Value.KARLSRUHE
                            KARNAL -> Value.KARNAL
                            KASKI -> Value.KASKI
                            KASTAMONU -> Value.KASTAMONU
                            KATHMANDU -> Value.KATHMANDU
                            KATOWICE -> Value.KATOWICE
                            KATSINA -> Value.KATSINA
                            KATY -> Value.KATY
                            KAUNAS -> Value.KAUNAS
                            KAYSERI -> Value.KAYSERI
                            KAZAN -> Value.KAZAN
                            KECSKEMET -> Value.KECSKEMET
                            KEDIRI -> Value.KEDIRI
                            KENITRA -> Value.KENITRA
                            KHARKIV -> Value.KHARKIV
                            KHMELNYTSKYI -> Value.KHMELNYTSKYI
                            KHON_KAEN -> Value.KHON_KAEN
                            KIELCE -> Value.KIELCE
                            KIGALI -> Value.KIGALI
                            KINGSTON -> Value.KINGSTON
                            KIRKLARELI -> Value.KIRKLARELI
                            KISSIMMEE -> Value.KISSIMMEE
                            KITCHENER -> Value.KITCHENER
                            KLAIPEDA -> Value.KLAIPEDA
                            KNOXVILLE -> Value.KNOXVILLE
                            KOCHI -> Value.KOCHI
                            KOLKATA -> Value.KOLKATA
                            KOLLAM -> Value.KOLLAM
                            KONYA -> Value.KONYA
                            KOSEKOY -> Value.KOSEKOY
                            KOSICE -> Value.KOSICE
                            KOTA_KINABALU -> Value.KOTA_KINABALU
                            KOZHIKODE -> Value.KOZHIKODE
                            KRAKOW -> Value.KRAKOW
                            KRASNODAR -> Value.KRASNODAR
                            KRYVYI_RIH -> Value.KRYVYI_RIH
                            KUALA_LUMPUR -> Value.KUALA_LUMPUR
                            KUCHING -> Value.KUCHING
                            KUTAHYA -> Value.KUTAHYA
                            KUTAISI -> Value.KUTAISI
                            KUWAIT_CITY -> Value.KUWAIT_CITY
                            KYIV -> Value.KYIV
                            LA_PAZ -> Value.LA_PAZ
                            LA_PLATA -> Value.LA_PLATA
                            LA_RIOJA -> Value.LA_RIOJA
                            LA_SERENA -> Value.LA_SERENA
                            LAFAYETTE -> Value.LAFAYETTE
                            LAFERRERE -> Value.LAFERRERE
                            LAGES -> Value.LAGES
                            LAGOS -> Value.LAGOS
                            LAHORE -> Value.LAHORE
                            LAHUG -> Value.LAHUG
                            LAKE_WORTH -> Value.LAKE_WORTH
                            LAKELAND -> Value.LAKELAND
                            LANCASTER -> Value.LANCASTER
                            LANUS -> Value.LANUS
                            LAS_PALMAS_DE_GRAN_CANARIA -> Value.LAS_PALMAS_DE_GRAN_CANARIA
                            LAS_PINAS -> Value.LAS_PINAS
                            LAS_VEGAS -> Value.LAS_VEGAS
                            LAUSANNE -> Value.LAUSANNE
                            LAVAL -> Value.LAVAL
                            LAWRENCEVILLE -> Value.LAWRENCEVILLE
                            LE_MANS -> Value.LE_MANS
                            LEEDS -> Value.LEEDS
                            LEICESTER -> Value.LEICESTER
                            LEIPZIG -> Value.LEIPZIG
                            LEON -> Value.LEON
                            LEXINGTON -> Value.LEXINGTON
                            LIBREVILLE -> Value.LIBREVILLE
                            LIEGE -> Value.LIEGE
                            LILLE -> Value.LILLE
                            LIMA -> Value.LIMA
                            LIMASSOL -> Value.LIMASSOL
                            LIMEIRA -> Value.LIMEIRA
                            LINCOLN -> Value.LINCOLN
                            LINHARES -> Value.LINHARES
                            LIPA_CITY -> Value.LIPA_CITY
                            LISBON -> Value.LISBON
                            LIVERPOOL -> Value.LIVERPOOL
                            LJUBLJANA -> Value.LJUBLJANA
                            LODZ -> Value.LODZ
                            LOJA -> Value.LOJA
                            LOMAS_DE_ZAMORA -> Value.LOMAS_DE_ZAMORA
                            LOME -> Value.LOME
                            LONDRINA -> Value.LONDRINA
                            LONG_BEACH -> Value.LONG_BEACH
                            LONGUEUIL -> Value.LONGUEUIL
                            LOUISVILLE -> Value.LOUISVILLE
                            LUANDA -> Value.LUANDA
                            LUBLIN -> Value.LUBLIN
                            LUCENA_CITY -> Value.LUCENA_CITY
                            LUCKNOW -> Value.LUCKNOW
                            LUDHIANA -> Value.LUDHIANA
                            LUSAKA -> Value.LUSAKA
                            LUXEMBOURG -> Value.LUXEMBOURG
                            LUZIANIA -> Value.LUZIANIA
                            LVIV -> Value.LVIV
                            LYON -> Value.LYON
                            MABALACAT -> Value.MABALACAT
                            MACAE -> Value.MACAE
                            MACAO -> Value.MACAO
                            MACAPA -> Value.MACAPA
                            MACEIO -> Value.MACEIO
                            MACHALA -> Value.MACHALA
                            MADISON -> Value.MADISON
                            MADRID -> Value.MADRID
                            MAGE -> Value.MAGE
                            MAGELANG -> Value.MAGELANG
                            MAGNESIA_AD_SIPYLUM -> Value.MAGNESIA_AD_SIPYLUM
                            MAKASSAR -> Value.MAKASSAR
                            MAKATI_CITY -> Value.MAKATI_CITY
                            MALABON -> Value.MALABON
                            MALAGA -> Value.MALAGA
                            MALANG -> Value.MALANG
                            MALAPPURAM -> Value.MALAPPURAM
                            MALDONADO -> Value.MALDONADO
                            MALE -> Value.MALE
                            MALMO -> Value.MALMO
                            MANADO -> Value.MANADO
                            MANAGUA -> Value.MANAGUA
                            MANAMA -> Value.MANAMA
                            MANAUS -> Value.MANAUS
                            MANCHESTER -> Value.MANCHESTER
                            MANDALUYONG_CITY -> Value.MANDALUYONG_CITY
                            MANILA -> Value.MANILA
                            MANIZALES -> Value.MANIZALES
                            MANNHEIM -> Value.MANNHEIM
                            MAPUTO -> Value.MAPUTO
                            MAR_DEL_PLATA -> Value.MAR_DEL_PLATA
                            MARABA -> Value.MARABA
                            MARACAIBO -> Value.MARACAIBO
                            MARACANAU -> Value.MARACANAU
                            MARACAY -> Value.MARACAY
                            MARDIN -> Value.MARDIN
                            MARIBOR -> Value.MARIBOR
                            MARICA -> Value.MARICA
                            MARIETTA -> Value.MARIETTA
                            MARIKINA_CITY -> Value.MARIKINA_CITY
                            MARILIA -> Value.MARILIA
                            MARINGA -> Value.MARINGA
                            MARRAKESH -> Value.MARRAKESH
                            MARSEILLE -> Value.MARSEILLE
                            MAUA -> Value.MAUA
                            MAZATLAN -> Value.MAZATLAN
                            MEDAN -> Value.MEDAN
                            MEDELLIN -> Value.MEDELLIN
                            MEDINA -> Value.MEDINA
                            MEERUT -> Value.MEERUT
                            MEKNES -> Value.MEKNES
                            MELBOURNE -> Value.MELBOURNE
                            MEMPHIS -> Value.MEMPHIS
                            MENDOZA -> Value.MENDOZA
                            MERIDA -> Value.MERIDA
                            MERKEZ -> Value.MERKEZ
                            MERLO -> Value.MERLO
                            MERSIN -> Value.MERSIN
                            MESA -> Value.MESA
                            MEXICALI -> Value.MEXICALI
                            MEXICO_CITY -> Value.MEXICO_CITY
                            MILAN -> Value.MILAN
                            MILTON_KEYNES -> Value.MILTON_KEYNES
                            MILWAUKEE -> Value.MILWAUKEE
                            MINNEAPOLIS -> Value.MINNEAPOLIS
                            MINSK -> Value.MINSK
                            MISKOLC -> Value.MISKOLC
                            MISSISSAUGA -> Value.MISSISSAUGA
                            MOGI_DAS_CRUZES -> Value.MOGI_DAS_CRUZES
                            MOHALI -> Value.MOHALI
                            MONROE -> Value.MONROE
                            MONTE_GRANDE -> Value.MONTE_GRANDE
                            MONTEGO_BAY -> Value.MONTEGO_BAY
                            MONTERREY -> Value.MONTERREY
                            MONTES_CLAROS -> Value.MONTES_CLAROS
                            MONTEVIDEO -> Value.MONTEVIDEO
                            MONTGOMERY -> Value.MONTGOMERY
                            MONTPELLIER -> Value.MONTPELLIER
                            MONTREAL -> Value.MONTREAL
                            MORELIA -> Value.MORELIA
                            MORENO -> Value.MORENO
                            MORON -> Value.MORON
                            MOSSORO -> Value.MOSSORO
                            MUGLA -> Value.MUGLA
                            MULTAN -> Value.MULTAN
                            MUMBAI -> Value.MUMBAI
                            MUNICH -> Value.MUNICH
                            MURCIA -> Value.MURCIA
                            MUSCAT -> Value.MUSCAT
                            MUZAFFARGARH -> Value.MUZAFFARGARH
                            MYKOLAYIV -> Value.MYKOLAYIV
                            NAALDWIJK -> Value.NAALDWIJK
                            NAGA -> Value.NAGA
                            NAGPUR -> Value.NAGPUR
                            NAIROBI -> Value.NAIROBI
                            NANTES -> Value.NANTES
                            NAPLES -> Value.NAPLES
                            NASHVILLE -> Value.NASHVILLE
                            NASSAU -> Value.NASSAU
                            NASUGBU -> Value.NASUGBU
                            NATAL -> Value.NATAL
                            NAUCALPAN -> Value.NAUCALPAN
                            NAVI_MUMBAI -> Value.NAVI_MUMBAI
                            NEIVA -> Value.NEIVA
                            NEUQUEN -> Value.NEUQUEN
                            NEVSEHIR -> Value.NEVSEHIR
                            NEW_DELHI -> Value.NEW_DELHI
                            NEW_ORLEANS -> Value.NEW_ORLEANS
                            NEW_TAIPEI -> Value.NEW_TAIPEI
                            NEWARK -> Value.NEWARK
                            NEWCASTLE_UPON_TYNE -> Value.NEWCASTLE_UPON_TYNE
                            NHA_TRANG -> Value.NHA_TRANG
                            NICE -> Value.NICE
                            NICOSIA -> Value.NICOSIA
                            NILOPOLIS -> Value.NILOPOLIS
                            NIS -> Value.NIS
                            NITEROI -> Value.NITEROI
                            NITRA -> Value.NITRA
                            NIZHNIY_NOVGOROD -> Value.NIZHNIY_NOVGOROD
                            NOGALES -> Value.NOGALES
                            NOIDA -> Value.NOIDA
                            NORTHAMPTON -> Value.NORTHAMPTON
                            NORWICH -> Value.NORWICH
                            NOTTINGHAM -> Value.NOTTINGHAM
                            NOVA_FRIBURGO -> Value.NOVA_FRIBURGO
                            NOVA_IGUACU -> Value.NOVA_IGUACU
                            NOVI_SAD -> Value.NOVI_SAD
                            NOVO_HAMBURGO -> Value.NOVO_HAMBURGO
                            NOVOSIBIRSK -> Value.NOVOSIBIRSK
                            NUREMBERG -> Value.NUREMBERG
                            OAKLAND -> Value.OAKLAND
                            OAXACA_CITY -> Value.OAXACA_CITY
                            ODESA -> Value.ODESA
                            OKLAHOMA_CITY -> Value.OKLAHOMA_CITY
                            OLINDA -> Value.OLINDA
                            OLOMOUC -> Value.OLOMOUC
                            OLONGAPO_CITY -> Value.OLONGAPO_CITY
                            OLSZTYN -> Value.OLSZTYN
                            OMAHA -> Value.OMAHA
                            ORADEA -> Value.ORADEA
                            ORAN -> Value.ORAN
                            ORDU -> Value.ORDU
                            ORLANDO -> Value.ORLANDO
                            OSASCO -> Value.OSASCO
                            OSLO -> Value.OSLO
                            OSMANIYE -> Value.OSMANIYE
                            OSTRAVA -> Value.OSTRAVA
                            OTTAWA -> Value.OTTAWA
                            OUJDA -> Value.OUJDA
                            OURINHOS -> Value.OURINHOS
                            PACHUCA -> Value.PACHUCA
                            PADOVA -> Value.PADOVA
                            PALAKKAD -> Value.PALAKKAD
                            PALEMBANG -> Value.PALEMBANG
                            PALERMO -> Value.PALERMO
                            PALHOCA -> Value.PALHOCA
                            PALMA -> Value.PALMA
                            PALMAS -> Value.PALMAS
                            PANAMA_CITY -> Value.PANAMA_CITY
                            PARAMARIBO -> Value.PARAMARIBO
                            PARANA -> Value.PARANA
                            PARANAGUA -> Value.PARANAGUA
                            PARANAQUE_CITY -> Value.PARANAQUE_CITY
                            PARAUAPEBAS -> Value.PARAUAPEBAS
                            PARIS -> Value.PARIS
                            PARNAIBA -> Value.PARNAIBA
                            PARNAMIRIM -> Value.PARNAMIRIM
                            PASSO_FUNDO -> Value.PASSO_FUNDO
                            PASTO -> Value.PASTO
                            PATAN -> Value.PATAN
                            PATNA -> Value.PATNA
                            PATOS_DE_MINAS -> Value.PATOS_DE_MINAS
                            PAULISTA -> Value.PAULISTA
                            PECS -> Value.PECS
                            PEKANBARU -> Value.PEKANBARU
                            PELOTAS -> Value.PELOTAS
                            PEORIA -> Value.PEORIA
                            PEREIRA -> Value.PEREIRA
                            PERM -> Value.PERM
                            PERTH -> Value.PERTH
                            PESCARA -> Value.PESCARA
                            PESHAWAR -> Value.PESHAWAR
                            PETAH_TIKVA -> Value.PETAH_TIKVA
                            PETALING_JAYA -> Value.PETALING_JAYA
                            PETROLINA -> Value.PETROLINA
                            PETROPOLIS -> Value.PETROPOLIS
                            PHILADELPHIA -> Value.PHILADELPHIA
                            PHNOM_PENH -> Value.PHNOM_PENH
                            PHOENIX -> Value.PHOENIX
                            PILAR -> Value.PILAR
                            PINDAMONHANGABA -> Value.PINDAMONHANGABA
                            PIRACICABA -> Value.PIRACICABA
                            PITESTI -> Value.PITESTI
                            PITTSBURGH -> Value.PITTSBURGH
                            PIURA -> Value.PIURA
                            PLANO -> Value.PLANO
                            PLOIESTI -> Value.PLOIESTI
                            PLOVDIV -> Value.PLOVDIV
                            PLYMOUTH -> Value.PLYMOUTH
                            POCOS_DE_CALDAS -> Value.POCOS_DE_CALDAS
                            PODGORICA -> Value.PODGORICA
                            POLTAVA -> Value.POLTAVA
                            PONTA_GROSSA -> Value.PONTA_GROSSA
                            PONTIANAK -> Value.PONTIANAK
                            POPAYAN -> Value.POPAYAN
                            PORT_AU_PRINCE -> Value.PORT_AU_PRINCE
                            PORT_ELIZABETH -> Value.PORT_ELIZABETH
                            PORT_HARCOURT -> Value.PORT_HARCOURT
                            PORT_LOUIS -> Value.PORT_LOUIS
                            PORT_MONTT -> Value.PORT_MONTT
                            PORT_OF_SPAIN -> Value.PORT_OF_SPAIN
                            PORT_SAID -> Value.PORT_SAID
                            PORTLAND -> Value.PORTLAND
                            PORTO -> Value.PORTO
                            PORTO_ALEGRE -> Value.PORTO_ALEGRE
                            PORTO_SEGURO -> Value.PORTO_SEGURO
                            PORTO_VELHO -> Value.PORTO_VELHO
                            PORTOVIEJO -> Value.PORTOVIEJO
                            POSADAS -> Value.POSADAS
                            POUSO_ALEGRE -> Value.POUSO_ALEGRE
                            POZNAN -> Value.POZNAN
                            PRAGUE -> Value.PRAGUE
                            PRAIA_GRANDE -> Value.PRAIA_GRANDE
                            PRESIDENTE_PRUDENTE -> Value.PRESIDENTE_PRUDENTE
                            PRETORIA -> Value.PRETORIA
                            PRISTINA -> Value.PRISTINA
                            PROVIDENCE -> Value.PROVIDENCE
                            PUCALLPA -> Value.PUCALLPA
                            PUCHONG_BATU_DUA_BELAS -> Value.PUCHONG_BATU_DUA_BELAS
                            PUEBLA_CITY -> Value.PUEBLA_CITY
                            PUNE -> Value.PUNE
                            QUEBEC -> Value.QUEBEC
                            QUEENS -> Value.QUEENS
                            QUEIMADOS -> Value.QUEIMADOS
                            QUERETARO_CITY -> Value.QUERETARO_CITY
                            QUEZON_CITY -> Value.QUEZON_CITY
                            QUILMES -> Value.QUILMES
                            QUITO -> Value.QUITO
                            RABAT -> Value.RABAT
                            RAIPUR -> Value.RAIPUR
                            RAJKOT -> Value.RAJKOT
                            RAJSHAHI -> Value.RAJSHAHI
                            RALEIGH -> Value.RALEIGH
                            RAMAT_GAN -> Value.RAMAT_GAN
                            RANCAGUA -> Value.RANCAGUA
                            RANCHI -> Value.RANCHI
                            RAS_AL_KHAIMAH -> Value.RAS_AL_KHAIMAH
                            RAWALPINDI -> Value.RAWALPINDI
                            READING -> Value.READING
                            RECIFE -> Value.RECIFE
                            REGINA -> Value.REGINA
                            RENNES -> Value.RENNES
                            RENO -> Value.RENO
                            RESISTENCIA -> Value.RESISTENCIA
                            REYKJAVIK -> Value.REYKJAVIK
                            REYNOSA -> Value.REYNOSA
                            RIBEIRAO_DAS_NEVES -> Value.RIBEIRAO_DAS_NEVES
                            RIBEIRAO_PRETO -> Value.RIBEIRAO_PRETO
                            RICHMOND -> Value.RICHMOND
                            RIGA -> Value.RIGA
                            RIO_BRANCO -> Value.RIO_BRANCO
                            RIO_CLARO -> Value.RIO_CLARO
                            RIO_CUARTO -> Value.RIO_CUARTO
                            RIO_DE_JANEIRO -> Value.RIO_DE_JANEIRO
                            RIO_DO_SUL -> Value.RIO_DO_SUL
                            RIO_GALLEGOS -> Value.RIO_GALLEGOS
                            RIO_GRANDE -> Value.RIO_GRANDE
                            RISHON_LETSIYYON -> Value.RISHON_LETSIYYON
                            RIVERSIDE -> Value.RIVERSIDE
                            RIYADH -> Value.RIYADH
                            RIZE -> Value.RIZE
                            ROCHESTER -> Value.ROCHESTER
                            ROME -> Value.ROME
                            RONDONOPOLIS -> Value.RONDONOPOLIS
                            ROSARIO -> Value.ROSARIO
                            ROSEAU -> Value.ROSEAU
                            ROSTOV_ON_DON -> Value.ROSTOV_ON_DON
                            ROTTERDAM -> Value.ROTTERDAM
                            ROUEN -> Value.ROUEN
                            ROUSSE -> Value.ROUSSE
                            RZESZOW -> Value.RZESZOW
                            SACRAMENTO -> Value.SACRAMENTO
                            SAGAR -> Value.SAGAR
                            SAINT_PAUL -> Value.SAINT_PAUL
                            SALE -> Value.SALE
                            SALT_LAKE_CITY -> Value.SALT_LAKE_CITY
                            SALTA -> Value.SALTA
                            SALTILLO -> Value.SALTILLO
                            SALVADOR -> Value.SALVADOR
                            SAMARA -> Value.SAMARA
                            SAMARINDA -> Value.SAMARINDA
                            SAMARKAND -> Value.SAMARKAND
                            SAMSUN -> Value.SAMSUN
                            SAN_ANTONIO -> Value.SAN_ANTONIO
                            SAN_DIEGO -> Value.SAN_DIEGO
                            SAN_FERNANDO -> Value.SAN_FERNANDO
                            SAN_FRANCISCO -> Value.SAN_FRANCISCO
                            SAN_JOSE -> Value.SAN_JOSE
                            SAN_JOSE_DEL_MONTE -> Value.SAN_JOSE_DEL_MONTE
                            SAN_JUAN -> Value.SAN_JUAN
                            SAN_JUSTO -> Value.SAN_JUSTO
                            SAN_LUIS -> Value.SAN_LUIS
                            SAN_LUIS_POTOSI_CITY -> Value.SAN_LUIS_POTOSI_CITY
                            SAN_MIGUEL -> Value.SAN_MIGUEL
                            SAN_MIGUEL_DE_TUCUMAN -> Value.SAN_MIGUEL_DE_TUCUMAN
                            SAN_PABLO_CITY -> Value.SAN_PABLO_CITY
                            SAN_PEDRO -> Value.SAN_PEDRO
                            SAN_PEDRO_SULA -> Value.SAN_PEDRO_SULA
                            SAN_SALVADOR -> Value.SAN_SALVADOR
                            SAN_SALVADOR_DE_JUJUY -> Value.SAN_SALVADOR_DE_JUJUY
                            SANAA -> Value.SANAA
                            SANLIURFA -> Value.SANLIURFA
                            SANTA_CRUZ -> Value.SANTA_CRUZ
                            SANTA_CRUZ_DE_TENERIFE -> Value.SANTA_CRUZ_DE_TENERIFE
                            SANTA_CRUZ_DO_SUL -> Value.SANTA_CRUZ_DO_SUL
                            SANTA_FE -> Value.SANTA_FE
                            SANTA_LUZIA -> Value.SANTA_LUZIA
                            SANTA_MARIA -> Value.SANTA_MARIA
                            SANTA_MARTA -> Value.SANTA_MARTA
                            SANTA_ROSA -> Value.SANTA_ROSA
                            SANTAREM -> Value.SANTAREM
                            SANTIAGO -> Value.SANTIAGO
                            SANTIAGO_DE_CALI -> Value.SANTIAGO_DE_CALI
                            SANTIAGO_DE_LOS_CABALLEROS -> Value.SANTIAGO_DE_LOS_CABALLEROS
                            SANTO_ANDRE -> Value.SANTO_ANDRE
                            SANTO_DOMINGO -> Value.SANTO_DOMINGO
                            SANTO_DOMINGO_ESTE -> Value.SANTO_DOMINGO_ESTE
                            SANTOS -> Value.SANTOS
                            SAO_BERNARDO_DO_CAMPO -> Value.SAO_BERNARDO_DO_CAMPO
                            SAO_CARLOS -> Value.SAO_CARLOS
                            SAO_GONCALO -> Value.SAO_GONCALO
                            SAO_JOAO_DE_MERITI -> Value.SAO_JOAO_DE_MERITI
                            SAO_JOSE -> Value.SAO_JOSE
                            SAO_JOSE_DO_RIO_PRETO -> Value.SAO_JOSE_DO_RIO_PRETO
                            SAO_JOSE_DOS_CAMPOS -> Value.SAO_JOSE_DOS_CAMPOS
                            SAO_JOSE_DOS_PINHAIS -> Value.SAO_JOSE_DOS_PINHAIS
                            SAO_LEOPOLDO -> Value.SAO_LEOPOLDO
                            SAO_LUIS -> Value.SAO_LUIS
                            SAO_PAULO -> Value.SAO_PAULO
                            SAO_VICENTE -> Value.SAO_VICENTE
                            SARAJEVO -> Value.SARAJEVO
                            SASKATOON -> Value.SASKATOON
                            SCARBOROUGH -> Value.SCARBOROUGH
                            SEATTLE -> Value.SEATTLE
                            SEMARANG -> Value.SEMARANG
                            SEO_GU -> Value.SEO_GU
                            SEONGNAM_SI -> Value.SEONGNAM_SI
                            SEOUL -> Value.SEOUL
                            SERRA -> Value.SERRA
                            SETE_LAGOAS -> Value.SETE_LAGOAS
                            SETIF -> Value.SETIF
                            SETUBAL -> Value.SETUBAL
                            SEVILLE -> Value.SEVILLE
                            SFAX -> Value.SFAX
                            SHAH_ALAM -> Value.SHAH_ALAM
                            SHANGHAI -> Value.SHANGHAI
                            SHARJAH -> Value.SHARJAH
                            SHEFFIELD -> Value.SHEFFIELD
                            SHENZHEN -> Value.SHENZHEN
                            SHIMLA -> Value.SHIMLA
                            SIAULIAI -> Value.SIAULIAI
                            SIBIU -> Value.SIBIU
                            SIDOARJO -> Value.SIDOARJO
                            SIKAR -> Value.SIKAR
                            SILVER_SPRING -> Value.SILVER_SPRING
                            SINOP -> Value.SINOP
                            SIVAS -> Value.SIVAS
                            SKIKDA -> Value.SKIKDA
                            SKOPJE -> Value.SKOPJE
                            SLOUGH -> Value.SLOUGH
                            SOBRAL -> Value.SOBRAL
                            SOFIA -> Value.SOFIA
                            SOROCABA -> Value.SOROCABA
                            SOUSSE -> Value.SOUSSE
                            SOUTH_TANGERANG -> Value.SOUTH_TANGERANG
                            SOUTHAMPTON -> Value.SOUTHAMPTON
                            SOUTHWARK -> Value.SOUTHWARK
                            SPLIT -> Value.SPLIT
                            SPOKANE -> Value.SPOKANE
                            SPRING -> Value.SPRING
                            SPRINGFIELD -> Value.SPRINGFIELD
                            ST_LOUIS -> Value.ST_LOUIS
                            ST_PETERSBURG -> Value.ST_PETERSBURG
                            STARA_ZAGORA -> Value.STARA_ZAGORA
                            STATEN_ISLAND -> Value.STATEN_ISLAND
                            STOCKHOLM -> Value.STOCKHOLM
                            STOCKTON -> Value.STOCKTON
                            STOKE_ON_TRENT -> Value.STOKE_ON_TRENT
                            STRASBOURG -> Value.STRASBOURG
                            STUTTGART -> Value.STUTTGART
                            SUMARE -> Value.SUMARE
                            SURABAYA -> Value.SURABAYA
                            SURAKARTA -> Value.SURAKARTA
                            SURAT -> Value.SURAT
                            SURREY -> Value.SURREY
                            SUWON -> Value.SUWON
                            SUZANO -> Value.SUZANO
                            SYDNEY -> Value.SYDNEY
                            SZCZECIN -> Value.SZCZECIN
                            SZEGED -> Value.SZEGED
                            SZEKESFEHERVAR -> Value.SZEKESFEHERVAR
                            TABOAO_DA_SERRA -> Value.TABOAO_DA_SERRA
                            TACNA -> Value.TACNA
                            TACOMA -> Value.TACOMA
                            TAGUIG -> Value.TAGUIG
                            TAICHUNG -> Value.TAICHUNG
                            TAINAN_CITY -> Value.TAINAN_CITY
                            TAIPEI -> Value.TAIPEI
                            TALAVERA -> Value.TALAVERA
                            TALCA -> Value.TALCA
                            TALLAHASSEE -> Value.TALLAHASSEE
                            TALLINN -> Value.TALLINN
                            TAMPA -> Value.TAMPA
                            TAMPERE -> Value.TAMPERE
                            TAMPICO -> Value.TAMPICO
                            TANGERANG -> Value.TANGERANG
                            TANGIER -> Value.TANGIER
                            TANTA -> Value.TANTA
                            TANZA -> Value.TANZA
                            TAOYUAN_DISTRICT -> Value.TAOYUAN_DISTRICT
                            TAPPAHANNOCK -> Value.TAPPAHANNOCK
                            TARLAC_CITY -> Value.TARLAC_CITY
                            TASHKENT -> Value.TASHKENT
                            TASIKMALAYA -> Value.TASIKMALAYA
                            TATUI -> Value.TATUI
                            TAUBATE -> Value.TAUBATE
                            TBILISI -> Value.TBILISI
                            TEGUCIGALPA -> Value.TEGUCIGALPA
                            TEHRAN -> Value.TEHRAN
                            TEIXEIRA_DE_FREITAS -> Value.TEIXEIRA_DE_FREITAS
                            TEKIRDAG -> Value.TEKIRDAG
                            TEL_AVIV -> Value.TEL_AVIV
                            TEMUCO -> Value.TEMUCO
                            TEPIC -> Value.TEPIC
                            TERESINA -> Value.TERESINA
                            TERNOPIL -> Value.TERNOPIL
                            TERRASSA -> Value.TERRASSA
                            TETOUAN -> Value.TETOUAN
                            THANE -> Value.THANE
                            THE_BRONX -> Value.THE_BRONX
                            THE_HAGUE -> Value.THE_HAGUE
                            THESSALONIKI -> Value.THESSALONIKI
                            THIRUVANANTHAPURAM -> Value.THIRUVANANTHAPURAM
                            THRISSUR -> Value.THRISSUR
                            TIJUANA -> Value.TIJUANA
                            TIMISOARA -> Value.TIMISOARA
                            TIRANA -> Value.TIRANA
                            TLALNEPANTLA -> Value.TLALNEPANTLA
                            TLAXCALA_CITY -> Value.TLAXCALA_CITY
                            TLEMCEN -> Value.TLEMCEN
                            TOKAT_PROVINCE -> Value.TOKAT_PROVINCE
                            TOKYO -> Value.TOKYO
                            TOLUCA -> Value.TOLUCA
                            TORONTO -> Value.TORONTO
                            TORREON -> Value.TORREON
                            TOULOUSE -> Value.TOULOUSE
                            TRABZON -> Value.TRABZON
                            TRUJILLO -> Value.TRUJILLO
                            TUBARAO -> Value.TUBARAO
                            TUCSON -> Value.TUCSON
                            TUGUEGARAO_CITY -> Value.TUGUEGARAO_CITY
                            TULSA -> Value.TULSA
                            TUNIS -> Value.TUNIS
                            TUNJA -> Value.TUNJA
                            TURIN -> Value.TURIN
                            TUXTLA_GUTIERREZ -> Value.TUXTLA_GUTIERREZ
                            TUZLA -> Value.TUZLA
                            UBERABA -> Value.UBERABA
                            UBERLANDIA -> Value.UBERLANDIA
                            UFA -> Value.UFA
                            ULAN_BATOR -> Value.ULAN_BATOR
                            UMEDA -> Value.UMEDA
                            URDANETA -> Value.URDANETA
                            USAK -> Value.USAK
                            VADODARA -> Value.VADODARA
                            VALENCIA -> Value.VALENCIA
                            VALINHOS -> Value.VALINHOS
                            VALLADOLID -> Value.VALLADOLID
                            VALLEDUPAR -> Value.VALLEDUPAR
                            VALPARAISO -> Value.VALPARAISO
                            VALPARAISO_DE_GOIAS -> Value.VALPARAISO_DE_GOIAS
                            VAN -> Value.VAN
                            VANCOUVER -> Value.VANCOUVER
                            VARANASI -> Value.VARANASI
                            VARGINHA -> Value.VARGINHA
                            VARNA -> Value.VARNA
                            VARZEA_PAULISTA -> Value.VARZEA_PAULISTA
                            VENUSTIANO_CARRANZA -> Value.VENUSTIANO_CARRANZA
                            VERACRUZ -> Value.VERACRUZ
                            VERONA -> Value.VERONA
                            VIAMAO -> Value.VIAMAO
                            VICTORIA -> Value.VICTORIA
                            VIENNA -> Value.VIENNA
                            VIENTIANE -> Value.VIENTIANE
                            VIGO -> Value.VIGO
                            VIJAYAWADA -> Value.VIJAYAWADA
                            VILA_NOVA_DE_GAIA -> Value.VILA_NOVA_DE_GAIA
                            VILA_VELHA -> Value.VILA_VELHA
                            VILLA_BALLESTER -> Value.VILLA_BALLESTER
                            VILLAVICENCIO -> Value.VILLAVICENCIO
                            VILNIUS -> Value.VILNIUS
                            VINA_DEL_MAR -> Value.VINA_DEL_MAR
                            VINNYTSIA -> Value.VINNYTSIA
                            VIRGINIA_BEACH -> Value.VIRGINIA_BEACH
                            VISAKHAPATNAM -> Value.VISAKHAPATNAM
                            VITORIA -> Value.VITORIA
                            VITORIA_DA_CONQUISTA -> Value.VITORIA_DA_CONQUISTA
                            VITORIA_DE_SANTO_ANTAO -> Value.VITORIA_DE_SANTO_ANTAO
                            VOLTA_REDONDA -> Value.VOLTA_REDONDA
                            VORONEZH -> Value.VORONEZH
                            WARSAW -> Value.WARSAW
                            WASHINGTON -> Value.WASHINGTON
                            WELLINGTON -> Value.WELLINGTON
                            WEST_PALM_BEACH -> Value.WEST_PALM_BEACH
                            WICHITA -> Value.WICHITA
                            WILLEMSTAD -> Value.WILLEMSTAD
                            WILMINGTON -> Value.WILMINGTON
                            WINDHOEK -> Value.WINDHOEK
                            WINDSOR -> Value.WINDSOR
                            WINNIPEG -> Value.WINNIPEG
                            WOLVERHAMPTON -> Value.WOLVERHAMPTON
                            WOODBRIDGE -> Value.WOODBRIDGE
                            WROCLAW -> Value.WROCLAW
                            WUPPERTAL -> Value.WUPPERTAL
                            XALAPA -> Value.XALAPA
                            YALOVA -> Value.YALOVA
                            YANGON -> Value.YANGON
                            YEKATERINBURG -> Value.YEKATERINBURG
                            YEREVAN -> Value.YEREVAN
                            YOGYAKARTA -> Value.YOGYAKARTA
                            YOKOHAMA -> Value.YOKOHAMA
                            YONGIN_SI -> Value.YONGIN_SI
                            ZABRZE -> Value.ZABRZE
                            ZAGAZIG -> Value.ZAGAZIG
                            ZAGREB -> Value.ZAGREB
                            ZAMBOANGA_CITY -> Value.ZAMBOANGA_CITY
                            ZAPOPAN -> Value.ZAPOPAN
                            ZAPORIZHZHYA -> Value.ZAPORIZHZHYA
                            ZARAGOZA -> Value.ZARAGOZA
                            ZHONGLI_DISTRICT -> Value.ZHONGLI_DISTRICT
                            ZIELONA_GORA -> Value.ZIELONA_GORA
                            ZONGULDAK -> Value.ZONGULDAK
                            ZURICH -> Value.ZURICH
                            else -> Value._UNKNOWN
                        }

                    fun known(): Known =
                        when (this) {
                            A_CORUNA -> Known.A_CORUNA
                            ABIDJAN -> Known.ABIDJAN
                            ABU_DHABI -> Known.ABU_DHABI
                            ABUJA -> Known.ABUJA
                            ACAPULCO_DE_JUAREZ -> Known.ACAPULCO_DE_JUAREZ
                            ACCRA -> Known.ACCRA
                            ADANA -> Known.ADANA
                            ADAPAZARI -> Known.ADAPAZARI
                            ADDIS_ABABA -> Known.ADDIS_ABABA
                            ADELAIDE -> Known.ADELAIDE
                            AFYONKARAHISAR -> Known.AFYONKARAHISAR
                            AGADIR -> Known.AGADIR
                            AGUAS_LINDAS_DE_GOIAS -> Known.AGUAS_LINDAS_DE_GOIAS
                            AGUASCALIENTES -> Known.AGUASCALIENTES
                            AHMEDABAD -> Known.AHMEDABAD
                            AIZAWL -> Known.AIZAWL
                            AJMAN -> Known.AJMAN
                            AKRON -> Known.AKRON
                            AKSARAY -> Known.AKSARAY
                            AL_AIN_CITY -> Known.AL_AIN_CITY
                            AL_MANSURAH -> Known.AL_MANSURAH
                            AL_QATIF -> Known.AL_QATIF
                            ALAJUELA -> Known.ALAJUELA
                            ALBANY -> Known.ALBANY
                            ALBUQUERQUE -> Known.ALBUQUERQUE
                            ALEXANDRIA -> Known.ALEXANDRIA
                            ALGIERS -> Known.ALGIERS
                            ALICANTE -> Known.ALICANTE
                            ALMADA -> Known.ALMADA
                            ALMATY -> Known.ALMATY
                            ALMERE_STAD -> Known.ALMERE_STAD
                            ALVORADA -> Known.ALVORADA
                            AMADORA -> Known.AMADORA
                            AMASYA -> Known.AMASYA
                            AMBATO -> Known.AMBATO
                            AMERICANA -> Known.AMERICANA
                            AMMAN -> Known.AMMAN
                            AMSTERDAM -> Known.AMSTERDAM
                            ANANINDEUA -> Known.ANANINDEUA
                            ANAPOLIS -> Known.ANAPOLIS
                            ANGELES_CITY -> Known.ANGELES_CITY
                            ANGERS -> Known.ANGERS
                            ANGRA_DOS_REIS -> Known.ANGRA_DOS_REIS
                            ANKARA -> Known.ANKARA
                            ANTAKYA -> Known.ANTAKYA
                            ANTALYA -> Known.ANTALYA
                            ANTANANARIVO -> Known.ANTANANARIVO
                            ANTIPOLO_CITY -> Known.ANTIPOLO_CITY
                            ANTOFAGASTA -> Known.ANTOFAGASTA
                            ANTWERP -> Known.ANTWERP
                            APARECIDA_DE_GOIANIA -> Known.APARECIDA_DE_GOIANIA
                            APODACA -> Known.APODACA
                            ARACAJU -> Known.ARACAJU
                            ARACATUBA -> Known.ARACATUBA
                            ARAD -> Known.ARAD
                            ARAGUAINA -> Known.ARAGUAINA
                            ARAPIRACA -> Known.ARAPIRACA
                            ARARAQUARA -> Known.ARARAQUARA
                            AREQUIPA -> Known.AREQUIPA
                            ARICA -> Known.ARICA
                            ARLINGTON -> Known.ARLINGTON
                            ARYANAH -> Known.ARYANAH
                            ASTANA -> Known.ASTANA
                            ASUNCION -> Known.ASUNCION
                            ASYUT -> Known.ASYUT
                            ATAKUM -> Known.ATAKUM
                            ATHENS -> Known.ATHENS
                            ATIBAIA -> Known.ATIBAIA
                            ATLANTA -> Known.ATLANTA
                            AUBURN -> Known.AUBURN
                            AUCKLAND -> Known.AUCKLAND
                            AURORA -> Known.AURORA
                            AUSTIN -> Known.AUSTIN
                            AVELLANEDA -> Known.AVELLANEDA
                            AYDIN -> Known.AYDIN
                            AZCAPOTZALCO -> Known.AZCAPOTZALCO
                            BACOLOD_CITY -> Known.BACOLOD_CITY
                            BACOOR -> Known.BACOOR
                            BAGHDAD -> Known.BAGHDAD
                            BAGUIO_CITY -> Known.BAGUIO_CITY
                            BAHIA_BLANCA -> Known.BAHIA_BLANCA
                            BAKERSFIELD -> Known.BAKERSFIELD
                            BAKU -> Known.BAKU
                            BALIKESIR -> Known.BALIKESIR
                            BALIKPAPAN -> Known.BALIKPAPAN
                            BALNEARIO_CAMBORIU -> Known.BALNEARIO_CAMBORIU
                            BALTIMORE -> Known.BALTIMORE
                            BANDAR_LAMPUNG -> Known.BANDAR_LAMPUNG
                            BANDAR_SERI_BEGAWAN -> Known.BANDAR_SERI_BEGAWAN
                            BANDUNG -> Known.BANDUNG
                            BANGKOK -> Known.BANGKOK
                            BANJA_LUKA -> Known.BANJA_LUKA
                            BANJARMASIN -> Known.BANJARMASIN
                            BARCELONA -> Known.BARCELONA
                            BARI -> Known.BARI
                            BARQUISIMETO -> Known.BARQUISIMETO
                            BARRA_MANSA -> Known.BARRA_MANSA
                            BARRANQUILLA -> Known.BARRANQUILLA
                            BARUERI -> Known.BARUERI
                            BATAM -> Known.BATAM
                            BATANGAS -> Known.BATANGAS
                            BATMAN -> Known.BATMAN
                            BATNA_CITY -> Known.BATNA_CITY
                            BATON_ROUGE -> Known.BATON_ROUGE
                            BATUMI -> Known.BATUMI
                            BAURU -> Known.BAURU
                            BEIRUT -> Known.BEIRUT
                            BEJAIA -> Known.BEJAIA
                            BEKASI -> Known.BEKASI
                            BELEM -> Known.BELEM
                            BELFAST -> Known.BELFAST
                            BELFORD_ROXO -> Known.BELFORD_ROXO
                            BELGRADE -> Known.BELGRADE
                            BELO_HORIZONTE -> Known.BELO_HORIZONTE
                            BENGALURU -> Known.BENGALURU
                            BENI_MELLAL -> Known.BENI_MELLAL
                            BERAZATEGUI -> Known.BERAZATEGUI
                            BERN -> Known.BERN
                            BETIM -> Known.BETIM
                            BHARATPUR -> Known.BHARATPUR
                            BHOPAL -> Known.BHOPAL
                            BHUBANESWAR -> Known.BHUBANESWAR
                            BIALYSTOK -> Known.BIALYSTOK
                            BIEN_HOA -> Known.BIEN_HOA
                            BILBAO -> Known.BILBAO
                            BILECIK -> Known.BILECIK
                            BIRATNAGAR -> Known.BIRATNAGAR
                            BIRMINGHAM -> Known.BIRMINGHAM
                            BISHKEK -> Known.BISHKEK
                            BIZERTE -> Known.BIZERTE
                            BLIDA -> Known.BLIDA
                            BLOEMFONTEIN -> Known.BLOEMFONTEIN
                            BLOOMINGTON -> Known.BLOOMINGTON
                            BLUMENAU -> Known.BLUMENAU
                            BOA_VISTA -> Known.BOA_VISTA
                            BOCHUM -> Known.BOCHUM
                            BOGOR -> Known.BOGOR
                            BOGOTA -> Known.BOGOTA
                            BOISE -> Known.BOISE
                            BOKSBURG -> Known.BOKSBURG
                            BOLOGNA -> Known.BOLOGNA
                            BOLU -> Known.BOLU
                            BORDEAUX -> Known.BORDEAUX
                            BOSTON -> Known.BOSTON
                            BOTUCATU -> Known.BOTUCATU
                            BRADFORD -> Known.BRADFORD
                            BRAGA -> Known.BRAGA
                            BRAGANCA_PAULISTA -> Known.BRAGANCA_PAULISTA
                            BRAMPTON -> Known.BRAMPTON
                            BRASILIA -> Known.BRASILIA
                            BRASOV -> Known.BRASOV
                            BRATISLAVA -> Known.BRATISLAVA
                            BREMEN -> Known.BREMEN
                            BRESCIA -> Known.BRESCIA
                            BREST -> Known.BREST
                            BRIDGETOWN -> Known.BRIDGETOWN
                            BRISBANE -> Known.BRISBANE
                            BRISTOL -> Known.BRISTOL
                            BRNO -> Known.BRNO
                            BROOKLYN -> Known.BROOKLYN
                            BRUSSELS -> Known.BRUSSELS
                            BUCARAMANGA -> Known.BUCARAMANGA
                            BUCHAREST -> Known.BUCHAREST
                            BUDAPEST -> Known.BUDAPEST
                            BUENOS_AIRES -> Known.BUENOS_AIRES
                            BUFFALO -> Known.BUFFALO
                            BUK_GU -> Known.BUK_GU
                            BUKHARA -> Known.BUKHARA
                            BURGAS -> Known.BURGAS
                            BURNABY -> Known.BURNABY
                            BURSA -> Known.BURSA
                            BUTUAN -> Known.BUTUAN
                            BYDGOSZCZ -> Known.BYDGOSZCZ
                            CABANATUAN_CITY -> Known.CABANATUAN_CITY
                            CABO_FRIO -> Known.CABO_FRIO
                            CABUYAO -> Known.CABUYAO
                            CACHOEIRO_DE_ITAPEMIRIM -> Known.CACHOEIRO_DE_ITAPEMIRIM
                            CAGAYAN_DE_ORO -> Known.CAGAYAN_DE_ORO
                            CAGLIARI -> Known.CAGLIARI
                            CAIRO -> Known.CAIRO
                            CALAMBA -> Known.CALAMBA
                            CALGARY -> Known.CALGARY
                            CALOOCAN_CITY -> Known.CALOOCAN_CITY
                            CAMACARI -> Known.CAMACARI
                            CAMARAGIBE -> Known.CAMARAGIBE
                            CAMPECHE -> Known.CAMPECHE
                            CAMPINA_GRANDE -> Known.CAMPINA_GRANDE
                            CAMPINAS -> Known.CAMPINAS
                            CAMPO_GRANDE -> Known.CAMPO_GRANDE
                            CAMPO_LARGO -> Known.CAMPO_LARGO
                            CAMPOS_DOS_GOYTACAZES -> Known.CAMPOS_DOS_GOYTACAZES
                            CAN_THO -> Known.CAN_THO
                            CANOAS -> Known.CANOAS
                            CANTON -> Known.CANTON
                            CAPE_TOWN -> Known.CAPE_TOWN
                            CARACAS -> Known.CARACAS
                            CARAGUATATUBA -> Known.CARAGUATATUBA
                            CARAPICUIBA -> Known.CARAPICUIBA
                            CARDIFF -> Known.CARDIFF
                            CARIACICA -> Known.CARIACICA
                            CARMONA -> Known.CARMONA
                            CARTAGENA -> Known.CARTAGENA
                            CARUARU -> Known.CARUARU
                            CASABLANCA -> Known.CASABLANCA
                            CASCAVEL -> Known.CASCAVEL
                            CASEROS -> Known.CASEROS
                            CASTANHAL -> Known.CASTANHAL
                            CASTRIES -> Known.CASTRIES
                            CATALAO -> Known.CATALAO
                            CATAMARCA -> Known.CATAMARCA
                            CATANDUVA -> Known.CATANDUVA
                            CATANIA -> Known.CATANIA
                            CAUCAIA -> Known.CAUCAIA
                            CAXIAS_DO_SUL -> Known.CAXIAS_DO_SUL
                            CEBU_CITY -> Known.CEBU_CITY
                            CENTRAL -> Known.CENTRAL
                            CENTRO -> Known.CENTRO
                            CENTURION -> Known.CENTURION
                            CHAGUANAS -> Known.CHAGUANAS
                            CHANDIGARH -> Known.CHANDIGARH
                            CHANDLER -> Known.CHANDLER
                            CHANG_HUA -> Known.CHANG_HUA
                            CHAPECO -> Known.CHAPECO
                            CHARLESTON -> Known.CHARLESTON
                            CHARLOTTE -> Known.CHARLOTTE
                            CHELYABINSK -> Known.CHELYABINSK
                            CHENNAI -> Known.CHENNAI
                            CHERKASY -> Known.CHERKASY
                            CHERNIVTSI -> Known.CHERNIVTSI
                            CHIA -> Known.CHIA
                            CHIANG_MAI -> Known.CHIANG_MAI
                            CHICLAYO -> Known.CHICLAYO
                            CHIHUAHUA_CITY -> Known.CHIHUAHUA_CITY
                            CHIMBOTE -> Known.CHIMBOTE
                            CHISINAU -> Known.CHISINAU
                            CHITTAGONG -> Known.CHITTAGONG
                            CHRISTCHURCH -> Known.CHRISTCHURCH
                            CINCINNATI -> Known.CINCINNATI
                            CIREBON -> Known.CIREBON
                            CITY_OF_MUNTINLUPA -> Known.CITY_OF_MUNTINLUPA
                            CIUDAD_DEL_ESTE -> Known.CIUDAD_DEL_ESTE
                            CIUDAD_GUAYANA -> Known.CIUDAD_GUAYANA
                            CIUDAD_JUAREZ -> Known.CIUDAD_JUAREZ
                            CIUDAD_NEZAHUALCOYOTL -> Known.CIUDAD_NEZAHUALCOYOTL
                            CIUDAD_OBREGON -> Known.CIUDAD_OBREGON
                            CLEVELAND -> Known.CLEVELAND
                            CLUJ_NAPOCA -> Known.CLUJ_NAPOCA
                            COCHABAMBA -> Known.COCHABAMBA
                            COIMBATORE -> Known.COIMBATORE
                            COIMBRA -> Known.COIMBRA
                            COLOGNE -> Known.COLOGNE
                            COLOMBO -> Known.COLOMBO
                            COLORADO_SPRINGS -> Known.COLORADO_SPRINGS
                            COLUMBIA -> Known.COLUMBIA
                            COLUMBUS -> Known.COLUMBUS
                            COMODORO_RIVADAVIA -> Known.COMODORO_RIVADAVIA
                            CONCEPCION -> Known.CONCEPCION
                            CONCORD -> Known.CONCORD
                            CONSTANTA -> Known.CONSTANTA
                            CONSTANTINE -> Known.CONSTANTINE
                            CONTAGEM -> Known.CONTAGEM
                            COPENHAGEN -> Known.COPENHAGEN
                            CORDOBA -> Known.CORDOBA
                            CORRIENTES -> Known.CORRIENTES
                            CORUM -> Known.CORUM
                            COTIA -> Known.COTIA
                            COVENTRY -> Known.COVENTRY
                            CRAIOVA -> Known.CRAIOVA
                            CRICIUMA -> Known.CRICIUMA
                            CROYDON -> Known.CROYDON
                            CUAUTITLAN_IZCALLI -> Known.CUAUTITLAN_IZCALLI
                            CUCUTA -> Known.CUCUTA
                            CUENCA -> Known.CUENCA
                            CUERNAVACA -> Known.CUERNAVACA
                            CUIABA -> Known.CUIABA
                            CULIACAN -> Known.CULIACAN
                            CURITIBA -> Known.CURITIBA
                            CUSCO -> Known.CUSCO
                            DA_NANG -> Known.DA_NANG
                            DAGUPAN -> Known.DAGUPAN
                            DAKAR -> Known.DAKAR
                            DALLAS -> Known.DALLAS
                            DAMIETTA -> Known.DAMIETTA
                            DAMMAM -> Known.DAMMAM
                            DAR_ES_SALAAM -> Known.DAR_ES_SALAAM
                            DASMARINAS -> Known.DASMARINAS
                            DAVAO_CITY -> Known.DAVAO_CITY
                            DAYTON -> Known.DAYTON
                            DEBRECEN -> Known.DEBRECEN
                            DECATUR -> Known.DECATUR
                            DEHRADUN -> Known.DEHRADUN
                            DELHI -> Known.DELHI
                            DENIZLI -> Known.DENIZLI
                            DENPASAR -> Known.DENPASAR
                            DENVER -> Known.DENVER
                            DEPOK -> Known.DEPOK
                            DERBY -> Known.DERBY
                            DETROIT -> Known.DETROIT
                            DHAKA -> Known.DHAKA
                            DIADEMA -> Known.DIADEMA
                            DIVINOPOLIS -> Known.DIVINOPOLIS
                            DIYARBAKIR -> Known.DIYARBAKIR
                            DJELFA -> Known.DJELFA
                            DNIPRO -> Known.DNIPRO
                            DOHA -> Known.DOHA
                            DORTMUND -> Known.DORTMUND
                            DOURADOS -> Known.DOURADOS
                            DRESDEN -> Known.DRESDEN
                            DUBAI -> Known.DUBAI
                            DUBLIN -> Known.DUBLIN
                            DUEZCE -> Known.DUEZCE
                            DUISBURG -> Known.DUISBURG
                            DUQUE_DE_CAXIAS -> Known.DUQUE_DE_CAXIAS
                            DURANGO -> Known.DURANGO
                            DURBAN -> Known.DURBAN
                            DUSSELDORF -> Known.DUSSELDORF
                            ECATEPEC -> Known.ECATEPEC
                            EDINBURGH -> Known.EDINBURGH
                            EDIRNE -> Known.EDIRNE
                            EDMONTON -> Known.EDMONTON
                            EL_JADIDA -> Known.EL_JADIDA
                            EL_PASO -> Known.EL_PASO
                            ELAZIG -> Known.ELAZIG
                            EMBU -> Known.EMBU
                            ENSENADA -> Known.ENSENADA
                            ERBIL -> Known.ERBIL
                            ERZURUM -> Known.ERZURUM
                            ESKISEHIR -> Known.ESKISEHIR
                            ESPOO -> Known.ESPOO
                            ESSEN -> Known.ESSEN
                            FAISALABAD -> Known.FAISALABAD
                            FAYETTEVILLE -> Known.FAYETTEVILLE
                            FAZENDA_RIO_GRANDE -> Known.FAZENDA_RIO_GRANDE
                            FEIRA_DE_SANTANA -> Known.FEIRA_DE_SANTANA
                            FES -> Known.FES
                            FLORENCE -> Known.FLORENCE
                            FLORENCIO_VARELA -> Known.FLORENCIO_VARELA
                            FLORIANOPOLIS -> Known.FLORIANOPOLIS
                            FONTANA -> Known.FONTANA
                            FORMOSA -> Known.FORMOSA
                            FORT_LAUDERDALE -> Known.FORT_LAUDERDALE
                            FORT_WAYNE -> Known.FORT_WAYNE
                            FORT_WORTH -> Known.FORT_WORTH
                            FORTALEZA -> Known.FORTALEZA
                            FOZ_DO_IGUACU -> Known.FOZ_DO_IGUACU
                            FRANCA -> Known.FRANCA
                            FRANCISCO_MORATO -> Known.FRANCISCO_MORATO
                            FRANCO_DA_ROCHA -> Known.FRANCO_DA_ROCHA
                            FRANKFURT_AM_MAIN -> Known.FRANKFURT_AM_MAIN
                            FREDERICKSBURG -> Known.FREDERICKSBURG
                            FRESNO -> Known.FRESNO
                            FUNCHAL -> Known.FUNCHAL
                            GABORONE -> Known.GABORONE
                            GAINESVILLE -> Known.GAINESVILLE
                            GALATI -> Known.GALATI
                            GANGNAM_GU -> Known.GANGNAM_GU
                            GARANHUNS -> Known.GARANHUNS
                            GATINEAU -> Known.GATINEAU
                            GAZIANTEP -> Known.GAZIANTEP
                            GDANSK -> Known.GDANSK
                            GDYNIA -> Known.GDYNIA
                            GENERAL_TRIAS -> Known.GENERAL_TRIAS
                            GENEVA -> Known.GENEVA
                            GENOA -> Known.GENOA
                            GEORGE_TOWN -> Known.GEORGE_TOWN
                            GEORGETOWN -> Known.GEORGETOWN
                            GHAZIABAD -> Known.GHAZIABAD
                            GHENT -> Known.GHENT
                            GIJON -> Known.GIJON
                            GIRESUN -> Known.GIRESUN
                            GIZA -> Known.GIZA
                            GLASGOW -> Known.GLASGOW
                            GLENDALE -> Known.GLENDALE
                            GLIWICE -> Known.GLIWICE
                            GOIANIA -> Known.GOIANIA
                            GOMEL -> Known.GOMEL
                            GOTHENBURG -> Known.GOTHENBURG
                            GOVERNADOR_VALADARES -> Known.GOVERNADOR_VALADARES
                            GOYANG_SI -> Known.GOYANG_SI
                            GRANADA -> Known.GRANADA
                            GRAND_RAPIDS -> Known.GRAND_RAPIDS
                            GRAVATAI -> Known.GRAVATAI
                            GRAZ -> Known.GRAZ
                            GREENSBORO -> Known.GREENSBORO
                            GREENVILLE -> Known.GREENVILLE
                            GUADALAJARA -> Known.GUADALAJARA
                            GUADALUPE -> Known.GUADALUPE
                            GUANGZHOU -> Known.GUANGZHOU
                            GUARAPUAVA -> Known.GUARAPUAVA
                            GUARATINGUETA -> Known.GUARATINGUETA
                            GUARUJA -> Known.GUARUJA
                            GUARULHOS -> Known.GUARULHOS
                            GUATEMALA_CITY -> Known.GUATEMALA_CITY
                            GUAYAQUIL -> Known.GUAYAQUIL
                            GUJRANWALA -> Known.GUJRANWALA
                            GURUGRAM -> Known.GURUGRAM
                            GUSTAVO_ADOLFO_MADERO -> Known.GUSTAVO_ADOLFO_MADERO
                            GUWAHATI -> Known.GUWAHATI
                            GWANAK_GU -> Known.GWANAK_GU
                            HACKNEY -> Known.HACKNEY
                            HAIFA -> Known.HAIFA
                            HAIPHONG -> Known.HAIPHONG
                            HAMBURG -> Known.HAMBURG
                            HAMILTON -> Known.HAMILTON
                            HANOI -> Known.HANOI
                            HANOVER -> Known.HANOVER
                            HARARE -> Known.HARARE
                            HAVANA -> Known.HAVANA
                            HELSINKI -> Known.HELSINKI
                            HENDERSON -> Known.HENDERSON
                            HEREDIA -> Known.HEREDIA
                            HERMOSILLO -> Known.HERMOSILLO
                            HIALEAH -> Known.HIALEAH
                            HO_CHI_MINH_CITY -> Known.HO_CHI_MINH_CITY
                            HOLLYWOOD -> Known.HOLLYWOOD
                            HOLON -> Known.HOLON
                            HONOLULU -> Known.HONOLULU
                            HORTOLANDIA -> Known.HORTOLANDIA
                            HRODNA -> Known.HRODNA
                            HSINCHU -> Known.HSINCHU
                            HUANCAYO -> Known.HUANCAYO
                            HUANUCO -> Known.HUANUCO
                            HULL -> Known.HULL
                            HURLINGHAM -> Known.HURLINGHAM
                            HYDERABAD -> Known.HYDERABAD
                            IASI -> Known.IASI
                            IBAGUE -> Known.IBAGUE
                            ICA -> Known.ICA
                            ILAM -> Known.ILAM
                            ILFORD -> Known.ILFORD
                            ILIGAN -> Known.ILIGAN
                            ILOILO_CITY -> Known.ILOILO_CITY
                            IMPERATRIZ -> Known.IMPERATRIZ
                            IMUS -> Known.IMUS
                            INCHEON -> Known.INCHEON
                            INDAIATUBA -> Known.INDAIATUBA
                            INDIANAPOLIS -> Known.INDIANAPOLIS
                            INDORE -> Known.INDORE
                            IPATINGA -> Known.IPATINGA
                            IPOH -> Known.IPOH
                            IQUIQUE -> Known.IQUIQUE
                            IRVINE -> Known.IRVINE
                            ISIDRO_CASANOVA -> Known.ISIDRO_CASANOVA
                            ISLAMABAD -> Known.ISLAMABAD
                            ISLINGTON -> Known.ISLINGTON
                            ISMAILIA -> Known.ISMAILIA
                            ISPARTA -> Known.ISPARTA
                            ISTANBUL -> Known.ISTANBUL
                            ITABORAI -> Known.ITABORAI
                            ITABUNA -> Known.ITABUNA
                            ITAJAI -> Known.ITAJAI
                            ITANHAEM -> Known.ITANHAEM
                            ITAPEVI -> Known.ITAPEVI
                            ITAQUAQUECETUBA -> Known.ITAQUAQUECETUBA
                            ITUZAINGO -> Known.ITUZAINGO
                            IZMIR -> Known.IZMIR
                            IZTAPALAPA -> Known.IZTAPALAPA
                            JABOATAO_DOS_GUARARAPES -> Known.JABOATAO_DOS_GUARARAPES
                            JACAREI -> Known.JACAREI
                            JACKSON -> Known.JACKSON
                            JACKSONVILLE -> Known.JACKSONVILLE
                            JAIPUR -> Known.JAIPUR
                            JAKARTA -> Known.JAKARTA
                            JARAGUA_DO_SUL -> Known.JARAGUA_DO_SUL
                            JAU -> Known.JAU
                            JEDDAH -> Known.JEDDAH
                            JEMBER -> Known.JEMBER
                            JERUSALEM -> Known.JERUSALEM
                            JOAO_MONLEVADE -> Known.JOAO_MONLEVADE
                            JOAO_PESSOA -> Known.JOAO_PESSOA
                            JODHPUR -> Known.JODHPUR
                            JOHANNESBURG -> Known.JOHANNESBURG
                            JOHOR_BAHRU -> Known.JOHOR_BAHRU
                            JOINVILLE -> Known.JOINVILLE
                            JOSE_C_PAZ -> Known.JOSE_C_PAZ
                            JOSE_MARIA_EZEIZA -> Known.JOSE_MARIA_EZEIZA
                            JUAREZ -> Known.JUAREZ
                            JUAZEIRO_DO_NORTE -> Known.JUAZEIRO_DO_NORTE
                            JUIZ_DE_FORA -> Known.JUIZ_DE_FORA
                            JUNDIAI -> Known.JUNDIAI
                            KAHRAMANMARAS -> Known.KAHRAMANMARAS
                            KAMPALA -> Known.KAMPALA
                            KANPUR -> Known.KANPUR
                            KANSAS_CITY -> Known.KANSAS_CITY
                            KAOHSIUNG_CITY -> Known.KAOHSIUNG_CITY
                            KARABUK -> Known.KARABUK
                            KARACHI -> Known.KARACHI
                            KARLSRUHE -> Known.KARLSRUHE
                            KARNAL -> Known.KARNAL
                            KASKI -> Known.KASKI
                            KASTAMONU -> Known.KASTAMONU
                            KATHMANDU -> Known.KATHMANDU
                            KATOWICE -> Known.KATOWICE
                            KATSINA -> Known.KATSINA
                            KATY -> Known.KATY
                            KAUNAS -> Known.KAUNAS
                            KAYSERI -> Known.KAYSERI
                            KAZAN -> Known.KAZAN
                            KECSKEMET -> Known.KECSKEMET
                            KEDIRI -> Known.KEDIRI
                            KENITRA -> Known.KENITRA
                            KHARKIV -> Known.KHARKIV
                            KHMELNYTSKYI -> Known.KHMELNYTSKYI
                            KHON_KAEN -> Known.KHON_KAEN
                            KIELCE -> Known.KIELCE
                            KIGALI -> Known.KIGALI
                            KINGSTON -> Known.KINGSTON
                            KIRKLARELI -> Known.KIRKLARELI
                            KISSIMMEE -> Known.KISSIMMEE
                            KITCHENER -> Known.KITCHENER
                            KLAIPEDA -> Known.KLAIPEDA
                            KNOXVILLE -> Known.KNOXVILLE
                            KOCHI -> Known.KOCHI
                            KOLKATA -> Known.KOLKATA
                            KOLLAM -> Known.KOLLAM
                            KONYA -> Known.KONYA
                            KOSEKOY -> Known.KOSEKOY
                            KOSICE -> Known.KOSICE
                            KOTA_KINABALU -> Known.KOTA_KINABALU
                            KOZHIKODE -> Known.KOZHIKODE
                            KRAKOW -> Known.KRAKOW
                            KRASNODAR -> Known.KRASNODAR
                            KRYVYI_RIH -> Known.KRYVYI_RIH
                            KUALA_LUMPUR -> Known.KUALA_LUMPUR
                            KUCHING -> Known.KUCHING
                            KUTAHYA -> Known.KUTAHYA
                            KUTAISI -> Known.KUTAISI
                            KUWAIT_CITY -> Known.KUWAIT_CITY
                            KYIV -> Known.KYIV
                            LA_PAZ -> Known.LA_PAZ
                            LA_PLATA -> Known.LA_PLATA
                            LA_RIOJA -> Known.LA_RIOJA
                            LA_SERENA -> Known.LA_SERENA
                            LAFAYETTE -> Known.LAFAYETTE
                            LAFERRERE -> Known.LAFERRERE
                            LAGES -> Known.LAGES
                            LAGOS -> Known.LAGOS
                            LAHORE -> Known.LAHORE
                            LAHUG -> Known.LAHUG
                            LAKE_WORTH -> Known.LAKE_WORTH
                            LAKELAND -> Known.LAKELAND
                            LANCASTER -> Known.LANCASTER
                            LANUS -> Known.LANUS
                            LAS_PALMAS_DE_GRAN_CANARIA -> Known.LAS_PALMAS_DE_GRAN_CANARIA
                            LAS_PINAS -> Known.LAS_PINAS
                            LAS_VEGAS -> Known.LAS_VEGAS
                            LAUSANNE -> Known.LAUSANNE
                            LAVAL -> Known.LAVAL
                            LAWRENCEVILLE -> Known.LAWRENCEVILLE
                            LE_MANS -> Known.LE_MANS
                            LEEDS -> Known.LEEDS
                            LEICESTER -> Known.LEICESTER
                            LEIPZIG -> Known.LEIPZIG
                            LEON -> Known.LEON
                            LEXINGTON -> Known.LEXINGTON
                            LIBREVILLE -> Known.LIBREVILLE
                            LIEGE -> Known.LIEGE
                            LILLE -> Known.LILLE
                            LIMA -> Known.LIMA
                            LIMASSOL -> Known.LIMASSOL
                            LIMEIRA -> Known.LIMEIRA
                            LINCOLN -> Known.LINCOLN
                            LINHARES -> Known.LINHARES
                            LIPA_CITY -> Known.LIPA_CITY
                            LISBON -> Known.LISBON
                            LIVERPOOL -> Known.LIVERPOOL
                            LJUBLJANA -> Known.LJUBLJANA
                            LODZ -> Known.LODZ
                            LOJA -> Known.LOJA
                            LOMAS_DE_ZAMORA -> Known.LOMAS_DE_ZAMORA
                            LOME -> Known.LOME
                            LONDRINA -> Known.LONDRINA
                            LONG_BEACH -> Known.LONG_BEACH
                            LONGUEUIL -> Known.LONGUEUIL
                            LOUISVILLE -> Known.LOUISVILLE
                            LUANDA -> Known.LUANDA
                            LUBLIN -> Known.LUBLIN
                            LUCENA_CITY -> Known.LUCENA_CITY
                            LUCKNOW -> Known.LUCKNOW
                            LUDHIANA -> Known.LUDHIANA
                            LUSAKA -> Known.LUSAKA
                            LUXEMBOURG -> Known.LUXEMBOURG
                            LUZIANIA -> Known.LUZIANIA
                            LVIV -> Known.LVIV
                            LYON -> Known.LYON
                            MABALACAT -> Known.MABALACAT
                            MACAE -> Known.MACAE
                            MACAO -> Known.MACAO
                            MACAPA -> Known.MACAPA
                            MACEIO -> Known.MACEIO
                            MACHALA -> Known.MACHALA
                            MADISON -> Known.MADISON
                            MADRID -> Known.MADRID
                            MAGE -> Known.MAGE
                            MAGELANG -> Known.MAGELANG
                            MAGNESIA_AD_SIPYLUM -> Known.MAGNESIA_AD_SIPYLUM
                            MAKASSAR -> Known.MAKASSAR
                            MAKATI_CITY -> Known.MAKATI_CITY
                            MALABON -> Known.MALABON
                            MALAGA -> Known.MALAGA
                            MALANG -> Known.MALANG
                            MALAPPURAM -> Known.MALAPPURAM
                            MALDONADO -> Known.MALDONADO
                            MALE -> Known.MALE
                            MALMO -> Known.MALMO
                            MANADO -> Known.MANADO
                            MANAGUA -> Known.MANAGUA
                            MANAMA -> Known.MANAMA
                            MANAUS -> Known.MANAUS
                            MANCHESTER -> Known.MANCHESTER
                            MANDALUYONG_CITY -> Known.MANDALUYONG_CITY
                            MANILA -> Known.MANILA
                            MANIZALES -> Known.MANIZALES
                            MANNHEIM -> Known.MANNHEIM
                            MAPUTO -> Known.MAPUTO
                            MAR_DEL_PLATA -> Known.MAR_DEL_PLATA
                            MARABA -> Known.MARABA
                            MARACAIBO -> Known.MARACAIBO
                            MARACANAU -> Known.MARACANAU
                            MARACAY -> Known.MARACAY
                            MARDIN -> Known.MARDIN
                            MARIBOR -> Known.MARIBOR
                            MARICA -> Known.MARICA
                            MARIETTA -> Known.MARIETTA
                            MARIKINA_CITY -> Known.MARIKINA_CITY
                            MARILIA -> Known.MARILIA
                            MARINGA -> Known.MARINGA
                            MARRAKESH -> Known.MARRAKESH
                            MARSEILLE -> Known.MARSEILLE
                            MAUA -> Known.MAUA
                            MAZATLAN -> Known.MAZATLAN
                            MEDAN -> Known.MEDAN
                            MEDELLIN -> Known.MEDELLIN
                            MEDINA -> Known.MEDINA
                            MEERUT -> Known.MEERUT
                            MEKNES -> Known.MEKNES
                            MELBOURNE -> Known.MELBOURNE
                            MEMPHIS -> Known.MEMPHIS
                            MENDOZA -> Known.MENDOZA
                            MERIDA -> Known.MERIDA
                            MERKEZ -> Known.MERKEZ
                            MERLO -> Known.MERLO
                            MERSIN -> Known.MERSIN
                            MESA -> Known.MESA
                            MEXICALI -> Known.MEXICALI
                            MEXICO_CITY -> Known.MEXICO_CITY
                            MILAN -> Known.MILAN
                            MILTON_KEYNES -> Known.MILTON_KEYNES
                            MILWAUKEE -> Known.MILWAUKEE
                            MINNEAPOLIS -> Known.MINNEAPOLIS
                            MINSK -> Known.MINSK
                            MISKOLC -> Known.MISKOLC
                            MISSISSAUGA -> Known.MISSISSAUGA
                            MOGI_DAS_CRUZES -> Known.MOGI_DAS_CRUZES
                            MOHALI -> Known.MOHALI
                            MONROE -> Known.MONROE
                            MONTE_GRANDE -> Known.MONTE_GRANDE
                            MONTEGO_BAY -> Known.MONTEGO_BAY
                            MONTERREY -> Known.MONTERREY
                            MONTES_CLAROS -> Known.MONTES_CLAROS
                            MONTEVIDEO -> Known.MONTEVIDEO
                            MONTGOMERY -> Known.MONTGOMERY
                            MONTPELLIER -> Known.MONTPELLIER
                            MONTREAL -> Known.MONTREAL
                            MORELIA -> Known.MORELIA
                            MORENO -> Known.MORENO
                            MORON -> Known.MORON
                            MOSSORO -> Known.MOSSORO
                            MUGLA -> Known.MUGLA
                            MULTAN -> Known.MULTAN
                            MUMBAI -> Known.MUMBAI
                            MUNICH -> Known.MUNICH
                            MURCIA -> Known.MURCIA
                            MUSCAT -> Known.MUSCAT
                            MUZAFFARGARH -> Known.MUZAFFARGARH
                            MYKOLAYIV -> Known.MYKOLAYIV
                            NAALDWIJK -> Known.NAALDWIJK
                            NAGA -> Known.NAGA
                            NAGPUR -> Known.NAGPUR
                            NAIROBI -> Known.NAIROBI
                            NANTES -> Known.NANTES
                            NAPLES -> Known.NAPLES
                            NASHVILLE -> Known.NASHVILLE
                            NASSAU -> Known.NASSAU
                            NASUGBU -> Known.NASUGBU
                            NATAL -> Known.NATAL
                            NAUCALPAN -> Known.NAUCALPAN
                            NAVI_MUMBAI -> Known.NAVI_MUMBAI
                            NEIVA -> Known.NEIVA
                            NEUQUEN -> Known.NEUQUEN
                            NEVSEHIR -> Known.NEVSEHIR
                            NEW_DELHI -> Known.NEW_DELHI
                            NEW_ORLEANS -> Known.NEW_ORLEANS
                            NEW_TAIPEI -> Known.NEW_TAIPEI
                            NEWARK -> Known.NEWARK
                            NEWCASTLE_UPON_TYNE -> Known.NEWCASTLE_UPON_TYNE
                            NHA_TRANG -> Known.NHA_TRANG
                            NICE -> Known.NICE
                            NICOSIA -> Known.NICOSIA
                            NILOPOLIS -> Known.NILOPOLIS
                            NIS -> Known.NIS
                            NITEROI -> Known.NITEROI
                            NITRA -> Known.NITRA
                            NIZHNIY_NOVGOROD -> Known.NIZHNIY_NOVGOROD
                            NOGALES -> Known.NOGALES
                            NOIDA -> Known.NOIDA
                            NORTHAMPTON -> Known.NORTHAMPTON
                            NORWICH -> Known.NORWICH
                            NOTTINGHAM -> Known.NOTTINGHAM
                            NOVA_FRIBURGO -> Known.NOVA_FRIBURGO
                            NOVA_IGUACU -> Known.NOVA_IGUACU
                            NOVI_SAD -> Known.NOVI_SAD
                            NOVO_HAMBURGO -> Known.NOVO_HAMBURGO
                            NOVOSIBIRSK -> Known.NOVOSIBIRSK
                            NUREMBERG -> Known.NUREMBERG
                            OAKLAND -> Known.OAKLAND
                            OAXACA_CITY -> Known.OAXACA_CITY
                            ODESA -> Known.ODESA
                            OKLAHOMA_CITY -> Known.OKLAHOMA_CITY
                            OLINDA -> Known.OLINDA
                            OLOMOUC -> Known.OLOMOUC
                            OLONGAPO_CITY -> Known.OLONGAPO_CITY
                            OLSZTYN -> Known.OLSZTYN
                            OMAHA -> Known.OMAHA
                            ORADEA -> Known.ORADEA
                            ORAN -> Known.ORAN
                            ORDU -> Known.ORDU
                            ORLANDO -> Known.ORLANDO
                            OSASCO -> Known.OSASCO
                            OSLO -> Known.OSLO
                            OSMANIYE -> Known.OSMANIYE
                            OSTRAVA -> Known.OSTRAVA
                            OTTAWA -> Known.OTTAWA
                            OUJDA -> Known.OUJDA
                            OURINHOS -> Known.OURINHOS
                            PACHUCA -> Known.PACHUCA
                            PADOVA -> Known.PADOVA
                            PALAKKAD -> Known.PALAKKAD
                            PALEMBANG -> Known.PALEMBANG
                            PALERMO -> Known.PALERMO
                            PALHOCA -> Known.PALHOCA
                            PALMA -> Known.PALMA
                            PALMAS -> Known.PALMAS
                            PANAMA_CITY -> Known.PANAMA_CITY
                            PARAMARIBO -> Known.PARAMARIBO
                            PARANA -> Known.PARANA
                            PARANAGUA -> Known.PARANAGUA
                            PARANAQUE_CITY -> Known.PARANAQUE_CITY
                            PARAUAPEBAS -> Known.PARAUAPEBAS
                            PARIS -> Known.PARIS
                            PARNAIBA -> Known.PARNAIBA
                            PARNAMIRIM -> Known.PARNAMIRIM
                            PASSO_FUNDO -> Known.PASSO_FUNDO
                            PASTO -> Known.PASTO
                            PATAN -> Known.PATAN
                            PATNA -> Known.PATNA
                            PATOS_DE_MINAS -> Known.PATOS_DE_MINAS
                            PAULISTA -> Known.PAULISTA
                            PECS -> Known.PECS
                            PEKANBARU -> Known.PEKANBARU
                            PELOTAS -> Known.PELOTAS
                            PEORIA -> Known.PEORIA
                            PEREIRA -> Known.PEREIRA
                            PERM -> Known.PERM
                            PERTH -> Known.PERTH
                            PESCARA -> Known.PESCARA
                            PESHAWAR -> Known.PESHAWAR
                            PETAH_TIKVA -> Known.PETAH_TIKVA
                            PETALING_JAYA -> Known.PETALING_JAYA
                            PETROLINA -> Known.PETROLINA
                            PETROPOLIS -> Known.PETROPOLIS
                            PHILADELPHIA -> Known.PHILADELPHIA
                            PHNOM_PENH -> Known.PHNOM_PENH
                            PHOENIX -> Known.PHOENIX
                            PILAR -> Known.PILAR
                            PINDAMONHANGABA -> Known.PINDAMONHANGABA
                            PIRACICABA -> Known.PIRACICABA
                            PITESTI -> Known.PITESTI
                            PITTSBURGH -> Known.PITTSBURGH
                            PIURA -> Known.PIURA
                            PLANO -> Known.PLANO
                            PLOIESTI -> Known.PLOIESTI
                            PLOVDIV -> Known.PLOVDIV
                            PLYMOUTH -> Known.PLYMOUTH
                            POCOS_DE_CALDAS -> Known.POCOS_DE_CALDAS
                            PODGORICA -> Known.PODGORICA
                            POLTAVA -> Known.POLTAVA
                            PONTA_GROSSA -> Known.PONTA_GROSSA
                            PONTIANAK -> Known.PONTIANAK
                            POPAYAN -> Known.POPAYAN
                            PORT_AU_PRINCE -> Known.PORT_AU_PRINCE
                            PORT_ELIZABETH -> Known.PORT_ELIZABETH
                            PORT_HARCOURT -> Known.PORT_HARCOURT
                            PORT_LOUIS -> Known.PORT_LOUIS
                            PORT_MONTT -> Known.PORT_MONTT
                            PORT_OF_SPAIN -> Known.PORT_OF_SPAIN
                            PORT_SAID -> Known.PORT_SAID
                            PORTLAND -> Known.PORTLAND
                            PORTO -> Known.PORTO
                            PORTO_ALEGRE -> Known.PORTO_ALEGRE
                            PORTO_SEGURO -> Known.PORTO_SEGURO
                            PORTO_VELHO -> Known.PORTO_VELHO
                            PORTOVIEJO -> Known.PORTOVIEJO
                            POSADAS -> Known.POSADAS
                            POUSO_ALEGRE -> Known.POUSO_ALEGRE
                            POZNAN -> Known.POZNAN
                            PRAGUE -> Known.PRAGUE
                            PRAIA_GRANDE -> Known.PRAIA_GRANDE
                            PRESIDENTE_PRUDENTE -> Known.PRESIDENTE_PRUDENTE
                            PRETORIA -> Known.PRETORIA
                            PRISTINA -> Known.PRISTINA
                            PROVIDENCE -> Known.PROVIDENCE
                            PUCALLPA -> Known.PUCALLPA
                            PUCHONG_BATU_DUA_BELAS -> Known.PUCHONG_BATU_DUA_BELAS
                            PUEBLA_CITY -> Known.PUEBLA_CITY
                            PUNE -> Known.PUNE
                            QUEBEC -> Known.QUEBEC
                            QUEENS -> Known.QUEENS
                            QUEIMADOS -> Known.QUEIMADOS
                            QUERETARO_CITY -> Known.QUERETARO_CITY
                            QUEZON_CITY -> Known.QUEZON_CITY
                            QUILMES -> Known.QUILMES
                            QUITO -> Known.QUITO
                            RABAT -> Known.RABAT
                            RAIPUR -> Known.RAIPUR
                            RAJKOT -> Known.RAJKOT
                            RAJSHAHI -> Known.RAJSHAHI
                            RALEIGH -> Known.RALEIGH
                            RAMAT_GAN -> Known.RAMAT_GAN
                            RANCAGUA -> Known.RANCAGUA
                            RANCHI -> Known.RANCHI
                            RAS_AL_KHAIMAH -> Known.RAS_AL_KHAIMAH
                            RAWALPINDI -> Known.RAWALPINDI
                            READING -> Known.READING
                            RECIFE -> Known.RECIFE
                            REGINA -> Known.REGINA
                            RENNES -> Known.RENNES
                            RENO -> Known.RENO
                            RESISTENCIA -> Known.RESISTENCIA
                            REYKJAVIK -> Known.REYKJAVIK
                            REYNOSA -> Known.REYNOSA
                            RIBEIRAO_DAS_NEVES -> Known.RIBEIRAO_DAS_NEVES
                            RIBEIRAO_PRETO -> Known.RIBEIRAO_PRETO
                            RICHMOND -> Known.RICHMOND
                            RIGA -> Known.RIGA
                            RIO_BRANCO -> Known.RIO_BRANCO
                            RIO_CLARO -> Known.RIO_CLARO
                            RIO_CUARTO -> Known.RIO_CUARTO
                            RIO_DE_JANEIRO -> Known.RIO_DE_JANEIRO
                            RIO_DO_SUL -> Known.RIO_DO_SUL
                            RIO_GALLEGOS -> Known.RIO_GALLEGOS
                            RIO_GRANDE -> Known.RIO_GRANDE
                            RISHON_LETSIYYON -> Known.RISHON_LETSIYYON
                            RIVERSIDE -> Known.RIVERSIDE
                            RIYADH -> Known.RIYADH
                            RIZE -> Known.RIZE
                            ROCHESTER -> Known.ROCHESTER
                            ROME -> Known.ROME
                            RONDONOPOLIS -> Known.RONDONOPOLIS
                            ROSARIO -> Known.ROSARIO
                            ROSEAU -> Known.ROSEAU
                            ROSTOV_ON_DON -> Known.ROSTOV_ON_DON
                            ROTTERDAM -> Known.ROTTERDAM
                            ROUEN -> Known.ROUEN
                            ROUSSE -> Known.ROUSSE
                            RZESZOW -> Known.RZESZOW
                            SACRAMENTO -> Known.SACRAMENTO
                            SAGAR -> Known.SAGAR
                            SAINT_PAUL -> Known.SAINT_PAUL
                            SALE -> Known.SALE
                            SALT_LAKE_CITY -> Known.SALT_LAKE_CITY
                            SALTA -> Known.SALTA
                            SALTILLO -> Known.SALTILLO
                            SALVADOR -> Known.SALVADOR
                            SAMARA -> Known.SAMARA
                            SAMARINDA -> Known.SAMARINDA
                            SAMARKAND -> Known.SAMARKAND
                            SAMSUN -> Known.SAMSUN
                            SAN_ANTONIO -> Known.SAN_ANTONIO
                            SAN_DIEGO -> Known.SAN_DIEGO
                            SAN_FERNANDO -> Known.SAN_FERNANDO
                            SAN_FRANCISCO -> Known.SAN_FRANCISCO
                            SAN_JOSE -> Known.SAN_JOSE
                            SAN_JOSE_DEL_MONTE -> Known.SAN_JOSE_DEL_MONTE
                            SAN_JUAN -> Known.SAN_JUAN
                            SAN_JUSTO -> Known.SAN_JUSTO
                            SAN_LUIS -> Known.SAN_LUIS
                            SAN_LUIS_POTOSI_CITY -> Known.SAN_LUIS_POTOSI_CITY
                            SAN_MIGUEL -> Known.SAN_MIGUEL
                            SAN_MIGUEL_DE_TUCUMAN -> Known.SAN_MIGUEL_DE_TUCUMAN
                            SAN_PABLO_CITY -> Known.SAN_PABLO_CITY
                            SAN_PEDRO -> Known.SAN_PEDRO
                            SAN_PEDRO_SULA -> Known.SAN_PEDRO_SULA
                            SAN_SALVADOR -> Known.SAN_SALVADOR
                            SAN_SALVADOR_DE_JUJUY -> Known.SAN_SALVADOR_DE_JUJUY
                            SANAA -> Known.SANAA
                            SANLIURFA -> Known.SANLIURFA
                            SANTA_CRUZ -> Known.SANTA_CRUZ
                            SANTA_CRUZ_DE_TENERIFE -> Known.SANTA_CRUZ_DE_TENERIFE
                            SANTA_CRUZ_DO_SUL -> Known.SANTA_CRUZ_DO_SUL
                            SANTA_FE -> Known.SANTA_FE
                            SANTA_LUZIA -> Known.SANTA_LUZIA
                            SANTA_MARIA -> Known.SANTA_MARIA
                            SANTA_MARTA -> Known.SANTA_MARTA
                            SANTA_ROSA -> Known.SANTA_ROSA
                            SANTAREM -> Known.SANTAREM
                            SANTIAGO -> Known.SANTIAGO
                            SANTIAGO_DE_CALI -> Known.SANTIAGO_DE_CALI
                            SANTIAGO_DE_LOS_CABALLEROS -> Known.SANTIAGO_DE_LOS_CABALLEROS
                            SANTO_ANDRE -> Known.SANTO_ANDRE
                            SANTO_DOMINGO -> Known.SANTO_DOMINGO
                            SANTO_DOMINGO_ESTE -> Known.SANTO_DOMINGO_ESTE
                            SANTOS -> Known.SANTOS
                            SAO_BERNARDO_DO_CAMPO -> Known.SAO_BERNARDO_DO_CAMPO
                            SAO_CARLOS -> Known.SAO_CARLOS
                            SAO_GONCALO -> Known.SAO_GONCALO
                            SAO_JOAO_DE_MERITI -> Known.SAO_JOAO_DE_MERITI
                            SAO_JOSE -> Known.SAO_JOSE
                            SAO_JOSE_DO_RIO_PRETO -> Known.SAO_JOSE_DO_RIO_PRETO
                            SAO_JOSE_DOS_CAMPOS -> Known.SAO_JOSE_DOS_CAMPOS
                            SAO_JOSE_DOS_PINHAIS -> Known.SAO_JOSE_DOS_PINHAIS
                            SAO_LEOPOLDO -> Known.SAO_LEOPOLDO
                            SAO_LUIS -> Known.SAO_LUIS
                            SAO_PAULO -> Known.SAO_PAULO
                            SAO_VICENTE -> Known.SAO_VICENTE
                            SARAJEVO -> Known.SARAJEVO
                            SASKATOON -> Known.SASKATOON
                            SCARBOROUGH -> Known.SCARBOROUGH
                            SEATTLE -> Known.SEATTLE
                            SEMARANG -> Known.SEMARANG
                            SEO_GU -> Known.SEO_GU
                            SEONGNAM_SI -> Known.SEONGNAM_SI
                            SEOUL -> Known.SEOUL
                            SERRA -> Known.SERRA
                            SETE_LAGOAS -> Known.SETE_LAGOAS
                            SETIF -> Known.SETIF
                            SETUBAL -> Known.SETUBAL
                            SEVILLE -> Known.SEVILLE
                            SFAX -> Known.SFAX
                            SHAH_ALAM -> Known.SHAH_ALAM
                            SHANGHAI -> Known.SHANGHAI
                            SHARJAH -> Known.SHARJAH
                            SHEFFIELD -> Known.SHEFFIELD
                            SHENZHEN -> Known.SHENZHEN
                            SHIMLA -> Known.SHIMLA
                            SIAULIAI -> Known.SIAULIAI
                            SIBIU -> Known.SIBIU
                            SIDOARJO -> Known.SIDOARJO
                            SIKAR -> Known.SIKAR
                            SILVER_SPRING -> Known.SILVER_SPRING
                            SINOP -> Known.SINOP
                            SIVAS -> Known.SIVAS
                            SKIKDA -> Known.SKIKDA
                            SKOPJE -> Known.SKOPJE
                            SLOUGH -> Known.SLOUGH
                            SOBRAL -> Known.SOBRAL
                            SOFIA -> Known.SOFIA
                            SOROCABA -> Known.SOROCABA
                            SOUSSE -> Known.SOUSSE
                            SOUTH_TANGERANG -> Known.SOUTH_TANGERANG
                            SOUTHAMPTON -> Known.SOUTHAMPTON
                            SOUTHWARK -> Known.SOUTHWARK
                            SPLIT -> Known.SPLIT
                            SPOKANE -> Known.SPOKANE
                            SPRING -> Known.SPRING
                            SPRINGFIELD -> Known.SPRINGFIELD
                            ST_LOUIS -> Known.ST_LOUIS
                            ST_PETERSBURG -> Known.ST_PETERSBURG
                            STARA_ZAGORA -> Known.STARA_ZAGORA
                            STATEN_ISLAND -> Known.STATEN_ISLAND
                            STOCKHOLM -> Known.STOCKHOLM
                            STOCKTON -> Known.STOCKTON
                            STOKE_ON_TRENT -> Known.STOKE_ON_TRENT
                            STRASBOURG -> Known.STRASBOURG
                            STUTTGART -> Known.STUTTGART
                            SUMARE -> Known.SUMARE
                            SURABAYA -> Known.SURABAYA
                            SURAKARTA -> Known.SURAKARTA
                            SURAT -> Known.SURAT
                            SURREY -> Known.SURREY
                            SUWON -> Known.SUWON
                            SUZANO -> Known.SUZANO
                            SYDNEY -> Known.SYDNEY
                            SZCZECIN -> Known.SZCZECIN
                            SZEGED -> Known.SZEGED
                            SZEKESFEHERVAR -> Known.SZEKESFEHERVAR
                            TABOAO_DA_SERRA -> Known.TABOAO_DA_SERRA
                            TACNA -> Known.TACNA
                            TACOMA -> Known.TACOMA
                            TAGUIG -> Known.TAGUIG
                            TAICHUNG -> Known.TAICHUNG
                            TAINAN_CITY -> Known.TAINAN_CITY
                            TAIPEI -> Known.TAIPEI
                            TALAVERA -> Known.TALAVERA
                            TALCA -> Known.TALCA
                            TALLAHASSEE -> Known.TALLAHASSEE
                            TALLINN -> Known.TALLINN
                            TAMPA -> Known.TAMPA
                            TAMPERE -> Known.TAMPERE
                            TAMPICO -> Known.TAMPICO
                            TANGERANG -> Known.TANGERANG
                            TANGIER -> Known.TANGIER
                            TANTA -> Known.TANTA
                            TANZA -> Known.TANZA
                            TAOYUAN_DISTRICT -> Known.TAOYUAN_DISTRICT
                            TAPPAHANNOCK -> Known.TAPPAHANNOCK
                            TARLAC_CITY -> Known.TARLAC_CITY
                            TASHKENT -> Known.TASHKENT
                            TASIKMALAYA -> Known.TASIKMALAYA
                            TATUI -> Known.TATUI
                            TAUBATE -> Known.TAUBATE
                            TBILISI -> Known.TBILISI
                            TEGUCIGALPA -> Known.TEGUCIGALPA
                            TEHRAN -> Known.TEHRAN
                            TEIXEIRA_DE_FREITAS -> Known.TEIXEIRA_DE_FREITAS
                            TEKIRDAG -> Known.TEKIRDAG
                            TEL_AVIV -> Known.TEL_AVIV
                            TEMUCO -> Known.TEMUCO
                            TEPIC -> Known.TEPIC
                            TERESINA -> Known.TERESINA
                            TERNOPIL -> Known.TERNOPIL
                            TERRASSA -> Known.TERRASSA
                            TETOUAN -> Known.TETOUAN
                            THANE -> Known.THANE
                            THE_BRONX -> Known.THE_BRONX
                            THE_HAGUE -> Known.THE_HAGUE
                            THESSALONIKI -> Known.THESSALONIKI
                            THIRUVANANTHAPURAM -> Known.THIRUVANANTHAPURAM
                            THRISSUR -> Known.THRISSUR
                            TIJUANA -> Known.TIJUANA
                            TIMISOARA -> Known.TIMISOARA
                            TIRANA -> Known.TIRANA
                            TLALNEPANTLA -> Known.TLALNEPANTLA
                            TLAXCALA_CITY -> Known.TLAXCALA_CITY
                            TLEMCEN -> Known.TLEMCEN
                            TOKAT_PROVINCE -> Known.TOKAT_PROVINCE
                            TOKYO -> Known.TOKYO
                            TOLUCA -> Known.TOLUCA
                            TORONTO -> Known.TORONTO
                            TORREON -> Known.TORREON
                            TOULOUSE -> Known.TOULOUSE
                            TRABZON -> Known.TRABZON
                            TRUJILLO -> Known.TRUJILLO
                            TUBARAO -> Known.TUBARAO
                            TUCSON -> Known.TUCSON
                            TUGUEGARAO_CITY -> Known.TUGUEGARAO_CITY
                            TULSA -> Known.TULSA
                            TUNIS -> Known.TUNIS
                            TUNJA -> Known.TUNJA
                            TURIN -> Known.TURIN
                            TUXTLA_GUTIERREZ -> Known.TUXTLA_GUTIERREZ
                            TUZLA -> Known.TUZLA
                            UBERABA -> Known.UBERABA
                            UBERLANDIA -> Known.UBERLANDIA
                            UFA -> Known.UFA
                            ULAN_BATOR -> Known.ULAN_BATOR
                            UMEDA -> Known.UMEDA
                            URDANETA -> Known.URDANETA
                            USAK -> Known.USAK
                            VADODARA -> Known.VADODARA
                            VALENCIA -> Known.VALENCIA
                            VALINHOS -> Known.VALINHOS
                            VALLADOLID -> Known.VALLADOLID
                            VALLEDUPAR -> Known.VALLEDUPAR
                            VALPARAISO -> Known.VALPARAISO
                            VALPARAISO_DE_GOIAS -> Known.VALPARAISO_DE_GOIAS
                            VAN -> Known.VAN
                            VANCOUVER -> Known.VANCOUVER
                            VARANASI -> Known.VARANASI
                            VARGINHA -> Known.VARGINHA
                            VARNA -> Known.VARNA
                            VARZEA_PAULISTA -> Known.VARZEA_PAULISTA
                            VENUSTIANO_CARRANZA -> Known.VENUSTIANO_CARRANZA
                            VERACRUZ -> Known.VERACRUZ
                            VERONA -> Known.VERONA
                            VIAMAO -> Known.VIAMAO
                            VICTORIA -> Known.VICTORIA
                            VIENNA -> Known.VIENNA
                            VIENTIANE -> Known.VIENTIANE
                            VIGO -> Known.VIGO
                            VIJAYAWADA -> Known.VIJAYAWADA
                            VILA_NOVA_DE_GAIA -> Known.VILA_NOVA_DE_GAIA
                            VILA_VELHA -> Known.VILA_VELHA
                            VILLA_BALLESTER -> Known.VILLA_BALLESTER
                            VILLAVICENCIO -> Known.VILLAVICENCIO
                            VILNIUS -> Known.VILNIUS
                            VINA_DEL_MAR -> Known.VINA_DEL_MAR
                            VINNYTSIA -> Known.VINNYTSIA
                            VIRGINIA_BEACH -> Known.VIRGINIA_BEACH
                            VISAKHAPATNAM -> Known.VISAKHAPATNAM
                            VITORIA -> Known.VITORIA
                            VITORIA_DA_CONQUISTA -> Known.VITORIA_DA_CONQUISTA
                            VITORIA_DE_SANTO_ANTAO -> Known.VITORIA_DE_SANTO_ANTAO
                            VOLTA_REDONDA -> Known.VOLTA_REDONDA
                            VORONEZH -> Known.VORONEZH
                            WARSAW -> Known.WARSAW
                            WASHINGTON -> Known.WASHINGTON
                            WELLINGTON -> Known.WELLINGTON
                            WEST_PALM_BEACH -> Known.WEST_PALM_BEACH
                            WICHITA -> Known.WICHITA
                            WILLEMSTAD -> Known.WILLEMSTAD
                            WILMINGTON -> Known.WILMINGTON
                            WINDHOEK -> Known.WINDHOEK
                            WINDSOR -> Known.WINDSOR
                            WINNIPEG -> Known.WINNIPEG
                            WOLVERHAMPTON -> Known.WOLVERHAMPTON
                            WOODBRIDGE -> Known.WOODBRIDGE
                            WROCLAW -> Known.WROCLAW
                            WUPPERTAL -> Known.WUPPERTAL
                            XALAPA -> Known.XALAPA
                            YALOVA -> Known.YALOVA
                            YANGON -> Known.YANGON
                            YEKATERINBURG -> Known.YEKATERINBURG
                            YEREVAN -> Known.YEREVAN
                            YOGYAKARTA -> Known.YOGYAKARTA
                            YOKOHAMA -> Known.YOKOHAMA
                            YONGIN_SI -> Known.YONGIN_SI
                            ZABRZE -> Known.ZABRZE
                            ZAGAZIG -> Known.ZAGAZIG
                            ZAGREB -> Known.ZAGREB
                            ZAMBOANGA_CITY -> Known.ZAMBOANGA_CITY
                            ZAPOPAN -> Known.ZAPOPAN
                            ZAPORIZHZHYA -> Known.ZAPORIZHZHYA
                            ZARAGOZA -> Known.ZARAGOZA
                            ZHONGLI_DISTRICT -> Known.ZHONGLI_DISTRICT
                            ZIELONA_GORA -> Known.ZIELONA_GORA
                            ZONGULDAK -> Known.ZONGULDAK
                            ZURICH -> Known.ZURICH
                            else -> throw SteelInvalidDataException("Unknown City: $value")
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

                    fun validate(): City = apply {
                        if (validated) {
                            return@apply
                        }

                        known()
                        validated = true
                    }

                    @JvmSynthetic
                    internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is City && value == other.value
                    }

                    override fun hashCode() = value.hashCode()

                    override fun toString() = value.toString()
                }

                class State @JsonCreator private constructor(private val value: JsonField<String>) :
                    Enum {

                    @com.fasterxml.jackson.annotation.JsonValue
                    fun _value(): JsonField<String> = value

                    companion object {

                        @JvmField val AL = of("AL")

                        @JvmField val AK = of("AK")

                        @JvmField val AZ = of("AZ")

                        @JvmField val AR = of("AR")

                        @JvmField val CA = of("CA")

                        @JvmField val CO = of("CO")

                        @JvmField val CT = of("CT")

                        @JvmField val DE = of("DE")

                        @JvmField val FL = of("FL")

                        @JvmField val GA = of("GA")

                        @JvmField val HI = of("HI")

                        @JvmField val ID = of("ID")

                        @JvmField val IL = of("IL")

                        @JvmField val IN = of("IN")

                        @JvmField val IA = of("IA")

                        @JvmField val KS = of("KS")

                        @JvmField val KY = of("KY")

                        @JvmField val LA = of("LA")

                        @JvmField val ME = of("ME")

                        @JvmField val MD = of("MD")

                        @JvmField val MA = of("MA")

                        @JvmField val MI = of("MI")

                        @JvmField val MN = of("MN")

                        @JvmField val MS = of("MS")

                        @JvmField val MO = of("MO")

                        @JvmField val MT = of("MT")

                        @JvmField val NE = of("NE")

                        @JvmField val NV = of("NV")

                        @JvmField val NH = of("NH")

                        @JvmField val NJ = of("NJ")

                        @JvmField val NM = of("NM")

                        @JvmField val NY = of("NY")

                        @JvmField val NC = of("NC")

                        @JvmField val ND = of("ND")

                        @JvmField val OH = of("OH")

                        @JvmField val OK = of("OK")

                        @JvmField val OR = of("OR")

                        @JvmField val PA = of("PA")

                        @JvmField val RI = of("RI")

                        @JvmField val SC = of("SC")

                        @JvmField val SD = of("SD")

                        @JvmField val TN = of("TN")

                        @JvmField val TX = of("TX")

                        @JvmField val UT = of("UT")

                        @JvmField val VT = of("VT")

                        @JvmField val VA = of("VA")

                        @JvmField val WA = of("WA")

                        @JvmField val WV = of("WV")

                        @JvmField val WI = of("WI")

                        @JvmField val WY = of("WY")

                        @JvmField val DC = of("DC")

                        @JvmField val PR = of("PR")

                        @JvmField val GU = of("GU")

                        @JvmField val VI = of("VI")

                        @JvmStatic fun of(value: String) = State(JsonField.of(value))
                    }

                    enum class Known {
                        AL,
                        AK,
                        AZ,
                        AR,
                        CA,
                        CO,
                        CT,
                        DE,
                        FL,
                        GA,
                        HI,
                        ID,
                        IL,
                        IN,
                        IA,
                        KS,
                        KY,
                        LA,
                        ME,
                        MD,
                        MA,
                        MI,
                        MN,
                        MS,
                        MO,
                        MT,
                        NE,
                        NV,
                        NH,
                        NJ,
                        NM,
                        NY,
                        NC,
                        ND,
                        OH,
                        OK,
                        OR,
                        PA,
                        RI,
                        SC,
                        SD,
                        TN,
                        TX,
                        UT,
                        VT,
                        VA,
                        WA,
                        WV,
                        WI,
                        WY,
                        DC,
                        PR,
                        GU,
                        VI,
                    }

                    enum class Value {
                        AL,
                        AK,
                        AZ,
                        AR,
                        CA,
                        CO,
                        CT,
                        DE,
                        FL,
                        GA,
                        HI,
                        ID,
                        IL,
                        IN,
                        IA,
                        KS,
                        KY,
                        LA,
                        ME,
                        MD,
                        MA,
                        MI,
                        MN,
                        MS,
                        MO,
                        MT,
                        NE,
                        NV,
                        NH,
                        NJ,
                        NM,
                        NY,
                        NC,
                        ND,
                        OH,
                        OK,
                        OR,
                        PA,
                        RI,
                        SC,
                        SD,
                        TN,
                        TX,
                        UT,
                        VT,
                        VA,
                        WA,
                        WV,
                        WI,
                        WY,
                        DC,
                        PR,
                        GU,
                        VI,
                        _UNKNOWN,
                    }

                    fun value(): Value =
                        when (this) {
                            AL -> Value.AL
                            AK -> Value.AK
                            AZ -> Value.AZ
                            AR -> Value.AR
                            CA -> Value.CA
                            CO -> Value.CO
                            CT -> Value.CT
                            DE -> Value.DE
                            FL -> Value.FL
                            GA -> Value.GA
                            HI -> Value.HI
                            ID -> Value.ID
                            IL -> Value.IL
                            IN -> Value.IN
                            IA -> Value.IA
                            KS -> Value.KS
                            KY -> Value.KY
                            LA -> Value.LA
                            ME -> Value.ME
                            MD -> Value.MD
                            MA -> Value.MA
                            MI -> Value.MI
                            MN -> Value.MN
                            MS -> Value.MS
                            MO -> Value.MO
                            MT -> Value.MT
                            NE -> Value.NE
                            NV -> Value.NV
                            NH -> Value.NH
                            NJ -> Value.NJ
                            NM -> Value.NM
                            NY -> Value.NY
                            NC -> Value.NC
                            ND -> Value.ND
                            OH -> Value.OH
                            OK -> Value.OK
                            OR -> Value.OR
                            PA -> Value.PA
                            RI -> Value.RI
                            SC -> Value.SC
                            SD -> Value.SD
                            TN -> Value.TN
                            TX -> Value.TX
                            UT -> Value.UT
                            VT -> Value.VT
                            VA -> Value.VA
                            WA -> Value.WA
                            WV -> Value.WV
                            WI -> Value.WI
                            WY -> Value.WY
                            DC -> Value.DC
                            PR -> Value.PR
                            GU -> Value.GU
                            VI -> Value.VI
                            else -> Value._UNKNOWN
                        }

                    fun known(): Known =
                        when (this) {
                            AL -> Known.AL
                            AK -> Known.AK
                            AZ -> Known.AZ
                            AR -> Known.AR
                            CA -> Known.CA
                            CO -> Known.CO
                            CT -> Known.CT
                            DE -> Known.DE
                            FL -> Known.FL
                            GA -> Known.GA
                            HI -> Known.HI
                            ID -> Known.ID
                            IL -> Known.IL
                            IN -> Known.IN
                            IA -> Known.IA
                            KS -> Known.KS
                            KY -> Known.KY
                            LA -> Known.LA
                            ME -> Known.ME
                            MD -> Known.MD
                            MA -> Known.MA
                            MI -> Known.MI
                            MN -> Known.MN
                            MS -> Known.MS
                            MO -> Known.MO
                            MT -> Known.MT
                            NE -> Known.NE
                            NV -> Known.NV
                            NH -> Known.NH
                            NJ -> Known.NJ
                            NM -> Known.NM
                            NY -> Known.NY
                            NC -> Known.NC
                            ND -> Known.ND
                            OH -> Known.OH
                            OK -> Known.OK
                            OR -> Known.OR
                            PA -> Known.PA
                            RI -> Known.RI
                            SC -> Known.SC
                            SD -> Known.SD
                            TN -> Known.TN
                            TX -> Known.TX
                            UT -> Known.UT
                            VT -> Known.VT
                            VA -> Known.VA
                            WA -> Known.WA
                            WV -> Known.WV
                            WI -> Known.WI
                            WY -> Known.WY
                            DC -> Known.DC
                            PR -> Known.PR
                            GU -> Known.GU
                            VI -> Known.VI
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

                    @JvmSynthetic
                    internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

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

                    return other is GeolocationModel &&
                        this.country == other.country &&
                        this.city == other.city &&
                        this.state == other.state &&
                        this.additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(country, city, state, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "GeolocationModel{country=$country, city=$city, state=$state, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Geolocation &&
                    this.geolocation == other.geolocation &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(geolocation, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Geolocation{geolocation=$geolocation, additionalProperties=$additionalProperties}"
        }

        class Server
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val server: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("server") @ExcludeMissing server: JsonField<String> = JsonMissing.of()
            ) : this(server, mutableMapOf())

            /** Proxy server URL */
            fun server(): String = server.getRequired("server")

            @JsonProperty("server") @ExcludeMissing fun _server(): JsonField<String> = server

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

                /** Returns a mutable builder for constructing an instance of [Server]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Server]. */
            class Builder internal constructor() {

                private var server: JsonField<String>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(server: Server) = apply {
                    this.server = server.server
                    this.additionalProperties = server.additionalProperties.toMutableMap()
                }

                /** Proxy server URL */
                fun server(server: String) = server(JsonField.of(server))

                /** Proxy server URL */
                fun server(server: JsonField<String>) = apply { this.server = server }

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

                fun build(): Server =
                    Server(checkRequired("server", server), additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): Server = apply {
                if (validated) {
                    return@apply
                }

                server()
                validated = true
            }

            @JvmSynthetic internal fun validity(): Int = (if (server.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Server &&
                    this.server == other.server &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(server, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Server{server=$server, additionalProperties=$additionalProperties}"
        }

        class UnionMember3
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val id: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            ) : this(type, id, mutableMapOf())

            /** Specific fixed IP identifier. Omit to use any active fixed IP owned by the org. */
            fun id(): Optional<String> = id.getOptional("id")

            /** Expected to always return `JsonValue.from("fixed")`. It is set by default. */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

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

                /** Returns a mutable builder for constructing an instance of [UnionMember3]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UnionMember3]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("fixed")
                private var id: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(unionMember3: UnionMember3) = apply {
                    this.type = unionMember3.type
                    this.id = unionMember3.id
                    this.additionalProperties = unionMember3.additionalProperties.toMutableMap()
                }

                /** Use Steel fixed IP proxies */
                fun type(type: JsonValue) = apply { this.type = type }

                /**
                 * Specific fixed IP identifier. Omit to use any active fixed IP owned by the org.
                 */
                fun id(id: String) = id(JsonField.of(id))

                /**
                 * Specific fixed IP identifier. Omit to use any active fixed IP owned by the org.
                 */
                fun id(id: JsonField<String>) = apply { this.id = id }

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

                fun build(): UnionMember3 =
                    UnionMember3(type, id, additionalProperties.toMutableMap())
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: SteelInvalidDataException) {
                    false
                }

            private var validated: Boolean = false

            fun validate(): UnionMember3 = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("fixed")) {
                        throw SteelInvalidDataException("'type' is invalid, received $it")
                    }
                }
                id()
                validated = true
            }

            @JvmSynthetic
            internal fun validity(): Int =
                (type.let { if (it == JsonValue.from("fixed")) 1 else 0 }) +
                    (if (id.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UnionMember3 &&
                    this.type == other.type &&
                    this.id == other.id &&
                    this.additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(type, id, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UnionMember3{type=$type, id=$id, additionalProperties=$additionalProperties}"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionCreateParams &&
            this.body == other.body &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SessionCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
