import Steel, { toFile } from 'steel-sdk';

import { conform } from './harness';

const make = (baseURL: string, apiKey: string) => new Steel({ baseURL, steelAPIKey: apiKey, maxRetries: 0 });

describe('conformance', () => {
  test('pdf/full', () =>
    conform('pdf/full', make, async (client) =>
      client.pdf({
        url: 'https://example.com',
        delay: 0,
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        region: {},
        useProxy: true,
      }),
    ));

  test('pdf/required', () =>
    conform('pdf/required', make, async (client) => client.pdf({ url: 'https://example.com' })));

  test('pdf/nulls', () =>
    conform('pdf/nulls', make, async (client) => client.pdf({ url: 'https://example.com' })));

  test('scrape/full', () =>
    conform('scrape/full', make, async (client) =>
      client.scrape({
        url: 'url',
        delay: 0,
        format: ['html'],
        pdf: true,
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        region: {},
        screenshot: true,
        useProxy: true,
      }),
    ));

  test('scrape/required', () =>
    conform('scrape/required', make, async (client) => client.scrape({ url: 'url' })));

  test('scrape/nulls', () => conform('scrape/nulls', make, async (client) => client.scrape({ url: 'url' })));

  test('screenshot/full', () =>
    conform('screenshot/full', make, async (client) =>
      client.screenshot({
        url: 'https://example.com',
        delay: 0,
        fullPage: true,
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        region: {},
        useProxy: true,
      }),
    ));

  test('screenshot/required', () =>
    conform('screenshot/required', make, async (client) =>
      client.screenshot({ url: 'https://example.com' }),
    ));

  test('screenshot/nulls', () =>
    conform('screenshot/nulls', make, async (client) => client.screenshot({ url: 'https://example.com' })));

  test('api_keys.create/full', () =>
    conform('api_keys.create/full', make, async (client) =>
      client.apiKeys.create({
        name: 'name',
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        scopeType: 'project',
      }),
    ));

  test('api_keys.create/required', () =>
    conform('api_keys.create/required', make, async (client) => client.apiKeys.create({})));

  test('api_keys.create/nulls', () =>
    conform('api_keys.create/nulls', make, async (client) => client.apiKeys.create({})));

  test('api_keys.create/unknown', () =>
    conform('api_keys.create/unknown', make, async (client) => client.apiKeys.create({})));

  test('credentials.create/full', () =>
    conform('credentials.create/full', make, async (client) =>
      client.credentials.create({
        value: { foo: 'string' },
        label: 'label',
        namespace: 'namespace',
        origin: 'origin',
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
      }),
    ));

  test('credentials.create/required', () =>
    conform('credentials.create/required', make, async (client) =>
      client.credentials.create({ value: { foo: 'string' } }),
    ));

  test('credentials.create/nulls', () =>
    conform('credentials.create/nulls', make, async (client) =>
      client.credentials.create({ value: { foo: 'string' } }),
    ));

  test('credentials.update/full', () =>
    conform('credentials.update/full', make, async (client) =>
      client.credentials.update({
        label: 'label',
        namespace: 'namespace',
        origin: 'origin',
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        value: { foo: 'string' },
      }),
    ));

  test('credentials.update/required', () =>
    conform('credentials.update/required', make, async (client) => client.credentials.update({})));

  test('credentials.update/nulls', () =>
    conform('credentials.update/nulls', make, async (client) => client.credentials.update({})));

  test('credentials.list/full', () =>
    conform('credentials.list/full', make, async (client) =>
      client.credentials.list({
        namespace: 'namespace',
        origin: 'origin',
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
      }),
    ));

  test('credentials.list/required', () =>
    conform('credentials.list/required', make, async (client) => client.credentials.list({})));

  test('credentials.list/nulls', () =>
    conform('credentials.list/nulls', make, async (client) => client.credentials.list({})));

  test('credentials.list/escaped', () =>
    conform('credentials.list/escaped', make, async (client) =>
      client.credentials.list({
        namespace: 'a b/c?d#e%f+g&h=i é',
        origin: 'a b/c?d#e%f+g&h=i é',
        projectId: 'a b/c?d#e%f+g&h=i é',
      }),
    ));

  test('credentials.delete/full', () =>
    conform('credentials.delete/full', make, async (client) =>
      client.credentials.delete({
        origin: 'origin',
        namespace: 'namespace',
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
      }),
    ));

  test('credentials.delete/required', () =>
    conform('credentials.delete/required', make, async (client) =>
      client.credentials.delete({ origin: 'origin' }),
    ));

  test('credentials.delete/nulls', () =>
    conform('credentials.delete/nulls', make, async (client) =>
      client.credentials.delete({ origin: 'origin' }),
    ));

  test('extensions.update/full', () =>
    conform('extensions.update/full', make, async (client) =>
      client.extensions.update('extensionId', {
        file: await toFile(Buffer.from('Example data'), 'README.md'),
        url: 'https://example.com',
      }),
    ));

  test('extensions.update/required', () =>
    conform('extensions.update/required', make, async (client) =>
      client.extensions.update('extensionId', {}),
    ));

  test('extensions.update/nulls', () =>
    conform('extensions.update/nulls', make, async (client) => client.extensions.update('extensionId', {})));

  test('extensions.update/escaped', () =>
    conform('extensions.update/escaped', make, async (client) =>
      client.extensions.update('a b/c?d#e%f+g&h=i é', {}),
    ));

  test('extensions.list/full', () =>
    conform('extensions.list/full', make, async (client) => client.extensions.list()));

  test('extensions.list/required', () =>
    conform('extensions.list/required', make, async (client) => client.extensions.list()));

  test('extensions.list/nulls', () =>
    conform('extensions.list/nulls', make, async (client) => client.extensions.list()));

  test('extensions.delete/full', () =>
    conform('extensions.delete/full', make, async (client) => client.extensions.delete('extensionId')));

  test('extensions.delete/required', () =>
    conform('extensions.delete/required', make, async (client) => client.extensions.delete('extensionId')));

  test('extensions.delete/nulls', () =>
    conform('extensions.delete/nulls', make, async (client) => client.extensions.delete('extensionId')));

  test('extensions.delete/escaped', () =>
    conform('extensions.delete/escaped', make, async (client) =>
      client.extensions.delete('a b/c?d#e%f+g&h=i é'),
    ));

  test('extensions.delete_all/full', () =>
    conform('extensions.delete_all/full', make, async (client) => client.extensions.deleteAll()));

  test('extensions.delete_all/required', () =>
    conform('extensions.delete_all/required', make, async (client) => client.extensions.deleteAll()));

  test('extensions.delete_all/nulls', () =>
    conform('extensions.delete_all/nulls', make, async (client) => client.extensions.deleteAll()));

  test('extensions.download/full', () =>
    conform('extensions.download/full', make, async (client) =>
      (await client.extensions.download('extensionId')).text(),
    ));

  test('extensions.download/required', () =>
    conform('extensions.download/required', make, async (client) =>
      (await client.extensions.download('extensionId')).text(),
    ));

  test('extensions.download/escaped', () =>
    conform('extensions.download/escaped', make, async (client) =>
      (await client.extensions.download('a b/c?d#e%f+g&h=i é')).text(),
    ));

  test('extensions.upload/full', () =>
    conform('extensions.upload/full', make, async (client) =>
      client.extensions.upload({
        file: await toFile(Buffer.from('Example data'), 'README.md'),
        url: 'https://example.com',
      }),
    ));

  test('extensions.upload/required', () =>
    conform('extensions.upload/required', make, async (client) => client.extensions.upload({})));

  test('extensions.upload/nulls', () =>
    conform('extensions.upload/nulls', make, async (client) => client.extensions.upload({})));

  test('files.list/full', () => conform('files.list/full', make, async (client) => client.files.list()));

  test('files.list/required', () =>
    conform('files.list/required', make, async (client) => client.files.list()));

  test('files.list/nulls', () => conform('files.list/nulls', make, async (client) => client.files.list()));

  test('files.delete/full', () =>
    conform('files.delete/full', make, async (client) => client.files.delete('path')));

  test('files.delete/required', () =>
    conform('files.delete/required', make, async (client) => client.files.delete('path')));

  test('files.delete/escaped', () =>
    conform('files.delete/escaped', make, async (client) => client.files.delete('a b/c?d#e%f+g&h=i é')));

  test('files.download/full', () =>
    conform('files.download/full', make, async (client) => (await client.files.download('path')).text()));

  test('files.download/required', () =>
    conform('files.download/required', make, async (client) => (await client.files.download('path')).text()));

  test('files.download/escaped', () =>
    conform('files.download/escaped', make, async (client) =>
      (await client.files.download('a b/c?d#e%f+g&h=i é')).text(),
    ));

  test('files.upload/full', () =>
    conform('files.upload/full', make, async (client) =>
      client.files.upload({ file: await toFile(Buffer.from('Example data'), 'README.md'), path: 'path' }),
    ));

  test('files.upload/required', () =>
    conform('files.upload/required', make, async (client) =>
      client.files.upload({ file: await toFile(Buffer.from('Example data'), 'README.md') }),
    ));

  test('files.upload/nulls', () =>
    conform('files.upload/nulls', make, async (client) =>
      client.files.upload({ file: await toFile(Buffer.from('Example data'), 'README.md') }),
    ));

  test('profiles.create/full', () =>
    conform('profiles.create/full', make, async (client) =>
      client.profiles.create({
        userDataDir: await toFile(Buffer.from('Example data'), 'README.md'),
        dimensions: { height: 0, width: 0 },
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        proxyUrl: 'https://example.com',
        useProxy: 'useProxy',
        userAgent: 'userAgent',
      }),
    ));

  test('profiles.create/required', () =>
    conform('profiles.create/required', make, async (client) =>
      client.profiles.create({ userDataDir: await toFile(Buffer.from('Example data'), 'README.md') }),
    ));

  test('profiles.create/nulls', () =>
    conform('profiles.create/nulls', make, async (client) =>
      client.profiles.create({ userDataDir: await toFile(Buffer.from('Example data'), 'README.md') }),
    ));

  test('profiles.create/unknown', () =>
    conform('profiles.create/unknown', make, async (client) =>
      client.profiles.create({ userDataDir: await toFile(Buffer.from('Example data'), 'README.md') }),
    ));

  test('profiles.update/full', () =>
    conform('profiles.update/full', make, async (client) =>
      client.profiles.update('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        query_projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        userDataDir: await toFile(Buffer.from('Example data'), 'README.md'),
        dimensions: { height: 0, width: 0 },
        body_projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
        proxyUrl: 'https://example.com',
        useProxy: 'useProxy',
        userAgent: 'userAgent',
      }),
    ));

  test('profiles.update/required', () =>
    conform('profiles.update/required', make, async (client) =>
      client.profiles.update('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        userDataDir: await toFile(Buffer.from('Example data'), 'README.md'),
      }),
    ));

  test('profiles.update/nulls', () =>
    conform('profiles.update/nulls', make, async (client) =>
      client.profiles.update('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        userDataDir: await toFile(Buffer.from('Example data'), 'README.md'),
      }),
    ));

  test('profiles.update/unknown', () =>
    conform('profiles.update/unknown', make, async (client) =>
      client.profiles.update('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        userDataDir: await toFile(Buffer.from('Example data'), 'README.md'),
      }),
    ));

  test('profiles.update/escaped', () =>
    conform('profiles.update/escaped', make, async (client) =>
      client.profiles.update('a b/c?d#e%f+g&h=i é', {
        query_projectId: 'a b/c?d#e%f+g&h=i é',
        userDataDir: await toFile(Buffer.from('Example data'), 'README.md'),
      }),
    ));

  test('profiles.list/full', () =>
    conform('profiles.list/full', make, async (client) =>
      client.profiles.list({ projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' }),
    ));

  test('profiles.list/required', () =>
    conform('profiles.list/required', make, async (client) => client.profiles.list({})));

  test('profiles.list/nulls', () =>
    conform('profiles.list/nulls', make, async (client) => client.profiles.list({})));

  test('profiles.list/unknown', () =>
    conform('profiles.list/unknown', make, async (client) => client.profiles.list({})));

  test('profiles.list/escaped', () =>
    conform('profiles.list/escaped', make, async (client) =>
      client.profiles.list({ projectId: 'a b/c?d#e%f+g&h=i é' }),
    ));

  test('profiles.get/full', () =>
    conform('profiles.get/full', make, async (client) =>
      client.profiles.get('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
      }),
    ));

  test('profiles.get/required', () =>
    conform('profiles.get/required', make, async (client) =>
      client.profiles.get('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('profiles.get/nulls', () =>
    conform('profiles.get/nulls', make, async (client) =>
      client.profiles.get('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('profiles.get/unknown', () =>
    conform('profiles.get/unknown', make, async (client) =>
      client.profiles.get('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('profiles.get/escaped', () =>
    conform('profiles.get/escaped', make, async (client) =>
      client.profiles.get('a b/c?d#e%f+g&h=i é', { projectId: 'a b/c?d#e%f+g&h=i é' }),
    ));

  test('projects.retrieve/full', () =>
    conform('projects.retrieve/full', make, async (client) =>
      client.projects.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('projects.retrieve/required', () =>
    conform('projects.retrieve/required', make, async (client) =>
      client.projects.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('projects.retrieve/nulls', () =>
    conform('projects.retrieve/nulls', make, async (client) =>
      client.projects.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('projects.retrieve/escaped', () =>
    conform('projects.retrieve/escaped', make, async (client) =>
      client.projects.retrieve('a b/c?d#e%f+g&h=i é'),
    ));

  test('projects.list/full', () =>
    conform('projects.list/full', make, async (client) => client.projects.list()));

  test('projects.list/required', () =>
    conform('projects.list/required', make, async (client) => client.projects.list()));

  test('projects.list/nulls', () =>
    conform('projects.list/nulls', make, async (client) => client.projects.list()));

  test('sessions.create/full', () =>
    conform('sessions.create/full', make, async (client) =>
      client.sessions.create({
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
      }),
    ));

  test('sessions.create/required', () =>
    conform('sessions.create/required', make, async (client) => client.sessions.create({})));

  test('sessions.create/nulls', () =>
    conform('sessions.create/nulls', make, async (client) => client.sessions.create({})));

  test('sessions.create/unknown', () =>
    conform('sessions.create/unknown', make, async (client) => client.sessions.create({})));

  test('sessions.retrieve/full', () =>
    conform('sessions.retrieve/full', make, async (client) =>
      client.sessions.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.retrieve/required', () =>
    conform('sessions.retrieve/required', make, async (client) =>
      client.sessions.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.retrieve/nulls', () =>
    conform('sessions.retrieve/nulls', make, async (client) =>
      client.sessions.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.retrieve/unknown', () =>
    conform('sessions.retrieve/unknown', make, async (client) =>
      client.sessions.retrieve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.retrieve/escaped', () =>
    conform('sessions.retrieve/escaped', make, async (client) =>
      client.sessions.retrieve('a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.list/full', () =>
    conform('sessions.list/full', make, async (client) =>
      (
        await client.sessions.list({
          createdAfter: '2019-12-27T18:11:19.117Z',
          createdBefore: '2019-12-27T18:11:19.117Z',
          cursorId: 'cursorId',
          limit: 1,
          metadata: { foo: 'x' },
          projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e',
          releaseReason: 'user_requested',
          signal: ['captcha_encountered'],
          status: 'live',
        })
      ).getPaginatedItems(),
    ));

  test('sessions.list/required', () =>
    conform('sessions.list/required', make, async (client) =>
      (await client.sessions.list({})).getPaginatedItems(),
    ));

  test('sessions.list/nulls', () =>
    conform('sessions.list/nulls', make, async (client) =>
      (await client.sessions.list({})).getPaginatedItems(),
    ));

  test('sessions.list/unknown', () =>
    conform('sessions.list/unknown', make, async (client) =>
      (await client.sessions.list({})).getPaginatedItems(),
    ));

  test('sessions.list/escaped', () =>
    conform('sessions.list/escaped', make, async (client) =>
      (
        await client.sessions.list({ cursorId: 'a b/c?d#e%f+g&h=i é', projectId: 'a b/c?d#e%f+g&h=i é' })
      ).getPaginatedItems(),
    ));

  test('sessions.list/pages', () =>
    conform('sessions.list/pages', make, async (client) => {
      const ids: string[] = [];
      for await (const item of client.sessions.list({})) {
        ids.push(String(item.id));
      }
      return ids;
    }));

  test('sessions.agent_traces/full', () =>
    conform('sessions.agent_traces/full', make, async (client) =>
      client.sessions.agentTraces('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        endTime: '2019-12-27T18:11:19.117Z',
        eventTypes: ['string'],
        limit: 1,
        namespace: 'namespace',
        offset: 0,
        startTime: '2019-12-27T18:11:19.117Z',
      }),
    ));

  test('sessions.agent_traces/required', () =>
    conform('sessions.agent_traces/required', make, async (client) =>
      client.sessions.agentTraces('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.agent_traces/nulls', () =>
    conform('sessions.agent_traces/nulls', make, async (client) =>
      client.sessions.agentTraces('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.agent_traces/escaped', () =>
    conform('sessions.agent_traces/escaped', make, async (client) =>
      client.sessions.agentTraces('a b/c?d#e%f+g&h=i é', { namespace: 'a b/c?d#e%f+g&h=i é' }),
    ));

  test('sessions.commands/full', () =>
    conform('sessions.commands/full', make, async (client) =>
      client.sessions.commands('sessionId', {
        'idempotency-key': 'idempotency-key',
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
      }),
    ));

  test('sessions.commands/required', () =>
    conform('sessions.commands/required', make, async (client) =>
      client.sessions.commands('sessionId', { commands: { command: 'navigate', url: 'x' } }),
    ));

  test('sessions.commands/nulls', () =>
    conform('sessions.commands/nulls', make, async (client) =>
      client.sessions.commands('sessionId', { commands: { command: 'navigate', url: 'x' } }),
    ));

  test('sessions.commands/unknown', () =>
    conform('sessions.commands/unknown', make, async (client) =>
      client.sessions.commands('sessionId', { commands: { command: 'navigate', url: 'x' } }),
    ));

  test('sessions.commands/variant-0-1', () =>
    conform('sessions.commands/variant-0-1', make, async (client) =>
      client.sessions.commands('sessionId', { commands: { command: 'navigate', url: 'x' } }),
    ));

  test('sessions.commands/escaped', () =>
    conform('sessions.commands/escaped', make, async (client) =>
      client.sessions.commands('a b/c?d#e%f+g&h=i é', { commands: { command: 'navigate', url: 'x' } }),
    ));

  test('sessions.computer/full', () =>
    conform('sessions.computer/full', make, async (client) =>
      client.sessions.computer('sessionId', {
        action: 'move_mouse',
        coordinates: [0, 0],
        hold_keys: ['string'],
        screenshot: true,
      }),
    ));

  test('sessions.computer/required', () =>
    conform('sessions.computer/required', make, async (client) =>
      client.sessions.computer('sessionId', { action: 'move_mouse', coordinates: [0, 0] }),
    ));

  test('sessions.computer/nulls', () =>
    conform('sessions.computer/nulls', make, async (client) =>
      client.sessions.computer('sessionId', { action: 'move_mouse', coordinates: [0, 0] }),
    ));

  test('sessions.computer/escaped', () =>
    conform('sessions.computer/escaped', make, async (client) =>
      client.sessions.computer('a b/c?d#e%f+g&h=i é', { action: 'move_mouse', coordinates: [0, 0] }),
    ));

  test('sessions.context/full', () =>
    conform('sessions.context/full', make, async (client) =>
      client.sessions.context('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.context/required', () =>
    conform('sessions.context/required', make, async (client) =>
      client.sessions.context('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.context/nulls', () =>
    conform('sessions.context/nulls', make, async (client) =>
      client.sessions.context('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.context/unknown', () =>
    conform('sessions.context/unknown', make, async (client) =>
      client.sessions.context('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.context/escaped', () =>
    conform('sessions.context/escaped', make, async (client) =>
      client.sessions.context('a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.cost/full', () =>
    conform('sessions.cost/full', make, async (client) =>
      client.sessions.cost('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.cost/required', () =>
    conform('sessions.cost/required', make, async (client) =>
      client.sessions.cost('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.cost/nulls', () =>
    conform('sessions.cost/nulls', make, async (client) =>
      client.sessions.cost('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.cost/unknown', () =>
    conform('sessions.cost/unknown', make, async (client) =>
      client.sessions.cost('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.cost/escaped', () =>
    conform('sessions.cost/escaped', make, async (client) => client.sessions.cost('a b/c?d#e%f+g&h=i é')));

  test('sessions.events/full', () =>
    conform('sessions.events/full', make, async (client) =>
      client.sessions.events('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        compressed: true,
        limit: 1,
        pointer: 'pointer',
      }),
    ));

  test('sessions.events/required', () =>
    conform('sessions.events/required', make, async (client) =>
      client.sessions.events('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.events/nulls', () =>
    conform('sessions.events/nulls', make, async (client) =>
      client.sessions.events('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.events/escaped', () =>
    conform('sessions.events/escaped', make, async (client) =>
      client.sessions.events('a b/c?d#e%f+g&h=i é', { pointer: 'a b/c?d#e%f+g&h=i é' }),
    ));

  test('sessions.hls/full', () =>
    conform('sessions.hls/full', make, async (client) =>
      (await client.sessions.hls('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e')).text(),
    ));

  test('sessions.hls/required', () =>
    conform('sessions.hls/required', make, async (client) =>
      (await client.sessions.hls('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e')).text(),
    ));

  test('sessions.hls/escaped', () =>
    conform('sessions.hls/escaped', make, async (client) =>
      (await client.sessions.hls('a b/c?d#e%f+g&h=i é')).text(),
    ));

  test('sessions.live_details/full', () =>
    conform('sessions.live_details/full', make, async (client) =>
      client.sessions.liveDetails('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.live_details/required', () =>
    conform('sessions.live_details/required', make, async (client) =>
      client.sessions.liveDetails('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.live_details/nulls', () =>
    conform('sessions.live_details/nulls', make, async (client) =>
      client.sessions.liveDetails('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.live_details/escaped', () =>
    conform('sessions.live_details/escaped', make, async (client) =>
      client.sessions.liveDetails('a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.logs/full', () =>
    conform('sessions.logs/full', make, async (client) =>
      client.sessions.logs('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        endTime: '2019-12-27T18:11:19.117Z',
        eventTypes: ['string'],
        limit: 1,
        namespace: 'namespace',
        offset: 0,
        startTime: '2019-12-27T18:11:19.117Z',
      }),
    ));

  test('sessions.logs/required', () =>
    conform('sessions.logs/required', make, async (client) =>
      client.sessions.logs('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.logs/nulls', () =>
    conform('sessions.logs/nulls', make, async (client) =>
      client.sessions.logs('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.logs/escaped', () =>
    conform('sessions.logs/escaped', make, async (client) =>
      client.sessions.logs('a b/c?d#e%f+g&h=i é', { namespace: 'a b/c?d#e%f+g&h=i é' }),
    ));

  test('sessions.release/full', () =>
    conform('sessions.release/full', make, async (client) =>
      client.sessions.release('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.release/required', () =>
    conform('sessions.release/required', make, async (client) =>
      client.sessions.release('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.release/nulls', () =>
    conform('sessions.release/nulls', make, async (client) =>
      client.sessions.release('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.release/escaped', () =>
    conform('sessions.release/escaped', make, async (client) =>
      client.sessions.release('a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.release_all/full', () =>
    conform('sessions.release_all/full', make, async (client) =>
      client.sessions.releaseAll({ projectId: '182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e' }),
    ));

  test('sessions.release_all/required', () =>
    conform('sessions.release_all/required', make, async (client) => client.sessions.releaseAll({})));

  test('sessions.release_all/nulls', () =>
    conform('sessions.release_all/nulls', make, async (client) => client.sessions.releaseAll({})));

  test('sessions.release_all/escaped', () =>
    conform('sessions.release_all/escaped', make, async (client) =>
      client.sessions.releaseAll({ projectId: 'a b/c?d#e%f+g&h=i é' }),
    ));

  test('sessions.replace_metadata/full', () =>
    conform('sessions.replace_metadata/full', make, async (client) =>
      client.sessions.replaceMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        metadata: { foo: 'string' },
      }),
    ));

  test('sessions.replace_metadata/required', () =>
    conform('sessions.replace_metadata/required', make, async (client) =>
      client.sessions.replaceMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        metadata: { foo: 'string' },
      }),
    ));

  test('sessions.replace_metadata/nulls', () =>
    conform('sessions.replace_metadata/nulls', make, async (client) =>
      client.sessions.replaceMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        metadata: { foo: 'string' },
      }),
    ));

  test('sessions.replace_metadata/escaped', () =>
    conform('sessions.replace_metadata/escaped', make, async (client) =>
      client.sessions.replaceMetadata('a b/c?d#e%f+g&h=i é', { metadata: { foo: 'string' } }),
    ));

  test('sessions.signal_catalog/full', () =>
    conform('sessions.signal_catalog/full', make, async (client) => client.sessions.signalCatalog()));

  test('sessions.signal_catalog/required', () =>
    conform('sessions.signal_catalog/required', make, async (client) => client.sessions.signalCatalog()));

  test('sessions.signal_catalog/nulls', () =>
    conform('sessions.signal_catalog/nulls', make, async (client) => client.sessions.signalCatalog()));

  test('sessions.signal_catalog/unknown', () =>
    conform('sessions.signal_catalog/unknown', make, async (client) => client.sessions.signalCatalog()));

  test('sessions.update_metadata/full', () =>
    conform('sessions.update_metadata/full', make, async (client) =>
      client.sessions.updateMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { metadata: { foo: 'string' } }),
    ));

  test('sessions.update_metadata/required', () =>
    conform('sessions.update_metadata/required', make, async (client) =>
      client.sessions.updateMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { metadata: { foo: 'string' } }),
    ));

  test('sessions.update_metadata/nulls', () =>
    conform('sessions.update_metadata/nulls', make, async (client) =>
      client.sessions.updateMetadata('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', { metadata: { foo: 'string' } }),
    ));

  test('sessions.update_metadata/escaped', () =>
    conform('sessions.update_metadata/escaped', make, async (client) =>
      client.sessions.updateMetadata('a b/c?d#e%f+g&h=i é', { metadata: { foo: 'string' } }),
    ));

  test('sessions.captchas.solve/full', () =>
    conform('sessions.captchas.solve/full', make, async (client) =>
      client.sessions.captchas.solve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        pageId: 'pageId',
        taskId: 'taskId',
        url: 'url',
      }),
    ));

  test('sessions.captchas.solve/required', () =>
    conform('sessions.captchas.solve/required', make, async (client) =>
      client.sessions.captchas.solve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.captchas.solve/nulls', () =>
    conform('sessions.captchas.solve/nulls', make, async (client) =>
      client.sessions.captchas.solve('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {}),
    ));

  test('sessions.captchas.solve/escaped', () =>
    conform('sessions.captchas.solve/escaped', make, async (client) =>
      client.sessions.captchas.solve('a b/c?d#e%f+g&h=i é', {}),
    ));

  test('sessions.captchas.solve_image/full', () =>
    conform('sessions.captchas.solve_image/full', make, async (client) =>
      client.sessions.captchas.solveImage('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        imageXPath: 'imageXPath',
        inputXPath: 'inputXPath',
        url: 'url',
      }),
    ));

  test('sessions.captchas.solve_image/required', () =>
    conform('sessions.captchas.solve_image/required', make, async (client) =>
      client.sessions.captchas.solveImage('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        imageXPath: 'imageXPath',
        inputXPath: 'inputXPath',
      }),
    ));

  test('sessions.captchas.solve_image/nulls', () =>
    conform('sessions.captchas.solve_image/nulls', make, async (client) =>
      client.sessions.captchas.solveImage('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e', {
        imageXPath: 'imageXPath',
        inputXPath: 'inputXPath',
      }),
    ));

  test('sessions.captchas.solve_image/escaped', () =>
    conform('sessions.captchas.solve_image/escaped', make, async (client) =>
      client.sessions.captchas.solveImage('a b/c?d#e%f+g&h=i é', {
        imageXPath: 'imageXPath',
        inputXPath: 'inputXPath',
      }),
    ));

  test('sessions.captchas.status/full', () =>
    conform('sessions.captchas.status/full', make, async (client) =>
      client.sessions.captchas.status('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.captchas.status/required', () =>
    conform('sessions.captchas.status/required', make, async (client) =>
      client.sessions.captchas.status('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.captchas.status/nulls', () =>
    conform('sessions.captchas.status/nulls', make, async (client) =>
      client.sessions.captchas.status('182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e'),
    ));

  test('sessions.captchas.status/escaped', () =>
    conform('sessions.captchas.status/escaped', make, async (client) =>
      client.sessions.captchas.status('a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.files.list/full', () =>
    conform('sessions.files.list/full', make, async (client) => client.sessions.files.list('sessionId')));

  test('sessions.files.list/required', () =>
    conform('sessions.files.list/required', make, async (client) => client.sessions.files.list('sessionId')));

  test('sessions.files.list/nulls', () =>
    conform('sessions.files.list/nulls', make, async (client) => client.sessions.files.list('sessionId')));

  test('sessions.files.list/escaped', () =>
    conform('sessions.files.list/escaped', make, async (client) =>
      client.sessions.files.list('a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.files.delete/full', () =>
    conform('sessions.files.delete/full', make, async (client) =>
      client.sessions.files.delete('sessionId', 'path'),
    ));

  test('sessions.files.delete/required', () =>
    conform('sessions.files.delete/required', make, async (client) =>
      client.sessions.files.delete('sessionId', 'path'),
    ));

  test('sessions.files.delete/escaped', () =>
    conform('sessions.files.delete/escaped', make, async (client) =>
      client.sessions.files.delete('a b/c?d#e%f+g&h=i é', 'a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.files.delete_all/full', () =>
    conform('sessions.files.delete_all/full', make, async (client) =>
      client.sessions.files.deleteAll('sessionId'),
    ));

  test('sessions.files.delete_all/required', () =>
    conform('sessions.files.delete_all/required', make, async (client) =>
      client.sessions.files.deleteAll('sessionId'),
    ));

  test('sessions.files.delete_all/escaped', () =>
    conform('sessions.files.delete_all/escaped', make, async (client) =>
      client.sessions.files.deleteAll('a b/c?d#e%f+g&h=i é'),
    ));

  test('sessions.files.download/full', () =>
    conform('sessions.files.download/full', make, async (client) =>
      (await client.sessions.files.download('sessionId', 'path')).text(),
    ));

  test('sessions.files.download/required', () =>
    conform('sessions.files.download/required', make, async (client) =>
      (await client.sessions.files.download('sessionId', 'path')).text(),
    ));

  test('sessions.files.download/escaped', () =>
    conform('sessions.files.download/escaped', make, async (client) =>
      (await client.sessions.files.download('a b/c?d#e%f+g&h=i é', 'a b/c?d#e%f+g&h=i é')).text(),
    ));

  test('sessions.files.download_archive/full', () =>
    conform('sessions.files.download_archive/full', make, async (client) =>
      (await client.sessions.files.downloadArchive('sessionId')).text(),
    ));

  test('sessions.files.download_archive/required', () =>
    conform('sessions.files.download_archive/required', make, async (client) =>
      (await client.sessions.files.downloadArchive('sessionId')).text(),
    ));

  test('sessions.files.download_archive/escaped', () =>
    conform('sessions.files.download_archive/escaped', make, async (client) =>
      (await client.sessions.files.downloadArchive('a b/c?d#e%f+g&h=i é')).text(),
    ));

  test('sessions.files.upload/full', () =>
    conform('sessions.files.upload/full', make, async (client) =>
      client.sessions.files.upload('sessionId', {
        file: await toFile(Buffer.from('Example data'), 'README.md'),
        path: 'path',
      }),
    ));

  test('sessions.files.upload/required', () =>
    conform('sessions.files.upload/required', make, async (client) =>
      client.sessions.files.upload('sessionId', {
        file: await toFile(Buffer.from('Example data'), 'README.md'),
      }),
    ));

  test('sessions.files.upload/nulls', () =>
    conform('sessions.files.upload/nulls', make, async (client) =>
      client.sessions.files.upload('sessionId', {
        file: await toFile(Buffer.from('Example data'), 'README.md'),
      }),
    ));

  test('sessions.files.upload/escaped', () =>
    conform('sessions.files.upload/escaped', make, async (client) =>
      client.sessions.files.upload('a b/c?d#e%f+g&h=i é', {
        file: await toFile(Buffer.from('Example data'), 'README.md'),
      }),
    ));
});
