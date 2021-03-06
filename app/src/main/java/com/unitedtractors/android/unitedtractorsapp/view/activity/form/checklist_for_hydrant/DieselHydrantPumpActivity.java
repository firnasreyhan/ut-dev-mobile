package com.unitedtractors.android.unitedtractorsapp.view.activity.form.checklist_for_hydrant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unitedtractors.android.unitedtractorsapp.adapter.Pertanyaan3Adapter;
import com.unitedtractors.android.unitedtractorsapp.databinding.ActivityDieselHydrantPumpBinding;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan3Model;

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

        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Pertanyaan3Model> list = new ArrayList<>();
        list.add(new Pertanyaan3Model(
                "Gate Valve",
                true,
                "*Standard: Air Pancingan selalu pada posisi ‘OPEN’",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Air Radiator",
                true,
                "*Standard: Harus penuh (Tidak boleh kosong)",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Oli Mesin Diesel",
                true,
                "*Standard: Pada level yang ditentukan",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Tali Kipas (Fan Belt)",
                true,
                "*Standard: Tidak retas dan kendor (ganti jika perlu)",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Air Battery",
                true,
                "*Standard: Pada level yang ditentukan",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Putaran Mesin RPM",
                true,
                "*Standard: Sesuai kebutuhan, setting (Bila perlu)",
                ""
        ));
        list.add(new Pertanyaan3Model(
                "Test Running",
                true,
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