package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_pompa_air_bersih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.unitedtractors.android.unitedtractorsapp.R;
import com.unitedtractors.android.unitedtractorsapp.adapter.MingguAdapter;
import com.unitedtractors.android.unitedtractorsapp.database.entity.MingguEntity;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityNewChecklistPompaAirBersihBinding;
import com.unitedtractors.android.unitedtractorsapp.viewmodel.ChecklistPompaAirBersihViewModel;

import java.util.List;

public class NewChecklistPompaAirBersihActivity extends AppCompatActivity {
    private ActivityNewChecklistPompaAirBersihBinding binding;
    private ChecklistPompaAirBersihViewModel viewModel;

    private boolean isValid;

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

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(NewChecklistPompaAirBersihActivity.this));

        viewModel.getMinggu().observe(this, new Observer<List<MingguEntity>>() {
            @Override
            public void onChanged(List<MingguEntity> mingguEntities) {
                if (mingguEntities.isEmpty()) {
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 1", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 2", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 3", false));
                    viewModel.insertMinggu(mingguEntity("Minggu Ke 4", false));
                } else {
                    binding.recyclerView.setAdapter(new MingguAdapter(mingguEntities));

                    if (mingguEntities.get(0).status && mingguEntities.get(1).status && mingguEntities.get(2).status && mingguEntities.get(3).status) {
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                }
            }
        });

        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.materialButtonAjukan.setEnabled(true);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.primary));
                } else {
                    binding.materialButtonAjukan.setEnabled(false);
                    binding.materialButtonAjukan.setBackgroundColor(getResources().getColor(R.color.button_disable));
                }
            }
        });

        binding.materialButtonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    viewModel.deleteDetaiAlllMinggu();
                    viewModel.updateAllMinggu(false);

                    binding.checkBox.setChecked(false);
                } else {
                    Toast.makeText(NewChecklistPompaAirBersihActivity.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
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