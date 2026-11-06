// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"bytes"
	"context"
	"errors"
	"fmt"
	"io"
	"mime/multipart"
	"net/http"
	"net/url"
	"slices"

	"github.com/steel-dev/sdk/go/internal/apiform"
	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// ExtensionService contains methods and other services that help with
// interacting with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewExtensionService] method instead.
type ExtensionService struct {
	Options []option.RequestOption
}

// NewExtensionService generates a new service that applies the given options to
// each request. These options are applied after the parent client's options (if
// there is one), and before any request-specific options.
func NewExtensionService(opts ...option.RequestOption) (r *ExtensionService) {
	r = &ExtensionService{}
	r.Options = opts
	return
}

// Update a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the
// organization
func (r *ExtensionService) Update(ctx context.Context, extensionID string, body ExtensionUpdateParams, opts ...option.RequestOption) (res *ExtensionUpdateResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if extensionID == "" {
		err = errors.New("missing required extensionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/extensions/%s", url.PathEscape(extensionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPut, path, body, &res, opts...)
	return res, err
}

type ExtensionUpdateParams struct {
	// Extension .zip/.crx file
	File param.Field[io.Reader] `json:"file" format:"binary"`
	// Extension URL
	URL param.Field[string] `json:"url"`
}

func (r ExtensionUpdateParams) MarshalMultipart() (data []byte, contentType string, err error) {
	buf := bytes.NewBuffer(nil)
	writer := multipart.NewWriter(buf)
	err = apiform.MarshalRoot(r, writer)
	if err != nil {
		writer.Close()
		return nil, "", err
	}
	err = writer.Close()
	if err != nil {
		return nil, "", err
	}
	return buf.Bytes(), writer.FormDataContentType(), nil
}

type ExtensionUpdateResponse struct {
	// Unique extension identifier (e.g., ext_12345)
	ID string `json:"id" api:"required"`
	// Creation timestamp
	CreatedAt string `json:"createdAt" api:"required"`
	// Extension name
	Name string `json:"name" api:"required"`
	// Last update timestamp
	UpdatedAt string                      `json:"updatedAt" api:"required"`
	JSON      extensionUpdateResponseJSON `json:"-"`
}

// extensionUpdateResponseJSON contains the JSON metadata for the struct
// [ExtensionUpdateResponse]
type extensionUpdateResponseJSON struct {
	ID          apijson.Field
	CreatedAt   apijson.Field
	Name        apijson.Field
	UpdatedAt   apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ExtensionUpdateResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r extensionUpdateResponseJSON) RawJSON() string {
	return r.raw
}

// List all extensions for the organization
func (r *ExtensionService) List(ctx context.Context, opts ...option.RequestOption) (res *ExtensionListResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/extensions"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Response containing a list of extensions for the organization
type ExtensionListResponse struct {
	// Total number of extensions
	Count float64 `json:"count" api:"required"`
	// List of extensions for the organization
	Extensions []ExtensionListResponseExtension `json:"extensions" api:"required"`
	JSON       extensionListResponseJSON        `json:"-"`
}

// extensionListResponseJSON contains the JSON metadata for the struct
// [ExtensionListResponse]
type extensionListResponseJSON struct {
	Count       apijson.Field
	Extensions  apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ExtensionListResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r extensionListResponseJSON) RawJSON() string {
	return r.raw
}

// List of extensions for the organization
type ExtensionListResponseExtension struct {
	// Unique extension identifier (e.g., ext_12345)
	ID string `json:"id" api:"required"`
	// Creation timestamp
	CreatedAt string `json:"createdAt" api:"required"`
	// Extension name
	Name string `json:"name" api:"required"`
	// Last update timestamp
	UpdatedAt string                             `json:"updatedAt" api:"required"`
	JSON      extensionListResponseExtensionJSON `json:"-"`
}

// extensionListResponseExtensionJSON contains the JSON metadata for the struct
// [ExtensionListResponseExtension]
type extensionListResponseExtensionJSON struct {
	ID          apijson.Field
	CreatedAt   apijson.Field
	Name        apijson.Field
	UpdatedAt   apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ExtensionListResponseExtension) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r extensionListResponseExtensionJSON) RawJSON() string {
	return r.raw
}

// Delete an extension by ID
func (r *ExtensionService) Delete(ctx context.Context, extensionID string, opts ...option.RequestOption) (res *ExtensionDeleteResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if extensionID == "" {
		err = errors.New("missing required extensionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/extensions/%s", url.PathEscape(extensionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, nil, &res, opts...)
	return res, err
}

type ExtensionDeleteResponse struct {
	Message string                      `json:"message" api:"required"`
	JSON    extensionDeleteResponseJSON `json:"-"`
}

// extensionDeleteResponseJSON contains the JSON metadata for the struct
// [ExtensionDeleteResponse]
type extensionDeleteResponseJSON struct {
	Message     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ExtensionDeleteResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r extensionDeleteResponseJSON) RawJSON() string {
	return r.raw
}

// Delete all extensions for the organization
func (r *ExtensionService) DeleteAll(ctx context.Context, opts ...option.RequestOption) (res *ExtensionDeleteAllResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/extensions"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, nil, &res, opts...)
	return res, err
}

type ExtensionDeleteAllResponse struct {
	Message string                         `json:"message" api:"required"`
	JSON    extensionDeleteAllResponseJSON `json:"-"`
}

// extensionDeleteAllResponseJSON contains the JSON metadata for the struct
// [ExtensionDeleteAllResponse]
type extensionDeleteAllResponseJSON struct {
	Message     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ExtensionDeleteAllResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r extensionDeleteAllResponseJSON) RawJSON() string {
	return r.raw
}

// Download an extension file by extension ID
func (r *ExtensionService) Download(ctx context.Context, extensionID string, opts ...option.RequestOption) (res *http.Response, err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "application/zip")}, opts...)
	if extensionID == "" {
		err = errors.New("missing required extensionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/extensions/%s", url.PathEscape(extensionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Upload a Chrome extension (.zip/.crx file or Chrome Web Store URL) for the
// organization
func (r *ExtensionService) Upload(ctx context.Context, body ExtensionUploadParams, opts ...option.RequestOption) (res *ExtensionUploadResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/extensions"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type ExtensionUploadParams struct {
	// Extension .zip/.crx file
	File param.Field[io.Reader] `json:"file" format:"binary"`
	// Extension URL
	URL param.Field[string] `json:"url"`
}

func (r ExtensionUploadParams) MarshalMultipart() (data []byte, contentType string, err error) {
	buf := bytes.NewBuffer(nil)
	writer := multipart.NewWriter(buf)
	err = apiform.MarshalRoot(r, writer)
	if err != nil {
		writer.Close()
		return nil, "", err
	}
	err = writer.Close()
	if err != nil {
		return nil, "", err
	}
	return buf.Bytes(), writer.FormDataContentType(), nil
}

type ExtensionUploadResponse struct {
	// Unique extension identifier (e.g., ext_12345)
	ID string `json:"id" api:"required"`
	// Creation timestamp
	CreatedAt string `json:"createdAt" api:"required"`
	// Extension name
	Name string `json:"name" api:"required"`
	// Last update timestamp
	UpdatedAt string                      `json:"updatedAt" api:"required"`
	JSON      extensionUploadResponseJSON `json:"-"`
}

// extensionUploadResponseJSON contains the JSON metadata for the struct
// [ExtensionUploadResponse]
type extensionUploadResponseJSON struct {
	ID          apijson.Field
	CreatedAt   apijson.Field
	Name        apijson.Field
	UpdatedAt   apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ExtensionUploadResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r extensionUploadResponseJSON) RawJSON() string {
	return r.raw
}
