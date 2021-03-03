package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistRuangMeetingModel;
import com.unitedtractors.android.unitedtractorsapp.model.OrderCateringModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderCateringViewModel extends AndroidViewModel {
    private Repository repository;

    public OrderCateringViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postOrderCatering(OrderCateringModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("idMapping", model.getIdMapping());

            return repository.postOrderCatering(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postOrderCatering(null);
    }
}
