package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

public class TransactionDetailResponse extends BaseResponse{
    @SerializedName("data")
    private TransactionModel data;

    public TransactionModel getData() {
        return data;
    }

    public static class TransactionModel {
        @SerializedName("ID_TRANS")
        private String idTrans;

        @SerializedName("ID_USERS")
        private String idUsers;

        @SerializedName("NAMA_USERS")
        private String namaUsers;

        @SerializedName("ROLE_APP")
        private String roleApp;

        @SerializedName("ID_MAPPING")
        private String idMapping;

        @SerializedName("NAMA_FORM")
        private String namaForm;

        @SerializedName("TS_TRANS")
        private String tsTrans;

        @SerializedName("PATH_TRANS")
        private String pathTrans;

        @SerializedName("FLAG_TRANS")
        private String flagTrans;

        @SerializedName("STAT_TRANS")
        private String statTrans;

        @SerializedName("KETERANGAN_TRANS")
        private String keteranganTrans;

        public String getIdTrans() {
            return idTrans;
        }

        public String getIdUsers() {
            return idUsers;
        }

        public String getNamaUsers() {
            return namaUsers;
        }

        public String getRoleApp() {
            return roleApp;
        }

        public String getIdMapping() {
            return idMapping;
        }

        public String getNamaForm() {
            return namaForm;
        }

        public String getTsTrans() {
            return tsTrans;
        }

        public String getPathTrans() {
            return pathTrans;
        }

        public String getFlagTrans() {
            return flagTrans;
        }

        public String getStatTrans() {
            return statTrans;
        }

        public String getKeteranganTrans() {
            return keteranganTrans;
        }
    }
}
