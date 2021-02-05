package com.example.gymrat;

import androidx.recyclerview.widget.RecyclerView;

public interface AlarmTouchListener {
    boolean onItemMove(int from_position, int to_position);
    void onItemSwipe(int position);
    void onLeftClick(int position, RecyclerView.ViewHolder viewHolder);
    void onRightClick(int position, RecyclerView.ViewHolder viewHolder);
}

