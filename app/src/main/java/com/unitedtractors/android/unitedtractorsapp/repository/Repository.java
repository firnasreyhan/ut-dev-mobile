package com.unitedtractors.android.unitedtractorsapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.ApiClient;
import com.unitedtractors.android.unitedtractorsapp.api.ApiInterface;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.ListFormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;

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

    public MutableLiveData<BaseResponse> postTransaction(String idUser, String idMapping) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postTransaction(
                idUser,
                idMapping
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("postTransaction", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<ListFormResponse> getListForm(String divisi) {
        MutableLiveData<ListFormResponse> data = new MutableLiveData<>();
        apiInterface.getListForm(
                divisi
        ).enqueue(new Callback<ListFormResponse>() {
            @Override
            public void onResponse(Call<ListFormResponse> call, Response<ListFormResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListFormResponse> call, Throwable t) {
                Log.e("getListForm", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }
}
