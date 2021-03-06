package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguPompaAirBersihEntity;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaAirBersihModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

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

    public LiveData<List<MingguPompaAirBersihEntity>> getMinggu(){
        return offlineRepository.getMingguPompaAirBersih();
    }

    public LiveData<List<DetailMingguPompaAirBersihEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMingguPompaAirBersih(mingguKe);
    }

    public void insertMinggu(MingguPompaAirBersihEntity mingguPompaAirBersihEntity){
        offlineRepository.insertMingguPompaAirBersih(mingguPompaAirBersihEntity);
    }

    public void deleteDetaiAlllMinggu(){
        offlineRepository.deleteDetailAllMingguPompaAirBersih();
    }

    public void updateAllMinggu(boolean status){
        offlineRepository.updateAllMingguPompaAirBersih(status);
    }

    public MutableLiveData<BaseResponse> postICPAB(ChecklistPompaAirBersihModel model) {
        try {
            JSONObject object = new JSONObject();
            object.put("idUser", model.getIdUser());
            object.put("idMapping", model.getIdMapping());
            object.put("tgl", model.getTgl());
            object.put("lokasi", model.getLokasi());

            object.put("cek1", detailMinggu(model.getMinggu1()));
            object.put("cek2", detailMinggu(model.getMinggu2()));
            object.put("cek3", detailMinggu(model.getMinggu3()));
            object.put("cek4", detailMinggu(model.getMinggu4()));
            return onlineRepository.postICPAB(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postICPAB(null);
    }

    private JSONObject detailMinggu(ChecklistPompaAirBersihModel.DetailMinggu detailMinggu) {
        JSONObject object = new JSONObject();
        try {
            object.put("tgl", detailMinggu.getTgl());
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
