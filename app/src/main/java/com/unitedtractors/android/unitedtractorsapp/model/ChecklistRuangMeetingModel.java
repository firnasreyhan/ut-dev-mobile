package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class ChecklistRuangMeetingModel {
    private String idUser;
    private String idMapping;
    private String ruang;
    private String tanggal;
    private DetailChecklistRuangMeeting minggu1;
    private DetailChecklistRuangMeeting minggu2;
    private DetailChecklistRuangMeeting minggu3;
    private DetailChecklistRuangMeeting minggu4;

    public ChecklistRuangMeetingModel(String idUser, String idMapping, String ruang, String tanggal, DetailChecklistRuangMeeting minggu1, DetailChecklistRuangMeeting minggu2, DetailChecklistRuangMeeting minggu3, DetailChecklistRuangMeeting minggu4) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.ruang = ruang;
        this.tanggal = tanggal;
        this.minggu1 = minggu1;
        this.minggu2 = minggu2;
        this.minggu3 = minggu3;
        this.minggu4 = minggu4;
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

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public DetailChecklistRuangMeeting getMinggu1() {
        return minggu1;
    }

    public void setMinggu1(DetailChecklistRuangMeeting minggu1) {
        this.minggu1 = minggu1;
    }

    public DetailChecklistRuangMeeting getMinggu2() {
        return minggu2;
    }

    public void setMinggu2(DetailChecklistRuangMeeting minggu2) {
        this.minggu2 = minggu2;
    }

    public DetailChecklistRuangMeeting getMinggu3() {
        return minggu3;
    }

    public void setMinggu3(DetailChecklistRuangMeeting minggu3) {
        this.minggu3 = minggu3;
    }

    public DetailChecklistRuangMeeting getMinggu4() {
        return minggu4;
    }

    public void setMinggu4(DetailChecklistRuangMeeting minggu4) {
        this.minggu4 = minggu4;
    }

    public static class DetailChecklistRuangMeeting {
        private String tanggal;
        private List<Integer> list;

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }
}
