import { chromium, type BrowserContext } from 'playwright-core';
import Steel from 'steel-sdk';

const client = new Steel();

async function inBrowser<T>(websocketUrl: string, use: (context: BrowserContext) => Promise<T>): Promise<T> {
  const browser = await chromium.connectOverCDP(`${websocketUrl}&apiKey=${client.steelAPIKey}`);
  try {
    return await use(browser.contexts()[0] ?? (await browser.newContext()));
  } finally {
    await browser.close();
  }
}

async function waitForProfile(profileId: string) {
  for (;;) {
    const profile = await client.profiles.get(profileId);
    if (profile.status === 'READY') {
      return;
    }
    if (profile.status === 'FAILED') {
      throw new Error(`Profile ${profileId} could not be saved`);
    }
    await new Promise((resolve) => setTimeout(resolve, 2000));
  }
}

async function main() {
  const first = await client.sessions.create({ persistProfile: true });
  const profileId = first.profileId;
  if (!profileId) {
    throw new Error('The session did not create a profile');
  }

  try {
    await inBrowser(first.websocketUrl, (context) =>
      context.addCookies([
        {
          name: 'greeting',
          value: 'saved by the first session',
          url: 'https://example.com',
          expires: Math.floor(Date.now() / 1000) + 24 * 60 * 60,
        },
      ]),
    );
  } finally {
    await client.sessions.release(first.id);
  }

  await waitForProfile(profileId);

  const second = await client.sessions.create({ profileId });
  try {
    const cookies = await inBrowser(second.websocketUrl, (context) => context.cookies('https://example.com'));
    const greeting = cookies.find((cookie) => cookie.name === 'greeting');
    console.log(`The second session sees: ${greeting?.value ?? 'nothing'}`);
  } finally {
    await client.sessions.release(second.id);
  }
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
