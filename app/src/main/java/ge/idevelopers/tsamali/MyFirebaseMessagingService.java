package ge.idevelopers.tsamali;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by soso on 4/3/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG="MyFirebaseMessagService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG,"FROM: "+remoteMessage.getFrom());

        if (remoteMessage.getData().size()>0)
            Log.d(TAG, "Message Data: "+remoteMessage.getData());

        if (remoteMessage.getNotification()!=null){
            Log.d(TAG,"Message Body: "+remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
        }


    }


// *** display notification ***\\\\
    private void sendNotification(String body,String title) {
        Intent mIntent=new Intent(this,MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent mPandingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if(title.length()==0)
            title="Tsamali.ge";

        android.support.v4.app.NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.tsamali_logo)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(mPandingIntent);

        NotificationManager mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0,mBuilder.build());

    }
}
