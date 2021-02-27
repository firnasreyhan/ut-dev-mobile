package com.unitedtractors.android.unitedtractorsapp.api;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PembelianSnackResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionDetailResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @POST("user/register")
    @Multipart
    Call<BaseResponse> postSignUp(
            @Part("username") RequestBody username,
            @Part("namaLengkap") RequestBody namaLengkap,
            @Part("role") RequestBody role,
            @Part("departement") RequestBody departemen,
            @Part("division") RequestBody divisi,
            @Part("password") RequestBody password,
            @Part MultipartBody.Part signature,
            @Part("token") RequestBody token
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
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formMobdin")
    Call<BaseResponse> postPermintaanMobilDinas(
            @Body String body
    );

    @GET("form")
    Call<FormResponse> getListForm(
            @Query("department") String department
    );

    @PUT("transaction/confirm")
    @FormUrlEncoded
    Call<BaseResponse> putConfirm(
            @Field("username") String username,
            @Field("idTrans") String idTrans,
            @Field("isApprove") int isApprove,
            @Field("keterangan") String keterangan
    );

    @GET("formSnack")
    Call<PembelianSnackResponse> getPembelianSnack(
            @Query("idTrans") String idTrans
    );

    @GET("transaction/detail")
    Call<TransactionDetailResponse> getTransactionDetail(
            @Query("username") String username,
            @Query("idTrans") String idTrans
    );
}
