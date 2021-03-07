package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class KontrolHarianModel {
    private String idUser;
    private String idMapping;
    private String jamKontrol;
    private String tglOut;
    private List<DetailKontrolHarian> data;

    public KontrolHarianModel(String idUser, String idMapping, String jamKontrol, String tglOut, List<DetailKontrolHarian> data) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.jamKontrol = jamKontrol;
        this.tglOut = tglOut;
        this.data = data;
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

    public String getJamKontrol() {
        return jamKontrol;
    }

    public void setJamKontrol(String jamKontrol) {
        this.jamKontrol = jamKontrol;
    }

    public String getTglOut() {
        return tglOut;
    }

    public void setTglOut(String tglOut) {
        this.tglOut = tglOut;
    }

    public List<DetailKontrolHarian> getData() {
        return data;
    }

    public void setData(List<DetailKontrolHarian> data) {
        this.data = data;
    }

    public static class DetailKontrolHarian {
        private String judul;
        private int status;
        private String keterangan;

        public DetailKontrolHarian(String judul, int status, String keterangan) {
            this.judul = judul;
            this.status = status;
            this.keterangan = keterangan;
        }

        public String getJudul() {
            return judul;
        }

        public void setJudul(String judul) {
            this.judul = judul;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }
    }
}
