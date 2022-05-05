package com.zeeplivework.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.RequiredFieldAdapter;
import com.zeeplivework.app.databinding.ActivityAddBankBinding;
import com.zeeplivework.app.response.RequiredField.RequiredFieldBody;
import com.zeeplivework.app.response.RequiredField.RequiredFieldRequest;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.RecyclerTouchListener;
import com.zeeplivework.app.utils.SessionManager;
import com.zeeplivework.app.utils.SignUtil;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.zeeplivework.app.utils.SessionManager.COUNTRY_CODE;
import static com.zeeplivework.app.utils.SessionManager.CURRENCY_CODE;
import static com.zeeplivework.app.utils.SessionManager.TRANSACTION_TYPE;

public class AddBankActivity extends AppCompatActivity implements ApiResponseInterface {
    ActivityAddBankBinding binding;
    String sKey = "";
    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String receiveCurrency = "AUD";
    String countryCode = "AU";
    String version = "V2.0.0";
    String transactionType = "C2C";
    SortedMap<String, Object> map = new TreeMap<>();
    ApiManager apiManager;

    List<RequiredFieldResult> list = new ArrayList<>();
    List<RequiredFieldResult> listnew = new ArrayList<>();
    RequiredFieldAdapter requiredFieldAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);

        HashMap<String, String> data = new SessionManager(getApplicationContext()).getCountryDetails();
        receiveCurrency = data.get(CURRENCY_CODE);
        countryCode = data.get(COUNTRY_CODE);
        transactionType = data.get(TRANSACTION_TYPE);

        map.put("epayAccount", epayAccount);
        map.put("category", category);
        map.put("receiveCurrency", receiveCurrency);
        map.put("countryCode", countryCode);
        map.put("transactionType", transactionType);
        map.put("version", version);
        Log.e("AddBank", "MapValue=> " + map);

        sKey = SignUtil.createSign(map, "2d00b386231806ec7e18e2d96dc043aa");
        Log.e("AddBank", "RequiredKey=> " + sKey);
        // for get required field data according to this api
        RequiredFieldRequest requiredFieldRequest = new RequiredFieldRequest();
        requiredFieldRequest.setSign(sKey);

        RequiredFieldBody requiredFieldBody = new RequiredFieldBody();
        requiredFieldBody.setEpayAccount(epayAccount);
        requiredFieldBody.setCategory(category);
        requiredFieldBody.setReceiveCurrency(receiveCurrency);
        requiredFieldBody.setCountryCode(countryCode);
        requiredFieldBody.setTransactionType(transactionType);
        requiredFieldBody.setVersion(version);
        requiredFieldRequest.setParam(requiredFieldBody);
        apiManager.getRequiredField(requiredFieldRequest);

        binding.rvAddBank.setHasFixedSize(true);
        binding.rvAddBank.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        RequiredFieldAdapter.fillForm.clear();
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
            list.addAll(rsp.getData());

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSenderOrReceiver() == 2 && list.get(i).getRequired() == 1) {
                    listnew.add(list.get(i));
                }
            }
            requiredFieldAdapter = new RequiredFieldAdapter(AddBankActivity.this, listnew);
            binding.rvAddBank.setAdapter(requiredFieldAdapter);
            requiredFieldAdapter.notifyDataSetChanged();
        }

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
            boolean flag = true;
            List<RequiredFieldResult> ValidationList = requiredFieldAdapter.getArrayList();
            for (int i = 0; i < ValidationList.size(); i++) {
                if (ValidationList.get(i).getValue().isEmpty()) {
                    flag = false;
                } else {
                }
            }
            if (flag) {
                Toast.makeText(AddBankActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddBankActivity.this, "Some Field Empty", Toast.LENGTH_SHORT).show();
            }
        }





            /*if (TextUtils.isEmpty(RequiredFieldAdapter.fillForm.values().toString())) {
                Toast.makeText(AddBankActivity.this, "Fill Required Fielld", Toast.LENGTH_SHORT).show();
               // Log.e("Fiil_value ", "empty");
            } else {
                Toast.makeText(AddBankActivity.this, "Success", Toast.LENGTH_SHORT).show();
                //Log.e("Fiil_value ", "" + RequiredFieldAdapter.fillForm);
            }
            int i = 0;
            for (Map.Entry<String, Object> es : RequiredFieldAdapter.fillForm.entrySet()) {
                if (i >= 20) {
                    break;
                }
                if (RequiredFieldAdapter.fillForm.containsKey(es.getKey())) {
                    Log.e("This does work:", "first=> " + es.getKey() + ":" + RequiredFieldAdapter.fillForm.get(es.getKey()));
                } else {
                    Log.e("This does work: ", "seconds" + es.getKey() + ":" + es.getValue());
                }
                i++;
            }*/

    }
}



   /* public static String getCurrencySymbol(String countryCode) {
        String currencySymbol = "";
        Locale locale = null;
        Currency currency = null;
        try {
            locale = new Locale("", countryCode);
            currency = Currency.getInstance(locale);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (currency != null) {
            currencySymbol = currency.getCurrencyCode();
        }
        Log.e("CurrencyCode", "is " + currencySymbol);
        return currencySymbol;
    }*/

