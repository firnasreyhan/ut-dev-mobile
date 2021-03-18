package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityRepeaterRadioBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityTroublesInformation2Binding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityTroublesInformationBinding;

public class TroublesInformationActivity extends AppCompatActivity {
    private ActivityTroublesInformation2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTroublesInformation2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}