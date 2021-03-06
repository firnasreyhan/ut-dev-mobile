package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_pond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityPompaPondBBinding;

public class PompaPondBActivity extends AppCompatActivity {
    private ActivityPompaPondBBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPompaPondBBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}