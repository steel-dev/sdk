package main

import (
	"context"
	"fmt"
	"log"

	"github.com/steel-dev/sdk/go"
)

func main() {
	ctx := context.Background()
	client := steel.NewClient()
	url := "https://steel.dev"

	page, err := client.Scrape(ctx, steel.ScrapeParams{
		URL:    steel.F(url),
		Format: steel.F([]steel.ScrapeParamsFormat{steel.ScrapeParamsFormatMarkdown}),
	})
	if err != nil {
		log.Fatal(err)
	}
	markdown := page.Content.Markdown
	if len(markdown) > 500 {
		markdown = markdown[:500]
	}
	fmt.Println(markdown)

	screenshot, err := client.Screenshot(ctx, steel.ScreenshotParams{URL: steel.F(url), FullPage: steel.F(true)})
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Screenshot:", screenshot.URL)

	pdf, err := client.Pdf(ctx, steel.PdfParams{URL: steel.F(url)})
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("PDF:", pdf.URL)
}
