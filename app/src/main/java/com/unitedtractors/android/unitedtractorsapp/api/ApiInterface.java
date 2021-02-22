package com.unitedtractors.android.unitedtractorsapp.api;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.ListFormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    @Headers("Content-Type: application/json")
    @POST("formSnack")
    Call<BaseResponse> postPembelianSnack(
            @Body String model
    );

    @GET("form")
    Call<ListFormResponse> getListForm(
            @Query("divisi") String divisi
    );
}
