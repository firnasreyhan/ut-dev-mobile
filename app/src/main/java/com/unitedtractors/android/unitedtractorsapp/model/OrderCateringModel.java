package com.unitedtractors.android.unitedtractorsapp.model;

public class OrderCateringModel {
    String idMapping;
    String idUser;
    String tanggal;
    String divisi;
    String jumlahPesanan;

    public OrderCateringModel(String idMapping, String idUser, String tanggal, String divisi, String jumlahPesanan) {
        this.idMapping = idMapping;
        this.idUser = idUser;
        this.tanggal = tanggal;
        this.divisi = divisi;
        this.jumlahPesanan = jumlahPesanan;
    }

    public String getIdMapping() {
        return idMapping;
    }

    public void setIdMapping(String idMapping) {
        this.idMapping = idMapping;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void setJumlahPesanan(String jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }

    public boolean checkData()  {
        if (!getJumlahPesanan().isEmpty() && !getTanggal().isEmpty()  && !getDivisi().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
