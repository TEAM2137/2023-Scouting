package com.team2137.chargedupscouting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static WeakReference<MainActivity> instance;

    boolean tablet;

    private static final int FRAGMENT_AUTO = 1;
    private static final int FRAGMENT_TELEOP = 2;
    private static final int FRAGMENT_POST_MATCH = 3;

    private int currentFragment = 1;

    private int buttonSwitchModeUpperDest = 2;
    private int buttonSwitchModeLowerDest = 3;

    private TextView textViewMode;
    private TextView textViewScoutNum;

    private Button buttonSaveData;
    private Button buttonResetData;
    private Button buttonUploadData;
    private ImageView frcHeader;
    private Spinner spinnerCompetition;
    private Spinner spinnerTeams;
    private EditText editTextTeamOther;
    private EditText editTextMatches;
    private EditText editTextScoutName;
    private Button buttonSwitchModeUpper;
    private Button buttonSwitchModeLower;
    private ScrollView scrollViewMain;

    private FrameLayout frameLayoutFragmentContainer;

    private RelativeLayout layoutMain;

    private LinearLayout linearLayoutTeamNumber;

    private static final long TOUCH_DELAY = 1000;
    private long lastTouch = 0L;
    private int touchCount = 0;

    public MatchData currentMatch;

    public SMSHelper smsHelper;
    private MatchDataDatabaseHelper matchDataDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Do theme stuff first.
        SharedPreferences sharedPreferences = this.getSharedPreferences("main", Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString(getResources().getString(R.string.pref_key_theme), "AppTheme.Blue");
        final String tabletName = sharedPreferences.getString(getResources().getString(R.string.pref_tablet_name), "S0");
        Log.i(TAG, "THEME:"+theme);
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getSupportActionBar().setTitle(getString(R.string.app_long_name)); // set the top title
        } catch (Exception e) {

        }
        instance = new WeakReference<>(this);

        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        tablet = dpWidth >= 600;

/*        if (tablet) {
            displayToast("Tablet");
            Log.i(TAG, "Tablet");
        } else {
            displayToast("Not Tablet");
            Log.i(TAG, "Not Tablet");
        } */

        smsHelper = new SMSHelper(this);
        matchDataDatabaseHelper = new MatchDataDatabaseHelper(this);

        final String[] competitionIds = getResources().getStringArray(R.array.frc_comp_ids);

        // Get the views.
        buttonSaveData     = (Button) findViewById(R.id.buttonSaveData);
        buttonResetData    = (Button) findViewById(R.id.buttonResetData);
        frcHeader          = (ImageView) findViewById(R.id.frcHeader);
        spinnerCompetition = (Spinner) findViewById(R.id.spinnerCompetition);
        spinnerTeams       = (Spinner) findViewById(R.id.spinnerTeam);
        editTextTeamOther  = (EditText) findViewById(R.id.editTextTeamNumberOther);
        editTextMatches    = (EditText) findViewById(R.id.editTextMatchNumber);
        editTextScoutName    = (EditText) findViewById(R.id.editTextScoutName);
        buttonSwitchModeUpper   = (Button) findViewById(R.id.buttonSwitchModeUpper);
        buttonSwitchModeLower = (Button) findViewById(R.id.buttonSwitchModeLower);
        scrollViewMain     = (ScrollView) findViewById(R.id.scrollViewMain);
        frameLayoutFragmentContainer = (FrameLayout) findViewById(R.id.layoutFragment);
        layoutMain         = (RelativeLayout) findViewById(R.id.layoutMain);
        linearLayoutTeamNumber = (LinearLayout) findViewById(R.id.layoutTeamNumberOther);
        textViewScoutNum   = (TextView) findViewById(R.id.textViewScoutNum);

        textViewScoutNum.setText(tabletName);

        if (!tablet) {
            buttonUploadData = (Button) findViewById(R.id.buttonUploadData);
            buttonUploadData.setText(String.format(getResources().getString(R.string.button_upload_data_format), matchDataDatabaseHelper.getUnuploadedMatchCount()));
        }
//        displayToast(String.valueOf(matchDataDatabaseHelper.DATABASE_VERSION));

        // HERE


        // attempt at swipe code near total failure
//        layoutMain.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    int actionIndex = event.getActionIndex();
//                    int pointerID = event.getPointerId(actionIndex);
//                    float initialX = event.getHistoricalX(0);
//                    float currentX = event.getX();
//                    float initialY = event.getHistoricalY(0);
//                    float currentY = event.getY();
//                    Log.i(TAG, initialX + ", " + currentX + ", " + initialY + ", " + currentY + ", ");
//                    displayToast("hi");
//
//                }
//                displayToast("called");
//                return false;
//            }
//        });

        spinnerCompetition.setAdapter(ArrayAdapter.createFromResource(this, R.array.frc_comp_names, R.layout.dropdown_item));
        spinnerCompetition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentMatch.matchCompetition = competitionIds[i];

                Log.i(TAG, currentMatch.matchCompetition);

                int resourceTeamList = getResources().getIdentifier("frc_comp_"+competitionIds[i]+"_teams", "array", getPackageName());
                spinnerTeams.setAdapter(ArrayAdapter.createFromResource(MainActivity.this, resourceTeamList, R.layout.dropdown_item));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentMatch.matchTeamPosition = i;
                int resourceTeamIdList = getResources().getIdentifier("frc_comp_"+currentMatch.matchCompetition+"_team_ids", "array", getPackageName());
                int[] teamArray = getResources().getIntArray(resourceTeamIdList);
                if(0 == i) {
                    // NONE
                    currentMatch.matchTeam = 0;
                    linearLayoutTeamNumber.setVisibility(View.GONE);
                    Log.i(TAG, "Selected none.");
                } else if(teamArray.length-1 == i) {
                    // OTHER
                    currentMatch.matchTeam = 0;
                    linearLayoutTeamNumber.setVisibility(View.VISIBLE);
                    Log.i(TAG, "Selected other.");
                } else {
                    // TEAM
                    currentMatch.matchTeam = teamArray[i];
                    linearLayoutTeamNumber.setVisibility(View.GONE);
                    Log.i(TAG, "Selected "+ String.valueOf(i)+":"+currentMatch.matchTeam);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        editTextMatches.setFilters(new InputFilter[]{ new InputRangeFilter(1, 299)});

        editTextTeamOther.setFilters(new InputFilter[]{ new InputRangeFilter(1, 9999)});
        linearLayoutTeamNumber.setVisibility(View.GONE);

        editTextTeamOther.addTextChangedListener(new TextWatcher() {
            private CharSequence value;
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    currentMatch.matchTeam = Integer.parseInt(value.toString());
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Bad number, ignoring.");
                    currentMatch.matchTeam = 0;
                }
                Log.i(TAG, String.valueOf(currentMatch.matchTeam));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                value = charSequence;
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

        editTextMatches.addTextChangedListener(new TextWatcher() {
            private CharSequence value;
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    currentMatch.matchNumber = Integer.parseInt(value.toString());
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Bad number, ignoring.");
                    currentMatch.matchNumber = 0;
                }
                Log.i(TAG, String.valueOf(currentMatch.matchNumber));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                value = charSequence;
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

        editTextScoutName.addTextChangedListener(new TextWatcher() {
            private CharSequence value;
            @Override
            public void afterTextChanged(Editable editable) {
                currentMatch.scoutName = value.toString();
                Log.i(TAG, currentMatch.scoutName);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                value = charSequence;
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

        buttonSaveData.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if(currentMatch.matchTeam == 0) {
                    displayToast("Invalid team number!");
                    return true;
                }
                if(currentMatch.matchNumber == 0) {
                    displayToast("Invalid match number!");
                    return true;
                }
                if(currentMatch.scoutName == "") {
                    displayToast("Invalid scout name!");
                    return true;
                }
                if(currentMatch.defenseSkill == -1) {
                    displayToast("Invalid defense rating!");
                    return true;
                }
                currentMatch.tabletName = tabletName;
                matchDataDatabaseHelper.addMatch(currentMatch);
                CSVHelper csvHelper = new CSVHelper();
                csvHelper.saveToCSVDownloads(currentMatch);
                MatchData newMatchData = new MatchData();
                newMatchData.matchCompetition = currentMatch.matchCompetition;
                newMatchData.matchTeam = currentMatch.matchTeam;
                newMatchData.scoutName = currentMatch.scoutName;
                currentMatch = newMatchData;
                loadFragment(FRAGMENT_AUTO);
                reloadDisplays();
                displayToast("Match saved.");
                Log.i(TAG, "Match data saved");
                return true;
            }
        });

        buttonResetData.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                MatchData newMatchData = new MatchData();
                newMatchData.matchCompetition = currentMatch.matchCompetition;
                newMatchData.matchTeam = currentMatch.matchTeam;
                newMatchData.scoutName = currentMatch.scoutName;
                currentMatch = newMatchData;
                reloadDisplays();
                displayToast("Match data reset.");
                Log.i(TAG, "Match data reset");
                return true;
            }
        });

        if (!tablet) {
            buttonUploadData.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    matchDataDatabaseHelper.uploadAllUnuploadedMatchData();
                    displayToast("Uploaded match data.");
                    Log.i(TAG, "Uploaded match data");
                    reloadDisplays();
                    return true;
                }
            });
        }

        buttonSwitchModeUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(buttonSwitchModeUpperDest);
                scrollToTop();
            }
        });

        buttonSwitchModeLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(buttonSwitchModeLowerDest);
                scrollToTop();
            }
        });

        frcHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "tap");
                if((SystemClock.elapsedRealtime() - lastTouch) < TOUCH_DELAY) {
                    touchCount++;
                } else {
                    touchCount = 1;
                }
                lastTouch = SystemClock.elapsedRealtime();
                reloadDisplays();
            }
        });

        frcHeader.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "longtap");
                Log.i(TAG, SystemClock.elapsedRealtime()+"-"+lastTouch+"="+(SystemClock.elapsedRealtime()-lastTouch));
                if((SystemClock.elapsedRealtime() - lastTouch) < TOUCH_DELAY) {
                    Log.i(TAG, "taps:"+touchCount);
                    if(touchCount >= 3) {
                        Log.i(TAG, "moving");

                        Intent resultIntent = new Intent(MainActivity.this, DebugActivity.class);
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                        stackBuilder.addParentStack(DebugActivity.class);
                        stackBuilder.addNextIntent(resultIntent);
                        stackBuilder.startActivities();
                        finish();
                    }
                }
                return true;
            }
        });

        currentMatch = new MatchData();

        int resourceTeamList = getResources().getIdentifier("frc_comp_"+currentMatch.matchCompetition+"_teams", "array", getPackageName());
        spinnerTeams.setAdapter(ArrayAdapter.createFromResource(MainActivity.this, resourceTeamList, R.layout.dropdown_item));

//        reloadDisplays();
    }

    public void reCreate() {
        Bundle savedInstanceState = new Bundle();
        //this is important to save all your open states/fragment states
        onSaveInstanceState(savedInstanceState);
        //this is needed to release the resources
        super.onDestroy();

        //call on create where new theme is applied
        onCreate(savedInstanceState);//you can pass bundle arguments to skip your code/flows on this scenario
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            CSVHelper.trySaveToCSVOnTheGo(MainActivity.instance.get(), MainActivity.instance.get().matchDataDatabaseHelper);
            runTimer();
        }
    };
    private void runTimer() {
        handler.postDelayed(runnable, 5000);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Load the auto fragment to start.
        loadFragment(FRAGMENT_AUTO);
        //OnTheGo doesn't work on the Motorola Moto E, so just disable automatic writing.
        //runTimer();
    }

    private void loadFragment(int fragmentID) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        currentFragment = fragmentID;
        SharedPreferences sharedPreferences = this.getSharedPreferences("main", Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString(getResources().getString(R.string.pref_key_theme), "AppTheme.Blue");
        textViewMode = (TextView) findViewById(R.id.textViewMode);
        switch(fragmentID) {
            case FRAGMENT_AUTO:
                fragment = new AutoFragment();
                buttonSwitchModeUpper.setText(getResources().getString(R.string.button_switch_teleop));
                buttonSwitchModeUpperDest = FRAGMENT_TELEOP;
                buttonSwitchModeLower.setText(getResources().getString(R.string.button_switch_post_match));
                buttonSwitchModeLowerDest = FRAGMENT_POST_MATCH;
                textViewMode.setText(getResources().getText(R.string.heading_auto));
                buttonSwitchModeUpper.setVisibility(View.VISIBLE);
                buttonSwitchModeLower.setVisibility(View.GONE);
                buttonSaveData.setVisibility(View.GONE);
                break;
            case FRAGMENT_TELEOP:
                fragment = new TeleopFragment();
                buttonSwitchModeUpper.setText(getResources().getString(R.string.button_switch_auto));
                buttonSwitchModeUpperDest = FRAGMENT_AUTO;
                buttonSwitchModeLower.setText(getResources().getString(R.string.button_switch_post_match));
                buttonSwitchModeLowerDest = FRAGMENT_POST_MATCH;
                textViewMode.setText(getResources().getText(R.string.heading_teleop));
                buttonSwitchModeUpper.setVisibility(View.VISIBLE);
                buttonSwitchModeLower.setVisibility(View.VISIBLE);
                buttonSaveData.setVisibility(View.GONE);
                break;
            case FRAGMENT_POST_MATCH:
                fragment = new PostMatchFragment();
                buttonSwitchModeUpper.setText(getResources().getString(R.string.button_switch_auto));
                buttonSwitchModeUpperDest = FRAGMENT_AUTO;
                buttonSwitchModeLower.setText(getResources().getString(R.string.button_switch_teleop));
                buttonSwitchModeLowerDest = FRAGMENT_TELEOP;
                textViewMode.setText(getResources().getText(R.string.heading_post_match));
                buttonSwitchModeUpper.setVisibility(View.GONE);
                buttonSwitchModeLower.setVisibility(View.VISIBLE);
                buttonSaveData.setVisibility(View.VISIBLE);
                break;
            default:
                fragment = new Fragment();
        }

        ft.replace(R.id.layoutFragment, fragment);
        ft.commit();
    }

    private void reloadDisplays() {
        getCurrentFragment().reloadDisplays();

        spinnerCompetition.setSelection(((ArrayAdapter<String>) spinnerCompetition.getAdapter())
                .getPosition(currentMatch.matchCompetition));
        spinnerTeams.setSelection(currentMatch.matchTeamPosition);
		linearLayoutTeamNumber.setVisibility(View.VISIBLE);
		editTextTeamOther.setText(String.valueOf(currentMatch.matchTeam));
		linearLayoutTeamNumber.setVisibility(View.GONE);
        editTextMatches.setText(String.valueOf(currentMatch.matchNumber));
        if (!tablet) {
            buttonUploadData.setText(String.format(getResources().getString(R.string.button_upload_data_format),
                    matchDataDatabaseHelper.getUnuploadedMatchCount()));
        }
    }

    private ScoutFragment getCurrentFragment() {
        return (ScoutFragment) getSupportFragmentManager().findFragmentById(R.id.layoutFragment);
    }

    public void displayToast(String text, int duration) {
        Toast.makeText(this, text, duration).show();
    }

    public void pause(long length) {
        try {
            Thread.sleep(length);
        } catch (Exception e) {

        }
    }

    public void displayToast(String text) {
        displayToast(text, Toast.LENGTH_SHORT);
    }

    private void scrollToTop() {
        if (!tablet && this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            scrollViewMain.smoothScrollTo(0, frameLayoutFragmentContainer.getTop());
            View targetView = findViewById(R.id.layoutFragment);
            targetView.getParent().requestChildFocus(targetView, targetView);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
