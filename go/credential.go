// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
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

// CredentialService contains methods and other services that help with
// interacting with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewCredentialService] method instead.
type CredentialService struct {
	Options []option.RequestOption
}

// NewCredentialService generates a new service that applies the given options
// to each request. These options are applied after the parent client's options
// (if there is one), and before any request-specific options.
func NewCredentialService(opts ...option.RequestOption) (r *CredentialService) {
	r = &CredentialService{}
	r.Options = opts
	return
}

// Encrypts and stores credentials for an origin
func (r *CredentialService) New(ctx context.Context, body CredentialNewParams, opts ...option.RequestOption) (res *CredentialNewResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/credentials"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type CredentialNewParams struct {
	// Value for the credential
	Value param.Field[map[string]string] `json:"value" api:"required"`
	// Label for the credential
	Label param.Field[string] `json:"label"`
	// The namespace the credential is stored against. Defaults to "default".
	Namespace param.Field[string] `json:"namespace"`
	// Website origin the credential is for
	Origin param.Field[string] `json:"origin"`
	// Project to store the credential in.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
}

func (r CredentialNewParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CredentialNewResponse struct {
	// Date and time the credential was created
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// Date and time the credential was last updated
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	// Label for the credential
	Label string `json:"label"`
	// The namespace the credential is stored against. Defaults to "default".
	Namespace string `json:"namespace"`
	// Website origin the credential is for
	Origin string                    `json:"origin"`
	JSON   credentialNewResponseJSON `json:"-"`
}

// credentialNewResponseJSON contains the JSON metadata for the struct
// [CredentialNewResponse]
type credentialNewResponseJSON struct {
	CreatedAt   apijson.Field
	UpdatedAt   apijson.Field
	Label       apijson.Field
	Namespace   apijson.Field
	Origin      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CredentialNewResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r credentialNewResponseJSON) RawJSON() string {
	return r.raw
}

// Encrypts and updates credentials for an origin
func (r *CredentialService) Update(ctx context.Context, body CredentialUpdateParams, opts ...option.RequestOption) (res *CredentialUpdateResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/credentials"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPut, path, body, &res, opts...)
	return res, err
}

type CredentialUpdateParams struct {
	// Label for the credential
	Label param.Field[string] `json:"label"`
	// The namespace the credential is stored against. Defaults to "default".
	Namespace param.Field[string] `json:"namespace"`
	// Website origin the credential is for
	Origin param.Field[string] `json:"origin"`
	// Project to update the credential in.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// Value for the credential
	Value param.Field[map[string]string] `json:"value"`
}

func (r CredentialUpdateParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CredentialUpdateResponse struct {
	// Date and time the credential was created
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// Date and time the credential was last updated
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	// Label for the credential
	Label string `json:"label"`
	// The namespace the credential is stored against. Defaults to "default".
	Namespace string `json:"namespace"`
	// Website origin the credential is for
	Origin string                       `json:"origin"`
	JSON   credentialUpdateResponseJSON `json:"-"`
}

// credentialUpdateResponseJSON contains the JSON metadata for the struct
// [CredentialUpdateResponse]
type credentialUpdateResponseJSON struct {
	CreatedAt   apijson.Field
	UpdatedAt   apijson.Field
	Label       apijson.Field
	Namespace   apijson.Field
	Origin      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CredentialUpdateResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r credentialUpdateResponseJSON) RawJSON() string {
	return r.raw
}

// Fetches all credential metadata for the current organization.
func (r *CredentialService) List(ctx context.Context, query CredentialListParams, opts ...option.RequestOption) (res *CredentialListResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/credentials"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type CredentialListParams struct {
	// namespace credential is stored against
	Namespace param.Field[string] `query:"namespace"`
	// website origin the credential is for
	Origin param.Field[string] `query:"origin"`
	// Project to query credentials from.
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [CredentialListParams]'s query parameters as `url.Values`.
func (r CredentialListParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type CredentialListResponse struct {
	Credentials []CredentialListResponseCredential `json:"credentials" api:"required"`
	JSON        credentialListResponseJSON         `json:"-"`
}

// credentialListResponseJSON contains the JSON metadata for the struct
// [CredentialListResponse]
type credentialListResponseJSON struct {
	Credentials apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CredentialListResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r credentialListResponseJSON) RawJSON() string {
	return r.raw
}

type CredentialListResponseCredential struct {
	// Date and time the credential was created
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// Date and time the credential was last updated
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	// Label for the credential
	Label string `json:"label"`
	// The namespace the credential is stored against. Defaults to "default".
	Namespace string `json:"namespace"`
	// Website origin the credential is for
	Origin string                               `json:"origin"`
	JSON   credentialListResponseCredentialJSON `json:"-"`
}

// credentialListResponseCredentialJSON contains the JSON metadata for the
// struct [CredentialListResponseCredential]
type credentialListResponseCredentialJSON struct {
	CreatedAt   apijson.Field
	UpdatedAt   apijson.Field
	Label       apijson.Field
	Namespace   apijson.Field
	Origin      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CredentialListResponseCredential) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r credentialListResponseCredentialJSON) RawJSON() string {
	return r.raw
}

// Deletes encrypted credentials from the database
func (r *CredentialService) Delete(ctx context.Context, body CredentialDeleteParams, opts ...option.RequestOption) (res *CredentialDeleteResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/credentials"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, body, &res, opts...)
	return res, err
}

type CredentialDeleteParams struct {
	// Website origin the credential is for
	Origin param.Field[string] `json:"origin" api:"required"`
	// The namespace the credential is stored against. Defaults to "default".
	Namespace param.Field[string] `json:"namespace"`
	// Project to delete the credential from.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
}

func (r CredentialDeleteParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CredentialDeleteResponse struct {
	Success bool                         `json:"success" api:"required"`
	JSON    credentialDeleteResponseJSON `json:"-"`
}

// credentialDeleteResponseJSON contains the JSON metadata for the struct
// [CredentialDeleteResponse]
type credentialDeleteResponseJSON struct {
	Success     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CredentialDeleteResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r credentialDeleteResponseJSON) RawJSON() string {
	return r.raw
}
