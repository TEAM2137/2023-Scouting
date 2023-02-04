package com.team2137.rapidreactscouting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TeleopFragment extends ScoutFragment {
    public static String TAG = "TeleopFragment";

    // Create Buttons

    private Button buttonTeleopHigh;
    private Button buttonTeleopLow;

    private Button buttonTeleopMiss;

    private Button buttonTeleopFouls;

    private ClimbingChronometer chronometerTeleopClimb;
    private Button buttonTeleopStartClimb;
    private Button buttonTeleopClimbL1;
    private Button buttonTeleopClimbL2;
    private Button buttonTeleopClimbL3;
    private Button buttonTeleopClimbL4;

    // 0 = not started
    // 1 = running
    // 2 = complete

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teleop, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Button declarations:

        buttonTeleopHigh       = view.findViewById(R.id.buttonTeleopHigh);
        buttonTeleopLow        = view.findViewById(R.id.buttonTeleopLow);

        buttonTeleopMiss       = view.findViewById(R.id.buttonTeleopMiss);

        buttonTeleopFouls      = view.findViewById(R.id.buttonTeleopFouls);

        chronometerTeleopClimb = view.findViewById(R.id.climbingChronometer);
        buttonTeleopStartClimb = view.findViewById(R.id.buttonTeleopStartClimb);
        buttonTeleopClimbL1    = view.findViewById(R.id.buttonTeleopL1);
        buttonTeleopClimbL2    = view.findViewById(R.id.buttonTeleopL2);
        buttonTeleopClimbL3    = view.findViewById(R.id.buttonTeleopL3);
        buttonTeleopClimbL4    = view.findViewById(R.id.buttonTeleopL4);


        // SUPER BUTTON ON-CLICK LISTENER BLOCK!!

        buttonTeleopHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.teleopHigh++;
                buttonTeleopHigh.setText(String.format(getResources().getString(R.string.button_high_goal), MainActivity.instance.get().currentMatch.teleopHigh));
                buttonTeleopHigh.setSelected(true);
            }
        });
        buttonTeleopHigh.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (MainActivity.instance.get().currentMatch.teleopHigh - 1 > 0) {
                    MainActivity.instance.get().currentMatch.teleopHigh--;
                    buttonTeleopHigh.setSelected(true);
                } else {
                    MainActivity.instance.get().currentMatch.teleopHigh = 0;
                    buttonTeleopHigh.setSelected(false);
                }
                buttonTeleopHigh.setText(String.format(getResources().getString(R.string.button_high_goal), MainActivity.instance.get().currentMatch.teleopHigh));
                return true;
            }
        });

        buttonTeleopLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.teleopLow++;
                buttonTeleopLow.setText(String.format(getResources().getString(R.string.button_low_goal), MainActivity.instance.get().currentMatch.teleopLow));
                buttonTeleopLow.setSelected(true);
            }
        });
        buttonTeleopLow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (MainActivity.instance.get().currentMatch.teleopLow - 1 > 0) {
                    MainActivity.instance.get().currentMatch.teleopLow--;
                    buttonTeleopLow.setSelected(true);
                } else {
                    MainActivity.instance.get().currentMatch.teleopLow = 0;
                    buttonTeleopLow.setSelected(false);
                }
                buttonTeleopLow.setText(String.format(getResources().getString(R.string.button_low_goal), MainActivity.instance.get().currentMatch.teleopLow));
                return true;
            }
        });

        buttonTeleopMiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.teleopMiss++;
                buttonTeleopMiss.setText(String.format(getResources().getString(R.string.button_miss), MainActivity.instance.get().currentMatch.teleopMiss));
                buttonTeleopMiss.setSelected(true);
            }
        });
        buttonTeleopMiss.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (MainActivity.instance.get().currentMatch.teleopMiss - 1 > 0) {
                    MainActivity.instance.get().currentMatch.teleopMiss--;
                    buttonTeleopMiss.setSelected(true);
                } else {
                    MainActivity.instance.get().currentMatch.teleopMiss = 0;
                    buttonTeleopMiss.setSelected(false);
                }
                buttonTeleopMiss.setText(String.format(getResources().getString(R.string.button_miss), MainActivity.instance.get().currentMatch.teleopMiss));
                return true;
            }
        });

        buttonTeleopFouls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.get().currentMatch.fouls++;
                buttonTeleopFouls.setText(String.format(getResources().getString(R.string.button_fouls), MainActivity.instance.get().currentMatch.fouls));
                buttonTeleopFouls.setSelected(true);
            }
        });
        buttonTeleopFouls.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (MainActivity.instance.get().currentMatch.fouls - 1 > 0) {
                    MainActivity.instance.get().currentMatch.fouls--;
                    buttonTeleopFouls.setSelected(true);
                } else {
                    MainActivity.instance.get().currentMatch.fouls = 0;
                    buttonTeleopFouls.setSelected(false);
                }
                buttonTeleopFouls.setText(String.format(getResources().getString(R.string.button_fouls), MainActivity.instance.get().currentMatch.fouls));
                return true;
            }
        });

        buttonTeleopStartClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.instance.get().currentMatch.climbTimerState == 0) {
                    chronometerTeleopClimb.resume();
                    MainActivity.instance.get().currentMatch.climbTimerState = 1;
                }
                MainActivity.instance.get().currentMatch.climbAttempt = true;
                buttonTeleopStartClimb.setSelected(true);
                buttonTeleopClimbL1.setEnabled(true);
                buttonTeleopClimbL2.setEnabled(true);
                buttonTeleopClimbL3.setEnabled(true);
                buttonTeleopClimbL4.setEnabled(true);

            }
        });
        buttonTeleopStartClimb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chronometerTeleopClimb.stop();
                chronometerTeleopClimb.reset();
                MainActivity.instance.get().currentMatch.climbTimerState = 0;
                MainActivity.instance.get().currentMatch.climbAttempt = false;
                buttonTeleopStartClimb.setSelected(false);
                buttonTeleopClimbL1.setEnabled(false);
                buttonTeleopClimbL2.setEnabled(false);
                buttonTeleopClimbL3.setEnabled(false);
                buttonTeleopClimbL4.setEnabled(false);
                buttonTeleopClimbL1.setSelected(false);
                buttonTeleopClimbL2.setSelected(false);
                buttonTeleopClimbL3.setSelected(false);
                buttonTeleopClimbL4.setSelected(false);
                MainActivity.instance.get().currentMatch.hangarL1 = false;
                MainActivity.instance.get().currentMatch.hangarL1TimeMillis = 0;
                MainActivity.instance.get().currentMatch.hangarL2 = false;
                MainActivity.instance.get().currentMatch.hangarL2TimeMillis = 0;
                MainActivity.instance.get().currentMatch.hangarL3 = false;
                MainActivity.instance.get().currentMatch.hangarL3TimeMillis = 0;
                MainActivity.instance.get().currentMatch.hangarL4 = false;
                MainActivity.instance.get().currentMatch.hangarL4TimeMillis = 0;
                return true;
            }
        });

        buttonTeleopClimbL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.instance.get().currentMatch.hangarL1) {
                    MainActivity.instance.get().currentMatch.hangarL1TimeMillis = (int) chronometerTeleopClimb.getCurrentTimeMilliseconds();
                }
                MainActivity.instance.get().currentMatch.hangarL1 = true;
                buttonTeleopClimbL1.setSelected(true);
            }
        });
        buttonTeleopClimbL1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.instance.get().currentMatch.hangarL1 = false;
                MainActivity.instance.get().currentMatch.hangarL1TimeMillis = 0;
                buttonTeleopClimbL1.setSelected(false);
                return true;
            }
        });

        buttonTeleopClimbL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.instance.get().currentMatch.hangarL2) {
                    MainActivity.instance.get().currentMatch.hangarL2TimeMillis = (int) chronometerTeleopClimb.getCurrentTimeMilliseconds();
                }
                MainActivity.instance.get().currentMatch.hangarL2 = true;
                buttonTeleopClimbL2.setSelected(true);
            }
        });
        buttonTeleopClimbL2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.instance.get().currentMatch.hangarL2 = false;
                MainActivity.instance.get().currentMatch.hangarL2TimeMillis = 0;
                buttonTeleopClimbL2.setSelected(false);
                return true;
            }
        });

        buttonTeleopClimbL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.instance.get().currentMatch.hangarL3) {
                    MainActivity.instance.get().currentMatch.hangarL3TimeMillis = (int) chronometerTeleopClimb.getCurrentTimeMilliseconds();
                }
                MainActivity.instance.get().currentMatch.hangarL3 = true;
                buttonTeleopClimbL3.setSelected(true);
            }
        });
        buttonTeleopClimbL3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.instance.get().currentMatch.hangarL3 = false;
                MainActivity.instance.get().currentMatch.hangarL3TimeMillis = 0;
                buttonTeleopClimbL3.setSelected(false);
                return true;
            }
        });

        buttonTeleopClimbL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.instance.get().currentMatch.hangarL4) {
                    MainActivity.instance.get().currentMatch.hangarL4TimeMillis = (int) chronometerTeleopClimb.getCurrentTimeMilliseconds();
                    MainActivity.instance.get().currentMatch.climbTimerState = 2;
                    chronometerTeleopClimb.pause();
                }
                MainActivity.instance.get().currentMatch.hangarL4 = true;
                buttonTeleopClimbL4.setSelected(true);
            }
        });
        buttonTeleopClimbL4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.instance.get().currentMatch.hangarL4 = false;
                MainActivity.instance.get().currentMatch.hangarL4TimeMillis = 0;
                buttonTeleopClimbL4.setSelected(false);
                return true;
            }
        });

        reloadDisplays();
    }

    @Override
    public void reloadDisplays() {
        buttonTeleopHigh.setSelected(MainActivity.instance.get().currentMatch.teleopHigh > 0);
        buttonTeleopHigh.setText(String.format(getResources().getString(R.string.button_high_goal), MainActivity.instance.get().currentMatch.teleopHigh));

        buttonTeleopLow.setSelected(MainActivity.instance.get().currentMatch.teleopLow > 0);
        buttonTeleopLow.setText(String.format(getResources().getString(R.string.button_low_goal), MainActivity.instance.get().currentMatch.teleopLow));

        buttonTeleopMiss.setSelected(MainActivity.instance.get().currentMatch.teleopMiss > 0);
        buttonTeleopMiss.setText(String.format(getResources().getString(R.string.button_miss), MainActivity.instance.get().currentMatch.teleopMiss));

        buttonTeleopFouls.setSelected(MainActivity.instance.get().currentMatch.fouls > 0);
        buttonTeleopFouls.setText((String.format(getResources().getString(R.string.button_fouls), MainActivity.instance.get().currentMatch.fouls)));

        buttonTeleopStartClimb.setSelected(MainActivity.instance.get().currentMatch.climbAttempt);
        buttonTeleopClimbL1.setEnabled(MainActivity.instance.get().currentMatch.climbAttempt);
        buttonTeleopClimbL2.setEnabled(MainActivity.instance.get().currentMatch.climbAttempt);
        buttonTeleopClimbL3.setEnabled(MainActivity.instance.get().currentMatch.climbAttempt);
        buttonTeleopClimbL4.setEnabled(MainActivity.instance.get().currentMatch.climbAttempt);

        buttonTeleopClimbL1.setSelected(MainActivity.instance.get().currentMatch.climbAttempt && MainActivity.instance.get().currentMatch.hangarL1);
        buttonTeleopClimbL2.setSelected(MainActivity.instance.get().currentMatch.climbAttempt && MainActivity.instance.get().currentMatch.hangarL2);
        buttonTeleopClimbL3.setSelected(MainActivity.instance.get().currentMatch.climbAttempt && MainActivity.instance.get().currentMatch.hangarL3);
        buttonTeleopClimbL4.setSelected(MainActivity.instance.get().currentMatch.climbAttempt && MainActivity.instance.get().currentMatch.hangarL4);
    }
}
