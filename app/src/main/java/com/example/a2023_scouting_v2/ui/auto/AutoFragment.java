package com.example.a2023_scouting_v2.ui.auto;

import static com.example.a2023_scouting_v2.SaveData.saveAuto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2023_scouting_v2.MainActivity;
import com.example.a2023_scouting_v2.R;
import com.example.a2023_scouting_v2.databinding.FragmentAutoBinding;

public class AutoFragment extends Fragment {
    private FragmentAutoBinding binding;
    public int topCount = 0;
    public int midCount = 0;
    public int lowCount = 0;
    public String scoutName = "";
    public String teamNum = "";
    public String matchNum = "";
    public boolean submitted = false;
    public boolean docked = false;
    public boolean engaged = false;
    public boolean community = false;
    public boolean switchedView = false;

    MainActivity main = new MainActivity();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AutoViewModel homeViewModel =
                new ViewModelProvider(this).get(AutoViewModel.class);

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

        //Set up the docked, engaged, and community buttons
        Button dockedB = binding.docked;
        Button engagedB = binding.engaged;
        Button communityB = binding.community;

        //If someone goes back to the auto section set the score numbers to what they set earlier
        if(submitted){
            submitInfo.setText("Success!");
            submitInfo.setBackgroundColor(Color.parseColor("#51f542"));
            submitInfo.setTextSize(17);
        }
        topScoreNum.setText("" + topCount);
        midScoreNum.setText("" + midCount);
        lowScoreNum.setText("" + lowCount);
        if(!community){
            communityB.setBackgroundColor(Color.parseColor("#3E3E3E"));
        } else {
            communityB.setTextColor(Color.parseColor("#000000"));
            communityB.setBackgroundColor(Color.parseColor("#51f542"));
        }
        if(docked){
            dockedB.setTextColor(Color.parseColor("#000000"));
            dockedB.setBackgroundColor(Color.parseColor("#51f542"));
        }else{
            dockedB.setBackgroundColor(Color.parseColor("#3E3E3E"));
        }
        if(engaged){
            engagedB.setTextColor(Color.parseColor("#000000"));
            engagedB.setBackgroundColor(Color.parseColor("#51f542"));
        }else{
            engagedB.setBackgroundColor(Color.parseColor("#3E3E3E"));
        }
        if(switchedView && submitted){
            submitInfo.setText("Resubmit?");
            submitInfo.setTextSize(15);
            submitInfo.setTextColor(Color.parseColor("#000000"));
            submitInfo.setBackgroundColor(Color.parseColor("#51f542"));
        }

        Spinner spinnerTeams = root.findViewById(R.id.teamNumI);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(root.getContext(), R.array.teams, R.layout.custom_spinner);
        adapter.setDropDownViewResource(R.layout.custom_spinner);
        spinnerTeams.setAdapter(adapter);

        communityB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(community){
                    community = false;
                    communityB.setTextColor(Color.parseColor("#FFFFFF"));
                    communityB.setBackgroundColor(Color.parseColor("#3E3E3E"));
                } else {
                    community = true;
                    communityB.setTextColor(Color.parseColor("#000000"));
                    communityB.setBackgroundColor(Color.parseColor("#51f542"));
                }

            }
        });

        //When the submit button is clicked save the input as a string
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoutName = scoutNameI.getText().toString();
                teamNum = teamNumI.getSelectedItem().toString();
                matchNum = matchNumI.getText().toString();
                System.out.println("Success! Scout name: " + scoutName + "Match Number: " + matchNum + ". Team Number: " + teamNum + ".");
                submitInfo.setText("Success!");
                submitInfo.setTextColor(Color.parseColor("#000000"));
                submitInfo.setBackgroundColor(Color.parseColor("#51f542"));
                submitInfo.setTextSize(17);
                submitted = true;
                saveAuto(topCount, midCount, lowCount, scoutName, teamNum, matchNum, docked, engaged, community);
            }
        });

        //Docked and engaged button "communication"
        engagedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View){
                if(!docked&&!engaged){
                    engaged = true;
                    engagedB.setBackgroundColor(Color.parseColor("#51f542"));
                    engagedB.setTextColor(Color.parseColor("#000000"));
                } else if (engaged&&!docked) {
                    engaged = false;
                    engagedB.setTextColor(Color.parseColor("#FFFFFF"));
                    engagedB.setBackgroundColor(Color.parseColor("#3E3E3E"));
                } else if (docked&&!engaged) {
                    engaged = true;
                    docked = false;
                    engagedB.setBackgroundColor(Color.parseColor("#51f542"));
                    engagedB.setTextColor(Color.parseColor("#000000"));
                    dockedB.setBackgroundColor(Color.parseColor("#3E3E3E"));
                    dockedB.setTextColor(Color.parseColor("#FFFFFF"));
                }

            }
        });
        dockedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View){
                if(!docked&&!engaged){
                    docked = true;
                    dockedB.setTextColor(Color.parseColor("#000000"));
                    dockedB.setBackgroundColor(Color.parseColor("#51f542"));
                } else if (engaged&&!docked) {
                    docked = true;
                    engaged = false;
                    dockedB.setTextColor(Color.parseColor("#000000"));
                    dockedB.setBackgroundColor(Color.parseColor("#51f542"));
                    engagedB.setTextColor(Color.parseColor("#FFFFFF"));
                    engagedB.setBackgroundColor(Color.parseColor("#3E3E3E"));
                } else if (docked&&!engaged) {
                    docked = false;
                    dockedB.setTextColor(Color.parseColor("#FFFFFF"));
                    dockedB.setBackgroundColor(Color.parseColor("#3E3E3E"));
                }

            }
        });

        // Top score button functionality
        topPlusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topCount > 8){
                    topCount = 0;
                    topScoreNum.setText("" + topCount);
                    System.out.println("" + topCount);
                } else {
                    topCount++;
                    topScoreNum.setText("" + topCount);
                    System.out.println("" + topCount);
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
                } else {
                    midCount--;
                }
                midScoreNum.setText("" + midCount);
                System.out.println(midCount);
            }
        });

        // Low score button functionality
        lowPlusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowCount > 8){
                    lowCount = 0;
                } else {
                    lowCount++;
                }
                lowScoreNum.setText("" + lowCount);
                System.out.println(lowCount);

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
        saveAuto(topCount, midCount, lowCount, scoutName, teamNum, matchNum, docked, engaged, community);
        switchedView = true;
        super.onDestroyView();
        binding = null;
        System.out.println("Top count: " + topCount);
    }
}