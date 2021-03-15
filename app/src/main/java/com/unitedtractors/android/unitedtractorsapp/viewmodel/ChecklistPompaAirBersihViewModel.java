package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;

import java.util.List;

public class ChecklistPompaAirBersihViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;


    public ChecklistPompaAirBersihViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
    }

    public LiveData<List<MingguEntity>> getMinggu(){
        return offlineRepository.getMinggu();
    }

    public void insertMinggu(MingguEntity mingguEntity){
        offlineRepository.insertMinggu(mingguEntity);
    }

    public void deleteDetaiAlllMinggu(){
        offlineRepository.deleteDetaiAlllMinggu();
    }

    public void updateAllMinggu(boolean status){
        offlineRepository.updateAllMinggu(status);
    }
}
