package com.example.a2023_scouting_v2.ui.endgame;

import static com.example.a2023_scouting_v2.SaveData.getTeam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a2023_scouting_v2.SaveData;

public class EndgameViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EndgameViewModel() {
        mText = new MutableLiveData<>();
        System.out.println(getTeam());
        mText.setValue("Endgame - (Color #) | " + getTeam());
    }

    public LiveData<String> getText() {
        return mText;
    }
}