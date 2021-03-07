package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentApprovalProgresBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ApprovalListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ApprovalProgresFragment extends Fragment {
    private FragmentApprovalProgresBinding binding;
    private ApprovalListViewModel viewModel;
    private boolean cekSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentApprovalProgresBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(this).get(ApprovalListViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.textInputEditTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
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

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
        viewModel.getTransaction(
                AppPreference.getUser(getActivity()).getUserUsers(),
                -1,
                true
        ).observe(this, new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                binding.shimmerFrameLayout.stopShimmer();
                binding.shimmerFrameLayout.setVisibility(View.GONE);
                binding.linearLayoutNoData.setVisibility(View.GONE);
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        binding.recyclerView.setVisibility(View.VISIBLE);
                        binding.recyclerView.setAdapter(new ApprovalAdapter(transactionResponse.getData(), AppPreference.getUser(getActivity()).getRoleUsers().equalsIgnoreCase("staff") ? false : true));
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
        viewModel.getTransaction(
                AppPreference.getUser(getActivity()).getUserUsers(),
                -1,
                true
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
                        binding.recyclerView.setAdapter(new ApprovalAdapter(filterList, AppPreference.getUser(getActivity()).getRoleUsers().equalsIgnoreCase("staff") ? false : true));

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