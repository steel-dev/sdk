// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel_test

import (
	"bytes"
	"context"
	"io"
	"testing"
	"time"

	"github.com/steel-dev/sdk/go"
	"github.com/steel-dev/sdk/go/internal/conformance"
	"github.com/steel-dev/sdk/go/option"
	"github.com/steel-dev/sdk/go/shared"
)

func conformanceClient(baseURL string, apiKey string) *steel.Client {
	return steel.NewClient(
		option.WithBaseURL(baseURL),
		option.WithSteelAPIKey(apiKey),
		option.WithMaxRetries(0),
	)
}

func TestConformancePdfFull(t *testing.T) {
	conformance.Conform(t, "pdf/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Pdf(context.TODO(), steel.PdfParams{
			URL:       steel.F("https://example.com"),
			Delay:     steel.F(0.0),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Region:    steel.F[any](map[string]interface{}{}),
			UseProxy:  steel.F(true),
		})
		return conformance.JSON(res), err
	})
}

func TestConformancePdfRequired(t *testing.T) {
	conformance.Conform(t, "pdf/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Pdf(context.TODO(), steel.PdfParams{
			URL: steel.F("https://example.com"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformancePdfNulls(t *testing.T) {
	conformance.Conform(t, "pdf/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Pdf(context.TODO(), steel.PdfParams{
			URL: steel.F("https://example.com"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceScrapeFull(t *testing.T) {
	conformance.Conform(t, "scrape/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Scrape(context.TODO(), steel.ScrapeParams{
			URL:        steel.F("url"),
			Delay:      steel.F(0.0),
			Format:     steel.F([]steel.ScrapeParamsFormat{steel.ScrapeParamsFormatHTML}),
			Pdf:        steel.F(true),
			ProjectID:  steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Region:     steel.F[any](map[string]interface{}{}),
			Screenshot: steel.F(true),
			UseProxy:   steel.F(true),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceScrapeRequired(t *testing.T) {
	conformance.Conform(t, "scrape/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Scrape(context.TODO(), steel.ScrapeParams{
			URL: steel.F("url"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceScrapeNulls(t *testing.T) {
	conformance.Conform(t, "scrape/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Scrape(context.TODO(), steel.ScrapeParams{
			URL: steel.F("url"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceScreenshotFull(t *testing.T) {
	conformance.Conform(t, "screenshot/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Screenshot(context.TODO(), steel.ScreenshotParams{
			URL:       steel.F("https://example.com"),
			Delay:     steel.F(0.0),
			FullPage:  steel.F(true),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Region:    steel.F[any](map[string]interface{}{}),
			UseProxy:  steel.F(true),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceScreenshotRequired(t *testing.T) {
	conformance.Conform(t, "screenshot/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Screenshot(context.TODO(), steel.ScreenshotParams{
			URL: steel.F("https://example.com"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceScreenshotNulls(t *testing.T) {
	conformance.Conform(t, "screenshot/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Screenshot(context.TODO(), steel.ScreenshotParams{
			URL: steel.F("https://example.com"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceAPIKeysCreateFull(t *testing.T) {
	conformance.Conform(t, "api_keys.create/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.APIKeys.New(context.TODO(), steel.APIKeyNewParams{
			Name:      steel.F("name"),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			ScopeType: steel.F(steel.APIKeyNewParamsScopeTypeProject),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceAPIKeysCreateRequired(t *testing.T) {
	conformance.Conform(t, "api_keys.create/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.APIKeys.New(context.TODO(), steel.APIKeyNewParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceAPIKeysCreateNulls(t *testing.T) {
	conformance.Conform(t, "api_keys.create/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.APIKeys.New(context.TODO(), steel.APIKeyNewParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceAPIKeysCreateUnknown(t *testing.T) {
	conformance.Conform(t, "api_keys.create/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.APIKeys.New(context.TODO(), steel.APIKeyNewParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsCreateFull(t *testing.T) {
	conformance.Conform(t, "credentials.create/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.New(context.TODO(), steel.CredentialNewParams{
			Value: steel.F(map[string]string{
				"foo": "string",
			}),
			Label:     steel.F("label"),
			Namespace: steel.F("namespace"),
			Origin:    steel.F("origin"),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsCreateRequired(t *testing.T) {
	conformance.Conform(t, "credentials.create/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.New(context.TODO(), steel.CredentialNewParams{
			Value: steel.F(map[string]string{
				"foo": "string",
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsCreateNulls(t *testing.T) {
	conformance.Conform(t, "credentials.create/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.New(context.TODO(), steel.CredentialNewParams{
			Value: steel.F(map[string]string{
				"foo": "string",
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsUpdateFull(t *testing.T) {
	conformance.Conform(t, "credentials.update/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.Update(context.TODO(), steel.CredentialUpdateParams{
			Label:     steel.F("label"),
			Namespace: steel.F("namespace"),
			Origin:    steel.F("origin"),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			Value: steel.F(map[string]string{
				"foo": "string",
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsUpdateRequired(t *testing.T) {
	conformance.Conform(t, "credentials.update/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.Update(context.TODO(), steel.CredentialUpdateParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsUpdateNulls(t *testing.T) {
	conformance.Conform(t, "credentials.update/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.Update(context.TODO(), steel.CredentialUpdateParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsListFull(t *testing.T) {
	conformance.Conform(t, "credentials.list/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.List(context.TODO(), steel.CredentialListParams{
			Namespace: steel.F("namespace"),
			Origin:    steel.F("origin"),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsListRequired(t *testing.T) {
	conformance.Conform(t, "credentials.list/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.List(context.TODO(), steel.CredentialListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsListNulls(t *testing.T) {
	conformance.Conform(t, "credentials.list/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.List(context.TODO(), steel.CredentialListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsListEscaped(t *testing.T) {
	conformance.Conform(t, "credentials.list/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.List(context.TODO(), steel.CredentialListParams{
			Namespace: steel.F("a b/c?d#e%f+g&h=i é"),
			Origin:    steel.F("a b/c?d#e%f+g&h=i é"),
			ProjectID: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsDeleteFull(t *testing.T) {
	conformance.Conform(t, "credentials.delete/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.Delete(context.TODO(), steel.CredentialDeleteParams{
			Origin:    steel.F("origin"),
			Namespace: steel.F("namespace"),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsDeleteRequired(t *testing.T) {
	conformance.Conform(t, "credentials.delete/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.Delete(context.TODO(), steel.CredentialDeleteParams{
			Origin: steel.F("origin"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceCredentialsDeleteNulls(t *testing.T) {
	conformance.Conform(t, "credentials.delete/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Credentials.Delete(context.TODO(), steel.CredentialDeleteParams{
			Origin: steel.F("origin"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsUpdateFull(t *testing.T) {
	conformance.Conform(t, "extensions.update/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Update(context.TODO(), "extensionId", steel.ExtensionUpdateParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			URL:  steel.F("https://example.com"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsUpdateRequired(t *testing.T) {
	conformance.Conform(t, "extensions.update/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Update(context.TODO(), "extensionId", steel.ExtensionUpdateParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsUpdateNulls(t *testing.T) {
	conformance.Conform(t, "extensions.update/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Update(context.TODO(), "extensionId", steel.ExtensionUpdateParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsUpdateEscaped(t *testing.T) {
	conformance.Conform(t, "extensions.update/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Update(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.ExtensionUpdateParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsListFull(t *testing.T) {
	conformance.Conform(t, "extensions.list/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsListRequired(t *testing.T) {
	conformance.Conform(t, "extensions.list/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsListNulls(t *testing.T) {
	conformance.Conform(t, "extensions.list/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDeleteFull(t *testing.T) {
	conformance.Conform(t, "extensions.delete/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Delete(context.TODO(), "extensionId")
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDeleteRequired(t *testing.T) {
	conformance.Conform(t, "extensions.delete/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Delete(context.TODO(), "extensionId")
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDeleteNulls(t *testing.T) {
	conformance.Conform(t, "extensions.delete/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Delete(context.TODO(), "extensionId")
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDeleteEscaped(t *testing.T) {
	conformance.Conform(t, "extensions.delete/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Delete(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDeleteAllFull(t *testing.T) {
	conformance.Conform(t, "extensions.delete_all/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.DeleteAll(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDeleteAllRequired(t *testing.T) {
	conformance.Conform(t, "extensions.delete_all/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.DeleteAll(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDeleteAllNulls(t *testing.T) {
	conformance.Conform(t, "extensions.delete_all/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.DeleteAll(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsDownloadFull(t *testing.T) {
	conformance.Conform(t, "extensions.download/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Download(context.TODO(), "extensionId")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceExtensionsDownloadRequired(t *testing.T) {
	conformance.Conform(t, "extensions.download/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Download(context.TODO(), "extensionId")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceExtensionsDownloadEscaped(t *testing.T) {
	conformance.Conform(t, "extensions.download/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Download(context.TODO(), "a b/c?d#e%f+g&h=i é")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceExtensionsUploadFull(t *testing.T) {
	conformance.Conform(t, "extensions.upload/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Upload(context.TODO(), steel.ExtensionUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			URL:  steel.F("https://example.com"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsUploadRequired(t *testing.T) {
	conformance.Conform(t, "extensions.upload/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Upload(context.TODO(), steel.ExtensionUploadParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceExtensionsUploadNulls(t *testing.T) {
	conformance.Conform(t, "extensions.upload/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Extensions.Upload(context.TODO(), steel.ExtensionUploadParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceFilesListFull(t *testing.T) {
	conformance.Conform(t, "files.list/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceFilesListRequired(t *testing.T) {
	conformance.Conform(t, "files.list/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceFilesListNulls(t *testing.T) {
	conformance.Conform(t, "files.list/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceFilesDeleteFull(t *testing.T) {
	conformance.Conform(t, "files.delete/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Files.Delete(context.TODO(), "path")
		return conformance.None(), err
	})
}

func TestConformanceFilesDeleteRequired(t *testing.T) {
	conformance.Conform(t, "files.delete/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Files.Delete(context.TODO(), "path")
		return conformance.None(), err
	})
}

func TestConformanceFilesDeleteEscaped(t *testing.T) {
	conformance.Conform(t, "files.delete/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Files.Delete(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.None(), err
	})
}

func TestConformanceFilesDownloadFull(t *testing.T) {
	conformance.Conform(t, "files.download/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.Download(context.TODO(), "path")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceFilesDownloadRequired(t *testing.T) {
	conformance.Conform(t, "files.download/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.Download(context.TODO(), "path")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceFilesDownloadEscaped(t *testing.T) {
	conformance.Conform(t, "files.download/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.Download(context.TODO(), "a b/c?d#e%f+g&h=i é")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceFilesUploadFull(t *testing.T) {
	conformance.Conform(t, "files.upload/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.Upload(context.TODO(), steel.FileUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			Path: steel.F("path"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceFilesUploadRequired(t *testing.T) {
	conformance.Conform(t, "files.upload/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.Upload(context.TODO(), steel.FileUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceFilesUploadNulls(t *testing.T) {
	conformance.Conform(t, "files.upload/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Files.Upload(context.TODO(), steel.FileUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesCreateFull(t *testing.T) {
	conformance.Conform(t, "profiles.create/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.New(context.TODO(), steel.ProfileNewParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			Dimensions: steel.F(steel.ProfileNewParamsDimensions{
				Height: steel.F(0.0),
				Width:  steel.F(0.0),
			}),
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
			ProxyURL:  steel.F("https://example.com"),
			UseProxy:  steel.F("useProxy"),
			UserAgent: steel.F("userAgent"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesCreateRequired(t *testing.T) {
	conformance.Conform(t, "profiles.create/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.New(context.TODO(), steel.ProfileNewParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesCreateNulls(t *testing.T) {
	conformance.Conform(t, "profiles.create/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.New(context.TODO(), steel.ProfileNewParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesCreateUnknown(t *testing.T) {
	conformance.Conform(t, "profiles.create/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.New(context.TODO(), steel.ProfileNewParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesUpdateFull(t *testing.T) {
	conformance.Conform(t, "profiles.update/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Update(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileUpdateParams{
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
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesUpdateRequired(t *testing.T) {
	conformance.Conform(t, "profiles.update/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Update(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileUpdateParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesUpdateNulls(t *testing.T) {
	conformance.Conform(t, "profiles.update/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Update(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileUpdateParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesUpdateUnknown(t *testing.T) {
	conformance.Conform(t, "profiles.update/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Update(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileUpdateParams{
			UserDataDir: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesUpdateEscaped(t *testing.T) {
	conformance.Conform(t, "profiles.update/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Update(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.ProfileUpdateParams{
			UserDataDir:    steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			QueryProjectID: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesListFull(t *testing.T) {
	conformance.Conform(t, "profiles.list/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.List(context.TODO(), steel.ProfileListParams{
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesListRequired(t *testing.T) {
	conformance.Conform(t, "profiles.list/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.List(context.TODO(), steel.ProfileListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesListNulls(t *testing.T) {
	conformance.Conform(t, "profiles.list/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.List(context.TODO(), steel.ProfileListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesListUnknown(t *testing.T) {
	conformance.Conform(t, "profiles.list/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.List(context.TODO(), steel.ProfileListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesListEscaped(t *testing.T) {
	conformance.Conform(t, "profiles.list/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.List(context.TODO(), steel.ProfileListParams{
			ProjectID: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesGetFull(t *testing.T) {
	conformance.Conform(t, "profiles.get/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileGetParams{
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesGetRequired(t *testing.T) {
	conformance.Conform(t, "profiles.get/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileGetParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesGetNulls(t *testing.T) {
	conformance.Conform(t, "profiles.get/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileGetParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesGetUnknown(t *testing.T) {
	conformance.Conform(t, "profiles.get/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.ProfileGetParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceProfilesGetEscaped(t *testing.T) {
	conformance.Conform(t, "profiles.get/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Profiles.Get(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.ProfileGetParams{
			ProjectID: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceProjectsRetrieveFull(t *testing.T) {
	conformance.Conform(t, "projects.retrieve/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Projects.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceProjectsRetrieveRequired(t *testing.T) {
	conformance.Conform(t, "projects.retrieve/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Projects.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceProjectsRetrieveNulls(t *testing.T) {
	conformance.Conform(t, "projects.retrieve/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Projects.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceProjectsRetrieveEscaped(t *testing.T) {
	conformance.Conform(t, "projects.retrieve/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Projects.Get(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceProjectsListFull(t *testing.T) {
	conformance.Conform(t, "projects.list/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Projects.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceProjectsListRequired(t *testing.T) {
	conformance.Conform(t, "projects.list/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Projects.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceProjectsListNulls(t *testing.T) {
	conformance.Conform(t, "projects.list/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Projects.List(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCreateFull(t *testing.T) {
	conformance.Conform(t, "sessions.create/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.New(context.TODO(), steel.SessionNewParams{
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
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCreateRequired(t *testing.T) {
	conformance.Conform(t, "sessions.create/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.New(context.TODO(), steel.SessionNewParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCreateNulls(t *testing.T) {
	conformance.Conform(t, "sessions.create/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.New(context.TODO(), steel.SessionNewParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCreateUnknown(t *testing.T) {
	conformance.Conform(t, "sessions.create/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.New(context.TODO(), steel.SessionNewParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsRetrieveFull(t *testing.T) {
	conformance.Conform(t, "sessions.retrieve/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsRetrieveRequired(t *testing.T) {
	conformance.Conform(t, "sessions.retrieve/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsRetrieveNulls(t *testing.T) {
	conformance.Conform(t, "sessions.retrieve/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsRetrieveUnknown(t *testing.T) {
	conformance.Conform(t, "sessions.retrieve/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Get(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsRetrieveEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.retrieve/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Get(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsListFull(t *testing.T) {
	conformance.Conform(t, "sessions.list/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.List(context.TODO(), steel.SessionListParams{
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
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsListRequired(t *testing.T) {
	conformance.Conform(t, "sessions.list/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.List(context.TODO(), steel.SessionListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsListNulls(t *testing.T) {
	conformance.Conform(t, "sessions.list/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.List(context.TODO(), steel.SessionListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsListUnknown(t *testing.T) {
	conformance.Conform(t, "sessions.list/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.List(context.TODO(), steel.SessionListParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsListEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.list/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.List(context.TODO(), steel.SessionListParams{
			CursorID:  steel.F("a b/c?d#e%f+g&h=i é"),
			ProjectID: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsListPages(t *testing.T) {
	conformance.Conform(t, "sessions.list/pages", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		iter := client.Sessions.ListAutoPaging(context.TODO(), steel.SessionListParams{})
		ids := []string{}
		for iter.Next() {
			ids = append(ids, iter.Current().ID)
		}
		return conformance.IDs(ids), iter.Err()
	})
}

func TestConformanceSessionsAgentTracesFull(t *testing.T) {
	conformance.Conform(t, "sessions.agent_traces/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.AgentTraces(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionAgentTracesParams{
			EndTime:    steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
			EventTypes: steel.F([]string{"string"}),
			Limit:      steel.F(int64(1)),
			Namespace:  steel.F("namespace"),
			Offset:     steel.F(int64(0)),
			StartTime:  steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsAgentTracesRequired(t *testing.T) {
	conformance.Conform(t, "sessions.agent_traces/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.AgentTraces(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionAgentTracesParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsAgentTracesNulls(t *testing.T) {
	conformance.Conform(t, "sessions.agent_traces/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.AgentTraces(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionAgentTracesParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsAgentTracesEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.agent_traces/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.AgentTraces(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionAgentTracesParams{
			Namespace: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCommandsFull(t *testing.T) {
	conformance.Conform(t, "sessions.commands/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Commands(context.TODO(), "sessionId", steel.SessionCommandsParams{
			Commands: steel.F[steel.SessionCommandsParamsCommandsUnion](steel.SessionCommandsParamsCommandsNavigateCommand{
				Command: steel.F(steel.SessionCommandsParamsCommandsNavigateCommandCommandNavigate),
				URL:     steel.F("x"),
				Tab:     steel.F("t321669910225"),
			}),
			After:       steel.F("x"),
			EventsAfter: steel.F("c-321669910225"),
			Observe: steel.F(steel.SessionCommandsParamsObserve{
				Mode:        steel.F(steel.SessionCommandsParamsObserveModeNone),
				Base:        steel.F("x"),
				Compact:     steel.F(true),
				Depth:       steel.F(int64(0)),
				Interactive: steel.F(true),
				Tab:         steel.F("t321669910225"),
			}),
			IdempotencyKey: steel.F("idempotency-key"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCommandsRequired(t *testing.T) {
	conformance.Conform(t, "sessions.commands/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Commands(context.TODO(), "sessionId", steel.SessionCommandsParams{
			Commands: steel.F[steel.SessionCommandsParamsCommandsUnion](steel.SessionCommandsParamsCommandsNavigateCommand{
				Command: steel.F(steel.SessionCommandsParamsCommandsNavigateCommandCommandNavigate),
				URL:     steel.F("x"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCommandsNulls(t *testing.T) {
	conformance.Conform(t, "sessions.commands/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Commands(context.TODO(), "sessionId", steel.SessionCommandsParams{
			Commands: steel.F[steel.SessionCommandsParamsCommandsUnion](steel.SessionCommandsParamsCommandsNavigateCommand{
				Command: steel.F(steel.SessionCommandsParamsCommandsNavigateCommandCommandNavigate),
				URL:     steel.F("x"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCommandsUnknown(t *testing.T) {
	conformance.Conform(t, "sessions.commands/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Commands(context.TODO(), "sessionId", steel.SessionCommandsParams{
			Commands: steel.F[steel.SessionCommandsParamsCommandsUnion](steel.SessionCommandsParamsCommandsNavigateCommand{
				Command: steel.F(steel.SessionCommandsParamsCommandsNavigateCommandCommandNavigate),
				URL:     steel.F("x"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCommandsVariant0_1(t *testing.T) {
	conformance.Conform(t, "sessions.commands/variant-0-1", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Commands(context.TODO(), "sessionId", steel.SessionCommandsParams{
			Commands: steel.F[steel.SessionCommandsParamsCommandsUnion](steel.SessionCommandsParamsCommandsNavigateCommand{
				Command: steel.F(steel.SessionCommandsParamsCommandsNavigateCommandCommandNavigate),
				URL:     steel.F("x"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCommandsEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.commands/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Commands(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionCommandsParams{
			Commands: steel.F[steel.SessionCommandsParamsCommandsUnion](steel.SessionCommandsParamsCommandsNavigateCommand{
				Command: steel.F(steel.SessionCommandsParamsCommandsNavigateCommandCommandNavigate),
				URL:     steel.F("x"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsComputerFull(t *testing.T) {
	conformance.Conform(t, "sessions.computer/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Computer(context.TODO(), "sessionId", steel.SessionComputerParams{
			Body: steel.SessionComputerParamsBodyMoveMouse{
				Action:      steel.F(steel.SessionComputerParamsBodyMoveMouseActionMoveMouse),
				Coordinates: steel.F([]float64{0.0, 0.0}),
				HoldKeys:    steel.F([]string{"string"}),
				Screenshot:  steel.F(true),
			},
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsComputerRequired(t *testing.T) {
	conformance.Conform(t, "sessions.computer/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Computer(context.TODO(), "sessionId", steel.SessionComputerParams{
			Body: steel.SessionComputerParamsBodyMoveMouse{
				Action:      steel.F(steel.SessionComputerParamsBodyMoveMouseActionMoveMouse),
				Coordinates: steel.F([]float64{0.0, 0.0}),
			},
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsComputerNulls(t *testing.T) {
	conformance.Conform(t, "sessions.computer/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Computer(context.TODO(), "sessionId", steel.SessionComputerParams{
			Body: steel.SessionComputerParamsBodyMoveMouse{
				Action:      steel.F(steel.SessionComputerParamsBodyMoveMouseActionMoveMouse),
				Coordinates: steel.F([]float64{0.0, 0.0}),
			},
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsComputerEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.computer/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Computer(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionComputerParams{
			Body: steel.SessionComputerParamsBodyMoveMouse{
				Action:      steel.F(steel.SessionComputerParamsBodyMoveMouseActionMoveMouse),
				Coordinates: steel.F([]float64{0.0, 0.0}),
			},
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsContextFull(t *testing.T) {
	conformance.Conform(t, "sessions.context/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Context(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsContextRequired(t *testing.T) {
	conformance.Conform(t, "sessions.context/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Context(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsContextNulls(t *testing.T) {
	conformance.Conform(t, "sessions.context/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Context(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsContextUnknown(t *testing.T) {
	conformance.Conform(t, "sessions.context/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Context(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsContextEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.context/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Context(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCostFull(t *testing.T) {
	conformance.Conform(t, "sessions.cost/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Cost(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCostRequired(t *testing.T) {
	conformance.Conform(t, "sessions.cost/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Cost(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCostNulls(t *testing.T) {
	conformance.Conform(t, "sessions.cost/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Cost(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCostUnknown(t *testing.T) {
	conformance.Conform(t, "sessions.cost/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Cost(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCostEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.cost/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Cost(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsEventsFull(t *testing.T) {
	conformance.Conform(t, "sessions.events/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Events(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionEventsParams{
			Compressed: steel.F(true),
			Limit:      steel.F(int64(1)),
			Pointer:    steel.F("pointer"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsEventsRequired(t *testing.T) {
	conformance.Conform(t, "sessions.events/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Events(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionEventsParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsEventsNulls(t *testing.T) {
	conformance.Conform(t, "sessions.events/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Events(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionEventsParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsEventsEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.events/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Events(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionEventsParams{
			Pointer: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsHlsFull(t *testing.T) {
	conformance.Conform(t, "sessions.hls/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Hls(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsHlsRequired(t *testing.T) {
	conformance.Conform(t, "sessions.hls/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Hls(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsHlsEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.hls/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Hls(context.TODO(), "a b/c?d#e%f+g&h=i é")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsLiveDetailsFull(t *testing.T) {
	conformance.Conform(t, "sessions.live_details/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.LiveDetails(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsLiveDetailsRequired(t *testing.T) {
	conformance.Conform(t, "sessions.live_details/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.LiveDetails(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsLiveDetailsNulls(t *testing.T) {
	conformance.Conform(t, "sessions.live_details/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.LiveDetails(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsLiveDetailsEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.live_details/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.LiveDetails(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsLogsFull(t *testing.T) {
	conformance.Conform(t, "sessions.logs/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Logs(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionLogsParams{
			EndTime:    steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
			EventTypes: steel.F([]string{"string"}),
			Limit:      steel.F(int64(1)),
			Namespace:  steel.F("namespace"),
			Offset:     steel.F(int64(0)),
			StartTime:  steel.F(time.Date(2019, 12, 27, 18, 11, 19, 117000000, time.UTC)),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsLogsRequired(t *testing.T) {
	conformance.Conform(t, "sessions.logs/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Logs(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionLogsParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsLogsNulls(t *testing.T) {
	conformance.Conform(t, "sessions.logs/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Logs(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionLogsParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsLogsEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.logs/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Logs(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionLogsParams{
			Namespace: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseFull(t *testing.T) {
	conformance.Conform(t, "sessions.release/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Release(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseRequired(t *testing.T) {
	conformance.Conform(t, "sessions.release/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Release(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseNulls(t *testing.T) {
	conformance.Conform(t, "sessions.release/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Release(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.release/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Release(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseAllFull(t *testing.T) {
	conformance.Conform(t, "sessions.release_all/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReleaseAll(context.TODO(), steel.SessionReleaseAllParams{
			ProjectID: steel.F("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseAllRequired(t *testing.T) {
	conformance.Conform(t, "sessions.release_all/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReleaseAll(context.TODO(), steel.SessionReleaseAllParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseAllNulls(t *testing.T) {
	conformance.Conform(t, "sessions.release_all/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReleaseAll(context.TODO(), steel.SessionReleaseAllParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReleaseAllEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.release_all/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReleaseAll(context.TODO(), steel.SessionReleaseAllParams{
			ProjectID: steel.F("a b/c?d#e%f+g&h=i é"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReplaceMetadataFull(t *testing.T) {
	conformance.Conform(t, "sessions.replace_metadata/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReplaceMetadata(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionReplaceMetadataParams{
			Metadata: steel.F(map[string]steel.SessionReplaceMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReplaceMetadataRequired(t *testing.T) {
	conformance.Conform(t, "sessions.replace_metadata/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReplaceMetadata(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionReplaceMetadataParams{
			Metadata: steel.F(map[string]steel.SessionReplaceMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReplaceMetadataNulls(t *testing.T) {
	conformance.Conform(t, "sessions.replace_metadata/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReplaceMetadata(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionReplaceMetadataParams{
			Metadata: steel.F(map[string]steel.SessionReplaceMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsReplaceMetadataEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.replace_metadata/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.ReplaceMetadata(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionReplaceMetadataParams{
			Metadata: steel.F(map[string]steel.SessionReplaceMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsSignalCatalogFull(t *testing.T) {
	conformance.Conform(t, "sessions.signal_catalog/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.SignalCatalog(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsSignalCatalogRequired(t *testing.T) {
	conformance.Conform(t, "sessions.signal_catalog/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.SignalCatalog(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsSignalCatalogNulls(t *testing.T) {
	conformance.Conform(t, "sessions.signal_catalog/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.SignalCatalog(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsSignalCatalogUnknown(t *testing.T) {
	conformance.Conform(t, "sessions.signal_catalog/unknown", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.SignalCatalog(context.TODO())
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsUpdateMetadataFull(t *testing.T) {
	conformance.Conform(t, "sessions.update_metadata/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.UpdateMetadata(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionUpdateMetadataParams{
			Metadata: steel.F(map[string]steel.SessionUpdateMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsUpdateMetadataRequired(t *testing.T) {
	conformance.Conform(t, "sessions.update_metadata/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.UpdateMetadata(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionUpdateMetadataParams{
			Metadata: steel.F(map[string]steel.SessionUpdateMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsUpdateMetadataNulls(t *testing.T) {
	conformance.Conform(t, "sessions.update_metadata/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.UpdateMetadata(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionUpdateMetadataParams{
			Metadata: steel.F(map[string]steel.SessionUpdateMetadataParamsMetadataUnion{
				"foo": nil,
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsUpdateMetadataEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.update_metadata/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.UpdateMetadata(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionUpdateMetadataParams{
			Metadata: steel.F(map[string]steel.SessionUpdateMetadataParamsMetadataUnion{
				"foo": shared.UnionString("string"),
			}),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveFull(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Solve(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionCaptchaSolveParams{
			PageID: steel.F("pageId"),
			TaskID: steel.F("taskId"),
			URL:    steel.F("url"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveRequired(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Solve(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionCaptchaSolveParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveNulls(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Solve(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionCaptchaSolveParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Solve(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionCaptchaSolveParams{})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveImageFull(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve_image/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.SolveImage(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionCaptchaSolveImageParams{
			ImageXPath: steel.F("imageXPath"),
			InputXPath: steel.F("inputXPath"),
			URL:        steel.F("url"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveImageRequired(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve_image/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.SolveImage(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionCaptchaSolveImageParams{
			ImageXPath: steel.F("imageXPath"),
			InputXPath: steel.F("inputXPath"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveImageNulls(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve_image/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.SolveImage(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e", steel.SessionCaptchaSolveImageParams{
			ImageXPath: steel.F("imageXPath"),
			InputXPath: steel.F("inputXPath"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasSolveImageEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.solve_image/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.SolveImage(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionCaptchaSolveImageParams{
			ImageXPath: steel.F("imageXPath"),
			InputXPath: steel.F("inputXPath"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasStatusFull(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.status/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Status(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasStatusRequired(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.status/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Status(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasStatusNulls(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.status/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Status(context.TODO(), "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsCaptchasStatusEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.captchas.status/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Captchas.Status(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesListFull(t *testing.T) {
	conformance.Conform(t, "sessions.files.list/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.List(context.TODO(), "sessionId")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesListRequired(t *testing.T) {
	conformance.Conform(t, "sessions.files.list/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.List(context.TODO(), "sessionId")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesListNulls(t *testing.T) {
	conformance.Conform(t, "sessions.files.list/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.List(context.TODO(), "sessionId")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesListEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.files.list/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.List(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesDeleteFull(t *testing.T) {
	conformance.Conform(t, "sessions.files.delete/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Sessions.Files.Delete(context.TODO(), "sessionId", "path")
		return conformance.None(), err
	})
}

func TestConformanceSessionsFilesDeleteRequired(t *testing.T) {
	conformance.Conform(t, "sessions.files.delete/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Sessions.Files.Delete(context.TODO(), "sessionId", "path")
		return conformance.None(), err
	})
}

func TestConformanceSessionsFilesDeleteEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.files.delete/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Sessions.Files.Delete(context.TODO(), "a b/c?d#e%f+g&h=i é", "a b/c?d#e%f+g&h=i é")
		return conformance.None(), err
	})
}

func TestConformanceSessionsFilesDeleteAllFull(t *testing.T) {
	conformance.Conform(t, "sessions.files.delete_all/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Sessions.Files.DeleteAll(context.TODO(), "sessionId")
		return conformance.None(), err
	})
}

func TestConformanceSessionsFilesDeleteAllRequired(t *testing.T) {
	conformance.Conform(t, "sessions.files.delete_all/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Sessions.Files.DeleteAll(context.TODO(), "sessionId")
		return conformance.None(), err
	})
}

func TestConformanceSessionsFilesDeleteAllEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.files.delete_all/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		err := client.Sessions.Files.DeleteAll(context.TODO(), "a b/c?d#e%f+g&h=i é")
		return conformance.None(), err
	})
}

func TestConformanceSessionsFilesDownloadFull(t *testing.T) {
	conformance.Conform(t, "sessions.files.download/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.Download(context.TODO(), "sessionId", "path")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsFilesDownloadRequired(t *testing.T) {
	conformance.Conform(t, "sessions.files.download/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.Download(context.TODO(), "sessionId", "path")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsFilesDownloadEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.files.download/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.Download(context.TODO(), "a b/c?d#e%f+g&h=i é", "a b/c?d#e%f+g&h=i é")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsFilesDownloadArchiveFull(t *testing.T) {
	conformance.Conform(t, "sessions.files.download_archive/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.DownloadArchive(context.TODO(), "sessionId")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsFilesDownloadArchiveRequired(t *testing.T) {
	conformance.Conform(t, "sessions.files.download_archive/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.DownloadArchive(context.TODO(), "sessionId")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsFilesDownloadArchiveEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.files.download_archive/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.DownloadArchive(context.TODO(), "a b/c?d#e%f+g&h=i é")
		if err != nil {
			return conformance.None(), err
		}
		defer res.Body.Close()
		content, err := io.ReadAll(res.Body)
		return conformance.Text(string(content)), err
	})
}

func TestConformanceSessionsFilesUploadFull(t *testing.T) {
	conformance.Conform(t, "sessions.files.upload/full", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.Upload(context.TODO(), "sessionId", steel.SessionFileUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
			Path: steel.F("path"),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesUploadRequired(t *testing.T) {
	conformance.Conform(t, "sessions.files.upload/required", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.Upload(context.TODO(), "sessionId", steel.SessionFileUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesUploadNulls(t *testing.T) {
	conformance.Conform(t, "sessions.files.upload/nulls", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.Upload(context.TODO(), "sessionId", steel.SessionFileUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}

func TestConformanceSessionsFilesUploadEscaped(t *testing.T) {
	conformance.Conform(t, "sessions.files.upload/escaped", func(baseURL string, apiKey string) (conformance.Result, error) {
		client := conformanceClient(baseURL, apiKey)
		res, err := client.Sessions.Files.Upload(context.TODO(), "a b/c?d#e%f+g&h=i é", steel.SessionFileUploadParams{
			File: steel.F(io.Reader(bytes.NewBuffer([]byte("Example data")))),
		})
		return conformance.JSON(res), err
	})
}
