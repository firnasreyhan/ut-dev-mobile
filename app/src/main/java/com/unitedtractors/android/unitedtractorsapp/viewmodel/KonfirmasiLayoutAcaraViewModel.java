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
import com.unitedtractors.android.unitedtractorsapp.model.LayoutAcaraModel;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KonfirmasiLayoutAcaraViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;
    private Context context;

    public KonfirmasiLayoutAcaraViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<IdTransResponse> postLayoutAcara(LayoutAcaraModel model) {
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("namaAcara", model.getNamaAcara());
            paramObject.put("lokasi", model.getLokasi());
            paramObject.put("jam", model.getJam());
            paramObject.put("tgl", model.getTgl());
            paramObject.put("peserta", model.getPeserta());
            paramObject.put("biaya", model.getBiaya());
            paramObject.put("keterangan", model.getKeterangan());

            return onlineRepository.postLayoutAcara(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postLayoutAcara(null);
    }

    public MutableLiveData<BaseResponse> postGambarLayoutAcara(String idUsers_, String idTrans_, File file_) {
        RequestBody idUsers = RequestBody.create(MediaType.parse("text/plain"), idUsers_);
        RequestBody idTrans = RequestBody.create(MediaType.parse("text/plain"), idTrans_);
        return onlineRepository.postGambarLayoutAcara(
                idUsers,
                idTrans,
                MultipartBody.Part.createFormData("file", file_.getName(), RequestBody.create(MediaType.parse("image/*"), file_))
        );
    }

    public File createTempFile(Bitmap bitmap) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +".JPEG");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        //write the bytes in file
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArray);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
