package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;

import java.util.List;

public class ListChecklistPompaAirBersihViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;


    public ListChecklistPompaAirBersihViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
    }

    public LiveData<List<DetailMingguPompaAirBersihEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMingguPompaAirBersih(mingguKe);
    }

    public void insertDetailMinggu(DetailMingguPompaAirBersihEntity detailMingguPompaAirBersihEntity){
        offlineRepository.insertDetailMingguPompaAirBersih(detailMingguPompaAirBersihEntity);
    }

    public void deleteDetailMinggu(int mingguKe){
        offlineRepository.deleteDetailMingguPompaAirBersih(mingguKe);
    }

    public void updateMinggu(int id, boolean b){
        offlineRepository.updateMingguPompaAirBersih(id, b);
    }
}
