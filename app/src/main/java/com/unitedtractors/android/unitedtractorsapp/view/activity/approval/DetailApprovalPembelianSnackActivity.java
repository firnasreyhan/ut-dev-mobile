package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDetailApprovalPembelianSnackBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.DetailApprovalPembelianSnackViewModel;

public class DetailApprovalPembelianSnackActivity extends AppCompatActivity {
    private ActivityDetailApprovalPembelianSnackBinding binding;
    private DetailApprovalPembelianSnackViewModel viewModel;

    private String idTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailApprovalPembelianSnackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        idTrans = getIntent().getStringExtra("ID_TRANS");

        viewModel = ViewModelProviders.of(this).get(DetailApprovalPembelianSnackViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (AppPreference.getUser(this).getRoleUsers().equalsIgnoreCase("staff")) {
            binding.linearLayoutButton.setVisibility(View.GONE);
        } else {
            binding.linearLayoutButton.setVisibility(View.VISIBLE);

            binding.materialButtonApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.putConfirm(
                            AppPreference.getUser(v.getContext()).getUserUsers(),
                            idTrans,
                            true
                    ).observe(DetailApprovalPembelianSnackActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            binding.linearLayoutButton.setVisibility(View.GONE);

                            binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                            binding.textViewStatus.setText("Approved");
                        }
                    });
                }
            });

            binding.materialButtonReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.putConfirm(
                            AppPreference.getUser(v.getContext()).getUserUsers(),
                            "",
                            false
                    ).observe(DetailApprovalPembelianSnackActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            binding.linearLayoutButton.setVisibility(View.GONE);

                            binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                            binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                            binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                            binding.textViewStatus.setText("Rejected");
                        }
                    });
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}