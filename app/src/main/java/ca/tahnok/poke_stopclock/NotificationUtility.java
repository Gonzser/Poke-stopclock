package ca.tahnok.poke_stopclock;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by wes on 16-07-08.
 */
public class NotificationUtility {
    public static final int NOTIFICATION_ID = 0;

    public static void showNotification(Context context, int progress) {
        String body = String.format("%d minutes left", 5 - progress);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                                                 .setSmallIcon(android.R.drawable.ic_menu_delete)
                                                 .setColor(context.getResources().getColor(R.color.colorPrimary))
                                                 .setAutoCancel(true)
                                                 .setContentText(body)
                                                 .setContentTitle("Poke-stop clock")
                                                 .setProgress(5, progress, false)
                                                 .setContentIntent(getDefaultIntent(context));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public static void showFinalNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                                                 .setSmallIcon(android.R.drawable.ic_menu_delete)
                                                 .setColor(context.getResources().getColor(R.color.colorPrimary))
                                                 .setAutoCancel(true)
                                                 .setContentText("DONE!")
                                                 .setContentTitle("Poke-stop clock")
                                                 .setVibrate(new long[]{0, 1000, 0, 500})
                                                 .setContentIntent(getDefaultIntent(context));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private static PendingIntent getDefaultIntent(Context context) {
        Intent defaultIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, 0, defaultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
