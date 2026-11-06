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

func TestUsage(t *testing.T) {
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
	session, err := client.Sessions.New(context.TODO(), steel.SessionNewParams{
		Timeout:  steel.F(int64(20000)),
		UseProxy: steel.F[any](true),
	})
	if err != nil {
		t.Error(err)
		return
	}
	t.Logf("%+v\n", session.ID)
}
