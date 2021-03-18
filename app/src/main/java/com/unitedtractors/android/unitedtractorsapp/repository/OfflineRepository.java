package com.unitedtractors.android.unitedtractorsapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.database.AppDAO;
import com.unitedtractors.android.unitedtractorsapp.database.Database;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguEntity;
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

    public void updateMinggu(int id, boolean b){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateMinggu(id, b);
            }
        });
    }

    public LiveData<List<DetailMingguEntity>> getDetailMinggu(int mingguKe){
        return appDAO.getDetailMinggu(mingguKe);
    }

    public LiveData<List<DetailMingguEntity>> getDetailMinggu(){
        return appDAO.getDetailMinggu();
    }

    public void insertDetailMinggu (final DetailMingguEntity detailMingguEntity){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.insertDetailMinggu(detailMingguEntity);
            }
        });
    }

    public void deleteDetailMinggu(int mingguKe){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteDetailMinggu(mingguKe);
            }
        });
    }

    public void deleteDetaiAlllMinggu (){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteDetaiAlllMinggu();
            }
        });
    }

    public void updateAllMinggu(boolean status){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateAllMinggu(status);
            }
        });
    }
}
