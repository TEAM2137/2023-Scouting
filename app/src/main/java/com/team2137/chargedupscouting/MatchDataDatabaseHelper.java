package com.team2137.chargedupscouting;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.Dictionary;
import java.util.Hashtable;

public class MatchDataDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MatchDataDBHelper";

    // If you change the database schema, you must increment the database version.
    // Version 1: Initial
    // Version 2: Added Uploaded and Comments
    // Version 3: Converted Booleans to Ints
	// Version 10: New Game - Power Up
	// Version 11: Power Up - Added Climb Complete 'boolean'
	// Version 12: Power Up - Fixed Uploading
	// Version 13: Power Up - Added Booleans for Auto Switch and Scale Scored
    // Version 20: Destionation Deep Space Presented by The Boeing Company
    // Version 30: Infinite Recharge
    // Version 40: Rapid React
    // Version 41: Rapid React - Added Auto/Teleop Miss
    public static final int DATABASE_VERSION = 41;
    private static final String DATABASE_NAME = "FeedReader.db";

    private static final String TABLE_NAME                               = "matchData";
    private static final String COLUMN_NAME_ID                           = "uniqueID";
    private static final String COLUMN_NAME_COMPETITION                  = "matchCompetition";
    private static final String COLUMN_NAME_MATCH_NUMBER                 = "matchNumber";
    private static final String COLUMN_NAME_TEAM_ID                      = "matchTeamID";

    private static final String COLUMN_NAME_AUTO_TAXI                    = "autoTaxi";
    private static final String COLUMN_NAME_AUTO_HIGH                    = "autoHigh";
    private static final String COLUMN_NAME_AUTO_LOW                     = "autoLow";
    private static final String COLUMN_NAME_AUTO_MISS                    = "autoMiss";

    private static final String COLUMN_NAME_TELEOP_HIGH                  = "teleopHigh";
    private static final String COLUMN_NAME_TELEOP_LOW                   = "teleopLow";
    private static final String COLUMN_NAME_TELEOP_MISS                  = "teleopMiss";

    private static final String COLUMN_NAME_HANGAR_L1                    = "hangarL1";
    private static final String COLUMN_NAME_HANGAR_L1_TIME               = "hangarL1Time";
    private static final String COLUMN_NAME_HANGAR_L2                    = "hangarL2";
    private static final String COLUMN_NAME_HANGAR_L2_TIME               = "hangarL2Time";
    private static final String COLUMN_NAME_HANGAR_L3                    = "hangarL3";
    private static final String COLUMN_NAME_HANGAR_L3_TIME               = "hangarL3Time";
    private static final String COLUMN_NAME_HANGAR_L4                    = "hangarL4";
    private static final String COLUMN_NAME_HANGAR_L4_TIME               = "hangarL4Time";

    private static final String COLUMN_NAME_POST_DEFENSE_PLAYED          = "postDefensePlayed";
    private static final String COLUMN_NAME_POST_DEFENSE_SKILL           = "postDefenseSkill";
    private static final String COLUMN_NAME_POST_DEFENSE_TIME            = "postDefenseTime";

    private static final String COLUMN_NAME_POST_FOULS                   = "postFouls";

    private static final String COLUMN_NAME_MATCH_COMMENTS               = "matchComments";
    private static final String COLUMN_NAME_UPLOADED                     = "uploaded";
    private static final String COLUMN_NAME_TABLET_NAME                  = "tabletName";
    private static final String COLUMN_NAME_SCOUT_NAME                   = "scoutName";

    private static final String SQL_CREATE_TABLE =
			"CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID                           + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_COMPETITION                  + " TEXT,"    +
                    COLUMN_NAME_MATCH_NUMBER                 + " INTEGER," +
                    COLUMN_NAME_TEAM_ID                      + " INTEGER," +

                    COLUMN_NAME_AUTO_TAXI                    + " INTEGER," +
                    COLUMN_NAME_AUTO_HIGH                    + " INTEGER," +
                    COLUMN_NAME_AUTO_LOW                     + " INTEGER," +
                    COLUMN_NAME_AUTO_MISS                    + " INTEGER," +

                    COLUMN_NAME_TELEOP_HIGH                  + " INTEGER," +
                    COLUMN_NAME_TELEOP_LOW                   + " INTEGER," +
                    COLUMN_NAME_TELEOP_MISS                  + " INTEGER," +

                    COLUMN_NAME_HANGAR_L1                    + " INTEGER," +
                    COLUMN_NAME_HANGAR_L1_TIME               + " INTEGER," +
                    COLUMN_NAME_HANGAR_L2                    + " INTEGER," +
                    COLUMN_NAME_HANGAR_L2_TIME               + " INTEGER," +
                    COLUMN_NAME_HANGAR_L3                    + " INTEGER," +
                    COLUMN_NAME_HANGAR_L3_TIME               + " INTEGER," +
                    COLUMN_NAME_HANGAR_L4                    + " INTEGER," +
                    COLUMN_NAME_HANGAR_L4_TIME               + " INTEGER," +

                    COLUMN_NAME_POST_DEFENSE_PLAYED          + " INTEGER," +
                    COLUMN_NAME_POST_DEFENSE_SKILL           + " INTEGER," +
                    COLUMN_NAME_POST_DEFENSE_TIME            + " INTEGER," +

                    COLUMN_NAME_POST_FOULS                   + " INTEGER," +

                    COLUMN_NAME_MATCH_COMMENTS               + " TEXT,"    +
                    COLUMN_NAME_UPLOADED                     + " INTEGER," +
                    COLUMN_NAME_TABLET_NAME                  + " TEXT,"    +
                    COLUMN_NAME_SCOUT_NAME                   + " TEXT"     +")";

    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SQL_WHERE_ID =
            COLUMN_NAME_ID + " = ?";

    private static final String SQL_WHERE_NOT_UPLOADED =
            COLUMN_NAME_UPLOADED + " = 0";

    public MatchDataDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // Returns an alarmID.
    public void addMatch(MatchData item) {
        ContentValues values = new ContentValues();
        if(item.uniqueID != -1)
			values.put(COLUMN_NAME_ID, item.uniqueID);
		values.put(COLUMN_NAME_COMPETITION, item.matchCompetition);
		values.put(COLUMN_NAME_MATCH_NUMBER, item.matchNumber);
		values.put(COLUMN_NAME_TEAM_ID, item.matchTeam);

		values.put(COLUMN_NAME_AUTO_TAXI, item.autoTaxi);
		values.put(COLUMN_NAME_AUTO_HIGH, item.autoHigh);
		values.put(COLUMN_NAME_AUTO_LOW, item.autoLow);
		values.put(COLUMN_NAME_AUTO_MISS, item.autoMiss);

		values.put(COLUMN_NAME_TELEOP_HIGH, item.teleopHigh);
		values.put(COLUMN_NAME_TELEOP_LOW, item.teleopHigh);
		values.put(COLUMN_NAME_TELEOP_MISS, item.teleopMiss);

		values.put(COLUMN_NAME_HANGAR_L1, item.hangarL1);
		values.put(COLUMN_NAME_HANGAR_L1_TIME, item.hangarL1TimeMillis);
        values.put(COLUMN_NAME_HANGAR_L2, item.hangarL2);
        values.put(COLUMN_NAME_HANGAR_L2_TIME, item.hangarL2TimeMillis);
        values.put(COLUMN_NAME_HANGAR_L3, item.hangarL3);
        values.put(COLUMN_NAME_HANGAR_L3_TIME, item.hangarL3TimeMillis);
        values.put(COLUMN_NAME_HANGAR_L4, item.hangarL4);
        values.put(COLUMN_NAME_HANGAR_L4_TIME, item.hangarL4TimeMillis);

        values.put(COLUMN_NAME_POST_DEFENSE_PLAYED, item.defenseSkill > 0);
        values.put(COLUMN_NAME_POST_DEFENSE_SKILL, item.defenseSkill);
        values.put(COLUMN_NAME_POST_DEFENSE_TIME, item.defensePercent);

        values.put(COLUMN_NAME_POST_FOULS, item.fouls);

        values.put(COLUMN_NAME_MATCH_COMMENTS, item.matchComments);
		values.put(COLUMN_NAME_TABLET_NAME, item.tabletName);
		values.put(COLUMN_NAME_SCOUT_NAME, item.scoutName);
		values.put(COLUMN_NAME_UPLOADED, false);

		getWritableDatabase().insert(TABLE_NAME, null, values);

    }

    @Nullable
    private long[] getAllMatchIDs() {
        SQLiteCursor result = (SQLiteCursor) getReadableDatabase().query(true, TABLE_NAME, new String[] {COLUMN_NAME_ID},
				null, null, null, null, null, null);

        if(result.getCount() > 0) {
            long[] value = new long[result.getCount()];
            result.moveToFirst();
            do {
                value[result.getPosition()] = result.getLong(result.getColumnIndex(COLUMN_NAME_ID));
            } while (result.moveToNext());
            return value;
        } else {
            return new long[0];
        }
    }

    @Nullable
    private long[] getAllUnuploadedMatchIDs() {
		SQLiteCursor result = (SQLiteCursor) getReadableDatabase().query(true, TABLE_NAME, new String[]{COLUMN_NAME_ID},
				SQL_WHERE_NOT_UPLOADED, null, null, null, null, null);
		if(result.getCount() > 0) {
			long[] value = new long[result.getCount()];
			result.moveToFirst();
			do {
				value[result.getPosition()] = result.getLong(result.getColumnIndex(COLUMN_NAME_ID));
			} while (result.moveToNext());
			return value;
		} else {
			return new long[0];
		}
    }

    public int getMatchCount() {
        return getAllMatchIDs().length;
    }

    public int getUnuploadedMatchCount() {
        return getAllUnuploadedMatchIDs().length;
    }

    public void removeAllMatchDatas() {
        removeMatchDatas(getAllMatchIDs());
    }

    Dictionary<Long, MatchData> getAllMatches() {
        long[] ids = getAllMatchIDs();
        Hashtable<Long, MatchData> result = new Hashtable<>();
        if(ids != null) {
            for (long id : ids) {
                result.put(id, getMatchData(id));
            }
        }
        return result;
    }

    Dictionary<Long, MatchData> getAllUnuploadedMatches() {
        long[] ids = getAllUnuploadedMatchIDs();
        Hashtable<Long, MatchData> result = new Hashtable<>();
        if(ids != null) {
            for (long id : ids) {
                result.put(id, getMatchData(id));
            }
        }
        return result;
    }

    Dictionary<Long, MatchData> getMatchFromID(long id) {
        Hashtable<Long, MatchData> result = new Hashtable<>();
        result.put(id, getMatchData(id));
        return result;
    }

    @Nullable
    private MatchData getMatchData(long id) {
        SQLiteCursor result = (SQLiteCursor) getReadableDatabase().query(true, TABLE_NAME,
                new String[] {COLUMN_NAME_ID, COLUMN_NAME_COMPETITION, COLUMN_NAME_MATCH_NUMBER,
                        COLUMN_NAME_TEAM_ID, COLUMN_NAME_MATCH_COMMENTS,

                        COLUMN_NAME_AUTO_TAXI,
                        COLUMN_NAME_AUTO_HIGH,
                        COLUMN_NAME_AUTO_LOW,
                        COLUMN_NAME_AUTO_MISS,

                        COLUMN_NAME_TELEOP_HIGH,
                        COLUMN_NAME_TELEOP_LOW,
                        COLUMN_NAME_TELEOP_MISS,

                        COLUMN_NAME_HANGAR_L1,
                        COLUMN_NAME_HANGAR_L1_TIME,
                        COLUMN_NAME_HANGAR_L2,
                        COLUMN_NAME_HANGAR_L2_TIME,
                        COLUMN_NAME_HANGAR_L3,
                        COLUMN_NAME_HANGAR_L3_TIME,
                        COLUMN_NAME_HANGAR_L4,
                        COLUMN_NAME_HANGAR_L4_TIME,

                        COLUMN_NAME_POST_DEFENSE_PLAYED,
                        COLUMN_NAME_POST_DEFENSE_SKILL,
                        COLUMN_NAME_POST_DEFENSE_TIME,

                        COLUMN_NAME_POST_FOULS,

                        COLUMN_NAME_UPLOADED,
                        COLUMN_NAME_TABLET_NAME,
                        COLUMN_NAME_SCOUT_NAME},
                SQL_WHERE_ID, new String[] {String.valueOf(id)},
                null, null, null, "1");
        if(result.getCount() > 0) {
            result.moveToFirst();
            MatchData data = new MatchData();

            data.uniqueID                          = result.getInt(result.getColumnIndex(COLUMN_NAME_ID));
            data.uploaded                          = result.getInt(result.getColumnIndex(COLUMN_NAME_UPLOADED)) > 0;
            data.matchCompetition                  = result.getString(result.getColumnIndex(COLUMN_NAME_COMPETITION));
            data.matchTeam                         = result.getInt(result.getColumnIndex(COLUMN_NAME_TEAM_ID));
            data.matchNumber                       = result.getInt(result.getColumnIndex(COLUMN_NAME_MATCH_NUMBER));

            data.autoTaxi                          = result.getInt(result.getColumnIndex(COLUMN_NAME_AUTO_TAXI)) == 1;
            data.autoHigh                          = result.getInt(result.getColumnIndex(COLUMN_NAME_AUTO_HIGH));
            data.autoLow                           = result.getInt(result.getColumnIndex(COLUMN_NAME_AUTO_LOW));
            data.autoMiss                          = result.getInt(result.getColumnIndex(COLUMN_NAME_AUTO_MISS));

            data.teleopHigh                        = result.getInt(result.getColumnIndex(COLUMN_NAME_TELEOP_HIGH));
            data.teleopLow                         = result.getInt(result.getColumnIndex(COLUMN_NAME_TELEOP_LOW));
            data.teleopMiss                        = result.getInt(result.getColumnIndex(COLUMN_NAME_TELEOP_MISS));

            data.hangarL1                          = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L1)) == 1;
            data.hangarL1TimeMillis                = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L1_TIME));
            data.hangarL2                          = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L2)) == 1;
            data.hangarL2TimeMillis                = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L2_TIME));
            data.hangarL3                          = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L3)) == 1;
            data.hangarL3TimeMillis                = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L3_TIME));
            data.hangarL4                          = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L4)) == 1;
            data.hangarL4TimeMillis                = result.getInt(result.getColumnIndex(COLUMN_NAME_HANGAR_L4_TIME));

            data.defenseSkill                      = result.getInt(result.getColumnIndex(COLUMN_NAME_POST_DEFENSE_SKILL));
            data.defensePercent                    = result.getInt(result.getColumnIndex(COLUMN_NAME_POST_DEFENSE_TIME));

            data.fouls                             = result.getInt(result.getColumnIndex(COLUMN_NAME_POST_FOULS));

            data.matchComments                     = result.getString(result.getColumnIndex(COLUMN_NAME_MATCH_COMMENTS));
            data.tabletName                        = result.getString(result.getColumnIndex(COLUMN_NAME_TABLET_NAME));
            data.scoutName                         = result.getString(result.getColumnIndex(COLUMN_NAME_SCOUT_NAME));

            return data;
        } else {
            return null;
        }
    }

    public void removeMatchData(long id) {
        removeMatchData(String.valueOf(id));
    }

    private void removeMatchDatas(long[] ids) {
        String[] idStrings = new String[ids.length];
        for(int i = 0; i < ids.length; i++) {
            idStrings[i] = String.valueOf(ids[i]);
        }
        removeMatchDatas(idStrings);
    }

    private void removeMatchData(String id) {
        getWritableDatabase().delete(TABLE_NAME, SQL_WHERE_ID, new String[] { id });
    }

    private void removeMatchDatas(String[] ids) {
        for (String i : ids) {
            removeMatchData(i);
        }
    }

    private void uploadMatchData(long id) {
        MainActivity.instance.get().smsHelper.sendSMS(getMatchData(id));
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_UPLOADED, 1);
        getWritableDatabase().update(TABLE_NAME, values, COLUMN_NAME_ID +" = '"+ id + "'", null);
    }

    public void uploadMatchData(String id) {
        uploadMatchData(Long.parseLong(id));
    }

    // WARNING This may reupload some entries.
    public void uploadAllMatchData() {
        long[] ids = getAllMatchIDs();
        if (ids != null) {
            for (long id : ids) {
                uploadMatchData(id);
            }
        }
    }

    public void uploadAllUnuploadedMatchData() {
        long[] ids = getAllUnuploadedMatchIDs();
        if (ids != null) {
            for (long id : ids) {
                uploadMatchData(id);
            }
        }
    }
}
