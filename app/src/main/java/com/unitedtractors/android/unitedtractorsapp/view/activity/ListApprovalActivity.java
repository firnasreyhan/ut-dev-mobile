package com.unitedtractors.android.unitedtractorsapp.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListApprovalBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ApprovalListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListApprovalActivity extends AppCompatActivity {
    private ActivityListApprovalBinding binding;
    private ApprovalListViewModel viewModel;
    private boolean cekSearch, isApproval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListApprovalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        isApproval = getIntent().getBooleanExtra("IS_APPROVAL", false);

        viewModel = ViewModelProviders.of(this).get(ApprovalListViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.textInputEditTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.linearLayoutNoData.setVisibility(View.GONE);

                    filterData(binding.textInputEditTextSearch.getText().toString());
                    cekSearch = true;
                }
                return false;
            }
        });

        binding.textInputLayoutSearch.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textInputEditTextSearch.getText().clear();
                if (cekSearch) {
                    getData();
                    cekSearch = false;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!cekSearch) {
            getData();
        }
    }

    @Override
    public void onPause() {
        binding.shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    public void getData() {
        binding.shimmerFrameLayout.startShimmer();
        binding.shimmerFrameLayout.setVisibility(View.VISIBLE);

        viewModel.getTransaction(
                AppPreference.getUser(this).getUserUsers(),
                -1,
                isApproval
        ).observe(this, new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                binding.shimmerFrameLayout.stopShimmer();
                binding.shimmerFrameLayout.setVisibility(View.GONE);
                binding.linearLayoutNoData.setVisibility(View.GONE);
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        binding.recyclerView.setVisibility(View.VISIBLE);
                        binding.recyclerView.setAdapter(new ApprovalAdapter(transactionResponse.getData(), isApproval));
                    } else {
                        binding.linearLayoutNoData.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void filterData(String filter) {
        binding.shimmerFrameLayout.startShimmer();
        binding.shimmerFrameLayout.setVisibility(View.VISIBLE);

        viewModel.getTransaction(
                AppPreference.getUser(this).getUserUsers(),
                -1,
                isApproval
        ).observe(this, new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                binding.shimmerFrameLayout.stopShimmer();
                binding.shimmerFrameLayout.setVisibility(View.GONE);
                binding.linearLayoutNoData.setVisibility(View.GONE);
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        List<TransactionResponse.TransactionModel> filterList = new ArrayList<>();
                        for (TransactionResponse.TransactionModel model : transactionResponse.getData()) {
                            if (model.getNamaForm().toLowerCase().contains(filter.toLowerCase()) || model.getNamaUsers().toLowerCase().contains(filter.toLowerCase())) {
                                filterList.add(model);
                            }
                        }
                        binding.recyclerView.setVisibility(View.VISIBLE);
                        binding.recyclerView.setAdapter(new ApprovalAdapter(filterList, isApproval));

                        if (filterList.isEmpty()) {
                            binding.linearLayoutNoData.setVisibility(View.VISIBLE);
                        }
                    } else {
                        binding.linearLayoutNoData.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}