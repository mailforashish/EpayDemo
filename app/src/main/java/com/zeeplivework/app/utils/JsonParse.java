package com.zeeplivework.app.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParse {
    public static String jsonDecode(String decode) {
        String fName = decode;
        try {
            JSONObject msgJson = new JSONObject(decode);
            fName = msgJson.getString("EN_US");
            fName = fName.replaceAll("\\p{P}","");
            fName = fName.replace("Input English","");

            //Log.e("jsonLog", new Gson().toJson(fName));
        } catch (JSONException e) {
        }
        return fName;
    }
}
