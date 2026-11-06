package dev.steel.example

import dev.steel.api.client.okhttp.SteelOkHttpClient

fun main() {
    val client = SteelOkHttpClient.fromEnv()

    val session = client.sessions().create()
    try {
        println("Watch the session live at ${session.sessionViewerUrl()}")
        println(
            "Connect Playwright or Puppeteer to ${session.websocketUrl()}&apiKey=<your API key>"
        )
    } finally {
        client.sessions().release(session.id())
    }
}
