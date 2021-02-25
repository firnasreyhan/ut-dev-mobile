package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.TaskAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentBerandaPICBinding;
import com.unitedtractors.android.unitedtractorsapp.model.TaskModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ListApprovalActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class BerandaPICFragment extends Fragment {
    private FragmentBerandaPICBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBerandaPICBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        binding.textViewNamaUser.setText(AppPreference.getUser(getActivity()).getNamaUsers());

        binding.recyclerViewApproval.setHasFixedSize(true);
        binding.recyclerViewApproval.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewTask.setHasFixedSize(true);
        binding.recyclerViewTask.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        binding.shimmerFrameLayoutApproval.startShimmer();

        viewModel.getTransaction(
                AppPreference.getUser(getActivity()).getUserUsers(),
                -1
        ).observe(getActivity(), new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        binding.textViewJumlahForm.setText(transactionResponse.getData().size() + " Form");
                    }
                }
            }
        });

        viewModel.getTransaction(
                AppPreference.getUser(getActivity()).getUserUsers(),
                2
        ).observe(getActivity(), new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                binding.shimmerFrameLayoutApproval.stopShimmer();
                binding.shimmerFrameLayoutApproval.setVisibility(View.GONE);
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        binding.recyclerViewApproval.setVisibility(View.VISIBLE);
                        binding.recyclerViewApproval.setAdapter(new ApprovalAdapter(transactionResponse.getData(), true));
                    } else {
                        binding.linearLayoutNoDataApproval.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoDataApproval.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onPause() {
        binding.shimmerFrameLayoutApproval.stopShimmer();
        super.onPause();
    }
}