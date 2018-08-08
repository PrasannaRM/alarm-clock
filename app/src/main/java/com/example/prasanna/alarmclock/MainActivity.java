package com.example.prasanna.alarmclock;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity","Oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker)findViewById(R.id.timePicker);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

    }

    public void onToggleButton(View view)
    {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(MainActivity.this, "ALARM_ON", Toast.LENGTH_SHORT).show();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = pendingIntent.getBroadcast(this, 0, intent, 0);

            time = (calendar.getTimeInMillis() - calendar.getTimeInMillis() % 60000);

            if (System.currentTimeMillis() >= time) {
                if (calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }
            alarmManager.setInexactRepeating(alarmManager.RTC_WAKEUP,time,10000, pendingIntent);
        }
        else
            {
                alarmManager.cancel(pendingIntent);
                Toast.makeText(MainActivity.this, "ALARM_OFF", Toast.LENGTH_SHORT).show();
            }
        }
    }