package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class ExtensionDanAksesModel {
    private String idUser;
    private String idMapping;
    private List<DetExtension> detExtension;

    public ExtensionDanAksesModel(String idUser, String idMapping, List<DetExtension> detExtension) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.detExtension = detExtension;
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

    public List<DetExtension> getDetExtension() {
        return detExtension;
    }

    public void setDetExtension(List<DetExtension> detExtension) {
        this.detExtension = detExtension;
    }

    public static class DetExtension{
        private String nama;
        private String nrp;
        private String noExtension;
        private String div;
        private String jenPermintaan;
        private String fasilitas;
        private String ct;

        public DetExtension(String nama, String nrp, String noExtension, String div, String jenPermintaan, String fasilitas, String ct) {
            this.nama = nama;
            this.nrp = nrp;
            this.noExtension = noExtension;
            this.div = div;
            this.jenPermintaan = jenPermintaan;
            this.fasilitas = fasilitas;
            this.ct = ct;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNrp() {
            return nrp;
        }

        public void setNrp(String nrp) {
            this.nrp = nrp;
        }

        public String getNoExtension() {
            return noExtension;
        }

        public void setNoExtension(String noExtension) {
            this.noExtension = noExtension;
        }

        public String getDiv() {
            return div;
        }

        public void setDiv(String div) {
            this.div = div;
        }

        public String getJenPermintaan() {
            return jenPermintaan;
        }

        public void setJenPermintaan(String jenPermintaan) {
            this.jenPermintaan = jenPermintaan;
        }

        public String getFasilitas() {
            return fasilitas;
        }

        public void setFasilitas(String fasilitas) {
            this.fasilitas = fasilitas;
        }

        public String getCt() {
            return ct;
        }

        public void setCt(String ct) {
            this.ct = ct;
        }

        public boolean checkData()  {
            if (!getNoExtension().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
