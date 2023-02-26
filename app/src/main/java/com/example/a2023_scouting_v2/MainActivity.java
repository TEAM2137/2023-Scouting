package com.example.a2023_scouting_v2;

import static com.example.a2023_scouting_v2.SaveData.getDataS;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.a2023_scouting_v2.databinding.ActivityMainBinding;
import com.example.a2023_scouting_v2.ui.driver.DriverFragment;
import com.example.a2023_scouting_v2.ui.auto.AutoFragment;
import com.example.a2023_scouting_v2.ui.endgame.EndgameFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    static AutoFragment auto = new AutoFragment();
    static DriverFragment teleOp = new DriverFragment();
    static EndgameFragment endgame = new EndgameFragment();
    private ActivityMainBinding binding;
    public String buttonClicked = "";
    public static String finalendvalue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoFragment auto = new AutoFragment();
        DriverFragment teleop = new DriverFragment();
        EndgameFragment endgame = new EndgameFragment();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner spinnerTeams = findViewById(R.id.teamNumI);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.teams, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeams.setAdapter(adapter);

        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Change the selected item's text color
                ((TextView) view).setTextColor(Color.rgb(255, 255, 255));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_auto, R.id.navigation_driver, R.id.navigation_endgame)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public static String getData() {
        System.out.println(getDataS());
        return getDataS();
        //return "2023Event" + "," + auto.teamNum + "," + auto.matchNum + "," + auto.community + "," +  auto.topCount + "," + auto.midCount + "," + auto.lowCount + "," + auto.docked + "," + auto.engaged + "," + teleOp.topCount + "," + teleOp.midCount + "," + teleOp.lowCount + "," + teleOp.linkCount + "," + teleOp.coop + "," + endgame.docked + "," + endgame.engaged + "," + endgame.parked + "," + endgame.none + "," + "Blue Dev" + "," + auto.scoutName;
    }

    public static void saveData(){
        try {
            File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/matches" + ".csv")));
            CSVWriter writer = new CSVWriter(new FileWriter(file, true));

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created new file at: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/matches" + ".csv"));
            }

            finalendvalue = getData();
            System.out.println(finalendvalue);
            String [] content = finalendvalue.split(",");

            writer.writeNext(content);
            System.out.println("Saved data to CSV file at " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/matches" + ".csv") + "\nData: " + content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}