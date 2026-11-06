package dev.steel.api.models.sessions

import dev.steel.api.core.AutoPagerAsync
import dev.steel.api.core.PageAsync
import dev.steel.api.core.checkRequired
import dev.steel.api.services.async.SessionServiceAsync
import java.util.Objects
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

class SessionListPageAsync
private constructor(
    private val service: SessionServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: SessionListParams,
    private val response: Sessionslist,
) : PageAsync<Sessionslist.Session> {

    fun sessions(): List<Sessionslist.Session> =
        response._sessions().getOptional("sessions").getOrNull() ?: emptyList()

    override fun items(): List<Sessionslist.Session> = sessions()

    override fun hasNextPage(): Boolean =
        items().isNotEmpty() && items().last()._id().asKnown().isPresent

    fun nextPageParams(): SessionListParams =
        params.toBuilder().cursorId(items().last()._id().asKnown().getOrNull()).build()

    override fun nextPage(): CompletableFuture<SessionListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<Sessionslist.Session> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    fun params(): SessionListParams = params

    fun response(): Sessionslist = response

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var service: SessionServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: SessionListParams? = null
        private var response: Sessionslist? = null

        @JvmSynthetic
        internal fun from(sessionListPageAsync: SessionListPageAsync) = apply {
            this.service = sessionListPageAsync.service
            this.streamHandlerExecutor = sessionListPageAsync.streamHandlerExecutor
            this.params = sessionListPageAsync.params
            this.response = sessionListPageAsync.response
        }

        fun service(service: SessionServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        fun params(params: SessionListParams) = apply { this.params = params }

        fun response(response: Sessionslist) = apply { this.response = response }

        fun build(): SessionListPageAsync =
            SessionListPageAsync(
                checkRequired("service", service),
                checkRequired("streamHandlerExecutor", streamHandlerExecutor),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionListPageAsync &&
            this.service == other.service &&
            this.streamHandlerExecutor == other.streamHandlerExecutor &&
            this.params == other.params &&
            this.response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "SessionListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
