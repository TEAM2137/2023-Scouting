package com.team2137.rapidreactscouting;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.ArrayList;

public class SMSHelper {
    private static final String TAG = "SMSHelper";

    private static final boolean SEND_COMPACT = true;
    public static final String TEST_MESSAGE =
            "According to all known laws of aviation," +
            "there is no way a bee should be able to fly." +
            "Its wings are too small to get its fat little body" +
            "off the ground." +
            "The bee, of course, flies anyway." +
            "Because bees don't care what humans think is impossible.";

    private final Context context;
    private final SmsManager smsManager;

    public SMSHelper(Context context) {
        this.context = context;
        this.smsManager = SmsManager.getDefault();
    }

    public void sendSMS(MatchData data) {
        sendSMS(SEND_COMPACT ? data.serializeDataCompact() : data.serializeData());
        sendSMS(SEND_COMPACT ? data.serializeCommentsCompact() : data.serializeComments());
    }

    public void sendSMS(MatchData data, String number) {
        sendSMS(SEND_COMPACT ? data.serializeDataCompact() : data.serializeData(), number);
        sendSMS(SEND_COMPACT ? data.serializeCommentsCompact() : data.serializeComments(), number);
    }

    private void sendSMS(String message) {
        sendSMS(message, context.getResources().getString(R.string.number_twilio));
    }

    private void sendSMS(String message, String number) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.SEND_SMS)) {
                Log.e(TAG, "Permission denied. Closing application.");
                System.exit(0);
            } else {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        } else {
            Log.i(TAG, "Got permission to send text.");
            ArrayList<String> messageParts = smsManager.divideMessage(message);

            smsManager.sendMultipartTextMessage(number, null, messageParts, null, null);
        }
    }
}
