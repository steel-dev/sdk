package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.projects.Project
import dev.steel.api.models.projects.ProjectListParams
import dev.steel.api.models.projects.ProjectRetrieveParams
import dev.steel.api.models.projects.ProjectsList
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ProjectServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectServiceAsync

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: ProjectRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Project> = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(id: String, params: ProjectRetrieveParams): CompletableFuture<Project> =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): CompletableFuture<Project> =
        retrieve(id, ProjectRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): CompletableFuture<Project> =
        retrieve(id, ProjectRetrieveParams.none(), requestOptions)

    /** Get a project by ID. */
    fun retrieve(
        params: ProjectRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Project>

    /** @see retrieve */
    fun retrieve(params: ProjectRetrieveParams): CompletableFuture<Project> =
        retrieve(params, RequestOptions.none())

    /** List projects for the authenticated organization. */
    fun list(
        params: ProjectListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProjectsList>

    /** @see list */
    fun list(params: ProjectListParams): CompletableFuture<ProjectsList> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<ProjectsList> = list(ProjectListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ProjectsList> =
        list(ProjectListParams.none(), requestOptions)

    /**
     * A view of [ProjectServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProjectServiceAsync.WithRawResponse

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: ProjectRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Project>> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: ProjectRetrieveParams,
        ): CompletableFuture<HttpResponseFor<Project>> = retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(id: String): CompletableFuture<HttpResponseFor<Project>> =
            retrieve(id, ProjectRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Project>> =
            retrieve(id, ProjectRetrieveParams.none(), requestOptions)

        /** Get a project by ID. */
        fun retrieve(
            params: ProjectRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Project>>

        /** @see retrieve */
        fun retrieve(params: ProjectRetrieveParams): CompletableFuture<HttpResponseFor<Project>> =
            retrieve(params, RequestOptions.none())

        /** List projects for the authenticated organization. */
        fun list(
            params: ProjectListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProjectsList>>

        /** @see list */
        fun list(params: ProjectListParams): CompletableFuture<HttpResponseFor<ProjectsList>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<ProjectsList>> =
            list(ProjectListParams.none())

        /** @see list */
        fun list(requestOptions: RequestOptions): CompletableFuture<HttpResponseFor<ProjectsList>> =
            list(ProjectListParams.none(), requestOptions)
    }
}
