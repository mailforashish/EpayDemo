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
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String PROFILE_ID = "profile_id";
    public static final String TOKEN_ID = "token_id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String IS_ONLINE = "is_online";
    public static final String PROFILE_PIC = "profile_pic";
    public static final String ACCOUNT_VERIFIED = "account_verified";
    public static final String FCM_TOKEN = "fcm_token";
    public static final String LOGIN_TYPE = "login_type";
    public static final String ONLINE_STATE = "online_State";
    public static final String ONLINE_STATEBACK = "online_Stateback";
    public static final String USER_LOCATION = "user_location";
    public static final String USER_Email = "user_Email";
    public static final String USER_Password = "user_Password";
    public static final String CART = "cart";
    public static final String MOBILE = "mobile";
    public static final String PAY_AMOUNT = "pay_amount";


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
     * Create login session
     */

    public void createLoginSession(String username, String profile_id) {
        //Log.e("inogin", new Gson().toJson(result));
        //Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // editor.putString(TOKEN_ID, result.getResult().getToken());
        editor.putString(NAME, username);
        // editor.putInt(IS_ONLINE, result.getResult().getIs_online());
        // editor.putString(GENDER, result.getResult().getGender());
        editor.putString(PROFILE_ID,profile_id );
        // commit changes
        editor.commit();
    }


    public String getUserToken() {
        return pref.getString(TOKEN_ID, null);
    }


    public int isOnline() {
        return pref.getInt(IS_ONLINE, 0);
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (this.isLoggedIn()) {

            Intent i = new Intent(_context, WalletActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

        } else {
            Intent i = new Intent(_context, WalletActivity.class);
            // Staring Login Activity
            //send data socialLogin page for
            i.putExtra("Splashpage", "fromSplash");
            _context.startActivity(i);
        }
    }

    public void saveFcmToken(String token) {
        editor.putString(FCM_TOKEN, token);
        editor.commit();
    }

    public String getFcmToken() {
        return pref.getString(FCM_TOKEN, null);
    }


    /**
     * Get stored session data
     */

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(TOKEN_ID, pref.getString(TOKEN_ID, null));
        user.put(NAME, pref.getString(NAME, null));
        user.put(GENDER, pref.getString(GENDER, null));
        user.put(PROFILE_ID, pref.getString(PROFILE_ID, null));
        // return user
        return user;
    }


    public String getGender() {
        return pref.getString(GENDER, null);
    }

    /**
     * Clear session details
     */
    public void logoutUser() {

        //Clear all data from Shared Preferences
        editor.clear();
        editor.commit();
        //After logout redirect user to Loing Activity
       /* Intent i = new Intent(_context, SocialLogin.class);
        //Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);*/
    }


    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setOnlineState(int id) {
        editor.putInt(ONLINE_STATE, id);
        editor.apply();
    }

    public int getOnlineState() {
        return pref.getInt(ONLINE_STATE, 0);
    }

    public String getUserId() {
        return pref.getString(PROFILE_ID, "");
    }

    public String getUserName() {
        return pref.getString(NAME, "");
    }


    public void setUserProfilepic(String profileUrl) {
        try {
            editor.putString(PROFILE_PIC, profileUrl);
            editor.apply();
        } catch (Exception E) {
        }
    }

    public String getUserProfilepic() {
        return pref.getString(PROFILE_PIC, "");
    }

    public void setOnlineFromBack(int id) {
        editor.putInt(ONLINE_STATEBACK, id);
        editor.apply();
    }

    public int getOnlineFromBack() {
        return pref.getInt(ONLINE_STATEBACK, 0);
    }

    public void setUserLocation(String c_name) {
        editor.putString(USER_LOCATION, c_name);
        editor.apply();
    }

    public String getUserLocation() {
        return pref.getString(USER_LOCATION, "null");
    }


    public void setPayAmount(String payAmount) {
        editor.putString(PAY_AMOUNT, payAmount);
        editor.apply();
    }

    public String getPayAmount() {
        return pref.getString(PAY_AMOUNT, "null");
    }

    public void setUserEmail(String c_name) {
        editor.putString(USER_Email, c_name);
        editor.apply();
    }


    public String getUserEmail() {
        return pref.getString(USER_Email, "null");
    }

    public void setUserPassword(String c_name) {
        editor.putString(USER_Password, c_name);
        editor.apply();
    }


    public String getUserPassword() {
        return pref.getString(USER_Password, "null");
    }


    public void saveMobile(String mobile) {
        editor.putString(MOBILE, mobile);
        editor.commit();
    }

    public String getMobile() {
        return pref.getString(MOBILE, null);
    }


}