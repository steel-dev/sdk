package dev.steel.api.errors

import dev.steel.api.core.JsonValue
import dev.steel.api.core.http.Headers

abstract class SteelServiceException
protected constructor(message: String, cause: Throwable? = null) : SteelException(message, cause) {

    abstract fun statusCode(): Int

    abstract fun headers(): Headers

    abstract fun body(): JsonValue
}
