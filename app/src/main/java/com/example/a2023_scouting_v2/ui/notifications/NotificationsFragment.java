package com.example.a2023_scouting_v2.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.SaveData;
import com.example.a2023_scouting_v2.databinding.FragmentEndgameBinding;
import com.example.a2023_scouting_v2.ui.home.HomeFragment;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NotificationsFragment extends Fragment {
    SaveData saveData = new SaveData();
    private FragmentEndgameBinding binding;
    private String buttonClicked = saveData.buttonClicked;
    private boolean engaged = saveData.engagedE;
    private boolean docked = saveData.dockedE;
    private boolean parked = saveData.parkedE;
    private boolean none = saveData.noneE;
    public int topCountA = saveData.topScoreA;
    public String scoutName = saveData.scoutName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentEndgameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button engagedB = binding.engagedTE;
        Button dockedB = binding.dockedTE;
        Button parkedB = binding.parkedTE;
        Button noneB = binding.noneTE;
        Button submitInfo = binding.saveData;

        System.out.println(topCountA);

        engagedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(!engaged){
                    engaged = true;
                    buttonClicked = "engaged";
                    disableDocked(dockedB);
                    disableParked(parkedB);
                    disableNone(noneB);
                    engagedB.setTextColor(Color.parseColor("#000000"));
                    engagedB.setBackgroundColor(Color.parseColor("#51f542"));
                } else {
                    buttonClicked = "";
                    disableEngaged(engagedB);
                }
            }
        });
        dockedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(!docked){
                    buttonClicked = "docked";
                    docked = true;
                    disableEngaged(engagedB);
                    disableParked(parkedB);
                    disableNone(noneB);
                    dockedB.setTextColor(Color.parseColor("#000000"));
                    dockedB.setBackgroundColor(Color.parseColor("#51f542"));
                } else{
                    buttonClicked = "";
                    disableDocked(dockedB);
                }

            }
        });
        parkedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                buttonClicked = "parked";
                parked = true;
                disableEngaged(engagedB);
                disableDocked(dockedB);
                disableNone(noneB);
                parkedB.setTextColor(Color.parseColor("#000000"));
                parkedB.setBackgroundColor(Color.parseColor("#51f542"));
            }
        });
        noneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                buttonClicked = "none";
                none = true;
                disableEngaged(engagedB);
                disableDocked(dockedB);
                disableParked(parkedB);
                noneB.setTextColor(Color.parseColor("#000000"));
                noneB.setBackgroundColor(Color.parseColor("#51f542"));
            }
        });

        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData.saveData();
            }
        });


        return root;
    }

    public void disableEngaged(Button button){
        engaged = false;
        button.setTextColor(Color.parseColor("#FFFFFF"));
        button.setBackgroundColor(Color.parseColor("#3E3E3E"));
    }

    public void disableDocked(Button button){
        docked = false;
        button.setTextColor(Color.parseColor("#FFFFFF"));
        button.setBackgroundColor(Color.parseColor("#3E3E3E"));
    }

    public void disableParked(Button button){
        parked = false;
        button.setTextColor(Color.parseColor("#FFFFFF"));
        button.setBackgroundColor(Color.parseColor("#3E3E3E"));
    }

    public void disableNone(Button button){
        none = false;
        button.setTextColor(Color.parseColor("#FFFFFF"));
        button.setBackgroundColor(Color.parseColor("#3E3E3E"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}