package com.unitedtractors.android.unitedtractorsapp.view.activity.form.perbaikan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityKeteranganPerbaikanBinding;

public class KeteranganPerbaikanActivity extends AppCompatActivity {
    private ActivityKeteranganPerbaikanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKeteranganPerbaikanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}