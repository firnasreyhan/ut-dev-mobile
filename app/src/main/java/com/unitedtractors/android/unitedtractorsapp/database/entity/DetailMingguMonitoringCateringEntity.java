package com.unitedtractors.android.unitedtractorsapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DetailMingguMonitoringCateringEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "mingguKe")
    public int mingguKe;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    @ColumnInfo(name = "tanggalView")
    public String tanggalView;

    @ColumnInfo(name = "order")
    public String order;

    @ColumnInfo(name = "bawa")
    public String bawa;

    @ColumnInfo(name = "kupon")
    public String kupon;
}
