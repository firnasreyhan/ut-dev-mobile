package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.FormAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.TaskAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.FormResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentBerandaBinding;
import com.unitedtractors.android.unitedtractorsapp.model.TaskModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ListApprovalActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.pembelian_snack.PembelianSnackActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_asset.PermintaanAssetActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.permintaan_mobil_dinas.PermintaanMobilDinasActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class BerandaFragment extends Fragment {
    private FragmentBerandaBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBerandaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        binding.textViewNamaUser.setText(AppPreference.getUser(getActivity()).getNamaUsers());
        binding.recyclerViewApproval.setHasFixedSize(true);
        binding.recyclerViewApproval.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewTask.setHasFixedSize(true);
        binding.recyclerViewTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewForm.setHasFixedSize(true);
        binding.recyclerViewForm.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));

        List<TaskModel> list = new ArrayList<>();
        list.add(new TaskModel("40"));
        list.add(new TaskModel("20"));

        binding.recyclerViewTask.setAdapter(new TaskAdapter(list));
        binding.textViewAllApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ListApprovalActivity.class));
            }
        });

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.recyclerViewApproval.setVisibility(View.GONE);
                binding.linearLayoutNoDataApproval.setVisibility(View.GONE);
                binding.recyclerViewForm.setVisibility(View.GONE);
                binding.linearLayoutNoDataForm.setVisibility(View.GONE);
                getApprovalData();
                getFormData();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        binding.swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
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

        getApprovalData();
        getFormData();
    }

    public void getApprovalData() {
        binding.shimmerFrameLayoutApproval.startShimmer();
        binding.shimmerFrameLayoutApproval.setVisibility(View.VISIBLE);
        viewModel.getTransaction(
                AppPreference.getUser(getActivity()).getUserUsers(),
                1
        ).observe(getActivity(), new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                binding.shimmerFrameLayoutApproval.stopShimmer();
                binding.shimmerFrameLayoutApproval.setVisibility(View.GONE);
                binding.linearLayoutNoDataApproval.setVisibility(View.GONE);
                binding.recyclerViewApproval.setVisibility(View.GONE);
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        binding.recyclerViewApproval.setVisibility(View.VISIBLE);
                        binding.recyclerViewApproval.setAdapter(new ApprovalAdapter(transactionResponse.getData(), false));
                    } else {
                        binding.linearLayoutNoDataApproval.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoDataApproval.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void getFormData() {
        binding.shimmerFrameLayoutForm.startShimmer();
        binding.shimmerFrameLayoutForm.setVisibility(View.VISIBLE);

        viewModel.getForm(
                AppPreference.getUser(getActivity()).getDeptUsers()
        ).observe(getActivity(), new Observer<FormResponse>() {
            @Override
            public void onChanged(FormResponse formResponse) {
                binding.shimmerFrameLayoutForm.stopShimmer();
                binding.shimmerFrameLayoutForm.setVisibility(View.GONE);
                binding.linearLayoutNoDataForm.setVisibility(View.GONE);
                binding.recyclerViewForm.setVisibility(View.GONE);
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

    @Override
    public void onPause() {
        binding.shimmerFrameLayoutApproval.stopShimmer();
        binding.shimmerFrameLayoutForm.stopShimmer();
        super.onPause();
    }
}