# Steel Rust API Library

The Steel Rust library provides access to the [Steel REST API](https://docs.steel.dev) from Rust.

## Installation

```toml
[dependencies]
steel-rs = "0.21.0"
```

## Usage

`Client::new()` reads the API key from `STEEL_API_KEY` and the base URL from `STEEL_BASE_URL`. Runnable examples are in [examples](examples).

```rust,no_run
#[tokio::main]
async fn main() -> Result<(), steel_rs::Error> {
    let client = steel_rs::Client::new();
    let session = client.sessions().create(steel_rs::SessionCreateParams { timeout: Some(20000), use_proxy: Some(serde_json::json!(true)), ..Default::default() }).await?;
    println!("{}", session.id);
    Ok(())
}
```

## Pagination

List methods return a `Page`. `next_page()` fetches the next one, and the `_auto_paging` methods return a `Stream` over every item.

```rust,no_run
use futures_util::StreamExt;

#[tokio::main]
async fn main() -> Result<(), steel_rs::Error> {
    let client = steel_rs::Client::new();
    let mut items = std::pin::pin!(client.sessions().list_auto_paging(steel_rs::SessionListParams { status: Some(steel_rs::SessionListParamsStatus::Live), ..Default::default() }));
    while let Some(item) = items.next().await {
        println!("{:?}", item?);
    }
    Ok(())
}
```

## Errors

A response with a status outside 2xx returns `Error::Api` with the status, the body and the headers. `Error::status()` gives the status of any error that has one.

## Retries and timeouts

The client retries connection errors, timeouts, 408, 409, 429 and 5xx responses twice, with exponential backoff, and honors `retry-after`. Each attempt times out after 60 seconds.

```rust,no_run
use std::time::Duration;

let client = steel_rs::Client::builder()
    .max_retries(5)
    .timeout(Duration::from_secs(20))
    .build();
```
