package com.team2137.rapidreactscouting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class DebugActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static WeakReference<DebugActivity> instance;

    boolean tablet;

    private ListView listViewDatabase;

    private SMSHelper smsHelper;
    public MatchDataDatabaseHelper matchDataDatabaseHelper;
    private SharedPreferences sharedPreferences;

    Button buttonUploadUnuploaded;
    Button buttonUploadAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Do theme stuff first.
        sharedPreferences = this.getSharedPreferences("main", Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString(getResources().getString(R.string.pref_key_theme), "AppTheme.Blue");
        String tabletName = sharedPreferences.getString(getResources().getString(R.string.pref_tablet_name), "S0");
        showToast(tabletName, Toast.LENGTH_SHORT);
        Log.i(TAG, "THEME:"+theme);
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        getSupportActionBar().setTitle(getString(R.string.app_debug_name)); // set the top title
        instance = new WeakReference<>(this);

        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        tablet = dpWidth >= 600;

        smsHelper = new SMSHelper(this);
        matchDataDatabaseHelper = new MatchDataDatabaseHelper(this);

        listViewDatabase = (ListView) findViewById(R.id.listViewDatabase);
        final Button buttonSaveCSV = (Button) findViewById(R.id.buttonSaveCSV);
        Button buttonDeleteData = (Button) findViewById(R.id.buttonDeleteData);
        Button buttonSwitchTeam = (Button) findViewById(R.id.buttonSwitchTeam);
        EditText editTextTabletName = (EditText) findViewById(R.id.editTextTabletName);
        editTextTabletName.setText(tabletName);

        buttonSaveCSV.setText(String.format(getResources().getString(R.string.button_save_csv_format),
                matchDataDatabaseHelper.getMatchCount()));

        if (!tablet) {
            buttonUploadUnuploaded = (Button) findViewById(R.id.buttonUploadUnuploaded);
            buttonUploadAll = (Button) findViewById(R.id.buttonUploadAll);
            buttonUploadUnuploaded.setText(String.format(getResources().getString(R.string.button_upload_data_format),
                    matchDataDatabaseHelper.getUnuploadedMatchCount()));
            buttonUploadAll.setText(String.format(getResources().getString(R.string.button_upload_data_format),
                    matchDataDatabaseHelper.getMatchCount()));
        }

        final DebugDatabaseListViewAdapter listViewAdapter = new DebugDatabaseListViewAdapter(matchDataDatabaseHelper.getAllMatches());
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.database_listview_header, listViewDatabase, false);
        listViewDatabase.addHeaderView(header, null, false);
        listViewDatabase.setAdapter(listViewAdapter);
        listViewDatabase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                MatchData data = (MatchData) listViewAdapter.getItem(position-1);
                if(tablet) {
                    CSVHelper.saveToCSVDownloads(data);
                    DebugActivity.this.showToast("Saving entry: "+data.uniqueID, Toast.LENGTH_SHORT);
                } else {
                    smsHelper.sendSMS(data);
                    DebugActivity.this.showToast("Uploading entry: "+data.uniqueID, Toast.LENGTH_SHORT);
                }
            }
        });

        if (!tablet) {
            buttonUploadUnuploaded.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "UploadUnuploaded");
                    DebugActivity.this.showToast("Uploading unuploaded data...", Toast.LENGTH_LONG);
                    matchDataDatabaseHelper.uploadAllUnuploadedMatchData();
                    buttonUploadAll.setText(String.format(getResources().getString(R.string.button_upload_data_format),
                            matchDataDatabaseHelper.getUnuploadedMatchCount()));
                    buttonUploadUnuploaded.setText(String.format(getResources().getString(R.string.button_upload_data_format),
                            matchDataDatabaseHelper.getMatchCount()));
                    buttonSaveCSV.setText(String.format(getResources().getString(R.string.button_save_csv_format),
                            matchDataDatabaseHelper.getMatchCount()));
                }
            });

            buttonUploadAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "UploadAll");
                    DebugActivity.this.showToast("Uploading ALL data...", Toast.LENGTH_LONG);
                    matchDataDatabaseHelper.uploadAllMatchData();
                    buttonUploadAll.setText(String.format(getResources().getString(R.string.button_upload_data_format),
                            matchDataDatabaseHelper.getUnuploadedMatchCount()));
                    buttonUploadUnuploaded.setText(String.format(getResources().getString(R.string.button_upload_data_format),
                            matchDataDatabaseHelper.getMatchCount()));
                    buttonSaveCSV.setText(String.format(getResources().getString(R.string.button_save_csv_format),
                            matchDataDatabaseHelper.getMatchCount()));
                }
            });
        }

        buttonSwitchTeam.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View v) {
                Log.i(TAG, "SwitchTeam");
                switch(sharedPreferences.getString(getResources().getString(R.string.pref_key_theme), "AppTheme.Blue")) {
                    case "AppTheme.Red":
                        Log.i(TAG, "SwitchToBlue");
                        sharedPreferences.edit().putString(getResources().getString(R.string.pref_key_theme), "AppTheme.Blue").commit();
                        DebugActivity.this.showToast("Switched team to Blue", Toast.LENGTH_LONG);
                        break;
                    case "AppTheme.Blue":
                    default:
                        Log.i(TAG, "SwitchToRed");
                        sharedPreferences.edit().putString(getResources().getString(R.string.pref_key_theme), "AppTheme.Red").commit();
                        DebugActivity.this.showToast("Switched team to Red", Toast.LENGTH_LONG);
                        break;
                }
            }
        });

        editTextTabletName.addTextChangedListener(new TextWatcher() {
            private CharSequence value;
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    sharedPreferences.edit().putString(getResources().getString(R.string.pref_tablet_name), value.toString()).commit();
                    showToast(sharedPreferences.getString(getResources().getString(R.string.pref_tablet_name), "S0"), Toast.LENGTH_SHORT);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                    MainActivity.instance.get().currentMatch.tabletName = value.toString();
                }
                Log.i(TAG, value.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                value = charSequence;
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

        buttonSaveCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "SaveCSV");
                new AlertDialog.Builder(DebugActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getResources().getString(R.string.dialog_save_csv_format_title))
                        .setMessage(getResources().getString(R.string.dialog_save_csv_format_message))
                        .setPositiveButton(getResources().getString(R.string.dialog_yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        CSVHelper.saveToCSVDownloads(matchDataDatabaseHelper);
                                    }
                                })
                        .setNegativeButton(getResources().getString(R.string.dialog_no), null)
                        .show();


            }
        });

        buttonDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DebugActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getResources().getString(R.string.dialog_delete_title))
                        .setMessage(getResources().getString(R.string.dialog_delete_message))
                        .setPositiveButton(getResources().getString(R.string.dialog_yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DebugActivity.this.matchDataDatabaseHelper.removeAllMatchDatas();
                                    }
                                })
                        .setNegativeButton(getResources().getString(R.string.dialog_no), null)
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void showToast(String message, int length) {
        Toast.makeText(this, message, length).show();
    }
}
