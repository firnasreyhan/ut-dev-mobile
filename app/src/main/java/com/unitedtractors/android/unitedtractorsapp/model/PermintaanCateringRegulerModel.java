package com.unitedtractors.android.unitedtractorsapp.model;

public class PermintaanCateringRegulerModel {
    String idUser;
    String idMapping;
    int jumlahOrang;
    String tgl;

    public PermintaanCateringRegulerModel(String idUser, String idMapping, int jumlahOrang, String tgl) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.jumlahOrang = jumlahOrang;
        this.tgl = tgl;
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

    public int getJumlahOrang() {
        return jumlahOrang;
    }

    public void setJumlahOrang(int jumlahOrang) {
        this.jumlahOrang = jumlahOrang;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

}
