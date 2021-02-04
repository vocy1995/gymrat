package com.example.gymrat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmRecyleView extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<MyData> myDataset;
    Button addAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_recycler_view);

        buildRecycView();

        addAlarm = findViewById(R.id.test);
        addAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetTime.class);
                intent.putExtra("data", Integer.toString(mAdapter.getItemCount()));
                startActivityForResult(intent, 1);
            }
        });
    }

    public static void insertItem(int position, String hour, String minute){
        myDataset.add(position, new MyData(hour, minute));
        mAdapter.notifyDataSetChanged();
    }

    public void removeItem(int position){

        myDataset.remove(position);
        mAdapter.notifyDataSetChanged();
    }
    public void createList(){
        myDataset.add(new MyData("30", "30"));
        myDataset.add(new MyData("5", "5"));
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

