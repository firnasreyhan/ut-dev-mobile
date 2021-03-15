package com.unitedtractors.android.unitedtractorsapp.model;

import java.io.Serializable;
import java.util.List;

public class ChecklistForHydrantModel {
    private String idUser;
    private String idMapping;
    private String tanggal;
    private String lokasi;
    private String catatan;
    private List<DetailChecklistHydrant> systemPemipaan;
    private List<DetailChecklistHydrant> jockeyPump;
    private List<DetailChecklistHydrant> electricPump;
    private List<DetailChecklistHydrant> dieselHydrantPump;
    private List<DetailChecklistHydrant> panelHydrant;

    public ChecklistForHydrantModel(String idUser, String idMapping, String tanggal, String lokasi, String catatan, List<DetailChecklistHydrant> systemPemipaan, List<DetailChecklistHydrant> jockeyPump, List<DetailChecklistHydrant> electricPump, List<DetailChecklistHydrant> dieselHydrantPump, List<DetailChecklistHydrant> panelHydrant) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.catatan = catatan;
        this.systemPemipaan = systemPemipaan;
        this.jockeyPump = jockeyPump;
        this.electricPump = electricPump;
        this.dieselHydrantPump = dieselHydrantPump;
        this.panelHydrant = panelHydrant;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public List<DetailChecklistHydrant> getSystemPemipaan() {
        return systemPemipaan;
    }

    public void setSystemPemipaan(List<DetailChecklistHydrant> systemPemipaan) {
        this.systemPemipaan = systemPemipaan;
    }

    public List<DetailChecklistHydrant> getJockeyPump() {
        return jockeyPump;
    }

    public void setJockeyPump(List<DetailChecklistHydrant> jockeyPump) {
        this.jockeyPump = jockeyPump;
    }

    public List<DetailChecklistHydrant> getElectricPump() {
        return electricPump;
    }

    public void setElectricPump(List<DetailChecklistHydrant> electricPump) {
        this.electricPump = electricPump;
    }

    public List<DetailChecklistHydrant> getDieselHydrantPump() {
        return dieselHydrantPump;
    }

    public void setDieselHydrantPump(List<DetailChecklistHydrant> dieselHydrantPump) {
        this.dieselHydrantPump = dieselHydrantPump;
    }

    public List<DetailChecklistHydrant> getPanelHydrant() {
        return panelHydrant;
    }

    public void setPanelHydrant(List<DetailChecklistHydrant> panelHydrant) {
        this.panelHydrant = panelHydrant;
    }

    public static class DetailChecklistHydrant implements Serializable {
        private String pertanyaan;
        private int status;

        public DetailChecklistHydrant(String pertanyaan, int status) {
            this.pertanyaan = pertanyaan;
            this.status = status;
        }

        public String getPertanyaan() {
            return pertanyaan;
        }

        public void setPertanyaan(String pertanyaan) {
            this.pertanyaan = pertanyaan;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
