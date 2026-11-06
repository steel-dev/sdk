#![allow(dead_code)]

use steel_rs::Client;

pub fn client() -> Client {
    let base_url =
        std::env::var("TEST_API_BASE_URL").unwrap_or_else(|_| "http://127.0.0.1:4010".to_owned());
    Client::builder()
        .base_url(base_url)
        .api_key("My Steel API Key")
        .max_retries(0)
        .build()
}

pub fn client_for(base_url: &str) -> Client {
    Client::builder()
        .base_url(base_url)
        .api_key("My Steel API Key")
        .max_retries(0)
        .build()
}
