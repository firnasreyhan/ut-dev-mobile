package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class OrderCateringModel {
    private String idUser;
    private String idMapping;
    private List<DetailOrder> detOrder;

    public OrderCateringModel(String idUser, String idMapping, List<DetailOrder> detOrder) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.detOrder = detOrder;
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

    public List<DetailOrder> getDetOrder() {
        return detOrder;
    }

    public void setDetOrder(List<DetailOrder> detOrder) {
        this.detOrder = detOrder;
    }

    public static class DetailOrder {
        private String tgl;
        private String tglView;
        private String divisi;
        private int jml;

        public DetailOrder(String tgl, String divisi, int jml) {
            this.tgl = tgl;
            this.divisi = divisi;
            this.jml = jml;
        }

        public String getTgl() {
            return tgl;
        }

        public void setTgl(String tgl) {
            this.tgl = tgl;
        }

        public String getTglView() {
            return tglView;
        }

        public void setTglView(String tglView) {
            this.tglView = tglView;
        }

        public String getDivisi() {
            return divisi;
        }

        public void setDivisi(String divisi) {
            this.divisi = divisi;
        }

        public int getJml() {
            return jml;
        }

        public void setJml(int jml) {
            this.jml = jml;
        }

        public boolean checkData()  {
            if (!getTgl().isEmpty() && !getDivisi().isEmpty()  && getJml() != 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
