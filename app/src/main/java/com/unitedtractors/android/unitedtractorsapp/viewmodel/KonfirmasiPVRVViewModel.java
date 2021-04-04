package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.IdTransResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PVRVModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KonfirmasiPVRVViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public KonfirmasiPVRVViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<IdTransResponse> postPVRV(PVRVModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("byrKepada", model.getByrKepada());
            paramObject.put("nrp", model.getNrp());
            paramObject.put("keperluan", model.getKeperluan());
            paramObject.put("noPo", model.getNoPo());
            paramObject.put("noInvoice", model.getNoInvoice());
            paramObject.put("type", model.getType());
            paramObject.put("noPPN", model.getNoPPN());
            paramObject.put("noPPH", model.getNoPPH());
            paramObject.put("totAmount", model.getTotAmount());
            paramObject.put("detPVRV", array(model.getDetPVRV()));

            return onlineRepository.postPVRV(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postPVRV(null);
    }

    public MutableLiveData<BaseResponse> postDokumenPVRV(String idUsers_, String idTrans_, String startUpload_, String totalUpload_, String path) {
        RequestBody idUsers = RequestBody.create(MediaType.parse("text/plain"), idUsers_);
        RequestBody idTrans = RequestBody.create(MediaType.parse("text/plain"), idTrans_);
        RequestBody startUpload = RequestBody.create(MediaType.parse("text/plain"), startUpload_);
        RequestBody totalUpload = RequestBody.create(MediaType.parse("text/plain"), totalUpload_);
        File file = new File(path);
        return onlineRepository.postDokumenPVRV(
                idUsers,
                idTrans,
                startUpload,
                totalUpload,
                MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("application/pdf"), file))
        );
    }

    private JSONArray array(List<PVRVModel.DetailPVRV> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (PVRVModel.DetailPVRV detailPVRV : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("acc", detailPVRV.getAcc());
            jsonObject.put("desc", detailPVRV.getDesc());
            jsonObject.put("alloc", detailPVRV.getAlloc());
            jsonObject.put("bArea", detailPVRV.getbArea());
            jsonObject.put("costCenter", detailPVRV.getCostCenter());
            jsonObject.put("amount", detailPVRV.getAmount());
            jsonObject.put("ket", detailPVRV.getKet().isEmpty() ? "-" : detailPVRV.getKet());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
