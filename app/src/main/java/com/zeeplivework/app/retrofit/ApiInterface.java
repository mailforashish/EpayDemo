package com.zeeplivework.app.retrofit;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.CountryList.CountryResponse;
import com.zeeplivework.app.response.CountryNew.CountryResponseNew;
import com.zeeplivework.app.response.CreateTransaction.CreateTransactionResponse;
import com.zeeplivework.app.response.LoginResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;
import com.zeeplivework.app.response.Prefill.PrefillResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("loginlocal")
    Call<LoginResponse> loginUser(@Field("username") String username, @Field("password") String password);

    @GET("epaycountryList")
    Call<CountryResponseNew> getCountry(@Header("Accept") String accept);

    @FormUrlEncoded
    @POST("getReceiveCurrencyList")
    Call<CountryResponse> getCurrencyList(@Header("Accept") String accept,
                                          @Field("currency") String currency);

    @FormUrlEncoded
    @POST("getRequiredField")
    Call<RequiredFieldResponse> getRequiredField(@Header("Accept") String accept,
                                                 @Field("countryCode") String countryCode,
                                                 @Field("receiveCurrency") String receiveCurrency,
                                                 @Field("transactionType") String transactionType);

    @FormUrlEncoded
    @POST("getBankList")
    Call<BankListResponse> getBankList(@Header("Accept") String accept,
                                       @Field("countryCode") String countryCode,
                                       @Field("currency") String receiveCurrency,
                                       @Field("transactionType") String transactionType);

    @POST("createTransactionEpay")
    Call<CreateTransactionResponse> createTransaction(@Header("Accept") String accept,
                                                      @Body JSONObject jsonObject);


    @GET("getEPAYPrefilled")
    Call<PrefillResponse> getPrefillData(@Header("Authorization") String token, @Header("Accept") String accept);


}

