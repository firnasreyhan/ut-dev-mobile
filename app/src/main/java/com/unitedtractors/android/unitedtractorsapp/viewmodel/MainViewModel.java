package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.FirebaseDatabase;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class MainViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> signOut() {
        String userKey = AppPreference.getUser(getApplication().getApplicationContext()).getUserUsers().replaceAll("[-+.^:,]","");
        FirebaseDatabase.getInstance().getReference("UnitedTractor").child("Token").child(userKey).removeValue();
        return onlineRepository.postSignOut(AppPreference.getUser(getApplication().getApplicationContext()).getIdUsers());
    }

    public MutableLiveData<TransactionResponse> getTransaction(String username, int limit, boolean isApproval) {
        return onlineRepository.getTransaction(username, limit, isApproval);
    }

    public MutableLiveData<FormResponse> getForm(String role) {
        return onlineRepository.getListForm(role);
    }
}
