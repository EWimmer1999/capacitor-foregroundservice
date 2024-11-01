package io.capacitor.plugin.foregroundservice;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RestartServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RestartServiceReceiver", "Receiver triggered");
        Intent serviceIntent = new Intent(context, ForegroundService.class);
        serviceIntent.setAction("start");

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, serviceIntent, PendingIntent.FLAG_IMMUTABLE);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            Log.e("RestartServiceReceiver", "Failed to send PendingIntent", e);
        }
    }

}

