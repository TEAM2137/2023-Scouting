package com.example.a2023_scouting_v2.ui.endgame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EndgameViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EndgameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Endgame | Blue");
    }

    public LiveData<String> getText() {
        return mText;
    }
}