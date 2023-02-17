package com.example.a2023_scouting_v2;

import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData extends AppCompatActivity {

    //Get all variables for the auto page
    public int topScoreA = 0;
    public int midScoreA = 0;
    public int lowScoreA = 0;
    public boolean dockedA = false;
    public boolean engagedA = false;
    public boolean community = false;
    public String scoutName = "";
    public String teamNum = "";
    public String matchNum = "";

    //all variables for the tele op page
    public int topScoreD = 0;
    public int midScoreD = 0;
    public int lowScoreD = 0;
    public int linksD = 0;
    public boolean coopD = false;

    //get all variables for the endgame page
    public boolean engagedE = false;
    public boolean dockedE = false;
    public boolean parkedE = false;
    public boolean noneE = false;
    public String buttonClicked = "";

    String finalendvalue = "";

    public void saveData(){
        System.out.println(
                String.valueOf(topScoreA) +
                String.valueOf(lowScoreA) +
                String.valueOf(dockedA) +
                String.valueOf(engagedA) +
                String.valueOf(scoutName) +
                String.valueOf(matchNum) +
                String.valueOf(teamNum) +
                String.valueOf(topScoreD) +
                String.valueOf(midScoreD) +
                String.valueOf(lowScoreD) +
                String.valueOf(linksD) +
                String.valueOf(coopD) +
                String.valueOf(engagedE) +
                String.valueOf(dockedE) +
                String.valueOf(parkedE) +
                String.valueOf(noneE) +
                String.valueOf(buttonClicked)
        );
        if(buttonClicked == ""){
            finalendvalue = (""+topScoreA) + "," + (""+midScoreA) + "," + (""+lowScoreA) + "," + scoutName + "," + "Unavailable" ;
        } else {
            finalendvalue = (""+topScoreA) + "," + (""+midScoreA) + "," + (""+lowScoreA) + "," + scoutName + "," + buttonClicked ;
        }

        try {
            String [] content = finalendvalue.split(",");
            File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/matches" + ".csv")));
            CSVWriter writer = new CSVWriter(new FileWriter(file, true));

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created new file at: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/matches" + ".csv"));
            }

            writer.writeNext(content);
            System.out.println("Saved data. " + content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
