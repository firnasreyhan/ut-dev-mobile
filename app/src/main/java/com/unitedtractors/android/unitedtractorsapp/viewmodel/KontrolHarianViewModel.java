package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.KontrolHarianModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class KontrolHarianViewModel extends AndroidViewModel {
    private Repository repository;

    public KontrolHarianViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postControlHarian(KontrolHarianModel model) {
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("jamControl", model.getJamKontrol());
            paramObject.put("kondisi", model.getData().get(0).getStatus());
            paramObject.put("kondisiKet", model.getData().get(0).getKeterangan());
            paramObject.put("kendaraan", model.getData().get(1).getStatus());
            paramObject.put("kendaraanKet", model.getData().get(1).getKeterangan());
            paramObject.put("makanan", model.getData().get(2).getStatus());
            paramObject.put("makananKet", model.getData().get(2).getKeterangan());
            paramObject.put("pesanan", model.getData().get(3).getStatus());
            paramObject.put("pesananKet", model.getData().get(3).getKeterangan());
            paramObject.put("sample", model.getData().get(4).getStatus());
            paramObject.put("sampleKet", model.getData().get(4).getKeterangan());
            paramObject.put("kebersihan", model.getData().get(5).getStatus());
            paramObject.put("kebersihanKet", model.getData().get(5).getKeterangan());
            paramObject.put("tglOut", model.getTglOut());

            return repository.postControlHarian(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postControlHarian(null);
    }
}
