import type { CDPSession as PlaywrightSession } from 'playwright-core';
import type { CDPSession as PuppeteerSession } from 'puppeteer-core';
import { wrapCDPSession } from 'steel-sdk/cdp';

// Compiled by tsc, not executed. Verify both native and Steel signatures.
async function checkTypes(playwright: PlaywrightSession, puppeteer: PuppeteerSession) {
  const pw = wrapCDPSession(playwright);
  const pp = wrapCDPSession(puppeteer);
  const product: string = (await pw.send('Browser.getVersion')).product;
  const otherProduct: string = (await pp.send('Browser.getVersion')).product;
  const injected: true = (await pp.steel.injectCredential({ selector: '#login', path: 'username' })).injected;
  // @ts-expect-error A field path is required; raw credential values are not accepted.
  await pw.steel.injectCredential({ selector: '#login', credential: 'value' });
  // @ts-expect-error Frame and document must be pinned together.
  await pp.steel.fill({ frameId: 1 });
  // @ts-expect-error Selection is by ID or reference, not both.
  await pw.steel.fill({ credentialId: 'id', reference: 'steel://items/id' });
  // @ts-expect-error Puppeteer's native send remains strictly typed.
  await pp.send('Steel.credentials.detect');
  return [product, otherProduct, injected];
}
void checkTypes;

test('forwards Steel and native commands on the original session', async () => {
  class Session {
    #send = jest.fn().mockResolvedValue({});
    send(...args: unknown[]) {
      return this.#send(...args);
    }
    get calls() {
      return this.#send.mock.calls;
    }
    on() {
      return this;
    }
    async detach() {
      await this.send('detach');
    }
  }
  const native = new Session();
  const cdp = wrapCDPSession(native);
  await cdp.steel.detect({ checkCredentials: true });
  await cdp.steel.fill();
  await cdp.steel.injectCredential({ selector: '#login', path: 'username' });
  await cdp.steel.getControls();
  await cdp.steel.setControls({ autoSubmit: false });
  await cdp.send('Browser.getVersion');
  expect(cdp.on()).toBe(cdp);
  await cdp.detach();
  expect(native.calls).toEqual([
    ['Steel.credentials.detect', { checkCredentials: true }],
    ['Steel.credentials.fill'],
    ['Steel.injectCredential', { selector: '#login', path: 'username' }],
    ['Steel.credentials.getControls'],
    ['Steel.credentials.setControls', { autoSubmit: false }],
    ['Browser.getVersion'],
    ['detach'],
  ]);
  expect(native).not.toHaveProperty('steel');
});

test('passes through browser errors without retrying', async () => {
  const failure = new Error('CDP rejected the command');
  const send = jest.fn().mockRejectedValue(failure);
  await expect(wrapCDPSession({ send }).steel.fill()).rejects.toBe(failure);
  expect(send).toHaveBeenCalledTimes(1);
});
