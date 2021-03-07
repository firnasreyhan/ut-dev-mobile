package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.IdentifikasiModel;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IdentifikasiViewModel extends AndroidViewModel {
    private Repository repository;

    public IdentifikasiViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postIdentifikasi(IdentifikasiModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());

            JSONArray jsonArray = new JSONArray();
            for (IdentifikasiModel.DetailIdentifikasi detailIdentifikasi: model.getDetIdentifikasi()) {
                JSONObject detailSnack = new JSONObject();
                detailSnack.put("temuan", detailIdentifikasi.getTemuanLapangan());
                detailSnack.put("tgl", detailIdentifikasi.getTanggal());
                Log.e("tanggal", detailIdentifikasi.getTanggalView());
                detailSnack.put("kategori", detailIdentifikasi.getKatergoriTemuan());
                detailSnack.put("lokasi", detailIdentifikasi.getLokasi());
                detailSnack.put("user", detailIdentifikasi.getUser());
                jsonArray.put(detailSnack);
            }
            paramObject.put("detIdentifikasi", jsonArray);

            return repository.postIdentifikasi(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postIdentifikasi(null);
    }
}
