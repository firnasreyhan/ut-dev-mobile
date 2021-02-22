package com.unitedtractors.android.unitedtractorsapp.model;

public class TujuanMobilDinasModel {

    private String tujuan;
    private String keperluan;
    private String catatan;

    public TujuanMobilDinasModel(String tujuan, String keperluan, String catatan) {
        this.tujuan = tujuan;
        this.keperluan = keperluan;
        this.catatan = catatan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public boolean checkData()  {
        if (!getTujuan().isEmpty() && !getKeperluan().isEmpty() && !getCatatan().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
