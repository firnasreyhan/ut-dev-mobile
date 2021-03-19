package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_alat_komunikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPabxBinding;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityRepeaterRadioBinding;

public class RepeaterRadioActivity extends AppCompatActivity {
    private ActivityRepeaterRadioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRepeaterRadioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TroublesInformationActivity.class);
                startActivity(intent);
            }
        });
    }
}