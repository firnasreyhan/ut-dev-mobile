package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class LaporanPerbaikanModel {
    private String idUser;
    private String idMapping;
    private String tglPerbaikan;
    private String tglPerbaikanView;
    private String tglOutPerbaikan;
    private String tglOutPerbaikanView;
    private List<DetailLaporanPerbaikan> detPerbaikan;

    public LaporanPerbaikanModel(String idUser, String idMapping, String tglPerbaikan, String tglPerbaikanView, String tglOutPerbaikan, String tglOutPerbaikanView, List<DetailLaporanPerbaikan> detPerbaikan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tglPerbaikan = tglPerbaikan;
        this.tglPerbaikanView = tglPerbaikanView;
        this.tglOutPerbaikan = tglOutPerbaikan;
        this.tglOutPerbaikanView = tglOutPerbaikanView;
        this.detPerbaikan = detPerbaikan;
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

    public String getTglPerbaikan() {
        return tglPerbaikan;
    }

    public void setTglPerbaikan(String tglPerbaikan) {
        this.tglPerbaikan = tglPerbaikan;
    }

    public String getTglPerbaikanView() {
        return tglPerbaikanView;
    }

    public void setTglPerbaikanView(String tglPerbaikanView) {
        this.tglPerbaikanView = tglPerbaikanView;
    }

    public String getTglOutPerbaikan() {
        return tglOutPerbaikan;
    }

    public void setTglOutPerbaikan(String tglOutPerbaikan) {
        this.tglOutPerbaikan = tglOutPerbaikan;
    }

    public String getTglOutPerbaikanView() {
        return tglOutPerbaikanView;
    }

    public void setTglOutPerbaikanView(String tglOutPerbaikanView) {
        this.tglOutPerbaikanView = tglOutPerbaikanView;
    }

    public List<DetailLaporanPerbaikan> getDetPerbaikan() {
        return detPerbaikan;
    }

    public void setDetPerbaikan(List<DetailLaporanPerbaikan> detPerbaikan) {
        this.detPerbaikan = detPerbaikan;
    }

    public static class DetailLaporanPerbaikan {
        private String laporanPerbaikan;
        private String kategoriPerbaikan;
        private String lokasiPerbaikan;
        private String userPerbaikan;
        private String picPerbaikan;
        private String durasiPerbaikan;
        private String statusPerbaikan;

        public DetailLaporanPerbaikan(String laporanPerbaikan, String kategoriPerbaikan, String lokasiPerbaikan, String userPerbaikan, String picPerbaikan, String durasiPerbaikan, String statusPerbaikan) {
            this.laporanPerbaikan = laporanPerbaikan;
            this.kategoriPerbaikan = kategoriPerbaikan;
            this.lokasiPerbaikan = lokasiPerbaikan;
            this.userPerbaikan = userPerbaikan;
            this.picPerbaikan = picPerbaikan;
            this.durasiPerbaikan = durasiPerbaikan;
            this.statusPerbaikan = statusPerbaikan;
        }

        public String getLaporanPerbaikan() {
            return laporanPerbaikan;
        }

        public void setLaporanPerbaikan(String laporanPerbaikan) {
            this.laporanPerbaikan = laporanPerbaikan;
        }

        public String getKategoriPerbaikan() {
            return kategoriPerbaikan;
        }

        public void setKategoriPerbaikan(String kategoriPerbaikan) {
            this.kategoriPerbaikan = kategoriPerbaikan;
        }

        public String getLokasiPerbaikan() {
            return lokasiPerbaikan;
        }

        public void setLokasiPerbaikan(String lokasiPerbaikan) {
            this.lokasiPerbaikan = lokasiPerbaikan;
        }

        public String getUserPerbaikan() {
            return userPerbaikan;
        }

        public void setUserPerbaikan(String userPerbaikan) {
            this.userPerbaikan = userPerbaikan;
        }

        public String getPicPerbaikan() {
            return picPerbaikan;
        }

        public void setPicPerbaikan(String picPerbaikan) {
            this.picPerbaikan = picPerbaikan;
        }

        public String getDurasiPerbaikan() {
            return durasiPerbaikan;
        }

        public void setDurasiPerbaikan(String durasiPerbaikan) {
            this.durasiPerbaikan = durasiPerbaikan;
        }

        public String getStatusPerbaikan() {
            return statusPerbaikan;
        }

        public void setStatusPerbaikan(String statusPerbaikan) {
            this.statusPerbaikan = statusPerbaikan;
        }

        public boolean checkData()  {
            if (!getLaporanPerbaikan().isEmpty() && !getKategoriPerbaikan().isEmpty() && !getLokasiPerbaikan().isEmpty() && !getUserPerbaikan().isEmpty() && !getPicPerbaikan().isEmpty() && !getDurasiPerbaikan().isEmpty() && !getStatusPerbaikan().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
