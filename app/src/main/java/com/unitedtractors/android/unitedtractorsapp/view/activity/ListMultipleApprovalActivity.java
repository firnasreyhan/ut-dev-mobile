package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.TabAdapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListMultipleApprovalBinding;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.ApprovalFragment;
import com.unitedtractors.android.unitedtractorsapp.view.fragment.ApprovalProgresFragment;

public class ListMultipleApprovalActivity extends AppCompatActivity {
    private ActivityListMultipleApprovalBinding binding;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMultipleApprovalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
        tabAdapter.addFragment(new ApprovalFragment(), "Approval", R.drawable.ic_circle);
        tabAdapter.addFragment(new ApprovalProgresFragment(), "Progres", R.drawable.ic_circle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}