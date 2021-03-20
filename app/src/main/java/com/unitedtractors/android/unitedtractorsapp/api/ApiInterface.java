package com.unitedtractors.android.unitedtractorsapp.api;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PembelianSnackResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PostMobilResponse;
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
            @Query("limit") int limit,
            @Query("isApproval") boolean isApproval
    );

    @Headers("Content-Type: application/json")
    @POST("formMaterialUsedSlip")
    Call<BaseResponse> postMaterialUsedSlip(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formOrderCatering")
    Call<BaseResponse> postOrderCatering(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formSnack")
    Call<BaseResponse> postPembelianSnack(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formMobdin")
    Call<PostMobilResponse> postPermintaanMobilDinas(
            @Body String body
    );

    @Multipart
    @POST("formMobdin/upload")
    Call<BaseResponse> postSimMobilDinas(
            @Part("idUser") RequestBody idUser,
            @Part("idTrans") RequestBody idTrans,
            @Part MultipartBody.Part file
    );

    @PUT("formMobdin/nopol")
    @FormUrlEncoded
    Call<BaseResponse> putNopolMobilDinas(
            @Field("idUser") String username,
            @Field("idTrans") String idTrans,
            @Field("nopol") String isApprove
    );

    @Headers("Content-Type: application/json")
    @POST("formMobpri")
    Call<PostMobilResponse> postPermintaanMobilPribadi(
            @Body String body
    );

    @Multipart
    @POST("formMobpri/upload")
    Call<BaseResponse> postSimMobilPribadi(
            @Part("idUser") RequestBody idUser,
            @Part("idTrans") RequestBody idTrans,
            @Part MultipartBody.Part file
    );

    @PUT("formMobpri/nopol")
    @FormUrlEncoded
    Call<BaseResponse> putNopolMobilPribadi(
            @Field("idUser") String username,
            @Field("idTrans") String idTrans,
            @Field("nopol") String isApprove
    );

    @Headers("Content-Type: application/json")
    @POST("formControlHarian")
    Call<BaseResponse> postControlHarian(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formIdentifikasi")
    Call<BaseResponse> postIdentifikasi(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formExternalWorkOrder")
    Call<BaseResponse> postExternalWorkOrder(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formSidakCatering")
    Call<BaseResponse> postSidakCatering(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formLegalitas")
    Call<BaseResponse> postLegalitas(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formLaporanPerbaikan")
    Call<BaseResponse> postLaporanPerbaikan(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formICGS")
    Call<BaseResponse> postICGS(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formPerbaikan")
    Call<BaseResponse> postPerbaikan(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formICP")
    Call<BaseResponse> postICP(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formICH")
    Call<BaseResponse> postICH(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formICPAB")
    Call<BaseResponse> postICPAB(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formKepuasan")
    Call<BaseResponse> postKepuasan(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formCateringReguler")
    Call<BaseResponse> postCateringReguler(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formCRM")
    Call<BaseResponse> postRuangMeeting(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formICAK")
    Call<BaseResponse> postAlatKomunikasi(
            @Body String body
    );

    @Headers("Content-Type: application/json")
    @POST("formIWO")
    Call<BaseResponse> postInternalWorkOrder(
            @Body String body
    );

    @GET("form")
    Call<FormResponse> getListForm(
            @Query("role") String role
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
            @Query("idTrans") String idTrans,
            @Query("isApproval") boolean isApproval
    );
}
