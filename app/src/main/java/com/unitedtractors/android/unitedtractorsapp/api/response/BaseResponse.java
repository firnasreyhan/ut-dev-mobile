package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
