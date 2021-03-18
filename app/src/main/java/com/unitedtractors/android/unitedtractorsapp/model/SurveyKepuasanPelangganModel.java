package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class SurveyKepuasanPelangganModel {
    private String idUser;
    private String idMapping;
    private String responden;
    private List<DetailSurveyKepuasanPelangan> penilaian;

    public SurveyKepuasanPelangganModel(String idUser, String idMapping, String responden, List<DetailSurveyKepuasanPelangan> penilaian) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.responden = responden;
        this.penilaian = penilaian;
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

    public String getResponden() {
        return responden;
    }

    public void setResponden(String responden) {
        this.responden = responden;
    }

    public List<DetailSurveyKepuasanPelangan> getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(List<DetailSurveyKepuasanPelangan> penilaian) {
        this.penilaian = penilaian;
    }

    public static class DetailSurveyKepuasanPelangan {
        private String pertanyaan;
        private int status;

        public DetailSurveyKepuasanPelangan(String pertanyaan, int status) {
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
