package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.ChecklistForHydrantAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan2Adapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivitySystemPemipaanBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForHydrantModel;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan2Model;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;
import com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_genset.K5Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SystemPemipaanActivity extends AppCompatActivity {
    private ActivitySystemPemipaanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySystemPemipaanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String lokasi = getIntent().getStringExtra("LOKASI");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<ChecklistForHydrantModel.DetailChecklistHydrant> list = new ArrayList<>();
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Kebocoran Pemipaan",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Flange / Packing",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Posisi Valve",
                1
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ChecklistForHydrantAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), JockeyPumpActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TANGGAL", tanggal);
                intent.putExtra("LOKASI", lokasi);
                intent.putExtra("SYSTEM_PEMIPAAN", (Serializable) ChecklistForHydrantAdapter.getList());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}