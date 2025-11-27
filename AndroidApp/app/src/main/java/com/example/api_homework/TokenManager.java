package com.example.api_homework;

import android.content.Context;

public class TokenManager {
    private static final String PREF_NAME = "my_prefs";
    private static final String KEY_TOKEN = "jwt_token";

    public static void saveToken(Context context, String token) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_TOKEN, token)
                .apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getString(KEY_TOKEN, null);
    }

    public static void clearToken(Context context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_TOKEN)
                .apply();
    }
}

