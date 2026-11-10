// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
	"errors"
	"fmt"
	"net/http"
	"net/url"
	"reflect"
	"slices"
	"time"

	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/apiquery"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
	"github.com/steel-dev/sdk/go/packages/pagination"
	"github.com/tidwall/gjson"
)

// SessionService contains methods and other services that help with interacting
// with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewSessionService] method instead.
type SessionService struct {
	Options           []option.RequestOption
	Captchas          *SessionCaptchaService
	CommandExecutions *SessionCommandExecutionService
	Files             *SessionFileService
}

// NewSessionService generates a new service that applies the given options to
// each request. These options are applied after the parent client's options (if
// there is one), and before any request-specific options.
func NewSessionService(opts ...option.RequestOption) (r *SessionService) {
	r = &SessionService{}
	r.Options = opts
	r.Captchas = NewSessionCaptchaService(opts...)
	r.CommandExecutions = NewSessionCommandExecutionService(opts...)
	r.Files = NewSessionFileService(opts...)
	return
}

// Creates a new session with the provided configuration.
func (r *SessionService) New(ctx context.Context, body SessionNewParams, opts ...option.RequestOption) (res *Session, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/sessions"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type SessionNewParams struct {
	// Block ads in the browser session. Default is false.
	BlockAds param.Field[bool] `json:"blockAds"`
	// PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY
	// AN EXPERIMENTAL FEATURE.
	CaCertificates param.Field[[]string] `json:"caCertificates"`
	// Number of sessions to create concurrently (check your plan limit)
	Concurrency param.Field[int64] `json:"concurrency"`
	// Configuration for session credentials
	Credentials param.Field[SessionNewParamsCredentials] `json:"credentials"`
	// Configuration for the debug URL and session viewer. Controls interaction
	// capabilities, cursor visibility, and other debug-related settings.
	DebugConfig param.Field[SessionNewParamsDebugConfig] `json:"debugConfig"`
	// Device configuration for the session. Specify 'mobile' for mobile device
	// fingerprints and configurations.
	DeviceConfig param.Field[SessionNewParamsDeviceConfig] `json:"deviceConfig"`
	// Viewport and browser window dimensions for the session. Mobile sessions
	// require dimensions of at least 508x1074; smaller mobile dimensions are
	// rejected with a 400 response.
	Dimensions param.Field[SessionNewParamsDimensions] `json:"dimensions"`
	// Enable experimental features for the session.
	ExperimentalFeatures param.Field[[]string] `json:"experimentalFeatures"`
	// Array of extension IDs to install in the session. Use ['all_ext'] to install
	// all uploaded extensions.
	ExtensionIDs param.Field[[]string] `json:"extensionIds"`
	// Launch the browser in fullscreen mode, covering the full screen with no
	// Chrome UI. Default is false.
	Fullscreen param.Field[bool] `json:"fullscreen"`
	// Enable headless browser mode (disable Headful mode)
	Headless param.Field[bool] `json:"headless"`
	// Inactivity timeout in milliseconds. When set, the session is released if no
	// CDP command or remote input is received for this duration, even if `timeout`
	// has not yet elapsed. Note that `timeout` remains the hard cap on session
	// lifetime: if `inactivityTimeout` is greater than or equal to the effective
	// `timeout`, it has no effect since `timeout` always elapses first. Omit to
	// disable.
	InactivityTimeout param.Field[int64] `json:"inactivityTimeout"`
	// Deprecated. Selenium sessions are no longer supported, and a request that
	// sets this to true is rejected with 400. Connect over CDP with Playwright or
	// Puppeteer instead.
	IsSelenium param.Field[bool] `json:"isSelenium"`
	// Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys
	// must start with a letter, use only letters, digits, underscores, dots or
	// hyphens, and be at most 64 characters; steel_ is reserved. Values are strings
	// (up to 1024 characters, valid Unicode without NUL), finite numbers or
	// booleans. Numbers and booleans are stored as strings, so 3 and "3" are the
	// same value.
	Metadata param.Field[map[string]SessionNewParamsMetadataUnion] `json:"metadata"`
	// The namespace the session should be created against. Defaults to "default".
	Namespace param.Field[string] `json:"namespace"`
	// Enable bandwidth optimizations. Passing true enables all flags (except
	// hosts/patterns). Object allows granular control.
	OptimizeBandwidth param.Field[SessionNewParamsOptimizeBandwidthUnion] `json:"optimizeBandwidth"`
	// This flag will persist the user profile for the session.
	PersistProfile param.Field[bool] `json:"persistProfile"`
	// This flag will set the profile for the session.
	ProfileID param.Field[string] `json:"profileId" format:"uuid"`
	// The project to create the session in. When provided, the session namespace is
	// resolved from the project.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// Custom proxy URL for the browser session. Overrides useProxy, disabling
	// Steel-provided proxies in favor of your specified proxy. Format:
	// http(s)://username:password@hostname:port
	ProxyURL param.Field[string] `json:"proxyUrl"`
	// Record the headful session for playback after it ends. Default is true.
	// Disabling recording does not affect live WHEP viewing.
	Recording param.Field[bool] `json:"recording"`
	// The desired region for the session. Available: us-east, us-west, us-central,
	// eu-west, eu-central, ap-northeast, ap-southeast, sa-east. Legacy codes (iad,
	// lax, ord) are also accepted.
	Region param.Field[interface{}] `json:"region"`
	// Session context data to be used in the created session. Sessions will start
	// with an empty context by default.
	SessionContext param.Field[SessionNewParamsSessionContext] `json:"sessionContext"`
	// Optional custom UUID for the session
	SessionID param.Field[string] `json:"sessionId" format:"uuid"`
	// Enable automatic captcha solving. Default is false.
	SolveCaptcha param.Field[bool] `json:"solveCaptcha"`
	// Stealth configuration for the session
	StealthConfig param.Field[SessionNewParamsStealthConfig] `json:"stealthConfig"`
	// Session timeout duration in milliseconds. Default is 300000 (5 minutes).
	Timeout param.Field[int64] `json:"timeout"`
	// Simple boolean to enable/disable Steel proxies
	UseProxy param.Field[interface{}] `json:"useProxy"`
	// Custom user agent string for the browser session
	UserAgent param.Field[string] `json:"userAgent"`
}

func (r SessionNewParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Configuration for session credentials
type SessionNewParamsCredentials struct {
	AutoSubmit param.Field[bool] `json:"autoSubmit"`
	BlurFields param.Field[bool] `json:"blurFields"`
	// Credential provider to use for runtime credential resolution. Defaults to
	// "steel".
	CredentialProvider param.Field[string] `json:"credentialProvider"`
	ExactOrigin        param.Field[bool]   `json:"exactOrigin"`
	// Optional allowlist of credential origins to inject into this session.
	Origins param.Field[[]string] `json:"origins"`
}

func (r SessionNewParamsCredentials) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Configuration for the debug URL and session viewer. Controls interaction
// capabilities, cursor visibility, and other debug-related settings.
type SessionNewParamsDebugConfig struct {
	// Allow interaction with the browser session via the debug URL viewer. When
	// false, the session viewer will be view-only. Default is true.
	Interactive param.Field[bool] `json:"interactive"`
	// Show the OS-level mouse cursor in the WebRTC stream (headful mode only). When
	// false, the system cursor will not be rendered in the stream. Default is true.
	SystemCursor param.Field[bool] `json:"systemCursor"`
}

func (r SessionNewParamsDebugConfig) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Device configuration for the session. Specify 'mobile' for mobile device
// fingerprints and configurations.
type SessionNewParamsDeviceConfig struct {
	Device param.Field[SessionNewParamsDeviceConfigDevice] `json:"device"`
}

func (r SessionNewParamsDeviceConfig) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionNewParamsDeviceConfigDevice string

const (
	SessionNewParamsDeviceConfigDeviceDesktop SessionNewParamsDeviceConfigDevice = "desktop"
	SessionNewParamsDeviceConfigDeviceMobile  SessionNewParamsDeviceConfigDevice = "mobile"
)

func (r SessionNewParamsDeviceConfigDevice) IsKnown() bool {
	switch r {
	case SessionNewParamsDeviceConfigDeviceDesktop, SessionNewParamsDeviceConfigDeviceMobile:
		return true
	}
	return false
}

// Viewport and browser window dimensions for the session. Mobile sessions
// require dimensions of at least 508x1074; smaller mobile dimensions are
// rejected with a 400 response.
type SessionNewParamsDimensions struct {
	// Height of the session
	Height param.Field[int64] `json:"height" api:"required"`
	// Width of the session
	Width param.Field[int64] `json:"width" api:"required"`
}

func (r SessionNewParamsDimensions) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys
// must start with a letter, use only letters, digits, underscores, dots or
// hyphens, and be at most 64 characters; steel_ is reserved. Values are strings
// (up to 1024 characters, valid Unicode without NUL), finite numbers or
// booleans. Numbers and booleans are stored as strings, so 3 and "3" are the
// same value.
// Satisfied by [shared.UnionString], [shared.UnionFloat], [shared.UnionBool].
type SessionNewParamsMetadataUnion interface {
	ImplementsSessionNewParamsMetadataUnion()
}

// Enable bandwidth optimizations. Passing true enables all flags (except
// hosts/patterns). Object allows granular control.
// Satisfied by [shared.UnionBool],
// [SessionNewParamsOptimizeBandwidthUnionMember1].
type SessionNewParamsOptimizeBandwidthUnion interface {
	ImplementsSessionNewParamsOptimizeBandwidthUnion()
}

type SessionNewParamsOptimizeBandwidthUnionMember1 struct {
	BlockHosts       param.Field[[]string] `json:"blockHosts"`
	BlockImages      param.Field[bool]     `json:"blockImages"`
	BlockMedia       param.Field[bool]     `json:"blockMedia"`
	BlockStylesheets param.Field[bool]     `json:"blockStylesheets"`
	BlockURLPatterns param.Field[[]string] `json:"blockUrlPatterns"`
}

func (r SessionNewParamsOptimizeBandwidthUnionMember1) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

func (r SessionNewParamsOptimizeBandwidthUnionMember1) ImplementsSessionNewParamsOptimizeBandwidthUnion() {
}

// Session context data to be used in the created session. Sessions will start
// with an empty context by default.
type SessionNewParamsSessionContext struct {
	// Cookies to initialize in the session
	Cookies param.Field[[]SessionNewParamsSessionContextCookie] `json:"cookies"`
	// Domain-specific indexedDB items to initialize in the session
	IndexedDB param.Field[map[string][]SessionNewParamsSessionContextIndexedDB] `json:"indexedDB"`
	// Domain-specific localStorage items to initialize in the session
	LocalStorage param.Field[map[string]map[string]string] `json:"localStorage"`
	// Domain-specific sessionStorage items to initialize in the session
	SessionStorage param.Field[map[string]map[string]string] `json:"sessionStorage"`
}

func (r SessionNewParamsSessionContext) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Cookies to initialize in the session
type SessionNewParamsSessionContextCookie struct {
	// The name of the cookie
	Name param.Field[string] `json:"name" api:"required"`
	// The value of the cookie
	Value param.Field[string] `json:"value" api:"required"`
	// The domain of the cookie
	Domain param.Field[string] `json:"domain"`
	// The expiration date of the cookie
	Expires param.Field[float64] `json:"expires"`
	// Whether the cookie is HTTP only
	HTTPOnly param.Field[bool] `json:"httpOnly"`
	// The partition key of the cookie
	PartitionKey param.Field[SessionNewParamsSessionContextCookiePartitionKey] `json:"partitionKey"`
	// The path of the cookie
	Path param.Field[string] `json:"path"`
	// The priority of the cookie
	Priority param.Field[SessionNewParamsSessionContextCookiePriority] `json:"priority"`
	// Whether the cookie is a same party cookie
	SameParty param.Field[bool] `json:"sameParty"`
	// The same site attribute of the cookie
	SameSite param.Field[SessionNewParamsSessionContextCookieSameSite] `json:"sameSite"`
	// Whether the cookie is secure
	Secure param.Field[bool] `json:"secure"`
	// Whether the cookie is a session cookie
	Session param.Field[bool] `json:"session"`
	// The size of the cookie
	Size param.Field[float64] `json:"size"`
	// The source port of the cookie
	SourcePort param.Field[float64] `json:"sourcePort"`
	// The source scheme of the cookie
	SourceScheme param.Field[SessionNewParamsSessionContextCookieSourceScheme] `json:"sourceScheme"`
	// The URL of the cookie
	URL param.Field[string] `json:"url"`
}

func (r SessionNewParamsSessionContextCookie) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// The partition key of the cookie
type SessionNewParamsSessionContextCookiePartitionKey struct {
	// Indicates if the cookie has any ancestors that are cross-site to the
	// topLevelSite.
	HasCrossSiteAncestor param.Field[bool] `json:"hasCrossSiteAncestor" api:"required"`
	// The site of the top-level URL the browser was visiting at the start of the
	// request to the endpoint that set the cookie.
	TopLevelSite param.Field[string] `json:"topLevelSite" api:"required"`
}

func (r SessionNewParamsSessionContextCookiePartitionKey) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// The priority of the cookie
type SessionNewParamsSessionContextCookiePriority string

const (
	SessionNewParamsSessionContextCookiePriorityLow    SessionNewParamsSessionContextCookiePriority = "Low"
	SessionNewParamsSessionContextCookiePriorityMedium SessionNewParamsSessionContextCookiePriority = "Medium"
	SessionNewParamsSessionContextCookiePriorityHigh   SessionNewParamsSessionContextCookiePriority = "High"
)

func (r SessionNewParamsSessionContextCookiePriority) IsKnown() bool {
	switch r {
	case SessionNewParamsSessionContextCookiePriorityLow, SessionNewParamsSessionContextCookiePriorityMedium, SessionNewParamsSessionContextCookiePriorityHigh:
		return true
	}
	return false
}

// The same site attribute of the cookie
type SessionNewParamsSessionContextCookieSameSite string

const (
	SessionNewParamsSessionContextCookieSameSiteStrict SessionNewParamsSessionContextCookieSameSite = "Strict"
	SessionNewParamsSessionContextCookieSameSiteLax    SessionNewParamsSessionContextCookieSameSite = "Lax"
	SessionNewParamsSessionContextCookieSameSiteNone   SessionNewParamsSessionContextCookieSameSite = "None"
)

func (r SessionNewParamsSessionContextCookieSameSite) IsKnown() bool {
	switch r {
	case SessionNewParamsSessionContextCookieSameSiteStrict, SessionNewParamsSessionContextCookieSameSiteLax, SessionNewParamsSessionContextCookieSameSiteNone:
		return true
	}
	return false
}

// The source scheme of the cookie
type SessionNewParamsSessionContextCookieSourceScheme string

const (
	SessionNewParamsSessionContextCookieSourceSchemeUnset     SessionNewParamsSessionContextCookieSourceScheme = "Unset"
	SessionNewParamsSessionContextCookieSourceSchemeNonSecure SessionNewParamsSessionContextCookieSourceScheme = "NonSecure"
	SessionNewParamsSessionContextCookieSourceSchemeSecure    SessionNewParamsSessionContextCookieSourceScheme = "Secure"
)

func (r SessionNewParamsSessionContextCookieSourceScheme) IsKnown() bool {
	switch r {
	case SessionNewParamsSessionContextCookieSourceSchemeUnset, SessionNewParamsSessionContextCookieSourceSchemeNonSecure, SessionNewParamsSessionContextCookieSourceSchemeSecure:
		return true
	}
	return false
}

// Domain-specific indexedDB items to initialize in the session
type SessionNewParamsSessionContextIndexedDB struct {
	ID   param.Field[float64]                                       `json:"id" api:"required"`
	Data param.Field[[]SessionNewParamsSessionContextIndexedDBData] `json:"data" api:"required"`
	Name param.Field[string]                                        `json:"name" api:"required"`
}

func (r SessionNewParamsSessionContextIndexedDB) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionNewParamsSessionContextIndexedDBData struct {
	ID      param.Field[float64]                                             `json:"id" api:"required"`
	Name    param.Field[string]                                              `json:"name" api:"required"`
	Records param.Field[[]SessionNewParamsSessionContextIndexedDBDataRecord] `json:"records" api:"required"`
}

func (r SessionNewParamsSessionContextIndexedDBData) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionNewParamsSessionContextIndexedDBDataRecord struct {
	Key       param.Field[interface{}]                                                 `json:"key" api:"required"`
	Value     param.Field[interface{}]                                                 `json:"value" api:"required"`
	BlobFiles param.Field[[]SessionNewParamsSessionContextIndexedDBDataRecordBlobFile] `json:"blobFiles"`
}

func (r SessionNewParamsSessionContextIndexedDBDataRecord) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionNewParamsSessionContextIndexedDBDataRecordBlobFile struct {
	BlobNumber   param.Field[float64]   `json:"blobNumber" api:"required"`
	MimeType     param.Field[string]    `json:"mimeType" api:"required"`
	Size         param.Field[float64]   `json:"size" api:"required"`
	Filename     param.Field[string]    `json:"filename"`
	LastModified param.Field[time.Time] `json:"lastModified" format:"date-time"`
	Path         param.Field[string]    `json:"path"`
}

func (r SessionNewParamsSessionContextIndexedDBDataRecordBlobFile) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Stealth configuration for the session
type SessionNewParamsStealthConfig struct {
	// When true, captchas will be automatically solved when detected. When false,
	// use the solve endpoints to manually initiate solving.
	AutoCaptchaSolving param.Field[bool] `json:"autoCaptchaSolving"`
	// This flag will make the browser act more human-like by moving the mouse in a
	// more natural way.
	HumanizeInteractions param.Field[bool] `json:"humanizeInteractions"`
	// This flag will skip the fingerprint generation for the session.
	SkipFingerprintInjection param.Field[bool] `json:"skipFingerprintInjection"`
}

func (r SessionNewParamsStealthConfig) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Retrieves details of a specific session by ID.
func (r *SessionService) Get(ctx context.Context, id string, opts ...option.RequestOption) (res *Session, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Fetches all active sessions for the current organization.
func (r *SessionService) List(ctx context.Context, query SessionListParams, opts ...option.RequestOption) (res *pagination.SessionsCursor[SessionslistSession], err error) {
	var raw *http.Response
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithResponseInto(&raw)}, opts...)
	path := "v1/sessions"
	cfg, err := requestconfig.NewRequestConfig(ctx, http.MethodGet, path, query, &res, opts...)
	if err != nil {
		return nil, err
	}
	err = cfg.Execute()
	if err != nil {
		return nil, err
	}
	res.SetPageConfig(cfg, raw)
	return res, nil
}

// Fetches all active sessions for the current organization.
func (r *SessionService) ListAutoPaging(ctx context.Context, query SessionListParams, opts ...option.RequestOption) *pagination.SessionsCursorAutoPager[SessionslistSession] {
	return pagination.NewSessionsCursorAutoPager(r.List(ctx, query, opts...))
}

type SessionListParams struct {
	// Only sessions created at or after this ISO 8601 timestamp, e.g.
	// 2026-10-01T00:00:00Z.
	CreatedAfter param.Field[time.Time] `query:"createdAfter" format:"date-time"`
	// Only sessions created before this ISO 8601 timestamp.
	CreatedBefore param.Field[time.Time] `query:"createdBefore" format:"date-time"`
	// Cursor ID for pagination
	CursorID param.Field[string] `query:"cursorId"`
	// Number of sessions to return. Default is 50, max is 100.
	Limit param.Field[int64] `query:"limit"`
	// Exact-match metadata filters, e.g. metadata[environment]=production. All
	// entries must match. Values match the stored string exactly; numbers and
	// booleans are stored as strings, so metadata[attempt]=3 matches attempt sent
	// as 3 or "3".
	Metadata param.Field[map[string]string] `query:"metadata"`
	// Filter sessions by project
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
	// Filter released sessions by why they ended. Live sessions have no release
	// reason and are excluded whenever this is set. Without an explicit status,
	// matching failed sessions are included.
	ReleaseReason param.Field[SessionListParamsReleaseReason] `query:"releaseReason"`
	// Signals the session must carry; repeat the parameter to require several. All
	// must match. Derived signals are finalized at release.
	Signal param.Field[[]SessionListParamsSignal] `query:"signal"`
	// Filter sessions by current status
	Status param.Field[SessionListParamsStatus] `query:"status"`
}

// URLQuery serializes [SessionListParams]'s query parameters as `url.Values`.
func (r SessionListParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Filter released sessions by why they ended. Live sessions have no release
// reason and are excluded whenever this is set. Without an explicit status,
// matching failed sessions are included.
type SessionListParamsReleaseReason string

const (
	SessionListParamsReleaseReasonUserRequested     SessionListParamsReleaseReason = "user_requested"
	SessionListParamsReleaseReasonTimeout           SessionListParamsReleaseReason = "timeout"
	SessionListParamsReleaseReasonInactivityTimeout SessionListParamsReleaseReason = "inactivity_timeout"
	SessionListParamsReleaseReasonCreationTimeout   SessionListParamsReleaseReason = "creation_timeout"
	SessionListParamsReleaseReasonStartupFailed     SessionListParamsReleaseReason = "startup_failed"
	SessionListParamsReleaseReasonBrowserClosed     SessionListParamsReleaseReason = "browser_closed"
	SessionListParamsReleaseReasonBrowserCrashed    SessionListParamsReleaseReason = "browser_crashed"
)

func (r SessionListParamsReleaseReason) IsKnown() bool {
	switch r {
	case SessionListParamsReleaseReasonUserRequested, SessionListParamsReleaseReasonTimeout, SessionListParamsReleaseReasonInactivityTimeout, SessionListParamsReleaseReasonCreationTimeout, SessionListParamsReleaseReasonStartupFailed, SessionListParamsReleaseReasonBrowserClosed, SessionListParamsReleaseReasonBrowserCrashed:
		return true
	}
	return false
}

// Signals the session must carry; repeat the parameter to require several. All
// must match. Derived signals are finalized at release.
type SessionListParamsSignal string

const (
	SessionListParamsSignalCaptchaEncountered     SessionListParamsSignal = "captcha_encountered"
	SessionListParamsSignalProxyConnectionFailed  SessionListParamsSignal = "proxy_connection_failed"
	SessionListParamsSignalSessionTimeoutExceeded SessionListParamsSignal = "session_timeout_exceeded"
	SessionListParamsSignalBotDetectionTriggered  SessionListParamsSignal = "bot_detection_triggered"
	SessionListParamsSignalContextRestored        SessionListParamsSignal = "context_restored"
)

func (r SessionListParamsSignal) IsKnown() bool {
	switch r {
	case SessionListParamsSignalCaptchaEncountered, SessionListParamsSignalProxyConnectionFailed, SessionListParamsSignalSessionTimeoutExceeded, SessionListParamsSignalBotDetectionTriggered, SessionListParamsSignalContextRestored:
		return true
	}
	return false
}

// Filter sessions by current status
type SessionListParamsStatus string

const (
	SessionListParamsStatusLive     SessionListParamsStatus = "live"
	SessionListParamsStatusReleased SessionListParamsStatus = "released"
	SessionListParamsStatusFailed   SessionListParamsStatus = "failed"
)

func (r SessionListParamsStatus) IsKnown() bool {
	switch r {
	case SessionListParamsStatusLive, SessionListParamsStatusReleased, SessionListParamsStatusFailed:
		return true
	}
	return false
}

type SessionslistSession struct {
	// Unique identifier for the session
	ID string `json:"id" api:"required" format:"uuid"`
	// Browser mode used for this session.
	BrowserMode SessionslistSessionBrowserMode `json:"browserMode" api:"required"`
	// Timestamp when the session started
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// Amount of credits consumed by the session
	CreditsUsed int64 `json:"creditsUsed" api:"required"`
	// URL for debugging the session
	DebugURL string `json:"debugUrl" api:"required"`
	// Viewport and browser window dimensions for the session
	Dimensions SessionslistSessionDimensions `json:"dimensions" api:"required"`
	// Duration of the session in milliseconds
	Duration int64 `json:"duration" api:"required"`
	// Number of events processed in the session
	EventCount int64 `json:"eventCount" api:"required"`
	// Bandwidth optimizations that were applied to the session.
	OptimizeBandwidth SessionslistSessionOptimizeBandwidth `json:"optimizeBandwidth" api:"required"`
	// Amount of data transmitted through the proxy
	ProxyBytesUsed int64 `json:"proxyBytesUsed" api:"required"`
	// Source of the proxy used for the session
	ProxySource SessionslistSessionProxySource `json:"proxySource" api:"required,nullable"`
	// URL to view session details
	SessionViewerURL string `json:"sessionViewerUrl" api:"required"`
	// Signals observed during the session. Filled in within a few seconds after the
	// session ends, and empty until then. An empty list does not mean nothing
	// happened: detection is heuristic and coverage is incomplete.
	Signals []SessionslistSessionSignal `json:"signals" api:"required"`
	// Status of the session
	Status SessionslistSessionStatus `json:"status" api:"required"`
	// Session timeout duration in milliseconds
	Timeout int64 `json:"timeout" api:"required"`
	// URL for the session's WebSocket connection
	WebsocketURL string `json:"websocketUrl" api:"required"`
	// Configuration for the debug URL and session viewer. Controls interaction
	// capabilities and cursor visibility.
	DebugConfig SessionslistSessionDebugConfig `json:"debugConfig"`
	// Device configuration for the session
	DeviceConfig SessionslistSessionDeviceConfig `json:"deviceConfig"`
	// Launch the browser in fullscreen mode, covering the full screen with no
	// Chrome UI.
	Fullscreen bool `json:"fullscreen"`
	// Indicates if the session is headless or headful
	Headless bool `json:"headless"`
	// Inactivity timeout in milliseconds, if one was set when the session was
	// created
	InactivityTimeout int64 `json:"inactivityTimeout"`
	// Indicates if Selenium is used in the session
	IsSelenium bool `json:"isSelenium"`
	// Customer-defined flat metadata. Values are always strings: numbers and
	// booleans sent on write come back in their string form.
	Metadata map[string]string `json:"metadata"`
	// This flag will persist the profile for the session.
	PersistProfile bool `json:"persistProfile"`
	// The ID of the profile associated with the session
	ProfileID string `json:"profileId" format:"uuid"`
	// The project associated with the session
	ProjectID string `json:"projectId" api:"nullable" format:"uuid"`
	// The region where the session was created.
	Region string `json:"region"`
	// Why the session reached a terminal state. Null while the session is live, or
	// when the reason is unknown (e.g. sessions created before this was tracked).
	// One of: user_requested (released via the API/SDK), timeout (hard `timeout`
	// elapsed), inactivity_timeout (no activity for the configured window),
	// creation_timeout (never started in time), startup_failed (could not be
	// dispatched), browser_closed (the browser or agent closed itself — not a
	// crash), browser_crashed (the browser crashed or its machine became
	// unresponsive).
	ReleaseReason SessionslistSessionReleaseReason `json:"releaseReason" api:"nullable"`
	// Indicates if captcha solving is enabled
	SolveCaptcha bool `json:"solveCaptcha"`
	// Stealth configuration for the session
	StealthConfig SessionslistSessionStealthConfig `json:"stealthConfig"`
	// User agent string used in the session
	UserAgent string                  `json:"userAgent"`
	JSON      sessionslistSessionJSON `json:"-"`
}

// sessionslistSessionJSON contains the JSON metadata for the struct
// [SessionslistSession]
type sessionslistSessionJSON struct {
	ID                apijson.Field
	BrowserMode       apijson.Field
	CreatedAt         apijson.Field
	CreditsUsed       apijson.Field
	DebugURL          apijson.Field
	Dimensions        apijson.Field
	Duration          apijson.Field
	EventCount        apijson.Field
	OptimizeBandwidth apijson.Field
	ProxyBytesUsed    apijson.Field
	ProxySource       apijson.Field
	SessionViewerURL  apijson.Field
	Signals           apijson.Field
	Status            apijson.Field
	Timeout           apijson.Field
	WebsocketURL      apijson.Field
	DebugConfig       apijson.Field
	DeviceConfig      apijson.Field
	Fullscreen        apijson.Field
	Headless          apijson.Field
	InactivityTimeout apijson.Field
	IsSelenium        apijson.Field
	Metadata          apijson.Field
	PersistProfile    apijson.Field
	ProfileID         apijson.Field
	ProjectID         apijson.Field
	Region            apijson.Field
	ReleaseReason     apijson.Field
	SolveCaptcha      apijson.Field
	StealthConfig     apijson.Field
	UserAgent         apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *SessionslistSession) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionslistSessionJSON) RawJSON() string {
	return r.raw
}

// Browser mode used for this session.
type SessionslistSessionBrowserMode string

const (
	SessionslistSessionBrowserModeStealth  SessionslistSessionBrowserMode = "stealth"
	SessionslistSessionBrowserModeStandard SessionslistSessionBrowserMode = "standard"
	SessionslistSessionBrowserModeUnknown  SessionslistSessionBrowserMode = "unknown"
)

func (r SessionslistSessionBrowserMode) IsKnown() bool {
	switch r {
	case SessionslistSessionBrowserModeStealth, SessionslistSessionBrowserModeStandard, SessionslistSessionBrowserModeUnknown:
		return true
	}
	return false
}

// Viewport and browser window dimensions for the session
type SessionslistSessionDimensions struct {
	// Height of the browser window
	Height int64 `json:"height" api:"required"`
	// Width of the browser window
	Width int64                             `json:"width" api:"required"`
	JSON  sessionslistSessionDimensionsJSON `json:"-"`
}

// sessionslistSessionDimensionsJSON contains the JSON metadata for the struct
// [SessionslistSessionDimensions]
type sessionslistSessionDimensionsJSON struct {
	Height      apijson.Field
	Width       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionslistSessionDimensions) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionslistSessionDimensionsJSON) RawJSON() string {
	return r.raw
}

// Bandwidth optimizations that were applied to the session.
type SessionslistSessionOptimizeBandwidth struct {
	BlockHosts       []string                                 `json:"blockHosts"`
	BlockImages      bool                                     `json:"blockImages"`
	BlockMedia       bool                                     `json:"blockMedia"`
	BlockStylesheets bool                                     `json:"blockStylesheets"`
	BlockURLPatterns []string                                 `json:"blockUrlPatterns"`
	JSON             sessionslistSessionOptimizeBandwidthJSON `json:"-"`
}

// sessionslistSessionOptimizeBandwidthJSON contains the JSON metadata for the
// struct [SessionslistSessionOptimizeBandwidth]
type sessionslistSessionOptimizeBandwidthJSON struct {
	BlockHosts       apijson.Field
	BlockImages      apijson.Field
	BlockMedia       apijson.Field
	BlockStylesheets apijson.Field
	BlockURLPatterns apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *SessionslistSessionOptimizeBandwidth) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionslistSessionOptimizeBandwidthJSON) RawJSON() string {
	return r.raw
}

// Source of the proxy used for the session
type SessionslistSessionProxySource string

const (
	SessionslistSessionProxySourceSteel    SessionslistSessionProxySource = "steel"
	SessionslistSessionProxySourceExternal SessionslistSessionProxySource = "external"
)

func (r SessionslistSessionProxySource) IsKnown() bool {
	switch r {
	case SessionslistSessionProxySourceSteel, SessionslistSessionProxySourceExternal:
		return true
	}
	return false
}

// Signals observed during the session. Filled in within a few seconds after the
// session ends, and empty until then. An empty list does not mean nothing
// happened: detection is heuristic and coverage is incomplete.
type SessionslistSessionSignal string

const (
	SessionslistSessionSignalCaptchaEncountered     SessionslistSessionSignal = "captcha_encountered"
	SessionslistSessionSignalProxyConnectionFailed  SessionslistSessionSignal = "proxy_connection_failed"
	SessionslistSessionSignalSessionTimeoutExceeded SessionslistSessionSignal = "session_timeout_exceeded"
	SessionslistSessionSignalBotDetectionTriggered  SessionslistSessionSignal = "bot_detection_triggered"
	SessionslistSessionSignalContextRestored        SessionslistSessionSignal = "context_restored"
)

func (r SessionslistSessionSignal) IsKnown() bool {
	switch r {
	case SessionslistSessionSignalCaptchaEncountered, SessionslistSessionSignalProxyConnectionFailed, SessionslistSessionSignalSessionTimeoutExceeded, SessionslistSessionSignalBotDetectionTriggered, SessionslistSessionSignalContextRestored:
		return true
	}
	return false
}

// Status of the session
type SessionslistSessionStatus string

const (
	SessionslistSessionStatusLive     SessionslistSessionStatus = "live"
	SessionslistSessionStatusReleased SessionslistSessionStatus = "released"
	SessionslistSessionStatusFailed   SessionslistSessionStatus = "failed"
)

func (r SessionslistSessionStatus) IsKnown() bool {
	switch r {
	case SessionslistSessionStatusLive, SessionslistSessionStatusReleased, SessionslistSessionStatusFailed:
		return true
	}
	return false
}

// Configuration for the debug URL and session viewer. Controls interaction
// capabilities and cursor visibility.
type SessionslistSessionDebugConfig struct {
	// Whether interaction is allowed via the debug URL viewer. When false, the
	// session viewer is view-only.
	Interactive bool `json:"interactive"`
	// Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode
	// only).
	SystemCursor bool                               `json:"systemCursor"`
	JSON         sessionslistSessionDebugConfigJSON `json:"-"`
}

// sessionslistSessionDebugConfigJSON contains the JSON metadata for the struct
// [SessionslistSessionDebugConfig]
type sessionslistSessionDebugConfigJSON struct {
	Interactive  apijson.Field
	SystemCursor apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *SessionslistSessionDebugConfig) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionslistSessionDebugConfigJSON) RawJSON() string {
	return r.raw
}

// Device configuration for the session
type SessionslistSessionDeviceConfig struct {
	Device SessionslistSessionDeviceConfigDevice `json:"device"`
	JSON   sessionslistSessionDeviceConfigJSON   `json:"-"`
}

// sessionslistSessionDeviceConfigJSON contains the JSON metadata for the struct
// [SessionslistSessionDeviceConfig]
type sessionslistSessionDeviceConfigJSON struct {
	Device      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionslistSessionDeviceConfig) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionslistSessionDeviceConfigJSON) RawJSON() string {
	return r.raw
}

type SessionslistSessionDeviceConfigDevice string

const (
	SessionslistSessionDeviceConfigDeviceDesktop SessionslistSessionDeviceConfigDevice = "desktop"
	SessionslistSessionDeviceConfigDeviceMobile  SessionslistSessionDeviceConfigDevice = "mobile"
)

func (r SessionslistSessionDeviceConfigDevice) IsKnown() bool {
	switch r {
	case SessionslistSessionDeviceConfigDeviceDesktop, SessionslistSessionDeviceConfigDeviceMobile:
		return true
	}
	return false
}

// Why the session reached a terminal state. Null while the session is live, or
// when the reason is unknown (e.g. sessions created before this was tracked).
// One of: user_requested (released via the API/SDK), timeout (hard `timeout`
// elapsed), inactivity_timeout (no activity for the configured window),
// creation_timeout (never started in time), startup_failed (could not be
// dispatched), browser_closed (the browser or agent closed itself — not a
// crash), browser_crashed (the browser crashed or its machine became
// unresponsive).
type SessionslistSessionReleaseReason string

const (
	SessionslistSessionReleaseReasonUserRequested     SessionslistSessionReleaseReason = "user_requested"
	SessionslistSessionReleaseReasonTimeout           SessionslistSessionReleaseReason = "timeout"
	SessionslistSessionReleaseReasonInactivityTimeout SessionslistSessionReleaseReason = "inactivity_timeout"
	SessionslistSessionReleaseReasonCreationTimeout   SessionslistSessionReleaseReason = "creation_timeout"
	SessionslistSessionReleaseReasonStartupFailed     SessionslistSessionReleaseReason = "startup_failed"
	SessionslistSessionReleaseReasonBrowserClosed     SessionslistSessionReleaseReason = "browser_closed"
	SessionslistSessionReleaseReasonBrowserCrashed    SessionslistSessionReleaseReason = "browser_crashed"
)

func (r SessionslistSessionReleaseReason) IsKnown() bool {
	switch r {
	case SessionslistSessionReleaseReasonUserRequested, SessionslistSessionReleaseReasonTimeout, SessionslistSessionReleaseReasonInactivityTimeout, SessionslistSessionReleaseReasonCreationTimeout, SessionslistSessionReleaseReasonStartupFailed, SessionslistSessionReleaseReasonBrowserClosed, SessionslistSessionReleaseReasonBrowserCrashed:
		return true
	}
	return false
}

// Stealth configuration for the session
type SessionslistSessionStealthConfig struct {
	// When true, captchas will be automatically solved when detected. When false,
	// use the solve endpoints to manually initiate solving.
	AutoCaptchaSolving bool `json:"autoCaptchaSolving"`
	// This flag will make the browser act more human-like by moving the mouse in a
	// more natural way
	HumanizeInteractions bool `json:"humanizeInteractions"`
	// This flag will skip the fingerprint generation for the session.
	SkipFingerprintInjection bool                                 `json:"skipFingerprintInjection"`
	JSON                     sessionslistSessionStealthConfigJSON `json:"-"`
}

// sessionslistSessionStealthConfigJSON contains the JSON metadata for the
// struct [SessionslistSessionStealthConfig]
type sessionslistSessionStealthConfigJSON struct {
	AutoCaptchaSolving       apijson.Field
	HumanizeInteractions     apijson.Field
	SkipFingerprintInjection apijson.Field
	raw                      string
	ExtraFields              map[string]apijson.Field
}

func (r *SessionslistSessionStealthConfig) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionslistSessionStealthConfigJSON) RawJSON() string {
	return r.raw
}

// Query the semantic agent trace timeline for a session: a coalesced,
// replay-oriented record of user/agent interactions (clicks, typing, scrolls,
// drags, navigations) with target metadata (role, accessible name, selectors).
// Returns an empty list for sessions that predate browser-interaction capture.
func (r *SessionService) AgentTraces(ctx context.Context, id string, query SessionAgentTracesParams, opts ...option.RequestOption) (res *SessionAgentTracesResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/agent-traces", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type SessionAgentTracesParams struct {
	EndTime    param.Field[time.Time] `query:"endTime" format:"date-time"`
	EventTypes param.Field[[]string]  `query:"eventTypes"`
	Limit      param.Field[int64]     `query:"limit"`
	Namespace  param.Field[string]    `query:"namespace"`
	Offset     param.Field[int64]     `query:"offset"`
	StartTime  param.Field[time.Time] `query:"startTime" format:"date-time"`
}

// URLQuery serializes [SessionAgentTracesParams]'s query parameters as `url.Values`.
func (r SessionAgentTracesParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type SessionAgentTracesResponse struct {
	Events  []map[string]interface{}       `json:"events" api:"required"`
	HasMore bool                           `json:"hasMore" api:"required"`
	Total   float64                        `json:"total" api:"required"`
	JSON    sessionAgentTracesResponseJSON `json:"-"`
}

// sessionAgentTracesResponseJSON contains the JSON metadata for the struct
// [SessionAgentTracesResponse]
type sessionAgentTracesResponseJSON struct {
	Events      apijson.Field
	HasMore     apijson.Field
	Total       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionAgentTracesResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionAgentTracesResponseJSON) RawJSON() string {
	return r.raw
}

// Drives the session's browser with high-level commands such as snapshot,
// click, and fill. Commands run in order and stop at the first failure. Send a
// random Idempotency-Key header to make retries safe.
func (r *SessionService) Commands(ctx context.Context, sessionID string, params SessionCommandsParams, opts ...option.RequestOption) (res *SessionCommandsResponse, err error) {
	if params.IdempotencyKey.Present {
		opts = append(opts, option.WithHeader("idempotency-key", fmt.Sprintf("%v", params.IdempotencyKey)))
	}
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/commands", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, params, &res, opts...)
	return res, err
}

type SessionCommandsParams struct {
	// One command, or an array of up to 50. They run in order and stop at the first
	// failure. Commands that already ran are not undone.
	Commands param.Field[SessionCommandsParamsCommandsUnion] `json:"commands" api:"required"`
	// The next value from your last response. A retry with the same after and
	// commands runs only once.
	After param.Field[string] `json:"after"`
	// Returns a snapshot, or a diff against the last one, after the commands.
	Observe param.Field[SessionCommandsParamsObserve] `json:"observe"`
	// A random value. A retry with the same key returns the stored result.
	IdempotencyKey param.Field[string] `header:"idempotency-key"`
}

func (r SessionCommandsParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// One command, or an array of up to 50. They run in order and stop at the first
// failure. Commands that already ran are not undone.
// Satisfied by [SessionCommandsParamsCommandsNavigateCommand],
// [SessionCommandsParamsCommandsBackCommand],
// [SessionCommandsParamsCommandsForwardCommand],
// [SessionCommandsParamsCommandsReloadCommand],
// [SessionCommandsParamsCommandsSnapshotCommand],
// [SessionCommandsParamsCommandsClickCommand],
// [SessionCommandsParamsCommandsDblclickCommand],
// [SessionCommandsParamsCommandsHoverCommand],
// [SessionCommandsParamsCommandsFocusCommand],
// [SessionCommandsParamsCommandsTypeCommand],
// [SessionCommandsParamsCommandsFillCommand],
// [SessionCommandsParamsCommandsPressCommand],
// [SessionCommandsParamsCommandsKeydownCommand],
// [SessionCommandsParamsCommandsKeyupCommand],
// [SessionCommandsParamsCommandsMouseCommand],
// [SessionCommandsParamsCommandsSelectCommand],
// [SessionCommandsParamsCommandsCheckCommand],
// [SessionCommandsParamsCommandsUploadCommand],
// [SessionCommandsParamsCommandsDragCommand],
// [SessionCommandsParamsCommandsUncheckCommand],
// [SessionCommandsParamsCommandsGetCommand],
// [SessionCommandsParamsCommandsIsCommand],
// [SessionCommandsParamsCommandsWaitCommand],
// [SessionCommandsParamsCommandsScreenshotCommand],
// [SessionCommandsParamsCommandsEvalCommand],
// [SessionCommandsParamsCommandsPdfCommand],
// [SessionCommandsParamsCommandsScrollCommand],
// [SessionCommandsParamsCommandsFindCommand],
// [SessionCommandsParamsCommandsTabCommand],
// [SessionCommandsParamsCommandsUnionMember29].
type SessionCommandsParamsCommandsUnion interface {
	ImplementsSessionCommandsParamsCommandsUnion()
}

// Opens a URL in the tab. Only http, https, and about:blank. A URL without a
// scheme gets https://.
type SessionCommandsParamsCommandsNavigateCommand struct {
	Command param.Field[SessionCommandsParamsCommandsNavigateCommandCommand] `json:"command" api:"required"`
	URL     param.Field[string]                                              `json:"url" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsNavigateCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsNavigateCommandCommand string

const (
	SessionCommandsParamsCommandsNavigateCommandCommandNavigate SessionCommandsParamsCommandsNavigateCommandCommand = "navigate"
)

func (r SessionCommandsParamsCommandsNavigateCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsNavigateCommandCommandNavigate:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsNavigateCommand) ImplementsSessionCommandsParamsCommandsUnion() {
}

// Goes back one history entry.
type SessionCommandsParamsCommandsBackCommand struct {
	Command param.Field[SessionCommandsParamsCommandsBackCommandCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsBackCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsBackCommandCommand string

const (
	SessionCommandsParamsCommandsBackCommandCommandBack SessionCommandsParamsCommandsBackCommandCommand = "back"
)

func (r SessionCommandsParamsCommandsBackCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsBackCommandCommandBack:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsBackCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Goes forward one history entry.
type SessionCommandsParamsCommandsForwardCommand struct {
	Command param.Field[SessionCommandsParamsCommandsForwardCommandCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsForwardCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsForwardCommandCommand string

const (
	SessionCommandsParamsCommandsForwardCommandCommandForward SessionCommandsParamsCommandsForwardCommandCommand = "forward"
)

func (r SessionCommandsParamsCommandsForwardCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsForwardCommandCommandForward:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsForwardCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Reloads the tab.
type SessionCommandsParamsCommandsReloadCommand struct {
	Command param.Field[SessionCommandsParamsCommandsReloadCommandCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsReloadCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsReloadCommandCommand string

const (
	SessionCommandsParamsCommandsReloadCommandCommandReload SessionCommandsParamsCommandsReloadCommandCommand = "reload"
)

func (r SessionCommandsParamsCommandsReloadCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsReloadCommandCommandReload:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsReloadCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Returns the accessibility tree of the page, with a ref for each element you
// can act on.
type SessionCommandsParamsCommandsSnapshotCommand struct {
	Command param.Field[SessionCommandsParamsCommandsSnapshotCommandCommand] `json:"command" api:"required"`
	// Only lines with refs or values, and their ancestors.
	Compact param.Field[bool] `json:"compact"`
	// Only lines up to this depth.
	Depth param.Field[int64] `json:"depth"`
	// Only lines with refs.
	Interactive param.Field[bool] `json:"interactive"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsSnapshotCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsSnapshotCommandCommand string

const (
	SessionCommandsParamsCommandsSnapshotCommandCommandSnapshot SessionCommandsParamsCommandsSnapshotCommandCommand = "snapshot"
)

func (r SessionCommandsParamsCommandsSnapshotCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsSnapshotCommandCommandSnapshot:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsSnapshotCommand) ImplementsSessionCommandsParamsCommandsUnion() {
}

// Clicks the element with the mouse.
type SessionCommandsParamsCommandsClickCommand struct {
	Command param.Field[SessionCommandsParamsCommandsClickCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsClickCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsClickCommandCommand string

const (
	SessionCommandsParamsCommandsClickCommandCommandClick SessionCommandsParamsCommandsClickCommandCommand = "click"
)

func (r SessionCommandsParamsCommandsClickCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsClickCommandCommandClick:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsClickCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Double-clicks the element with the mouse.
type SessionCommandsParamsCommandsDblclickCommand struct {
	Command param.Field[SessionCommandsParamsCommandsDblclickCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsDblclickCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsDblclickCommandCommand string

const (
	SessionCommandsParamsCommandsDblclickCommandCommandDblclick SessionCommandsParamsCommandsDblclickCommandCommand = "dblclick"
)

func (r SessionCommandsParamsCommandsDblclickCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsDblclickCommandCommandDblclick:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsDblclickCommand) ImplementsSessionCommandsParamsCommandsUnion() {
}

// Moves the mouse onto the element.
type SessionCommandsParamsCommandsHoverCommand struct {
	Command param.Field[SessionCommandsParamsCommandsHoverCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsHoverCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsHoverCommandCommand string

const (
	SessionCommandsParamsCommandsHoverCommandCommandHover SessionCommandsParamsCommandsHoverCommandCommand = "hover"
)

func (r SessionCommandsParamsCommandsHoverCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsHoverCommandCommandHover:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsHoverCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Focuses the element.
type SessionCommandsParamsCommandsFocusCommand struct {
	Command param.Field[SessionCommandsParamsCommandsFocusCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsFocusCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsFocusCommandCommand string

const (
	SessionCommandsParamsCommandsFocusCommandCommandFocus SessionCommandsParamsCommandsFocusCommandCommand = "focus"
)

func (r SessionCommandsParamsCommandsFocusCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsFocusCommandCommandFocus:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsFocusCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Types text one key press at a time, after the current content.
type SessionCommandsParamsCommandsTypeCommand struct {
	Command param.Field[SessionCommandsParamsCommandsTypeCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	Text   param.Field[string]             `json:"text" api:"required"`
	// Replace the current content.
	Clear param.Field[bool] `json:"clear"`
	// Milliseconds to wait between key presses.
	Delay param.Field[int64] `json:"delay"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsTypeCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsTypeCommandCommand string

const (
	SessionCommandsParamsCommandsTypeCommandCommandType SessionCommandsParamsCommandsTypeCommandCommand = "type"
)

func (r SessionCommandsParamsCommandsTypeCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsTypeCommandCommandType:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsTypeCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Replaces the content of a field in one step, without key presses.
type SessionCommandsParamsCommandsFillCommand struct {
	Command param.Field[SessionCommandsParamsCommandsFillCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	Value  param.Field[string]             `json:"value" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsFillCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsFillCommandCommand string

const (
	SessionCommandsParamsCommandsFillCommandCommandFill SessionCommandsParamsCommandsFillCommandCommand = "fill"
)

func (r SessionCommandsParamsCommandsFillCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsFillCommandCommandFill:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsFillCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Presses a key, such as Enter, or a combination such as Control+a.
type SessionCommandsParamsCommandsPressCommand struct {
	Command param.Field[SessionCommandsParamsCommandsPressCommandCommand] `json:"command" api:"required"`
	Key     param.Field[string]                                           `json:"key" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r SessionCommandsParamsCommandsPressCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsPressCommandCommand string

const (
	SessionCommandsParamsCommandsPressCommandCommandPress SessionCommandsParamsCommandsPressCommandCommand = "press"
)

func (r SessionCommandsParamsCommandsPressCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsPressCommandCommandPress:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsPressCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Holds a key down until its keyup.
type SessionCommandsParamsCommandsKeydownCommand struct {
	Command param.Field[SessionCommandsParamsCommandsKeydownCommandCommand] `json:"command" api:"required"`
	Key     param.Field[string]                                             `json:"key" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsKeydownCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsKeydownCommandCommand string

const (
	SessionCommandsParamsCommandsKeydownCommandCommandKeydown SessionCommandsParamsCommandsKeydownCommandCommand = "keydown"
)

func (r SessionCommandsParamsCommandsKeydownCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsKeydownCommandCommandKeydown:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsKeydownCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Lets go of a key held with keydown.
type SessionCommandsParamsCommandsKeyupCommand struct {
	Command param.Field[SessionCommandsParamsCommandsKeyupCommandCommand] `json:"command" api:"required"`
	Key     param.Field[string]                                           `json:"key" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsKeyupCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsKeyupCommandCommand string

const (
	SessionCommandsParamsCommandsKeyupCommandCommandKeyup SessionCommandsParamsCommandsKeyupCommandCommand = "keyup"
)

func (r SessionCommandsParamsCommandsKeyupCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsKeyupCommandCommandKeyup:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsKeyupCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Mouse input at a point in CSS pixels of the visible part of the page, for
// pages without useful elements such as a canvas.
type SessionCommandsParamsCommandsMouseCommand struct {
	Action  param.Field[SessionCommandsParamsCommandsMouseCommandAction]  `json:"action" api:"required"`
	Command param.Field[SessionCommandsParamsCommandsMouseCommandCommand] `json:"command" api:"required"`
	Button  param.Field[SessionCommandsParamsCommandsMouseCommandButton]  `json:"button"`
	Clicks  param.Field[int64]                                            `json:"clicks"`
	DeltaX  param.Field[float64]                                          `json:"deltaX"`
	DeltaY  param.Field[float64]                                          `json:"deltaY"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string]  `json:"tab"`
	X   param.Field[float64] `json:"x"`
	Y   param.Field[float64] `json:"y"`
}

func (r SessionCommandsParamsCommandsMouseCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsMouseCommandAction string

const (
	SessionCommandsParamsCommandsMouseCommandActionMove  SessionCommandsParamsCommandsMouseCommandAction = "move"
	SessionCommandsParamsCommandsMouseCommandActionDown  SessionCommandsParamsCommandsMouseCommandAction = "down"
	SessionCommandsParamsCommandsMouseCommandActionUp    SessionCommandsParamsCommandsMouseCommandAction = "up"
	SessionCommandsParamsCommandsMouseCommandActionClick SessionCommandsParamsCommandsMouseCommandAction = "click"
	SessionCommandsParamsCommandsMouseCommandActionWheel SessionCommandsParamsCommandsMouseCommandAction = "wheel"
)

func (r SessionCommandsParamsCommandsMouseCommandAction) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsMouseCommandActionMove, SessionCommandsParamsCommandsMouseCommandActionDown, SessionCommandsParamsCommandsMouseCommandActionUp, SessionCommandsParamsCommandsMouseCommandActionClick, SessionCommandsParamsCommandsMouseCommandActionWheel:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsMouseCommandCommand string

const (
	SessionCommandsParamsCommandsMouseCommandCommandMouse SessionCommandsParamsCommandsMouseCommandCommand = "mouse"
)

func (r SessionCommandsParamsCommandsMouseCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsMouseCommandCommandMouse:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsMouseCommandButton string

const (
	SessionCommandsParamsCommandsMouseCommandButtonLeft   SessionCommandsParamsCommandsMouseCommandButton = "left"
	SessionCommandsParamsCommandsMouseCommandButtonRight  SessionCommandsParamsCommandsMouseCommandButton = "right"
	SessionCommandsParamsCommandsMouseCommandButtonMiddle SessionCommandsParamsCommandsMouseCommandButton = "middle"
)

func (r SessionCommandsParamsCommandsMouseCommandButton) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsMouseCommandButtonLeft, SessionCommandsParamsCommandsMouseCommandButtonRight, SessionCommandsParamsCommandsMouseCommandButtonMiddle:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsMouseCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Selects options. Matches by value, then by label.
type SessionCommandsParamsCommandsSelectCommand struct {
	Command param.Field[SessionCommandsParamsCommandsSelectCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam]                                    `json:"target" api:"required"`
	Values param.Field[SessionCommandsParamsCommandsSelectCommandValuesUnion] `json:"values" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsSelectCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsSelectCommandCommand string

const (
	SessionCommandsParamsCommandsSelectCommandCommandSelect SessionCommandsParamsCommandsSelectCommandCommand = "select"
)

func (r SessionCommandsParamsCommandsSelectCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsSelectCommandCommandSelect:
		return true
	}
	return false
}

// Satisfied by [shared.UnionString],
// [SessionCommandsParamsCommandsSelectCommandValuesUnionMember1].
type SessionCommandsParamsCommandsSelectCommandValuesUnion interface {
	ImplementsSessionCommandsParamsCommandsSelectCommandValuesUnion()
}

type SessionCommandsParamsCommandsSelectCommandValuesUnionMember1 []string

func (r SessionCommandsParamsCommandsSelectCommandValuesUnionMember1) ImplementsSessionCommandsParamsCommandsSelectCommandValuesUnion() {
}

func (r SessionCommandsParamsCommandsSelectCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Checks a checkbox, radio button, switch, or toggle button.
type SessionCommandsParamsCommandsCheckCommand struct {
	Command param.Field[SessionCommandsParamsCommandsCheckCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsCheckCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsCheckCommandCommand string

const (
	SessionCommandsParamsCommandsCheckCommandCommandCheck SessionCommandsParamsCommandsCheckCommandCommand = "check"
)

func (r SessionCommandsParamsCommandsCheckCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsCheckCommandCommandCheck:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsCheckCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Sets the files of a file input. Upload the files to the session first.
type SessionCommandsParamsCommandsUploadCommand struct {
	Command param.Field[SessionCommandsParamsCommandsUploadCommandCommand]    `json:"command" api:"required"`
	Files   param.Field[SessionCommandsParamsCommandsUploadCommandFilesUnion] `json:"files" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsUploadCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsUploadCommandCommand string

const (
	SessionCommandsParamsCommandsUploadCommandCommandUpload SessionCommandsParamsCommandsUploadCommandCommand = "upload"
)

func (r SessionCommandsParamsCommandsUploadCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsUploadCommandCommandUpload:
		return true
	}
	return false
}

// Satisfied by [shared.UnionString],
// [SessionCommandsParamsCommandsUploadCommandFilesUnionMember1].
type SessionCommandsParamsCommandsUploadCommandFilesUnion interface {
	ImplementsSessionCommandsParamsCommandsUploadCommandFilesUnion()
}

type SessionCommandsParamsCommandsUploadCommandFilesUnionMember1 []string

func (r SessionCommandsParamsCommandsUploadCommandFilesUnionMember1) ImplementsSessionCommandsParamsCommandsUploadCommandFilesUnion() {
}

func (r SessionCommandsParamsCommandsUploadCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Drags one element onto another with the mouse.
type SessionCommandsParamsCommandsDragCommand struct {
	Command param.Field[SessionCommandsParamsCommandsDragCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Source param.Field[CommandTargetParam] `json:"source" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsDragCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsDragCommandCommand string

const (
	SessionCommandsParamsCommandsDragCommandCommandDrag SessionCommandsParamsCommandsDragCommandCommand = "drag"
)

func (r SessionCommandsParamsCommandsDragCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsDragCommandCommandDrag:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsDragCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Unchecks a checkbox, switch, or toggle button.
type SessionCommandsParamsCommandsUncheckCommand struct {
	Command param.Field[SessionCommandsParamsCommandsUncheckCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsUncheckCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsUncheckCommandCommand string

const (
	SessionCommandsParamsCommandsUncheckCommandCommandUncheck SessionCommandsParamsCommandsUncheckCommandCommand = "uncheck"
)

func (r SessionCommandsParamsCommandsUncheckCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsUncheckCommandCommandUncheck:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsUncheckCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Reads something from the page or an element.
type SessionCommandsParamsCommandsGetCommand struct {
	Command param.Field[SessionCommandsParamsCommandsGetCommandCommand] `json:"command" api:"required"`
	What    param.Field[SessionCommandsParamsCommandsGetCommandWhat]    `json:"what" api:"required"`
	// The attribute to read with attr.
	Attribute param.Field[string] `json:"attribute"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r SessionCommandsParamsCommandsGetCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsGetCommandCommand string

const (
	SessionCommandsParamsCommandsGetCommandCommandGet SessionCommandsParamsCommandsGetCommandCommand = "get"
)

func (r SessionCommandsParamsCommandsGetCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsGetCommandCommandGet:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsGetCommandWhat string

const (
	SessionCommandsParamsCommandsGetCommandWhatText     SessionCommandsParamsCommandsGetCommandWhat = "text"
	SessionCommandsParamsCommandsGetCommandWhatHTML     SessionCommandsParamsCommandsGetCommandWhat = "html"
	SessionCommandsParamsCommandsGetCommandWhatMarkdown SessionCommandsParamsCommandsGetCommandWhat = "markdown"
	SessionCommandsParamsCommandsGetCommandWhatValue    SessionCommandsParamsCommandsGetCommandWhat = "value"
	SessionCommandsParamsCommandsGetCommandWhatAttr     SessionCommandsParamsCommandsGetCommandWhat = "attr"
	SessionCommandsParamsCommandsGetCommandWhatTitle    SessionCommandsParamsCommandsGetCommandWhat = "title"
	SessionCommandsParamsCommandsGetCommandWhatURL      SessionCommandsParamsCommandsGetCommandWhat = "url"
	SessionCommandsParamsCommandsGetCommandWhatCount    SessionCommandsParamsCommandsGetCommandWhat = "count"
	SessionCommandsParamsCommandsGetCommandWhatBox      SessionCommandsParamsCommandsGetCommandWhat = "box"
	SessionCommandsParamsCommandsGetCommandWhatLocator  SessionCommandsParamsCommandsGetCommandWhat = "locator"
)

func (r SessionCommandsParamsCommandsGetCommandWhat) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsGetCommandWhatText, SessionCommandsParamsCommandsGetCommandWhatHTML, SessionCommandsParamsCommandsGetCommandWhatMarkdown, SessionCommandsParamsCommandsGetCommandWhatValue, SessionCommandsParamsCommandsGetCommandWhatAttr, SessionCommandsParamsCommandsGetCommandWhatTitle, SessionCommandsParamsCommandsGetCommandWhatURL, SessionCommandsParamsCommandsGetCommandWhatCount, SessionCommandsParamsCommandsGetCommandWhatBox, SessionCommandsParamsCommandsGetCommandWhatLocator:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsGetCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Checks the state of an element.
type SessionCommandsParamsCommandsIsCommand struct {
	Command param.Field[SessionCommandsParamsCommandsIsCommandCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam]                         `json:"target" api:"required"`
	What   param.Field[SessionCommandsParamsCommandsIsCommandWhat] `json:"what" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsIsCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsIsCommandCommand string

const (
	SessionCommandsParamsCommandsIsCommandCommandIs SessionCommandsParamsCommandsIsCommandCommand = "is"
)

func (r SessionCommandsParamsCommandsIsCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsIsCommandCommandIs:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsIsCommandWhat string

const (
	SessionCommandsParamsCommandsIsCommandWhatVisible SessionCommandsParamsCommandsIsCommandWhat = "visible"
	SessionCommandsParamsCommandsIsCommandWhatEnabled SessionCommandsParamsCommandsIsCommandWhat = "enabled"
	SessionCommandsParamsCommandsIsCommandWhatChecked SessionCommandsParamsCommandsIsCommandWhat = "checked"
)

func (r SessionCommandsParamsCommandsIsCommandWhat) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsIsCommandWhatVisible, SessionCommandsParamsCommandsIsCommandWhatEnabled, SessionCommandsParamsCommandsIsCommandWhatChecked:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsIsCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Waits for one of an element, text, a URL, a load state, a download, or a
// fixed time.
type SessionCommandsParamsCommandsWaitCommand struct {
	Command param.Field[SessionCommandsParamsCommandsWaitCommandCommand] `json:"command" api:"required"`
	// Waits for the next download to finish.
	Download param.Field[SessionCommandsParamsCommandsWaitCommandDownload] `json:"download"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam]                             `json:"frame"`
	Load  param.Field[SessionCommandsParamsCommandsWaitCommandLoad]  `json:"load"`
	Ms    param.Field[int64]                                         `json:"ms"`
	State param.Field[SessionCommandsParamsCommandsWaitCommandState] `json:"state"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
	Text   param.Field[string]             `json:"text"`
	// Milliseconds. 10000 by default.
	Timeout param.Field[int64] `json:"timeout"`
	// A URL pattern. * matches any characters.
	URL param.Field[string] `json:"url"`
}

func (r SessionCommandsParamsCommandsWaitCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsWaitCommandCommand string

const (
	SessionCommandsParamsCommandsWaitCommandCommandWait SessionCommandsParamsCommandsWaitCommandCommand = "wait"
)

func (r SessionCommandsParamsCommandsWaitCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsWaitCommandCommandWait:
		return true
	}
	return false
}

// Waits for the next download to finish.
type SessionCommandsParamsCommandsWaitCommandDownload bool

const (
	SessionCommandsParamsCommandsWaitCommandDownloadTrue SessionCommandsParamsCommandsWaitCommandDownload = true
)

func (r SessionCommandsParamsCommandsWaitCommandDownload) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsWaitCommandDownloadTrue:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsWaitCommandLoad string

const (
	SessionCommandsParamsCommandsWaitCommandLoadLoad             SessionCommandsParamsCommandsWaitCommandLoad = "load"
	SessionCommandsParamsCommandsWaitCommandLoadDomcontentloaded SessionCommandsParamsCommandsWaitCommandLoad = "domcontentloaded"
	SessionCommandsParamsCommandsWaitCommandLoadNetworkidle      SessionCommandsParamsCommandsWaitCommandLoad = "networkidle"
)

func (r SessionCommandsParamsCommandsWaitCommandLoad) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsWaitCommandLoadLoad, SessionCommandsParamsCommandsWaitCommandLoadDomcontentloaded, SessionCommandsParamsCommandsWaitCommandLoadNetworkidle:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsWaitCommandState string

const (
	SessionCommandsParamsCommandsWaitCommandStateVisible  SessionCommandsParamsCommandsWaitCommandState = "visible"
	SessionCommandsParamsCommandsWaitCommandStateHidden   SessionCommandsParamsCommandsWaitCommandState = "hidden"
	SessionCommandsParamsCommandsWaitCommandStateAttached SessionCommandsParamsCommandsWaitCommandState = "attached"
	SessionCommandsParamsCommandsWaitCommandStateDetached SessionCommandsParamsCommandsWaitCommandState = "detached"
)

func (r SessionCommandsParamsCommandsWaitCommandState) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsWaitCommandStateVisible, SessionCommandsParamsCommandsWaitCommandStateHidden, SessionCommandsParamsCommandsWaitCommandStateAttached, SessionCommandsParamsCommandsWaitCommandStateDetached:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsWaitCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Captures the page or an element as base64 data.
type SessionCommandsParamsCommandsScreenshotCommand struct {
	Command param.Field[SessionCommandsParamsCommandsScreenshotCommandCommand] `json:"command" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam]                              `json:"expect"`
	Format param.Field[SessionCommandsParamsCommandsScreenshotCommandFormat] `json:"format"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame    param.Field[CommandFrameParam] `json:"frame"`
	FullPage param.Field[bool]              `json:"fullPage"`
	Quality  param.Field[int64]             `json:"quality"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r SessionCommandsParamsCommandsScreenshotCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsScreenshotCommandCommand string

const (
	SessionCommandsParamsCommandsScreenshotCommandCommandScreenshot SessionCommandsParamsCommandsScreenshotCommandCommand = "screenshot"
)

func (r SessionCommandsParamsCommandsScreenshotCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsScreenshotCommandCommandScreenshot:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsScreenshotCommandFormat string

const (
	SessionCommandsParamsCommandsScreenshotCommandFormatPng  SessionCommandsParamsCommandsScreenshotCommandFormat = "png"
	SessionCommandsParamsCommandsScreenshotCommandFormatJpeg SessionCommandsParamsCommandsScreenshotCommandFormat = "jpeg"
	SessionCommandsParamsCommandsScreenshotCommandFormatWebp SessionCommandsParamsCommandsScreenshotCommandFormat = "webp"
)

func (r SessionCommandsParamsCommandsScreenshotCommandFormat) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsScreenshotCommandFormatPng, SessionCommandsParamsCommandsScreenshotCommandFormatJpeg, SessionCommandsParamsCommandsScreenshotCommandFormatWebp:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsScreenshotCommand) ImplementsSessionCommandsParamsCommandsUnion() {
}

// Runs JavaScript in the page and returns the result as JSON.
type SessionCommandsParamsCommandsEvalCommand struct {
	Command param.Field[SessionCommandsParamsCommandsEvalCommandCommand] `json:"command" api:"required"`
	Script  param.Field[string]                                          `json:"script" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// Milliseconds. 10000 by default.
	Timeout param.Field[int64] `json:"timeout"`
}

func (r SessionCommandsParamsCommandsEvalCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsEvalCommandCommand string

const (
	SessionCommandsParamsCommandsEvalCommandCommandEval SessionCommandsParamsCommandsEvalCommandCommand = "eval"
)

func (r SessionCommandsParamsCommandsEvalCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsEvalCommandCommandEval:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsEvalCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Prints the page to a PDF as base64 data.
type SessionCommandsParamsCommandsPdfCommand struct {
	Command         param.Field[SessionCommandsParamsCommandsPdfCommandCommand] `json:"command" api:"required"`
	Landscape       param.Field[bool]                                           `json:"landscape"`
	PrintBackground param.Field[bool]                                           `json:"printBackground"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsPdfCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsPdfCommandCommand string

const (
	SessionCommandsParamsCommandsPdfCommandCommandPdf SessionCommandsParamsCommandsPdfCommandCommand = "pdf"
)

func (r SessionCommandsParamsCommandsPdfCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsPdfCommandCommandPdf:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsPdfCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Scrolls the page, or an element when target is set.
type SessionCommandsParamsCommandsScrollCommand struct {
	Command param.Field[SessionCommandsParamsCommandsScrollCommandCommand] `json:"command" api:"required"`
	// Pixels. 300 by default.
	Amount    param.Field[float64]                                             `json:"amount"`
	Direction param.Field[SessionCommandsParamsCommandsScrollCommandDirection] `json:"direction"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r SessionCommandsParamsCommandsScrollCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsScrollCommandCommand string

const (
	SessionCommandsParamsCommandsScrollCommandCommandScroll SessionCommandsParamsCommandsScrollCommandCommand = "scroll"
)

func (r SessionCommandsParamsCommandsScrollCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsScrollCommandCommandScroll:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsScrollCommandDirection string

const (
	SessionCommandsParamsCommandsScrollCommandDirectionUp    SessionCommandsParamsCommandsScrollCommandDirection = "up"
	SessionCommandsParamsCommandsScrollCommandDirectionDown  SessionCommandsParamsCommandsScrollCommandDirection = "down"
	SessionCommandsParamsCommandsScrollCommandDirectionLeft  SessionCommandsParamsCommandsScrollCommandDirection = "left"
	SessionCommandsParamsCommandsScrollCommandDirectionRight SessionCommandsParamsCommandsScrollCommandDirection = "right"
)

func (r SessionCommandsParamsCommandsScrollCommandDirection) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsScrollCommandDirectionUp, SessionCommandsParamsCommandsScrollCommandDirectionDown, SessionCommandsParamsCommandsScrollCommandDirectionLeft, SessionCommandsParamsCommandsScrollCommandDirectionRight:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsScrollCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Finds elements and returns refs for up to 50 matches. With nth, it returns
// that one match.
type SessionCommandsParamsCommandsFindCommand struct {
	Command param.Field[SessionCommandsParamsCommandsFindCommandCommand] `json:"command" api:"required"`
	// A CSS selector or a locator, not a ref.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsCommandsFindCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsFindCommandCommand string

const (
	SessionCommandsParamsCommandsFindCommandCommandFind SessionCommandsParamsCommandsFindCommandCommand = "find"
)

func (r SessionCommandsParamsCommandsFindCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsFindCommandCommandFind:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsFindCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

// Lists, opens, or closes tabs. The last tab cannot be closed.
type SessionCommandsParamsCommandsTabCommand struct {
	Action  param.Field[SessionCommandsParamsCommandsTabCommandAction]  `json:"action" api:"required"`
	Command param.Field[SessionCommandsParamsCommandsTabCommandCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	URL param.Field[string] `json:"url"`
}

func (r SessionCommandsParamsCommandsTabCommand) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsCommandsTabCommandAction string

const (
	SessionCommandsParamsCommandsTabCommandActionList  SessionCommandsParamsCommandsTabCommandAction = "list"
	SessionCommandsParamsCommandsTabCommandActionNew   SessionCommandsParamsCommandsTabCommandAction = "new"
	SessionCommandsParamsCommandsTabCommandActionClose SessionCommandsParamsCommandsTabCommandAction = "close"
)

func (r SessionCommandsParamsCommandsTabCommandAction) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsTabCommandActionList, SessionCommandsParamsCommandsTabCommandActionNew, SessionCommandsParamsCommandsTabCommandActionClose:
		return true
	}
	return false
}

type SessionCommandsParamsCommandsTabCommandCommand string

const (
	SessionCommandsParamsCommandsTabCommandCommandTab SessionCommandsParamsCommandsTabCommandCommand = "tab"
)

func (r SessionCommandsParamsCommandsTabCommandCommand) IsKnown() bool {
	switch r {
	case SessionCommandsParamsCommandsTabCommandCommandTab:
		return true
	}
	return false
}

func (r SessionCommandsParamsCommandsTabCommand) ImplementsSessionCommandsParamsCommandsUnion() {}

type SessionCommandsParamsCommandsUnionMember29 []CommandParam

func (r SessionCommandsParamsCommandsUnionMember29) ImplementsSessionCommandsParamsCommandsUnion() {}

// Returns a snapshot, or a diff against the last one, after the commands.
type SessionCommandsParamsObserve struct {
	Mode param.Field[SessionCommandsParamsObserveMode] `json:"mode" api:"required"`
	// A snapshot id to diff against.
	Base param.Field[string] `json:"base"`
	// Only lines with refs or values, and their ancestors.
	Compact param.Field[bool] `json:"compact"`
	// Only lines up to this depth.
	Depth param.Field[int64] `json:"depth"`
	// Only lines with refs.
	Interactive param.Field[bool] `json:"interactive"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r SessionCommandsParamsObserve) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionCommandsParamsObserveMode string

const (
	SessionCommandsParamsObserveModeNone     SessionCommandsParamsObserveMode = "none"
	SessionCommandsParamsObserveModeSnapshot SessionCommandsParamsObserveMode = "snapshot"
	SessionCommandsParamsObserveModeDiff     SessionCommandsParamsObserveMode = "diff"
)

func (r SessionCommandsParamsObserveMode) IsKnown() bool {
	switch r {
	case SessionCommandsParamsObserveModeNone, SessionCommandsParamsObserveModeSnapshot, SessionCommandsParamsObserveModeDiff:
		return true
	}
	return false
}

type SessionCommandsResponse struct {
	// The id of this execution, the same as execution.id. Use it to get or cancel
	// the execution.
	ID        string                           `json:"id" api:"required"`
	Execution SessionCommandsResponseExecution `json:"execution" api:"required"`
	// The tab the execution used.
	Page        SessionCommandsResponsePage `json:"page" api:"required,nullable"`
	Results     []CommandResult             `json:"results" api:"required"`
	BodyExpired bool                        `json:"bodyExpired"`
	// A dialog that is open on the tab and blocks it until it closes.
	Dialog CommandDialog `json:"dialog"`
	// Set when the execution ran with after. Whether another execution, a quick
	// action, or a browser restart changed the session since then. Input from CDP
	// clients or the live view does not count.
	EpochChanged bool `json:"epochChanged"`
	// Send it as after in the next request.
	Next    string                         `json:"next"`
	Observe SessionCommandsResponseObserve `json:"observe"`
	JSON    sessionCommandsResponseJSON    `json:"-"`
}

// sessionCommandsResponseJSON contains the JSON metadata for the struct
// [SessionCommandsResponse]
type sessionCommandsResponseJSON struct {
	ID           apijson.Field
	Execution    apijson.Field
	Page         apijson.Field
	Results      apijson.Field
	BodyExpired  apijson.Field
	Dialog       apijson.Field
	EpochChanged apijson.Field
	Next         apijson.Field
	Observe      apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *SessionCommandsResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandsResponseJSON) RawJSON() string {
	return r.raw
}

type SessionCommandsResponseExecution struct {
	ID string `json:"id" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect SessionCommandsResponseExecutionEffect `json:"effect" api:"required"`
	// Commands started.
	Ran      int64                                 `json:"ran" api:"required"`
	Replayed bool                                  `json:"replayed" api:"required"`
	State    SessionCommandsResponseExecutionState `json:"state" api:"required"`
	Error    CommandErrorInfo                      `json:"error"`
	JSON     sessionCommandsResponseExecutionJSON  `json:"-"`
}

// sessionCommandsResponseExecutionJSON contains the JSON metadata for the
// struct [SessionCommandsResponseExecution]
type sessionCommandsResponseExecutionJSON struct {
	ID          apijson.Field
	Effect      apijson.Field
	Ran         apijson.Field
	Replayed    apijson.Field
	State       apijson.Field
	Error       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCommandsResponseExecution) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandsResponseExecutionJSON) RawJSON() string {
	return r.raw
}

// What happened to the page: nothing, a change, or a possible change.
type SessionCommandsResponseExecutionEffect string

const (
	SessionCommandsResponseExecutionEffectNone    SessionCommandsResponseExecutionEffect = "none"
	SessionCommandsResponseExecutionEffectApplied SessionCommandsResponseExecutionEffect = "applied"
	SessionCommandsResponseExecutionEffectUnknown SessionCommandsResponseExecutionEffect = "unknown"
)

func (r SessionCommandsResponseExecutionEffect) IsKnown() bool {
	switch r {
	case SessionCommandsResponseExecutionEffectNone, SessionCommandsResponseExecutionEffectApplied, SessionCommandsResponseExecutionEffectUnknown:
		return true
	}
	return false
}

type SessionCommandsResponseExecutionState string

const (
	SessionCommandsResponseExecutionStateQueued    SessionCommandsResponseExecutionState = "queued"
	SessionCommandsResponseExecutionStateRunning   SessionCommandsResponseExecutionState = "running"
	SessionCommandsResponseExecutionStateCompleted SessionCommandsResponseExecutionState = "completed"
	SessionCommandsResponseExecutionStateFailed    SessionCommandsResponseExecutionState = "failed"
	SessionCommandsResponseExecutionStateCancelled SessionCommandsResponseExecutionState = "cancelled"
	SessionCommandsResponseExecutionStateTimeout   SessionCommandsResponseExecutionState = "timeout"
	SessionCommandsResponseExecutionStateExpired   SessionCommandsResponseExecutionState = "expired"
)

func (r SessionCommandsResponseExecutionState) IsKnown() bool {
	switch r {
	case SessionCommandsResponseExecutionStateQueued, SessionCommandsResponseExecutionStateRunning, SessionCommandsResponseExecutionStateCompleted, SessionCommandsResponseExecutionStateFailed, SessionCommandsResponseExecutionStateCancelled, SessionCommandsResponseExecutionStateTimeout, SessionCommandsResponseExecutionStateExpired:
		return true
	}
	return false
}

// The tab the execution used.
type SessionCommandsResponsePage struct {
	Tab   string                          `json:"tab" api:"required"`
	Title string                          `json:"title" api:"required"`
	URL   string                          `json:"url" api:"required"`
	JSON  sessionCommandsResponsePageJSON `json:"-"`
}

// sessionCommandsResponsePageJSON contains the JSON metadata for the struct
// [SessionCommandsResponsePage]
type sessionCommandsResponsePageJSON struct {
	Tab         apijson.Field
	Title       apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCommandsResponsePage) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandsResponsePageJSON) RawJSON() string {
	return r.raw
}

type SessionCommandsResponseObserve struct {
	Additions  int64                              `json:"additions"`
	Base       string                             `json:"base"`
	Diff       string                             `json:"diff"`
	Error      CommandErrorInfo                   `json:"error"`
	Full       bool                               `json:"full"`
	Removals   int64                              `json:"removals"`
	Snapshot   string                             `json:"snapshot"`
	SnapshotID string                             `json:"snapshotId"`
	JSON       sessionCommandsResponseObserveJSON `json:"-"`
}

// sessionCommandsResponseObserveJSON contains the JSON metadata for the struct
// [SessionCommandsResponseObserve]
type sessionCommandsResponseObserveJSON struct {
	Additions   apijson.Field
	Base        apijson.Field
	Diff        apijson.Field
	Error       apijson.Field
	Full        apijson.Field
	Removals    apijson.Field
	Snapshot    apijson.Field
	SnapshotID  apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCommandsResponseObserve) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandsResponseObserveJSON) RawJSON() string {
	return r.raw
}

// Execute computer actions like mouse movements, clicks, keyboard input, and
// more
func (r *SessionService) Computer(ctx context.Context, sessionID string, body SessionComputerParams, opts ...option.RequestOption) (res *SessionComputerResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/computer", url.PathEscape(sessionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type SessionComputerParams struct {
	Body SessionComputerParamsBodyUnion `json:"body" api:"required"`
}

func (r SessionComputerParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r.Body)
}

// Satisfied by [SessionComputerParamsBodyMoveMouse],
// [SessionComputerParamsBodyClickMouse], [SessionComputerParamsBodyDragMouse],
// [SessionComputerParamsBodyScroll], [SessionComputerParamsBodyPressKey],
// [SessionComputerParamsBodyTypeText], [SessionComputerParamsBodyWait],
// [SessionComputerParamsBodyTakeScreenshot],
// [SessionComputerParamsBodyGetCursorPosition].
type SessionComputerParamsBodyUnion interface {
	ImplementsSessionComputerParamsBodyUnion()
}

type SessionComputerParamsBodyMoveMouse struct {
	Action param.Field[SessionComputerParamsBodyMoveMouseAction] `json:"action" api:"required"`
	// X and Y coordinates [x, y]
	Coordinates param.Field[[]float64] `json:"coordinates" api:"required"`
	// Keys to hold while moving
	HoldKeys param.Field[[]string] `json:"hold_keys"`
	// Whether to take a screenshot after the action
	Screenshot param.Field[bool] `json:"screenshot"`
}

func (r SessionComputerParamsBodyMoveMouse) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyMoveMouseAction string

const (
	SessionComputerParamsBodyMoveMouseActionMoveMouse SessionComputerParamsBodyMoveMouseAction = "move_mouse"
)

func (r SessionComputerParamsBodyMoveMouseAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyMoveMouseActionMoveMouse:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyMoveMouse) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyClickMouse struct {
	Action param.Field[SessionComputerParamsBodyClickMouseAction] `json:"action" api:"required"`
	// Mouse button to click. Defaults to 'left'
	Button param.Field[SessionComputerParamsBodyClickMouseButton] `json:"button"`
	// Type of click (down, up, or click). Defaults to 'click'
	ClickType param.Field[SessionComputerParamsBodyClickMouseClickType] `json:"click_type"`
	// X and Y coordinates [x, y]
	Coordinates param.Field[[]float64] `json:"coordinates"`
	// Keys to hold while clicking
	HoldKeys param.Field[[]string] `json:"hold_keys"`
	// Number of clicks. Defaults to 1
	NumClicks param.Field[float64] `json:"num_clicks"`
	// Whether to take a screenshot after the action
	Screenshot param.Field[bool] `json:"screenshot"`
}

func (r SessionComputerParamsBodyClickMouse) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyClickMouseAction string

const (
	SessionComputerParamsBodyClickMouseActionClickMouse SessionComputerParamsBodyClickMouseAction = "click_mouse"
)

func (r SessionComputerParamsBodyClickMouseAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyClickMouseActionClickMouse:
		return true
	}
	return false
}

// Mouse button to click. Defaults to 'left'
type SessionComputerParamsBodyClickMouseButton string

const (
	SessionComputerParamsBodyClickMouseButtonLeft    SessionComputerParamsBodyClickMouseButton = "left"
	SessionComputerParamsBodyClickMouseButtonRight   SessionComputerParamsBodyClickMouseButton = "right"
	SessionComputerParamsBodyClickMouseButtonMiddle  SessionComputerParamsBodyClickMouseButton = "middle"
	SessionComputerParamsBodyClickMouseButtonBack    SessionComputerParamsBodyClickMouseButton = "back"
	SessionComputerParamsBodyClickMouseButtonForward SessionComputerParamsBodyClickMouseButton = "forward"
)

func (r SessionComputerParamsBodyClickMouseButton) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyClickMouseButtonLeft, SessionComputerParamsBodyClickMouseButtonRight, SessionComputerParamsBodyClickMouseButtonMiddle, SessionComputerParamsBodyClickMouseButtonBack, SessionComputerParamsBodyClickMouseButtonForward:
		return true
	}
	return false
}

// Type of click (down, up, or click). Defaults to 'click'
type SessionComputerParamsBodyClickMouseClickType string

const (
	SessionComputerParamsBodyClickMouseClickTypeDown  SessionComputerParamsBodyClickMouseClickType = "down"
	SessionComputerParamsBodyClickMouseClickTypeUp    SessionComputerParamsBodyClickMouseClickType = "up"
	SessionComputerParamsBodyClickMouseClickTypeClick SessionComputerParamsBodyClickMouseClickType = "click"
)

func (r SessionComputerParamsBodyClickMouseClickType) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyClickMouseClickTypeDown, SessionComputerParamsBodyClickMouseClickTypeUp, SessionComputerParamsBodyClickMouseClickTypeClick:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyClickMouse) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyDragMouse struct {
	Action param.Field[SessionComputerParamsBodyDragMouseAction] `json:"action" api:"required"`
	// Array of [x, y] coordinate pairs
	Path param.Field[[][]float64] `json:"path" api:"required"`
	// Keys to hold while dragging
	HoldKeys param.Field[[]string] `json:"hold_keys"`
	// Whether to take a screenshot after the action
	Screenshot param.Field[bool] `json:"screenshot"`
}

func (r SessionComputerParamsBodyDragMouse) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyDragMouseAction string

const (
	SessionComputerParamsBodyDragMouseActionDragMouse SessionComputerParamsBodyDragMouseAction = "drag_mouse"
)

func (r SessionComputerParamsBodyDragMouseAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyDragMouseActionDragMouse:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyDragMouse) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyScroll struct {
	Action param.Field[SessionComputerParamsBodyScrollAction] `json:"action" api:"required"`
	// X and Y coordinates [x, y]
	Coordinates param.Field[[]float64] `json:"coordinates"`
	// Horizontal scroll amount. Defaults to 0
	DeltaX param.Field[float64] `json:"delta_x"`
	// Vertical scroll amount. Defaults to 0
	DeltaY param.Field[float64] `json:"delta_y"`
	// Keys to hold while scrolling
	HoldKeys param.Field[[]string] `json:"hold_keys"`
	// Whether to take a screenshot after the action
	Screenshot param.Field[bool] `json:"screenshot"`
}

func (r SessionComputerParamsBodyScroll) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyScrollAction string

const (
	SessionComputerParamsBodyScrollActionScroll SessionComputerParamsBodyScrollAction = "scroll"
)

func (r SessionComputerParamsBodyScrollAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyScrollActionScroll:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyScroll) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyPressKey struct {
	Action param.Field[SessionComputerParamsBodyPressKeyAction] `json:"action" api:"required"`
	// Keys to press
	Keys param.Field[[]string] `json:"keys" api:"required"`
	// Duration to hold keys in seconds
	Duration param.Field[float64] `json:"duration"`
	// Whether to take a screenshot after the action
	Screenshot param.Field[bool] `json:"screenshot"`
}

func (r SessionComputerParamsBodyPressKey) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyPressKeyAction string

const (
	SessionComputerParamsBodyPressKeyActionPressKey SessionComputerParamsBodyPressKeyAction = "press_key"
)

func (r SessionComputerParamsBodyPressKeyAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyPressKeyActionPressKey:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyPressKey) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyTypeText struct {
	Action param.Field[SessionComputerParamsBodyTypeTextAction] `json:"action" api:"required"`
	// Text to type
	Text param.Field[string] `json:"text" api:"required"`
	// Keys to hold while typing
	HoldKeys param.Field[[]string] `json:"hold_keys"`
	// Whether to take a screenshot after the action
	Screenshot param.Field[bool] `json:"screenshot"`
}

func (r SessionComputerParamsBodyTypeText) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyTypeTextAction string

const (
	SessionComputerParamsBodyTypeTextActionTypeText SessionComputerParamsBodyTypeTextAction = "type_text"
)

func (r SessionComputerParamsBodyTypeTextAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyTypeTextActionTypeText:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyTypeText) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyWait struct {
	Action param.Field[SessionComputerParamsBodyWaitAction] `json:"action" api:"required"`
	// Duration to wait in seconds
	Duration param.Field[float64] `json:"duration" api:"required"`
	// Whether to take a screenshot after the action
	Screenshot param.Field[bool] `json:"screenshot"`
}

func (r SessionComputerParamsBodyWait) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyWaitAction string

const (
	SessionComputerParamsBodyWaitActionWait SessionComputerParamsBodyWaitAction = "wait"
)

func (r SessionComputerParamsBodyWaitAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyWaitActionWait:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyWait) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyTakeScreenshot struct {
	Action param.Field[SessionComputerParamsBodyTakeScreenshotAction] `json:"action" api:"required"`
}

func (r SessionComputerParamsBodyTakeScreenshot) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyTakeScreenshotAction string

const (
	SessionComputerParamsBodyTakeScreenshotActionTakeScreenshot SessionComputerParamsBodyTakeScreenshotAction = "take_screenshot"
)

func (r SessionComputerParamsBodyTakeScreenshotAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyTakeScreenshotActionTakeScreenshot:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyTakeScreenshot) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerParamsBodyGetCursorPosition struct {
	Action param.Field[SessionComputerParamsBodyGetCursorPositionAction] `json:"action" api:"required"`
}

func (r SessionComputerParamsBodyGetCursorPosition) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type SessionComputerParamsBodyGetCursorPositionAction string

const (
	SessionComputerParamsBodyGetCursorPositionActionGetCursorPosition SessionComputerParamsBodyGetCursorPositionAction = "get_cursor_position"
)

func (r SessionComputerParamsBodyGetCursorPositionAction) IsKnown() bool {
	switch r {
	case SessionComputerParamsBodyGetCursorPositionActionGetCursorPosition:
		return true
	}
	return false
}

func (r SessionComputerParamsBodyGetCursorPosition) ImplementsSessionComputerParamsBodyUnion() {}

type SessionComputerResponse struct {
	// Base64 encoded screenshot if requested
	Base64Image string `json:"base64_image"`
	// Error message if action failed
	Error string `json:"error"`
	// Output message from the action
	Output string `json:"output"`
	// System information
	System string                      `json:"system"`
	JSON   sessionComputerResponseJSON `json:"-"`
}

// sessionComputerResponseJSON contains the JSON metadata for the struct
// [SessionComputerResponse]
type sessionComputerResponseJSON struct {
	Base64Image apijson.Field
	Error       apijson.Field
	Output      apijson.Field
	System      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionComputerResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionComputerResponseJSON) RawJSON() string {
	return r.raw
}

// Fetches the context data of a specific session.
func (r *SessionService) Context(ctx context.Context, id string, opts ...option.RequestOption) (res *SessionContext, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/context", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Returns the finalized cost of a released session as an exact amount in US
// dollars (e.g. 0.031905), suitable for precise billing passthrough.
func (r *SessionService) Cost(ctx context.Context, id string, opts ...option.RequestOption) (res *SessionCostResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/cost", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Session cost breakdown in US dollars
type SessionCostResponse struct {
	// Session ID
	ID string `json:"id" api:"required" format:"uuid"`
	// Currency used for the cost values
	Currency SessionCostResponseCurrency `json:"currency" api:"required"`
	// Exact total session cost in US dollars (e.g. 0.031905), with no rounding to
	// whole cents. Rounded only to micro-dollar precision (6 decimal places) for
	// precise billing passthrough.
	TotalCost float64 `json:"totalCost" api:"required"`
	// Cost unit. Values are expressed in US dollars.
	Unit SessionCostResponseUnit `json:"unit" api:"required"`
	// Billable usage inputs used to calculate the costs
	Usage SessionCostResponseUsage `json:"usage" api:"required"`
	JSON  sessionCostResponseJSON  `json:"-"`
}

// sessionCostResponseJSON contains the JSON metadata for the struct
// [SessionCostResponse]
type sessionCostResponseJSON struct {
	ID          apijson.Field
	Currency    apijson.Field
	TotalCost   apijson.Field
	Unit        apijson.Field
	Usage       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCostResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCostResponseJSON) RawJSON() string {
	return r.raw
}

// Currency used for the cost values
type SessionCostResponseCurrency string

const (
	SessionCostResponseCurrencyUsd SessionCostResponseCurrency = "usd"
)

func (r SessionCostResponseCurrency) IsKnown() bool {
	switch r {
	case SessionCostResponseCurrencyUsd:
		return true
	}
	return false
}

// Cost unit. Values are expressed in US dollars.
type SessionCostResponseUnit string

const (
	SessionCostResponseUnitDollar SessionCostResponseUnit = "dollar"
)

func (r SessionCostResponseUnit) IsKnown() bool {
	switch r {
	case SessionCostResponseUnitDollar:
		return true
	}
	return false
}

// Billable usage inputs used to calculate the costs
type SessionCostResponseUsage struct {
	Browser SessionCostResponseUsageBrowser `json:"browser" api:"required"`
	Captcha SessionCostResponseUsageCaptcha `json:"captcha" api:"required"`
	Proxy   SessionCostResponseUsageProxy   `json:"proxy" api:"required"`
	JSON    sessionCostResponseUsageJSON    `json:"-"`
}

// sessionCostResponseUsageJSON contains the JSON metadata for the struct
// [SessionCostResponseUsage]
type sessionCostResponseUsageJSON struct {
	Browser     apijson.Field
	Captcha     apijson.Field
	Proxy       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCostResponseUsage) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCostResponseUsageJSON) RawJSON() string {
	return r.raw
}

type SessionCostResponseUsageBrowser struct {
	// Billing unit for browser usage
	Unit SessionCostResponseUsageBrowserUnit `json:"unit" api:"required"`
	// Billable browser usage value
	Value float64                             `json:"value" api:"required"`
	JSON  sessionCostResponseUsageBrowserJSON `json:"-"`
}

// sessionCostResponseUsageBrowserJSON contains the JSON metadata for the struct
// [SessionCostResponseUsageBrowser]
type sessionCostResponseUsageBrowserJSON struct {
	Unit        apijson.Field
	Value       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCostResponseUsageBrowser) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCostResponseUsageBrowserJSON) RawJSON() string {
	return r.raw
}

// Billing unit for browser usage
type SessionCostResponseUsageBrowserUnit string

const (
	SessionCostResponseUsageBrowserUnitMinute SessionCostResponseUsageBrowserUnit = "minute"
	SessionCostResponseUsageBrowserUnitSecond SessionCostResponseUsageBrowserUnit = "second"
)

func (r SessionCostResponseUsageBrowserUnit) IsKnown() bool {
	switch r {
	case SessionCostResponseUsageBrowserUnitMinute, SessionCostResponseUsageBrowserUnitSecond:
		return true
	}
	return false
}

type SessionCostResponseUsageCaptcha struct {
	// Billable captcha solves
	Solves int64                               `json:"solves" api:"required"`
	JSON   sessionCostResponseUsageCaptchaJSON `json:"-"`
}

// sessionCostResponseUsageCaptchaJSON contains the JSON metadata for the struct
// [SessionCostResponseUsageCaptcha]
type sessionCostResponseUsageCaptchaJSON struct {
	Solves      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCostResponseUsageCaptcha) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCostResponseUsageCaptchaJSON) RawJSON() string {
	return r.raw
}

type SessionCostResponseUsageProxy struct {
	// Billable Steel proxy bytes for the session
	Bytes int64                             `json:"bytes" api:"required"`
	JSON  sessionCostResponseUsageProxyJSON `json:"-"`
}

// sessionCostResponseUsageProxyJSON contains the JSON metadata for the struct
// [SessionCostResponseUsageProxy]
type sessionCostResponseUsageProxyJSON struct {
	Bytes       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCostResponseUsageProxy) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCostResponseUsageProxyJSON) RawJSON() string {
	return r.raw
}

// This endpoint allows you to get the recorded session events in the RRWeb
// format
func (r *SessionService) Events(ctx context.Context, id string, query SessionEventsParams, opts ...option.RequestOption) (res *[]interface{}, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/events", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type SessionEventsParams struct {
	// Compress the events
	Compressed param.Field[bool] `query:"compressed"`
	// Optional pagination limit
	Limit param.Field[int64] `query:"limit"`
	// Opaque pagination token. Pass the Next-Cursor header value to get the next
	// page.
	Pointer param.Field[string] `query:"pointer"`
}

// URLQuery serializes [SessionEventsParams]'s query parameters as `url.Values`.
func (r SessionEventsParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Get HLS playlist for a session recording using app auth or an RTC token
func (r *SessionService) Hls(ctx context.Context, id string, opts ...option.RequestOption) (res *http.Response, err error) {
	opts = slices.Concat(r.Options, opts)
	opts = append([]option.RequestOption{option.WithHeader("Accept", "application/vnd.apple.mpegurl")}, opts...)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/hls", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Returns the live state of the session, including pages, tabs, and browser
// state
func (r *SessionService) LiveDetails(ctx context.Context, id string, opts ...option.RequestOption) (res *SessionLiveDetailsResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/live-details", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

type SessionLiveDetailsResponse struct {
	Pages                      []SessionLiveDetailsResponsePage `json:"pages" api:"required"`
	SessionViewerFullscreenURL string                           `json:"sessionViewerFullscreenUrl" api:"required"`
	SessionViewerURL           string                           `json:"sessionViewerUrl" api:"required"`
	WsURL                      string                           `json:"wsUrl" api:"required"`
	JSON                       sessionLiveDetailsResponseJSON   `json:"-"`
}

// sessionLiveDetailsResponseJSON contains the JSON metadata for the struct
// [SessionLiveDetailsResponse]
type sessionLiveDetailsResponseJSON struct {
	Pages                      apijson.Field
	SessionViewerFullscreenURL apijson.Field
	SessionViewerURL           apijson.Field
	WsURL                      apijson.Field
	raw                        string
	ExtraFields                map[string]apijson.Field
}

func (r *SessionLiveDetailsResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionLiveDetailsResponseJSON) RawJSON() string {
	return r.raw
}

type SessionLiveDetailsResponsePage struct {
	ID                         string                             `json:"id" api:"required"`
	Favicon                    string                             `json:"favicon" api:"required,nullable"`
	SessionViewerFullscreenURL string                             `json:"sessionViewerFullscreenUrl" api:"required"`
	SessionViewerURL           string                             `json:"sessionViewerUrl" api:"required"`
	Title                      string                             `json:"title" api:"required"`
	URL                        string                             `json:"url" api:"required"`
	JSON                       sessionLiveDetailsResponsePageJSON `json:"-"`
}

// sessionLiveDetailsResponsePageJSON contains the JSON metadata for the struct
// [SessionLiveDetailsResponsePage]
type sessionLiveDetailsResponsePageJSON struct {
	ID                         apijson.Field
	Favicon                    apijson.Field
	SessionViewerFullscreenURL apijson.Field
	SessionViewerURL           apijson.Field
	Title                      apijson.Field
	URL                        apijson.Field
	raw                        string
	ExtraFields                map[string]apijson.Field
}

func (r *SessionLiveDetailsResponsePage) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionLiveDetailsResponsePageJSON) RawJSON() string {
	return r.raw
}

// Query browser event logs for a session
func (r *SessionService) Logs(ctx context.Context, id string, query SessionLogsParams, opts ...option.RequestOption) (res *SessionLogsResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/logs", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type SessionLogsParams struct {
	EndTime    param.Field[time.Time] `query:"endTime" format:"date-time"`
	EventTypes param.Field[[]string]  `query:"eventTypes"`
	Limit      param.Field[int64]     `query:"limit"`
	Namespace  param.Field[string]    `query:"namespace"`
	Offset     param.Field[int64]     `query:"offset"`
	StartTime  param.Field[time.Time] `query:"startTime" format:"date-time"`
}

// URLQuery serializes [SessionLogsParams]'s query parameters as `url.Values`.
func (r SessionLogsParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type SessionLogsResponse struct {
	Events  []SessionLogsResponseEvent `json:"events" api:"required"`
	HasMore bool                       `json:"hasMore" api:"required"`
	Total   float64                    `json:"total" api:"required"`
	JSON    sessionLogsResponseJSON    `json:"-"`
}

// sessionLogsResponseJSON contains the JSON metadata for the struct
// [SessionLogsResponse]
type sessionLogsResponseJSON struct {
	Events      apijson.Field
	HasMore     apijson.Field
	Total       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionLogsResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionLogsResponseJSON) RawJSON() string {
	return r.raw
}

type SessionLogsResponseEvent struct {
	ID        string                       `json:"id" api:"required"`
	Log       string                       `json:"log" api:"required"`
	Timestamp time.Time                    `json:"timestamp" api:"required" format:"date-time"`
	Type      string                       `json:"type" api:"required"`
	JSON      sessionLogsResponseEventJSON `json:"-"`
}

// sessionLogsResponseEventJSON contains the JSON metadata for the struct
// [SessionLogsResponseEvent]
type sessionLogsResponseEventJSON struct {
	ID          apijson.Field
	Log         apijson.Field
	Timestamp   apijson.Field
	Type        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionLogsResponseEvent) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionLogsResponseEventJSON) RawJSON() string {
	return r.raw
}

// Releases a specific session by ID.
func (r *SessionService) Release(ctx context.Context, id string, opts ...option.RequestOption) (res *SessionReleaseResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/release", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, nil, &res, opts...)
	return res, err
}

// Response for releasing a single session.
type SessionReleaseResponse struct {
	// Details about the outcome of the release operation
	Message string `json:"message" api:"required"`
	// Indicates if the session was successfully released
	Success bool                       `json:"success" api:"required"`
	JSON    sessionReleaseResponseJSON `json:"-"`
}

// sessionReleaseResponseJSON contains the JSON metadata for the struct
// [SessionReleaseResponse]
type sessionReleaseResponseJSON struct {
	Message     apijson.Field
	Success     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionReleaseResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionReleaseResponseJSON) RawJSON() string {
	return r.raw
}

// Releases all active sessions for the current organization.
func (r *SessionService) ReleaseAll(ctx context.Context, query SessionReleaseAllParams, opts ...option.RequestOption) (res *SessionReleaseAllResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/sessions/release"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, query, &res, opts...)
	return res, err
}

type SessionReleaseAllParams struct {
	// Release sessions only within this project
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [SessionReleaseAllParams]'s query parameters as `url.Values`.
func (r SessionReleaseAllParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// Response for releasing multiple sessions.
type SessionReleaseAllResponse struct {
	// Details about the outcome of the release operation
	Message string `json:"message" api:"required"`
	// Indicates if the sessions were successfully released
	Success bool                          `json:"success" api:"required"`
	JSON    sessionReleaseAllResponseJSON `json:"-"`
}

// sessionReleaseAllResponseJSON contains the JSON metadata for the struct
// [SessionReleaseAllResponse]
type sessionReleaseAllResponseJSON struct {
	Message     apijson.Field
	Success     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionReleaseAllResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionReleaseAllResponseJSON) RawJSON() string {
	return r.raw
}

// Atomically replace customer metadata. Send an empty object to clear it. Last
// accepted write wins; use PATCH to change individual keys. Subject to session
// organization and project access.
func (r *SessionService) ReplaceMetadata(ctx context.Context, id string, body SessionReplaceMetadataParams, opts ...option.RequestOption) (res *SessionReplaceMetadataResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/metadata", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPut, path, body, &res, opts...)
	return res, err
}

type SessionReplaceMetadataParams struct {
	// Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys
	// must start with a letter, use only letters, digits, underscores, dots or
	// hyphens, and be at most 64 characters; steel_ is reserved. Values are strings
	// (up to 1024 characters, valid Unicode without NUL), finite numbers or
	// booleans. Numbers and booleans are stored as strings, so 3 and "3" are the
	// same value.
	Metadata param.Field[map[string]SessionReplaceMetadataParamsMetadataUnion] `json:"metadata" api:"required"`
}

func (r SessionReplaceMetadataParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys
// must start with a letter, use only letters, digits, underscores, dots or
// hyphens, and be at most 64 characters; steel_ is reserved. Values are strings
// (up to 1024 characters, valid Unicode without NUL), finite numbers or
// booleans. Numbers and booleans are stored as strings, so 3 and "3" are the
// same value.
// Satisfied by [shared.UnionString], [shared.UnionFloat], [shared.UnionBool].
type SessionReplaceMetadataParamsMetadataUnion interface {
	ImplementsSessionReplaceMetadataParamsMetadataUnion()
}

type SessionReplaceMetadataResponse struct {
	// Customer-defined flat metadata. Values are always strings: numbers and
	// booleans sent on write come back in their string form.
	Metadata map[string]string                  `json:"metadata" api:"required"`
	JSON     sessionReplaceMetadataResponseJSON `json:"-"`
}

// sessionReplaceMetadataResponseJSON contains the JSON metadata for the struct
// [SessionReplaceMetadataResponse]
type sessionReplaceMetadataResponseJSON struct {
	Metadata    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionReplaceMetadataResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionReplaceMetadataResponseJSON) RawJSON() string {
	return r.raw
}

// Returns current signal labels and meanings for customer integrations. Uses
// normal Steel API-key authentication. Includes no session data or
// administrative signals. Definitions do not establish that detection ran for a
// particular session.
func (r *SessionService) SignalCatalog(ctx context.Context, opts ...option.RequestOption) (res *SessionSignalCatalogResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/sessions/signals/catalog"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// Current customer-visible signal definitions, not per-session detection
// evidence
type SessionSignalCatalogResponse struct {
	// Interpretation and coverage limitations of session signals
	Semantics string                               `json:"semantics" api:"required"`
	Signals   []SessionSignalCatalogResponseSignal `json:"signals" api:"required"`
	JSON      sessionSignalCatalogResponseJSON     `json:"-"`
}

// sessionSignalCatalogResponseJSON contains the JSON metadata for the struct
// [SessionSignalCatalogResponse]
type sessionSignalCatalogResponseJSON struct {
	Semantics   apijson.Field
	Signals     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionSignalCatalogResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionSignalCatalogResponseJSON) RawJSON() string {
	return r.raw
}

type SessionSignalCatalogResponseSignal struct {
	Description string                                     `json:"description" api:"required"`
	Label       string                                     `json:"label" api:"required"`
	Severity    SessionSignalCatalogResponseSignalSeverity `json:"severity" api:"required"`
	Source      SessionSignalCatalogResponseSignalSource   `json:"source" api:"required"`
	Value       SessionSignalCatalogResponseSignalValue    `json:"value" api:"required"`
	JSON        sessionSignalCatalogResponseSignalJSON     `json:"-"`
}

// sessionSignalCatalogResponseSignalJSON contains the JSON metadata for the
// struct [SessionSignalCatalogResponseSignal]
type sessionSignalCatalogResponseSignalJSON struct {
	Description apijson.Field
	Label       apijson.Field
	Severity    apijson.Field
	Source      apijson.Field
	Value       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionSignalCatalogResponseSignal) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionSignalCatalogResponseSignalJSON) RawJSON() string {
	return r.raw
}

type SessionSignalCatalogResponseSignalSeverity string

const (
	SessionSignalCatalogResponseSignalSeverityInfo    SessionSignalCatalogResponseSignalSeverity = "info"
	SessionSignalCatalogResponseSignalSeverityWarning SessionSignalCatalogResponseSignalSeverity = "warning"
	SessionSignalCatalogResponseSignalSeverityDanger  SessionSignalCatalogResponseSignalSeverity = "danger"
	SessionSignalCatalogResponseSignalSeveritySuccess SessionSignalCatalogResponseSignalSeverity = "success"
)

func (r SessionSignalCatalogResponseSignalSeverity) IsKnown() bool {
	switch r {
	case SessionSignalCatalogResponseSignalSeverityInfo, SessionSignalCatalogResponseSignalSeverityWarning, SessionSignalCatalogResponseSignalSeverityDanger, SessionSignalCatalogResponseSignalSeveritySuccess:
		return true
	}
	return false
}

type SessionSignalCatalogResponseSignalSource string

const (
	SessionSignalCatalogResponseSignalSourceManual  SessionSignalCatalogResponseSignalSource = "manual"
	SessionSignalCatalogResponseSignalSourceDerived SessionSignalCatalogResponseSignalSource = "derived"
)

func (r SessionSignalCatalogResponseSignalSource) IsKnown() bool {
	switch r {
	case SessionSignalCatalogResponseSignalSourceManual, SessionSignalCatalogResponseSignalSourceDerived:
		return true
	}
	return false
}

type SessionSignalCatalogResponseSignalValue string

const (
	SessionSignalCatalogResponseSignalValueCaptchaEncountered     SessionSignalCatalogResponseSignalValue = "captcha_encountered"
	SessionSignalCatalogResponseSignalValueProxyConnectionFailed  SessionSignalCatalogResponseSignalValue = "proxy_connection_failed"
	SessionSignalCatalogResponseSignalValueSessionTimeoutExceeded SessionSignalCatalogResponseSignalValue = "session_timeout_exceeded"
	SessionSignalCatalogResponseSignalValueBotDetectionTriggered  SessionSignalCatalogResponseSignalValue = "bot_detection_triggered"
	SessionSignalCatalogResponseSignalValueContextRestored        SessionSignalCatalogResponseSignalValue = "context_restored"
)

func (r SessionSignalCatalogResponseSignalValue) IsKnown() bool {
	switch r {
	case SessionSignalCatalogResponseSignalValueCaptchaEncountered, SessionSignalCatalogResponseSignalValueProxyConnectionFailed, SessionSignalCatalogResponseSignalValueSessionTimeoutExceeded, SessionSignalCatalogResponseSignalValueBotDetectionTriggered, SessionSignalCatalogResponseSignalValueContextRestored:
		return true
	}
	return false
}

// Atomically merge keys into customer metadata. Set a key to null to remove it;
// keys you leave out are kept, so concurrent updates to different keys are all
// kept. Returns the merged metadata, which must stay within the metadata
// limits. Subject to session organization and project access.
func (r *SessionService) UpdateMetadata(ctx context.Context, id string, body SessionUpdateMetadataParams, opts ...option.RequestOption) (res *SessionUpdateMetadataResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/metadata", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPatch, path, body, &res, opts...)
	return res, err
}

type SessionUpdateMetadataParams struct {
	// Metadata keys to change, up to 100 per update. Keys follow the same rules as
	// metadata keys. Send a string, finite number or boolean to set a key, or null
	// to remove it; keys you leave out are kept. The merged metadata must stay
	// within 50 keys and 8192 UTF-8 JSON bytes.
	Metadata param.Field[map[string]SessionUpdateMetadataParamsMetadataUnion] `json:"metadata" api:"required"`
}

func (r SessionUpdateMetadataParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Metadata keys to change, up to 100 per update. Keys follow the same rules as
// metadata keys. Send a string, finite number or boolean to set a key, or null
// to remove it; keys you leave out are kept. The merged metadata must stay
// within 50 keys and 8192 UTF-8 JSON bytes.
// Satisfied by [shared.UnionString], [shared.UnionFloat], [shared.UnionBool].
type SessionUpdateMetadataParamsMetadataUnion interface {
	ImplementsSessionUpdateMetadataParamsMetadataUnion()
}

type SessionUpdateMetadataResponse struct {
	// Customer-defined flat metadata. Values are always strings: numbers and
	// booleans sent on write come back in their string form.
	Metadata map[string]string                 `json:"metadata" api:"required"`
	JSON     sessionUpdateMetadataResponseJSON `json:"-"`
}

// sessionUpdateMetadataResponseJSON contains the JSON metadata for the struct
// [SessionUpdateMetadataResponse]
type sessionUpdateMetadataResponseJSON struct {
	Metadata    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionUpdateMetadataResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionUpdateMetadataResponseJSON) RawJSON() string {
	return r.raw
}

// Satisfied by [CommandNavigateParam], [CommandBackParam],
// [CommandForwardParam], [CommandReloadParam], [CommandSnapshotParam],
// [CommandClickParam], [CommandDblclickParam], [CommandHoverParam],
// [CommandFocusParam], [CommandTypeParam], [CommandFillParam],
// [CommandPressParam], [CommandKeydownParam], [CommandKeyupParam],
// [CommandMouseParam], [CommandSelectParam], [CommandCheckParam],
// [CommandUploadParam], [CommandDragParam], [CommandUncheckParam],
// [CommandGetParam], [CommandIsParam], [CommandWaitParam],
// [CommandScreenshotParam], [CommandEvalParam], [CommandPdfParam],
// [CommandScrollParam], [CommandFindParam], [CommandTabParam].
type CommandUnionParam interface {
	ImplementsCommandUnionParam()
}

// Opens a URL in the tab. Only http, https, and about:blank. A URL without a
// scheme gets https://.
type CommandNavigateParam struct {
	Command param.Field[CommandNavigateCommand] `json:"command" api:"required"`
	URL     param.Field[string]                 `json:"url" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandNavigateParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandNavigateCommand string

const (
	CommandNavigateCommandNavigate CommandNavigateCommand = "navigate"
)

func (r CommandNavigateCommand) IsKnown() bool {
	switch r {
	case CommandNavigateCommandNavigate:
		return true
	}
	return false
}

func (r CommandNavigateParam) ImplementsCommandUnionParam() {}

// Goes back one history entry.
type CommandBackParam struct {
	Command param.Field[CommandBackCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandBackParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandBackCommand string

const (
	CommandBackCommandBack CommandBackCommand = "back"
)

func (r CommandBackCommand) IsKnown() bool {
	switch r {
	case CommandBackCommandBack:
		return true
	}
	return false
}

func (r CommandBackParam) ImplementsCommandUnionParam() {}

// Goes forward one history entry.
type CommandForwardParam struct {
	Command param.Field[CommandForwardCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandForwardParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandForwardCommand string

const (
	CommandForwardCommandForward CommandForwardCommand = "forward"
)

func (r CommandForwardCommand) IsKnown() bool {
	switch r {
	case CommandForwardCommandForward:
		return true
	}
	return false
}

func (r CommandForwardParam) ImplementsCommandUnionParam() {}

// Reloads the tab.
type CommandReloadParam struct {
	Command param.Field[CommandReloadCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandReloadParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandReloadCommand string

const (
	CommandReloadCommandReload CommandReloadCommand = "reload"
)

func (r CommandReloadCommand) IsKnown() bool {
	switch r {
	case CommandReloadCommandReload:
		return true
	}
	return false
}

func (r CommandReloadParam) ImplementsCommandUnionParam() {}

// Returns the accessibility tree of the page, with a ref for each element you
// can act on.
type CommandSnapshotParam struct {
	Command param.Field[CommandSnapshotCommand] `json:"command" api:"required"`
	// Only lines with refs or values, and their ancestors.
	Compact param.Field[bool] `json:"compact"`
	// Only lines up to this depth.
	Depth param.Field[int64] `json:"depth"`
	// Only lines with refs.
	Interactive param.Field[bool] `json:"interactive"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandSnapshotParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandSnapshotCommand string

const (
	CommandSnapshotCommandSnapshot CommandSnapshotCommand = "snapshot"
)

func (r CommandSnapshotCommand) IsKnown() bool {
	switch r {
	case CommandSnapshotCommandSnapshot:
		return true
	}
	return false
}

func (r CommandSnapshotParam) ImplementsCommandUnionParam() {}

// Clicks the element with the mouse.
type CommandClickParam struct {
	Command param.Field[CommandClickCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandClickParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandClickCommand string

const (
	CommandClickCommandClick CommandClickCommand = "click"
)

func (r CommandClickCommand) IsKnown() bool {
	switch r {
	case CommandClickCommandClick:
		return true
	}
	return false
}

func (r CommandClickParam) ImplementsCommandUnionParam() {}

// Double-clicks the element with the mouse.
type CommandDblclickParam struct {
	Command param.Field[CommandDblclickCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandDblclickParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandDblclickCommand string

const (
	CommandDblclickCommandDblclick CommandDblclickCommand = "dblclick"
)

func (r CommandDblclickCommand) IsKnown() bool {
	switch r {
	case CommandDblclickCommandDblclick:
		return true
	}
	return false
}

func (r CommandDblclickParam) ImplementsCommandUnionParam() {}

// Moves the mouse onto the element.
type CommandHoverParam struct {
	Command param.Field[CommandHoverCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandHoverParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandHoverCommand string

const (
	CommandHoverCommandHover CommandHoverCommand = "hover"
)

func (r CommandHoverCommand) IsKnown() bool {
	switch r {
	case CommandHoverCommandHover:
		return true
	}
	return false
}

func (r CommandHoverParam) ImplementsCommandUnionParam() {}

// Focuses the element.
type CommandFocusParam struct {
	Command param.Field[CommandFocusCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandFocusParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandFocusCommand string

const (
	CommandFocusCommandFocus CommandFocusCommand = "focus"
)

func (r CommandFocusCommand) IsKnown() bool {
	switch r {
	case CommandFocusCommandFocus:
		return true
	}
	return false
}

func (r CommandFocusParam) ImplementsCommandUnionParam() {}

// Types text one key press at a time, after the current content.
type CommandTypeParam struct {
	Command param.Field[CommandTypeCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	Text   param.Field[string]             `json:"text" api:"required"`
	// Replace the current content.
	Clear param.Field[bool] `json:"clear"`
	// Milliseconds to wait between key presses.
	Delay param.Field[int64] `json:"delay"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandTypeParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandTypeCommand string

const (
	CommandTypeCommandType CommandTypeCommand = "type"
)

func (r CommandTypeCommand) IsKnown() bool {
	switch r {
	case CommandTypeCommandType:
		return true
	}
	return false
}

func (r CommandTypeParam) ImplementsCommandUnionParam() {}

// Replaces the content of a field in one step, without key presses.
type CommandFillParam struct {
	Command param.Field[CommandFillCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	Value  param.Field[string]             `json:"value" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandFillParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandFillCommand string

const (
	CommandFillCommandFill CommandFillCommand = "fill"
)

func (r CommandFillCommand) IsKnown() bool {
	switch r {
	case CommandFillCommandFill:
		return true
	}
	return false
}

func (r CommandFillParam) ImplementsCommandUnionParam() {}

// Presses a key, such as Enter, or a combination such as Control+a.
type CommandPressParam struct {
	Command param.Field[CommandPressCommand] `json:"command" api:"required"`
	Key     param.Field[string]              `json:"key" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r CommandPressParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandPressCommand string

const (
	CommandPressCommandPress CommandPressCommand = "press"
)

func (r CommandPressCommand) IsKnown() bool {
	switch r {
	case CommandPressCommandPress:
		return true
	}
	return false
}

func (r CommandPressParam) ImplementsCommandUnionParam() {}

// Holds a key down until its keyup.
type CommandKeydownParam struct {
	Command param.Field[CommandKeydownCommand] `json:"command" api:"required"`
	Key     param.Field[string]                `json:"key" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandKeydownParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandKeydownCommand string

const (
	CommandKeydownCommandKeydown CommandKeydownCommand = "keydown"
)

func (r CommandKeydownCommand) IsKnown() bool {
	switch r {
	case CommandKeydownCommandKeydown:
		return true
	}
	return false
}

func (r CommandKeydownParam) ImplementsCommandUnionParam() {}

// Lets go of a key held with keydown.
type CommandKeyupParam struct {
	Command param.Field[CommandKeyupCommand] `json:"command" api:"required"`
	Key     param.Field[string]              `json:"key" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandKeyupParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandKeyupCommand string

const (
	CommandKeyupCommandKeyup CommandKeyupCommand = "keyup"
)

func (r CommandKeyupCommand) IsKnown() bool {
	switch r {
	case CommandKeyupCommandKeyup:
		return true
	}
	return false
}

func (r CommandKeyupParam) ImplementsCommandUnionParam() {}

// Mouse input at a point in CSS pixels of the visible part of the page, for
// pages without useful elements such as a canvas.
type CommandMouseParam struct {
	Action  param.Field[CommandMouseAction]  `json:"action" api:"required"`
	Command param.Field[CommandMouseCommand] `json:"command" api:"required"`
	Button  param.Field[CommandMouseButton]  `json:"button"`
	Clicks  param.Field[int64]               `json:"clicks"`
	DeltaX  param.Field[float64]             `json:"deltaX"`
	DeltaY  param.Field[float64]             `json:"deltaY"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string]  `json:"tab"`
	X   param.Field[float64] `json:"x"`
	Y   param.Field[float64] `json:"y"`
}

func (r CommandMouseParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandMouseAction string

const (
	CommandMouseActionMove  CommandMouseAction = "move"
	CommandMouseActionDown  CommandMouseAction = "down"
	CommandMouseActionUp    CommandMouseAction = "up"
	CommandMouseActionClick CommandMouseAction = "click"
	CommandMouseActionWheel CommandMouseAction = "wheel"
)

func (r CommandMouseAction) IsKnown() bool {
	switch r {
	case CommandMouseActionMove, CommandMouseActionDown, CommandMouseActionUp, CommandMouseActionClick, CommandMouseActionWheel:
		return true
	}
	return false
}

type CommandMouseCommand string

const (
	CommandMouseCommandMouse CommandMouseCommand = "mouse"
)

func (r CommandMouseCommand) IsKnown() bool {
	switch r {
	case CommandMouseCommandMouse:
		return true
	}
	return false
}

type CommandMouseButton string

const (
	CommandMouseButtonLeft   CommandMouseButton = "left"
	CommandMouseButtonRight  CommandMouseButton = "right"
	CommandMouseButtonMiddle CommandMouseButton = "middle"
)

func (r CommandMouseButton) IsKnown() bool {
	switch r {
	case CommandMouseButtonLeft, CommandMouseButtonRight, CommandMouseButtonMiddle:
		return true
	}
	return false
}

func (r CommandMouseParam) ImplementsCommandUnionParam() {}

// Selects options. Matches by value, then by label.
type CommandSelectParam struct {
	Command param.Field[CommandSelectCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam]            `json:"target" api:"required"`
	Values param.Field[CommandSelectValuesUnionParam] `json:"values" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandSelectParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandSelectCommand string

const (
	CommandSelectCommandSelect CommandSelectCommand = "select"
)

func (r CommandSelectCommand) IsKnown() bool {
	switch r {
	case CommandSelectCommandSelect:
		return true
	}
	return false
}

// Satisfied by [shared.UnionString], [CommandSelectValuesUnionMember1Param].
type CommandSelectValuesUnionParam interface {
	ImplementsCommandSelectValuesUnionParam()
}

type CommandSelectValuesUnionMember1Param []string

func (r CommandSelectValuesUnionMember1Param) ImplementsCommandSelectValuesUnionParam() {}

func (r CommandSelectParam) ImplementsCommandUnionParam() {}

// Checks a checkbox, radio button, switch, or toggle button.
type CommandCheckParam struct {
	Command param.Field[CommandCheckCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandCheckParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandCheckCommand string

const (
	CommandCheckCommandCheck CommandCheckCommand = "check"
)

func (r CommandCheckCommand) IsKnown() bool {
	switch r {
	case CommandCheckCommandCheck:
		return true
	}
	return false
}

func (r CommandCheckParam) ImplementsCommandUnionParam() {}

// Sets the files of a file input. Upload the files to the session first.
type CommandUploadParam struct {
	Command param.Field[CommandUploadCommand]         `json:"command" api:"required"`
	Files   param.Field[CommandUploadFilesUnionParam] `json:"files" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandUploadParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandUploadCommand string

const (
	CommandUploadCommandUpload CommandUploadCommand = "upload"
)

func (r CommandUploadCommand) IsKnown() bool {
	switch r {
	case CommandUploadCommandUpload:
		return true
	}
	return false
}

// Satisfied by [shared.UnionString], [CommandUploadFilesUnionMember1Param].
type CommandUploadFilesUnionParam interface {
	ImplementsCommandUploadFilesUnionParam()
}

type CommandUploadFilesUnionMember1Param []string

func (r CommandUploadFilesUnionMember1Param) ImplementsCommandUploadFilesUnionParam() {}

func (r CommandUploadParam) ImplementsCommandUnionParam() {}

// Drags one element onto another with the mouse.
type CommandDragParam struct {
	Command param.Field[CommandDragCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Source param.Field[CommandTargetParam] `json:"source" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandDragParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandDragCommand string

const (
	CommandDragCommandDrag CommandDragCommand = "drag"
)

func (r CommandDragCommand) IsKnown() bool {
	switch r {
	case CommandDragCommandDrag:
		return true
	}
	return false
}

func (r CommandDragParam) ImplementsCommandUnionParam() {}

// Unchecks a checkbox, switch, or toggle button.
type CommandUncheckParam struct {
	Command param.Field[CommandUncheckCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandUncheckParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandUncheckCommand string

const (
	CommandUncheckCommandUncheck CommandUncheckCommand = "uncheck"
)

func (r CommandUncheckCommand) IsKnown() bool {
	switch r {
	case CommandUncheckCommandUncheck:
		return true
	}
	return false
}

func (r CommandUncheckParam) ImplementsCommandUnionParam() {}

// Reads something from the page or an element.
type CommandGetParam struct {
	Command param.Field[CommandGetCommand] `json:"command" api:"required"`
	What    param.Field[CommandGetWhat]    `json:"what" api:"required"`
	// The attribute to read with attr.
	Attribute param.Field[string] `json:"attribute"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r CommandGetParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandGetCommand string

const (
	CommandGetCommandGet CommandGetCommand = "get"
)

func (r CommandGetCommand) IsKnown() bool {
	switch r {
	case CommandGetCommandGet:
		return true
	}
	return false
}

type CommandGetWhat string

const (
	CommandGetWhatText     CommandGetWhat = "text"
	CommandGetWhatHTML     CommandGetWhat = "html"
	CommandGetWhatMarkdown CommandGetWhat = "markdown"
	CommandGetWhatValue    CommandGetWhat = "value"
	CommandGetWhatAttr     CommandGetWhat = "attr"
	CommandGetWhatTitle    CommandGetWhat = "title"
	CommandGetWhatURL      CommandGetWhat = "url"
	CommandGetWhatCount    CommandGetWhat = "count"
	CommandGetWhatBox      CommandGetWhat = "box"
	CommandGetWhatLocator  CommandGetWhat = "locator"
)

func (r CommandGetWhat) IsKnown() bool {
	switch r {
	case CommandGetWhatText, CommandGetWhatHTML, CommandGetWhatMarkdown, CommandGetWhatValue, CommandGetWhatAttr, CommandGetWhatTitle, CommandGetWhatURL, CommandGetWhatCount, CommandGetWhatBox, CommandGetWhatLocator:
		return true
	}
	return false
}

func (r CommandGetParam) ImplementsCommandUnionParam() {}

// Checks the state of an element.
type CommandIsParam struct {
	Command param.Field[CommandIsCommand] `json:"command" api:"required"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	What   param.Field[CommandIsWhat]      `json:"what" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandIsParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandIsCommand string

const (
	CommandIsCommandIs CommandIsCommand = "is"
)

func (r CommandIsCommand) IsKnown() bool {
	switch r {
	case CommandIsCommandIs:
		return true
	}
	return false
}

type CommandIsWhat string

const (
	CommandIsWhatVisible CommandIsWhat = "visible"
	CommandIsWhatEnabled CommandIsWhat = "enabled"
	CommandIsWhatChecked CommandIsWhat = "checked"
)

func (r CommandIsWhat) IsKnown() bool {
	switch r {
	case CommandIsWhatVisible, CommandIsWhatEnabled, CommandIsWhatChecked:
		return true
	}
	return false
}

func (r CommandIsParam) ImplementsCommandUnionParam() {}

// Waits for one of an element, text, a URL, a load state, a download, or a
// fixed time.
type CommandWaitParam struct {
	Command param.Field[CommandWaitCommand] `json:"command" api:"required"`
	// Waits for the next download to finish.
	Download param.Field[CommandWaitDownload] `json:"download"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	Load  param.Field[CommandWaitLoad]   `json:"load"`
	Ms    param.Field[int64]             `json:"ms"`
	State param.Field[CommandWaitState]  `json:"state"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
	Text   param.Field[string]             `json:"text"`
	// Milliseconds. 10000 by default.
	Timeout param.Field[int64] `json:"timeout"`
	// A URL pattern. * matches any characters.
	URL param.Field[string] `json:"url"`
}

func (r CommandWaitParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandWaitCommand string

const (
	CommandWaitCommandWait CommandWaitCommand = "wait"
)

func (r CommandWaitCommand) IsKnown() bool {
	switch r {
	case CommandWaitCommandWait:
		return true
	}
	return false
}

// Waits for the next download to finish.
type CommandWaitDownload bool

const (
	CommandWaitDownloadTrue CommandWaitDownload = true
)

func (r CommandWaitDownload) IsKnown() bool {
	switch r {
	case CommandWaitDownloadTrue:
		return true
	}
	return false
}

type CommandWaitLoad string

const (
	CommandWaitLoadLoad             CommandWaitLoad = "load"
	CommandWaitLoadDomcontentloaded CommandWaitLoad = "domcontentloaded"
	CommandWaitLoadNetworkidle      CommandWaitLoad = "networkidle"
)

func (r CommandWaitLoad) IsKnown() bool {
	switch r {
	case CommandWaitLoadLoad, CommandWaitLoadDomcontentloaded, CommandWaitLoadNetworkidle:
		return true
	}
	return false
}

type CommandWaitState string

const (
	CommandWaitStateVisible  CommandWaitState = "visible"
	CommandWaitStateHidden   CommandWaitState = "hidden"
	CommandWaitStateAttached CommandWaitState = "attached"
	CommandWaitStateDetached CommandWaitState = "detached"
)

func (r CommandWaitState) IsKnown() bool {
	switch r {
	case CommandWaitStateVisible, CommandWaitStateHidden, CommandWaitStateAttached, CommandWaitStateDetached:
		return true
	}
	return false
}

func (r CommandWaitParam) ImplementsCommandUnionParam() {}

// Captures the page or an element as base64 data.
type CommandScreenshotParam struct {
	Command param.Field[CommandScreenshotCommand] `json:"command" api:"required"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	Format param.Field[CommandScreenshotFormat] `json:"format"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame    param.Field[CommandFrameParam] `json:"frame"`
	FullPage param.Field[bool]              `json:"fullPage"`
	Quality  param.Field[int64]             `json:"quality"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r CommandScreenshotParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandScreenshotCommand string

const (
	CommandScreenshotCommandScreenshot CommandScreenshotCommand = "screenshot"
)

func (r CommandScreenshotCommand) IsKnown() bool {
	switch r {
	case CommandScreenshotCommandScreenshot:
		return true
	}
	return false
}

type CommandScreenshotFormat string

const (
	CommandScreenshotFormatPng  CommandScreenshotFormat = "png"
	CommandScreenshotFormatJpeg CommandScreenshotFormat = "jpeg"
	CommandScreenshotFormatWebp CommandScreenshotFormat = "webp"
)

func (r CommandScreenshotFormat) IsKnown() bool {
	switch r {
	case CommandScreenshotFormatPng, CommandScreenshotFormatJpeg, CommandScreenshotFormatWebp:
		return true
	}
	return false
}

func (r CommandScreenshotParam) ImplementsCommandUnionParam() {}

// Runs JavaScript in the page and returns the result as JSON.
type CommandEvalParam struct {
	Command param.Field[CommandEvalCommand] `json:"command" api:"required"`
	Script  param.Field[string]             `json:"script" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// Milliseconds. 10000 by default.
	Timeout param.Field[int64] `json:"timeout"`
}

func (r CommandEvalParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandEvalCommand string

const (
	CommandEvalCommandEval CommandEvalCommand = "eval"
)

func (r CommandEvalCommand) IsKnown() bool {
	switch r {
	case CommandEvalCommandEval:
		return true
	}
	return false
}

func (r CommandEvalParam) ImplementsCommandUnionParam() {}

// Prints the page to a PDF as base64 data.
type CommandPdfParam struct {
	Command         param.Field[CommandPdfCommand] `json:"command" api:"required"`
	Landscape       param.Field[bool]              `json:"landscape"`
	PrintBackground param.Field[bool]              `json:"printBackground"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandPdfParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandPdfCommand string

const (
	CommandPdfCommandPdf CommandPdfCommand = "pdf"
)

func (r CommandPdfCommand) IsKnown() bool {
	switch r {
	case CommandPdfCommandPdf:
		return true
	}
	return false
}

func (r CommandPdfParam) ImplementsCommandUnionParam() {}

// Scrolls the page, or an element when target is set.
type CommandScrollParam struct {
	Command param.Field[CommandScrollCommand] `json:"command" api:"required"`
	// Pixels. 300 by default.
	Amount    param.Field[float64]                `json:"amount"`
	Direction param.Field[CommandScrollDirection] `json:"direction"`
	// Fails the command when the element does not have this role or name.
	Expect param.Field[CommandExpectationParam] `json:"expect"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	// A ref from a snapshot such as e12, a CSS selector, or a locator.
	Target param.Field[CommandTargetParam] `json:"target"`
}

func (r CommandScrollParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandScrollCommand string

const (
	CommandScrollCommandScroll CommandScrollCommand = "scroll"
)

func (r CommandScrollCommand) IsKnown() bool {
	switch r {
	case CommandScrollCommandScroll:
		return true
	}
	return false
}

type CommandScrollDirection string

const (
	CommandScrollDirectionUp    CommandScrollDirection = "up"
	CommandScrollDirectionDown  CommandScrollDirection = "down"
	CommandScrollDirectionLeft  CommandScrollDirection = "left"
	CommandScrollDirectionRight CommandScrollDirection = "right"
)

func (r CommandScrollDirection) IsKnown() bool {
	switch r {
	case CommandScrollDirectionUp, CommandScrollDirectionDown, CommandScrollDirectionLeft, CommandScrollDirectionRight:
		return true
	}
	return false
}

func (r CommandScrollParam) ImplementsCommandUnionParam() {}

// Finds elements and returns refs for up to 50 matches. With nth, it returns
// that one match.
type CommandFindParam struct {
	Command param.Field[CommandFindCommand] `json:"command" api:"required"`
	// A CSS selector or a locator, not a ref.
	Target param.Field[CommandTargetParam] `json:"target" api:"required"`
	// The iframe to look in. List nested iframes from the outside in.
	Frame param.Field[CommandFrameParam] `json:"frame"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
}

func (r CommandFindParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandFindCommand string

const (
	CommandFindCommandFind CommandFindCommand = "find"
)

func (r CommandFindCommand) IsKnown() bool {
	switch r {
	case CommandFindCommandFind:
		return true
	}
	return false
}

func (r CommandFindParam) ImplementsCommandUnionParam() {}

// Lists, opens, or closes tabs. The last tab cannot be closed.
type CommandTabParam struct {
	Action  param.Field[CommandTabAction]  `json:"action" api:"required"`
	Command param.Field[CommandTabCommand] `json:"command" api:"required"`
	// A tab id such as t2. A command on a ref does not need it.
	Tab param.Field[string] `json:"tab"`
	URL param.Field[string] `json:"url"`
}

func (r CommandTabParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type CommandTabAction string

const (
	CommandTabActionList  CommandTabAction = "list"
	CommandTabActionNew   CommandTabAction = "new"
	CommandTabActionClose CommandTabAction = "close"
)

func (r CommandTabAction) IsKnown() bool {
	switch r {
	case CommandTabActionList, CommandTabActionNew, CommandTabActionClose:
		return true
	}
	return false
}

type CommandTabCommand string

const (
	CommandTabCommandTab CommandTabCommand = "tab"
)

func (r CommandTabCommand) IsKnown() bool {
	switch r {
	case CommandTabCommandTab:
		return true
	}
	return false
}

func (r CommandTabParam) ImplementsCommandUnionParam() {}

type CommandParam = CommandUnionParam

// A JavaScript dialog: alert, confirm, prompt, or beforeunload.
type CommandDialog struct {
	ID            string            `json:"id" api:"required"`
	Message       string            `json:"message" api:"required"`
	Tab           string            `json:"tab" api:"required"`
	Type          CommandDialogType `json:"type" api:"required"`
	DefaultPrompt string            `json:"defaultPrompt"`
	JSON          commandDialogJSON `json:"-"`
}

// commandDialogJSON contains the JSON metadata for the struct [CommandDialog]
type commandDialogJSON struct {
	ID            apijson.Field
	Message       apijson.Field
	Tab           apijson.Field
	Type          apijson.Field
	DefaultPrompt apijson.Field
	raw           string
	ExtraFields   map[string]apijson.Field
}

func (r *CommandDialog) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandDialogJSON) RawJSON() string {
	return r.raw
}

type CommandDialogType string

const (
	CommandDialogTypeAlert        CommandDialogType = "alert"
	CommandDialogTypeConfirm      CommandDialogType = "confirm"
	CommandDialogTypePrompt       CommandDialogType = "prompt"
	CommandDialogTypeBeforeunload CommandDialogType = "beforeunload"
)

func (r CommandDialogType) IsKnown() bool {
	switch r {
	case CommandDialogTypeAlert, CommandDialogTypeConfirm, CommandDialogTypePrompt, CommandDialogTypeBeforeunload:
		return true
	}
	return false
}

type CommandErrorInfo struct {
	// Error code, such as stale_ref or not_actionable.
	Code    string `json:"code" api:"required"`
	Message string `json:"message" api:"required"`
	// More detail, such as covered or detached.
	Reason string               `json:"reason"`
	JSON   commandErrorInfoJSON `json:"-"`
}

// commandErrorInfoJSON contains the JSON metadata for the struct
// [CommandErrorInfo]
type commandErrorInfoJSON struct {
	Code        apijson.Field
	Message     apijson.Field
	Reason      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandErrorInfo) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandErrorInfoJSON) RawJSON() string {
	return r.raw
}

// Fails the command when the element does not have this role or name.
type CommandExpectationParam struct {
	Name param.Field[string] `json:"name"`
	Role param.Field[string] `json:"role"`
}

func (r CommandExpectationParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// The iframe to look in. List nested iframes from the outside in.
// Satisfied by [shared.UnionString], [CommandLocatorParam],
// [CommandFrameUnionMember2Param].
type CommandFrameUnionParam interface {
	ImplementsCommandFrameUnionParam()
}

func (r CommandLocatorParam) ImplementsCommandFrameUnionParam() {}

type CommandFrameUnionMember2Param []CommandTargetParam

func (r CommandFrameUnionMember2Param) ImplementsCommandFrameUnionParam() {}

type CommandFrameParam = CommandFrameUnionParam

// Finds an element. Set exactly one of role, text, label, placeholder, alt,
// title, testid, css.
type CommandLocatorParam struct {
	Alt param.Field[string] `json:"alt"`
	Css param.Field[string] `json:"css"`
	// Match the whole text. Text matches a case-insensitive substring by default.
	Exact param.Field[bool]   `json:"exact"`
	Label param.Field[string] `json:"label"`
	// The accessible name. Works only with role.
	Name param.Field[string] `json:"name"`
	// Picks one of several matches. Negative values count from the end.
	Nth         param.Field[int64]  `json:"nth"`
	Placeholder param.Field[string] `json:"placeholder"`
	Role        param.Field[string] `json:"role"`
	Testid      param.Field[string] `json:"testid"`
	Text        param.Field[string] `json:"text"`
	Title       param.Field[string] `json:"title"`
}

func (r CommandLocatorParam) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Result of one command, with the fields of that command.
type CommandResult struct {
	Command interface{} `json:"command"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect interface{} `json:"effect"`
	Ok     bool        `json:"ok"`
	Error  interface{} `json:"error"`
	URL    string      `json:"url"`
	// False when there was no history entry.
	Navigated  bool   `json:"navigated"`
	Snapshot   string `json:"snapshot"`
	SnapshotID string `json:"snapshotId"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog interface{} `json:"dialog"`
	// The command started a navigation that was still loading when it returned.
	Loading bool `json:"loading"`
	// The values of the selected options.
	Selected interface{} `json:"selected"`
	Checked  bool        `json:"checked"`
	Files    interface{} `json:"files"`
	// The value is a secret, so it is left out.
	Masked bool `json:"masked"`
	// A string for text, html, markdown, value, attr, title, and url. A number for
	// count. { x, y, width, height } or null for box. A list of locators for
	// locator.
	Value interface{} `json:"value"`
	Bytes int64       `json:"bytes"`
	// The downloaded file, for download.
	File    string            `json:"file"`
	Waited  interface{}       `json:"waited"`
	Data    string            `json:"data"`
	Format  interface{}       `json:"format"`
	Moved   bool              `json:"moved"`
	X       float64           `json:"x"`
	Y       float64           `json:"y"`
	Count   int64             `json:"count"`
	Matches interface{}       `json:"matches"`
	Tab     string            `json:"tab"`
	Tabs    interface{}       `json:"tabs"`
	JSON    commandResultJSON `json:"-"`
	union   CommandResultUnion
}

// commandResultJSON contains the JSON metadata for the struct [CommandResult]
type commandResultJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	URL         apijson.Field
	Navigated   apijson.Field
	Snapshot    apijson.Field
	SnapshotID  apijson.Field
	Dialog      apijson.Field
	Loading     apijson.Field
	Selected    apijson.Field
	Checked     apijson.Field
	Files       apijson.Field
	Masked      apijson.Field
	Value       apijson.Field
	Bytes       apijson.Field
	File        apijson.Field
	Waited      apijson.Field
	Data        apijson.Field
	Format      apijson.Field
	Moved       apijson.Field
	X           apijson.Field
	Y           apijson.Field
	Count       apijson.Field
	Matches     apijson.Field
	Tab         apijson.Field
	Tabs        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r commandResultJSON) RawJSON() string {
	return r.raw
}

func (r *CommandResult) UnmarshalJSON(data []byte) (err error) {
	*r = CommandResult{}
	err = apijson.UnmarshalRoot(data, &r.union)
	if err != nil {
		return err
	}
	return apijson.Port(r.union, &r)
}

// AsUnion returns a [CommandResultUnion] interface which you can cast to the
// specific types for more type safety. Possible runtime types of the union are
// [CommandResultNavigate], [CommandResultBack], [CommandResultForward],
// [CommandResultReload], [CommandResultSnapshot], [CommandResultClick],
// [CommandResultDblclick], [CommandResultHover], [CommandResultFocus],
// [CommandResultType], [CommandResultFill], [CommandResultPress],
// [CommandResultKeydown], [CommandResultKeyup], [CommandResultMouse],
// [CommandResultSelect], [CommandResultCheck], [CommandResultUncheck],
// [CommandResultUpload], [CommandResultDrag], [CommandResultGet],
// [CommandResultIs], [CommandResultWait], [CommandResultScreenshot],
// [CommandResultEval], [CommandResultPdf], [CommandResultScroll],
// [CommandResultFind], [CommandResultTab].
func (r CommandResult) AsUnion() CommandResultUnion {
	return r.union
}

type CommandResultUnion interface {
	implementsCommandResult()
}

func init() {
	apijson.RegisterUnion(
		reflect.TypeOf((*CommandResultUnion)(nil)).Elem(),
		"command",
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultNavigate{}),
			DiscriminatorValue: "navigate",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultBack{}),
			DiscriminatorValue: "back",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultForward{}),
			DiscriminatorValue: "forward",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultReload{}),
			DiscriminatorValue: "reload",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultSnapshot{}),
			DiscriminatorValue: "snapshot",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultClick{}),
			DiscriminatorValue: "click",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultDblclick{}),
			DiscriminatorValue: "dblclick",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultHover{}),
			DiscriminatorValue: "hover",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultFocus{}),
			DiscriminatorValue: "focus",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultType{}),
			DiscriminatorValue: "type",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultFill{}),
			DiscriminatorValue: "fill",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultPress{}),
			DiscriminatorValue: "press",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultKeydown{}),
			DiscriminatorValue: "keydown",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultKeyup{}),
			DiscriminatorValue: "keyup",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultMouse{}),
			DiscriminatorValue: "mouse",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultSelect{}),
			DiscriminatorValue: "select",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultCheck{}),
			DiscriminatorValue: "check",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultUncheck{}),
			DiscriminatorValue: "uncheck",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultUpload{}),
			DiscriminatorValue: "upload",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultDrag{}),
			DiscriminatorValue: "drag",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultGet{}),
			DiscriminatorValue: "get",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultIs{}),
			DiscriminatorValue: "is",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultWait{}),
			DiscriminatorValue: "wait",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultScreenshot{}),
			DiscriminatorValue: "screenshot",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultEval{}),
			DiscriminatorValue: "eval",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultPdf{}),
			DiscriminatorValue: "pdf",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultScroll{}),
			DiscriminatorValue: "scroll",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultFind{}),
			DiscriminatorValue: "find",
		},
		apijson.UnionVariant{
			TypeFilter:         gjson.JSON,
			Type:               reflect.TypeOf(CommandResultTab{}),
			DiscriminatorValue: "tab",
		},
	)
}

// Result of a navigate command.
type CommandResultNavigate struct {
	Command CommandResultNavigateCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultNavigateEffect `json:"effect" api:"required"`
	Ok     bool                        `json:"ok" api:"required"`
	Error  CommandErrorInfo            `json:"error"`
	URL    string                      `json:"url"`
	JSON   commandResultNavigateJSON   `json:"-"`
}

// commandResultNavigateJSON contains the JSON metadata for the struct
// [CommandResultNavigate]
type commandResultNavigateJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultNavigate) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultNavigateJSON) RawJSON() string {
	return r.raw
}

type CommandResultNavigateCommand string

const (
	CommandResultNavigateCommandNavigate CommandResultNavigateCommand = "navigate"
)

func (r CommandResultNavigateCommand) IsKnown() bool {
	switch r {
	case CommandResultNavigateCommandNavigate:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultNavigateEffect string

const (
	CommandResultNavigateEffectNone    CommandResultNavigateEffect = "none"
	CommandResultNavigateEffectApplied CommandResultNavigateEffect = "applied"
	CommandResultNavigateEffectUnknown CommandResultNavigateEffect = "unknown"
)

func (r CommandResultNavigateEffect) IsKnown() bool {
	switch r {
	case CommandResultNavigateEffectNone, CommandResultNavigateEffectApplied, CommandResultNavigateEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultNavigate) implementsCommandResult() {}

// Result of a back command.
type CommandResultBack struct {
	Command CommandResultBackCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultBackEffect `json:"effect" api:"required"`
	Ok     bool                    `json:"ok" api:"required"`
	Error  CommandErrorInfo        `json:"error"`
	// False when there was no history entry.
	Navigated bool                  `json:"navigated"`
	URL       string                `json:"url"`
	JSON      commandResultBackJSON `json:"-"`
}

// commandResultBackJSON contains the JSON metadata for the struct
// [CommandResultBack]
type commandResultBackJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	Navigated   apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultBack) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultBackJSON) RawJSON() string {
	return r.raw
}

type CommandResultBackCommand string

const (
	CommandResultBackCommandBack CommandResultBackCommand = "back"
)

func (r CommandResultBackCommand) IsKnown() bool {
	switch r {
	case CommandResultBackCommandBack:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultBackEffect string

const (
	CommandResultBackEffectNone    CommandResultBackEffect = "none"
	CommandResultBackEffectApplied CommandResultBackEffect = "applied"
	CommandResultBackEffectUnknown CommandResultBackEffect = "unknown"
)

func (r CommandResultBackEffect) IsKnown() bool {
	switch r {
	case CommandResultBackEffectNone, CommandResultBackEffectApplied, CommandResultBackEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultBack) implementsCommandResult() {}

// Result of a forward command.
type CommandResultForward struct {
	Command CommandResultForwardCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultForwardEffect `json:"effect" api:"required"`
	Ok     bool                       `json:"ok" api:"required"`
	Error  CommandErrorInfo           `json:"error"`
	// False when there was no history entry.
	Navigated bool                     `json:"navigated"`
	URL       string                   `json:"url"`
	JSON      commandResultForwardJSON `json:"-"`
}

// commandResultForwardJSON contains the JSON metadata for the struct
// [CommandResultForward]
type commandResultForwardJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	Navigated   apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultForward) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultForwardJSON) RawJSON() string {
	return r.raw
}

type CommandResultForwardCommand string

const (
	CommandResultForwardCommandForward CommandResultForwardCommand = "forward"
)

func (r CommandResultForwardCommand) IsKnown() bool {
	switch r {
	case CommandResultForwardCommandForward:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultForwardEffect string

const (
	CommandResultForwardEffectNone    CommandResultForwardEffect = "none"
	CommandResultForwardEffectApplied CommandResultForwardEffect = "applied"
	CommandResultForwardEffectUnknown CommandResultForwardEffect = "unknown"
)

func (r CommandResultForwardEffect) IsKnown() bool {
	switch r {
	case CommandResultForwardEffectNone, CommandResultForwardEffectApplied, CommandResultForwardEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultForward) implementsCommandResult() {}

// Result of a reload command.
type CommandResultReload struct {
	Command CommandResultReloadCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultReloadEffect `json:"effect" api:"required"`
	Ok     bool                      `json:"ok" api:"required"`
	Error  CommandErrorInfo          `json:"error"`
	URL    string                    `json:"url"`
	JSON   commandResultReloadJSON   `json:"-"`
}

// commandResultReloadJSON contains the JSON metadata for the struct
// [CommandResultReload]
type commandResultReloadJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultReload) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultReloadJSON) RawJSON() string {
	return r.raw
}

type CommandResultReloadCommand string

const (
	CommandResultReloadCommandReload CommandResultReloadCommand = "reload"
)

func (r CommandResultReloadCommand) IsKnown() bool {
	switch r {
	case CommandResultReloadCommandReload:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultReloadEffect string

const (
	CommandResultReloadEffectNone    CommandResultReloadEffect = "none"
	CommandResultReloadEffectApplied CommandResultReloadEffect = "applied"
	CommandResultReloadEffectUnknown CommandResultReloadEffect = "unknown"
)

func (r CommandResultReloadEffect) IsKnown() bool {
	switch r {
	case CommandResultReloadEffectNone, CommandResultReloadEffectApplied, CommandResultReloadEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultReload) implementsCommandResult() {}

// Result of a snapshot command.
type CommandResultSnapshot struct {
	Command CommandResultSnapshotCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect     CommandResultSnapshotEffect `json:"effect" api:"required"`
	Ok         bool                        `json:"ok" api:"required"`
	Error      CommandErrorInfo            `json:"error"`
	Snapshot   string                      `json:"snapshot"`
	SnapshotID string                      `json:"snapshotId"`
	JSON       commandResultSnapshotJSON   `json:"-"`
}

// commandResultSnapshotJSON contains the JSON metadata for the struct
// [CommandResultSnapshot]
type commandResultSnapshotJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	Snapshot    apijson.Field
	SnapshotID  apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultSnapshot) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultSnapshotJSON) RawJSON() string {
	return r.raw
}

type CommandResultSnapshotCommand string

const (
	CommandResultSnapshotCommandSnapshot CommandResultSnapshotCommand = "snapshot"
)

func (r CommandResultSnapshotCommand) IsKnown() bool {
	switch r {
	case CommandResultSnapshotCommandSnapshot:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultSnapshotEffect string

const (
	CommandResultSnapshotEffectNone    CommandResultSnapshotEffect = "none"
	CommandResultSnapshotEffectApplied CommandResultSnapshotEffect = "applied"
	CommandResultSnapshotEffectUnknown CommandResultSnapshotEffect = "unknown"
)

func (r CommandResultSnapshotEffect) IsKnown() bool {
	switch r {
	case CommandResultSnapshotEffectNone, CommandResultSnapshotEffectApplied, CommandResultSnapshotEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultSnapshot) implementsCommandResult() {}

// Result of a click command.
type CommandResultClick struct {
	Command CommandResultClickCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultClickEffect `json:"effect" api:"required"`
	Ok     bool                     `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                   `json:"loading"`
	JSON    commandResultClickJSON `json:"-"`
}

// commandResultClickJSON contains the JSON metadata for the struct
// [CommandResultClick]
type commandResultClickJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultClick) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultClickJSON) RawJSON() string {
	return r.raw
}

type CommandResultClickCommand string

const (
	CommandResultClickCommandClick CommandResultClickCommand = "click"
)

func (r CommandResultClickCommand) IsKnown() bool {
	switch r {
	case CommandResultClickCommandClick:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultClickEffect string

const (
	CommandResultClickEffectNone    CommandResultClickEffect = "none"
	CommandResultClickEffectApplied CommandResultClickEffect = "applied"
	CommandResultClickEffectUnknown CommandResultClickEffect = "unknown"
)

func (r CommandResultClickEffect) IsKnown() bool {
	switch r {
	case CommandResultClickEffectNone, CommandResultClickEffectApplied, CommandResultClickEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultClick) implementsCommandResult() {}

// Result of a dblclick command.
type CommandResultDblclick struct {
	Command CommandResultDblclickCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultDblclickEffect `json:"effect" api:"required"`
	Ok     bool                        `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                      `json:"loading"`
	JSON    commandResultDblclickJSON `json:"-"`
}

// commandResultDblclickJSON contains the JSON metadata for the struct
// [CommandResultDblclick]
type commandResultDblclickJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultDblclick) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultDblclickJSON) RawJSON() string {
	return r.raw
}

type CommandResultDblclickCommand string

const (
	CommandResultDblclickCommandDblclick CommandResultDblclickCommand = "dblclick"
)

func (r CommandResultDblclickCommand) IsKnown() bool {
	switch r {
	case CommandResultDblclickCommandDblclick:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultDblclickEffect string

const (
	CommandResultDblclickEffectNone    CommandResultDblclickEffect = "none"
	CommandResultDblclickEffectApplied CommandResultDblclickEffect = "applied"
	CommandResultDblclickEffectUnknown CommandResultDblclickEffect = "unknown"
)

func (r CommandResultDblclickEffect) IsKnown() bool {
	switch r {
	case CommandResultDblclickEffectNone, CommandResultDblclickEffectApplied, CommandResultDblclickEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultDblclick) implementsCommandResult() {}

// Result of a hover command.
type CommandResultHover struct {
	Command CommandResultHoverCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultHoverEffect `json:"effect" api:"required"`
	Ok     bool                     `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog          `json:"dialog"`
	Error  CommandErrorInfo       `json:"error"`
	JSON   commandResultHoverJSON `json:"-"`
}

// commandResultHoverJSON contains the JSON metadata for the struct
// [CommandResultHover]
type commandResultHoverJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultHover) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultHoverJSON) RawJSON() string {
	return r.raw
}

type CommandResultHoverCommand string

const (
	CommandResultHoverCommandHover CommandResultHoverCommand = "hover"
)

func (r CommandResultHoverCommand) IsKnown() bool {
	switch r {
	case CommandResultHoverCommandHover:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultHoverEffect string

const (
	CommandResultHoverEffectNone    CommandResultHoverEffect = "none"
	CommandResultHoverEffectApplied CommandResultHoverEffect = "applied"
	CommandResultHoverEffectUnknown CommandResultHoverEffect = "unknown"
)

func (r CommandResultHoverEffect) IsKnown() bool {
	switch r {
	case CommandResultHoverEffectNone, CommandResultHoverEffectApplied, CommandResultHoverEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultHover) implementsCommandResult() {}

// Result of a focus command.
type CommandResultFocus struct {
	Command CommandResultFocusCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultFocusEffect `json:"effect" api:"required"`
	Ok     bool                     `json:"ok" api:"required"`
	Error  CommandErrorInfo         `json:"error"`
	JSON   commandResultFocusJSON   `json:"-"`
}

// commandResultFocusJSON contains the JSON metadata for the struct
// [CommandResultFocus]
type commandResultFocusJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultFocus) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultFocusJSON) RawJSON() string {
	return r.raw
}

type CommandResultFocusCommand string

const (
	CommandResultFocusCommandFocus CommandResultFocusCommand = "focus"
)

func (r CommandResultFocusCommand) IsKnown() bool {
	switch r {
	case CommandResultFocusCommandFocus:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultFocusEffect string

const (
	CommandResultFocusEffectNone    CommandResultFocusEffect = "none"
	CommandResultFocusEffectApplied CommandResultFocusEffect = "applied"
	CommandResultFocusEffectUnknown CommandResultFocusEffect = "unknown"
)

func (r CommandResultFocusEffect) IsKnown() bool {
	switch r {
	case CommandResultFocusEffectNone, CommandResultFocusEffectApplied, CommandResultFocusEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultFocus) implementsCommandResult() {}

// Result of a type command.
type CommandResultType struct {
	Command CommandResultTypeCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultTypeEffect `json:"effect" api:"required"`
	Ok     bool                    `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                  `json:"loading"`
	JSON    commandResultTypeJSON `json:"-"`
}

// commandResultTypeJSON contains the JSON metadata for the struct
// [CommandResultType]
type commandResultTypeJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultType) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultTypeJSON) RawJSON() string {
	return r.raw
}

type CommandResultTypeCommand string

const (
	CommandResultTypeCommandType CommandResultTypeCommand = "type"
)

func (r CommandResultTypeCommand) IsKnown() bool {
	switch r {
	case CommandResultTypeCommandType:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultTypeEffect string

const (
	CommandResultTypeEffectNone    CommandResultTypeEffect = "none"
	CommandResultTypeEffectApplied CommandResultTypeEffect = "applied"
	CommandResultTypeEffectUnknown CommandResultTypeEffect = "unknown"
)

func (r CommandResultTypeEffect) IsKnown() bool {
	switch r {
	case CommandResultTypeEffectNone, CommandResultTypeEffectApplied, CommandResultTypeEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultType) implementsCommandResult() {}

// Result of a fill command.
type CommandResultFill struct {
	Command CommandResultFillCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultFillEffect `json:"effect" api:"required"`
	Ok     bool                    `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                  `json:"loading"`
	JSON    commandResultFillJSON `json:"-"`
}

// commandResultFillJSON contains the JSON metadata for the struct
// [CommandResultFill]
type commandResultFillJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultFill) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultFillJSON) RawJSON() string {
	return r.raw
}

type CommandResultFillCommand string

const (
	CommandResultFillCommandFill CommandResultFillCommand = "fill"
)

func (r CommandResultFillCommand) IsKnown() bool {
	switch r {
	case CommandResultFillCommandFill:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultFillEffect string

const (
	CommandResultFillEffectNone    CommandResultFillEffect = "none"
	CommandResultFillEffectApplied CommandResultFillEffect = "applied"
	CommandResultFillEffectUnknown CommandResultFillEffect = "unknown"
)

func (r CommandResultFillEffect) IsKnown() bool {
	switch r {
	case CommandResultFillEffectNone, CommandResultFillEffectApplied, CommandResultFillEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultFill) implementsCommandResult() {}

// Result of a press command.
type CommandResultPress struct {
	Command CommandResultPressCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultPressEffect `json:"effect" api:"required"`
	Ok     bool                     `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                   `json:"loading"`
	JSON    commandResultPressJSON `json:"-"`
}

// commandResultPressJSON contains the JSON metadata for the struct
// [CommandResultPress]
type commandResultPressJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultPress) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultPressJSON) RawJSON() string {
	return r.raw
}

type CommandResultPressCommand string

const (
	CommandResultPressCommandPress CommandResultPressCommand = "press"
)

func (r CommandResultPressCommand) IsKnown() bool {
	switch r {
	case CommandResultPressCommandPress:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultPressEffect string

const (
	CommandResultPressEffectNone    CommandResultPressEffect = "none"
	CommandResultPressEffectApplied CommandResultPressEffect = "applied"
	CommandResultPressEffectUnknown CommandResultPressEffect = "unknown"
)

func (r CommandResultPressEffect) IsKnown() bool {
	switch r {
	case CommandResultPressEffectNone, CommandResultPressEffectApplied, CommandResultPressEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultPress) implementsCommandResult() {}

// Result of a keydown command.
type CommandResultKeydown struct {
	Command CommandResultKeydownCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultKeydownEffect `json:"effect" api:"required"`
	Ok     bool                       `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                     `json:"loading"`
	JSON    commandResultKeydownJSON `json:"-"`
}

// commandResultKeydownJSON contains the JSON metadata for the struct
// [CommandResultKeydown]
type commandResultKeydownJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultKeydown) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultKeydownJSON) RawJSON() string {
	return r.raw
}

type CommandResultKeydownCommand string

const (
	CommandResultKeydownCommandKeydown CommandResultKeydownCommand = "keydown"
)

func (r CommandResultKeydownCommand) IsKnown() bool {
	switch r {
	case CommandResultKeydownCommandKeydown:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultKeydownEffect string

const (
	CommandResultKeydownEffectNone    CommandResultKeydownEffect = "none"
	CommandResultKeydownEffectApplied CommandResultKeydownEffect = "applied"
	CommandResultKeydownEffectUnknown CommandResultKeydownEffect = "unknown"
)

func (r CommandResultKeydownEffect) IsKnown() bool {
	switch r {
	case CommandResultKeydownEffectNone, CommandResultKeydownEffectApplied, CommandResultKeydownEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultKeydown) implementsCommandResult() {}

// Result of a keyup command.
type CommandResultKeyup struct {
	Command CommandResultKeyupCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultKeyupEffect `json:"effect" api:"required"`
	Ok     bool                     `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                   `json:"loading"`
	JSON    commandResultKeyupJSON `json:"-"`
}

// commandResultKeyupJSON contains the JSON metadata for the struct
// [CommandResultKeyup]
type commandResultKeyupJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultKeyup) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultKeyupJSON) RawJSON() string {
	return r.raw
}

type CommandResultKeyupCommand string

const (
	CommandResultKeyupCommandKeyup CommandResultKeyupCommand = "keyup"
)

func (r CommandResultKeyupCommand) IsKnown() bool {
	switch r {
	case CommandResultKeyupCommandKeyup:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultKeyupEffect string

const (
	CommandResultKeyupEffectNone    CommandResultKeyupEffect = "none"
	CommandResultKeyupEffectApplied CommandResultKeyupEffect = "applied"
	CommandResultKeyupEffectUnknown CommandResultKeyupEffect = "unknown"
)

func (r CommandResultKeyupEffect) IsKnown() bool {
	switch r {
	case CommandResultKeyupEffectNone, CommandResultKeyupEffectApplied, CommandResultKeyupEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultKeyup) implementsCommandResult() {}

// Result of a mouse command.
type CommandResultMouse struct {
	Command CommandResultMouseCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultMouseEffect `json:"effect" api:"required"`
	Ok     bool                     `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                   `json:"loading"`
	JSON    commandResultMouseJSON `json:"-"`
}

// commandResultMouseJSON contains the JSON metadata for the struct
// [CommandResultMouse]
type commandResultMouseJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultMouse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultMouseJSON) RawJSON() string {
	return r.raw
}

type CommandResultMouseCommand string

const (
	CommandResultMouseCommandMouse CommandResultMouseCommand = "mouse"
)

func (r CommandResultMouseCommand) IsKnown() bool {
	switch r {
	case CommandResultMouseCommandMouse:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultMouseEffect string

const (
	CommandResultMouseEffectNone    CommandResultMouseEffect = "none"
	CommandResultMouseEffectApplied CommandResultMouseEffect = "applied"
	CommandResultMouseEffectUnknown CommandResultMouseEffect = "unknown"
)

func (r CommandResultMouseEffect) IsKnown() bool {
	switch r {
	case CommandResultMouseEffectNone, CommandResultMouseEffectApplied, CommandResultMouseEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultMouse) implementsCommandResult() {}

// Result of a select command.
type CommandResultSelect struct {
	Command CommandResultSelectCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultSelectEffect `json:"effect" api:"required"`
	Ok     bool                      `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool `json:"loading"`
	// The values of the selected options.
	Selected []string                `json:"selected"`
	JSON     commandResultSelectJSON `json:"-"`
}

// commandResultSelectJSON contains the JSON metadata for the struct
// [CommandResultSelect]
type commandResultSelectJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	Selected    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultSelect) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultSelectJSON) RawJSON() string {
	return r.raw
}

type CommandResultSelectCommand string

const (
	CommandResultSelectCommandSelect CommandResultSelectCommand = "select"
)

func (r CommandResultSelectCommand) IsKnown() bool {
	switch r {
	case CommandResultSelectCommandSelect:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultSelectEffect string

const (
	CommandResultSelectEffectNone    CommandResultSelectEffect = "none"
	CommandResultSelectEffectApplied CommandResultSelectEffect = "applied"
	CommandResultSelectEffectUnknown CommandResultSelectEffect = "unknown"
)

func (r CommandResultSelectEffect) IsKnown() bool {
	switch r {
	case CommandResultSelectEffectNone, CommandResultSelectEffectApplied, CommandResultSelectEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultSelect) implementsCommandResult() {}

// Result of a check command.
type CommandResultCheck struct {
	Command CommandResultCheckCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect  CommandResultCheckEffect `json:"effect" api:"required"`
	Ok      bool                     `json:"ok" api:"required"`
	Checked bool                     `json:"checked"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                   `json:"loading"`
	JSON    commandResultCheckJSON `json:"-"`
}

// commandResultCheckJSON contains the JSON metadata for the struct
// [CommandResultCheck]
type commandResultCheckJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Checked     apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultCheck) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultCheckJSON) RawJSON() string {
	return r.raw
}

type CommandResultCheckCommand string

const (
	CommandResultCheckCommandCheck CommandResultCheckCommand = "check"
)

func (r CommandResultCheckCommand) IsKnown() bool {
	switch r {
	case CommandResultCheckCommandCheck:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultCheckEffect string

const (
	CommandResultCheckEffectNone    CommandResultCheckEffect = "none"
	CommandResultCheckEffectApplied CommandResultCheckEffect = "applied"
	CommandResultCheckEffectUnknown CommandResultCheckEffect = "unknown"
)

func (r CommandResultCheckEffect) IsKnown() bool {
	switch r {
	case CommandResultCheckEffectNone, CommandResultCheckEffectApplied, CommandResultCheckEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultCheck) implementsCommandResult() {}

// Result of a uncheck command.
type CommandResultUncheck struct {
	Command CommandResultUncheckCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect  CommandResultUncheckEffect `json:"effect" api:"required"`
	Ok      bool                       `json:"ok" api:"required"`
	Checked bool                       `json:"checked"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                     `json:"loading"`
	JSON    commandResultUncheckJSON `json:"-"`
}

// commandResultUncheckJSON contains the JSON metadata for the struct
// [CommandResultUncheck]
type commandResultUncheckJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Checked     apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultUncheck) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultUncheckJSON) RawJSON() string {
	return r.raw
}

type CommandResultUncheckCommand string

const (
	CommandResultUncheckCommandUncheck CommandResultUncheckCommand = "uncheck"
)

func (r CommandResultUncheckCommand) IsKnown() bool {
	switch r {
	case CommandResultUncheckCommandUncheck:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultUncheckEffect string

const (
	CommandResultUncheckEffectNone    CommandResultUncheckEffect = "none"
	CommandResultUncheckEffectApplied CommandResultUncheckEffect = "applied"
	CommandResultUncheckEffectUnknown CommandResultUncheckEffect = "unknown"
)

func (r CommandResultUncheckEffect) IsKnown() bool {
	switch r {
	case CommandResultUncheckEffectNone, CommandResultUncheckEffectApplied, CommandResultUncheckEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultUncheck) implementsCommandResult() {}

// Result of a upload command.
type CommandResultUpload struct {
	Command CommandResultUploadCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultUploadEffect `json:"effect" api:"required"`
	Ok     bool                      `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	Files  []string         `json:"files"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                    `json:"loading"`
	JSON    commandResultUploadJSON `json:"-"`
}

// commandResultUploadJSON contains the JSON metadata for the struct
// [CommandResultUpload]
type commandResultUploadJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Files       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultUpload) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultUploadJSON) RawJSON() string {
	return r.raw
}

type CommandResultUploadCommand string

const (
	CommandResultUploadCommandUpload CommandResultUploadCommand = "upload"
)

func (r CommandResultUploadCommand) IsKnown() bool {
	switch r {
	case CommandResultUploadCommandUpload:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultUploadEffect string

const (
	CommandResultUploadEffectNone    CommandResultUploadEffect = "none"
	CommandResultUploadEffectApplied CommandResultUploadEffect = "applied"
	CommandResultUploadEffectUnknown CommandResultUploadEffect = "unknown"
)

func (r CommandResultUploadEffect) IsKnown() bool {
	switch r {
	case CommandResultUploadEffectNone, CommandResultUploadEffectApplied, CommandResultUploadEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultUpload) implementsCommandResult() {}

// Result of a drag command.
type CommandResultDrag struct {
	Command CommandResultDragCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultDragEffect `json:"effect" api:"required"`
	Ok     bool                    `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool                  `json:"loading"`
	JSON    commandResultDragJSON `json:"-"`
}

// commandResultDragJSON contains the JSON metadata for the struct
// [CommandResultDrag]
type commandResultDragJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultDrag) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultDragJSON) RawJSON() string {
	return r.raw
}

type CommandResultDragCommand string

const (
	CommandResultDragCommandDrag CommandResultDragCommand = "drag"
)

func (r CommandResultDragCommand) IsKnown() bool {
	switch r {
	case CommandResultDragCommandDrag:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultDragEffect string

const (
	CommandResultDragEffectNone    CommandResultDragEffect = "none"
	CommandResultDragEffectApplied CommandResultDragEffect = "applied"
	CommandResultDragEffectUnknown CommandResultDragEffect = "unknown"
)

func (r CommandResultDragEffect) IsKnown() bool {
	switch r {
	case CommandResultDragEffectNone, CommandResultDragEffectApplied, CommandResultDragEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultDrag) implementsCommandResult() {}

// Result of a get command.
type CommandResultGet struct {
	Command CommandResultGetCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultGetEffect `json:"effect" api:"required"`
	Ok     bool                   `json:"ok" api:"required"`
	Error  CommandErrorInfo       `json:"error"`
	// The value is a secret, so it is left out.
	Masked bool `json:"masked"`
	// A string for text, html, markdown, value, attr, title, and url. A number for
	// count. { x, y, width, height } or null for box. A list of locators for
	// locator.
	Value interface{}          `json:"value"`
	JSON  commandResultGetJSON `json:"-"`
}

// commandResultGetJSON contains the JSON metadata for the struct
// [CommandResultGet]
type commandResultGetJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	Masked      apijson.Field
	Value       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultGet) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultGetJSON) RawJSON() string {
	return r.raw
}

type CommandResultGetCommand string

const (
	CommandResultGetCommandGet CommandResultGetCommand = "get"
)

func (r CommandResultGetCommand) IsKnown() bool {
	switch r {
	case CommandResultGetCommandGet:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultGetEffect string

const (
	CommandResultGetEffectNone    CommandResultGetEffect = "none"
	CommandResultGetEffectApplied CommandResultGetEffect = "applied"
	CommandResultGetEffectUnknown CommandResultGetEffect = "unknown"
)

func (r CommandResultGetEffect) IsKnown() bool {
	switch r {
	case CommandResultGetEffectNone, CommandResultGetEffectApplied, CommandResultGetEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultGet) implementsCommandResult() {}

// Result of a is command.
type CommandResultIs struct {
	Command CommandResultIsCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultIsEffect `json:"effect" api:"required"`
	Ok     bool                  `json:"ok" api:"required"`
	Error  CommandErrorInfo      `json:"error"`
	Value  bool                  `json:"value"`
	JSON   commandResultIsJSON   `json:"-"`
}

// commandResultIsJSON contains the JSON metadata for the struct
// [CommandResultIs]
type commandResultIsJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	Value       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultIs) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultIsJSON) RawJSON() string {
	return r.raw
}

type CommandResultIsCommand string

const (
	CommandResultIsCommandIs CommandResultIsCommand = "is"
)

func (r CommandResultIsCommand) IsKnown() bool {
	switch r {
	case CommandResultIsCommandIs:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultIsEffect string

const (
	CommandResultIsEffectNone    CommandResultIsEffect = "none"
	CommandResultIsEffectApplied CommandResultIsEffect = "applied"
	CommandResultIsEffectUnknown CommandResultIsEffect = "unknown"
)

func (r CommandResultIsEffect) IsKnown() bool {
	switch r {
	case CommandResultIsEffectNone, CommandResultIsEffectApplied, CommandResultIsEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultIs) implementsCommandResult() {}

// Result of a wait command.
type CommandResultWait struct {
	Command CommandResultWaitCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultWaitEffect `json:"effect" api:"required"`
	Ok     bool                    `json:"ok" api:"required"`
	Bytes  int64                   `json:"bytes"`
	Error  CommandErrorInfo        `json:"error"`
	// The downloaded file, for download.
	File   string                  `json:"file"`
	Waited CommandResultWaitWaited `json:"waited"`
	JSON   commandResultWaitJSON   `json:"-"`
}

// commandResultWaitJSON contains the JSON metadata for the struct
// [CommandResultWait]
type commandResultWaitJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Bytes       apijson.Field
	Error       apijson.Field
	File        apijson.Field
	Waited      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultWait) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultWaitJSON) RawJSON() string {
	return r.raw
}

type CommandResultWaitCommand string

const (
	CommandResultWaitCommandWait CommandResultWaitCommand = "wait"
)

func (r CommandResultWaitCommand) IsKnown() bool {
	switch r {
	case CommandResultWaitCommandWait:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultWaitEffect string

const (
	CommandResultWaitEffectNone    CommandResultWaitEffect = "none"
	CommandResultWaitEffectApplied CommandResultWaitEffect = "applied"
	CommandResultWaitEffectUnknown CommandResultWaitEffect = "unknown"
)

func (r CommandResultWaitEffect) IsKnown() bool {
	switch r {
	case CommandResultWaitEffectNone, CommandResultWaitEffectApplied, CommandResultWaitEffectUnknown:
		return true
	}
	return false
}

type CommandResultWaitWaited string

const (
	CommandResultWaitWaitedMs       CommandResultWaitWaited = "ms"
	CommandResultWaitWaitedTarget   CommandResultWaitWaited = "target"
	CommandResultWaitWaitedText     CommandResultWaitWaited = "text"
	CommandResultWaitWaitedURL      CommandResultWaitWaited = "url"
	CommandResultWaitWaitedLoad     CommandResultWaitWaited = "load"
	CommandResultWaitWaitedDownload CommandResultWaitWaited = "download"
)

func (r CommandResultWaitWaited) IsKnown() bool {
	switch r {
	case CommandResultWaitWaitedMs, CommandResultWaitWaitedTarget, CommandResultWaitWaitedText, CommandResultWaitWaitedURL, CommandResultWaitWaitedLoad, CommandResultWaitWaitedDownload:
		return true
	}
	return false
}

func (r CommandResultWait) implementsCommandResult() {}

// Result of a screenshot command.
type CommandResultScreenshot struct {
	Command CommandResultScreenshotCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultScreenshotEffect `json:"effect" api:"required"`
	Ok     bool                          `json:"ok" api:"required"`
	Data   string                        `json:"data"`
	Error  CommandErrorInfo              `json:"error"`
	Format CommandResultScreenshotFormat `json:"format"`
	JSON   commandResultScreenshotJSON   `json:"-"`
}

// commandResultScreenshotJSON contains the JSON metadata for the struct
// [CommandResultScreenshot]
type commandResultScreenshotJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Data        apijson.Field
	Error       apijson.Field
	Format      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultScreenshot) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultScreenshotJSON) RawJSON() string {
	return r.raw
}

type CommandResultScreenshotCommand string

const (
	CommandResultScreenshotCommandScreenshot CommandResultScreenshotCommand = "screenshot"
)

func (r CommandResultScreenshotCommand) IsKnown() bool {
	switch r {
	case CommandResultScreenshotCommandScreenshot:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultScreenshotEffect string

const (
	CommandResultScreenshotEffectNone    CommandResultScreenshotEffect = "none"
	CommandResultScreenshotEffectApplied CommandResultScreenshotEffect = "applied"
	CommandResultScreenshotEffectUnknown CommandResultScreenshotEffect = "unknown"
)

func (r CommandResultScreenshotEffect) IsKnown() bool {
	switch r {
	case CommandResultScreenshotEffectNone, CommandResultScreenshotEffectApplied, CommandResultScreenshotEffectUnknown:
		return true
	}
	return false
}

type CommandResultScreenshotFormat string

const (
	CommandResultScreenshotFormatPng  CommandResultScreenshotFormat = "png"
	CommandResultScreenshotFormatJpeg CommandResultScreenshotFormat = "jpeg"
	CommandResultScreenshotFormatWebp CommandResultScreenshotFormat = "webp"
)

func (r CommandResultScreenshotFormat) IsKnown() bool {
	switch r {
	case CommandResultScreenshotFormatPng, CommandResultScreenshotFormatJpeg, CommandResultScreenshotFormatWebp:
		return true
	}
	return false
}

func (r CommandResultScreenshot) implementsCommandResult() {}

// Result of a eval command.
type CommandResultEval struct {
	Command CommandResultEvalCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultEvalEffect `json:"effect" api:"required"`
	Ok     bool                    `json:"ok" api:"required"`
	// A dialog that the command opened. It blocks the tab until it closes.
	Dialog CommandDialog    `json:"dialog"`
	Error  CommandErrorInfo `json:"error"`
	// The command started a navigation that was still loading when it returned.
	Loading bool `json:"loading"`
	// The result of the script as JSON.
	Value interface{}           `json:"value"`
	JSON  commandResultEvalJSON `json:"-"`
}

// commandResultEvalJSON contains the JSON metadata for the struct
// [CommandResultEval]
type commandResultEvalJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Dialog      apijson.Field
	Error       apijson.Field
	Loading     apijson.Field
	Value       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultEval) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultEvalJSON) RawJSON() string {
	return r.raw
}

type CommandResultEvalCommand string

const (
	CommandResultEvalCommandEval CommandResultEvalCommand = "eval"
)

func (r CommandResultEvalCommand) IsKnown() bool {
	switch r {
	case CommandResultEvalCommandEval:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultEvalEffect string

const (
	CommandResultEvalEffectNone    CommandResultEvalEffect = "none"
	CommandResultEvalEffectApplied CommandResultEvalEffect = "applied"
	CommandResultEvalEffectUnknown CommandResultEvalEffect = "unknown"
)

func (r CommandResultEvalEffect) IsKnown() bool {
	switch r {
	case CommandResultEvalEffectNone, CommandResultEvalEffectApplied, CommandResultEvalEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultEval) implementsCommandResult() {}

// Result of a pdf command.
type CommandResultPdf struct {
	Command CommandResultPdfCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultPdfEffect `json:"effect" api:"required"`
	Ok     bool                   `json:"ok" api:"required"`
	Data   string                 `json:"data"`
	Error  CommandErrorInfo       `json:"error"`
	Format CommandResultPdfFormat `json:"format"`
	JSON   commandResultPdfJSON   `json:"-"`
}

// commandResultPdfJSON contains the JSON metadata for the struct
// [CommandResultPdf]
type commandResultPdfJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Data        apijson.Field
	Error       apijson.Field
	Format      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultPdf) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultPdfJSON) RawJSON() string {
	return r.raw
}

type CommandResultPdfCommand string

const (
	CommandResultPdfCommandPdf CommandResultPdfCommand = "pdf"
)

func (r CommandResultPdfCommand) IsKnown() bool {
	switch r {
	case CommandResultPdfCommandPdf:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultPdfEffect string

const (
	CommandResultPdfEffectNone    CommandResultPdfEffect = "none"
	CommandResultPdfEffectApplied CommandResultPdfEffect = "applied"
	CommandResultPdfEffectUnknown CommandResultPdfEffect = "unknown"
)

func (r CommandResultPdfEffect) IsKnown() bool {
	switch r {
	case CommandResultPdfEffectNone, CommandResultPdfEffectApplied, CommandResultPdfEffectUnknown:
		return true
	}
	return false
}

type CommandResultPdfFormat string

const (
	CommandResultPdfFormatPdf CommandResultPdfFormat = "pdf"
)

func (r CommandResultPdfFormat) IsKnown() bool {
	switch r {
	case CommandResultPdfFormatPdf:
		return true
	}
	return false
}

func (r CommandResultPdf) implementsCommandResult() {}

// Result of a scroll command.
type CommandResultScroll struct {
	Command CommandResultScrollCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultScrollEffect `json:"effect" api:"required"`
	Ok     bool                      `json:"ok" api:"required"`
	Error  CommandErrorInfo          `json:"error"`
	Moved  bool                      `json:"moved"`
	X      float64                   `json:"x"`
	Y      float64                   `json:"y"`
	JSON   commandResultScrollJSON   `json:"-"`
}

// commandResultScrollJSON contains the JSON metadata for the struct
// [CommandResultScroll]
type commandResultScrollJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	Moved       apijson.Field
	X           apijson.Field
	Y           apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultScroll) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultScrollJSON) RawJSON() string {
	return r.raw
}

type CommandResultScrollCommand string

const (
	CommandResultScrollCommandScroll CommandResultScrollCommand = "scroll"
)

func (r CommandResultScrollCommand) IsKnown() bool {
	switch r {
	case CommandResultScrollCommandScroll:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultScrollEffect string

const (
	CommandResultScrollEffectNone    CommandResultScrollEffect = "none"
	CommandResultScrollEffectApplied CommandResultScrollEffect = "applied"
	CommandResultScrollEffectUnknown CommandResultScrollEffect = "unknown"
)

func (r CommandResultScrollEffect) IsKnown() bool {
	switch r {
	case CommandResultScrollEffectNone, CommandResultScrollEffectApplied, CommandResultScrollEffectUnknown:
		return true
	}
	return false
}

func (r CommandResultScroll) implementsCommandResult() {}

// Result of a find command.
type CommandResultFind struct {
	Command CommandResultFindCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect  CommandResultFindEffect  `json:"effect" api:"required"`
	Ok      bool                     `json:"ok" api:"required"`
	Count   int64                    `json:"count"`
	Error   CommandErrorInfo         `json:"error"`
	Matches []CommandResultFindMatch `json:"matches"`
	JSON    commandResultFindJSON    `json:"-"`
}

// commandResultFindJSON contains the JSON metadata for the struct
// [CommandResultFind]
type commandResultFindJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Count       apijson.Field
	Error       apijson.Field
	Matches     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultFind) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultFindJSON) RawJSON() string {
	return r.raw
}

type CommandResultFindCommand string

const (
	CommandResultFindCommandFind CommandResultFindCommand = "find"
)

func (r CommandResultFindCommand) IsKnown() bool {
	switch r {
	case CommandResultFindCommandFind:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultFindEffect string

const (
	CommandResultFindEffectNone    CommandResultFindEffect = "none"
	CommandResultFindEffectApplied CommandResultFindEffect = "applied"
	CommandResultFindEffectUnknown CommandResultFindEffect = "unknown"
)

func (r CommandResultFindEffect) IsKnown() bool {
	switch r {
	case CommandResultFindEffectNone, CommandResultFindEffectApplied, CommandResultFindEffectUnknown:
		return true
	}
	return false
}

type CommandResultFindMatch struct {
	Name string                     `json:"name" api:"required"`
	Ref  string                     `json:"ref" api:"required"`
	Role string                     `json:"role" api:"required"`
	JSON commandResultFindMatchJSON `json:"-"`
}

// commandResultFindMatchJSON contains the JSON metadata for the struct
// [CommandResultFindMatch]
type commandResultFindMatchJSON struct {
	Name        apijson.Field
	Ref         apijson.Field
	Role        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultFindMatch) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultFindMatchJSON) RawJSON() string {
	return r.raw
}

func (r CommandResultFind) implementsCommandResult() {}

// Result of a tab command.
type CommandResultTab struct {
	Command CommandResultTabCommand `json:"command" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect CommandResultTabEffect `json:"effect" api:"required"`
	Ok     bool                   `json:"ok" api:"required"`
	Error  CommandErrorInfo       `json:"error"`
	Tab    string                 `json:"tab"`
	Tabs   []CommandResultTabTab  `json:"tabs"`
	JSON   commandResultTabJSON   `json:"-"`
}

// commandResultTabJSON contains the JSON metadata for the struct
// [CommandResultTab]
type commandResultTabJSON struct {
	Command     apijson.Field
	Effect      apijson.Field
	Ok          apijson.Field
	Error       apijson.Field
	Tab         apijson.Field
	Tabs        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultTab) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultTabJSON) RawJSON() string {
	return r.raw
}

type CommandResultTabCommand string

const (
	CommandResultTabCommandTab CommandResultTabCommand = "tab"
)

func (r CommandResultTabCommand) IsKnown() bool {
	switch r {
	case CommandResultTabCommandTab:
		return true
	}
	return false
}

// What happened to the page: nothing, a change, or a possible change.
type CommandResultTabEffect string

const (
	CommandResultTabEffectNone    CommandResultTabEffect = "none"
	CommandResultTabEffectApplied CommandResultTabEffect = "applied"
	CommandResultTabEffectUnknown CommandResultTabEffect = "unknown"
)

func (r CommandResultTabEffect) IsKnown() bool {
	switch r {
	case CommandResultTabEffectNone, CommandResultTabEffectApplied, CommandResultTabEffectUnknown:
		return true
	}
	return false
}

type CommandResultTabTab struct {
	Tab   string                  `json:"tab" api:"required"`
	Title string                  `json:"title" api:"required"`
	URL   string                  `json:"url" api:"required"`
	JSON  commandResultTabTabJSON `json:"-"`
}

// commandResultTabTabJSON contains the JSON metadata for the struct
// [CommandResultTabTab]
type commandResultTabTabJSON struct {
	Tab         apijson.Field
	Title       apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *CommandResultTabTab) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r commandResultTabTabJSON) RawJSON() string {
	return r.raw
}

func (r CommandResultTab) implementsCommandResult() {}

// A ref from a snapshot such as e12, a CSS selector, or a locator.
// Satisfied by [shared.UnionString], [CommandLocatorParam].
type CommandTargetUnionParam interface {
	ImplementsCommandTargetUnionParam()
}

func (r CommandLocatorParam) ImplementsCommandTargetUnionParam() {}

type CommandTargetParam = CommandTargetUnionParam

// Represents the data structure for a browser session, including its
// configuration and status.
type Session struct {
	// Unique identifier for the session
	ID string `json:"id" api:"required" format:"uuid"`
	// Browser mode used for this session.
	BrowserMode SessionBrowserMode `json:"browserMode" api:"required"`
	// Timestamp when the session started
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// Amount of credits consumed by the session
	CreditsUsed int64 `json:"creditsUsed" api:"required"`
	// URL for debugging the session
	DebugURL string `json:"debugUrl" api:"required"`
	// Viewport and browser window dimensions for the session
	Dimensions SessionDimensions `json:"dimensions" api:"required"`
	// Duration of the session in milliseconds
	Duration int64 `json:"duration" api:"required"`
	// Number of events processed in the session
	EventCount int64 `json:"eventCount" api:"required"`
	// Bandwidth optimizations that were applied to the session.
	OptimizeBandwidth SessionOptimizeBandwidth `json:"optimizeBandwidth" api:"required"`
	// Amount of data transmitted through the proxy
	ProxyBytesUsed int64 `json:"proxyBytesUsed" api:"required"`
	// Source of the proxy used for the session
	ProxySource SessionProxySource `json:"proxySource" api:"required,nullable"`
	// URL to view session details
	SessionViewerURL string `json:"sessionViewerUrl" api:"required"`
	// Signals observed during the session. Filled in within a few seconds after the
	// session ends, and empty until then. An empty list does not mean nothing
	// happened: detection is heuristic and coverage is incomplete.
	Signals []SessionSignal `json:"signals" api:"required"`
	// Status of the session
	Status SessionStatus `json:"status" api:"required"`
	// Session timeout duration in milliseconds
	Timeout int64 `json:"timeout" api:"required"`
	// URL for the session's WebSocket connection
	WebsocketURL string `json:"websocketUrl" api:"required"`
	// Configuration for the debug URL and session viewer. Controls interaction
	// capabilities and cursor visibility.
	DebugConfig SessionDebugConfig `json:"debugConfig"`
	// Device configuration for the session
	DeviceConfig SessionDeviceConfig `json:"deviceConfig"`
	// Launch the browser in fullscreen mode, covering the full screen with no
	// Chrome UI.
	Fullscreen bool `json:"fullscreen"`
	// Indicates if the session is headless or headful
	Headless bool `json:"headless"`
	// Inactivity timeout in milliseconds, if one was set when the session was
	// created
	InactivityTimeout int64 `json:"inactivityTimeout"`
	// Indicates if Selenium is used in the session
	IsSelenium bool `json:"isSelenium"`
	// Customer-defined flat metadata. Values are always strings: numbers and
	// booleans sent on write come back in their string form.
	Metadata map[string]string `json:"metadata"`
	// This flag will persist the profile for the session.
	PersistProfile bool `json:"persistProfile"`
	// The ID of the profile associated with the session
	ProfileID string `json:"profileId" format:"uuid"`
	// The project associated with the session
	ProjectID string `json:"projectId" api:"nullable" format:"uuid"`
	// The region where the session was created.
	Region string `json:"region"`
	// Why the session reached a terminal state. Null while the session is live, or
	// when the reason is unknown (e.g. sessions created before this was tracked).
	// One of: user_requested (released via the API/SDK), timeout (hard `timeout`
	// elapsed), inactivity_timeout (no activity for the configured window),
	// creation_timeout (never started in time), startup_failed (could not be
	// dispatched), browser_closed (the browser or agent closed itself — not a
	// crash), browser_crashed (the browser crashed or its machine became
	// unresponsive).
	ReleaseReason SessionReleaseReason `json:"releaseReason" api:"nullable"`
	// Indicates if captcha solving is enabled
	SolveCaptcha bool `json:"solveCaptcha"`
	// Stealth configuration for the session
	StealthConfig SessionStealthConfig `json:"stealthConfig"`
	// User agent string used in the session
	UserAgent string      `json:"userAgent"`
	JSON      sessionJSON `json:"-"`
}

// sessionJSON contains the JSON metadata for the struct [Session]
type sessionJSON struct {
	ID                apijson.Field
	BrowserMode       apijson.Field
	CreatedAt         apijson.Field
	CreditsUsed       apijson.Field
	DebugURL          apijson.Field
	Dimensions        apijson.Field
	Duration          apijson.Field
	EventCount        apijson.Field
	OptimizeBandwidth apijson.Field
	ProxyBytesUsed    apijson.Field
	ProxySource       apijson.Field
	SessionViewerURL  apijson.Field
	Signals           apijson.Field
	Status            apijson.Field
	Timeout           apijson.Field
	WebsocketURL      apijson.Field
	DebugConfig       apijson.Field
	DeviceConfig      apijson.Field
	Fullscreen        apijson.Field
	Headless          apijson.Field
	InactivityTimeout apijson.Field
	IsSelenium        apijson.Field
	Metadata          apijson.Field
	PersistProfile    apijson.Field
	ProfileID         apijson.Field
	ProjectID         apijson.Field
	Region            apijson.Field
	ReleaseReason     apijson.Field
	SolveCaptcha      apijson.Field
	StealthConfig     apijson.Field
	UserAgent         apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *Session) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionJSON) RawJSON() string {
	return r.raw
}

// Browser mode used for this session.
type SessionBrowserMode string

const (
	SessionBrowserModeStealth  SessionBrowserMode = "stealth"
	SessionBrowserModeStandard SessionBrowserMode = "standard"
	SessionBrowserModeUnknown  SessionBrowserMode = "unknown"
)

func (r SessionBrowserMode) IsKnown() bool {
	switch r {
	case SessionBrowserModeStealth, SessionBrowserModeStandard, SessionBrowserModeUnknown:
		return true
	}
	return false
}

// Viewport and browser window dimensions for the session
type SessionDimensions struct {
	// Height of the browser window
	Height int64 `json:"height" api:"required"`
	// Width of the browser window
	Width int64                 `json:"width" api:"required"`
	JSON  sessionDimensionsJSON `json:"-"`
}

// sessionDimensionsJSON contains the JSON metadata for the struct
// [SessionDimensions]
type sessionDimensionsJSON struct {
	Height      apijson.Field
	Width       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionDimensions) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionDimensionsJSON) RawJSON() string {
	return r.raw
}

// Bandwidth optimizations that were applied to the session.
type SessionOptimizeBandwidth struct {
	BlockHosts       []string                     `json:"blockHosts"`
	BlockImages      bool                         `json:"blockImages"`
	BlockMedia       bool                         `json:"blockMedia"`
	BlockStylesheets bool                         `json:"blockStylesheets"`
	BlockURLPatterns []string                     `json:"blockUrlPatterns"`
	JSON             sessionOptimizeBandwidthJSON `json:"-"`
}

// sessionOptimizeBandwidthJSON contains the JSON metadata for the struct
// [SessionOptimizeBandwidth]
type sessionOptimizeBandwidthJSON struct {
	BlockHosts       apijson.Field
	BlockImages      apijson.Field
	BlockMedia       apijson.Field
	BlockStylesheets apijson.Field
	BlockURLPatterns apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *SessionOptimizeBandwidth) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionOptimizeBandwidthJSON) RawJSON() string {
	return r.raw
}

// Source of the proxy used for the session
type SessionProxySource string

const (
	SessionProxySourceSteel    SessionProxySource = "steel"
	SessionProxySourceExternal SessionProxySource = "external"
)

func (r SessionProxySource) IsKnown() bool {
	switch r {
	case SessionProxySourceSteel, SessionProxySourceExternal:
		return true
	}
	return false
}

// Signals observed during the session. Filled in within a few seconds after the
// session ends, and empty until then. An empty list does not mean nothing
// happened: detection is heuristic and coverage is incomplete.
type SessionSignal string

const (
	SessionSignalCaptchaEncountered     SessionSignal = "captcha_encountered"
	SessionSignalProxyConnectionFailed  SessionSignal = "proxy_connection_failed"
	SessionSignalSessionTimeoutExceeded SessionSignal = "session_timeout_exceeded"
	SessionSignalBotDetectionTriggered  SessionSignal = "bot_detection_triggered"
	SessionSignalContextRestored        SessionSignal = "context_restored"
)

func (r SessionSignal) IsKnown() bool {
	switch r {
	case SessionSignalCaptchaEncountered, SessionSignalProxyConnectionFailed, SessionSignalSessionTimeoutExceeded, SessionSignalBotDetectionTriggered, SessionSignalContextRestored:
		return true
	}
	return false
}

// Status of the session
type SessionStatus string

const (
	SessionStatusLive     SessionStatus = "live"
	SessionStatusReleased SessionStatus = "released"
	SessionStatusFailed   SessionStatus = "failed"
)

func (r SessionStatus) IsKnown() bool {
	switch r {
	case SessionStatusLive, SessionStatusReleased, SessionStatusFailed:
		return true
	}
	return false
}

// Configuration for the debug URL and session viewer. Controls interaction
// capabilities and cursor visibility.
type SessionDebugConfig struct {
	// Whether interaction is allowed via the debug URL viewer. When false, the
	// session viewer is view-only.
	Interactive bool `json:"interactive"`
	// Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode
	// only).
	SystemCursor bool                   `json:"systemCursor"`
	JSON         sessionDebugConfigJSON `json:"-"`
}

// sessionDebugConfigJSON contains the JSON metadata for the struct
// [SessionDebugConfig]
type sessionDebugConfigJSON struct {
	Interactive  apijson.Field
	SystemCursor apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *SessionDebugConfig) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionDebugConfigJSON) RawJSON() string {
	return r.raw
}

// Device configuration for the session
type SessionDeviceConfig struct {
	Device SessionDeviceConfigDevice `json:"device"`
	JSON   sessionDeviceConfigJSON   `json:"-"`
}

// sessionDeviceConfigJSON contains the JSON metadata for the struct
// [SessionDeviceConfig]
type sessionDeviceConfigJSON struct {
	Device      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionDeviceConfig) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionDeviceConfigJSON) RawJSON() string {
	return r.raw
}

type SessionDeviceConfigDevice string

const (
	SessionDeviceConfigDeviceDesktop SessionDeviceConfigDevice = "desktop"
	SessionDeviceConfigDeviceMobile  SessionDeviceConfigDevice = "mobile"
)

func (r SessionDeviceConfigDevice) IsKnown() bool {
	switch r {
	case SessionDeviceConfigDeviceDesktop, SessionDeviceConfigDeviceMobile:
		return true
	}
	return false
}

// Why the session reached a terminal state. Null while the session is live, or
// when the reason is unknown (e.g. sessions created before this was tracked).
// One of: user_requested (released via the API/SDK), timeout (hard `timeout`
// elapsed), inactivity_timeout (no activity for the configured window),
// creation_timeout (never started in time), startup_failed (could not be
// dispatched), browser_closed (the browser or agent closed itself — not a
// crash), browser_crashed (the browser crashed or its machine became
// unresponsive).
type SessionReleaseReason string

const (
	SessionReleaseReasonUserRequested     SessionReleaseReason = "user_requested"
	SessionReleaseReasonTimeout           SessionReleaseReason = "timeout"
	SessionReleaseReasonInactivityTimeout SessionReleaseReason = "inactivity_timeout"
	SessionReleaseReasonCreationTimeout   SessionReleaseReason = "creation_timeout"
	SessionReleaseReasonStartupFailed     SessionReleaseReason = "startup_failed"
	SessionReleaseReasonBrowserClosed     SessionReleaseReason = "browser_closed"
	SessionReleaseReasonBrowserCrashed    SessionReleaseReason = "browser_crashed"
)

func (r SessionReleaseReason) IsKnown() bool {
	switch r {
	case SessionReleaseReasonUserRequested, SessionReleaseReasonTimeout, SessionReleaseReasonInactivityTimeout, SessionReleaseReasonCreationTimeout, SessionReleaseReasonStartupFailed, SessionReleaseReasonBrowserClosed, SessionReleaseReasonBrowserCrashed:
		return true
	}
	return false
}

// Stealth configuration for the session
type SessionStealthConfig struct {
	// When true, captchas will be automatically solved when detected. When false,
	// use the solve endpoints to manually initiate solving.
	AutoCaptchaSolving bool `json:"autoCaptchaSolving"`
	// This flag will make the browser act more human-like by moving the mouse in a
	// more natural way
	HumanizeInteractions bool `json:"humanizeInteractions"`
	// This flag will skip the fingerprint generation for the session.
	SkipFingerprintInjection bool                     `json:"skipFingerprintInjection"`
	JSON                     sessionStealthConfigJSON `json:"-"`
}

// sessionStealthConfigJSON contains the JSON metadata for the struct
// [SessionStealthConfig]
type sessionStealthConfigJSON struct {
	AutoCaptchaSolving       apijson.Field
	HumanizeInteractions     apijson.Field
	SkipFingerprintInjection apijson.Field
	raw                      string
	ExtraFields              map[string]apijson.Field
}

func (r *SessionStealthConfig) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionStealthConfigJSON) RawJSON() string {
	return r.raw
}

// Session context data returned from a browser session.
type SessionContext struct {
	// Cookies to initialize in the session
	Cookies []SessionContextCookie `json:"cookies"`
	// Domain-specific indexedDB items to initialize in the session
	IndexedDB map[string][]SessionContextIndexedDB `json:"indexedDB"`
	// Domain-specific localStorage items to initialize in the session
	LocalStorage map[string]map[string]string `json:"localStorage"`
	// Domain-specific sessionStorage items to initialize in the session
	SessionStorage map[string]map[string]string `json:"sessionStorage"`
	JSON           sessionContextJSON           `json:"-"`
}

// sessionContextJSON contains the JSON metadata for the struct [SessionContext]
type sessionContextJSON struct {
	Cookies        apijson.Field
	IndexedDB      apijson.Field
	LocalStorage   apijson.Field
	SessionStorage apijson.Field
	raw            string
	ExtraFields    map[string]apijson.Field
}

func (r *SessionContext) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionContextJSON) RawJSON() string {
	return r.raw
}

// Cookies to initialize in the session
type SessionContextCookie struct {
	// The name of the cookie
	Name string `json:"name" api:"required"`
	// The value of the cookie
	Value string `json:"value" api:"required"`
	// The domain of the cookie
	Domain string `json:"domain"`
	// The expiration date of the cookie
	Expires float64 `json:"expires"`
	// Whether the cookie is HTTP only
	HTTPOnly bool `json:"httpOnly"`
	// The partition key of the cookie
	PartitionKey SessionContextCookiePartitionKey `json:"partitionKey"`
	// The path of the cookie
	Path string `json:"path"`
	// The priority of the cookie
	Priority SessionContextCookiePriority `json:"priority"`
	// Whether the cookie is a same party cookie
	SameParty bool `json:"sameParty"`
	// The same site attribute of the cookie
	SameSite SessionContextCookieSameSite `json:"sameSite"`
	// Whether the cookie is secure
	Secure bool `json:"secure"`
	// Whether the cookie is a session cookie
	Session bool `json:"session"`
	// The size of the cookie
	Size float64 `json:"size"`
	// The source port of the cookie
	SourcePort float64 `json:"sourcePort"`
	// The source scheme of the cookie
	SourceScheme SessionContextCookieSourceScheme `json:"sourceScheme"`
	// The URL of the cookie
	URL  string                   `json:"url"`
	JSON sessionContextCookieJSON `json:"-"`
}

// sessionContextCookieJSON contains the JSON metadata for the struct
// [SessionContextCookie]
type sessionContextCookieJSON struct {
	Name         apijson.Field
	Value        apijson.Field
	Domain       apijson.Field
	Expires      apijson.Field
	HTTPOnly     apijson.Field
	PartitionKey apijson.Field
	Path         apijson.Field
	Priority     apijson.Field
	SameParty    apijson.Field
	SameSite     apijson.Field
	Secure       apijson.Field
	Session      apijson.Field
	Size         apijson.Field
	SourcePort   apijson.Field
	SourceScheme apijson.Field
	URL          apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *SessionContextCookie) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionContextCookieJSON) RawJSON() string {
	return r.raw
}

// The partition key of the cookie
type SessionContextCookiePartitionKey struct {
	// Indicates if the cookie has any ancestors that are cross-site to the
	// topLevelSite.
	HasCrossSiteAncestor bool `json:"hasCrossSiteAncestor" api:"required"`
	// The site of the top-level URL the browser was visiting at the start of the
	// request to the endpoint that set the cookie.
	TopLevelSite string                               `json:"topLevelSite" api:"required"`
	JSON         sessionContextCookiePartitionKeyJSON `json:"-"`
}

// sessionContextCookiePartitionKeyJSON contains the JSON metadata for the
// struct [SessionContextCookiePartitionKey]
type sessionContextCookiePartitionKeyJSON struct {
	HasCrossSiteAncestor apijson.Field
	TopLevelSite         apijson.Field
	raw                  string
	ExtraFields          map[string]apijson.Field
}

func (r *SessionContextCookiePartitionKey) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionContextCookiePartitionKeyJSON) RawJSON() string {
	return r.raw
}

// The priority of the cookie
type SessionContextCookiePriority string

const (
	SessionContextCookiePriorityLow    SessionContextCookiePriority = "Low"
	SessionContextCookiePriorityMedium SessionContextCookiePriority = "Medium"
	SessionContextCookiePriorityHigh   SessionContextCookiePriority = "High"
)

func (r SessionContextCookiePriority) IsKnown() bool {
	switch r {
	case SessionContextCookiePriorityLow, SessionContextCookiePriorityMedium, SessionContextCookiePriorityHigh:
		return true
	}
	return false
}

// The same site attribute of the cookie
type SessionContextCookieSameSite string

const (
	SessionContextCookieSameSiteStrict SessionContextCookieSameSite = "Strict"
	SessionContextCookieSameSiteLax    SessionContextCookieSameSite = "Lax"
	SessionContextCookieSameSiteNone   SessionContextCookieSameSite = "None"
)

func (r SessionContextCookieSameSite) IsKnown() bool {
	switch r {
	case SessionContextCookieSameSiteStrict, SessionContextCookieSameSiteLax, SessionContextCookieSameSiteNone:
		return true
	}
	return false
}

// The source scheme of the cookie
type SessionContextCookieSourceScheme string

const (
	SessionContextCookieSourceSchemeUnset     SessionContextCookieSourceScheme = "Unset"
	SessionContextCookieSourceSchemeNonSecure SessionContextCookieSourceScheme = "NonSecure"
	SessionContextCookieSourceSchemeSecure    SessionContextCookieSourceScheme = "Secure"
)

func (r SessionContextCookieSourceScheme) IsKnown() bool {
	switch r {
	case SessionContextCookieSourceSchemeUnset, SessionContextCookieSourceSchemeNonSecure, SessionContextCookieSourceSchemeSecure:
		return true
	}
	return false
}

// Domain-specific indexedDB items to initialize in the session
type SessionContextIndexedDB struct {
	ID   float64                       `json:"id" api:"required"`
	Data []SessionContextIndexedDBData `json:"data" api:"required"`
	Name string                        `json:"name" api:"required"`
	JSON sessionContextIndexedDBJSON   `json:"-"`
}

// sessionContextIndexedDBJSON contains the JSON metadata for the struct
// [SessionContextIndexedDB]
type sessionContextIndexedDBJSON struct {
	ID          apijson.Field
	Data        apijson.Field
	Name        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionContextIndexedDB) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionContextIndexedDBJSON) RawJSON() string {
	return r.raw
}

type SessionContextIndexedDBData struct {
	ID      float64                             `json:"id" api:"required"`
	Name    string                              `json:"name" api:"required"`
	Records []SessionContextIndexedDBDataRecord `json:"records" api:"required"`
	JSON    sessionContextIndexedDBDataJSON     `json:"-"`
}

// sessionContextIndexedDBDataJSON contains the JSON metadata for the struct
// [SessionContextIndexedDBData]
type sessionContextIndexedDBDataJSON struct {
	ID          apijson.Field
	Name        apijson.Field
	Records     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionContextIndexedDBData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionContextIndexedDBDataJSON) RawJSON() string {
	return r.raw
}

type SessionContextIndexedDBDataRecord struct {
	Key       interface{}                                 `json:"key" api:"required"`
	Value     interface{}                                 `json:"value" api:"required"`
	BlobFiles []SessionContextIndexedDBDataRecordBlobFile `json:"blobFiles"`
	JSON      sessionContextIndexedDBDataRecordJSON       `json:"-"`
}

// sessionContextIndexedDBDataRecordJSON contains the JSON metadata for the
// struct [SessionContextIndexedDBDataRecord]
type sessionContextIndexedDBDataRecordJSON struct {
	Key         apijson.Field
	Value       apijson.Field
	BlobFiles   apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionContextIndexedDBDataRecord) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionContextIndexedDBDataRecordJSON) RawJSON() string {
	return r.raw
}

type SessionContextIndexedDBDataRecordBlobFile struct {
	BlobNumber   float64                                       `json:"blobNumber" api:"required"`
	MimeType     string                                        `json:"mimeType" api:"required"`
	Size         float64                                       `json:"size" api:"required"`
	Filename     string                                        `json:"filename"`
	LastModified time.Time                                     `json:"lastModified" format:"date-time"`
	Path         string                                        `json:"path"`
	JSON         sessionContextIndexedDBDataRecordBlobFileJSON `json:"-"`
}

// sessionContextIndexedDBDataRecordBlobFileJSON contains the JSON metadata for
// the struct [SessionContextIndexedDBDataRecordBlobFile]
type sessionContextIndexedDBDataRecordBlobFileJSON struct {
	BlobNumber   apijson.Field
	MimeType     apijson.Field
	Size         apijson.Field
	Filename     apijson.Field
	LastModified apijson.Field
	Path         apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *SessionContextIndexedDBDataRecordBlobFile) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionContextIndexedDBDataRecordBlobFileJSON) RawJSON() string {
	return r.raw
}

// Response containing a list of browser sessions with pagination details.
type Sessionslist struct {
	// Cursor for the next page of results. Null if no more pages.
	NextCursor string `json:"nextCursor" api:"required,nullable"`
	// List of browser sessions
	Sessions []SessionslistSession `json:"sessions" api:"required"`
	// Exact number of live sessions in the organization/project scope. Only
	// included for status=live without metadata, signal, releaseReason,
	// createdAfter or createdBefore filters. Other queries omit this field; use
	// nextCursor to paginate until it is null.
	TotalCount int64            `json:"totalCount"`
	JSON       sessionslistJSON `json:"-"`
}

// sessionslistJSON contains the JSON metadata for the struct [Sessionslist]
type sessionslistJSON struct {
	NextCursor  apijson.Field
	Sessions    apijson.Field
	TotalCount  apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *Sessionslist) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionslistJSON) RawJSON() string {
	return r.raw
}
