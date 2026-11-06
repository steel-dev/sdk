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
	"time"

	"github.com/steel-dev/sdk/go/internal/apiform"
	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// FileService contains methods and other services that help with interacting
// with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewFileService] method instead.
type FileService struct {
	Options []option.RequestOption
}

// NewFileService generates a new service that applies the given options to each
// request. These options are applied after the parent client's options (if
// there is one), and before any request-specific options.
func NewFileService(opts ...option.RequestOption) (r *FileService) {
	r = &FileService{}
	r.Options = opts
	return
}

// List all global files for the organization in descending order.
func (r *FileService) List(ctx context.Context, opts ...option.RequestOption) (res *Fileslist, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/files"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Delete a file from global storage
func (r *FileService) Delete(ctx context.Context, path_ string, opts ...option.RequestOption) (err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "*/*")}, opts...)
	if path_ == "" {
		err = errors.New("missing required path parameter")
		return err
	}
	path := fmt.Sprintf("v1/files/%s", url.PathEscape(path_))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, nil, nil, opts...)
	return err
}

// Download a file from global storage
func (r *FileService) Download(ctx context.Context, path_ string, opts ...option.RequestOption) (res *http.Response, err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "application/octet-stream")}, opts...)
	if path_ == "" {
		err = errors.New("missing required path parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/files/%s", url.PathEscape(path_))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Uploads a file to global storage via `multipart/form-data` with a `file`
// field that accepts either binary data or a URL string to download from, and
// an optional `path` field for the file storage path.
func (r *FileService) Upload(ctx context.Context, body FileUploadParams, opts ...option.RequestOption) (res *File, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/files"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type FileUploadParams struct {
	// The file to upload (binary) or URL string to download from
	File param.Field[io.Reader] `json:"file" api:"required" format:"binary"`
	// Path to the file in the storage system
	Path param.Field[string] `json:"path"`
}

func (r FileUploadParams) MarshalMultipart() (data []byte, contentType string, err error) {
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

type File struct {
	// Timestamp when the file was created
	LastModified time.Time `json:"lastModified" api:"required" format:"date-time"`
	// Path to the file in the storage system
	Path string `json:"path" api:"required"`
	// Size of the file in bytes
	Size float64  `json:"size" api:"required"`
	JSON fileJSON `json:"-"`
}

// fileJSON contains the JSON metadata for the struct [File]
type fileJSON struct {
	LastModified apijson.Field
	Path         apijson.Field
	Size         apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *File) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r fileJSON) RawJSON() string {
	return r.raw
}

type Fileslist struct {
	// Array of files for the current page
	Data []FileslistData `json:"data" api:"required"`
	JSON fileslistJSON   `json:"-"`
}

// fileslistJSON contains the JSON metadata for the struct [Fileslist]
type fileslistJSON struct {
	Data        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *Fileslist) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r fileslistJSON) RawJSON() string {
	return r.raw
}

// Array of files for the current page
type FileslistData struct {
	// Timestamp when the file was created
	LastModified time.Time `json:"lastModified" api:"required" format:"date-time"`
	// Path to the file in the storage system
	Path string `json:"path" api:"required"`
	// Size of the file in bytes
	Size float64           `json:"size" api:"required"`
	JSON fileslistDataJSON `json:"-"`
}

// fileslistDataJSON contains the JSON metadata for the struct [FileslistData]
type fileslistDataJSON struct {
	LastModified apijson.Field
	Path         apijson.Field
	Size         apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *FileslistData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r fileslistDataJSON) RawJSON() string {
	return r.raw
}
