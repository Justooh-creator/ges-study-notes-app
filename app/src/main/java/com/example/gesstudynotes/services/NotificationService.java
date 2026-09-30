package com.example.gesstudynotes.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.utils.Constants;

public class NotificationService {

    private Context context;
    private NotificationManager notificationManager;

    public NotificationService(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "GES Study Notes";
            String description = "Notifications for GES Study Notes app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    Constants.NOTIFICATION_CHANNEL_ID,
                    name,
                    importance
            );
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showDownloadNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context,
                Constants.NOTIFICATION_CHANNEL_ID
        )
                .setSmallIcon(R.drawable.ic_download)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        notificationManager.notify(
                Constants.NOTIFICATION_ID_DOWNLOAD,
                builder.build()
        );
    }

    public void showSyncNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context,
                Constants.NOTIFICATION_CHANNEL_ID
        )
                .setSmallIcon(R.drawable.ic_sync)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setAutoCancel(true);

        notificationManager.notify(
                Constants.NOTIFICATION_ID_SYNC,
                builder.build()
        );
    }

    public void dismissDownloadNotification() {
        notificationManager.cancel(Constants.NOTIFICATION_ID_DOWNLOAD);
    }

    public void dismissSyncNotification() {
        notificationManager.cancel(Constants.NOTIFICATION_ID_SYNC);
    }
}
