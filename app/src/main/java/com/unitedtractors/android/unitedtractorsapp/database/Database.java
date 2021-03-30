package com.unitedtractors.android.unitedtractorsapp.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguRuangMeetingEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {
        MingguPompaAirBersihEntity.class,
        DetailMingguPompaAirBersihEntity.class,
        MingguRuangMeetingEntity.class,
        DetailMingguRuangMeetingEntity.class,
        MingguMonitoringCateringEntity.class,
        DetailMingguMonitoringCateringEntity.class
}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static final String DB_NAME = "ut_db";
    private static Database instance;
    public static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract AppDAO appDAO();
}
