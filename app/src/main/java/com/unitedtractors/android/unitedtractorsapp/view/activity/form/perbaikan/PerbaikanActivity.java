package com.unitedtractors.android.unitedtractorsapp.view.activity.form.perbaikan;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPerbaikanBinding;

public class PerbaikanActivity extends AppCompatActivity {
    private ActivityPerbaikanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerbaikanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}