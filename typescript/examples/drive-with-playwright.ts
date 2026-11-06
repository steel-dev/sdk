import { chromium } from 'playwright-core';
import Steel from 'steel-sdk';

const client = new Steel();

async function main() {
  const session = await client.sessions.create();
  console.log(`Watch the session live at ${session.sessionViewerUrl}`);

  try {
    const browser = await chromium.connectOverCDP(`${session.websocketUrl}&apiKey=${client.steelAPIKey}`);
    const context = browser.contexts()[0] ?? (await browser.newContext());
    const page = context.pages()[0] ?? (await context.newPage());

    await page.goto('https://news.ycombinator.com');
    const titles = await page.locator('.titleline > a').allTextContents();
    console.log(titles.slice(0, 5).join('\n'));

    await browser.close();
  } finally {
    await client.sessions.release(session.id);
  }
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
