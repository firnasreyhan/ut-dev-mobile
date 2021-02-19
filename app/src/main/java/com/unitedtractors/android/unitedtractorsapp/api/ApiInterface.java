package com.unitedtractors.android.unitedtractorsapp.api;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
