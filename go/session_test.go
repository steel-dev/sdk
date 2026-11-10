// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel_test

import (
	"bytes"
	"context"
	"errors"
	"io"
	"net/http"
	"net/http/httptest"
	"os"
	"testing"
	"time"

	"github.com/steel-dev/sdk/go"
	"github.com/steel-dev/sdk/go/internal/testutil"
	"github.com/steel-dev/sdk/go/option"
	"github.com/steel-dev/sdk/go/shared"
)

func TestSessionNewWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.New(
		context.TODO(),
		steel.SessionNewParams{
			BlockAds:       steel.F(true),
			CaCertificates: steel.F([]string{"string"}),
			Concurrency:    steel.F(int64(-9007199254740991)),
			Credentials: steel.F(steel.SessionNewParamsCredentials{
				AutoSubmit:         steel.F(true),
				BlurFields:         steel.F(true),
				CredentialProvider: steel.F("credentialProvider"),
				ExactOrigin:        steel.F(true),
				Origins:            steel.F([]string{"string"}),
			}),
			DebugConfig: steel.F(steel.SessionNewParamsDebugConfig{
				Interactive:  steel.F(true),
				SystemCursor: steel.F(true),
			}),
			DeviceConfig: steel.F(steel.SessionNewParamsDeviceConfig{
				Device: steel.F(steel.SessionNewParamsDeviceConfigDeviceDesktop),
			}),
			Dimensions: steel.F(steel.SessionNewParamsDimensions{
				Height: steel.F(int64(-9007199254740991)),
				Width:  steel.F(int64(-9007199254740991)),
			}),
			ExperimentalFeatures: steel.F([]string{"string"}),
			ExtensionIDs:         steel.F([]string{"string"}),
			Fullscreen:           steel.F(true),
			Headless:             steel.F(true),
			InactivityTimeout:    steel.F(int64(1)),
			IsSelenium:           steel.F(true),
			Metadata: steel.F(map[string]steel.SessionNewParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
			Namespace:         steel.F("namespace"),
			OptimizeBandwidth: steel.F[steel.SessionNewParamsOptimizeBandwidthUnion](shared.UnionBool(true)),
			PersistProfile:    steel.F(true),
			ProfileID:         steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			ProjectID:         steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			ProxyURL:          steel.F("https://example.com"),
			Recording:         steel.F(true),
			Region:            steel.F[any](map[string]interface{}{}),
			SessionContext: steel.F(steel.SessionNewParamsSessionContext{
				Cookies: steel.F([]steel.SessionNewParamsSessionContextCookie{{
					Name:     steel.F("name"),
					Value:    steel.F("value"),
					Domain:   steel.F("domain"),
					Expires:  steel.F(0.0),
					HTTPOnly: steel.F(true),
					PartitionKey: steel.F(steel.SessionNewParamsSessionContextCookiePartitionKey{
						HasCrossSiteAncestor: steel.F(true),
						TopLevelSite:         steel.F("topLevelSite"),
					}),
					Path:         steel.F("path"),
					Priority:     steel.F(steel.SessionNewParamsSessionContextCookiePriorityLow),
					SameParty:    steel.F(true),
					SameSite:     steel.F(steel.SessionNewParamsSessionContextCookieSameSiteStrict),
					Secure:       steel.F(true),
					Session:      steel.F(true),
					Size:         steel.F(0.0),
					SourcePort:   steel.F(0.0),
					SourceScheme: steel.F(steel.SessionNewParamsSessionContextCookieSourceSchemeUnset),
					URL:          steel.F("url"),
				}}),
				IndexedDB: steel.F(map[string][]steel.SessionNewParamsSessionContextIndexedDB{
					"foo": {{
						ID: steel.F(0.0),
						Data: steel.F([]steel.SessionNewParamsSessionContextIndexedDBData{{
							ID:   steel.F(0.0),
							Name: steel.F("name"),
							Records: steel.F([]steel.SessionNewParamsSessionContextIndexedDBDataRecord{{
								Key:   steel.F[any](map[string]interface{}{}),
								Value: steel.F[any](map[string]interface{}{}),
								BlobFiles: steel.F([]steel.SessionNewParamsSessionContextIndexedDBDataRecordBlobFile{{
									BlobNumber:   steel.F(0.0),
									MimeType:     steel.F("mimeType"),
									Size:         steel.F(0.0),
									Filename:     steel.F("filename"),
									LastModified: steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
									Path:         steel.F("path"),
								}}),
							}}),
						}}),
						Name: steel.F("name"),
					}},
				}),
				LocalStorage: steel.F(map[string]map[string]string{
					"foo": {
						"foo": "string",
					},
				}),
				SessionStorage: steel.F(map[string]map[string]string{
					"foo": {
						"foo": "string",
					},
				}),
			}),
			SessionID:    steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			SolveCaptcha: steel.F(true),
			StealthConfig: steel.F(steel.SessionNewParamsStealthConfig{
				AutoCaptchaSolving:       steel.F(true),
				HumanizeInteractions:     steel.F(true),
				SkipFingerprintInjection: steel.F(true),
			}),
			Timeout:   steel.F(int64(-9007199254740991)),
			UseProxy:  steel.F[any](true),
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

func TestSessionGet(t *testing.T) {
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
	_, err := client.Sessions.Get(
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

func TestSessionListWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.List(
		context.TODO(),
		steel.SessionListParams{
			CreatedAfter:  steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
			CreatedBefore: steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
			CursorID:      steel.F("cursorId"),
			Limit:         steel.F(int64(1)),
			Metadata: steel.F(map[string]string{
				"foo": "x",
			}),
			ProjectID:     steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			ReleaseReason: steel.F(steel.SessionListParamsReleaseReasonUserRequested),
			Signal:        steel.F([]steel.SessionListParamsSignal{steel.SessionListParamsSignalCaptchaEncountered}),
			Status:        steel.F(steel.SessionListParamsStatusLive),
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

func TestSessionAgentTracesWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.AgentTraces(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.SessionAgentTracesParams{
			EndTime:    steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
			EventTypes: steel.F([]string{"string"}),
			Limit:      steel.F(int64(1)),
			Namespace:  steel.F("namespace"),
			Offset:     steel.F(int64(0)),
			StartTime:  steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
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

func TestSessionCommandsWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.Commands(
		context.TODO(),
		"sessionId",
		steel.SessionCommandsParams{
			Commands: steel.F[steel.SessionCommandsParamsCommandsUnion](steel.SessionCommandsParamsCommandsNavigateCommand{
				Command: steel.F(steel.SessionCommandsParamsCommandsNavigateCommandCommandNavigate),
				URL:     steel.F("x"),
				Tab:     steel.F("t321669910225"),
			}),
			After: steel.F("x"),
			Observe: steel.F(steel.SessionCommandsParamsObserve{
				Mode:        steel.F(steel.SessionCommandsParamsObserveModeNone),
				Base:        steel.F("x"),
				Compact:     steel.F(true),
				Depth:       steel.F(int64(0)),
				Interactive: steel.F(true),
				Tab:         steel.F("t321669910225"),
			}),
			IdempotencyKey: steel.F("idempotency-key"),
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

func TestSessionComputerWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.Computer(
		context.TODO(),
		"sessionId",
		steel.SessionComputerParams{
			Body: steel.SessionComputerParamsBodyMoveMouse{
				Action:      steel.F(steel.SessionComputerParamsBodyMoveMouseActionMoveMouse),
				Coordinates: steel.F([]float64{0.0, 0.0}),
				HoldKeys:    steel.F([]string{"string"}),
				Screenshot:  steel.F(true),
			},
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

func TestSessionContext(t *testing.T) {
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
	_, err := client.Sessions.Context(
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

func TestSessionCost(t *testing.T) {
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
	_, err := client.Sessions.Cost(
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

func TestSessionEventsWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.Events(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.SessionEventsParams{
			Compressed: steel.F(true),
			Limit:      steel.F(int64(1)),
			Pointer:    steel.F("pointer"),
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

func TestSessionHls(t *testing.T) {
	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(200)
		w.Write([]byte("abc"))
	}))
	defer server.Close()
	baseURL := server.URL
	client := steel.NewClient(
		option.WithBaseURL(baseURL),
		option.WithSteelAPIKey("My Steel API Key"),
	)
	resp, err := client.Sessions.Hls(
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
	defer resp.Body.Close()

	b, err := io.ReadAll(resp.Body)
	if err != nil {
		var apierr *steel.Error
		if errors.As(err, &apierr) {
			t.Log(string(apierr.DumpRequest(true)))
		}
		t.Fatalf("err should be nil: %s", err.Error())
	}
	if !bytes.Equal(b, []byte("abc")) {
		t.Fatalf("return value not %s: %s", "abc", b)
	}
}

func TestSessionLiveDetails(t *testing.T) {
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
	_, err := client.Sessions.LiveDetails(
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

func TestSessionLogsWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.Logs(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.SessionLogsParams{
			EndTime:    steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
			EventTypes: steel.F([]string{"string"}),
			Limit:      steel.F(int64(1)),
			Namespace:  steel.F("namespace"),
			Offset:     steel.F(int64(0)),
			StartTime:  steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
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

func TestSessionRelease(t *testing.T) {
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
	_, err := client.Sessions.Release(
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

func TestSessionReleaseAllWithOptionalParams(t *testing.T) {
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
	_, err := client.Sessions.ReleaseAll(
		context.TODO(),
		steel.SessionReleaseAllParams{
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

func TestSessionReplaceMetadata(t *testing.T) {
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
	_, err := client.Sessions.ReplaceMetadata(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.SessionReplaceMetadataParams{
			Metadata: steel.F(map[string]steel.SessionReplaceMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
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

func TestSessionSignalCatalog(t *testing.T) {
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
	_, err := client.Sessions.SignalCatalog(
		context.TODO(),
	)
	if err != nil {
		var apierr *steel.Error
		if errors.As(err, &apierr) {
			t.Log(string(apierr.DumpRequest(true)))
		}
		t.Fatalf("err should be nil: %s", err.Error())
	}
}

func TestSessionUpdateMetadata(t *testing.T) {
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
	_, err := client.Sessions.UpdateMetadata(
		context.TODO(),
		"182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
		steel.SessionUpdateMetadataParams{
			Metadata: steel.F(map[string]steel.SessionUpdateMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
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
