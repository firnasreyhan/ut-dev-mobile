package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.SyaratLegalitasCateringModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiSyaratLegalitasCateringViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public KonfirmasiSyaratLegalitasCateringViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
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
                object.put("status", String.valueOf(b));
                syarat.put(object);
            }
            paramObject.put("syarat", syarat);

            JSONArray survey = new JSONArray();
            for (boolean b : model.getSurvey()) {
                JSONObject object = new JSONObject();
                object.put("status", String.valueOf(b));
                survey.put(object);
            }
            paramObject.put("survey", survey);

            return onlineRepository.postLegalitas(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postLegalitas(null);
    }
}
