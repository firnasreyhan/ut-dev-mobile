package com.unitedtractors.android.unitedtractorsapp.model;

public class PembelianSnackModel {
    private String jenisSnack;
    private String jumlah;

    public PembelianSnackModel(String jenisSnack, String jumlah) {
        this.jenisSnack = jenisSnack;
        this.jumlah = jumlah;
    }

    public String getJenisSnack() {
        return jenisSnack;
    }

    public void setJenisSnack(String jenisSnack) {
        this.jenisSnack = jenisSnack;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public boolean checkData()  {
        if (!getJenisSnack().isEmpty() && (!getJumlah().isEmpty() && !getJumlah().equalsIgnoreCase("0"))) {
            return true;
        } else {
            return false;
        }
    }
}
