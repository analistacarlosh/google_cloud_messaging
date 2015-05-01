package br.com.chfmr.gcm;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by carlosrodrigues on 4/18/15.
 */
public class GcmService extends IntentService {

    public static final int NOTIFICATION_ID = 1;

    public GcmService(){
        super("GcmService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if(!extras.isEmpty()){
            if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){
                // startService(new Intent(this, MainIntentService.class));
                sendNotification(extras.getString("mensagem"));
            }
        }
        GcmReceiver.completeWakefulIntent(intent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification(String msg){

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        Intent it = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        PendingIntent pit = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pit)
                .setSmallIcon(R.drawable.ic_action)
                .setContentTitle(getString(R.string.text_notification))
                .setContentText(msg);

        nm.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
