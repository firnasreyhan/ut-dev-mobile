package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanAssetBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySplashScreenBinding;

public class PermintaanAssetActivity extends AppCompatActivity {
    private ActivityPermintaanAssetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanAssetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}