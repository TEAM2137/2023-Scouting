package com.team2137.chargedupscouting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AutoFragment extends ScoutFragment {

	// Declare Buttons

    Button buttonAutoTaxi;

    Button buttonAutoHigh;
    Button buttonAutoLow;

    Button buttonAutoMiss;

    // 0 = not started
    // 1 = running
    // 2 = complete

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_auto, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
		// Get buttons from R and assign them locally to a button class of their names.

        buttonAutoTaxi = view.findViewById(R.id.buttonAutoTaxi);

        buttonAutoHigh = view.findViewById(R.id.buttonAutoHigh);
        buttonAutoLow  = view.findViewById(R.id.buttonAutoLow);

        buttonAutoMiss = view.findViewById(R.id.buttonAutoMiss);


        // SUPER BUTTON ON-CLICK LISTENER BLOCK!!

        buttonAutoTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.autoTaxi = true;
                buttonAutoTaxi.setSelected(true);
            }
        });
        buttonAutoTaxi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.instance.get().currentMatch.autoTaxi = false;
                buttonAutoTaxi.setSelected(false);
                return true;
            }
        });

        buttonAutoHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.autoHigh++;
                buttonAutoHigh.setText(String.format(getResources().getString(R.string.button_high_goal), MainActivity.instance.get().currentMatch.autoHigh));
                buttonAutoHigh.setSelected(true);
            }
        });
        buttonAutoHigh.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (MainActivity.instance.get().currentMatch.autoHigh - 1 > 0) {
                    MainActivity.instance.get().currentMatch.autoHigh--;
                    buttonAutoHigh.setSelected(true);
                } else {
                    MainActivity.instance.get().currentMatch.autoHigh = 0;
                    buttonAutoHigh.setSelected(false);
                }
                buttonAutoHigh.setText(String.format(getResources().getString(R.string.button_high_goal), MainActivity.instance.get().currentMatch.autoHigh));
                return true;
            }
        });

        buttonAutoLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.autoLow++;
                buttonAutoLow.setText(String.format(getResources().getString(R.string.button_low_goal), MainActivity.instance.get().currentMatch.autoLow));
                buttonAutoLow.setSelected(true);
            }
        });
        buttonAutoLow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (MainActivity.instance.get().currentMatch.autoLow - 1 > 0) {
                    MainActivity.instance.get().currentMatch.autoLow--;
                    buttonAutoLow.setSelected(true);
                } else {
                    MainActivity.instance.get().currentMatch.autoLow = 0;
                    buttonAutoLow.setSelected(false);
                }
                buttonAutoLow.setText(String.format(getResources().getString(R.string.button_low_goal), MainActivity.instance.get().currentMatch.autoLow));
                return true;
            }
        });

        buttonAutoMiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.autoMiss++;
                buttonAutoMiss.setText(String.format(getResources().getString(R.string.button_miss), MainActivity.instance.get().currentMatch.autoMiss));
                buttonAutoMiss.setSelected(true);
            }
        });
        buttonAutoMiss.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (MainActivity.instance.get().currentMatch.autoMiss - 1 > 0) {
                    MainActivity.instance.get().currentMatch.autoMiss--;
                    buttonAutoMiss.setSelected(true);
                } else {
                    MainActivity.instance.get().currentMatch.autoMiss = 0;
                    buttonAutoMiss.setSelected(false);
                }
                buttonAutoMiss.setText(String.format(getResources().getString(R.string.button_miss), MainActivity.instance.get().currentMatch.autoMiss));
                return true;
            }
        });

        reloadDisplays();
    }

    @Override
    public void reloadDisplays() {
        buttonAutoTaxi.setSelected(MainActivity.instance.get().currentMatch.autoTaxi);

        buttonAutoHigh.setSelected(MainActivity.instance.get().currentMatch.autoHigh > 0);
        buttonAutoHigh.setText(String.format(getResources().getString(R.string.button_high_goal), MainActivity.instance.get().currentMatch.autoHigh));

        buttonAutoLow.setSelected(MainActivity.instance.get().currentMatch.autoLow > 0);
        buttonAutoLow.setText(String.format(getResources().getString(R.string.button_low_goal), MainActivity.instance.get().currentMatch.autoLow));

        buttonAutoMiss.setSelected(MainActivity.instance.get().currentMatch.autoMiss > 0);
        buttonAutoMiss.setText(String.format(getResources().getString(R.string.button_miss), MainActivity.instance.get().currentMatch.autoMiss));
    }
}
