package io.capacitor.plugin.foregroundservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class ForegroundService extends Service {
    public static final String CHANNEL_ID = "capacitor.foreground.service.channel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ForegroundService", "onStartCommand called");

        // Setze den Alarm für den Neustart des Services
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent restartServiceIntent = new Intent(this, RestartServiceReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, restartServiceIntent, PendingIntent.FLAG_IMMUTABLE);

        // Setze den Alarm
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

        if (intent != null) {
            String action = intent.getAction();
            if ("start".equals(action)) {
                startForegroundService(intent.getExtras());
            } else if ("stop".equals(action)) {
                stopForeground(true);
                stopSelf();
            }
        } else {
            Log.d("ForegroundService", "Received null intent");
        }

        return START_STICKY;
    }



    private void startForegroundService(Bundle extras) {
        Log.d("ForegroundService", "Starting service!");
        Notification notification = buildNotification(extras);
        startForeground(NOTIFICATION_ID, notification);
    }

    private Notification buildNotification(Bundle extras) {
        String title = extras != null ? extras.getString("title", "Foreground Service") : "Foreground Service";
        String description = extras != null ? extras.getString("description", "Running in the background") : "Running in the background";
        int icon = extras != null && extras.getString("icon") != null
                ? getResources().getIdentifier(extras.getString("icon"), "drawable", getPackageName())
                : android.R.drawable.ic_menu_info_details;

        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(icon)
                .setOngoing(true);

        return builder.build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager.getNotificationChannel(CHANNEL_ID) == null) {
                NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Foreground Service Channel", NotificationManager.IMPORTANCE_HIGH);
                serviceChannel.setDescription("Enables background processing.");
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent("io.capacitor.plugin.foregroundservice.action.RESTART_SERVICE");
        sendBroadcast(broadcastIntent);
    }

}
