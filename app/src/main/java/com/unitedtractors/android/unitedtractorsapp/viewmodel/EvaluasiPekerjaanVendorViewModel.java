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
import com.unitedtractors.android.unitedtractorsapp.model.EvaluasiPekerjaanVendorModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OfflineRepository;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EvaluasiPekerjaanVendorViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public EvaluasiPekerjaanVendorViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postEvaluasiVendor(EvaluasiPekerjaanVendorModel model) {
        try {
            JSONObject object = new JSONObject();
            object.put("idUser", model.getIdUser());
            object.put("idMapping", model.getIdMapping());
            object.put("periode", model.getPerisode());
            object.put("namaVendor", model.getNamaVendor());
            object.put("namaPekerjaan", model.getNamaPekerjaan());
            object.put("spkNo", model.getSpkNo());
            object.put("tglSpk", model.getTglSPK());
            object.put("referensi", refrensi(model.getRefrensi()));
            object.put("hasilPenilaian", hasilPenilaian(model.getHasilPenilaian()));
            object.put("kesimpulan", model.getKesimpulan());
            return onlineRepository.postEvaluasiVendor(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postEvaluasiVendor(null);
    }

    private JSONObject refrensi(EvaluasiPekerjaanVendorModel.Refrensi refrensi) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("troubTicket", refrensi.getTroupTicket());
        object.put("ewo", refrensi.getEwo());
        return object;
    }

    private JSONObject hasilPenilaian(EvaluasiPekerjaanVendorModel.HasilPenilaian hasilPenilaian) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("mutu", String.valueOf(hasilPenilaian.getMutu()));
        object.put("harga", String.valueOf(hasilPenilaian.getHarga()));
        object.put("delivery", String.valueOf(hasilPenilaian.getDelivery()));
        object.put("safety", String.valueOf(hasilPenilaian.getSafety()));
        object.put("pelayanan", String.valueOf(hasilPenilaian.getPelayanan()));
        return object;
    }
}
