package com.unitedtractors.android.unitedtractorsapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;

import java.util.List;

@Dao
public interface AppDAO {
    @Query("SELECT * FROM mingguentity")
    LiveData<List<MingguEntity>> getMinggu();

    @Insert
    void insertMinggu(MingguEntity mingguEntity);

    @Query("UPDATE MingguEntity SET status = :status WHERE id = :id")
    void updateMinggu(int id, boolean status);
}
