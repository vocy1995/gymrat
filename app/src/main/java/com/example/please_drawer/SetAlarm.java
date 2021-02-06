package com.example.please_drawer;

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

public class SetAlarm extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        // 앞서 설정한 값으로 보여주기
        // 없으면 디폴트 값은 현재시간
        SharedPreferences sharedPreferences = getSharedPreferences("daily alarm", MODE_PRIVATE);
        long millis = sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis());

        Intent intent = getIntent();
        int hour = intent.getIntExtra("hour", 1);
        int minute = intent.getIntExtra("minute", 1);
        int position = intent.getIntExtra("position", 1);
        boolean isAlarm = intent.getBooleanExtra("isAlarm", true);
        boolean[] dataArr = intent.getBooleanArrayExtra("dataArr");

        int checkBoolCount = 0;
        if (isAlarm){
            for(int a = 0; a < dataArr.length; a++){
                if (dataArr[a] == true){
                    setAlarm(hour, minute, position, a + 1);
                }
                else{
                    checkBoolCount += 1;
                }
            }
            if (checkBoolCount == 7){
                setAlarm(hour, minute, position, 0);
            }
        }
        else{
            cancelAlarm(position);
        }
        finish();
    }

    public void setAlarm(int hourValue, int minuteValue, int position, int dayValue){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        if (dayValue != 0){
            calendar.set(Calendar.DAY_OF_WEEK, dayValue);
            calendar.set(Calendar.HOUR_OF_DAY, hourValue);
            calendar.set(Calendar.MINUTE, minuteValue);
            calendar.set(Calendar.SECOND, 0);

            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 7);
            }
        }
        else{
            calendar.set(Calendar.HOUR_OF_DAY, hourValue);
            calendar.set(Calendar.MINUTE, minuteValue);
            calendar.set(Calendar.SECOND, 0);

            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }
        }

        Toast.makeText(getApplicationContext(),"알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
        editor.putLong("nextNotifyTime", (long)calendar.getTimeInMillis());
        editor.apply();

        diaryNotification(calendar, position);
    }
    void diaryNotification(Calendar calendar, int pid){

        Boolean dailyNotify = true; // 무조건 알람을 사용

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmIntent.putExtra("state", "on");

        this.pendingIntent = PendingIntent.getBroadcast(this, pid, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        this.alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        // 사용자가 매일 알람을 허용했다면
        if (this.alarmManager != null) {

            this.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY + 6, this.pendingIntent);

            if (Build.VERSION.SDK_INT >= 23) {
                this.alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }

        // 부팅 후 실행되는 리시버 사용가능하게 설정
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

    }

    public void cancelAlarm(int position) {
        this.alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        this.pendingIntent = PendingIntent.getBroadcast(this, position, intent, 0);
        if (this.pendingIntent != null && alarmManager != null) {
                alarmManager.cancel(this.pendingIntent);
        }
        this.pendingIntent.cancel();
        Toast.makeText(getApplicationContext(),"알람이 취소되었습니다!", Toast.LENGTH_SHORT).show();

    }

    public void checkAlarm(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        System.out.println("pendingIntent" +  pendingIntent.getIntentSender());
    }
}