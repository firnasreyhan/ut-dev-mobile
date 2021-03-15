package com.unitedtractors.android.unitedtractorsapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;

import java.util.List;

@Dao
public interface AppDAO {
    @Query("SELECT * FROM MingguEntity")
    LiveData<List<MingguEntity>> getMinggu();

    @Insert
    void insertMinggu(MingguEntity mingguEntity);

    @Query("UPDATE MingguEntity SET status = :status WHERE id = :id")
    void updateMinggu(int id, boolean status);

    @Query("SELECT * FROM DetailMingguEntity WHERE mingguKe = :mingguKe")
    LiveData<List<DetailMingguEntity>> getDetailMinggu(int mingguKe);

    @Insert
    void insertDetailMinggu(DetailMingguEntity detailMingguEntity);

    @Query("DELETE FROM DetailMingguEntity WHERE mingguKe = :mingguKe")
    void deleteDetailMinggu(int mingguKe);

    @Query("DELETE FROM DetailMingguEntity")
    void deleteDetaiAlllMinggu();

    @Query("UPDATE MingguEntity SET status = :status")
    void updateAllMinggu(boolean status);
}
