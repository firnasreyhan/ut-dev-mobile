package com.unitedtractors.android.unitedtractorsapp.view.model;

public class PermintaanAssetModel {
    private String namaBarang;
    private String alasanFungsi;
    private String manfaatBagiPerusahaan;
    private String quantity;
    private String tanggal;

    public PermintaanAssetModel(String namaBarang, String alasanFungsi, String manfaatBagiPerusahaan, String quantity, String tanggal) {
        this.namaBarang = namaBarang;
        this.alasanFungsi = alasanFungsi;
        this.manfaatBagiPerusahaan = manfaatBagiPerusahaan;
        this.quantity = quantity;
        this.tanggal = tanggal;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getAlasanFungsi() {
        return alasanFungsi;
    }

    public void setAlasanFungsi(String alasanFungsi) {
        this.alasanFungsi = alasanFungsi;
    }

    public String getManfaatBagiPerusahaan() {
        return manfaatBagiPerusahaan;
    }

    public void setManfaatBagiPerusahaan(String manfaatBagiPerusahaan) {
        this.manfaatBagiPerusahaan = manfaatBagiPerusahaan;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
