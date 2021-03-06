package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistForHydrantBinding;

public class ChecklistForHydrantActivity extends AppCompatActivity {
    private ActivityChecklistForHydrantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChecklistForHydrantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}