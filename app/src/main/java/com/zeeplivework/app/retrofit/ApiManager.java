package com.zeeplivework.app.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zeeplivework.app.activity.AddBankActivity;
import com.zeeplivework.app.dialog.MyProgressDialog;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.CountryList.CountryResponse;
import com.zeeplivework.app.response.CreateTransaction.CreateTransactionResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;
import com.zeeplivework.app.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiManager {
    private Context mContext;
    private MyProgressDialog dialog;
    private ApiResponseInterface mApiResponseInterface;
    private ApiInterface apiService;

    public ApiManager(Context context, ApiResponseInterface apiResponseInterface) {
        this.mContext = context;
        this.mApiResponseInterface = apiResponseInterface;
        apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        dialog = new MyProgressDialog(mContext);

    }


    public void getCurrencyListDetails(String currency) {
        Call<CountryResponse> call = apiService.getCurrencyList("application/json", currency);
        Log.e("CountryRequestLog", "" + new Gson().toJson(call.request().toString()));
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                Log.e("EPAYLOG", "getCurrencyListDetail=> " + new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.CURRENCY_LIST);
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.e("EPAYLOG", "getCurrencyError=> " + t);
            }
        });
    }

    public void getRequiredField(String countryCode, String receiveCurrency, String transactionType) {
        Call<RequiredFieldResponse> call = apiService.getRequiredField("application/json", countryCode, receiveCurrency, transactionType);
        Log.e("FieldRequestLog", "" + new Gson().toJson(call.request().toString()));
        call.enqueue(new Callback<RequiredFieldResponse>() {
            @Override
            public void onResponse(Call<RequiredFieldResponse> call, Response<RequiredFieldResponse> response) {
                Log.e("getFieldListDetail", new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.REQUIRED_FIELD);
                }
            }

            @Override
            public void onFailure(Call<RequiredFieldResponse> call, Throwable t) {
                Log.e("getFieldDataError", "getFieldListError=> " + t);
            }
        });
    }

    public void getBankListDetails(String countryCode, String currency, String transactionType) {
        Call<BankListResponse> call = apiService.getBankList("application/json", countryCode, currency, transactionType);
        Log.e("BankRequestLog", "" + new Gson().toJson(call.request().toString()));
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


    public void getBankListNextPage(String countryCode, String currency, String transactionType) {
        Call<BankListResponse> call = apiService.getBankList("application/json", countryCode, currency, transactionType);
        Log.e("BankRequestLog", "" + new Gson().toJson(call.request().toString()));
        call.enqueue(new Callback<BankListResponse>() {
            @Override
            public void onResponse(Call<BankListResponse> call, Response<BankListResponse> response) {
                Log.e("getBankListDetail", new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.BANK_LIST_NEXT_PAGE);
                }
            }

            @Override
            public void onFailure(Call<BankListResponse> call, Throwable t) {
                Log.e("getBankListError", "getBankListError=> " + t);
            }
        });
    }




    public void createTransaction(JSONObject data) {
        Call<CreateTransactionResponse> call = apiService.createTransaction("application/json", data);
        //Log.e("createTransactionLog", "" + new Gson().toJson(call.request().toString()));
        call.enqueue(new Callback<CreateTransactionResponse>() {
            @Override
            public void onResponse(Call<CreateTransactionResponse> call, Response<CreateTransactionResponse> response) {
                Log.e("createTransactionDetail", new Gson().toJson(response.body().getResult()));
                if (response.body().getSuccess()) {
                    mApiResponseInterface.isSuccess(response.body(), Constant.CREATE_TRANSACTION);
                    Toast.makeText(mContext, new Gson().toJson(response.body().getResult()), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, new Gson().toJson(response.body().getError()), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CreateTransactionResponse> call, Throwable t) {
                Log.e("createTransactionError", "createTransactionError=> " + t);
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


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
