package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class ChecklistPompaPondModel {
    private String idUser;
    private String idMapping;
    private String tanggal;
    private String pondA;
    private String pondB;
    private String pondC;
    private String pondD;
    private List<String> checking;
    private List<String> running;
    private String catatan;

    public ChecklistPompaPondModel(String idUser, String idMapping, String tanggal, String pondA, String pondB, String pondC, String pondD, List<String> checking, List<String> running, String catatan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tanggal = tanggal;
        this.pondA = pondA;
        this.pondB = pondB;
        this.pondC = pondC;
        this.pondD = pondD;
        this.checking = checking;
        this.running = running;
        this.catatan = catatan;
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

    public String getPondA() {
        return pondA;
    }

    public void setPondA(String pondA) {
        this.pondA = pondA;
    }

    public String getPondB() {
        return pondB;
    }

    public void setPondB(String pondB) {
        this.pondB = pondB;
    }

    public String getPondC() {
        return pondC;
    }

    public void setPondC(String pondC) {
        this.pondC = pondC;
    }

    public String getPondD() {
        return pondD;
    }

    public void setPondD(String pondD) {
        this.pondD = pondD;
    }

    public List<String> getChecking() {
        return checking;
    }

    public void setChecking(List<String> checking) {
        this.checking = checking;
    }

    public List<String> getRunning() {
        return running;
    }

    public void setRunning(List<String> running) {
        this.running = running;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
