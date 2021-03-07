package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

public class ApprovalListViewModel extends AndroidViewModel {
    private Repository repository;

    public ApprovalListViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<TransactionResponse> getTransaction(String username, int limit, boolean isApproval) {
        return repository.getTransaction(username, limit, isApproval);
    }
}
