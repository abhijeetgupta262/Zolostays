package com.zolostays.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is used to save and fetch the shared preferences data.
 */
public class PrefHelper {
    private static final String TAG = PrefHelper.class.getSimpleName();

    private static final String fileName = "Zolo.pref";

    private static PrefHelper mPrefHelper;
    private SharedPreferences mSharedPrefs;

    private PrefHelper(Context context) {
        mSharedPrefs = context.getSharedPreferences(PrefHelper.fileName, Activity.MODE_PRIVATE);
    }

    public static PrefHelper getInstance(Context context) {
        if (mPrefHelper == null) {
            mPrefHelper = new PrefHelper(context);
        }
        return mPrefHelper;
    }

    //Clear Preferences data permanently
    public static void clearPreferences(Context context) {
        PrefHelper.getInstance(context).mSharedPrefs.edit().clear().commit();
    }

    public void writePreference(String key, String value) {
        SharedPreferences.Editor e = mSharedPrefs.edit();
        e.putString(key, value);
        e.commit();
    }

    private void writePreference(String key, int value) {
        SharedPreferences.Editor e = mSharedPrefs.edit();
        e.putInt(key, value);
        e.commit();
    }

    public void writePreference(String key, boolean value) {
        SharedPreferences.Editor e = mSharedPrefs.edit();
        e.putBoolean(key, value);
        e.commit();
    }

    public void writePreference(String key, long value) {
        SharedPreferences.Editor e = mSharedPrefs.edit();
        e.putLong(key, value);
        e.commit();
    }

    public SharedPreferences getPrefrence() {
        return mSharedPrefs;
    }

    public String readPreference(String key) {
        return mSharedPrefs.getString(key, "");
    }

    public String readPreference(String key, String defValue) {
        return mSharedPrefs.getString(key, defValue);
    }

    private int readPreference(String key, int defaultValue) {
        return mSharedPrefs.getInt(key, defaultValue);
    }

    public boolean readPreference(String key, boolean defaultValue) {
        return mSharedPrefs.getBoolean(key, defaultValue);
    }

    public long readPreference(String key, long defaultValue) {
        return mSharedPrefs.getLong(key, defaultValue);
    }

    public boolean writePreferenceSet(String key, HashSet<String> value) {
        SharedPreferences.Editor e = mSharedPrefs.edit();
        e.putStringSet(key, value);
        return e.commit();
    }

    public Set<String> readPreferenceSet(String key, HashSet<String> defaultValue) {
        return mSharedPrefs.getStringSet(key, defaultValue);
    }

    /**
     * This method s used to check the key exist in shared preferences.
     *
     * @param key - Key
     * @return - True if key exist otherwise false
     */
    public boolean containPreference(String key) {
        return mSharedPrefs.contains(key);
    }

    /**
     * clear the shared preference storage.
     *
     * @param keyList - remove the keys that are in list.
     */
    public boolean clearPreferences(final List<String> keyList) {
        final SharedPreferences.Editor editor = mSharedPrefs.edit();
        for (final String key : mSharedPrefs.getAll().keySet()) {
            if (keyList.contains(key)) {
                editor.remove(key);
            }
            return editor.commit();
        }
        return false;
    }

    /**
     * clear the shared preference storage.
     *
     * @param key - remove the key.
     */
    public boolean clearPreferences(String key) {
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.remove(key);
        return editor.commit();
    }
}
