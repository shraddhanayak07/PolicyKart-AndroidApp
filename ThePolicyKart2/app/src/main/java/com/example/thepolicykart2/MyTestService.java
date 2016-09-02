package com.example.thepolicykart2;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyTestService extends IntentService {
    Context context;
    // Must create a default constructor
    public MyTestService() {
        // Used to name the worker thread, important only for debugging.
        super("test-service");
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
        context = getApplicationContext();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Intent i = new Intent(this, MyAccounts.class);
// Next, let's turn this into a PendingIntent using
//   public static PendingIntent getActivity(Context context, int requestCode,
//       Intent intent, int flags)
        int requestID = (int) System.currentTimeMillis(); //unique requestID to differentiate between various notification with same NotifId
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // cancel old intent and create new one
        PendingIntent pIntent = PendingIntent.getActivity(this, requestID, i, flags);
        // This describes what will happen when service is triggered
        Notification noti =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.b)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setContentIntent(pIntent).build();

// Hide the notification after its selected
       // noti.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.cancel(0);
        mNotificationManager.notify(0, noti);
        Log.v("Service started","here");
    }
}