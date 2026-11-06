package dev.steel.api.services.async

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
import dev.steel.api.core.prepareAsync
import dev.steel.api.models.projects.Project
import dev.steel.api.models.projects.ProjectListParams
import dev.steel.api.models.projects.ProjectRetrieveParams
import dev.steel.api.models.projects.ProjectsList
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ProjectServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ProjectServiceAsync {

    private val withRawResponse: ProjectServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ProjectServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectServiceAsync =
        ProjectServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ProjectRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Project> =
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: ProjectListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProjectsList> =
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ProjectServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProjectServiceAsync.WithRawResponse =
            ProjectServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<Project> =
            jsonHandler<Project>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ProjectRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Project>> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "projects", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<ProjectsList> =
            jsonHandler<ProjectsList>(clientOptions.jsonMapper)

        override fun list(
            params: ProjectListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProjectsList>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "projects")
                    .build()
                    .prepareAsync(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, options) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (options.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }
    }
}
