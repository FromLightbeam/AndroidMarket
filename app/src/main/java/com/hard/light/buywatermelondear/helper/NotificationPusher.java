package com.hard.light.buywatermelondear.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.MainActivity;

import java.util.UUID;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationPusher {
    private Context context;

    public NotificationPusher(Context context) {
        this.context = context;
    }

    public void push(String contentTitle, String contentText, Integer id) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this.context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText);

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

//    public void remove(Integer id) {
//        NotificationManager notificationManager = (NotificationManager)_context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancel(id);
//    }
}
