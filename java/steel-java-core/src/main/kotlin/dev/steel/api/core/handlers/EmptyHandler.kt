@file:JvmName("EmptyHandler")

package dev.steel.api.core.handlers

import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponse.Handler

@JvmSynthetic internal fun emptyHandler(): Handler<Void?> = EmptyHandlerInternal

private object EmptyHandlerInternal : Handler<Void?> {
    override fun handle(response: HttpResponse): Void? = null
}
