package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class NonAssetModel {
    private String idUser;
    private String idMapping;
    private List<DetNonAsset> detNonAsset;

    public NonAssetModel(String idUser, String idMapping, List<DetNonAsset> detNonAsset) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.detNonAsset = detNonAsset;
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

    public List<DetNonAsset> getDetNonAsset() {
        return detNonAsset;
    }

    public void setDetNonAsset(List<DetNonAsset> detNonAsset) {
        this.detNonAsset = detNonAsset;
    }

    public static class DetNonAsset{
        private String tgl;
        private String jenBarang;
        private String account;
        private String cost;
        private int jmlPesan;
        private String keterangan;

        public DetNonAsset(String tgl, String jenBarang, String account, String cost, int jmlPesan, String keterangan) {
            this.tgl = tgl;
            this.jenBarang = jenBarang;
            this.account = account;
            this.cost = cost;
            this.jmlPesan = jmlPesan;
            this.keterangan = keterangan;
        }

        public String getTgl() {
            return tgl;
        }

        public void setTgl(String tgl) {
            this.tgl = tgl;
        }

        public String getJenBarang() {
            return jenBarang;
        }

        public void setJenBarang(String jenBarang) {
            this.jenBarang = jenBarang;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public int getJmlPesan() {
            return jmlPesan;
        }

        public void setJmlPesan(int jmlPesan) {
            this.jmlPesan = jmlPesan;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public boolean checkData() {
            return !getTgl().isEmpty() && !getJenBarang().isEmpty() && !getAccount().isEmpty() && !getCost().isEmpty() && getJmlPesan() != 0;
        }
    }
}
