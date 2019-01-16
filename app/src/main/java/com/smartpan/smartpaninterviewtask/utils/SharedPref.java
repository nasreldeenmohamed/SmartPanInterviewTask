package com.smartpan.smartpaninterviewtask.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nasr on 1/12/2017.
 */

public class SharedPref {

    public static final String MyPREFERENCES = "MyPrefs";

    SharedPreferences sharedpreferences;
    Context context;
    SharedPreferences.Editor editor;

    public SharedPref(Context con) {
        context = con;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void setString(String key, String value) {
        editor.putString(key, value).commit();
    }

    public String getString(String key) {
        return sharedpreferences.getString(key, "");
    }

    public String getUserInfo() {
        return sharedpreferences.getString("user", "-1");
    }

    public void setUserInfo(String ID) {
        editor.putString("user", ID).commit();
    }

    public void setInteger(String key, int value) {
        editor.putInt(key, value).commit();
    }

    public int getInteger(String key) {
        return sharedpreferences.getInt(key, 0);
    }

    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key) {
        return sharedpreferences.getBoolean(key, false);
    }

}
