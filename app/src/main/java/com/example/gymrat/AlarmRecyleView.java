package com.example.gymrat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class AlarmRecyleView extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<MyData> myDataset;
    ImageButton addAlarm;
    public static int fileCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_recycler_view);

        buildRecycView();
        createList();
        addAlarm = findViewById(R.id.test);
        addAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetTime.class);
                intent.putExtra("position", Integer.toString(mAdapter.getItemCount()));
                startActivityForResult(intent, 1);
            }
        });
    }

    public static void insertItem(int position, String hour, String minute, int[] color, boolean[] dateArr, boolean isSwitch){
        myDataset.add(position, new MyData(hour, minute, color, dateArr, isSwitch));
        mAdapter.notifyDataSetChanged();
    }

    public static void modifyItem(int position, String hour, String minute, int[] color, boolean[] dateArr, boolean isSwitch){
        myDataset.set(position, new MyData(hour, minute, color, dateArr, isSwitch));
        mAdapter.notifyDataSetChanged();
    }

    public void removeItem(int position){
        myDataset.remove(position);
        mAdapter.notifyDataSetChanged();
    }
    public void createList(){
        System.out.println("fileCount " + fileCount);

        if (fileCount > 0){
            for(int a = 0; a < fileCount; a ++){
                boolean[] dateCheckArr = new boolean[7];
                int[] colorCheckArr = new int[7];
                SharedPreferences test = getSharedPreferences("Test" + a, MODE_PRIVATE);
                String position = test.getString("position", "102");
                String hour = test.getString("hour", "100");
                String min = test.getString("minute", "100");
                String color = test.getString("color", "100");
                boolean isAlarm = test.getBoolean("isChecked", false);
                String[] splitColor = color.split(" ");

                int dataCount = 0;
                for(int c = 1; c < splitColor.length; c += 2){
                    if (Integer.parseInt(splitColor[c]) == 1){
                        dateCheckArr[dataCount] = true;
                        colorCheckArr[dataCount] = Color.parseColor("#FF9999");
                    }
                    else{
                        dateCheckArr[dataCount] = false;
                        colorCheckArr[dataCount] = Color.parseColor("#000000");
                    }
                    dataCount++;
                }
                myDataset.add(Integer.parseInt(position), new MyData(hour, min, colorCheckArr, dateCheckArr, isAlarm));

                System.out.println("position " + position + " hour " + hour + " minute " + min + " color " + color);

            }
        }
        //fileCount = 0;
    }

    public void buildRecycView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.alarmrecyclerview);
        mRecyclerView.setHasFixedSize(true);
        myDataset = new ArrayList<>();
        mAdapter = new AlarmAdapter(myDataset);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}

