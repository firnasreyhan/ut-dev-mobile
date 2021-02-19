package com.unitedtractors.android.unitedtractorsapp.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.unitedtractors.android.unitedtractorsapp.api.response.SignInResponse;

public class AppPreference {
    static final String PREF = "PREF";
    static final String USER_PREF = "USER_PREF";

    public static void saveUser(Context context, SignInResponse.SignInModel signInModel){
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit().putString(USER_PREF, new Gson().toJson(signInModel)).apply();
    }

    public static SignInResponse.SignInModel getUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        if(pref.contains(USER_PREF)){
            Gson gson = new Gson();

            return gson.fromJson(pref.getString(USER_PREF, ""), SignInResponse.SignInModel.class);
        }

        return null;
    }

    public static void removeUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        if(pref.contains(USER_PREF)){
            pref.edit().remove(USER_PREF).apply();
        }
    }
}
