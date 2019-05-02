package com.zeneo.horoscope.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.zeneo.horoscope.MainActivity;
import com.zeneo.horoscope.R;

public class Utils {

    public static NotificationManager mManager;


    @SuppressWarnings("static-access")
    public static void generateNotification(Context context, String text){

        NotificationCompat.Builder nb= new NotificationCompat.Builder(context);
        nb.setSmallIcon(R.drawable.logo);
        nb.setContentTitle("نصائح جديدة");
        nb.setContentText(text);
        nb.setTicker("Take a look");

        nb.setAutoCancel(true);



        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());

    }

}
