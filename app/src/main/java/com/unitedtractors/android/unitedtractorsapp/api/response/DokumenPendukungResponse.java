package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DokumenPendukungResponse extends BaseResponse {
    @SerializedName("data")
    private List<DetailDokumenPendukung> data;

    public List<DetailDokumenPendukung> getData() {
        return data;
    }

    public static class DetailDokumenPendukung {
        @SerializedName("fileName")
        private String fileName;

        @SerializedName("link")
        private String link;

        public String getFileName() {
            return fileName;
        }

        public String getLink() {
            return link;
        }
    }
}
