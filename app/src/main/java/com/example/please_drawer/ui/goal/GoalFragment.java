package com.example.please_drawer.ui.goal;

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

public class GoalFragment extends Fragment {

    private GoalViewModel GoalViewModel;

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
        return root;
    }
}