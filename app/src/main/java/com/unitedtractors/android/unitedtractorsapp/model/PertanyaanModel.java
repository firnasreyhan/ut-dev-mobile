package com.unitedtractors.android.unitedtractorsapp.model;

public class PertanyaanModel {
    private String pertanyaan;
    private boolean status;

    public PertanyaanModel(String pertanyaan, boolean status) {
        this.pertanyaan = pertanyaan;
        this.status = status;
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
}
