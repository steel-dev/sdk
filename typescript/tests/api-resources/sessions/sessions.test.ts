// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import Steel from 'steel-sdk';
import { Response } from 'node-fetch';

const client = new Steel({
  steelAPIKey: 'My Steel API Key',
  baseURL: process.env['TEST_API_BASE_URL'] ?? 'http://127.0.0.1:4010',
});

describe('resource sessions', () => {
  test('create', async () => {
    const responsePromise = client.sessions.create();
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('create: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.create(
        {
          blockAds: true,
          caCertificates: ['string'],
          concurrency: -9007199254740991,
          credentials: {
            autoSubmit: true,
            blurFields: true,
            credentialProvider: 'credentialProvider',
            exactOrigin: true,
            origins: ['string'],
          },
          debugConfig: { interactive: true, systemCursor: true },
          deviceConfig: { device: 'desktop' },
          dimensions: { height: -9007199254740991, width: -9007199254740991 },
          experimentalFeatures: ['string'],
          extensionIds: ['string'],
          fullscreen: true,
          headless: true,
          inactivityTimeout: 1,
          isSelenium: true,
          metadata: { foo: 'string' },
          namespace: 'namespace',
          optimizeBandwidth: true,
          persistProfile: true,
          profileId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
          projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
          proxyUrl: 'https://example.com',
          recording: true,
          region: {},
          sessionContext: {
            cookies: [
              {
                name: 'name',
                value: 'value',
                domain: 'domain',
                expires: 0,
                httpOnly: true,
                partitionKey: { hasCrossSiteAncestor: true, topLevelSite: 'topLevelSite' },
                path: 'path',
                priority: 'Low',
                sameParty: true,
                sameSite: 'Strict',
                secure: true,
                session: true,
                size: 0,
                sourcePort: 0,
                sourceScheme: 'Unset',
                url: 'url',
              },
            ],
            indexedDB: {
              foo: [
                {
                  id: 0,
                  data: [
                    {
                      id: 0,
                      name: 'name',
                      records: [
                        {
                          key: {},
                          value: {},
                          blobFiles: [
                            {
                              blobNumber: 0,
                              mimeType: 'mimeType',
                              size: 0,
                              filename: 'filename',
                              lastModified: '2019-12-27T18:11:19.117Z',
                              path: 'path',
                            },
                          ],
                        },
                      ],
                    },
                  ],
                  name: 'name',
                },
              ],
            },
            localStorage: { foo: { foo: 'string' } },
            sessionStorage: { foo: { foo: 'string' } },
          },
          sessionId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
          solveCaptcha: true,
          stealthConfig: {
            autoCaptchaSolving: true,
            humanizeInteractions: true,
            skipFingerprintInjection: true,
          },
          timeout: -9007199254740991,
          useProxy: true,
          userAgent: 'userAgent',
        },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('retrieve', async () => {
    const responsePromise = client.sessions.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('retrieve: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('list', async () => {
    const responsePromise = client.sessions.list();
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('list: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.list(
        {
          createdAfter: '2019-12-27T18:11:19.117Z',
          createdBefore: '2019-12-27T18:11:19.117Z',
          cursorId: 'cursorId',
          limit: 1,
          metadata: { foo: 'x' },
          projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
          releaseReason: 'user_requested',
          signal: ['captcha_encountered'],
          status: 'live',
        },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('agentTraces', async () => {
    const responsePromise = client.sessions.agentTraces('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('agentTraces: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.agentTraces('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('agentTraces: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.agentTraces(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        {
          endTime: '2019-12-27T18:11:19.117Z',
          eventTypes: ['string'],
          limit: 1,
          namespace: 'namespace',
          offset: 0,
          startTime: '2019-12-27T18:11:19.117Z',
        },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('commands: only required params', async () => {
    const responsePromise = client.sessions.commands('sessionId', {
      commands: { command: 'navigate', url: 'x' },
    });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('commands: required and optional params', async () => {
    const response = await client.sessions.commands('sessionId', {
      commands: {
        command: 'navigate',
        url: 'x',
        tab: 't321669910225',
      },
      after: 'x',
      eventsAfter: 'c-321669910225',
      observe: {
        mode: 'none',
        base: 'x',
        compact: true,
        depth: 0,
        interactive: true,
        tab: 't321669910225',
      },
      'idempotency-key': 'idempotency-key',
    });
  });

  test('computer: only required params', async () => {
    const responsePromise = client.sessions.computer('sessionId', {
      action: 'move_mouse',
      coordinates: [0, 0],
    });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('computer: required and optional params', async () => {
    const response = await client.sessions.computer('sessionId', {
      action: 'move_mouse',
      coordinates: [0, 0],
      hold_keys: ['string'],
      screenshot: true,
    });
  });

  test('context', async () => {
    const responsePromise = client.sessions.context('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('context: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.context('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('cost', async () => {
    const responsePromise = client.sessions.cost('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('cost: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.cost('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('events', async () => {
    const responsePromise = client.sessions.events('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('events: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.events('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('events: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.events(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        {
          compressed: true,
          limit: 1,
          pointer: 'pointer',
        },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('hls: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.hls('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('liveDetails', async () => {
    const responsePromise = client.sessions.liveDetails('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('liveDetails: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.liveDetails('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('logs', async () => {
    const responsePromise = client.sessions.logs('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('logs: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.logs('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('logs: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.logs(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        {
          endTime: '2019-12-27T18:11:19.117Z',
          eventTypes: ['string'],
          limit: 1,
          namespace: 'namespace',
          offset: 0,
          startTime: '2019-12-27T18:11:19.117Z',
        },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('release', async () => {
    const responsePromise = client.sessions.release('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('release: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.release('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('releaseAll', async () => {
    const responsePromise = client.sessions.releaseAll();
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('releaseAll: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(client.sessions.releaseAll({ path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('releaseAll: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.releaseAll(
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('replaceMetadata: only required params', async () => {
    const responsePromise = client.sessions.replaceMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
      metadata: { foo: 'string' },
    });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('replaceMetadata: required and optional params', async () => {
    const response = await client.sessions.replaceMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
      metadata: { foo: 'string' },
    });
  });

  test('signalCatalog', async () => {
    const responsePromise = client.sessions.signalCatalog();
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('signalCatalog: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(client.sessions.signalCatalog({ path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('updateMetadata: only required params', async () => {
    const responsePromise = client.sessions.updateMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
      metadata: { foo: 'string' },
    });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('updateMetadata: required and optional params', async () => {
    const response = await client.sessions.updateMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
      metadata: { foo: 'string' },
    });
  });
});
