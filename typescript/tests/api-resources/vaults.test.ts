// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import Steel from 'steel-sdk';
import { Response } from 'node-fetch';

const client = new Steel({
  steelAPIKey: 'My Steel API Key',
  baseURL: process.env['TEST_API_BASE_URL'] ?? 'http://127.0.0.1:4010',
});

describe('resource vaults', () => {
  test('create: only required params', async () => {
    const responsePromise = client.vaults.create({ name: 'name' });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('create: required and optional params', async () => {
    const response = await client.vaults.create({
      name: 'name',
      description: 'description',
      projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
    });
  });

  test('retrieve', async () => {
    const responsePromise = client.vaults.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
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
      client.vaults.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('retrieve: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.vaults.retrieve(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('update', async () => {
    const responsePromise = client.vaults.update('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {});
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('list', async () => {
    const responsePromise = client.vaults.list();
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
    await expect(client.vaults.list({ path: '/_steel_unknown_path' })).rejects.toThrow(Steel.NotFoundError);
  });

  test('list: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.vaults.list(
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('delete', async () => {
    const responsePromise = client.vaults.delete('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e');
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
      client.vaults.delete('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('delete: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.vaults.delete(
        '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        { projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });
});
