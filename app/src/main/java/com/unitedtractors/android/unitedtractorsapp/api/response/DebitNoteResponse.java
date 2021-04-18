package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DebitNoteResponse extends BaseResponse{
    @SerializedName("data")
    private List<DetailDebitNote> data;

    public List<DetailDebitNote> getData() {
        return data;
    }

    public static class DetailDebitNote {
        @SerializedName("ID_DEBITNOTE")
        private String idDebitNote;

        @SerializedName("EMAIL_DEBITNOTE")
        private String emailDebitNote;

        @SerializedName("PATH_DEBITNOTE")
        private String pathDebitNote;

        @SerializedName("TS_APP")
        private String tsApp;

        @SerializedName("STAT_DEBITNOTE")
        private String statDebitNote;

        @SerializedName("ISAPPROVE_APP")
        private String isApproveApp;

        public String getIdDebitNote() {
            return idDebitNote;
        }

        public String getEmailDebitNote() {
            return emailDebitNote;
        }

        public String getPathDebitNote() {
            return pathDebitNote;
        }

        public String getTsApp() {
            return tsApp;
        }

        public String getStatDebitNote() {
            return statDebitNote;
        }

        public String getIsApproveApp() {
            return isApproveApp;
        }
    }
}
