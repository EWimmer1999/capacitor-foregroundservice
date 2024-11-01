# capacitor-foregroundservice

This is a plugin to enable a foreground service for apps.

## Install

```bash
npm install capacitor-foregroundservice
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`startService(...)`](#startservice)
* [`stopService()`](#stopservice)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### startService(...)

```typescript
startService(options: StartServiceOptions) => Promise<{ title: string; }>
```

| Param         | Type                                                                |
| ------------- | ------------------------------------------------------------------- |
| **`options`** | <code><a href="#startserviceoptions">StartServiceOptions</a></code> |

**Returns:** <code>Promise&lt;{ title: string; }&gt;</code>

--------------------


### stopService()

```typescript
stopService() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Interfaces


#### StartServiceOptions

| Prop                 | Type                |
| -------------------- | ------------------- |
| **`title`**          | <code>string</code> |
| **`description`**    | <code>string</code> |
| **`icon`**           | <code>string</code> |
| **`importance`**     | <code>number</code> |
| **`notificationId`** | <code>number</code> |

</docgen-api>
