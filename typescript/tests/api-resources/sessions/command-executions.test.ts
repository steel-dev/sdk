// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

import Steel from 'steel-sdk';
import { Response } from 'node-fetch';

const client = new Steel({
  steelAPIKey: 'My Steel API Key',
  baseURL: process.env['TEST_API_BASE_URL'] ?? 'http://127.0.0.1:4010',
});

describe('resource commandExecutions', () => {
  test('retrieve', async () => {
    const responsePromise = client.sessions.commandExecutions.retrieve('sessionId', 'executionId');
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
      client.sessions.commandExecutions.retrieve('sessionId', 'executionId', {
        path: '/_steel_unknown_path',
      }),
    ).rejects.toThrow(Steel.NotFoundError);
  });

  test('cancel', async () => {
    const responsePromise = client.sessions.commandExecutions.cancel('sessionId', 'executionId');
    const rawResponse = await responsePromise.asResponse();
    expect(rawResponse).toBeInstanceOf(Response);
    const response = await responsePromise;
    expect(response).not.toBeInstanceOf(Response);
    const dataAndResponse = await responsePromise.withResponse();
    expect(dataAndResponse.data).toBe(response);
    expect(dataAndResponse.response).toBe(rawResponse);
  });

  test('cancel: request options instead of params are passed correctly', async () => {
    // ensure the request options are being passed correctly by passing an invalid HTTP method in order to cause an error
    await expect(
      client.sessions.commandExecutions.cancel('sessionId', 'executionId', { path: '/_steel_unknown_path' }),
    ).rejects.toThrow(Steel.NotFoundError);
  });
});
