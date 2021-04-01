package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.database.entity.DetailMingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguMonitoringCateringEntity;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistPompaAirBersihModel;
import com.unitedtractors.android.unitedtractorsapp.model.MonitoringLapanganModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MonitoringLapanganViewModel extends AndroidViewModel {
    private OfflineRepository offlineRepository;
    private OnlineRepository onlineRepository;

    public MonitoringLapanganViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
        onlineRepository = new OnlineRepository();
    }

    public LiveData<List<MingguMonitoringCateringEntity>> getMinggu(){
        return offlineRepository.getMingguMonitoringCatering();
    }

    public LiveData<List<DetailMingguMonitoringCateringEntity>> getDetailMinggu(int mingguKe){
        return offlineRepository.getDetailMingguMonitoringCatering(mingguKe);
    }

    public void insertMinggu(MingguMonitoringCateringEntity mingguMonitoringCateringEntity){
        offlineRepository.insertMingguMonitoringCatering(mingguMonitoringCateringEntity);
    }

    public void deleteDetaiAlllMinggu(){
        offlineRepository.deleteDetailAllMingguMonitoringCatering();
    }

    public void updateAllMinggu(boolean status){
        offlineRepository.updateAllMingguMonitoringCatering(status);
    }

    public MutableLiveData<BaseResponse> postMonitoringLapangan(MonitoringLapanganModel model) {
        try {
            JSONObject object = new JSONObject();
            object.put("idUser", model.getIdUser());
            object.put("idMapping", model.getIdMapping());

            object.put("cek1", detailMinggu(model.getCek1()));
            object.put("cek2", detailMinggu(model.getCek2()));
            object.put("cek3", detailMinggu(model.getCek3()));
            object.put("cek4", detailMinggu(model.getCek4()));
            object.put("cek5", detailMinggu(model.getCek5()));
            object.put("cek6", detailMinggu(model.getCek6()));
            object.put("cek7", detailMinggu(model.getCek7()));
            return onlineRepository.postMonitoringLapangan(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postMonitoringLapangan(null);
    }

    private JSONObject detailMinggu(MonitoringLapanganModel.DetailMonitoringLapanganCatering detailMinggu) {
        JSONObject object = new JSONObject();
        try {
            if (detailMinggu.getTgl() != null && detailMinggu.getOrder() != null && detailMinggu.getBawa() != null && detailMinggu.getKupon() != null) {
                object.put("tgl", detailMinggu.getTgl().isEmpty() ? "-" : detailMinggu.getTgl());
                object.put("order", detailMinggu.getOrder().isEmpty() ? "-" : detailMinggu.getOrder());
                object.put("bawa", detailMinggu.getBawa().isEmpty() ? "-" : detailMinggu.getBawa());
                object.put("kupon", detailMinggu.getKupon().isEmpty() ? "-" : detailMinggu.getKupon());
            } else {
                object.put("tgl", "-");
                object.put("order", "-");
                object.put("bawa", "-");
                object.put("kupon", "-");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
