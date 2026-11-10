// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel_test

import (
	"context"
	"errors"
	"os"
	"testing"

	"github.com/steel-dev/sdk/go"
	"github.com/steel-dev/sdk/go/internal/testutil"
	"github.com/steel-dev/sdk/go/option"
)

func TestSessionCommandExecutionGet(t *testing.T) {
	baseURL := "http://localhost:4010"
	if envURL, ok := os.LookupEnv("TEST_API_BASE_URL"); ok {
		baseURL = envURL
	}
	if !testutil.CheckTestServer(t, baseURL) {
		return
	}
	client := steel.NewClient(
		option.WithBaseURL(baseURL),
		option.WithSteelAPIKey("My Steel API Key"),
	)
	_, err := client.Sessions.CommandExecutions.Get(
		context.TODO(),
		"sessionId",
		"executionId",
	)
	if err != nil {
		var apierr *steel.Error
		if errors.As(err, &apierr) {
			t.Log(string(apierr.DumpRequest(true)))
		}
		t.Fatalf("err should be nil: %s", err.Error())
	}
}

func TestSessionCommandExecutionCancel(t *testing.T) {
	baseURL := "http://localhost:4010"
	if envURL, ok := os.LookupEnv("TEST_API_BASE_URL"); ok {
		baseURL = envURL
	}
	if !testutil.CheckTestServer(t, baseURL) {
		return
	}
	client := steel.NewClient(
		option.WithBaseURL(baseURL),
		option.WithSteelAPIKey("My Steel API Key"),
	)
	_, err := client.Sessions.CommandExecutions.Cancel(
		context.TODO(),
		"sessionId",
		"executionId",
	)
	if err != nil {
		var apierr *steel.Error
		if errors.As(err, &apierr) {
			t.Log(string(apierr.DumpRequest(true)))
		}
		t.Fatalf("err should be nil: %s", err.Error())
	}
}
