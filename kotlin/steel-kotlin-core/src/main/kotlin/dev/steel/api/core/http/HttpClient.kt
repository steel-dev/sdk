package dev.steel.api.core.http

import dev.steel.api.core.RequestOptions
import java.lang.AutoCloseable

interface HttpClient : AutoCloseable {

    fun execute(
        request: HttpRequest,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    fun execute(request: HttpRequest): HttpResponse = execute(request, RequestOptions.none())

    suspend fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    suspend fun executeAsync(request: HttpRequest): HttpResponse =
        executeAsync(request, RequestOptions.none())

    /** Overridden from [AutoCloseable] to not have a checked exception in its signature. */
    override fun close()
}
