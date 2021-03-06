package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistForGensetBinding;

public class ChecklistForGensetActivity extends AppCompatActivity {
    private ActivityChecklistForGensetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChecklistForGensetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}