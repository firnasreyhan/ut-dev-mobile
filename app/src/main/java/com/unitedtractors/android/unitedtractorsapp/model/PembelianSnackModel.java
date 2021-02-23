package com.unitedtractors.android.unitedtractorsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PembelianSnackModel {
    private String idUser;
    private String idMapping;
    private String tglSnack;
    private String divisiSnack;
    private String keperluanSnack;
    private List<DetailPembelianSnackModel> detSnack;

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

    public String getTglSnack() {
        return tglSnack;
    }

    public void setTglSnack(String tglSnack) {
        this.tglSnack = tglSnack;
    }

    public String getDivisiSnack() {
        return divisiSnack;
    }

    public void setDivisiSnack(String divisiSnack) {
        this.divisiSnack = divisiSnack;
    }

    public String getKeperluanSnack() {
        return keperluanSnack;
    }

    public void setKeperluanSnack(String keperluanSnack) {
        this.keperluanSnack = keperluanSnack;
    }

    public List<DetailPembelianSnackModel> getDetSnack() {
        return detSnack;
    }

    public void setDetSnack(List<DetailPembelianSnackModel> detSnack) {
        this.detSnack = detSnack;
    }

    public static class DetailPembelianSnackModel {
        private String jenisSnack;
        private String jumlah;

        public DetailPembelianSnackModel(String jenisSnack, String jumlah) {
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
}
