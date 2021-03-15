package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaPondModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckingRunningTestViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public CheckingRunningTestViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postICP(ChecklistPompaPondModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTanggal());
            paramObject.put("pondA", model.getPondA());
            paramObject.put("pondB", model.getPondB());
            paramObject.put("pondC", model.getPondC());
            paramObject.put("pondD", model.getPondD());

            JSONArray checking = new JSONArray();
            for (String s: model.getChecking()) {
                JSONObject object = new JSONObject();
                object.put("status", s);
                checking.put(object);
            }
            paramObject.put("checking", checking);

            JSONArray runningTest = new JSONArray();
            for (String s: model.getRunning()) {
                JSONObject object = new JSONObject();
                object.put("status", s);
                runningTest.put(object);
            }
            paramObject.put("runningTest", runningTest);

            paramObject.put("catatan", model.getCatatan());

            return onlineRepository.postICP(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postICP(null);
    }
}
