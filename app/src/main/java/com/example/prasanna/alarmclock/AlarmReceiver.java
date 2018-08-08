package com.example.prasanna.alarmclock;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.media.Ringtone;
        import android.media.RingtoneManager;
        import android.net.Uri;
        import android.util.Log;
        import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.i("AlarmService","OnReceive");
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }
}