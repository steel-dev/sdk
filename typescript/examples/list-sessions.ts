import Steel from 'steel-sdk';

const client = new Steel();

async function main() {
  for await (const session of client.sessions.list({ status: 'live' })) {
    console.log(`${session.id} started at ${session.createdAt}`);
  }
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
