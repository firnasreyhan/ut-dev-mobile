package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.SidakCateringModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiSidakCateringViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public KonfirmasiSidakCateringViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postformSidakCatering(SidakCateringModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("perusahaan", model.getPerusahaan());
            paramObject.put("pemilik", model.getPemilik());
            paramObject.put("pengurus", model.getPengurus());
            paramObject.put("alamat", model.getAlamat());
            paramObject.put("tlp", model.getTelepon());
            paramObject.put("fax", model.getFax());
            paramObject.put("jmlTenagaKerja", model.getJumlahTenagaKerja());
            paramObject.put("perusahanDilayani", model.getPerusahaanDilayani());
            paramObject.put("kandepnaker", model.getKandepnaker());
            paramObject.put("kanwil", model.getKanwil());

            JSONArray ptk = new JSONArray();
            for (boolean b : model.getPersyaratanTenagaKerja()) {
                JSONObject object = new JSONObject();
                object.put("status", String.valueOf(b));
                ptk.put(object);
            }
            paramObject.put("ptk", ptk);

            JSONArray pkb_pm = new JSONArray();
            for (boolean b : model.getKesehatanBahanDanPenyimpananMakanan()) {
                JSONObject object = new JSONObject();
                object.put("status", String.valueOf(b));
                pkb_pm.put(object);
            }
            paramObject.put("pkb_pm", pkb_pm);

            JSONArray psl_pfm = new JSONArray();
            for (boolean b : model.getSanitasiLingkunganDanFasilitasPengolahMakanan()) {
                JSONObject object = new JSONObject();
                object.put("status", String.valueOf(b));
                psl_pfm.put(object);
            }
            paramObject.put("psl_pfm", psl_pfm);

            paramObject.put("catatan", model.getCatatan());

            return onlineRepository.postSidakCatering(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postSidakCatering(null);
    }
}
