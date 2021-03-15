package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.MingguAdapter;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityNewChecklistPompaAirBersihBinding;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ChecklistPompaAirBersihViewModel;

import java.util.List;

public class NewChecklistPompaAirBersihActivity extends AppCompatActivity {
    private ActivityNewChecklistPompaAirBersihBinding binding;
    private ChecklistPompaAirBersihViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewChecklistPompaAirBersihBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(ChecklistPompaAirBersihViewModel.class);

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel.getMinggu().observe(this, new Observer<List<MingguEntity>>() {
            @Override
            public void onChanged(List<MingguEntity> mingguEntities) {
                if (mingguEntities.isEmpty()) {
                    viewModel.insertAlarm(mingguEntity("Minggu Ke 1", false));
                    viewModel.insertAlarm(mingguEntity("Minggu Ke 2", false));
                    viewModel.insertAlarm(mingguEntity("Minggu Ke 3", false));
                    viewModel.insertAlarm(mingguEntity("Minggu Ke 4", false));
                } else {
                    binding.recyclerView.setHasFixedSize(true);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(NewChecklistPompaAirBersihActivity.this));
                    binding.recyclerView.setAdapter(new MingguAdapter(mingguEntities));
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private MingguEntity mingguEntity(String mingguKe, boolean status) {
        MingguEntity entity = new MingguEntity();
        entity.mingguKe = mingguKe;
        entity.status = status;
        return entity;
    }
}