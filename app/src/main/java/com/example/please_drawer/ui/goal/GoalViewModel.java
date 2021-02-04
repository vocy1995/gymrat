package com.example.please_drawer.ui.goal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GoalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GoalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("목표 설정");
    }

    public LiveData<String> getText() {
        return mText;
    }
}