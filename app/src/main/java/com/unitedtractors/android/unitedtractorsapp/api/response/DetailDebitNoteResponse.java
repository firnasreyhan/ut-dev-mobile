package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailDebitNoteResponse extends BaseResponse{
    @SerializedName("data")
    private DetailDebitNote data;

    public DetailDebitNote getData() {
        return data;
    }

    public static class DetailDebitNote {
        @SerializedName("ID_DEBITNOTE")
        private String idDebitNote;

        @SerializedName("EMAIL_DEBITNOTE")
        private String emailDebitNote;

        @SerializedName("TS_APP")
        private String tsApp;

        @SerializedName("ROLE_APP")
        private String roleApp;

        @SerializedName("PATH_DEBITNOTE")
        private String pathDebitNote;

        @SerializedName("ISAPPROVE_APP")
        private String isApproveApp;

        public String getIdDebitNote() {
            return idDebitNote;
        }

        public String getEmailDebitNote() {
            return emailDebitNote;
        }

        public String getTsApp() {
            return tsApp;
        }

        public String getRoleApp() {
            return roleApp;
        }

        public String getPathDebitNote() {
            return pathDebitNote;
        }

        public String getIsApproveApp() {
            return isApproveApp;
        }
    }
}
