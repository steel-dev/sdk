// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel_test

import (
	"bytes"
	"context"
	"errors"
	"io"
	"os"
	"testing"

	"github.com/steel-dev/sdk/go"
	"github.com/steel-dev/sdk/go/internal/testutil"
	"github.com/steel-dev/sdk/go/option"
)

func TestProfileNewWithOptionalParams(t *testing.T) {
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
	_, err := client.Profiles.New(
		context.TODO(),
		steel.ProfileNewParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			Dimensions: steel.F(steel.ProfileNewParamsDimensions{
				Height: steel.F(0.0),
				Width:  steel.F(0.0),
			}),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			ProxyURL:  steel.F("https://example.com"),
			UseProxy:  steel.F("useProxy"),
			UserAgent: steel.F("userAgent"),
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

func TestProfileUpdateWithOptionalParams(t *testing.T) {
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
	_, err := client.Profiles.Update(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.ProfileUpdateParams{
			UserDataDir:    steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			QueryProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Dimensions: steel.F(steel.ProfileUpdateParamsDimensions{
				Height: steel.F(0.0),
				Width:  steel.F(0.0),
			}),
			BodyProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			ProxyURL:      steel.F("https://example.com"),
			UseProxy:      steel.F("useProxy"),
			UserAgent:     steel.F("userAgent"),
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

func TestProfileListWithOptionalParams(t *testing.T) {
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
	_, err := client.Profiles.List(
		context.TODO(),
		steel.ProfileListParams{
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
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

func TestProfileGetWithOptionalParams(t *testing.T) {
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
	_, err := client.Profiles.Get(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.ProfileGetParams{
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
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
