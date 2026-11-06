import Steel from 'steel-sdk';

const client = new Steel();

async function main() {
  const session = await client.sessions.create();
  await client.sessions.release(session.id);

  const finished = await client.sessions.retrieve(session.id);
  console.log(`Session ${finished.id} is ${finished.status} after ${finished.duration} ms`);

  const logs = await client.sessions.logs(session.id);
  console.log(`${logs.total} log events`);
  for (const event of logs.events.slice(0, 10)) {
    console.log(`${event.timestamp} ${event.type}`);
  }

  const cost = await client.sessions.cost(session.id);
  console.log(`The session cost $${cost.totalCost}`);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
