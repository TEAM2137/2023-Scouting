package com.example.a2023_scouting_v2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mTitle;

    public HomeViewModel() {
        mTitle = new MutableLiveData<>();
        mTitle.setValue("Charged Up Scouting | Blue");
    }

    public LiveData<String> getTitle() {
        return mTitle;
    }
}