package com.appsrox.remindme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import com.appsrox.remindme.model.Alarm;
import com.appsrox.remindme.model.AlarmMsg;

/**
 * @author appsrox.com
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	
//	private static final String TAG = "AlarmReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		long alarmMsgId = intent.getLongExtra(AlarmMsg.COL_ID, -1);
		long alarmId = intent.getLongExtra(AlarmMsg.COL_ALARMID, -1);
		
		AlarmMsg alarmMsg = new AlarmMsg(alarmMsgId);
		alarmMsg.setStatus(AlarmMsg.EXPIRED);
		alarmMsg.persist(RemindMe.db);
		
		Alarm alarm = new Alarm(alarmId);
		alarm.load(RemindMe.db);

		PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
		setSmallIcon(R.drawable.ic_launcher).
		setTicker(alarm.getName()).
		setWhen(System.currentTimeMillis()).
		setContentTitle("Remind Me").
		setContentText(alarm.getName()).
		setContentIntent(pi);



		if (RemindMe.isVibrate()) {
			builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
		}
		if (alarm.getSound()) {
			builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
		}
		builder.setAutoCancel(true);
		
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification n = builder.build();
		nm.notify((int)alarmMsgId,n );
	}

}
