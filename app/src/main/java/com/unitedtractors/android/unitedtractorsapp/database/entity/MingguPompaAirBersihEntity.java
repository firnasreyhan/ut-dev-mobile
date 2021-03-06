package com.unitedtractors.android.unitedtractorsapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MingguPompaAirBersihEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "mingguKe")
    public String mingguKe;

    @ColumnInfo (name = "status")
    public boolean status;
}
