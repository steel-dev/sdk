# Contributing

Thanks for helping improve the Steel SDKs.

## The code is generated

Every file in this repository is generated from the Steel API's OpenAPI spec, [openapi.json](openapi.json), and every release regenerates all of it. A pull request can't be merged as it is, because the next release would overwrite it.

Please [open an issue](https://github.com/steel-dev/sdk/issues/new/choose) instead:

- **Bug:** the SDK, its version, the smallest code that shows the problem, what you expected and what happened.
- **Missing method or option:** what you want to do and why the SDK makes it hard today.
- **Example or doc:** the recipe you would like to see, in which language.

We fix it at the source and ship the fix in the next release. [CHANGELOG.md](CHANGELOG.md) lists what each release changed.

## Running an SDK locally

Each SDK has its own guide to setting it up, building it and running its tests:

- [TypeScript](typescript/CONTRIBUTING.md)
- [Python](python/CONTRIBUTING.md)
- [Go](go/CONTRIBUTING.md)
- [Rust](rust/README.md)
- [Java](java/README.md) and [Kotlin](kotlin/README.md), both in beta

The [README](README.md#cookbook) shows how to run the examples.

## Questions

Ask on [Discord](https://discord.gg/steel-dev), or read the [documentation](https://docs.steel.dev).
