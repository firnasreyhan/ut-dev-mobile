package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.LaporanPerbaikanModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiLaporanPerbaikanViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public KonfirmasiLaporanPerbaikanViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postLaporanPerbaikan(LaporanPerbaikanModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tglPerbaikan", model.getTglPerbaikan());
            paramObject.put("tglOutPerbaikan", model.getTglOutPerbaikan());

            JSONArray jsonArray = new JSONArray();
            for (LaporanPerbaikanModel.DetailLaporanPerbaikan snackModel: model.getDetPerbaikan()) {
                JSONObject object = new JSONObject();
                object.put("laporanPerbaikan", snackModel.getLaporanPerbaikan());
                object.put("kategoriPerbaikan", snackModel.getKategoriPerbaikan());
                object.put("lokasiPerbaikan", snackModel.getLokasiPerbaikan());
                object.put("userPerbaikan", snackModel.getUserPerbaikan());
                object.put("picPerbaikan", snackModel.getPicPerbaikan());
                object.put("durasiPerbaikan", snackModel.getDurasiPerbaikan());
                object.put("statusPerbaikan", snackModel.getStatusPerbaikan());
                jsonArray.put(object);
            }
            paramObject.put("detPerbaikan", jsonArray);

            return onlineRepository.postLaporanPerbaikan(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postLaporanPerbaikan(null);
    }
}
