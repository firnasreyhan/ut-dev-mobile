package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;

import java.util.List;

public class ListChecklistRuangMeetingViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;


    public ListChecklistRuangMeetingViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
    }

    public LiveData<List<DetailMingguRuangMeetingEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMingguRuangMeeting(mingguKe);
    }

    public void insertDetailMinggu(DetailMingguRuangMeetingEntity detailMingguRuangMeetingEntity){
        offlineRepository.insertDetailMingguRuangMeeting(detailMingguRuangMeetingEntity);
    }

    public void deleteDetailMinggu(int mingguKe){
        offlineRepository.deleteDetailMingguRuangMeeting(mingguKe);
    }

    public void updateMinggu(int id, boolean b){
        offlineRepository.updateMingguRuangMeeting(id, b);
    }
}
