// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
	"errors"
	"fmt"
	"net/http"
	"net/url"
	"slices"

	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/apiquery"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// SessionCredentialService contains methods and other services that help with
// interacting with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewSessionCredentialService] method instead.
type SessionCredentialService struct {
	Options []option.RequestOption
}

// NewSessionCredentialService generates a new service that applies the given
// options to each request. These options are applied after the parent client's
// options (if there is one), and before any request-specific options.
func NewSessionCredentialService(opts ...option.RequestOption) (r *SessionCredentialService) {
	r = &SessionCredentialService{}
	r.Options = opts
	return
}

// The session's credential lookup and the live credentials bound to it (no
// values). Subject to session organization and project access.
func (r *SessionCredentialService) List(ctx context.Context, id string, opts ...option.RequestOption) (res *SessionCredentials, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/credentials", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Attach credentialIds and/or Vault or item references in bindings. Each array
// accepts at most 100 entries; at most 100 distinct direct items and whole
// Vaults may be bound. Only credentials can be autofilled. Lookup becomes
// explicit and remains so. Errors: 400 `invalid_reference`,
// `reference_not_found`, `reference_ambiguous`, `reference_not_supported`,
// `credential_not_bindable`, or `too_many_credentials`; 409
// `credentials_not_enabled` or `session_not_running`; 503
// `credential_bindings_busy` (retryable).
func (r *SessionCredentialService) Attach(ctx context.Context, id string, body SessionCredentialAttachParams, opts ...option.RequestOption) (res *SessionCredentials, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/credentials", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type SessionCredentialAttachParams struct {
	// At most 100 bindings; KV bindings may specify allowedOrigins.
	Bindings param.Field[[]SessionCredentialAttachParamsBindingUnion] `json:"bindings"`
	// Ids of credentials (Secrets of kind `credential`) in the session's project.
	// Binding makes the session's lookup `explicit` for good.
	CredentialIDs param.Field[[]string] `json:"credentialIds"`
}

func (r SessionCredentialAttachParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// At most 100 bindings; KV bindings may specify allowedOrigins.
// Satisfied by [shared.UnionString],
// [SessionCredentialAttachParamsBindingUnionMember1].
type SessionCredentialAttachParamsBindingUnion interface {
	ImplementsSessionCredentialAttachParamsBindingUnion()
}

type SessionCredentialAttachParamsBindingUnionMember1 struct {
	AllowedOrigins param.Field[[]string] `json:"allowedOrigins" api:"required"`
	Reference      param.Field[string]   `json:"reference" api:"required"`
}

func (r SessionCredentialAttachParamsBindingUnionMember1) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

func (r SessionCredentialAttachParamsBindingUnionMember1) ImplementsSessionCredentialAttachParamsBindingUnion() {
}

// Unbind a credential from a running session. The lookup stays `explicit`: with
// nothing bound, the session fills nothing. Errors: 404
// `credential_not_attached`, 409 `session_not_running`, 503
// `credential_bindings_busy` (retryable).
func (r *SessionCredentialService) Detach(ctx context.Context, id string, credentialID string, opts ...option.RequestOption) (res *SessionCredentials, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	if credentialID == "" {
		err = errors.New("missing required credentialId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/credentials/%s", url.PathEscape(id), url.PathEscape(credentialID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, nil, &res, opts...)
	return res, err
}

// Pass reference as a query parameter to detach one Vault or item binding,
// including steel://connections/<connection>/vaults/<vault>[/<item>]. Does not
// detach items covered by a whole Vault binding. Errors: 400
// `invalid_reference` or `reference_ambiguous`; 404 `credential_not_attached`;
// 409 `session_not_running`; 503 `credential_bindings_busy` (retryable).
func (r *SessionCredentialService) DetachBinding(ctx context.Context, id string, query SessionCredentialDetachBindingParams, opts ...option.RequestOption) (res *SessionCredentials, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/credentials/bindings", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, query, &res, opts...)
	return res, err
}

type SessionCredentialDetachBindingParams struct {
	// Vault or item reference to detach
	Reference param.Field[string] `query:"reference" api:"required"`
}

// URLQuery serializes [SessionCredentialDetachBindingParams]'s query parameters as `url.Values`.
func (r SessionCredentialDetachBindingParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Replace all direct item and Vault bindings, including provider references
// steel://connections/<connection>/vaults/<vault>[/<item>]. Supply
// credentialIds and/or bindings; empty arrays detach all. At most 100 distinct
// bindings may be stored. Only credentials can be autofilled. Lookup becomes
// explicit and remains so.
func (r *SessionCredentialService) Replace(ctx context.Context, id string, body SessionCredentialReplaceParams, opts ...option.RequestOption) (res *SessionCredentials, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/credentials", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPut, path, body, &res, opts...)
	return res, err
}

type SessionCredentialReplaceParams struct {
	// At most 100 bindings; KV bindings may specify allowedOrigins.
	Bindings param.Field[[]SessionCredentialReplaceParamsBindingUnion] `json:"bindings"`
	// Ids of credentials (Secrets of kind `credential`) in the session's project.
	// Binding makes the session's lookup `explicit` for good.
	CredentialIDs param.Field[[]string] `json:"credentialIds"`
}

func (r SessionCredentialReplaceParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// At most 100 bindings; KV bindings may specify allowedOrigins.
// Satisfied by [shared.UnionString],
// [SessionCredentialReplaceParamsBindingUnionMember1].
type SessionCredentialReplaceParamsBindingUnion interface {
	ImplementsSessionCredentialReplaceParamsBindingUnion()
}

type SessionCredentialReplaceParamsBindingUnionMember1 struct {
	AllowedOrigins param.Field[[]string] `json:"allowedOrigins" api:"required"`
	Reference      param.Field[string]   `json:"reference" api:"required"`
}

func (r SessionCredentialReplaceParamsBindingUnionMember1) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

func (r SessionCredentialReplaceParamsBindingUnionMember1) ImplementsSessionCredentialReplaceParamsBindingUnion() {
}
