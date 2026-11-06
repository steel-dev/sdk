import puppeteer from 'puppeteer-core';
import Steel from 'steel-sdk';

const client = new Steel();

async function main() {
  const session = await client.sessions.create();
  console.log(`Watch the session live at ${session.sessionViewerUrl}`);

  try {
    const browser = await puppeteer.connect({
      browserWSEndpoint: `${session.websocketUrl}&apiKey=${client.steelAPIKey}`,
    });
    const page = (await browser.pages())[0] ?? (await browser.newPage());

    await page.goto('https://example.com');
    console.log(await page.title());

    await browser.disconnect();
  } finally {
    await client.sessions.release(session.id);
  }
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
