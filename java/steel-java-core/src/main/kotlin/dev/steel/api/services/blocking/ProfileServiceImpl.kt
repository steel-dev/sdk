package dev.steel.api.services.blocking

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
import dev.steel.api.core.http.multipartFormData
import dev.steel.api.core.http.parseable
import dev.steel.api.core.prepare
import dev.steel.api.models.profiles.ProfileCreateParams
import dev.steel.api.models.profiles.ProfileCreateResponse
import dev.steel.api.models.profiles.ProfileGetParams
import dev.steel.api.models.profiles.ProfileGetResponse
import dev.steel.api.models.profiles.ProfileListParams
import dev.steel.api.models.profiles.ProfileListResponse
import dev.steel.api.models.profiles.ProfileUpdateParams
import dev.steel.api.models.profiles.ProfileUpdateResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ProfileServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ProfileService {

    private val withRawResponse: ProfileService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ProfileService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProfileService =
        ProfileServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: ProfileCreateParams,
        requestOptions: RequestOptions,
    ): ProfileCreateResponse = withRawResponse().create(params, requestOptions).parse()

    override fun update(
        params: ProfileUpdateParams,
        requestOptions: RequestOptions,
    ): ProfileUpdateResponse = withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: ProfileListParams,
        requestOptions: RequestOptions,
    ): ProfileListResponse = withRawResponse().list(params, requestOptions).parse()

    override fun get(params: ProfileGetParams, requestOptions: RequestOptions): ProfileGetResponse =
        withRawResponse().get(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ProfileService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProfileService.WithRawResponse =
            ProfileServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<ProfileCreateResponse> =
            jsonHandler<ProfileCreateResponse>(clientOptions.jsonMapper)

        override fun create(
            params: ProfileCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "profiles")
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val updateHandler: Handler<ProfileUpdateResponse> =
            jsonHandler<ProfileUpdateResponse>(clientOptions.jsonMapper)

        override fun update(
            params: ProfileUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileUpdateResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "profiles", params._pathParam(0))
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<ProfileListResponse> =
            jsonHandler<ProfileListResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ProfileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "profiles")
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getHandler: Handler<ProfileGetResponse> =
            jsonHandler<ProfileGetResponse>(clientOptions.jsonMapper)

        override fun get(
            params: ProfileGetParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProfileGetResponse> {
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "profiles", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val options = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, options)
            return errorHandler.handle(response).parseable {
                response
                    .use { getHandler.handle(it) }
                    .also {
                        if (options.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
