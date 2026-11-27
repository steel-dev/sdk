// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import { APIResource } from '../resource';
import { isRequestOptions } from '../core';
import * as Core from '../core';

export class Vaults extends APIResource {
  /**
   * Create a Vault
   */
  create(body: VaultCreateParams, options?: Core.RequestOptions): Core.APIPromise<Vault> {
    return this._client.post('/v1/vaults', { body, ...options });
  }

  /**
   * Get a Vault
   */
  retrieve(id: string, query?: VaultRetrieveParams, options?: Core.RequestOptions): Core.APIPromise<Vault>;
  retrieve(id: string, options?: Core.RequestOptions): Core.APIPromise<Vault>;
  retrieve(
    id: string,
    query: VaultRetrieveParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<Vault> {
    if (isRequestOptions(query)) {
      return this.retrieve(id, {}, query);
    }
    return this._client.get(`/v1/vaults/${encodeURIComponent(id)}`, { query, ...options });
  }

  /**
   * Update a Vault
   */
  update(id: string, params: VaultUpdateParams, options?: Core.RequestOptions): Core.APIPromise<Vault> {
    const { projectId, ...body } = params;
    return this._client.patch(`/v1/vaults/${encodeURIComponent(id)}`, {
      query: { projectId },
      body,
      ...options,
    });
  }

  /**
   * List Vaults
   */
  list(query?: VaultListParams, options?: Core.RequestOptions): Core.APIPromise<VaultList>;
  list(options?: Core.RequestOptions): Core.APIPromise<VaultList>;
  list(
    query: VaultListParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<VaultList> {
    if (isRequestOptions(query)) {
      return this.list({}, query);
    }
    return this._client.get('/v1/vaults', { query, ...options });
  }

  /**
   * Soft-delete a Vault. Returns 409 while it contains any live Secrets or
   * credentials; move or delete them first.
   */
  delete(id: string, params?: VaultDeleteParams, options?: Core.RequestOptions): Core.APIPromise<void>;
  delete(id: string, options?: Core.RequestOptions): Core.APIPromise<void>;
  delete(
    id: string,
    params: VaultDeleteParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<void> {
    if (isRequestOptions(params)) {
      return this.delete(id, {}, params);
    }
    const { projectId } = params;
    return this._client.delete(`/v1/vaults/${encodeURIComponent(id)}`, {
      query: { projectId },
      ...options,
      headers: { Accept: '*/*', ...options?.headers },
    });
  }
}

export interface Vault {
  id: string;

  createdAt: string;

  description: string | null;

  /**
   * 1–63 lowercase letters, digits, hyphens, underscores or dots; must start with a
   * letter or digit.
   */
  name: string;

  projectId: string;

  updatedAt: string;
}

export interface VaultList {
  vaults: Array<VaultList.Vault>;
}

export namespace VaultList {
  export interface Vault {
    id: string;

    createdAt: string;

    description: string | null;

    /**
     * 1–63 lowercase letters, digits, hyphens, underscores or dots; must start with a
     * letter or digit.
     */
    name: string;

    projectId: string;

    updatedAt: string;
  }
}

export interface VaultCreateParams {
  /**
   * 1–63 lowercase letters, digits, hyphens, underscores or dots; must start with a
   * letter or digit.
   */
  name: string;

  description?: string | null;

  projectId?: string;
}

export interface VaultRetrieveParams {
  projectId?: string;
}

export interface VaultUpdateParams {
  /**
   * Query param
   */
  projectId?: string;

  /**
   * Body param
   */
  description?: string | null;

  /**
   * Body param: 1–63 lowercase letters, digits, hyphens, underscores or dots; must
   * start with a letter or digit.
   */
  name?: string;
}

export interface VaultListParams {
  projectId?: string;
}

export interface VaultDeleteParams {
  projectId?: string;
}

export declare namespace Vaults {
  export {
    type Vault as Vault,
    type VaultList as VaultList,
    type VaultCreateParams as VaultCreateParams,
    type VaultRetrieveParams as VaultRetrieveParams,
    type VaultUpdateParams as VaultUpdateParams,
    type VaultListParams as VaultListParams,
    type VaultDeleteParams as VaultDeleteParams,
  };
}
