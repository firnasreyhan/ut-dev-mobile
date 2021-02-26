package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class PermintaanMobilDinasModel {

    private String namaPeminjam;
    private String namaPengemudi;
    private String tglPeminjaman;
    private String tglPengembalian;
    private String divisi;
    private String noPolisi;
    private String jamBerangkat;
    private String jamPulang;
    private String kmAwal;
    private String kmAkhir;
    private String catatan;
    private List<TujuanMobilDinasModel> tujuan;

    public PermintaanMobilDinasModel(String namaPeminjam, String namaPengemudi, String tglPeminjaman, String tglPengembalian, String divisi, String noPolisi, String jamBerangkat, String jamPulang, String kmAwal, String kmAkhir, String catatan, List<TujuanMobilDinasModel> tujuan) {
        this.namaPeminjam = namaPeminjam;
        this.namaPengemudi = namaPengemudi;
        this.tglPeminjaman = tglPeminjaman;
        this.tglPengembalian = tglPengembalian;
        this.divisi = divisi;
        this.noPolisi = noPolisi;
        this.jamBerangkat = jamBerangkat;
        this.jamPulang = jamPulang;
        this.kmAwal = kmAwal;
        this.kmAkhir = kmAkhir;
        this.catatan = catatan;
        this.tujuan = tujuan;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public String getNamaPengemudi() {
        return namaPengemudi;
    }

    public void setNamaPengemudi(String namaPengemudi) {
        this.namaPengemudi = namaPengemudi;
    }

    public String getTglPeminjaman() {
        return tglPeminjaman;
    }

    public void setTglPeminjaman(String tglPeminjaman) {
        this.tglPeminjaman = tglPeminjaman;
    }

    public String getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(String tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getJamBerangkat() {
        return jamBerangkat;
    }

    public void setJamBerangkat(String jamBerangkat) {
        this.jamBerangkat = jamBerangkat;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
    }

    public String getKmAwal() {
        return kmAwal;
    }

    public void setKmAwal(String kmAwal) {
        this.kmAwal = kmAwal;
    }

    public String getKmAkhir() {
        return kmAkhir;
    }

    public void setKmAkhir(String kmAkhir) {
        this.kmAkhir = kmAkhir;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public List<TujuanMobilDinasModel> getTujuan() {
        return tujuan;
    }

    public void setTujuan(List<TujuanMobilDinasModel> tujuan) {
        this.tujuan = tujuan;
    }

    public static class TujuanMobilDinasModel {

        private String tujuan;
        private String keperluan;

        public TujuanMobilDinasModel(String tujuan, String keperluan) {
            this.tujuan = tujuan;
            this.keperluan = keperluan;
        }

        public String getTujuan() {
            return tujuan;
        }

        public void setTujuan(String tujuan) {
            this.tujuan = tujuan;
        }

        public String getKeperluan() {
            return keperluan;
        }

        public void setKeperluan(String keperluan) {
            this.keperluan = keperluan;
        }

        public boolean checkData()  {
            if (!getTujuan().isEmpty() && !getKeperluan().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
