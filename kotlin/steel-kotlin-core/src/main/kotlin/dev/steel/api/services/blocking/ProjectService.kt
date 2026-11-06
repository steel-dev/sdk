package dev.steel.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.projects.Project
import dev.steel.api.models.projects.ProjectListParams
import dev.steel.api.models.projects.ProjectRetrieveParams
import dev.steel.api.models.projects.ProjectsList
import java.util.function.Consumer

interface ProjectService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectService

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: ProjectRetrieveParams,
        requestOptions: RequestOptions,
    ): Project = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(id: String, params: ProjectRetrieveParams): Project =
        retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String): Project = retrieve(id, ProjectRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): Project =
        retrieve(id, ProjectRetrieveParams.none(), requestOptions)

    /** Get a project by ID. */
    fun retrieve(params: ProjectRetrieveParams, requestOptions: RequestOptions): Project

    /** @see retrieve */
    fun retrieve(params: ProjectRetrieveParams): Project = retrieve(params, RequestOptions.none())

    /** List projects for the authenticated organization. */
    fun list(params: ProjectListParams, requestOptions: RequestOptions): ProjectsList

    /** @see list */
    fun list(params: ProjectListParams): ProjectsList = list(params, RequestOptions.none())

    /** @see list */
    fun list(): ProjectsList = list(ProjectListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ProjectsList =
        list(ProjectListParams.none(), requestOptions)

    /** A view of [ProjectService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectService.WithRawResponse

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: ProjectRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Project> = retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String, params: ProjectRetrieveParams): HttpResponseFor<Project> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String): HttpResponseFor<Project> =
            retrieve(id, ProjectRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String, requestOptions: RequestOptions): HttpResponseFor<Project> =
            retrieve(id, ProjectRetrieveParams.none(), requestOptions)

        /** Get a project by ID. */
        @MustBeClosed
        fun retrieve(
            params: ProjectRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Project>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: ProjectRetrieveParams): HttpResponseFor<Project> =
            retrieve(params, RequestOptions.none())

        /** List projects for the authenticated organization. */
        @MustBeClosed
        fun list(
            params: ProjectListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProjectsList>

        /** @see list */
        @MustBeClosed
        fun list(params: ProjectListParams): HttpResponseFor<ProjectsList> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed fun list(): HttpResponseFor<ProjectsList> = list(ProjectListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ProjectsList> =
            list(ProjectListParams.none(), requestOptions)
    }
}
