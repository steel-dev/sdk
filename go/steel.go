// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
	"net/http"
	"slices"
	"time"

	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// Generates a PDF from a specified webpage.
func (r *Client) Pdf(ctx context.Context, body PdfParams, opts ...option.RequestOption) (res *PdfResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/pdf"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type PdfParams struct {
	// URL of the webpage to convert to PDF
	URL param.Field[string] `json:"url" api:"required"`
	// Delay before generating the PDF (in milliseconds)
	Delay param.Field[float64] `json:"delay"`
	// Project to execute the PDF generation in.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// The desired region for the action to be performed in
	Region param.Field[interface{}] `json:"region"`
	// Use a Steel-provided residential proxy for generating the PDF
	UseProxy param.Field[bool] `json:"useProxy"`
}

func (r PdfParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type PdfResponse struct {
	// URL where the PDF is hosted
	URL  string          `json:"url" api:"required"`
	JSON pdfResponseJSON `json:"-"`
}

// pdfResponseJSON contains the JSON metadata for the struct [PdfResponse]
type pdfResponseJSON struct {
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *PdfResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r pdfResponseJSON) RawJSON() string {
	return r.raw
}

// Extracts content from a specified URL.
func (r *Client) Scrape(ctx context.Context, body ScrapeParams, opts ...option.RequestOption) (res *ScrapeResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/scrape"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type ScrapeParams struct {
	// URL of the webpage to scrape. A URL without a scheme is fetched over HTTPS.
	URL param.Field[string] `json:"url" api:"required"`
	// Delay before scraping (in milliseconds)
	Delay param.Field[float64] `json:"delay"`
	// Desired format(s) for the scraped content. Default is `html`.
	Format param.Field[[]ScrapeParamsFormat] `json:"format"`
	// Include a PDF in the response
	Pdf param.Field[bool] `json:"pdf"`
	// Project to execute the scrape in.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// The desired region for the action to be performed in
	Region param.Field[interface{}] `json:"region"`
	// Include a screenshot in the response
	Screenshot param.Field[bool] `json:"screenshot"`
	// Use a Steel-provided residential proxy for the scrape
	UseProxy param.Field[bool] `json:"useProxy"`
}

func (r ScrapeParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

// Desired format(s) for the scraped content. Default is `html`.
type ScrapeParamsFormat string

const (
	ScrapeParamsFormatHTML        ScrapeParamsFormat = "html"
	ScrapeParamsFormatReadability ScrapeParamsFormat = "readability"
	ScrapeParamsFormatCleanedHTML ScrapeParamsFormat = "cleaned_html"
	ScrapeParamsFormatMarkdown    ScrapeParamsFormat = "markdown"
)

func (r ScrapeParamsFormat) IsKnown() bool {
	switch r {
	case ScrapeParamsFormatHTML, ScrapeParamsFormatReadability, ScrapeParamsFormatCleanedHTML, ScrapeParamsFormatMarkdown:
		return true
	}
	return false
}

// Response from a successful scrape request
type ScrapeResponse struct {
	Content    ScrapeResponseContent    `json:"content" api:"required"`
	Links      []ScrapeResponseLink     `json:"links" api:"required"`
	Metadata   ScrapeResponseMetadata   `json:"metadata" api:"required"`
	Pdf        ScrapeResponsePdf        `json:"pdf"`
	Screenshot ScrapeResponseScreenshot `json:"screenshot"`
	JSON       scrapeResponseJSON       `json:"-"`
}

// scrapeResponseJSON contains the JSON metadata for the struct [ScrapeResponse]
type scrapeResponseJSON struct {
	Content     apijson.Field
	Links       apijson.Field
	Metadata    apijson.Field
	Pdf         apijson.Field
	Screenshot  apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ScrapeResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r scrapeResponseJSON) RawJSON() string {
	return r.raw
}

type ScrapeResponseContent struct {
	// Cleaned HTML content of the webpage
	CleanedHTML string `json:"cleaned_html"`
	// Raw HTML content of the webpage
	HTML string `json:"html"`
	// Webpage content converted to Markdown
	Markdown string `json:"markdown"`
	// Webpage content in Readability format
	Readability map[string]interface{}    `json:"readability"`
	JSON        scrapeResponseContentJSON `json:"-"`
}

// scrapeResponseContentJSON contains the JSON metadata for the struct
// [ScrapeResponseContent]
type scrapeResponseContentJSON struct {
	CleanedHTML apijson.Field
	HTML        apijson.Field
	Markdown    apijson.Field
	Readability apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ScrapeResponseContent) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r scrapeResponseContentJSON) RawJSON() string {
	return r.raw
}

type ScrapeResponseLink struct {
	// Text content of the link
	Text string `json:"text" api:"required"`
	// URL of the link
	URL  string                 `json:"url" api:"required"`
	JSON scrapeResponseLinkJSON `json:"-"`
}

// scrapeResponseLinkJSON contains the JSON metadata for the struct
// [ScrapeResponseLink]
type scrapeResponseLinkJSON struct {
	Text        apijson.Field
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ScrapeResponseLink) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r scrapeResponseLinkJSON) RawJSON() string {
	return r.raw
}

type ScrapeResponseMetadata struct {
	// HTTP status code of the response
	StatusCode int64 `json:"statusCode" api:"required"`
	// Author of the article content
	ArticleAuthor string `json:"articleAuthor"`
	// Author of the webpage content
	Author string `json:"author"`
	// Canonical URL of the webpage
	Canonical string `json:"canonical"`
	// Description of the webpage
	Description string `json:"description"`
	// Favicon URL of the website
	Favicon string `json:"favicon"`
	// JSON-LD structured data from the webpage
	JsonLd interface{} `json:"jsonLd"`
	// Keywords associated with the webpage
	Keywords string `json:"keywords"`
	// Detected language of the webpage
	Language string `json:"language"`
	// Last modification time of the content
	ModifiedTime string `json:"modifiedTime"`
	// Open Graph description
	OgDescription string `json:"ogDescription"`
	// Open Graph image URL
	OgImage string `json:"ogImage"`
	// Open Graph site name
	OgSiteName string `json:"ogSiteName"`
	// Open Graph title
	OgTitle string `json:"ogTitle"`
	// Open Graph URL
	OgURL string `json:"ogUrl"`
	// Publication time of the content
	PublishedTime string `json:"publishedTime"`
	// Timestamp when the scrape was performed
	Timestamp time.Time `json:"timestamp" format:"date-time"`
	// Title of the webpage
	Title string `json:"title"`
	// Source URL of the scraped page
	URLSource string                     `json:"urlSource"`
	JSON      scrapeResponseMetadataJSON `json:"-"`
}

// scrapeResponseMetadataJSON contains the JSON metadata for the struct
// [ScrapeResponseMetadata]
type scrapeResponseMetadataJSON struct {
	StatusCode    apijson.Field
	ArticleAuthor apijson.Field
	Author        apijson.Field
	Canonical     apijson.Field
	Description   apijson.Field
	Favicon       apijson.Field
	JsonLd        apijson.Field
	Keywords      apijson.Field
	Language      apijson.Field
	ModifiedTime  apijson.Field
	OgDescription apijson.Field
	OgImage       apijson.Field
	OgSiteName    apijson.Field
	OgTitle       apijson.Field
	OgURL         apijson.Field
	PublishedTime apijson.Field
	Timestamp     apijson.Field
	Title         apijson.Field
	URLSource     apijson.Field
	raw           string
	ExtraFields   map[string]apijson.Field
}

func (r *ScrapeResponseMetadata) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r scrapeResponseMetadataJSON) RawJSON() string {
	return r.raw
}

type ScrapeResponsePdf struct {
	// URL of the generated PDF
	URL  string                `json:"url" api:"required"`
	JSON scrapeResponsePdfJSON `json:"-"`
}

// scrapeResponsePdfJSON contains the JSON metadata for the struct
// [ScrapeResponsePdf]
type scrapeResponsePdfJSON struct {
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ScrapeResponsePdf) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r scrapeResponsePdfJSON) RawJSON() string {
	return r.raw
}

type ScrapeResponseScreenshot struct {
	// URL of the screenshot image
	URL  string                       `json:"url" api:"required"`
	JSON scrapeResponseScreenshotJSON `json:"-"`
}

// scrapeResponseScreenshotJSON contains the JSON metadata for the struct
// [ScrapeResponseScreenshot]
type scrapeResponseScreenshotJSON struct {
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ScrapeResponseScreenshot) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r scrapeResponseScreenshotJSON) RawJSON() string {
	return r.raw
}

// Captures a screenshot of a specified webpage.
func (r *Client) Screenshot(ctx context.Context, body ScreenshotParams, opts ...option.RequestOption) (res *ScreenshotResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/screenshot"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type ScreenshotParams struct {
	// URL of the webpage to capture
	URL param.Field[string] `json:"url" api:"required"`
	// Delay before capturing the screenshot (in milliseconds)
	Delay param.Field[float64] `json:"delay"`
	// Capture the full page screenshot. Default is `false`.
	FullPage param.Field[bool] `json:"fullPage"`
	// Project to execute the screenshot in.
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// The desired region for the action to be performed in
	Region param.Field[interface{}] `json:"region"`
	// Use a Steel-provided residential proxy for capturing the screenshot
	UseProxy param.Field[bool] `json:"useProxy"`
}

func (r ScreenshotParams) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type ScreenshotResponse struct {
	// URL where the screenshot is hosted
	URL  string                 `json:"url" api:"required"`
	JSON screenshotResponseJSON `json:"-"`
}

// screenshotResponseJSON contains the JSON metadata for the struct
// [ScreenshotResponse]
type screenshotResponseJSON struct {
	URL         apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ScreenshotResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r screenshotResponseJSON) RawJSON() string {
	return r.raw
}
