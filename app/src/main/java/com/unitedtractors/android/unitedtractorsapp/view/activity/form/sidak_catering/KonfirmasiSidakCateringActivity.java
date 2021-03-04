package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKonfirmasiSidakCateringBinding;

public class KonfirmasiSidakCateringActivity extends AppCompatActivity {
    private ActivityKonfirmasiSidakCateringBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKonfirmasiSidakCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}