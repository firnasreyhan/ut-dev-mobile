package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiPembelianSnackViewModel extends AndroidViewModel {
    private Repository repository;

    public KonfirmasiPembelianSnackViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postPembelianSnack(PembelianSnackModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tglSnack", model.getTglSnack());
            paramObject.put("divisiSnack", model.getDivisiSnack());
            paramObject.put("keperluanSnack", model.getKeperluanSnack());

            JSONArray jsonArray = new JSONArray();
            for (PembelianSnackModel.DetailPembelianSnackModel snackModel: model.getDetSnack()) {
                JSONObject detailSnack = new JSONObject();
                detailSnack.put("jenisSnack", snackModel.getJenisSnack());
                detailSnack.put("jmlSnack", snackModel.getJumlah());
                jsonArray.put(detailSnack);
            }
            paramObject.put("detSnack", jsonArray);

            return repository.postPembelianSnack(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postPembelianSnack(null);
    }
}
