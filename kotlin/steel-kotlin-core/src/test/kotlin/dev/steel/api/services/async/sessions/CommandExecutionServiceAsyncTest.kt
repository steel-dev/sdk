@file:Suppress("DEPRECATION")

package dev.steel.api.services.async.sessions

import com.github.tomakehurst.wiremock.client.WireMock.get
import dev.steel.api.TestServerExtension
import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionCancelParams
import dev.steel.api.models.sessions.commandexecutions.CommandExecutionRetrieveParams
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CommandExecutionServiceAsyncTest {

    @Test
    fun retrieve() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions().commandExecutions()
            val response =
                service.retrieve(
                    CommandExecutionRetrieveParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
            response.validate()
        }

    @Test
    fun cancel() =
        runBlocking<Unit> {
            val client =
                SteelOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .steelApiKey("My Steel API Key")
                    .build()
            val service = client.sessions().commandExecutions()
            val response =
                service.cancel(
                    CommandExecutionCancelParams.builder()
                        .sessionId("sessionId")
                        .executionId("executionId")
                        .build()
                )
            response.validate()
        }
}
