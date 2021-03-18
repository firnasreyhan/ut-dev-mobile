package com.unitedtractors.android.unitedtractorsapp.model;

public class SurveyKepuasanPelangganModel {
    private String idUser;
    private String idMapping;
    private String tglSurvey;
    private String nama;
    private String departemen;
    private String divisi;
    private int kualitas;
    private int profesional;
    private int cepat;
    private int aspekLk3;
    private int sikap;
    private int kelancaran;

    public SurveyKepuasanPelangganModel(String idUser, String idMapping, String tglSurvey, String nama, String departemen, String divisi, int kualitas, int profesional, int cepat, int aspekLk3, int sikap, int kelancaran) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tglSurvey = tglSurvey;
        this.nama = nama;
        this.departemen = departemen;
        this.divisi = divisi;
        this.kualitas = kualitas;
        this.profesional = profesional;
        this.cepat = cepat;
        this.aspekLk3 = aspekLk3;
        this.sikap = sikap;
        this.kelancaran = kelancaran;
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

    public String getTglSurvey() {
        return tglSurvey;
    }

    public void setTglSurvey(String tglSurvey) {
        this.tglSurvey = tglSurvey;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public int getKualitas() {
        return kualitas;
    }

    public void setKualitas(int kualitas) {
        this.kualitas = kualitas;
    }

    public int getProfesional() {
        return profesional;
    }

    public void setProfesional(int profesional) {
        this.profesional = profesional;
    }

    public int getCepat() {
        return cepat;
    }

    public void setCepat(int cepat) {
        this.cepat = cepat;
    }

    public int getAspekLk3() {
        return aspekLk3;
    }

    public void setAspekLk3(int aspekLk3) {
        this.aspekLk3 = aspekLk3;
    }

    public int getSikap() {
        return sikap;
    }

    public void setSikap(int sikap) {
        this.sikap = sikap;
    }

    public int getKelancaran() {
        return kelancaran;
    }

    public void setKelancaran(int kelancaran) {
        this.kelancaran = kelancaran;
    }
}
