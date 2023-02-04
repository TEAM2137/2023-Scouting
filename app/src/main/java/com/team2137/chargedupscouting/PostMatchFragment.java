package com.team2137.chargedupscouting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PostMatchFragment extends ScoutFragment {
    private static final String TAG = "PostMatchFragment";

    // Button Declarations

    private Button buttonPostDefenseSkill0;
    private Button buttonPostDefenseSkill1;
    private Button buttonPostDefenseSkill2;

    private Button buttonPostDefense25;
    private Button buttonPostDefense50;
    private Button buttonPostDefense75;
    private Button buttonPostDefense100;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_post_match, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get buttons from R and assign them locally to a button class of their name's.

        buttonPostDefenseSkill0 = view.findViewById(R.id.buttonPostDefenseSkill0);
        buttonPostDefenseSkill1 = view.findViewById(R.id.buttonPostDefenseSkill1);
        buttonPostDefenseSkill2 = view.findViewById(R.id.buttonPostDefenseSkill2);

        buttonPostDefense25     = view.findViewById(R.id.buttonPostDefense25);
        buttonPostDefense50     = view.findViewById(R.id.buttonPostDefense50);
        buttonPostDefense75     = view.findViewById(R.id.buttonPostDefense75);
        buttonPostDefense100    = view.findViewById(R.id.buttonPostDefense100);

        // SUPER BUTTON ON-CLICK LISTENER BLOCK!!

        buttonPostDefenseSkill0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.defenseSkill = 0;
                MainActivity.instance.get().currentMatch.defensePercent = 0;
                buttonPostDefenseSkill0.setSelected(true);
                buttonPostDefenseSkill1.setSelected(false);
                buttonPostDefenseSkill2.setSelected(false);
                buttonPostDefense25.setEnabled(false);
                buttonPostDefense50.setEnabled(false);
                buttonPostDefense75.setEnabled(false);
                buttonPostDefense100.setEnabled(false);
                buttonPostDefense25.setSelected(false);
                buttonPostDefense50.setSelected(false);
                buttonPostDefense75.setSelected(false);
                buttonPostDefense100.setSelected(false);
            }
        });

        buttonPostDefenseSkill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.defenseSkill = 1;
                buttonPostDefenseSkill0.setSelected(false);
                buttonPostDefenseSkill1.setSelected(true);
                buttonPostDefenseSkill2.setSelected(false);
                buttonPostDefense25.setEnabled(true);
                buttonPostDefense50.setEnabled(true);
                buttonPostDefense75.setEnabled(true);
                buttonPostDefense100.setEnabled(true);
            }
        });

        buttonPostDefenseSkill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.defenseSkill = 2;
                buttonPostDefenseSkill0.setSelected(false);
                buttonPostDefenseSkill1.setSelected(false);
                buttonPostDefenseSkill2.setSelected(true);
                buttonPostDefense25.setEnabled(true);
                buttonPostDefense50.setEnabled(true);
                buttonPostDefense75.setEnabled(true);
                buttonPostDefense100.setEnabled(true);
            }
        });

        buttonPostDefense25.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.instance.get().currentMatch.defenseSkill != -1 && MainActivity.instance.get().currentMatch.defenseSkill != 0) {
                    MainActivity.instance.get().currentMatch.defensePercent = 25;
                    buttonPostDefense25.setSelected(true);
                    buttonPostDefense50.setSelected(false);
                    buttonPostDefense75.setSelected(false);
                    buttonPostDefense100.setSelected(false);
                }
            }
        });

        buttonPostDefense50.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.instance.get().currentMatch.defenseSkill != -1 && MainActivity.instance.get().currentMatch.defenseSkill != 0) {
                    MainActivity.instance.get().currentMatch.defensePercent = 50;
                    buttonPostDefense25.setSelected(false);
                    buttonPostDefense50.setSelected(true);
                    buttonPostDefense75.setSelected(false);
                    buttonPostDefense100.setSelected(false);
                }
            }
        });

        buttonPostDefense75.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.instance.get().currentMatch.defenseSkill != -1 && MainActivity.instance.get().currentMatch.defenseSkill != 0) {
                    MainActivity.instance.get().currentMatch.defensePercent = 75;
                    buttonPostDefense25.setSelected(false);
                    buttonPostDefense50.setSelected(false);
                    buttonPostDefense75.setSelected(true);
                    buttonPostDefense100.setSelected(false);
                }
            }
        });

        buttonPostDefense100.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.instance.get().currentMatch.defenseSkill != -1 && MainActivity.instance.get().currentMatch.defenseSkill != 0) {
                    MainActivity.instance.get().currentMatch.defensePercent = 100;
                    buttonPostDefense25.setSelected(false);
                    buttonPostDefense50.setSelected(false);
                    buttonPostDefense75.setSelected(false);
                    buttonPostDefense100.setSelected(true);
                }
            }
        });
        
        reloadDisplays();
    }

    @Override
    public void reloadDisplays() {

        buttonPostDefenseSkill0.setSelected(MainActivity.instance.get().currentMatch.defenseSkill == 0);
        buttonPostDefenseSkill1.setSelected(MainActivity.instance.get().currentMatch.defenseSkill == 1);
        buttonPostDefenseSkill2.setSelected(MainActivity.instance.get().currentMatch.defenseSkill == 2);

        if (MainActivity.instance.get().currentMatch.defenseSkill != -1 && MainActivity.instance.get().currentMatch.defenseSkill != 0) {
            buttonPostDefense25.setEnabled(true);
            buttonPostDefense50.setEnabled(true);
            buttonPostDefense75.setEnabled(true);
            buttonPostDefense100.setEnabled(true);
            switch (MainActivity.instance.get().currentMatch.defensePercent) {
                case 0:
                    buttonPostDefense25.setSelected(false);
                    buttonPostDefense50.setSelected(false);
                    buttonPostDefense75.setSelected(false);
                    buttonPostDefense100.setSelected(false);
                    break;
                case 25:
                    buttonPostDefense25.setSelected(true);
                    buttonPostDefense50.setSelected(false);
                    buttonPostDefense75.setSelected(false);
                    buttonPostDefense100.setSelected(false);
                    break;
                case 50:
                    buttonPostDefense25.setSelected(false);
                    buttonPostDefense50.setSelected(true);
                    buttonPostDefense75.setSelected(false);
                    buttonPostDefense100.setSelected(false);
                    break;
                case 75:
                    buttonPostDefense25.setSelected(false);
                    buttonPostDefense50.setSelected(false);
                    buttonPostDefense75.setSelected(true);
                    buttonPostDefense100.setSelected(false);
                    break;
                case 100:
                    buttonPostDefense25.setSelected(false);
                    buttonPostDefense50.setSelected(false);
                    buttonPostDefense75.setSelected(false);
                    buttonPostDefense100.setSelected(true);
                    break;
            }

            buttonPostDefenseSkill0.setEnabled(true);
            buttonPostDefenseSkill1.setEnabled(true);
            buttonPostDefenseSkill2.setEnabled(true);
            buttonPostDefenseSkill0.setSelected(MainActivity.instance.get().currentMatch.defenseSkill == 0);
            buttonPostDefenseSkill1.setSelected(MainActivity.instance.get().currentMatch.defenseSkill == 1);
            buttonPostDefenseSkill2.setSelected(MainActivity.instance.get().currentMatch.defenseSkill == 2);
        } else {
            buttonPostDefense25.setEnabled(false);
            buttonPostDefense50.setEnabled(false);
            buttonPostDefense75.setEnabled(false);
            buttonPostDefense100.setEnabled(false);
            buttonPostDefense25.setSelected(false);
            buttonPostDefense50.setSelected(false);
            buttonPostDefense75.setSelected(false);
            buttonPostDefense100.setSelected(false);
        }

    }
}