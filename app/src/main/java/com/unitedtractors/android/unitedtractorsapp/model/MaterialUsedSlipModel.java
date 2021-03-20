package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class MaterialUsedSlipModel {
    private String idUser;
    private String idMapping;
    private String tanggal;
    private List<DetailMaterialUsedSlipModel> detailMaterialUsedSlipModelList;

    public MaterialUsedSlipModel(String idUser, String idMapping, String tanggal, List<DetailMaterialUsedSlipModel> detailMaterialUsedSlipModelList) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tanggal = tanggal;
        this.detailMaterialUsedSlipModelList = detailMaterialUsedSlipModelList;
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

    public List<DetailMaterialUsedSlipModel> getDetailMaterialUsedSlipModelList() {
        return detailMaterialUsedSlipModelList;
    }

    public void setDetailMaterialUsedSlipModelList(List<DetailMaterialUsedSlipModel> detailMaterialUsedSlipModelList) {
        this.detailMaterialUsedSlipModelList = detailMaterialUsedSlipModelList;
    }

    public static class DetailMaterialUsedSlipModel {
        private String namaBarang;
        private int jumlahBarang;
        private String dipergunakan;
        private String keterangan;

        public DetailMaterialUsedSlipModel(String namaBarang, int jumlahBarang, String dipergunakan, String keterangan) {
            this.namaBarang = namaBarang;
            this.jumlahBarang = jumlahBarang;
            this.dipergunakan = dipergunakan;
            this.keterangan = keterangan;
        }

        public String getNamaBarang() {
            return namaBarang;
        }

        public void setNamaBarang(String namaBarang) {
            this.namaBarang = namaBarang;
        }

        public int getJumlahBarang() {
            return jumlahBarang;
        }

        public void setJumlahBarang(int jumlahBarang) {
            this.jumlahBarang = jumlahBarang;
        }

        public String getDipergunakan() {
            return dipergunakan;
        }

        public void setDipergunakan(String dipergunakan) {
            this.dipergunakan = dipergunakan;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public boolean checkData()  {
            if (!getNamaBarang().isEmpty() && !getDipergunakan().isEmpty() && !getKeterangan().isEmpty() && getJumlahBarang() != 0) {
                return true;
            } else {
                return false;
            }
        }
    }

}

