package dev.steel.example

import dev.steel.api.client.okhttp.SteelOkHttpClientAsync
import dev.steel.api.models.sessions.SessionListParams
import kotlinx.coroutines.runBlocking

fun main() =
    runBlocking<Unit> {
        val client = SteelOkHttpClientAsync.fromEnv()

        val session = client.sessions().create()
        try {
            println("Started ${session.id()}")
            client
                .sessions()
                .list(SessionListParams.builder().status(SessionListParams.Status.LIVE).build())
                .autoPager()
                .collect { println("Live: ${it.id()} since ${it.createdAt()}") }
        } finally {
            client.sessions().release(session.id())
            client.close()
        }
    }
