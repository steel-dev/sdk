// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import Steel, { toFile } from 'steel-sdk';
import { Response } from 'node-fetch';

const client = new Steel({
  steelAPIKey: 'My Steel API Key',
  baseURL: process.env['TEST_API_BASE_URL'] ?? 'http://127.0.0.1:4010',
});

describe('resource extensions', () => {
  test('update', async () => {
    const responsePromise = client.extensions.update('extensionId', { url: 'https://example.com' });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('update: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(client.extensions.update('extensionId', { path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('update: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.extensions.update(
        'extensionId',
        { file: await toFile(Buffer.from('Example data'), 'README.md'), url: 'https://example.com' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('list', async () => {
    const responsePromise = client.extensions.list();
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
    await expect(client.extensions.list({ path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('delete', async () => {
    const responsePromise = client.extensions.delete('extensionId');
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
    await expect(client.extensions.delete('extensionId', { path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('deleteAll', async () => {
    const responsePromise = client.extensions.deleteAll();
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('deleteAll: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(client.extensions.deleteAll({ path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('download: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(client.extensions.download('extensionId', { path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('upload', async () => {
    const responsePromise = client.extensions.upload({ url: 'https://example.com' });
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('upload: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(client.extensions.upload({ path: '/_steel_unknown_path' })).rejects.toThrow(
      Steel.NotFoundError,
    );
  });

  test('upload: request options and params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.extensions.upload(
        { file: await toFile(Buffer.from('Example data'), 'README.md'), url: 'https://example.com' },
        { path: '/_steel_unknown_path' },
      ),
    ).rejects.toThrow(Steel.NotFoundError);
  });
});
