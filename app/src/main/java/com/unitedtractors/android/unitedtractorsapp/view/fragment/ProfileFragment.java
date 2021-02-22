package com.unitedtractors.android.unitedtractorsapp.view.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.FragmentProfileBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.view.activity.SignInActivity;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.MainViewModel;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private MainViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        progressDialog = new ProgressDialog(getContext());

        binding.textViewNamaUser.setText(AppPreference.getUser(getContext()).getNamaUsers());
        binding.textViewRoleuser.setText(AppPreference.getUser(getContext()).getRoleUsers());
        binding.textViewDepartementUser.setText(AppPreference.getUser(getContext()).getDeptUsers());
        binding.textViewDivisiUser.setText(AppPreference.getUser(getContext()).getDivUsers());

        binding.materialButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Pesan")
                        .setMessage("Yakin ingin keluar dari aplikasi?")
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                progressDialog.setMessage("Mohon tunggu sebentar...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                int loadingTime = 3000;
                                new Handler().postDelayed(() -> {
                                    viewModel.signOut().observe(getActivity(), new Observer<BaseResponse>() {
                                        @Override
                                        public void onChanged(BaseResponse baseResponse) {
                                            if (progressDialog.isShowing()) {
                                                progressDialog.dismiss();
                                            }
                                            Toast.makeText(getContext(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                            if (baseResponse.isStatus()) {
                                                AppPreference.removeUser(getContext());
                                                Intent intent = new Intent(getContext(), SignInActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                }, loadingTime);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}