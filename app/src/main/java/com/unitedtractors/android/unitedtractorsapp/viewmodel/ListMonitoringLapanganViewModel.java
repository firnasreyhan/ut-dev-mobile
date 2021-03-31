package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;

import java.util.List;

public class ListMonitoringLapanganViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;


    public ListMonitoringLapanganViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
    }

    public LiveData<List<DetailMingguMonitoringCateringEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMingguMonitoringCatering(mingguKe);
    }

    public void insertDetailMinggu(DetailMingguMonitoringCateringEntity detailMingguMonitoringCateringEntity){
        offlineRepository.insertDetailMingguMonitoringCatering(detailMingguMonitoringCateringEntity);
    }

    public void deleteDetailMinggu(int mingguKe){
        offlineRepository.deleteDetailMingguMonitoringCatering(mingguKe);
    }

    public void updateMinggu(int id, boolean b){
        offlineRepository.updateMingguMonitoringCatering(id, b);
    }
}
