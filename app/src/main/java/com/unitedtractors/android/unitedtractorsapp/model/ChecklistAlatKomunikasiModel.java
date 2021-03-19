package com.unitedtractors.android.unitedtractorsapp.model;

import java.io.Serializable;
import java.util.List;

public class ChecklistAlatKomunikasiModel {
    private String idUser;
    private String idMapping;
    private String tanggal;
    private String lokasi;
    private List<DetailChecklistAlatKomunikasi> pabx;
    private List<DetailChecklistAlatKomunikasi> repeater;
    private List<DetailChecklistAlatKomunikasi> radio;
    private String keterangan;
    private String probIdentification;
    private String rootCause;
    private String correctAct;
    private String preventAct;
    private String deadLine;
    private String pic;

    public ChecklistAlatKomunikasiModel(String idUser, String idMapping, String tanggal, String lokasi, List<DetailChecklistAlatKomunikasi> pabx, List<DetailChecklistAlatKomunikasi> repeater, List<DetailChecklistAlatKomunikasi> radio, String keterangan, String probIdentification, String rootCause, String correctAct, String preventAct, String deadLine, String pic) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.pabx = pabx;
        this.repeater = repeater;
        this.radio = radio;
        this.keterangan = keterangan;
        this.probIdentification = probIdentification;
        this.rootCause = rootCause;
        this.correctAct = correctAct;
        this.preventAct = preventAct;
        this.deadLine = deadLine;
        this.pic = pic;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public List<DetailChecklistAlatKomunikasi> getPabx() {
        return pabx;
    }

    public void setPabx(List<DetailChecklistAlatKomunikasi> pabx) {
        this.pabx = pabx;
    }

    public List<DetailChecklistAlatKomunikasi> getRepeater() {
        return repeater;
    }

    public void setRepeater(List<DetailChecklistAlatKomunikasi> repeater) {
        this.repeater = repeater;
    }

    public List<DetailChecklistAlatKomunikasi> getRadio() {
        return radio;
    }

    public void setRadio(List<DetailChecklistAlatKomunikasi> radio) {
        this.radio = radio;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getProbIdentification() {
        return probIdentification;
    }

    public void setProbIdentification(String probIdentification) {
        this.probIdentification = probIdentification;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getCorrectAct() {
        return correctAct;
    }

    public void setCorrectAct(String correctAct) {
        this.correctAct = correctAct;
    }

    public String getPreventAct() {
        return preventAct;
    }

    public void setPreventAct(String preventAct) {
        this.preventAct = preventAct;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public static class DetailChecklistAlatKomunikasi implements Serializable {
        private String pertanyaan;
        private int status;

        public DetailChecklistAlatKomunikasi(String pertanyaan, int status) {
            this.pertanyaan = pertanyaan;
            this.status = status;
        }

        public String getPertanyaan() {
            return pertanyaan;
        }

        public void setPertanyaan(String pertanyaan) {
            this.pertanyaan = pertanyaan;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
