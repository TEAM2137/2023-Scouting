package com.example.a2023_scouting_v2.ui.driver;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DriverViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DriverViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Tele-Op | Blue");
    }

    public LiveData<String> getText() {
        return mText;
    }
}