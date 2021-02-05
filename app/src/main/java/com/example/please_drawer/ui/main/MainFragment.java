package com.example.please_drawer.ui.main;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.please_drawer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainFragment extends Fragment {

    private MainViewModel MainViewModel;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.text_main);
        MainViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        database = FirebaseDatabase.getInstance("https://gymrat-f925a-default-rtdb.firebaseio.com/"); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference(); // DB 테이블 연결

        TextView goalToday = (TextView)root.findViewById(R.id.goalDate);
        ProgressBar proBar = (ProgressBar)root.findViewById(R.id.progress);

        databaseReference.child("Date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateInString = value;

                try {
                    Date date = formatter.parse(dateInString);

                    Calendar today = Calendar.getInstance();
                    Calendar d_day = Calendar.getInstance();

                    d_day.setTime(date);

                    long l_dday = d_day.getTimeInMillis()/(24*60*60*1000);
                    long l_today = today.getTimeInMillis()/(24*60*60*1000);
                    long substract = l_dday - l_today;

                    databaseReference.child("S-day").setValue(Long.toString(substract));

                    goalToday.setText(Long.toString(substract));

                    databaseReference.child("S-day").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String valu = dataSnapshot.getValue(String.class);
                            long a = Long.parseLong(valu);
                            long b = substract;
                            Integer c = (int)((a-b)*100/a);

                            proBar.setProgress(c);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        return root;
    }
}