// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import { APIResource } from '../../resource';
import { isRequestOptions } from '../../core';
import * as Core from '../../core';
import * as SessionsAPI from './sessions';
import * as CaptchasAPI from './captchas';
import {
  CaptchaSolveImageParams,
  CaptchaSolveImageResponse,
  CaptchaSolveParams,
  CaptchaSolveResponse,
  CaptchaStatusResponse,
  Captchas,
} from './captchas';
import * as CommandExecutionsAPI from './command-executions';
import {
  CommandExecutionCancelResponse,
  CommandExecutionRetrieveResponse,
  CommandExecutions,
} from './command-executions';
import * as FilesAPI from './files';
import { FileUploadParams, Files } from './files';
import { type Response } from '../../_shims/index';
import { SessionsCursor, type SessionsCursorParams } from '../../pagination';

export class Sessions extends APIResource {
  captchas: CaptchasAPI.Captchas = new CaptchasAPI.Captchas(this._client);
  commandExecutions: CommandExecutionsAPI.CommandExecutions = new CommandExecutionsAPI.CommandExecutions(
    this._client,
  );
  files: FilesAPI.Files = new FilesAPI.Files(this._client);

  /**
   * Creates a new session with the provided configuration.
   */
  create(body?: SessionCreateParams, options?: Core.RequestOptions): Core.APIPromise<Session>;
  create(options?: Core.RequestOptions): Core.APIPromise<Session>;
  create(
    body: SessionCreateParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<Session> {
    return this._client.post('/v1/sessions', { body, ...options });
  }

  /**
   * Retrieves details of a specific session by ID.
   */
  retrieve(id: string, options?: Core.RequestOptions): Core.APIPromise<Session> {
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}`, options);
  }

  /**
   * Fetches all active sessions for the current organization.
   */
  list(
    query?: SessionListParams,
    options?: Core.RequestOptions,
  ): Core.PagePromise<SessionslistSessionsSessionsCursor, Sessionslist.Session>;
  list(
    options?: Core.RequestOptions,
  ): Core.PagePromise<SessionslistSessionsSessionsCursor, Sessionslist.Session>;
  list(
    query: SessionListParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.PagePromise<SessionslistSessionsSessionsCursor, Sessionslist.Session> {
    return this._client.getAPIList('/v1/sessions', SessionslistSessionsSessionsCursor, { query, ...options });
  }

  /**
   * Query the semantic agent trace timeline for a session: a coalesced,
   * replay-oriented record of user/agent interactions (clicks, typing, scrolls,
   * drags, navigations) with target metadata (role, accessible name, selectors).
   * Returns an empty list for sessions that predate browser-interaction capture.
   */
  agentTraces(
    id: string,
    query?: SessionAgentTracesParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionAgentTracesResponse>;
  agentTraces(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionAgentTracesResponse>;
  agentTraces(
    id: string,
    query: SessionAgentTracesParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionAgentTracesResponse> {
    if (isRequestOptions(query)) {
      return this.agentTraces(id, {}, query);
    }
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/agent-traces`, { query, ...options });
  }

  /**
   * Drives the session's browser with high-level commands such as snapshot, click,
   * and fill. Commands run in order and stop at the first failure. Send a random
   * Idempotency-Key header to make retries safe.
   */
  commands(
    sessionId: string,
    params: SessionCommandsParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionCommandsResponse> {
    const { 'idempotency-key': idempotencyKey, ...body } = params;
    return this._client.post(`/v1/sessions/${encodeURIComponent(sessionId)}/commands`, {
      body,
      ...options,
      headers: {
        ...(idempotencyKey != null ? { 'idempotency-key': idempotencyKey } : undefined),
        ...options?.headers,
      },
    });
  }

  /**
   * Execute computer actions like mouse movements, clicks, keyboard input, and more
   */
  computer(
    sessionId: string,
    body: SessionComputerParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionComputerResponse> {
    return this._client.post(`/v1/sessions/${encodeURIComponent(sessionId)}/computer`, { body, ...options });
  }

  /**
   * Fetches the context data of a specific session.
   */
  context(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionContext> {
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/context`, options);
  }

  /**
   * Returns the finalized cost of a released session as an exact amount in US
   * dollars (e.g. 0.031905), suitable for precise billing passthrough.
   */
  cost(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionCostResponse> {
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/cost`, options);
  }

  /**
   * This endpoint allows you to get the recorded session events in the RRWeb format
   */
  events(
    id: string,
    query?: SessionEventsParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionEventsResponse>;
  events(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionEventsResponse>;
  events(
    id: string,
    query: SessionEventsParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionEventsResponse> {
    if (isRequestOptions(query)) {
      return this.events(id, {}, query);
    }
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/events`, { query, ...options });
  }

  /**
   * Get HLS playlist for a session recording using app auth or an RTC token
   */
  hls(id: string, options?: Core.RequestOptions): Core.APIPromise<Response> {
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/hls`, {
      ...options,
      headers: { Accept: 'application/vnd.apple.mpegurl', ...options?.headers },
      __binaryResponse: true,
    });
  }

  /**
   * Returns the live state of the session, including pages, tabs, and browser state
   */
  liveDetails(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionLiveDetailsResponse> {
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/live-details`, options);
  }

  /**
   * Query browser event logs for a session
   */
  logs(
    id: string,
    query?: SessionLogsParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionLogsResponse>;
  logs(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionLogsResponse>;
  logs(
    id: string,
    query: SessionLogsParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionLogsResponse> {
    if (isRequestOptions(query)) {
      return this.logs(id, {}, query);
    }
    return this._client.get(`/v1/sessions/${encodeURIComponent(id)}/logs`, { query, ...options });
  }

  /**
   * Releases a specific session by ID.
   */
  release(id: string, options?: Core.RequestOptions): Core.APIPromise<SessionReleaseResponse> {
    return this._client.post(`/v1/sessions/${encodeURIComponent(id)}/release`, options);
  }

  /**
   * Releases all active sessions for the current organization.
   */
  releaseAll(
    params?: SessionReleaseAllParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionReleaseAllResponse>;
  releaseAll(options?: Core.RequestOptions): Core.APIPromise<SessionReleaseAllResponse>;
  releaseAll(
    params: SessionReleaseAllParams | Core.RequestOptions = {},
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionReleaseAllResponse> {
    if (isRequestOptions(params)) {
      return this.releaseAll({}, params);
    }
    const { projectId } = params;
    return this._client.post('/v1/sessions/release', { query: { projectId }, ...options });
  }

  /**
   * Atomically replace customer metadata. Send an empty object to clear it. Last
   * accepted write wins; use PATCH to change individual keys. Subject to session
   * organization and project access.
   */
  replaceMetadata(
    id: string,
    body: SessionReplaceMetadataParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionReplaceMetadataResponse> {
    return this._client.put(`/v1/sessions/${encodeURIComponent(id)}/metadata`, { body, ...options });
  }

  /**
   * Returns current signal labels and meanings for customer integrations. Uses
   * normal Steel API-key authentication. Includes no session data or administrative
   * signals. Definitions do not establish that detection ran for a particular
   * session.
   */
  signalCatalog(options?: Core.RequestOptions): Core.APIPromise<SessionSignalCatalogResponse> {
    return this._client.get('/v1/sessions/signals/catalog', options);
  }

  /**
   * Atomically merge keys into customer metadata. Set a key to null to remove it;
   * keys you leave out are kept, so concurrent updates to different keys are all
   * kept. Returns the merged metadata, which must stay within the metadata limits.
   * Subject to session organization and project access.
   */
  updateMetadata(
    id: string,
    body: SessionUpdateMetadataParams,
    options?: Core.RequestOptions,
  ): Core.APIPromise<SessionUpdateMetadataResponse> {
    return this._client.patch(`/v1/sessions/${encodeURIComponent(id)}/metadata`, { body, ...options });
  }
}

export class SessionslistSessionsSessionsCursor extends SessionsCursor<Sessionslist.Session> {}

/**
 * Opens a URL in the tab. Only http, https, and about:blank. A URL without a
 * scheme gets https://.
 */
export type Command =
  | Command.NavigateCommand
  | Command.BackCommand
  | Command.ForwardCommand
  | Command.ReloadCommand
  | Command.SnapshotCommand
  | Command.ClickCommand
  | Command.DblclickCommand
  | Command.HoverCommand
  | Command.FocusCommand
  | Command.TypeCommand
  | Command.FillCommand
  | Command.PressCommand
  | Command.KeydownCommand
  | Command.KeyupCommand
  | Command.MouseCommand
  | Command.SelectCommand
  | Command.CheckCommand
  | Command.UploadCommand
  | Command.DragCommand
  | Command.UncheckCommand
  | Command.GetCommand
  | Command.IsCommand
  | Command.WaitCommand
  | Command.ScreenshotCommand
  | Command.EvalCommand
  | Command.PdfCommand
  | Command.ScrollCommand
  | Command.FindCommand
  | Command.TabCommand;

export namespace Command {
  /**
   * Opens a URL in the tab. Only http, https, and about:blank. A URL without a
   * scheme gets https://.
   */
  export interface NavigateCommand {
    command: 'navigate';

    url: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Goes back one history entry.
   */
  export interface BackCommand {
    command: 'back';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Goes forward one history entry.
   */
  export interface ForwardCommand {
    command: 'forward';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Reloads the tab.
   */
  export interface ReloadCommand {
    command: 'reload';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Returns the accessibility tree of the page, with a ref for each element you can
   * act on.
   */
  export interface SnapshotCommand {
    command: 'snapshot';

    /**
     * Only lines with refs or values, and their ancestors.
     */
    compact?: boolean;

    /**
     * Only lines up to this depth.
     */
    depth?: number;

    /**
     * Only lines with refs.
     */
    interactive?: boolean;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Clicks the element with the mouse.
   */
  export interface ClickCommand {
    command: 'click';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Double-clicks the element with the mouse.
   */
  export interface DblclickCommand {
    command: 'dblclick';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Moves the mouse onto the element.
   */
  export interface HoverCommand {
    command: 'hover';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Focuses the element.
   */
  export interface FocusCommand {
    command: 'focus';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Types text one key press at a time, after the current content.
   */
  export interface TypeCommand {
    command: 'type';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    text: string;

    /**
     * Replace the current content.
     */
    clear?: boolean;

    /**
     * Milliseconds to wait between key presses.
     */
    delay?: number;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Replaces the content of a field in one step, without key presses.
   */
  export interface FillCommand {
    command: 'fill';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    value: string;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Presses a key, such as Enter, or a combination such as Control+a.
   */
  export interface PressCommand {
    command: 'press';

    key: string;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Holds a key down until its keyup.
   */
  export interface KeydownCommand {
    command: 'keydown';

    key: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Lets go of a key held with keydown.
   */
  export interface KeyupCommand {
    command: 'keyup';

    key: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Mouse input at a point in CSS pixels of the visible part of the page, for pages
   * without useful elements such as a canvas.
   */
  export interface MouseCommand {
    action: 'move' | 'down' | 'up' | 'click' | 'wheel';

    command: 'mouse';

    button?: 'left' | 'right' | 'middle';

    clicks?: number;

    deltaX?: number;

    deltaY?: number;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    x?: number;

    y?: number;
  }

  /**
   * Selects options. Matches by value, then by label.
   */
  export interface SelectCommand {
    command: 'select';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    values: string | Array<string>;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Checks a checkbox, radio button, switch, or toggle button.
   */
  export interface CheckCommand {
    command: 'check';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Sets the files of a file input. Upload the files to the session first.
   */
  export interface UploadCommand {
    command: 'upload';

    files: string | Array<string>;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Drags one element onto another with the mouse.
   */
  export interface DragCommand {
    command: 'drag';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    source: SessionsAPI.CommandTarget;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Unchecks a checkbox, switch, or toggle button.
   */
  export interface UncheckCommand {
    command: 'uncheck';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Reads something from the page or an element.
   */
  export interface GetCommand {
    command: 'get';

    what: 'text' | 'html' | 'markdown' | 'value' | 'attr' | 'title' | 'url' | 'count' | 'box' | 'locator';

    /**
     * The attribute to read with attr.
     */
    attribute?: string;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Checks the state of an element.
   */
  export interface IsCommand {
    command: 'is';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    what: 'visible' | 'enabled' | 'checked';

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Waits for one of an element, text, a URL, a load state, a download, or a fixed
   * time.
   */
  export interface WaitCommand {
    command: 'wait';

    /**
     * Waits for the next download to finish.
     */
    download?: true;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    load?: 'load' | 'domcontentloaded' | 'networkidle';

    ms?: number;

    state?: 'visible' | 'hidden' | 'attached' | 'detached';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;

    text?: string;

    /**
     * Milliseconds. 10000 by default.
     */
    timeout?: number;

    /**
     * A URL pattern. \* matches any characters.
     */
    url?: string;
  }

  /**
   * Captures the page or an element as base64 data.
   */
  export interface ScreenshotCommand {
    command: 'screenshot';

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    format?: 'png' | 'jpeg' | 'webp';

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    fullPage?: boolean;

    quality?: number;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Runs JavaScript in the page and returns the result as JSON.
   */
  export interface EvalCommand {
    command: 'eval';

    script: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * Milliseconds. 10000 by default.
     */
    timeout?: number;
  }

  /**
   * Prints the page to a PDF as base64 data.
   */
  export interface PdfCommand {
    command: 'pdf';

    landscape?: boolean;

    printBackground?: boolean;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Scrolls the page, or an element when target is set.
   */
  export interface ScrollCommand {
    command: 'scroll';

    /**
     * Pixels. 300 by default.
     */
    amount?: number;

    direction?: 'up' | 'down' | 'left' | 'right';

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Finds elements and returns refs for up to 50 matches. With nth, it returns that
   * one match.
   */
  export interface FindCommand {
    command: 'find';

    /**
     * A CSS selector or a locator, not a ref.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Lists, opens, or closes tabs. The last tab cannot be closed.
   */
  export interface TabCommand {
    action: 'list' | 'new' | 'close';

    command: 'tab';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    url?: string;
  }
}

/**
 * A JavaScript dialog: alert, confirm, prompt, or beforeunload.
 */
export interface CommandDialog {
  id: string;

  message: string;

  tab: string;

  type: 'alert' | 'confirm' | 'prompt' | 'beforeunload';

  defaultPrompt?: string;
}

export interface CommandErrorInfo {
  /**
   * Error code, such as stale_ref or not_actionable.
   */
  code: string;

  message: string;

  /**
   * More detail, such as covered or detached.
   */
  reason?: string;
}

/**
 * Fails the command when the element does not have this role or name.
 */
export interface CommandExpectation {
  name?: string;

  role?: string;
}

/**
 * The iframe to look in. List nested iframes from the outside in.
 */
export type CommandFrame = string | CommandLocator | Array<CommandTarget>;

/**
 * Finds an element. Set exactly one of role, text, label, placeholder, alt, title,
 * testid, css.
 */
export interface CommandLocator {
  alt?: string;

  css?: string;

  /**
   * Match the whole text. Text matches a case-insensitive substring by default.
   */
  exact?: boolean;

  label?: string;

  /**
   * The accessible name. Works only with role.
   */
  name?: string;

  /**
   * Picks one of several matches. Negative values count from the end.
   */
  nth?: number;

  placeholder?: string;

  role?: string;

  testid?: string;

  text?: string;

  title?: string;
}

/**
 * Result of one command, with the fields of that command.
 */
export type CommandResult =
  | CommandResult.NavigateResult
  | CommandResult.BackResult
  | CommandResult.ForwardResult
  | CommandResult.ReloadResult
  | CommandResult.SnapshotResult
  | CommandResult.ClickResult
  | CommandResult.DblclickResult
  | CommandResult.HoverResult
  | CommandResult.FocusResult
  | CommandResult.TypeResult
  | CommandResult.FillResult
  | CommandResult.PressResult
  | CommandResult.KeydownResult
  | CommandResult.KeyupResult
  | CommandResult.MouseResult
  | CommandResult.SelectResult
  | CommandResult.CheckResult
  | CommandResult.UncheckResult
  | CommandResult.UploadResult
  | CommandResult.DragResult
  | CommandResult.GetResult
  | CommandResult.IsResult
  | CommandResult.WaitResult
  | CommandResult.ScreenshotResult
  | CommandResult.EvalResult
  | CommandResult.PdfResult
  | CommandResult.ScrollResult
  | CommandResult.FindResult
  | CommandResult.TabResult;

export namespace CommandResult {
  /**
   * Result of a navigate command.
   */
  export interface NavigateResult {
    command: 'navigate';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    url?: string;
  }

  /**
   * Result of a back command.
   */
  export interface BackResult {
    command: 'back';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * False when there was no history entry.
     */
    navigated?: boolean;

    url?: string;
  }

  /**
   * Result of a forward command.
   */
  export interface ForwardResult {
    command: 'forward';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * False when there was no history entry.
     */
    navigated?: boolean;

    url?: string;
  }

  /**
   * Result of a reload command.
   */
  export interface ReloadResult {
    command: 'reload';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    url?: string;
  }

  /**
   * Result of a snapshot command.
   */
  export interface SnapshotResult {
    command: 'snapshot';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    snapshot?: string;

    snapshotId?: string;
  }

  /**
   * Result of a click command.
   */
  export interface ClickResult {
    command: 'click';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a dblclick command.
   */
  export interface DblclickResult {
    command: 'dblclick';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a hover command.
   */
  export interface HoverResult {
    command: 'hover';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;
  }

  /**
   * Result of a focus command.
   */
  export interface FocusResult {
    command: 'focus';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;
  }

  /**
   * Result of a type command.
   */
  export interface TypeResult {
    command: 'type';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a fill command.
   */
  export interface FillResult {
    command: 'fill';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a press command.
   */
  export interface PressResult {
    command: 'press';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a keydown command.
   */
  export interface KeydownResult {
    command: 'keydown';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a keyup command.
   */
  export interface KeyupResult {
    command: 'keyup';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a mouse command.
   */
  export interface MouseResult {
    command: 'mouse';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a select command.
   */
  export interface SelectResult {
    command: 'select';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;

    /**
     * The values of the selected options.
     */
    selected?: Array<string>;
  }

  /**
   * Result of a check command.
   */
  export interface CheckResult {
    command: 'check';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    checked?: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a uncheck command.
   */
  export interface UncheckResult {
    command: 'uncheck';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    checked?: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a upload command.
   */
  export interface UploadResult {
    command: 'upload';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    files?: Array<string>;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a drag command.
   */
  export interface DragResult {
    command: 'drag';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;
  }

  /**
   * Result of a get command.
   */
  export interface GetResult {
    command: 'get';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The value is a secret, so it is left out.
     */
    masked?: boolean;

    /**
     * A string for text, html, markdown, value, attr, title, and url. A number for
     * count. { x, y, width, height } or null for box. A list of locators for locator.
     */
    value?: unknown;
  }

  /**
   * Result of a is command.
   */
  export interface IsResult {
    command: 'is';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    value?: boolean;
  }

  /**
   * Result of a wait command.
   */
  export interface WaitResult {
    command: 'wait';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    bytes?: number;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The downloaded file, for download.
     */
    file?: string;

    waited?: 'ms' | 'target' | 'text' | 'url' | 'load' | 'download';
  }

  /**
   * Result of a screenshot command.
   */
  export interface ScreenshotResult {
    command: 'screenshot';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    data?: string;

    error?: SessionsAPI.CommandErrorInfo;

    format?: 'png' | 'jpeg' | 'webp';
  }

  /**
   * Result of a eval command.
   */
  export interface EvalResult {
    command: 'eval';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    /**
     * A dialog that the command opened. It blocks the tab until it closes.
     */
    dialog?: SessionsAPI.CommandDialog;

    error?: SessionsAPI.CommandErrorInfo;

    /**
     * The command started a navigation that was still loading when it returned.
     */
    loading?: boolean;

    /**
     * The result of the script as JSON.
     */
    value?: unknown;
  }

  /**
   * Result of a pdf command.
   */
  export interface PdfResult {
    command: 'pdf';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    data?: string;

    error?: SessionsAPI.CommandErrorInfo;

    format?: 'pdf';
  }

  /**
   * Result of a scroll command.
   */
  export interface ScrollResult {
    command: 'scroll';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    moved?: boolean;

    x?: number;

    y?: number;
  }

  /**
   * Result of a find command.
   */
  export interface FindResult {
    command: 'find';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    count?: number;

    error?: SessionsAPI.CommandErrorInfo;

    matches?: Array<FindResult.Match>;
  }

  export namespace FindResult {
    export interface Match {
      name: string;

      ref: string;

      role: string;
    }
  }

  /**
   * Result of a tab command.
   */
  export interface TabResult {
    command: 'tab';

    /**
     * What happened to the page: nothing, a change, or a possible change.
     */
    effect: 'none' | 'applied' | 'unknown';

    ok: boolean;

    error?: SessionsAPI.CommandErrorInfo;

    tab?: string;

    tabs?: Array<TabResult.Tab>;
  }

  export namespace TabResult {
    export interface Tab {
      tab: string;

      title: string;

      url: string;
    }
  }
}

/**
 * A ref from a snapshot such as e12, a CSS selector, or a locator.
 */
export type CommandTarget = string | CommandLocator;

/**
 * Represents the data structure for a browser session, including its configuration
 * and status.
 */
export interface Session {
  /**
   * Unique identifier for the session
   */
  id: string;

  /**
   * Browser mode used for this session.
   */
  browserMode: 'stealth' | 'standard' | 'unknown';

  /**
   * Timestamp when the session started
   */
  createdAt: string;

  /**
   * Amount of credits consumed by the session
   */
  creditsUsed: number;

  /**
   * URL for debugging the session
   */
  debugUrl: string;

  /**
   * Viewport and browser window dimensions for the session
   */
  dimensions: Session.Dimensions;

  /**
   * Duration of the session in milliseconds
   */
  duration: number;

  /**
   * Number of events processed in the session
   */
  eventCount: number;

  /**
   * Bandwidth optimizations that were applied to the session.
   */
  optimizeBandwidth: Session.OptimizeBandwidth;

  /**
   * Amount of data transmitted through the proxy
   */
  proxyBytesUsed: number;

  /**
   * Source of the proxy used for the session
   */
  proxySource: 'steel' | 'external' | null;

  /**
   * URL to view session details
   */
  sessionViewerUrl: string;

  /**
   * Signals observed during the session. Filled in within a few seconds after the
   * session ends, and empty until then. An empty list does not mean nothing
   * happened: detection is heuristic and coverage is incomplete.
   */
  signals: Array<
    | 'captcha_encountered'
    | 'proxy_connection_failed'
    | 'session_timeout_exceeded'
    | 'bot_detection_triggered'
    | 'context_restored'
  >;

  /**
   * Status of the session
   */
  status: 'live' | 'released' | 'failed';

  /**
   * Session timeout duration in milliseconds
   */
  timeout: number;

  /**
   * URL for the session's WebSocket connection
   */
  websocketUrl: string;

  /**
   * Configuration for the debug URL and session viewer. Controls interaction
   * capabilities and cursor visibility.
   */
  debugConfig?: Session.DebugConfig;

  /**
   * Device configuration for the session
   */
  deviceConfig?: Session.DeviceConfig;

  /**
   * Launch the browser in fullscreen mode, covering the full screen with no Chrome
   * UI.
   */
  fullscreen?: boolean;

  /**
   * Indicates if the session is headless or headful
   */
  headless?: boolean;

  /**
   * Inactivity timeout in milliseconds, if one was set when the session was created
   */
  inactivityTimeout?: number;

  /**
   * Indicates if Selenium is used in the session
   */
  isSelenium?: boolean;

  /**
   * Customer-defined flat metadata. Values are always strings: numbers and booleans
   * sent on write come back in their string form.
   */
  metadata?: { [key: string]: string };

  /**
   * This flag will persist the profile for the session.
   */
  persistProfile?: boolean;

  /**
   * The ID of the profile associated with the session
   */
  profileId?: string;

  /**
   * The project associated with the session
   */
  projectId?: string | null;

  /**
   * The region where the session was created.
   */
  region?: string;

  /**
   * Why the session reached a terminal state. Null while the session is live, or
   * when the reason is unknown (e.g. sessions created before this was tracked). One
   * of: user_requested (released via the API/SDK), timeout (hard `timeout` elapsed),
   * inactivity_timeout (no activity for the configured window), creation_timeout
   * (never started in time), startup_failed (could not be dispatched),
   * browser_closed (the browser or agent closed itself — not a crash),
   * browser_crashed (the browser crashed or its machine became unresponsive).
   */
  releaseReason?:
    | 'user_requested'
    | 'timeout'
    | 'inactivity_timeout'
    | 'creation_timeout'
    | 'startup_failed'
    | 'browser_closed'
    | 'browser_crashed'
    | null;

  /**
   * Indicates if captcha solving is enabled
   */
  solveCaptcha?: boolean;

  /**
   * Stealth configuration for the session
   */
  stealthConfig?: Session.StealthConfig;

  /**
   * User agent string used in the session
   */
  userAgent?: string;
}

export namespace Session {
  /**
   * Viewport and browser window dimensions for the session
   */
  export interface Dimensions {
    /**
     * Height of the browser window
     */
    height: number;

    /**
     * Width of the browser window
     */
    width: number;
  }

  /**
   * Bandwidth optimizations that were applied to the session.
   */
  export interface OptimizeBandwidth {
    blockHosts?: Array<string>;

    blockImages?: boolean;

    blockMedia?: boolean;

    blockStylesheets?: boolean;

    blockUrlPatterns?: Array<string>;
  }

  /**
   * Configuration for the debug URL and session viewer. Controls interaction
   * capabilities and cursor visibility.
   */
  export interface DebugConfig {
    /**
     * Whether interaction is allowed via the debug URL viewer. When false, the session
     * viewer is view-only.
     */
    interactive?: boolean;

    /**
     * Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode
     * only).
     */
    systemCursor?: boolean;
  }

  /**
   * Device configuration for the session
   */
  export interface DeviceConfig {
    device?: 'desktop' | 'mobile';
  }

  /**
   * Stealth configuration for the session
   */
  export interface StealthConfig {
    /**
     * When true, captchas will be automatically solved when detected. When false, use
     * the solve endpoints to manually initiate solving.
     */
    autoCaptchaSolving?: boolean;

    /**
     * This flag will make the browser act more human-like by moving the mouse in a
     * more natural way
     */
    humanizeInteractions?: boolean;

    /**
     * This flag will skip the fingerprint generation for the session.
     */
    skipFingerprintInjection?: boolean;
  }
}

/**
 * Session context data returned from a browser session.
 */
export interface SessionContext {
  /**
   * Cookies to initialize in the session
   */
  cookies?: Array<SessionContext.Cookie>;

  /**
   * Domain-specific indexedDB items to initialize in the session
   */
  indexedDB?: { [key: string]: Array<SessionContext.IndexedDB> };

  /**
   * Domain-specific localStorage items to initialize in the session
   */
  localStorage?: { [key: string]: { [key: string]: string } };

  /**
   * Domain-specific sessionStorage items to initialize in the session
   */
  sessionStorage?: { [key: string]: { [key: string]: string } };
}

export namespace SessionContext {
  export interface Cookie {
    /**
     * The name of the cookie
     */
    name: string;

    /**
     * The value of the cookie
     */
    value: string;

    /**
     * The domain of the cookie
     */
    domain?: string;

    /**
     * The expiration date of the cookie
     */
    expires?: number;

    /**
     * Whether the cookie is HTTP only
     */
    httpOnly?: boolean;

    /**
     * The partition key of the cookie
     */
    partitionKey?: Cookie.PartitionKey;

    /**
     * The path of the cookie
     */
    path?: string;

    /**
     * The priority of the cookie
     */
    priority?: 'Low' | 'Medium' | 'High';

    /**
     * Whether the cookie is a same party cookie
     */
    sameParty?: boolean;

    /**
     * The same site attribute of the cookie
     */
    sameSite?: 'Strict' | 'Lax' | 'None';

    /**
     * Whether the cookie is secure
     */
    secure?: boolean;

    /**
     * Whether the cookie is a session cookie
     */
    session?: boolean;

    /**
     * The size of the cookie
     */
    size?: number;

    /**
     * The source port of the cookie
     */
    sourcePort?: number;

    /**
     * The source scheme of the cookie
     */
    sourceScheme?: 'Unset' | 'NonSecure' | 'Secure';

    /**
     * The URL of the cookie
     */
    url?: string;
  }

  export namespace Cookie {
    /**
     * The partition key of the cookie
     */
    export interface PartitionKey {
      /**
       * Indicates if the cookie has any ancestors that are cross-site to the
       * topLevelSite.
       */
      hasCrossSiteAncestor: boolean;

      /**
       * The site of the top-level URL the browser was visiting at the start of the
       * request to the endpoint that set the cookie.
       */
      topLevelSite: string;
    }
  }

  export interface IndexedDB {
    id: number;

    data: Array<IndexedDB.Data>;

    name: string;
  }

  export namespace IndexedDB {
    export interface Data {
      id: number;

      name: string;

      records: Array<Data.Record>;
    }

    export namespace Data {
      export interface Record {
        key: unknown;

        value: unknown;

        blobFiles?: Array<Record.BlobFile>;
      }

      export namespace Record {
        export interface BlobFile {
          blobNumber: number;

          mimeType: string;

          size: number;

          filename?: string;

          lastModified?: string;

          path?: string;
        }
      }
    }
  }
}

/**
 * Response containing a list of browser sessions with pagination details.
 */
export interface Sessionslist {
  /**
   * Cursor for the next page of results. Null if no more pages.
   */
  nextCursor: string | null;

  /**
   * List of browser sessions
   */
  sessions: Array<Sessionslist.Session>;

  /**
   * Exact number of live sessions in the organization/project scope. Only included
   * for status=live without metadata, signal, releaseReason, createdAfter or
   * createdBefore filters. Other queries omit this field; use nextCursor to paginate
   * until it is null.
   */
  totalCount?: number;
}

export namespace Sessionslist {
  /**
   * Represents the data structure for a browser session, including its configuration
   * and status.
   */
  export interface Session {
    /**
     * Unique identifier for the session
     */
    id: string;

    /**
     * Browser mode used for this session.
     */
    browserMode: 'stealth' | 'standard' | 'unknown';

    /**
     * Timestamp when the session started
     */
    createdAt: string;

    /**
     * Amount of credits consumed by the session
     */
    creditsUsed: number;

    /**
     * URL for debugging the session
     */
    debugUrl: string;

    /**
     * Viewport and browser window dimensions for the session
     */
    dimensions: Session.Dimensions;

    /**
     * Duration of the session in milliseconds
     */
    duration: number;

    /**
     * Number of events processed in the session
     */
    eventCount: number;

    /**
     * Bandwidth optimizations that were applied to the session.
     */
    optimizeBandwidth: Session.OptimizeBandwidth;

    /**
     * Amount of data transmitted through the proxy
     */
    proxyBytesUsed: number;

    /**
     * Source of the proxy used for the session
     */
    proxySource: 'steel' | 'external' | null;

    /**
     * URL to view session details
     */
    sessionViewerUrl: string;

    /**
     * Signals observed during the session. Filled in within a few seconds after the
     * session ends, and empty until then. An empty list does not mean nothing
     * happened: detection is heuristic and coverage is incomplete.
     */
    signals: Array<
      | 'captcha_encountered'
      | 'proxy_connection_failed'
      | 'session_timeout_exceeded'
      | 'bot_detection_triggered'
      | 'context_restored'
    >;

    /**
     * Status of the session
     */
    status: 'live' | 'released' | 'failed';

    /**
     * Session timeout duration in milliseconds
     */
    timeout: number;

    /**
     * URL for the session's WebSocket connection
     */
    websocketUrl: string;

    /**
     * Configuration for the debug URL and session viewer. Controls interaction
     * capabilities and cursor visibility.
     */
    debugConfig?: Session.DebugConfig;

    /**
     * Device configuration for the session
     */
    deviceConfig?: Session.DeviceConfig;

    /**
     * Launch the browser in fullscreen mode, covering the full screen with no Chrome
     * UI.
     */
    fullscreen?: boolean;

    /**
     * Indicates if the session is headless or headful
     */
    headless?: boolean;

    /**
     * Inactivity timeout in milliseconds, if one was set when the session was created
     */
    inactivityTimeout?: number;

    /**
     * Indicates if Selenium is used in the session
     */
    isSelenium?: boolean;

    /**
     * Customer-defined flat metadata. Values are always strings: numbers and booleans
     * sent on write come back in their string form.
     */
    metadata?: { [key: string]: string };

    /**
     * This flag will persist the profile for the session.
     */
    persistProfile?: boolean;

    /**
     * The ID of the profile associated with the session
     */
    profileId?: string;

    /**
     * The project associated with the session
     */
    projectId?: string | null;

    /**
     * The region where the session was created.
     */
    region?: string;

    /**
     * Why the session reached a terminal state. Null while the session is live, or
     * when the reason is unknown (e.g. sessions created before this was tracked). One
     * of: user_requested (released via the API/SDK), timeout (hard `timeout` elapsed),
     * inactivity_timeout (no activity for the configured window), creation_timeout
     * (never started in time), startup_failed (could not be dispatched),
     * browser_closed (the browser or agent closed itself — not a crash),
     * browser_crashed (the browser crashed or its machine became unresponsive).
     */
    releaseReason?:
      | 'user_requested'
      | 'timeout'
      | 'inactivity_timeout'
      | 'creation_timeout'
      | 'startup_failed'
      | 'browser_closed'
      | 'browser_crashed'
      | null;

    /**
     * Indicates if captcha solving is enabled
     */
    solveCaptcha?: boolean;

    /**
     * Stealth configuration for the session
     */
    stealthConfig?: Session.StealthConfig;

    /**
     * User agent string used in the session
     */
    userAgent?: string;
  }

  export namespace Session {
    /**
     * Viewport and browser window dimensions for the session
     */
    export interface Dimensions {
      /**
       * Height of the browser window
       */
      height: number;

      /**
       * Width of the browser window
       */
      width: number;
    }

    /**
     * Bandwidth optimizations that were applied to the session.
     */
    export interface OptimizeBandwidth {
      blockHosts?: Array<string>;

      blockImages?: boolean;

      blockMedia?: boolean;

      blockStylesheets?: boolean;

      blockUrlPatterns?: Array<string>;
    }

    /**
     * Configuration for the debug URL and session viewer. Controls interaction
     * capabilities and cursor visibility.
     */
    export interface DebugConfig {
      /**
       * Whether interaction is allowed via the debug URL viewer. When false, the session
       * viewer is view-only.
       */
      interactive?: boolean;

      /**
       * Whether the OS-level mouse cursor is shown in the WebRTC stream (headful mode
       * only).
       */
      systemCursor?: boolean;
    }

    /**
     * Device configuration for the session
     */
    export interface DeviceConfig {
      device?: 'desktop' | 'mobile';
    }

    /**
     * Stealth configuration for the session
     */
    export interface StealthConfig {
      /**
       * When true, captchas will be automatically solved when detected. When false, use
       * the solve endpoints to manually initiate solving.
       */
      autoCaptchaSolving?: boolean;

      /**
       * This flag will make the browser act more human-like by moving the mouse in a
       * more natural way
       */
      humanizeInteractions?: boolean;

      /**
       * This flag will skip the fingerprint generation for the session.
       */
      skipFingerprintInjection?: boolean;
    }
  }
}

export interface SessionAgentTracesResponse {
  events: Array<{ [key: string]: unknown }>;

  hasMore: boolean;

  total: number;
}

export interface SessionCommandsResponse {
  /**
   * The id of this execution, the same as execution.id. Use it to get or cancel the
   * execution.
   */
  id: string;

  execution: SessionCommandsResponse.Execution;

  /**
   * The tab the execution used.
   */
  page: SessionCommandsResponse.Page | null;

  results: Array<CommandResult>;

  bodyExpired?: boolean;

  /**
   * A dialog that is open on the tab and blocks it until it closes.
   */
  dialog?: CommandDialog;

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

  observe?: SessionCommandsResponse.Observe;
}

export namespace SessionCommandsResponse {
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

export interface SessionComputerResponse {
  /**
   * Base64 encoded screenshot if requested
   */
  base64_image?: string;

  /**
   * Error message if action failed
   */
  error?: string;

  /**
   * Output message from the action
   */
  output?: string;

  /**
   * System information
   */
  system?: string;
}

/**
 * Session cost breakdown in US dollars
 */
export interface SessionCostResponse {
  /**
   * Session ID
   */
  id: string;

  /**
   * Currency used for the cost values
   */
  currency: 'usd';

  /**
   * Exact total session cost in US dollars (e.g. 0.031905), with no rounding to
   * whole cents. Rounded only to micro-dollar precision (6 decimal places) for
   * precise billing passthrough.
   */
  totalCost: number;

  /**
   * Cost unit. Values are expressed in US dollars.
   */
  unit: 'dollar';

  /**
   * Billable usage inputs used to calculate the costs
   */
  usage: SessionCostResponse.Usage;
}

export namespace SessionCostResponse {
  /**
   * Billable usage inputs used to calculate the costs
   */
  export interface Usage {
    browser: Usage.Browser;

    captcha: Usage.Captcha;

    proxy: Usage.Proxy;
  }

  export namespace Usage {
    export interface Browser {
      /**
       * Billing unit for browser usage
       */
      unit: 'minute' | 'second';

      /**
       * Billable browser usage value
       */
      value: number;
    }

    export interface Captcha {
      /**
       * Billable captcha solves
       */
      solves: number;
    }

    export interface Proxy {
      /**
       * Billable Steel proxy bytes for the session
       */
      bytes: number;
    }
  }
}

/**
 * Events for a browser session
 */
export type SessionEventsResponse = Array<unknown>;

export interface SessionLiveDetailsResponse {
  pages: Array<SessionLiveDetailsResponse.Page>;

  sessionViewerFullscreenUrl: string;

  sessionViewerUrl: string;

  wsUrl: string;
}

export namespace SessionLiveDetailsResponse {
  export interface Page {
    id: string;

    favicon: string | null;

    sessionViewerFullscreenUrl: string;

    sessionViewerUrl: string;

    title: string;

    url: string;
  }
}

export interface SessionLogsResponse {
  events: Array<SessionLogsResponse.Event>;

  hasMore: boolean;

  total: number;
}

export namespace SessionLogsResponse {
  export interface Event {
    id: string;

    log: string;

    timestamp: string;

    type: string;
  }
}

/**
 * Response for releasing a single session.
 */
export interface SessionReleaseResponse {
  /**
   * Details about the outcome of the release operation
   */
  message: string;

  /**
   * Indicates if the session was successfully released
   */
  success: boolean;
}

/**
 * Response for releasing multiple sessions.
 */
export interface SessionReleaseAllResponse {
  /**
   * Details about the outcome of the release operation
   */
  message: string;

  /**
   * Indicates if the sessions were successfully released
   */
  success: boolean;
}

export interface SessionReplaceMetadataResponse {
  /**
   * Customer-defined flat metadata. Values are always strings: numbers and booleans
   * sent on write come back in their string form.
   */
  metadata: { [key: string]: string };
}

/**
 * Current customer-visible signal definitions, not per-session detection evidence
 */
export interface SessionSignalCatalogResponse {
  /**
   * Interpretation and coverage limitations of session signals
   */
  semantics: string;

  signals: Array<SessionSignalCatalogResponse.Signal>;
}

export namespace SessionSignalCatalogResponse {
  export interface Signal {
    description: string;

    label: string;

    severity: 'info' | 'warning' | 'danger' | 'success';

    source: 'manual' | 'derived';

    value:
      | 'captcha_encountered'
      | 'proxy_connection_failed'
      | 'session_timeout_exceeded'
      | 'bot_detection_triggered'
      | 'context_restored';
  }
}

export interface SessionUpdateMetadataResponse {
  /**
   * Customer-defined flat metadata. Values are always strings: numbers and booleans
   * sent on write come back in their string form.
   */
  metadata: { [key: string]: string };
}

export interface SessionCreateParams {
  /**
   * Block ads in the browser session. Default is false.
   */
  blockAds?: boolean;

  /**
   * PEM-encoded root CA certificates to trust in this session. THIS IS CURRENTLY AN
   * EXPERIMENTAL FEATURE.
   */
  caCertificates?: Array<string>;

  /**
   * Number of sessions to create concurrently (check your plan limit)
   */
  concurrency?: number;

  /**
   * Configuration for session credentials
   */
  credentials?: SessionCreateParams.Credentials;

  /**
   * Configuration for the debug URL and session viewer. Controls interaction
   * capabilities, cursor visibility, and other debug-related settings.
   */
  debugConfig?: SessionCreateParams.DebugConfig;

  /**
   * Device configuration for the session. Specify 'mobile' for mobile device
   * fingerprints and configurations.
   */
  deviceConfig?: SessionCreateParams.DeviceConfig;

  /**
   * Viewport and browser window dimensions for the session. Mobile sessions require
   * dimensions of at least 508x1074; smaller mobile dimensions are rejected with a
   * 400 response.
   */
  dimensions?: SessionCreateParams.Dimensions;

  /**
   * Enable experimental features for the session.
   */
  experimentalFeatures?: Array<string>;

  /**
   * Array of extension IDs to install in the session. Use ['all_ext'] to install all
   * uploaded extensions.
   */
  extensionIds?: Array<string>;

  /**
   * Launch the browser in fullscreen mode, covering the full screen with no Chrome
   * UI. Default is false.
   */
  fullscreen?: boolean;

  /**
   * Enable headless browser mode (disable Headful mode)
   */
  headless?: boolean;

  /**
   * Inactivity timeout in milliseconds. When set, the session is released if no CDP
   * command or remote input is received for this duration, even if `timeout` has not
   * yet elapsed. Note that `timeout` remains the hard cap on session lifetime: if
   * `inactivityTimeout` is greater than or equal to the effective `timeout`, it has
   * no effect since `timeout` always elapses first. Omit to disable.
   */
  inactivityTimeout?: number;

  /**
   * @deprecated Deprecated. Selenium sessions are no longer supported, and a request
   * that sets this to true is rejected with 400. Connect over CDP with Playwright or
   * Puppeteer instead.
   */
  isSelenium?: boolean;

  /**
   * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys
   * must start with a letter, use only letters, digits, underscores, dots or
   * hyphens, and be at most 64 characters; steel\_ is reserved. Values are strings
   * (up to 1024 characters, valid Unicode without NUL), finite numbers or booleans.
   * Numbers and booleans are stored as strings, so 3 and "3" are the same value.
   */
  metadata?: { [key: string]: string | number | boolean };

  /**
   * The namespace the session should be created against. Defaults to "default".
   */
  namespace?: string;

  /**
   * Enable bandwidth optimizations. Passing true enables all flags (except
   * hosts/patterns). Object allows granular control.
   */
  optimizeBandwidth?: boolean | SessionCreateParams.UnionMember1;

  /**
   * This flag will persist the user profile for the session.
   */
  persistProfile?: boolean;

  /**
   * This flag will set the profile for the session.
   */
  profileId?: string;

  /**
   * The project to create the session in. When provided, the session namespace is
   * resolved from the project.
   */
  projectId?: string;

  /**
   * Custom proxy URL for the browser session. Overrides useProxy, disabling
   * Steel-provided proxies in favor of your specified proxy. Format:
   * http(s)://username:password@hostname:port
   */
  proxyUrl?: string;

  /**
   * Record the headful session for playback after it ends. Default is true.
   * Disabling recording does not affect live WHEP viewing.
   */
  recording?: boolean;

  /**
   * The desired region for the session. Available: us-east, us-west, us-central,
   * eu-west, eu-central, ap-northeast, ap-southeast, sa-east. Legacy codes (iad,
   * lax, ord) are also accepted.
   */
  region?: unknown;

  /**
   * Session context data to be used in the created session. Sessions will start with
   * an empty context by default.
   */
  sessionContext?: SessionCreateParams.SessionContext;

  /**
   * Optional custom UUID for the session
   */
  sessionId?: string;

  /**
   * Enable automatic captcha solving. Default is false.
   */
  solveCaptcha?: boolean;

  /**
   * Stealth configuration for the session
   */
  stealthConfig?: SessionCreateParams.StealthConfig;

  /**
   * Session timeout duration in milliseconds. Default is 300000 (5 minutes).
   */
  timeout?: number;

  /**
   * Simple boolean to enable/disable Steel proxies
   */
  useProxy?:
    | boolean
    | SessionCreateParams.Geolocation
    | SessionCreateParams.Server
    | SessionCreateParams.UnionMember3
    | unknown;

  /**
   * Custom user agent string for the browser session
   */
  userAgent?: string;
}

export namespace SessionCreateParams {
  /**
   * Configuration for session credentials
   */
  export interface Credentials {
    autoSubmit?: boolean;

    blurFields?: boolean;

    /**
     * Credential provider to use for runtime credential resolution. Defaults to
     * "steel".
     */
    credentialProvider?: string;

    exactOrigin?: boolean;

    /**
     * Optional allowlist of credential origins to inject into this session.
     */
    origins?: Array<string>;
  }

  /**
   * Configuration for the debug URL and session viewer. Controls interaction
   * capabilities, cursor visibility, and other debug-related settings.
   */
  export interface DebugConfig {
    /**
     * Allow interaction with the browser session via the debug URL viewer. When false,
     * the session viewer will be view-only. Default is true.
     */
    interactive?: boolean;

    /**
     * Show the OS-level mouse cursor in the WebRTC stream (headful mode only). When
     * false, the system cursor will not be rendered in the stream. Default is true.
     */
    systemCursor?: boolean;
  }

  /**
   * Device configuration for the session. Specify 'mobile' for mobile device
   * fingerprints and configurations.
   */
  export interface DeviceConfig {
    device?: 'desktop' | 'mobile';
  }

  /**
   * Viewport and browser window dimensions for the session. Mobile sessions require
   * dimensions of at least 508x1074; smaller mobile dimensions are rejected with a
   * 400 response.
   */
  export interface Dimensions {
    /**
     * Height of the session
     */
    height: number;

    /**
     * Width of the session
     */
    width: number;
  }

  export interface UnionMember1 {
    blockHosts?: Array<string>;

    blockImages?: boolean;

    blockMedia?: boolean;

    blockStylesheets?: boolean;

    blockUrlPatterns?: Array<string>;
  }

  /**
   * Session context data to be used in the created session. Sessions will start with
   * an empty context by default.
   */
  export interface SessionContext {
    /**
     * Cookies to initialize in the session
     */
    cookies?: Array<SessionContext.Cookie>;

    /**
     * Domain-specific indexedDB items to initialize in the session
     */
    indexedDB?: { [key: string]: Array<SessionContext.IndexedDB> };

    /**
     * Domain-specific localStorage items to initialize in the session
     */
    localStorage?: { [key: string]: { [key: string]: string } };

    /**
     * Domain-specific sessionStorage items to initialize in the session
     */
    sessionStorage?: { [key: string]: { [key: string]: string } };
  }

  export namespace SessionContext {
    export interface Cookie {
      /**
       * The name of the cookie
       */
      name: string;

      /**
       * The value of the cookie
       */
      value: string;

      /**
       * The domain of the cookie
       */
      domain?: string;

      /**
       * The expiration date of the cookie
       */
      expires?: number;

      /**
       * Whether the cookie is HTTP only
       */
      httpOnly?: boolean;

      /**
       * The partition key of the cookie
       */
      partitionKey?: Cookie.PartitionKey;

      /**
       * The path of the cookie
       */
      path?: string;

      /**
       * The priority of the cookie
       */
      priority?: 'Low' | 'Medium' | 'High';

      /**
       * Whether the cookie is a same party cookie
       */
      sameParty?: boolean;

      /**
       * The same site attribute of the cookie
       */
      sameSite?: 'Strict' | 'Lax' | 'None';

      /**
       * Whether the cookie is secure
       */
      secure?: boolean;

      /**
       * Whether the cookie is a session cookie
       */
      session?: boolean;

      /**
       * The size of the cookie
       */
      size?: number;

      /**
       * The source port of the cookie
       */
      sourcePort?: number;

      /**
       * The source scheme of the cookie
       */
      sourceScheme?: 'Unset' | 'NonSecure' | 'Secure';

      /**
       * The URL of the cookie
       */
      url?: string;
    }

    export namespace Cookie {
      /**
       * The partition key of the cookie
       */
      export interface PartitionKey {
        /**
         * Indicates if the cookie has any ancestors that are cross-site to the
         * topLevelSite.
         */
        hasCrossSiteAncestor: boolean;

        /**
         * The site of the top-level URL the browser was visiting at the start of the
         * request to the endpoint that set the cookie.
         */
        topLevelSite: string;
      }
    }

    export interface IndexedDB {
      id: number;

      data: Array<IndexedDB.Data>;

      name: string;
    }

    export namespace IndexedDB {
      export interface Data {
        id: number;

        name: string;

        records: Array<Data.Record>;
      }

      export namespace Data {
        export interface Record {
          key: unknown;

          value: unknown;

          blobFiles?: Array<Record.BlobFile>;
        }

        export namespace Record {
          export interface BlobFile {
            blobNumber: number;

            mimeType: string;

            size: number;

            filename?: string;

            lastModified?: string;

            path?: string;
          }
        }
      }
    }
  }

  /**
   * Stealth configuration for the session
   */
  export interface StealthConfig {
    /**
     * When true, captchas will be automatically solved when detected. When false, use
     * the solve endpoints to manually initiate solving.
     */
    autoCaptchaSolving?: boolean;

    /**
     * This flag will make the browser act more human-like by moving the mouse in a
     * more natural way.
     */
    humanizeInteractions?: boolean;

    /**
     * This flag will skip the fingerprint generation for the session.
     */
    skipFingerprintInjection?: boolean;
  }

  export interface Geolocation {
    /**
     * Geographic location for the proxy
     */
    geolocation: Geolocation.Geolocation;
  }

  export namespace Geolocation {
    /**
     * Geographic location for the proxy
     */
    export interface Geolocation {
      /**
       * Country code (e.g., 'US', 'GB', 'DE') - ISO 3166-1 alpha-2
       */
      country:
        | 'US'
        | 'CA'
        | 'MX'
        | 'GB'
        | 'DE'
        | 'FR'
        | 'IT'
        | 'ES'
        | 'PL'
        | 'NL'
        | 'SE'
        | 'NO'
        | 'DK'
        | 'FI'
        | 'CH'
        | 'AT'
        | 'BE'
        | 'IE'
        | 'PT'
        | 'GR'
        | 'CZ'
        | 'HU'
        | 'RO'
        | 'BG'
        | 'SK'
        | 'SI'
        | 'HR'
        | 'EE'
        | 'LV'
        | 'LT'
        | 'LU'
        | 'MT'
        | 'CY'
        | 'IS'
        | 'LI'
        | 'MC'
        | 'SM'
        | 'VA'
        | 'JP'
        | 'KR'
        | 'CN'
        | 'HK'
        | 'TW'
        | 'SG'
        | 'AU'
        | 'NZ'
        | 'IN'
        | 'TH'
        | 'MY'
        | 'PH'
        | 'ID'
        | 'VN'
        | 'AF'
        | 'BD'
        | 'BN'
        | 'KH'
        | 'LA'
        | 'LK'
        | 'MM'
        | 'NP'
        | 'PK'
        | 'FJ'
        | 'PG'
        | 'AE'
        | 'SA'
        | 'IL'
        | 'TR'
        | 'IR'
        | 'IQ'
        | 'JO'
        | 'KW'
        | 'LB'
        | 'OM'
        | 'QA'
        | 'BH'
        | 'YE'
        | 'SY'
        | 'ZA'
        | 'EG'
        | 'MA'
        | 'NG'
        | 'KE'
        | 'DZ'
        | 'AO'
        | 'BW'
        | 'ET'
        | 'GH'
        | 'CI'
        | 'LY'
        | 'MZ'
        | 'RW'
        | 'SN'
        | 'TN'
        | 'UG'
        | 'ZM'
        | 'ZW'
        | 'TZ'
        | 'MU'
        | 'SC'
        | 'BR'
        | 'AR'
        | 'CL'
        | 'CO'
        | 'PE'
        | 'VE'
        | 'EC'
        | 'UY'
        | 'PY'
        | 'BO'
        | 'CR'
        | 'CU'
        | 'DO'
        | 'GT'
        | 'HN'
        | 'JM'
        | 'NI'
        | 'PA'
        | 'SV'
        | 'TT'
        | 'BB'
        | 'BZ'
        | 'GY'
        | 'SR'
        | 'RU'
        | 'UA'
        | 'BY'
        | 'KZ'
        | 'UZ'
        | 'AZ'
        | 'GE'
        | 'AM'
        | 'MD'
        | 'MK'
        | 'AL'
        | 'BA'
        | 'RS'
        | 'ME'
        | 'XK'
        | 'MN'
        | 'KG'
        | 'TJ'
        | 'TM';

      /**
       * City name (e.g., 'NEW_YORK', 'LOS_ANGELES')
       */
      city?:
        | 'A_CORUNA'
        | 'ABIDJAN'
        | 'ABU_DHABI'
        | 'ABUJA'
        | 'ACAPULCO_DE JUAREZ'
        | 'ACCRA'
        | 'ADANA'
        | 'ADAPAZARI'
        | 'ADDIS_ABABA'
        | 'ADELAIDE'
        | 'AFYONKARAHISAR'
        | 'AGADIR'
        | 'AGUAS_LINDAS DE GOIAS'
        | 'AGUASCALIENTES'
        | 'AHMEDABAD'
        | 'AIZAWL'
        | 'AJMAN'
        | 'AKRON'
        | 'AKSARAY'
        | 'AL_AIN CITY'
        | 'AL_MANSURAH'
        | 'AL_QATIF'
        | 'ALAJUELA'
        | 'ALBANY'
        | 'ALBUQUERQUE'
        | 'ALEXANDRIA'
        | 'ALGIERS'
        | 'ALICANTE'
        | 'ALMADA'
        | 'ALMATY'
        | 'ALMERE_STAD'
        | 'ALVORADA'
        | 'AMADORA'
        | 'AMASYA'
        | 'AMBATO'
        | 'AMERICANA'
        | 'AMMAN'
        | 'AMSTERDAM'
        | 'ANANINDEUA'
        | 'ANAPOLIS'
        | 'ANGELES_CITY'
        | 'ANGERS'
        | 'ANGRA_DOS REIS'
        | 'ANKARA'
        | 'ANTAKYA'
        | 'ANTALYA'
        | 'ANTANANARIVO'
        | 'ANTIPOLO_CITY'
        | 'ANTOFAGASTA'
        | 'ANTWERP'
        | 'APARECIDA_DE GOIANIA'
        | 'APODACA'
        | 'ARACAJU'
        | 'ARACATUBA'
        | 'ARAD'
        | 'ARAGUAINA'
        | 'ARAPIRACA'
        | 'ARARAQUARA'
        | 'AREQUIPA'
        | 'ARICA'
        | 'ARLINGTON'
        | 'ARYANAH'
        | 'ASTANA'
        | 'ASUNCION'
        | 'ASYUT'
        | 'ATAKUM'
        | 'ATHENS'
        | 'ATIBAIA'
        | 'ATLANTA'
        | 'AUBURN'
        | 'AUCKLAND'
        | 'AURORA'
        | 'AUSTIN'
        | 'AVELLANEDA'
        | 'AYDIN'
        | 'AZCAPOTZALCO'
        | 'BACOLOD_CITY'
        | 'BACOOR'
        | 'BAGHDAD'
        | 'BAGUIO_CITY'
        | 'BAHIA_BLANCA'
        | 'BAKERSFIELD'
        | 'BAKU'
        | 'BALIKESIR'
        | 'BALIKPAPAN'
        | 'BALNEARIO_CAMBORIU'
        | 'BALTIMORE'
        | 'BANDAR_LAMPUNG'
        | 'BANDAR_SERI BEGAWAN'
        | 'BANDUNG'
        | 'BANGKOK'
        | 'BANJA_LUKA'
        | 'BANJARMASIN'
        | 'BARCELONA'
        | 'BARI'
        | 'BARQUISIMETO'
        | 'BARRA_MANSA'
        | 'BARRANQUILLA'
        | 'BARUERI'
        | 'BATAM'
        | 'BATANGAS'
        | 'BATMAN'
        | 'BATNA_CITY'
        | 'BATON_ROUGE'
        | 'BATUMI'
        | 'BAURU'
        | 'BEIRUT'
        | 'BEJAIA'
        | 'BEKASI'
        | 'BELEM'
        | 'BELFAST'
        | 'BELFORD_ROXO'
        | 'BELGRADE'
        | 'BELO_HORIZONTE'
        | 'BENGALURU'
        | 'BENI_MELLAL'
        | 'BERAZATEGUI'
        | 'BERN'
        | 'BETIM'
        | 'BHARATPUR'
        | 'BHOPAL'
        | 'BHUBANESWAR'
        | 'BIALYSTOK'
        | 'BIEN_HOA'
        | 'BILBAO'
        | 'BILECIK'
        | 'BIRATNAGAR'
        | 'BIRMINGHAM'
        | 'BISHKEK'
        | 'BIZERTE'
        | 'BLIDA'
        | 'BLOEMFONTEIN'
        | 'BLOOMINGTON'
        | 'BLUMENAU'
        | 'BOA_VISTA'
        | 'BOCHUM'
        | 'BOGOR'
        | 'BOGOTA'
        | 'BOISE'
        | 'BOKSBURG'
        | 'BOLOGNA'
        | 'BOLU'
        | 'BORDEAUX'
        | 'BOSTON'
        | 'BOTUCATU'
        | 'BRADFORD'
        | 'BRAGA'
        | 'BRAGANCA_PAULISTA'
        | 'BRAMPTON'
        | 'BRASILIA'
        | 'BRASOV'
        | 'BRATISLAVA'
        | 'BREMEN'
        | 'BRESCIA'
        | 'BREST'
        | 'BRIDGETOWN'
        | 'BRISBANE'
        | 'BRISTOL'
        | 'BRNO'
        | 'BROOKLYN'
        | 'BRUSSELS'
        | 'BUCARAMANGA'
        | 'BUCHAREST'
        | 'BUDAPEST'
        | 'BUENOS_AIRES'
        | 'BUFFALO'
        | 'BUK_GU'
        | 'BUKHARA'
        | 'BURGAS'
        | 'BURNABY'
        | 'BURSA'
        | 'BUTUAN'
        | 'BYDGOSZCZ'
        | 'CABANATUAN_CITY'
        | 'CABO_FRIO'
        | 'CABUYAO'
        | 'CACHOEIRO_DE ITAPEMIRIM'
        | 'CAGAYAN_DE ORO'
        | 'CAGLIARI'
        | 'CAIRO'
        | 'CALAMBA'
        | 'CALGARY'
        | 'CALOOCAN_CITY'
        | 'CAMACARI'
        | 'CAMARAGIBE'
        | 'CAMPECHE'
        | 'CAMPINA_GRANDE'
        | 'CAMPINAS'
        | 'CAMPO_GRANDE'
        | 'CAMPO_LARGO'
        | 'CAMPOS_DOS GOYTACAZES'
        | 'CAN_THO'
        | 'CANOAS'
        | 'CANTON'
        | 'CAPE_TOWN'
        | 'CARACAS'
        | 'CARAGUATATUBA'
        | 'CARAPICUIBA'
        | 'CARDIFF'
        | 'CARIACICA'
        | 'CARMONA'
        | 'CARTAGENA'
        | 'CARUARU'
        | 'CASABLANCA'
        | 'CASCAVEL'
        | 'CASEROS'
        | 'CASTANHAL'
        | 'CASTRIES'
        | 'CATALAO'
        | 'CATAMARCA'
        | 'CATANDUVA'
        | 'CATANIA'
        | 'CAUCAIA'
        | 'CAXIAS_DO SUL'
        | 'CEBU_CITY'
        | 'CENTRAL'
        | 'CENTRO'
        | 'CENTURION'
        | 'CHAGUANAS'
        | 'CHANDIGARH'
        | 'CHANDLER'
        | 'CHANG_HUA'
        | 'CHAPECO'
        | 'CHARLESTON'
        | 'CHARLOTTE'
        | 'CHELYABINSK'
        | 'CHENNAI'
        | 'CHERKASY'
        | 'CHERNIVTSI'
        | 'CHIA'
        | 'CHIANG_MAI'
        | 'CHICLAYO'
        | 'CHIHUAHUA_CITY'
        | 'CHIMBOTE'
        | 'CHISINAU'
        | 'CHITTAGONG'
        | 'CHRISTCHURCH'
        | 'CINCINNATI'
        | 'CIREBON'
        | 'CITY_OF MUNTINLUPA'
        | 'CIUDAD_DEL ESTE'
        | 'CIUDAD_GUAYANA'
        | 'CIUDAD_JUAREZ'
        | 'CIUDAD_NEZAHUALCOYOTL'
        | 'CIUDAD_OBREGON'
        | 'CLEVELAND'
        | 'CLUJ_NAPOCA'
        | 'COCHABAMBA'
        | 'COIMBATORE'
        | 'COIMBRA'
        | 'COLOGNE'
        | 'COLOMBO'
        | 'COLORADO_SPRINGS'
        | 'COLUMBIA'
        | 'COLUMBUS'
        | 'COMODORO_RIVADAVIA'
        | 'CONCEPCION'
        | 'CONCORD'
        | 'CONSTANTA'
        | 'CONSTANTINE'
        | 'CONTAGEM'
        | 'COPENHAGEN'
        | 'CORDOBA'
        | 'CORRIENTES'
        | 'CORUM'
        | 'COTIA'
        | 'COVENTRY'
        | 'CRAIOVA'
        | 'CRICIUMA'
        | 'CROYDON'
        | 'CUAUTITLAN_IZCALLI'
        | 'CUCUTA'
        | 'CUENCA'
        | 'CUERNAVACA'
        | 'CUIABA'
        | 'CULIACAN'
        | 'CURITIBA'
        | 'CUSCO'
        | 'DA_NANG'
        | 'DAGUPAN'
        | 'DAKAR'
        | 'DALLAS'
        | 'DAMIETTA'
        | 'DAMMAM'
        | 'DAR_ES SALAAM'
        | 'DASMARINAS'
        | 'DAVAO_CITY'
        | 'DAYTON'
        | 'DEBRECEN'
        | 'DECATUR'
        | 'DEHRADUN'
        | 'DELHI'
        | 'DENIZLI'
        | 'DENPASAR'
        | 'DENVER'
        | 'DEPOK'
        | 'DERBY'
        | 'DETROIT'
        | 'DHAKA'
        | 'DIADEMA'
        | 'DIVINOPOLIS'
        | 'DIYARBAKIR'
        | 'DJELFA'
        | 'DNIPRO'
        | 'DOHA'
        | 'DORTMUND'
        | 'DOURADOS'
        | 'DRESDEN'
        | 'DUBAI'
        | 'DUBLIN'
        | 'DUEZCE'
        | 'DUISBURG'
        | 'DUQUE_DE CAXIAS'
        | 'DURANGO'
        | 'DURBAN'
        | 'DUSSELDORF'
        | 'ECATEPEC'
        | 'EDINBURGH'
        | 'EDIRNE'
        | 'EDMONTON'
        | 'EL_JADIDA'
        | 'EL_PASO'
        | 'ELAZIG'
        | 'EMBU'
        | 'ENSENADA'
        | 'ERBIL'
        | 'ERZURUM'
        | 'ESKISEHIR'
        | 'ESPOO'
        | 'ESSEN'
        | 'FAISALABAD'
        | 'FAYETTEVILLE'
        | 'FAZENDA_RIO GRANDE'
        | 'FEIRA_DE SANTANA'
        | 'FES'
        | 'FLORENCE'
        | 'FLORENCIO_VARELA'
        | 'FLORIANOPOLIS'
        | 'FONTANA'
        | 'FORMOSA'
        | 'FORT_LAUDERDALE'
        | 'FORT_WAYNE'
        | 'FORT_WORTH'
        | 'FORTALEZA'
        | 'FOZ_DO IGUACU'
        | 'FRANCA'
        | 'FRANCISCO_MORATO'
        | 'FRANCO_DA ROCHA'
        | 'FRANKFURT_AM MAIN'
        | 'FREDERICKSBURG'
        | 'FRESNO'
        | 'FUNCHAL'
        | 'GABORONE'
        | 'GAINESVILLE'
        | 'GALATI'
        | 'GANGNAM_GU'
        | 'GARANHUNS'
        | 'GATINEAU'
        | 'GAZIANTEP'
        | 'GDANSK'
        | 'GDYNIA'
        | 'GENERAL_TRIAS'
        | 'GENEVA'
        | 'GENOA'
        | 'GEORGE_TOWN'
        | 'GEORGETOWN'
        | 'GHAZIABAD'
        | 'GHENT'
        | 'GIJON'
        | 'GIRESUN'
        | 'GIZA'
        | 'GLASGOW'
        | 'GLENDALE'
        | 'GLIWICE'
        | 'GOIANIA'
        | 'GOMEL'
        | 'GOTHENBURG'
        | 'GOVERNADOR_VALADARES'
        | 'GOYANG_SI'
        | 'GRANADA'
        | 'GRAND_RAPIDS'
        | 'GRAVATAI'
        | 'GRAZ'
        | 'GREENSBORO'
        | 'GREENVILLE'
        | 'GUADALAJARA'
        | 'GUADALUPE'
        | 'GUANGZHOU'
        | 'GUARAPUAVA'
        | 'GUARATINGUETA'
        | 'GUARUJA'
        | 'GUARULHOS'
        | 'GUATEMALA_CITY'
        | 'GUAYAQUIL'
        | 'GUJRANWALA'
        | 'GURUGRAM'
        | 'GUSTAVO_ADOLFO MADERO'
        | 'GUWAHATI'
        | 'GWANAK_GU'
        | 'HACKNEY'
        | 'HAIFA'
        | 'HAIPHONG'
        | 'HAMBURG'
        | 'HAMILTON'
        | 'HANOI'
        | 'HANOVER'
        | 'HARARE'
        | 'HAVANA'
        | 'HELSINKI'
        | 'HENDERSON'
        | 'HEREDIA'
        | 'HERMOSILLO'
        | 'HIALEAH'
        | 'HO_CHI MINH CITY'
        | 'HOLLYWOOD'
        | 'HOLON'
        | 'HONOLULU'
        | 'HORTOLANDIA'
        | 'HRODNA'
        | 'HSINCHU'
        | 'HUANCAYO'
        | 'HUANUCO'
        | 'HULL'
        | 'HURLINGHAM'
        | 'HYDERABAD'
        | 'IASI'
        | 'IBAGUE'
        | 'ICA'
        | 'ILAM'
        | 'ILFORD'
        | 'ILIGAN'
        | 'ILOILO_CITY'
        | 'IMPERATRIZ'
        | 'IMUS'
        | 'INCHEON'
        | 'INDAIATUBA'
        | 'INDIANAPOLIS'
        | 'INDORE'
        | 'IPATINGA'
        | 'IPOH'
        | 'IQUIQUE'
        | 'IRVINE'
        | 'ISIDRO_CASANOVA'
        | 'ISLAMABAD'
        | 'ISLINGTON'
        | 'ISMAILIA'
        | 'ISPARTA'
        | 'ISTANBUL'
        | 'ITABORAI'
        | 'ITABUNA'
        | 'ITAJAI'
        | 'ITANHAEM'
        | 'ITAPEVI'
        | 'ITAQUAQUECETUBA'
        | 'ITUZAINGO'
        | 'IZMIR'
        | 'IZTAPALAPA'
        | 'JABOATAO_DOS GUARARAPES'
        | 'JACAREI'
        | 'JACKSON'
        | 'JACKSONVILLE'
        | 'JAIPUR'
        | 'JAKARTA'
        | 'JARAGUA_DO SUL'
        | 'JAU'
        | 'JEDDAH'
        | 'JEMBER'
        | 'JERUSALEM'
        | 'JOAO_MONLEVADE'
        | 'JOAO_PESSOA'
        | 'JODHPUR'
        | 'JOHANNESBURG'
        | 'JOHOR_BAHRU'
        | 'JOINVILLE'
        | 'JOSE_C PAZ'
        | 'JOSE_MARIA EZEIZA'
        | 'JUAREZ'
        | 'JUAZEIRO_DO NORTE'
        | 'JUIZ_DE FORA'
        | 'JUNDIAI'
        | 'KAHRAMANMARAS'
        | 'KAMPALA'
        | 'KANPUR'
        | 'KANSAS_CITY'
        | 'KAOHSIUNG_CITY'
        | 'KARABUK'
        | 'KARACHI'
        | 'KARLSRUHE'
        | 'KARNAL'
        | 'KASKI'
        | 'KASTAMONU'
        | 'KATHMANDU'
        | 'KATOWICE'
        | 'KATSINA'
        | 'KATY'
        | 'KAUNAS'
        | 'KAYSERI'
        | 'KAZAN'
        | 'KECSKEMET'
        | 'KEDIRI'
        | 'KENITRA'
        | 'KHARKIV'
        | 'KHMELNYTSKYI'
        | 'KHON_KAEN'
        | 'KIELCE'
        | 'KIGALI'
        | 'KINGSTON'
        | 'KIRKLARELI'
        | 'KISSIMMEE'
        | 'KITCHENER'
        | 'KLAIPEDA'
        | 'KNOXVILLE'
        | 'KOCHI'
        | 'KOLKATA'
        | 'KOLLAM'
        | 'KONYA'
        | 'KOSEKOY'
        | 'KOSICE'
        | 'KOTA_KINABALU'
        | 'KOZHIKODE'
        | 'KRAKOW'
        | 'KRASNODAR'
        | 'KRYVYI_RIH'
        | 'KUALA_LUMPUR'
        | 'KUCHING'
        | 'KUTAHYA'
        | 'KUTAISI'
        | 'KUWAIT_CITY'
        | 'KYIV'
        | 'LA_PAZ'
        | 'LA_PLATA'
        | 'LA_RIOJA'
        | 'LA_SERENA'
        | 'LAFAYETTE'
        | 'LAFERRERE'
        | 'LAGES'
        | 'LAGOS'
        | 'LAHORE'
        | 'LAHUG'
        | 'LAKE_WORTH'
        | 'LAKELAND'
        | 'LANCASTER'
        | 'LANUS'
        | 'LAS_PALMAS DE GRAN CANARIA'
        | 'LAS_PINAS'
        | 'LAS_VEGAS'
        | 'LAUSANNE'
        | 'LAVAL'
        | 'LAWRENCEVILLE'
        | 'LE_MANS'
        | 'LEEDS'
        | 'LEICESTER'
        | 'LEIPZIG'
        | 'LEON'
        | 'LEXINGTON'
        | 'LIBREVILLE'
        | 'LIEGE'
        | 'LILLE'
        | 'LIMA'
        | 'LIMASSOL'
        | 'LIMEIRA'
        | 'LINCOLN'
        | 'LINHARES'
        | 'LIPA_CITY'
        | 'LISBON'
        | 'LIVERPOOL'
        | 'LJUBLJANA'
        | 'LODZ'
        | 'LOJA'
        | 'LOMAS_DE ZAMORA'
        | 'LOME'
        | 'LONDRINA'
        | 'LONG_BEACH'
        | 'LONGUEUIL'
        | 'LOUISVILLE'
        | 'LUANDA'
        | 'LUBLIN'
        | 'LUCENA_CITY'
        | 'LUCKNOW'
        | 'LUDHIANA'
        | 'LUSAKA'
        | 'LUXEMBOURG'
        | 'LUZIANIA'
        | 'LVIV'
        | 'LYON'
        | 'MABALACAT'
        | 'MACAE'
        | 'MACAO'
        | 'MACAPA'
        | 'MACEIO'
        | 'MACHALA'
        | 'MADISON'
        | 'MADRID'
        | 'MAGE'
        | 'MAGELANG'
        | 'MAGNESIA_AD SIPYLUM'
        | 'MAKASSAR'
        | 'MAKATI_CITY'
        | 'MALABON'
        | 'MALAGA'
        | 'MALANG'
        | 'MALAPPURAM'
        | 'MALDONADO'
        | 'MALE'
        | 'MALMO'
        | 'MANADO'
        | 'MANAGUA'
        | 'MANAMA'
        | 'MANAUS'
        | 'MANCHESTER'
        | 'MANDALUYONG_CITY'
        | 'MANILA'
        | 'MANIZALES'
        | 'MANNHEIM'
        | 'MAPUTO'
        | 'MAR_DEL PLATA'
        | 'MARABA'
        | 'MARACAIBO'
        | 'MARACANAU'
        | 'MARACAY'
        | 'MARDIN'
        | 'MARIBOR'
        | 'MARICA'
        | 'MARIETTA'
        | 'MARIKINA_CITY'
        | 'MARILIA'
        | 'MARINGA'
        | 'MARRAKESH'
        | 'MARSEILLE'
        | 'MAUA'
        | 'MAZATLAN'
        | 'MEDAN'
        | 'MEDELLIN'
        | 'MEDINA'
        | 'MEERUT'
        | 'MEKNES'
        | 'MELBOURNE'
        | 'MEMPHIS'
        | 'MENDOZA'
        | 'MERIDA'
        | 'MERKEZ'
        | 'MERLO'
        | 'MERSIN'
        | 'MESA'
        | 'MEXICALI'
        | 'MEXICO_CITY'
        | 'MILAN'
        | 'MILTON_KEYNES'
        | 'MILWAUKEE'
        | 'MINNEAPOLIS'
        | 'MINSK'
        | 'MISKOLC'
        | 'MISSISSAUGA'
        | 'MOGI_DAS CRUZES'
        | 'MOHALI'
        | 'MONROE'
        | 'MONTE_GRANDE'
        | 'MONTEGO_BAY'
        | 'MONTERREY'
        | 'MONTES_CLAROS'
        | 'MONTEVIDEO'
        | 'MONTGOMERY'
        | 'MONTPELLIER'
        | 'MONTREAL'
        | 'MORELIA'
        | 'MORENO'
        | 'MORON'
        | 'MOSSORO'
        | 'MUGLA'
        | 'MULTAN'
        | 'MUMBAI'
        | 'MUNICH'
        | 'MURCIA'
        | 'MUSCAT'
        | 'MUZAFFARGARH'
        | 'MYKOLAYIV'
        | 'NAALDWIJK'
        | 'NAGA'
        | 'NAGPUR'
        | 'NAIROBI'
        | 'NANTES'
        | 'NAPLES'
        | 'NASHVILLE'
        | 'NASSAU'
        | 'NASUGBU'
        | 'NATAL'
        | 'NAUCALPAN'
        | 'NAVI_MUMBAI'
        | 'NEIVA'
        | 'NEUQUEN'
        | 'NEVSEHIR'
        | 'NEW_DELHI'
        | 'NEW_ORLEANS'
        | 'NEW_TAIPEI'
        | 'NEWARK'
        | 'NEWCASTLE_UPON TYNE'
        | 'NHA_TRANG'
        | 'NICE'
        | 'NICOSIA'
        | 'NILOPOLIS'
        | 'NIS'
        | 'NITEROI'
        | 'NITRA'
        | 'NIZHNIY_NOVGOROD'
        | 'NOGALES'
        | 'NOIDA'
        | 'NORTHAMPTON'
        | 'NORWICH'
        | 'NOTTINGHAM'
        | 'NOVA_FRIBURGO'
        | 'NOVA_IGUACU'
        | 'NOVI_SAD'
        | 'NOVO_HAMBURGO'
        | 'NOVOSIBIRSK'
        | 'NUREMBERG'
        | 'OAKLAND'
        | 'OAXACA_CITY'
        | 'ODESA'
        | 'OKLAHOMA_CITY'
        | 'OLINDA'
        | 'OLOMOUC'
        | 'OLONGAPO_CITY'
        | 'OLSZTYN'
        | 'OMAHA'
        | 'ORADEA'
        | 'ORAN'
        | 'ORDU'
        | 'ORLANDO'
        | 'OSASCO'
        | 'OSLO'
        | 'OSMANIYE'
        | 'OSTRAVA'
        | 'OTTAWA'
        | 'OUJDA'
        | 'OURINHOS'
        | 'PACHUCA'
        | 'PADOVA'
        | 'PALAKKAD'
        | 'PALEMBANG'
        | 'PALERMO'
        | 'PALHOCA'
        | 'PALMA'
        | 'PALMAS'
        | 'PANAMA_CITY'
        | 'PARAMARIBO'
        | 'PARANA'
        | 'PARANAGUA'
        | 'PARANAQUE_CITY'
        | 'PARAUAPEBAS'
        | 'PARIS'
        | 'PARNAIBA'
        | 'PARNAMIRIM'
        | 'PASSO_FUNDO'
        | 'PASTO'
        | 'PATAN'
        | 'PATNA'
        | 'PATOS_DE MINAS'
        | 'PAULISTA'
        | 'PECS'
        | 'PEKANBARU'
        | 'PELOTAS'
        | 'PEORIA'
        | 'PEREIRA'
        | 'PERM'
        | 'PERTH'
        | 'PESCARA'
        | 'PESHAWAR'
        | 'PETAH_TIKVA'
        | 'PETALING_JAYA'
        | 'PETROLINA'
        | 'PETROPOLIS'
        | 'PHILADELPHIA'
        | 'PHNOM_PENH'
        | 'PHOENIX'
        | 'PILAR'
        | 'PINDAMONHANGABA'
        | 'PIRACICABA'
        | 'PITESTI'
        | 'PITTSBURGH'
        | 'PIURA'
        | 'PLANO'
        | 'PLOIESTI'
        | 'PLOVDIV'
        | 'PLYMOUTH'
        | 'POCOS_DE CALDAS'
        | 'PODGORICA'
        | 'POLTAVA'
        | 'PONTA_GROSSA'
        | 'PONTIANAK'
        | 'POPAYAN'
        | 'PORT_AU PRINCE'
        | 'PORT_ELIZABETH'
        | 'PORT_HARCOURT'
        | 'PORT_LOUIS'
        | 'PORT_MONTT'
        | 'PORT_OF SPAIN'
        | 'PORT_SAID'
        | 'PORTLAND'
        | 'PORTO'
        | 'PORTO_ALEGRE'
        | 'PORTO_SEGURO'
        | 'PORTO_VELHO'
        | 'PORTOVIEJO'
        | 'POSADAS'
        | 'POUSO_ALEGRE'
        | 'POZNAN'
        | 'PRAGUE'
        | 'PRAIA_GRANDE'
        | 'PRESIDENTE_PRUDENTE'
        | 'PRETORIA'
        | 'PRISTINA'
        | 'PROVIDENCE'
        | 'PUCALLPA'
        | 'PUCHONG_BATU DUA BELAS'
        | 'PUEBLA_CITY'
        | 'PUNE'
        | 'QUEBEC'
        | 'QUEENS'
        | 'QUEIMADOS'
        | 'QUERETARO_CITY'
        | 'QUEZON_CITY'
        | 'QUILMES'
        | 'QUITO'
        | 'RABAT'
        | 'RAIPUR'
        | 'RAJKOT'
        | 'RAJSHAHI'
        | 'RALEIGH'
        | 'RAMAT_GAN'
        | 'RANCAGUA'
        | 'RANCHI'
        | 'RAS_AL KHAIMAH'
        | 'RAWALPINDI'
        | 'READING'
        | 'RECIFE'
        | 'REGINA'
        | 'RENNES'
        | 'RENO'
        | 'RESISTENCIA'
        | 'REYKJAVIK'
        | 'REYNOSA'
        | 'RIBEIRAO_DAS NEVES'
        | 'RIBEIRAO_PRETO'
        | 'RICHMOND'
        | 'RIGA'
        | 'RIO_BRANCO'
        | 'RIO_CLARO'
        | 'RIO_CUARTO'
        | 'RIO_DE JANEIRO'
        | 'RIO_DO SUL'
        | 'RIO_GALLEGOS'
        | 'RIO_GRANDE'
        | 'RISHON_LETSIYYON'
        | 'RIVERSIDE'
        | 'RIYADH'
        | 'RIZE'
        | 'ROCHESTER'
        | 'ROME'
        | 'RONDONOPOLIS'
        | 'ROSARIO'
        | 'ROSEAU'
        | 'ROSTOV_ON DON'
        | 'ROTTERDAM'
        | 'ROUEN'
        | 'ROUSSE'
        | 'RZESZOW'
        | 'SACRAMENTO'
        | 'SAGAR'
        | 'SAINT_PAUL'
        | 'SALE'
        | 'SALT_LAKE CITY'
        | 'SALTA'
        | 'SALTILLO'
        | 'SALVADOR'
        | 'SAMARA'
        | 'SAMARINDA'
        | 'SAMARKAND'
        | 'SAMSUN'
        | 'SAN_ANTONIO'
        | 'SAN_DIEGO'
        | 'SAN_FERNANDO'
        | 'SAN_FRANCISCO'
        | 'SAN_JOSE'
        | 'SAN_JOSE DEL MONTE'
        | 'SAN_JUAN'
        | 'SAN_JUSTO'
        | 'SAN_LUIS'
        | 'SAN_LUIS POTOSI CITY'
        | 'SAN_MIGUEL'
        | 'SAN_MIGUEL DE TUCUMAN'
        | 'SAN_PABLO CITY'
        | 'SAN_PEDRO'
        | 'SAN_PEDRO SULA'
        | 'SAN_SALVADOR'
        | 'SAN_SALVADOR DE JUJUY'
        | 'SANAA'
        | 'SANLIURFA'
        | 'SANTA_CRUZ'
        | 'SANTA_CRUZ DE TENERIFE'
        | 'SANTA_CRUZ DO SUL'
        | 'SANTA_FE'
        | 'SANTA_LUZIA'
        | 'SANTA_MARIA'
        | 'SANTA_MARTA'
        | 'SANTA_ROSA'
        | 'SANTAREM'
        | 'SANTIAGO'
        | 'SANTIAGO_DE CALI'
        | 'SANTIAGO_DE LOS CABALLEROS'
        | 'SANTO_ANDRE'
        | 'SANTO_DOMINGO'
        | 'SANTO_DOMINGO ESTE'
        | 'SANTOS'
        | 'SAO_BERNARDO DO CAMPO'
        | 'SAO_CARLOS'
        | 'SAO_GONCALO'
        | 'SAO_JOAO DE MERITI'
        | 'SAO_JOSE'
        | 'SAO_JOSE DO RIO PRETO'
        | 'SAO_JOSE DOS CAMPOS'
        | 'SAO_JOSE DOS PINHAIS'
        | 'SAO_LEOPOLDO'
        | 'SAO_LUIS'
        | 'SAO_PAULO'
        | 'SAO_VICENTE'
        | 'SARAJEVO'
        | 'SASKATOON'
        | 'SCARBOROUGH'
        | 'SEATTLE'
        | 'SEMARANG'
        | 'SEO_GU'
        | 'SEONGNAM_SI'
        | 'SEOUL'
        | 'SERRA'
        | 'SETE_LAGOAS'
        | 'SETIF'
        | 'SETUBAL'
        | 'SEVILLE'
        | 'SFAX'
        | 'SHAH_ALAM'
        | 'SHANGHAI'
        | 'SHARJAH'
        | 'SHEFFIELD'
        | 'SHENZHEN'
        | 'SHIMLA'
        | 'SIAULIAI'
        | 'SIBIU'
        | 'SIDOARJO'
        | 'SIKAR'
        | 'SILVER_SPRING'
        | 'SINOP'
        | 'SIVAS'
        | 'SKIKDA'
        | 'SKOPJE'
        | 'SLOUGH'
        | 'SOBRAL'
        | 'SOFIA'
        | 'SOROCABA'
        | 'SOUSSE'
        | 'SOUTH_TANGERANG'
        | 'SOUTHAMPTON'
        | 'SOUTHWARK'
        | 'SPLIT'
        | 'SPOKANE'
        | 'SPRING'
        | 'SPRINGFIELD'
        | 'ST_LOUIS'
        | 'ST_PETERSBURG'
        | 'STARA_ZAGORA'
        | 'STATEN_ISLAND'
        | 'STOCKHOLM'
        | 'STOCKTON'
        | 'STOKE_ON TRENT'
        | 'STRASBOURG'
        | 'STUTTGART'
        | 'SUMARE'
        | 'SURABAYA'
        | 'SURAKARTA'
        | 'SURAT'
        | 'SURREY'
        | 'SUWON'
        | 'SUZANO'
        | 'SYDNEY'
        | 'SZCZECIN'
        | 'SZEGED'
        | 'SZEKESFEHERVAR'
        | 'TABOAO_DA SERRA'
        | 'TACNA'
        | 'TACOMA'
        | 'TAGUIG'
        | 'TAICHUNG'
        | 'TAINAN_CITY'
        | 'TAIPEI'
        | 'TALAVERA'
        | 'TALCA'
        | 'TALLAHASSEE'
        | 'TALLINN'
        | 'TAMPA'
        | 'TAMPERE'
        | 'TAMPICO'
        | 'TANGERANG'
        | 'TANGIER'
        | 'TANTA'
        | 'TANZA'
        | 'TAOYUAN_DISTRICT'
        | 'TAPPAHANNOCK'
        | 'TARLAC_CITY'
        | 'TASHKENT'
        | 'TASIKMALAYA'
        | 'TATUI'
        | 'TAUBATE'
        | 'TBILISI'
        | 'TEGUCIGALPA'
        | 'TEHRAN'
        | 'TEIXEIRA_DE FREITAS'
        | 'TEKIRDAG'
        | 'TEL_AVIV'
        | 'TEMUCO'
        | 'TEPIC'
        | 'TERESINA'
        | 'TERNOPIL'
        | 'TERRASSA'
        | 'TETOUAN'
        | 'THANE'
        | 'THE_BRONX'
        | 'THE_HAGUE'
        | 'THESSALONIKI'
        | 'THIRUVANANTHAPURAM'
        | 'THRISSUR'
        | 'TIJUANA'
        | 'TIMISOARA'
        | 'TIRANA'
        | 'TLALNEPANTLA'
        | 'TLAXCALA_CITY'
        | 'TLEMCEN'
        | 'TOKAT_PROVINCE'
        | 'TOKYO'
        | 'TOLUCA'
        | 'TORONTO'
        | 'TORREON'
        | 'TOULOUSE'
        | 'TRABZON'
        | 'TRUJILLO'
        | 'TUBARAO'
        | 'TUCSON'
        | 'TUGUEGARAO_CITY'
        | 'TULSA'
        | 'TUNIS'
        | 'TUNJA'
        | 'TURIN'
        | 'TUXTLA_GUTIERREZ'
        | 'TUZLA'
        | 'UBERABA'
        | 'UBERLANDIA'
        | 'UFA'
        | 'ULAN_BATOR'
        | 'UMEDA'
        | 'URDANETA'
        | 'USAK'
        | 'VADODARA'
        | 'VALENCIA'
        | 'VALINHOS'
        | 'VALLADOLID'
        | 'VALLEDUPAR'
        | 'VALPARAISO'
        | 'VALPARAISO_DE GOIAS'
        | 'VAN'
        | 'VANCOUVER'
        | 'VARANASI'
        | 'VARGINHA'
        | 'VARNA'
        | 'VARZEA_PAULISTA'
        | 'VENUSTIANO_CARRANZA'
        | 'VERACRUZ'
        | 'VERONA'
        | 'VIAMAO'
        | 'VICTORIA'
        | 'VIENNA'
        | 'VIENTIANE'
        | 'VIGO'
        | 'VIJAYAWADA'
        | 'VILA_NOVA DE GAIA'
        | 'VILA_VELHA'
        | 'VILLA_BALLESTER'
        | 'VILLAVICENCIO'
        | 'VILNIUS'
        | 'VINA_DEL MAR'
        | 'VINNYTSIA'
        | 'VIRGINIA_BEACH'
        | 'VISAKHAPATNAM'
        | 'VITORIA'
        | 'VITORIA_DA CONQUISTA'
        | 'VITORIA_DE SANTO ANTAO'
        | 'VOLTA_REDONDA'
        | 'VORONEZH'
        | 'WARSAW'
        | 'WASHINGTON'
        | 'WELLINGTON'
        | 'WEST_PALM BEACH'
        | 'WICHITA'
        | 'WILLEMSTAD'
        | 'WILMINGTON'
        | 'WINDHOEK'
        | 'WINDSOR'
        | 'WINNIPEG'
        | 'WOLVERHAMPTON'
        | 'WOODBRIDGE'
        | 'WROCLAW'
        | 'WUPPERTAL'
        | 'XALAPA'
        | 'YALOVA'
        | 'YANGON'
        | 'YEKATERINBURG'
        | 'YEREVAN'
        | 'YOGYAKARTA'
        | 'YOKOHAMA'
        | 'YONGIN_SI'
        | 'ZABRZE'
        | 'ZAGAZIG'
        | 'ZAGREB'
        | 'ZAMBOANGA_CITY'
        | 'ZAPOPAN'
        | 'ZAPORIZHZHYA'
        | 'ZARAGOZA'
        | 'ZHONGLI_DISTRICT'
        | 'ZIELONA_GORA'
        | 'ZONGULDAK'
        | 'ZURICH';

      /**
       * State code (e.g., 'NY', 'CA') - US states only
       */
      state?:
        | 'AL'
        | 'AK'
        | 'AZ'
        | 'AR'
        | 'CA'
        | 'CO'
        | 'CT'
        | 'DE'
        | 'FL'
        | 'GA'
        | 'HI'
        | 'ID'
        | 'IL'
        | 'IN'
        | 'IA'
        | 'KS'
        | 'KY'
        | 'LA'
        | 'ME'
        | 'MD'
        | 'MA'
        | 'MI'
        | 'MN'
        | 'MS'
        | 'MO'
        | 'MT'
        | 'NE'
        | 'NV'
        | 'NH'
        | 'NJ'
        | 'NM'
        | 'NY'
        | 'NC'
        | 'ND'
        | 'OH'
        | 'OK'
        | 'OR'
        | 'PA'
        | 'RI'
        | 'SC'
        | 'SD'
        | 'TN'
        | 'TX'
        | 'UT'
        | 'VT'
        | 'VA'
        | 'WA'
        | 'WV'
        | 'WI'
        | 'WY'
        | 'DC'
        | 'PR'
        | 'GU'
        | 'VI';
    }
  }

  export interface Server {
    /**
     * Proxy server URL
     */
    server: string;
  }

  export interface UnionMember3 {
    /**
     * Use Steel fixed IP proxies
     */
    type: 'fixed';

    /**
     * Specific fixed IP identifier. Omit to use any active fixed IP owned by the org.
     */
    id?: string;
  }
}

export interface SessionListParams extends SessionsCursorParams {
  /**
   * Only sessions created at or after this ISO 8601 timestamp, e.g.
   * 2026-10-01T00:00:00Z.
   */
  createdAfter?: string;

  /**
   * Only sessions created before this ISO 8601 timestamp.
   */
  createdBefore?: string;

  /**
   * Exact-match metadata filters, e.g. metadata[environment]=production. All entries
   * must match. Values match the stored string exactly; numbers and booleans are
   * stored as strings, so metadata[attempt]=3 matches attempt sent as 3 or "3".
   */
  metadata?: { [key: string]: string };

  /**
   * Filter sessions by project
   */
  projectId?: string;

  /**
   * Filter released sessions by why they ended. Live sessions have no release reason
   * and are excluded whenever this is set. Without an explicit status, matching
   * failed sessions are included.
   */
  releaseReason?:
    | 'user_requested'
    | 'timeout'
    | 'inactivity_timeout'
    | 'creation_timeout'
    | 'startup_failed'
    | 'browser_closed'
    | 'browser_crashed';

  /**
   * Signals the session must carry; repeat the parameter to require several. All
   * must match. Derived signals are finalized at release.
   */
  signal?: Array<
    | 'captcha_encountered'
    | 'proxy_connection_failed'
    | 'session_timeout_exceeded'
    | 'bot_detection_triggered'
    | 'context_restored'
  >;

  /**
   * Filter sessions by current status
   */
  status?: 'live' | 'released' | 'failed';
}

export interface SessionAgentTracesParams {
  endTime?: string;

  eventTypes?: Array<string>;

  limit?: number;

  namespace?: string;

  offset?: number;

  startTime?: string;
}

export interface SessionCommandsParams {
  /**
   * Body param: One command, or an array of up to 50. They run in order and stop at
   * the first failure. Commands that already ran are not undone.
   */
  commands:
    | SessionCommandsParams.NavigateCommand
    | SessionCommandsParams.BackCommand
    | SessionCommandsParams.ForwardCommand
    | SessionCommandsParams.ReloadCommand
    | SessionCommandsParams.SnapshotCommand
    | SessionCommandsParams.ClickCommand
    | SessionCommandsParams.DblclickCommand
    | SessionCommandsParams.HoverCommand
    | SessionCommandsParams.FocusCommand
    | SessionCommandsParams.TypeCommand
    | SessionCommandsParams.FillCommand
    | SessionCommandsParams.PressCommand
    | SessionCommandsParams.KeydownCommand
    | SessionCommandsParams.KeyupCommand
    | SessionCommandsParams.MouseCommand
    | SessionCommandsParams.SelectCommand
    | SessionCommandsParams.CheckCommand
    | SessionCommandsParams.UploadCommand
    | SessionCommandsParams.DragCommand
    | SessionCommandsParams.UncheckCommand
    | SessionCommandsParams.GetCommand
    | SessionCommandsParams.IsCommand
    | SessionCommandsParams.WaitCommand
    | SessionCommandsParams.ScreenshotCommand
    | SessionCommandsParams.EvalCommand
    | SessionCommandsParams.PdfCommand
    | SessionCommandsParams.ScrollCommand
    | SessionCommandsParams.FindCommand
    | SessionCommandsParams.TabCommand
    | Array<Command>;

  /**
   * Body param: The next value from your last response. A retry with the same after
   * and commands runs only once.
   */
  after?: string;

  /**
   * Body param: Returns a snapshot, or a diff against the last one, after the
   * commands.
   */
  observe?: SessionCommandsParams.Observe;

  /**
   * Header param: A random value. A retry with the same key returns the stored
   * result.
   */
  'idempotency-key'?: string;
}

export namespace SessionCommandsParams {
  /**
   * Opens a URL in the tab. Only http, https, and about:blank. A URL without a
   * scheme gets https://.
   */
  export interface NavigateCommand {
    command: 'navigate';

    url: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Goes back one history entry.
   */
  export interface BackCommand {
    command: 'back';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Goes forward one history entry.
   */
  export interface ForwardCommand {
    command: 'forward';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Reloads the tab.
   */
  export interface ReloadCommand {
    command: 'reload';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Returns the accessibility tree of the page, with a ref for each element you can
   * act on.
   */
  export interface SnapshotCommand {
    command: 'snapshot';

    /**
     * Only lines with refs or values, and their ancestors.
     */
    compact?: boolean;

    /**
     * Only lines up to this depth.
     */
    depth?: number;

    /**
     * Only lines with refs.
     */
    interactive?: boolean;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Clicks the element with the mouse.
   */
  export interface ClickCommand {
    command: 'click';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Double-clicks the element with the mouse.
   */
  export interface DblclickCommand {
    command: 'dblclick';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Moves the mouse onto the element.
   */
  export interface HoverCommand {
    command: 'hover';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Focuses the element.
   */
  export interface FocusCommand {
    command: 'focus';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Types text one key press at a time, after the current content.
   */
  export interface TypeCommand {
    command: 'type';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    text: string;

    /**
     * Replace the current content.
     */
    clear?: boolean;

    /**
     * Milliseconds to wait between key presses.
     */
    delay?: number;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Replaces the content of a field in one step, without key presses.
   */
  export interface FillCommand {
    command: 'fill';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    value: string;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Presses a key, such as Enter, or a combination such as Control+a.
   */
  export interface PressCommand {
    command: 'press';

    key: string;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Holds a key down until its keyup.
   */
  export interface KeydownCommand {
    command: 'keydown';

    key: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Lets go of a key held with keydown.
   */
  export interface KeyupCommand {
    command: 'keyup';

    key: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Mouse input at a point in CSS pixels of the visible part of the page, for pages
   * without useful elements such as a canvas.
   */
  export interface MouseCommand {
    action: 'move' | 'down' | 'up' | 'click' | 'wheel';

    command: 'mouse';

    button?: 'left' | 'right' | 'middle';

    clicks?: number;

    deltaX?: number;

    deltaY?: number;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    x?: number;

    y?: number;
  }

  /**
   * Selects options. Matches by value, then by label.
   */
  export interface SelectCommand {
    command: 'select';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    values: string | Array<string>;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Checks a checkbox, radio button, switch, or toggle button.
   */
  export interface CheckCommand {
    command: 'check';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Sets the files of a file input. Upload the files to the session first.
   */
  export interface UploadCommand {
    command: 'upload';

    files: string | Array<string>;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Drags one element onto another with the mouse.
   */
  export interface DragCommand {
    command: 'drag';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    source: SessionsAPI.CommandTarget;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Unchecks a checkbox, switch, or toggle button.
   */
  export interface UncheckCommand {
    command: 'uncheck';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Reads something from the page or an element.
   */
  export interface GetCommand {
    command: 'get';

    what: 'text' | 'html' | 'markdown' | 'value' | 'attr' | 'title' | 'url' | 'count' | 'box' | 'locator';

    /**
     * The attribute to read with attr.
     */
    attribute?: string;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Checks the state of an element.
   */
  export interface IsCommand {
    command: 'is';

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target: SessionsAPI.CommandTarget;

    what: 'visible' | 'enabled' | 'checked';

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Waits for one of an element, text, a URL, a load state, a download, or a fixed
   * time.
   */
  export interface WaitCommand {
    command: 'wait';

    /**
     * Waits for the next download to finish.
     */
    download?: true;

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    load?: 'load' | 'domcontentloaded' | 'networkidle';

    ms?: number;

    state?: 'visible' | 'hidden' | 'attached' | 'detached';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;

    text?: string;

    /**
     * Milliseconds. 10000 by default.
     */
    timeout?: number;

    /**
     * A URL pattern. \* matches any characters.
     */
    url?: string;
  }

  /**
   * Captures the page or an element as base64 data.
   */
  export interface ScreenshotCommand {
    command: 'screenshot';

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    format?: 'png' | 'jpeg' | 'webp';

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    fullPage?: boolean;

    quality?: number;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Runs JavaScript in the page and returns the result as JSON.
   */
  export interface EvalCommand {
    command: 'eval';

    script: string;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * Milliseconds. 10000 by default.
     */
    timeout?: number;
  }

  /**
   * Prints the page to a PDF as base64 data.
   */
  export interface PdfCommand {
    command: 'pdf';

    landscape?: boolean;

    printBackground?: boolean;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Scrolls the page, or an element when target is set.
   */
  export interface ScrollCommand {
    command: 'scroll';

    /**
     * Pixels. 300 by default.
     */
    amount?: number;

    direction?: 'up' | 'down' | 'left' | 'right';

    /**
     * Fails the command when the element does not have this role or name.
     */
    expect?: SessionsAPI.CommandExpectation;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    /**
     * A ref from a snapshot such as e12, a CSS selector, or a locator.
     */
    target?: SessionsAPI.CommandTarget;
  }

  /**
   * Finds elements and returns refs for up to 50 matches. With nth, it returns that
   * one match.
   */
  export interface FindCommand {
    command: 'find';

    /**
     * A CSS selector or a locator, not a ref.
     */
    target: SessionsAPI.CommandTarget;

    /**
     * The iframe to look in. List nested iframes from the outside in.
     */
    frame?: SessionsAPI.CommandFrame;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }

  /**
   * Lists, opens, or closes tabs. The last tab cannot be closed.
   */
  export interface TabCommand {
    action: 'list' | 'new' | 'close';

    command: 'tab';

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;

    url?: string;
  }

  /**
   * Returns a snapshot, or a diff against the last one, after the commands.
   */
  export interface Observe {
    mode: 'none' | 'snapshot' | 'diff';

    /**
     * A snapshot id to diff against.
     */
    base?: string;

    /**
     * Only lines with refs or values, and their ancestors.
     */
    compact?: boolean;

    /**
     * Only lines up to this depth.
     */
    depth?: number;

    /**
     * Only lines with refs.
     */
    interactive?: boolean;

    /**
     * A tab id such as t2. A command on a ref does not need it.
     */
    tab?: string;
  }
}

export type SessionComputerParams =
  | SessionComputerParams.Variant0
  | SessionComputerParams.Variant1
  | SessionComputerParams.Variant2
  | SessionComputerParams.Variant3
  | SessionComputerParams.Variant4
  | SessionComputerParams.Variant5
  | SessionComputerParams.Variant6
  | SessionComputerParams.Variant7
  | SessionComputerParams.Variant8;

export declare namespace SessionComputerParams {
  export interface Variant0 {
    action: 'move_mouse';

    /**
     * X and Y coordinates [x, y]
     */
    coordinates: Array<number>;

    /**
     * Keys to hold while moving
     */
    hold_keys?: Array<string>;

    /**
     * Whether to take a screenshot after the action
     */
    screenshot?: boolean;
  }

  export interface Variant1 {
    action: 'click_mouse';

    /**
     * Mouse button to click. Defaults to 'left'
     */
    button?: 'left' | 'right' | 'middle' | 'back' | 'forward';

    /**
     * Type of click (down, up, or click). Defaults to 'click'
     */
    click_type?: 'down' | 'up' | 'click';

    /**
     * X and Y coordinates [x, y]
     */
    coordinates?: Array<number>;

    /**
     * Keys to hold while clicking
     */
    hold_keys?: Array<string>;

    /**
     * Number of clicks. Defaults to 1
     */
    num_clicks?: number;

    /**
     * Whether to take a screenshot after the action
     */
    screenshot?: boolean;
  }

  export interface Variant2 {
    action: 'drag_mouse';

    /**
     * Array of [x, y] coordinate pairs
     */
    path: Array<Array<number>>;

    /**
     * Keys to hold while dragging
     */
    hold_keys?: Array<string>;

    /**
     * Whether to take a screenshot after the action
     */
    screenshot?: boolean;
  }

  export interface Variant3 {
    action: 'scroll';

    /**
     * X and Y coordinates [x, y]
     */
    coordinates?: Array<number>;

    /**
     * Horizontal scroll amount. Defaults to 0
     */
    delta_x?: number;

    /**
     * Vertical scroll amount. Defaults to 0
     */
    delta_y?: number;

    /**
     * Keys to hold while scrolling
     */
    hold_keys?: Array<string>;

    /**
     * Whether to take a screenshot after the action
     */
    screenshot?: boolean;
  }

  export interface Variant4 {
    action: 'press_key';

    /**
     * Keys to press
     */
    keys: Array<string>;

    /**
     * Duration to hold keys in seconds
     */
    duration?: number;

    /**
     * Whether to take a screenshot after the action
     */
    screenshot?: boolean;
  }

  export interface Variant5 {
    action: 'type_text';

    /**
     * Text to type
     */
    text: string;

    /**
     * Keys to hold while typing
     */
    hold_keys?: Array<string>;

    /**
     * Whether to take a screenshot after the action
     */
    screenshot?: boolean;
  }

  export interface Variant6 {
    action: 'wait';

    /**
     * Duration to wait in seconds
     */
    duration: number;

    /**
     * Whether to take a screenshot after the action
     */
    screenshot?: boolean;
  }

  export interface Variant7 {
    action: 'take_screenshot';
  }

  export interface Variant8 {
    action: 'get_cursor_position';
  }
}

export interface SessionEventsParams {
  /**
   * Compress the events
   */
  compressed?: boolean;

  /**
   * Optional pagination limit
   */
  limit?: number;

  /**
   * Opaque pagination token. Pass the Next-Cursor header value to get the next page.
   */
  pointer?: string;
}

export interface SessionLogsParams {
  endTime?: string;

  eventTypes?: Array<string>;

  limit?: number;

  namespace?: string;

  offset?: number;

  startTime?: string;
}

export interface SessionReleaseAllParams {
  /**
   * Release sessions only within this project
   */
  projectId?: string;
}

export interface SessionReplaceMetadataParams {
  /**
   * Customer-defined flat metadata: up to 50 keys and 8192 UTF-8 JSON bytes. Keys
   * must start with a letter, use only letters, digits, underscores, dots or
   * hyphens, and be at most 64 characters; steel\_ is reserved. Values are strings
   * (up to 1024 characters, valid Unicode without NUL), finite numbers or booleans.
   * Numbers and booleans are stored as strings, so 3 and "3" are the same value.
   */
  metadata: { [key: string]: string | number | boolean };
}

export interface SessionUpdateMetadataParams {
  /**
   * Metadata keys to change, up to 100 per update. Keys follow the same rules as
   * metadata keys. Send a string, finite number or boolean to set a key, or null to
   * remove it; keys you leave out are kept. The merged metadata must stay within 50
   * keys and 8192 UTF-8 JSON bytes.
   */
  metadata: { [key: string]: string | number | boolean | null };
}

Sessions.SessionslistSessionsSessionsCursor = SessionslistSessionsSessionsCursor;
Sessions.Captchas = Captchas;
Sessions.CommandExecutions = CommandExecutions;
Sessions.Files = Files;

export declare namespace Sessions {
  export {
    type Command as Command,
    type CommandDialog as CommandDialog,
    type CommandErrorInfo as CommandErrorInfo,
    type CommandExpectation as CommandExpectation,
    type CommandFrame as CommandFrame,
    type CommandLocator as CommandLocator,
    type CommandResult as CommandResult,
    type CommandTarget as CommandTarget,
    type Session as Session,
    type SessionContext as SessionContext,
    type Sessionslist as Sessionslist,
    type SessionAgentTracesResponse as SessionAgentTracesResponse,
    type SessionCommandsResponse as SessionCommandsResponse,
    type SessionComputerResponse as SessionComputerResponse,
    type SessionCostResponse as SessionCostResponse,
    type SessionEventsResponse as SessionEventsResponse,
    type SessionLiveDetailsResponse as SessionLiveDetailsResponse,
    type SessionLogsResponse as SessionLogsResponse,
    type SessionReleaseResponse as SessionReleaseResponse,
    type SessionReleaseAllResponse as SessionReleaseAllResponse,
    type SessionReplaceMetadataResponse as SessionReplaceMetadataResponse,
    type SessionSignalCatalogResponse as SessionSignalCatalogResponse,
    type SessionUpdateMetadataResponse as SessionUpdateMetadataResponse,
    SessionslistSessionsSessionsCursor as SessionslistSessionsSessionsCursor,
    type SessionCreateParams as SessionCreateParams,
    type SessionListParams as SessionListParams,
    type SessionAgentTracesParams as SessionAgentTracesParams,
    type SessionCommandsParams as SessionCommandsParams,
    type SessionComputerParams as SessionComputerParams,
    type SessionEventsParams as SessionEventsParams,
    type SessionLogsParams as SessionLogsParams,
    type SessionReleaseAllParams as SessionReleaseAllParams,
    type SessionReplaceMetadataParams as SessionReplaceMetadataParams,
    type SessionUpdateMetadataParams as SessionUpdateMetadataParams,
  };

  export {
    Captchas as Captchas,
    type CaptchaSolveResponse as CaptchaSolveResponse,
    type CaptchaSolveImageResponse as CaptchaSolveImageResponse,
    type CaptchaStatusResponse as CaptchaStatusResponse,
    type CaptchaSolveParams as CaptchaSolveParams,
    type CaptchaSolveImageParams as CaptchaSolveImageParams,
  };

  export {
    CommandExecutions as CommandExecutions,
    type CommandExecutionRetrieveResponse as CommandExecutionRetrieveResponse,
    type CommandExecutionCancelResponse as CommandExecutionCancelResponse,
  };

  export { Files as Files, type FileUploadParams as FileUploadParams };
}
