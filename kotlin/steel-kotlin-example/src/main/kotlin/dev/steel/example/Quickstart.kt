package dev.steel.example

import dev.steel.api.client.okhttp.SteelOkHttpClient
import dev.steel.api.models.sessions.SessionCreateParams

fun main() {
    val client = SteelOkHttpClient.fromEnv()

    val params = SessionCreateParams.builder().inactivityTimeout(3 * 60 * 1000L).build()
    val session = client.sessions().create(params)
    try {
        println("Watch the session live at ${session.sessionViewerUrl()}")
        println(
            "Connect Playwright or Puppeteer to ${session.websocketUrl()}&apiKey=<your API key>"
        )
    } finally {
        client.sessions().release(session.id())
    }
}
