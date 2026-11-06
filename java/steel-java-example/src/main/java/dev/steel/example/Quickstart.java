package dev.steel.example;

import dev.steel.api.client.SteelClient;
import dev.steel.api.client.okhttp.SteelOkHttpClient;
import dev.steel.api.models.sessions.Session;
import dev.steel.api.models.sessions.SessionCreateParams;

public final class Quickstart {
    private Quickstart() {}

    public static void main(String[] args) {
        SteelClient client = SteelOkHttpClient.fromEnv();

        Session session = client.sessions().create(SessionCreateParams.none());
        try {
            System.out.println("Watch the session live at " + session.sessionViewerUrl());
            System.out.println(
                    "Connect Playwright or Puppeteer to " + session.websocketUrl() + "&apiKey=<your API key>");
        } finally {
            client.sessions().release(session.id());
        }
    }
}
