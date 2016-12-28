package com.abhishek.findingfalcone.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.abhishek.findingfalcone.utils.Constants;

/**
 * Created by abhishek on 22/12/16.
 */

public class Pref {

    private Context mContext;

    public Pref(Context context) {

        this.mContext = context;
    }


    public String getString(String key, String defaultvalue) {

        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        try {
            String value = settings.getString(key, defaultvalue);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public void put(String key, String value) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }



    public boolean isContains(String key) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        if (settings.contains(key)) {
            return true;
        } else {
            return false;
        }

    }

    public void delete(String key) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }


}
