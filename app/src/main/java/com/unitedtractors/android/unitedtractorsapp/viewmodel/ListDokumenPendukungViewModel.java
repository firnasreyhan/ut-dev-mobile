package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.DokumenPendukungResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class ListDokumenPendukungViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public ListDokumenPendukungViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<DokumenPendukungResponse> getDokumenPendukung(String idUser, String idTrans) {
        return onlineRepository.getDokumenPendukung(idUser, idTrans);
    }
}
