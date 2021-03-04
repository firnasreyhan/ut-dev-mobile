package com.unitedtractors.android.unitedtractorsapp.view.activity.form.sidak_catering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySidakCateringBinding;

public class SidakCateringActivity extends AppCompatActivity {
    private ActivitySidakCateringBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySidakCateringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}