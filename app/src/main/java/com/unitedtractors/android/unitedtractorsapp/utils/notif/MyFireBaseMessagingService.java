package com.unitedtractors.android.unitedtractorsapp.utils.notif;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.view.activity.SplashScreenActivity;

import java.util.Random;

import static android.app.Notification.DEFAULT_ALL;
import static android.app.Notification.DEFAULT_LIGHTS;
import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    private final String ADMIN_CHANNEL_ID ="admin_channel";
    String title,message;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        title = remoteMessage.getData().get("Title");
        message = remoteMessage.getData().get("Message");

        Log.d("msg", "onMessageReceived: " + remoteMessage.getData().get("Message"));
        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = String.valueOf(new Random().nextInt(3000));
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_logo))
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setLights(Color.WHITE, 3000, 3000)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_ALL)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ADMIN_CHANNEL_ID, "Default channel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        manager.notify(Integer.parseInt(channelId), builder.build());
//        final Intent intent = new Intent(this, SplashScreenActivity.class);
//        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        int notificationID = new Random().nextInt(3000);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this , 303, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
//                R.drawable.ic_stat_icon);
//
//        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            setupChannels(notificationManager);
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
//                    .setSmallIcon(R.drawable.ic_stat_icon)
//                    .setLargeIcon(largeIcon)
//                    .setContentTitle(title)
//                    .setContentText(message)
//                    .setAutoCancel(true)
//                    .setSound(notificationSoundUri)
//                    .setVibrate(new long[]{1000, 300, 1000})
//                    .setContentIntent(pendingIntent);
//
//            //Set notification color to match your app color template
////            notificationBuilder.setColor(getResources().getColor(R.color.dark_green));
//            notificationManager.notify(notificationID, notificationBuilder.build());
//        } else {
//            NotificationCompat.Builder builder =
//                    new NotificationCompat.Builder(getApplicationContext())
//                            .setLargeIcon(largeIcon)
//                            .setSmallIcon(R.drawable.ic_stat_icon)
//                            .setContentTitle(title)
//                            .setContentText(message)
//                            .setAutoCancel(true)
//                            .setSound(notificationSoundUri)
//                            .setVibrate(new long[]{1000, 300, 1000})
//                            .setContentIntent(pendingIntent);
//                    ;
//            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//            manager.notify(0, builder.build());
//        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void setupChannels(NotificationManager notificationManager){
//        CharSequence adminChannelName = "New notification";
//        String adminChannelDescription = "Device to devie notification";
//
//        NotificationChannel adminChannel;
//        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
//        adminChannel.setDescription(adminChannelDescription);
//        adminChannel.enableLights(true);
//        adminChannel.setLightColor(Color.RED);
//        adminChannel.enableVibration(true);
//        if (notificationManager != null) {
//            notificationManager.createNotificationChannel(adminChannel);
//        }
//    }
}