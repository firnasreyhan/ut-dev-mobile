package com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_layout_acara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPermintaanLayoutAcaraBinding;

public class PermintaanLayoutAcaraActivity extends AppCompatActivity {
    private ActivityPermintaanLayoutAcaraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermintaanLayoutAcaraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}