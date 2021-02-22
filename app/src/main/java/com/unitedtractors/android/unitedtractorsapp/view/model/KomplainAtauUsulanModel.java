package com.unitedtractors.android.unitedtractorsapp.view.model;

public class KomplainAtauUsulanModel {

    private String nama;
    private String divisi;
    private String komplainAtauUsulan;

    public KomplainAtauUsulanModel(String nama, String divisi, String komplainAtauUsulan) {
        this.nama = nama;
        this.divisi = divisi;
        this.komplainAtauUsulan = komplainAtauUsulan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getKomplainAtauUsulan() {
        return komplainAtauUsulan;
    }

    public void setKomplainAtauUsulan(String komplainAtauUsulan) {
        this.komplainAtauUsulan = komplainAtauUsulan;
    }

    public boolean checkData()  {
        if (!getNama().isEmpty() && !getDivisi().isEmpty() && !getKomplainAtauUsulan().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
