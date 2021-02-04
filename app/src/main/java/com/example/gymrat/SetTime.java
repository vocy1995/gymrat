package com.example.gymrat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TimePicker;

public class SetTime extends Activity {

    TimePicker timePicker;
    AlarmRecyleView alarmRecyleView;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.set_time);

        timePicker = findViewById(R.id.timePicker);

        Intent intent = getIntent();
        data = intent.getStringExtra("data");
        System.out.println("data" + data);

    }

    public String[] getTime(){
        String[] arrTime = new String[2];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            arrTime[0] = Integer.toString(timePicker.getHour());
            arrTime[1] = Integer.toString(timePicker.getMinute());
        } else {
            arrTime[0] = Integer.toString(timePicker.getCurrentHour());
            arrTime[1] = Integer.toString(timePicker.getCurrentMinute());
        }

        return arrTime;

    }
    public void mOnClose(View v){
        finish();
    }

    public void mOnConfirm(View v){

        String hour = getTime()[0];
        String minute = getTime()[1];
        System.out.println(hour);
        System.out.println(minute);
        String[] time = getTime();

        System.out.println("Integer.parseInt(data) " + Integer.parseInt(data));
        alarmRecyleView.insertItem(Integer.parseInt(data),time[0], time[1]);
//        startActivityForResult(intent, 1);

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}

