package com.unitedtractors.android.unitedtractorsapp.view.activity.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.DokumenPendukungAdapter;
import com.unitedtractors.android.unitedtractorsapp.api.response.DokumenPendukungResponse;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityListDokumenPendukungBinding;
import com.unitedtractors.android.unitedtractorsapp.preference.AppPreference;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ListDokumenPendukungViewModel;

public class ListDokumenPendukungActivity extends AppCompatActivity {
    private ActivityListDokumenPendukungBinding binding;
    private ListDokumenPendukungViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListDokumenPendukungBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idTrans = getIntent().getStringExtra("ID_TRANS");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ListDokumenPendukungViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getDokumenPendukung(
                AppPreference.getUser(this).getIdUsers(),
                idTrans
        ).observe(this, new Observer<DokumenPendukungResponse>() {
            @Override
            public void onChanged(DokumenPendukungResponse dokumenPendukungResponse) {
                if (dokumenPendukungResponse != null) {
                    if (dokumenPendukungResponse.isStatus()) {
                        binding.recyclerView.setAdapter(new DokumenPendukungAdapter(dokumenPendukungResponse.getData()));
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}