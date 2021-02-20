package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDetailApprovalPembelianSnackBinding;

public class DetailApprovalPembelianSnackActivity extends AppCompatActivity {
    private ActivityDetailApprovalPembelianSnackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailApprovalPembelianSnackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.materialButtonApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.linearLayoutButton.setVisibility(View.GONE);

                binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgApprove));
                binding.textViewStatus.setTextColor(getResources().getColor(R.color.approve));
                binding.textViewStatus.setText("Approved");
            }
        });

        binding.materialButtonReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.linearLayoutButton.setVisibility(View.GONE);

                binding.linearLayoutStatus.setVisibility(View.VISIBLE);
                binding.linearLayoutStatus.setBackgroundColor(getResources().getColor(R.color.bgReject));
                binding.textViewStatus.setTextColor(getResources().getColor(R.color.reject));
                binding.textViewStatus.setText("Rejected");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}