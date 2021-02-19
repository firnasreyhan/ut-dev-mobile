package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;
import com.unitedtractors.android.unitedtractorsapp.model.TokenModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

public class SignInViewModel extends AndroidViewModel {
    private Repository repository;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<SignInResponse> signIn(String username, String password) {
        return repository.postSignIn(username, password, updateToken(username));
    }

    private String updateToken(String username) {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        String userKey = username.replaceAll("[-+.^:,]","");
        Log.e("userKey", userKey);
        TokenModel token = new TokenModel(refreshToken);
        FirebaseDatabase.getInstance().getReference("UnitedTractor").child("Token").child(userKey).setValue(token);
        return refreshToken;
    }
}
