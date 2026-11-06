// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel_test

import (
	"context"
	"os"
	"testing"

	"github.com/steel-dev/sdk/go"
	"github.com/steel-dev/sdk/go/internal/testutil"
	"github.com/steel-dev/sdk/go/option"
)

func TestAutoPagination(t *testing.T) {
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
	iter := client.Sessions.ListAutoPaging(context.TODO(), steel.SessionListParams{
		Status: steel.F(steel.SessionListParamsStatusLive),
	})
	for i := 0; i < 3 && iter.Next(); i++ {
		session := iter.Current()
		t.Logf("%+v\n", session.ID)
	}
	if err := iter.Err(); err != nil {
		t.Fatalf("err should be nil: %s", err.Error())
	}
}
