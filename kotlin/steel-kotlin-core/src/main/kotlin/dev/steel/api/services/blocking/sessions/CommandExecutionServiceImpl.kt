package dev.steel.api.services.blocking.sessions

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.checkRequired
import dev.steel.api.core.handlers.errorBodyHandler
import dev.steel.api.core.handlers.errorHandler
import dev.steel.api.core.handlers.jsonHandler
import dev.steel.api.core.http.HttpMethod
import dev.steel.api.core.http.HttpRequest
import dev.steel.api.core.http.HttpResponse
import dev.steel.api.core.http.HttpResponse.Handler
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepare
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelResponse
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveResponse
import java.util.function.Consumer

class CommandExecutionServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    CommandExecutionService {

    private val withRawResponse: CommandExecutionService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CommandExecutionService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CommandExecutionService =
        CommandExecutionServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: CommandExecutionRetrieveParams,
        requestOptions: RequestOptions,
    ): CommandExecutionRetrieveResponse = withRawResponse().retrieve(params, requestOptions).parse()

    override fun cancel(
        params: CommandExecutionCancelParams,
        requestOptions: RequestOptions,
    ): CommandExecutionCancelResponse = withRawResponse().cancel(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CommandExecutionService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CommandExecutionService.WithRawResponse =
            CommandExecutionServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<CommandExecutionRetrieveResponse> =
            jsonHandler<CommandExecutionRetrieveResponse>(clientOptions.jsonMapper)

        override fun retrieve(
            params: CommandExecutionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionRetrieveResponse> {
            checkRequired("executionId", params.executionId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "commands",
                        "executions",
                        params._pathParam(1),
                    )
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val cancelHandler: Handler<CommandExecutionCancelResponse> =
            jsonHandler<CommandExecutionCancelResponse>(clientOptions.jsonMapper)

        override fun cancel(
            params: CommandExecutionCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommandExecutionCancelResponse> {
            checkRequired("executionId", params.executionId())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "commands",
                        "executions",
                        params._pathParam(1),
                        "cancel",
                    )
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { cancelHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
