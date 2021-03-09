package com.unitedtractors.android.unitedtractorsapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.FormAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListFormBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.FormListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFormActivity extends AppCompatActivity {
    private ActivityListFormBinding binding;
    private FormListViewModel viewModel;
    private boolean cekSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(FormListViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.recyclerViewForm.setHasFixedSize(true);
        binding.recyclerViewForm.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));

        binding.textInputEditTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    binding.recyclerViewForm.setVisibility(View.GONE);
                    binding.linearLayoutNoDataForm.setVisibility(View.GONE);

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
        binding.shimmerFrameLayoutForm.stopShimmer();
        super.onPause();
    }

    public void getData() {
        binding.shimmerFrameLayoutForm.startShimmer();
        binding.shimmerFrameLayoutForm.setVisibility(View.VISIBLE);

        viewModel.getForm(
                AppPreference.getUser(this).getRoleUsers()
        ).observe(this, new Observer<FormResponse>() {
            @Override
            public void onChanged(FormResponse formResponse) {
                binding.shimmerFrameLayoutForm.stopShimmer();
                binding.shimmerFrameLayoutForm.setVisibility(View.GONE);
                binding.linearLayoutNoDataForm.setVisibility(View.GONE);
                if (formResponse != null) {
                    if (formResponse.isStatus()) {
                        binding.recyclerViewForm.setVisibility(View.VISIBLE);
                        binding.recyclerViewForm.setAdapter(new FormAdapter(formResponse.getData()));
                    } else {
                        binding.linearLayoutNoDataForm.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoDataForm.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void filterData(String filter) {
        binding.shimmerFrameLayoutForm.startShimmer();
        binding.shimmerFrameLayoutForm.setVisibility(View.VISIBLE);

        viewModel.getForm(
                AppPreference.getUser(this).getRoleUsers()
        ).observe(this, new Observer<FormResponse>() {
            @Override
            public void onChanged(FormResponse formResponse) {
                binding.shimmerFrameLayoutForm.stopShimmer();
                binding.shimmerFrameLayoutForm.setVisibility(View.GONE);
                binding.linearLayoutNoDataForm.setVisibility(View.GONE);
                if (formResponse != null) {
                    if (formResponse.isStatus()) {
                        List<FormResponse.FormModel> filterList = new ArrayList<>();
                        for (FormResponse.FormModel model : formResponse.getData()) {
                            if (model.getNamaForm().toLowerCase().contains(filter.toLowerCase())) {
                                filterList.add(model);
                            }
                        }
                        binding.recyclerViewForm.setVisibility(View.VISIBLE);
                        binding.recyclerViewForm.setAdapter(new FormAdapter(filterList));

                        if (filterList.isEmpty()) {
                            binding.linearLayoutNoDataForm.setVisibility(View.VISIBLE);
                        }
                    } else {
                        binding.linearLayoutNoDataForm.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoDataForm.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}