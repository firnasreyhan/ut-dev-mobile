package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistAlatKomunikasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChecklistAlatkomunikasiViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public ChecklistAlatkomunikasiViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postAlatKomunikasi(ChecklistAlatKomunikasiModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTanggal());
            paramObject.put("lokasi", model.getLokasi());
            paramObject.put("pabx", array(splitArray(model.getPabx(), 0, 9)));
            paramObject.put("progData", array(splitArray(model.getPabx(), 9, model.getPabx().size())));
            paramObject.put("repeater", array(model.getRepeater()));
            paramObject.put("radio", array(model.getRadio()));
            paramObject.put("keterangan", model.getKeterangan());
            paramObject.put("probIdentification", model.getProbIdentification());
            paramObject.put("rootCause", model.getRootCause());
            paramObject.put("correctAct", model.getCorrectAct());
            paramObject.put("preventAct", model.getPreventAct());
            paramObject.put("deadLine", model.getDeadLine());
            paramObject.put("pic", model.getPic());

            return onlineRepository.postAlatKomunikasi(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postAlatKomunikasi(null);
    }

    private JSONArray array(List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi detailChecklistAlatKomunikasi: list) {
            JSONObject object = new JSONObject();
            object.put("status", String.valueOf(detailChecklistAlatKomunikasi.getStatus()));
            jsonArray.put(object);
        }
        return jsonArray;
    }

    private List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> splitArray(List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> list, int start, int end) {
        List<ChecklistAlatKomunikasiModel.DetailChecklistAlatKomunikasi> arrayList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arrayList.add(list.get(i));
        }
        return arrayList;
    }
}
