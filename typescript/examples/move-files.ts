import Steel, { toFile } from 'steel-sdk';

const client = new Steel();

async function main() {
  const session = await client.sessions.create();

  try {
    const file = await toFile(Buffer.from('Hello from my machine\n'), 'hello.txt');
    const uploaded = await client.sessions.files.upload(session.id, { file, path: 'hello.txt' });
    console.log(`Uploaded ${uploaded.path} (${uploaded.size} bytes)`);

    const files = await client.sessions.files.list(session.id);
    console.log(files.data.map((entry) => entry.path));

    const download = await client.sessions.files.download(session.id, 'hello.txt');
    console.log(await download.text());
  } finally {
    await client.sessions.release(session.id);
  }
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
