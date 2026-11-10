# Changelog

## 0.23.0 (2026-11-10)

- Breaking: the SDKs switch to the new forms of the commands API below. Browsers still accept the old `status`, `cancel`, `find` and `eventsAfter` forms for now, so older SDKs keep working, and they mark those responses with `x-steel-deprecated`.
  - `status` and `cancel` are no longer commands. Use `sessions.commandExecutions.retrieve` and `sessions.commandExecutions.cancel`.
  - `dialog` is no longer a command, and commands do not answer dialogs. A response still shows an open dialog in `dialog`. Close it in the live view, or close its tab.
  - `find` takes `target`, a CSS selector or a locator, in place of `by`, `value`, `name` and `exact`. `{ by: "role", value: "button", name: "Save" }` becomes `{ target: { role: "button", name: "Save" } }`.
  - `eventsAfter`, `events` and `epoch` are gone. `epochChanged` stays.
- Added: a response has `id` at the top, the same as `execution.id`.
- Changed: each item of `results` names its `command` and has the fields of that command, typed as `CommandResult`.
- Changed: arguments that do not fit together, such as a `wait` with two conditions or `get attr` without `attribute`, are refused with `400` before any command runs.
- Changed: an unexpected engine failure answers `500 internal_error` instead of `200` with `error`. The `x-should-retry` header says whether sending the request again is safe.
- Added: every SDK sends an `Idempotency-Key` header on requests other than `GET` and keeps it across automatic retries. A key you pass with a request replaces it. Rust takes one only on `sessions.commands`, as `idempotency_key`.
- Fixed: Rust follows the `x-should-retry` header, as the other SDKs do.

- Added `client.sessions.command_executions.cancel`.
- Added `client.sessions.command_executions.retrieve`.
- Updated the docs of the response of `client.sessions.commands`.
- Breaking: Removed `client.sessions.commands` body `eventsAfter`.
- Breaking: `client.sessions.commands` body `commands` changed its variants.
- Breaking: `client.sessions.commands` response changed from a union to an object.
- Breaking: Removed model `CommandEvents`.
- Breaking: `Command` changed its variants.
- Breaking: Removed `CommandDialog.accepted`.
- Breaking: Removed `CommandDialog.by`.
- Breaking: Removed `CommandDialog.text`.
- Added model `CommandResult`.
- Updated the generator and the runtimes.

## 0.22.0 (2026-11-07)

- Fixed: Go and Rust can send `null` in `sessions.updateMetadata`, which removes a metadata key. TypeScript and Python already could.
- Changed: Go sends a `nil` map value as `null`. It used to leave the key out. This also applies to `metadata` in `Sessions.New` and `Sessions.ReplaceMetadata`.
- Breaking types: in Rust, `SessionUpdateMetadataParams.metadata` is a `HashMap<String, Option<SessionUpdateMetadataParamsMetadata>>`, where `None` removes the key, and `SessionUpdateMetadataParamsMetadata::String2` is gone. The string lists and maps in the profile fingerprint, such as `fonts` and `audio_codecs`, hold `Option<String>`, because the API can send `null` in them.
- Added: Rust request objects with required fields have a `new` constructor that takes only those fields. Fields with a single allowed value, such as `command`, fill themselves, and objects with no other required field implement `Default`.
- Fixed: the links in the TypeScript and Python READMEs work on npm and PyPI.

- Updated the generator and the runtimes.

## 0.21.0 (2026-11-06)

The SDKs now live in this repository, and all six languages share one version. This release follows 0.18.0 on npm and 0.19.0 on PyPI.

- Added `apiKeys.create`, `projects.list` and `projects.retrieve`, and on sessions `agentTraces`, `commands`, `cost`, `hls`, `logs`, `replaceMetadata`, `signalCatalog` and `updateMetadata`. Python uses the snake_case names, such as `agent_traces`.
- Breaking types: `extensions.download()` returns the zip as binary. TypeScript returns a `Response` instead of a `string`, and Python a `BinaryAPIResponse` instead of a `str`. The old string could not hold the bytes of the zip intact.
- Fixed: TypeScript percent-encodes path parameters, so an ID with `/`, `?` or `#` no longer changes the request.
- Fixed: Python sends `user_data_dir` in `profiles.create` and `profiles.update` as a file.
- New, in beta: a Kotlin SDK in `kotlin/`, with nullable types and suspend functions. The Java and Kotlin SDKs are in beta and not on Maven Central yet.
- Go moves from `github.com/steel-dev/steel-go` to `github.com/steel-dev/sdk/go`. The Rust crate keeps its name, `steel-rs`. The API of both changes with this release.
- Requests carry `X-Steel-Lang`, `X-Steel-Package-Version` and related headers.

