package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class ApprovalListViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public ApprovalListViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<TransactionResponse> getTransaction(String username, int limit, boolean isApproval) {
        return onlineRepository.getTransaction(username, limit, isApproval);
    }
}
