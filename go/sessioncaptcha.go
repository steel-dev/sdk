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
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// SessionCaptchaService contains methods and other services that help with
// interacting with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewSessionCaptchaService] method instead.
type SessionCaptchaService struct {
	Options []option.RequestOption
}

// NewSessionCaptchaService generates a new service that applies the given
// options to each request. These options are applied after the parent client's
// options (if there is one), and before any request-specific options.
func NewSessionCaptchaService(opts ...option.RequestOption) (r *SessionCaptchaService) {
	r = &SessionCaptchaService{}
	r.Options = opts
	return
}

// Solves captcha(s) for the session. If pageId, url, or taskId is provided,
// solves that specific captcha. If no parameters are provided, solves all
// detected captchas. Use this when autoCaptchaSolving is disabled in
// stealthConfig.
func (r *SessionCaptchaService) Solve(ctx context.Context, sessionID string, body SessionCaptchaSolveParams, opts ...option.RequestOption) (res *SessionCaptchaSolveResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/captchas/solve", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type SessionCaptchaSolveParams struct {
	// The page ID where the captcha is located
	PageID param.Field[string] `json:"pageId"`
	// The task ID of the specific captcha to solve
	TaskID param.Field[string] `json:"taskId"`
	// The URL where the captcha is located
	URL param.Field[string] `json:"url"`
}

func (r SessionCaptchaSolveParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCaptchaSolveResponse struct {
	// Whether the action was successful
	Success bool `json:"success" api:"required"`
	// Response message
	Message string                          `json:"message"`
	JSON    sessionCaptchaSolveResponseJSON `json:"-"`
}

// sessionCaptchaSolveResponseJSON contains the JSON metadata for the struct
// [SessionCaptchaSolveResponse]
type sessionCaptchaSolveResponseJSON struct {
	Success     apijson.Field
	Message     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCaptchaSolveResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCaptchaSolveResponseJSON) RawJSON() string {
	return r.raw
}

// Solves an image captcha using XPath selectors
func (r *SessionCaptchaService) SolveImage(ctx context.Context, sessionID string, body SessionCaptchaSolveImageParams, opts ...option.RequestOption) (res *SessionCaptchaSolveImageResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/captchas/solve-image", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type SessionCaptchaSolveImageParams struct {
	// XPath to the captcha image element
	ImageXPath param.Field[string] `json:"imageXPath" api:"required"`
	// XPath to the captcha input element
	InputXPath param.Field[string] `json:"inputXPath" api:"required"`
	// URL where the captcha is located. Defaults to the current page URL
	URL param.Field[string] `json:"url"`
}

func (r SessionCaptchaSolveImageParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCaptchaSolveImageResponse struct {
	// Whether the action was successful
	Success bool `json:"success" api:"required"`
	// Response message
	Message string                               `json:"message"`
	JSON    sessionCaptchaSolveImageResponseJSON `json:"-"`
}

// sessionCaptchaSolveImageResponseJSON contains the JSON metadata for the
// struct [SessionCaptchaSolveImageResponse]
type sessionCaptchaSolveImageResponseJSON struct {
	Success     apijson.Field
	Message     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCaptchaSolveImageResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCaptchaSolveImageResponseJSON) RawJSON() string {
	return r.raw
}

// Gets the current captcha status for a session
func (r *SessionCaptchaService) Status(ctx context.Context, sessionID string, opts ...option.RequestOption) (res *[]SessionCaptchaStatusResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/captchas/status", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

type SessionCaptchaStatusResponse struct {
	// Whether a captcha is currently being solved
	IsSolvingCaptcha bool `json:"isSolvingCaptcha" api:"required"`
	// The page ID where the captcha is located
	PageID string `json:"pageId" api:"required"`
	// Array of captcha tasks
	Tasks []interface{} `json:"tasks" api:"required"`
	// The URL where the captcha is located
	URL string `json:"url" api:"required"`
	// Timestamp when the state was created
	Created float64 `json:"created"`
	// Timestamp when the state was last updated
	LastUpdated float64                          `json:"lastUpdated"`
	JSON        sessionCaptchaStatusResponseJSON `json:"-"`
}

// sessionCaptchaStatusResponseJSON contains the JSON metadata for the struct
// [SessionCaptchaStatusResponse]
type sessionCaptchaStatusResponseJSON struct {
	IsSolvingCaptcha apijson.Field
	PageID           apijson.Field
	Tasks            apijson.Field
	URL              apijson.Field
	Created          apijson.Field
	LastUpdated      apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *SessionCaptchaStatusResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCaptchaStatusResponseJSON) RawJSON() string {
	return r.raw
}
