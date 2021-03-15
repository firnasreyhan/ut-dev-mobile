package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;

import java.util.List;

public class ListChecklistPompaAirBersihViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;


    public ListChecklistPompaAirBersihViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
    }

    public LiveData<List<DetailMingguEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMinggu(mingguKe);
    }

    public void insertDetailMinggu(DetailMingguEntity detailMingguEntity){
        offlineRepository.insertDetailMinggu(detailMingguEntity);
    }

    public void deleteDetailMinggu(int mingguKe){
        offlineRepository.deleteDetailMinggu(mingguKe);
    }

    public void updateMinggu(int id, boolean b){
        offlineRepository.updateMinggu(id, b);
    }
}
