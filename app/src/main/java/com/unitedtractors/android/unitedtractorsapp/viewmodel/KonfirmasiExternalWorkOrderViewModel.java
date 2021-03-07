package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiExternalWorkOrderViewModel extends AndroidViewModel {
    private Repository repository;

    public KonfirmasiExternalWorkOrderViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postExternalWorkOrder(ExternalWorkOrderModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("intrKepada", model.getIntrKepada());
            paramObject.put("intrDari", model.getIntrDari());
            paramObject.put("deptDiv", model.getDeptDiv());
            paramObject.put("pekerjaan", model.getPekerjaan());
            paramObject.put("noReg", model.getNoReg());
            paramObject.put("reqDate", model.getReqDate());
            paramObject.put("pages", model.getPages());
            paramObject.put("cc", model.getCc());

            JSONArray jsonArray = new JSONArray();
            for (ExternalWorkOrderModel.DetailExternalWorkOrder detailExternalWorkOrder: model.getDetEwo()) {
                JSONObject detailSnack = new JSONObject();
                detailSnack.put("item", detailExternalWorkOrder.getItemPekerjaan());
                detailSnack.put("lokasi", detailExternalWorkOrder.getLokasiDiv());
                detailSnack.put("tglDiminta", detailExternalWorkOrder.getTanggalDimintaServer());
                detailSnack.put("troubleTicket", detailExternalWorkOrder.getTroubleTicket());
                detailSnack.put("keterangan", detailExternalWorkOrder.getKeterangan());
                jsonArray.put(detailSnack);
            }
            paramObject.put("detEwo", jsonArray);

            return repository.postExternalWorkOrder(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postExternalWorkOrder(null);
    }
}
