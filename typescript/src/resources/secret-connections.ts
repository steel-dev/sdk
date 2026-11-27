// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import { APIResource } from '../resource';
import { isRequestOptions } from '../core';
import * as Core from '../core';

export class SecretConnections extends APIResource {
  /**
   * Create a secret connection
   */
  create(
    body: SecretConnectionCreateParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SecretConnectionCreateResponse> {
    return this._client.post('/v1/secret-connections', { body, ...options });
  }

  /**
   * Get a secret connection
   */
  retrieve(
    id: string,
    query?: SecretConnectionRetrieveParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SecretConnectionRetrieveResponse>;
  retrieve(id: string, options?: Core.RequestOptions): Core.APIPromise<SecretConnectionRetrieveResponse>;
  retrieve(
    id: string,
    query: SecretConnectionRetrieveParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<SecretConnectionRetrieveResponse> {
    if (isRequestOptions(query)) {
      return this.retrieve(id, {}, query);
    }
    return this._client.get(`/v1/secret-connections/${encodeURIComponent(id)}`, { query, ...options });
  }

  /**
   * Update a secret connection
   */
  update(
    id: string,
    params: SecretConnectionUpdateParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SecretConnectionUpdateResponse> {
    const { projectId, ...body } = params;
    return this._client.patch(`/v1/secret-connections/${encodeURIComponent(id)}`, {
      query: { projectId },
      body,
      ...options,
    });
  }

  /**
   * List secret connections
   */
  list(
    query?: SecretConnectionListParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SecretConnectionListResponse>;
  list(options?: Core.RequestOptions): Core.APIPromise<SecretConnectionListResponse>;
  list(
    query: SecretConnectionListParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<SecretConnectionListResponse> {
    if (isRequestOptions(query)) {
      return this.list({}, query);
    }
    return this._client.get('/v1/secret-connections', { query, ...options });
  }

  /**
   * Delete a secret connection
   */
  delete(
    id: string,
    params?: SecretConnectionDeleteParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<void>;
  delete(id: string, options?: Core.RequestOptions): Core.APIPromise<void>;
  delete(
    id: string,
    params: SecretConnectionDeleteParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<void> {
    if (isRequestOptions(params)) {
      return this.delete(id, {}, params);
    }
    const { projectId } = params;
    return this._client.delete(`/v1/secret-connections/${encodeURIComponent(id)}`, {
      query: { projectId },
      ...options,
      headers: { Accept: '*/*', ...options?.headers },
    });
  }

  /**
   * Test a secret connection
   */
  test(id: string, params?: SecretConnectionTestParams, options?: Core.RequestOptions): Core.APIPromise<void>;
  test(id: string, options?: Core.RequestOptions): Core.APIPromise<void>;
  test(
    id: string,
    params: SecretConnectionTestParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<void> {
    if (isRequestOptions(params)) {
      return this.test(id, {}, params);
    }
    const { projectId } = params;
    return this._client.post(`/v1/secret-connections/${encodeURIComponent(id)}/test`, {
      query: { projectId },
      ...options,
      headers: { Accept: '*/*', ...options?.headers },
    });
  }
}

export interface SecretConnectionCreateResponse {
  id: string;

  createdAt: string;

  enabled: boolean;

  name: string;

  provider: '1password' | 'hashicorp_vault';

  tokenBudget: SecretConnectionCreateResponse.TokenBudget | null;

  updatedAt: string;

  /**
   * For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs.
   * For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and
   * namespace. Neither proves immutable account or server identity.
   */
  vaultFingerprint: string;

  version: number;
}

export namespace SecretConnectionCreateResponse {
  export interface TokenBudget {
    burst: number;

    /**
     * Tokens per second, with at most three decimal places.
     */
    refillPerSecond: number;
  }
}

export interface SecretConnectionRetrieveResponse {
  id: string;

  createdAt: string;

  enabled: boolean;

  name: string;

  provider: '1password' | 'hashicorp_vault';

  tokenBudget: SecretConnectionRetrieveResponse.TokenBudget | null;

  updatedAt: string;

  /**
   * For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs.
   * For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and
   * namespace. Neither proves immutable account or server identity.
   */
  vaultFingerprint: string;

  version: number;
}

export namespace SecretConnectionRetrieveResponse {
  export interface TokenBudget {
    burst: number;

    /**
     * Tokens per second, with at most three decimal places.
     */
    refillPerSecond: number;
  }
}

export interface SecretConnectionUpdateResponse {
  id: string;

  createdAt: string;

  enabled: boolean;

  name: string;

  provider: '1password' | 'hashicorp_vault';

  tokenBudget: SecretConnectionUpdateResponse.TokenBudget | null;

  updatedAt: string;

  /**
   * For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs.
   * For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and
   * namespace. Neither proves immutable account or server identity.
   */
  vaultFingerprint: string;

  version: number;
}

export namespace SecretConnectionUpdateResponse {
  export interface TokenBudget {
    burst: number;

    /**
     * Tokens per second, with at most three decimal places.
     */
    refillPerSecond: number;
  }
}

export interface SecretConnectionListResponse {
  connections: Array<SecretConnectionListResponse.Connection>;
}

export namespace SecretConnectionListResponse {
  export interface Connection {
    id: string;

    createdAt: string;

    enabled: boolean;

    name: string;

    provider: '1password' | 'hashicorp_vault';

    tokenBudget: Connection.TokenBudget | null;

    updatedAt: string;

    /**
     * For 1Password, a SHA-256 diagnostic fingerprint of sorted accessible vault IDs.
     * For HashiCorp Vault, a SHA-256 fingerprint of the normalized endpoint and
     * namespace. Neither proves immutable account or server identity.
     */
    vaultFingerprint: string;

    version: number;
  }

  export namespace Connection {
    export interface TokenBudget {
      burst: number;

      /**
       * Tokens per second, with at most three decimal places.
       */
      refillPerSecond: number;
    }
  }
}

export interface SecretConnectionCreateParams {
  /**
   * Write-only provider authentication. Supply a service account token for
   * 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
   * HashiCorp AppRole.
   */
  auth: SecretConnectionCreateParams.Auth;

  name: string;

  provider: '1password' | 'hashicorp_vault';

  config?: SecretConnectionCreateParams.Config;

  projectId?: string;

  tokenBudget?: SecretConnectionCreateParams.TokenBudget | null;
}

export namespace SecretConnectionCreateParams {
  /**
   * Write-only provider authentication. Supply a service account token for
   * 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
   * HashiCorp AppRole.
   */
  export interface Auth {
    /**
     * Write-only; never returned by the API.
     */
    token?: string;

    mount?: string;

    /**
     * Write-only; never returned by the API.
     */
    roleId?: string;

    /**
     * Write-only; never returned by the API.
     */
    secretId?: string;

    /**
     * Write-only; never returned by the API.
     */
    serviceAccountToken?: string;

    type?: 'token' | 'approle';
  }

  export interface Config {
    address: string;

    caPem?: string;

    namespace?: string;
  }

  export interface TokenBudget {
    burst: number;

    /**
     * Tokens per second, with at most three decimal places.
     */
    refillPerSecond: number;
  }
}

export interface SecretConnectionRetrieveParams {
  projectId?: string;
}

export interface SecretConnectionUpdateParams {
  /**
   * Query param
   */
  projectId?: string;

  /**
   * Body param: Write-only provider authentication. Supply a service account token
   * for 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
   * HashiCorp AppRole.
   */
  auth?: SecretConnectionUpdateParams.Auth;

  /**
   * Body param
   */
  config?: SecretConnectionUpdateParams.Config;

  /**
   * Body param
   */
  enabled?: boolean;

  /**
   * Body param
   */
  name?: string;

  /**
   * Body param
   */
  tokenBudget?: SecretConnectionUpdateParams.TokenBudget | null;
}

export namespace SecretConnectionUpdateParams {
  /**
   * Write-only provider authentication. Supply a service account token for
   * 1Password, a token for HashiCorp token auth, or a role ID and secret ID for
   * HashiCorp AppRole.
   */
  export interface Auth {
    /**
     * Write-only; never returned by the API.
     */
    token?: string;

    mount?: string;

    /**
     * Write-only; never returned by the API.
     */
    roleId?: string;

    /**
     * Write-only; never returned by the API.
     */
    secretId?: string;

    /**
     * Write-only; never returned by the API.
     */
    serviceAccountToken?: string;

    type?: 'token' | 'approle';
  }

  export interface Config {
    address: string;

    caPem?: string;

    namespace?: string;
  }

  export interface TokenBudget {
    burst: number;

    /**
     * Tokens per second, with at most three decimal places.
     */
    refillPerSecond: number;
  }
}

export interface SecretConnectionListParams {
  projectId?: string;
}

export interface SecretConnectionDeleteParams {
  projectId?: string;
}

export interface SecretConnectionTestParams {
  projectId?: string;
}

export declare namespace SecretConnections {
  export {
    type SecretConnectionCreateResponse as SecretConnectionCreateResponse,
    type SecretConnectionRetrieveResponse as SecretConnectionRetrieveResponse,
    type SecretConnectionUpdateResponse as SecretConnectionUpdateResponse,
    type SecretConnectionListResponse as SecretConnectionListResponse,
    type SecretConnectionCreateParams as SecretConnectionCreateParams,
    type SecretConnectionRetrieveParams as SecretConnectionRetrieveParams,
    type SecretConnectionUpdateParams as SecretConnectionUpdateParams,
    type SecretConnectionListParams as SecretConnectionListParams,
    type SecretConnectionDeleteParams as SecretConnectionDeleteParams,
    type SecretConnectionTestParams as SecretConnectionTestParams,
  };
}
