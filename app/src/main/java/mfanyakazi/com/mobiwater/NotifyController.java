package mfanyakazi.com.mobiwater;;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

public class NotifyController {

    private static final String CHANNEL_ID = "majimaji";
    private int notificationId = 1011;
    private Context context;
    private String url;
    public NotifyController(Context context, String url){
        this.context = context;
        this.url = url;
    }

    public void tankNotification(){
        // Create an explicit intent for an Activity in your app
        Log.e("intent url", url);
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("url", url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Tank update")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify((int)System.currentTimeMillis(), builder.build());

    }


}
