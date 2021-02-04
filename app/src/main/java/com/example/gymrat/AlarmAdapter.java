package com.example.gymrat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mHourView;
        public TextView mMinuteView;

        public ViewHolder(View view) {
            super(view);
            mHourView = (TextView)view.findViewById(R.id.hourTime);
            mMinuteView = (TextView)view.findViewById(R.id.minTime);
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
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class MyData{
    public String cardHour;
    public String cardMinute;
    public boolean[] dateArr;
//    public MyData(String cardHour, String cardMinute, boolean[] dateArr){
//        this.cardHour = cardHour;
//        this.cardMinute = cardMinute;
//        this.dateArr = dateArr;
//    }

    public MyData(String cardHour, String cardMinute){
        this.cardHour = cardHour;
        this.cardMinute = cardMinute;
    }
}
