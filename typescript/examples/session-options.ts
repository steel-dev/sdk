import Steel from 'steel-sdk';

const client = new Steel();

async function main() {
  const session = await client.sessions.create({
    useProxy: true,
    solveCaptcha: true,
    blockAds: true,
    region: 'us-east',
    timeout: 10 * 60 * 1000,
    dimensions: { width: 1440, height: 900 },
  });

  try {
    console.log(`Session ${session.id} runs in ${session.region} for up to ${session.timeout} ms`);
    console.log(`Proxy: ${session.proxySource}, captcha solving: ${session.solveCaptcha}`);
    console.log(`Connect Playwright or Puppeteer to ${session.websocketUrl}&apiKey=<your API key>`);
  } finally {
    await client.sessions.release(session.id);
  }
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
