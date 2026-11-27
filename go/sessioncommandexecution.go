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
	// The id of this execution. Use it to get or cancel the execution.
	ID string `json:"id" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect SessionCommandExecutionGetResponseEffect `json:"effect" api:"required"`
	// The tab the execution used.
	Page SessionCommandExecutionGetResponsePage `json:"page" api:"required,nullable"`
	// Commands started.
	Ran int64 `json:"ran" api:"required"`
	// This is the stored result of an earlier request. Nothing ran again.
	Replayed    bool                                    `json:"replayed" api:"required"`
	Results     []CommandResult                         `json:"results" api:"required"`
	State       SessionCommandExecutionGetResponseState `json:"state" api:"required"`
	BodyExpired bool                                    `json:"bodyExpired"`
	// A dialog that is open on the tab and blocks it until it closes.
	Dialog CommandDialog `json:"dialog"`
	// Set when the execution ran with after. Whether another execution, a quick
	// action, or a browser restart changed the session since then. Input from CDP
	// clients or the live view does not count.
	EpochChanged bool `json:"epochChanged"`
	// Tabs with keys or a mouse button still held down. Left out when nothing is
	// held.
	Held []CommandHeld `json:"held"`
	// Send it as after in the next request.
	Next    string                                    `json:"next"`
	Observe SessionCommandExecutionGetResponseObserve `json:"observe"`
	// Why the execution stopped early, such as a cancel, a timeout, or a browser
	// restart. A failed command has its error in results instead.
	Stopped CommandErrorInfo                       `json:"stopped"`
	JSON    sessionCommandExecutionGetResponseJSON `json:"-"`
}

// sessionCommandExecutionGetResponseJSON contains the JSON metadata for the
// struct [SessionCommandExecutionGetResponse]
type sessionCommandExecutionGetResponseJSON struct {
	ID           apijson.Field
	Effect       apijson.Field
	Page         apijson.Field
	Ran          apijson.Field
	Replayed     apijson.Field
	Results      apijson.Field
	State        apijson.Field
	BodyExpired  apijson.Field
	Dialog       apijson.Field
	EpochChanged apijson.Field
	Held         apijson.Field
	Next         apijson.Field
	Observe      apijson.Field
	Stopped      apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *SessionCommandExecutionGetResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionGetResponseJSON) RawJSON() string {
	return r.raw
}

// What happened to the page: nothing, a change, or a possible change.
type SessionCommandExecutionGetResponseEffect string

const (
	SessionCommandExecutionGetResponseEffectNone    SessionCommandExecutionGetResponseEffect = "none"
	SessionCommandExecutionGetResponseEffectApplied SessionCommandExecutionGetResponseEffect = "applied"
	SessionCommandExecutionGetResponseEffectUnknown SessionCommandExecutionGetResponseEffect = "unknown"
)

func (r SessionCommandExecutionGetResponseEffect) IsKnown() bool {
	switch r {
	case SessionCommandExecutionGetResponseEffectNone, SessionCommandExecutionGetResponseEffectApplied, SessionCommandExecutionGetResponseEffectUnknown:
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

type SessionCommandExecutionGetResponseState string

const (
	SessionCommandExecutionGetResponseStateRunning   SessionCommandExecutionGetResponseState = "running"
	SessionCommandExecutionGetResponseStateCompleted SessionCommandExecutionGetResponseState = "completed"
	SessionCommandExecutionGetResponseStateFailed    SessionCommandExecutionGetResponseState = "failed"
	SessionCommandExecutionGetResponseStateCancelled SessionCommandExecutionGetResponseState = "cancelled"
	SessionCommandExecutionGetResponseStateTimeout   SessionCommandExecutionGetResponseState = "timeout"
)

func (r SessionCommandExecutionGetResponseState) IsKnown() bool {
	switch r {
	case SessionCommandExecutionGetResponseStateRunning, SessionCommandExecutionGetResponseStateCompleted, SessionCommandExecutionGetResponseStateFailed, SessionCommandExecutionGetResponseStateCancelled, SessionCommandExecutionGetResponseStateTimeout:
		return true
	}
	return false
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
	// The id of this execution. Use it to get or cancel the execution.
	ID string `json:"id" api:"required"`
	// What happened to the page: nothing, a change, or a possible change.
	Effect SessionCommandExecutionCancelResponseEffect `json:"effect" api:"required"`
	// The tab the execution used.
	Page SessionCommandExecutionCancelResponsePage `json:"page" api:"required,nullable"`
	// Commands started.
	Ran int64 `json:"ran" api:"required"`
	// This is the stored result of an earlier request. Nothing ran again.
	Replayed    bool                                       `json:"replayed" api:"required"`
	Results     []CommandResult                            `json:"results" api:"required"`
	State       SessionCommandExecutionCancelResponseState `json:"state" api:"required"`
	BodyExpired bool                                       `json:"bodyExpired"`
	// A dialog that is open on the tab and blocks it until it closes.
	Dialog CommandDialog `json:"dialog"`
	// Set when the execution ran with after. Whether another execution, a quick
	// action, or a browser restart changed the session since then. Input from CDP
	// clients or the live view does not count.
	EpochChanged bool `json:"epochChanged"`
	// Tabs with keys or a mouse button still held down. Left out when nothing is
	// held.
	Held []CommandHeld `json:"held"`
	// Send it as after in the next request.
	Next    string                                       `json:"next"`
	Observe SessionCommandExecutionCancelResponseObserve `json:"observe"`
	// Why the execution stopped early, such as a cancel, a timeout, or a browser
	// restart. A failed command has its error in results instead.
	Stopped CommandErrorInfo                          `json:"stopped"`
	JSON    sessionCommandExecutionCancelResponseJSON `json:"-"`
}

// sessionCommandExecutionCancelResponseJSON contains the JSON metadata for the
// struct [SessionCommandExecutionCancelResponse]
type sessionCommandExecutionCancelResponseJSON struct {
	ID           apijson.Field
	Effect       apijson.Field
	Page         apijson.Field
	Ran          apijson.Field
	Replayed     apijson.Field
	Results      apijson.Field
	State        apijson.Field
	BodyExpired  apijson.Field
	Dialog       apijson.Field
	EpochChanged apijson.Field
	Held         apijson.Field
	Next         apijson.Field
	Observe      apijson.Field
	Stopped      apijson.Field
	raw          string
	ExtraFields  map[string]apijson.Field
}

func (r *SessionCommandExecutionCancelResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionCommandExecutionCancelResponseJSON) RawJSON() string {
	return r.raw
}

// What happened to the page: nothing, a change, or a possible change.
type SessionCommandExecutionCancelResponseEffect string

const (
	SessionCommandExecutionCancelResponseEffectNone    SessionCommandExecutionCancelResponseEffect = "none"
	SessionCommandExecutionCancelResponseEffectApplied SessionCommandExecutionCancelResponseEffect = "applied"
	SessionCommandExecutionCancelResponseEffectUnknown SessionCommandExecutionCancelResponseEffect = "unknown"
)

func (r SessionCommandExecutionCancelResponseEffect) IsKnown() bool {
	switch r {
	case SessionCommandExecutionCancelResponseEffectNone, SessionCommandExecutionCancelResponseEffectApplied, SessionCommandExecutionCancelResponseEffectUnknown:
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

type SessionCommandExecutionCancelResponseState string

const (
	SessionCommandExecutionCancelResponseStateRunning   SessionCommandExecutionCancelResponseState = "running"
	SessionCommandExecutionCancelResponseStateCompleted SessionCommandExecutionCancelResponseState = "completed"
	SessionCommandExecutionCancelResponseStateFailed    SessionCommandExecutionCancelResponseState = "failed"
	SessionCommandExecutionCancelResponseStateCancelled SessionCommandExecutionCancelResponseState = "cancelled"
	SessionCommandExecutionCancelResponseStateTimeout   SessionCommandExecutionCancelResponseState = "timeout"
)

func (r SessionCommandExecutionCancelResponseState) IsKnown() bool {
	switch r {
	case SessionCommandExecutionCancelResponseStateRunning, SessionCommandExecutionCancelResponseStateCompleted, SessionCommandExecutionCancelResponseStateFailed, SessionCommandExecutionCancelResponseStateCancelled, SessionCommandExecutionCancelResponseStateTimeout:
		return true
	}
	return false
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
