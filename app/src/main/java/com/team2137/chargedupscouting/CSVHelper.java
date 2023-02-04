package com.team2137.chargedupscouting;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import android.media.MediaScannerConnection;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * Created by eric on 3/21/2017.
 */

public class CSVHelper {
    private static final String TAG = "CSVHelper";

    private static Activity getContext() throws NullPointerException {
/*        if (DebugActivity.instance == null) {
            if (MainActivity.instance == null) {
                throw new NullPointerException("Neither DebugActivity nor MainActivity has an instance!");
            } else {
                return MainActivity.instance.get();
            }
        } else {
            return DebugActivity.instance.get();
        }*/

        if (MainActivity.instance == null) {
            throw new NullPointerException("Neither DebugActivity nor MainActivity has an instance!");
        } else {
            return MainActivity.instance.get();
        }
    }

    private static void saveToCSV(File directoryParent, MatchDataDatabaseHelper matchDataDatabaseHelper) { // All matches (Debug Screen)
//        String dateTime = new SimpleDateFormat("yy-MM-dd").format(new Date());
        File csvMatches = new File(directoryParent, "matches.csv");
        File csvComments = new File(directoryParent, "comments.csv");
        try {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                boolean makeMatches = false;
                boolean makeComments = false;
                if (!csvMatches.isFile()) {
                    makeMatches = true;
                    if (!csvMatches.createNewFile()) {
                        Log.w(TAG, "Failed to write file.");
                        return;
                    }
                }
                if (!csvComments.isFile()) {
                    makeComments = true;
                    if (!csvComments.createNewFile()) {
                        Log.w(TAG, "Failed to write file.");
                        return;
                    }
                }
                FileWriter csvMatchesWriter = new FileWriter(csvMatches, true);
                FileWriter csvCommentsWriter = new FileWriter(csvComments, true);
                if (makeMatches)
                    csvMatchesWriter.write(MatchData.createMatchCSVHeader());
                if (makeComments)
                    csvCommentsWriter.write(MatchData.createCommentsCSVHeader());
                for (MatchData i : Collections.list(matchDataDatabaseHelper.getAllMatches().elements())) {
                    csvMatchesWriter.write(i.serializeDataCSV());
                    csvCommentsWriter.write(i.serializeCommentsCSV());
                }
                Log.i(TAG, "Wrote logs! " + csvMatches.getAbsolutePath());
                Toast.makeText(getContext(), "Created CSVs!", Toast.LENGTH_SHORT);
                csvMatchesWriter.close();
                csvCommentsWriter.close();
                MediaScannerConnection.scanFile(getContext(), new String[] { csvMatches.getAbsolutePath() }, null, null);
                MediaScannerConnection.scanFile(getContext(), new String[] { csvComments.getAbsolutePath() }, null, null);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Log.w(TAG, "Permission failed for file writing.");
                } else {
                    ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    private static void saveToCSV(File directoryParent, MatchData data) { // All matches (Debug Screen)
        String dateTime = new SimpleDateFormat("yy-MM-dd").format(new Date());
        File csvMatches = new File(directoryParent, "matches.csv");
        File csvComments = new File(directoryParent, "comments.csv");
        try {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                boolean makeMatches = false;
                boolean makeComments = false;
                if (!csvMatches.isFile()) {
                    makeMatches = true;
                    if (!csvMatches.createNewFile()) {
                        Log.w(TAG, "Failed to write file.");
                        return;
                    }
                }
                if (!csvComments.isFile()) {
                    makeComments = true;
                    if (!csvComments.createNewFile()) {
                        Log.w(TAG, "Failed to write file.");
                        return;
                    }
                }
                FileWriter csvMatchesWriter = new FileWriter(csvMatches, true);
                FileWriter csvCommentsWriter = new FileWriter(csvComments, true);
                if (makeMatches)
                    csvMatchesWriter.write(MatchData.createMatchCSVHeader());
                if (makeComments)
                    csvCommentsWriter.write(MatchData.createCommentsCSVHeader());
                csvMatchesWriter.write(data.serializeDataCSV());
                csvCommentsWriter.write(data.serializeCommentsCSV());
                Log.i(TAG, "Wrote logs! " + csvMatches.getAbsolutePath());
                Toast.makeText(getContext(), "Created CSVs!", Toast.LENGTH_SHORT);
                csvMatchesWriter.close();
                csvCommentsWriter.close();
                MediaScannerConnection.scanFile(getContext(), new String[] { csvMatches.getAbsolutePath() }, null, null);
                MediaScannerConnection.scanFile(getContext(), new String[] { csvComments.getAbsolutePath() }, null, null);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Log.w(TAG, "Permission failed for file writing.");
                } else {
                    ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    static void saveToCSVDownloads(MatchDataDatabaseHelper matchDataDatabaseHelper) {
        saveToCSV(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), matchDataDatabaseHelper);
    }

    static void saveToCSVDownloads(MatchData data) {
        saveToCSV(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), data);
    }

    static void trySaveToCSVOnTheGo(Context context, MatchDataDatabaseHelper matchDataDatabaseHelper) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            File[] externalDirs = context.getExternalFilesDirs(null);
            if (externalDirs.length > 0) {
                Log.i(TAG, "DIR: " + externalDirs[0]);
                saveToCSV(externalDirs[0], matchDataDatabaseHelper);
            } else {
                Log.i(TAG, "NODIR");
            }
        }
    }
}
