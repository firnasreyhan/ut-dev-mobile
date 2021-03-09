package com.unitedtractors.android.unitedtractorsapp.model;

public class PerbaikanModel {
    private String idUser;
    private String idMapping;
    private String tgl;
    private String waktu;
    private String namaDari;
    private String divisi;
    private String extension;
    private String namaDiterima;
    private String troubTicket;
    private String jenisPerbaikan;
    private String alasan;
    private String dikerjakanOleh;
    private String estWaktu;
    private String estBiaya;

    public PerbaikanModel(String idUser, String idMapping, String tgl, String waktu, String namaDari, String divisi, String extension, String namaDiterima, String troubTicket, String jenisPerbaikan, String alasan, String dikerjakanOleh, String estWaktu, String estBiaya) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tgl = tgl;
        this.waktu = waktu;
        this.namaDari = namaDari;
        this.divisi = divisi;
        this.extension = extension;
        this.namaDiterima = namaDiterima;
        this.troubTicket = troubTicket;
        this.jenisPerbaikan = jenisPerbaikan;
        this.alasan = alasan;
        this.dikerjakanOleh = dikerjakanOleh;
        this.estWaktu = estWaktu;
        this.estBiaya = estBiaya;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdMapping() {
        return idMapping;
    }

    public String getTgl() {
        return tgl;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getNamaDari() {
        return namaDari;
    }

    public String getDivisi() {
        return divisi;
    }

    public String getExtension() {
        return extension;
    }

    public String getNamaDiterima() {
        return namaDiterima;
    }

    public String getTroubTicket() {
        return troubTicket;
    }

    public String getJenisPerbaikan() {
        return jenisPerbaikan;
    }

    public String getAlasan() {
        return alasan;
    }

    public String getDikerjakanOleh() {
        return dikerjakanOleh;
    }

    public String getEstWaktu() {
        return estWaktu;
    }

    public String getEstBiaya() {
        return estBiaya;
    }
}
