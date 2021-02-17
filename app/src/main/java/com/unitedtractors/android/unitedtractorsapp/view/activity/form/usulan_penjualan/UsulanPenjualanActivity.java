package com.unitedtractors.android.unitedtractorsapp.view.activity.form.usulan_penjualan;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityUsulanPenjualanBinding;

public class UsulanPenjualanActivity extends AppCompatActivity {
    private ActivityUsulanPenjualanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsulanPenjualanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}