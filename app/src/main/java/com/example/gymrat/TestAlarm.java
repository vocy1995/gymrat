package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TestAlarm extends AppCompatActivity {

    private TimePicker timePicker;
    private AlarmManager alarmManager;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }// onCreate()..
}

//    public static void regist(View view) {
//
//        Intent intent = new Intent(this, Alarm.class);
//        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0,intent, 0);
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            hour=timePicker.getHour();
//            minute=timePicker.getMinute();
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        // 지정한 시간에 매일 알림
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, pIntent);
//
//    }// regist()..
//
//    public void unregist(View view) {
//        Intent intent = new Intent(this, Alarm.class);
//        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//        alarmManager.cancel(pIntent);
//    }// unregist()..

