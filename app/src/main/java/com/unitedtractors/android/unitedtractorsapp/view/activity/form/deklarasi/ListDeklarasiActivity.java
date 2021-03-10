package com.unitedtractors.android.unitedtractorsapp.view.activity.form.deklarasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListDeklarasiBinding;

public class ListDeklarasiActivity extends AppCompatActivity {
    private ActivityListDeklarasiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListDeklarasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}