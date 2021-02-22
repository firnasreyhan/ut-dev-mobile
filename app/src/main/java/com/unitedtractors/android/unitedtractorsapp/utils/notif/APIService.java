package com.unitedtractors.android.unitedtractorsapp.utils.notif;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAeaYXr4M:APA91bHbyfgMLwsCBGeYRTNC2oXGq_al-hKDVIyK-DxV8cljHqxIhpF9Rp27AJGxS1gDeICW6dx04WnPEoBEzlrm_pIa5NVm7sbn3McSzfrg2hvl3EN9Afg0KaZWTv7EMyG2-hjRbVQJ" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
}