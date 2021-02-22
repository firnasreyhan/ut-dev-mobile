package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.FirebaseDatabase;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> signOut() {
        String userKey = AppPreference.getUser(getApplication().getApplicationContext()).getUserUsers().replaceAll("[-+.^:,]","");
        FirebaseDatabase.getInstance().getReference("UnitedTractor").child("Token").child(userKey).removeValue();
        return repository.postSignOut(AppPreference.getUser(getApplication().getApplicationContext()).getIdUsers());
    }

    public MutableLiveData<TransactionResponse> getTransaction(String username, int limit) {
        return repository.getTransaction(username, limit);
    }
}
