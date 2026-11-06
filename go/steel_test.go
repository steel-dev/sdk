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

func TestPdfWithOptionalParams(t *testing.T) {
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
	_, err := client.Pdf(
		context.TODO(),
		steel.PdfParams{
			URL:       steel.F("https://example.com"),
			Delay:     steel.F(0.0),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Region:    steel.F[any](map[string]interface{}{}),
			UseProxy:  steel.F(true),
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

func TestScrapeWithOptionalParams(t *testing.T) {
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
	_, err := client.Scrape(
		context.TODO(),
		steel.ScrapeParams{
			URL:        steel.F("url"),
			Delay:      steel.F(0.0),
			Format:     steel.F([]steel.ScrapeParamsFormat{steel.ScrapeParamsFormatHTML}),
			Pdf:        steel.F(true),
			ProjectID:  steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Region:     steel.F[any](map[string]interface{}{}),
			Screenshot: steel.F(true),
			UseProxy:   steel.F(true),
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

func TestScreenshotWithOptionalParams(t *testing.T) {
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
	_, err := client.Screenshot(
		context.TODO(),
		steel.ScreenshotParams{
			URL:       steel.F("https://example.com"),
			Delay:     steel.F(0.0),
			FullPage:  steel.F(true),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Region:    steel.F[any](map[string]interface{}{}),
			UseProxy:  steel.F(true),
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
