package com.example.company.sabborah.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mohamed Sayed on 11/11/2017.
 */

public class MySharedPreferences {
    public static final String MYPREF = "SABBORAH";
    public static final String TOKEN_KEY = "TOKEN";
    private static MySharedPreferences sharedPreferenceUtil;
    private Context context;

    public static MySharedPreferences getReference(Context context) {
        if (sharedPreferenceUtil == null) {
            sharedPreferenceUtil = new MySharedPreferences(context);
        }
        return sharedPreferenceUtil;
    }

    private MySharedPreferences() {
    }

    private MySharedPreferences(Context context) {
        this.context = context;
    }

    public SharedPreferences getSharedPreferences() {
        SharedPreferences settings = context.getSharedPreferences(MYPREF,
                Context.MODE_PRIVATE);
        return settings;
    }

    public void clearSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences.contains(TOKEN_KEY)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    public boolean isSharedPreferencesExists() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences.contains(TOKEN_KEY)) {
            return true;
        } else {
            return false;
        }
    }

    public void setToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token).apply();
        editor.commit();
    }

    public String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getString(TOKEN_KEY, "");
    }

}
