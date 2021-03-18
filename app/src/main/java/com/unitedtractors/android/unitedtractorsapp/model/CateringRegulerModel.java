package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class CateringRegulerModel {
    private String idUser;
    private String idMapping;
    private List<DetailCatering> detailCaterings;

    public CateringRegulerModel(String idUser, String idMapping, List<DetailCatering> detailCaterings) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.detailCaterings = detailCaterings;
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

    public List<DetailCatering> getDetailCaterings() {
        return detailCaterings;
    }

    public void setDetailCaterings(List<DetailCatering> detailCaterings) {
        this.detailCaterings = detailCaterings;
    }

    public static class DetailCatering {
        private String tanggal;
        private String tanggalView;
        private String jumlahOrang;

        public DetailCatering(String tanggal, String jumlahOrang) {
            this.tanggal = tanggal;
            this.jumlahOrang = jumlahOrang;
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

        public String getJumlahOrang() {
            return jumlahOrang;
        }

        public void setJumlahOrang(String jumlahOrang) {
            this.jumlahOrang = jumlahOrang;
        }

        public boolean checkData()  {
            return !getTanggal().isEmpty() && !getJumlahOrang().isEmpty();
        }
    }
}
