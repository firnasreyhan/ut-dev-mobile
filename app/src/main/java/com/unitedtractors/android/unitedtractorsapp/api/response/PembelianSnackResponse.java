package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;

import java.util.List;

public class PembelianSnackResponse extends BaseResponse{
    @SerializedName("data")
    private PembelianSnack data;

    public PembelianSnack getData() {
        return data;
    }

    public static class PembelianSnack {
        @SerializedName("ID_TRANS")
        private String idTrans;

        @SerializedName("ID_SNACK")
        private String idSnack;

        @SerializedName("ID_USERS")
        private String idUsers;

        @SerializedName("NAMA_USERS")
        private String namaUsers;

        @SerializedName("ID_MAPPING")
        private String idMapping;

        @SerializedName("TGL_SNACK")
        private String tglSnack;

        @SerializedName("DIVISI_SNACK")
        private String divisiSnack;

        @SerializedName("KEPERLUAN_SNACK")
        private String keperluanSnack;

        @SerializedName("TGLOUT_SNACK")
        private String tglOutSnack;

        @SerializedName("PATH_TRANS")
        private String pathTrans;

        @SerializedName("FLAG_TRANS")
        private String flagTrans;

        @SerializedName("STAT_TRANS")
        private int statTrans;

        @SerializedName("DETAIL_SNACK")
        private List<DetailSnack> dteailSnack;

        public String getIdTrans() {
            return idTrans;
        }

        public String getIdSnack() {
            return idSnack;
        }

        public String getIdUsers() {
            return idUsers;
        }

        public String getNamaUsers() {
            return namaUsers;
        }

        public String getIdMapping() {
            return idMapping;
        }

        public String getTglSnack() {
            return tglSnack;
        }

        public String getDivisiSnack() {
            return divisiSnack;
        }

        public String getKeperluanSnack() {
            return keperluanSnack;
        }

        public String getTglOutSnack() {
            return tglOutSnack;
        }

        public String getPathTrans() {
            return pathTrans;
        }

        public String getFlagTrans() {
            return flagTrans;
        }

        public int getStatTrans() {
            return statTrans;
        }

        public List<DetailSnack> getDteailSnack() {
            return dteailSnack;
        }

        public static class DetailSnack {
            @SerializedName("IDDET_SNACK")
            private String idDetSnack;

            @SerializedName("ID_SNACK")
            private String idSnack;

            @SerializedName("JENIS_SNACK")
            private String jenisSnack;

            @SerializedName("JML_SNACK")
            private String jmlSnack;

            public String getIdDetSnack() {
                return idDetSnack;
            }

            public String getIdSnack() {
                return idSnack;
            }

            public String getJenisSnack() {
                return jenisSnack;
            }

            public String getJmlSnack() {
                return jmlSnack;
            }
        }
    }
}
