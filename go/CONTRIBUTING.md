## Setting up the environment

To set up the repository, run:

```sh
$ ./scripts/bootstrap
$ ./scripts/build
```

This will install all the required dependencies and build the SDK.

You can also [install go 1.22+ manually](https://go.dev/doc/install).

## Modifying/Adding code

Every file in this repository is generated, and each release replaces all of them. A change made here directly does not last, so please open an issue instead of a pull request.

## Adding and running examples

All files in the `examples/` directory are not modified by the generator and can be freely edited or added to.

```go
# add an example to examples/<your-example>/main.go

package main

func main() {
  // ...
}
```

```sh
$ go run ./examples/<your-example>
```

## Using the repository from source

To use a local version of this library from source in another project, edit the `go.mod` with a replace
directive. This can be done through the CLI with the following:

```sh
$ go mod edit -replace github.com/steel-dev/sdk/go=/path/to/sdk
```

## Running tests

Most tests require you to [set up a mock server](https://github.com/dgellow/steady) against the OpenAPI spec to run the tests.

```sh
$ ./scripts/mock
```

```sh
$ ./scripts/test
```

## Formatting

This library uses the standard gofmt code formatter:

```sh
$ ./scripts/format
```
