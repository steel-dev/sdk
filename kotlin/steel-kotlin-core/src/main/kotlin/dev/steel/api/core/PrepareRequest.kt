@file:JvmName("PrepareRequest")

package dev.steel.api.core

import dev.steel.api.core.http.HttpRequest

@JvmSynthetic
internal fun HttpRequest.prepare(clientOptions: ClientOptions, params: Params): HttpRequest =
    toBuilder()
        .putAllQueryParams(clientOptions.queryParams)
        .replaceAllQueryParams(params._queryParams())
        .putAllHeaders(clientOptions.headers)
        .replaceAllHeaders(params._headers())
        .build()

@JvmSynthetic
internal suspend fun HttpRequest.prepareAsync(
    clientOptions: ClientOptions,
    params: Params,
): HttpRequest = prepare(clientOptions, params)
