package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.DetailDebitNoteResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.DokumenPendukungResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionDetailResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class DebitNoteDetailViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public DebitNoteDetailViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> putConfirmDebitNote(String username, String idTrans, int isApprove) {
        return onlineRepository.putConfirmDebitNote(username, idTrans, isApprove);
    }

    public MutableLiveData<DetailDebitNoteResponse> getDebitNoteDetail(String username, String idTrans) {
        return onlineRepository.getDebitNoteDetail(username, idTrans);
    }
}
