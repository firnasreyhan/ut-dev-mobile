package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PerbaikanModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiPerbaikanViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public KonfirmasiPerbaikanViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postPerbaikan(PerbaikanModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTgl());
            paramObject.put("waktu", model.getWaktu());
            paramObject.put("namaDari", model.getNamaDari());
            paramObject.put("divisi", model.getDivisi());
            paramObject.put("extension", model.getExtension());
            paramObject.put("namaDiterima", model.getNamaDiterima());
            paramObject.put("troubTicket", model.getTroubTicket());
            paramObject.put("jenisPerbaikan", model.getJenisPerbaikan());
            paramObject.put("alasan", model.getAlasan());
            paramObject.put("dikerjakanOleh", model.getDikerjakanOleh());
            paramObject.put("estWaktu", model.getEstWaktu());
            paramObject.put("estBiaya", model.getEstBiaya());

            return onlineRepository.postPerbaikan(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postPerbaikan(null);
    }
}
