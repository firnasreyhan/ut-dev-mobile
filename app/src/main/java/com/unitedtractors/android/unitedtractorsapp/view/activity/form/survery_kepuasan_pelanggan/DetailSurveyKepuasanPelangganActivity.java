package com.unitedtractors.android.unitedtractorsapp.view.activity.form.survery_kepuasan_pelanggan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDetailSurveyKepuasanPelangganBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySurveryKepuasanPelangganBinding;
import com.unitedtractors.android.unitedtractorsapp.model.SurveyKepuasanPelangganModel;

public class DetailSurveyKepuasanPelangganActivity extends AppCompatActivity {
    private ActivityDetailSurveyKepuasanPelangganBinding binding;

    SurveyKepuasanPelangganModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailSurveyKepuasanPelangganBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tglSurvey = getIntent().getStringExtra("TGL_SURVEY");
        String nama = getIntent().getStringExtra("NAM");
        String departemen = getIntent().getStringExtra("DEPARTEMEN");
        String divisi = getIntent().getStringExtra("DIVISI");


        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}