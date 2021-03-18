package com.unitedtractors.android.unitedtractorsapp.view.activity.form.survery_kepuasan_pelanggan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDetailSurveyKepuasanPelangganBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySurveryKepuasanPelangganBinding;

public class DetailSurveyKepuasanPelangganActivity extends AppCompatActivity {
    private ActivityDetailSurveyKepuasanPelangganBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailSurveyKepuasanPelangganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}