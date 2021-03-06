package com.unitedtractors.android.unitedtractorsapp.model;

public class MonitoringLapanganModel {
    private String tanggalView;
    private String tangalServer;
    private String jumlahPesanan;
    private String actualBawa;
    private String actualKupon;

    public MonitoringLapanganModel(String tanggalView, String tangalServer, String jumlahPesanan, String actualBawa, String actualKupon) {
        this.tanggalView = tanggalView;
        this.tangalServer = tangalServer;
        this.jumlahPesanan = jumlahPesanan;
        this.actualBawa = actualBawa;
        this.actualKupon = actualKupon;
    }

    public String getTanggalView() {
        return tanggalView;
    }

    public void setTanggalView(String tanggalView) {
        this.tanggalView = tanggalView;
    }

    public String getTangalServer() {
        return tangalServer;
    }

    public void setTangalServer(String tangalServer) {
        this.tangalServer = tangalServer;
    }

    public String getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void setJumlahPesanan(String jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }

    public String getActualBawa() {
        return actualBawa;
    }

    public void setActualBawa(String actualBawa) {
        this.actualBawa = actualBawa;
    }

    public String getActualKupon() {
        return actualKupon;
    }

    public void setActualKupon(String actualKupon) {
        this.actualKupon = actualKupon;
    }
}
