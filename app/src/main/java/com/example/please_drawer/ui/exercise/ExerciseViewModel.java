package com.example.please_drawer.ui.exercise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExerciseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExerciseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("운동 정보");
    }

    public LiveData<String> getText() {
        return mText;
    }
}