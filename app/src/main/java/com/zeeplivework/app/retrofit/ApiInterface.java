package com.zeeplivework.app.retrofit;


import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.CountryList.CountryResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
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


}

