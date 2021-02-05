package com.example.gymrat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;
    private AlarmManager alarmManager;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
//    int pinkColor = ContextCompat.getColor(getApplicationContext(), R.color.skyblue);
    
    //int color = contextt.getResource().getColor(R.color.자신의ID값);
    private int hourValue;
    private int minuteValue;

    String[] textArr = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mHourView;
        public TextView mMinuteView;
        public TextView sunday, monday, tuesday, wednesday, thursday, friday, saturday;
        public Switch aSwitch;

        public ViewHolder(View view) {
            super(view);
            mHourView = (TextView)view.findViewById(R.id.hourTime);
            mMinuteView = (TextView)view.findViewById(R.id.minTime);
            sunday = (TextView)view.findViewById(R.id.cSunday);
            monday = (TextView)view.findViewById(R.id.cMonday);
            tuesday = (TextView)view.findViewById(R.id.cTuesday);
            wednesday = (TextView)view.findViewById(R.id.cWednesday);
            thursday = (TextView)view.findViewById(R.id.cThursday);
            friday = (TextView)view.findViewById(R.id.cFriday);
            saturday = (TextView)view.findViewById(R.id.cSturday);
            aSwitch = (Switch)view.findViewById(R.id.isSwitch);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlarmAdapter(ArrayList<MyData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mHourView.setText(mDataset.get(position).cardHour);
        holder.mMinuteView.setText(mDataset.get(position).cardMinute);
        holder.sunday.setTextColor(mDataset.get(position).color[0]);
        holder.monday.setTextColor(mDataset.get(position).color[1]);
        holder.tuesday.setTextColor(mDataset.get(position).color[2]);
        holder.wednesday.setTextColor(mDataset.get(position).color[3]);
        holder.thursday.setTextColor(mDataset.get(position).color[4]);
        holder.friday.setTextColor(mDataset.get(position).color[5]);
        holder.saturday.setTextColor(mDataset.get(position).color[6]);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            Context context;
            @Override
            public void onClick(View v){
                context = v.getContext();
                Intent intent = new Intent(context, ModifyTime.class);
                intent.putExtra("position", position);
                intent.putExtra("hour", mDataset.get(position).cardHour);
                intent.putExtra("minute", mDataset.get(position).cardMinute);
                intent.putExtra("dataBool", mDataset.get(position).dateArr);

                context.startActivity(intent);
//                System.out.println("ttt" + position);
//                System.out.println("boolean arr " +  mDataset.get(position).dateArr[0]);

            }
        });
        holder.aSwitch.setOnClickListener(new View.OnClickListener(){
            Context context;
            @Override
            public void onClick(View v){
                context = v.getContext();
                System.out.println();

//                context.startActivity(intent);
//                System.out.println("ttt" + position);
//                System.out.println("boolean arr " +  mDataset.get(position).dateArr[0]);

            }
        });

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                System.out.println("isChecked" + isChecked);
                if (isChecked == true){
                    setHour(Integer.parseInt(mDataset.get(position).cardHour));
                    setMinute(Integer.parseInt(mDataset.get(position).cardMinute));
                }
                else{
                }
            }
        });
//        holder.
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public int getHour(){
        return hourValue;
    }
    public int getMinute(){
        return minuteValue;
    }
    public void setHour(int hour){
        hourValue = hour;
    }
    public void setMinute(int minute){
        minuteValue = minute;
    }

    public void regist(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, Alarm.class);
        //PendingIntent pIntent = PendingIntent.getBroadcast(this, 0,intent, 0);
        int setHourValue = getHour();
        int setMinuteValue = getHour();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, setHourValue);
        calendar.set(Calendar.MINUTE, setMinuteValue);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // 지정한 시간에 매일 알림
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, pIntent);

    }// regist()..

    public void unregist(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(pIntent);
    }// unregist()..



}

class MyData{
    public String cardHour;
    public String cardMinute;
    public int[] color;
    public boolean[] dateArr;
//    public MyData(String cardHour, String cardMinute, boolean[] dateArr){
//        this.cardHour = cardHour;
//        this.cardMinute = cardMinute;
//        this.dateArr = dateArr;
//    }

    public MyData(String cardHour, String cardMinute, int[] color, boolean[] dateArr){
        this.cardHour = cardHour;
        this.cardMinute = cardMinute;
        this.color = color;
        this.dateArr = dateArr;
    }
}
