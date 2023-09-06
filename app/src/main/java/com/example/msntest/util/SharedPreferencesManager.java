package com.example.msntest.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String SHARED_PREF_NAME = "LoginStatusPrefs";
    private static final String KEY_IS_LOGIN_SUCCESS = "isLoginSuccess";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SharedPreferencesManager instance;

    private SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public void saveLoginSuccess(boolean isLoginSuccess) {
        editor.putBoolean(KEY_IS_LOGIN_SUCCESS, isLoginSuccess);
        editor.apply();
    }

    public boolean isLoginSuccess() {
        return sharedPreferences.getBoolean(KEY_IS_LOGIN_SUCCESS, false);
    }

    public void clearLoginStatus() {
        editor.remove(KEY_IS_LOGIN_SUCCESS);
        editor.apply();
    }
}
