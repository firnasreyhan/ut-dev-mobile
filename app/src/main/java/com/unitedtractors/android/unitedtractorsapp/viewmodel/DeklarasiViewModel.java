package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.DeklarasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DeklarasiViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public DeklarasiViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postDeklarasi(DeklarasiModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTgl());
            paramObject.put("dd", model.getDd());
            paramObject.put("nopol", model.getNopol());
            paramObject.put("detKeperluan", arrayDetailKeperluan(model.getDetKeperluan()));

            return onlineRepository.postDeklarasi(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postDeklarasi(null);
    }

    private JSONArray arrayDetailKeperluan(List<DeklarasiModel.DetKeperluan> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (DeklarasiModel.DetKeperluan detKeperluan : list) {
            JSONObject object = new JSONObject();
            object.put("bbm", String.valueOf(detKeperluan.getBbm()));
            object.put("tol", String.valueOf(detKeperluan.getTol()));
            object.put("grab", String.valueOf(detKeperluan.getGrab()));
            object.put("lain", String.valueOf(detKeperluan.getLain()));
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
