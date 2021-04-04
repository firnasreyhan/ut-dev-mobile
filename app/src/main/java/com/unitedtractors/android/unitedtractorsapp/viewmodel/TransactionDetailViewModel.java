package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.DokumenPendukungResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionDetailResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class TransactionDetailViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public TransactionDetailViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> putConfirm(String username, String idTrans, int isApprove, String keterangan) {
        return onlineRepository.putConfirm(username, idTrans, isApprove, keterangan);
    }

    public MutableLiveData<BaseResponse> putNopolMobilDinas(String idUser, String idTrans, String nopol) {
        return onlineRepository.putNopolMobilDinas(idUser, idTrans, nopol);
    }

    public MutableLiveData<BaseResponse> putNopolMobilPribadi(String idUser, String idTrans, String nopol) {
        return onlineRepository.putNopolMobilPribadi(idUser, idTrans, nopol);
    }

    public MutableLiveData<TransactionDetailResponse> getTransactionDetail(String username, String idTrans, boolean isApproval) {
        return onlineRepository.getTransactionDetail(username, idTrans, isApproval);
    }

    public MutableLiveData<DokumenPendukungResponse> getDokumenPendukung(String idUser, String idTrans) {
        return onlineRepository.getDokumenPendukung(idUser, idTrans);
    }
}
