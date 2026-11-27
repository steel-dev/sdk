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
   * The id of this execution. Use it to get or cancel the execution.
   */
  id: string;

  /**
   * What happened to the page: nothing, a change, or a possible change.
   */
  effect: 'none' | 'applied' | 'unknown';

  /**
   * The tab the execution used.
   */
  page: CommandExecutionRetrieveResponse.Page | null;

  /**
   * Commands started.
   */
  ran: number;

  /**
   * This is the stored result of an earlier request. Nothing ran again.
   */
  replayed: boolean;

  results: Array<SessionsAPI.CommandResult>;

  state: 'running' | 'completed' | 'failed' | 'cancelled' | 'timeout';

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
   * Tabs with keys or a mouse button still held down. Left out when nothing is held.
   */
  held?: Array<SessionsAPI.CommandHeld>;

  /**
   * Send it as after in the next request.
   */
  next?: string;

  observe?: CommandExecutionRetrieveResponse.Observe;

  /**
   * Why the execution stopped early, such as a cancel, a timeout, or a browser
   * restart. A failed command has its error in results instead.
   */
  stopped?: SessionsAPI.CommandErrorInfo;
}

export namespace CommandExecutionRetrieveResponse {
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
   * The id of this execution. Use it to get or cancel the execution.
   */
  id: string;

  /**
   * What happened to the page: nothing, a change, or a possible change.
   */
  effect: 'none' | 'applied' | 'unknown';

  /**
   * The tab the execution used.
   */
  page: CommandExecutionCancelResponse.Page | null;

  /**
   * Commands started.
   */
  ran: number;

  /**
   * This is the stored result of an earlier request. Nothing ran again.
   */
  replayed: boolean;

  results: Array<SessionsAPI.CommandResult>;

  state: 'running' | 'completed' | 'failed' | 'cancelled' | 'timeout';

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
   * Tabs with keys or a mouse button still held down. Left out when nothing is held.
   */
  held?: Array<SessionsAPI.CommandHeld>;

  /**
   * Send it as after in the next request.
   */
  next?: string;

  observe?: CommandExecutionCancelResponse.Observe;

  /**
   * Why the execution stopped early, such as a cancel, a timeout, or a browser
   * restart. A failed command has its error in results instead.
   */
  stopped?: SessionsAPI.CommandErrorInfo;
}

export namespace CommandExecutionCancelResponse {
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
