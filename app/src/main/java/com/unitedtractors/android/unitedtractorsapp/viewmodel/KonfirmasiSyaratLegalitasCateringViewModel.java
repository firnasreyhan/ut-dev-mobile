package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.SidakCateringModel;
import com.unitedtractors.android.unitedtractorsapp.model.SyaratLegalitasCateringModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiSyaratLegalitasCateringViewModel extends AndroidViewModel {
    private Repository repository;

    public KonfirmasiSyaratLegalitasCateringViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postLegalitas(SyaratLegalitasCateringModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("nama", model.getNama());
            paramObject.put("alamat", model.getAlamat());

            JSONArray syarat = new JSONArray();
            for (boolean b : model.getSyarat()) {
                JSONObject object = new JSONObject();
                object.put("status", b);
                syarat.put(object);
            }
            paramObject.put("syarat", syarat);

            JSONArray survey = new JSONArray();
            for (boolean b : model.getSurvey()) {
                JSONObject object = new JSONObject();
                object.put("status", b);
                survey.put(object);
            }
            paramObject.put("survey", survey);

            return repository.postLegalitas(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postLegalitas(null);
    }
}
