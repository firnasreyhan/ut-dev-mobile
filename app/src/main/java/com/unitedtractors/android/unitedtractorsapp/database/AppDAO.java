package com.unitedtractors.android.unitedtractorsapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguRuangMeetingEntity;

import java.util.List;

@Dao
public interface AppDAO {
    //Pompa Air Berish
    @Query("SELECT * FROM MingguPompaAirBersihEntity")
    LiveData<List<MingguPompaAirBersihEntity>> getMingguPompaAirBersih();

    @Insert
    void insertMingguPompaAirBersih(MingguPompaAirBersihEntity mingguPompaAirBersihEntity);

    @Query("UPDATE MingguPompaAirBersihEntity SET status = :status WHERE id = :id")
    void updateMingguPompaAirBersih(int id, boolean status);

    @Query("SELECT * FROM DetailMingguPompaAirBersihEntity WHERE mingguKe = :mingguKe")
    LiveData<List<DetailMingguPompaAirBersihEntity>> getDetailMingguPompaAirBersih(int mingguKe);

    @Query("SELECT * FROM DetailMingguPompaAirBersihEntity")
    LiveData<List<DetailMingguPompaAirBersihEntity>> getDetailMingguPompaAirBersih();

    @Insert
    void insertDetailMingguPompaAirBersih(DetailMingguPompaAirBersihEntity detailMingguPompaAirBersihEntity);

    @Query("DELETE FROM DetailMingguPompaAirBersihEntity WHERE mingguKe = :mingguKe")
    void deleteDetailMingguPompaAirBersih(int mingguKe);

    @Query("DELETE FROM DetailMingguPompaAirBersihEntity")
    void deleteDetailAllMingguPompaAirBersih();

    @Query("UPDATE MingguPompaAirBersihEntity SET status = :status")
    void updateAllMingguPompaAirBersih(boolean status);

    //Ruang Meeting
    @Query("SELECT * FROM MingguRuangMeetingEntity")
    LiveData<List<MingguRuangMeetingEntity>> getMingguRuangMeeting();

    @Insert
    void insertMingguRuangMeeting(MingguRuangMeetingEntity mingguRuangMeetingEntity);

    @Query("UPDATE MingguRuangMeetingEntity SET status = :status WHERE id = :id")
    void updateMingguRuangMeeting(int id, boolean status);

    @Query("SELECT * FROM DetailMingguRuangMeetingEntity WHERE mingguKe = :mingguKe")
    LiveData<List<DetailMingguRuangMeetingEntity>> getDetailMingguRuangMeeting(int mingguKe);

    @Query("SELECT * FROM DetailMingguRuangMeetingEntity")
    LiveData<List<DetailMingguRuangMeetingEntity>> getDetailMingguRuangMeeting();

    @Insert
    void insertDetailMingguRuangMeeting(DetailMingguRuangMeetingEntity detailMingguRuangMeetingEntity);

    @Query("DELETE FROM DetailMingguRuangMeetingEntity WHERE mingguKe = :mingguKe")
    void deleteDetailMingguRuangMeeting(int mingguKe);

    @Query("DELETE FROM DetailMingguRuangMeetingEntity")
    void deleteDetailAllMingguRuangMeeting();

    @Query("UPDATE MingguRuangMeetingEntity SET status = :status")
    void updateAllMingguRuangMeeting(boolean status);

    //Monitoring Catering
    @Query("SELECT * FROM MingguMonitoringCateringEntity")
    LiveData<List<MingguMonitoringCateringEntity>> getMingguMonitoringCatering();

    @Insert
    void insertMonitoringCatering(MingguMonitoringCateringEntity mingguMonitoringCateringEntity);

    @Query("UPDATE MingguMonitoringCateringEntity SET status = :status WHERE id = :id")
    void updateMingguMonitoringCatering(int id, boolean status);

    @Query("SELECT * FROM DetailMingguMonitoringCateringEntity WHERE mingguKe = :mingguKe")
    LiveData<List<DetailMingguMonitoringCateringEntity>> getDetailMonitoringCatering(int mingguKe);

    @Query("SELECT * FROM DetailMingguMonitoringCateringEntity")
    LiveData<List<DetailMingguMonitoringCateringEntity>> getDetailMonitoringCatering();

    @Insert
    void insertDetailMingguMonitoringCatering(DetailMingguMonitoringCateringEntity detailMingguMonitoringCateringEntity);

    @Query("DELETE FROM DetailMingguMonitoringCateringEntity WHERE mingguKe = :mingguKe")
    void deleteDetailMingguMonitoringCatering(int mingguKe);

    @Query("DELETE FROM DetailMingguMonitoringCateringEntity")
    void deleteDetailAllMingguMonitoringCatering();

    @Query("UPDATE MingguMonitoringCateringEntity SET status = :status")
    void updateAllMingguMonitoringCatering(boolean status);
}
