package com.unitedtractors.android.unitedtractorsapp.model;

public class MonitoringLapanganCatering {
    private String idUser;
    private String idMapping;
    private DetailMonitoringLapanganCatering cek1;
    private DetailMonitoringLapanganCatering cek2;
    private DetailMonitoringLapanganCatering cek3;
    private DetailMonitoringLapanganCatering cek4;
    private DetailMonitoringLapanganCatering cek5;
    private DetailMonitoringLapanganCatering cek6;
    private DetailMonitoringLapanganCatering cek7;

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
