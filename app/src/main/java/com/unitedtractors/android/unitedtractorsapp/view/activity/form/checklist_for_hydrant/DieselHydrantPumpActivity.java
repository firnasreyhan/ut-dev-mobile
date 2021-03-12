package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDieselHydrantPumpBinding;
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
        List<Pertanyaan3Model> systemPemipaan = (List<Pertanyaan3Model>) getIntent().getSerializableExtra("SYSTEM_PEMIPAAN");
        List<Pertanyaan3Model> jockeyPump = (List<Pertanyaan3Model>) getIntent().getSerializableExtra("JOCKEY_PUMP");
        List<Pertanyaan3Model> electricPump = (List<Pertanyaan3Model>) getIntent().getSerializableExtra("ELECTRIC_PUMP");

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan3Model> list = new ArrayList<>();
        list.add(new Pertanyaan3Model(
                "Gate Valve",
                1,
                "*Standard: Air Pancingan selalu pada posisi ‘OPEN’",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Air Radiator",
                1,
                "*Standard: Harus penuh (Tidak boleh kosong)",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Oli Mesin Diesel",
                1,
                "*Standard: Pada level yang ditentukan",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Tali Kipas (Fan Belt)",
                1,
                "*Standard: Tidak retas dan kendor (ganti jika perlu)",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Air Battery",
                1,
                "*Standard: Pada level yang ditentukan",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Putaran Mesin RPM",
                1,
                "*Standard: Sesuai kebutuhan, setting (Bila perlu)",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Test Running",
                1,
                "*Standard: Laksanakan 2 minggu sekali",
                ""
        ));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new Pertanyaan3Adapter(list));

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
                intent.putExtra("DIESEL_HYDRANT_PUMP", (Serializable) list(Pertanyaan3Adapter.getList()));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private List<Pertanyaan3Model> list(List<Pertanyaan3Model> list) {
        for (Pertanyaan3Model model : list) {
            if (model.getCatatan().isEmpty()) {
                model.setCatatan("-");
            }
        }
        return list;
    }
}