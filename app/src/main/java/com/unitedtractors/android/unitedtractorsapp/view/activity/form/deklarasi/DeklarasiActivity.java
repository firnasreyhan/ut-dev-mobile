package com.unitedtractors.android.unitedtractorsapp.view.activity.form.deklarasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDeklarasiBinding;

public class DeklarasiActivity extends AppCompatActivity {
    private ActivityDeklarasiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeklarasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}