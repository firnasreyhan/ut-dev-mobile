package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.BuildConfig;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.textViewVersion.setText("Ver " + BuildConfig.VERSION_NAME);

        toMainActivity();
    }

    private void toMainActivity() {
        int loadingTime = 3000;
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
            finish();
        }, loadingTime);
    }
}