package com.unitedtractors.android.unitedtractorsapp.model;

public class SyaratLegalitasCateringModel {
    private String idUser;
    private String idMapping;
    private String nama;
    private String alamat;
    private boolean[] syarat;
    private boolean[] survey;

    public SyaratLegalitasCateringModel(String idUser, String idMapping, String nama, String alamat, boolean[] syarat, boolean[] survey) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.nama = nama;
        this.alamat = alamat;
        this.syarat = syarat;
        this.survey = survey;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public boolean[] getSyarat() {
        return syarat;
    }

    public void setSyarat(boolean[] syarat) {
        this.syarat = syarat;
    }

    public boolean[] getSurvey() {
        return survey;
    }

    public void setSurvey(boolean[] survey) {
        this.survey = survey;
    }
}
