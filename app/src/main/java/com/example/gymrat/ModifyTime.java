package com.example.gymrat;

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

public class ModifyTime extends Activity {

    TimePicker timePicker;
    AlarmRecyleView alarmRecyleView;
    TextView sun, mon, tue, wen, thu, fri, sat;
    boolean isSun, isMon, isTue, isWen, isThu, isFri, isSat;
    boolean[] isDateArr = new boolean[7];

    String hourData, miData;
    int modifyPosition;
    int skyblueColor, redColor, blacklColor, blueColor, pinkColor;
    int[] colorArr = new int[7];
    int[] checkColorArr;

    int sunCount, monCount, tueCount, wenCount, thuCount, friCount, satCount;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
//      int sunCount = 0, monCount = 0, tueCount = 0, wenCount = 0, thuCount = 0, friCount = 0, satCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        skyblueColor = ContextCompat.getColor(getApplicationContext(), R.color.skyblue);
        redColor = ContextCompat.getColor(getApplicationContext(), R.color.red);
        blacklColor = ContextCompat.getColor(getApplicationContext(), R.color.black);
        blueColor = ContextCompat.getColor(getApplicationContext(), R.color.blue);
        pinkColor = ContextCompat.getColor(getApplicationContext(), R.color.pink);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.modfiy_time);

        Intent intent = getIntent();
        hourData = intent.getStringExtra("hour");
        miData = intent.getStringExtra("minute");
        modifyPosition = intent.getIntExtra("position", 0);
        isDateArr = intent.getBooleanArrayExtra("dataBool");
//        System.out.println("isDateArr leng "+ isDateArr.length);
//        System.out.println("modifyPosition " + modifyPosition);
//        System.out.println("hourData " + hourData);
//        System.out.println("miData " + miData);
//        System.out.println("isDateArr " + isDateArr[0]);


        timePicker = findViewById(R.id.mTimePicker);
        sun = findViewById(R.id.mSunday);
        mon = findViewById(R.id.mMonday);
        tue = findViewById(R.id.mTuesday);
        wen = findViewById(R.id.mWednesday);
        thu = findViewById(R.id.mThursday);
        fri = findViewById(R.id.mFriday);
        sat = findViewById(R.id.mSaturday);

        timePicker.setHour(Integer.parseInt(hourData));
        timePicker.setMinute(Integer.parseInt(miData));


        setColor();
        System.out.println("isDateArr[0]" +  isDateArr[0]);
        System.out.println("isDateArr[1]" +  isDateArr[1]);
        sunCount = setBackgroundTextView(sun, isDateArr[0], redColor, sunCount);
        monCount = setBackgroundTextView(mon, isDateArr[1], blacklColor, monCount);
        tueCount = setBackgroundTextView(tue, isDateArr[2], blacklColor, tueCount);
        wenCount = setBackgroundTextView(wen, isDateArr[3], blacklColor, wenCount);
        thuCount = setBackgroundTextView(thu, isDateArr[4], blacklColor, thuCount);
        friCount = setBackgroundTextView(fri, isDateArr[5], blacklColor, friCount);
        satCount = setBackgroundTextView(sat, isDateArr[6], blueColor, satCount);

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sunCount == 0){
                    sunCount += 1;
                    setTextview(sun, isSun);
                    colorArr[0] = pinkColor;
                    isDateArr[0] = true;
                }
                else{
                    unSetTextview(sun, isSun, redColor);
                    colorArr[0] = blacklColor;
                    isDateArr[0] = false;
                    sunCount = 0;
                }
            }
        });

        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monCount == 0){
                    monCount += 1;
                    setTextview(mon, isMon);
                    colorArr[1] = pinkColor;
                    isDateArr[1] = true;
                }
                else{
                    unSetTextview(mon, isMon, blacklColor);
                    colorArr[1] = blacklColor;
                    isDateArr[1] = false;
                    monCount = 0;
                }
            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tueCount == 0){
                    tueCount += 1;
                    setTextview(tue, isTue);
                    colorArr[2] = pinkColor;
                    isDateArr[2] = true;
                }
                else{
                    unSetTextview(tue, isTue, blacklColor);
                    colorArr[2] = blacklColor;
                    isDateArr[2] = false;
                    tueCount = 0;
                }
            }
        });

        wen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wenCount == 0){
                    wenCount += 1;
                    setTextview(wen, isWen);
                    colorArr[3] = pinkColor;
                    isDateArr[3] = true;
                }
                else{
                    unSetTextview(wen, isWen, blacklColor);
                    colorArr[3] = blacklColor;
                    isDateArr[3] = false;
                    wenCount = 0;
                }
            }
        });

        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thuCount == 0){
                    thuCount += 1;
                    setTextview(thu, isThu);
                    colorArr[4] = pinkColor;
                    isDateArr[4] = true;
                }
                else{
                    unSetTextview(thu, isThu, blacklColor);
                    colorArr[4] = blacklColor;
                    isDateArr[4] = false;
                    thuCount = 0;
                }
            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (friCount == 0){
                    friCount += 1;
                    setTextview(fri, isFri);
                    colorArr[5] = pinkColor;
                    isDateArr[5] = true;
                }
                else{
                    unSetTextview(fri, isFri, blacklColor);
                    colorArr[5] = blacklColor;
                    isDateArr[5] = false;
                    friCount = 0;
                }
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (satCount == 0){
                    satCount += 1;
                    setTextview(sat, isSat);
                    colorArr[6] = pinkColor;
                    isDateArr[6] = true;
                }
                else{
                    unSetTextview(sat, isSat, blueColor);
                    colorArr[6] = blacklColor;
                    isDateArr[6] = false;
                    satCount = 0;
                }
            }
        });
        int[] dateColor = {pinkColor, blacklColor, blueColor};
        int[] checkValue = {sunCount, monCount, tueCount, wenCount, thuCount, friCount, satCount};
        checkColorArr = checkValue;
        setArrColor(checkValue, dateColor);
    }

    public void setColor(){
        System.out.println("colorArr.length " + colorArr.length);
        for (int i = 0; i < colorArr.length; i ++){
            System.out.println("colorArr " + colorArr[i]);
            colorArr[i] = blacklColor;
        }
    }

    public void setArrColor(int[] index, int[] color){
        for(int i = 0; i < index.length; i++){
            if (index[i] == 1){
                colorArr[i] = color[0];
                isDateArr[i] = true;
            }
            else{
                colorArr[i] = color[1];
                isDateArr[i] = false;
            }

        }
    }
    public void setTextview(TextView textView, boolean isDate){
        isDate = true;
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_date));
        textView.setTextColor(pinkColor);
    }

    public void unSetTextview(TextView textView, boolean isDate, int colorCode){
        isDate = false;
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_false_date));
        textView.setTextColor(colorCode);
    }

    public int setBackgroundTextView(TextView tv, boolean isDate, int colorCode, int mCount){
        if (isDate == true){
            tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_date));
            tv.setTextColor(pinkColor);
            mCount = 1;
        }
        else{
            tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_false_date));
            tv.setTextColor(colorCode);
            mCount = 0;
        }
        return mCount;
    }

    public void setCount(){

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
    public void modiOnClose(View v){
        finish();
    }

    public void mOnModifiy(View v){

        String[] time = getTime();
        String hour = getTime()[0];
        String minute;

        if (Integer.parseInt(time[1]) < 10){
            minute = "0" + getTime()[1];
        }
        else{
            minute = getTime()[1];
        }

        boolean isSwitch = false;

        alarmRecyleView.modifyItem(modifyPosition, hour, minute, colorArr, isDateArr, isSwitch);

        setEditor(modifyPosition);
        String checkColorString = getColorIndex(isDateArr);
        editor.putString("position", String.valueOf(modifyPosition));
        editor.putString("hour", hour);
        editor.putString("minute", minute);
        editor.putString("color", checkColorString);
        editor.putBoolean("isChecked", isSwitch);
        //AlarmRecyleView.fileCount += 1;
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