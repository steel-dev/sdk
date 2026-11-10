// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import Steel from 'steel-sdk';
import { APIUserAbortError } from 'steel-sdk';
import { Headers } from 'steel-sdk/core';
import defaultFetch, { Response, type RequestInit, type RequestInfo } from 'node-fetch';

describe('instantiate client', () => {
  const env = process.env;

  beforeEach(() => {
    jest.resetModules();
    process.env = { ...env };

    console.warn = jest.fn();
  });

  afterEach(() => {
    process.env = env;
  });

  describe('defaultHeaders', () => {
    const client = new Steel({
      baseURL: 'http://localhost:5000/',
      defaultHeaders: { 'X-My-Default-Header': '2' },
      steelAPIKey: 'My Steel API Key',
    });

    test('they are used in the request', async () => {
      const { req } = await client.buildRequest({ path: '/foo', method: 'post' });
      expect((req.headers as Headers)['x-my-default-header']).toEqual('2');
    });

    test('can ignore `undefined` and leave the default', async () => {
      const { req } = await client.buildRequest({
        path: '/foo',
        method: 'post',
        headers: { 'X-My-Default-Header': undefined },
      });
      expect((req.headers as Headers)['x-my-default-header']).toEqual('2');
    });

    test('can be removed with `null`', async () => {
      const { req } = await client.buildRequest({
        path: '/foo',
        method: 'post',
        headers: { 'X-My-Default-Header': null },
      });
      expect(req.headers as Headers).not.toHaveProperty('x-my-default-header');
    });
  });

  describe('defaultQuery', () => {
    test('with null query params given', () => {
      const client = new Steel({
        baseURL: 'http://localhost:5000/',
        defaultQuery: { apiVersion: 'foo' },
        steelAPIKey: 'My Steel API Key',
      });
      expect(client.buildURL('/foo', null)).toEqual('http://localhost:5000/foo?apiVersion=foo');
    });

    test('multiple default query params', () => {
      const client = new Steel({
        baseURL: 'http://localhost:5000/',
        defaultQuery: { apiVersion: 'foo', hello: 'world' },
        steelAPIKey: 'My Steel API Key',
      });
      expect(client.buildURL('/foo', null)).toEqual('http://localhost:5000/foo?apiVersion=foo&hello=world');
    });

    test('overriding with `undefined`', () => {
      const client = new Steel({
        baseURL: 'http://localhost:5000/',
        defaultQuery: { hello: 'world' },
        steelAPIKey: 'My Steel API Key',
      });
      expect(client.buildURL('/foo', { hello: undefined })).toEqual('http://localhost:5000/foo');
    });
  });

  test('custom fetch', async () => {
    const client = new Steel({
      baseURL: 'http://localhost:5000/',
      steelAPIKey: 'My Steel API Key',
      fetch: (url) => {
        return Promise.resolve(
          new Response(JSON.stringify({ url, custom: true }), {
            headers: { 'Content-Type': 'application/json' },
          }),
        );
      },
    });

    const response = await client.get('/foo');
    expect(response).toEqual({ url: 'http://localhost:5000/foo', custom: true });
  });

  test('explicit global fetch', async () => {
    // make sure the global fetch type is assignable to our Fetch type
    const client = new Steel({
      baseURL: 'http://localhost:5000/',
      steelAPIKey: 'My Steel API Key',
      fetch: defaultFetch,
    });
  });

  test('custom signal', async () => {
    const client = new Steel({
      baseURL: process.env['TEST_API_BASE_URL'] ?? 'http://127.0.0.1:4010',
      steelAPIKey: 'My Steel API Key',
      fetch: (...args) => {
        return new Promise((resolve, reject) =>
          setTimeout(
            () =>
              defaultFetch(...args)
                .then(resolve)
                .catch(reject),
            300,
          ),
        );
      },
    });

    const controller = new AbortController();
    setTimeout(() => controller.abort(), 200);

    const spy = jest.spyOn(client, 'request');

    await expect(client.get('/foo', { signal: controller.signal })).rejects.toThrowError(APIUserAbortError);
    expect(spy).toHaveBeenCalledTimes(1);
  });

  test('normalized method', async () => {
    let capturedRequest: RequestInit | undefined;
    const testFetch = async (url: RequestInfo, init: RequestInit = {}): Promise<Response> => {
      capturedRequest = init;
      return new Response(JSON.stringify({}), { headers: { 'Content-Type': 'application/json' } });
    };

    const client = new Steel({
      baseURL: 'http://localhost:5000/',
      steelAPIKey: 'My Steel API Key',
      fetch: testFetch,
    });

    await client.patch('/foo');
    expect(capturedRequest?.method).toEqual('PATCH');
  });

  describe('baseUrl', () => {
    test('trailing slash', () => {
      const client = new Steel({
        baseURL: 'http://localhost:5000/custom/path/',
        steelAPIKey: 'My Steel API Key',
      });
      expect(client.buildURL('/foo', null)).toEqual('http://localhost:5000/custom/path/foo');
    });

    test('no trailing slash', () => {
      const client = new Steel({
        baseURL: 'http://localhost:5000/custom/path',
        steelAPIKey: 'My Steel API Key',
      });
      expect(client.buildURL('/foo', null)).toEqual('http://localhost:5000/custom/path/foo');
    });

    afterEach(() => {
      process.env['STEEL_BASE_URL'] = undefined;
    });

    test('explicit option', () => {
      const client = new Steel({ baseURL: 'https://example.com', steelAPIKey: 'My Steel API Key' });
      expect(client.baseURL).toEqual('https://example.com');
    });

    test('env variable', () => {
      process.env['STEEL_BASE_URL'] = 'https://example.com/from_env';
      const client = new Steel({ steelAPIKey: 'My Steel API Key' });
      expect(client.baseURL).toEqual('https://example.com/from_env');
    });

    test('empty env variable', () => {
      process.env['STEEL_BASE_URL'] = ''; // empty
      const client = new Steel({ steelAPIKey: 'My Steel API Key' });
      expect(client.baseURL).toEqual('https://api.steel.dev');
    });

    test('blank env variable', () => {
      process.env['STEEL_BASE_URL'] = '  '; // blank
      const client = new Steel({ steelAPIKey: 'My Steel API Key' });
      expect(client.baseURL).toEqual('https://api.steel.dev');
    });

    test('in request options', () => {
      const client = new Steel({ steelAPIKey: 'My Steel API Key' });
      expect(client.buildURL('/foo', null, 'http://localhost:5000/option')).toEqual(
        'http://localhost:5000/option/foo',
      );
    });

    test('in request options overridden by client options', () => {
      const client = new Steel({ steelAPIKey: 'My Steel API Key', baseURL: 'http://localhost:5000/client' });
      expect(client.buildURL('/foo', null, 'http://localhost:5000/option')).toEqual(
        'http://localhost:5000/client/foo',
      );
    });

    test('in request options overridden by env variable', () => {
      process.env['STEEL_BASE_URL'] = 'http://localhost:5000/env';
      const client = new Steel({ steelAPIKey: 'My Steel API Key' });
      expect(client.buildURL('/foo', null, 'http://localhost:5000/option')).toEqual(
        'http://localhost:5000/env/foo',
      );
    });
  });

  test('maxRetries option is correctly set', () => {
    const client = new Steel({ maxRetries: 4, steelAPIKey: 'My Steel API Key' });
    expect(client.maxRetries).toEqual(4);

    // default
    const client2 = new Steel({ steelAPIKey: 'My Steel API Key' });
    expect(client2.maxRetries).toEqual(2);
  });

  test('with environment variable arguments', () => {
    // set options via env var
    process.env['STEEL_API_KEY'] = 'My Steel API Key';
    const client = new Steel();
    expect(client.steelAPIKey).toBe('My Steel API Key');
  });

  test('with overridden environment variable arguments', () => {
    // set options via env var
    process.env['STEEL_API_KEY'] = 'another My Steel API Key';
    const client = new Steel({ steelAPIKey: 'My Steel API Key' });
    expect(client.steelAPIKey).toBe('My Steel API Key');
  });
});

describe('request building', () => {
  const client = new Steel({ steelAPIKey: 'My Steel API Key' });

  describe('Content-Length', () => {
    test('handles multi-byte characters', async () => {
      const { req } = await client.buildRequest({ path: '/foo', method: 'post', body: { value: '—' } });
      expect((req.headers as Record<string, string>)['content-length']).toEqual('20');
    });

    test('handles standard characters', async () => {
      const { req } = await client.buildRequest({ path: '/foo', method: 'post', body: { value: 'hello' } });
      expect((req.headers as Record<string, string>)['content-length']).toEqual('22');
    });
  });

  describe('custom headers', () => {
    test('handles undefined', async () => {
      const { req } = await client.buildRequest({
        path: '/foo',
        method: 'post',
        body: { value: 'hello' },
        headers: { 'X-Foo': 'baz', 'x-foo': 'bar', 'x-Foo': undefined, 'x-baz': 'bam', 'X-Baz': null },
      });
      expect((req.headers as Record<string, string>)['x-foo']).toEqual('bar');
      expect((req.headers as Record<string, string>)['x-Foo']).toEqual(undefined);
      expect((req.headers as Record<string, string>)['X-Foo']).toEqual(undefined);
      expect((req.headers as Record<string, string>)['x-baz']).toEqual(undefined);
    });
  });
});

describe('retries', () => {
  test('retry on timeout', async () => {
    let count = 0;
    const testFetch = async (url: RequestInfo, { signal }: RequestInit = {}): Promise<Response> => {
      if (count++ === 0) {
        return new Promise(
          (resolve, reject) => signal?.addEventListener('abort', () => reject(new Error('timed out'))),
        );
      }
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };

    const client = new Steel({
      steelAPIKey: 'My Steel API Key',
      timeout: 10,
      fetch: testFetch,
    });

    expect(await client.request({ path: '/foo', method: 'get' })).toEqual({ a: 1 });
    expect(count).toEqual(2);
    expect(
      await client
        .request({ path: '/foo', method: 'get' })
        .asResponse()
        .then((r) => r.text()),
    ).toEqual(JSON.stringify({ a: 1 }));
    expect(count).toEqual(3);
  });

  test('retry count header', async () => {
    let count = 0;
    let capturedRequest: RequestInit | undefined;
    const testFetch = async (url: RequestInfo, init: RequestInit = {}): Promise<Response> => {
      count++;
      if (count <= 2) {
        return new Response(undefined, {
          status: 429,
          headers: {
            'Retry-After': '0.1',
          },
        });
      }
      capturedRequest = init;
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };

    const client = new Steel({
      steelAPIKey: 'My Steel API Key',
      fetch: testFetch,
      maxRetries: 4,
    });

    expect(await client.request({ path: '/foo', method: 'get' })).toEqual({ a: 1 });

    expect((capturedRequest!.headers as Headers)['x-steel-retry-count']).toEqual('2');
    expect(count).toEqual(3);
  });

  test('omit retry count header', async () => {
    let count = 0;
    let capturedRequest: RequestInit | undefined;
    const testFetch = async (url: RequestInfo, init: RequestInit = {}): Promise<Response> => {
      count++;
      if (count <= 2) {
        return new Response(undefined, {
          status: 429,
          headers: {
            'Retry-After': '0.1',
          },
        });
      }
      capturedRequest = init;
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };
    const client = new Steel({
      steelAPIKey: 'My Steel API Key',
      fetch: testFetch,
      maxRetries: 4,
    });

    expect(
      await client.request({
        path: '/foo',
        method: 'get',
        headers: { 'X-Steel-Retry-Count': null },
      }),
    ).toEqual({ a: 1 });

    expect(capturedRequest!.headers as Headers).not.toHaveProperty('x-steel-retry-count');
  });

  test('omit retry count header by default', async () => {
    let count = 0;
    let capturedRequest: RequestInit | undefined;
    const testFetch = async (url: RequestInfo, init: RequestInit = {}): Promise<Response> => {
      count++;
      if (count <= 2) {
        return new Response(undefined, {
          status: 429,
          headers: {
            'Retry-After': '0.1',
          },
        });
      }
      capturedRequest = init;
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };
    const client = new Steel({
      steelAPIKey: 'My Steel API Key',
      fetch: testFetch,
      maxRetries: 4,
      defaultHeaders: { 'X-Steel-Retry-Count': null },
    });

    expect(
      await client.request({
        path: '/foo',
        method: 'get',
      }),
    ).toEqual({ a: 1 });

    expect(capturedRequest!.headers as Headers).not.toHaveProperty('x-steel-retry-count');
  });

  test('overwrite retry count header', async () => {
    let count = 0;
    let capturedRequest: RequestInit | undefined;
    const testFetch = async (url: RequestInfo, init: RequestInit = {}): Promise<Response> => {
      count++;
      if (count <= 2) {
        return new Response(undefined, {
          status: 429,
          headers: {
            'Retry-After': '0.1',
          },
        });
      }
      capturedRequest = init;
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };
    const client = new Steel({
      steelAPIKey: 'My Steel API Key',
      fetch: testFetch,
      maxRetries: 4,
    });

    expect(
      await client.request({
        path: '/foo',
        method: 'get',
        headers: { 'X-Steel-Retry-Count': '42' },
      }),
    ).toEqual({ a: 1 });

    expect((capturedRequest!.headers as Headers)['x-steel-retry-count']).toBe('42');
  });

  test('retry on 429 with retry-after', async () => {
    let count = 0;
    const testFetch = async (url: RequestInfo, { signal }: RequestInit = {}): Promise<Response> => {
      if (count++ === 0) {
        return new Response(undefined, {
          status: 429,
          headers: {
            'Retry-After': '0.1',
          },
        });
      }
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };

    const client = new Steel({ steelAPIKey: 'My Steel API Key', fetch: testFetch });

    expect(await client.request({ path: '/foo', method: 'get' })).toEqual({ a: 1 });
    expect(count).toEqual(2);
    expect(
      await client
        .request({ path: '/foo', method: 'get' })
        .asResponse()
        .then((r) => r.text()),
    ).toEqual(JSON.stringify({ a: 1 }));
    expect(count).toEqual(3);
  });

  test('retry on 429 with retry-after-ms', async () => {
    let count = 0;
    const testFetch = async (url: RequestInfo, { signal }: RequestInit = {}): Promise<Response> => {
      if (count++ === 0) {
        return new Response(undefined, {
          status: 429,
          headers: {
            'Retry-After-Ms': '10',
          },
        });
      }
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };

    const client = new Steel({ steelAPIKey: 'My Steel API Key', fetch: testFetch });

    expect(await client.request({ path: '/foo', method: 'get' })).toEqual({ a: 1 });
    expect(count).toEqual(2);
    expect(
      await client
        .request({ path: '/foo', method: 'get' })
        .asResponse()
        .then((r) => r.text()),
    ).toEqual(JSON.stringify({ a: 1 }));
    expect(count).toEqual(3);
  });
});

describe('idempotency key', () => {
  const recorder = (failures = 0) => {
    const sent: Headers[] = [];
    const fetch = async (url: RequestInfo, init: RequestInit = {}): Promise<Response> => {
      sent.push(init.headers as Headers);
      if (sent.length <= failures) {
        return new Response(undefined, { status: 503, headers: { 'Retry-After-Ms': '10' } });
      }
      return new Response(JSON.stringify({ a: 1 }), { headers: { 'Content-Type': 'application/json' } });
    };
    return { sent, client: new Steel({ steelAPIKey: 'My Steel API Key', fetch }) };
  };

  const keys = (headers: Headers) =>
    Object.keys(headers).filter((name) => name.toLowerCase() === 'idempotency-key');

  test('a request other than GET sends one key and reuses it on retry', async () => {
    const { sent, client } = recorder(1);

    await client.request({ path: '/foo', method: 'post' });

    expect(sent).toHaveLength(2);
    expect(sent[0]!['idempotency-key']).toMatch(/^steel-node-retry-/);
    expect(sent[1]!['idempotency-key']).toEqual(sent[0]!['idempotency-key']);
  });

  test('each call gets its own key and leaves the headers it was given alone', async () => {
    const { sent, client } = recorder();
    const headers = { 'X-Custom': 'value' };

    await client.request({ path: '/foo', method: 'post', headers });
    await client.request({ path: '/foo', method: 'post', headers });

    expect(headers).toEqual({ 'X-Custom': 'value' });
    expect(sent[0]!['idempotency-key']).toBeTruthy();
    expect(sent[1]!['idempotency-key']).toBeTruthy();
    expect(sent[1]!['idempotency-key']).not.toEqual(sent[0]!['idempotency-key']);
  });

  test('an options object passed twice gets a new key each time and stays as it was', async () => {
    const { sent, client } = recorder();
    const options = { path: '/foo', method: 'post' as const };

    await client.request(options);
    await client.request(options);

    expect(options).toEqual({ path: '/foo', method: 'post' });
    expect(sent[0]!['idempotency-key']).toBeTruthy();
    expect(sent[1]!['idempotency-key']).not.toEqual(sent[0]!['idempotency-key']);
  });

  test('GET sends no key', async () => {
    const { sent, client } = recorder();

    await client.request({ path: '/foo', method: 'get' });

    expect(keys(sent[0]!)).toEqual([]);
  });

  test('a key in the headers wins and is sent once', async () => {
    const { sent, client } = recorder(1);

    await client.request({ path: '/foo', method: 'post', headers: { 'idempotency-key': 'mine' } });

    expect(sent.map((headers) => headers['idempotency-key'])).toEqual(['mine', 'mine']);
    expect(keys(sent[0]!)).toEqual(['idempotency-key']);
  });

  test('the idempotencyKey option wins', async () => {
    const { sent, client } = recorder();

    await client.request({
      path: '/foo',
      method: 'post',
      idempotencyKey: 'option',
      headers: { 'Idempotency-Key': 'header' },
    });

    expect(sent[0]!['idempotency-key']).toEqual('option');
    expect(keys(sent[0]!)).toEqual(['idempotency-key']);
  });

  test('null removes the key', async () => {
    const { sent, client } = recorder();

    await client.request({ path: '/foo', method: 'post', headers: { 'Idempotency-Key': null } });

    expect(keys(sent[0]!)).toEqual([]);
  });
});
