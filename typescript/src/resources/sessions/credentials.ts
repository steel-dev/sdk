// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import { APIResource } from '../../resource';
import * as Core from '../../core';
import * as SessionsAPI from './sessions';

export class Credentials extends APIResource {
  /**
   * The session's credential lookup and the live credentials bound to it (no
   * values). Subject to session organization and project access.
   */
  list(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionsAPI.SessionCredentials> {
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/credentials`, options);
  }

  /**
   * Attach credentialIds and/or Vault or item references in bindings. Each array
   * accepts at most 100 entries; at most 100 distinct direct items and whole Vaults
   * may be bound. Only credentials can be autofilled. Lookup becomes explicit and
   * remains so. Errors: 400 `invalid_reference`, `reference_not_found`,
   * `reference_ambiguous`, `reference_not_supported`, `credential_not_bindable`, or
   * `too_many_credentials`; 409 `credentials_not_enabled` or `session_not_running`;
   * 503 `credential_bindings_busy` (retryable).
   */
  attach(
    id: string,
    body: CredentialAttachParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionsAPI.SessionCredentials> {
    return this._client.post(`/v1/sessions/${encodeURIComponent(id)}/credentials`, { body, ...options });
  }

  /**
   * Unbind a credential from a running session. The lookup stays `explicit`: with
   * nothing bound, the session fills nothing. Errors: 404 `credential_not_attached`,
   * 409 `session_not_running`, 503 `credential_bindings_busy` (retryable).
   */
  detach(
    id: string,
    credentialId: string,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionsAPI.SessionCredentials> {
    return this._client.delete(
      `/v1/sessions/${encodeURIComponent(id)}/credentials/${encodeURIComponent(credentialId)}`,
      options,
    );
  }

  /**
   * Pass reference as a query parameter to detach one Vault or item binding,
   * including steel://connections/<connection>/vaults/<vault>[/<item>]. Does not
   * detach items covered by a whole Vault binding. Errors: 400 `invalid_reference`
   * or `reference_ambiguous`; 404 `credential_not_attached`; 409
   * `session_not_running`; 503 `credential_bindings_busy` (retryable).
   */
  detachBinding(
    id: string,
    params: CredentialDetachBindingParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionsAPI.SessionCredentials> {
    const { reference } = params;
    return this._client.delete(`/v1/sessions/${encodeURIComponent(id)}/credentials/bindings`, {
      query: { reference },
      ...options,
    });
  }

  /**
   * Replace all direct item and Vault bindings, including provider references
   * steel://connections/<connection>/vaults/<vault>[/<item>]. Supply credentialIds
   * and/or bindings; empty arrays detach all. At most 100 distinct bindings may be
   * stored. Only credentials can be autofilled. Lookup becomes explicit and remains
   * so.
   */
  replace(
    id: string,
    body: CredentialReplaceParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionsAPI.SessionCredentials> {
    return this._client.put(`/v1/sessions/${encodeURIComponent(id)}/credentials`, { body, ...options });
  }
}

export interface CredentialAttachParams {
  /**
   * At most 100 bindings; KV bindings may specify allowedOrigins.
   */
  bindings?: Array<string | CredentialAttachParams.UnionMember1>;

  /**
   * Ids of credentials (Secrets of kind `credential`) in the session's project.
   * Binding makes the session's lookup `explicit` for good.
   */
  credentialIds?: Array<string>;
}

export namespace CredentialAttachParams {
  export interface UnionMember1 {
    allowedOrigins: Array<string>;

    reference: string;
  }
}

export interface CredentialDetachBindingParams {
  /**
   * Vault or item reference to detach
   */
  reference: string;
}

export interface CredentialReplaceParams {
  /**
   * At most 100 bindings; KV bindings may specify allowedOrigins.
   */
  bindings?: Array<string | CredentialReplaceParams.UnionMember1>;

  /**
   * Ids of credentials (Secrets of kind `credential`) in the session's project.
   * Binding makes the session's lookup `explicit` for good.
   */
  credentialIds?: Array<string>;
}

export namespace CredentialReplaceParams {
  export interface UnionMember1 {
    allowedOrigins: Array<string>;

    reference: string;
  }
}

export declare namespace Credentials {
  export {
    type CredentialAttachParams as CredentialAttachParams,
    type CredentialDetachBindingParams as CredentialDetachBindingParams,
    type CredentialReplaceParams as CredentialReplaceParams,
  };
}
