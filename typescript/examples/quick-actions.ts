import Steel from 'steel-sdk';

const client = new Steel();

async function main() {
  const url = 'https://steel.dev';

  const page = await client.scrape({ url, format: ['markdown'] });
  console.log(page.content.markdown?.slice(0, 500));

  const screenshot = await client.screenshot({ url, fullPage: true });
  console.log(`Screenshot: ${screenshot.url}`);

  const pdf = await client.pdf({ url });
  console.log(`PDF: ${pdf.url}`);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
