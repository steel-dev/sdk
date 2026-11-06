package dev.steel.api.errors

import dev.steel.api.core.JsonValue
import dev.steel.api.core.checkRequired
import dev.steel.api.core.http.Headers
import dev.steel.api.core.jsonMapper

class InternalServerException
private constructor(
    private val statusCode: Int,
    private val headers: Headers,
    private val body: JsonValue,
    cause: Throwable?,
) :
    SteelServiceException(
        "$statusCode: ${if (body.isMissing()) "Unknown" else jsonMapper().writeValueAsString(body)}",
        cause,
    ) {

    override fun statusCode(): Int = statusCode

    override fun headers(): Headers = headers

    override fun body(): JsonValue = body

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [InternalServerException].
         *
         * The following fields are required:
         * ```java
         * .statusCode()
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [InternalServerException]. */
    class Builder internal constructor() {

        private var statusCode: Int? = null
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(internalServerException: InternalServerException) = apply {
            statusCode = internalServerException.statusCode
            headers = internalServerException.headers
            body = internalServerException.body
            cause = internalServerException.cause
        }

        fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        fun headers(headers: Headers) = apply { this.headers = headers }

        fun body(body: JsonValue) = apply { this.body = body }

        fun cause(cause: Throwable?) = apply { this.cause = cause }

        /**
         * Returns an immutable instance of [InternalServerException].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .statusCode()
         * .headers()
         * .body()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): InternalServerException =
            InternalServerException(
                checkRequired("statusCode", statusCode),
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
