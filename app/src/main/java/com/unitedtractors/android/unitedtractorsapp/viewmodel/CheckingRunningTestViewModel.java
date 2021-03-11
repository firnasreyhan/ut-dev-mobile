package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaPondModel;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckingRunningTestViewModel extends AndroidViewModel {
    private Repository repository;

    public CheckingRunningTestViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postICGS(ChecklistPompaPondModel model) {
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

            return repository.postICGS(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postICGS(null);
    }
}
