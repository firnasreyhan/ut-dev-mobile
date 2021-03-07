package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.PermintaanMobilModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONException;
import org.json.JSONObject;

public class KonfirmasiPermintaanMobilPribadiViewModel extends AndroidViewModel {
    private Repository repository;
    private Context context;

    public KonfirmasiPermintaanMobilPribadiViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<BaseResponse> postPermintaanMobilPribadi(PermintaanMobilModel model) {
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("peminjam", model.getNamaPeminjam());
            paramObject.put("pengemudi", model.getNamaPengemudi());
            paramObject.put("tglPinjam", model.getTglPeminjaman());
            paramObject.put("tglKembali", model.getTglPengembalian());
            paramObject.put("divDept", model.getDivisi());
            paramObject.put("nopol", model.getNoPolisi());
            paramObject.put("jamBerangkat", model.getJamBerangkat());
            paramObject.put("jamPulang", model.getJamPulang());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postPermintaanMobilPribadi(paramObject.toString());
    }

//    private File createTempFile(Bitmap bitmap) {
//        bitmap.getScaledWidth(600);
//        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//                , System.currentTimeMillis() +".PNG");
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream.toByteArray();
//        //write the bytes in file
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(byteArray);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
//
//    private MultipartBody.Part compressFile(Bitmap bitmap, String path) {
//        File file1 = createTempFile(scaleDown(bitmap, 600, true));
//        Log.e("path", file1.getAbsolutePath());
//        return MultipartBody.Part.createFormData(path, file1.getName(), RequestBody.create(MediaType.parse("image/*"), file1));
//    }
}
