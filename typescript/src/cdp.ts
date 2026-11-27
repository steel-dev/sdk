export type CredentialRole = 'username' | 'password' | 'otp';

export interface CredentialSelector {
  selector: string | null;
  shadowHosts: string[];
}

export interface CredentialField extends CredentialSelector {
  fieldId: string;
  role: CredentialRole | null;
  visible: boolean;
  empty: boolean;
  filledBySteel: boolean;
  segment?: { index: number; count: number };
}

export interface CredentialGroup {
  groupId: string;
  status: 'accepted' | 'unknown' | 'rejected';
  stepKind: string;
  submittedBySteel: boolean;
  roles: CredentialRole[];
  evidence: string[];
  fields: CredentialField[];
  submit: CredentialSelector | null;
  formSelector: CredentialSelector | null;
}

export interface CredentialCandidate {
  id: string;
  name: string | null;
  origin: string;
  reference?: string;
}

export interface CredentialFrame {
  frameId: number;
  parentFrameId: number;
  documentId: string;
  origin: string;
  path: string;
  url: string;
  groups: CredentialGroup[];
  credentialAvailable?: boolean;
  credentialRoles?: CredentialRole[];
  credentialPaths?: string[] | null;
  credentialsDisabled?: boolean;
  credentialsChanged?: boolean;
  credentialAmbiguous?: boolean;
  credentialResolverUnavailable?: boolean;
  credentialRateLimited?: boolean;
  providerFailureCount?: number;
  credentialLookup?: 'implicit' | 'explicit';
  credentialCandidates?: CredentialCandidate[];
  defaultCredentialId?: string | null;
}

export interface DetectCredentialsParams {
  origin?: string;
  includeRejected?: boolean;
  checkCredentials?: boolean;
}

export interface DetectCredentialsResult {
  frames: CredentialFrame[];
  unresponsiveFrames: { frameId: number; documentId: string | null }[];
}

type CredentialChoice =
  | { credentialId?: string; reference?: never }
  | { credentialId?: never; reference: string };

export type FillCredentialsParams = CredentialChoice &
  (
    | { frameId: number; documentId: string; groupId?: string }
    | { frameId?: never; documentId?: never; groupId?: never }
  ) & {
    origin?: string;
    /** Defaults to false. */
    submit?: boolean;
  };

type FieldOutcome =
  | 'filled'
  | 'already_filled_by_steel'
  | 'not_empty'
  | 'not_writable'
  | 'credential_unavailable'
  | 'segment_length_mismatch';

export interface FillCredentialsResult {
  frameId: number;
  documentId: string;
  groupId: string;
  filled: CredentialRole[];
  fields: { fieldId: string; role: CredentialRole; outcome: FieldOutcome }[];
  skipped: { role: CredentialRole; reason: Exclude<FieldOutcome, 'filled'> }[];
  submitted: boolean;
  submitError?: { code: string; reason?: string };
}

export type InjectCredentialParams = CredentialChoice & {
  selector: string;
  path: string;
  origin?: string;
};

export interface CredentialControls {
  resolution: boolean;
  autofill: boolean;
  autoSubmit: boolean;
  blurFields: boolean;
  domTrigger: boolean;
}

export interface CredentialControlsState {
  controls: CredentialControls;
  revision: number;
}

export interface CredentialControlsUpdate extends CredentialControlsState {
  acknowledgedFrames: number;
  unacknowledgedFrames: number;
  skippedFrames: number;
}

export interface SteelCDPHelpers {
  detect(params?: DetectCredentialsParams): Promise<DetectCredentialsResult>;
  fill(params?: FillCredentialsParams): Promise<FillCredentialsResult>;
  injectCredential(params: InjectCredentialParams): Promise<{ injected: true }>;
  getControls(): Promise<CredentialControlsState>;
  setControls(params: Partial<CredentialControls>): Promise<CredentialControlsUpdate>;
}

// The browser libraries expose different generic send signatures. Don't import
// either library into the SDK's public declarations or broaden native send.
type CDPTransport = { send: (...args: never[]) => Promise<unknown> };

/** Add typed Steel commands to a Playwright or Puppeteer CDP session without modifying it. */
export function wrapCDPSession<Session extends CDPTransport>(
  session: Session,
): Session & { readonly steel: SteelCDPHelpers } {
  const send = session.send as (method: string, params?: object) => Promise<unknown>;
  const call = <Result>(method: string, params?: object): Promise<Result> =>
    (params === undefined ?
      send.call(session, method)
    : send.call(session, method, params)) as Promise<Result>;

  const steel: SteelCDPHelpers = {
    detect: (params) => call('Steel.credentials.detect', params),
    fill: (params) => call('Steel.credentials.fill', params),
    injectCredential: (params) => call('Steel.injectCredential', params),
    getControls: () => call('Steel.credentials.getControls'),
    setControls: (params) => call('Steel.credentials.setControls', params),
  };

  const methods = new WeakMap<Function, Function>();
  const wrapped = new Proxy(session, {
    get(target, property) {
      if (property === 'steel') return steel;
      const value: unknown = Reflect.get(target, property, target);
      if (typeof value !== 'function' || property === 'constructor') return value;
      const cached = methods.get(value);
      if (cached) return cached;
      // Native methods may use private fields, so invoke them on the original
      // session. Keep fluent event registration on the wrapped session.
      const method = (...args: unknown[]): unknown => {
        const result: unknown = Reflect.apply(value, target, args);
        return result === target ? wrapped : result;
      };
      methods.set(value, method);
      return method;
    },
  });
  return wrapped as Session & { readonly steel: SteelCDPHelpers };
}
