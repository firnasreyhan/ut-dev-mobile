package com.unitedtractors.android.unitedtractorsapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.unitedtractors.android.unitedtractorsapp.database.AppDAO;
import com.unitedtractors.android.unitedtractorsapp.database.Database;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguRuangMeetingEntity;

import java.util.List;

public class OfflineRepository {
    private AppDAO appDAO;

    public OfflineRepository(Application application) {
        this.appDAO = Database.getInstance(application).appDAO();
    }

    //Pompa Air Bersih
    public LiveData<List<MingguPompaAirBersihEntity>> getMingguPompaAirBersih(){
        return appDAO.getMingguPompaAirBersih();
    }

    public void insertMingguPompaAirBersih(final MingguPompaAirBersihEntity mingguPompaAirBersihEntity){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.insertMingguPompaAirBersih(mingguPompaAirBersihEntity);
            }
        });
    }

    public void updateMingguPompaAirBersih(int id, boolean b){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateMingguPompaAirBersih(id, b);
            }
        });
    }

    public LiveData<List<DetailMingguPompaAirBersihEntity>> getDetailMingguPompaAirBersih(int mingguKe){
        return appDAO.getDetailMingguPompaAirBersih(mingguKe);
    }

    public LiveData<List<DetailMingguPompaAirBersihEntity>> getDetailMingguPompaAirBersih(){
        return appDAO.getDetailMingguPompaAirBersih();
    }

    public void insertDetailMingguPompaAirBersih(final DetailMingguPompaAirBersihEntity detailMingguPompaAirBersihEntity){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.insertDetailMingguPompaAirBersih(detailMingguPompaAirBersihEntity);
            }
        });
    }

    public void deleteDetailMingguPompaAirBersih(int mingguKe){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteDetailMingguPompaAirBersih(mingguKe);
            }
        });
    }

    public void deleteDetailAllMingguPompaAirBersih(){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteDetailAllMingguPompaAirBersih();
            }
        });
    }

    public void updateAllMingguPompaAirBersih(boolean status){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateAllMingguPompaAirBersih(status);
            }
        });
    }

    //Ruang Meeting
    public LiveData<List<MingguRuangMeetingEntity>> getMingguRuangMeeting(){
        return appDAO.getMingguRuangMeeting();
    }

    public void insertMingguRuangMeeting(final MingguRuangMeetingEntity mingguRuangMeetingEntity){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.insertMingguRuangMeeting(mingguRuangMeetingEntity);
            }
        });
    }

    public void updateMingguRuangMeeting(int id, boolean b){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateMingguRuangMeeting(id, b);
            }
        });
    }

    public LiveData<List<DetailMingguRuangMeetingEntity>> getDetailMingguRuangMeeting(int mingguKe){
        return appDAO.getDetailMingguRuangMeeting(mingguKe);
    }

    public LiveData<List<DetailMingguRuangMeetingEntity>> getDetailMingguRuangMeeting(){
        return appDAO.getDetailMingguRuangMeeting();
    }

    public void insertDetailMingguRuangMeeting(final DetailMingguRuangMeetingEntity detailMingguRuangMeetingEntity){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.insertDetailMingguRuangMeeting(detailMingguRuangMeetingEntity);
            }
        });
    }

    public void deleteDetailMingguRuangMeeting(int mingguKe){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteDetailMingguRuangMeeting(mingguKe);
            }
        });
    }

    public void deleteDetailAllMingguRuangMeeting(){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteDetailAllMingguRuangMeeting();
            }
        });
    }

    public void updateAllMingguRuangMeeting(boolean status){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateAllMingguRuangMeeting(status);
            }
        });
    }
}
