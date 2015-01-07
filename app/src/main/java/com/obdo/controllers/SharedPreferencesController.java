package com.obdo.controllers;

import android.app.Activity;
import android.content.SharedPreferences;
import com.obdo.R;

/**
 * Controller that saves and loads information on the Shared Preferences
 * @author Marcus Vinícius de Carvalho
 * @since 01/07/2014
 * @version 1.0
 */
public class SharedPreferencesController {
    /**
     * Activity that calls this class
     * @since 01/07/2014
     */
    private static Activity activity;

    /**
     * Enum with keys for the shared preferences file
     * @since 01/07/2014
     */
    public enum SharedPreferencesUserInformationENUM {
        PHONENUMBER, UID;

        public String toString() {
            switch(this){
                case PHONENUMBER: return activity.getApplicationContext().getString(R.string.phoneNumber);
                case UID: return activity.getApplicationContext().getString(R.string.uid);
                default: throw new IllegalArgumentException();
            }
        }
    }

    public SharedPreferencesController(Activity activity) {
        this.activity = activity;
    }

    /**
     * Save Shared Preferences with user phone number
     * @param phoneNumber User phone number
     * @since 01/07/2014
     */
    public void savePhoneNumber(String phoneNumber) {
        SharedPreferences settings = activity.getSharedPreferences(activity.getApplicationContext().getString(R.string.preferencesFileName), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(SharedPreferencesUserInformationENUM.PHONENUMBER.toString(),phoneNumber);
        editor.commit();
    }

    /**
     * Save Shared Preferences with smartphone UID
     * @param uid smartphone UID
     * @since 01/07/2014
     */
    public void saveUID(String uid) {
        SharedPreferences settings = activity.getSharedPreferences(activity.getApplicationContext().getString(R.string.preferencesFileName), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(SharedPreferencesUserInformationENUM.UID.toString(), uid);
        editor.commit();
    }

    /**
     * Load Shared Preferences with user phonenumber
     * @return User phonenumber
     * @since 01/07/2014
     */
    public String loadPhoneNumber() {
        SharedPreferences settings = activity.getSharedPreferences(activity.getApplicationContext().getString(R.string.preferencesFileName), 0);
        return settings.getString(SharedPreferencesUserInformationENUM.PHONENUMBER.toString(), null);
    }

    /**
     * Load Shared Preferences with smartphone UID
     * @return Smartphone UID
     * @since 01/07/2014
     */
    public String loadUID() {
        SharedPreferences settings = activity.getSharedPreferences(activity.getApplicationContext().getString(R.string.preferencesFileName), 0);
        return settings.getString(SharedPreferencesUserInformationENUM.UID.toString(), null);
    }
}
