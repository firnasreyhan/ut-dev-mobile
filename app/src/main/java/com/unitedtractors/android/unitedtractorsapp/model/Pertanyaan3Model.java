package com.unitedtractors.android.unitedtractorsapp.model;

import java.io.Serializable;

public class Pertanyaan3Model implements Serializable {
    private String pertanyaan;
    private int status;
    private String keterangan;
    private String catatan;

    public Pertanyaan3Model(String pertanyaan, int status, String keterangan, String catatan) {
        this.pertanyaan = pertanyaan;
        this.status = status;
        this.keterangan = keterangan;
        this.catatan = catatan;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
