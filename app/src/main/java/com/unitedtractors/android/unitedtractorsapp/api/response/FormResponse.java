package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FormResponse extends BaseResponse {
    @SerializedName("data")
    private List<FormModel> data;

    public List<FormModel> getData() {
        return data;
    }

    public static class FormModel {
        @SerializedName("ID_MAPPING")
        private String idMapping;

        @SerializedName("NAMA_TABEL")
        private String namaTabel;

        @SerializedName("NO_DOC")
        private String noDoc;

        @SerializedName("NAMA_FORM")
        private String namaForm;

        @SerializedName("SECTION_FORM")
        private String sectionForm;

        public FormModel(String idMapping, String namaTabel, String noDoc, String namaForm, String sectionForm) {
            this.idMapping = idMapping;
            this.namaTabel = namaTabel;
            this.noDoc = noDoc;
            this.namaForm = namaForm;
            this.sectionForm = sectionForm;
        }

        public String getIdMapping() {
            return idMapping;
        }

        public String getNamaTabel() {
            return namaTabel;
        }

        public String getNoDoc() {
            return noDoc;
        }

        public String getNamaForm() {
            return namaForm;
        }

        public String getSectionForm() {
            return sectionForm;
        }
    }
}
