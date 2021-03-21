package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistAlatKomunikasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.OrderCateringModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class OrderCateringViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public OrderCateringViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postOrderCatering(OrderCateringModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("detOrder", array(model.getDetOrder()));

            return onlineRepository.postOrderCatering(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postOrderCatering(null);
    }

    private JSONArray array(List<OrderCateringModel.DetailOrder> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (OrderCateringModel.DetailOrder detailOrder: list) {
            JSONObject object = new JSONObject();
            object.put("tgl", detailOrder.getTgl());
            object.put("divisi", detailOrder.getDivisi());
            object.put("jml", detailOrder.getJml());
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
