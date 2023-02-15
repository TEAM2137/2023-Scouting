package com.example.a2023_scouting_v2.ui.home;

import static java.lang.Thread.sleep;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.databinding.FragmentAutoBinding;

public class HomeFragment extends Fragment {

    private FragmentAutoBinding binding;

    int topCount = 0;
    int midCount = 0;
    int lowCount = 0;

    public String scoutName = "";
    public String teamNum = "";
    public String matchNum = "";
    boolean submitted = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentAutoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAutoTop;
        homeViewModel.getTitle().observe(getViewLifecycleOwner(), textView::setText);

        //Reference the text boxes that keep track of counting
        final TextView topScoreNum = binding.topScoreTA;
        final TextView midScoreNum = binding.midScoreTA;
        final TextView lowScoreNum = binding.lowScoreTA;

        //Info TextEdits and Buttons
        Button submitInfo = binding.submitInfo;
        EditText scoutNameI = binding.scoutNameI;
        Spinner teamNumI = binding.teamNumI;
        EditText matchNumI = binding.matchNumI;

        //Top + & - buttons
        Button topPlusA = binding.topPlusA;
        Button topMinusA = binding.topMinusA;

        //Medium + & - buttons
        Button midPlusA = binding.midPlusA;
        Button midMinusA = binding.midMinusA;

        //Low + & - buttons
        Button lowPlusA = binding.lowPlusA;
        Button lowMinusA = binding.lowMinusA;

        //If someone goes back to the auto section set the score numbers to what they set earlier
        if(submitted){
            submitInfo.setText("Success!");
            submitInfo.setBackgroundColor(Color.parseColor("#51f542"));
            submitInfo.setTextSize(17);
        }
        topScoreNum.setText("" + topCount);
        midScoreNum.setText("" + midCount);
        lowScoreNum.setText("" + lowCount);

        //When the submit button is clicked save the input as a string
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoutName = scoutNameI.getText().toString();
                teamNum = teamNumI.getSelectedItem().toString();
                matchNum = matchNumI.getText().toString();
                System.out.println("Success! Scout name: " + scoutName + "Match Number: " + matchNum + ". Team Number: " + teamNum + ".");
                submitInfo.setText("Success!");
                submitInfo.setBackgroundColor(Color.parseColor("#51f542"));
                submitInfo.setTextSize(17);
                submitted = true;
            }
        });

        // Top score button functionality
        topPlusA.setOnClickListener(new View.OnClickListener() {
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

        topMinusA.setOnClickListener(new View.OnClickListener() {
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

        // Mid score button functionality
        midPlusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(midCount > 8){
                    midCount = 0;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                } else {
                    midCount++;
                    midScoreNum.setText("" + midCount);
                    System.out.println(midCount);
                }
            }
        });

        midMinusA.setOnClickListener(new View.OnClickListener() {
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

        // Low score button functionality
        lowPlusA.setOnClickListener(new View.OnClickListener() {
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

        lowMinusA.setOnClickListener(new View.OnClickListener() {
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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}