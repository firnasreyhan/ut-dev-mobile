package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.MaterialUsedSlipModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONException;
import org.json.JSONObject;

public class MaterialUsedSlipViewModel extends AndroidViewModel {

    private Repository repository;

    public MaterialUsedSlipViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postMaterialUsedSlip(MaterialUsedSlipModel model) {
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tglMaterial", model.getTanggal());
            paramObject.put("namaBarang", model.getDetailMaterialUsedSlipModelList().get(0).getNamaBarang());
            paramObject.put("jumlahBarang", model.getDetailMaterialUsedSlipModelList().get(0).getJumlahBarang());
            paramObject.put("keterangan", model.getDetailMaterialUsedSlipModelList().get(0).getKeterangan());
            paramObject.put("dipergunakan", model.getDetailMaterialUsedSlipModelList().get(0).getDipergunakan());

            return repository.postMaterialUsedSlip(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postMaterialUsedSlip(null);
    }
}
