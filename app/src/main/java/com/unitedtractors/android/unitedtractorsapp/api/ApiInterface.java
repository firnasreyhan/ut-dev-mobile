package com.unitedtractors.android.unitedtractorsapp.api;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("transaction/{username}")
    Call<TransactionResponse> getTransaction(
            @Path("username") String username,
            @Query("limit") int limit
    );

    @Headers("Content-Type: application/json")
    @POST("formSnack")
    Call<BaseResponse> postPembelianSnack(
            @Body String model
    );

    @GET("form")
    Call<FormResponse> getListForm(
            @Query("divisi") String divisi
    );

    @PUT("transaction/confirm")
    @FormUrlEncoded
    Call<BaseResponse> putConfirm(
            @Field("username") String username,
            @Field("idTrans") String idTrans,
            @Field("isApprove") boolean isApprove
    );
}
