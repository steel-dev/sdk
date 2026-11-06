#![doc = include_str!("../README.md")]
#![allow(clippy::large_enum_variant)]

#[allow(dead_code)]
mod client;
#[allow(dead_code)]
mod encoding;
mod error;
pub mod resources;
mod upload;

pub use client::{Client, ClientBuilder, Page};
pub use error::Error;
pub use resources::*;
pub use upload::Upload;

pub const VERSION: &str = "0.21.0";
pub const DEFAULT_BASE_URL: &str = "https://api.steel.dev";
pub(crate) const CLIENT: &str = "Steel";
pub(crate) const API_KEY_HEADER: &str = "steel-api-key";
pub(crate) const API_KEY_ENV: &str = "STEEL_API_KEY";
pub(crate) const BASE_URL_ENV: &str = "STEEL_BASE_URL";
pub(crate) const CUSTOM_HEADERS_ENV: &str = "STEEL_CUSTOM_HEADERS";
