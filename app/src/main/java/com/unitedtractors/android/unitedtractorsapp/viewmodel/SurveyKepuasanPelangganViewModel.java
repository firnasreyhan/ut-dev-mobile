package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.model.SurveyKepuasanPelangganModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SurveyKepuasanPelangganViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public SurveyKepuasanPelangganViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postKepuasan(SurveyKepuasanPelangganModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("responden", model.getResponden());
            paramObject.put("penilaian", array(model.getPenilaian()));

            return onlineRepository.postKepuasan(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postKepuasan(null);
    }

    private JSONArray array(List<SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (SurveyKepuasanPelangganModel.DetailSurveyKepuasanPelangan surveyKepuasanPelangan : list) {
            JSONObject object = new JSONObject();
            object.put("status", String.valueOf(surveyKepuasanPelangan.getStatus()));
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
