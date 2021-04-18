package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalProgressAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.DebitNoteAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.DebitNoteResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListDebitNoteBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ApprovalListViewModel;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.DebitNoteListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListDebitNoteActivity extends AppCompatActivity {
    private ActivityListDebitNoteBinding binding;
    private DebitNoteListViewModel viewModel;
    private boolean cekSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListDebitNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(DebitNoteListViewModel.class);

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

        viewModel.getDebitNote(
                AppPreference.getUser(this).getUserUsers(),
                -1
        ).observe(this, new Observer<DebitNoteResponse>() {
            @Override
            public void onChanged(DebitNoteResponse debitNoteResponse) {
                binding.shimmerFrameLayout.stopShimmer();
                binding.shimmerFrameLayout.setVisibility(View.GONE);
                binding.linearLayoutNoData.setVisibility(View.GONE);
                if (debitNoteResponse != null) {
                    if (debitNoteResponse.isStatus()) {
                        binding.recyclerView.setVisibility(View.VISIBLE);
                        binding.recyclerView.setAdapter(new DebitNoteAdapter(debitNoteResponse.getData()));
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

        viewModel.getDebitNote(
                AppPreference.getUser(this).getUserUsers(),
                -1
        ).observe(this, new Observer<DebitNoteResponse>() {
            @Override
            public void onChanged(DebitNoteResponse debitNoteResponse) {
                binding.shimmerFrameLayout.stopShimmer();
                binding.shimmerFrameLayout.setVisibility(View.GONE);
                binding.linearLayoutNoData.setVisibility(View.GONE);
                if (debitNoteResponse != null) {
                    if (debitNoteResponse.isStatus()) {
                        List<DebitNoteResponse.DetailDebitNote> filterList = new ArrayList<>();
                        for (DebitNoteResponse.DetailDebitNote model : debitNoteResponse.getData()) {
                            if (model.getEmailDebitNote().toLowerCase().contains(filter.toLowerCase())) {
                                filterList.add(model);
                            }
                        }
                        binding.recyclerView.setVisibility(View.VISIBLE);
                        binding.recyclerView.setAdapter(new DebitNoteAdapter(filterList));

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