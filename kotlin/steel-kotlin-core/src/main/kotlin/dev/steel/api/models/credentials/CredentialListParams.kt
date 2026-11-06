package dev.steel.api.models.credentials

import dev.steel.api.core.Params
import dev.steel.api.core.http.Headers
import dev.steel.api.core.http.QueryParams
import java.util.Objects
import kotlin.io.path.name

/** Fetches all credential metadata for the current organization. */
class CredentialListParams
private constructor(
    private val namespace: String?,
    private val origin: String?,
    private val projectId: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** namespace credential is stored against */
    fun namespace(): String? = namespace

    /** website origin the credential is for */
    fun origin(): String? = origin

    /** Project to query credentials from. */
    fun projectId(): String? = projectId

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): CredentialListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [CredentialListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CredentialListParams]. */
    class Builder internal constructor() {

        private var namespace: String? = null
        private var origin: String? = null
        private var projectId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(credentialListParams: CredentialListParams) = apply {
            this.namespace = credentialListParams.namespace
            this.origin = credentialListParams.origin
            this.projectId = credentialListParams.projectId
            this.additionalHeaders = credentialListParams.additionalHeaders.toBuilder()
            this.additionalQueryParams = credentialListParams.additionalQueryParams.toBuilder()
        }

        /** namespace credential is stored against */
        fun namespace(namespace: String?) = apply { this.namespace = namespace }

        /** website origin the credential is for */
        fun origin(origin: String?) = apply { this.origin = origin }

        /** Project to query credentials from. */
        fun projectId(projectId: String?) = apply { this.projectId = projectId }

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

        fun build(): CredentialListParams =
            CredentialListParams(
                namespace,
                origin,
                projectId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                namespace?.let { put("namespace", it) }
                origin?.let { put("origin", it) }
                projectId?.let { put("projectId", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CredentialListParams &&
            this.namespace == other.namespace &&
            this.origin == other.origin &&
            this.projectId == other.projectId &&
            this.additionalHeaders == other.additionalHeaders &&
            this.additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(namespace, origin, projectId, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "CredentialListParams{namespace=$namespace, origin=$origin, projectId=$projectId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
