package com.unitedtractors.android.unitedtractorsapp.model;

public class Pertanyaan4Model {
    private String pertanyaan;
    private int status;

    public Pertanyaan4Model(String pertanyaan, int status) {
        this.pertanyaan = pertanyaan;
        this.status = status;
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
}
