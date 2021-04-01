package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

public class IdTransResponse extends BaseResponse {
    @SerializedName("idTrans")
    private String idTrans;

    public String getIdTrans() {
        return idTrans;
    }
}
