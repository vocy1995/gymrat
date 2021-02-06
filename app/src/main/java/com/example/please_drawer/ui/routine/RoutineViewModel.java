package com.example.please_drawer.ui.routine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RoutineViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RoutineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("루틴 설정");
    }

    public LiveData<String> getText() {
        return mText;
    }
}