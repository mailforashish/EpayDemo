package com.zeeplivework.app.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zeeplivework.app.dialog.MyProgressDialog;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.CurrencyList.CurrenciesResponse;
import com.zeeplivework.app.response.EpayBankList.BankRequest;
import com.zeeplivework.app.response.EpayRequest.EpayRequest;
import com.zeeplivework.app.response.login.LoginResponse;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SessionManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiManager {
    private Context mContext;
    private MyProgressDialog dialog;
    private ApiResponseInterface mApiResponseInterface;
    private ApiInterface apiService;
    String authToken;

    public ApiManager(Context context, ApiResponseInterface apiResponseInterface) {
        this.mContext = context;
        this.mApiResponseInterface = apiResponseInterface;
        apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        dialog = new MyProgressDialog(mContext);
        authToken = Constant.BEARER + new SessionManager(context).getUserToken();
        Log.e("authToken", authToken);
    }


    public void getCurrencyListDetails(EpayRequest epayRequest) {
        Call<CurrenciesResponse> call = apiService.getCurrencyList("application/json",epayRequest);
        Log.e("PayRequestLog",""+ new Gson().toJson(call.request().toString()));
        call.enqueue(new Callback<CurrenciesResponse>() {
            @Override
            public void onResponse(Call<CurrenciesResponse> call, Response<CurrenciesResponse> response) {
                Log.e("EPAYLOG", "getCurrencyListDetail=> " + new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.CURRENCY_LIST);
                }
            }

            @Override
            public void onFailure(Call<CurrenciesResponse> call, Throwable t) {
                Log.e("EPAYLOG", "getCurrencyError=> " + t);
            }
        });
    }


    public void getBankListDetails(BankRequest bankRequest) {
        Call<BankListResponse> call = apiService.getBankList("application/json", bankRequest);
        Log.e("BankRequestLog",""+ new Gson().toJson(call.request().toString()));
        call.enqueue(new Callback<BankListResponse>() {
            @Override
            public void onResponse(Call<BankListResponse> call, Response<BankListResponse> response) {
                Log.e("getBankListDetail", new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.BANK_LIST);
                }
            }

            @Override
            public void onFailure(Call<BankListResponse> call, Throwable t) {
                Log.e("getBankListError", "getBankListError=> " + t);
            }
        });
    }

     /*public void getCurrencyListDetails(String epayAccount, String category, String currency, String version, String transactionType, String sign) {
        Call<CurrenciesResponse> call = apiService.getCurrencyList("application/json", epayAccount, category, "", version, "", "");
        Log.e("PayRequestLog",call.request().toString());
        call.enqueue(new Callback<CurrenciesResponse>() {
            @Override
            public void onResponse(Call<CurrenciesResponse> call, Response<CurrenciesResponse> response) {
                Log.e("EPAYLOG", "getCurrencyCall=> " + JSON.toJSONString(epayAccount + category + currency + version + transactionType + sign));
                Log.e("EPAYLOG", "getCurrencyListDetail=> " + new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.CURRENCY_LIST);
                }
            }

            @Override
            public void onFailure(Call<CurrenciesResponse> call, Throwable t) {
                Log.e("EPAYLOG", "getCurrencyError=> " + t);
            }
        });
    }*/


    /*public void getBankListDetails(String epayAccount, String category, String transactionType,
                                   String currency, String countryCode, String pageNum, String pageSize,
                                   String version, String sign) {
        Call<BankListResponse> call = apiService.getBankList("application/json", "test2020@epay.com", "BANK", "C2C",
                "AUD", "AU", "1", "10", "V2.0.0", "");
        call.enqueue(new Callback<BankListResponse>() {
            @Override
            public void onResponse(Call<BankListResponse> call, Response<BankListResponse> response) {
                Log.e("getCurrencyListDetail", new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.BANK_LIST);
                }
            }

            @Override
            public void onFailure(Call<BankListResponse> call, Throwable t) {
                Log.e("getCurrencyError", "" + t);
            }
        });
    }*/











    public void showDialog() {
        try {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
