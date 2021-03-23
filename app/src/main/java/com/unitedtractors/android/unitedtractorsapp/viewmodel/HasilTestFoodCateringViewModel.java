package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.HasilTestFoodCateringModel;
import com.unitedtractors.android.unitedtractorsapp.model.KomplainAtauUsulanModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HasilTestFoodCateringViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public HasilTestFoodCateringViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postTestFood(HasilTestFoodCateringModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("detTestfood", arrayDetailTestFood(model.getDetTestfood()));

            return onlineRepository.postTestFood(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postTestFood(null);
    }

    private JSONArray arrayDetailTestFood(List<HasilTestFoodCateringModel.DetailTestFood> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (HasilTestFoodCateringModel.DetailTestFood detailTestFood : list) {
            JSONObject object = new JSONObject();
            object.put("namaCatering", detailTestFood.getNamaCatering());
            object.put("rasa", String.valueOf(detailTestFood.getRasa()));
            object.put("aroma", String.valueOf(detailTestFood.getAroma()));
            object.put("kebersihan", String.valueOf(detailTestFood.getKebersihan()));
            object.put("kualitas", String.valueOf(detailTestFood.getKualitas()));
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
