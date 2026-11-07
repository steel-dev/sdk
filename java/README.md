# Steel Java API Library

The Steel Java SDK gives Java applications typed access to the Steel REST API. It is generated from the API's OpenAPI spec. Kotlin applications can use the [Kotlin SDK](../kotlin), with nullable types and coroutines.

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
    implementation("dev.steel:steel-java:0.22.0")
}
```

### Maven

Maven reads the local repository by default.

```xml
<dependency>
  <groupId>dev.steel</groupId>
  <artifactId>steel-java</artifactId>
  <version>0.22.0</version>
</dependency>
```

## Requirements

Java 8 or later.

## Jackson

The SDK reads and writes JSON with [Jackson](https://github.com/FasterXML/jackson). It needs Jackson 2.13.4 or a later 2.x release, except 2.18.1, which has [a bug](https://github.com/FasterXML/jackson-databind/issues/4639) that breaks the SDK. The client checks the Jackson versions on the classpath when it starts and throws an error that names any version it cannot use. That happens when your build, or a library it depends on, pulls in another Jackson version.

## Usage

```java
import dev.steel.api.client.SteelClient;
import dev.steel.api.client.okhttp.SteelOkHttpClient;
import dev.steel.api.models.sessions.Session;
import dev.steel.api.models.sessions.SessionCreateParams;

SteelClient client = SteelOkHttpClient.fromEnv();

SessionCreateParams params = SessionCreateParams.builder()
    .useProxy(true)
    .timeout(20000L)
    .build();
Session session = client.sessions().create(params);
```

Runnable examples are in [steel-java-example](steel-java-example).

## Client configuration

`fromEnv()` reads the API key from the `STEEL_API_KEY` environment variable or the `steel.apiKey` system property, and the base URL from `STEEL_BASE_URL` or `steel.baseUrl`. The builder sets them directly:

```java
SteelClient client = SteelOkHttpClient.builder()
    .steelApiKey("My Steel API Key")
    .build();
```

`withOptions` returns a copy of the client with some options changed. The original client is not modified:

```java
SteelClient clientWithOptions = client.withOptions(optionsBuilder -> optionsBuilder.maxRetries(5));
```

## Requests and responses

Each method takes a params object, built with `builder()`, and returns an immutable response object. Fields that may be absent come back as `Optional`. Call `validate()` on a response to check every field against the expected types, or set `responseValidation(true)` on the client builder to validate every response.

Params builders take `putAdditionalHeader` and `putAdditionalQueryParam`, and those with a body also take `putAdditionalBodyProperty`, for values the SDK does not know yet. Response objects return unknown fields from `_additionalProperties()`.

## Asynchronous execution

`client.async()` returns a client with the same methods, which return a `CompletableFuture`:

```java
import dev.steel.api.client.SteelClient;
import dev.steel.api.client.okhttp.SteelOkHttpClient;
import dev.steel.api.models.sessions.Session;
import dev.steel.api.models.sessions.SessionCreateParams;
import java.util.concurrent.CompletableFuture;

SteelClient client = SteelOkHttpClient.fromEnv();

SessionCreateParams params = SessionCreateParams.builder()
    .useProxy(true)
    .timeout(20000L)
    .build();
CompletableFuture<Session> session = client.async().sessions().create(params);
```

## Pagination

List methods return a page. `autoPager()` goes through every item and fetches more pages as needed:

```java
import dev.steel.api.models.sessions.SessionListPage;
import dev.steel.api.models.sessions.SessionListParams;
import dev.steel.api.models.sessions.Sessionslist;

SessionListParams params = SessionListParams.builder()
    .status(SessionListParams.Status.LIVE)
    .build();
SessionListPage page = client.sessions().list(params);
for (Sessionslist.Session session : page.autoPager()) {
    System.out.println(session);
}
```

To work page by page, use `hasNextPage()` and `nextPage()`:

```java
SessionListPage page = client.sessions().list(params);
while (true) {
    for (Sessionslist.Session session : page.items()) {
        System.out.println(session);
    }
    if (!page.hasNextPage()) {
        break;
    }
    page = page.nextPage();
}
```

On the async client, `autoPager().subscribe(item -> ...)` streams the items.

## Raw responses

`withRawResponse()` returns a view of the client whose methods return the HTTP response with its status code and headers. Call `parse()` to read the body:

```java
import dev.steel.api.client.SteelClient;
import dev.steel.api.client.okhttp.SteelOkHttpClient;
import dev.steel.api.core.http.Headers;
import dev.steel.api.core.http.HttpResponseFor;
import dev.steel.api.models.sessions.Session;
import dev.steel.api.models.sessions.SessionCreateParams;

SteelClient client = SteelOkHttpClient.fromEnv();

SessionCreateParams params = SessionCreateParams.builder()
    .useProxy(true)
    .timeout(20000L)
    .build();
HttpResponseFor<Session> response = client.withRawResponse().sessions().create(params);
int statusCode = response.statusCode();
Headers headers = response.headers();
Session parsed = response.parse();
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

```java
SteelClient client = SteelOkHttpClient.builder()
    .fromEnv()
    .maxRetries(4)
    .timeout(Duration.ofSeconds(30))
    .build();
```

## Logging

Set the `STEEL_LOG` environment variable to `info` or `debug` to log requests and responses.

## Versioning

This SDK shares its version with the other Steel SDKs. While it is in beta, any release can change its API.
