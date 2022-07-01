package com.zeeplivework.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.RequiredFieldAdapter;
import com.zeeplivework.app.databinding.ActivityAddBankBinding;
import com.zeeplivework.app.response.CreateTransaction.CreateTransactionResponse;
import com.zeeplivework.app.response.Prefill.PrefillResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SessionManager;
import com.zeeplivework.app.utils.JsonToMapConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.zeeplivework.app.utils.SessionManager.AREA_CODE;
import static com.zeeplivework.app.utils.SessionManager.COUNTRY_CODE;
import static com.zeeplivework.app.utils.SessionManager.COUNTRY_NAME;
import static com.zeeplivework.app.utils.SessionManager.CURRENCY_CODE;


public class AddBankActivity extends AppCompatActivity implements ApiResponseInterface {
    ActivityAddBankBinding binding;
    private String receiveCurrency = "";
    private String countryCode = "";
    private String areaCode = "";
    private String transactionType = "";
    private String Country = "";

    ApiManager apiManager;
    List<RequiredFieldResult> list = new ArrayList<>();
    List<RequiredFieldResult> receiverList = new ArrayList<>();

    RequiredFieldAdapter requiredFieldAdapter;
    SessionManager sessionManager;
    JSONObject SenderInfo = new JSONObject();
    JSONObject ReceiverInfo = new JSONObject();
    public String preFillData;
    HashMap<String, Object> FormMap;
    String MatchVal = "NO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);

        HashMap<String, String> data = sessionManager.getCountryDetails();
        receiveCurrency = data.get(CURRENCY_CODE);
        countryCode = data.get(COUNTRY_CODE);
        Country = data.get(COUNTRY_NAME);
        areaCode = data.get(AREA_CODE);
        transactionType = sessionManager.getTransactionType();
        RequiredFieldAdapter.ReceiverInfo.clear();
        apiManager.getPrefill();
        apiManager.getRequiredField(countryCode, receiveCurrency, transactionType);

        binding.rvAddBank.setHasFixedSize(true);
        binding.rvAddBank.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void isError(String errorCode) {
        Log.e("AddBank", "BankListError=> " + errorCode);
    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        list.clear();
        if (ServiceCode == Constant.PRE_FILL) {
            PrefillResponse rsp = (PrefillResponse) response;
            Log.e("AddBank", "RequiredList=> " + new Gson().toJson(rsp.getResult().getEpayReceiverInfo()));
            if (rsp.getResult().getEpayReceiverInfo() != null) {
                try {
                    preFillData = rsp.getResult().getEpayReceiverInfo();
                    FormMap = new HashMap<>(JsonToMapConverter.jsonToMap(preFillData));
                    Log.e("rspLog", FormMap.toString());
                } catch (Exception e) {

                }

            }


        }
        if (ServiceCode == Constant.REQUIRED_FIELD) {
            RequiredFieldResponse rsp = (RequiredFieldResponse) response;
            //Log.e("AddBank", "RequiredList=> " + new Gson().toJson(rsp.getData()));
            LogPrint(new Gson().toJson(rsp.getData()));
            try {
                list.addAll(rsp.getData());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSenderOrReceiver() == 2 && list.get(i).getRequired() == 1) {
                        receiverList.add(list.get(i));
                    } else if (list.get(i).getSenderOrReceiver() == 1 && list.get(i).getRequired() == 1) {
                        SenderInfo.put("_" + list.get(i).getValue(), "");
                    }
                }
                for (Map.Entry map : FormMap.entrySet()) {
                    if (countryCode.equals(map.getValue())) {
                        //Log.e("VALUE: ", "MatchValue " + map.getValue());
                        MatchVal = "YES";
                    }
                }
                requiredFieldAdapter = new RequiredFieldAdapter(AddBankActivity.this, receiverList, MatchVal, FormMap, countryCode, areaCode);
                binding.rvAddBank.setAdapter(requiredFieldAdapter);

            } catch (Exception e) {
            }

        }
        if (ServiceCode == Constant.CREATE_TRANSACTION) {
            CreateTransactionResponse rsp = (CreateTransactionResponse) response;
            try {
                if (rsp != null) {
                    if (rsp.getResult().equals("success")) {
                        startActivity(new Intent(AddBankActivity.this, DailyStarActivity.class));
                        finishAffinity();
                    } else {
                        startActivity(new Intent(AddBankActivity.this, WalletActivity.class));
                        finishAffinity();
                    }
                    Log.e("AddBank", "TransactionData=> " + new Gson().toJson(rsp.getResult()));
                }
            } catch (Exception e) {
                Log.e("AddBank", "TransactionDataError=> " + e.getMessage());
            }
        }

    }

    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backPage() {
            startActivity(new Intent(AddBankActivity.this, WalletActivity.class));
            finishAffinity();
        }

        public void saveContinue() {
            //Log.e("TestingDaat", "mapsizeFill" + RequiredFieldAdapter.ReceiverInfo.size());
            //Log.e("TestingDaat", "mapsizeList" + receiverList.size());
            if (receiverList.size() == RequiredFieldAdapter.ReceiverInfo.size()) {
                //TransferTransaction();
                //Toast.makeText(AddBankActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddBankActivity.this, "Fill Required Field", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void TransferTransaction() {
        ReceiverInfo = RequiredFieldAdapter.ReceiverInfo;
        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("country_id", Country);
            jsonResult.put("transaction_type", transactionType);
            jsonResult.put("receiveCurrency", receiveCurrency);
            jsonResult.put("senderInfo", SenderInfo);
            jsonResult.put("receiverInfo", ReceiverInfo);
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
        //String msg = jsonResult.toString();
        Log.e("AddBank", "MSGInfoData=> " + jsonResult);
        //Log.e("AddBank", "FORMLIST=> " + sessionManager.getFromInLocal("form"));

        apiManager.createTransaction(jsonResult);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddBankActivity.this, WalletActivity.class));
        finishAffinity();
    }

    public void LogPrint(String message) {
        int maxLogSize = 1000;
        for (int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > message.length() ? message.length() : end;
            Log.v(TAG, message.substring(start, end));
        }
    }


}