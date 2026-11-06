# Changelog

## 0.21.0 (2026-11-06)

The SDKs now live in this repository, and all six languages share one version. This release follows 0.18.0 on npm and 0.19.0 on PyPI.

- Added `apiKeys.create`, `projects.list` and `projects.retrieve`, and on sessions `agentTraces`, `commands`, `cost`, `hls`, `logs`, `replaceMetadata`, `signalCatalog` and `updateMetadata`. Python uses the snake_case names, such as `agent_traces`.
- Breaking types: `extensions.download()` returns the zip as binary. TypeScript returns a `Response` instead of a `string`, and Python a `BinaryAPIResponse` instead of a `str`. The old string could not hold the bytes of the zip intact.
- Fixed: TypeScript percent-encodes path parameters, so an ID with `/`, `?` or `#` no longer changes the request.
- Fixed: Python sends `user_data_dir` in `profiles.create` and `profiles.update` as a file.
- New, in beta: a Kotlin SDK in `kotlin/`, with nullable types and suspend functions. The Java and Kotlin SDKs are in beta and not on Maven Central yet.
- Go moves from `github.com/steel-dev/steel-go` to `github.com/steel-dev/sdk/go`. The Rust crate keeps its name, `steel-rs`. The API of both changes with this release.
- Requests carry `X-Steel-Lang`, `X-Steel-Package-Version` and related headers.

