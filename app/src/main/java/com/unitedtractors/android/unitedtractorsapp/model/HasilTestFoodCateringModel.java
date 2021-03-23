package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class HasilTestFoodCateringModel {
    private String idUser;
    private String idMapping;
    private List<DetailTestFood> detTestfood;

    public HasilTestFoodCateringModel(String idUser, String idMapping, List<DetailTestFood> detTestfood) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.detTestfood = detTestfood;
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

    public List<DetailTestFood> getDetTestfood() {
        return detTestfood;
    }

    public void setDetTestfood(List<DetailTestFood> detTestfood) {
        this.detTestfood = detTestfood;
    }

    public static class DetailTestFood {
        private String namaCatering;
        private int rasa, aroma, kebersihan, kualitas;

        public DetailTestFood(String namaCatering, int rasa, int aroma, int kebersihan, int kualitas) {
            this.namaCatering = namaCatering;
            this.rasa = rasa;
            this.aroma = aroma;
            this.kebersihan = kebersihan;
            this.kualitas = kualitas;
        }

        public String getNamaCatering() {
            return namaCatering;
        }

        public void setNamaCatering(String namaCatering) {
            this.namaCatering = namaCatering;
        }

        public int getRasa() {
            return rasa;
        }

        public void setRasa(int rasa) {
            this.rasa = rasa;
        }

        public int getAroma() {
            return aroma;
        }

        public void setAroma(int aroma) {
            this.aroma = aroma;
        }

        public int getKebersihan() {
            return kebersihan;
        }

        public void setKebersihan(int kebersihan) {
            this.kebersihan = kebersihan;
        }

        public int getKualitas() {
            return kualitas;
        }

        public void setKualitas(int kualitas) {
            this.kualitas = kualitas;
        }

        public boolean checkData()  {
            if (!getNamaCatering().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
