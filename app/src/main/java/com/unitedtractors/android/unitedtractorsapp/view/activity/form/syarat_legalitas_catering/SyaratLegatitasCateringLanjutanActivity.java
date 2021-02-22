package com.unitedtractors.android.unitedtractorsapp.view.activity.form.syarat_legalitas_catering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySyaratLegalitasCateringBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySyaratLegatitasCateringLanjutanBinding;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ScreenFeedbackActivity;

public class SyaratLegatitasCateringLanjutanActivity extends AppCompatActivity {
    private ActivitySyaratLegatitasCateringLanjutanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySyaratLegatitasCateringLanjutanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScreenFeedbackActivity.class);
                startActivity(intent);
            }
        });
    }
}