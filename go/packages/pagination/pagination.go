// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package pagination

import (
	"net/http"
	"reflect"

	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

type SessionsCursor[T any] struct {
	Sessions []T                `json:"sessions"`
	JSON     sessionsCursorJSON `json:"-"`
	cfg      *requestconfig.RequestConfig
	res      *http.Response
}

// sessionsCursorJSON contains the JSON metadata for the struct
// [SessionsCursor[T]]
type sessionsCursorJSON struct {
	Sessions    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *SessionsCursor[T]) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r sessionsCursorJSON) RawJSON() string {
	return r.raw
}

// GetNextPage returns the next page as defined by this pagination style. When
// there is no next page, this function will return a 'nil' for the page value,
// but will not return an error
func (r *SessionsCursor[T]) GetNextPage() (res *SessionsCursor[T], err error) {
	items := r.Sessions
	if len(items) == 0 {
		return nil, nil
	}
	cfg := r.cfg.Clone(r.cfg.Context)
	value := reflect.ValueOf(items[len(items)-1])
	field := value.FieldByName("ID")
	err = cfg.Apply(option.WithQuery("cursorId", field.Interface().(string)))
	if err != nil {
		return nil, err
	}
	var raw *http.Response
	cfg.ResponseInto = &raw
	cfg.ResponseBodyInto = &res
	err = cfg.Execute()
	if err != nil {
		return nil, err
	}
	res.SetPageConfig(cfg, raw)
	return res, nil
}

func (r *SessionsCursor[T]) SetPageConfig(cfg *requestconfig.RequestConfig, res *http.Response) {
	if r == nil {
		r = &SessionsCursor[T]{}
	}
	r.cfg = cfg
	r.res = res
}

type SessionsCursorAutoPager[T any] struct {
	page *SessionsCursor[T]
	cur  T
	idx  int
	run  int
	err  error
}

func NewSessionsCursorAutoPager[T any](page *SessionsCursor[T], err error) *SessionsCursorAutoPager[T] {
	return &SessionsCursorAutoPager[T]{
		page: page,
		err:  err,
	}
}

func (r *SessionsCursorAutoPager[T]) Next() bool {
	if r.page == nil || len(r.page.Sessions) == 0 {
		return false
	}
	if r.idx >= len(r.page.Sessions) {
		r.idx = 0
		r.page, r.err = r.page.GetNextPage()
		if r.err != nil || r.page == nil || len(r.page.Sessions) == 0 {
			return false
		}
	}
	r.cur = r.page.Sessions[r.idx]
	r.run += 1
	r.idx += 1
	return true
}

func (r *SessionsCursorAutoPager[T]) Current() T {
	return r.cur
}

func (r *SessionsCursorAutoPager[T]) Err() error {
	return r.err
}

func (r *SessionsCursorAutoPager[T]) Index() int {
	return r.run
}
