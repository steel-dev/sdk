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
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// SessionCommandExecutionService contains methods and other services that help
// with interacting with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewSessionCommandExecutionService] method instead.
type SessionCommandExecutionService struct {
	Options []option.RequestOption
}

// NewSessionCommandExecutionService generates a new service that applies the
// given options to each request. These options are applied after the parent
// client's options (if there is one), and before any request-specific options.
func NewSessionCommandExecutionService(opts ...option.RequestOption) (r *SessionCommandExecutionService) {
	r = &SessionCommandExecutionService{}
	r.Options = opts
	return
}

// Waits up to 20 seconds for an execution to finish and returns it. Use it when
// a run answered with state running.
func (r *SessionCommandExecutionService) Get(ctx context.Context, sessionID string, executionID string, opts ...option.RequestOption) (res *SessionCommandExecutionGetResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	if executionID == "" {
		err = errors.New("missing required executionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/commands/executions/%s", url.PathEscape(sessionID), url.PathEscape(executionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

type SessionCommandExecutionGetResponse struct {
	// The id of this execution, the same as execution.id. Use it to get or cancel
	// the execution.
	ID        string                                      `json:"id" api:"required"`
	Execution SessionCommandExecutionGetResponseExecution `json:"execution" api:"required"`
	// The tab the execution used.
	Page        SessionCommandExecutionGetResponsePage `json:"page" api:"required,nullable"`
	Results     []CommandResult                        `json:"results" api:"required"`
	BodyExpired bool                                   `json:"bodyExpired"`
	// A dialog that is open on the tab and blocks it until it closes.
	Dialog CommandDialog `json:"dialog"`
	// Set when the execution ran with after. Whether another execution, a quick
	// action, or a browser restart changed the session since then. Input from CDP
	// clients or the live view does not count.
	EpochChanged bool `json:"epochChanged"`
	// Send it as after in the next request.
	Next    string                                    `json:"next"`
	Observe SessionCommandExecutionGetResponseObserve `json:"observe"`
	JSON    sessionCommandExecutionGetResponseJSON    `json:"-"`
}

// sessionCommandExecutionGetResponseJSON contains the JSON metadata for the
// struct [SessionCommandExecutionGetResponse]
type sessionCommandExecutionGetResponseJSON struct {
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

func (r *SessionCommandExecutionGetResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionGetResponseJSON) RawJSON() string {
	return r.raw
}

type SessionCommandExecutionGetResponseExecution struct {
	ID string `json:"id" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect SessionCommandExecutionGetResponseExecutionEffect `json:"effect" api:"required"`
	// Commands started.
	Ran      int64                                            `json:"ran" api:"required"`
	Replayed bool                                             `json:"replayed" api:"required"`
	State    SessionCommandExecutionGetResponseExecutionState `json:"state" api:"required"`
	Error    CommandErrorInfo                                 `json:"error"`
	JSON     sessionCommandExecutionGetResponseExecutionJSON  `json:"-"`
}

// sessionCommandExecutionGetResponseExecutionJSON contains the JSON metadata
// for the struct [SessionCommandExecutionGetResponseExecution]
type sessionCommandExecutionGetResponseExecutionJSON struct {
	ID          apijson.Field
	Effect      apijson.Field
	Ran         apijson.Field
	Replayed    apijson.Field
	State       apijson.Field
	Error       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCommandExecutionGetResponseExecution) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionGetResponseExecutionJSON) RawJSON() string {
	return r.raw
}

// What happened to the page: nothing, a change, or a possible change.
type SessionCommandExecutionGetResponseExecutionEffect string

const (
	SessionCommandExecutionGetResponseExecutionEffectNone    SessionCommandExecutionGetResponseExecutionEffect = "none"
	SessionCommandExecutionGetResponseExecutionEffectApplied SessionCommandExecutionGetResponseExecutionEffect = "applied"
	SessionCommandExecutionGetResponseExecutionEffectUnknown SessionCommandExecutionGetResponseExecutionEffect = "unknown"
)

func (r SessionCommandExecutionGetResponseExecutionEffect) IsKnown() bool {
	switch r {
	case SessionCommandExecutionGetResponseExecutionEffectNone, SessionCommandExecutionGetResponseExecutionEffectApplied, SessionCommandExecutionGetResponseExecutionEffectUnknown:
		return true
	}
	return false
}

type SessionCommandExecutionGetResponseExecutionState string

const (
	SessionCommandExecutionGetResponseExecutionStateQueued    SessionCommandExecutionGetResponseExecutionState = "queued"
	SessionCommandExecutionGetResponseExecutionStateRunning   SessionCommandExecutionGetResponseExecutionState = "running"
	SessionCommandExecutionGetResponseExecutionStateCompleted SessionCommandExecutionGetResponseExecutionState = "completed"
	SessionCommandExecutionGetResponseExecutionStateFailed    SessionCommandExecutionGetResponseExecutionState = "failed"
	SessionCommandExecutionGetResponseExecutionStateCancelled SessionCommandExecutionGetResponseExecutionState = "cancelled"
	SessionCommandExecutionGetResponseExecutionStateTimeout   SessionCommandExecutionGetResponseExecutionState = "timeout"
	SessionCommandExecutionGetResponseExecutionStateExpired   SessionCommandExecutionGetResponseExecutionState = "expired"
)

func (r SessionCommandExecutionGetResponseExecutionState) IsKnown() bool {
	switch r {
	case SessionCommandExecutionGetResponseExecutionStateQueued, SessionCommandExecutionGetResponseExecutionStateRunning, SessionCommandExecutionGetResponseExecutionStateCompleted, SessionCommandExecutionGetResponseExecutionStateFailed, SessionCommandExecutionGetResponseExecutionStateCancelled, SessionCommandExecutionGetResponseExecutionStateTimeout, SessionCommandExecutionGetResponseExecutionStateExpired:
		return true
	}
	return false
}

// The tab the execution used.
type SessionCommandExecutionGetResponsePage struct {
	Tab   string                                     `json:"tab" api:"required"`
	Title string                                     `json:"title" api:"required"`
	URL   string                                     `json:"url" api:"required"`
	JSON  sessionCommandExecutionGetResponsePageJSON `json:"-"`
}

// sessionCommandExecutionGetResponsePageJSON contains the JSON metadata for the
// struct [SessionCommandExecutionGetResponsePage]
type sessionCommandExecutionGetResponsePageJSON struct {
	Tab         apijson.Field
	Title       apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCommandExecutionGetResponsePage) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionGetResponsePageJSON) RawJSON() string {
	return r.raw
}

type SessionCommandExecutionGetResponseObserve struct {
	Additions  int64                                         `json:"additions"`
	Base       string                                        `json:"base"`
	Diff       string                                        `json:"diff"`
	Error      CommandErrorInfo                              `json:"error"`
	Full       bool                                          `json:"full"`
	Removals   int64                                         `json:"removals"`
	Snapshot   string                                        `json:"snapshot"`
	SnapshotID string                                        `json:"snapshotId"`
	JSON       sessionCommandExecutionGetResponseObserveJSON `json:"-"`
}

// sessionCommandExecutionGetResponseObserveJSON contains the JSON metadata for
// the struct [SessionCommandExecutionGetResponseObserve]
type sessionCommandExecutionGetResponseObserveJSON struct {
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

func (r *SessionCommandExecutionGetResponseObserve) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionGetResponseObserveJSON) RawJSON() string {
	return r.raw
}

// Stops an execution. The running command stops before its next input, and
// commands that already ran are not undone.
func (r *SessionCommandExecutionService) Cancel(ctx context.Context, sessionID string, executionID string, opts ...option.RequestOption) (res *SessionCommandExecutionCancelResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if sessionID == "" {
		err = errors.New("missing required sessionId parameter")
		return nil, err
	}
	if executionID == "" {
		err = errors.New("missing required executionId parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/sessions/%s/commands/executions/%s/cancel", url.PathEscape(sessionID), url.PathEscape(executionID))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, nil, &res, opts...)
	return res, err
}

type SessionCommandExecutionCancelResponse struct {
	// The id of this execution, the same as execution.id. Use it to get or cancel
	// the execution.
	ID        string                                         `json:"id" api:"required"`
	Execution SessionCommandExecutionCancelResponseExecution `json:"execution" api:"required"`
	// The tab the execution used.
	Page        SessionCommandExecutionCancelResponsePage `json:"page" api:"required,nullable"`
	Results     []CommandResult                           `json:"results" api:"required"`
	BodyExpired bool                                      `json:"bodyExpired"`
	// A dialog that is open on the tab and blocks it until it closes.
	Dialog CommandDialog `json:"dialog"`
	// Set when the execution ran with after. Whether another execution, a quick
	// action, or a browser restart changed the session since then. Input from CDP
	// clients or the live view does not count.
	EpochChanged bool `json:"epochChanged"`
	// Send it as after in the next request.
	Next    string                                       `json:"next"`
	Observe SessionCommandExecutionCancelResponseObserve `json:"observe"`
	JSON    sessionCommandExecutionCancelResponseJSON    `json:"-"`
}

// sessionCommandExecutionCancelResponseJSON contains the JSON metadata for the
// struct [SessionCommandExecutionCancelResponse]
type sessionCommandExecutionCancelResponseJSON struct {
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

func (r *SessionCommandExecutionCancelResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionCancelResponseJSON) RawJSON() string {
	return r.raw
}

type SessionCommandExecutionCancelResponseExecution struct {
	ID string `json:"id" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect SessionCommandExecutionCancelResponseExecutionEffect `json:"effect" api:"required"`
	// Commands started.
	Ran      int64                                               `json:"ran" api:"required"`
	Replayed bool                                                `json:"replayed" api:"required"`
	State    SessionCommandExecutionCancelResponseExecutionState `json:"state" api:"required"`
	Error    CommandErrorInfo                                    `json:"error"`
	JSON     sessionCommandExecutionCancelResponseExecutionJSON  `json:"-"`
}

// sessionCommandExecutionCancelResponseExecutionJSON contains the JSON metadata
// for the struct [SessionCommandExecutionCancelResponseExecution]
type sessionCommandExecutionCancelResponseExecutionJSON struct {
	ID          apijson.Field
	Effect      apijson.Field
	Ran         apijson.Field
	Replayed    apijson.Field
	State       apijson.Field
	Error       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCommandExecutionCancelResponseExecution) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionCancelResponseExecutionJSON) RawJSON() string {
	return r.raw
}

// What happened to the page: nothing, a change, or a possible change.
type SessionCommandExecutionCancelResponseExecutionEffect string

const (
	SessionCommandExecutionCancelResponseExecutionEffectNone    SessionCommandExecutionCancelResponseExecutionEffect = "none"
	SessionCommandExecutionCancelResponseExecutionEffectApplied SessionCommandExecutionCancelResponseExecutionEffect = "applied"
	SessionCommandExecutionCancelResponseExecutionEffectUnknown SessionCommandExecutionCancelResponseExecutionEffect = "unknown"
)

func (r SessionCommandExecutionCancelResponseExecutionEffect) IsKnown() bool {
	switch r {
	case SessionCommandExecutionCancelResponseExecutionEffectNone, SessionCommandExecutionCancelResponseExecutionEffectApplied, SessionCommandExecutionCancelResponseExecutionEffectUnknown:
		return true
	}
	return false
}

type SessionCommandExecutionCancelResponseExecutionState string

const (
	SessionCommandExecutionCancelResponseExecutionStateQueued    SessionCommandExecutionCancelResponseExecutionState = "queued"
	SessionCommandExecutionCancelResponseExecutionStateRunning   SessionCommandExecutionCancelResponseExecutionState = "running"
	SessionCommandExecutionCancelResponseExecutionStateCompleted SessionCommandExecutionCancelResponseExecutionState = "completed"
	SessionCommandExecutionCancelResponseExecutionStateFailed    SessionCommandExecutionCancelResponseExecutionState = "failed"
	SessionCommandExecutionCancelResponseExecutionStateCancelled SessionCommandExecutionCancelResponseExecutionState = "cancelled"
	SessionCommandExecutionCancelResponseExecutionStateTimeout   SessionCommandExecutionCancelResponseExecutionState = "timeout"
	SessionCommandExecutionCancelResponseExecutionStateExpired   SessionCommandExecutionCancelResponseExecutionState = "expired"
)

func (r SessionCommandExecutionCancelResponseExecutionState) IsKnown() bool {
	switch r {
	case SessionCommandExecutionCancelResponseExecutionStateQueued, SessionCommandExecutionCancelResponseExecutionStateRunning, SessionCommandExecutionCancelResponseExecutionStateCompleted, SessionCommandExecutionCancelResponseExecutionStateFailed, SessionCommandExecutionCancelResponseExecutionStateCancelled, SessionCommandExecutionCancelResponseExecutionStateTimeout, SessionCommandExecutionCancelResponseExecutionStateExpired:
		return true
	}
	return false
}

// The tab the execution used.
type SessionCommandExecutionCancelResponsePage struct {
	Tab   string                                        `json:"tab" api:"required"`
	Title string                                        `json:"title" api:"required"`
	URL   string                                        `json:"url" api:"required"`
	JSON  sessionCommandExecutionCancelResponsePageJSON `json:"-"`
}

// sessionCommandExecutionCancelResponsePageJSON contains the JSON metadata for
// the struct [SessionCommandExecutionCancelResponsePage]
type sessionCommandExecutionCancelResponsePageJSON struct {
	Tab         apijson.Field
	Title       apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionCommandExecutionCancelResponsePage) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionCancelResponsePageJSON) RawJSON() string {
	return r.raw
}

type SessionCommandExecutionCancelResponseObserve struct {
	Additions  int64                                            `json:"additions"`
	Base       string                                           `json:"base"`
	Diff       string                                           `json:"diff"`
	Error      CommandErrorInfo                                 `json:"error"`
	Full       bool                                             `json:"full"`
	Removals   int64                                            `json:"removals"`
	Snapshot   string                                           `json:"snapshot"`
	SnapshotID string                                           `json:"snapshotId"`
	JSON       sessionCommandExecutionCancelResponseObserveJSON `json:"-"`
}

// sessionCommandExecutionCancelResponseObserveJSON contains the JSON metadata
// for the struct [SessionCommandExecutionCancelResponseObserve]
type sessionCommandExecutionCancelResponseObserveJSON struct {
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

func (r *SessionCommandExecutionCancelResponseObserve) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionCancelResponseObserveJSON) RawJSON() string {
	return r.raw
}
