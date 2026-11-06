package dev.steel.api.models.sessions

import dev.steel.api.core.AutoPager
import dev.steel.api.core.Page
import dev.steel.api.core.checkRequired
import dev.steel.api.services.blocking.SessionService
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class SessionListPage
private constructor(
    private val service: SessionService,
    private val params: SessionListParams,
    private val response: Sessionslist,
) : Page<Sessionslist.Session> {

    fun sessions(): List<Sessionslist.Session> =
        response._sessions().getOptional("sessions").getOrNull() ?: emptyList()

    override fun items(): List<Sessionslist.Session> = sessions()

    override fun hasNextPage(): Boolean =
        items().isNotEmpty() && items().last()._id().asKnown().isPresent

    fun nextPageParams(): SessionListParams =
        params.toBuilder().cursorId(items().last()._id().asKnown().getOrNull()).build()

    override fun nextPage(): SessionListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<Sessionslist.Session> = AutoPager.from(this)

    fun params(): SessionListParams = params

    fun response(): Sessionslist = response

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var service: SessionService? = null
        private var params: SessionListParams? = null
        private var response: Sessionslist? = null

        @JvmSynthetic
        internal fun from(sessionListPage: SessionListPage) = apply {
            this.service = sessionListPage.service
            this.params = sessionListPage.params
            this.response = sessionListPage.response
        }

        fun service(service: SessionService) = apply { this.service = service }

        fun params(params: SessionListParams) = apply { this.params = params }

        fun response(response: Sessionslist) = apply { this.response = response }

        fun build(): SessionListPage =
            SessionListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SessionListPage &&
            this.service == other.service &&
            this.params == other.params &&
            this.response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "SessionListPage{service=$service, params=$params, response=$response}"
}
