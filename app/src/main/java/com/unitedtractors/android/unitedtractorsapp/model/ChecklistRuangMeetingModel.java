package com.unitedtractors.android.unitedtractorsapp.model;

public class ChecklistRuangMeetingModel {
    private String idMapping;
    private String idUser;
    private String nama;
    private String tglCek;
    private String jamCek;
    private int viewer;
    private int whiteBoard;
    private int lcd;
    private int screen;
    private int spidol;

    public ChecklistRuangMeetingModel(String idMapping, String idUser, String nama, String tglCek, String jamCek, int viewer, int whiteBoard, int lcd, int screen, int spidol) {
        this.idMapping = idMapping;
        this.idUser = idUser;
        this.nama = nama;
        this.tglCek = tglCek;
        this.jamCek = jamCek;
        this.viewer = viewer;
        this.whiteBoard = whiteBoard;
        this.lcd = lcd;
        this.screen = screen;
        this.spidol = spidol;
    }

    public String getIdMapping() {
        return idMapping;
    }

    public void setIdMapping(String idMapping) {
        this.idMapping = idMapping;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglCek() {
        return tglCek;
    }

    public void setTglCek(String tglCek) {
        this.tglCek = tglCek;
    }

    public String getJamCek() {
        return jamCek;
    }

    public void setJamCek(String jamCek) {
        this.jamCek = jamCek;
    }

    public int getViewer() {
        return viewer;
    }

    public void setViewer(int viewer) {
        this.viewer = viewer;
    }

    public int getWhiteBoard() {
        return whiteBoard;
    }

    public void setWhiteBoard(int whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public int getLcd() {
        return lcd;
    }

    public void setLcd(int lcd) {
        this.lcd = lcd;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public int getSpidol() {
        return spidol;
    }

    public void setSpidol(int spidol) {
        this.spidol = spidol;
    }
}
