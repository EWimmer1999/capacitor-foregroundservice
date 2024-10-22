export interface ForegroundservicePlugin {
  startService(): Promise<void>
  stopService(): Promise<void>
}
