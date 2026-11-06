# Steel Kotlin API Library

The Steel Kotlin SDK gives Kotlin applications typed access to the Steel REST API, with nullable types and coroutines. It is generated from the API's OpenAPI spec. Java applications can use the [Java SDK](../java).

> **Beta.** Every release generates and tests this SDK, but any release can change its API, and support goes to the TypeScript, Python, Go and Rust SDKs first.

The REST API documentation lives at [docs.steel.dev](https://docs.steel.dev).

## Installation

The SDK is not on Maven Central yet. Install it into your local Maven repository from this directory:

```sh
./gradlew publishToMavenLocal
```

### Gradle

```kotlin
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("dev.steel:steel-kotlin:0.21.0")
}
```

### Maven

Maven reads the local repository by default.

```xml
<dependency>
  <groupId>dev.steel</groupId>
  <artifactId>steel-kotlin</artifactId>
  <version>0.21.0</version>
</dependency>
```

## Requirements

Kotlin 1.9 or later on Java 8 or later. The async client uses [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines).

## Jackson

The SDK reads and writes JSON with [Jackson](https://github.com/FasterXML/jackson). It needs Jackson 2.13.4 or a later 2.x release, except 2.18.1, which has [a bug](https://github.com/FasterXML/jackson-databind/issues/4639) that breaks the SDK. The client checks the Jackson versions on the classpath when it starts and throws an error that names any version it cannot use. That happens when your build, or a library it depends on, pulls in another Jackson version.

## Usage

```kotlin
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.sessions.SessionCreateParams

val client = SteelOkHttpClient.fromEnv()

val params = SessionCreateParams.builder()
    .useProxy(true)
    .timeout(20000L)
    .build()
val session = client.sessions().create(params)
```

Runnable examples are in [steel-kotlin-example](steel-kotlin-example).

## Client configuration

`fromEnv()` reads the API key from the `STEEL_API_KEY` environment variable or the `steel.apiKey` system property, and the base URL from `STEEL_BASE_URL` or `steel.baseUrl`. The builder sets them directly:

```kotlin
val client = SteelOkHttpClient.builder()
    .steelApiKey("My Steel API Key")
    .build()
```

`withOptions` returns a copy of the client with some options changed. The original client is not modified:

```kotlin
val clientWithOptions = client.withOptions { it.maxRetries(5) }
```

## Requests and responses

Each method takes a params object, built with `builder()`, and returns an immutable response object. Fields that may be absent come back as nullable types. Call `validate()` on a response to check every field against the expected types, or set `responseValidation(true)` on the client builder to validate every response.

Params builders take `putAdditionalHeader` and `putAdditionalQueryParam`, and those with a body also take `putAdditionalBodyProperty`, for values the SDK does not know yet. Response objects return unknown fields from `_additionalProperties()`.

## Coroutines

`client.async()` returns a client with the same methods as suspend functions. Call them from a coroutine:

```kotlin
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.sessions.SessionCreateParams

val client = SteelOkHttpClient.fromEnv()

val params = SessionCreateParams.builder()
    .useProxy(true)
    .timeout(20000L)
    .build()
val session = client.async().sessions().create(params)
```

`SteelOkHttpClientAsync.fromEnv()` creates the async client directly.

## Pagination

List methods return a page. `autoPager()` goes through every item and fetches more pages as needed:

```kotlin
import dev.steel.api.models.sessions.SessionListParams

val params = SessionListParams.builder()
    .status(SessionListParams.Status.LIVE)
    .build()
val page = client.sessions().list(params)
for (session in page.autoPager()) {
    println(session)
}
```

To work page by page, use `hasNextPage()` and `nextPage()`:

```kotlin
var page = client.sessions().list(params)
while (true) {
    for (session in page.items()) {
        println(session)
    }
    if (!page.hasNextPage()) {
        break
    }
    page = page.nextPage()
}
```

On the async client, `autoPager()` returns a `Flow`, so `page.autoPager().collect { println(it) }` streams the items.

## Raw responses

`withRawResponse()` returns a view of the client whose methods return the HTTP response with its status code and headers. Call `parse()` to read the body:

```kotlin
import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.sessions.SessionCreateParams

val client = SteelOkHttpClient.fromEnv()

val params = SessionCreateParams.builder()
    .useProxy(true)
    .timeout(20000L)
    .build()
val response = client.withRawResponse().sessions().create(params)
val statusCode = response.statusCode()
val headers = response.headers()
val parsed = response.parse()
```

## Error handling

The SDK throws unchecked exceptions:

| Status | Exception |
| ------ | --------- |
| 400 | `BadRequestException` |
| 401 | `UnauthorizedException` |
| 403 | `PermissionDeniedException` |
| 404 | `NotFoundException` |
| 422 | `UnprocessableEntityException` |
| 429 | `RateLimitException` |
| 5xx | `InternalServerException` |
| Other | `UnexpectedStatusCodeException` |

They all extend `SteelServiceException`. Network failures throw `SteelIoException`, and response data with the wrong types throws `SteelInvalidDataException`. Every exception from the SDK extends `SteelException`.

## Retries and timeouts

The client retries connection errors and 408, 409, 429 and 5xx responses twice by default, with exponential backoff. A request times out after one minute by default:

```kotlin
val client = SteelOkHttpClient.builder()
    .fromEnv()
    .maxRetries(4)
    .timeout(Duration.ofSeconds(30))
    .build()
```

## Logging

Set the `STEEL_LOG` environment variable to `info` or `debug` to log requests and responses.

## Versioning

This SDK shares its version with the other Steel SDKs. While it is in beta, any release can change its API.
