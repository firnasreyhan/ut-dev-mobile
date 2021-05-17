package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
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
import com.unitedtractors.android.unitedtractorsapp.model.HasilTestFoodCateringModel;
import com.unitedtractors.android.unitedtractorsapp.model.KomplainAtauUsulanModel;
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

public class HasilTestFoodCateringViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public HasilTestFoodCateringViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<IdTransResponse> postTestFood(HasilTestFoodCateringModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("detTestfood", arrayDetailTestFood(model.getDetTestfood()));

            return onlineRepository.postTestFood(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postTestFood(null);
    }

    private JSONArray arrayDetailTestFood(List<HasilTestFoodCateringModel.DetailTestFood> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (HasilTestFoodCateringModel.DetailTestFood detailTestFood : list) {
            JSONObject object = new JSONObject();
            object.put("namaCatering", detailTestFood.getNamaCatering());
            object.put("rasa", String.valueOf(detailTestFood.getRasa()));
            object.put("aroma", String.valueOf(detailTestFood.getAroma()));
            object.put("kebersihan", String.valueOf(detailTestFood.getKebersihan()));
            object.put("kualitas", String.valueOf(detailTestFood.getKualitas()));
            jsonArray.put(object);
        }
        return jsonArray;
    }

    public MutableLiveData<BaseResponse> postGambarTestFood(String idUsers_, String idTrans_, Uri file_) {
        RequestBody idUsers = RequestBody.create(MediaType.parse("text/plain"), idUsers_);
        RequestBody idTrans = RequestBody.create(MediaType.parse("text/plain"), idTrans_);
        return onlineRepository.postGambarTestFood(
                idUsers,
                idTrans,
                compressFile(file_, "file")
        );
    }

    private File createTempFile(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getApplication().getApplicationContext().getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(getApplication().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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
            File fileCompress = new Compressor(getApplication().getApplicationContext())
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
