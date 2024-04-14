package com.hq.apptest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApi {
    @GET("/")
    Call<List<sachModel>> getData();

    @POST("/")
    Call<Integer> addData(@Body sachModel sach);

    @PUT("/{id}")
    Call<Integer> updateData(@Path("id") String id, @Body sachModel sach);

    @DELETE("/{id}")
    Call<Integer> deleteData(@Path("id") String id);
}
