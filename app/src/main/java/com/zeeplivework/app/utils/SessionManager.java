package com.zeeplivework.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeeplivework.app.activity.WalletActivity;
import com.zeeplivework.app.response.LoginResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    public static final String AREA_CODE = "area_code";
    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String ADDRESS = "address";
    public static final String BANK_ID = "bank_Id";
    public static final String BANK_NAME = "bank_name";
    public static final String CITY = "city";
    public static final String BANK_BRANCH = "bank_branch";
    public static final String LOCATION_ID = "location_id";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String PROFILE_ID = "profile_id";
    public static final String TOKEN_ID = "token_id";
    public static final String NAME = "name";
    public static final String FORM = "form";




    public SessionManager(Context context) {
        try {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        } catch (Exception e) {
        }
    }


    public void createLoginSession(LoginResponse result) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(TOKEN_ID, result.getResult().getToken());
        editor.putString(NAME, result.getResult().getName());
        editor.putString(PROFILE_ID, result.getResult().getProfile_id());
        // commit changes
        editor.commit();
    }


    /**
     * Create country session
     */
    public void createCountrySession(String area_code,String countryName, String countryCode, String currencyCode ) {
        editor.putString(COUNTRY_NAME, countryName);
        editor.putString(COUNTRY_CODE, countryCode);
        editor.putString(CURRENCY_CODE, currencyCode);
        editor.putString(AREA_CODE, area_code);
        // commit changes
        editor.commit();
    }
    public void saveBankData(String address, String bankId, String bankName, String city, String bankBranch, String locationId) {
        editor.putString(ADDRESS, address);
        editor.putString(BANK_ID, bankId);
        editor.putString(BANK_NAME, bankName);
        editor.putString(CITY, city);
        editor.putString(BANK_BRANCH, bankBranch);
        editor.putString(LOCATION_ID, locationId);
        // commit changes
        editor.commit();
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getCountryDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(AREA_CODE, pref.getString(AREA_CODE, null));
        user.put(COUNTRY_NAME, pref.getString(COUNTRY_NAME, null));
        user.put(COUNTRY_CODE, pref.getString(COUNTRY_CODE, null));
        user.put(CURRENCY_CODE, pref.getString(CURRENCY_CODE, null));
       // user.put(TRANSACTION_TYPE, pref.getString(TRANSACTION_TYPE, null));
        return user;
    }

    public HashMap<String, String> getBankDetails() {
        HashMap<String, String> userForm = new HashMap<String, String>();
        userForm.put(ADDRESS, pref.getString(ADDRESS, null));
        userForm.put(BANK_ID, pref.getString(BANK_ID, null));
        userForm.put(BANK_NAME, pref.getString(BANK_NAME, null));
        userForm.put(CITY, pref.getString(CITY, null));
        userForm.put(BANK_BRANCH, pref.getString(BANK_BRANCH, null));
        userForm.put(LOCATION_ID, pref.getString(LOCATION_ID, null));
        return userForm;
    }

    public void setTransactionType(String transactionType) {
        editor.putString(TRANSACTION_TYPE, transactionType);
        editor.apply();
    }
    public String getTransactionType() {
        return pref.getString(TRANSACTION_TYPE, "null");
    }


    public void set(String key, String value) {
        if (pref != null) {
            SharedPreferences.Editor prefsEditor = pref.edit();
            prefsEditor.putString(key, value);
            prefsEditor.commit();
        }
    }
    public <T> void saveFormInLocal(String key, JSONObject list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        set(key, json);
    }

    public List<RequiredFieldResult> getFromInLocal(String key) {
        if (pref != null) {
            Gson gson = new Gson();
            ArrayList<RequiredFieldResult> formList;
            String string = pref.getString(key, null);
            Type type = new TypeToken<ArrayList<RequiredFieldResult>>() {
            }.getType();
            formList = gson.fromJson(string, type);
            return formList;
        }
        return null;
    }

    public String getUserToken() {
        return pref.getString(TOKEN_ID, null);
    }

    public void setPreFill(String preFill) {
        editor.putString(FORM, preFill);
        editor.apply();
    }
    public String getPreFill() {
        return pref.getString(FORM, "null");
    }
   /*
     public RequiredFieldResult getFromInLocal(String key) {
        if (pref != null) {
            Gson gson = new Gson();
            RequiredFieldResult formList;
            String string = pref.getString(key, null);
            Type type = new TypeToken<RequiredFieldResult>() {
            }.getType();
            formList = gson.fromJson(string, type);
            return formList;
        }
        return null;
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
   */


}