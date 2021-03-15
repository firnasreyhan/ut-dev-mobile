package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

public class FormListViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public FormListViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<FormResponse> getForm(String role) {
        return onlineRepository.getListForm(role);
    }
}
