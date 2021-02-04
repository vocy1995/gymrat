package com.example.please_drawer.ui.food;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FoodViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FoodViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("식단 추천");
    }

    public LiveData<String> getText() {
        return mText;
    }
}