package com.unitedtractors.android.unitedtractorsapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.ApiClient;
import com.unitedtractors.android.unitedtractorsapp.api.ApiInterface;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PembelianSnackResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionDetailResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiInterface apiInterface;

    public Repository() {
        this.apiInterface = ApiClient.getClient();
    }

    public MutableLiveData<SignInResponse> postSignIn(String username, String password, String token) {
        MutableLiveData<SignInResponse> data = new MutableLiveData<>();
        apiInterface.postSignIn(
                username,
                password,
                token
        ).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                    Log.e("login", "masuk");
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e("postSignIn", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postSignOut(String idUser) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postSigngOut(
                idUser
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("postSignOut", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<TransactionResponse> getTransaction(String username, int limit) {
        MutableLiveData<TransactionResponse> data = new MutableLiveData<>();
        apiInterface.getTransaction(
                username,
                limit
        ).enqueue(new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                Log.e("getTransaction", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<FormResponse> getListForm(String divisi) {
        MutableLiveData<FormResponse> data = new MutableLiveData<>();
        apiInterface.getListForm(
                divisi
        ).enqueue(new Callback<FormResponse>() {
            @Override
            public void onResponse(Call<FormResponse> call, Response<FormResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormResponse> call, Throwable t) {
                Log.e("getListForm", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postPembelianSnack(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postPembelianSnack(
                body
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("postPembelianSnack", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> putConfirm(String username, String idTrans, int isApprove, String keterangan) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.putConfirm(
                username,
                idTrans,
                isApprove,
                keterangan
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("putConfirm", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<PembelianSnackResponse> getPembelianSnack(String idTrans) {
        MutableLiveData<PembelianSnackResponse> data = new MutableLiveData<>();
        apiInterface.getPembelianSnack(
                idTrans
        ).enqueue(new Callback<PembelianSnackResponse>() {
            @Override
            public void onResponse(Call<PembelianSnackResponse> call, Response<PembelianSnackResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PembelianSnackResponse> call, Throwable t) {
                Log.e("getPembelianSnack", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<TransactionDetailResponse> getTransactionDetail(String username, String idTrans) {
        MutableLiveData<TransactionDetailResponse> data = new MutableLiveData<>();
        apiInterface.getTransactionDetail(
                username,
                idTrans
        ).enqueue(new Callback<TransactionDetailResponse>() {
            @Override
            public void onResponse(Call<TransactionDetailResponse> call, Response<TransactionDetailResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TransactionDetailResponse> call, Throwable t) {
                Log.e("getTransactionDetail", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }
}
