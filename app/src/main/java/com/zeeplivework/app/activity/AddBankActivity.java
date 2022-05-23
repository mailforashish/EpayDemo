package com.zeeplivework.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.RequiredFieldAdapter;
import com.zeeplivework.app.databinding.ActivityAddBankBinding;
import com.zeeplivework.app.response.BankList.BankRequest;
import com.zeeplivework.app.response.BankList.BankRequestBody;
import com.zeeplivework.app.response.RequiredField.RequiredFieldBody;
import com.zeeplivework.app.response.RequiredField.RequiredFieldRequest;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SessionManager;
import com.zeeplivework.app.utils.SignUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


import static com.zeeplivework.app.utils.SessionManager.COUNTRY_CODE;
import static com.zeeplivework.app.utils.SessionManager.COUNTRY_NAME;
import static com.zeeplivework.app.utils.SessionManager.CURRENCY_CODE;


public class AddBankActivity extends AppCompatActivity implements ApiResponseInterface {
    ActivityAddBankBinding binding;
    String receiveCurrency = "";
    String countryCode = "";
    String transactionType = "";
    String Country = "";
    private List<String> searchWordList;
    ApiManager apiManager;
    List<RequiredFieldResult> list = new ArrayList<>();
    List<RequiredFieldResult> receiverList = new ArrayList<>();
    RequiredFieldAdapter requiredFieldAdapter;
    SessionManager sessionManager;
    JSONObject SenderInfo = new JSONObject();
    JSONObject ReceiverInfo = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);

        searchWordList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.searchWordsArray)));
        HashMap<String, String> data = sessionManager.getCountryDetails();
        receiveCurrency = data.get(CURRENCY_CODE);
        countryCode = data.get(COUNTRY_CODE);
        Country = data.get(COUNTRY_NAME);
        transactionType = sessionManager.getTransactionType();

        RequiredFieldAdapter.ReceiverInfo.clear();

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
        if (ServiceCode == Constant.REQUIRED_FIELD) {
            RequiredFieldResponse rsp = (RequiredFieldResponse) response;
            Log.e("AddBank", "RequiredList=> " + new Gson().toJson(rsp.getData()));
            try {
                list.addAll(rsp.getData());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSenderOrReceiver() == 2 && list.get(i).getRequired() == 1) {
                        receiverList.add(list.get(i));
                    } else if (list.get(i).getSenderOrReceiver() == 1 && list.get(i).getRequired() == 1) {
                        SenderInfo.put("_" + list.get(i).getValue(), "");
                    }
                }

            /* for (int i = 0; i < listnew.size(); i++) {
                for (String search : searchWordList) {
                    listnew.removeIf(item -> item.getValue().equals(search));
                }
            }*/

                requiredFieldAdapter = new RequiredFieldAdapter(AddBankActivity.this, receiverList, countryCode);
                binding.rvAddBank.setAdapter(requiredFieldAdapter);
            } catch (Exception e) {

            }

        }
       /* if (ServiceCode == Constant.CREATE_TRANSACTION) {
            CreateTransactionResponse rsp = (CreateTransactionResponse) response;
            Log.e("AddBank", "TransactiData=> " + new Gson().toJson(rsp.getData()));
        }*/

    }


    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backPage() {
            onBackPressed();
        }

        public void saveContinue() {
            Log.e("TestingDaat", "mapsizeFill" + RequiredFieldAdapter.ReceiverInfo.size());
            Log.e("TestingDaat", "mapsizeList" + receiverList.size());
            if (receiverList.size() == RequiredFieldAdapter.ReceiverInfo.size()) {
                TransferTransaction();
                Toast.makeText(AddBankActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddBankActivity.this, "Fill Required Field", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void TransferTransaction() {
        ReceiverInfo = RequiredFieldAdapter.ReceiverInfo;
        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("country_id", countryCode);
            jsonResult.put("transaction_type", transactionType);
            jsonResult.put("receiveCurrency", receiveCurrency);
            jsonResult.put("senderInfo", SenderInfo);
            jsonResult.put("receiverInfo", ReceiverInfo);
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
        String msg = jsonResult.toString();
        Log.e("AddBank", "SenderInfoData=> " + new Gson().toJson(SenderInfo));
        Log.e("AddBank", "ReceiverInfoData=> " + new Gson().toJson(ReceiverInfo));
        Log.e("AddBank", "MSGInfoData=> " + msg);


        //createTransactionRequest.setParam(createTransactionBody);
        //apiManager.createTransaction(createTransactionRequest);

    }

}




