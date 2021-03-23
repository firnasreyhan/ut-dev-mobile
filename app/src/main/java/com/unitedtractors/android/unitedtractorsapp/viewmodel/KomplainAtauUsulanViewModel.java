package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.KomplainAtauUsulanModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class KomplainAtauUsulanViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public KomplainAtauUsulanViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postKomplainUsulan(KomplainAtauUsulanModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("detKomplain", arrayDetailKomplain(model.getDetKomplain()));

            return onlineRepository.postKomplainUsulan(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postKomplainUsulan(null);
    }

    private JSONArray arrayDetailKomplain(List<KomplainAtauUsulanModel.DetailKomplain> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (KomplainAtauUsulanModel.DetailKomplain detailKomplain : list) {
            JSONObject object = new JSONObject();
            object.put("komplainUsul", detailKomplain.getKomplainUsul());
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
