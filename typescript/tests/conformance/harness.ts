import { readFileSync } from 'fs';
import { createServer, IncomingHttpHeaders } from 'http';
import { AddressInfo } from 'net';
import { join } from 'path';

type Json = null | boolean | number | string | Json[] | { [key: string]: Json };

interface Part {
  name: string;
  value?: string;
  file?: string;
}

interface ExpectedRequest {
  method: string;
  path: string[];
  query: [string, string][];
  headers: { [name: string]: string };
  contentType: string | null;
  body: { json: Json } | { multipart: Part[] } | null;
}

interface Exchange {
  request: ExpectedRequest;
  response: {
    status: number;
    contentType: string | null;
    body: { json: Json } | { text: string } | null;
  };
}

interface Case {
  lenient: boolean;
  exchanges: Exchange[];
  result: { json?: Json; items?: Json; ids?: string[]; text?: string };
}

interface Fixture {
  apiKey: string;
  cases: { [id: string]: Case };
}

interface Recorded {
  method: string;
  url: string;
  headers: IncomingHttpHeaders;
  body: Buffer;
}

const fixture: Fixture = JSON.parse(readFileSync(join(__dirname, 'cases.json'), 'utf8'));

const DATE_TIME = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}(:\d{2}(\.\d+)?)?(Z|[+-]\d{2}:\d{2})$/;
const NUMBER = /^-?\d+(\.\d+)?([eE][+-]?\d+)?$/;

function scalar(text: string): string {
  if (DATE_TIME.test(text)) {
    return new Date(text).toISOString();
  }
  if (NUMBER.test(text)) {
    return String(Number(text));
  }
  return text;
}

function normalize(value: unknown): unknown {
  if (typeof value === 'string') {
    return DATE_TIME.test(value) ? new Date(value).toISOString() : value;
  }
  if (Array.isArray(value)) {
    return value.map(normalize);
  }
  if (value !== null && typeof value === 'object') {
    return Object.fromEntries(Object.entries(value).map(([key, item]) => [key, normalize(item)]));
  }
  return value;
}

function decode(text: string): string {
  return decodeURIComponent(text.replace(/\+/g, ' '));
}

function query(raw: string): string[] {
  return raw
    .split('&')
    .filter((pair) => pair.length > 0)
    .map((pair) => {
      const index = pair.indexOf('=');
      const key = decode(index < 0 ? pair : pair.slice(0, index));
      const value = decode(index < 0 ? '' : pair.slice(index + 1));
      return `${key}=${scalar(value)}`;
    })
    .sort();
}

function parts(body: Buffer, contentType: string): string[] {
  const match = /boundary=(?:"([^"]+)"|([^;]+))/i.exec(contentType);
  const boundary = match?.[1] ?? match?.[2];
  if (!boundary) {
    throw new Error(`multipart body without a boundary: ${contentType}`);
  }
  return body
    .toString('latin1')
    .split(`--${boundary}`)
    .slice(1, -1)
    .map((chunk) => {
      const trimmed = chunk.replace(/^\r\n/, '').replace(/\r\n$/, '');
      const split = trimmed.indexOf('\r\n\r\n');
      const head = split < 0 ? trimmed : trimmed.slice(0, split);
      const content = Buffer.from(split < 0 ? '' : trimmed.slice(split + 4), 'latin1').toString('utf8');
      const disposition = /content-disposition:[^\r\n]*/i.exec(head)?.[0] ?? '';
      const name = /;\s*name="([^"]*)"/i.exec(disposition)?.[1] ?? '';
      const file = /;\s*filename="/i.test(disposition);
      return file ? `${name}=file:${content}` : `${name}=${scalar(content)}`;
    })
    .sort();
}

function header(headers: IncomingHttpHeaders, name: string): string {
  const value = headers[name];
  return Array.isArray(value) ? value.join(', ') : value ?? '';
}

function check(recorded: Recorded, expected: ExpectedRequest): void {
  expect(recorded.method).toBe(expected.method);
  const mark = recorded.url.indexOf('?');
  const path = mark < 0 ? recorded.url : recorded.url.slice(0, mark);
  const search = mark < 0 ? '' : recorded.url.slice(mark + 1);
  expect(
    path
      .split('/')
      .filter((segment) => segment.length > 0)
      .map(decodeURIComponent),
  ).toEqual(expected.path);
  expect(query(search)).toEqual(expected.query.map(([key, value]) => `${key}=${scalar(value)}`).sort());
  for (const [name, value] of Object.entries(expected.headers)) {
    expect(header(recorded.headers, name)).toBe(value);
  }
  const contentType = header(recorded.headers, 'content-type');
  if (expected.body === null) {
    expect(recorded.body.length).toBe(0);
    return;
  }
  if ('json' in expected.body) {
    expect(contentType.startsWith('application/json')).toBe(true);
    expect(normalize(JSON.parse(recorded.body.toString('utf8')))).toEqual(normalize(expected.body.json));
    return;
  }
  expect(contentType.startsWith('multipart/form-data')).toBe(true);
  expect(parts(recorded.body, contentType)).toEqual(
    expected.body.multipart
      .map((part) =>
        part.file !== undefined ?
          `${part.name}=file:${part.file}`
        : `${part.name}=${scalar(part.value ?? '')}`,
      )
      .sort(),
  );
}

async function serve(exchanges: Exchange[]) {
  const requests: Recorded[] = [];
  const server = createServer((request, response) => {
    const chunks: Buffer[] = [];
    request.on('data', (chunk: Buffer) => chunks.push(chunk));
    request.on('end', () => {
      const exchange = exchanges[requests.length];
      requests.push({
        method: request.method ?? '',
        url: request.url ?? '',
        headers: request.headers,
        body: Buffer.concat(chunks),
      });
      if (!exchange) {
        response.writeHead(500);
        response.end();
        return;
      }
      const reply = exchange.response;
      response.writeHead(reply.status, reply.contentType ? { 'content-type': reply.contentType } : {});
      if (reply.body === null) {
        response.end();
      } else if ('json' in reply.body) {
        response.end(JSON.stringify(reply.body.json));
      } else {
        response.end(reply.body.text);
      }
    });
  });
  await new Promise<void>((resolve) => server.listen(0, '127.0.0.1', resolve));
  const { port } = server.address() as AddressInfo;
  return {
    url: `http://127.0.0.1:${port}`,
    requests,
    close: () =>
      new Promise<void>((resolve) => {
        server.closeAllConnections();
        server.close(() => resolve());
      }),
  };
}

export async function conform<Client>(
  id: string,
  make: (baseURL: string, apiKey: string) => Client,
  call: (client: Client) => Promise<unknown>,
): Promise<void> {
  const expected = fixture.cases[id];
  if (!expected) {
    throw new Error(`unknown conformance case ${id}`);
  }
  const server = await serve(expected.exchanges);
  try {
    const result = await call(make(server.url, fixture.apiKey));
    expect(server.requests.length).toBe(expected.exchanges.length);
    expected.exchanges.forEach((exchange, index) => {
      const recorded = server.requests[index];
      if (!recorded) {
        throw new Error(`request ${index} of ${id} was not sent`);
      }
      check(recorded, exchange.request);
    });
    if (expected.result.ids !== undefined) {
      expect(result).toEqual(expected.result.ids);
    } else if (expected.result.items !== undefined) {
      expect(normalize(result)).toEqual(normalize(expected.result.items));
    } else if (expected.result.text !== undefined) {
      expect(result).toBe(expected.result.text);
    } else if (expected.result.json !== undefined) {
      expect(normalize(result)).toEqual(normalize(expected.result.json));
    }
  } finally {
    await server.close();
  }
}
