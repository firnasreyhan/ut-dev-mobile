package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class ChecklistForHydrantModel {
    private String idUser;
    private String idMapping;
    private String tanggal;
    private String lokasi;
    private String catatan;
    private List<Pertanyaan3Model> systemPemipaan;
    private List<Pertanyaan3Model> jockeyPump;
    private List<Pertanyaan3Model> electricPump;
    private List<Pertanyaan3Model> dieselHydrantPump;
    private List<Pertanyaan3Model> panelHydrant;

    public ChecklistForHydrantModel(String idUser, String idMapping, String tanggal, String lokasi, String catatan, List<Pertanyaan3Model> systemPemipaan, List<Pertanyaan3Model> jockeyPump, List<Pertanyaan3Model> electricPump, List<Pertanyaan3Model> dieselHydrantPump, List<Pertanyaan3Model> panelHydrant) {
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

    public List<Pertanyaan3Model> getSystemPemipaan() {
        return systemPemipaan;
    }

    public void setSystemPemipaan(List<Pertanyaan3Model> systemPemipaan) {
        this.systemPemipaan = systemPemipaan;
    }

    public List<Pertanyaan3Model> getJockeyPump() {
        return jockeyPump;
    }

    public void setJockeyPump(List<Pertanyaan3Model> jockeyPump) {
        this.jockeyPump = jockeyPump;
    }

    public List<Pertanyaan3Model> getElectricPump() {
        return electricPump;
    }

    public void setElectricPump(List<Pertanyaan3Model> electricPump) {
        this.electricPump = electricPump;
    }

    public List<Pertanyaan3Model> getDieselHydrantPump() {
        return dieselHydrantPump;
    }

    public void setDieselHydrantPump(List<Pertanyaan3Model> dieselHydrantPump) {
        this.dieselHydrantPump = dieselHydrantPump;
    }

    public List<Pertanyaan3Model> getPanelHydrant() {
        return panelHydrant;
    }

    public void setPanelHydrant(List<Pertanyaan3Model> panelHydrant) {
        this.panelHydrant = panelHydrant;
    }
}
