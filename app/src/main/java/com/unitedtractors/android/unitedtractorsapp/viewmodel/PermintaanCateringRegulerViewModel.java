package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistRuangMeetingModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanCateringRegulerModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONException;
import org.json.JSONObject;

public class PermintaanCateringRegulerViewModel extends AndroidViewModel {
    private Repository repository;

    public PermintaanCateringRegulerViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postPermintaanCateringReguler(PermintaanCateringRegulerModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tglCatering", model.getTgl());
            paramObject.put("jumlahOrang", model.getJumlahOrang());

            return repository.postPermintaanCateringReguler(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postChecklistRuangMeeting(null);
    }
}
