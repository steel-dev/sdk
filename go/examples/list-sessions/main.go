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

	sessions := client.Sessions.ListAutoPaging(ctx, steel.SessionListParams{
		Status: steel.F(steel.SessionListParamsStatusLive),
	})
	for sessions.Next() {
		session := sessions.Current()
		fmt.Println(session.ID, "started at", session.CreatedAt)
	}
	if err := sessions.Err(); err != nil {
		log.Fatal(err)
	}
}
