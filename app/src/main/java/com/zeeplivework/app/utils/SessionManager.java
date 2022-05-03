package com.zeeplivework.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeeplivework.app.activity.WalletActivity;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "zeeplive";
    // All Shared Preferences Keys
    public static final String COUNTRY_NAME = "country_name";
    public static final String COUNTRY_CODE = "country_code";
    public static final String CURRENCY_CODE = "currency_code";
    public static final String NAME = "name";
    public static final String GENDER = "gender";

    // Constructor
    public SessionManager(Context context) {
        try {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        } catch (Exception e) {
        }
    }



    public void setCountryName(String countryName) {
        editor.putString(COUNTRY_NAME, countryName);
        editor.apply();
    }

    public String getCountryName() {
        return pref.getString(COUNTRY_NAME, "null");
    }


    public void setCountryCode(String countryCode) {
        editor.putString(COUNTRY_CODE, countryCode);
        editor.apply();
    }

    public String getCountryCode() {
        return pref.getString(COUNTRY_CODE, "null");
    }

    public void setCurrencyCode(String currencyCode) {
        editor.putString(CURRENCY_CODE, currencyCode);
        editor.apply();
    }


    public String getCurrencyCode() {
        return pref.getString(CURRENCY_CODE, "null");
    }





}