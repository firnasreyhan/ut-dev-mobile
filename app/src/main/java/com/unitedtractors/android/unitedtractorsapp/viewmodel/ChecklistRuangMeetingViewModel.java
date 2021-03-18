package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguRuangMeetingEntity;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistRuangMeetingModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChecklistRuangMeetingViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;
    private OnlineRepository onlineRepository;

    public ChecklistRuangMeetingViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
        onlineRepository = new OnlineRepository();
    }
    public LiveData<List<MingguRuangMeetingEntity>> getMinggu(){
        return offlineRepository.getMingguRuangMeeting();
    }

    public LiveData<List<DetailMingguRuangMeetingEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMingguRuangMeeting(mingguKe);
    }

    public void insertMinggu(MingguRuangMeetingEntity mingguRuangMeetingEntity){
        offlineRepository.insertMingguRuangMeeting(mingguRuangMeetingEntity);
    }

    public void deleteDetaiAlllMinggu(){
        offlineRepository.deleteDetailAllMingguRuangMeeting();
    }

    public void updateAllMinggu(boolean status){
        offlineRepository.updateAllMingguRuangMeeting(status);
    }

    public MutableLiveData<BaseResponse> postChecklistRuangMeeting(ChecklistRuangMeetingModel model) {
        try {
            JSONObject object = new JSONObject();
            object.put("idUser", model.getIdUser());
            object.put("idMapping", model.getIdMapping());
            object.put("ruang", model.getRuang());
            object.put("tgl", model.getTanggal());

            object.put("cek1", detailMinggu(model.getMinggu1()));
            object.put("cek2", detailMinggu(model.getMinggu2()));
            object.put("cek3", detailMinggu(model.getMinggu3()));
            object.put("cek4", detailMinggu(model.getMinggu4()));
            return onlineRepository.postRuangMeeting(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postRuangMeeting(null);
    }

    private JSONObject detailMinggu(ChecklistRuangMeetingModel.DetailChecklistRuangMeeting detailChecklistRuangMeeting) {
        JSONObject object = new JSONObject();
        try {
            object.put("tgl", detailChecklistRuangMeeting.getTanggal());
            object.put("viewer", detailChecklistRuangMeeting.getList().get(0));
            object.put("board", detailChecklistRuangMeeting.getList().get(1));
            object.put("lcd", detailChecklistRuangMeeting.getList().get(2));
            object.put("screen", detailChecklistRuangMeeting.getList().get(3));
            object.put("spidol", detailChecklistRuangMeeting.getList().get(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
