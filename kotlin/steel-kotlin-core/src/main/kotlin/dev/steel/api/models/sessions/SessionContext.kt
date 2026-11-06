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
import java.util.Collections
import java.util.Objects
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Session context data returned from a browser session. */
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
    fun cookies(): List<Cookie>? = cookies.getNullable("cookies")

    /** Domain-specific indexedDB items to initialize in the session */
    fun indexedDb(): IndexedDb? = indexedDb.getNullable("indexedDB")

    /** Domain-specific localStorage items to initialize in the session */
    fun localStorage(): LocalStorage? = localStorage.getNullable("localStorage")

    /** Domain-specific sessionStorage items to initialize in the session */
    fun sessionStorage(): SessionStorage? = sessionStorage.getNullable("sessionStorage")

    @JsonProperty("cookies") @ExcludeMissing fun _cookies(): JsonField<List<Cookie>> = cookies

    @JsonProperty("indexedDB") @ExcludeMissing fun _indexedDb(): JsonField<IndexedDb> = indexedDb

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

        cookies()?.let { it.forEach { it.validate() } }
        indexedDb()?.let { it.validate() }
        localStorage()?.let { it.validate() }
        sessionStorage()?.let { it.validate() }
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
            @JsonProperty("domain") @ExcludeMissing domain: JsonField<String> = JsonMissing.of(),
            @JsonProperty("expires") @ExcludeMissing expires: JsonField<Double> = JsonMissing.of(),
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
            @JsonProperty("secure") @ExcludeMissing secure: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("session") @ExcludeMissing session: JsonField<Boolean> = JsonMissing.of(),
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
        fun domain(): String? = domain.getNullable("domain")

        /** The expiration date of the cookie */
        fun expires(): Double? = expires.getNullable("expires")

        /** Whether the cookie is HTTP only */
        fun httpOnly(): Boolean? = httpOnly.getNullable("httpOnly")

        /** The partition key of the cookie */
        fun partitionKey(): PartitionKey? = partitionKey.getNullable("partitionKey")

        /** The path of the cookie */
        fun path(): String? = path.getNullable("path")

        /** The priority of the cookie */
        fun priority(): Priority? = priority.getNullable("priority")

        /** Whether the cookie is a same party cookie */
        fun sameParty(): Boolean? = sameParty.getNullable("sameParty")

        /** The same site attribute of the cookie */
        fun sameSite(): SameSite? = sameSite.getNullable("sameSite")

        /** Whether the cookie is secure */
        fun secure(): Boolean? = secure.getNullable("secure")

        /** Whether the cookie is a session cookie */
        fun session(): Boolean? = session.getNullable("session")

        /** The size of the cookie */
        fun size(): Double? = size.getNullable("size")

        /** The source port of the cookie */
        fun sourcePort(): Double? = sourcePort.getNullable("sourcePort")

        /** The source scheme of the cookie */
        fun sourceScheme(): SourceScheme? = sourceScheme.getNullable("sourceScheme")

        /** The URL of the cookie */
        fun url(): String? = url.getNullable("url")

        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<String> = value

        @JsonProperty("domain") @ExcludeMissing fun _domain(): JsonField<String> = domain

        @JsonProperty("expires") @ExcludeMissing fun _expires(): JsonField<Double> = expires

        @JsonProperty("httpOnly") @ExcludeMissing fun _httpOnly(): JsonField<Boolean> = httpOnly

        @JsonProperty("partitionKey")
        @ExcludeMissing
        fun _partitionKey(): JsonField<PartitionKey> = partitionKey

        @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

        @JsonProperty("priority") @ExcludeMissing fun _priority(): JsonField<Priority> = priority

        @JsonProperty("sameParty") @ExcludeMissing fun _sameParty(): JsonField<Boolean> = sameParty

        @JsonProperty("sameSite") @ExcludeMissing fun _sameSite(): JsonField<SameSite> = sameSite

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
            fun partitionKey(partitionKey: PartitionKey) = partitionKey(JsonField.of(partitionKey))

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
            fun sourcePort(sourcePort: JsonField<Double>) = apply { this.sourcePort = sourcePort }

            /** The source scheme of the cookie */
            fun sourceScheme(sourceScheme: SourceScheme) = sourceScheme(JsonField.of(sourceScheme))

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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
            partitionKey()?.let { it.validate() }
            path()
            priority()?.let { it.validate() }
            sameParty()
            sameSite()?.let { it.validate() }
            secure()
            session()
            size()
            sourcePort()
            sourceScheme()?.let { it.validate() }
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
             * Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
             */
            fun hasCrossSiteAncestor(): Boolean =
                hasCrossSiteAncestor.getRequired("hasCrossSiteAncestor")

            /**
             * The site of the top-level URL the browser was visiting at the start of the request to
             * the endpoint that set the cookie.
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
                fun topLevelSite(topLevelSite: String) = topLevelSite(JsonField.of(topLevelSite))

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

        class SourceScheme @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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

            return other is LocalStorage && this.additionalProperties == other.additionalProperties
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

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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
