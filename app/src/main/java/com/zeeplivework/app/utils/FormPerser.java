package com.zeeplivework.app.utils;

import android.content.Context;

import com.zeeplivework.app.activity.AddBankActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class FormPerser {
    public static String jsonData(String parser) {
        String fName = parser;
        /*try {
            JSONObject msgJson = new JSONObject(AddBankActivity.preFillData);
            fName = msgJson.getString(parser);
        } catch (JSONException e) {
        }*/
        return fName;
    }

}
