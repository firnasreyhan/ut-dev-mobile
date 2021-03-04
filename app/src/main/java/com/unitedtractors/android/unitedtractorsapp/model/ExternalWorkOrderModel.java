package com.unitedtractors.android.unitedtractorsapp.model;

public class ExternalWorkOrderModel {
    private String itemPekerjaan;
    private String lokasiDiv;
    private String tanggalDimintaView;
    private String tanggalDimintaServer;
    private String troubleTicket;
    private String keterangan;

    public ExternalWorkOrderModel(String itemPekerjaan, String lokasiDiv, String tanggalDimintaView, String tanggalDimintaServer, String troubleTicket, String keterangan) {
        this.itemPekerjaan = itemPekerjaan;
        this.lokasiDiv = lokasiDiv;
        this.tanggalDimintaView = tanggalDimintaView;
        this.tanggalDimintaServer = tanggalDimintaServer;
        this.troubleTicket = troubleTicket;
        this.keterangan = keterangan;
    }

    public String getItemPekerjaan() {
        return itemPekerjaan;
    }

    public void setItemPekerjaan(String itemPekerjaan) {
        this.itemPekerjaan = itemPekerjaan;
    }

    public String getLokasiDiv() {
        return lokasiDiv;
    }

    public void setLokasiDiv(String lokasiDiv) {
        this.lokasiDiv = lokasiDiv;
    }

    public String getTanggalDimintaView() {
        return tanggalDimintaView;
    }

    public void setTanggalDimintaView(String tanggalDimintaView) {
        this.tanggalDimintaView = tanggalDimintaView;
    }

    public String getTanggalDimintaServer() {
        return tanggalDimintaServer;
    }

    public void setTanggalDimintaServer(String tanggalDimintaServer) {
        this.tanggalDimintaServer = tanggalDimintaServer;
    }

    public String getTroubleTicket() {
        return troubleTicket;
    }

    public void setTroubleTicket(String troubleTicket) {
        this.troubleTicket = troubleTicket;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public boolean checkData()  {
        if (!getItemPekerjaan().isEmpty() && !getLokasiDiv().isEmpty() && !getTanggalDimintaServer().isEmpty() && !getTroubleTicket().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
