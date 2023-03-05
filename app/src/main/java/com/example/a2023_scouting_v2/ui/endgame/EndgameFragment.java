package com.example.a2023_scouting_v2.ui.endgame;

import static com.example.a2023_scouting_v2.MainActivity.saveData;
import static com.example.a2023_scouting_v2.SaveData.saveEndgame;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.MainActivity;
import com.example.a2023_scouting_v2.databinding.FragmentEndgameBinding;

public class EndgameFragment extends Fragment {
    private FragmentEndgameBinding binding;
    private String buttonClicked = "";
    public boolean engaged = false;
    public boolean docked = false;
    public boolean parked = false;
    public boolean none = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EndgameViewModel notificationsViewModel =
                new ViewModelProvider(this).get(EndgameViewModel.class);

        binding = FragmentEndgameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button engagedB = binding.engagedTE;
        Button dockedB = binding.dockedTE;
        Button parkedB = binding.parkedTE;
        Button noneB = binding.noneTE;
        Button submitInfo = binding.saveData;

        if(engaged) {
            engagedB.setTextColor(Color.parseColor("#000000"));
            engagedB.setBackgroundColor(Color.parseColor("#51f542"));
        }

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
                if(!parked){
                    buttonClicked = "parked";
                    parked = true;
                    disableEngaged(engagedB);
                    disableDocked(dockedB);
                    disableNone(noneB);
                    parkedB.setTextColor(Color.parseColor("#000000"));
                    parkedB.setBackgroundColor(Color.parseColor("#51f542"));
                } else {
                    buttonClicked = "";
                    disableParked(parkedB);
                }

            }
        });
        noneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(!none) {
                    buttonClicked = "none";
                    none = true;
                    disableEngaged(engagedB);
                    disableDocked(dockedB);
                    disableParked(parkedB);
                    noneB.setTextColor(Color.parseColor("#000000"));
                    noneB.setBackgroundColor(Color.parseColor("#51f542"));
                } else{
                    buttonClicked = "";
                    disableNone(noneB);
                }

            }
        });

        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(engaged);
                saveEndgame(engaged, docked, parked, none);
                saveData();
                Context context = view.getContext();
                Intent mStartActivity = new Intent(context, MainActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
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