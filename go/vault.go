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

// VaultService contains methods and other services that help with interacting
// with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewVaultService] method instead.
type VaultService struct {
	Options []option.RequestOption
}

// NewVaultService generates a new service that applies the given options to
// each request. These options are applied after the parent client's options (if
// there is one), and before any request-specific options.
func NewVaultService(opts ...option.RequestOption) (r *VaultService) {
	r = &VaultService{}
	r.Options = opts
	return
}

// Create a Vault
func (r *VaultService) New(ctx context.Context, body VaultNewParams, opts ...option.RequestOption) (res *Vault, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/vaults"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type VaultNewParams struct {
	// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start
	// with a letter or digit.
	Name        param.Field[string] `json:"name" api:"required"`
	Description param.Field[string] `json:"description"`
	ProjectID   param.Field[string] `json:"projectId" format:"uuid"`
}

func (r VaultNewParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Get a Vault
func (r *VaultService) Get(ctx context.Context, id string, query VaultGetParams, opts ...option.RequestOption) (res *Vault, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/vaults/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type VaultGetParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [VaultGetParams]'s query parameters as `url.Values`.
func (r VaultGetParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Update a Vault
func (r *VaultService) Update(ctx context.Context, id string, params VaultUpdateParams, opts ...option.RequestOption) (res *Vault, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/vaults/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPatch, path, params, &res, opts...)
	return res, err
}

type VaultUpdateParams struct {
	ProjectID   param.Field[string] `query:"projectId" format:"uuid"`
	Description param.Field[string] `json:"description"`
	// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start
	// with a letter or digit.
	Name param.Field[string] `json:"name"`
}

func (r VaultUpdateParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// URLQuery serializes [VaultUpdateParams]'s query parameters as `url.Values`.
func (r VaultUpdateParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// List Vaults
func (r *VaultService) List(ctx context.Context, query VaultListParams, opts ...option.RequestOption) (res *VaultList, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/vaults"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type VaultListParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [VaultListParams]'s query parameters as `url.Values`.
func (r VaultListParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Soft-delete a Vault. Returns 409 while it contains any live Secrets or
// credentials; move or delete them first.
func (r *VaultService) Delete(ctx context.Context, id string, query VaultDeleteParams, opts ...option.RequestOption) (err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "*/*")}, opts...)
	if id == "" {
		err = errors.New("missing required id parameter")
		return err
	}
	path := fmt.Sprintf("v1/vaults/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, query, nil, opts...)
	return err
}

type VaultDeleteParams struct {
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [VaultDeleteParams]'s query parameters as `url.Values`.
func (r VaultDeleteParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type Vault struct {
	ID          string    `json:"id" api:"required" format:"uuid"`
	CreatedAt   time.Time `json:"createdAt" api:"required" format:"date-time"`
	Description string    `json:"description" api:"required,nullable"`
	// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start
	// with a letter or digit.
	Name      string    `json:"name" api:"required"`
	ProjectID string    `json:"projectId" api:"required" format:"uuid"`
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	JSON      vaultJSON `json:"-"`
}

// vaultJSON contains the JSON metadata for the struct [Vault]
type vaultJSON struct {
	ID          apijson.Field
	CreatedAt   apijson.Field
	Description apijson.Field
	Name        apijson.Field
	ProjectID   apijson.Field
	UpdatedAt   apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *Vault) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r vaultJSON) RawJSON() string {
	return r.raw
}

type VaultList struct {
	Vaults []VaultListVault `json:"vaults" api:"required"`
	JSON   vaultListJSON    `json:"-"`
}

// vaultListJSON contains the JSON metadata for the struct [VaultList]
type vaultListJSON struct {
	Vaults      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *VaultList) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r vaultListJSON) RawJSON() string {
	return r.raw
}

type VaultListVault struct {
	ID          string    `json:"id" api:"required" format:"uuid"`
	CreatedAt   time.Time `json:"createdAt" api:"required" format:"date-time"`
	Description string    `json:"description" api:"required,nullable"`
	// 1–63 lowercase letters, digits, hyphens, underscores or dots; must start
	// with a letter or digit.
	Name      string             `json:"name" api:"required"`
	ProjectID string             `json:"projectId" api:"required" format:"uuid"`
	UpdatedAt time.Time          `json:"updatedAt" api:"required" format:"date-time"`
	JSON      vaultListVaultJSON `json:"-"`
}

// vaultListVaultJSON contains the JSON metadata for the struct [VaultListVault]
type vaultListVaultJSON struct {
	ID          apijson.Field
	CreatedAt   apijson.Field
	Description apijson.Field
	Name        apijson.Field
	ProjectID   apijson.Field
	UpdatedAt   apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *VaultListVault) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r vaultListVaultJSON) RawJSON() string {
	return r.raw
}
