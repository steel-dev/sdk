<p align="center">
  <a href="https://steel.dev">
    <img src=".github/steel-logo.svg" alt="Steel" width="72">
  </a>
</p>

<h3 align="center">Steel SDKs</h3>

<p align="center">
  <b>Official TypeScript, Python, Go and Rust clients for the Steel browser API, with Java and Kotlin in beta.</b><br />
  Start cloud browsers for your AI agents and automations, and drive them with Playwright, Puppeteer or Selenium.
</p>

<div align="center">

[![npm](https://img.shields.io/npm/v/steel-sdk?label=npm&color=yellow)](https://www.npmjs.com/package/steel-sdk)
[![PyPI](https://img.shields.io/pypi/v/steel-sdk?label=pypi&color=yellow)](https://pypi.org/project/steel-sdk/)
[![crates.io](https://img.shields.io/crates/v/steel-rs?label=crates.io&color=yellow)](https://crates.io/crates/steel-rs)
[![Go Reference](https://pkg.go.dev/badge/github.com/steel-dev/sdk/go.svg)](https://pkg.go.dev/github.com/steel-dev/sdk/go)
[![Discord](https://img.shields.io/discord/1285696350117167226?label=discord)](https://discord.gg/steel-dev)
[![Twitter Follow](https://img.shields.io/twitter/follow/steeldotdev)](https://twitter.com/steeldotdev)

</div>

<h4 align="center">
  <a href="https://app.steel.dev/sign-up">Get an API key</a> ·
  <a href="https://docs.steel.dev">Documentation</a> ·
  <a href="https://docs.steel.dev/api-reference">API reference</a> ·
  <a href="https://github.com/steel-dev/steel-cookbook">Cookbook</a> ·
  <a href="https://discord.gg/steel-dev">Discord</a>
</h4>

[Steel](https://steel.dev) runs browsers in the cloud for AI agents and automation. Every session is a real Chrome with proxies, captcha solving, persistent profiles, file transfer and a live viewer built in. These SDKs start and manage sessions, and you drive the browser with the tools you already use.

## Quickstart

Get an API key from the [Steel dashboard](https://app.steel.dev) and export it.

```sh
export STEEL_API_KEY=...
```

### TypeScript

```sh
npm install steel-sdk playwright-core
```

```ts
import { chromium } from 'playwright-core';
import Steel from 'steel-sdk';

const client = new Steel();

async function main() {
  const session = await client.sessions.create();
  console.log(`Watch the session live at ${session.sessionViewerUrl}`);

  try {
    const browser = await chromium.connectOverCDP(`${session.websocketUrl}&apiKey=${client.steelAPIKey}`);
    const context = browser.contexts()[0] ?? (await browser.newContext());
    const page = context.pages()[0] ?? (await context.newPage());

    await page.goto('https://news.ycombinator.com');
    const titles = await page.locator('.titleline > a').allTextContents();
    console.log(titles.slice(0, 5).join('\n'));

    await browser.close();
  } finally {
    await client.sessions.release(session.id);
  }
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
```

### Python

```sh
pip install steel-sdk playwright
```

```python
from playwright.sync_api import sync_playwright

from steel import Steel

client = Steel()


def main() -> None:
    session = client.sessions.create()
    print(f"Watch the session live at {session.session_viewer_url}")

    try:
        with sync_playwright() as playwright:
            browser = playwright.chromium.connect_over_cdp(f"{session.websocket_url}&apiKey={client.steel_api_key}")
            context = browser.contexts[0] if browser.contexts else browser.new_context()
            page = context.pages[0] if context.pages else context.new_page()

            page.goto("https://news.ycombinator.com")
            for title in page.locator(".titleline > a").all_text_contents()[:5]:
                print(title)

            browser.close()
    finally:
        client.sessions.release(session.id)


if __name__ == "__main__":
    main()
```

<details>
<summary><b>Go</b></summary>

```sh
go get github.com/steel-dev/sdk/go
```

```go
package main

import (
	"context"
	"fmt"
	"log"

	"github.com/steel-dev/sdk/go"
)

func main() {
	ctx := context.Background()
	client := steel.NewClient()

	session, err := client.Sessions.New(ctx, steel.SessionNewParams{})
	if err != nil {
		log.Fatal(err)
	}
	defer func() {
		if _, err := client.Sessions.Release(ctx, session.ID); err != nil {
			log.Print(err)
		}
	}()

	fmt.Println("Watch the session live at", session.SessionViewerURL)
	fmt.Println("Connect Playwright or Puppeteer to", session.WebsocketURL+"&apiKey=<your API key>")
}
```

</details>

<details>
<summary><b>Rust</b></summary>

```sh
cargo add steel-rs
cargo add tokio --features macros,rt-multi-thread
```

```rust
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = steel_rs::Client::new();

    let session = client
        .sessions()
        .create(steel_rs::SessionCreateParams::default())
        .await?;
    println!("Watch the session live at {}", session.session_viewer_url);
    println!(
        "Connect Playwright or Puppeteer to {}&apiKey=<your API key>",
        session.websocket_url
    );

    client.sessions().release(&session.id).await?;
    Ok(())
}
```

</details>

<details>
<summary><b>Java (beta)</b></summary>

The Java SDK is not on Maven Central yet. [java/README.md](java/README.md#installation) shows how to install it locally.

```java
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
```

</details>

<details>
<summary><b>Kotlin (beta)</b></summary>

The Kotlin SDK is not on Maven Central yet. [kotlin/README.md](kotlin/README.md#installation) shows how to install it locally.

```kotlin
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
```

The async client has the same methods as suspend functions, and its pages stream as a `Flow`. [Coroutines.kt](kotlin/steel-kotlin-example/src/main/kotlin/dev/steel/example/Coroutines.kt) shows both.

</details>

A session runs until you release it or it times out, so release it when you are done, as the examples do in `finally`.

## Packages

| Language | Package | Install | Reference |
|---|---|---|---|
| TypeScript and JavaScript | [`steel-sdk`](https://www.npmjs.com/package/steel-sdk) | `npm install steel-sdk` | [README](typescript/README.md) · [API](typescript/api.md) |
| Python | [`steel-sdk`](https://pypi.org/project/steel-sdk/) | `pip install steel-sdk` | [README](python/README.md) · [API](python/api.md) |
| Go | [`github.com/steel-dev/sdk/go`](https://pkg.go.dev/github.com/steel-dev/sdk/go) | `go get github.com/steel-dev/sdk/go` | [README](go/README.md) · [API](go/api.md) |
| Rust | [`steel-rs`](https://crates.io/crates/steel-rs) | `cargo add steel-rs` | [README](rust/README.md) · [docs.rs](https://docs.rs/steel-rs) |
| Java (beta) | `dev.steel:steel-java` | [Install locally](java/README.md#installation) | [README](java/README.md) |
| Kotlin (beta) | `dev.steel:steel-kotlin` | [Install locally](kotlin/README.md#installation) | [README](kotlin/README.md) |

## What you can do

| | TypeScript | Python |
|---|---|---|
| Start and release a browser | `client.sessions.create()` · `client.sessions.release(id)` | `client.sessions.create()` · `client.sessions.release(id)` |
| Use a residential proxy, solve captchas, block ads | `sessions.create({ useProxy: true, solveCaptcha: true, blockAds: true })` | `sessions.create(use_proxy=True, solve_captcha=True, block_ads=True)` |
| Turn a page into Markdown, a screenshot or a PDF, without a session | `client.scrape()` · `client.screenshot()` · `client.pdf()` | `client.scrape()` · `client.screenshot()` · `client.pdf()` |
| Keep cookies and logins between sessions | `sessions.create({ persistProfile: true })`, then `{ profileId }` | `sessions.create(persist_profile=True)`, then `profile_id=` |
| Move files in and out of a browser | `client.sessions.files.upload()` · `.download()` · `.list()` | `client.sessions.files.upload()` · `.download()` · `.list()` |
| Sign in with stored credentials | `client.credentials.create()`, then `sessions.create({ credentials: {} })` | `client.credentials.create()`, then `sessions.create(credentials={})` |
| Load your own Chrome extensions | `client.extensions.upload()`, then `sessions.create({ extensionIds })` | `client.extensions.upload()`, then `sessions.create(extension_ids=...)` |
| Watch a session live or replay it | `session.sessionViewerUrl` · `client.sessions.hls(id)` | `session.session_viewer_url` · `client.sessions.hls(id)` |
| See what a session did and what it cost | `client.sessions.logs(id)` · `client.sessions.cost(id)` | `client.sessions.logs(id)` · `client.sessions.cost(id)` |

The Go, Rust, Java and Kotlin SDKs have the same methods under their own names, such as `client.Sessions.New` in Go and `client.sessions().create` in Rust, Java and Kotlin.

## Cookbook

Every SDK comes with examples, and CI compiles all of them for each release.

| Recipe | TypeScript | Python | Go | Rust | Java | Kotlin |
|---|---|---|---|---|---|---|
| Drive a session with Playwright | [ts](typescript/examples/drive-with-playwright.ts) | [sync](python/examples/drive_with_playwright.py) · [async](python/examples/drive_with_playwright_async.py) | | | | |
| Drive a session with Puppeteer | [ts](typescript/examples/drive-with-puppeteer.ts) | | | | | |
| Start a session for any CDP client | | | [go](go/examples/quickstart/main.go) | [rs](rust/examples/quickstart.rs) | [java](java/steel-java-example/src/main/java/dev/steel/example/Quickstart.java) | [kt](kotlin/steel-kotlin-example/src/main/kotlin/dev/steel/example/Quickstart.kt) |
| Scrape, screenshot and PDF without a session | [ts](typescript/examples/quick-actions.ts) | [py](python/examples/quick_actions.py) | [go](go/examples/quick-actions/main.go) | [rs](rust/examples/quick_actions.rs) | [java](java/steel-java-example/src/main/java/dev/steel/example/QuickActions.java) | [kt](kotlin/steel-kotlin-example/src/main/kotlin/dev/steel/example/QuickActions.kt) |
| Keep logins between sessions with profiles | [ts](typescript/examples/keep-logins-with-profiles.ts) | [py](python/examples/keep_logins_with_profiles.py) | | | | |
| Upload and download files | [ts](typescript/examples/move-files.ts) | [py](python/examples/move_files.py) | | | | |
| Proxies, captcha solving and other session options | [ts](typescript/examples/session-options.ts) | [py](python/examples/session_options.py) | | | | |
| Read the logs and the cost of a session | [ts](typescript/examples/logs-and-cost.ts) | [py](python/examples/logs_and_cost.py) | | | | |
| List live sessions | [ts](typescript/examples/list-sessions.ts) | [py](python/examples/list_sessions.py) | [go](go/examples/list-sessions/main.go) | [rs](rust/examples/list_sessions.rs) | | [kt, async](kotlin/steel-kotlin-example/src/main/kotlin/dev/steel/example/Coroutines.kt) |

Run an example from its SDK's directory with `STEEL_API_KEY` set.

| Language | Command |
|---|---|
| TypeScript | `yarn && yarn tsn examples/quick-actions.ts` |
| Python | `rye sync && rye run python examples/quick_actions.py` |
| Go | `go run ./examples/quick-actions` |
| Rust | `cargo run --example quick_actions` |
| Java | `./gradlew :steel-java-example:run -Pexample=QuickActions` |
| Kotlin | `./gradlew :steel-kotlin-example:run -Pexample=QuickActions` |

For complete projects, such as agents built on popular frameworks, see [steel-cookbook](https://github.com/steel-dev/steel-cookbook).

## Versioning

All six SDKs share one version and are released together. [CHANGELOG.md](CHANGELOG.md) lists what each release changed, and [Releases](https://github.com/steel-dev/sdk/releases) show each one with its notes. Before 1.0, a minor release can include breaking changes, and the changelog calls them out. The Java and Kotlin SDKs are in beta, so any release can change their API.

## Contributing

The code in this repository is generated from the Steel API's OpenAPI spec, [openapi.json](openapi.json), and every release regenerates all of it. A pull request can't be merged as it is, so please [open an issue](https://github.com/steel-dev/sdk/issues) for a bug, a missing feature or a recipe you would like to see. We fix it at the source and ship it in the next release. [CONTRIBUTING.md](CONTRIBUTING.md) has the details.

Questions are welcome on [Discord](https://discord.gg/steel-dev).

## Security

Please report security issues privately to security@steel.dev, as [SECURITY.md](SECURITY.md) describes.

## License

[Apache 2.0](LICENSE)
