package com.example.please_drawer;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        public TextView mCheckAmPm;
        public TextView sunday, monday, tuesday, wednesday, thursday, friday, saturday;
        public Switch aSwitch;

        public ViewHolder(View view) {
            super(view);
            mHourView = (TextView)view.findViewById(R.id.hourTime);
            mMinuteView = (TextView)view.findViewById(R.id.minTime);
            mCheckAmPm = (TextView)view.findViewById(R.id.checkAmPm);
            sunday = (TextView)view.findViewById(R.id.cSunday);
            monday = (TextView)view.findViewById(R.id.cMonday);
            tuesday = (TextView)view.findViewById(R.id.cTuesday);
            wednesday = (TextView)view.findViewById(R.id.cWednesday);
            thursday = (TextView)view.findViewById(R.id.cThursday);
            friday = (TextView)view.findViewById(R.id.cFriday);
            saturday = (TextView)view.findViewById(R.id.cSturday);
            aSwitch = (Switch)view.findViewById(R.id.mIsSwitch);
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
        int hourData = Integer.parseInt(mDataset.get(position).cardHour);
        if ((hourData / 12) == 1){
            holder.mHourView.setText(Integer.toString(hourData % 12));
            holder.mCheckAmPm.setText("PM");
        }
        else{
            holder.mHourView.setText(mDataset.get(position).cardHour);
            holder.mCheckAmPm.setText("AM");
        }

        holder.mMinuteView.setText(mDataset.get(position).cardMinute);
        holder.sunday.setTextColor(mDataset.get(position).color[0]);
        holder.monday.setTextColor(mDataset.get(position).color[1]);
        holder.tuesday.setTextColor(mDataset.get(position).color[2]);
        holder.wednesday.setTextColor(mDataset.get(position).color[3]);
        holder.thursday.setTextColor(mDataset.get(position).color[4]);
        holder.friday.setTextColor(mDataset.get(position).color[5]);
        holder.saturday.setTextColor(mDataset.get(position).color[6]);
        System.out.println("mDataset.get(position).isSwitch " + mDataset.get(position).isSwitch);
        holder.aSwitch.setChecked(mDataset.get(position).isSwitch);

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
            }
        });
        holder.aSwitch.setOnClickListener(new View.OnClickListener(){
            Context context;

            @Override
            public void onClick(View v){
                context = v.getContext();
                boolean isSwitch_s = holder.aSwitch.isChecked();

                if (isSwitch_s == true){
//                    System.out.println("isSwitch" + isSwitch);
                    Intent intent = new Intent(context, SetAlarm.class);
                    intent.putExtra("hour", Integer.parseInt(mDataset.get(position).cardHour));
                    intent.putExtra("minute", Integer.parseInt(mDataset.get(position).cardMinute));
                    intent.putExtra("dataArr", mDataset.get(position).dateArr);
                    intent.putExtra("position", position);
                    intent.putExtra("isAlarm", true);
                    mDataset.get(position).isSwitch = true;

                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(context, SetAlarm.class);
                    intent.putExtra("hour", Integer.parseInt(mDataset.get(position).cardHour));
                    intent.putExtra("minute", Integer.parseInt(mDataset.get(position).cardMinute));
                    intent.putExtra("dataArr", mDataset.get(position).dateArr);
                    intent.putExtra("position", position);
                    intent.putExtra("isAlarm", false);
                    mDataset.get(position).isSwitch = false;
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class MyData{
    public String cardHour;
    public String cardMinute;
    public int[] color;
    public boolean[] dateArr;
    public boolean isSwitch;

    public MyData(String cardHour, String cardMinute, int[] color, boolean[] dateArr,  boolean isSwitch){
        this.cardHour = cardHour;
        this.cardMinute = cardMinute;
        this.color = color;
        this.dateArr = dateArr;
        this.isSwitch = isSwitch;
    }
}
