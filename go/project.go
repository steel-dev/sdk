// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"context"
	"errors"
	"fmt"
	"net/http"
	"net/url"
	"slices"
	"time"

	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// ProjectService contains methods and other services that help with interacting
// with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewProjectService] method instead.
type ProjectService struct {
	Options []option.RequestOption
}

// NewProjectService generates a new service that applies the given options to
// each request. These options are applied after the parent client's options (if
// there is one), and before any request-specific options.
func NewProjectService(opts ...option.RequestOption) (r *ProjectService) {
	r = &ProjectService{}
	r.Options = opts
	return
}

// Get a project by ID.
func (r *ProjectService) Get(ctx context.Context, id string, opts ...option.RequestOption) (res *Project, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/projects/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

// List projects for the authenticated organization.
func (r *ProjectService) List(ctx context.Context, opts ...option.RequestOption) (res *ProjectsList, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/projects"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, nil, &res, opts...)
	return res, err
}

type Project struct {
	ID                     string      `json:"id" api:"required" format:"uuid"`
	CreatedAt              time.Time   `json:"createdAt" api:"required" format:"date-time"`
	IsDefault              bool        `json:"isDefault" api:"required"`
	IsProduction           bool        `json:"isProduction" api:"required"`
	Name                   string      `json:"name" api:"required"`
	PromotedToProductionAt time.Time   `json:"promotedToProductionAt" api:"required,nullable" format:"date-time"`
	Slug                   string      `json:"slug" api:"required"`
	UpdatedAt              time.Time   `json:"updatedAt" api:"required" format:"date-time"`
	JSON                   projectJSON `json:"-"`
}

// projectJSON contains the JSON metadata for the struct [Project]
type projectJSON struct {
	ID                     apijson.Field
	CreatedAt              apijson.Field
	IsDefault              apijson.Field
	IsProduction           apijson.Field
	Name                   apijson.Field
	PromotedToProductionAt apijson.Field
	Slug                   apijson.Field
	UpdatedAt              apijson.Field
	raw                    string
	ExtraFields            map[string]apijson.Field
}

func (r *Project) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r projectJSON) RawJSON() string {
	return r.raw
}

type ProjectsList struct {
	Projects []ProjectsListProject `json:"projects" api:"required"`
	JSON     projectsListJSON      `json:"-"`
}

// projectsListJSON contains the JSON metadata for the struct [ProjectsList]
type projectsListJSON struct {
	Projects    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProjectsList) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r projectsListJSON) RawJSON() string {
	return r.raw
}

type ProjectsListProject struct {
	ID                     string                  `json:"id" api:"required" format:"uuid"`
	CreatedAt              time.Time               `json:"createdAt" api:"required" format:"date-time"`
	IsDefault              bool                    `json:"isDefault" api:"required"`
	IsProduction           bool                    `json:"isProduction" api:"required"`
	Name                   string                  `json:"name" api:"required"`
	PromotedToProductionAt time.Time               `json:"promotedToProductionAt" api:"required,nullable" format:"date-time"`
	Slug                   string                  `json:"slug" api:"required"`
	UpdatedAt              time.Time               `json:"updatedAt" api:"required" format:"date-time"`
	JSON                   projectsListProjectJSON `json:"-"`
}

// projectsListProjectJSON contains the JSON metadata for the struct
// [ProjectsListProject]
type projectsListProjectJSON struct {
	ID                     apijson.Field
	CreatedAt              apijson.Field
	IsDefault              apijson.Field
	IsProduction           apijson.Field
	Name                   apijson.Field
	PromotedToProductionAt apijson.Field
	Slug                   apijson.Field
	UpdatedAt              apijson.Field
	raw                    string
	ExtraFields            map[string]apijson.Field
}

func (r *ProjectsListProject) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r projectsListProjectJSON) RawJSON() string {
	return r.raw
}
