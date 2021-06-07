package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ExtensionDanAksesModel;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganModel;
import com.unitedtractors.android.unitedtractorsapp.model.NonAssetModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PermintaanExtensionDanAksesViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public PermintaanExtensionDanAksesViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postExtensionDanAkses(ExtensionDanAksesModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("detExtension", arrayDetailExtension(model.getDetExtension()));

            return onlineRepository.postExtensionDanAkses(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postExtensionDanAkses(null);
    }

    private JSONArray arrayDetailExtension(List<ExtensionDanAksesModel.DetExtension> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ExtensionDanAksesModel.DetExtension detExtension : list) {
            JSONObject object = new JSONObject();
            object.put("nama", detExtension.getNama());
            object.put("nrp", detExtension.getNrp());
            object.put("noExtension", detExtension.getNoExtension());
            object.put("div", detExtension.getDiv());
            object.put("aksesExisting", detExtension.getAksesExisting());
            object.put("aksesBaru", detExtension.getAksesBaru());
            object.put("ct", contactTo(detExtension.getContactTo()));
            jsonArray.put(object);
        }
        return jsonArray;
    }

    private JSONObject contactTo(ExtensionDanAksesModel.DetExtension.ContactTo contactTo) {
        JSONObject object = new JSONObject();
        try {
            object.put("principle", contactTo.isPrinciple());
            object.put("uthi", contactTo.isUthi());
            object.put("cabang", contactTo.isCabang());
            object.put("partner", contactTo.isPartner());
            object.put("customer", contactTo.isCustomer());
            object.put("vendor", contactTo.isVendor());
            object.put("subCont", contactTo.isSubCont());
            object.put("hotel", contactTo.isHotel());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
