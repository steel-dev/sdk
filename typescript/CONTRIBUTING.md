## Setting up the environment

This repository uses [`yarn@v1`](https://classic.yarnpkg.com/lang/en/docs/install).
Other package managers may work but are not officially supported for development.

To set up the repository, run:

```sh
$ yarn
$ yarn build
```

This will install all the required dependencies and build output files to `dist/`.

## Modifying/Adding code

Every file in this repository is generated, and each release replaces all of them. A change made here directly does not last, so please open an issue instead of a pull request.

## Adding and running examples

All files in the `examples/` directory are not modified by the generator and can be freely edited or added to.

```ts
// add an example to examples/<your-example>.ts

#!/usr/bin/env -S npm run tsn -T
…
```

```sh
$ chmod +x examples/<your-example>.ts
# run the example against your api
$ yarn tsn -T examples/<your-example>.ts
```

## Using the repository from source

To link a local copy of the repository:

```sh
# Clone
$ git clone https://github.com/steel-dev/sdk
$ cd sdk/typescript

# With yarn
$ yarn link
$ cd ../my-package
$ yarn link steel-sdk

# With pnpm
$ pnpm link --global
$ cd ../my-package
$ pnpm link --global steel-sdk
```

## Running tests

Most tests require you to [set up a mock server](https://github.com/dgellow/steady) against the OpenAPI spec to run the tests.

```sh
$ ./scripts/mock
```

```sh
$ yarn run test
```

## Linting and formatting

This repository uses [prettier](https://www.npmjs.com/package/prettier) and
[eslint](https://www.npmjs.com/package/eslint) to format the code in the repository.

To lint:

```sh
$ yarn lint
```

To format and fix all lint issues automatically:

```sh
$ yarn fix
```

## Publishing and releases

Releases are automatic. Each version tag on this repository runs the `Publish` workflow, which builds the package and publishes it to npm.
