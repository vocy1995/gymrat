package com.example.please_drawer.ui.routine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.please_drawer.R;

public class RoutineFragment extends Fragment {

    private RoutineViewModel RoutineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RoutineViewModel =
                new ViewModelProvider(this).get(RoutineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_routine, container, false);
        final TextView textView = root.findViewById(R.id.text_routine);
        RoutineViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}