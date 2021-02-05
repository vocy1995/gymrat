package com.example.please_drawer.ui.goal;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class GoalFragment extends Fragment {

    private GoalViewModel GoalViewModel;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String calNow;
    private String yearValue;
    private String monthValue;
    private String dayValue;
    private final int ONE_DAY = 24 * 60 * 60 * 1000;

public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        GoalViewModel =
        new ViewModelProvider(this).get(GoalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goal, container, false);
        final TextView textView = root.findViewById(R.id.text_goal);
        GoalViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        database = FirebaseDatabase.getInstance("https://gymrat-f925a-default-rtdb.firebaseio.com/"); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference(); // DB 테이블 연결

        EditText idEdit = (EditText)root.findViewById(R.id.editTest);
        Button button1 = (Button)root.findViewById(R.id.button_test);
        Calendar cal = Calendar.getInstance();
        EditText editCale = root.findViewById(R.id.editCal);

        databaseReference.child("Comment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                idEdit.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editCale.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = idEdit.getText().toString();
                String DateValue = editCale.getText().toString();

                databaseReference.child("Date").setValue(DateValue);
                databaseReference.child("Comment").setValue(value);
                databaseReference.child("Year").setValue(yearValue);
                databaseReference.child("Month").setValue(monthValue);
                databaseReference.child("Day").setValue(dayValue);
            }
        });

        editCale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(v);
            }

            public void openDatePickerDialog(final View v) {

                // Get Current Date
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        (view, year, monthOfYear, dayOfMonth) -> {
                            String selectedDate = year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일";

                            Integer a = year;
                            yearValue = a.toString();
                            Integer c = (monthOfYear + 1);
                            monthValue = c.toString();
                            Integer e = dayOfMonth;
                            dayValue = e.toString();

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String today =  sdf.format(new Date());
                            Log.d("A", today);

                            switch (v.getId()) {
                                case R.id.editCal:
                                    ((EditText)v).setText(selectedDate);
                                    break;
                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH+1));
                datePickerDialog.show();
            }
        });


    return root;
}
    }