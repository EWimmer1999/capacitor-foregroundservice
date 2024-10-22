package io.capacitor.plugin.foregroundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.Plugin;
import com.getcapacitor.annotation.CapacitorPlugin;
import


@CapacitorPlugin(name = "Foregroundservice")
public class ForegroundservicePlugin extends Plugin {

    private static final String CHANNEL_ID = "ForegroundServiceChannel";

    public void startForegroundService(PluginCall call) {
        Intent serviceIntent = new Intent(getContext(), ForegroundService.class);
        getContext().startService(serviceIntent);
        call.resolve();
    }

    public void stopForegroundService(PluginCall call) {
        Intent serviceIntent = new Intent(getContext(), ForegroundService.class);
        getContext().stopService(serviceIntent);
        call.resolve();
    }
}




   