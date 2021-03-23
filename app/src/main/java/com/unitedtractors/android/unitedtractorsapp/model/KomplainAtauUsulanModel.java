package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class KomplainAtauUsulanModel {
    private String idUser;
    private String idMapping;
    private List<DetailKomplain> detKomplain;

    public KomplainAtauUsulanModel(String idUser, String idMapping, List<DetailKomplain> detKomplain) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.detKomplain = detKomplain;
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

    public List<DetailKomplain> getDetKomplain() {
        return detKomplain;
    }

    public void setDetKomplain(List<DetailKomplain> detKomplain) {
        this.detKomplain = detKomplain;
    }

    public static class DetailKomplain {
        private String komplainUsul;

        public DetailKomplain(String komplainUsul) {
            this.komplainUsul = komplainUsul;
        }

        public String getKomplainUsul() {
            return komplainUsul;
        }

        public void setKomplainUsul(String komplainUsul) {
            this.komplainUsul = komplainUsul;
        }

        public boolean checkData()  {
            if (!getKomplainUsul().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
