package com.example.please_drawer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.core.content.ContextCompat;

public class SetTime extends Activity {

    TimePicker timePicker;
    AlarmRecyleView alarmRecyleView;
    TextView sun, mon, tue, wen, thu, fri, sat;
    boolean isSun, isMon, isTue, isWen, isThu, isFri, isSat;
    boolean[] isDateArr = {false, false, false, false, false, false, false};

    String data;
    int skyblueColor, redColor, blacklColor, blueColor, pinkColor;
    int sunCount, monCount, tueCount, wenCount, thuCount, friCount, satCount;
    int[] colorArr = new int[7];
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setEditor();

        skyblueColor = ContextCompat.getColor(getApplicationContext(), R.color.skyblue);
        System.out.println("skyblueColor" + skyblueColor);
        redColor = ContextCompat.getColor(getApplicationContext(), R.color.red);
        blacklColor = ContextCompat.getColor(getApplicationContext(), R.color.black);
        blueColor = ContextCompat.getColor(getApplicationContext(), R.color.blue);
        pinkColor = ContextCompat.getColor(getApplicationContext(), R.color.pink);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.set_time);

        timePicker = findViewById(R.id.timePicker);
        sun = findViewById(R.id.sunday);
        mon = findViewById(R.id.monday);
        tue = findViewById(R.id.tuesday);
        wen = findViewById(R.id.wednesday);
        thu = findViewById(R.id.thursday);
        fri = findViewById(R.id.friday);
        sat = findViewById(R.id.saturday);

        setColor();
        sun.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
                if (count == 0){
                    count += 1;
                    setTextview(sun, isSun);
                    colorArr[0] = pinkColor;
                    isDateArr[0] = true;
                }
                else{
                    unSetTextview(sun, isSun, redColor);
                    colorArr[0] = redColor;
                    isDateArr[0] = false;
                    count = 0;
                }
            }
        });

        mon.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
                if (count == 0){
                    count += 1;
                    setTextview(mon, isMon);
                    colorArr[1] = pinkColor;
                    isDateArr[1] = true;
                }
                else{
                    unSetTextview(mon, isMon, blacklColor);
                    colorArr[1] = blacklColor;
                    isDateArr[1] = false;
                    count = 0;
                }
            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
                if (count == 0){
                    count += 1;
                    setTextview(tue, isTue);
                    colorArr[2] = pinkColor;
                    isDateArr[2] = true;
                }
                else{
                    unSetTextview(tue, isTue, blacklColor);
                    colorArr[2] = blacklColor;
                    isDateArr[2] = false;
                    count = 0;
                }
            }
        });

        wen.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
                if (count == 0){
                    count += 1;
                    setTextview(wen, isWen);
                    colorArr[3] = pinkColor;
                    isDateArr[3] = true;
                }
                else{
                    unSetTextview(wen, isWen, blacklColor);
                    colorArr[3] = blacklColor;
                    isDateArr[3] = false;
                    count = 0;
                }
            }
        });

        thu.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
                if (count == 0){
                    count += 1;
                    setTextview(thu, isThu);
                    colorArr[4] = pinkColor;
                    isDateArr[4] = true;
                }
                else{
                    unSetTextview(thu, isThu, blacklColor);
                    colorArr[4] = blacklColor;
                    isDateArr[4] = false;
                    count = 0;
                }
            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
                if (count == 0){
                    count += 1;
                    setTextview(fri, isFri);
                    colorArr[5] = pinkColor;
                    isDateArr[5] = true;
                }
                else{
                    unSetTextview(fri, isFri, blacklColor);
                    colorArr[5] = blacklColor;
                    isDateArr[5] = false;
                    count = 0;
                }
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
                if (count == 0){
                    count += 1;
                    setTextview(sat, isSat);
                    colorArr[6] = pinkColor;
                    isDateArr[6] = true;
                }
                else{
                    unSetTextview(sat, isSat, blueColor);
                    colorArr[6] = blacklColor;
                    isDateArr[6] = false;
                    count = 0;
                }
            }
        });

//        isDateArr = {isSat,isSat,isSat,isSat,isSat,isSat,isSat};
        Intent intent = getIntent();
        data = intent.getStringExtra("position");
        System.out.println("data" + data);

        System.out.println("isDateAtrr le " + isDateArr.length);

    }

    public void setColor(){
        for (int i = 0; i < colorArr.length; i ++){
            colorArr[i] = blacklColor;
        }
    }
    public void setTextview(TextView textView, boolean isDate){
        isDate = true;
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_date));
        textView.setTextColor(pinkColor);
    }

    public void unSetTextview(TextView textView, boolean isDate, int colorCode){
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_false_date));
        textView.setTextColor(colorCode);
        isDate = false;
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

        String[] time = getTime();
        String hour = getTime()[0];
        String minute;

        if (Integer.parseInt(time[1]) < 10){
            minute = "0" + getTime()[1];
        }
        else{
            minute = getTime()[1];
        }
        System.out.println("set hour" + hour);
        System.out.println("set mi" +minute);

        boolean isSwitch = false;

        alarmRecyleView.insertItem(Integer.parseInt(data), hour, minute, colorArr, isDateArr, isSwitch);
        Context context = v.getContext();

        setEditor(Integer.parseInt(data));
        String checkColorString = getColorIndex(isDateArr);
        editor.putString("position", data);
        editor.putString("hour", hour);
        editor.putString("minute", minute);
        editor.putString("color", checkColorString);
        editor.putBoolean("isChecked", isSwitch);
        AlarmRecyleView.fileCount += 1;
        editor.apply();
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

    public String getColorIndex(boolean[] arr){
        String temp = "";
        int count = 0;
        for(count = 0; count < arr.length; count ++){
            if (arr[count] == true){
                temp = temp + count + " " + 1 + " ";
            }
            else{
                temp = temp + count + " " + 0 + " ";
            }

        }
        return temp;
    }

    public void setEditor(int i){
        this.sharedPreferences = getSharedPreferences("Test" + i, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }
}

