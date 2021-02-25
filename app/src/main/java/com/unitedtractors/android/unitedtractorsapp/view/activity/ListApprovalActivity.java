package com.unitedtractors.android.unitedtractorsapp.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListApprovalBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ApprovalListViewModel;

public class ListApprovalActivity extends AppCompatActivity {
    private ActivityListApprovalBinding binding;
    private ApprovalListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListApprovalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(ApprovalListViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerFrameLayout.startShimmer();

        viewModel.getTransaction(
                AppPreference.getUser(this).getUserUsers(),
                -1
        ).observe(this, new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                binding.shimmerFrameLayout.stopShimmer();
                binding.shimmerFrameLayout.setVisibility(View.GONE);
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        binding.recyclerView.setVisibility(View.VISIBLE);
                        binding.recyclerView.setAdapter(new ApprovalAdapter(transactionResponse.getData(), AppPreference.getUser(ListApprovalActivity.this).getRoleUsers().equalsIgnoreCase("staff") ? false : true));
                    } else {
                        binding.linearLayoutNoData.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onPause() {
        binding.shimmerFrameLayout.stopShimmer();
        super.onPause();
    }
}