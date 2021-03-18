package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.CateringRegulerModel;
import com.unitedtractors.android.unitedtractorsapp.model.SurveyKepuasanPelangganModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PermintaanCateringRegulerViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public PermintaanCateringRegulerViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postCateringReguler(CateringRegulerModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("detReguler", array(model.getDetailCaterings()));

            return onlineRepository.postCateringReguler(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postCateringReguler(null);
    }

    private JSONArray array(List<CateringRegulerModel.DetailCatering> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (CateringRegulerModel.DetailCatering detailCatering : list) {
            JSONObject object = new JSONObject();
            object.put("tgl", detailCatering.getTanggal());
            object.put("jmlOrang", detailCatering.getJumlahOrang());
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
