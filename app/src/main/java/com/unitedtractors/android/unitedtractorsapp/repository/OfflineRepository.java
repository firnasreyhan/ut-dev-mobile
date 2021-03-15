package com.unitedtractors.android.unitedtractorsapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.unitedtractors.android.unitedtractorsapp.database.AppDAO;
import com.unitedtractors.android.unitedtractorsapp.database.Database;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;

import java.util.List;

public class OfflineRepository {
    private AppDAO appDAO;

    public OfflineRepository(Application application) {
        this.appDAO = Database.getInstance(application).appDAO();
    }

    public LiveData<List<MingguEntity>> getMinggu(){
        return appDAO.getMinggu();
    }

    public void insertMinggu (final MingguEntity mingguEntity){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.insertMinggu(mingguEntity);
            }
        });
    }

    public void updateAlarm(int id, boolean b){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateMinggu(id, b);
            }
        });
    }
}
