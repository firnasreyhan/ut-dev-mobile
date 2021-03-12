package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PembelianSnackResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionDetailResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

public class TransactionDetailViewModel extends AndroidViewModel {
    private Repository repository;

    public TransactionDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> putConfirm(String username, String idTrans, int isApprove, String keterangan) {
        return repository.putConfirm(username, idTrans, isApprove, keterangan);
    }

    public MutableLiveData<BaseResponse> putNopolMobilDinas(String idUser, String idTrans, String nopol) {
        return repository.putNopolMobilDinas(idUser, idTrans, nopol);
    }

    public MutableLiveData<BaseResponse> putNopolMobilPribadi(String idUser, String idTrans, String nopol) {
        return repository.putNopolMobilPribadi(idUser, idTrans, nopol);
    }

    public MutableLiveData<TransactionDetailResponse> getTransactionDetail(String username, String idTrans, boolean isApproval) {
        return repository.getTransactionDetail(username, idTrans, isApproval);
    }
}
