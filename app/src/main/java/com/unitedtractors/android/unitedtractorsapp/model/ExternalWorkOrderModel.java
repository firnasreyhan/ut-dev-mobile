package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class ExternalWorkOrderModel {
    private String idUser;
    private String idMapping;
    private String intrKepada;
    private String intrDari;
    private String deptDiv;
    private String pekerjaan;
    private String noReg;
    private String reqDate;
    private String pages;
    private String cc;
    private List<DetailExternalWorkOrder> detEwo;

    public ExternalWorkOrderModel(String idUser, String idMapping, String intrKepada, String intrDari, String deptDiv, String pekerjaan, String noReg, String reqDate, String pages, String cc, List<DetailExternalWorkOrder> detEwo) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.intrKepada = intrKepada;
        this.intrDari = intrDari;
        this.deptDiv = deptDiv;
        this.pekerjaan = pekerjaan;
        this.noReg = noReg;
        this.reqDate = reqDate;
        this.pages = pages;
        this.cc = cc;
        this.detEwo = detEwo;
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

    public String getIntrKepada() {
        return intrKepada;
    }

    public void setIntrKepada(String intrKepada) {
        this.intrKepada = intrKepada;
    }

    public String getIntrDari() {
        return intrDari;
    }

    public void setIntrDari(String intrDari) {
        this.intrDari = intrDari;
    }

    public String getDeptDiv() {
        return deptDiv;
    }

    public void setDeptDiv(String deptDiv) {
        this.deptDiv = deptDiv;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getNoReg() {
        return noReg;
    }

    public void setNoReg(String noReg) {
        this.noReg = noReg;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public List<DetailExternalWorkOrder> getDetEwo() {
        return detEwo;
    }

    public void setDetEwo(List<DetailExternalWorkOrder> detEwo) {
        this.detEwo = detEwo;
    }

    public static class DetailExternalWorkOrder {
        private String itemPekerjaan;
        private String lokasiDiv;
        private String tanggalDimintaView;
        private String tanggalDimintaServer;
        private String troubleTicket;
        private String keterangan;

        public DetailExternalWorkOrder(String itemPekerjaan, String lokasiDiv, String tanggalDimintaView, String tanggalDimintaServer, String troubleTicket, String keterangan) {
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
}
