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

	session, err := client.Sessions.New(ctx, steel.SessionNewParams{})
	if err != nil {
		log.Fatal(err)
	}
	defer func() {
		if _, err := client.Sessions.Release(ctx, session.ID); err != nil {
			log.Print(err)
		}
	}()

	fmt.Println("Watch the session live at", session.SessionViewerURL)
	fmt.Println("Connect Playwright or Puppeteer to", session.WebsocketURL+"&apiKey=<your API key>")
}
