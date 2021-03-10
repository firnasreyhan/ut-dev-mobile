package com.unitedtractors.android.unitedtractorsapp.view.activity.form.internal_work_order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListInternalWorkOrderBinding;

public class ListInternalWorkOrderActivity extends AppCompatActivity {
    private ActivityListInternalWorkOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListInternalWorkOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}