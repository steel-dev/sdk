// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import { APIResource } from '../resource';
import * as Core from '../core';

export class Projects extends APIResource {
  /**
   * Get a project by ID.
   */
  retrieve(id: string, options?: Core.RequestOptions): Core.APIPromise<Project> {
    return this._client.get(`/v1/projects/${encodeURIComponent(id)}`, options);
  }

  /**
   * List projects for the authenticated organization.
   */
  list(options?: Core.RequestOptions): Core.APIPromise<ProjectsList> {
    return this._client.get('/v1/projects', options);
  }
}

export interface Project {
  id: string;

  createdAt: string;

  isDefault: boolean;

  isProduction: boolean;

  name: string;

  promotedToProductionAt: string | null;

  slug: string;

  updatedAt: string;
}

export interface ProjectsList {
  projects: Array<ProjectsList.Project>;
}

export namespace ProjectsList {
  export interface Project {
    id: string;

    createdAt: string;

    isDefault: boolean;

    isProduction: boolean;

    name: string;

    promotedToProductionAt: string | null;

    slug: string;

    updatedAt: string;
  }
}

export declare namespace Projects {
  export { type Project as Project, type ProjectsList as ProjectsList };
}
