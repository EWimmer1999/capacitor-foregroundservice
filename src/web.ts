import { WebPlugin } from '@capacitor/core';

import type { ForegroundservicePlugin, StartServiceOptions } from './definitions';

export class ForegroundserviceWeb extends WebPlugin implements ForegroundservicePlugin {
  async startService(options: StartServiceOptions): Promise<{ title: string }> {
    return options;
  }
  async stopService(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
