package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_pond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityChecklistPompaPondBinding;

public class ChecklistPompaPondActivity extends AppCompatActivity {
    private ActivityChecklistPompaPondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChecklistPompaPondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}