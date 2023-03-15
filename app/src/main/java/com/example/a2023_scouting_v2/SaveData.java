package com.example.a2023_scouting_v2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SaveData {

    public static int topCountA = 0;
    public static int midCountA = 0;
    public static int lowCountA = 0;
    public static String scoutName = "N/A";
    public static String teamNum = "N/A";
    public static String matchNum = "N/A";
    public static boolean dockedA = false;
    public static boolean engagedA = false;
    public static boolean communityA = false;
    public static int topCountD = 0;
    public static int midCountD = 0;
    public static int lowCountD = 0;
    public static int linkCount = 0;
    public static boolean coopD = false;

    public static boolean engagedE = false;
    public static boolean dockedE = false;
    public static boolean parkedE = false;
    public static boolean noneE = false;

    public static void saveAuto (int high, int mid, int low, String scout, String team, String match, Boolean docked, Boolean engaged, Boolean community) {
        topCountA = high;
        midCountA = mid;
        lowCountA = low;
        scoutName = scout;
        teamNum = team;
        matchNum = match;
        dockedA = docked;
        engagedA = engaged;
        communityA = community;
    }

    public static void saveDriver (int top, int mid, int low, int links, boolean coop) {
        topCountD = top;
        midCountD = mid;
        lowCountD = low;
        linkCount = links;
        coopD = coop;
    }

    public static void saveEndgame (boolean engaged, boolean docked, boolean parked, boolean none) {
        engagedE = engaged;
        dockedE = docked;
        parkedE = parked;
        noneE = none;
    }

    public static List<Integer> getDriverInt() {
        return Arrays.asList(topCountD, midCountD, lowCountD, linkCount);
    }

    public static List<String> getStrings(){
        return Arrays.asList(teamNum);
    }

    public static List<Boolean> getEndgameButtons() {
        return Arrays.asList(engagedE, dockedE, parkedE, noneE);
    }

    public static boolean getDriverCoop() {
        return coopD;
    }

    public static String getTeam() {
        return teamNum;
    }

    public static String getDataS () {
        return "MIKet2" + "," +
                teamNum + "," +
                matchNum + "," +
                convertBool(communityA) + "," +
                topCountA + "," +
                midCountA + "," +
                lowCountA + "," +
                convertBool(dockedA) + "," +
                convertBool(engagedA) + "," +
                topCountD + "," +
                midCountD + "," +
                lowCountD + "," +
                linkCount + "," +
                convertBool(coopD) + "," +
                convertBool(dockedE) + "," +
                convertBool(engagedE) + "," +
                convertBool(parkedE) + "," +
                convertBool(noneE) + "," +
                "Blue 3" + "," +
                scoutName;
    }

    private static int convertBool(boolean value){
        int newValue;
        if(value) {
            newValue = 1;
        } else {
            newValue = 0;
        }

        return newValue;
    }
}
