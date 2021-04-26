package com.scenera.nicesecurityapplib.utilities;

import android.util.Log;

import com.scenera.nicesecurityapplib.BuildConfig;


public class AppLog {

    public static final boolean isDebug = BuildConfig.DEBUG;
    private static final int LOGCAT_MAX_LENGTH = 3950;
    public static final void Log(String tag, String message) {
        if (isDebug) {
            Log.i(tag, message + "");
        }
    }
    public static void handleException(String tag, Exception e) {
        if (isDebug) {
            if (e != null) {
                Log.i(tag, e.getMessage() + "");
                e.printStackTrace();
            }
        }
    }

    public static final void handleThrowable(String tag, Throwable t) {
        if (isDebug) {
            if (t != null) {
                Log.d(tag, t + "");
            }
        }
    }
    public static void LogMaxSize(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            while (message.length() > LOGCAT_MAX_LENGTH) {
                int substringIndex = message.lastIndexOf(",", LOGCAT_MAX_LENGTH);
                if (substringIndex == -1)
                    substringIndex = LOGCAT_MAX_LENGTH;
                Log.d(TAG, message.substring(0, substringIndex));
                message = message.substring(substringIndex).trim();
            }
            Log.d(TAG, message);
        }

    }

}
