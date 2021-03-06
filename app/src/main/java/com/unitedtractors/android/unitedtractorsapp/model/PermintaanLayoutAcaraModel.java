package com.unitedtractors.android.unitedtractorsapp.model;

import android.net.Uri;

import java.util.List;

public class PermintaanLayoutAcaraModel {
    private String namaAcara;
    private String lokasiAcara;
    private String jam;
    private String tanggalServer;
    private String tanggalView;
    private String jumlahPeserta;
    private String bebanBiaya;
    private List<ImagePermintaanAcaraModel> image;
    private String keterangan;

    public PermintaanLayoutAcaraModel(String namaAcara, String lokasiAcara, String jam, String tanggalServer, String tanggalView, String jumlahPeserta, String bebanBiaya, List<ImagePermintaanAcaraModel> image, String keterangan) {
        this.namaAcara = namaAcara;
        this.lokasiAcara = lokasiAcara;
        this.jam = jam;
        this.tanggalServer = tanggalServer;
        this.tanggalView = tanggalView;
        this.jumlahPeserta = jumlahPeserta;
        this.bebanBiaya = bebanBiaya;
        this.image = image;
        this.keterangan = keterangan;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public void setNamaAcara(String namaAcara) {
        this.namaAcara = namaAcara;
    }

    public String getLokasiAcara() {
        return lokasiAcara;
    }

    public void setLokasiAcara(String lokasiAcara) {
        this.lokasiAcara = lokasiAcara;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggalServer() {
        return tanggalServer;
    }

    public void setTanggalServer(String tanggalServer) {
        this.tanggalServer = tanggalServer;
    }

    public String getTanggalView() {
        return tanggalView;
    }

    public void setTanggalView(String tanggalView) {
        this.tanggalView = tanggalView;
    }

    public String getJumlahPeserta() {
        return jumlahPeserta;
    }

    public void setJumlahPeserta(String jumlahPeserta) {
        this.jumlahPeserta = jumlahPeserta;
    }

    public String getBebanBiaya() {
        return bebanBiaya;
    }

    public void setBebanBiaya(String bebanBiaya) {
        this.bebanBiaya = bebanBiaya;
    }

    public List<ImagePermintaanAcaraModel> getImage() {
        return image;
    }

    public void setImage(List<ImagePermintaanAcaraModel> image) {
        this.image = image;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public class ImagePermintaanAcaraModel {
        private Uri image;

        public ImagePermintaanAcaraModel(Uri image) {
            this.image = image;
        }

        public Uri getImage() {
            return image;
        }

        public void setImage(Uri image) {
            this.image = image;
        }
    }
}
