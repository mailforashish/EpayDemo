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
import com.zeeplivework.app.adapter.BankAdapter;
import com.zeeplivework.app.adapter.CountryAdapter;
import com.zeeplivework.app.databinding.CountryDialogBinding;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.CountryList.CountryResponse;
import com.zeeplivework.app.response.CountryListNew;
import com.zeeplivework.app.response.CountryNew.CountryResponseNew;
import com.zeeplivework.app.response.CountryNew.CountryResultNew;
import com.zeeplivework.app.response.LoginResponse;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.CountrySelect;
import com.zeeplivework.app.utils.SessionManager;

import java.util.ArrayList;


public class CountryDialog extends Dialog implements ApiResponseInterface, CountrySelect {
    CountryDialogBinding binding;
    ArrayList<CountryResultNew> countryListNews = new ArrayList<>();
    CountryAdapter adapter;
    Context context;
    SessionManager sessionManager;
    ApiManager apiManager;

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
        apiManager = new ApiManager(getContext(), this);
        sessionManager = new SessionManager(getContext());
        binding.rvCountry.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        apiManager.getCountryList();

        show();
    }

    @Override
    public void getCountry(boolean select, String area_code, String country_name, String country_code, String currency_code) {
        Log.e("Epay", "SelectedValue=> " + country_name + "\n" + country_code + "\n" + currency_code);
        if (select) {
            ((WalletActivity) context).setCountry(country_name, currency_code);
            sessionManager.createCountrySession(area_code, country_name, country_code, currency_code);
            apiManager.login("arpana123@gmail.com", "1234567890");
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

    @Override
    public void isError(String errorCode) {

    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        if (ServiceCode == Constant.COUNTRY_LIST) {
            CountryResponseNew rsp = (CountryResponseNew) response;
            try {
                Log.e("CountryDialog", "CountryList=> " + new Gson().toJson(rsp.getResult()));
                countryListNews.addAll(rsp.getResult());
                if (countryListNews.size() > 0) {
                    adapter = new CountryAdapter(context, countryListNews, this);
                    binding.rvCountry.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {

            }
        }
        if (ServiceCode == Constant.LOGIN) {
            LoginResponse rsp = (LoginResponse) response;
            new SessionManager(getContext()).createLoginSession(rsp);
        }

    }


}
