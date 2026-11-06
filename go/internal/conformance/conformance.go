package conformance

import (
	_ "embed"
	"encoding/json"
	"fmt"
	"io"
	"math/big"
	"mime"
	"mime/multipart"
	"net/http"
	"net/http/httptest"
	"net/url"
	"reflect"
	"sort"
	"strings"
	"sync"
	"testing"
	"time"

	"github.com/steel-dev/sdk/go/internal/apijson"
)

//go:embed cases.json
var cases []byte

type part struct {
	Name  string  `json:"name"`
	Value *string `json:"value"`
	File  *string `json:"file"`
}

type body struct {
	JSON      json.RawMessage `json:"json"`
	Text      *string         `json:"text"`
	Multipart []part          `json:"multipart"`
}

type request struct {
	Method      string            `json:"method"`
	Path        []string          `json:"path"`
	Query       [][2]string       `json:"query"`
	Headers     map[string]string `json:"headers"`
	ContentType *string           `json:"contentType"`
	Body        *body             `json:"body"`
}

type response struct {
	Status      int     `json:"status"`
	ContentType *string `json:"contentType"`
	Body        *body   `json:"body"`
}

type exchange struct {
	Request  request  `json:"request"`
	Response response `json:"response"`
}

type result struct {
	IDs  []string `json:"ids"`
	Text *string  `json:"text"`
}

type testCase struct {
	Lenient   bool       `json:"lenient"`
	Exchanges []exchange `json:"exchanges"`
	Result    result     `json:"result"`
}

type fixture struct {
	APIKey string              `json:"apiKey"`
	Cases  map[string]testCase `json:"cases"`
}

var (
	loaded fixture
	once   sync.Once
)

func load(t *testing.T) fixture {
	t.Helper()
	once.Do(func() {
		if err := json.Unmarshal(cases, &loaded); err != nil {
			panic(err)
		}
	})
	return loaded
}

type kind int

const (
	kindNone kind = iota
	kindJSON
	kindIDs
	kindText
)

type Result struct {
	value any
	ids   []string
	text  string
	kind  kind
}

func JSON(value any) Result { return Result{value: value, kind: kindJSON} }

func IDs(ids []string) Result { return Result{ids: ids, kind: kindIDs} }

func Text(text string) Result { return Result{text: text, kind: kindText} }

func None() Result { return Result{kind: kindNone} }

type recorded struct {
	method  string
	path    string
	query   string
	headers http.Header
	body    []byte
}

func scalar(text string) string {
	if moment, err := time.Parse(time.RFC3339Nano, text); err == nil && strings.Contains(text, "T") {
		return "time:" + moment.UTC().Format(time.RFC3339Nano)
	}
	if number, ok := new(big.Float).SetString(text); ok && strings.IndexFunc(text, func(r rune) bool {
		return !strings.ContainsRune("0123456789-+.eE", r)
	}) < 0 {
		return "number:" + number.Text('g', 30)
	}
	return text
}

func normalize(value any) any {
	switch typed := value.(type) {
	case float64:
		return "number:" + new(big.Float).SetFloat64(typed).Text('g', 30)
	case json.Number:
		return scalar(typed.String())
	case string:
		if moment, err := time.Parse(time.RFC3339Nano, typed); err == nil && strings.Contains(typed, "T") {
			return "time:" + moment.UTC().Format(time.RFC3339Nano)
		}
		return typed
	case []any:
		out := make([]any, len(typed))
		for index, item := range typed {
			out[index] = normalize(item)
		}
		return out
	case map[string]any:
		out := make(map[string]any, len(typed))
		for key, item := range typed {
			out[key] = normalize(item)
		}
		return out
	default:
		return typed
	}
}

func decodeJSON(data []byte) (any, error) {
	decoder := json.NewDecoder(strings.NewReader(string(data)))
	decoder.UseNumber()
	var value any
	err := decoder.Decode(&value)
	return normalize(value), err
}

func pairs(raw string) []string {
	out := []string{}
	for _, pair := range strings.Split(raw, "&") {
		if pair == "" {
			continue
		}
		key, value, _ := strings.Cut(pair, "=")
		decodedKey, err := url.QueryUnescape(key)
		if err != nil {
			decodedKey = key
		}
		decodedValue, err := url.QueryUnescape(value)
		if err != nil {
			decodedValue = value
		}
		out = append(out, decodedKey+"="+scalar(decodedValue))
	}
	sort.Strings(out)
	return out
}

func parts(t *testing.T, id string, data []byte, contentType string) []string {
	t.Helper()
	_, params, err := mime.ParseMediaType(contentType)
	if err != nil {
		t.Fatalf("%s: content type %q: %v", id, contentType, err)
	}
	reader := multipart.NewReader(strings.NewReader(string(data)), params["boundary"])
	out := []string{}
	for {
		next, err := reader.NextPart()
		if err == io.EOF {
			break
		}
		if err != nil {
			t.Fatalf("%s: multipart: %v", id, err)
		}
		content, err := io.ReadAll(next)
		if err != nil {
			t.Fatalf("%s: multipart: %v", id, err)
		}
		_, disposition, _ := mime.ParseMediaType(next.Header.Get("Content-Disposition"))
		if _, file := disposition["filename"]; file {
			out = append(out, next.FormName()+"=file:"+string(content))
		} else {
			out = append(out, next.FormName()+"="+scalar(string(content)))
		}
	}
	sort.Strings(out)
	return out
}

func equal(t *testing.T, id string, what string, actual any, expected any) {
	t.Helper()
	if !reflect.DeepEqual(actual, expected) {
		t.Errorf("%s %s\n  got:  %#v\n  want: %#v", id, what, actual, expected)
	}
}

func check(t *testing.T, id string, got recorded, want request) {
	t.Helper()
	equal(t, id, "method", got.method, want.Method)
	segments := []string{}
	for _, segment := range strings.Split(got.path, "/") {
		if segment == "" {
			continue
		}
		decoded, err := url.PathUnescape(segment)
		if err != nil {
			decoded = segment
		}
		segments = append(segments, decoded)
	}
	expectedPath := want.Path
	if expectedPath == nil {
		expectedPath = []string{}
	}
	equal(t, id, "path", segments, expectedPath)
	expectedQuery := []string{}
	for _, pair := range want.Query {
		expectedQuery = append(expectedQuery, pair[0]+"="+scalar(pair[1]))
	}
	sort.Strings(expectedQuery)
	equal(t, id, "query", pairs(got.query), expectedQuery)
	for name, value := range want.Headers {
		equal(t, id, "header "+name, got.headers.Get(name), value)
	}
	contentType := got.headers.Get("Content-Type")
	switch {
	case want.Body == nil:
		equal(t, id, "body length", len(got.body), 0)
	case want.Body.JSON != nil:
		if !strings.HasPrefix(contentType, "application/json") {
			t.Errorf("%s content type %q", id, contentType)
		}
		actual, err := decodeJSON(got.body)
		if err != nil {
			t.Fatalf("%s body: %v", id, err)
		}
		expected, err := decodeJSON(want.Body.JSON)
		if err != nil {
			t.Fatalf("%s expected body: %v", id, err)
		}
		equal(t, id, "body", actual, expected)
	default:
		if !strings.HasPrefix(contentType, "multipart/form-data") {
			t.Errorf("%s content type %q", id, contentType)
		}
		expected := []string{}
		for _, part := range want.Body.Multipart {
			if part.File != nil {
				expected = append(expected, part.Name+"=file:"+*part.File)
			} else if part.Value != nil {
				expected = append(expected, part.Name+"="+scalar(*part.Value))
			}
		}
		sort.Strings(expected)
		equal(t, id, "parts", parts(t, id, got.body, contentType), expected)
	}
}

var fieldType = reflect.TypeOf(apijson.Field{})

func jsonKey(field reflect.StructField) string {
	name, _, _ := strings.Cut(field.Tag.Get("json"), ",")
	if name == "" {
		return field.Name
	}
	return name
}

func decoded(t *testing.T, id string, value reflect.Value, path string) {
	t.Helper()
	switch value.Kind() {
	case reflect.Pointer, reflect.Interface:
		if !value.IsNil() {
			decoded(t, id, value.Elem(), path)
		}
	case reflect.Slice, reflect.Array:
		for index := 0; index < value.Len(); index++ {
			decoded(t, id, value.Index(index), fmt.Sprintf("%s[%d]", path, index))
		}
	case reflect.Map:
		for _, key := range value.MapKeys() {
			decoded(t, id, value.MapIndex(key), fmt.Sprintf("%s[%v]", path, key.Interface()))
		}
	case reflect.Struct:
		meta := value.FieldByName("JSON")
		keys := map[string]bool{}
		if meta.IsValid() && meta.Kind() == reflect.Struct {
			if method := meta.MethodByName("RawJSON"); method.IsValid() {
				var object map[string]json.RawMessage
				if json.Unmarshal([]byte(method.Call(nil)[0].String()), &object) == nil {
					for key := range object {
						keys[key] = true
					}
				}
			}
		}
		for index := 0; index < value.NumField(); index++ {
			field := value.Type().Field(index)
			if !field.IsExported() || field.Name == "JSON" {
				continue
			}
			if meta.IsValid() && meta.Kind() == reflect.Struct {
				status := meta.FieldByName(field.Name)
				if status.IsValid() && status.Type() == fieldType && status.CanInterface() {
					metadata := status.Interface().(apijson.Field)
					if metadata.IsInvalid() {
						t.Errorf("%s: %s.%s did not decode: %s", id, path, field.Name, metadata.Raw())
					}
					if keys[jsonKey(field)] && metadata.IsMissing() {
						t.Errorf("%s: %s.%s is missing although the response has it", id, path, field.Name)
					}
				}
			}
			decoded(t, id, value.Field(index), path+"."+field.Name)
		}
	default:
	}
}

func Conform(t *testing.T, id string, call func(baseURL string, apiKey string) (Result, error)) {
	t.Helper()
	data := load(t)
	expected, ok := data.Cases[id]
	if !ok {
		t.Fatalf("unknown conformance case %s", id)
	}
	var (
		lock     sync.Mutex
		requests []recorded
	)
	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		content, _ := io.ReadAll(r.Body)
		lock.Lock()
		index := len(requests)
		requests = append(requests, recorded{
			method:  r.Method,
			path:    r.URL.EscapedPath(),
			query:   r.URL.RawQuery,
			headers: r.Header.Clone(),
			body:    content,
		})
		lock.Unlock()
		if index >= len(expected.Exchanges) {
			w.WriteHeader(http.StatusInternalServerError)
			return
		}
		reply := expected.Exchanges[index].Response
		if reply.ContentType != nil {
			w.Header().Set("Content-Type", *reply.ContentType)
		}
		w.WriteHeader(reply.Status)
		switch {
		case reply.Body == nil:
		case reply.Body.JSON != nil:
			_, _ = w.Write(reply.Body.JSON)
		case reply.Body.Text != nil:
			_, _ = w.Write([]byte(*reply.Body.Text))
		}
	}))
	defer server.Close()
	outcome, err := call(server.URL, data.APIKey)
	if err != nil {
		t.Fatalf("%s: %v", id, err)
	}
	lock.Lock()
	defer lock.Unlock()
	if len(requests) != len(expected.Exchanges) {
		t.Fatalf("%s: sent %d requests, want %d", id, len(requests), len(expected.Exchanges))
	}
	for index, exchange := range expected.Exchanges {
		check(t, id, requests[index], exchange.Request)
	}
	switch outcome.kind {
	case kindJSON:
		if !expected.Lenient {
			decoded(t, id, reflect.ValueOf(outcome.value), "response")
		}
	case kindIDs:
		equal(t, id, "ids", outcome.ids, expected.Result.IDs)
	case kindText:
		if expected.Result.Text != nil {
			equal(t, id, "text", outcome.text, *expected.Result.Text)
		}
	case kindNone:
	}
}
