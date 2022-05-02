package com.zeeplivework.app.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;

    private static final String BASE_URL = "http://29597375fx.epaydev.xyz/capi/openapi/payoutApi/"; //testing server Epay
    //private static final String BASE_URL = "https://api.epay.com/capi/openapi/payoutApi/"; //live server Epay

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
