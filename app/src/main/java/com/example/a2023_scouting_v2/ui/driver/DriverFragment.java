package com.example.a2023_scouting_v2.ui.driver;

import static com.example.a2023_scouting_v2.SaveData.getDriverCoop;
import static com.example.a2023_scouting_v2.SaveData.getDriverInt;
import static com.example.a2023_scouting_v2.SaveData.getStrings;
import static com.example.a2023_scouting_v2.SaveData.saveDriver;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.SaveData;
import com.example.a2023_scouting_v2.databinding.FragmentDriverBinding;

import java.util.List;

public class DriverFragment extends Fragment {
    private FragmentDriverBinding binding;
    public int topCount = 0;
    public int midCount = 0;
    public int lowCount = 0;
    public int linkCount = 0;
    public boolean coop = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println(topCount + ", " + midCount + ", " + lowCount + ", " + linkCount + ", " + coop);
        DriverViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DriverViewModel.class);

        binding = FragmentDriverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDriverTop;

        Button topPlusD = binding.topPlusD;
        Button midPlusD = binding.midPlusD;
        Button lowPlusD = binding.lowPlusD;
        Button linksPlusD = binding.linksPlusD;

        Button topMinusD = binding.topMinusD;
        Button midMinusD = binding.midMinusD;
        Button lowMinusD = binding.lowMinusD;
        Button linksMinusD = binding.linksMinusD;

        Button coopB = binding.coopB;

        final TextView topScoreNum = binding.topScoreTD;
        final TextView midScoreNum = binding.midScoreTD;
        final TextView lowScoreNum = binding.lowScoreTD;
        final TextView linkScoreNum = binding.linksScoreTD;

        List<Integer> saveints = getDriverInt();
        List<String> saveStrings = getStrings();
        String saveteamString = saveStrings.get(0);
        System.out.println(saveints);

        topCount = saveints.get(0);
        midCount = saveints.get(1);
        lowCount = saveints.get(2);
        linkCount = saveints.get(3);
        textView.setText("Tele-Op - (Color #) | " + saveteamString);
        coop = getDriverCoop();

        topScoreNum.setText("" + topCount);
        midScoreNum.setText("" + midCount);
        lowScoreNum.setText("" + lowCount);
        linkScoreNum.setText("" + linkCount);

        if(coop){
            coopB.setTextColor(Color.parseColor("#000000"));
            coopB.setBackgroundColor(Color.parseColor("#51f542"));
        }

        // Top score button functionality
        topPlusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topCount > 8){
                    topCount = 0;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                } else {
                    topCount++;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                }
            }
        });

        topMinusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topCount < 1){
                    topCount = 9;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                } else {
                    topCount--;
                    topScoreNum.setText("" + topCount);
                    System.out.println(topCount);
                }
            }
        });
        //Middle button row functionality
        midPlusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(midCount > 8){
                    midCount = 0;
                    midScoreNum.setText("" + midCount);
                    System.out.println(topCount);
                } else {
                    midCount++;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                }
            }
        });

        midMinusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(midCount < 1){
                    midCount = 9;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                } else {
                    midCount--;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                }
            }
        });

        lowPlusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowCount > 8){
                    lowCount = 0;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
                } else {
                    lowCount++;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
                }
            }
        });

        lowMinusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowCount < 1){
                    lowCount = 9;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
                } else {
                    lowCount--;
                    lowScoreNum.setText("" + lowCount);
                    System.out.println(lowCount);
                }
            }
        });

        linksPlusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(linkCount > 8){
                    linkCount = 0;
                    linkScoreNum.setText("" + linkCount);
                    System.out.println(linkCount);
                } else {
                    linkCount++;
                    linkScoreNum.setText("" + linkCount);
                    System.out.println(linkCount);
                }
            }
        });

        linksMinusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(linkCount < 1){
                    linkCount = 9;
                    linkScoreNum.setText("" + linkCount);
                    System.out.println(linkCount);
                } else {
                    linkCount--;
                    linkScoreNum.setText("" + linkCount);
                    System.out.println(linkCount);
                }
            }
        });

        coopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(coop){
                    coop = false;
                    coopB.setTextColor(Color.parseColor("#FFFFFF"));
                    coopB.setBackgroundColor(Color.parseColor("#3E3E3E"));
                } else if (!coop) {
                    coop = true;
                    coopB.setTextColor(Color.parseColor("#000000"));
                    coopB.setBackgroundColor(Color.parseColor("#51f542"));
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        saveDriver(topCount, midCount, lowCount, linkCount, coop);
        super.onDestroyView();
        binding = null;
        System.out.println(topCount + ", " + midCount + ", " + lowCount + ", " + linkCount + ", " + coop);
    }
}