package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.PembelianSnackResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class DetailApprovalPembelianSnackViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public DetailApprovalPembelianSnackViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> putConfirm(String username, String idTrans, int isApprove, String keterangan) {
        return onlineRepository.putConfirm(username, idTrans, isApprove, keterangan);
    }

    public MutableLiveData<PembelianSnackResponse> getPembelianSnack(String idTrans) {
        return onlineRepository.getPembelianSnack(idTrans);
    }
}
