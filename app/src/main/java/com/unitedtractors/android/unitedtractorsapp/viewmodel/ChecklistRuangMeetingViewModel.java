package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistRuangMeetingModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONException;
import org.json.JSONObject;

public class ChecklistRuangMeetingViewModel extends AndroidViewModel {
    private Repository repository;

    public ChecklistRuangMeetingViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postChecklistRuangMeeting(ChecklistRuangMeetingModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tglCek", model.getTglCek());
            paramObject.put("namaCek", model.getLcd());
            paramObject.put("screen", model.getScreen());
            paramObject.put("lcd", model.getLcd());
            paramObject.put("viewer", model.getViewer());
            paramObject.put("spidol", model.getSpidol());
            paramObject.put("whiteBoard", model.getWhiteBoard());

            return repository.postChecklistRuangMeeting(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postChecklistRuangMeeting(null);
    }
}
