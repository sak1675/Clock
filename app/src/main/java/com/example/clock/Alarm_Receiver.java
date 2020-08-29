package com.example.clock;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //Notification setup
        Notification notification = new Notification.Builder(context)
                .setContentTitle("ALARM")
                .setContentText("Your Alarm is ringing")
                .setSmallIcon(R.mipmap.ic_launcher).build();

        NotificationManager notimanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notimanager.notify(0,notification);

        //Ringtone setup
        Uri ring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = RingtoneManager.getRingtone(context,ring);
        ringtone.play();



    }
}
