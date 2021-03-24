package com.unitedtractors.android.unitedtractorsapp.model;

import java.io.Serializable;
import java.util.List;

public class DeklarasiModel {
    private String idUser;
    private String idMapping;
    private String tgl;
    private String dd;
    private String nopol;
    private List<DetKeperluan> detKeperluan;

    public DeklarasiModel(String idUser, String idMapping, String tgl, String dd, String nopol, List<DetKeperluan> detKeperluan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tgl = tgl;
        this.dd = dd;
        this.nopol = nopol;
        this.detKeperluan = detKeperluan;
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

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public List<DetKeperluan> getDetKeperluan() {
        return detKeperluan;
    }

    public void setDetKeperluan(List<DetKeperluan> detKeperluan) {
        this.detKeperluan = detKeperluan;
    }

    public static class DetKeperluan implements Serializable {
        private int bbm;
        private int tol;
        private int grab;
        private int lain;

        public DetKeperluan(int bbm, int tol, int grab, int lain) {
            this.bbm = bbm;
            this.tol = tol;
            this.grab = grab;
            this.lain = lain;
        }

        public int getBbm() {
            return bbm;
        }

        public void setBbm(int bbm) {
            this.bbm = bbm;
        }

        public int getTol() {
            return tol;
        }

        public void setTol(int tol) {
            this.tol = tol;
        }

        public int getGrab() {
            return grab;
        }

        public void setGrab(int grab) {
            this.grab = grab;
        }

        public int getLain() {
            return lain;
        }

        public void setLain(int lain) {
            this.lain = lain;
        }
    }
}
