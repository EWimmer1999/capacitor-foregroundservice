package io.capacitor.plugin.foregroundservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.Manifest;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.PermissionState;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(
        name = "Foregroundservice",
        permissions = {
                @Permission(
                        strings = {Manifest.permission.FOREGROUND_SERVICE},
                        alias = ForegroundServicePlugin.FOREGROUND_SERVICE
                ),
                @Permission(
                        strings = {Manifest.permission.FOREGROUND_SERVICE_HEALTH},
                        alias = ForegroundServicePlugin.FOREGROUND_SERVICE_HEALTH
                ),
                @Permission(
                        strings = {Manifest.permission.POST_NOTIFICATIONS},
                        alias = ForegroundServicePlugin.POST_NOTIFICATIONS
                ),
        }
)
public class ForegroundServicePlugin extends Plugin {

    static final String FOREGROUND_SERVICE = "foregroundService";
    static final String FOREGROUND_SERVICE_HEALTH = "foregroundServiceHealth";
    static final String POST_NOTIFICATIONS = "postNotifications";

    private Boolean runningService = false;

    @Override
    public void handleOnDestroy() {
        super.handleOnDestroy();
        if (runningService) {
            stopServiceForeground();
        }
    }

    @PluginMethod
    public void startService(PluginCall call) {
        if (getPermissionState(FOREGROUND_SERVICE) != PermissionState.GRANTED) {
            requestPermissionForAlias(FOREGROUND_SERVICE, call, "pluginPermissionsCallback");
        } else if (getPermissionState(POST_NOTIFICATIONS) != PermissionState.GRANTED) {
            requestPermissionForAlias(POST_NOTIFICATIONS, call, "pluginPermissionsCallback");
        } else if (getPermissionState(FOREGROUND_SERVICE_HEALTH) != PermissionState.GRANTED) {
            Log.d("Permissions", "Health permission not granted");
            requestPermissionForAlias(FOREGROUND_SERVICE_HEALTH, call, "pluginPermissionsCallback");
        } else {
            String title = call.getString("title", "StrEssen");
            String description = call.getString("description", "Die StrEssen App zählt deine Schritte. Bitte schließe die App nicht.");
            String icon = call.getString("icon");
            Integer notificationId = call.getInt("id", 1);

            Context context = getContext();
            Intent intent = new Intent(context, ForegroundService.class);
            intent.setAction("start");
            intent.putExtra("title", title)
                    .putExtra("description", description)
                    .putExtra("icon", icon)
                    .putExtra("id", notificationId);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }

            runningService = true;
            JSObject result = new JSObject();
            result.put("resolved", true);
            call.resolve(result);
        }
    }


    @PluginMethod
    public void stopService(PluginCall call) {
        stopServiceForeground();
        runningService = false;
        call.resolve();
    }

    private void stopServiceForeground() {
        Context context = getContext(); // Verwende den Plugin-Kontext
        Intent intent = new Intent(context, ForegroundService.class);
        intent.setAction("stop");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.stopService(intent);
        }
    }

    @PermissionCallback
    private void pluginPermissionsCallback(PluginCall call) {
        if (getPermissionState(POST_NOTIFICATIONS) == PermissionState.GRANTED) {
            call.resolve();
        } else {
            call.reject("Permission is required to use the step counter");
        }
    }
}
