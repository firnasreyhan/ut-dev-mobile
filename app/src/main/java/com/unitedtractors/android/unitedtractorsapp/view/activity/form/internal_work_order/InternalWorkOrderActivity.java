package com.unitedtractors.android.unitedtractorsapp.view.activity.form.internal_work_order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityInternalWorkOrderBinding;

public class InternalWorkOrderActivity extends AppCompatActivity {
    private ActivityInternalWorkOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}