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
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// SessionFileService contains methods and other services that help with
// interacting with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewSessionFileService] method instead.
type SessionFileService struct {
	Options []option.RequestOption
}

// NewSessionFileService generates a new service that applies the given options
// to each request. These options are applied after the parent client's options
// (if there is one), and before any request-specific options.
func NewSessionFileService(opts ...option.RequestOption) (r *SessionFileService) {
	r = &SessionFileService{}
	r.Options = opts
	return
}

// List all files from the session in descending order.
func (r *SessionFileService) List(ctx context.Context, sessionID string, opts ...option.RequestOption) (res *Fileslist, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/files", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Delete a file from a session
func (r *SessionFileService) Delete(ctx context.Context, sessionID string, path_ string, opts ...option.RequestOption) (err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "*/*")}, opts...)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return err
	}
	if path_ == "" {
		err = errors.New("missing required path parameter")
		return err
	}
	path := fmt.Sprintf("v1/sessions/%s/files/%s", url.PathEscape(sessionID), url.PathEscape(path_))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, nil, nil, opts...)
	return err
}

// Delete all files from a session
func (r *SessionFileService) DeleteAll(ctx context.Context, sessionID string, opts ...option.RequestOption) (err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "*/*")}, opts...)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return err
	}
	path := fmt.Sprintf("v1/sessions/%s/files", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodDelete, path, nil, nil, opts...)
	return err
}

// Download a file from a session
func (r *SessionFileService) Download(ctx context.Context, sessionID string, path_ string, opts ...option.RequestOption) (res *http.Response, err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "application/octet-stream")}, opts...)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	if path_ == "" {
		err = errors.New("missing required path parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/files/%s", url.PathEscape(sessionID), url.PathEscape(path_))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Download all files from the session as a zip archive.
func (r *SessionFileService) DownloadArchive(ctx context.Context, sessionID string, opts ...option.RequestOption) (res *http.Response, err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "application/zip")}, opts...)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/files.zip", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Uploads a file to a session via `multipart/form-data` with a `file` field
// that accepts either binary data or a URL string to download from, and an
// optional `path` field for the file storage path.
func (r *SessionFileService) Upload(ctx context.Context, sessionID string, body SessionFileUploadParams, opts ...option.RequestOption) (res *File, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/files", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type SessionFileUploadParams struct {
	// The file to upload (binary) or URL string to download from
	File param.Field[io.Reader] `json:"file" api:"required" format:"binary"`
	// Path to the file in the storage system
	Path param.Field[string] `json:"path"`
}

func (r SessionFileUploadParams) MarshalMultipart() (data []byte, contentType string, err error) {
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
