package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.DebitNoteResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class DebitNoteListViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public DebitNoteListViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<DebitNoteResponse> getDebitNote(String username, int limit) {
        return onlineRepository.getDebitNote(username, limit);
    }
}
