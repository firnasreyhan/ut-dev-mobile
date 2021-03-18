package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForHydrantModel;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaAirBersihModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChecklistPompaAirBersihViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;
    private OnlineRepository onlineRepository;

    public ChecklistPompaAirBersihViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
        onlineRepository = new OnlineRepository();
    }

    public LiveData<List<MingguEntity>> getMinggu(){
        return offlineRepository.getMinggu();
    }

    public LiveData<List<DetailMingguEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMinggu(mingguKe);
    }

    public void insertMinggu(MingguEntity mingguEntity){
        offlineRepository.insertMinggu(mingguEntity);
    }

    public void deleteDetaiAlllMinggu(){
        offlineRepository.deleteDetaiAlllMinggu();
    }

    public void updateAllMinggu(boolean status){
        offlineRepository.updateAllMinggu(status);
    }

    public MutableLiveData<BaseResponse> postICPAB(ChecklistPompaAirBersihModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTgl());
            paramObject.put("lokasi", model.getLokasi());

            paramObject.put("cek1", detailMinggu(model.getMinggu1()));
            paramObject.put("cek2", detailMinggu(model.getMinggu2()));
            paramObject.put("cek3", detailMinggu(model.getMinggu3()));
            paramObject.put("cek4", detailMinggu(model.getMinggu4()));
            return onlineRepository.postICPAB(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postICPAB(null);
    }

    private JSONObject detailMinggu(ChecklistPompaAirBersihModel.DetailMinggu detailMinggu) {
        JSONObject object = new JSONObject();
        try {
            object.put("kondAir", detailChecklist(detailMinggu.getKondAir()));
            object.put("airPancingan", detailChecklist(detailMinggu.getAirPancingan()));
            object.put("indikator", detailChecklist(detailMinggu.getIndikator()));
            object.put("tekUdara", detailChecklist(detailMinggu.getTekUdara()));
            object.put("flowMeter", detailChecklist(detailMinggu.getFlowMeter()));
            object.put("supplyAir", detailChecklist(detailMinggu.getSupplyAir()));
            object.put("manSupply", detailChecklist(detailMinggu.getManSupply()));
            object.put("fungsiPanel", detailChecklist(detailMinggu.getFungsiPanel()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    private JSONObject detailChecklist(ChecklistPompaAirBersihModel.DetailMinggu.DetailChecklist detailChecklist) {
        JSONObject object = new JSONObject();
        try {
            object.put("status", detailChecklist.isStatus());
            object.put("keterangan", detailChecklist.getKeterangan());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
