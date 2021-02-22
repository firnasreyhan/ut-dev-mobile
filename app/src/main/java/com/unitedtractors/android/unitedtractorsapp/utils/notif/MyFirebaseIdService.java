package com.unitedtractors.android.unitedtractorsapp.utils.notif;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;

public class MyFirebaseIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String userKey = AppPreference.getUser(getApplicationContext()).getUserUsers();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        if(userKey != null){
            updateToken(refreshToken);
        }
    }

    private void updateToken(String refreshToken) {
        String userKey = AppPreference.getUser(getApplicationContext()).getUserUsers();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("UnitedTractor").child("Token").child(userKey).setValue(token);
    }
}