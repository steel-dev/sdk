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

func TestSessionCaptchaSolveWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.Captchas.Solve(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.SessionCaptchaSolveParams{
			PageID: steel.F("pageId"),
			TaskID: steel.F("taskId"),
			URL:    steel.F("url"),
		},
	)
	if err != nil {
		var apierr *steel.Error
		if errors.As(err, &apierr) {
			t.Log(string(apierr.DumpRequest(true)))
		}
		t.Fatalf("err should be nil: %s", err.Error())
	}
}

func TestSessionCaptchaSolveImageWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.Captchas.SolveImage(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.SessionCaptchaSolveImageParams{
			ImageXPath: steel.F("imageXPath"),
			InputXPath: steel.F("inputXPath"),
			URL:        steel.F("url"),
		},
	)
	if err != nil {
		var apierr *steel.Error
		if errors.As(err, &apierr) {
			t.Log(string(apierr.DumpRequest(true)))
		}
		t.Fatalf("err should be nil: %s", err.Error())
	}
}

func TestSessionCaptchaStatus(t *testing.T) {
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
	_, err := client.Sessions.Captchas.Status(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
	)
	if err != nil {
		var apierr *steel.Error
		if errors.As(err, &apierr) {
			t.Log(string(apierr.DumpRequest(true)))
		}
		t.Fatalf("err should be nil: %s", err.Error())
	}
}
