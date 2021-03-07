package com.unitedtractors.android.unitedtractorsapp.model;

import android.net.Uri;

import java.util.List;

public class SidakCateringModel {
    private String idUser;
    private String idMapping;
    private String perusahaan;
    private String pemilik;
    private String pengurus;
    private String alamat;
    private String telepon;
    private String fax;
    private String jumlahTenagaKerja;
    private String perusahaanDilayani;
    private String kandepnaker;
    private String kanwil;
    private boolean[] persyaratanTenagaKerja;
    private boolean[] kesehatanBahanDanPenyimpananMakanan;
    private boolean[] sanitasiLingkunganDanFasilitasPengolahMakanan;
    private String catatan;

    public SidakCateringModel(String idUser, String idMapping, String perusahaan, String pemilik, String pengurus, String alamat, String telepon, String fax, String jumlahTenagaKerja, String perusahaanDilayani, String kandepnaker, String kanwil, boolean[] persyaratanTenagaKerja, boolean[] kesehatanBahanDanPenyimpananMakanan, boolean[] sanitasiLingkunganDanFasilitasPengolahMakanan, String catatan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.perusahaan = perusahaan;
        this.pemilik = pemilik;
        this.pengurus = pengurus;
        this.alamat = alamat;
        this.telepon = telepon;
        this.fax = fax;
        this.jumlahTenagaKerja = jumlahTenagaKerja;
        this.perusahaanDilayani = perusahaanDilayani;
        this.kandepnaker = kandepnaker;
        this.kanwil = kanwil;
        this.persyaratanTenagaKerja = persyaratanTenagaKerja;
        this.kesehatanBahanDanPenyimpananMakanan = kesehatanBahanDanPenyimpananMakanan;
        this.sanitasiLingkunganDanFasilitasPengolahMakanan = sanitasiLingkunganDanFasilitasPengolahMakanan;
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

    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getPengurus() {
        return pengurus;
    }

    public void setPengurus(String pengurus) {
        this.pengurus = pengurus;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getJumlahTenagaKerja() {
        return jumlahTenagaKerja;
    }

    public void setJumlahTenagaKerja(String jumlahTenagaKerja) {
        this.jumlahTenagaKerja = jumlahTenagaKerja;
    }

    public String getPerusahaanDilayani() {
        return perusahaanDilayani;
    }

    public void setPerusahaanDilayani(String perusahaanDilayani) {
        this.perusahaanDilayani = perusahaanDilayani;
    }

    public String getKandepnaker() {
        return kandepnaker;
    }

    public void setKandepnaker(String kandepnaker) {
        this.kandepnaker = kandepnaker;
    }

    public String getKanwil() {
        return kanwil;
    }

    public void setKanwil(String kanwil) {
        this.kanwil = kanwil;
    }

    public boolean[] getPersyaratanTenagaKerja() {
        return persyaratanTenagaKerja;
    }

    public void setPersyaratanTenagaKerja(boolean[] persyaratanTenagaKerja) {
        this.persyaratanTenagaKerja = persyaratanTenagaKerja;
    }

    public boolean[] getKesehatanBahanDanPenyimpananMakanan() {
        return kesehatanBahanDanPenyimpananMakanan;
    }

    public void setKesehatanBahanDanPenyimpananMakanan(boolean[] kesehatanBahanDanPenyimpananMakanan) {
        this.kesehatanBahanDanPenyimpananMakanan = kesehatanBahanDanPenyimpananMakanan;
    }

    public boolean[] getSanitasiLingkunganDanFasilitasPengolahMakanan() {
        return sanitasiLingkunganDanFasilitasPengolahMakanan;
    }

    public void setSanitasiLingkunganDanFasilitasPengolahMakanan(boolean[] sanitasiLingkunganDanFasilitasPengolahMakanan) {
        this.sanitasiLingkunganDanFasilitasPengolahMakanan = sanitasiLingkunganDanFasilitasPengolahMakanan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
