package com.example.kosrbajnoksag;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotiHandler {
    private static final String CHANNEL_ID = "login Notification";

    private final NotificationManager mNotifyManager;
    private final Context mContext;

    public NotiHandler(Context context){
        this.mContext = context;
        this.mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return;

        NotificationChannel channel = new NotificationChannel
                (CHANNEL_ID, "login Notification", NotificationManager.IMPORTANCE_HIGH);

        channel.enableLights(true);
        channel.setLightColor(Color.MAGENTA);
        channel.enableVibration(true);
        channel.setDescription("login Notifications");

        mNotifyManager.createNotificationChannel(channel);
    }

    public void send(String message) {
        /*Intent intent = new Intent(mContext, ShopListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle("Logged in")
                .setContentText(message)
                .setSmallIcon(R.drawable.baseline_login_24);
                //.setContentIntent(pendingIntent);

        int NOTIFICATION_ID = 0;
        mNotifyManager.notify(NOTIFICATION_ID, builder.build());
    }

}
