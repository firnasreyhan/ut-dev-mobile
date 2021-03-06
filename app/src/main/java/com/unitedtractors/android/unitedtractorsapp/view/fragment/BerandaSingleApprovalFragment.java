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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.unitedtractors.android.unitedtractorsapp.adapter.ApprovalAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.DebitNoteAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.TaskAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.DebitNoteResponse;
import com.unitedtractors.android.unitedtractorsapp.api.response.TransactionResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentBerandaSingleApprovalBinding;
import com.unitedtractors.android.unitedtractorsapp.model.TaskModel;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ListApprovalActivity;
import com.unitedtractors.android.unitedtractorsapp.view.activity.ListDebitNoteActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class BerandaSingleApprovalFragment extends Fragment {
    private FragmentBerandaSingleApprovalBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBerandaSingleApprovalBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        binding.textViewNamaUser.setText(AppPreference.getUser(getActivity()).getNamaUsers());

        binding.recyclerViewApproval.setHasFixedSize(true);
        binding.recyclerViewApproval.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewDebitNote.setHasFixedSize(true);
        binding.recyclerViewDebitNote.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewTask.setHasFixedSize(true);
        binding.recyclerViewTask.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<TaskModel> list = new ArrayList<>();
        list.add(new TaskModel("40"));
        list.add(new TaskModel("20"));

        binding.recyclerViewTask.setAdapter(new TaskAdapter(list));

        binding.textViewAllApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(v.getContext(), ListApprovalActivity.class));
                intent.putExtra("IS_APPROVAL", true);
                startActivity(intent);
            }
        });

        if (AppPreference.getUser(getContext()).getRoleUsers().equalsIgnoreCase("Department Head")) {
            binding.linearLayoutDebitNote.setVisibility(View.VISIBLE);

            binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    binding.recyclerViewApproval.setVisibility(View.GONE);
                    binding.linearLayoutNoDataApproval.setVisibility(View.GONE);
                    binding.recyclerViewDebitNote.setVisibility(View.GONE);
                    binding.linearLayoutNoDataDebitNote.setVisibility(View.GONE);
                    getForm();
                    getDebitNote();
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 3000);
                }
            });

            binding.textViewAllDebitNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(new Intent(v.getContext(), ListDebitNoteActivity.class));
                    startActivity(intent);
                }
            });
        } else {
            binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    binding.recyclerViewApproval.setVisibility(View.GONE);
                    binding.linearLayoutNoDataApproval.setVisibility(View.GONE);
                    getForm();
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 3000);
                }
            });
        }

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
        binding.recyclerViewApproval.setVisibility(View.GONE);
        binding.linearLayoutNoDataApproval.setVisibility(View.GONE);
        getForm();

        if (AppPreference.getUser(getContext()) != null) {
            if (AppPreference.getUser(getContext()).getRoleUsers().equalsIgnoreCase("Department Head")) {
                binding.recyclerViewDebitNote.setVisibility(View.GONE);
                binding.linearLayoutNoDataDebitNote.setVisibility(View.GONE);
                getDebitNote();
            }
        }
    }

    @Override
    public void onPause() {
        binding.shimmerFrameLayoutApproval.stopShimmer();
        if (AppPreference.getUser(getContext()) != null) {
            if (AppPreference.getUser(getContext()).getRoleUsers().equalsIgnoreCase("Department Head")) {
                binding.shimmerFrameLayoutDebitNote.stopShimmer();
            }
        }
        super.onPause();
    }

    public void getForm() {
        binding.shimmerFrameLayoutApproval.startShimmer();
        binding.shimmerFrameLayoutApproval.setVisibility(View.VISIBLE);
        binding.textViewJumlahForm.setText("0 Form");
        
        viewModel.getTransaction(
                AppPreference.getUser(getActivity()).getUserUsers(),
                -1,
                true
        ).observe(getActivity(), new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                if (transactionResponse != null) {
                    if (transactionResponse.isStatus()) {
                        int i = 0;
                        for (TransactionResponse.TransactionModel model : transactionResponse.getData()) {
                            if (model.getStatTrans() == null) {
                                i++;
                            }
                        }
                        binding.textViewJumlahForm.setText(i + " Form");
                    }
                }
            }
        });

        viewModel.getTransaction(
                AppPreference.getUser(getActivity()).getUserUsers(),
                2,
                true
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
                        binding.recyclerViewApproval.setAdapter(new ApprovalAdapter(transactionResponse.getData()));
                    } else {
                        binding.linearLayoutNoDataApproval.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoDataApproval.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void getDebitNote() {
        binding.shimmerFrameLayoutDebitNote.startShimmer();
        binding.shimmerFrameLayoutDebitNote.setVisibility(View.VISIBLE);

        viewModel.getDebitNote(
                AppPreference.getUser(getActivity()).getUserUsers(),
                2
        ).observe(getActivity(), new Observer<DebitNoteResponse>() {
            @Override
            public void onChanged(DebitNoteResponse debitNoteResponse) {
                binding.shimmerFrameLayoutDebitNote.stopShimmer();
                binding.shimmerFrameLayoutDebitNote.setVisibility(View.GONE);
                binding.linearLayoutNoDataDebitNote.setVisibility(View.GONE);
                binding.recyclerViewDebitNote.setVisibility(View.GONE);
                if (debitNoteResponse != null) {
                    if (debitNoteResponse.isStatus()) {
                        binding.recyclerViewDebitNote.setVisibility(View.VISIBLE);
                        binding.recyclerViewDebitNote.setAdapter(new DebitNoteAdapter(debitNoteResponse.getData()));
                    } else {
                        binding.linearLayoutNoDataDebitNote.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.linearLayoutNoDataDebitNote.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}