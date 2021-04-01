package com.unitedtractors.android.unitedtractorsapp.model;

public class MonitoringLapanganModel {
    private String idUser;
    private String idMapping;
    private DetailMonitoringLapanganCatering cek1;
    private DetailMonitoringLapanganCatering cek2;
    private DetailMonitoringLapanganCatering cek3;
    private DetailMonitoringLapanganCatering cek4;
    private DetailMonitoringLapanganCatering cek5;
    private DetailMonitoringLapanganCatering cek6;
    private DetailMonitoringLapanganCatering cek7;

    public MonitoringLapanganModel(String idUser, String idMapping, DetailMonitoringLapanganCatering cek1, DetailMonitoringLapanganCatering cek2, DetailMonitoringLapanganCatering cek3, DetailMonitoringLapanganCatering cek4, DetailMonitoringLapanganCatering cek5, DetailMonitoringLapanganCatering cek6, DetailMonitoringLapanganCatering cek7) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.cek1 = cek1;
        this.cek2 = cek2;
        this.cek3 = cek3;
        this.cek4 = cek4;
        this.cek5 = cek5;
        this.cek6 = cek6;
        this.cek7 = cek7;
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

    public DetailMonitoringLapanganCatering getCek1() {
        return cek1;
    }

    public void setCek1(DetailMonitoringLapanganCatering cek1) {
        this.cek1 = cek1;
    }

    public DetailMonitoringLapanganCatering getCek2() {
        return cek2;
    }

    public void setCek2(DetailMonitoringLapanganCatering cek2) {
        this.cek2 = cek2;
    }

    public DetailMonitoringLapanganCatering getCek3() {
        return cek3;
    }

    public void setCek3(DetailMonitoringLapanganCatering cek3) {
        this.cek3 = cek3;
    }

    public DetailMonitoringLapanganCatering getCek4() {
        return cek4;
    }

    public void setCek4(DetailMonitoringLapanganCatering cek4) {
        this.cek4 = cek4;
    }

    public DetailMonitoringLapanganCatering getCek5() {
        return cek5;
    }

    public void setCek5(DetailMonitoringLapanganCatering cek5) {
        this.cek5 = cek5;
    }

    public DetailMonitoringLapanganCatering getCek6() {
        return cek6;
    }

    public void setCek6(DetailMonitoringLapanganCatering cek6) {
        this.cek6 = cek6;
    }

    public DetailMonitoringLapanganCatering getCek7() {
        return cek7;
    }

    public void setCek7(DetailMonitoringLapanganCatering cek7) {
        this.cek7 = cek7;
    }

    public static class DetailMonitoringLapanganCatering {
        private String tgl;
        private String order;
        private String bawa;
        private String kupon;

        public String getTgl() {
            return tgl;
        }

        public void setTgl(String tgl) {
            this.tgl = tgl;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getBawa() {
            return bawa;
        }

        public void setBawa(String bawa) {
            this.bawa = bawa;
        }

        public String getKupon() {
            return kupon;
        }

        public void setKupon(String kupon) {
            this.kupon = kupon;
        }
    }
}
