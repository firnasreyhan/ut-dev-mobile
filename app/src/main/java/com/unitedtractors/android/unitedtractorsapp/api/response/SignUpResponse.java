package com.unitedtractors.android.unitedtractorsapp.api.response;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse extends BaseResponse {
    @SerializedName("data")
    private SignUpModel data;

    public SignUpModel getData() {
        return data;
    }

    public static class SignUpModel {
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

        @SerializedName("TOKEN_USERS")
        private String tokenUsers;

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

        public String getTokenUsers() {
            return tokenUsers;
        }
    }
}
