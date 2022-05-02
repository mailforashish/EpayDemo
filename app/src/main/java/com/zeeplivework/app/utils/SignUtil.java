package com.zeeplivework.app.utils;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zeeplivework.app.utils.SHAUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.alibaba.fastjson.JSON.parse;


public class SignUtil {
    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * create signature
     *
     * @param parameters
     * @param key
     * @return
     */
    public static String createSign(JSONObject parameters, String key) {
        //sort map
        SortedMap<String, Object> param = JSON.parseObject(parameters.toString(), SortedMap.class);
        return createSign(param, key);
    }

    /**
     * create signature
     *
     * @param param
     * @param key
     * @return
     */
    public static String createSign(SortedMap<String, Object> param, String key) {
        logger.info("begin param:" + param.toString());
        //restart sorting
        param = sortedMap(param);
        logger.info("sort param:" + param.toString());
        //sort map
        StringBuffer sbkey = new StringBuffer();
        sbkey = sbkey.append(concatMap(param));
        sbkey = sbkey.append("&key=" + key);
        logger.info("before param:" + sbkey.toString());
        //MD5 encryption,character set UTF-8,Result Convert to uppercase characters
        String sign = SHAUtils.sha256(sbkey.toString()).toUpperCase();
        logger.info("end(sha256)sign:" + sign);
        return sign;
    }

    /**
     * verify signature
     *
     * @param checkSign
     * @param parameters
     * @param key
     * @return
     */
    public static boolean checkSign(String checkSign, JSONObject parameters, String key) {
        if (TextUtils.isEmpty(checkSign) || TextUtils.isEmpty(key) || parameters.isEmpty()) {
            return false;
        }
        String mySign = createSign(parameters, key);
        logger.info("checkSign:" + checkSign);
        logger.info("mySign:" + mySign);
        if (checkSign.equalsIgnoreCase(mySign)) {
            return true;
        }
        return false;
    }


    /**
     * Json string sort by parameter accsiI (ascending)
     *
     * @param jsonStr The JSON string to sort
     * @return map
     */
    public static SortedMap<String, Object> sortedMap(String jsonStr) {
        return sortedMap(JSON.parseObject(jsonStr, SortedMap.class));

    }

    /**
     * Map sort by parameter by accsiI (ascending)
     *
     * @param map
     * @return
     */
    public static SortedMap<String, Object> sortedMap(SortedMap<String, Object> map) {
        SortedMap<String, Object> resultMap = new TreeMap<>();
        Iterator<String> keys = map.keySet().iterator();
        StringBuffer sbkey = new StringBuffer();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = map.get(key);
            // Check whether the incoming kay-value contains "" or null
            if (map.get(key) == null || value == null || value.toString().length() == 0 || "null".equals(value)) {
                // If there is null in the JSON string, the kay-value is not added to the Map, that is, the output does not include the kay-value
                continue;
            }
            // Check whether it is JSONArray(JSON array)
            if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                List<Object> arrayList = new ArrayList<>();
                for (Object object : jsonArray) {
                    // Determine whether it is JSONObject, if it is converted to TreeMap
                    if (object instanceof JSONObject) {
                        object = sortedMap(object.toString());
                    }
                    arrayList.add(object);
                }
                resultMap.put(key, arrayList);
            } else {
                // Sort the nested maps recursively if they are nested
                if (value instanceof TreeMap) {
                    value = sortedMap((SortedMap) value);
                } else if (value instanceof HashMap) {
                    value = sortedMap(new TreeMap<String, Object>((HashMap) value));
                } else if (isJSONValid(value.toString())) {
                    // If nested JSON, sort the nested JSON (child JSON) recursively
                    value = sortedMap(value.toString());
                }
                // Other base types go directly into treeMap
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }

    /**
     * Json string sort by parameter accsiI (ascending)
     *
     * @return map
     */
    public static String concatMap(SortedMap<String, Object> map) {
        StringBuilder sbkey = new StringBuilder();
        // All parameters are sorted by accsii (ascending order)
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            // Check whether there is nested JSON in this JSON
            String value = entry.getValue().toString();
            boolean flag = isJSONValid(value);
            if (flag) {
                // If nested JSON, sort the nested JSON (child JSON) recursively
                sbkey.append(entry.getKey() + "={" + concatMap((SortedMap) entry.getValue()) + "}&");
            } else {
                sbkey.append(entry.getKey() + "=" + value + "&");
            }
        }
        return sbkey.length() > 0 ? sbkey.substring(0, sbkey.length() - 1) : sbkey.toString();
    }


    /**
     * Verify that JSON is a string
     *
     * @param json
     * @return return JSON return true, Otherwise false
     */

    public final static boolean isJSONValid(String json) {
        if (json == null || "".equals(json) || json.length() == 0 || "null".equals(json)) {
            return false;
        }
        try {
            //Log.e("JSONVALUE", "JAS=> " + json);
            //JSONObject.parseObject(json);
            JSONObject.parseArray(json);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
       // String s = "{\"epayAccount\":\"api@epay.com\",\"category\":\"BANK\",\"notifyUrl\":\"\",\"merchantOrderNo\":\"202103220010\",\"amount\":\"\",\"receiveAmount\":\"3000\",\"settlementCurrency\":\"USD\",\"receiveCurrency\":\"RUB\",\"version\":\"V1.0.0\",\"senderInfo\":{\"surName\":\"tom\",\"givName\":\"cat\",\"gender\":\"M\",\"email\":\"tomcat@epay.com\",\"country\":\"CN\",\"address\":\"address\",\"city\":\"city\",\"area\":\"86\",\"phone\":\"11111111111\",\"nationality\":\"AU\",\"idType\":\"1\",\"idNumber\":\"111111\",\"issueDate\":\"1980-01-01\",\"expireDate\":\"2050-01-01\",\"birthday\":\"1970-01-01\",\"occupation\":\"1\",\"sourceOfFund\":\"1\",\"beneficiaryRelationShip\":\"1\",\"purposeOfRemittance\":\"1\"},\"receiverInfo\":{\"surName\":\"ll\",\"givName\":\"test\",\"otherName\":\"其他语言\",\"address\":\"address\",\"area\":\"86\",\"phone\":\"11111111111\",\"country\":\"RU\",\"nationality\":\"RU\",\"idType\":\"1\",\"idNumber\":\"222222\",\"locationId\":\"RURLR00001-1\",\"bankId\":\"RURLR01299-2\",\"bankName\":\"AMP Bank Limited\",\"bankBranchName\":\"AMP Bank Limited\",\"bankBranchCode\":\"\",\"accountNo\":\"42222222225222222222\"}}";
        String s1 = "{\"epayAccount\":\"api@epay.com\",\"category\":\"BANK\",\"transactionType\":\"C2C\",\"currency\":\"INR\",\"CountryCode\":\"IN\",\"pageNum\":\"1\",\"pageSize\":\"10\",\"version\":\"V2.0.0\"}}";
        System.out.println(isJSONValid(createSign(JSONObject.parseObject(s1), "2d00b386231806ec7e18e2d96dc043aa")));

    }
}
