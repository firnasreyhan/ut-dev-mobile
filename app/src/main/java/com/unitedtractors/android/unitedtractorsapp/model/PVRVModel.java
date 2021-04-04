package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class PVRVModel {
    private String idUser;
    private String idMapping;
    private String byrKepada;
    private String nrp;
    private String keperluan;
    private String noPo;
    private String noInvoice;
    private String type;
    private String noPPN;
    private String noPPH;
    private String totAmount;
    private List<DetailPVRV> detPVRV;

    public PVRVModel(String idUser, String idMapping, String byrKepada, String nrp, String keperluan, String noPo, String noInvoice, String type, String noPPN, String noPPH, String totAmount, List<DetailPVRV> detPVRV) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.byrKepada = byrKepada;
        this.nrp = nrp;
        this.keperluan = keperluan;
        this.noPo = noPo;
        this.noInvoice = noInvoice;
        this.type = type;
        this.noPPN = noPPN;
        this.noPPH = noPPH;
        this.totAmount = totAmount;
        this.detPVRV = detPVRV;
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

    public String getByrKepada() {
        return byrKepada;
    }

    public void setByrKepada(String byrKepada) {
        this.byrKepada = byrKepada;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getNoPo() {
        return noPo;
    }

    public void setNoPo(String noPo) {
        this.noPo = noPo;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNoPPN() {
        return noPPN;
    }

    public void setNoPPN(String noPPN) {
        this.noPPN = noPPN;
    }

    public String getNoPPH() {
        return noPPH;
    }

    public void setNoPPH(String noPPH) {
        this.noPPH = noPPH;
    }

    public String getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(String totAmount) {
        this.totAmount = totAmount;
    }

    public List<DetailPVRV> getDetPVRV() {
        return detPVRV;
    }

    public void setDetPVRV(List<DetailPVRV> detPVRV) {
        this.detPVRV = detPVRV;
    }

    public static class DetailPVRV{
        private String acc;
        private String desc;
        private String alloc;
        private String bArea;
        private String costCenter;
        private String amount;
        private String ket;

        public DetailPVRV(String acc, String desc, String alloc, String bArea, String costCenter, String amount, String ket) {
            this.acc = acc;
            this.desc = desc;
            this.alloc = alloc;
            this.bArea = bArea;
            this.costCenter = costCenter;
            this.amount = amount;
            this.ket = ket;
        }

        public String getAcc() {
            return acc;
        }

        public void setAcc(String acc) {
            this.acc = acc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAlloc() {
            return alloc;
        }

        public void setAlloc(String alloc) {
            this.alloc = alloc;
        }

        public String getbArea() {
            return bArea;
        }

        public void setbArea(String bArea) {
            this.bArea = bArea;
        }

        public String getCostCenter() {
            return costCenter;
        }

        public void setCostCenter(String costCenter) {
            this.costCenter = costCenter;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getKet() {
            return ket;
        }

        public void setKet(String ket) {
            this.ket = ket;
        }

        public boolean checkData()  {
            return !getAcc().isEmpty() && !getDesc().isEmpty() && !getAlloc().isEmpty() && !getbArea().isEmpty() && !getCostCenter().isEmpty() && !getAmount().isEmpty();
        }
    }
}
