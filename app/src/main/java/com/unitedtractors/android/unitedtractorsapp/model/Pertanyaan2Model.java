package com.unitedtractors.android.unitedtractorsapp.model;

public class Pertanyaan2Model {
    private String pertanyaan;
    private int status;
    private String catatan;

    public Pertanyaan2Model(String pertanyaan, int status, String catatan) {
        this.pertanyaan = pertanyaan;
        this.status = status;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
