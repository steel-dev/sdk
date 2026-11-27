// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
	"errors"
	"fmt"
	"net/http"
	"net/url"
	"slices"
	"time"

	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/apiquery"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// SecretConnectionService contains methods and other services that help with
// interacting with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewSecretConnectionService] method instead.
type SecretConnectionService struct {
	Options []option.RequestOption
}

// NewSecretConnectionService generates a new service that applies the given
// options to each request. These options are applied after the parent client's
// options (if there is one), and before any request-specific options.
func NewSecretConnectionService(opts ...option.RequestOption) (r *SecretConnectionService) {
	r = &SecretConnectionService{}
	r.Options = opts
	return
}

// Create a secret connection
func (r *SecretConnectionService) New(ctx context.Context, body SecretConnectionNewParams, opts ...option.RequestOption) (res *SecretConnectionNewResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/secret-connections"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type SecretConnectionNewParams struct {
	// Write-only provider authentication. Supply a service account token for
	// 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
	// HashiCorp AppRole.
	Auth        param.Field[SecretConnectionNewParamsAuth]        `json:"auth" api:"required"`
	Name        param.Field[string]                               `json:"name" api:"required"`
	Provider    param.Field[SecretConnectionNewParamsProvider]    `json:"provider" api:"required"`
	Config      param.Field[SecretConnectionNewParamsConfig]      `json:"config"`
	ProjectID   param.Field[string]                               `json:"projectId" format:"uuid"`
	TokenBudget param.Field[SecretConnectionNewParamsTokenBudget] `json:"tokenBudget"`
}

func (r SecretConnectionNewParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Write-only provider authentication. Supply a service account token for
// 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
// HashiCorp AppRole.
type SecretConnectionNewParamsAuth struct {
	// Write-only; never returned by the API.
	Token param.Field[string] `json:"token"`
	Mount param.Field[string] `json:"mount"`
	// Write-only; never returned by the API.
	RoleID param.Field[string] `json:"roleId"`
	// Write-only; never returned by the API.
	SecretID param.Field[string] `json:"secretId"`
	// Write-only; never returned by the API.
	ServiceAccountToken param.Field[string]                            `json:"serviceAccountToken"`
	Type                param.Field[SecretConnectionNewParamsAuthType] `json:"type"`
}

func (r SecretConnectionNewParamsAuth) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SecretConnectionNewParamsAuthType string

const (
	SecretConnectionNewParamsAuthTypeToken   SecretConnectionNewParamsAuthType = "token"
	SecretConnectionNewParamsAuthTypeApprole SecretConnectionNewParamsAuthType = "approle"
)

func (r SecretConnectionNewParamsAuthType) IsKnown() bool {
	switch r {
	case SecretConnectionNewParamsAuthTypeToken, SecretConnectionNewParamsAuthTypeApprole:
		return true
	}
	return false
}

type SecretConnectionNewParamsProvider string

const (
	SecretConnectionNewParamsProvider1password      SecretConnectionNewParamsProvider = "1password"
	SecretConnectionNewParamsProviderHashicorpVault SecretConnectionNewParamsProvider = "hashicorp_vault"
)

func (r SecretConnectionNewParamsProvider) IsKnown() bool {
	switch r {
	case SecretConnectionNewParamsProvider1password, SecretConnectionNewParamsProviderHashicorpVault:
		return true
	}
	return false
}

type SecretConnectionNewParamsConfig struct {
	Address   param.Field[string] `json:"address" api:"required"`
	CaPem     param.Field[string] `json:"caPem"`
	Namespace param.Field[string] `json:"namespace"`
}

func (r SecretConnectionNewParamsConfig) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SecretConnectionNewParamsTokenBudget struct {
	Burst param.Field[int64] `json:"burst" api:"required"`
	// Tokens per second, with at most three decimal places.
	RefillPerSecond param.Field[float64] `json:"refillPerSecond" api:"required"`
}

func (r SecretConnectionNewParamsTokenBudget) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SecretConnectionNewResponse struct {
	ID          string                                 `json:"id" api:"required" format:"uuid"`
	CreatedAt   time.Time                              `json:"createdAt" api:"required" format:"date-time"`
	Enabled     bool                                   `json:"enabled" api:"required"`
	Name        string                                 `json:"name" api:"required"`
	Provider    SecretConnectionNewResponseProvider    `json:"provider" api:"required"`
	TokenBudget SecretConnectionNewResponseTokenBudget `json:"tokenBudget" api:"required,nullable"`
	UpdatedAt   time.Time                              `json:"updatedAt" api:"required" format:"date-time"`
	// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault
	// IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint
	// and namespace. Neither proves immutable account or server identity.
	VaultFingerprint string                          `json:"vaultFingerprint" api:"required"`
	Version          int64                           `json:"version" api:"required"`
	JSON             secretConnectionNewResponseJSON `json:"-"`
}

// secretConnectionNewResponseJSON contains the JSON metadata for the struct
// [SecretConnectionNewResponse]
type secretConnectionNewResponseJSON struct {
	ID               apijson.Field
	CreatedAt        apijson.Field
	Enabled          apijson.Field
	Name             apijson.Field
	Provider         apijson.Field
	TokenBudget      apijson.Field
	UpdatedAt        apijson.Field
	VaultFingerprint apijson.Field
	Version          apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *SecretConnectionNewResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionNewResponseJSON) RawJSON() string {
	return r.raw
}

type SecretConnectionNewResponseProvider string

const (
	SecretConnectionNewResponseProvider1password      SecretConnectionNewResponseProvider = "1password"
	SecretConnectionNewResponseProviderHashicorpVault SecretConnectionNewResponseProvider = "hashicorp_vault"
)

func (r SecretConnectionNewResponseProvider) IsKnown() bool {
	switch r {
	case SecretConnectionNewResponseProvider1password, SecretConnectionNewResponseProviderHashicorpVault:
		return true
	}
	return false
}

type SecretConnectionNewResponseTokenBudget struct {
	Burst int64 `json:"burst" api:"required"`
	// Tokens per second, with at most three decimal places.
	RefillPerSecond float64                                    `json:"refillPerSecond" api:"required"`
	JSON            secretConnectionNewResponseTokenBudgetJSON `json:"-"`
}

// secretConnectionNewResponseTokenBudgetJSON contains the JSON metadata for the
// struct [SecretConnectionNewResponseTokenBudget]
type secretConnectionNewResponseTokenBudgetJSON struct {
	Burst           apijson.Field
	RefillPerSecond apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *SecretConnectionNewResponseTokenBudget) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionNewResponseTokenBudgetJSON) RawJSON() string {
	return r.raw
}

// Get a secret connection
func (r *SecretConnectionService) Get(ctx context.Context, id string, query SecretConnectionGetParams, opts ...option.RequestOption) (res *SecretConnectionGetResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/secret-connections/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type SecretConnectionGetParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [SecretConnectionGetParams]'s query parameters as `url.Values`.
func (r SecretConnectionGetParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type SecretConnectionGetResponse struct {
	ID          string                                 `json:"id" api:"required" format:"uuid"`
	CreatedAt   time.Time                              `json:"createdAt" api:"required" format:"date-time"`
	Enabled     bool                                   `json:"enabled" api:"required"`
	Name        string                                 `json:"name" api:"required"`
	Provider    SecretConnectionGetResponseProvider    `json:"provider" api:"required"`
	TokenBudget SecretConnectionGetResponseTokenBudget `json:"tokenBudget" api:"required,nullable"`
	UpdatedAt   time.Time                              `json:"updatedAt" api:"required" format:"date-time"`
	// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault
	// IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint
	// and namespace. Neither proves immutable account or server identity.
	VaultFingerprint string                          `json:"vaultFingerprint" api:"required"`
	Version          int64                           `json:"version" api:"required"`
	JSON             secretConnectionGetResponseJSON `json:"-"`
}

// secretConnectionGetResponseJSON contains the JSON metadata for the struct
// [SecretConnectionGetResponse]
type secretConnectionGetResponseJSON struct {
	ID               apijson.Field
	CreatedAt        apijson.Field
	Enabled          apijson.Field
	Name             apijson.Field
	Provider         apijson.Field
	TokenBudget      apijson.Field
	UpdatedAt        apijson.Field
	VaultFingerprint apijson.Field
	Version          apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *SecretConnectionGetResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionGetResponseJSON) RawJSON() string {
	return r.raw
}

type SecretConnectionGetResponseProvider string

const (
	SecretConnectionGetResponseProvider1password      SecretConnectionGetResponseProvider = "1password"
	SecretConnectionGetResponseProviderHashicorpVault SecretConnectionGetResponseProvider = "hashicorp_vault"
)

func (r SecretConnectionGetResponseProvider) IsKnown() bool {
	switch r {
	case SecretConnectionGetResponseProvider1password, SecretConnectionGetResponseProviderHashicorpVault:
		return true
	}
	return false
}

type SecretConnectionGetResponseTokenBudget struct {
	Burst int64 `json:"burst" api:"required"`
	// Tokens per second, with at most three decimal places.
	RefillPerSecond float64                                    `json:"refillPerSecond" api:"required"`
	JSON            secretConnectionGetResponseTokenBudgetJSON `json:"-"`
}

// secretConnectionGetResponseTokenBudgetJSON contains the JSON metadata for the
// struct [SecretConnectionGetResponseTokenBudget]
type secretConnectionGetResponseTokenBudgetJSON struct {
	Burst           apijson.Field
	RefillPerSecond apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *SecretConnectionGetResponseTokenBudget) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionGetResponseTokenBudgetJSON) RawJSON() string {
	return r.raw
}

// Update a secret connection
func (r *SecretConnectionService) Update(ctx context.Context, id string, params SecretConnectionUpdateParams, opts ...option.RequestOption) (res *SecretConnectionUpdateResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/secret-connections/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPatch, path, params, &res, opts...)
	return res, err
}

type SecretConnectionUpdateParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
	// Write-only provider authentication. Supply a service account token for
	// 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
	// HashiCorp AppRole.
	Auth        param.Field[SecretConnectionUpdateParamsAuth]        `json:"auth"`
	Config      param.Field[SecretConnectionUpdateParamsConfig]      `json:"config"`
	Enabled     param.Field[bool]                                    `json:"enabled"`
	Name        param.Field[string]                                  `json:"name"`
	TokenBudget param.Field[SecretConnectionUpdateParamsTokenBudget] `json:"tokenBudget"`
}

func (r SecretConnectionUpdateParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// URLQuery serializes [SecretConnectionUpdateParams]'s query parameters as `url.Values`.
func (r SecretConnectionUpdateParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Write-only provider authentication. Supply a service account token for
// 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
// HashiCorp AppRole.
type SecretConnectionUpdateParamsAuth struct {
	// Write-only; never returned by the API.
	Token param.Field[string] `json:"token"`
	Mount param.Field[string] `json:"mount"`
	// Write-only; never returned by the API.
	RoleID param.Field[string] `json:"roleId"`
	// Write-only; never returned by the API.
	SecretID param.Field[string] `json:"secretId"`
	// Write-only; never returned by the API.
	ServiceAccountToken param.Field[string]                               `json:"serviceAccountToken"`
	Type                param.Field[SecretConnectionUpdateParamsAuthType] `json:"type"`
}

func (r SecretConnectionUpdateParamsAuth) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SecretConnectionUpdateParamsAuthType string

const (
	SecretConnectionUpdateParamsAuthTypeToken   SecretConnectionUpdateParamsAuthType = "token"
	SecretConnectionUpdateParamsAuthTypeApprole SecretConnectionUpdateParamsAuthType = "approle"
)

func (r SecretConnectionUpdateParamsAuthType) IsKnown() bool {
	switch r {
	case SecretConnectionUpdateParamsAuthTypeToken, SecretConnectionUpdateParamsAuthTypeApprole:
		return true
	}
	return false
}

type SecretConnectionUpdateParamsConfig struct {
	Address   param.Field[string] `json:"address" api:"required"`
	CaPem     param.Field[string] `json:"caPem"`
	Namespace param.Field[string] `json:"namespace"`
}

func (r SecretConnectionUpdateParamsConfig) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SecretConnectionUpdateParamsTokenBudget struct {
	Burst param.Field[int64] `json:"burst" api:"required"`
	// Tokens per second, with at most three decimal places.
	RefillPerSecond param.Field[float64] `json:"refillPerSecond" api:"required"`
}

func (r SecretConnectionUpdateParamsTokenBudget) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SecretConnectionUpdateResponse struct {
	ID          string                                    `json:"id" api:"required" format:"uuid"`
	CreatedAt   time.Time                                 `json:"createdAt" api:"required" format:"date-time"`
	Enabled     bool                                      `json:"enabled" api:"required"`
	Name        string                                    `json:"name" api:"required"`
	Provider    SecretConnectionUpdateResponseProvider    `json:"provider" api:"required"`
	TokenBudget SecretConnectionUpdateResponseTokenBudget `json:"tokenBudget" api:"required,nullable"`
	UpdatedAt   time.Time                                 `json:"updatedAt" api:"required" format:"date-time"`
	// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault
	// IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint
	// and namespace. Neither proves immutable account or server identity.
	VaultFingerprint string                             `json:"vaultFingerprint" api:"required"`
	Version          int64                              `json:"version" api:"required"`
	JSON             secretConnectionUpdateResponseJSON `json:"-"`
}

// secretConnectionUpdateResponseJSON contains the JSON metadata for the struct
// [SecretConnectionUpdateResponse]
type secretConnectionUpdateResponseJSON struct {
	ID               apijson.Field
	CreatedAt        apijson.Field
	Enabled          apijson.Field
	Name             apijson.Field
	Provider         apijson.Field
	TokenBudget      apijson.Field
	UpdatedAt        apijson.Field
	VaultFingerprint apijson.Field
	Version          apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *SecretConnectionUpdateResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionUpdateResponseJSON) RawJSON() string {
	return r.raw
}

type SecretConnectionUpdateResponseProvider string

const (
	SecretConnectionUpdateResponseProvider1password      SecretConnectionUpdateResponseProvider = "1password"
	SecretConnectionUpdateResponseProviderHashicorpVault SecretConnectionUpdateResponseProvider = "hashicorp_vault"
)

func (r SecretConnectionUpdateResponseProvider) IsKnown() bool {
	switch r {
	case SecretConnectionUpdateResponseProvider1password, SecretConnectionUpdateResponseProviderHashicorpVault:
		return true
	}
	return false
}

type SecretConnectionUpdateResponseTokenBudget struct {
	Burst int64 `json:"burst" api:"required"`
	// Tokens per second, with at most three decimal places.
	RefillPerSecond float64                                       `json:"refillPerSecond" api:"required"`
	JSON            secretConnectionUpdateResponseTokenBudgetJSON `json:"-"`
}

// secretConnectionUpdateResponseTokenBudgetJSON contains the JSON metadata for
// the struct [SecretConnectionUpdateResponseTokenBudget]
type secretConnectionUpdateResponseTokenBudgetJSON struct {
	Burst           apijson.Field
	RefillPerSecond apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *SecretConnectionUpdateResponseTokenBudget) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionUpdateResponseTokenBudgetJSON) RawJSON() string {
	return r.raw
}

// List secret connections
func (r *SecretConnectionService) List(ctx context.Context, query SecretConnectionListParams, opts ...option.RequestOption) (res *SecretConnectionListResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/secret-connections"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type SecretConnectionListParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [SecretConnectionListParams]'s query parameters as `url.Values`.
func (r SecretConnectionListParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type SecretConnectionListResponse struct {
	Connections []SecretConnectionListResponseConnection `json:"connections" api:"required"`
	JSON        secretConnectionListResponseJSON         `json:"-"`
}

// secretConnectionListResponseJSON contains the JSON metadata for the struct
// [SecretConnectionListResponse]
type secretConnectionListResponseJSON struct {
	Connections apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SecretConnectionListResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionListResponseJSON) RawJSON() string {
	return r.raw
}

type SecretConnectionListResponseConnection struct {
	ID          string                                            `json:"id" api:"required" format:"uuid"`
	CreatedAt   time.Time                                         `json:"createdAt" api:"required" format:"date-time"`
	Enabled     bool                                              `json:"enabled" api:"required"`
	Name        string                                            `json:"name" api:"required"`
	Provider    SecretConnectionListResponseConnectionProvider    `json:"provider" api:"required"`
	TokenBudget SecretConnectionListResponseConnectionTokenBudget `json:"tokenBudget" api:"required,nullable"`
	UpdatedAt   time.Time                                         `json:"updatedAt" api:"required" format:"date-time"`
	// For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault
	// IDs. For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint
	// and namespace. Neither proves immutable account or server identity.
	VaultFingerprint string                                     `json:"vaultFingerprint" api:"required"`
	Version          int64                                      `json:"version" api:"required"`
	JSON             secretConnectionListResponseConnectionJSON `json:"-"`
}

// secretConnectionListResponseConnectionJSON contains the JSON metadata for the
// struct [SecretConnectionListResponseConnection]
type secretConnectionListResponseConnectionJSON struct {
	ID               apijson.Field
	CreatedAt        apijson.Field
	Enabled          apijson.Field
	Name             apijson.Field
	Provider         apijson.Field
	TokenBudget      apijson.Field
	UpdatedAt        apijson.Field
	VaultFingerprint apijson.Field
	Version          apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *SecretConnectionListResponseConnection) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionListResponseConnectionJSON) RawJSON() string {
	return r.raw
}

type SecretConnectionListResponseConnectionProvider string

const (
	SecretConnectionListResponseConnectionProvider1password      SecretConnectionListResponseConnectionProvider = "1password"
	SecretConnectionListResponseConnectionProviderHashicorpVault SecretConnectionListResponseConnectionProvider = "hashicorp_vault"
)

func (r SecretConnectionListResponseConnectionProvider) IsKnown() bool {
	switch r {
	case SecretConnectionListResponseConnectionProvider1password, SecretConnectionListResponseConnectionProviderHashicorpVault:
		return true
	}
	return false
}

type SecretConnectionListResponseConnectionTokenBudget struct {
	Burst int64 `json:"burst" api:"required"`
	// Tokens per second, with at most three decimal places.
	RefillPerSecond float64                                               `json:"refillPerSecond" api:"required"`
	JSON            secretConnectionListResponseConnectionTokenBudgetJSON `json:"-"`
}

// secretConnectionListResponseConnectionTokenBudgetJSON contains the JSON
// metadata for the struct [SecretConnectionListResponseConnectionTokenBudget]
type secretConnectionListResponseConnectionTokenBudgetJSON struct {
	Burst           apijson.Field
	RefillPerSecond apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *SecretConnectionListResponseConnectionTokenBudget) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r secretConnectionListResponseConnectionTokenBudgetJSON) RawJSON() string {
	return r.raw
}

// Delete a secret connection
func (r *SecretConnectionService) Delete(ctx context.Context, id string, query SecretConnectionDeleteParams, opts ...option.RequestOption) (err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "*/*")}, opts...)
	if id == "" {
		err = errors.New("missing required id parameter")
		return err
	}
	path := fmt.Sprintf("v1/secret-connections/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, query, nil, opts...)
	return err
}

type SecretConnectionDeleteParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [SecretConnectionDeleteParams]'s query parameters as `url.Values`.
func (r SecretConnectionDeleteParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Test a secret connection
func (r *SecretConnectionService) Test(ctx context.Context, id string, query SecretConnectionTestParams, opts ...option.RequestOption) (err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "*/*")}, opts...)
	if id == "" {
		err = errors.New("missing required id parameter")
		return err
	}
	path := fmt.Sprintf("v1/secret-connections/%s/test", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, query, nil, opts...)
	return err
}

type SecretConnectionTestParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [SecretConnectionTestParams]'s query parameters as `url.Values`.
func (r SecretConnectionTestParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}
