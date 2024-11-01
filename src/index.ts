import { registerPlugin } from '@capacitor/core';
import type { ForegroundservicePlugin } from './definitions';

const Foregroundservice = registerPlugin<ForegroundservicePlugin>('Foregroundservice', {
  web: () => import('./web').then((m) => new m.ForegroundserviceWeb()),
});

export * from './definitions';
export { Foregroundservice }; // Instanz des Plugins
