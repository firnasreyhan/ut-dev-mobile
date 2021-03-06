package com.unitedtractors.android.unitedtractorsapp.model;

public class Pertanyaan3Model {
    private String pertanyaan;
    private boolean status;
    private String keterangan;
    private String catatan;

    public Pertanyaan3Model(String pertanyaan, boolean status, String keterangan, String catatan) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
