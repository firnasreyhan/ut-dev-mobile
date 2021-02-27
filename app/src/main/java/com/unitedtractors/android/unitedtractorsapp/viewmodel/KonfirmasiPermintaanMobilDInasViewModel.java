package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilDinasModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiPermintaanMobilDInasViewModel extends AndroidViewModel {
    private Repository repository;

    public KonfirmasiPermintaanMobilDInasViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postPermintaanMobilDinas(PermintaanMobilDinasModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("pengemudi", model.getNamaPengemudi());
            paramObject.put("tglPinjam", model.getTglPeminjaman());
            paramObject.put("tglKembali", model.getTglPengembalian());
            paramObject.put("divDept", model.getDivisi());
            paramObject.put("nopol", model.getNoPolisi());
            paramObject.put("jamBerangkat", model.getJamBerangkat());
            paramObject.put("jamPulang", model.getJamPulang());
            paramObject.put("kmAwal", model.getKmAwal());
            paramObject.put("kmAkhir", model.getKmAkhir());
            paramObject.put("catatan", model.getCatatan());

            JSONArray jsonArray = new JSONArray();
            for (PermintaanMobilDinasModel.TujuanMobilDinasModel tujuanMobilDinasModel: model.getTujuan()) {
                JSONObject detailSnack = new JSONObject();
                detailSnack.put("tujuan", tujuanMobilDinasModel.getTujuan());
                detailSnack.put("keperluan", tujuanMobilDinasModel.getKeperluan());
                jsonArray.put(detailSnack);
            }
            paramObject.put("detMobdin", jsonArray);

            return repository.postPermintaanMobilDinas(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postPermintaanMobilDinas(null);
    }
}
