package com.hq.apptest;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFunc {
    public static RetrofitApi callRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.41.55:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitApi.class);
    }
}
