@file:JvmName("StringHandler")

package dev.steel.api.core.handlers

import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponse.Handler

@JvmSynthetic internal fun stringHandler(): Handler<String> = StringHandlerInternal

private object StringHandlerInternal : Handler<String> {
    override fun handle(response: HttpResponse): String =
        response.body().readBytes().toString(Charsets.UTF_8)
}
