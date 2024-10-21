import { WebPlugin } from '@capacitor/core';

import type { ForegroundservicePlugin } from './definitions';

export class ForegroundserviceWeb extends WebPlugin implements ForegroundservicePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
