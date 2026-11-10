// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import { APIResource } from '../../resource';
import * as Core from '../../core';
import * as SessionsAPI from './sessions';

export class CommandExecutions extends APIResource {
  /**
   * Waits up to 20 seconds for an execution to finish and returns it. Use it when a
   * run answered with state running.
   */
  retrieve(
    sessionId: string,
    executionId: string,
    options?: Core.RequestOptions,
  ): Core.APIPromise<CommandExecutionRetrieveResponse> {
    return this._client.get(
      `/v1/sessions/${encodeURIComponent(sessionId)}/commands/executions/${encodeURIComponent(executionId)}`,
      options,
    );
  }

  /**
   * Stops an execution. The running command stops before its next input, and
   * commands that already ran are not undone.
   */
  cancel(
    sessionId: string,
    executionId: string,
    options?: Core.RequestOptions,
  ): Core.APIPromise<CommandExecutionCancelResponse> {
    return this._client.post(
      `/v1/sessions/${encodeURIComponent(sessionId)}/commands/executions/${encodeURIComponent(
        executionId,
      )}/cancel`,
      options,
    );
  }
}

export interface CommandExecutionRetrieveResponse {
  /**
   * The id of this execution, the same as execution.id. Use it to get or cancel the
   * execution.
   */
  id: string;

  execution: CommandExecutionRetrieveResponse.Execution;

  /**
   * The tab the execution used.
   */
  page: CommandExecutionRetrieveResponse.Page | null;

  results: Array<SessionsAPI.CommandResult>;

  bodyExpired?: boolean;

  /**
   * A dialog that is open on the tab and blocks it until it closes.
   */
  dialog?: SessionsAPI.CommandDialog;

  /**
   * Set when the execution ran with after. Whether another execution, a quick
   * action, or a browser restart changed the session since then. Input from CDP
   * clients or the live view does not count.
   */
  epochChanged?: boolean;

  /**
   * Send it as after in the next request.
   */
  next?: string;

  observe?: CommandExecutionRetrieveResponse.Observe;
}

export namespace CommandExecutionRetrieveResponse {
  export interface Execution {
    id: string;

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    /**
     * Commands started.
     */
    ran: number;

    replayed: boolean;

    state: 'queued' | 'running' | 'completed' | 'failed' | 'cancelled' | 'timeout' | 'expired';

    error?: SessionsAPI.CommandErrorInfo;
  }

  /**
   * The tab the execution used.
   */
  export interface Page {
    tab: string;

    title: string;

    url: string;
  }

  export interface Observe {
    additions?: number;

    base?: string;

    diff?: string;

    error?: SessionsAPI.CommandErrorInfo;

    full?: boolean;

    removals?: number;

    snapshot?: string;

    snapshotId?: string;
  }
}

export interface CommandExecutionCancelResponse {
  /**
   * The id of this execution, the same as execution.id. Use it to get or cancel the
   * execution.
   */
  id: string;

  execution: CommandExecutionCancelResponse.Execution;

  /**
   * The tab the execution used.
   */
  page: CommandExecutionCancelResponse.Page | null;

  results: Array<SessionsAPI.CommandResult>;

  bodyExpired?: boolean;

  /**
   * A dialog that is open on the tab and blocks it until it closes.
   */
  dialog?: SessionsAPI.CommandDialog;

  /**
   * Set when the execution ran with after. Whether another execution, a quick
   * action, or a browser restart changed the session since then. Input from CDP
   * clients or the live view does not count.
   */
  epochChanged?: boolean;

  /**
   * Send it as after in the next request.
   */
  next?: string;

  observe?: CommandExecutionCancelResponse.Observe;
}

export namespace CommandExecutionCancelResponse {
  export interface Execution {
    id: string;

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    /**
     * Commands started.
     */
    ran: number;

    replayed: boolean;

    state: 'queued' | 'running' | 'completed' | 'failed' | 'cancelled' | 'timeout' | 'expired';

    error?: SessionsAPI.CommandErrorInfo;
  }

  /**
   * The tab the execution used.
   */
  export interface Page {
    tab: string;

    title: string;

    url: string;
  }

  export interface Observe {
    additions?: number;

    base?: string;

    diff?: string;

    error?: SessionsAPI.CommandErrorInfo;

    full?: boolean;

    removals?: number;

    snapshot?: string;

    snapshotId?: string;
  }
}

export declare namespace CommandExecutions {
  export {
    type CommandExecutionRetrieveResponse as CommandExecutionRetrieveResponse,
    type CommandExecutionCancelResponse as CommandExecutionCancelResponse,
  };
}
