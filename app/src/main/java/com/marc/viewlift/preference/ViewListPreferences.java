package com.marc.viewlift.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


public class ViewListPreferences {
    private static String TAG;
    public ViewListPreferences() {
        TAG = getClass().getName();
    }

    private static final String PREFS_NAME = "ViewListPreference";

    public static String getLastTimeLogIn(Context context) {
        Log.d(TAG,"getLastTimeLogIn()");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return prefs.getString("lastLogInTime","00:00:00");
    }
    public static void saveLastTimeLogIn(Context context, String loggin) {
        Log.d(TAG,"savetLastTimeLogIn()");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString("lastLogInTime",loggin);
        editor.commit();
    }
 }
