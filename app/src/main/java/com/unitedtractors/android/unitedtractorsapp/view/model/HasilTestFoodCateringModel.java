package com.unitedtractors.android.unitedtractorsapp.view.model;

public class HasilTestFoodCateringModel {

    String namaCatering;
    int rasa, aroma, kebersihan, kualitas;

    public HasilTestFoodCateringModel(String namaCatering, int rasa, int aroma, int kebersihan, int kualitas) {
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
        if (!getNamaCatering().isEmpty() && getRasa() != 0 && getAroma() != 0 && getKebersihan() != 0 && getKualitas() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
