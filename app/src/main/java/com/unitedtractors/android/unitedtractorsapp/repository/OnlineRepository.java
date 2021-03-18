package com.unitedtractors.android.unitedtractorsapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.ApiClient;
import com.unitedtractors.android.unitedtractorsapp.api.ApiInterface;
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
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineRepository {
    private ApiInterface apiInterface;

    public OnlineRepository() {
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

    public MutableLiveData<BaseResponse> postSignUp(RequestBody username, RequestBody namaLengkap, RequestBody role, RequestBody departement, RequestBody division, RequestBody password, MultipartBody.Part signature, RequestBody token) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postSignUp(
                username,
                namaLengkap,
                role,
                departement,
                division,
                password,
                signature,
                token
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("postSignUp", t.getMessage());
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

    public MutableLiveData<TransactionResponse> getTransaction(String username, int limit, boolean isApproval) {
        MutableLiveData<TransactionResponse> data = new MutableLiveData<>();
        apiInterface.getTransaction(
                username,
                limit,
                isApproval
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

    public MutableLiveData<FormResponse> getListForm(String role) {
        MutableLiveData<FormResponse> data = new MutableLiveData<>();
        apiInterface.getListForm(
                role
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

    public MutableLiveData<BaseResponse> postChecklistRuangMeeting(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postChecklistRuangMeeting(
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
                Log.e("postCheckRuangMeeting", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postOrderCatering(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postOrderCatering(
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
                Log.e("postOrderCatering", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postMaterialUsedSlip(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postMaterialUsedSlip(
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
                Log.e("postMaterialUsedSlip", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postPermintaanCateringReguler(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postPermintaanCateringReguler(
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
                Log.e("postPermintaanCatReg", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<PostMobilResponse> postPermintaanMobilDinas(String body) {
        MutableLiveData<PostMobilResponse> data = new MutableLiveData<>();
        apiInterface.postPermintaanMobilDinas(
                body
        ).enqueue(new Callback<PostMobilResponse>() {
            @Override
            public void onResponse(Call<PostMobilResponse> call, Response<PostMobilResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PostMobilResponse> call, Throwable t) {
                Log.e("postPermintaanMobDin", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<PostMobilResponse> postPermintaanMobilPribadi(String body) {
        MutableLiveData<PostMobilResponse> data = new MutableLiveData<>();
        apiInterface.postPermintaanMobilPribadi(
                body
        ).enqueue(new Callback<PostMobilResponse>() {
            @Override
            public void onResponse(Call<PostMobilResponse> call, Response<PostMobilResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PostMobilResponse> call, Throwable t) {
                Log.e("postPermintaanMobPri", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postIdentifikasi(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postIdentifikasi(
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
                Log.e("postIdentifikasi", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postExternalWorkOrder(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postExternalWorkOrder(
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
                Log.e("postExternalWorkOrder", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postSidakCatering(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postSidakCatering(
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
                Log.e("SidakCatering", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postLegalitas(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postLegalitas(
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
                Log.e("postLegalitas", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postControlHarian(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postControlHarian(
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
                Log.e("postControlHarian", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postLaporanPerbaikan(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postLaporanPerbaikan(
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
                Log.e("postLaporanPerbaikan", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postICGS(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postICGS(
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
                Log.e("postICGS", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postPerbaikan(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postPerbaikan(
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
                Log.e("postPerbaikan", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postICP(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postICP(
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
                Log.e("postICP", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postICH(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postICH(
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
                Log.e("postICH", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postICPAB(String body) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postICPAB(
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
                Log.e("postICPAB", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postSimMobilDinas(RequestBody idUser, RequestBody idTrans, MultipartBody.Part file) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postSimMobilDinas(
                idUser,
                idTrans,
                file
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("postSimMobilDinas", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> postSimMobilPribadi(RequestBody idUser, RequestBody idTrans, MultipartBody.Part file) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.postSimMobilPribadi(
                idUser,
                idTrans,
                file
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("postSimMobilPribadi", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> putNopolMobilDinas(String idUser, String idTrans, String nopol) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.putNopolMobilDinas(
                idUser,
                idTrans,
                nopol
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("putNopolMobilDinas", t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<BaseResponse> putNopolMobilPribadi(String idUser, String idTrans, String nopol) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterface.putNopolMobilPribadi(
                idUser,
                idTrans,
                nopol
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("putNopolMobilPribadi", t.getMessage());
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

    public MutableLiveData<TransactionDetailResponse> getTransactionDetail(String username, String idTrans, boolean isApproval) {
        MutableLiveData<TransactionDetailResponse> data = new MutableLiveData<>();
        apiInterface.getTransactionDetail(
                username,
                idTrans,
                isApproval
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
