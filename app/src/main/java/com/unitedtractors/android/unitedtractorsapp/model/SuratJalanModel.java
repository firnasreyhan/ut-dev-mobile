package com.unitedtractors.android.unitedtractorsapp.model;

public class SuratJalanModel {
    private String idUser;
    private String idMapping;
    private String tanggal;
    private String nama;
    private String kendaraan;
    private String keperluan;

    public SuratJalanModel(String idUser, String idMapping, String tanggal, String nama, String kendaraan, String keperluan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tanggal = tanggal;
        this.nama = nama;
        this.kendaraan = kendaraan;
        this.keperluan = keperluan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdMapping() {
        return idMapping;
    }

    public void setIdMapping(String idMapping) {
        this.idMapping = idMapping;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(String kendaraan) {
        this.kendaraan = kendaraan;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }
}
