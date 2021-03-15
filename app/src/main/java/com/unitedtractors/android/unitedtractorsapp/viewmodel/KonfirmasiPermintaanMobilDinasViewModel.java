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
import com.unitedtractors.android.unitedtractorsapp.api.response.PostMobilResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
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

public class KonfirmasiPermintaanMobilDinasViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;
    private Context context;

    public KonfirmasiPermintaanMobilDinasViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<PostMobilResponse> postPermintaanMobilDinas(PermintaanMobilModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("pengemudi", model.getNamaPengemudi());
            paramObject.put("tglPinjam", model.getTglPeminjaman());
            paramObject.put("tglKembali", model.getTglPengembalian());
            paramObject.put("divDept", model.getDivisi());
            paramObject.put("nopol", model.getNoPolisi().toUpperCase());
            paramObject.put("jamBerangkat", model.getJamBerangkat());
            paramObject.put("jamPulang", model.getJamPulang());
            paramObject.put("kmAwal", model.getKmAwal());
            paramObject.put("kmAkhir", model.getKmAkhir());
            paramObject.put("catatan", model.getCatatan());

            JSONArray jsonArray = new JSONArray();
            for (PermintaanMobilModel.TujuanMobilDinasModel tujuanMobilDinasModel: model.getTujuan()) {
                JSONObject detailSnack = new JSONObject();
                detailSnack.put("tujuan", tujuanMobilDinasModel.getTujuan());
                detailSnack.put("keperluan", tujuanMobilDinasModel.getKeperluan());
                jsonArray.put(detailSnack);
            }
            paramObject.put("detMobdin", jsonArray);

            return onlineRepository.postPermintaanMobilDinas(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postPermintaanMobilDinas(null);
    }
    public MutableLiveData<BaseResponse> postUploadSim(String idUsers_, String idTrans_, Uri file_) {
        RequestBody idUsers = RequestBody.create(MediaType.parse("text/plain"), idUsers_);
        RequestBody idTrans = RequestBody.create(MediaType.parse("text/plain"), idTrans_);
        return onlineRepository.postSimMobilDinas(
                idUsers,
                idTrans,
                compressFile(file_, "file")
        );
    }

    private File createTempFile(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private MultipartBody.Part compressFile(Uri uri, String path) {
        File file = new File(uri.getPath());
        try {
            File fileCompress = new Compressor(context)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .compressToFile(file);
            File file1 = createTempFile(Uri.fromFile(fileCompress.getAbsoluteFile()));
            Log.e("path", file1.getAbsolutePath());
            return MultipartBody.Part.createFormData(path, file1.getName(), RequestBody.create(MediaType.parse("image/*"), file1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
