// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
	"net/http"
	"os"
	"slices"
	"strings"

	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// Client creates a struct with services and top level methods that help with
// interacting with the steel API. You should not instantiate this client
// directly, and instead use the [NewClient] method instead.
type Client struct {
	Options []option.RequestOption
	// Create project-scoped API keys using an organization-scoped API key.
	APIKeys     *APIKeyService
	Credentials *CredentialService
	Extensions  *ExtensionService
	Files       *FileService
	Profiles    *ProfileService
	Projects    *ProjectService
	Sessions    *SessionService
}

// DefaultClientOptions read from the environment (STEEL_API_KEY,
// STEEL_BASE_URL). This should be used to initialize new clients.
func DefaultClientOptions() []option.RequestOption {
	defaults := []option.RequestOption{option.WithHTTPClient(defaultHTTPClient()), option.WithEnvironmentProduction(), requestconfig.WithIdempotencyHeader("Idempotency-Key")}
	if o, ok := os.LookupEnv("STEEL_BASE_URL"); ok {
		defaults = append(defaults, option.WithBaseURL(o))
	}
	if o, ok := os.LookupEnv("STEEL_API_KEY"); ok {
		defaults = append(defaults, option.WithSteelAPIKey(o))
	}
	if o, ok := os.LookupEnv("STEEL_CUSTOM_HEADERS"); ok {
		for _, line := range strings.Split(o, "\n") {
			colon := strings.Index(line, ":")
			if colon >= 0 {
				defaults = append(defaults, option.WithHeader(strings.TrimSpace(line[:colon]), strings.TrimSpace(line[colon+1:])))
			}
		}
	}
	return defaults
}

// NewClient generates a new client with the default option read from the
// environment (STEEL_API_KEY, STEEL_BASE_URL). The option passed in as
// arguments are applied after these default arguments, and all option will be
// passed down to the services and requests that this client makes.
func NewClient(opts ...option.RequestOption) (r *Client) {
	opts = append(DefaultClientOptions(), opts...)

	r = &Client{Options: opts}

	r.APIKeys = NewAPIKeyService(opts...)
	r.Credentials = NewCredentialService(opts...)
	r.Extensions = NewExtensionService(opts...)
	r.Files = NewFileService(opts...)
	r.Profiles = NewProfileService(opts...)
	r.Projects = NewProjectService(opts...)
	r.Sessions = NewSessionService(opts...)

	return
}

// Execute makes a request with the given context, method, URL, request params,
// response, and request options. This is useful for hitting undocumented
// endpoints while retaining the base URL, auth, retries, and other options from
// the client.
func (r *Client) Execute(ctx context.Context, method string, path string, params interface{}, res interface{}, opts ...option.RequestOption) error {
	opts = slices.Concat(r.Options, opts)
	return requestconfig.ExecuteNewRequest(ctx, method, path, params, res, opts...)
}

// Get makes a GET request with the given URL, params, and optionally
// deserializes to a response. See [Execute] documentation on the params and
// response.
func (r *Client) Get(ctx context.Context, path string, params interface{}, res interface{}, opts ...option.RequestOption) error {
	return r.Execute(ctx, http.MethodGet, path, params, res, opts...)
}

// Post makes a POST request with the given URL, params, and optionally
// deserializes to a response. See [Execute] documentation on the params and
// response.
func (r *Client) Post(ctx context.Context, path string, params interface{}, res interface{}, opts ...option.RequestOption) error {
	return r.Execute(ctx, http.MethodPost, path, params, res, opts...)
}

// Put makes a PUT request with the given URL, params, and optionally
// deserializes to a response. See [Execute] documentation on the params and
// response.
func (r *Client) Put(ctx context.Context, path string, params interface{}, res interface{}, opts ...option.RequestOption) error {
	return r.Execute(ctx, http.MethodPut, path, params, res, opts...)
}

// Patch makes a PATCH request with the given URL, params, and optionally
// deserializes to a response. See [Execute] documentation on the params and
// response.
func (r *Client) Patch(ctx context.Context, path string, params interface{}, res interface{}, opts ...option.RequestOption) error {
	return r.Execute(ctx, http.MethodPatch, path, params, res, opts...)
}

// Delete makes a DELETE request with the given URL, params, and optionally
// deserializes to a response. See [Execute] documentation on the params and
// response.
func (r *Client) Delete(ctx context.Context, path string, params interface{}, res interface{}, opts ...option.RequestOption) error {
	return r.Execute(ctx, http.MethodDelete, path, params, res, opts...)
}
