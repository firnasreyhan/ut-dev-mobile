package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.TokenModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SignUpViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;
    private Context context;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<BaseResponse> signUp(String username, String namaLengkap, String role, String departement, String division, String password, Bitmap signature) {
        RequestBody username_ = RequestBody.create(MediaType.parse("text/plain"), username);
        RequestBody namaLengkap_ = RequestBody.create(MediaType.parse("text/plain"), namaLengkap);
        RequestBody role_ = RequestBody.create(MediaType.parse("text/plain"), role);
        RequestBody departement_ = RequestBody.create(MediaType.parse("text/plain"), departement);
        RequestBody division_ = RequestBody.create(MediaType.parse("text/plain"), division);
        RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody token_ = RequestBody.create(MediaType.parse("text/plain"), updateToken(username));
        return onlineRepository.postSignUp(
                username_,
                namaLengkap_,
                role_,
                departement_,
                division_,
                password_,
                compressFile(signature, "signature"),
                token_
        );
    }

    private String updateToken(String username) {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        String userKey = username.replaceAll("[-+.^:,]","");
        Log.e("userKey", userKey);
        TokenModel token = new TokenModel(refreshToken);
        FirebaseDatabase.getInstance().getReference("UnitedTractor").child("Token").child(userKey).setValue(token);
        return refreshToken;
    }

    private File createTempFile(Bitmap bitmap) {
        bitmap.getScaledWidth(600);
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +".PNG");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
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

    private MultipartBody.Part compressFile(Bitmap bitmap, String path) {
        File file1 = createTempFile(scaleDown(bitmap, 600, true));
        Log.e("path", file1.getAbsolutePath());
        return MultipartBody.Part.createFormData(path, file1.getName(), RequestBody.create(MediaType.parse("image/*"), file1));
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }
}
