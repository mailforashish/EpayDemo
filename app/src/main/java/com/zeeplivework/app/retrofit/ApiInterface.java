package com.zeeplivework.app.retrofit;


import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.CurrencyList.CurrenciesResponse;
import com.zeeplivework.app.response.BankList.BankRequest;
import com.zeeplivework.app.response.CurrencyList.CountryRequest;
import com.zeeplivework.app.response.RequiredField.RequiredFieldRequest;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    //@FormUrlEncoded
    @POST("getReceiveCurrencyList")
    Call<CurrenciesResponse> getCurrencyList(@Header("Accept") String accept,
                                             @Body CountryRequest countryRequest);


    @POST("getRequiredField")
    Call<RequiredFieldResponse> getRequiredField(@Header("Accept") String accept,
                                                 @Body RequiredFieldRequest requiredFieldRequest);

    @POST("getBankList")
    Call<BankListResponse> getBankList(@Header("Accept") String accept,
                                       @Body BankRequest bankRequest);

    /*@FormUrlEncoded
    @POST("getBankList")
    Call<BankListResponse> getBankList(@Header("Accept") String accept,
                                       @Field("epayAccount") String epayAccount,
                                       @Field("category") String category,
                                       @Field("transactionType") String transactionType,
                                       @Field("currency") String currency,
                                       @Field("countryCode") String countryCode,
                                       @Field("pageNum") String pageNum,
                                       @Field("pageSize") String pageSize,
                                       @Field("version") String version,
                                       @Field("sign") String sign);*/


}

