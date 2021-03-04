package com.unitedtractors.android.unitedtractorsapp.model;

public class IdentifikasiModel {
    private String temuanLapangan;
    private String tanggal;
    private String tanggalView;
    private String katergoriTemuan;
    private String lokasi;
    private String user;

    public IdentifikasiModel(String temuanLapangan, String tanggal, String tanggalView, String katergoriTemuan, String lokasi, String user) {
        this.temuanLapangan = temuanLapangan;
        this.tanggal = tanggal;
        this.tanggalView = tanggalView;
        this.katergoriTemuan = katergoriTemuan;
        this.lokasi = lokasi;
        this.user = user;
    }

    public String getTemuanLapangan() {
        return temuanLapangan;
    }

    public void setTemuanLapangan(String temuanLapangan) {
        this.temuanLapangan = temuanLapangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggalView() {
        return tanggalView;
    }

    public void setTanggalView(String tanggalView) {
        this.tanggalView = tanggalView;
    }

    public String getKatergoriTemuan() {
        return katergoriTemuan;
    }

    public void setKatergoriTemuan(String katergoriTemuan) {
        this.katergoriTemuan = katergoriTemuan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean checkData()  {
        if (!getTemuanLapangan().isEmpty() && !getTanggal().isEmpty() && !getKatergoriTemuan().isEmpty() && !getLokasi().isEmpty() && !getUser().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
