package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.InternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.PembelianSnackModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class KonfirmasiInternalWorkOrderViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public KonfirmasiInternalWorkOrderViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postInternalWorkOrder(InternalWorkOrderModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("troubTicket", model.getTroubTicket());
            paramObject.put("tgl", model.getTgl());
            paramObject.put("tglMulai", model.getTglMulai());
            paramObject.put("tglSelesai", model.getTglSelesai());
            paramObject.put("namaPemohon", model.getNamaPemohon());
            paramObject.put("div", model.getDiv());
            paramObject.put("ext", model.getExt());
            paramObject.put("namaPenerima", model.getNamaPenerima());
            paramObject.put("jenPerbaikan", objectJenisPerbaikan(model.getJenPerbaikan()));
            paramObject.put("ketPerbaikan", model.getKetPerbaikan());
            paramObject.put("alsnPerbaikan", model.getKetPerbaikan());
            paramObject.put("detKebutuhan", arrayDetailKebutuhan(model.getDetKebutuhan()));

            return onlineRepository.postInternalWorkOrder(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postInternalWorkOrder(null);
    }

    private JSONObject objectJenisPerbaikan(InternalWorkOrderModel.JenisPerbaikan jenisPerbaikan) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("pengBaru", jenisPerbaikan.isPengBaru());
        object.put("pengSebagian", jenisPerbaikan.isPengSebagian());
        object.put("perbaikan", jenisPerbaikan.isPerbaikan());
        return object;
    }

    private JSONArray arrayDetailKebutuhan(List<InternalWorkOrderModel.DetailKebutuhan> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (InternalWorkOrderModel.DetailKebutuhan detailKebutuhan : list) {
            JSONObject object = new JSONObject();
            object.put("nama", detailKebutuhan.getNama());
            object.put("quant", detailKebutuhan.getQuant());
            object.put("musNo", detailKebutuhan.getMusNo());
            jsonArray.put(object);
        }
        return jsonArray;
    }
}
