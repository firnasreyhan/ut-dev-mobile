package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.ChecklistForHydrantAdapter;
import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDieselHydrantPumpBinding;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForHydrantModel;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DieselHydrantPumpActivity extends AppCompatActivity {
    private ActivityDieselHydrantPumpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDieselHydrantPumpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String idMapping = getIntent().getStringExtra("ID_MAPPING");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String lokasi = getIntent().getStringExtra("LOKASI");
        List<ChecklistForHydrantModel.DetailChecklistHydrant> systemPemipaan = (List<ChecklistForHydrantModel.DetailChecklistHydrant>) getIntent().getSerializableExtra("SYSTEM_PEMIPAAN");
        List<ChecklistForHydrantModel.DetailChecklistHydrant> jockeyPump = (List<ChecklistForHydrantModel.DetailChecklistHydrant>) getIntent().getSerializableExtra("JOCKEY_PUMP");
        List<ChecklistForHydrantModel.DetailChecklistHydrant> electricPump = (List<ChecklistForHydrantModel.DetailChecklistHydrant>) getIntent().getSerializableExtra("ELECTRIC_PUMP");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<ChecklistForHydrantModel.DetailChecklistHydrant> list = new ArrayList<>();
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Gate Valve",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Air Radiator",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Oli Mesin Diesel",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Tali Kipas (Fan Belt)",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Air Battery",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Putaran Mesin RPM",
                1
        ));
        list.add(new ChecklistForHydrantModel.DetailChecklistHydrant(
                "Test Running",
                1
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ChecklistForHydrantAdapter(list));

        binding.materialButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PanelHydrantActivity.class);
                intent.putExtra("ID_MAPPING", idMapping);
                intent.putExtra("TANGGAL", tanggal);
                intent.putExtra("LOKASI", lokasi);
                intent.putExtra("SYSTEM_PEMIPAAN", (Serializable) systemPemipaan);
                intent.putExtra("JOCKEY_PUMP", (Serializable) jockeyPump);
                intent.putExtra("ELECTRIC_PUMP", (Serializable) electricPump);
                intent.putExtra("DIESEL_HYDRANT_PUMP", (Serializable) ChecklistForHydrantAdapter.getList());
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