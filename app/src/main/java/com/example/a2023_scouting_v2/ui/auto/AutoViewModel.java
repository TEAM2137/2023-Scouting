package com.example.a2023_scouting_v2.ui.auto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AutoViewModel extends ViewModel {

    private final MutableLiveData<String> mTitle;

    public AutoViewModel() {
        mTitle = new MutableLiveData<>();
        mTitle.setValue("Charged Up Scouting | Red 3");
    }

    public LiveData<String> getTitle() {
        return mTitle;
    }
}