package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.MaterialUsedSlipModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MaterialUsedSlipViewModel extends AndroidViewModel {

    private OnlineRepository onlineRepository;

    public MaterialUsedSlipViewModel(Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postMaterialUsedSlip(MaterialUsedSlipModel model) {
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTanggal());
            paramObject.put("detMaterial", arrayDetailMaterial(model.getDetailMaterialUsedSlipModelList()));

            return onlineRepository.postMaterialUsedSlip(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postMaterialUsedSlip(null);
    }

    private JSONArray arrayDetailMaterial(List<MaterialUsedSlipModel.DetailMaterialUsedSlipModel> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (MaterialUsedSlipModel.DetailMaterialUsedSlipModel model : list) {
            JSONObject object = new JSONObject();
            object.put("nama", model.getNamaBarang());
            object.put("jml", model.getJumlahBarang());
            object.put("dipergunakan", model.getDipergunakan());
            object.put("keterangan", model.getKeterangan());
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
