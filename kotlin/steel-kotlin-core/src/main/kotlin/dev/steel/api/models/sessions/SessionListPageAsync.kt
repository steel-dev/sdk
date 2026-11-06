package dev.steel.api.models.sessions

import dev.steel.api.core.PageAsync
import dev.steel.api.core.autoPagerAsync
import dev.steel.api.core.checkRequired
import dev.steel.api.services.async.SessionServiceAsync
import java.util.Objects
import kotlin.jvm.optionals.getOrNull
import kotlinx.coroutines.flow.Flow

class SessionListPageAsync
private constructor(
    private val service: SessionServiceAsync,
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

    override suspend fun nextPage(): SessionListPageAsync = service.list(nextPageParams())

    fun autoPager(): Flow<Sessionslist.Session> = autoPagerAsync(this)

    fun params(): SessionListParams = params

    fun response(): Sessionslist = response

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var service: SessionServiceAsync? = null
        private var params: SessionListParams? = null
        private var response: Sessionslist? = null

        @JvmSynthetic
        internal fun from(sessionListPageAsync: SessionListPageAsync) = apply {
            this.service = sessionListPageAsync.service
            this.params = sessionListPageAsync.params
            this.response = sessionListPageAsync.response
        }

        fun service(service: SessionServiceAsync) = apply { this.service = service }

        fun params(params: SessionListParams) = apply { this.params = params }

        fun response(response: Sessionslist) = apply { this.response = response }

        fun build(): SessionListPageAsync =
            SessionListPageAsync(
                checkRequired("service", service),
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
            this.params == other.params &&
            this.response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "SessionListPageAsync{service=$service, params=$params, response=$response}"
}
