package com.unitedtractors.android.unitedtractorsapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DetailMingguEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "mingguKe")
    public int mingguKe;

    @ColumnInfo(name = "pertanyaan")
    public String pertanyaan;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "keterangan")
    public String keterangan;

    @ColumnInfo(name = "catatan")
    public String catatan;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    @ColumnInfo(name = "tanggalView")
    public String tanggalView;
}
