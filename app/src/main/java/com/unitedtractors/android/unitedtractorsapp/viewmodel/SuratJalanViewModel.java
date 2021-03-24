package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.SuratJalanModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SuratJalanViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public SuratJalanViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postSuratJalan(SuratJalanModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTanggal());
            paramObject.put("nama", model.getNama());
            paramObject.put("kendaraan", model.getKendaraan());
            paramObject.put("keperluan", model.getKeperluan());

            return onlineRepository.postSuratJalan(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postSuratJalan(null);
    }
}
