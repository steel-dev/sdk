package dev.steel.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
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
import java.util.function.Consumer

interface ProfileService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProfileService

    /** Create a new profile */
    fun create(params: ProfileCreateParams, requestOptions: RequestOptions): ProfileCreateResponse

    /** @see create */
    fun create(params: ProfileCreateParams): ProfileCreateResponse =
        create(params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: ProfileUpdateParams,
        requestOptions: RequestOptions,
    ): ProfileUpdateResponse = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(id: String, params: ProfileUpdateParams): ProfileUpdateResponse =
        update(id, params, RequestOptions.none())

    /** Update an existing profile */
    fun update(params: ProfileUpdateParams, requestOptions: RequestOptions): ProfileUpdateResponse

    /** @see update */
    fun update(params: ProfileUpdateParams): ProfileUpdateResponse =
        update(params, RequestOptions.none())

    /** Retrieve a list of all profiles */
    fun list(params: ProfileListParams, requestOptions: RequestOptions): ProfileListResponse

    /** @see list */
    fun list(params: ProfileListParams): ProfileListResponse = list(params, RequestOptions.none())

    /** @see list */
    fun list(): ProfileListResponse = list(ProfileListParams.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ProfileListResponse =
        list(ProfileListParams.none(), requestOptions)

    /** @see get */
    fun get(
        id: String,
        params: ProfileGetParams,
        requestOptions: RequestOptions,
    ): ProfileGetResponse = get(params.toBuilder().id(id).build(), requestOptions)

    /** @see get */
    fun get(id: String, params: ProfileGetParams): ProfileGetResponse =
        get(id, params, RequestOptions.none())

    /** @see get */
    fun get(id: String): ProfileGetResponse = get(id, ProfileGetParams.none())

    /** @see get */
    fun get(id: String, requestOptions: RequestOptions): ProfileGetResponse =
        get(id, ProfileGetParams.none(), requestOptions)

    /** Retrieve a profile by ID */
    fun get(params: ProfileGetParams, requestOptions: RequestOptions): ProfileGetResponse

    /** @see get */
    fun get(params: ProfileGetParams): ProfileGetResponse = get(params, RequestOptions.none())

    /** A view of [ProfileService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProfileService.WithRawResponse

        /** Create a new profile */
        @MustBeClosed
        fun create(
            params: ProfileCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileCreateResponse>

        /** @see create */
        @MustBeClosed
        fun create(params: ProfileCreateParams): HttpResponseFor<ProfileCreateResponse> =
            create(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            id: String,
            params: ProfileUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileUpdateResponse> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            id: String,
            params: ProfileUpdateParams,
        ): HttpResponseFor<ProfileUpdateResponse> = update(id, params, RequestOptions.none())

        /** Update an existing profile */
        @MustBeClosed
        fun update(
            params: ProfileUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileUpdateResponse>

        /** @see update */
        @MustBeClosed
        fun update(params: ProfileUpdateParams): HttpResponseFor<ProfileUpdateResponse> =
            update(params, RequestOptions.none())

        /** Retrieve a list of all profiles */
        @MustBeClosed
        fun list(
            params: ProfileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileListResponse>

        /** @see list */
        @MustBeClosed
        fun list(params: ProfileListParams): HttpResponseFor<ProfileListResponse> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(): HttpResponseFor<ProfileListResponse> = list(ProfileListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ProfileListResponse> =
            list(ProfileListParams.none(), requestOptions)

        /** @see get */
        @MustBeClosed
        fun get(
            id: String,
            params: ProfileGetParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileGetResponse> =
            get(params.toBuilder().id(id).build(), requestOptions)

        /** @see get */
        @MustBeClosed
        fun get(id: String, params: ProfileGetParams): HttpResponseFor<ProfileGetResponse> =
            get(id, params, RequestOptions.none())

        /** @see get */
        @MustBeClosed
        fun get(id: String): HttpResponseFor<ProfileGetResponse> = get(id, ProfileGetParams.none())

        /** @see get */
        @MustBeClosed
        fun get(id: String, requestOptions: RequestOptions): HttpResponseFor<ProfileGetResponse> =
            get(id, ProfileGetParams.none(), requestOptions)

        /** Retrieve a profile by ID */
        @MustBeClosed
        fun get(
            params: ProfileGetParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileGetResponse>

        /** @see get */
        @MustBeClosed
        fun get(params: ProfileGetParams): HttpResponseFor<ProfileGetResponse> =
            get(params, RequestOptions.none())
    }
}
