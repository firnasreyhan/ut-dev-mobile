package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

public class BerandaViewModel extends AndroidViewModel {
    private Repository repository;

    public BerandaViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<TransactionResponse> getTransaction(String username, int limit) {
        return repository.getTransaction(username, limit);
    }

    public MutableLiveData<FormResponse> getForm(String divisi) {
        return repository.getListForm(divisi);
    }
}
