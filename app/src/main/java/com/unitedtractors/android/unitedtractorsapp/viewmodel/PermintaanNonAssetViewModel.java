package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.DeklarasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.NonAssetModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PermintaanNonAssetViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public PermintaanNonAssetViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postNonAsset(NonAssetModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("detNonAsset", arrayDetailNonAsset(model.getDetNonAsset()));

            return onlineRepository.postNonAsset(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postNonAsset(null);
    }

    private JSONArray arrayDetailNonAsset(List<NonAssetModel.DetNonAsset> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (NonAssetModel.DetNonAsset detNonAsset : list) {
            JSONObject object = new JSONObject();
            object.put("tgl", detNonAsset.getTgl());
            object.put("jenBarang", detNonAsset.getJenBarang());
            object.put("account", detNonAsset.getAccount());
            object.put("cost", detNonAsset.getCost());
            object.put("jmlPesan", String.valueOf(detNonAsset.getJmlPesan()));
            object.put("keterangan", detNonAsset.getKeterangan().isEmpty() ? "-" : detNonAsset.getKeterangan());
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
