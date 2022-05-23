package com.zeeplivework.app.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParse {
    public static String jsonDecode(String decode) {
        String fName = decode;
        try {
            JSONObject msgJson = new JSONObject(decode);
            Log.e("jsonLog", new Gson().toJson(msgJson));
            fName = msgJson.getString("EN_US");
           // String str = fName;
           /* str = str.replace(" (Input English)", "");
            fName = str;*/
            /*String[] selectedDate = list.get(i).getSettlementCycle().split(" ~ ");
            String[] start = selectedDate[0].split("\\s+");
            String[] end = selectedDate[1].split("\\s+");
            String startDate = start[1] + "." + start[2];
            String endDate = end[1] + "." + end[2];
            listDate.add(startDate + " - " + endDate);*/
            // holder.tv_Name.setText(fName);
            Log.e("jsonLog", new Gson().toJson(fName));
        } catch (JSONException e) {
        }
        return fName;
    }
}
