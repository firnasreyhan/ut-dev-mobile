package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_pond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPompaPondDBinding;

public class PompaPondDActivity extends AppCompatActivity {
    private ActivityPompaPondDBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPompaPondDBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}