package com.unitedtractors.android.unitedtractorsapp.model;

public class LayoutAcaraModel {
    private String idUser;
    private String idMapping;
    private String namaAcara;
    private String lokasi;
    private String jam;
    private String tgl;
    private String peserta;
    private String biaya;
    private String keterangan;

    public LayoutAcaraModel(String idUser, String idMapping, String namaAcara, String lokasi, String jam, String tgl, String peserta, String biaya, String keterangan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.namaAcara = namaAcara;
        this.lokasi = lokasi;
        this.jam = jam;
        this.tgl = tgl;
        this.peserta = peserta;
        this.biaya = biaya;
        this.keterangan = keterangan;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdMapping() {
        return idMapping;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getJam() {
        return jam;
    }

    public String getTgl() {
        return tgl;
    }

    public String getPeserta() {
        return peserta;
    }

    public String getBiaya() {
        return biaya;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
