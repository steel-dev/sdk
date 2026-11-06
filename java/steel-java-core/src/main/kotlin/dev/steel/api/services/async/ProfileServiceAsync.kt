package dev.steel.api.services.async

import dev.steel.api.core.ClientOptions
import dev.steel.api.core.RequestOptions
import dev.steel.api.core.http.HttpResponseFor
import dev.steel.api.models.profiles.ProfileCreateParams
import dev.steel.api.models.profiles.ProfileCreateResponse
import dev.steel.api.models.profiles.ProfileGetParams
import dev.steel.api.models.profiles.ProfileGetResponse
import dev.steel.api.models.profiles.ProfileListParams
import dev.steel.api.models.profiles.ProfileListResponse
import dev.steel.api.models.profiles.ProfileUpdateParams
import dev.steel.api.models.profiles.ProfileUpdateResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ProfileServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProfileServiceAsync

    /** Create a new profile */
    fun create(
        params: ProfileCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProfileCreateResponse>

    /** @see create */
    fun create(params: ProfileCreateParams): CompletableFuture<ProfileCreateResponse> =
        create(params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: ProfileUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProfileUpdateResponse> =
        update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(id: String, params: ProfileUpdateParams): CompletableFuture<ProfileUpdateResponse> =
        update(id, params, RequestOptions.none())

    /** Update an existing profile */
    fun update(
        params: ProfileUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProfileUpdateResponse>

    /** @see update */
    fun update(params: ProfileUpdateParams): CompletableFuture<ProfileUpdateResponse> =
        update(params, RequestOptions.none())

    /** Retrieve a list of all profiles */
    fun list(
        params: ProfileListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProfileListResponse>

    /** @see list */
    fun list(params: ProfileListParams): CompletableFuture<ProfileListResponse> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(): CompletableFuture<ProfileListResponse> = list(ProfileListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ProfileListResponse> =
        list(ProfileListParams.none(), requestOptions)

    /** @see get */
    fun get(
        id: String,
        params: ProfileGetParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProfileGetResponse> =
        get(params.toBuilder().id(id).build(), requestOptions)

    /** @see get */
    fun get(id: String, params: ProfileGetParams): CompletableFuture<ProfileGetResponse> =
        get(id, params, RequestOptions.none())

    /** @see get */
    fun get(id: String): CompletableFuture<ProfileGetResponse> = get(id, ProfileGetParams.none())

    /** @see get */
    fun get(id: String, requestOptions: RequestOptions): CompletableFuture<ProfileGetResponse> =
        get(id, ProfileGetParams.none(), requestOptions)

    /** Retrieve a profile by ID */
    fun get(
        params: ProfileGetParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ProfileGetResponse>

    /** @see get */
    fun get(params: ProfileGetParams): CompletableFuture<ProfileGetResponse> =
        get(params, RequestOptions.none())

    /**
     * A view of [ProfileServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProfileServiceAsync.WithRawResponse

        /** Create a new profile */
        fun create(
            params: ProfileCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProfileCreateResponse>>

        /** @see create */
        fun create(
            params: ProfileCreateParams
        ): CompletableFuture<HttpResponseFor<ProfileCreateResponse>> =
            create(params, RequestOptions.none())

        /** @see update */
        fun update(
            id: String,
            params: ProfileUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProfileUpdateResponse>> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        fun update(
            id: String,
            params: ProfileUpdateParams,
        ): CompletableFuture<HttpResponseFor<ProfileUpdateResponse>> =
            update(id, params, RequestOptions.none())

        /** Update an existing profile */
        fun update(
            params: ProfileUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProfileUpdateResponse>>

        /** @see update */
        fun update(
            params: ProfileUpdateParams
        ): CompletableFuture<HttpResponseFor<ProfileUpdateResponse>> =
            update(params, RequestOptions.none())

        /** Retrieve a list of all profiles */
        fun list(
            params: ProfileListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProfileListResponse>>

        /** @see list */
        fun list(
            params: ProfileListParams
        ): CompletableFuture<HttpResponseFor<ProfileListResponse>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(): CompletableFuture<HttpResponseFor<ProfileListResponse>> =
            list(ProfileListParams.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ProfileListResponse>> =
            list(ProfileListParams.none(), requestOptions)

        /** @see get */
        fun get(
            id: String,
            params: ProfileGetParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProfileGetResponse>> =
            get(params.toBuilder().id(id).build(), requestOptions)

        /** @see get */
        fun get(
            id: String,
            params: ProfileGetParams,
        ): CompletableFuture<HttpResponseFor<ProfileGetResponse>> =
            get(id, params, RequestOptions.none())

        /** @see get */
        fun get(id: String): CompletableFuture<HttpResponseFor<ProfileGetResponse>> =
            get(id, ProfileGetParams.none())

        /** @see get */
        fun get(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProfileGetResponse>> =
            get(id, ProfileGetParams.none(), requestOptions)

        /** Retrieve a profile by ID */
        fun get(
            params: ProfileGetParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ProfileGetResponse>>

        /** @see get */
        fun get(params: ProfileGetParams): CompletableFuture<HttpResponseFor<ProfileGetResponse>> =
            get(params, RequestOptions.none())
    }
}
