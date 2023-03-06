package com.example.a2023_scouting_v2.ui.driver;

import static com.example.a2023_scouting_v2.SaveData.getTeam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a2023_scouting_v2.SaveData;

public class DriverViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DriverViewModel() {
        mText = new MutableLiveData<>();
        System.out.println(getTeam());
        mText.setValue("Tele-Op - Red 1 | " + getTeam());
    }

    public LiveData<String> getText() { return mText; }
}