export interface ForegroundservicePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
