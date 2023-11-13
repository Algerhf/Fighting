package com.cuibaby.eat;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */
public class SpUtils {
    private static SharedPreferences preferences;

    public static void init(Context context) {
        preferences = context.getSharedPreferences("eat", Context.MODE_PRIVATE);
    }

    public static String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public static void putString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public static void putInt(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public static boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public static void putLong(String key, long value) {
        preferences.edit().putLong(key, value).apply();
    }

    public static long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

}
