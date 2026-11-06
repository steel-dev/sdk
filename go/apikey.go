// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
	"net/http"
	"slices"
	"time"

	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// Create project-scoped API keys using an organization-scoped API key.
//
// APIKeyService contains methods and other services that help with interacting
// with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewAPIKeyService] method instead.
type APIKeyService struct {
	Options []option.RequestOption
}

// NewAPIKeyService generates a new service that applies the given options to
// each request. These options are applied after the parent client's options (if
// there is one), and before any request-specific options.
func NewAPIKeyService(opts ...option.RequestOption) (r *APIKeyService) {
	r = &APIKeyService{}
	r.Options = opts
	return
}

// Create a project-scoped API key using a global (organization-scoped) API key.
// Supply projectId for an active project in the authenticated organization;
// scopeType must be project or omitted. Project-scoped API keys cannot create
// keys, and global keys cannot create other global keys. Dashboard users may
// also create project keys, or global keys with organization admin access.
// Returns the full secret once with Cache-Control: no-store; save it securely.
// The new key is independent: revoking the creating global key does not revoke
// keys it created. Missing projectId returns 400; insufficient scope returns
// 403; an unavailable or foreign project returns 404.
func (r *APIKeyService) New(ctx context.Context, body APIKeyNewParams, opts ...option.RequestOption) (res *APIKey, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/api-keys"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type APIKeyNewParams struct {
	// A human-readable label for the new key. Defaults to Default.
	Name param.Field[string] `json:"name"`
	// The active project to bind the new key to, belonging to the authenticated
	// organization. Required when authenticating with a global API key. Dashboard
	// users may omit it to use their default project.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// Defaults to `project`. Global API keys may only create project-scoped keys.
	// Only organization admins using dashboard authentication may create a `global`
	// key; projectId is ignored for those keys.
	ScopeType param.Field[APIKeyNewParamsScopeType] `json:"scopeType"`
}

func (r APIKeyNewParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Defaults to `project`. Global API keys may only create project-scoped keys.
// Only organization admins using dashboard authentication may create a `global`
// key; projectId is ignored for those keys.
type APIKeyNewParamsScopeType string

const (
	APIKeyNewParamsScopeTypeProject APIKeyNewParamsScopeType = "project"
	APIKeyNewParamsScopeTypeGlobal  APIKeyNewParamsScopeType = "global"
)

func (r APIKeyNewParamsScopeType) IsKnown() bool {
	switch r {
	case APIKeyNewParamsScopeTypeProject, APIKeyNewParamsScopeTypeGlobal:
		return true
	}
	return false
}

type APIKey struct {
	ID        string    `json:"id" api:"required" format:"uuid"`
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// The full secret on creation or rotation only. Metadata reads return a masked
	// preview. Store a newly created secret securely; it cannot be retrieved later.
	Key string `json:"key" api:"required"`
	// The pinned project, or null for a global key, which binds to no project.
	ProjectID string `json:"projectId" api:"required,nullable" format:"uuid"`
	// `project` pins the key to one project. `global` authorizes access across the
	// organization and has no project binding.
	ScopeType APIKeyScopeType `json:"scopeType" api:"required"`
	UpdatedAt time.Time       `json:"updatedAt" api:"required" format:"date-time"`
	Name      string          `json:"name"`
	JSON      apiKeyJSON      `json:"-"`
}

// apiKeyJSON contains the JSON metadata for the struct [APIKey]
type apiKeyJSON struct {
	ID          apijson.Field
	CreatedAt   apijson.Field
	Key         apijson.Field
	ProjectID   apijson.Field
	ScopeType   apijson.Field
	UpdatedAt   apijson.Field
	Name        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *APIKey) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r apiKeyJSON) RawJSON() string {
	return r.raw
}

// `project` pins the key to one project. `global` authorizes access across the
// organization and has no project binding.
type APIKeyScopeType string

const (
	APIKeyScopeTypeProject APIKeyScopeType = "project"
	APIKeyScopeTypeGlobal  APIKeyScopeType = "global"
)

func (r APIKeyScopeType) IsKnown() bool {
	switch r {
	case APIKeyScopeTypeProject, APIKeyScopeTypeGlobal:
		return true
	}
	return false
}
