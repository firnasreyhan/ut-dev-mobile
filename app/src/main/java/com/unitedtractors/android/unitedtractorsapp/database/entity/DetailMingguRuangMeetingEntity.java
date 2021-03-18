package com.unitedtractors.android.unitedtractorsapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DetailMingguRuangMeetingEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "mingguKe")
    public int mingguKe;

    @ColumnInfo(name = "pertanyaan")
    public String pertanyaan;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    @ColumnInfo(name = "tanggalView")
    public String tanggalView;

    @ColumnInfo(name = "jam")
    public String jam;
}
