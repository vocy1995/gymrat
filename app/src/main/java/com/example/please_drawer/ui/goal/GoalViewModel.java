package com.example.please_drawer.ui.goal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GoalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GoalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("나의 목표");
    }

    public LiveData<String> getText() {
        return mText;
    }
}