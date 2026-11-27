// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import Steel from 'steel-sdk';
import { Response } from 'node-fetch';

const client = new Steel({
  steelAPIKey: 'My Steel API Key',
  baseURL: process.env['TEST_API_BASE_URL'] ?? 'http://127.0.0.1:4010',
});

describe('resource secretConnections', () => {
  test('create: only required params', async () => {
    const responsePromise = client.secretConnections.create({
      auth: {},
      name: 'x',
      provider: '1password',
    });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('create: required and optional params', async () => {
    const response = await client.secretConnections.create({
      auth: {
        token: 'x',
        mount: 'x',
        roleId: 'x',
        secretId: 'x',
        serviceAccountToken: 'x',
        type: 'token',
      },
      name: 'x',
      provider: '1password',
      config: {
        address: 'x',
        caPem: 'x',
        namespace: 'x',
      },
      projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
      tokenBudget: { burst: 2, refillPerSecond: 0.001 },
    });
  });

  test('retrieve', async () => {
    const responsePromise = client.secretConnections.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
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
      client.secretConnections.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        path: '/_steel_unknown_path',
      }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('retrieve: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.secretConnections.retrieve(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('update', async () => {
    const responsePromise = client.secretConnections.update('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {});
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('list', async () => {
    const responsePromise = client.secretConnections.list();
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('list: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(client.secretConnections.list({ path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('list: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.secretConnections.list(
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('delete', async () => {
    const responsePromise = client.secretConnections.delete('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('delete: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.secretConnections.delete('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        path: '/_steel_unknown_path',
      }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('delete: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.secretConnections.delete(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('test', async () => {
    const responsePromise = client.secretConnections.test('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('test: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.secretConnections.test('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('test: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.secretConnections.test(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });
});
