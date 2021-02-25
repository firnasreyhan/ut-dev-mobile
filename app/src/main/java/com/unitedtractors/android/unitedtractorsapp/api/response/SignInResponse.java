package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignInResponse extends BaseResponse {
    @SerializedName("data")
    private SignInModel data;

    public SignInModel getData() {
        return data;
    }

    public static class SignInModel {
        @SerializedName("ID_USERS")
        private String idUsers;

        @SerializedName("NAMA_USERS")
        private String namaUsers;

        @SerializedName("ROLE_USERS")
        private String roleUsers;

        @SerializedName("DEPT_USERS")
        private String deptUsers;

        @SerializedName("DIV_USERS")
        private String divUsers;

        @SerializedName("USER_USERS")
        private String userUsers;

        @SerializedName("PASS_USERS")
        private String passUsers;

        @SerializedName("LOGIN_USERS")
        private String loginUsers;

        @SerializedName("TOKEN_USERS")
        private String tokenUsers;

        @SerializedName("PATH_TTD")
        private String pathTTD;

        @SerializedName("STAT_USERS")
        private int statUsers;

        @SerializedName("TS_USERS")
        private String tsUsers;

        public String getIdUsers() {
            return idUsers;
        }

        public String getNamaUsers() {
            return namaUsers;
        }

        public String getRoleUsers() {
            return roleUsers;
        }

        public String getDeptUsers() {
            return deptUsers;
        }

        public String getDivUsers() {
            return divUsers;
        }

        public String getUserUsers() {
            return userUsers;
        }

        public String getPassUsers() {
            return passUsers;
        }

        public String getLoginUsers() {
            return loginUsers;
        }

        public String getTokenUsers() {
            return tokenUsers;
        }

        public String getPathTTD() {
            return pathTTD;
        }

        public int getStatUsers() {
            return statUsers;
        }

        public String getTsUsers() {
            return tsUsers;
        }
    }
}
