package com.unitedtractors.android.unitedtractorsapp.api;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.ListFormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("user/login")
    @FormUrlEncoded
    Call<SignInResponse> postSignIn(
            @Field("username") String username,
            @Field("password") String password,
            @Field("token") String token
    );

    @POST("user/logout")
    @FormUrlEncoded
    Call<BaseResponse> postSigngOut(
            @Field("idUser") String idUser
    );

    @POST("transaction")
    @FormUrlEncoded
    Call<BaseResponse> postTransaction(
            @Field("idUser") String idUser,
            @Field("idMapping") String idMapping
    );

    @GET("form")
    Call<ListFormResponse> getListForm(
            @Query("divisi") String divisi
    );
}
