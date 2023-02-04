package com.team2137.chargedupscouting;

import org.json.JSONException;
import org.json.JSONObject;

class MatchData {
    private static final String PREFIX_DATA = "&#~";
    private static final String PREFIX_COMMENTS = "$#~";
    private static final String DELIMITER = "~";
    private static final String COMMA = ",";

    // General
    String matchCompetition = "2022mibel";
    int matchTeam = 0;
    int matchTeamPosition = 0; // Position in spinner
    int matchNumber = 0;
    String scoutName = "";
    String tabletName = "";

    // Used by database, may be undefined.
    int uniqueID = -1;
    boolean uploaded = false;

    // Auto
    boolean autoTaxi = false;
    int autoLow = 0;
    int autoHigh = 0;
    int autoMiss = 0;

    //Teleop
    int teleopLow = 0;
    int teleopHigh = 0;
    int teleopMiss = 0;

    boolean climbAttempt = false;
    int climbTimerState = 0;

    boolean hangarL1 = false;
    int hangarL1TimeMillis = 0;
    boolean hangarL2 = false;
    int hangarL2TimeMillis = 0;
    boolean hangarL3 = false;
    int hangarL3TimeMillis = 0;
    boolean hangarL4 = false;
    int hangarL4TimeMillis = 0;

    int defenseSkill = -1;
    int defensePercent = 0;
    int fouls = 0;

    String matchComments = "";

    MatchData() { }

	String serializeDataCompact() {
		return  PREFIX_DATA
				+ MatchDataDatabaseHelper.DATABASE_VERSION + DELIMITER
				+ matchCompetition + DELIMITER
				+ matchTeam + DELIMITER
				+ matchNumber + DELIMITER

                + (autoTaxi ? 1 : 0) + DELIMITER
                + autoHigh + DELIMITER
                + autoLow + DELIMITER
                + autoMiss + DELIMITER

                + teleopHigh + DELIMITER
                + teleopLow + DELIMITER
                + teleopMiss + DELIMITER

                + (hangarL1 ? 1 : 0) + DELIMITER
                + ((double) hangarL1TimeMillis / 1000) + DELIMITER
                + (hangarL2 ? 1 : 0) + DELIMITER
                + ((double) hangarL2TimeMillis / 1000) + DELIMITER
                + (hangarL3 ? 1 : 0) + DELIMITER
                + ((double) hangarL3TimeMillis / 1000) + DELIMITER
                + (hangarL4 ? 1 : 0) + DELIMITER
                + ((double) hangarL4TimeMillis / 1000) + DELIMITER

                + (defenseSkill == 0 ? 1 : 0) + DELIMITER
                + defenseSkill + DELIMITER
                + defensePercent + DELIMITER

                + fouls + DELIMITER


                + tabletName + DELIMITER
                + scoutName + "\n"
        + "\n";
	}

    String serializeCommentsCompact() {
        //TODO Filter out tildes.
        return PREFIX_COMMENTS
                + MatchDataDatabaseHelper.DATABASE_VERSION + DELIMITER
                + matchCompetition + DELIMITER
                + matchTeam + DELIMITER
                + matchNumber + DELIMITER
                + matchComments + DELIMITER
                + tabletName + DELIMITER
                + scoutName + "\n";
    }

    static String createMatchCSVHeader() {
        return    "Competitition" + COMMA
                + "Team" + COMMA
                + "Match" + COMMA

                + "autoTaxi" + COMMA
                + "autoHigh" + COMMA
                + "autoLow" + COMMA
                + "autoMiss" + COMMA

                + "teleopHigh" + COMMA
                + "teleopLow" + COMMA
                + "teleopMiss" + COMMA

                + "hangarL1" + COMMA
                + "hangarL1Time" + COMMA
                + "hangarL2" + COMMA
                + "hangarL2Time" + COMMA
                + "hangarL3" + COMMA
                + "hangarL3Time" + COMMA
                + "hangarL4" + COMMA
                + "hangarL4Time" + COMMA

                + "defensePlayed" + COMMA
                + "defenseSkill"+ COMMA
                + "defensePercent"+ COMMA

                + "fouls"+ COMMA

                + "tabletName" + COMMA
                + "scoutName" + COMMA
                + "\n";
	}

    String serializeDataCSV() {
        return    matchCompetition + COMMA
                + matchTeam + COMMA
                + matchNumber + COMMA

                + (autoTaxi ? 1 : 0) + COMMA
                + autoHigh + COMMA
                + autoLow + COMMA
                + autoMiss + COMMA

                + teleopHigh + COMMA
                + teleopLow + COMMA
                + teleopMiss + COMMA

                + (hangarL1 ? 1 : 0) + COMMA
                + ((double) hangarL1TimeMillis / 1000) + COMMA
                + (hangarL2 ? 1 : 0) + COMMA
                + ((double) hangarL2TimeMillis / 1000) + COMMA
                + (hangarL3 ? 1 : 0) + COMMA
                + ((double) hangarL3TimeMillis / 1000) + COMMA
                + (hangarL4 ? 1 : 0) + COMMA
                + ((double) hangarL4TimeMillis / 1000) + COMMA

                + (defenseSkill == 0 ? 1 : 0) + COMMA
                + defenseSkill + COMMA
                + defensePercent + COMMA

                + fouls + COMMA

                + tabletName + COMMA
                + scoutName + "\n";
	}

    static String createCommentsCSVHeader() {
        return "Competitition" + COMMA
                + "Team" + COMMA
                + "Match" + COMMA
                + "Comments" + COMMA
                + "Tablet Name" + COMMA
                + "Scout Name" + "\n";
    }

    String serializeCommentsCSV() {
        return    matchCompetition + COMMA
                + matchTeam + COMMA
                + matchNumber + COMMA
                + matchComments + COMMA
                + tabletName + COMMA
                + scoutName + "\n";
    }

    String serializeData() {
        try {
            //UNUSED
			//Didn't bother updating, due to above comment
            JSONObject output = new JSONObject();

            output.put("matchCompetition", matchCompetition);
            output.put("matchTeam", matchTeam);
            output.put("matchNumber", matchNumber);


            return output.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    String serializeComments() {
        try {
            //UNUSED
			//Didn't bother updating, due to above comment
            JSONObject output = new JSONObject();

            output.put("matchComments", matchComments);

            return output.toString();
        } catch (JSONException e) {
            return "";
        }
    }
}