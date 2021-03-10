package com.unitedtractors.android.unitedtractorsapp.view.activity.form.surat_jalan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySuratJalanBinding;

public class SuratJalanActivity extends AppCompatActivity {
    private ActivitySuratJalanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuratJalanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}