package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

public class PostMobilResponse extends BaseResponse {
    @SerializedName("idTrans")
    private String idTrans;

    public String getIdTrans() {
        return idTrans;
    }
}
