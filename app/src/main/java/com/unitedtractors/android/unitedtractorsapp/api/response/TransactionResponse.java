package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionResponse extends BaseResponse{
    @SerializedName("data")
    private List<TransactionModel> data;

    public List<TransactionModel> getData() {
        return data;
    }

    public static class TransactionModel {
        @SerializedName("ID_TRANS")
        private String idTrans;

        @SerializedName("ID_MAPPING")
        private String idMapping;

        @SerializedName("ID_USERS")
        private String idUsers;

        @SerializedName("NAMA_USERS")
        private String namaUsers;

        @SerializedName("NAMA_FORM")
        private String namaForm;

        @SerializedName("PATH_TRANS")
        private String pathTrans;

        @SerializedName("TS_TRANS")
        private String tsTrans;

        @SerializedName("FLAG_TRANS")
        private String flagTrans;

        @SerializedName("CONFIRM_STAT_TRANS")
        private int confirmStatTrans;

        @SerializedName("STAT_TRANS")
        private String statTrans;

        public String getIdTrans() {
            return idTrans;
        }

        public String getIdMapping() {
            return idMapping;
        }

        public String getIdUsers() {
            return idUsers;
        }

        public String getNamaUsers() {
            return namaUsers;
        }

        public String getNamaForm() {
            return namaForm;
        }

        public String getPathTrans() {
            return pathTrans;
        }

        public String getTsTrans() {
            return tsTrans;
        }

        public String getFlagTrans() {
            return flagTrans;
        }

        public int getConfirmStatTrans() {
            return confirmStatTrans;
        }

        public String getStatTrans() {
            return statTrans;
        }

        public void setStatTrans(String statTrans) {
            this.statTrans = statTrans;
        }
    }
}
