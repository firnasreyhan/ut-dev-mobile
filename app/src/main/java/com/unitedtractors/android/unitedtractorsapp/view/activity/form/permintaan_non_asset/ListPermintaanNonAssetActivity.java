package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_non_asset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListPermintaanNonAssetBinding;

public class ListPermintaanNonAssetActivity extends AppCompatActivity {
    private ActivityListPermintaanNonAssetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPermintaanNonAssetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}