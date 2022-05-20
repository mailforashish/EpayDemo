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
    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String ADDRESS = "address";
    public static final String BANK_ID = "bank_Id";
    public static final String BANK_NAME = "bank_name";
    public static final String CITY = "city";
    public static final String BANK_BRANCH = "bank_branch";
    public static final String LOCATION_ID = "location_id";
//String address, String bankId, String bankName, String city, String bankBranch, String locationId)
    // Constructor
    public SessionManager(Context context) {
        try {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        } catch (Exception e) {
        }
    }

    /**
     * Create country session
     */
    public void createCountrySession(String countryName, String countryCode, String currencyCode ) {
        editor.putString(COUNTRY_NAME, countryName);
        editor.putString(COUNTRY_CODE, countryCode);
        editor.putString(CURRENCY_CODE, currencyCode);
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

   /* public void setCountryName(String countryName) {
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
   */


}