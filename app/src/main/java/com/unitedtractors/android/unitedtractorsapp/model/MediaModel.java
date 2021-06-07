package com.unitedtractors.android.unitedtractorsapp.model;

public class MediaModel {
    private String nama;
    private String path;
    private String size;

    public MediaModel(String nama, String path, String size) {
        this.nama = nama;
        this.path = path;
        this.size = size;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
