package com.zeeplivework.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.google.gson.Gson;
import com.zeeplivework.app.R;

import com.zeeplivework.app.activity.WalletActivity;
import com.zeeplivework.app.adapter.CountryAdapter;
import com.zeeplivework.app.databinding.CountryDialogBinding;
import com.zeeplivework.app.response.CountryList.CountryResponse;
import com.zeeplivework.app.response.CountryList.CountryResult;
import com.zeeplivework.app.response.CountryList.CountryRequest;
import com.zeeplivework.app.response.CountryList.CountryRequestBody;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.CountrySelect;
import com.zeeplivework.app.utils.SessionManager;
import com.zeeplivework.app.utils.SignUtil;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;


public class CountryDialog extends Dialog implements ApiResponseInterface, CountrySelect {
    CountryDialogBinding binding;
    ArrayList<CountryResult> countryResultArrayList = new ArrayList<>();
    CountryAdapter adapter;
    ApiManager apiManager;
    Context context;

    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String currency = "";
    String version = "V2.0.0";
    String transactionType = "";
    String sKey = "";
    SortedMap<String, Object> map = new TreeMap<>();
    SessionManager sessionManager;

    public CountryDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.country_dialog, null, false);
        setContentView(binding.getRoot());
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.setCancelable(true);

        binding.setClickListener(new EventHandler(getContext()));
        sessionManager = new SessionManager(getContext());
        apiManager = new ApiManager(getContext(), this);
        binding.rvCountry.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        map.put("epayAccount", epayAccount);
        map.put("category", category);
        map.put("currency", currency);
        map.put("version", version);
        map.put("transactionType", transactionType);


        //sKey=  SignUtil.createSign(map, "920c3cb4ff6f6ab80456b461bf5b9766");//for live
        sKey = SignUtil.createSign(map, "2d00b386231806ec7e18e2d96dc043aa");// for Testing
        Log.e("EPAYLOG", "DialogsSignKey=> " + sKey);

        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setSign(sKey);

        CountryRequestBody countryRequestBody = new CountryRequestBody();
        countryRequestBody.setEpayAccount(epayAccount);
        countryRequestBody.setCategory(category);
        countryRequestBody.setCurrency(currency);
        countryRequestBody.setVersion(version);
        countryRequestBody.setTransactionType(transactionType);
        countryRequest.setParam(countryRequestBody);
        apiManager.getCurrencyListDetails(countryRequest);

        show();
    }

    @Override
    public void isError(String errorCode) {
        Log.e("EPAYLOG", "CurrencyListError=> " + errorCode);
    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        countryResultArrayList.clear();
        if (ServiceCode == Constant.CURRENCY_LIST) {
            CountryResponse rsp = (CountryResponse) response;
            try {
                countryResultArrayList.addAll(rsp.getData());
                Log.e("EPAYLOG", "CurrencyListP2=> " + new Gson().toJson(countryResultArrayList));
                Log.e("currencyLog", "CurrencyData=> " + countryResultArrayList.get(0).getCurrency());

                adapter = new CountryAdapter(context, countryResultArrayList, this);
                binding.rvCountry.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } catch (Exception e) {

            }


        }
    }

    @Override
    public void getCountry(boolean select, String country_name, String country_code, String currency_code, String tran_type) {
        Log.e("Epay", "SelectedValue=> " + country_name + "\n" + country_code + "\n" + currency_code + "\n" + tran_type);
        if (select) {
            ((WalletActivity) context).setCountry(country_name);
            sessionManager.createCountrySession(country_name, country_code, currency_code, tran_type);
            dismiss();
        }
    }


    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backDialog() {
            dismiss();
        }

    }


}
