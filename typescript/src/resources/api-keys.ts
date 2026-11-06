// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import { APIResource } from '../resource';
import * as Core from '../core';

/**
 * Create project-scoped API keys using an organization-scoped API key.
 */
export class APIKeys extends APIResource {
  /**
   * Create a project-scoped API key using a global (organization-scoped) API key.
   * Supply projectId for an active project in the authenticated organization;
   * scopeType must be project or omitted. Project-scoped API keys cannot create
   * keys, and global keys cannot create other global keys. Dashboard users may also
   * create project keys, or global keys with organization admin access. Returns the
   * full secret once with Cache-Control: no-store; save it securely. The new key is
   * independent: revoking the creating global key does not revoke keys it created.
   * Missing projectId returns 400; insufficient scope returns 403; an unavailable or
   * foreign project returns 404.
   */
  create(body: APIKeyCreateParams, options?: Core.RequestOptions): Core.APIPromise<APIKey> {
    return this._client.post('/v1/api-keys', { body, ...options });
  }
}

export interface APIKey {
  id: string;

  createdAt: string;

  /**
   * The full secret on creation or rotation only. Metadata reads return a masked
   * preview. Store a newly created secret securely; it cannot be retrieved later.
   */
  key: string;

  /**
   * The pinned project, or null for a global key, which binds to no project.
   */
  projectId: string | null;

  /**
   * `project` pins the key to one project. `global` authorizes access across the
   * organization and has no project binding.
   */
  scopeType: 'project' | 'global';

  updatedAt: string;

  name?: string;
}

export interface APIKeyCreateParams {
  /**
   * A human-readable label for the new key. Defaults to Default.
   */
  name?: string;

  /**
   * The active project to bind the new key to, belonging to the authenticated
   * organization. Required when authenticating with a global API key. Dashboard
   * users may omit it to use their default project.
   */
  projectId?: string;

  /**
   * Defaults to `project`. Global API keys may only create project-scoped keys. Only
   * organization admins using dashboard authentication may create a `global` key;
   * projectId is ignored for those keys.
   */
  scopeType?: 'project' | 'global';
}

export declare namespace APIKeys {
  export { type APIKey as APIKey, type APIKeyCreateParams as APIKeyCreateParams };
}
