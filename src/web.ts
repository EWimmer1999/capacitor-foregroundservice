import { WebPlugin } from '@capacitor/core';

import type { ForegroundservicePlugin } from './definitions';

export class ForegroundserviceWeb extends WebPlugin implements ForegroundservicePlugin {
  

  async startService(): Promise<void> {
    console.log("Start!")
  }

  async stopService(): Promise<void> {
    console.log("Stop!")
  }
}
